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

import co.com.jungla.sac.persistencia.entidades.AnticipoProveedor;
import co.com.jungla.sac.persistencia.entidades.Persona;
import co.com.jungla.sac.persistencia.entidades.Proveedor;

import co.com.jungla.sac.negocio.delegados.DelegadoAnticipoProveedor;
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

public class VentReportarAnticipoProveedor extends JFrame {

	private JPanel contentPane;
	private JTable tbAnticiposProv;
	private JTextField txtTotal;
	private JTextField txtItems;
	private DefaultComboBoxModel modeloProveedor = new DefaultComboBoxModel();
	private DefaultTableModel modeloTbAnticiposProv = new DefaultTableModel();
	private JDateChooser dchDesde;
	private JDateChooser dchHasta;
	private List<AnticipoProveedor> listaAnticiposProv;
	private int filaSeleccionada;
	private JTextField txtAplicados;
	private JTextField txtPendientes;
	private JComboBox cbProveedor;
	private JComboBox cbEstado;
	private DelegadoProveedor delegadoProveedor = new DelegadoProveedor();


	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VentReportarAnticipoProveedor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentReportarAnticipoProveedor.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Reporte de Anticipos a Proveedores");
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
				reportarAnticipoProv();
				calcularTotalAnticipos();
				calcularCantidadAnticipos();
				calcularTotalPendientes();
				calcularTotalAplicados();
			}
		});
		btnMostrar.setForeground(new Color(0, 51, 0));
		btnMostrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMostrar.setBounds(875, 20, 117, 23);
		pn1.add(btnMostrar);
		
		JPanel pnRangoFechaPago = new JPanel();
		pnRangoFechaPago.setLayout(null);
		pnRangoFechaPago.setBackground(new Color(0, 51, 0));
		pnRangoFechaPago.setBounds(646, 11, 198, 72);
		pn1.add(pnRangoFechaPago);
		
		JLabel lblRangoFechaPago = new JLabel("Rango (Fecha Pago)");
		lblRangoFechaPago.setForeground(Color.WHITE);
		lblRangoFechaPago.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRangoFechaPago.setBackground(Color.BLACK);
		lblRangoFechaPago.setBounds(42, 0, 133, 14);
		pnRangoFechaPago.add(lblRangoFechaPago);
		
		dchDesde = new JDateChooser();
		dchDesde.setBounds(49, 18, 144, 20);
		dchDesde.setDate(new Date());
		pnRangoFechaPago.add(dchDesde);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setForeground(Color.WHITE);
		lblDesde.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDesde.setBackground(Color.BLACK);
		lblDesde.setBounds(8, 21, 50, 14);
		pnRangoFechaPago.add(lblDesde);
		
		dchHasta = new JDateChooser();
		dchHasta.setBounds(49, 45, 144, 20);
		dchHasta.setDate(new Date());
		pnRangoFechaPago.add(dchHasta);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setForeground(Color.WHITE);
		lblHasta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHasta.setBackground(Color.BLACK);
		lblHasta.setBounds(8, 48, 50, 14);
		pnRangoFechaPago.add(lblHasta);
		
		JPanel pnEstado = new JPanel();
		pnEstado.setLayout(null);
		pnEstado.setBackground(new Color(0, 51, 0));
		pnEstado.setBounds(163, 11, 189, 38);
		pn1.add(pnEstado);
		
		cbEstado = new JComboBox();
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Pendientes", "Aplicados"}));
		cbEstado.setBounds(0, 16, 189, 22);
		pnEstado.add(cbEstado);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstado.setBackground(Color.BLACK);
		lblEstado.setBounds(75, 0, 73, 14);
		pnEstado.add(lblEstado);
		
		JPanel pnProveedor = new JPanel();
		pnProveedor.setLayout(null);
		pnProveedor.setBackground(new Color(0, 51, 0));
		pnProveedor.setBounds(362, 11, 274, 38);
		pn1.add(pnProveedor);
		
		cbProveedor = new JComboBox();
		cbProveedor.setBounds(0, 16, 274, 22);
		pnProveedor.add(cbProveedor);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setForeground(Color.WHITE);
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProveedor.setBackground(Color.BLACK);
		lblProveedor.setBounds(105, 0, 85, 14);
		pnProveedor.add(lblProveedor);
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 116, 1155, 320);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JScrollPane scrAnticiposProv = new JScrollPane();
		scrAnticiposProv.setBounds(2, 2, 1151, 316);
		pn2.add(scrAnticiposProv);
		
		tbAnticiposProv = new JTable();
		tbAnticiposProv.setEnabled(false);
		tbAnticiposProv.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbAnticiposProv.rowAtPoint(point);
		        tbAnticiposProv.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		        mostrarPopupEnTabla();
			}
		});
		scrAnticiposProv.setViewportView(tbAnticiposProv);
		
		
		
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
		
		JPanel pnAplicados = new JPanel();
		pnAplicados.setLayout(null);
		pnAplicados.setBackground(new Color(0, 51, 0));
		pnAplicados.setBounds(311, 11, 160, 38);
		pn3.add(pnAplicados);
		
		JLabel lblAplicados = new JLabel("Aplicados");
		lblAplicados.setForeground(Color.WHITE);
		lblAplicados.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAplicados.setBackground(SystemColor.desktop);
		lblAplicados.setBounds(53, 0, 90, 14);
		pnAplicados.add(lblAplicados);
		
		txtAplicados = new JTextField();
		txtAplicados.setEditable(false);
		txtAplicados.setColumns(10);
		txtAplicados.setBounds(0, 18, 160, 20);
		pnAplicados.add(txtAplicados);
		
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
		
		listarProveedores();
		
	}
	
	//Metodo para listar los proveedores y desplegarlos en un combo box
	private void listarProveedores() {
		DelegadoProveedor delegadoProveedor = new DelegadoProveedor();
		List<Proveedor> proveedores = delegadoProveedor.listarProveedor();
		modeloProveedor.addElement("--TODOS LOS PROVEEDORES--");
		
		for(Proveedor proveedor : proveedores){
			modeloProveedor.addElement(new Persona (proveedor.getNombre(), proveedor.getIdentificacion()));
			cbProveedor.setModel(modeloProveedor);
		}
	}
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbAnticiposProv() {
		modeloTbAnticiposProv.addColumn("Anticipo");
		modeloTbAnticiposProv.addColumn("Fecha Gen");
		modeloTbAnticiposProv.addColumn("Fecha Anticipo");
		modeloTbAnticiposProv.addColumn("Proveedor");
		modeloTbAnticiposProv.addColumn("Concepto");
		modeloTbAnticiposProv.addColumn("CXP");
		modeloTbAnticiposProv.addColumn("Total");
		modeloTbAnticiposProv.addColumn("Estado");
		modeloTbAnticiposProv.addColumn("Fecha Egreso");
		modeloTbAnticiposProv.addColumn("Egreso");
		modeloTbAnticiposProv.addColumn("Observación");
		
		tbAnticiposProv.setModel(modeloTbAnticiposProv);
	}
	
	//Metodo para listar los anticipos proveedores de acuerdo a los parametros de fecha anticipo, estado y proveedor
	private void reportarAnticipoProv(){
		
		DelegadoAnticipoProveedor delegadoAnticipoProveedor = new DelegadoAnticipoProveedor();
		String estado;
		
		if("Pendientes".equals(cbEstado.getSelectedItem())){
			estado = "Pendiente";
		}else{
			estado = "Aplicado";
		}
		
		if(cbEstado.getSelectedItem().equals("Todos") && cbProveedor.getSelectedItem().equals("--TODOS LOS PROVEEDORES--") && dchDesde.getDate()!=null && dchHasta.getDate()!=null){
			listaAnticiposProv = delegadoAnticipoProveedor.reportarAnticiposPorF(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
			limpiarTabla();
			llenarTabla(listaAnticiposProv);
		}else{
			if(cbEstado.getSelectedItem().equals("Todos") && cbProveedor.getSelectedItem()!="" && dchDesde.getDate()!=null && dchHasta.getDate()!=null){
				listaAnticiposProv = delegadoAnticipoProveedor.reportarAnticiposPorPF(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), cbProveedor.getSelectedItem().toString());
				limpiarTabla();
				llenarTabla(listaAnticiposProv);
			}else{
				if(cbEstado.getSelectedItem()!="" && cbProveedor.getSelectedItem().equals("--TODOS LOS PROVEEDORES--") && dchDesde.getDate()!=null && dchHasta.getDate()!=null){
					listaAnticiposProv = delegadoAnticipoProveedor.reportarAnticiposPorEF(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), estado);
					limpiarTabla();
					llenarTabla(listaAnticiposProv);
				}else{
					listaAnticiposProv = delegadoAnticipoProveedor.reportarAnticiposPorPEF(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()),cbProveedor.getSelectedItem().toString() ,estado);
					limpiarTabla();
					llenarTabla(listaAnticiposProv);
				}
			}
		}
		
	}
	
	//Metodo para llenar la tabla cuando recibe como parametro una lista de anticipos
	private void llenarTabla( List<AnticipoProveedor> listaAnticipoProv) {
		
		if(tbAnticiposProv.getModel().getColumnCount()==0){
			llenarColumnasTbAnticiposProv();
		}
		String [] fila = new String[modeloTbAnticiposProv.getColumnCount()];
		
		for(AnticipoProveedor anticipos : listaAnticipoProv){
			
			fila[0]= String.valueOf(anticipos.getIdAnticipoProveedor());
			fila[1]= convertirDateAString(anticipos.getFechaGeneracion());
			fila[2]= convertirDateAString(anticipos.getFechaAnticipo());
			fila[3]= delegadoProveedor.encontrarPorIdentificacion(anticipos.getIdentificacionProv()).getNombre();
			fila[4]= anticipos.getConcepto().toString();
			if(anticipos.getCxp()==0){
				fila[5]= "";
			}else{
				fila[5]= String.valueOf(anticipos.getCxp());
			}
			fila[6]= formatearNumero(anticipos.getValorAnticipo());
			fila[7]= anticipos.getEstadoAnticipo().toString();
			if(anticipos.getFechaEgreso()==null){
				fila[8]= "";
			}else{
				fila[8]= convertirDateAString(anticipos.getFechaEgreso());
			}
			if(anticipos.getEgreso()==0){
				fila[9]= "";
			}else{
				fila[9]= String.valueOf(anticipos.getEgreso());
			}
			fila[10]= anticipos.getObservaciones().toString();
			
			modeloTbAnticiposProv.addRow(fila);
		}
		
		tbAnticiposProv.setModel(modeloTbAnticiposProv);
		
		colorearFechaAnticipoEnTabla(tbAnticiposProv);
		
	}
	
	//Metodo para calcular el total de anticipos pendientes
	private void calcularTotalPendientes(){
		
		double totalPendientes = 0;
		double acumulador = 0;
		
		for(int i=0; i<tbAnticiposProv.getRowCount(); i++) {
			if(tbAnticiposProv.getValueAt(i,7).equals("Pendiente")){
				acumulador= Double.parseDouble(desformatearNumero(String.valueOf(tbAnticiposProv.getValueAt(i,6))));
				totalPendientes += acumulador;
			}
		}
		
		txtPendientes.setText(formatearNumero(totalPendientes));

	}
	
	//Metodo para calcular el total de anticipos aplicados
	private void calcularTotalAplicados(){
		
		double totalAplicados = 0;
		double acumulador = 0;
		
		for(int i=0; i<tbAnticiposProv.getRowCount(); i++) {
			if(tbAnticiposProv.getValueAt(i,7).equals("Aplicado")){
				acumulador= Double.parseDouble(desformatearNumero(String.valueOf(tbAnticiposProv.getValueAt(i,6))));
				totalAplicados += acumulador;
			}
		}
		
		txtAplicados.setText(formatearNumero(totalAplicados));
	}
	
	//Metodo para calcular el total de anticipos
	private void calcularTotalAnticipos(){
		
		double total = 0;
		double acumulador = 0;
		
		for(int i=0; i<tbAnticiposProv.getRowCount(); i++) {
			acumulador= Double.parseDouble(desformatearNumero(String.valueOf(tbAnticiposProv.getValueAt(i,6))));
			total += acumulador;
		}
		
		txtTotal.setText(formatearNumero(total));
	}
	
	//Metodo que permite calcular la cantidad de compras encontradas
	private void calcularCantidadAnticipos() {
		
		int cantidad = tbAnticiposProv.getRowCount();
		txtItems.setText(Integer.toString(cantidad));
		
	}
	
	//Metodo para mostrar los popus en la tabla
	private void mostrarPopupEnTabla(){
		JPopupMenu pmAnticipos = new JPopupMenu();
		JMenuItem miVerAnticipo = new JMenuItem("Ver Anticipo Proveedor");
		JMenuItem miVerEgreso = new JMenuItem("Ver Egreso");
		
		pmAnticipos.add(miVerAnticipo);
		miVerAnticipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verAnticipoProv();
			}
		});
		pmAnticipos.add(miVerEgreso);
		miVerEgreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verEgreso();
			}
		});
		tbAnticiposProv.setComponentPopupMenu(pmAnticipos);
	}
	
	//Metodo para mostrar el anticipo de proveedor
	private void verAnticipoProv() {
		// TODO Auto-generated method stub
		
	}
	
	//Metodo para mostrar el egreso
	private void verEgreso() {
		// TODO Auto-generated method stub
		
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
	       for (int i = 0; i < tbAnticiposProv.getRowCount(); i++) {
	    	   modeloTbAnticiposProv.removeRow(i);
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
	
	//Metodo para colorear la columna de la fecha de anticipo
	private void colorearFechaAnticipoEnTabla(JTable tablaArticulos) {
		tablaArticulos.getColumn("Fecha Anticipo").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			comp.setBackground(new Color(204, 255, 204));
	        return comp; 
			}}); 
	}
}
