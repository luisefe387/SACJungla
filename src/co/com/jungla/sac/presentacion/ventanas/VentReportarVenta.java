package co.com.jungla.sac.presentacion.ventanas;

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

import co.com.jungla.sac.persistencia.entidades.Cliente;
import co.com.jungla.sac.persistencia.entidades.DetalleDevolucionCliente;
import co.com.jungla.sac.persistencia.entidades.Persona;
import co.com.jungla.sac.persistencia.entidades.Vendedor;
import co.com.jungla.sac.persistencia.entidades.VentaArticulos;

import co.com.jungla.sac.negocio.delegados.DelegadoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleDevolucionCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoVendedor;
import co.com.jungla.sac.negocio.delegados.DelegadoVentaArticulos;
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

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el reporte de las ventas de articulos
 * @author Luis Fernando Pedroza T.
 * @version: 19/09/2016
 */
public class VentReportarVenta extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtVentaTotal;
	private JTextField txtItems;
	private JTextField txtDctos;
	private JTextField txtVentaNeta;
	private JTextField txtCostoVentas;
	private JTextField txtUtilidadBruta;
	private JTextField txtRecaudado;
	private JTextField txtCartMorosa;
	private JTextField txtTotalCartera;
	private JTextField txtDevoluciones;
	private JComboBox cbCliente;
	private JComboBox cbVendedor;
	private JComboBox cbTipo;
	private JDateChooser dchDesde;
	private JDateChooser dchHasta;
	private JTable tbVentas;
	private DefaultComboBoxModel modeloVendedor = new DefaultComboBoxModel();
	private DefaultComboBoxModel modeloCliente = new DefaultComboBoxModel();
	private DefaultTableModel modeloTbVenta = new DefaultTableModel();
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private List<VentaArticulos> listaVentaArticulos;
	private int filaSeleccionada;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentReportarVenta() {
		setTitle("Reporte de Ventas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentReportarVenta.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 1279, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 1245, 94);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JPanel pnClienteTipo = new JPanel();
		pnClienteTipo.setBounds(394, 11, 254, 38);
		pn1.add(pnClienteTipo);
		pnClienteTipo.setBackground(new Color(0, 51, 0));
		pnClienteTipo.setLayout(null);
		
		//Funcionalidad para crear el cliente al desplegar el combo box
		cbCliente = new JComboBox();
		cbCliente.setBounds(0, 16, 254, 22);
		pnClienteTipo.add(cbCliente);
		
		JLabel lblClienteTipo = new JLabel("Cliente / Tipo");
		lblClienteTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblClienteTipo.setBackground(SystemColor.desktop);
		lblClienteTipo.setForeground(SystemColor.window);
		lblClienteTipo.setBounds(85, 0, 108, 14);
		pnClienteTipo.add(lblClienteTipo);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				reportarVenta();
				txtVentaTotal.setText(formatearNumero(calcularTotalVenta()));
				txtDctos.setText(formatearNumero(calcularDctosCom()));
				txtDevoluciones.setText(formatearNumero(calcularTotalDevoluciones()));
				txtVentaNeta.setText(formatearNumero(calcularTotalVentaNeta()));
				txtCostoVentas.setText(formatearNumero(calcularTotalCostoVenta()));
				txtUtilidadBruta.setText(formatearNumero(calcularTotalUtilidadBruta()));
				txtRecaudado.setText(formatearNumero(calcularTotalRecaudado()));
				txtCartMorosa.setText(formatearNumero(calcularTotalCarteraMorosa()));
				txtTotalCartera.setText(formatearNumero(calcularTotalCartera()));
				calcularCantidadVentas();
			}
		});
		btnMostrar.setForeground(new Color(0, 51, 0));
		btnMostrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMostrar.setBounds(997, 20, 117, 23);
		pn1.add(btnMostrar);
		
		JPanel pnRangoDias = new JPanel();
		pnRangoDias.setLayout(null);
		pnRangoDias.setBackground(new Color(0, 51, 0));
		pnRangoDias.setBounds(768, 11, 198, 72);
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
		pnVendedor.setBounds(137, 11, 247, 38);
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
		
		JPanel pnTipo = new JPanel();
		pnTipo.setLayout(null);
		pnTipo.setBackground(new Color(0, 51, 0));
		pnTipo.setBounds(658, 11, 100, 38);
		pn1.add(pnTipo);
		
		cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"Todas", "Contado", "Cr\u00E9dito"}));
		cbTipo.setBounds(0, 16, 100, 22);
		pnTipo.add(cbTipo);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipo.setBackground(Color.BLACK);
		lblTipo.setBounds(37, 0, 47, 14);
		pnTipo.add(lblTipo);
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 116, 1245, 320);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JScrollPane scrVentas = new JScrollPane();
		scrVentas.setBounds(2, 2, 1241, 316);
		pn2.add(scrVentas);
		
		tbVentas = new JTable();
		tbVentas.setEnabled(false);
		tbVentas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbVentas.rowAtPoint(point);
		        tbVentas.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		        mostrarPopupEnTabla();
			}
		});
		scrVentas.setViewportView(tbVentas);
		
		
		
		JPanel pn3 = new JPanel();
		pn3.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn3.setBounds(10, 447, 1245, 73);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JPanel pnVentaTotal = new JPanel();
		pnVentaTotal.setLayout(null);
		pnVentaTotal.setBackground(new Color(0, 51, 0));
		pnVentaTotal.setBounds(10, 18, 104, 38);
		pn3.add(pnVentaTotal);
		
		JLabel lblVentaTotal = new JLabel("VENTA TOTAL");
		lblVentaTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVentaTotal.setForeground(Color.WHITE);
		lblVentaTotal.setBackground(SystemColor.desktop);
		lblVentaTotal.setBounds(15, 0, 90, 14);
		pnVentaTotal.add(lblVentaTotal);
		
		txtVentaTotal = new JTextField();
		txtVentaTotal.setColumns(10);
		txtVentaTotal.setBounds(0, 18, 104, 20);
		txtVentaTotal.setEditable(false);
		pnVentaTotal.add(txtVentaTotal);
		
		JPanel pnItems = new JPanel();
		pnItems.setLayout(null);
		pnItems.setBackground(new Color(0, 51, 0));
		pnItems.setBounds(1025, 18, 60, 38);
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
		btnExportar.setBounds(1095, 9, 140, 23);
		pn3.add(btnExportar);
		
		JButton btnImprimir = new JButton("Salir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnImprimir.setForeground(new Color(0, 51, 0));
		btnImprimir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnImprimir.setBounds(1120, 40, 104, 23);
		pn3.add(btnImprimir);
		
		JPanel pnDctos = new JPanel();
		pnDctos.setLayout(null);
		pnDctos.setBackground(new Color(0, 51, 0));
		pnDctos.setBounds(124, 18, 93, 38);
		pn3.add(pnDctos);
		
		JLabel lblDctos = new JLabel("DCTOs COM");
		lblDctos.setForeground(Color.WHITE);
		lblDctos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDctos.setBackground(Color.BLACK);
		lblDctos.setBounds(15, 0, 79, 14);
		pnDctos.add(lblDctos);
		
		txtDctos = new JTextField();
		txtDctos.setEditable(false);
		txtDctos.setColumns(10);
		txtDctos.setBounds(0, 18, 93, 20);
		pnDctos.add(txtDctos);
		
		JPanel pnVentaNeta = new JPanel();
		pnVentaNeta.setLayout(null);
		pnVentaNeta.setBackground(new Color(0, 51, 0));
		pnVentaNeta.setBounds(341, 18, 104, 38);
		pn3.add(pnVentaNeta);
		
		JLabel lblVentaNeta = new JLabel("VENTA NETA");
		lblVentaNeta.setForeground(Color.WHITE);
		lblVentaNeta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVentaNeta.setBackground(Color.BLACK);
		lblVentaNeta.setBounds(20, 0, 79, 14);
		pnVentaNeta.add(lblVentaNeta);
		
		txtVentaNeta = new JTextField();
		txtVentaNeta.setEditable(false);
		txtVentaNeta.setColumns(10);
		txtVentaNeta.setBounds(0, 18, 104, 20);
		pnVentaNeta.add(txtVentaNeta);
		
		JPanel pnCostoVentas = new JPanel();
		pnCostoVentas.setLayout(null);
		pnCostoVentas.setBackground(new Color(0, 51, 0));
		pnCostoVentas.setBounds(455, 18, 104, 38);
		pn3.add(pnCostoVentas);
		
		JLabel lblCostoVentas = new JLabel("Costo Ventas");
		lblCostoVentas.setForeground(Color.WHITE);
		lblCostoVentas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCostoVentas.setBackground(Color.BLACK);
		lblCostoVentas.setBounds(15, 0, 84, 14);
		pnCostoVentas.add(lblCostoVentas);
		
		txtCostoVentas = new JTextField();
		txtCostoVentas.setEditable(false);
		txtCostoVentas.setColumns(10);
		txtCostoVentas.setBounds(0, 18, 104, 20);
		pnCostoVentas.add(txtCostoVentas);
		
		JPanel pnUtilidadBruta = new JPanel();
		pnUtilidadBruta.setLayout(null);
		pnUtilidadBruta.setBackground(new Color(0, 51, 0));
		pnUtilidadBruta.setBounds(569, 18, 104, 38);
		pn3.add(pnUtilidadBruta);
		
		JLabel lblUtilidadBruta = new JLabel("Utilidad Bruta");
		lblUtilidadBruta.setForeground(Color.WHITE);
		lblUtilidadBruta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUtilidadBruta.setBackground(Color.BLACK);
		lblUtilidadBruta.setBounds(13, 0, 88, 14);
		pnUtilidadBruta.add(lblUtilidadBruta);
		
		txtUtilidadBruta = new JTextField();
		txtUtilidadBruta.setEditable(false);
		txtUtilidadBruta.setColumns(10);
		txtUtilidadBruta.setBounds(0, 18, 104, 20);
		pnUtilidadBruta.add(txtUtilidadBruta);
		
		JPanel pnRecaudado = new JPanel();
		pnRecaudado.setLayout(null);
		pnRecaudado.setBackground(new Color(0, 51, 0));
		pnRecaudado.setBounds(683, 18, 104, 38);
		pn3.add(pnRecaudado);
		
		JLabel lblRecaudado = new JLabel("Recaudado");
		lblRecaudado.setForeground(Color.WHITE);
		lblRecaudado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRecaudado.setBackground(Color.BLACK);
		lblRecaudado.setBounds(20, 0, 79, 14);
		pnRecaudado.add(lblRecaudado);
		
		txtRecaudado = new JTextField();
		txtRecaudado.setEditable(false);
		txtRecaudado.setColumns(10);
		txtRecaudado.setBounds(0, 18, 104, 20);
		pnRecaudado.add(txtRecaudado);
		
		JPanel pnCartMorosa = new JPanel();
		pnCartMorosa.setLayout(null);
		pnCartMorosa.setBackground(new Color(0, 51, 0));
		pnCartMorosa.setBounds(797, 18, 104, 38);
		pn3.add(pnCartMorosa);
		
		JLabel lblCartMorosa = new JLabel("Cart. Morosa");
		lblCartMorosa.setForeground(Color.WHITE);
		lblCartMorosa.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCartMorosa.setBackground(Color.BLACK);
		lblCartMorosa.setBounds(16, 0, 88, 14);
		pnCartMorosa.add(lblCartMorosa);
		
		txtCartMorosa = new JTextField();
		txtCartMorosa.setEditable(false);
		txtCartMorosa.setColumns(10);
		txtCartMorosa.setBounds(0, 18, 104, 20);
		pnCartMorosa.add(txtCartMorosa);
		
		JPanel pnTotalCartera = new JPanel();
		pnTotalCartera.setLayout(null);
		pnTotalCartera.setBackground(new Color(0, 51, 0));
		pnTotalCartera.setBounds(911, 18, 104, 38);
		pn3.add(pnTotalCartera);
		
		JLabel lblTotalCartera = new JLabel("Total Cartera");
		lblTotalCartera.setForeground(Color.WHITE);
		lblTotalCartera.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalCartera.setBackground(Color.BLACK);
		lblTotalCartera.setBounds(14, 0, 88, 14);
		pnTotalCartera.add(lblTotalCartera);
		
		txtTotalCartera = new JTextField();
		txtTotalCartera.setEditable(false);
		txtTotalCartera.setColumns(10);
		txtTotalCartera.setBounds(0, 18, 104, 20);
		pnTotalCartera.add(txtTotalCartera);
		
		JPanel pnDevoluciones = new JPanel();
		pnDevoluciones.setLayout(null);
		pnDevoluciones.setBackground(new Color(0, 51, 0));
		pnDevoluciones.setBounds(227, 18, 104, 38);
		pn3.add(pnDevoluciones);
		
		JLabel lblDevoluciones = new JLabel("Devoluciones");
		lblDevoluciones.setForeground(Color.WHITE);
		lblDevoluciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDevoluciones.setBackground(Color.BLACK);
		lblDevoluciones.setBounds(15, 0, 89, 14);
		pnDevoluciones.add(lblDevoluciones);
		
		txtDevoluciones = new JTextField();
		txtDevoluciones.setEditable(false);
		txtDevoluciones.setColumns(10);
		txtDevoluciones.setBounds(0, 18, 104, 20);
		pnDevoluciones.add(txtDevoluciones);
		
		//Metodos que debe ejecutar la ventana al inicializarse
		listarVendedores();
		listarClientes();
		
	}
	
	//Metodo para listar los vendedores y desplegarlos en un combo box
	private void listarVendedores() {
		DelegadoVendedor delegadoVendedor = new DelegadoVendedor();
		List<Vendedor> vendedores = delegadoVendedor.listarVendedor();
		modeloVendedor.addElement("--TODOS LOS VENDEDORES--");
		cbVendedor.setModel(modeloVendedor);
		
		for(Vendedor vendedor : vendedores){
			modeloVendedor.addElement(new Persona (vendedor.getNombre(), vendedor.getIdentificacion()));
			cbVendedor.setModel(modeloVendedor);
		}
	}
	
	//Metodo para listar los clientes y desplegarlos en un combo box
	private void listarClientes() {
		DelegadoCliente delegadoCliente = new DelegadoCliente();
		List<Cliente> clientes = delegadoCliente.listarCliente();
		modeloCliente.addElement("--TODOS LOS CLIENTES--");
		cbCliente.setModel(modeloCliente);
		
		for(Cliente cliente : clientes){
			modeloCliente.addElement(new Persona (cliente.getNombre(), cliente.getIdentificacion()));
			cbCliente.setModel(modeloCliente);
		}
	}
	
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbVentas() {
		modeloTbVenta.addColumn("Consecutivo");
		modeloTbVenta.addColumn("Cliente");
		modeloTbVenta.addColumn("Generado");
		modeloTbVenta.addColumn("Fecha Limite");
		modeloTbVenta.addColumn("Dias");
		modeloTbVenta.addColumn("Valor");
		modeloTbVenta.addColumn("Dctos com");
		modeloTbVenta.addColumn("Vlr Neto");
		modeloTbVenta.addColumn("Saldo");
		modeloTbVenta.addColumn("Costo");
		modeloTbVenta.addColumn("Recibos");
		modeloTbVenta.addColumn("Forma Pago");
		modeloTbVenta.addColumn("Vendedor");
		
		tbVentas.setModel(modeloTbVenta);
	}
	
	//Metodo para listar las ventas de acuerdo a los parametros de busqueda
	private void reportarVenta(){
		
		DelegadoVentaArticulos delegadoVentaArticulos = new DelegadoVentaArticulos();
		
		if(cbVendedor.getSelectedItem().equals("--TODOS LOS VENDEDORES--") && cbCliente.getSelectedItem().equals("--TODOS LOS CLIENTES--") && cbTipo.getSelectedItem().equals("Todas") && dchDesde.getDate()!=null && dchHasta.getDate()!=null){
			listaVentaArticulos = delegadoVentaArticulos.reportarVentasPorF(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
			limpiarTabla();
			llenarTabla(listaVentaArticulos);
		}else{
			if(cbVendedor.getSelectedItem().equals("--TODOS LOS VENDEDORES--") && cbCliente.getSelectedItem().equals("--TODOS LOS CLIENTES--") && cbTipo.getSelectedItem()!="" && dchDesde.getDate()!=null && dchHasta.getDate()!=null){
				listaVentaArticulos = delegadoVentaArticulos.reportarVentasPorFF(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), cbTipo.getSelectedItem().toString());
				limpiarTabla();
				llenarTabla(listaVentaArticulos);
			}else{
				if(cbVendedor.getSelectedItem().equals("--TODOS LOS VENDEDORES--") && cbCliente.getSelectedItem()!="" && cbTipo.getSelectedItem().equals("Todas") && dchDesde.getDate()!=null && dchHasta.getDate()!=null){
					listaVentaArticulos = delegadoVentaArticulos.reportarVentasPorCF(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), cbCliente.getSelectedItem().toString());
					limpiarTabla();
					llenarTabla(listaVentaArticulos);
				}else{
					if(cbVendedor.getSelectedItem()!="" && cbCliente.getSelectedItem().equals("--TODOS LOS CLIENTES--") && cbTipo.getSelectedItem().equals("Todas") && dchDesde.getDate()!=null && dchHasta.getDate()!=null){
						listaVentaArticulos = delegadoVentaArticulos.reportarVentasPorVF(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), cbVendedor.getSelectedItem().toString());
						limpiarTabla();
						llenarTabla(listaVentaArticulos);
					}else{
						if(cbVendedor.getSelectedItem()!="" && cbCliente.getSelectedItem()!="" && cbTipo.getSelectedItem().equals("Todas") && dchDesde.getDate()!=null && dchHasta.getDate()!=null){
							listaVentaArticulos = delegadoVentaArticulos.reportarVentasPorVCF(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), cbCliente.getSelectedItem().toString(), cbVendedor.getSelectedItem().toString());
							limpiarTabla();
							llenarTabla(listaVentaArticulos);
						}else{
							if(cbVendedor.getSelectedItem()!="" && cbCliente.getSelectedItem().equals("--TODOS LOS CLIENTES--") && cbTipo.getSelectedItem()!="" && dchDesde.getDate()!=null && dchHasta.getDate()!=null){
								listaVentaArticulos = delegadoVentaArticulos.reportarVentasPorVFF(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), cbVendedor.getSelectedItem().toString(), cbTipo.getSelectedItem().toString());
								limpiarTabla();
								llenarTabla(listaVentaArticulos);
							}else{
								listaVentaArticulos = delegadoVentaArticulos.reportarVentasPorVCFF(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()), cbCliente.getSelectedItem().toString(), cbVendedor.getSelectedItem().toString(), cbTipo.getSelectedItem().toString());
								limpiarTabla();
								llenarTabla(listaVentaArticulos);
							}
						}
					}
				}
			}
		}
		
	}
	
	//Metodo para llenar la tabla cuando recibe como parametro una lista de ventas
	private void llenarTabla( List<VentaArticulos> listaVentaArticulos) {
		
		if(tbVentas.getModel().getColumnCount()==0){
			llenarColumnasTbVentas();
		}
		
		String [] fila = new String[modeloTbVenta.getColumnCount()];
		
		for(VentaArticulos ventas : listaVentaArticulos){
			
			fila[0]= String.valueOf(ventas.getIdVenta());
			fila[1]= ventas.getClientes().getNombre().toString();
			fila[2]= convertirDateAString(ventas.getFechaGeneracion());
			fila[3]= convertirDateAString(ventas.getFechaLimitePago());
			if(calcularSaldo()==0){
				fila[4]= String.valueOf(0);
			}else{
				fila[4]= String.valueOf(calcularDiasPlazo(ventas.getFechaLimitePago(), new Date()));
			}
			fila[5]= formatearNumero(ventas.getSubtotal());
			fila[6]= String.valueOf(ventas.getDescuento())+"%";
			fila[7]= formatearNumero(ventas.getTotalVenta());
			fila[8]= String.valueOf(calcularSaldo());
			fila[9]= formatearNumero(ventas.getCostoVenta());
			fila[10]= String.valueOf(ventas.getReciboCaja());
			fila[11]= ventas.getFormaPagoCliente().getDescripcion();	
			fila[12]= ventas.getVendedores().getNombre();
			
			modeloTbVenta.addRow(fila);
		}
		
		tbVentas.setModel(modeloTbVenta);
		
		colorearFechaPagoVencidaEnTabla(tbVentas);
		colorearDiasPlazoEnTabla(tbVentas);
		
	}
	//Metodo que permite calcular el saldo de cada venta
	private double calcularSaldo(){
		double saldo;
		saldo = 0;
		return saldo;
	}
	
	//Metodo para calcular el total de la venta
	private double calcularTotalVenta(){
		
		double totalVenta = 0;
		double acumulador = 0;
		
		for(int i=0; i<tbVentas.getRowCount(); i++) {
			acumulador= Double.parseDouble(desformatearNumero(String.valueOf(tbVentas.getValueAt(i,5))));
			totalVenta += acumulador;
		}
		
		return totalVenta;
	}
	
	//Metodo para calcular el total de los descuentos comerciales
	private double calcularDctosCom(){
		
		double dctoCom = 0;
		double acumulador = 0;
		
		for(int i=0; i<tbVentas.getRowCount(); i++) {
			acumulador= Double.parseDouble(desformatearNumero(String.valueOf(tbVentas.getValueAt(i,5))))*(Integer.parseInt(quitarPorcentaje(String.valueOf(tbVentas.getValueAt(i,6))))/100);
			dctoCom += acumulador;
		}
		return dctoCom;
	}
	//Metodo para calcular el total de devoluciones
	private double calcularTotalDevoluciones(){
		DelegadoDetalleDevolucionCliente delegadoDevolucionCliente = new DelegadoDetalleDevolucionCliente();
		double devoluciones = 0;
		double acumulador = 0;
		DetalleDevolucionCliente devolucion; 
		
		for(int i=0; i<tbVentas.getRowCount(); i++) {
			devolucion = delegadoDevolucionCliente.traerDetalleDevolucionClientePoridVenta(Integer.parseInt(tbVentas.getValueAt(i,0).toString())).get(i);
			acumulador= (devolucion.getVlrUnitario()*devolucion.getCantidadDevuelta());	
			devoluciones += acumulador;
		}
		
		return devoluciones;
	}
	
	//Metodo para calcular el total de la venta neta
	private double calcularTotalVentaNeta(){
		
		double totalVentaNeta = calcularTotalVenta() - calcularDctosCom() - calcularTotalDevoluciones(); 
		return totalVentaNeta;
		
	}
	
	//Metodo para calcular el total del costo de la venta
	private double calcularTotalCostoVenta(){
		
		double totalCostoVenta = 0;
		double acumulador = 0;
		
		for(int i=0; i<tbVentas.getRowCount(); i++) {
			acumulador= Double.parseDouble(desformatearNumero(String.valueOf(tbVentas.getValueAt(i,9))));
			totalCostoVenta += acumulador;
		}
		return totalCostoVenta;
	}
	
	//Metodo para calcular el total de la utilidad bruta
	private double calcularTotalUtilidadBruta(){
		
		double totalUtilidadBruta = calcularTotalVentaNeta() - calcularTotalCostoVenta();
		return totalUtilidadBruta;
	}
	
	//Metodo para calcular el total recaudado
	private double calcularTotalRecaudado(){
		
		double totalRecaudado = 0;
		double acumulador = 0;
		
		for(int i=0; i<tbVentas.getRowCount(); i++) {
			if(Double.parseDouble(desformatearNumero(String.valueOf(tbVentas.getValueAt(i,8))))==0){
				acumulador= Double.parseDouble(desformatearNumero(String.valueOf(tbVentas.getValueAt(i,7))));
				totalRecaudado += acumulador;
			}
		}
		
		return totalRecaudado;
	}
	
	//Metodo para calcular el total de cartera morosa
	private double calcularTotalCarteraMorosa(){
		
		double totalCarteraMorosa = 0;
		double acumulador = 0;
		
		for(int i=0; i<tbVentas.getRowCount(); i++) {
			if(Integer.parseInt(String.valueOf(tbVentas.getValueAt(i,4)))<0){
				acumulador= Double.parseDouble(desformatearNumero(String.valueOf(tbVentas.getValueAt(i,7))));
				totalCarteraMorosa += acumulador;
			}
		}
		
		return totalCarteraMorosa;
	}
	
	//Metodo para calcular el total de cartera
	private double calcularTotalCartera(){
		
		double totalCartera = 0;
		double acumulador = 0;
		
		for(int i=0; i<tbVentas.getRowCount(); i++) {
			if(Double.parseDouble(desformatearNumero(String.valueOf(tbVentas.getValueAt(i,8))))!=0){
				acumulador= Double.parseDouble(desformatearNumero(String.valueOf(tbVentas.getValueAt(i,7))));
				totalCartera += acumulador;
			}
		}
		
		return totalCartera;
	}
		
	//Metodo que permite calcular la cantidad de ventas encontradas
	private void calcularCantidadVentas() {
		
		int cantidad = tbVentas.getRowCount();
		txtItems.setText(Integer.toString(cantidad));
		
	}
	//Metodo para mostrar popup en la tabla
	private void mostrarPopupEnTabla(){
		JPopupMenu pmVentas = new JPopupMenu();
		JMenuItem miVerVenta = new JMenuItem("Ver Detalle Venta");
		JMenuItem miModificarFechaLimite = new JMenuItem("Modificar Fecha Limite");
		JMenuItem miVerReciboCaja = new JMenuItem("Ver Recibo Caja");
		
		VentaArticulos ventaElegido= listaVentaArticulos.get(filaSeleccionada);
		if(ventaElegido.getFormaPagoCliente().getDescripcion().equals("CONTADO")){
			pmVentas.add(miVerVenta);
		}else{
			pmVentas.add(miVerVenta);
			pmVentas.add(miModificarFechaLimite);
		}
		
		if(ventaElegido.getReciboCaja().get(0).getIdReciboCaja()!=0){
			pmVentas.add(miVerReciboCaja);
		}
			
		tbVentas.setComponentPopupMenu(pmVentas);	
		
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
	
	//Metodo para convertir un numero en formato de pesos y decimales en un numero corriente
	private String quitarPorcentaje(String numero){
		String numeroReemplazado=numero.replace("%", "");
		return numeroReemplazado;
	}
	//Metodo que permite la conversion de un dato de tipo date a uno de tipo string
	public String convertirDateAString(Date strFecha){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(strFecha);
		
		return fecha;
	}
	//Metodo para limpiar la tabla 
	private void limpiarTabla(){
	       for (int i = 0; i < tbVentas.getRowCount(); i++) {
	    	   modeloTbVenta.removeRow(i);
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
	//Metodo para colorear la columna de fecha pago vencida basandose en los dias vencidos
	private void colorearFechaPagoVencidaEnTabla(JTable tablaVentas) {
		tablaVentas.getColumn("Fecha Limite").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			if(Integer.parseInt(table.getValueAt(row, 4).toString())==0){
				comp.setBackground(Color.WHITE);
			}else{
				comp.setBackground(new Color(204, 102, 102));
			}
	        return comp; 
			}}); 
	}
	
	//Metodo para colorear la columna de dias plazo basandose en los dias vencidos
	private void colorearDiasPlazoEnTabla(JTable tablaVentas) {
		tablaVentas.getColumn("Dias").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			if(Integer.parseInt(table.getValueAt(row, 4).toString())==0){
				comp.setBackground(Color.WHITE);
			}else{
				comp.setBackground(new Color(204, 102, 102));
			}
	        return comp; 
			}}); 
	}
	
	//Metodo para exportar la tabla en excel
	private void exportarExcel(){
		VentExportarExcel ventExportarExcel = new VentExportarExcel(tbVentas, getTitle());
		ventExportarExcel.setVisible(true);
	}
}
