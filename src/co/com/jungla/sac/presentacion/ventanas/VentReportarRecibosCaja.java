package co.com.jungla.sac.presentacion.ventanas;

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

import co.com.jungla.sac.negocio.delegados.DelegadoDetalleCompra;
import co.com.jungla.sac.negocio.delegados.DelegadoReciboCaja;
import co.com.jungla.sac.negocio.delegados.DelegadoVendedor;
import co.com.jungla.sac.persistencia.entidades.CompraArticulos;
import co.com.jungla.sac.persistencia.entidades.DetalleCompra;
import co.com.jungla.sac.persistencia.entidades.Persona;
import co.com.jungla.sac.persistencia.entidades.ReciboCaja;
import co.com.jungla.sac.persistencia.entidades.Vendedor;

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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo la visualizacion de los recibos de caja mediante un reporte
 * @author Luis Fernando Pedroza T.
 * @version: 10/09/2016
 */
public class VentReportarRecibosCaja extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtTotalDocs;
	private JTextField txtItems;
	private JTextField txtTotalRecibido;
	private JTextField txtTotalNotasCredito;
	private JDateChooser dchDesde;
	private JDateChooser dchHasta;
	private JComboBox cbVendedor;
	private JTable tbRecibosCaja;
	private DefaultComboBoxModel modeloVendedor = new DefaultComboBoxModel();
	private DefaultTableModel modeloTbRecibos = new DefaultTableModel();
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private List<ReciboCaja> listaRecibosCaja;
	private int filaSeleccionada;
	
	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentReportarRecibosCaja() {
		setTitle("Reporte de Recuados o Recibos de Caja");
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
				reportarRecibosCaja();
				calcularTotalDocs();
				calcularCantidadCompras();
				calcularTotalRecibido();
				calcularTotalNotasCredito();
			}
		});
		btnMostrar.setForeground(new Color(0, 51, 0));
		btnMostrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMostrar.setBounds(758, 20, 117, 23);
		pn1.add(btnMostrar);
		
		JPanel pnRangoDias = new JPanel();
		pnRangoDias.setLayout(null);
		pnRangoDias.setBackground(new Color(0, 51, 0));
		pnRangoDias.setBounds(529, 11, 198, 72);
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
		
		JPanel pnVendedor = new JPanel();
		pnVendedor.setLayout(null);
		pnVendedor.setBackground(new Color(0, 51, 0));
		pnVendedor.setBounds(272, 11, 247, 38);
		pn1.add(pnVendedor);
		
		cbVendedor = new JComboBox();
		cbVendedor.setBounds(0, 16, 247, 22);
		pnVendedor.add(cbVendedor);
		
		JLabel lblVendedor = new JLabel("Vendedor");
		lblVendedor.setForeground(Color.WHITE);
		lblVendedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVendedor.setBackground(Color.BLACK);
		lblVendedor.setBounds(94, 0, 73, 14);
		pnVendedor.add(lblVendedor);
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 116, 1155, 320);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JScrollPane scrRecibosCaja = new JScrollPane();
		scrRecibosCaja.setBounds(2, 2, 1151, 316);
		pn2.add(scrRecibosCaja);
		
		tbRecibosCaja = new JTable();
		tbRecibosCaja.setEnabled(false);
		tbRecibosCaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbRecibosCaja.rowAtPoint(point);
		        tbRecibosCaja.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		        mostrarPopupEnTabla();
			}
		});
		scrRecibosCaja.setViewportView(tbRecibosCaja);
		
		
		
		JPanel pn3 = new JPanel();
		pn3.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn3.setBounds(10, 447, 1155, 61);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JPanel pnVentaTotal = new JPanel();
		pnVentaTotal.setLayout(null);
		pnVentaTotal.setBackground(new Color(0, 51, 0));
		pnVentaTotal.setBounds(142, 11, 160, 38);
		pn3.add(pnVentaTotal);
		
		JLabel lblVentaTotal = new JLabel("Total Docs");
		lblVentaTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVentaTotal.setForeground(Color.WHITE);
		lblVentaTotal.setBackground(SystemColor.desktop);
		lblVentaTotal.setBounds(50, 0, 90, 14);
		pnVentaTotal.add(lblVentaTotal);
		
		txtTotalDocs = new JTextField();
		txtTotalDocs.setColumns(10);
		txtTotalDocs.setBounds(0, 18, 160, 20);
		txtTotalDocs.setEditable(false);
		pnVentaTotal.add(txtTotalDocs);
		
		JPanel pnItems = new JPanel();
		pnItems.setLayout(null);
		pnItems.setBackground(new Color(0, 51, 0));
		pnItems.setBounds(662, 11, 60, 38);
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
		btnExportar.setBounds(759, 20, 140, 23);
		pn3.add(btnExportar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setForeground(new Color(0, 51, 0));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalir.setBounds(920, 20, 104, 23);
		pn3.add(btnSalir);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 51, 0));
		panel.setBounds(312, 11, 160, 38);
		pn3.add(panel);
		
		JLabel lblTotalRecibido = new JLabel("Total Recibido");
		lblTotalRecibido.setForeground(Color.WHITE);
		lblTotalRecibido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalRecibido.setBackground(SystemColor.desktop);
		lblTotalRecibido.setBounds(40, 0, 90, 14);
		panel.add(lblTotalRecibido);
		
		txtTotalRecibido = new JTextField();
		txtTotalRecibido.setEditable(false);
		txtTotalRecibido.setColumns(10);
		txtTotalRecibido.setBounds(0, 18, 160, 20);
		panel.add(txtTotalRecibido);
		
		JPanel pnTotalNotasCredito = new JPanel();
		pnTotalNotasCredito.setLayout(null);
		pnTotalNotasCredito.setBackground(new Color(0, 51, 0));
		pnTotalNotasCredito.setBounds(492, 11, 160, 38);
		pn3.add(pnTotalNotasCredito);
		
		JLabel lblNotasCredito = new JLabel("Total Recibido");
		lblNotasCredito.setForeground(Color.WHITE);
		lblNotasCredito.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNotasCredito.setBackground(SystemColor.desktop);
		lblNotasCredito.setBounds(40, 0, 90, 14);
		pnTotalNotasCredito.add(lblNotasCredito);
		
		txtTotalNotasCredito = new JTextField();
		txtTotalNotasCredito.setEditable(false);
		txtTotalNotasCredito.setColumns(10);
		txtTotalNotasCredito.setBounds(0, 18, 160, 20);
		pnTotalNotasCredito.add(txtTotalNotasCredito);
		
		//Metodos que debe ejecutar la ventana al inicializarse
		listarVendedores();
		llenarColumnasTbOrdenesCompra();
		
	}
	
	//Metodo para listar los vendedores y desplegarlos en un combo box
	private void listarVendedores() {
		DelegadoVendedor delegadoVendedor = new DelegadoVendedor();
		List<Vendedor> vendedores = delegadoVendedor.listarVendedor();
		modeloVendedor.addElement("--TODOS LOS VENDEDORES--");
		
		for(Vendedor vendedor : vendedores){
			modeloVendedor.addElement(new Persona (vendedor.getNombre(), vendedor.getIdentificacion()));
			cbVendedor.setModel(modeloVendedor);
		}
	}
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbOrdenesCompra() {
		modeloTbRecibos.addColumn("Recibo");
		modeloTbRecibos.addColumn("Fecha Venta");
		modeloTbRecibos.addColumn("Fecha Recaudo");
		modeloTbRecibos.addColumn("Cliente");
		modeloTbRecibos.addColumn("Identificacion");
		modeloTbRecibos.addColumn("Ciudad");
		modeloTbRecibos.addColumn("Venta");
		modeloTbRecibos.addColumn("Total docs");
		modeloTbRecibos.addColumn("Recibido");
		modeloTbRecibos.addColumn("Notas Crédito");
		modeloTbRecibos.addColumn("Vendedor");
		
		tbRecibosCaja.setModel(modeloTbRecibos);
	}
	
	//Metodo para listar los recibos caja de acuerdo a los parametros de fecha de recaudo y vendedor
	private void reportarRecibosCaja(){
		
		DelegadoReciboCaja delegadoReciboCaja = new DelegadoReciboCaja();
		
		if(cbVendedor.getSelectedItem().equals("--TODOS LOS VENDEDORES--") && dchDesde.getDate()!=null && dchHasta.getDate()!=null){
			listaRecibosCaja = delegadoReciboCaja.reportarRecibosPorF(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
			limpiarTabla();
			llenarTabla(listaRecibosCaja);
		}else{
			Vendedor vendedor = (Vendedor) cbVendedor.getSelectedItem(); 
			listaRecibosCaja = delegadoReciboCaja.reportarRecibosPorFV(vendedor.getIdentificacion(),restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
			limpiarTabla();
			llenarTabla(listaRecibosCaja);
		}
		
	}
	
	//Metodo para llenar la tabla cuando recibe como parametro una lista de recibos de caja
	private void llenarTabla( List<ReciboCaja> listaReciboCajas) {
		
		String [] fila = new String[modeloTbRecibos.getColumnCount()];
		
		for(ReciboCaja recibos : listaReciboCajas){
			
			fila[0]= String.valueOf(recibos.getIdReciboCaja());
			fila[1]= convertirDateAString(recibos.getFechaVenta());
			fila[2]= convertirDateAString(recibos.getFechaRecaudo());
			fila[3]= recibos.getVentaArticulos().get(0).getClientes().getNombre();
			fila[4]= recibos.getVentaArticulos().get(0).getClientes().getIdentificacion();
		
			fila[5]= recibos.getVentaArticulos().get(0).getClientes().getMunicipio().getNombre();
			fila[6]= String.valueOf(recibos.getVentaArticulos().get(0).getIdVenta());
			fila[7]= formatearNumero(recibos.getTotalDocs());
			fila[8]= formatearNumero(recibos.getTotalRecibido());
			fila[9]= formatearNumero(recibos.getTotalNCredito());
			fila[10]= recibos.getVentaArticulos().get(0).getVendedores().getNombre();	
			
			modeloTbRecibos.addRow(fila);
		}
		
		tbRecibosCaja.setModel(modeloTbRecibos);
		
	}
	
	//Metodo para mostrar un menu en cada celda de la tabla
	private void mostrarPopupEnTabla(){
		JPopupMenu pmArticulos = new JPopupMenu();
		JMenuItem miVerFactura = new JMenuItem("Ver Detalle Venta");
		JMenuItem miVerRecibo = new JMenuItem("Ver Recibo Caja");

		//pmArticulos.add(miVerFactura);
		miVerFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//verFacturaVenta();
			}
		});
		
		pmArticulos.add(miVerRecibo);
		miVerRecibo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verReciboCaja();
			}
		});

		tbRecibosCaja.setComponentPopupMenu(pmArticulos);
	}
	
	//Metodo para calcular el total de documentos
	private void calcularTotalDocs(){
		
		double acumulador = 0;
		double totalDocs = 0;
		
		for(int i=0; i<tbRecibosCaja.getRowCount(); i++) {
			
			acumulador= Double.parseDouble(desformatearNumero(String.valueOf(tbRecibosCaja.getValueAt(i,7))));
			totalDocs += acumulador;
		}
		
		txtTotalDocs.setText(formatearNumero(totalDocs));
	}
	
	//Metodo para calcular el total recibido
	private void calcularTotalRecibido(){
		
		double acumulador = 0;
		double totalRecibido = 0;
		
		for(int i=0; i<tbRecibosCaja.getRowCount(); i++) {
			
				acumulador= Double.parseDouble(desformatearNumero(String.valueOf(tbRecibosCaja.getValueAt(i,8))));
				totalRecibido += acumulador;

		}
		
		txtTotalRecibido.setText(formatearNumero(totalRecibido));
	}
	
	//Metodo para calcular el total notas credito
	private void calcularTotalNotasCredito(){
		
		double acumulador = 0;
		double totalNotasCredito = 0;
		
		for(int i=0; i<tbRecibosCaja.getRowCount(); i++) {
			
				acumulador= Double.parseDouble(desformatearNumero(String.valueOf(tbRecibosCaja.getValueAt(i,8))));
				totalNotasCredito += acumulador;

		}
		
		txtTotalNotasCredito.setText(formatearNumero(totalNotasCredito));
	}
	
	//Metodo que permite calcular la cantidad de compras encontradas
	private void calcularCantidadCompras() {
		
		int cantidad = tbRecibosCaja.getRowCount();
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
	       for (int i = 0; i < tbRecibosCaja.getRowCount(); i++) {
	    	   modeloTbRecibos.removeRow(i);
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
	
	/*//Metodo para visualizar la factura de venta
	private void verFacturaVenta() {
	URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteFacturaVenta.jasper");
	 List<ReporteDetalle> lista = new ArrayList<ReporteDetalle>();
	 DelegadoDetalleCompra delegadoDetalleCompra = new DelegadoDetalleCompra();
	 
	 CompraArticulos compra= listaCompraArticulos.get(filaSeleccionada);
	 List<DetalleCompra> listaDetalleCompra = delegadoDetalleCompra.listarDetallePorCodigoCompra(compra.getIdCompra()); 
	 
	 for(DetalleCompra detalles : listaDetalleCompra){
		 ReporteDetalle detalleCompra = new ReporteDetalle(detalles.getArticulo().getCodigo(),detalles.getArticulo().getNombre(), detalles.getArticulo().getUnidadMedida().getAbreviatura(), detalles.getCantidad() , formatearNumero(detalles.getCosto()), formatearNumero(detalles.getTotal()));
		 lista.add(detalleCompra);
	 }
	 
	    try {
	        JasperReport reporte= (JasperReport) JRLoader.loadObject(ubicacion);
	        Map<String, Object> parametros = new HashMap<String, Object>();
	        parametros.put("logoJungla", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png"));
	        parametros.put("firma", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/firma.jpg"));
	        parametros.put("idVenta", ultimaVentaArticulo.get(0).getIdVenta());
	        parametros.put("nombreCliente", ultimaVentaArticulo.get(0).getClientes().getNombre());
	        parametros.put("identCliente", ultimaVentaArticulo.get(0).getClientes().getIdentificacion());
	        parametros.put("direccion", ultimaVentaArticulo.get(0).getClientes().getDireccion());
	        parametros.put("telefono", ultimaVentaArticulo.get(0).getClientes().getTelefono());
	        parametros.put("ciudad", ultimaVentaArticulo.get(0).getClientes().getMunicipio().getNombre());
	        parametros.put("formaPago", ultimaVentaArticulo.get(0).getFormaPagoCliente().getDescripcion());
	        parametros.put("nombreVendedor", ultimaVentaArticulo.get(0).getVendedores().getNombre());
	        parametros.put("ordenCompra", String.valueOf(ultimaVentaArticulo.get(0).getOrdCompra()));
	        parametros.put("pedido", String.valueOf(ultimaVentaArticulo.get(0).getPedido()));
	        parametros.put("subtotal", formatearNumero(ultimaVentaArticulo.get(0).getSubtotal()));
	        parametros.put("descuento", formatearNumero(ultimaVentaArticulo.get(0).getDescuento()));
	        parametros.put("total", formatearNumero(ultimaVentaArticulo.get(0).getTotalVenta()));
	        parametros.put("items", String.valueOf(ultimaVentaArticulo.get(0).getItems()));
	        parametros.put("fechaFactura", convertirDateAString(ultimaVentaArticulo.get(0).getFechaGeneracion()));
	        parametros.put("fechaLimite", convertirDateAString(ultimaVentaArticulo.get(0).getFechaLimitePago()));
	        parametros.put("observaciones", ultimaVentaArticulo.get(0).getObservaciones());
	        parametros.put("anulado", "");
		    parametros.put("fechaAnulado", "");
	        
	        JasperPrint jprint= JasperFillManager.fillReport(reporte, parametros,new JRBeanCollectionDataSource(lista));
	        JasperViewer.viewReport(jprint,false);
	    } catch (JRException ex) {
	        Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
	    }
	
	}*/
	
	//Metodo para visualizar el recibo de caja
	private void verReciboCaja() {
		
		
	}
	
	//Metodo para exportar la tabla en excel
	private void exportarExcel(){
		VentExportarExcel ventExportarExcel = new VentExportarExcel(tbRecibosCaja, getTitle());
		ventExportarExcel.setVisible(true);
	}
}
