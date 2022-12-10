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

import co.com.jungla.sac.persistencia.entidades.DetalleVenta;
import co.com.jungla.sac.persistencia.entidades.Persona;
import co.com.jungla.sac.persistencia.entidades.ReciboCaja;
import co.com.jungla.sac.persistencia.entidades.Vendedor;
import co.com.jungla.sac.persistencia.entidades.VentaArticulos;

import co.com.jungla.sac.negocio.delegados.DelegadoDetalleVenta;
import co.com.jungla.sac.negocio.delegados.DelegadoReciboCaja;
import co.com.jungla.sac.negocio.delegados.DelegadoVendedor;
import co.com.jungla.sac.negocio.delegados.DelegadoVentaArticulos;

import com.toedter.calendar.JDateChooser;

import javax.swing.border.LineBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
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
import javax.swing.JCheckBox;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el reporte de cartera pendientes, es decir, aquellas ventas pendientes de pago
 * @author Luis Fernando Pedroza T.
 * @version: 10/09/2016
 */
public class VentReportarCarteraPendiente extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTable tbVentasPendientes;
	private JTextField txtItems;
	private JFormattedTextField txtRecaudado;
	private JFormattedTextField txtGenerado;
	private JFormattedTextField txtSaldoCorte;
	private JFormattedTextField txtCarteraMorosa;
	private JDateChooser dchDesde;
	private JDateChooser dchHasta;
	private JCheckBox chCarteraVencida;
	private JComboBox cbVendedor;
	private DefaultComboBoxModel modeloVendedor = new DefaultComboBoxModel();
	private DefaultTableModel modeloTbVentasPendientes = new DefaultTableModel();
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private DelegadoVentaArticulos delegadoVentaArticulos = new DelegadoVentaArticulos();
	private List<VentaArticulos> ventaPendientes;
	private double totalRecibosCaja;
	private List<VentaArticulos> listaVentasPendientes;
	private int filaSeleccionada;
	
	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentReportarCarteraPendiente() {
		setTitle("Cartera Pendiente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 1125, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 1088, 94);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JPanel pnVendedor = new JPanel();
		pnVendedor.setBounds(112, 11, 250, 38);
		pn1.add(pnVendedor);
		pnVendedor.setBackground(new Color(0, 51, 0));
		pnVendedor.setLayout(null);
		
		cbVendedor = new JComboBox();
		cbVendedor.setBounds(0, 16, 250, 22);
		pnVendedor.add(cbVendedor);
		
		JLabel lblPVendedor = new JLabel("Vendedor");
		lblPVendedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPVendedor.setBackground(SystemColor.desktop);
		lblPVendedor.setForeground(SystemColor.window);
		lblPVendedor.setBounds(94, 0, 73, 14);
		pnVendedor.add(lblPVendedor);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				reportarVentasPendientes();
				calcularTotalCarterMorosa();
				calcularCantidadVentasPendientes();
				calcularTotalGenerado();
				calcularTotalRecaudado();
				calcularTotalSaldo();
			}
		});
		btnMostrar.setForeground(new Color(0, 51, 0));
		btnMostrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMostrar.setBounds(861, 20, 117, 23);
		pn1.add(btnMostrar);
		
		JPanel pnRangoDias = new JPanel();
		pnRangoDias.setLayout(null);
		pnRangoDias.setBackground(new Color(0, 51, 0));
		pnRangoDias.setBounds(610, 11, 198, 72);
		pn1.add(pnRangoDias);
		
		JLabel lblRangoDias = new JLabel("Rango de dias");
		lblRangoDias.setForeground(Color.WHITE);
		lblRangoDias.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRangoDias.setBackground(Color.BLACK);
		lblRangoDias.setBounds(55, 0, 119, 14);
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
		
		chCarteraVencida = new JCheckBox("Cartera Vencida");
		chCarteraVencida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarCartaraVencida();
			}
		});
		chCarteraVencida.setFont(new Font("Tahoma", Font.BOLD, 11));
		chCarteraVencida.setBounds(112, 60, 194, 23);
		pn1.add(chCarteraVencida);
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 116, 1088, 320);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JScrollPane scrCompra = new JScrollPane();
		scrCompra.setBounds(2, 2, 1084, 316);
		pn2.add(scrCompra);
		
		tbVentasPendientes = new JTable();
		tbVentasPendientes.setEnabled(false);
		tbVentasPendientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbVentasPendientes.rowAtPoint(point);
		        tbVentasPendientes.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		        mostrarPopupEnTabla();
			}
		});
		scrCompra.setViewportView(tbVentasPendientes);
		
		JPanel pn3 = new JPanel();
		pn3.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn3.setBounds(10, 447, 1088, 62);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JPanel pnItems = new JPanel();
		pnItems.setLayout(null);
		pnItems.setBackground(new Color(0, 51, 0));
		pnItems.setBounds(742, 11, 60, 38);
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
		
		JButton btnQuitarArticulo = new JButton("Exportar a Excel");
		btnQuitarArticulo.setForeground(new Color(0, 51, 0));
		btnQuitarArticulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuitarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarExcel();
			}
		});
		btnQuitarArticulo.setBounds(841, 18, 140, 23);
		pn3.add(btnQuitarArticulo);
		
		JPanel pnCarteraMorosa = new JPanel();
		pnCarteraMorosa.setLayout(null);
		pnCarteraMorosa.setBackground(new Color(0, 51, 0));
		pnCarteraMorosa.setBounds(290, 11, 130, 38);
		pn3.add(pnCarteraMorosa);
		
		JLabel lblCarteraMorosa = new JLabel("Cartera Morosa");
		lblCarteraMorosa.setForeground(Color.WHITE);
		lblCarteraMorosa.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCarteraMorosa.setBackground(Color.BLACK);
		lblCarteraMorosa.setBounds(21, 0, 102, 14);
		pnCarteraMorosa.add(lblCarteraMorosa);
		
		txtCarteraMorosa = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtCarteraMorosa.setEditable(false);
		txtCarteraMorosa.setColumns(10);
		txtCarteraMorosa.setBounds(0, 18, 130, 20);
		pnCarteraMorosa.add(txtCarteraMorosa);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setForeground(new Color(0, 51, 0));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalir.setBounds(991, 18, 87, 23);
		pn3.add(btnSalir);
		
		JPanel pnRecaudado = new JPanel();
		pnRecaudado.setLayout(null);
		pnRecaudado.setBackground(new Color(0, 51, 0));
		pnRecaudado.setBounds(150, 11, 130, 38);
		pn3.add(pnRecaudado);
		
		JLabel lblRecaudado = new JLabel("Recaudado");
		lblRecaudado.setForeground(Color.WHITE);
		lblRecaudado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRecaudado.setBackground(Color.BLACK);
		lblRecaudado.setBounds(37, 0, 75, 14);
		pnRecaudado.add(lblRecaudado);
		
		txtRecaudado = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtRecaudado.setEditable(false);
		txtRecaudado.setColumns(10);
		txtRecaudado.setBounds(0, 18, 130, 20);
		pnRecaudado.add(txtRecaudado);
		
		JPanel pnGenerado = new JPanel();
		pnGenerado.setLayout(null);
		pnGenerado.setBackground(new Color(0, 51, 0));
		pnGenerado.setBounds(10, 11, 130, 38);
		pn3.add(pnGenerado);
		
		JLabel lblGenerado = new JLabel("Generado");
		lblGenerado.setForeground(Color.WHITE);
		lblGenerado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGenerado.setBackground(Color.BLACK);
		lblGenerado.setBounds(37, 0, 75, 14);
		pnGenerado.add(lblGenerado);
		
		txtGenerado = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtGenerado.setEditable(false);
		txtGenerado.setColumns(10);
		txtGenerado.setBounds(0, 18, 130, 20);
		pnGenerado.add(txtGenerado);
		
		JPanel pnSaldoCorte = new JPanel();
		pnSaldoCorte.setLayout(null);
		pnSaldoCorte.setBackground(new Color(0, 51, 0));
		pnSaldoCorte.setBounds(570, 11, 162, 38);
		pn3.add(pnSaldoCorte);
		
		JLabel lblSaldoCorte = new JLabel("Saldo al Corte");
		lblSaldoCorte.setForeground(Color.WHITE);
		lblSaldoCorte.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSaldoCorte.setBackground(Color.BLACK);
		lblSaldoCorte.setBounds(42, 0, 115, 14);
		pnSaldoCorte.add(lblSaldoCorte);
		
		txtSaldoCorte = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtSaldoCorte.setEditable(false);
		txtSaldoCorte.setColumns(10);
		txtSaldoCorte.setBounds(0, 18, 162, 20);
		pnSaldoCorte.add(txtSaldoCorte);
		
		//Metodos que debe ejecutar la ventana al inicializarse
		listarVendedores();
		llenarColumnasTbVentasPendientes();
	}
	
	//Metodo para listar los proveedores y desplegarlos en un combo box
	private void listarVendedores() {
		DelegadoVendedor delegadoVendedor = new DelegadoVendedor();
		List<Vendedor> vendedores= delegadoVendedor.listarVendedor();
		modeloVendedor.addElement("--TODOS LOS VENDEDORES--");
		cbVendedor.setModel(modeloVendedor);
		
		for(Vendedor vendedor : vendedores){
			modeloVendedor.addElement(new Persona (vendedor.getNombre(), vendedor.getIdentificacion()));
			cbVendedor.setModel(modeloVendedor);
		}
	}
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbVentasPendientes() {
		modeloTbVentasPendientes.addColumn("Cliente");
		modeloTbVentasPendientes.addColumn("Identificacion");
		modeloTbVentasPendientes.addColumn("Ciudad");
		modeloTbVentasPendientes.addColumn("Factura");
		modeloTbVentasPendientes.addColumn("Generado");
		modeloTbVentasPendientes.addColumn("Fecha Limite");
		modeloTbVentasPendientes.addColumn("Dias");
		modeloTbVentasPendientes.addColumn("Valor");
		modeloTbVentasPendientes.addColumn("Descuento");
		modeloTbVentasPendientes.addColumn("Vlr Neto");
		modeloTbVentasPendientes.addColumn("Vlr Recaudado");
		modeloTbVentasPendientes.addColumn("Saldo");
		modeloTbVentasPendientes.addColumn("Recibos");
		modeloTbVentasPendientes.addColumn("Forma Pago");
		modeloTbVentasPendientes.addColumn("Vendedor");
		
		tbVentasPendientes.setModel(modeloTbVentasPendientes);
	}
	
	//Metodo para listar las ventas pendientes de acuerdo a los parametros de fecha limite y vendedor
	private void reportarVentasPendientes(){
		
		if(cbVendedor.getSelectedItem().equals("--TODOS LOS VENDEDORES--") && dchDesde.getDate()!=null && dchHasta.getDate()!=null){
			listaVentasPendientes = delegadoVentaArticulos.reportarVentasPendientesPorF(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), "Pendiente");
			limpiarTabla();
			llenarTabla(listaVentasPendientes);
		}else{
			Vendedor vendedor = (Vendedor) cbVendedor.getSelectedItem();
			listaVentasPendientes = delegadoVentaArticulos.reportarVentasPendientesPorFV(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), vendedor.getIdentificacion(), "Pendiente");
			limpiarTabla();
			llenarTabla(listaVentasPendientes);
		}
		
	}
	
	//Metodo para llenar la tabla cuando recibe como parametro una lista de ventas pendientes
	private void llenarTabla( List<VentaArticulos> listaVentasPendientes) {
		
		DelegadoReciboCaja delegadoReciboCaja = new DelegadoReciboCaja();
		double acumulador = 0;
		totalRecibosCaja = 0;
		
		String [] fila = new String[modeloTbVentasPendientes.getColumnCount()];
		
		for(VentaArticulos ventasPendientes : listaVentasPendientes){
			
			List<ReciboCaja> listaRecibosCajaPorVenta = delegadoReciboCaja.traerRecibosCajaPorVenta(ventasPendientes.getIdVenta());
			
			fila[0]= ventasPendientes.getClientes().getNombre();
			fila[1]= ventasPendientes.getClientes().getIdentificacion();
			fila[2]= ventasPendientes.getClientes().getMunicipio().getNombre();
			fila[3]= String.valueOf(ventasPendientes.getIdVenta());
			fila[4]= convertirDateAString(ventasPendientes.getFechaGeneracion());
			fila[5]= convertirDateAString(ventasPendientes.getFechaLimitePago());
			fila[6]= String.valueOf(calcularDiasPlazo(ventasPendientes.getFechaLimitePago(), new Date()));
			fila[7]= formatearNumero(ventasPendientes.getSubtotal());
			fila[8]= String.valueOf((ventasPendientes.getDescuento())+"%");
			fila[9]= formatearNumero(ventasPendientes.getTotalVenta());
			for(ReciboCaja recibos : listaRecibosCajaPorVenta){
				acumulador= recibos.getTotalRecibido();
				totalRecibosCaja += acumulador;
			}
			fila[10]= formatearNumero(totalRecibosCaja);
			fila[11]= formatearNumero(ventasPendientes.getTotalVenta()-totalRecibosCaja);
			fila[12]= String.valueOf(ventasPendientes.getReciboCaja().get(0).getIdReciboCaja());
			fila[13]= ventasPendientes.getFormaPagoCliente().getDescripcion();	
			fila[14]= ventasPendientes.getVendedores().getNombre();
			
			modeloTbVentasPendientes.addRow(fila);
		}
		
		tbVentasPendientes.setModel(modeloTbVentasPendientes);
		
		colorearFechaLimiteEnTabla(tbVentasPendientes);
		colorearSaldoEnTabla(tbVentasPendientes);
		colorearDiasEnTabla(tbVentasPendientes);
	}

	//Metodo para calcular el total generado por las ventas pendientes
	private void calcularTotalGenerado(){
		
		double acumulador = 0;
		double totalGenerado = 0;
		
		for(int i=0; i<tbVentasPendientes.getRowCount(); i++) {
			
			acumulador= Double.parseDouble(desformatearNumero(String.valueOf(tbVentasPendientes.getValueAt(i,9))));
			totalGenerado += acumulador;
		}
		
		txtGenerado.setText(formatearNumero(totalGenerado));
	}
	
	//Metodo para calcular el total recaudado por los abonos o pago de las ventas pendientes
	private void calcularTotalRecaudado(){
		double acumulador = 0;
		double totalRecaudado = 0;
		
		for(int i=0; i<tbVentasPendientes.getRowCount(); i++) {
			
			acumulador= Double.parseDouble(desformatearNumero(String.valueOf(tbVentasPendientes.getValueAt(i,10))));
			totalRecaudado += acumulador;
		}
		
		txtRecaudado.setText(formatearNumero(totalRecaudado));
	}
	
	//Metodo para calcular el total de la cartera morosa
	private void calcularTotalCarterMorosa(){
		double acumulador = 0;
		double totalCarteraMorosa = 0;
		
		for(int i=0; i<tbVentasPendientes.getRowCount(); i++) {
			if(Integer.parseInt(tbVentasPendientes.getValueAt(i,6).toString())>0){
				acumulador= Double.parseDouble(desformatearNumero(tbVentasPendientes.getValueAt(i,9).toString()));
				totalCarteraMorosa += acumulador;
			}
		}
		
		txtCarteraMorosa.setText(formatearNumero(totalCarteraMorosa));
	}
	
	//Metodo para calcular el saldo al corte
	private void calcularTotalSaldo(){
		double acumulador = 0;
		double totalSaldoAlCorte = 0;
		
		for(int i=0; i<tbVentasPendientes.getRowCount(); i++) {
			
			acumulador= Double.parseDouble(desformatearNumero(tbVentasPendientes.getValueAt(i,11).toString()));
			totalSaldoAlCorte += acumulador;
		}
		
		txtSaldoCorte.setText(formatearNumero(totalSaldoAlCorte));
	}
	
	//Metodo que permite calcular la cantidad de ventas pendientes encontradas
	private void calcularCantidadVentasPendientes() {
		
		int cantidad = tbVentasPendientes.getRowCount();
		txtItems.setText(Integer.toString(cantidad));
		
	}
	
	//Metodo para mostrar un pequeño menu a cada celda de la tabla
	private void mostrarPopupEnTabla(){
		JPopupMenu pmArticulos = new JPopupMenu();
		JMenuItem miVerFactura = new JMenuItem("Ver Detalle Venta");
		JMenuItem miVerRecibo = new JMenuItem("Ver Recibo Caja");

		pmArticulos.add(miVerFactura);
		miVerFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verFacturaVenta();
			}
		});
		
		//pmArticulos.add(miVerRecibo);
		miVerRecibo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//verReciboCaja();
			}
		});

		tbVentasPendientes.setComponentPopupMenu(pmArticulos);
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
       for (int i = 0; i < tbVentasPendientes.getRowCount(); i++) {
    	   modeloTbVentasPendientes.removeRow(i);
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
	
	//Metodo para calcular los dias entre la fecha de pago y la actual
	private int calcularDiasPlazo(Date fechaPago, Date fechaActual){
		long diferencia_fechas = fechaPago.getTime() - fechaActual.getTime();
		long dias = diferencia_fechas/(1000*60*60*24);
		return (int)dias;
	}
		
	//Metodo para colorear la columna fecha limite
	private void colorearFechaLimiteEnTabla(JTable tabla) {
		tabla.getColumn("Fecha Limite").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			comp.setBackground(new Color(255, 102, 102));
	        return comp; 
			}}); 
	}
	
	//Metodo para colorear la columna saldo
	private void colorearSaldoEnTabla(JTable tabla) {
		tabla.getColumn("Saldo").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			comp.setBackground(new Color(127, 165, 162));
	        return comp; 
			}}); 
	}
	
	//Metodo para colorear la columna dias
	private void colorearDiasEnTabla(JTable tabla) {
		tabla.getColumn("Dias").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			comp.setBackground(new Color(255, 102, 102));
	        return comp; 
			}}); 
	}
	
	//Metodo para visualizar mediante un reporte la factura de la venta pendiente
	private void verFacturaVenta() {
		DelegadoDetalleVenta delegadoDetalleVenta = new DelegadoDetalleVenta();
		 URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteFacturaVenta.jasper");
		 List<ReporteDetalle> lista = new ArrayList<ReporteDetalle>();
		 
		 VentaArticulos ventasPendientes= listaVentasPendientes.get(filaSeleccionada);
		 List<DetalleVenta> listaDetalleVenta = delegadoDetalleVenta.listarDetallePorCodigoVenta(ventasPendientes.getIdVenta()); 
		 
		 for(DetalleVenta detalles : listaDetalleVenta){
			 ReporteDetalle detalleVenta = new ReporteDetalle(detalles.getArticulo().getCodigo(),detalles.getArticulo().getNombre(), detalles.getArticulo().getUnidadMedida().getAbreviatura(), detalles.getCantidad() , formatearNumero(detalles.getVlrUnitario()), formatearNumero(detalles.getTotal()));
			 lista.add(detalleVenta);
		 }
		 
        try {
		    JasperReport reporte= (JasperReport) JRLoader.loadObject(ubicacion);
		    Map<String, Object> parametros = new HashMap<String, Object>();
		    parametros.put("logoJungla", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png"));
		    parametros.put("firma", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/firma.jpg"));
		    parametros.put("idVenta", ventaPendientes.get(0).getIdVenta());
		    parametros.put("nombreCliente", ventaPendientes.get(0).getClientes().getNombre());
		    parametros.put("identCliente", ventaPendientes.get(0).getClientes().getIdentificacion());
		    parametros.put("direccion", ventaPendientes.get(0).getClientes().getDireccion());
		    parametros.put("telefono", ventaPendientes.get(0).getClientes().getTelefono());
		    parametros.put("ciudad", ventaPendientes.get(0).getClientes().getMunicipio().getNombre());
		    parametros.put("formaPago", ventaPendientes.get(0).getFormaPagoCliente().getDescripcion());
		    parametros.put("nombreVendedor", ventaPendientes.get(0).getVendedores().getNombre());
		    parametros.put("ordenCompra", String.valueOf(ventaPendientes.get(0).getOrdCompra()));
		    parametros.put("pedido", String.valueOf(ventaPendientes.get(0).getPedido()));
		    parametros.put("subtotal", formatearNumero(ventaPendientes.get(0).getSubtotal()));
		    parametros.put("descuento", formatearNumero(ventaPendientes.get(0).getDescuento()));
		    parametros.put("total", formatearNumero(ventaPendientes.get(0).getTotalVenta()));
		    parametros.put("items", String.valueOf(ventaPendientes.get(0).getItems()));
		    parametros.put("fechaFactura", convertirDateAString(ventaPendientes.get(0).getFechaGeneracion()));
		    parametros.put("fechaLimite", convertirDateAString(ventaPendientes.get(0).getFechaLimitePago()));
		    parametros.put("observaciones", ventaPendientes.get(0).getObservaciones());
		    parametros.put("anulado", "");
		    parametros.put("fechaAnulado", "");
		   
		    JasperPrint jprint= JasperFillManager.fillReport(reporte, parametros,new JRBeanCollectionDataSource(lista));
		    JasperViewer.viewReport(jprint,false);
		} catch (JRException ex) {
		    Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
	//Metodo para mostrar toda la cartera vencida al activarse el checkbox
	private void mostrarCartaraVencida() {
		
		List<VentaArticulos> ventasPendientesVencidas = delegadoVentaArticulos.reportarTodaVentasPendientesVencidas("Pendiente", new Date());

		if(chCarteraVencida.isSelected()){
			limpiarTabla();
			llenarTabla(ventasPendientesVencidas);
			
			calcularTotalCarterMorosa();
			calcularCantidadVentasPendientes();
			calcularTotalGenerado();
			calcularTotalRecaudado();
			calcularTotalSaldo();
		}else{
			reportarVentasPendientes();
			calcularTotalCarterMorosa();
			calcularCantidadVentasPendientes();
			calcularTotalGenerado();
			calcularTotalRecaudado();
			calcularTotalSaldo();
		}
	}
	
	//Metodo para exportar la tabla en excel
	private void exportarExcel(){
		VentExportarExcel ventExportarExcel = new VentExportarExcel(tbVentasPendientes, getTitle());
		ventExportarExcel.setVisible(true);
	}
}
