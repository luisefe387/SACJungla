package co.com.jungla.sac.presentacion.ventanas;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Color;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.com.jungla.sac.negocio.delegados.DelegadoArticulo;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleDevolucionCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleVenta;
import co.com.jungla.sac.negocio.delegados.DelegadoDevolucionCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoMedioPagoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoReciboCaja;
import co.com.jungla.sac.negocio.delegados.DelegadoVentaArticulos;
import co.com.jungla.sac.persistencia.entidades.Articulo;
import co.com.jungla.sac.persistencia.entidades.DetalleDevolucionCliente;
import co.com.jungla.sac.persistencia.entidades.DetalleVenta;
import co.com.jungla.sac.persistencia.entidades.DevolucionCliente;
import co.com.jungla.sac.persistencia.entidades.MedioPagoCliente;
import co.com.jungla.sac.persistencia.entidades.ReciboCaja;
import co.com.jungla.sac.persistencia.entidades.VentaArticulos;

import com.toedter.calendar.JDateChooser;

import javax.swing.border.LineBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
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

import java.awt.Component;

import javax.swing.table.DefaultTableCellRenderer;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo la visualizacion de las notas credito mediante un reporte
 * @author Luis Fernando Pedroza T.
 * @version: 10/09/2016
 */
public class VentReportarNotasCredito extends JFrame {
	
	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtTotal;
	private JTextField txtItems;
	private JTextField txtCompensadas;
	private JTextField txtPendientes;
	private JTextField txtCodCliente;
	private JDateChooser dchDesde;
	private JDateChooser dchHasta;
	private JComboBox cbEstado;
	private JTable tbNotasCredito;
	private DefaultTableModel modeloTbNotasCredito = new DefaultTableModel();
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private List<DevolucionCliente> listaNotasCredito;
	private int filaSeleccionada;
	
	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentReportarNotasCredito() {
		setTitle("Reporte de Notas Credito");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 1188, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 1155, 94);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				reportarNotasCredito();
				calcularTotal();
				calcularCantidadNotasCredito();
				calcularTotalPendientes();
				calcularTotalCompensadas();
			}
		});
		btnMostrar.setForeground(new Color(0, 51, 0));
		btnMostrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMostrar.setBounds(798, 20, 117, 23);
		pn1.add(btnMostrar);
		
		JPanel pnRangoDias = new JPanel();
		pnRangoDias.setLayout(null);
		pnRangoDias.setBackground(new Color(0, 51, 0));
		pnRangoDias.setBounds(569, 11, 198, 72);
		pn1.add(pnRangoDias);
		
		JLabel lblRangoDias = new JLabel("Rango de D\u00EDas");
		lblRangoDias.setForeground(Color.WHITE);
		lblRangoDias.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRangoDias.setBackground(Color.BLACK);
		lblRangoDias.setBounds(57, 0, 106, 14);
		pnRangoDias.add(lblRangoDias);
		
		dchDesde = new JDateChooser();
		dchDesde.setBounds(49, 18, 144, 20);
		dchDesde.setDate(new Date());
		pnRangoDias.add(dchDesde);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setForeground(Color.WHITE);
		lblDesde.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDesde.setBackground(Color.BLACK);
		lblDesde.setBounds(8, 21, 50, 14);
		pnRangoDias.add(lblDesde);
		
		dchHasta = new JDateChooser();
		dchHasta.setBounds(49, 45, 144, 20);
		dchHasta.setDate(new Date());
		pnRangoDias.add(dchHasta);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setForeground(Color.WHITE);
		lblHasta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHasta.setBackground(Color.BLACK);
		lblHasta.setBounds(8, 48, 50, 14);
		pnRangoDias.add(lblHasta);
		
		JPanel pnEstado = new JPanel();
		pnEstado.setLayout(null);
		pnEstado.setBackground(new Color(0, 51, 0));
		pnEstado.setBounds(238, 11, 189, 38);
		pn1.add(pnEstado);
		
		cbEstado = new JComboBox();
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Pendientes", "Compensadas"}));
		cbEstado.setBounds(0, 16, 189, 22);
		pnEstado.add(cbEstado);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstado.setBackground(Color.BLACK);
		lblEstado.setBounds(75, 0, 73, 14);
		pnEstado.add(lblEstado);
		
		JPanel pnCodCliente = new JPanel();
		pnCodCliente.setLayout(null);
		pnCodCliente.setBackground(new Color(0, 51, 0));
		pnCodCliente.setBounds(437, 11, 122, 38);
		pn1.add(pnCodCliente);
		
		JLabel lblCodCliente = new JLabel("Ident. Cliente");
		lblCodCliente.setForeground(Color.WHITE);
		lblCodCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCodCliente.setBackground(SystemColor.desktop);
		lblCodCliente.setBounds(22, 0, 90, 14);
		pnCodCliente.add(lblCodCliente);
		
		txtCodCliente = new JTextField();
		txtCodCliente.setColumns(10);
		txtCodCliente.setBounds(0, 18, 122, 20);
		pnCodCliente.add(txtCodCliente);
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 116, 1155, 320);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JScrollPane scrNotasCredito = new JScrollPane();
		scrNotasCredito.setBounds(2, 2, 1151, 316);
		pn2.add(scrNotasCredito);
		
		tbNotasCredito = new JTable();
		tbNotasCredito.setEnabled(false);
		tbNotasCredito.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbNotasCredito.rowAtPoint(point);
		        tbNotasCredito.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		        mostrarPopupEnTabla();
			}
		});
		scrNotasCredito.setViewportView(tbNotasCredito);
		
		JPanel pn3 = new JPanel();
		pn3.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn3.setBounds(10, 447, 1155, 61);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JPanel pnTotal = new JPanel();
		pnTotal.setLayout(null);
		pnTotal.setBackground(new Color(0, 51, 0));
		pnTotal.setBounds(141, 11, 160, 38);
		pn3.add(pnTotal);
		
		JLabel lblTotal = new JLabel("Total ");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setBackground(SystemColor.desktop);
		lblTotal.setBounds(70, 0, 70, 14);
		pnTotal.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(0, 18, 160, 20);
		txtTotal.setEditable(false);
		pnTotal.add(txtTotal);
		
		JPanel pnItems = new JPanel();
		pnItems.setLayout(null);
		pnItems.setBackground(new Color(0, 51, 0));
		pnItems.setBounds(651, 11, 60, 38);
		pn3.add(pnItems);
		
		JLabel lblItems = new JLabel("Items");
		lblItems.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblItems.setForeground(Color.WHITE);
		lblItems.setBackground(SystemColor.desktop);
		lblItems.setBounds(14, 0, 49, 14);
		pnItems.add(lblItems);
		
		txtItems = new JTextField();
		txtItems.setColumns(10);
		txtItems.setBounds(0, 18, 60, 20);
		txtItems.setEditable(false);
		pnItems.add(txtItems);
		
		JButton btnExportar = new JButton("Exportar a Excel");
		btnExportar.setForeground(new Color(0, 51, 0));
		btnExportar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarExcel();
			}
		});
		btnExportar.setBounds(748, 20, 140, 23);
		pn3.add(btnExportar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setForeground(new Color(0, 51, 0));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalir.setBounds(909, 20, 104, 23);
		pn3.add(btnSalir);
		
		JPanel pnCompensadas = new JPanel();
		pnCompensadas.setLayout(null);
		pnCompensadas.setBackground(new Color(0, 51, 0));
		pnCompensadas.setBounds(311, 11, 160, 38);
		pn3.add(pnCompensadas);
		
		JLabel lblCompensadas = new JLabel("Descontadas");
		lblCompensadas.setForeground(Color.WHITE);
		lblCompensadas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCompensadas.setBackground(SystemColor.desktop);
		lblCompensadas.setBounds(45, 0, 90, 14);
		pnCompensadas.add(lblCompensadas);
		
		txtCompensadas = new JTextField();
		txtCompensadas.setEditable(false);
		txtCompensadas.setColumns(10);
		txtCompensadas.setBounds(0, 18, 160, 20);
		pnCompensadas.add(txtCompensadas);
		
		JPanel pnPendientes = new JPanel();
		pnPendientes.setLayout(null);
		pnPendientes.setBackground(new Color(0, 51, 0));
		pnPendientes.setBounds(481, 11, 160, 38);
		pn3.add(pnPendientes);
		
		JLabel lblPendientes = new JLabel("Pendientes");
		lblPendientes.setForeground(Color.WHITE);
		lblPendientes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPendientes.setBackground(SystemColor.desktop);
		lblPendientes.setBounds(48, 0, 90, 14);
		pnPendientes.add(lblPendientes);
		
		txtPendientes = new JTextField();
		txtPendientes.setEditable(false);
		txtPendientes.setColumns(10);
		txtPendientes.setBounds(0, 18, 160, 20);
		pnPendientes.add(txtPendientes);
		
		//Metodos que debe ejecutar la ventana al inicializarse
		llenarColumnasTbNotasCredito();
		
	}
	
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbNotasCredito() {
		modeloTbNotasCredito.addColumn("Nota");
		modeloTbNotasCredito.addColumn("Fecha");
		modeloTbNotasCredito.addColumn("Cliente");
		modeloTbNotasCredito.addColumn("Factura");
		modeloTbNotasCredito.addColumn("Total");
		modeloTbNotasCredito.addColumn("Estado");
		modeloTbNotasCredito.addColumn("Fecha Recaudo");
		modeloTbNotasCredito.addColumn("Recibo");
		modeloTbNotasCredito.addColumn("Concepto");
	
		tbNotasCredito.setModel(modeloTbNotasCredito);
	}
	
	//Metodo para listar las notas credito de acuerdo a los parametros de estado, rango entre fechas o codigo cliene
	private void reportarNotasCredito(){
		
		DelegadoDevolucionCliente delegadoDevolucionCliente = new DelegadoDevolucionCliente();
		String estado ;
		
		if("Compensadas".equals(cbEstado.getSelectedItem().toString())){
			estado = "Compensada";
		}else{
			estado = "Pendiente";
		}
		
		if(cbEstado.getSelectedItem().equals("Todos") && txtCodCliente.getText().isEmpty()){
			listaNotasCredito = delegadoDevolucionCliente.reportarDevolucionesPorF(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
			limpiarTabla();
			llenarTabla(listaNotasCredito);
		}else{
			if(cbEstado.getSelectedItem().equals("Todos") && txtCodCliente.getText()!=""){
				listaNotasCredito = delegadoDevolucionCliente.reportarDevolucionesPorFC(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), txtCodCliente.getText());
				limpiarTabla();
				llenarTabla(listaNotasCredito);
			}else{
				if(cbEstado.getSelectedItem()!=null && txtCodCliente.getText()!=""){
					
					listaNotasCredito = delegadoDevolucionCliente.reportarDevolucionesPorFCE(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), txtCodCliente.getText(), estado );
					limpiarTabla();
					llenarTabla(listaNotasCredito);
				}else{
					listaNotasCredito = delegadoDevolucionCliente.reportarDevolucionesPorFE(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), estado);
					limpiarTabla();
					llenarTabla(listaNotasCredito);
				}
			}
		}
	}
	
	//Metodo para llenar la tabla cuando recibe como parametro una lista de devoluciones
	private void llenarTabla( List<DevolucionCliente> listaNotasCredito) {
		
		String [] fila = new String[modeloTbNotasCredito.getColumnCount()];
		
		for(DevolucionCliente notasCredito : listaNotasCredito){
			
			fila[0]= String.valueOf(notasCredito.getIdDevolucionCliente());
			fila[1]= convertirDateAString(notasCredito.getFecha());
			fila[2]= notasCredito.getVentaArticulos().getClientes().getIdentificacion();
			fila[3]= String.valueOf(notasCredito.getVentaArticulos().getIdVenta());
			fila[4]= formatearNumero(notasCredito.getTotalDevolucion());
			fila[5]= notasCredito.getEstado();
			if(notasCredito.getFechaRecaudo()==null){
				fila[6]= "";
			}else{
				fila[6]= convertirDateAString(notasCredito.getFechaRecaudo());
			}
			fila[7]= String.valueOf(notasCredito.getIdRecibo());
			fila[8]= notasCredito.getConcepto();
			
			modeloTbNotasCredito.addRow(fila);
		}
		
		tbNotasCredito.setModel(modeloTbNotasCredito);
		colorearEstadoEnTabla(tbNotasCredito);
		
	}
	
	//Metodo para mostrar un pequeño menu a cada celda seleccionada
	private void mostrarPopupEnTabla(){
		JPopupMenu pmArticulos = new JPopupMenu();
		JMenuItem miVerDevolucion = new JMenuItem("Ver Nota Credito");
		JMenuItem miVerFactura = new JMenuItem("Ver Factura Venta");
		JMenuItem miVerRecibo = new JMenuItem("Ver Recibo Caja");
		
		pmArticulos.add(miVerDevolucion);
		miVerDevolucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verNotaCredito();
			}
		});
		 
		pmArticulos.add(miVerFactura);
		miVerFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verFacturaVenta();
			}
		});
		
		/*if("0".equals(tbNotasCredito.getValueAt(filaSeleccionada, 7))){
			pmArticulos.add(miVerRecibo);
			miVerRecibo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verReciboCaja();
				}
			});
		}*/
	
		tbNotasCredito.setComponentPopupMenu(pmArticulos);
	}
	
	//Metodo para calcular el total devoluciones
	private void calcularTotal(){
		
		double acumulador = 0;
		double total = 0;
		
		for(int i=0; i<tbNotasCredito.getRowCount(); i++) {
			
				acumulador= Double.parseDouble(desformatearNumero(tbNotasCredito.getValueAt(i,4).toString()));
				total += acumulador;
		}
		
		txtTotal.setText(formatearNumero(total));
	}
	
	//Metodo para calcular el total de devoluciones compensadas
	private void calcularTotalCompensadas(){
		
		double acumulador = 0;
		double totalCompensadas = 0;
		
		for(int i=0; i<tbNotasCredito.getRowCount(); i++) {
			if("Compensada".equals(tbNotasCredito.getValueAt(i,4).toString())){
				acumulador= Double.parseDouble(desformatearNumero(tbNotasCredito.getValueAt(i,4).toString()));
				totalCompensadas += acumulador;
			}
		}
		
		txtCompensadas.setText(formatearNumero(totalCompensadas));
	}
	
	//Metodo para calcular el total de devoluciones pendientes
		private void calcularTotalPendientes(){
			
			double acumulador = 0;
			double totalPendientes = 0;
			
			for(int i=0; i<tbNotasCredito.getRowCount(); i++) {
				if("Pendiente".equals(tbNotasCredito.getValueAt(i,4).toString())){
					acumulador= Double.parseDouble(desformatearNumero(tbNotasCredito.getValueAt(i,4).toString()));
					totalPendientes += acumulador;
				}
			}
			
			txtPendientes.setText(formatearNumero(totalPendientes));
		}
		
	//Metodo que permite calcular la cantidad de notas credito encontradas
	private void calcularCantidadNotasCredito() {
		
		int cantidad = tbNotasCredito.getRowCount();
		txtItems.setText(Integer.toString(cantidad));
		
	}
	
	//Metodo para convertir un numero corriente en formato de pesos y decimales
	private String formatearNumero(Double numero){
		NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es","CO"));
		return formato.format(numero); 
	}
	
	//Metodo para convertir un numero en formato de pesos y decimales en un numero corriente
	private String desformatearNumero(String numero){
		String numeroReemplazado=numero.replace("$", "");
		String numeroReemplazado1=numeroReemplazado.replace(".", "");
		String numeroReemplazado2=numeroReemplazado1.replace(",", ".");
		return numeroReemplazado2;
	}
	
	//Metodo que permite la conversion de un dato de tipo date a uno de tipo string
	public String convertirDateAString(Date strFecha){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(strFecha);
		
		return fecha;
	}
	
	//Metodo para limpiar la tabla 
	private void limpiarTabla(){
	       for (int i = 0; i < tbNotasCredito.getRowCount(); i++) {
	    	   modeloTbNotasCredito.removeRow(i);
	           i-=1;
	       }
	 }
	
	//Metodo para sumar dias a una fecha determinada
	public Date sumarDias(Date fechaAAplicar){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaAAplicar); 
		calendar.add(Calendar.DAY_OF_YEAR, 1);  
		return calendar.getTime(); 
	}
	
	//Metodo para restar dias a una fecha determinada
	public Date restarDias(Date fechaAAplicar){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaAAplicar); 
		calendar.add(Calendar.DAY_OF_YEAR, -1);  
		return calendar.getTime(); 
	}
	
	//Metodo para colorear las celdas de los estados pendiente y compensada
	private void colorearEstadoEnTabla(JTable tablaCompras) {
		tablaCompras.getColumn("Estado").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			boolean valid = String.valueOf(table.getValueAt(row, 5)).equals("Pendiente");
	        comp.setBackground(valid ? Color.getHSBColor(0.08f, 0.78f, 0.83f) : Color.getHSBColor(1.19f, 0.26f, 0.77f));
	        return comp; 
			}}); 
	}
	
	//Metodo para ver la nota credito
	private void verNotaCredito() {
		DelegadoDetalleDevolucionCliente delegadoDetalleDevolucionCliente = new DelegadoDetalleDevolucionCliente();
		DelegadoArticulo delegadoArticulo = new DelegadoArticulo();
		
		 URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteNotaCredito.jasper");
		 List<ReporteDetalle> lista = new ArrayList<ReporteDetalle>();
		 
		 DevolucionCliente notaCredito= listaNotasCredito.get(filaSeleccionada);
		 List<DetalleDevolucionCliente> listaDetalleDevolucion = delegadoDetalleDevolucionCliente.listarDetallePorCodigoDevolucionCliente(notaCredito.getIdDevolucionCliente()); 
		 
		 for(DetalleDevolucionCliente detalles : listaDetalleDevolucion){
			 Articulo articulo = delegadoArticulo.traerLineaUnidadArticuloPorCodigo(detalles.getCodigoArticulo()).get(0);
			 ReporteDetalle detalleDevolucion = new ReporteDetalle(articulo.getCodigo(),articulo.getNombre(), articulo.getUnidadMedida().getAbreviatura(), detalles.getCantidadDevuelta() , formatearNumero(detalles.getVlrUnitario()), formatearNumero(detalles.getCantidadDevuelta()*detalles.getVlrUnitario()));
			 lista.add(detalleDevolucion);
		 }
		 
       try {
		    JasperReport reporte= (JasperReport) JRLoader.loadObject(ubicacion);
		    Map<String, Object> parametros = new HashMap<String, Object>();
		    parametros.put("logoJungla", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png"));
		    parametros.put("firma", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/firma.jpg"));
		    parametros.put("idNotaCredito", notaCredito.getIdDevolucionCliente());
		    parametros.put("nombreCliente", notaCredito.getVentaArticulos().getClientes().getNombre());
		    parametros.put("identCliente", notaCredito.getVentaArticulos().getClientes().getIdentificacion());
		    parametros.put("direccion", notaCredito.getVentaArticulos().getClientes().getDireccion());
		    parametros.put("telefono", notaCredito.getVentaArticulos().getClientes().getTelefono());
		    parametros.put("ciudad", notaCredito.getVentaArticulos().getClientes().getMunicipio().getNombre());
		    parametros.put("nombreVendedor", notaCredito.getVentaArticulos().getVendedores().getNombre());
		    parametros.put("factura", String.valueOf(notaCredito.getVentaArticulos().getIdVenta()));
		    parametros.put("total", formatearNumero(notaCredito.getTotalDevolucion()));
		    parametros.put("fecha", convertirDateAString(notaCredito.getFecha()));
		    parametros.put("observaciones", notaCredito.getObservaciones());
		    
		    JasperPrint jprint= JasperFillManager.fillReport(reporte, parametros,new JRBeanCollectionDataSource(lista));
		    JasperViewer.viewReport(jprint,false);
		} catch (JRException ex) {
		    Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
	//Metodo para ver el recibo de caja
	private void verReciboCaja() {
		DelegadoReciboCaja delegadoReciboCaja = new DelegadoReciboCaja();
		DelegadoMedioPagoCliente delegadoMedioPagoCliente = new DelegadoMedioPagoCliente();
		
		 URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteReciboCaja.jasper");
		 List<ReporteDetalle> lista = new ArrayList<ReporteDetalle>();
		 
		 ReciboCaja reciboCaja= delegadoReciboCaja.traerRecibosCajaPorIdRecibo(listaNotasCredito.get(filaSeleccionada).getIdRecibo()).get(0);
		 List<MedioPagoCliente> listaMedioPago = delegadoMedioPagoCliente.listarMedioPagosPorCodigoReciboCaja(reciboCaja.getIdReciboCaja()) ; 
		 
		 for(MedioPagoCliente mediosPago : listaMedioPago){
			 ReporteDetalle medios = new ReporteDetalle(mediosPago.getNombreMedioPago(), formatearNumero(mediosPago.getValor()), String.valueOf(mediosPago.getDocReferencia()), mediosPago.getBanco().getEntidad(), mediosPago.getFranquicia());
			 lista.add(medios);
		 }
		 
       try {
		    JasperReport reporte= (JasperReport) JRLoader.loadObject(ubicacion);
		    Map<String, Object> parametros = new HashMap<String, Object>();
		    parametros.put("logoJungla", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png"));
		    parametros.put("firma", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/firma.jpg"));
		    parametros.put("idReciboCaja", reciboCaja.getIdReciboCaja());
		    parametros.put("nombreCliente", reciboCaja.getVentaArticulos().get(0).getClientes().getNombre());
		    parametros.put("identCliente", reciboCaja.getVentaArticulos().get(0).getClientes().getIdentificacion());
		    parametros.put("direccion", reciboCaja.getVentaArticulos().get(0).getClientes().getDireccion());
		    parametros.put("telefono", reciboCaja.getVentaArticulos().get(0).getClientes().getTelefono());
		    parametros.put("ciudad", reciboCaja.getVentaArticulos().get(0).getClientes().getMunicipio().getNombre());
		    parametros.put("fechaEmision", convertirDateAString(reciboCaja.getFechaGeneracion()));
		    parametros.put("fechaRecaudo", convertirDateAString(reciboCaja.getFechaRecaudo()));
		    //parametros.put("saldoPendiente", formatearNumero(reciboCaja.getTotalDevolucion()));
		    parametros.put("observaciones", reciboCaja.getObservaciones());
		    
		    JasperPrint jprint= JasperFillManager.fillReport(reporte, parametros,new JRBeanCollectionDataSource(lista));
		    JasperViewer.viewReport(jprint,false);
		} catch (JRException ex) {
		    Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
	//Metodo para ver la factura de la venta
	private void verFacturaVenta() {
		DelegadoDetalleVenta delegadoDetalleVenta = new DelegadoDetalleVenta();
		DelegadoVentaArticulos delegadoVentaArticulos = new DelegadoVentaArticulos();
		
		 URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteFacturaVenta.jasper");
		 List<ReporteDetalle> lista = new ArrayList<ReporteDetalle>();
		 
		 VentaArticulos ventaConDevolucion= delegadoVentaArticulos.traerVentaPorCodigo(listaNotasCredito.get(filaSeleccionada).getVentaArticulos().getIdVenta()).get(0);
		 List<DetalleVenta> listaDetalleVenta = delegadoDetalleVenta.listarDetallePorCodigoVenta(ventaConDevolucion.getIdVenta()); 
		 
		 for(DetalleVenta detalles : listaDetalleVenta){
			 ReporteDetalle detalleVenta = new ReporteDetalle(detalles.getArticulo().getCodigo(),detalles.getArticulo().getNombre(), detalles.getArticulo().getUnidadMedida().getAbreviatura(), detalles.getCantidad() , formatearNumero(detalles.getVlrUnitario()), formatearNumero(detalles.getTotal()));
			 lista.add(detalleVenta);
		 }
		 
       try {
		    JasperReport reporte= (JasperReport) JRLoader.loadObject(ubicacion);
		    Map<String, Object> parametros = new HashMap<String, Object>();
		    parametros.put("logoJungla", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png"));
		    parametros.put("firma", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/firma.jpg"));
		    parametros.put("idVenta", ventaConDevolucion.getIdVenta());
		    parametros.put("nombreCliente", ventaConDevolucion.getClientes().getNombre());
		    parametros.put("identCliente", ventaConDevolucion.getClientes().getIdentificacion());
		    parametros.put("direccion", ventaConDevolucion.getClientes().getDireccion());
		    parametros.put("telefono", ventaConDevolucion.getClientes().getTelefono());
		    parametros.put("ciudad", ventaConDevolucion.getClientes().getMunicipio().getNombre());
		    parametros.put("formaPago", ventaConDevolucion.getFormaPagoCliente().getDescripcion());
		    parametros.put("nombreVendedor", ventaConDevolucion.getVendedores().getNombre());
		    parametros.put("ordenCompra", String.valueOf(ventaConDevolucion.getOrdCompra()));
		    parametros.put("pedido", String.valueOf(ventaConDevolucion.getPedido()));
		    parametros.put("subtotal", formatearNumero(ventaConDevolucion.getSubtotal()));
		    parametros.put("descuento", formatearNumero(ventaConDevolucion.getDescuento()));
		    parametros.put("total", formatearNumero(ventaConDevolucion.getTotalVenta()));
		    parametros.put("items", String.valueOf(ventaConDevolucion.getItems()));
		    parametros.put("fechaFactura", convertirDateAString(ventaConDevolucion.getFechaGeneracion()));
		    parametros.put("fechaLimite", convertirDateAString(ventaConDevolucion.getFechaLimitePago()));
		    parametros.put("observaciones", ventaConDevolucion.getObservaciones());
		    parametros.put("anulado", "");
		    parametros.put("fechaAnulado", "");
		    
		    JasperPrint jprint= JasperFillManager.fillReport(reporte, parametros,new JRBeanCollectionDataSource(lista));
		    JasperViewer.viewReport(jprint,false);
		} catch (JRException ex) {
		    Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	//Metodo para exportar la tabla en excel
	private void exportarExcel(){
		VentExportarExcel ventExportarExcel = new VentExportarExcel(tbNotasCredito, getTitle());
		ventExportarExcel.setVisible(true);
	}
}
