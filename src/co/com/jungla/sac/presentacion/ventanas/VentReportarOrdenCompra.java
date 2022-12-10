package co.com.jungla.sac.presentacion.ventanas;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import co.com.jungla.sac.persistencia.entidades.DetalleOrdenCompra;
import co.com.jungla.sac.persistencia.entidades.OrdenCompraArticulos;
import co.com.jungla.sac.persistencia.entidades.Persona;
import co.com.jungla.sac.persistencia.entidades.Proveedor;

import co.com.jungla.sac.negocio.delegados.DelegadoDetalleOrdenCompra;
import co.com.jungla.sac.negocio.delegados.DelegadoOrdenCompraArticulos;
import co.com.jungla.sac.negocio.delegados.DelegadoProveedor;
import com.toedter.calendar.JDateChooser;

import javax.swing.border.LineBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.UIManager;
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


public class VentReportarOrdenCompra extends JFrame {

	private JPanel contentPane;
	private JTable tbOrdenesCompra;
	private JTextField txtTotalOC;
	private JTextField txtItem;
	private JComboBox cbProveedor;
	private JComboBox cbEstado;
	private DefaultComboBoxModel modeloProveedor = new DefaultComboBoxModel();
	private DefaultTableModel modeloTbOrdenesCompra = new DefaultTableModel();
	private Double totalOC;
	private Double totalOCE;
	private Double totalOCP;
	private JTextField txtEntregadas;
	private JTextField txtPendiente;
	private JDateChooser dchDesde;
	private JDateChooser dchHasta;
	private List<OrdenCompraArticulos> listaOrdenesCompraArticulos;
	private int filaSeleccionada;
	private DelegadoProveedor delegadoProveedor = new DelegadoProveedor();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentReportarOrdenCompra frame = new VentReportarOrdenCompra();
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
	public VentReportarOrdenCompra() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentReportarOrdenCompra.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Reporte de Ordenes de Compra");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 1049, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 1013, 94);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JPanel pnProveedor = new JPanel();
		pnProveedor.setBounds(119, 11, 250, 38);
		pn1.add(pnProveedor);
		pnProveedor.setBackground(new Color(0, 51, 0));
		pnProveedor.setLayout(null);
		
		//Funcionalidad para crear el proveedor al desplegar el combo box
		cbProveedor = new JComboBox();
		cbProveedor.setBounds(0, 16, 250, 22);
		pnProveedor.add(cbProveedor);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProveedor.setBackground(SystemColor.desktop);
		lblProveedor.setForeground(SystemColor.window);
		lblProveedor.setBounds(94, 0, 73, 14);
		pnProveedor.add(lblProveedor);
		
		JPanel pnEstado = new JPanel();
		pnEstado.setLayout(null);
		pnEstado.setBackground(new Color(0, 51, 0));
		pnEstado.setBounds(587, 11, 155, 38);
		pn1.add(pnEstado);
		
		cbEstado = new JComboBox();
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"Todas", "Entregada", "Pendiente"}));
		cbEstado.setBounds(0, 16, 155, 22);
		pnEstado.add(cbEstado);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstado.setBackground(Color.BLACK);
		lblEstado.setBounds(60, 0, 74, 14);
		pnEstado.add(lblEstado);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				reportarOrdenesCompra();
				calcularTotalOC();
				calcularCantidadOrdenes();
				calcularTotalPendientes();
				calcularTotalEntregadas();
			}
		});
		btnMostrar.setForeground(new Color(0, 51, 0));
		btnMostrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMostrar.setBounds(788, 20, 117, 23);
		pn1.add(btnMostrar);
		
		JPanel pnFechaEntrega = new JPanel();
		pnFechaEntrega.setLayout(null);
		pnFechaEntrega.setBackground(new Color(0, 51, 0));
		pnFechaEntrega.setBounds(379, 11, 198, 72);
		pn1.add(pnFechaEntrega);
		
		JLabel lblFechaEntrega = new JLabel("Fecha de Entrega");
		lblFechaEntrega.setForeground(Color.WHITE);
		lblFechaEntrega.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaEntrega.setBackground(Color.BLACK);
		lblFechaEntrega.setBounds(49, 0, 111, 14);
		pnFechaEntrega.add(lblFechaEntrega);
		
		dchDesde = new JDateChooser();
		dchDesde.setBounds(49, 18, 144, 20);
		dchDesde.setDate(new Date());
		pnFechaEntrega.add(dchDesde);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setForeground(Color.WHITE);
		lblDesde.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDesde.setBackground(Color.BLACK);
		lblDesde.setBounds(8, 21, 50, 14);
		pnFechaEntrega.add(lblDesde);
		
		dchHasta = new JDateChooser();
		dchHasta.setBounds(49, 45, 144, 20);
		dchHasta.setDate(new Date());
		pnFechaEntrega.add(dchHasta);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setForeground(Color.WHITE);
		lblHasta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHasta.setBackground(Color.BLACK);
		lblHasta.setBounds(8, 48, 50, 14);
		pnFechaEntrega.add(lblHasta);
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 116, 1013, 320);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JScrollPane scrOrdenesCompra = new JScrollPane();
		scrOrdenesCompra.setBounds(2, 29, 1009, 289);
		pn2.add(scrOrdenesCompra);
		
		tbOrdenesCompra = new JTable();
		tbOrdenesCompra.setEnabled(false);
		tbOrdenesCompra.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbOrdenesCompra.rowAtPoint(point);
		        tbOrdenesCompra.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		        mostrarPopupEnTabla();
			}
		});
		scrOrdenesCompra.setViewportView(tbOrdenesCompra);
      		
		
		
		JLabel lblNota = new JLabel("NOTA: Si desea cargar los datos de la orden de compra a la utilidad de compras, ver su detalle o cambiar su estado SELECCIONE la orden encontrada y PRESIONE CLIC DERECHO ");
		lblNota.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNota.setBounds(19, 8, 992, 14);
		pn2.add(lblNota);
		
		JPanel pn3 = new JPanel();
		pn3.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn3.setBounds(10, 447, 1021, 62);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JPanel pnTotalOC = new JPanel();
		pnTotalOC.setLayout(null);
		pnTotalOC.setBackground(new Color(0, 51, 0));
		pnTotalOC.setBounds(134, 11, 104, 38);
		pn3.add(pnTotalOC);
		
		JLabel lblTotalOC = new JLabel("Total OC");
		lblTotalOC.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalOC.setForeground(Color.WHITE);
		lblTotalOC.setBackground(SystemColor.desktop);
		lblTotalOC.setBounds(29, 0, 60, 14);
		pnTotalOC.add(lblTotalOC);
		
		txtTotalOC = new JTextField();
		txtTotalOC.setColumns(10);
		txtTotalOC.setBounds(0, 18, 104, 20);
		txtTotalOC.setEditable(false);
		pnTotalOC.add(txtTotalOC);
		
		JPanel pnItem = new JPanel();
		pnItem.setLayout(null);
		pnItem.setBackground(new Color(0, 51, 0));
		pnItem.setBounds(248, 11, 60, 38);
		pn3.add(pnItem);
		
		JLabel lblItem = new JLabel("Item");
		lblItem.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblItem.setForeground(Color.WHITE);
		lblItem.setBackground(SystemColor.desktop);
		lblItem.setBounds(16, 0, 49, 14);
		pnItem.add(lblItem);
		
		txtItem = new JTextField();
		txtItem.setColumns(10);
		txtItem.setBounds(0, 18, 60, 20);
		txtItem.setEditable(false);
		pnItem.add(txtItem);
		
		JButton btnQuitarArticulo = new JButton("Exportar a Excel");
		btnQuitarArticulo.setForeground(new Color(0, 51, 0));
		btnQuitarArticulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuitarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarExcel();
			}
		});
		btnQuitarArticulo.setBounds(634, 20, 140, 23);
		pn3.add(btnQuitarArticulo);
		
		JPanel pnEntregadas = new JPanel();
		pnEntregadas.setLayout(null);
		pnEntregadas.setBackground(new Color(0, 51, 0));
		pnEntregadas.setBounds(319, 11, 130, 38);
		pn3.add(pnEntregadas);
		
		JLabel lblEntregadas = new JLabel("Entregadas");
		lblEntregadas.setForeground(Color.WHITE);
		lblEntregadas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEntregadas.setBackground(Color.BLACK);
		lblEntregadas.setBounds(29, 0, 75, 14);
		pnEntregadas.add(lblEntregadas);
		
		txtEntregadas = new JTextField();
		txtEntregadas.setEditable(false);
		txtEntregadas.setColumns(10);
		txtEntregadas.setBounds(0, 18, 130, 20);
		pnEntregadas.add(txtEntregadas);
		
		JPanel pnPendiente = new JPanel();
		pnPendiente.setLayout(null);
		pnPendiente.setBackground(new Color(0, 51, 0));
		pnPendiente.setBounds(459, 11, 130, 38);
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
		btnSalir.setBounds(801, 20, 87, 23);
		pn3.add(btnSalir);
		
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
		modeloTbOrdenesCompra.addColumn("OC");
		modeloTbOrdenesCompra.addColumn("Proveedor");
		modeloTbOrdenesCompra.addColumn("Identificación");
		modeloTbOrdenesCompra.addColumn("Generada");
		modeloTbOrdenesCompra.addColumn("Fecha Pago");
		modeloTbOrdenesCompra.addColumn("Fecha Entrega");
		modeloTbOrdenesCompra.addColumn("Total");
		modeloTbOrdenesCompra.addColumn("Estado");
		modeloTbOrdenesCompra.addColumn("Compra");
		modeloTbOrdenesCompra.addColumn("Items");
		
		tbOrdenesCompra.setModel(modeloTbOrdenesCompra);
	}
	
	//Metodo para listar las ordenes de compra de acuerdo a los parametros de fecha entrega, proveedor y estado
	private void reportarOrdenesCompra(){
		
		DelegadoOrdenCompraArticulos delegadoOrdenCompraArticulos = new DelegadoOrdenCompraArticulos();
		
		if(cbProveedor.getSelectedItem().equals("--TODOS LOS PROVEEDORES--") && dchDesde.getDate()!=null && dchHasta.getDate()!=null && cbEstado.getSelectedItem().equals("Todas")){
			listaOrdenesCompraArticulos = delegadoOrdenCompraArticulos.reportarOrdenesPorFE(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
			limpiarTabla();
			llenarTabla(listaOrdenesCompraArticulos);
		}else{
			if(cbProveedor.getSelectedItem()!="" && dchDesde.getDate()!=null && dchHasta.getDate()!=null && cbEstado.getSelectedItem().equals("Todas")){
				listaOrdenesCompraArticulos = delegadoOrdenCompraArticulos.reportarOrdenesPorPFE(cbProveedor.getSelectedItem().toString(),restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
				limpiarTabla();
				llenarTabla(listaOrdenesCompraArticulos);
			}else{
				if(cbProveedor.getSelectedItem().equals("--TODOS LOS PROVEEDORES--") && dchDesde.getDate()!=null && dchHasta.getDate()!=null && cbEstado.getSelectedItem()!="" ){
					listaOrdenesCompraArticulos = delegadoOrdenCompraArticulos.reportarOrdenesPorFEE(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), cbEstado.getSelectedItem().toString());
					limpiarTabla();
					llenarTabla(listaOrdenesCompraArticulos);
				}else{
					listaOrdenesCompraArticulos = delegadoOrdenCompraArticulos.reportarOrdenesPorPFEE(cbProveedor.getSelectedItem().toString(),restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), cbEstado.getSelectedItem().toString());
					limpiarTabla();
					llenarTabla(listaOrdenesCompraArticulos);
				}
			}
		}
		
	}
	
	//Metodo para llenar la tabla cuando recibe como parametro una lista de ordenes de compra
	private void llenarTabla( List<OrdenCompraArticulos> listaOrdenesCompraArticulos) {
		
		Object [] fila = new String[modeloTbOrdenesCompra.getColumnCount()];
		
		for(OrdenCompraArticulos ordenes : listaOrdenesCompraArticulos){
			if("Activo".equals(ordenes.getEstadoActividad())){
				fila[0]= String.valueOf(ordenes.getIdOrdenCompra());
				fila[1]= delegadoProveedor.encontrarPorIdentificacion(ordenes.getIdentificacionProv()).getNombre();
				fila[2]= ordenes.getIdentificacionProv();
				fila[3]= convertirDateAString(ordenes.getFechaGeneracion());
				fila[4]= convertirDateAString(ordenes.getFechaPagoPactado());
				fila[5]= convertirDateAString(ordenes.getFechaEntregaEsperada());
				fila[6]= formatearNumero(ordenes.getTotalOrdenCompra());
				fila[7]= ordenes.getEstado();		
				fila[8]= ordenes.getCompra();
				fila[9]= Integer.toString(ordenes.getItem());
				
				modeloTbOrdenesCompra.addRow(fila);
			}
		}
		
		tbOrdenesCompra.setModel(modeloTbOrdenesCompra);
		
		colorearEstadoEnTabla(tbOrdenesCompra);
	}

	//Metodo para calcular el saldo total de las ordenes de compra encontradas
	private void calcularTotalOC(){
		
		Double totalOC1 = (double) 0;
		
		for(int i=0; i<tbOrdenesCompra.getRowCount(); i++) {
			totalOC= Double.parseDouble(desformatearNumero(String.valueOf(tbOrdenesCompra.getValueAt(i,6))));
			totalOC1 += totalOC;
		}
		
		txtTotalOC.setText(formatearNumero(totalOC1));

	}
	//Metodo para calcular el total de ordenes pendientes
	private void calcularTotalPendientes(){
		
		Double totalOCE1 = (double) 0;
		
		for(int i=0; i<tbOrdenesCompra.getRowCount(); i++) {
			if(tbOrdenesCompra.getValueAt(i,7).equals("Pendiente")){
				totalOCE= Double.parseDouble(desformatearNumero(String.valueOf(tbOrdenesCompra.getValueAt(i,6))));
				totalOCE1 += totalOCE;
			}
		}
		
		txtPendiente.setText(formatearNumero(totalOCE1));

	}
	//Metodo para calcular el total de ordenes entregadas
	private void calcularTotalEntregadas(){
		
		Double totalOCP1 = (double) 0;
		
		for(int i=0; i<tbOrdenesCompra.getRowCount(); i++) {
			if(tbOrdenesCompra.getValueAt(i,7).equals("Entregada")){
				totalOCP= Double.parseDouble(desformatearNumero(String.valueOf(tbOrdenesCompra.getValueAt(i,6))));
				totalOCP1 += totalOCP;
			}
		}
		
		txtEntregadas.setText(formatearNumero(totalOCP1));

	}
	//Metodo que permite calcular la cantidad de ordenes encontradas
	private void calcularCantidadOrdenes() {
		
		int cantidad = tbOrdenesCompra.getRowCount();
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
	       for (int i = 0; i < tbOrdenesCompra.getRowCount(); i++) {
	           modeloTbOrdenesCompra.removeRow(i);
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
	//Metodo para cargar los datos de la orden de compra a la ventana de compra de articulos
	public void cargarACompras(){
		VentRegistrarCompraArticulos ventRegistrarCompraArticulos = new VentRegistrarCompraArticulos();
		OrdenCompraArticulos datosOrdenCompra= listaOrdenesCompraArticulos.get(filaSeleccionada);
		DelegadoDetalleOrdenCompra delegadoDetalleOrdenCompra = new DelegadoDetalleOrdenCompra();
		List <DetalleOrdenCompra> detallesOrden= delegadoDetalleOrdenCompra.listarDetalleOrdenPorCodigoOrden(datosOrdenCompra.getIdOrdenCompra());
		
		ventRegistrarCompraArticulos.agregarDatosACompras(datosOrdenCompra.getIdOrdenCompra(), delegadoProveedor.encontrarPorIdentificacion(datosOrdenCompra.getIdentificacionProv()), datosOrdenCompra.getFechaEntregaEsperada(), datosOrdenCompra.getTotalOrdenCompra(), datosOrdenCompra.getItem(), detallesOrden, datosOrdenCompra.getObservaciones(), datosOrdenCompra.getFechaPagoPactado());
		ventRegistrarCompraArticulos.setVisible(true);
	}
	//Metodo para cambiar el estado de la orden
	public void cambiarEstado(){
		DelegadoOrdenCompraArticulos delegadoOrdenCompraArticulos = new DelegadoOrdenCompraArticulos();; 
		OrdenCompraArticulos ordenAModificar = listaOrdenesCompraArticulos.get(filaSeleccionada);
		
		JTextPane txpEstado = new JTextPane();
		txpEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txpEstado.setEditable(false);
		txpEstado.setBackground(UIManager.getColor("Button.background"));
		txpEstado.setText("El sistema cambia automaticamente el estado a ENTREGADO cuando usted carga la OC en\r\nuna compra con el icono del carrito de compras.\r\n\r\nSin embargo, usted puede cambiar manualmente el estado en casos como los siguientes:\r\n\r\n* No acostumbra a cargar las OC como compras y necesita cambiar el estado manualmente.\r\n* Ha cargado una OC como compra pero solo la ha usado parcialmente.");
		
		int seleccion = JOptionPane.showOptionDialog(
				   null,
				   txpEstado, 
				   "Estado de Orden de Compra",
				   JOptionPane.YES_NO_CANCEL_OPTION,
				   JOptionPane.QUESTION_MESSAGE,
				   null,    
				   new Object[] { "Entregada", "Pendiente" }, 
				   null);

		if(seleccion == 0){
			ordenAModificar.setEstado("Entregada");
			delegadoOrdenCompraArticulos.actualizarOrdenCompraArticulos(ordenAModificar);
			reportarOrdenesCompra();
			calcularTotalEntregadas();
			calcularTotalPendientes();
		}else{
			ordenAModificar.setEstado("Pendiente");
			delegadoOrdenCompraArticulos.actualizarOrdenCompraArticulos(ordenAModificar);
			reportarOrdenesCompra();
			calcularTotalEntregadas();
			calcularTotalPendientes();
		}
	}
	//Ventana para colorear las celdas de los estados pendiente (rojo) y entregado (verde)
	private void colorearEstadoEnTabla(JTable tablaOrdenes) {
		tablaOrdenes.getColumn("Estado").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			boolean valid = String.valueOf(table.getValueAt(row, 7)).equals("Pendiente");
	        comp.setBackground(valid ? Color.getHSBColor(0.08f, 0.78f, 0.83f) : Color.getHSBColor(1.19f, 0.26f, 0.77f));
	        return comp; 
			}}); 
	}
	//Metodo para mostrar una especie de menu al interior de la tabla
	private void mostrarPopupEnTabla(){
		JPopupMenu pmArticulos = new JPopupMenu();
		JMenuItem miVerCompra = new JMenuItem("Ver Detalle Compra");
		JMenuItem miVerOrden = new JMenuItem("Ver Detalle Orden");
		JMenuItem miCargar = new JMenuItem("Cargar a Compras"); 
		JMenuItem miCambiar = new JMenuItem("Cambiar Estado");
		
		OrdenCompraArticulos datosOrdenCompra= listaOrdenesCompraArticulos.get(filaSeleccionada);
		
		if(datosOrdenCompra.getEstado().equals("Pendiente")){
			pmArticulos.add(miCargar);
			miCargar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cargarACompras();
				}
			});
			
			pmArticulos.add(miCambiar);
			miCambiar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cambiarEstado();
				}
			});
			
			pmArticulos.add(miVerOrden);
			tbOrdenesCompra.setComponentPopupMenu(pmArticulos);	
		}else{
			pmArticulos.add(miCargar);
			miCargar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cargarACompras();
				}
			});
			
			pmArticulos.add(miCambiar);
			miCambiar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cambiarEstado();
				}
			});
			
			pmArticulos.add(miVerOrden);
			pmArticulos.add(miVerCompra);
			tbOrdenesCompra.setComponentPopupMenu(pmArticulos);	
		}
	}
	
	//Metodo para exportar la tabla en excel
	private void exportarExcel(){
		VentExportarExcel ventExportarExcel = new VentExportarExcel(tbOrdenesCompra, getTitle());
		ventExportarExcel.setVisible(true);
	}
}
