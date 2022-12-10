package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Color;
import java.io.File;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.com.jungla.sac.negocio.delegados.DelegadoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoCotizacion;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleCotizacion;
import co.com.jungla.sac.persistencia.entidades.Cotizacion;
import co.com.jungla.sac.persistencia.entidades.DetalleCotizacion;

import javax.swing.border.LineBorder;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo la visualizacion de las cotizaciones previamente consultadas
 * @author Luis Fernando Pedroza T.
 * @version: 01/09/2016
 */
public class VentMostrarCotizaciones extends JDialog {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtNumeroRegistros;
	private JTable tbCotizaciones;
	private DefaultTableModel modeloTbCotizaciones = new DefaultTableModel();
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private List<Cotizacion> listaCotizaciones;
	private int filaSeleccionada;

	/**
	 * Metodo constructor con un parametro para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentMostrarCotizaciones(List <Cotizacion> listaCotizaciones) {
		setTitle("Cotizaciones");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 1279, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 1245, 353);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JScrollPane scrProveedores = new JScrollPane();
		scrProveedores.setBounds(2, 2, 1241, 349);
		pn1.add(scrProveedores);
		
		tbCotizaciones = new JTable();
		tbCotizaciones.setToolTipText("Puede seleccionar cualquiera de las filas y presionar clic derecho para ver las acciones que puede realizar.");
		tbCotizaciones.setEnabled(false);
		tbCotizaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbCotizaciones.rowAtPoint(point);
		        tbCotizaciones.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		        mostrarPopupEnTabla();
			}
		});
		scrProveedores.setViewportView(tbCotizaciones);
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 375, 1245, 61);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JPanel pnNumeroRegistros = new JPanel();
		pnNumeroRegistros.setLayout(null);
		pnNumeroRegistros.setBackground(new Color(0, 51, 0));
		pnNumeroRegistros.setBounds(409, 11, 150, 38);
		pn2.add(pnNumeroRegistros);
		
		JLabel lblNumeroRegistros = new JLabel("Numero de registros");
		lblNumeroRegistros.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNumeroRegistros.setForeground(Color.WHITE);
		lblNumeroRegistros.setBackground(SystemColor.desktop);
		lblNumeroRegistros.setBounds(18, 0, 128, 14);
		pnNumeroRegistros.add(lblNumeroRegistros);
		
		txtNumeroRegistros = new JTextField();
		txtNumeroRegistros.setColumns(10);
		txtNumeroRegistros.setBounds(0, 18, 150, 20);
		txtNumeroRegistros.setEditable(false);
		pnNumeroRegistros.add(txtNumeroRegistros);
		
		JButton btnExportar = new JButton("Exportar a Excel");
		btnExportar.setForeground(new Color(0, 51, 0));
		btnExportar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExportar.setBounds(590, 18, 140, 23);
		pn2.add(btnExportar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setForeground(new Color(0, 51, 0));
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVolver.setBounds(766, 18, 104, 23);
		pn2.add(btnVolver);
		
		this.listaCotizaciones = listaCotizaciones;
		
		llenarTabla(this.listaCotizaciones);
		calcularCantidadItems();
		
		setModal(true);
		
	}
	
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbArticulos() {
		
		modeloTbCotizaciones.addColumn("Consecutivo");
		modeloTbCotizaciones.addColumn("Fecha");
		modeloTbCotizaciones.addColumn("Subtotal");
		modeloTbCotizaciones.addColumn("Descuento");
		modeloTbCotizaciones.addColumn("Total");
		modeloTbCotizaciones.addColumn("Items");
		modeloTbCotizaciones.addColumn("Dias Validez");
		modeloTbCotizaciones.addColumn("Identificación");
		modeloTbCotizaciones.addColumn("Cliente");
		modeloTbCotizaciones.addColumn("Contacto");
		modeloTbCotizaciones.addColumn("Ciudad");
		modeloTbCotizaciones.addColumn("Dirección");
		modeloTbCotizaciones.addColumn("Telefono");
		modeloTbCotizaciones.addColumn("Observaciones");
		
		tbCotizaciones.setModel(modeloTbCotizaciones);
	}
	
	//Metodo para llenar la tabla cuando recibe como parametro una lista de proveedores
	private void llenarTabla( List <Cotizacion> listaCotizaciones) {

		if(tbCotizaciones.getModel().getColumnCount()==0){
			llenarColumnasTbArticulos();
		}
		
		String [] fila = new String[modeloTbCotizaciones.getColumnCount()];
		
		for(Cotizacion cotizaciones : listaCotizaciones){
			
			fila[0]= String.valueOf(cotizaciones.getIdCotizacion());
			fila[1]= convertirDateAString(cotizaciones.getFecha());
			fila[2]= formatearNumero(cotizaciones.getSubtotal());
			fila[3]= formatearNumero(cotizaciones.getDescuento());
			fila[4]= formatearNumero(cotizaciones.getTotalCotizado());
			fila[5]= String.valueOf(cotizaciones.getItems());
			fila[6]= String.valueOf(cotizaciones.getDiasValidez());
			fila[7]= cotizaciones.getIdentificacionCliente();
			fila[8]= cotizaciones.getNombreCliente();
			fila[9]= cotizaciones.getContacto();
			fila[10]= cotizaciones.getCiudad();
			fila[11]= cotizaciones.getDireccion();
			fila[12]= cotizaciones.getTelefono();
			fila[13]= cotizaciones.getObservaciones();
			
			modeloTbCotizaciones.addRow(fila);
		}
		
		tbCotizaciones.setModel(modeloTbCotizaciones);
	}
	
	//Metodo que permite calcular la cantidad de registros
	private void calcularCantidadItems() {
		
		int cantidad = tbCotizaciones.getRowCount();
		txtNumeroRegistros.setText(Integer.toString(cantidad));
		
	}
	
	//Metodo para mostrar el popup en la tabla	
	private void mostrarPopupEnTabla(){
		JPopupMenu pmCotizaciones = new JPopupMenu();
		JMenuItem miFacturar = new JMenuItem("Facturar Cotización");
		JMenuItem miVer = new JMenuItem("Ver Cotización");
		JMenuItem miEliminar = new JMenuItem("Eliminar Cotización");
		
		pmCotizaciones.add(miFacturar);
		miFacturar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facturar();
			}
		});
		
		pmCotizaciones.add(miVer);
		miVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCotizacion();
			}
		});
		
		pmCotizaciones.add(miEliminar);
		miEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirDialogoConfirmacionEliminacionRegistro();
			}
		});
			
		tbCotizaciones.setComponentPopupMenu( pmCotizaciones);	
	}
	
	//Metodo para cargar los datos de la cotizacion a la venta para ser facturada
	private void facturar() {
		VentRegistrarVentaArticulos ventRegistrarVentaArticulos = new VentRegistrarVentaArticulos();
		Cotizacion datosCotizacion= listaCotizaciones.get(filaSeleccionada);
		
		ventRegistrarVentaArticulos.agregarDatosAVentas(datosCotizacion);
		ventRegistrarVentaArticulos.setVisible(true);
	}
	
	//Metodo para visualizar la cotizacion como un reporte para ser impreso o guardado
	private void verCotizacion() {
		DelegadoDetalleCotizacion delegadoDetalleCotizacion = new DelegadoDetalleCotizacion();
		
		 URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteCotizacion.jasper");
		 List<ReporteDetalle> lista = new ArrayList<ReporteDetalle>();
		 
		 Cotizacion cotizacion= listaCotizaciones.get(filaSeleccionada);
		 List<DetalleCotizacion> listaDetalleCotizacion = delegadoDetalleCotizacion.listarDetallePorCodigoCotizacion(cotizacion.getIdCotizacion()); 
		 
		 for(DetalleCotizacion detalles : listaDetalleCotizacion){
			 ReporteDetalle detalleCotizacion = new ReporteDetalle(detalles.getArticulo().getCodigo(), detalles.getArticulo().getNombre(), detalles.getArticulo().getUnidadMedida().getNombre(), detalles.getCantidad(), formatearNumero(detalles.getVlrUnitario()), formatearNumero(detalles.getTotal()));
			 lista.add(detalleCotizacion);
		 }
	        try {
	            JasperReport reporte= (JasperReport) JRLoader.loadObject(ubicacion);
	            Map<String, Object> parametros = new HashMap<String, Object>();
	            parametros.put("logoJungla", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png"));
	            parametros.put("firma", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/firma.jpg"));
	            parametros.put("idCotizacion", cotizacion.getIdCotizacion());
	            parametros.put("nombreCliente", cotizacion.getNombreCliente());
	            parametros.put("identCliente", cotizacion.getIdentificacionCliente());
	            parametros.put("direccion", cotizacion.getDireccion());
	            parametros.put("telefono", cotizacion.getTelefono());
	            parametros.put("ciudad", cotizacion.getCiudad());
	            parametros.put("contacto", cotizacion.getContacto());
	            parametros.put("subtotal", formatearNumero(cotizacion.getSubtotal()));
	            parametros.put("descuento", formatearNumero(cotizacion.getDescuento()));
	            parametros.put("total", formatearNumero(cotizacion.getTotalCotizado()));
	            parametros.put("items", String.valueOf(cotizacion.getItems()));
	            parametros.put("fecha", convertirDateAString(cotizacion.getFecha()));
	            parametros.put("observaciones", cotizacion.getObservaciones());
	            parametros.put("diasValidez", String.valueOf(cotizacion.getDiasValidez()));
	            
	            JasperPrint jprint= JasperFillManager.fillReport(reporte, parametros,new JRBeanCollectionDataSource(lista));
	            JasperViewer.viewReport(jprint,false);
	        } catch (JRException ex) {
	            Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
	
	//Metodo para eliminar la cotizacion
	private void eliminarCotizacion() {
	
		DelegadoCotizacion delegadoCotizacion = new DelegadoCotizacion();
		Cotizacion cotizacionElegida = listaCotizaciones.get(filaSeleccionada);
		//Cotizacion cotizacionAEliminar = delegadoCotizacion.actualizarCotizacion(cotizacionAEliminar); ;
		//cotizacionAEliminar.setIdCotizacion(cotizacionElegida.getIdCotizacion());
		
		delegadoCotizacion.eliminarCotizacion(cotizacionElegida);
		limpiarTablaCotizaciones();
		llenarTabla(delegadoCotizacion.listarCotizaciones());
		calcularCantidadItems();
	}
	
	/*//Metodo para enviar los datos del proveedor a la ventana modificar proveedor
	private void enviarCotizacionParaModificar() {
		VentModificarCliente ventModificarCliente = new VentModificarCliente();
		Cotizacion cotizacionElegido = listaCotizaciones.get(filaSeleccionada);
		ventModificarCliente.agregarDatos(cotizacionElegido);
		ventModificarCliente.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
            	limpiarTabla();
            	llenarTabla(listaCotizaciones);
            }
        });
		ventModificarCliente.setVisible(true);
		
	}
	
	//Metodo para limpiar la tabla 
	private void limpiarTabla(){
	       for (int i = 0; i < tbCotizaciones.getRowCount(); i++) {
	    	   modeloTbCotizaciones.removeRow(i);
	           i-=1;
	       }
	 }*/
	
	//Metodo para convertir una fecha de tipo date a una cadena
	public String convertirDateAString(Date strFecha){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(strFecha);
		
		return fecha;
	}
	
	//Metodo para convertir un numero corriente en formato de pesos y decimales
	private String formatearNumero(Double numero){
		NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es","CO"));
		return formato.format(numero); 
	}
	
	//Metodo para abrir ventanta de confirmacion de eliminacion de registro
	private void abrirDialogoConfirmacionEliminacionRegistro() {
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar esta cotizacion?", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			eliminarCotizacion();
		}else{
		
		}
	}
	
	//Metodo para limpiar la tabla de articulos 
	private void limpiarTablaCotizaciones() {
		for (int i = 0; i < tbCotizaciones.getRowCount(); i++) {
	           modeloTbCotizaciones.removeRow(i);
	           i-=1;
	       }
	}
}
