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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.com.jungla.sac.negocio.delegados.DelegadoCompraArticulos;
import co.com.jungla.sac.negocio.delegados.DelegadoCuentaXPagar;
import co.com.jungla.sac.negocio.delegados.DelegadoEgreso;
import co.com.jungla.sac.negocio.delegados.DelegadoPagoAbonoCxP;
import co.com.jungla.sac.negocio.delegados.DelegadoProveedor;
import co.com.jungla.sac.persistencia.entidades.CompraArticulos;
import co.com.jungla.sac.persistencia.entidades.CuentaXPagar;
import co.com.jungla.sac.persistencia.entidades.Egreso;
import co.com.jungla.sac.persistencia.entidades.PagoAbonoCxP;
import co.com.jungla.sac.persistencia.entidades.Persona;
import co.com.jungla.sac.persistencia.entidades.Proveedor;

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

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el reporte de los egresos
 * @author Luis Fernando Pedroza T.
 * @version: 10/09/2016
 */
public class VentReportarEgreso extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtOtros;
	private JTextField txtSubtotal;
	private JTextField txtTotalPagado;
	private JTextField txtItems;
	private JTextField txtConcepto;
	private JDateChooser dchDesde;
	private JDateChooser dchHasta;
	private JComboBox cbProveedor;
	private JTable tbEgresos;
	private DefaultComboBoxModel modeloProveedor = new DefaultComboBoxModel();
	private DefaultTableModel modeloTbEgresos = new DefaultTableModel();
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private DelegadoCompraArticulos delegadoCompraArticulos = new DelegadoCompraArticulos();
	private List<Egreso> listaEgresos;
	private int filaSeleccionada;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentReportarEgreso() {
		setTitle("Reporte de Egresos (Gastos y Pagos)");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 1279, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 1245, 94);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				reportarEgresos();
				calcularSubtotal();
				calcularOtros();
				calcularTotal();
				calcularCantidadEgresos();
			}
		});
		btnMostrar.setForeground(new Color(0, 51, 0));
		btnMostrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMostrar.setBounds(961, 26, 117, 23);
		pn1.add(btnMostrar);
		
		JPanel pnFechaPago = new JPanel();
		pnFechaPago.setLayout(null);
		pnFechaPago.setBackground(new Color(0, 51, 0));
		pnFechaPago.setBounds(703, 11, 198, 72);
		pn1.add(pnFechaPago);
		
		JLabel lblFechaPago = new JLabel("Fecha de Pago");
		lblFechaPago.setForeground(Color.WHITE);
		lblFechaPago.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaPago.setBackground(Color.BLACK);
		lblFechaPago.setBounds(57, 0, 106, 14);
		pnFechaPago.add(lblFechaPago);
		
		dchDesde = new JDateChooser();
		dchDesde.setBounds(49, 18, 144, 20);
		dchDesde.setDate(new Date());
		pnFechaPago.add(dchDesde);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setForeground(Color.WHITE);
		lblDesde.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDesde.setBackground(Color.BLACK);
		lblDesde.setBounds(8, 21, 50, 14);
		pnFechaPago.add(lblDesde);
		
		dchHasta = new JDateChooser();
		dchHasta.setBounds(49, 45, 144, 20);
		dchHasta.setDate(new Date());
		pnFechaPago.add(dchHasta);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setForeground(Color.WHITE);
		lblHasta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHasta.setBackground(Color.BLACK);
		lblHasta.setBounds(8, 48, 50, 14);
		pnFechaPago.add(lblHasta);
		
		JPanel pnProveedor = new JPanel();
		pnProveedor.setLayout(null);
		pnProveedor.setBackground(new Color(0, 51, 0));
		pnProveedor.setBounds(182, 11, 260, 38);
		pn1.add(pnProveedor);
		
		cbProveedor = new JComboBox();
		cbProveedor.setBounds(0, 16, 260, 22);
		pnProveedor.add(cbProveedor);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setForeground(Color.WHITE);
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProveedor.setBackground(Color.BLACK);
		lblProveedor.setBounds(94, 0, 73, 14);
		pnProveedor.add(lblProveedor);
		
		JPanel pnConcepto = new JPanel();
		pnConcepto.setLayout(null);
		pnConcepto.setBackground(new Color(0, 51, 0));
		pnConcepto.setBounds(452, 11, 241, 38);
		pn1.add(pnConcepto);
		
		JLabel lblConcepto = new JLabel("Concepto");
		lblConcepto.setForeground(Color.WHITE);
		lblConcepto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConcepto.setBackground(SystemColor.desktop);
		lblConcepto.setBounds(86, 0, 108, 14);
		pnConcepto.add(lblConcepto);
		
		txtConcepto = new JTextField();
		txtConcepto.setBounds(0, 18, 241, 20);
		pnConcepto.add(txtConcepto);
		txtConcepto.setColumns(10);
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 116, 1245, 320);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JScrollPane scrEgresos = new JScrollPane();
		scrEgresos.setBounds(2, 2, 1241, 316);
		pn2.add(scrEgresos);
		
		tbEgresos = new JTable();
		tbEgresos.setEnabled(false);
		tbEgresos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbEgresos.rowAtPoint(point);
		        tbEgresos.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		        mostrarPopupEnTabla();
			}
		});
		scrEgresos.setViewportView(tbEgresos);
		
		
		
		JPanel pn3 = new JPanel();
		pn3.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn3.setBounds(10, 447, 1245, 62);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JPanel pnOtros = new JPanel();
		pnOtros.setLayout(null);
		pnOtros.setBackground(new Color(0, 51, 0));
		pnOtros.setBounds(346, 11, 153, 38);
		pn3.add(pnOtros);
		
		JLabel lblOtros = new JLabel("Otros");
		lblOtros.setForeground(Color.WHITE);
		lblOtros.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOtros.setBackground(SystemColor.desktop);
		lblOtros.setBounds(61, 0, 66, 14);
		pnOtros.add(lblOtros);
		
		txtOtros = new JTextField();
		txtOtros.setEditable(false);
		txtOtros.setColumns(10);
		txtOtros.setBounds(0, 18, 153, 20);
		pnOtros.add(txtOtros);
		
		JPanel pnSubtotal = new JPanel();
		pnSubtotal.setLayout(null);
		pnSubtotal.setBackground(new Color(0, 51, 0));
		pnSubtotal.setBounds(183, 11, 153, 38);
		pn3.add(pnSubtotal);
		
		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setForeground(Color.WHITE);
		lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSubtotal.setBackground(SystemColor.desktop);
		lblSubtotal.setBounds(50, 0, 84, 14);
		pnSubtotal.add(lblSubtotal);
		
		txtSubtotal = new JTextField();
		txtSubtotal.setEditable(false);
		txtSubtotal.setColumns(10);
		txtSubtotal.setBounds(0, 18, 153, 20);
		pnSubtotal.add(txtSubtotal);
		
		JPanel pnTotalPagado = new JPanel();
		pnTotalPagado.setLayout(null);
		pnTotalPagado.setBackground(new Color(0, 51, 0));
		pnTotalPagado.setBounds(509, 11, 153, 38);
		pn3.add(pnTotalPagado);
		
		JLabel lblTotalPagado = new JLabel("Total Pagado");
		lblTotalPagado.setForeground(Color.WHITE);
		lblTotalPagado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalPagado.setBackground(SystemColor.desktop);
		lblTotalPagado.setBounds(43, 0, 90, 14);
		pnTotalPagado.add(lblTotalPagado);
		
		txtTotalPagado = new JTextField();
		txtTotalPagado.setEditable(false);
		txtTotalPagado.setColumns(10);
		txtTotalPagado.setBounds(0, 18, 153, 20);
		pnTotalPagado.add(txtTotalPagado);
		
		JPanel pnItems = new JPanel();
		pnItems.setLayout(null);
		pnItems.setBackground(new Color(0, 51, 0));
		pnItems.setBounds(672, 11, 60, 38);
		pn3.add(pnItems);
		
		JLabel lblItems = new JLabel("Items");
		lblItems.setForeground(Color.WHITE);
		lblItems.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblItems.setBackground(SystemColor.desktop);
		lblItems.setBounds(14, 0, 49, 14);
		pnItems.add(lblItems);
		
		txtItems = new JTextField();
		txtItems.setEditable(false);
		txtItems.setColumns(10);
		txtItems.setBounds(0, 18, 60, 20);
		pnItems.add(txtItems);
		
		JButton btnExportar = new JButton("Exportar a Excel");
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarExcel();
			}
		});
		btnExportar.setForeground(new Color(0, 51, 0));
		btnExportar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExportar.setBounds(785, 18, 140, 23);
		pn3.add(btnExportar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setForeground(new Color(0, 51, 0));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalir.setBounds(954, 18, 104, 23);
		pn3.add(btnSalir);
		
		//Metodos que debe ejecutar la ventana al inicializarse
		listarProveedores();
		llenarColumnasTbEgresos();
		
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
		private void llenarColumnasTbEgresos() {
			
			modeloTbEgresos.addColumn("Egreso");
			modeloTbEgresos.addColumn("CxP");
			modeloTbEgresos.addColumn("Generado");
			modeloTbEgresos.addColumn("Pagado");
			modeloTbEgresos.addColumn("Proveedor");
			modeloTbEgresos.addColumn("Concepto");
			modeloTbEgresos.addColumn("Doc. Soporte");
			modeloTbEgresos.addColumn("Subtotal");
			modeloTbEgresos.addColumn("Otros");
			modeloTbEgresos.addColumn("Total Pagado");
			
			tbEgresos.setModel(modeloTbEgresos);
		}
		
		//Metodo para listar los egresos de acuerdo a los parametros de fecha pago y proveedor
		private void reportarEgresos(){
			
			
			DelegadoEgreso delegadoEgresos = new DelegadoEgreso();
			Persona proveedor =  (Persona) cbProveedor.getItemAt(1); 
			
			if(cbProveedor.getSelectedItem().equals("--TODOS LOS PROVEEDORES--") && txtConcepto.getText().isEmpty() && dchDesde.getDate()!=null && dchHasta.getDate()!=null){
				listaEgresos = delegadoEgresos.reportarEgresosPorF(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
				limpiarTabla();
				llenarTabla(listaEgresos);
			}else{
				if(cbProveedor.getSelectedItem().equals("--TODOS LOS PROVEEDORES--") && txtConcepto.getText()!="" && dchDesde.getDate()!=null && dchHasta.getDate()!=null){
					listaEgresos = delegadoEgresos.reportarEgresosPorFC(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), txtConcepto.getText());
					limpiarTabla();
					llenarTabla(listaEgresos);
				}else{
					if(cbProveedor.getSelectedItem()!=null && txtConcepto.getText().isEmpty() && dchDesde.getDate()!=null && dchHasta.getDate()!=null){
						
						listaEgresos = delegadoEgresos.reportarEgresosPorFP(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), proveedor.getIdentificacion());
						limpiarTabla();
						llenarTabla(listaEgresos);
					}else{
						listaEgresos = delegadoEgresos.reportarEgresosPorFCP(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), proveedor.getIdentificacion(), txtConcepto.getText());
						limpiarTabla();
						llenarTabla(listaEgresos);
					}
				}
			}
			
		}
		
		//Metodo para llenar la tabla cuando recibe como parametro una lista de egresos
		private void llenarTabla( List<Egreso> listaEgresos) {
			
			DelegadoProveedor delegadoProveedor = new DelegadoProveedor();
		
			String [] fila = new String[modeloTbEgresos.getColumnCount()];
			
			for(Egreso egresos : listaEgresos){
				
				fila[0]= String.valueOf(egresos.getIdEgreso());
				fila[1]= String.valueOf(egresos.getIdCuentaXPagar());
				fila[2]= convertirDateAString(egresos.getFechaGeneracion());
				fila[3]= convertirDateAString(egresos.getFechaPago());
				fila[4]= delegadoProveedor.traerProveedorPorIdentificacion(egresos.getIdentificacionProv()).get(0).getNombre();
				fila[5]= egresos.getConcepto();
				fila[6]= String.valueOf(egresos.getDocSoporte());
				fila[7]= formatearNumero(egresos.getSubtotal());
				fila[8]= formatearNumero(egresos.getOtros());
				fila[9]= formatearNumero(egresos.getTotalPagado());
				
				modeloTbEgresos.addRow(fila);
			}
			
			tbEgresos.setModel(modeloTbEgresos);
		}

		//Metodo para calcular el subtotal de los egresos
		private void calcularSubtotal(){
			
			double acumulador = 0;
			double subtotal = 0;
			
			for(int i=0; i<tbEgresos.getRowCount(); i++) {
				
				acumulador= Double.parseDouble(desformatearNumero(String.valueOf(tbEgresos.getValueAt(i,7))));
				subtotal += acumulador;
			}
			
			txtSubtotal.setText(formatearNumero(subtotal));
		}
		
		//Metodo para calcular el total de los egresos
		private void calcularTotal(){
			
			double acumulador = 0;
			double total = 0;
			
			for(int i=0; i<tbEgresos.getRowCount(); i++) {
				
				acumulador= Double.parseDouble(desformatearNumero(String.valueOf(tbEgresos.getValueAt(i,9))));
				total += acumulador;
			}
			
			txtTotalPagado.setText(formatearNumero(total));
		}
		
		//Metodo para calcular la columna otros de los egresos
		private void calcularOtros(){
			
			double acumulador = 0;
			double otros = 0;
			
			for(int i=0; i<tbEgresos.getRowCount(); i++) {
				
				acumulador= Double.parseDouble(desformatearNumero(String.valueOf(tbEgresos.getValueAt(i,8))));
				otros += acumulador;
			}
			
			txtOtros.setText(formatearNumero(otros));
		}
		
		//Metodo que permite calcular la cantidad de egresos
		private void calcularCantidadEgresos() {
			
			int cantidad = tbEgresos.getRowCount();
			txtItems.setText(Integer.toString(cantidad));
			
		}
		
		
		//Metodo para mostrar un pequeño menu a cada celda de la tabla
		private void mostrarPopupEnTabla(){
			JPopupMenu pmArticulos = new JPopupMenu();
			JMenuItem miVerEgreso = new JMenuItem("Ver Egreso");
			JMenuItem miVerCxP= new JMenuItem("Ver CxP");
			
			if("0".equals(tbEgresos.getValueAt(filaSeleccionada, 1).toString())){
				
				pmArticulos.add(miVerEgreso);
				miVerEgreso.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						verEgreso();
					}
				});
			}else{
				pmArticulos.add(miVerEgreso);
				miVerEgreso.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						verEgreso();
					}
				});
				
				pmArticulos.add(miVerCxP);
				miVerCxP.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						verCxP();
					}
				});
			}

			tbEgresos.setComponentPopupMenu(pmArticulos);
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
	       for (int i = 0; i < tbEgresos.getRowCount(); i++) {
	    	   modeloTbEgresos.removeRow(i);
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
		
		//Metodo para visualizar la cuenta por pagar mediante un reporte
		private void verCxP() {
			
			DelegadoCuentaXPagar delegadoCuentaXPagar = new DelegadoCuentaXPagar();
			DelegadoProveedor delegadoProveedor = new DelegadoProveedor();
			DelegadoPagoAbonoCxP delegadoPagoAbonoCxP = new DelegadoPagoAbonoCxP();
			
			 URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteCuentaXPagar.jasper");
			 
			 CuentaXPagar cuentaxPagar = delegadoCuentaXPagar.traerCuentaXPagarPorCodigo(listaEgresos.get(0).getIdCuentaXPagar()).get(0);
			 CompraArticulos compra= delegadoCompraArticulos.traerCompraPorCodigo(cuentaxPagar.getCompra());
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
		            
		        } catch (JRException ex) {
		            Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
		        }  
		}
		
	//Metodo para visualizar mediante un reporte de egreso
	private void verEgreso() {
		DelegadoProveedor delegadoProveedor = new DelegadoProveedor();
		
		 URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteEgreso.jasper");
		 
		 CompraArticulos compra= delegadoCompraArticulos.traerCompraPorCodigo(listaEgresos.get(filaSeleccionada).getCompra());
		 Egreso egreso = listaEgresos.get(filaSeleccionada);
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
	            if(egreso.getMedioPagoProv2().getDescripcion()==null){
	            	 parametros.put("formaPago", egreso.getMedioPagoProv1().getDescripcion());
	            }else{
	            	 parametros.put("formaPago", egreso.getMedioPagoProv1().getDescripcion() +" y " +egreso.getMedioPagoProv2().getDescripcion());
	            }
	           
	            JasperPrint jprint= JasperFillManager.fillReport(reporte, parametros,new JREmptyDataSource());
	            JasperViewer.viewReport(jprint,false);
	        } catch (JRException ex) {
	            Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
	
	//Metodo para exportar la tabla en excel
	private void exportarExcel(){
		VentExportarExcel ventExportarExcel = new VentExportarExcel(tbEgresos, getTitle());
		ventExportarExcel.setVisible(true);
	}
}
