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

import co.com.jungla.sac.negocio.delegados.DelegadoCompraArticulos;
import co.com.jungla.sac.negocio.delegados.DelegadoCuentaXPagar;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleCompra;
import co.com.jungla.sac.negocio.delegados.DelegadoPagoAbonoCxP;
import co.com.jungla.sac.negocio.delegados.DelegadoProveedor;
import co.com.jungla.sac.persistencia.entidades.CompraArticulos;
import co.com.jungla.sac.persistencia.entidades.CuentaXPagar;
import co.com.jungla.sac.persistencia.entidades.DetalleCompra;
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
import javax.swing.JCheckBox;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el reporte de las cuentas por pagar
 * @author Luis Fernando Pedroza T.
 * @version: 10/09/2016
 */
public class VentReportarCuentaXPagar extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtTotalCxP;
	private JTextField txtItems;
	private JTextField txtTotalAbonado;
	private JTextField txtSaldoActual;
	private JTextField txtCxpCompras;
	private JTextField txtConcepto;
	private JDateChooser dchDesde;
	private JDateChooser dchHasta;
	private JCheckBox chCxPVencidas;
	private JComboBox cbProveedor;
	private JButton btnMostrar;
	private JTable tbCxP;
	private DefaultComboBoxModel modeloProveedor = new DefaultComboBoxModel();
	private DefaultTableModel modeloTbCxP = new DefaultTableModel();
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private DelegadoCompraArticulos delegadoCompraArticulos = new DelegadoCompraArticulos();
	private List<CuentaXPagar> listaCxP;
	private int filaSeleccionada;
	
	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentReportarCuentaXPagar() {
		setTitle("Reporte de Cuentas x Pagar");
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
		
		JPanel pnConcepto = new JPanel();
		pnConcepto.setBounds(456, 11, 241, 38);
		pn1.add(pnConcepto);
		pnConcepto.setBackground(new Color(0, 51, 0));
		pnConcepto.setLayout(null);
		
		JLabel lblConcepto = new JLabel("Concepto");
		lblConcepto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConcepto.setBackground(SystemColor.desktop);
		lblConcepto.setForeground(SystemColor.window);
		lblConcepto.setBounds(85, 0, 108, 14);
		pnConcepto.add(lblConcepto);
		
		txtConcepto = new JTextField();
		txtConcepto.setBounds(0, 18, 241, 20);
		pnConcepto.add(txtConcepto);
		txtConcepto.setColumns(10);
		
		btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				reportarCuentasXPagar();
				calcularTotalCxP();
				calcularTotalAbonado();
				calcularSaldoActual();
				calcularTotalCxPCompras();
				calcularCantidadCxP();
			}
		});
		btnMostrar.setForeground(new Color(0, 51, 0));
		btnMostrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMostrar.setBounds(956, 26, 117, 23);
		pn1.add(btnMostrar);
		
		JPanel pnVendedor = new JPanel();
		pnVendedor.setLayout(null);
		pnVendedor.setBackground(new Color(0, 51, 0));
		pnVendedor.setBounds(186, 11, 260, 38);
		pn1.add(pnVendedor);
		
		cbProveedor = new JComboBox();
		cbProveedor.setBounds(0, 16, 260, 22);
		pnVendedor.add(cbProveedor);
		
		JLabel lblVendedor = new JLabel("Proveedor");
		lblVendedor.setForeground(Color.WHITE);
		lblVendedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVendedor.setBackground(Color.BLACK);
		lblVendedor.setBounds(94, 0, 73, 14);
		pnVendedor.add(lblVendedor);
		
		JPanel pnPago = new JPanel();
		pnPago.setLayout(null);
		pnPago.setBackground(new Color(0, 51, 0));
		pnPago.setBounds(707, 11, 198, 72);
		pn1.add(pnPago);
		
		JLabel lblFechaDePago = new JLabel("Fecha de Pago");
		lblFechaDePago.setForeground(Color.WHITE);
		lblFechaDePago.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaDePago.setBackground(Color.BLACK);
		lblFechaDePago.setBounds(57, 0, 106, 14);
		pnPago.add(lblFechaDePago);
		
		dchDesde = new JDateChooser();
		dchDesde.setBounds(49, 18, 144, 20);
		pnPago.add(dchDesde);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setForeground(Color.WHITE);
		lblDesde.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDesde.setBackground(Color.BLACK);
		lblDesde.setBounds(8, 21, 50, 14);
		pnPago.add(lblDesde);
		
		dchHasta = new JDateChooser();
		dchHasta.setBounds(49, 45, 144, 20);
		pnPago.add(dchHasta);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setForeground(Color.WHITE);
		lblHasta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHasta.setBackground(Color.BLACK);
		lblHasta.setBounds(8, 48, 50, 14);
		pnPago.add(lblHasta);
		
		chCxPVencidas = new JCheckBox("CxP Vencidas");
		chCxPVencidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarCxPVencidas();
			}
		});
		chCxPVencidas.setFont(new Font("Tahoma", Font.BOLD, 11));
		chCxPVencidas.setBounds(186, 60, 200, 23);
		pn1.add(chCxPVencidas);
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 116, 1245, 320);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JScrollPane scrCxP = new JScrollPane();
		scrCxP.setBounds(2, 2, 1241, 316);
		pn2.add(scrCxP);
		
		tbCxP = new JTable();
		tbCxP.setEnabled(false);
		tbCxP.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbCxP.rowAtPoint(point);
		        tbCxP.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		        mostrarPopupEnTabla();
			}
		});
		scrCxP.setViewportView(tbCxP);
		
		
		
		JPanel pn3 = new JPanel();
		pn3.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn3.setBounds(10, 447, 1245, 62);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JPanel pnTotalCxP = new JPanel();
		pnTotalCxP.setLayout(null);
		pnTotalCxP.setBackground(new Color(0, 51, 0));
		pnTotalCxP.setBounds(102, 11, 153, 38);
		pn3.add(pnTotalCxP);
		
		JLabel lblTotalCxP = new JLabel("Total CxP");
		lblTotalCxP.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalCxP.setForeground(Color.WHITE);
		lblTotalCxP.setBackground(SystemColor.desktop);
		lblTotalCxP.setBounds(50, 0, 80, 14);
		pnTotalCxP.add(lblTotalCxP);
		
		txtTotalCxP = new JTextField();
		txtTotalCxP.setColumns(10);
		txtTotalCxP.setBounds(0, 18, 153, 20);
		txtTotalCxP.setEditable(false);
		pnTotalCxP.add(txtTotalCxP);
		
		JPanel pnItems = new JPanel();
		pnItems.setLayout(null);
		pnItems.setBackground(new Color(0, 51, 0));
		pnItems.setBounds(752, 11, 60, 38);
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
		btnExportar.setBounds(865, 18, 140, 23);
		pn3.add(btnExportar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setForeground(new Color(0, 51, 0));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalir.setBounds(1034, 18, 104, 23);
		pn3.add(btnSalir);
		
		JPanel pnTotalAbonado = new JPanel();
		pnTotalAbonado.setLayout(null);
		pnTotalAbonado.setBackground(new Color(0, 51, 0));
		pnTotalAbonado.setBounds(263, 11, 153, 38);
		pn3.add(pnTotalAbonado);
		
		JLabel lblTotalAbonado = new JLabel("Total Abonado");
		lblTotalAbonado.setForeground(Color.WHITE);
		lblTotalAbonado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalAbonado.setBackground(SystemColor.desktop);
		lblTotalAbonado.setBounds(35, 0, 90, 14);
		pnTotalAbonado.add(lblTotalAbonado);
		
		txtTotalAbonado = new JTextField();
		txtTotalAbonado.setEditable(false);
		txtTotalAbonado.setColumns(10);
		txtTotalAbonado.setBounds(0, 18, 153, 20);
		pnTotalAbonado.add(txtTotalAbonado);
		
		JPanel pnSaldoActual = new JPanel();
		pnSaldoActual.setLayout(null);
		pnSaldoActual.setBackground(new Color(0, 51, 0));
		pnSaldoActual.setBounds(426, 11, 153, 38);
		pn3.add(pnSaldoActual);
		
		JLabel lblSaldoActual = new JLabel("Saldo Actual");
		lblSaldoActual.setForeground(Color.WHITE);
		lblSaldoActual.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSaldoActual.setBackground(SystemColor.desktop);
		lblSaldoActual.setBounds(40, 0, 90, 14);
		pnSaldoActual.add(lblSaldoActual);
		
		txtSaldoActual = new JTextField();
		txtSaldoActual.setEditable(false);
		txtSaldoActual.setColumns(10);
		txtSaldoActual.setBounds(0, 18, 153, 20);
		pnSaldoActual.add(txtSaldoActual);
		
		JPanel pnCxpCompras = new JPanel();
		pnCxpCompras.setLayout(null);
		pnCxpCompras.setBackground(new Color(0, 51, 0));
		pnCxpCompras.setBounds(589, 11, 153, 38);
		pn3.add(pnCxpCompras);
		
		JLabel lblCxpCompras = new JLabel("CxP Compras");
		lblCxpCompras.setForeground(Color.WHITE);
		lblCxpCompras.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCxpCompras.setBackground(SystemColor.desktop);
		lblCxpCompras.setBounds(43, 0, 90, 14);
		pnCxpCompras.add(lblCxpCompras);
		
		txtCxpCompras = new JTextField();
		txtCxpCompras.setEditable(false);
		txtCxpCompras.setColumns(10);
		txtCxpCompras.setBounds(0, 18, 153, 20);
		pnCxpCompras.add(txtCxpCompras);
		
		//Metodos que debe ejecutar la ventana al inicializarse
		listarProveedores();
		llenarColumnasTbCuentasPorPagar();
		
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
	private void llenarColumnasTbCuentasPorPagar() {
		modeloTbCxP.addColumn("CxP");
		modeloTbCxP.addColumn("Causado");
		modeloTbCxP.addColumn("Generado");
		modeloTbCxP.addColumn("Fecha Pago");
		modeloTbCxP.addColumn("Dias");
		modeloTbCxP.addColumn("Proveedor");
		modeloTbCxP.addColumn("Identificacion");
		modeloTbCxP.addColumn("Concepto");
		modeloTbCxP.addColumn("Doc. Soporte");
		modeloTbCxP.addColumn("Compra");
		modeloTbCxP.addColumn("Total");
		modeloTbCxP.addColumn("Total Abonado");
		modeloTbCxP.addColumn("Saldo");
		modeloTbCxP.addColumn("Abonos");
		
		tbCxP.setModel(modeloTbCxP);
	}
	
	//Metodo para listar las cuentas por pagar de acuerdo a los parametros de fecha pago y proveedor
	private void reportarCuentasXPagar(){
		
		DelegadoCuentaXPagar delegadoCuentaXPagar = new DelegadoCuentaXPagar();
		Persona proveedor =  (Persona) cbProveedor.getItemAt(1); 
		
		if(cbProveedor.getSelectedItem().equals("--TODOS LOS PROVEEDORES--") && txtConcepto.getText().isEmpty() && dchDesde.getDate()!=null && dchHasta.getDate()!=null){
			listaCxP = delegadoCuentaXPagar.reportarCuentaXPagarPorF(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
			limpiarTabla();
			llenarTabla(listaCxP);
		}else{
			if(cbProveedor.getSelectedItem().equals("--TODOS LOS PROVEEDORES--") && txtConcepto.getText()!="" && dchDesde.getDate()!=null && dchHasta.getDate()!=null){
				listaCxP = delegadoCuentaXPagar.reportarCuentaXPagarPorFC(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), txtConcepto.getText());
				limpiarTabla();
				llenarTabla(listaCxP);
			}else{
				if(cbProveedor.getSelectedItem()!=null && txtConcepto.getText().isEmpty() && dchDesde.getDate()!=null && dchHasta.getDate()!=null){
					
					listaCxP = delegadoCuentaXPagar.reportarCuentaXPagarPorFP(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), proveedor.getIdentificacion());
					limpiarTabla();
					llenarTabla(listaCxP);
				}else{
					listaCxP = delegadoCuentaXPagar.reportarCuentaXPagarPorFCP(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), proveedor.getIdentificacion(), txtConcepto.getText());
					limpiarTabla();
					llenarTabla(listaCxP);
				}
			}
		}
		
	}
	
	//Metodo para llenar la tabla cuando recibe como parametro una lista de cuentas por pagar
	private void llenarTabla( List<CuentaXPagar> listaCxP) {
		
		DelegadoProveedor delegadoProveedor = new DelegadoProveedor();
		DelegadoPagoAbonoCxP delegadoPagoAbonoCxP = new DelegadoPagoAbonoCxP();
		double acumulador = 0;
		double totalPagoAbono = 0;

		String [] fila = new String[modeloTbCxP.getColumnCount()];
		
		for(CuentaXPagar cuentasXPagar : listaCxP){
			
			List<PagoAbonoCxP> listaPagoAbono = delegadoPagoAbonoCxP.listarPagoAbonoCxPPorCodigoCxP(cuentasXPagar.getIdCuentaXPagar());
			
			fila[0]= String.valueOf(cuentasXPagar.getIdCuentaXPagar());
			fila[1]= convertirDateAString(cuentasXPagar.getFechaCausacion());
			fila[2]= convertirDateAString(cuentasXPagar.getFechaGeneracion());
			fila[3]= convertirDateAString(cuentasXPagar.getFechaPago());
			fila[4]= String.valueOf(calcularDiasPlazo(cuentasXPagar.getFechaPago(), new Date()));
			fila[5]= delegadoProveedor.traerProveedorPorIdentificacion(cuentasXPagar.getIdentificacionProv()).get(0).getNombre();
			fila[6]= cuentasXPagar.getIdentificacionProv();
			fila[7]= cuentasXPagar.getConcepto();
			fila[8]= String.valueOf(cuentasXPagar.getDocSoporte());
			fila[9]= String.valueOf(cuentasXPagar.getCompra());
			fila[10]= formatearNumero(cuentasXPagar.getTotalPagar());
			for(PagoAbonoCxP pagoAbonos : listaPagoAbono){
				acumulador= pagoAbonos.getPagoAbono();
				totalPagoAbono += acumulador;
				
			}
			fila[11]= formatearNumero(totalPagoAbono);
			fila[12]= formatearNumero(cuentasXPagar.getTotalPagar()- totalPagoAbono);
			fila[13]= String.valueOf(cuentasXPagar.getAbonosCXP().get(0).getIdAbono());
			
			modeloTbCxP.addRow(fila);
		}
		
		tbCxP.setModel(modeloTbCxP);
		
		colorearFechaPagoEnTabla(tbCxP);
		colorearDiasEnTabla(tbCxP);
	}

	//Metodo para calcular el total de cuenta por pagar
	private void calcularTotalCxP(){
		
		double acumulador = 0;
		double totalCxP = 0;
		
		for(int i=0; i<tbCxP.getRowCount(); i++) {
			
			acumulador= Double.parseDouble(desformatearNumero(String.valueOf(tbCxP.getValueAt(i,10))));
			totalCxP += acumulador;
		}
		
		txtTotalCxP.setText(formatearNumero(totalCxP));
	}
	
	//Metodo para calcular el total abonado a las cuentas por pagar
	private void calcularTotalAbonado(){
		double acumulador = 0;
		double totalAbonado = 0;
		
		for(int i=0; i<tbCxP.getRowCount(); i++) {
			
			acumulador= Double.parseDouble(desformatearNumero(String.valueOf(tbCxP.getValueAt(i,11))));
			totalAbonado += acumulador;
		}
		
		txtTotalAbonado.setText(formatearNumero(totalAbonado));
	}
	
	//Metodo para calcular el saldo actual
	private void calcularSaldoActual(){
		double acumulador = 0;
		double totalSaldoActual = 0;
		
		for(int i=0; i<tbCxP.getRowCount(); i++) {
			
			acumulador= Double.parseDouble(desformatearNumero(tbCxP.getValueAt(i,12).toString()));
			totalSaldoActual += acumulador;
		}
		
		txtSaldoActual.setText(formatearNumero(totalSaldoActual));
	}
	
	//Metodo para calcular el total de cuentas por pagar por compras
	private void calcularTotalCxPCompras(){
		double acumulador = 0;
		double totalCxPCompras = 0;
		
		for(int i=0; i<tbCxP.getRowCount(); i++) {
			if("Compra".equals(tbCxP.getValueAt(i,10).toString())){
				acumulador= Double.parseDouble(desformatearNumero(tbCxP.getValueAt(i,10).toString()));
				totalCxPCompras += acumulador;
			}
		}
		
		txtCxpCompras.setText(formatearNumero(totalCxPCompras));
	}
	
	//Metodo que permite calcular la cantidad de cuentas por pagar
	private void calcularCantidadCxP() {
		
		int cantidad = tbCxP.getRowCount();
		txtItems.setText(Integer.toString(cantidad));
		
	}
	
	
	//Metodo para mostrar un pequeño menu a cada celda de la tabla
	private void mostrarPopupEnTabla(){
		JPopupMenu pmArticulos = new JPopupMenu();
		JMenuItem miVerCxP = new JMenuItem("Ver CxP");
		JMenuItem miVerCompra= new JMenuItem("Ver Compra");
		
		if("0".equals(tbCxP.getValueAt(filaSeleccionada, 9).toString())){

			pmArticulos.add(miVerCxP);
			miVerCxP.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verCxP();
				}

				
			});
		}else{
			pmArticulos.add(miVerCxP);
			miVerCxP.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verCxP();
				}

				
			});
			
			pmArticulos.add(miVerCompra);
			miVerCompra.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verCompra();
				}
			});
		}

		tbCxP.setComponentPopupMenu(pmArticulos);
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
       for (int i = 0; i < tbCxP.getRowCount(); i++) {
    	   modeloTbCxP.removeRow(i);
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
	private void colorearFechaPagoEnTabla(JTable tabla) {
		tabla.getColumn("Fecha Pago").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			comp.setBackground(new Color(255, 102, 102));
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
	
	//Metodo para visualizar la cuenta por pagar mediante un reporte
	private void verCxP() {

		DelegadoProveedor delegadoProveedor = new DelegadoProveedor();
		DelegadoPagoAbonoCxP delegadoPagoAbonoCxP = new DelegadoPagoAbonoCxP();
		
		 URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteCuentaXPagar.jasper");
		 
		 CompraArticulos compra= delegadoCompraArticulos.traerCompraPorCodigo(listaCxP.get(filaSeleccionada).getCompra());
		 CuentaXPagar cuentaxPagar = listaCxP.get(filaSeleccionada);
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
	
	//Metodo para visualizar mediante un reporte la compra
	private void verCompra() {
		DelegadoDetalleCompra delegadoDetalleCompra = new DelegadoDetalleCompra();
		 
		 URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteFacturaCompra.jasper");
		 List<ReporteDetalle> lista = new ArrayList<ReporteDetalle>();
		 
		 CompraArticulos compra= delegadoCompraArticulos.traerCompraPorCodigo(listaCxP.get(filaSeleccionada).getCompra());
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
	
	//Metodo para mostrar toda las cuentas por pagar vencidas al activarse el checkbox
	private void mostrarCxPVencidas() {
		
		DelegadoCuentaXPagar delegadoCuentaXPagar = new DelegadoCuentaXPagar();
		List<CuentaXPagar> cuentasXPagarVencidas = delegadoCuentaXPagar.reportarTodasCxPVencidas(new Date());

		if(chCxPVencidas.isSelected()){
			limpiarTabla();
			llenarTabla(cuentasXPagarVencidas);
			
			calcularTotalCxP();
			calcularTotalAbonado();
			calcularSaldoActual();
			calcularTotalCxPCompras();
			calcularCantidadCxP();
		}else{
			reportarCuentasXPagar();
			calcularTotalCxP();
			calcularTotalAbonado();
			calcularSaldoActual();
			calcularTotalCxPCompras();
			calcularCantidadCxP();
		}
	}
	
	//Metodo para exportar la tabla en excel
	private void exportarExcel(){
		VentExportarExcel ventExportarExcel = new VentExportarExcel(tbCxP, getTitle());
		ventExportarExcel.setVisible(true);
	}
}
