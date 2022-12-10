package co.com.jungla.sac.presentacion.ventanas;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Color;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import co.com.jungla.sac.persistencia.entidades.CompraArticulos;
import co.com.jungla.sac.persistencia.entidades.OrdenCompraArticulos;
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

import java.awt.Component;

import javax.swing.table.DefaultTableCellRenderer;


public class VentReportarSalidaArticulos extends JFrame {

	private JPanel contentPane;
	private JTable tbSalidas;
	private JTextField txtTotal;
	private DefaultComboBoxModel modeloProveedor = new DefaultComboBoxModel();
	private DefaultTableModel modeloTbCompra = new DefaultTableModel();
	private Double totalOC;
	private Double totalOCE;
	private Double totalOCP;
	private JDateChooser dchDesde;
	private JDateChooser dchHasta;
	private List<CompraArticulos> listaCompraArticulos;
	
	private int filaSeleccionada;
	private JTextField txtSalidas;
	private JTextField txtlItems;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentReportarSalidaArticulos frame = new VentReportarSalidaArticulos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentReportarSalidaArticulos() {
		setTitle("Reporte de Salida de Art\u00EDculos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 1006, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 976, 94);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*limpiarTabla();
				reportarCompra();
				calcularTotalOC();
				calcularCantidadCompras();
				calcularTotalPendientes();
				calcularTotalCanceladas();*/
			}
		});
		btnMostrar.setForeground(new Color(0, 51, 0));
		btnMostrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMostrar.setBounds(431, 35, 117, 23);
		pn1.add(btnMostrar);
		
		JPanel pnRangoDias = new JPanel();
		pnRangoDias.setLayout(null);
		pnRangoDias.setBackground(new Color(0, 51, 0));
		pnRangoDias.setBounds(176, 11, 198, 72);
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
		
		JButton btnExportar = new JButton("Exportar a Excel");
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarExcel();
			}
		});
		btnExportar.setForeground(new Color(0, 51, 0));
		btnExportar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExportar.setBounds(558, 35, 140, 23);
		pn1.add(btnExportar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setForeground(new Color(0, 51, 0));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalir.setBounds(708, 35, 104, 23);
		pn1.add(btnSalir);
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 116, 976, 320);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JScrollPane scrSalidas = new JScrollPane();
		scrSalidas.setBounds(2, 2, 972, 316);
		pn2.add(scrSalidas);
		
		tbSalidas = new JTable();
		tbSalidas.setEnabled(false);
		tbSalidas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbSalidas.rowAtPoint(point);
		        tbSalidas.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		        //mostrarPopupEnTabla();
			}
		});
		scrSalidas.setViewportView(tbSalidas);
		
		
		
		JPanel pn3 = new JPanel();
		pn3.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn3.setBounds(10, 447, 976, 62);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JPanel pnTotal = new JPanel();
		pnTotal.setLayout(null);
		pnTotal.setBackground(new Color(0, 51, 0));
		pnTotal.setBounds(309, 11, 133, 38);
		pn3.add(pnTotal);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setBackground(SystemColor.desktop);
		lblTotal.setBounds(53, 0, 58, 14);
		pnTotal.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(0, 18, 133, 20);
		txtTotal.setEditable(false);
		pnTotal.add(txtTotal);
		
		JPanel pnSalidas = new JPanel();
		pnSalidas.setLayout(null);
		pnSalidas.setBackground(new Color(0, 51, 0));
		pnSalidas.setBounds(452, 11, 133, 38);
		pn3.add(pnSalidas);
		
		JLabel lblEntradas = new JLabel("Salidas");
		lblEntradas.setForeground(Color.WHITE);
		lblEntradas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEntradas.setBackground(Color.BLACK);
		lblEntradas.setBounds(42, 0, 64, 14);
		pnSalidas.add(lblEntradas);
		
		txtSalidas = new JTextField();
		txtSalidas.setEditable(false);
		txtSalidas.setColumns(10);
		txtSalidas.setBounds(0, 18, 133, 20);
		pnSalidas.add(txtSalidas);
		
		JPanel pnlItems = new JPanel();
		pnlItems.setLayout(null);
		pnlItems.setBackground(new Color(0, 51, 0));
		pnlItems.setBounds(595, 11, 60, 38);
		pn3.add(pnlItems);
		
		JLabel lblItems = new JLabel("Items");
		lblItems.setForeground(Color.WHITE);
		lblItems.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblItems.setBackground(SystemColor.desktop);
		lblItems.setBounds(14, 0, 49, 14);
		pnlItems.add(lblItems);
		
		txtlItems = new JTextField();
		txtlItems.setEditable(false);
		txtlItems.setColumns(10);
		txtlItems.setBounds(0, 18, 60, 20);
		pnlItems.add(txtlItems);
		
		//llenarColumnasTbOrdenesCompra();
		
	}
	
	/*//Metodo para listar los proveedores y desplegarlos en un combo box
	private void listarProveedores() {
		DelegadoProveedor delegadoProveedor = new DelegadoProveedor();
		List<Proveedor> proveedores = delegadoProveedor.listarProveedor();
		modeloProveedor.addElement("--TODOS LOS PROVEEDORES--");
		
		for(Proveedor proveedor : proveedores){
			modeloProveedor.addElement(new Persona (proveedor.getNombre(), proveedor.getIdentificacion()));
			cbClienteTipo.setModel(modeloProveedor);
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
		
		tbSalidas.setModel(modeloTbCompra);
	}
	
	//Metodo para listar las compras de acuerdo a los parametros de fecha causacion y proveedor
	private void reportarCompra(){
		
		DelegadoCompraArticulos delegadoCompraArticulos = new DelegadoCompraArticulos();
		
		if(cbClienteTipo.getSelectedItem().equals("--TODOS LOS PROVEEDORES--") && dchDesde.getDate()!=null && dchHasta.getDate()!=null){
			listaCompraArticulos = delegadoCompraArticulos.reportarComprasPorFE(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
			limpiarTabla();
			llenarTabla(listaCompraArticulos);
		}else{
			listaCompraArticulos = delegadoCompraArticulos.reportarComprasPorPFE(cbClienteTipo.getSelectedItem().toString(),restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
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
				fila[11]= String.valueOf(compras.getEgreso().getIdEgreso());
			}catch(NullPointerException n){
				fila[11]= "";
			}
			try{
				fila[12]= String.valueOf(compras.getCuentaxPagar().getIdCuentaXPagar()); 
			}catch(NullPointerException n){
				fila[12]= "";
			}
			fila[13]= Integer.toString(compras.getItem());
			
			modeloTbCompra.addRow(fila);
		}
		
		tbSalidas.setModel(modeloTbCompra);
		
		colorearEstadoEnTabla(tbSalidas);
		
	}

	//Metodo para calcular el saldo total de las compras encontradas
	private void calcularTotalOC(){
		
		Double totalOC1 = (double) 0;
		
		for(int i=0; i<tbSalidas.getRowCount(); i++) {
			totalOC= Double.parseDouble(desformatearNumero(String.valueOf(tbSalidas.getValueAt(i,9))));
			totalOC1 += totalOC;
		}
		
		txtTotal.setText(formatearNumero(totalOC1));

	}
	
	private void mostrarPopupEnTabla(){
		JPopupMenu pmArticulos = new JPopupMenu();
		JMenuItem miVerCompra = new JMenuItem("Ver Detalle Compra");
		JMenuItem miVerEgreso = new JMenuItem("Ver Egreso");
		JMenuItem miVerCxP = new JMenuItem("Ver CXP");
		
		CompraArticulos datosCompra= listaCompraArticulos.get(filaSeleccionada);
		if(datosCompra.getEstadoPago().equals("Cancelado")){
			pmArticulos.add(miVerCompra);
			pmArticulos.add(miVerEgreso);
			//tbCompras.add(pmArticulos);
			tbSalidas.setComponentPopupMenu(pmArticulos);
		}else{
			pmArticulos.add(miVerCompra);
			pmArticulos.add(miVerCxP);
			//tbCompras.add(pmArticulos);
			tbSalidas.setComponentPopupMenu(pmArticulos);
		}
			
			
		
	}
	//Metodo para calcular el total de compras pendientes
	private void calcularTotalPendientes(){
		
		Double totalOCE1 = (double) 0;
		
		for(int i=0; i<tbSalidas.getRowCount(); i++) {
			if(tbSalidas.getValueAt(i,10).equals("Pendiente")){
				totalOCE= Double.parseDouble(desformatearNumero(String.valueOf(tbSalidas.getValueAt(i,9))));
				totalOCE1 += totalOCE;
			}
		}
		
		txtPendiente.setText(formatearNumero(totalOCE1));

	}
	//Metodo para calcular el total de compras canceladas
	private void calcularTotalCanceladas(){
		
		Double totalOCP1 = (double) 0;
		
		for(int i=0; i<tbSalidas.getRowCount(); i++) {
			if(tbSalidas.getValueAt(i,10).equals("Cancelado")){
				totalOCP= Double.parseDouble(desformatearNumero(String.valueOf(tbSalidas.getValueAt(i,9))));
				totalOCP1 += totalOCP;
			}
		}
		
		txtCancelado.setText(formatearNumero(totalOCP1));

	}
	//Metodo que permite calcular la cantidad de compras encontradas
	private void calcularCantidadCompras() {
		
		int cantidad = tbSalidas.getRowCount();
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
	       for (int i = 0; i < tbSalidas.getRowCount(); i++) {
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
	}*/
	
	//Metodo para exportar la tabla en excel
	private void exportarExcel(){
		VentExportarExcel ventExportarExcel = new VentExportarExcel(tbSalidas, getTitle());
		ventExportarExcel.setVisible(true);
	}
}
