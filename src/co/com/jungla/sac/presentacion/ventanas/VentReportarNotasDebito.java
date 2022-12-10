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

import co.com.jungla.sac.negocio.delegados.DelegadoArticulo;
import co.com.jungla.sac.negocio.delegados.DelegadoCompraArticulos;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleCompra;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleDevolucionProveedor;
import co.com.jungla.sac.negocio.delegados.DelegadoDevolucionProveedor;
import co.com.jungla.sac.negocio.delegados.DelegadoEgreso;
import co.com.jungla.sac.negocio.delegados.DelegadoProveedor;
import co.com.jungla.sac.persistencia.entidades.Articulo;
import co.com.jungla.sac.persistencia.entidades.CompraArticulos;
import co.com.jungla.sac.persistencia.entidades.DetalleCompra;
import co.com.jungla.sac.persistencia.entidades.DetalleDevolucionProveedor;
import co.com.jungla.sac.persistencia.entidades.DevolucionProveedor;
import co.com.jungla.sac.persistencia.entidades.Egreso;
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
 *  llevar a cabo la visualizacion de las notas debito mediante un reporte
 * @author Luis Fernando Pedroza T.
 * @version: 10/09/2016
 */
public class VentReportarNotasDebito extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtTotal;
	private JTextField txtItems;
	private JTextField txtDescontadas;
	private JTextField txtPendientes;
	private JTextField txtCodProveedor;
	private JDateChooser dchDesde;
	private JDateChooser dchHasta;
	private JComboBox cbEstado;
	private JTable tbNotasDebito;
	private DefaultTableModel modeloTbNotasDebito = new DefaultTableModel();
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private List<DevolucionProveedor> listaNotasDebito;
	private int filaSeleccionada;
	private  DelegadoCompraArticulos delegadoCompraArticulos = new DelegadoCompraArticulos();;
	
	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentReportarNotasDebito() {
		setTitle("Reporte de Notas Debito");
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
				reportarNotasDebito();
				calcularTotal();
				calcularTotalDescontadas();
				calcularCantidadNotasDebito();
				calcularTotalPendientes();
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
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Pendientes", "Descontadas"}));
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
		
		JLabel lblCodCliente = new JLabel("Idt. Proveedor");
		lblCodCliente.setForeground(Color.WHITE);
		lblCodCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCodCliente.setBackground(SystemColor.desktop);
		lblCodCliente.setBounds(18, 0, 90, 14);
		pnCodCliente.add(lblCodCliente);
		
		txtCodProveedor = new JTextField();
		txtCodProveedor.setColumns(10);
		txtCodProveedor.setBounds(0, 18, 122, 20);
		pnCodCliente.add(txtCodProveedor);
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 116, 1155, 320);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JScrollPane scrNotasDebito = new JScrollPane();
		scrNotasDebito.setBounds(2, 2, 1151, 316);
		pn2.add(scrNotasDebito);
		
		tbNotasDebito = new JTable();
		tbNotasDebito.setEnabled(false);
		tbNotasDebito.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbNotasDebito.rowAtPoint(point);
		        tbNotasDebito.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		        mostrarPopupEnTabla();
			}
		});
		scrNotasDebito.setViewportView(tbNotasDebito);
		
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
		
		JButton btnImprimir = new JButton("Salir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnImprimir.setForeground(new Color(0, 51, 0));
		btnImprimir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnImprimir.setBounds(909, 20, 104, 23);
		pn3.add(btnImprimir);
		
		JPanel pnDescontadas = new JPanel();
		pnDescontadas.setLayout(null);
		pnDescontadas.setBackground(new Color(0, 51, 0));
		pnDescontadas.setBounds(311, 11, 160, 38);
		pn3.add(pnDescontadas);
		
		JLabel lblDescontadas = new JLabel("Descontadas");
		lblDescontadas.setForeground(Color.WHITE);
		lblDescontadas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescontadas.setBackground(SystemColor.desktop);
		lblDescontadas.setBounds(45, 0, 90, 14);
		pnDescontadas.add(lblDescontadas);
		
		txtDescontadas = new JTextField();
		txtDescontadas.setEditable(false);
		txtDescontadas.setColumns(10);
		txtDescontadas.setBounds(0, 18, 160, 20);
		pnDescontadas.add(txtDescontadas);
		
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
		llenarColumnasTbNotasDebito();
		
	}
	
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbNotasDebito() {
		modeloTbNotasDebito.addColumn("Nota");
		modeloTbNotasDebito.addColumn("Fecha");
		modeloTbNotasDebito.addColumn("Proveedor");
		modeloTbNotasDebito.addColumn("Compra");
		modeloTbNotasDebito.addColumn("Total");
		modeloTbNotasDebito.addColumn("Estado");
		modeloTbNotasDebito.addColumn("Fecha Egreso");
		modeloTbNotasDebito.addColumn("Egreso");
		modeloTbNotasDebito.addColumn("Concepto");
	
		tbNotasDebito.setModel(modeloTbNotasDebito);
	}
	
	//Metodo para listar las notas credito de acuerdo a los parametros de estado, rango entre fechas o codigo cliene
	private void reportarNotasDebito(){
		
		DelegadoDevolucionProveedor delegadoDevolucionProveedor = new DelegadoDevolucionProveedor();
		String estado ;
		
		if("Descontadas".equals(cbEstado.getSelectedItem().toString())){
			estado = "Descontada";
		}else{
			estado = "Pendiente";
		}
		
		if(cbEstado.getSelectedItem().equals("Todos") && txtCodProveedor.getText().isEmpty()){
			listaNotasDebito = delegadoDevolucionProveedor.reportarDevolucionesPorF(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
			limpiarTabla();
			llenarTabla(listaNotasDebito);
		}else{
			if(cbEstado.getSelectedItem().equals("Todos") && txtCodProveedor.getText()!=""){
				listaNotasDebito = delegadoDevolucionProveedor.reportarDevolucionesPorFP(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), txtCodProveedor.getText());
				limpiarTabla();
				llenarTabla(listaNotasDebito);
			}else{
				if(cbEstado.getSelectedItem()!=null && txtCodProveedor.getText()!=""){
					
					listaNotasDebito = delegadoDevolucionProveedor.reportarDevolucionesPorFPE(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), txtCodProveedor.getText(), estado );
					limpiarTabla();
					llenarTabla(listaNotasDebito);
				}else{
					listaNotasDebito = delegadoDevolucionProveedor.reportarDevolucionesPorFE(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), estado);
					limpiarTabla();
					llenarTabla(listaNotasDebito);
				}
			}
		}
	}
	
	//Metodo para llenar la tabla cuando recibe como parametro una lista de devoluciones
	private void llenarTabla( List<DevolucionProveedor> listaNotasDebito) {
		
		String [] fila = new String[modeloTbNotasDebito.getColumnCount()];
		
		for(DevolucionProveedor notasDebito : listaNotasDebito){
			
			fila[0]= String.valueOf(notasDebito.getIdDevolucionProveedor());
			fila[1]= convertirDateAString(notasDebito.getFecha());
			fila[2]= notasDebito.getCompraArticulos().getProveedores().getIdentificacion();
			fila[3]= String.valueOf(notasDebito.getCompraArticulos().getIdCompra());
			fila[4]= formatearNumero(notasDebito.getTotalDevolucion());
			fila[5]= notasDebito.getEstado();
			if(notasDebito.getFechaEgreso()==null){
				fila[6]= "";
			}else{
				fila[6]= convertirDateAString(notasDebito.getFechaEgreso());
			}
			fila[7]= String.valueOf(notasDebito.getIdEgreso());
			fila[8]= notasDebito.getConcepto();
			
			modeloTbNotasDebito.addRow(fila);
		}
		
		tbNotasDebito.setModel(modeloTbNotasDebito);
		colorearEstadoEnTabla(tbNotasDebito);
		
	}
	
	//Metodo para mostrar un pequeño menu a cada celda seleccionada
	private void mostrarPopupEnTabla(){
		JPopupMenu pmArticulos = new JPopupMenu();
		JMenuItem miVerDevolucion = new JMenuItem("Ver Nota Debito");
		JMenuItem miVerCompra = new JMenuItem("Ver Compra");
		JMenuItem miVerEgreso = new JMenuItem("Ver Egreso");
		
		pmArticulos.add(miVerDevolucion);
		miVerDevolucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verNotaDebito();
			}
		});
		 
		pmArticulos.add(miVerCompra);
		miVerCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCompra();
			}
		});
		
		if("0".equals(tbNotasDebito.getValueAt(filaSeleccionada, 7))){
			pmArticulos.add(miVerEgreso);
			miVerEgreso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verEgreso();
				}
			});
		}
	
		tbNotasDebito.setComponentPopupMenu(pmArticulos);
	}
	
	//Metodo para calcular el total devoluciones
	private void calcularTotal(){
		
		double acumulador = 0;
		double total = 0;
		
		for(int i=0; i<tbNotasDebito.getRowCount(); i++) {
			
				acumulador= Double.parseDouble(desformatearNumero(tbNotasDebito.getValueAt(i,4).toString()));
				total += acumulador;
		}
		
		txtTotal.setText(formatearNumero(total));
	}
	
	//Metodo para calcular el total de devoluciones descontadas
	private void calcularTotalDescontadas(){
		
		double acumulador = 0;
		double totalDescontadas = 0;
		
		for(int i=0; i<tbNotasDebito.getRowCount(); i++) {
			if("Descontada".equals(tbNotasDebito.getValueAt(i,4).toString())){
				acumulador= Double.parseDouble(desformatearNumero(tbNotasDebito.getValueAt(i,4).toString()));
				totalDescontadas += acumulador;
			}
		}
		
		txtDescontadas.setText(formatearNumero(totalDescontadas));
	}
	
	//Metodo para calcular el total de devoluciones pendientes
		private void calcularTotalPendientes(){
			
			double acumulador = 0;
			double totalPendientes = 0;
			
			for(int i=0; i<tbNotasDebito.getRowCount(); i++) {
				if("Pendiente".equals(tbNotasDebito.getValueAt(i,4).toString())){
					acumulador= Double.parseDouble(desformatearNumero(tbNotasDebito.getValueAt(i,4).toString()));
					totalPendientes += acumulador;
				}
			}
			
			txtPendientes.setText(formatearNumero(totalPendientes));
		}
		
	//Metodo que permite calcular la cantidad de notas debito encontradas
	private void calcularCantidadNotasDebito() {
		
		int cantidad = tbNotasDebito.getRowCount();
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
	       for (int i = 0; i < tbNotasDebito.getRowCount(); i++) {
	    	   modeloTbNotasDebito.removeRow(i);
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
	
	//Metodo para colorear las celdas de los estados pendiente y descontada
	private void colorearEstadoEnTabla(JTable tablaCompras) {
		tablaCompras.getColumn("Estado").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			boolean valid = String.valueOf(table.getValueAt(row, 5)).equals("Pendiente");
	        comp.setBackground(valid ? Color.getHSBColor(0.08f, 0.78f, 0.83f) : Color.getHSBColor(1.19f, 0.26f, 0.77f));
	        return comp; 
			}}); 
	}
	
	//Metodo para ver la nota debito
	private void verNotaDebito() {
		DelegadoDetalleDevolucionProveedor delegadoDetalleDevolucionProveedor = new DelegadoDetalleDevolucionProveedor();
		DelegadoArticulo delegadoArticulo = new DelegadoArticulo();
		
		 URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteNotaDebito.jasper");
		 List<ReporteDetalle> lista = new ArrayList<ReporteDetalle>();
		 
		 CompraArticulos compra= delegadoCompraArticulos.traerCompraPorCodigo(listaNotasDebito.get(filaSeleccionada).getCompraArticulos().getFactProv());
		 DevolucionProveedor notaDebito= listaNotasDebito.get(filaSeleccionada);
		 List<DetalleDevolucionProveedor> listaDetalleDevolucion = delegadoDetalleDevolucionProveedor.listarDetallePorCodigoDevolucionProveedor(notaDebito.getIdDevolucionProveedor()); 
		 
		 for(DetalleDevolucionProveedor detalles : listaDetalleDevolucion){
			 Articulo articulo = delegadoArticulo.traerLineaUnidadArticuloPorCodigo(detalles.getCodigoArticulo()).get(0);
			 ReporteDetalle detalleDevolucion = new ReporteDetalle(articulo.getCodigo(),articulo.getNombre(), articulo.getUnidadMedida().getAbreviatura(), detalles.getCantidadDevuelta() , formatearNumero(detalles.getCosto()), formatearNumero(detalles.getCantidadDevuelta()*detalles.getCosto()));
			 lista.add(detalleDevolucion);
		 }
		 
       try {
		    JasperReport reporte= (JasperReport) JRLoader.loadObject(ubicacion);
		    Map<String, Object> parametros = new HashMap<String, Object>();
		    parametros.put("logoJungla", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png"));
		    parametros.put("firma", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/firma.jpg"));
		    parametros.put("idNotaDebito", notaDebito.getIdDevolucionProveedor());
		    parametros.put("nombreProveedor", notaDebito.getCompraArticulos().getProveedores().getNombre());
		    parametros.put("identProveedor", notaDebito.getCompraArticulos().getProveedores().getIdentificacion());
		    parametros.put("direccion", notaDebito.getCompraArticulos().getProveedores().getDireccion());
		    parametros.put("telefono", notaDebito.getCompraArticulos().getProveedores().getTelefono());
		    parametros.put("ciudad", notaDebito.getCompraArticulos().getProveedores().getMunicipio().getNombre());
		    parametros.put("idCompra", String.valueOf(notaDebito.getCompraArticulos().getIdCompra()));
		    parametros.put("factProv", String.valueOf(compra.getFactProv()));
		    parametros.put("total", formatearNumero(notaDebito.getTotalDevolucion()));
		    parametros.put("fecha", convertirDateAString(notaDebito.getFecha()));
		    parametros.put("observaciones", notaDebito.getObservaciones());
		    
		    JasperPrint jprint= JasperFillManager.fillReport(reporte, parametros,new JRBeanCollectionDataSource(lista));
		    JasperViewer.viewReport(jprint,false);
		} catch (JRException ex) {
		    Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
	//Metodo para ver el egreso
	private void verEgreso() {
		DelegadoEgreso delegadoEgreso = new DelegadoEgreso();
		DelegadoProveedor delegadoProveedor = new DelegadoProveedor();
		
		 URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteEgreso.jasper");
		 CompraArticulos compra= delegadoCompraArticulos.traerCompraPorCodigo(listaNotasDebito.get(filaSeleccionada).getCompraArticulos().getIdCompra());
		 Egreso egreso = delegadoEgreso.traerEgresoPorCodigo(listaNotasDebito.get(filaSeleccionada).getIdEgreso()).get(0);
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
	
	//Metodo para ver la compra
	private void verCompra() {
		 DelegadoDetalleCompra delegadoDetalleCompra = new DelegadoDetalleCompra();
		 URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteFacturaCompra.jasper");
		 List<ReporteDetalle> lista = new ArrayList<ReporteDetalle>();
		 
		 CompraArticulos compra= delegadoCompraArticulos.traerCompraPorCodigo(listaNotasDebito.get(filaSeleccionada).getCompraArticulos().getIdCompra());
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
	
	//Metodo para exportar la tabla en excel
	private void exportarExcel(){
		VentExportarExcel ventExportarExcel = new VentExportarExcel(tbNotasDebito, getTitle());
		ventExportarExcel.setVisible(true);
	}
}
