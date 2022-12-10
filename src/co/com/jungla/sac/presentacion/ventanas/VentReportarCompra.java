package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Toolkit;
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

import co.com.jungla.sac.persistencia.entidades.CompraArticulos;
import co.com.jungla.sac.persistencia.entidades.CuentaXPagar;
import co.com.jungla.sac.persistencia.entidades.DetalleCompra;
import co.com.jungla.sac.persistencia.entidades.Egreso;
import co.com.jungla.sac.persistencia.entidades.PagoAbonoCxP;
import co.com.jungla.sac.persistencia.entidades.Persona;
import co.com.jungla.sac.persistencia.entidades.Proveedor;

import co.com.jungla.sac.negocio.delegados.DelegadoCompraArticulos;
import co.com.jungla.sac.negocio.delegados.DelegadoCuentaXPagar;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleCompra;
import co.com.jungla.sac.negocio.delegados.DelegadoEgreso;
import co.com.jungla.sac.negocio.delegados.DelegadoPagoAbonoCxP;
import co.com.jungla.sac.negocio.delegados.DelegadoProveedor;
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

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el reporte de las compras de articulos
 * @author Luis Fernando Pedroza T.
 * @version: 19/09/2016
 */
public class VentReportarCompra extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtCancelado;
	private JTextField txtPendiente;
	private JTextField txtTotalOC;
	private JTextField txtItem;
	private JComboBox cbProveedor;
	private JDateChooser dchDesde;
	private JDateChooser dchHasta;
	private JTable tbCompras;
	private DefaultComboBoxModel modeloProveedor = new DefaultComboBoxModel();
	private DefaultTableModel modeloTbCompra = new DefaultTableModel();
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private Double totalCompras;
	private Double totalComprasPendientes;
	private Double totalComprasCanceladas;
	private List<CompraArticulos> listaCompraArticulos;
	private int filaSeleccionada;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentReportarCompra() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentReportarCompra.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Reporte de Compras");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 1116, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 1088, 94);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JPanel pnProveedor = new JPanel();
		pnProveedor.setBounds(236, 11, 250, 38);
		pn1.add(pnProveedor);
		pnProveedor.setBackground(new Color(0, 51, 0));
		pnProveedor.setLayout(null);
		
		cbProveedor = new JComboBox();
		cbProveedor.setBounds(0, 16, 250, 22);
		pnProveedor.add(cbProveedor);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProveedor.setBackground(SystemColor.desktop);
		lblProveedor.setForeground(SystemColor.window);
		lblProveedor.setBounds(94, 0, 73, 14);
		pnProveedor.add(lblProveedor);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				reportarCompra();
				calcularTotalOC();
				calcularCantidadCompras();
				calcularTotalPendientes();
				calcularTotalCanceladas();
			}
		});
		btnMostrar.setForeground(new Color(0, 51, 0));
		btnMostrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMostrar.setBounds(747, 20, 117, 23);
		pn1.add(btnMostrar);
		
		JPanel pnFechaCausacion = new JPanel();
		pnFechaCausacion.setLayout(null);
		pnFechaCausacion.setBackground(new Color(0, 51, 0));
		pnFechaCausacion.setBounds(496, 11, 198, 72);
		pn1.add(pnFechaCausacion);
		
		JLabel lblFechaCausacion = new JLabel("Fecha de Causaci\u00F3n");
		lblFechaCausacion.setForeground(Color.WHITE);
		lblFechaCausacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaCausacion.setBackground(Color.BLACK);
		lblFechaCausacion.setBounds(42, 0, 119, 14);
		pnFechaCausacion.add(lblFechaCausacion);
		
		dchDesde = new JDateChooser();
		dchDesde.setBounds(49, 18, 144, 20);
		dchDesde.setDate(new Date());
		pnFechaCausacion.add(dchDesde);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setForeground(Color.WHITE);
		lblDesde.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDesde.setBackground(Color.BLACK);
		lblDesde.setBounds(8, 21, 50, 14);
		pnFechaCausacion.add(lblDesde);
		
		dchHasta = new JDateChooser();
		dchHasta.setBounds(49, 45, 144, 20);
		dchHasta.setDate(new Date());
		pnFechaCausacion.add(dchHasta);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setForeground(Color.WHITE);
		lblHasta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHasta.setBackground(Color.BLACK);
		lblHasta.setBounds(8, 48, 50, 14);
		pnFechaCausacion.add(lblHasta);
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 116, 1088, 320);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JScrollPane scrCompra = new JScrollPane();
		scrCompra.setBounds(2, 2, 1084, 316);
		pn2.add(scrCompra);
		
		tbCompras = new JTable();
		tbCompras.setEnabled(false);
		tbCompras.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbCompras.rowAtPoint(point);
		        tbCompras.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		        mostrarPopupEnTabla();
			}
		});
		scrCompra.setViewportView(tbCompras);
		
		
		
		JPanel pn3 = new JPanel();
		pn3.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn3.setBounds(10, 447, 1088, 62);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JPanel pnTotalOC = new JPanel();
		pnTotalOC.setLayout(null);
		pnTotalOC.setBackground(new Color(0, 51, 0));
		pnTotalOC.setBounds(180, 11, 104, 38);
		pn3.add(pnTotalOC);
		
		JLabel lblComprado = new JLabel("Comprado");
		lblComprado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblComprado.setForeground(Color.WHITE);
		lblComprado.setBackground(SystemColor.desktop);
		lblComprado.setBounds(24, 0, 65, 14);
		pnTotalOC.add(lblComprado);
		
		txtTotalOC = new JTextField();
		txtTotalOC.setColumns(10);
		txtTotalOC.setBounds(0, 18, 104, 20);
		txtTotalOC.setEditable(false);
		pnTotalOC.add(txtTotalOC);
		
		JPanel pnItem = new JPanel();
		pnItem.setLayout(null);
		pnItem.setBackground(new Color(0, 51, 0));
		pnItem.setBounds(294, 11, 60, 38);
		pn3.add(pnItem);
		
		JLabel lblItem = new JLabel("Items");
		lblItem.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblItem.setForeground(Color.WHITE);
		lblItem.setBackground(SystemColor.desktop);
		lblItem.setBounds(14, 0, 49, 14);
		pnItem.add(lblItem);
		
		txtItem = new JTextField();
		txtItem.setColumns(10);
		txtItem.setBounds(0, 18, 60, 20);
		txtItem.setEditable(false);
		pnItem.add(txtItem);
		
		JButton btnExportarExcel = new JButton("Exportar a Excel");
		btnExportarExcel.setForeground(new Color(0, 51, 0));
		btnExportarExcel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExportarExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarExcel();
			}
		});
		btnExportarExcel.setBounds(680, 20, 140, 23);
		pn3.add(btnExportarExcel);
		
		JPanel pnEntregadas = new JPanel();
		pnEntregadas.setLayout(null);
		pnEntregadas.setBackground(new Color(0, 51, 0));
		pnEntregadas.setBounds(365, 11, 130, 38);
		pn3.add(pnEntregadas);
		
		JLabel lblCancelado = new JLabel("Cancelado");
		lblCancelado.setForeground(Color.WHITE);
		lblCancelado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCancelado.setBackground(Color.BLACK);
		lblCancelado.setBounds(37, 0, 75, 14);
		pnEntregadas.add(lblCancelado);
		
		txtCancelado = new JTextField();
		txtCancelado.setEditable(false);
		txtCancelado.setColumns(10);
		txtCancelado.setBounds(0, 18, 130, 20);
		pnEntregadas.add(txtCancelado);
		
		JPanel pnPendiente = new JPanel();
		pnPendiente.setLayout(null);
		pnPendiente.setBackground(new Color(0, 51, 0));
		pnPendiente.setBounds(505, 11, 130, 38);
		pn3.add(pnPendiente);
		
		JLabel lblPendiente = new JLabel("Pendiente");
		lblPendiente.setForeground(Color.WHITE);
		lblPendiente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPendiente.setBackground(Color.BLACK);
		lblPendiente.setBounds(36, 0, 75, 14);
		pnPendiente.add(lblPendiente);
		
		txtPendiente = new JTextField();
		txtPendiente.setEditable(false);
		txtPendiente.setColumns(10);
		txtPendiente.setBounds(0, 18, 130, 20);
		pnPendiente.add(txtPendiente);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setForeground(new Color(0, 51, 0));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalir.setBounds(847, 20, 87, 23);
		pn3.add(btnSalir);
		
		//Metodos que debe ejecutar la ventana al inicializarse
		listarProveedores();
		llenarColumnasTbOrdenesCompra();
		
	}
	
	//Metodo para listar los proveedores y desplegarlos en un combo box
	private void listarProveedores() {
		DelegadoProveedor delegadoProveedor = new DelegadoProveedor();
		List<Proveedor> proveedores = delegadoProveedor.listarProveedor();
		modeloProveedor.addElement("--TODOS LOS PROVEEDORES--");
		cbProveedor.setModel(modeloProveedor);
		
		for(Proveedor proveedor : proveedores){
			modeloProveedor.addElement(new Persona (proveedor.getNombre(), proveedor.getIdentificacion()));
			cbProveedor.setModel(modeloProveedor);
		}
	}
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbOrdenesCompra() {
		modeloTbCompra.addColumn("Compra");
		modeloTbCompra.addColumn("Proveedor");
		modeloTbCompra.addColumn("Identificación");
		modeloTbCompra.addColumn("Factura");
		modeloTbCompra.addColumn("OC");
		modeloTbCompra.addColumn("Causada");
		modeloTbCompra.addColumn("Generada");
		modeloTbCompra.addColumn("Fecha Pago");
		modeloTbCompra.addColumn("Entregado");
		modeloTbCompra.addColumn("Total");
		modeloTbCompra.addColumn("Estado");
		modeloTbCompra.addColumn("EGR");
		modeloTbCompra.addColumn("CXP");
		modeloTbCompra.addColumn("Items");
		
		tbCompras.setModel(modeloTbCompra);
	}
	
	//Metodo para listar las compras de acuerdo a los parametros de fecha causacion y proveedor
	private void reportarCompra(){
		
		DelegadoCompraArticulos delegadoCompraArticulos = new DelegadoCompraArticulos();
		
		if(cbProveedor.getSelectedItem().equals("--TODOS LOS PROVEEDORES--") && dchDesde.getDate()!=null && dchHasta.getDate()!=null){
			listaCompraArticulos = delegadoCompraArticulos.reportarComprasPorFE(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
			limpiarTabla();
			llenarTabla(listaCompraArticulos);
		}else{
			listaCompraArticulos = delegadoCompraArticulos.reportarComprasPorPFE(cbProveedor.getSelectedItem().toString(),restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
			limpiarTabla();
			llenarTabla(listaCompraArticulos);
		}
		
	}
	
	//Metodo para llenar la tabla cuando recibe como parametro una lista de compras
	private void llenarTabla( List<CompraArticulos> listaCompraArticulos) {
		
		String [] fila = new String[modeloTbCompra.getColumnCount()];
		
		for(CompraArticulos compras : listaCompraArticulos){
			
			fila[0]= String.valueOf(compras.getIdCompra());
			fila[1]= compras.getProveedores().getNombre();
			fila[2]= compras.getProveedores().getIdentificacion();
			fila[3]= String.valueOf(compras.getFactProv());
			if(compras.getOrdCompra()==0){
				fila[4]= "";
			}else{
				fila[4]= String.valueOf(compras.getOrdCompra());
			}
			fila[5]= convertirDateAString(compras.getFechaCausacion());
			fila[6]= convertirDateAString(compras.getFechaGeneracion());
			fila[7]= convertirDateAString(compras.getFechaPago());
			fila[8]= convertirDateAString(compras.getFechaEntrega());
			fila[9]= formatearNumero(compras.getTotalCompra());
			fila[10]= compras.getEstadoPago();	
			try{
				fila[11]= String.valueOf(compras.getEgreso());
			}catch(NullPointerException n){
				fila[11]= "";
			}
			try{
				fila[12]= String.valueOf(compras.getCuentaXPagar()); 
			}catch(NullPointerException n){
				fila[12]= "";
			}
			fila[13]= Integer.toString(compras.getItem());
			
			modeloTbCompra.addRow(fila);
		}
		
		tbCompras.setModel(modeloTbCompra);
		
		colorearEstadoEnTabla(tbCompras);
		
	}

	//Metodo para calcular el saldo total de las compras encontradas
	private void calcularTotalOC(){
		
		Double totalOC1 = (double) 0;
		
		for(int i=0; i<tbCompras.getRowCount(); i++) {
			totalCompras= Double.parseDouble(desformatearNumero(String.valueOf(tbCompras.getValueAt(i,9))));
			totalOC1 += totalCompras;
		}
		
		txtTotalOC.setText(formatearNumero(totalOC1));

	}
	
	//Metodo para mostrar un pequeño menu en cada celda de la tabla seleccionada
	private void mostrarPopupEnTabla(){
		JPopupMenu pmArticulos = new JPopupMenu();
		JMenuItem miVerCompra = new JMenuItem("Ver Detalle Compra");
		JMenuItem miVerEgreso = new JMenuItem("Ver Egreso");
		JMenuItem miVerCxP = new JMenuItem("Ver CXP");
		
		CompraArticulos datosCompra= listaCompraArticulos.get(filaSeleccionada);
		if(datosCompra.getEstadoPago().equals("Cancelado")){
			pmArticulos.add(miVerCompra);
			miVerCompra.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verFacturaCompra();
				}
			});
			
			pmArticulos.add(miVerEgreso);
			miVerEgreso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verEgreso();
				}

			});

			tbCompras.setComponentPopupMenu(pmArticulos);
		}else{
			pmArticulos.add(miVerCompra);
			miVerCompra.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verFacturaCompra();
				}
			});
			pmArticulos.add(miVerCxP);
			miVerCxP.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verCuentaXPagar();
				}
			});

			tbCompras.setComponentPopupMenu(pmArticulos);
		}
	}
	//Metodo para calcular el total de compras pendientes
	private void calcularTotalPendientes(){
		
		Double totalOCE1 = (double) 0;
		
		for(int i=0; i<tbCompras.getRowCount(); i++) {
			if(tbCompras.getValueAt(i,10).equals("Pendiente")){
				totalComprasPendientes= Double.parseDouble(desformatearNumero(String.valueOf(tbCompras.getValueAt(i,9))));
				totalOCE1 += totalComprasPendientes;
			}
		}
		
		txtPendiente.setText(formatearNumero(totalOCE1));

	}
	//Metodo para calcular el total de compras canceladas
	private void calcularTotalCanceladas(){
		
		Double totalOCP1 = (double) 0;
		
		for(int i=0; i<tbCompras.getRowCount(); i++) {
			if(tbCompras.getValueAt(i,10).equals("Cancelado")){
				totalComprasCanceladas= Double.parseDouble(desformatearNumero(String.valueOf(tbCompras.getValueAt(i,9))));
				totalOCP1 += totalComprasCanceladas;
			}
		}
		
		txtCancelado.setText(formatearNumero(totalOCP1));

	}
	//Metodo que permite calcular la cantidad de compras encontradas
	private void calcularCantidadCompras() {
		
		int cantidad = tbCompras.getRowCount();
		txtItem.setText(Integer.toString(cantidad));
		
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
	       for (int i = 0; i < tbCompras.getRowCount(); i++) {
	    	   modeloTbCompra.removeRow(i);
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
	
	//Ventana para colorear las celdas de los estados pendiente (rojo) y cancelado (verde)
	private void colorearEstadoEnTabla(JTable tablaCompras) {
		tablaCompras.getColumn("Estado").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			boolean valid = String.valueOf(table.getValueAt(row, 10)).equals("Pendiente");
	        comp.setBackground(valid ? Color.getHSBColor(0.08f, 0.78f, 0.83f) : Color.getHSBColor(1.19f, 0.26f, 0.77f));
	        return comp; 
			}}); 
	}
	
	//Metodo para visualizar la factura de compra en forma de reporte para luego ser impreso o guardado
	private void verFacturaCompra(){

	 DelegadoDetalleCompra delegadoDetalleCompra = new DelegadoDetalleCompra();
	 URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteFacturaCompra.jasper");
	 List<ReporteDetalle> lista = new ArrayList<ReporteDetalle>();
	 
	 CompraArticulos compra= listaCompraArticulos.get(filaSeleccionada);
	 List<DetalleCompra> listaDetalleCompra = delegadoDetalleCompra.listarDetallePorCodigoCompra(compra.getIdCompra()); 
	 
	 for(DetalleCompra detalles : listaDetalleCompra){
		 ReporteDetalle detalleCompra = new ReporteDetalle(detalles.getArticulo().getCodigo(),detalles.getArticulo().getNombre(), detalles.getArticulo().getUnidadMedida().getAbreviatura(), detalles.getCantidad() , detalles.getArticulo().getLineaArticulos().getNombreL(), formatearNumero(detalles.getCosto()), formatearNumero(detalles.getTotal()));
		 lista.add(detalleCompra);
	 }
	        try {
	            JasperReport reporte= (JasperReport) JRLoader.loadObject(ubicacion);
	            Map<String, Object> parametros = new HashMap<String, Object>();
	            parametros.put("logoJungla", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png"));
	            parametros.put("idCompra", compra.getIdCompra());
	            parametros.put("nombreProveedor", compra.getProveedores().getNombre());
	            parametros.put("fechaGeneracion", convertirDateAString(compra.getFechaGeneracion()));
	            parametros.put("identProveedor", compra.getProveedores().getIdentificacion());
	            parametros.put("fechaCausado", convertirDateAString(compra.getFechaCausacion()));
	            parametros.put("factProv", String.valueOf(compra.getFactProv()));
	            parametros.put("ordenCompra", String.valueOf(compra.getOrdCompra()));
	            parametros.put("fechaPago", convertirDateAString(compra.getFechaPago()));
	            parametros.put("fechaEntrega", convertirDateAString(compra.getFechaEntrega()));
	            parametros.put("subtotal", formatearNumero(compra.getSubtotal()));
	            parametros.put("descuento", formatearNumero(compra.getDescuento()));
	            parametros.put("total", formatearNumero(compra.getTotalCompra()));
	            parametros.put("items", String.valueOf(compra.getItem()));
	            parametros.put("observaciones", compra.getObservaciones());
	            parametros.put("anulado", "");
			    parametros.put("fechaAnulado", "");
	            
	            JasperPrint jprint= JasperFillManager.fillReport(reporte, parametros,new JRBeanCollectionDataSource(lista));
	            JasperViewer.viewReport(jprint,false);
	        } catch (JRException ex) {
	            Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
	
	//Metodo para visualizar la cuenta por pagar como un reporte para ser impreso o guardado
	private void verCuentaXPagar() {
		DelegadoCuentaXPagar delegadoCuentaXPagar = new DelegadoCuentaXPagar();
		DelegadoProveedor delegadoProveedor = new DelegadoProveedor();
		DelegadoPagoAbonoCxP delegadoPagoAbonoCxP = new DelegadoPagoAbonoCxP();
		
		 URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteCuentaXPagar.jasper");
		 
		 CompraArticulos compra= listaCompraArticulos.get(filaSeleccionada);
		 CuentaXPagar cuentaxPagar = delegadoCuentaXPagar.traerCuentaXPagarPorCodigo(compra.getCuentaXPagar()).get(0);
		 Proveedor proveedor = delegadoProveedor.traerProveedorPorIdentificacion(cuentaxPagar.getIdentificacionProv()).get(0);
		 double acumulador = 0;
		 double totalAbonado = 0;
		 
		 for(PagoAbonoCxP abonos : delegadoPagoAbonoCxP.listarPagoAbonoCxPPorCodigoCxP(cuentaxPagar.getIdCuentaXPagar())){
			 acumulador = abonos.getPagoAbono();
			 totalAbonado += acumulador;
		 }
		 
	        try {
	            JasperReport reporte= (JasperReport) JRLoader.loadObject(ubicacion);
	            Map<String, Object> parametros = new HashMap<String, Object>();
	            parametros.put("logoJungla", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png"));
	            parametros.put("firma", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/firma.jpg"));
	            parametros.put("idCxP", cuentaxPagar.getIdCuentaXPagar());
	            parametros.put("fechaGeneracion", convertirDateAString(cuentaxPagar.getFechaGeneracion()));
	            parametros.put("fechaCausado", convertirDateAString(cuentaxPagar.getFechaCausacion()));
	            parametros.put("fechaPago", convertirDateAString(cuentaxPagar.getFechaPago()));
	            parametros.put("nombreProveedor", proveedor.getNombre());
	            parametros.put("identProveedor", proveedor.getIdentificacion());
	            parametros.put("direccion", proveedor.getDireccion());
	            parametros.put("ciudad", proveedor.getMunicipio().getNombre());
	            parametros.put("departamento", proveedor.getMunicipio().getDepartamento().getNombre());
	            parametros.put("subtotal", formatearNumero(cuentaxPagar.getSubtotal()));
	            parametros.put("otros", formatearNumero(cuentaxPagar.getOtros()));
	            parametros.put("total", formatearNumero(cuentaxPagar.getTotalPagar()));
	            parametros.put("concepto", String.valueOf(cuentaxPagar.getConcepto()+" N° "+cuentaxPagar.getCompra()));
	            parametros.put("factProv", String.valueOf(compra.getFactProv()));
	            parametros.put("observaciones", cuentaxPagar.getObservaciones());
	            parametros.put("estado", cuentaxPagar.getEstado()+ ", Saldo = " + formatearNumero(cuentaxPagar.getTotalPagar()- totalAbonado));
	            
	            JasperPrint jprint= JasperFillManager.fillReport(reporte, parametros,new JREmptyDataSource());
	            JasperViewer.viewReport(jprint,false);
	        } catch (JRException ex) {
	            Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
	
	//Metodo para visualizar el egreso como un reporte para ser impreso o guardado
	private void verEgreso() {
		DelegadoEgreso delegadoEgreso = new DelegadoEgreso();
		DelegadoProveedor delegadoProveedor = new DelegadoProveedor();
		
		 URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteEgreso.jasper");
		 
		 CompraArticulos compra= listaCompraArticulos.get(filaSeleccionada);
		 Egreso egreso = delegadoEgreso.traerEgresoPorCodigo(compra.getEgreso()).get(0);
		 Proveedor proveedor = delegadoProveedor.traerProveedorPorIdentificacion(egreso.getIdentificacionProv()).get(0);
		 
	        try {
	            JasperReport reporte= (JasperReport) JRLoader.loadObject(ubicacion);
	            Map<String, Object> parametros = new HashMap<String, Object>();
	            parametros.put("logoJungla", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png"));
	            parametros.put("firma", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/firma.jpg"));
	            parametros.put("idEgreso", egreso.getIdEgreso());
	            parametros.put("idCxP", egreso.getIdCuentaXPagar());
	            parametros.put("fechaGeneracion", convertirDateAString(egreso.getFechaGeneracion()));
	            parametros.put("fechaPago", convertirDateAString(egreso.getFechaPago()));
	            parametros.put("nombreProveedor", proveedor.getNombre());
	            parametros.put("identProveedor", proveedor.getIdentificacion());
	            parametros.put("direccion", proveedor.getDireccion());
	            parametros.put("ciudad", proveedor.getMunicipio().getNombre());
	            parametros.put("departamento", proveedor.getMunicipio().getDepartamento().getNombre());
	            parametros.put("subtotal", formatearNumero(egreso.getSubtotal()));
	            parametros.put("otros", formatearNumero(egreso.getOtros()));
	            parametros.put("total", formatearNumero(egreso.getTotalPagado()));
	            parametros.put("concepto", String.valueOf(egreso.getConcepto()+" N° "+egreso.getCompra()));
	            parametros.put("factProv", String.valueOf(compra.getFactProv()));
	            parametros.put("observaciones", egreso.getObservaciones());
	            parametros.put("formaPago", egreso.getMedioPagoProv1().getDescripcion() +" y " +egreso.getMedioPagoProv2().getDescripcion());
	            
	            JasperPrint jprint= JasperFillManager.fillReport(reporte, parametros,new JREmptyDataSource());
	            JasperViewer.viewReport(jprint,false);
	        } catch (JRException ex) {
	            Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
	
	//Metodo para exportar la tabla en excel
	private void exportarExcel(){
		VentExportarExcel ventExportarExcel = new VentExportarExcel(tbCompras, getTitle());
		ventExportarExcel.setVisible(true);
	}
}
