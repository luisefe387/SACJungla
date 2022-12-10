package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Component;
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

import co.com.jungla.sac.persistencia.entidades.Articulo;
import co.com.jungla.sac.persistencia.entidades.KardexElectronico;
import co.com.jungla.sac.persistencia.entidades.LineaArticulos;

import co.com.jungla.sac.negocio.delegados.DelegadoArticulo;
import co.com.jungla.sac.negocio.delegados.DelegadoKardexElectronico;
import co.com.jungla.sac.negocio.delegados.DelegadoLineaArticulos;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import java.awt.Font;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo la consulta del kardex electronico para articulos y mostrar la informacion
 * @author Luis Fernando Pedroza T.
 * @version: 21/09/2016
 */
public class VentMostrarKardexElectronicoArticulos extends JFrame {
	
	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtVentas;
	private JTextField txtSalidas;
	private JTextField txtCompras;
	private JTextField txtEntradas;
	private JTextField txtDevolucionProveed;
	private JTextField txtBusquedaRapida;
	private JTextField txtDevolucionCliente;
	private JComboBox cbMovimiento;
	private JComboBox cbLineaArticulos;
	private JComboBox cbArticulos;
	private JTable tbArticulos;
	private DefaultComboBoxModel modeloLineaArticulos = new DefaultComboBoxModel();
	private DefaultComboBoxModel modeloArticulos = new DefaultComboBoxModel();
	private DefaultTableModel modeloTbArticulos = new DefaultTableModel();
	private JDateChooser dchDesde;
	private JDateChooser dchHasta;
	private JCheckBox chMostrarProveedor;
	private JCheckBox chMostrarGrupo;
	private JCheckBox chMostrarCodigo;
	private JCheckBox chMostrarReferencia;
	private JCheckBox chMostrarCostoInv;
	private JCheckBox chMostrarDescripcion;

	/*Atributos para ejecutar las funcionalidades de la ventana*/
	/**
	 * el atributo movimiento, se refiere al nombre del movimiento como una venta,compra,devolucion, entrada o salida
	 */
	private String movimiento;
	private int filaSeleccionada;
	private List<KardexElectronico> listaRegistrosKardex;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentMostrarKardexElectronicoArticulos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentMostrarKardexElectronicoArticulos.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Mostrar Kardex Electr\u00F3nico de Articulos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1222, 563);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(13, 11, 1184, 139);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JPanel pnMovimiento = new JPanel();
		pnMovimiento.setBounds(10, 11, 315, 38);
		pn1.add(pnMovimiento);
		pnMovimiento.setBackground(new Color(0, 51, 0));
		pnMovimiento.setLayout(null);
		
		//Funcionalidad para crear el proveedor al desplegar el combo box
		cbMovimiento = new JComboBox();
		cbMovimiento.setModel(new DefaultComboBoxModel(new String[] {"--TODOS--", "Venta", "Compra", "Salida", "Entrada", "Devolucion Cliente", "Devolucion Proveedor", "Venta Anulada", "Compra Anulada"}));
		cbMovimiento.setBounds(0, 16, 315, 22);
		pnMovimiento.add(cbMovimiento);
		
		JLabel lblMovimiento = new JLabel("Movimiento");
		lblMovimiento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMovimiento.setBackground(SystemColor.desktop);
		lblMovimiento.setForeground(SystemColor.window);
		lblMovimiento.setBounds(110, 0, 91, 14);
		pnMovimiento.add(lblMovimiento);
		
		JPanel pnGrupoArticulo = new JPanel();
		pnGrupoArticulo.setLayout(null);
		pnGrupoArticulo.setBackground(new Color(0, 51, 0));
		pnGrupoArticulo.setBounds(338, 11, 289, 38);
		pn1.add(pnGrupoArticulo);
		
		cbLineaArticulos = new JComboBox();
		cbLineaArticulos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cbArticulos.removeAllItems();
				listarArticulos(cbLineaArticulos.getSelectedItem().toString());
			}
		});
		cbLineaArticulos.setBounds(0, 16, 289, 22);
		pnGrupoArticulo.add(cbLineaArticulos);
		
		JLabel lblGrupoArticulo = new JLabel("Linea de Articulo");
		lblGrupoArticulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGrupoArticulo.setForeground(Color.WHITE);
		lblGrupoArticulo.setBackground(SystemColor.desktop);
		lblGrupoArticulo.setBounds(96, 0, 124, 14);
		pnGrupoArticulo.add(lblGrupoArticulo);
		
		JPanel pnArticulos = new JPanel();
		pnArticulos.setLayout(null);
		pnArticulos.setBackground(new Color(0, 51, 0));
		pnArticulos.setBounds(637, 11, 325, 38);
		pn1.add(pnArticulos);
		
		cbArticulos = new JComboBox();
		cbArticulos.setModel(new DefaultComboBoxModel(new String[] {"--TODOS--"}));
		cbArticulos.setBounds(0, 16, 325, 22);
		pnArticulos.add(cbArticulos);
		
		JLabel lbArticulos = new JLabel("Articulo");
		lbArticulos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbArticulos.setForeground(Color.WHITE);
		lbArticulos.setBackground(SystemColor.desktop);
		lbArticulos.setBounds(129, 0, 87, 14);
		pnArticulos.add(lbArticulos);
		
		JButton btnMostrar = new JButton("Mostrar ");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarRangoFechas();
			}
		});
		btnMostrar.setForeground(new Color(0, 51, 0));
		btnMostrar.setBackground(new Color(152, 251, 152));
		btnMostrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMostrar.setBounds(705, 68, 119, 23);
		pn1.add(btnMostrar);
		
		chMostrarCodigo = new JCheckBox("Mostrar C\u00F3digo");
		chMostrarCodigo.setEnabled(false);
		chMostrarCodigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chMostrarCodigo.isSelected()){
					mostrarColumnaKardex(4, 50, 10, 30);
				}else{
					ocultarColumnaKardex(4);
				}
			}
		});
		chMostrarCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		chMostrarCodigo.setBounds(267, 105, 136, 23);
		pn1.add(chMostrarCodigo);
		
		chMostrarGrupo = new JCheckBox("Mostrar Grupo");
		chMostrarGrupo.setEnabled(false);
		chMostrarGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chMostrarGrupo.isSelected()){
					mostrarColumnaKardex(5, 250, 10, 150);
				}else{
					ocultarColumnaKardex(5);
				}
			}
		});
		chMostrarGrupo.setFont(new Font("Tahoma", Font.BOLD, 11));
		chMostrarGrupo.setBounds(405, 105, 131, 23);
		pn1.add(chMostrarGrupo);
		
		chMostrarReferencia = new JCheckBox("Mostrar Referencia");
		chMostrarReferencia.setEnabled(false);
		chMostrarReferencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chMostrarReferencia.isSelected()){
					mostrarColumnaKardex(7, 100, 10, 70);
				}else{
					ocultarColumnaKardex(7);
				}
			}
		});
		chMostrarReferencia.setFont(new Font("Tahoma", Font.BOLD, 11));
		chMostrarReferencia.setBounds(546, 105, 146, 23);
		pn1.add(chMostrarReferencia);
		
		JPanel pnBusquedaRapida = new JPanel();
		pnBusquedaRapida.setLayout(null);
		pnBusquedaRapida.setBackground(new Color(0, 51, 0));
		pnBusquedaRapida.setBounds(332, 60, 315, 38);
		pn1.add(pnBusquedaRapida);
		
		JLabel lblBusquedaRapidfa = new JLabel("B\u00FAsqueda R\u00E1pida");
		lblBusquedaRapidfa.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBusquedaRapidfa.setForeground(Color.WHITE);
		lblBusquedaRapidfa.setBackground(Color.BLACK);
		lblBusquedaRapidfa.setBounds(96, 0, 112, 14);
		pnBusquedaRapida.add(lblBusquedaRapidfa);
		
		txtBusquedaRapida = new JTextField();
		txtBusquedaRapida.setBounds(0, 18, 315, 20);
		pnBusquedaRapida.add(txtBusquedaRapida);
		txtBusquedaRapida.setColumns(10);
		
		chMostrarCostoInv = new JCheckBox("Mostrar Costo Inventario");
		chMostrarCostoInv.setEnabled(false);
		chMostrarCostoInv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chMostrarCostoInv.isSelected()){
					mostrarColumnaKardex(13, 200, 10, 100);
				}else{
					ocultarColumnaKardex(13);
				}
			}
		});
		chMostrarCostoInv.setFont(new Font("Tahoma", Font.BOLD, 11));
		chMostrarCostoInv.setBounds(708, 105, 194, 23);
		pn1.add(chMostrarCostoInv);
		
		JPanel pnRangoFechas = new JPanel();
		pnRangoFechas.setLayout(null);
		pnRangoFechas.setBackground(new Color(0, 51, 0));
		pnRangoFechas.setBounds(972, 11, 198, 72);
		pn1.add(pnRangoFechas);
		
		JLabel lblRangoFechas = new JLabel("Rango de Fechas");
		lblRangoFechas.setForeground(Color.WHITE);
		lblRangoFechas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRangoFechas.setBackground(Color.BLACK);
		lblRangoFechas.setBounds(47, 0, 119, 14);
		pnRangoFechas.add(lblRangoFechas);
		
		dchDesde = new JDateChooser();
		dchDesde.setBounds(49, 18, 144, 20);
		dchDesde.setDate(new Date());
		pnRangoFechas.add(dchDesde);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setForeground(Color.WHITE);
		lblDesde.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDesde.setBackground(Color.BLACK);
		lblDesde.setBounds(8, 21, 50, 14);
		pnRangoFechas.add(lblDesde);
		
		dchHasta = new JDateChooser();
		dchHasta.setBounds(49, 45, 144, 20);
		dchHasta.setDate(new Date());
		pnRangoFechas.add(dchHasta);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setForeground(Color.WHITE);
		lblHasta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHasta.setBackground(Color.BLACK);
		lblHasta.setBounds(8, 48, 50, 14);
		pnRangoFechas.add(lblHasta);
		
		chMostrarProveedor = new JCheckBox("Mostrar Proveedor");
		chMostrarProveedor.setEnabled(false);
		chMostrarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chMostrarProveedor.isSelected()){
					mostrarColumnaKardex(3, 250, 10, 150);
				}else{
					ocultarColumnaKardex(3);
				}
			}
		});
		chMostrarProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		chMostrarProveedor.setBounds(106, 105, 151, 23);
		pn1.add(chMostrarProveedor);
		
		chMostrarDescripcion = new JCheckBox("Mostrar Descripcion");
		chMostrarDescripcion.setEnabled(false);
		chMostrarDescripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chMostrarDescripcion.isSelected()){
					mostrarColumnaKardex(15, 250, 10, 150);
				}else{
					ocultarColumnaKardex(15);
				}
			}
		});
		chMostrarDescripcion.setFont(new Font("Tahoma", Font.BOLD, 11));
		chMostrarDescripcion.setBounds(904, 105, 146, 23);
		pn1.add(chMostrarDescripcion);
		
		JPanel pn3 = new JPanel();
		pn3.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn3.setBounds(13, 186, 1184, 246);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JScrollPane scrArticulos = new JScrollPane();
		scrArticulos.setBounds(2, 2, 1179, 242);
		pn3.add(scrArticulos);
		
		tbArticulos = new JTable();
		tbArticulos.setToolTipText("Puede seleccionar cualquiera de las filas y presionar clic derecho para ver las acciones que puede realizar.");
		tbArticulos.setEnabled(false);
		tbArticulos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbArticulos.rowAtPoint(point);
		        tbArticulos.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		        mostrarPopupTbArticulos();
			}
		});
		scrArticulos.setViewportView(tbArticulos);
      		
		JPanel pn4 = new JPanel();
		pn4.setBorder(new LineBorder(new Color(0, 0, 0)));
		pn4.setBounds(13, 443, 1184, 79);
		contentPane.add(pn4);
		pn4.setLayout(null);
		
		JPanel pnVentas = new JPanel();
		pnVentas.setLayout(null);
		pnVentas.setBackground(new Color(0, 51, 0));
		pnVentas.setBounds(299, 20, 85, 38);
		pn4.add(pnVentas);
		
		JLabel lblVentas = new JLabel("Ventas");
		lblVentas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVentas.setForeground(Color.WHITE);
		lblVentas.setBackground(SystemColor.desktop);
		lblVentas.setBounds(25, 0, 54, 14);
		pnVentas.add(lblVentas);
		
		txtVentas = new JTextField();
		txtVentas.setColumns(10);
		txtVentas.setBounds(0, 18, 85, 20);
		txtVentas.setEditable(false);
		txtVentas.setText("0");
		pnVentas.add(txtVentas);
		
		JPanel pnSalidas = new JPanel();
		pnSalidas.setLayout(null);
		pnSalidas.setBackground(new Color(0, 51, 0));
		pnSalidas.setBounds(489, 20, 85, 38);
		pn4.add(pnSalidas);
		
		JLabel lblSalidas = new JLabel("Salidas");
		lblSalidas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSalidas.setForeground(Color.WHITE);
		lblSalidas.setBackground(SystemColor.desktop);
		lblSalidas.setBounds(25, 0, 45, 14);
		pnSalidas.add(lblSalidas);
		
		txtSalidas = new JTextField();
		txtSalidas.setColumns(10);
		txtSalidas.setBounds(0, 18, 85, 20);
		txtSalidas.setEditable(false);
		txtSalidas.setText("0");
		pnSalidas.add(txtSalidas);
		
		JPanel pnCompras = new JPanel();
		pnCompras.setLayout(null);
		pnCompras.setBackground(new Color(0, 51, 0));
		pnCompras.setBounds(394, 20, 85, 38);
		pn4.add(pnCompras);
		
		JLabel lblCompras = new JLabel("Compras");
		lblCompras.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCompras.setForeground(Color.WHITE);
		lblCompras.setBackground(SystemColor.desktop);
		lblCompras.setBounds(21, 0, 54, 14);
		pnCompras.add(lblCompras);
		
		txtCompras = new JTextField();
		txtCompras.setEditable(false);
		txtCompras.setColumns(10);
		txtCompras.setBounds(0, 18, 85, 20);
		txtCompras.setText("0");
		pnCompras.add(txtCompras);
		
		JPanel pnEntradas = new JPanel();
		pnEntradas.setLayout(null);
		pnEntradas.setBackground(new Color(0, 51, 0));
		pnEntradas.setBounds(584, 20, 85, 38);
		pn4.add(pnEntradas);
		
		JLabel lblEntradas = new JLabel("Entradas");
		lblEntradas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEntradas.setForeground(Color.WHITE);
		lblEntradas.setBackground(SystemColor.desktop);
		lblEntradas.setBounds(18, 0, 61, 14);
		pnEntradas.add(lblEntradas);
		
		txtEntradas = new JTextField();
		txtEntradas.setEditable(false);
		txtEntradas.setColumns(10);
		txtEntradas.setBounds(0, 18, 85, 20);
		txtEntradas.setText("0");
		pnEntradas.add(txtEntradas);
		
		JPanel pnDevolucionProveed = new JPanel();
		pnDevolucionProveed.setLayout(null);
		pnDevolucionProveed.setBackground(new Color(0, 51, 0));
		pnDevolucionProveed.setBounds(679, 20, 113, 38);
		pn4.add(pnDevolucionProveed);
		
		JLabel lblDevolucioProveed = new JLabel("Dev-Proveed");
		lblDevolucioProveed.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDevolucioProveed.setForeground(Color.WHITE);
		lblDevolucioProveed.setBackground(SystemColor.desktop);
		lblDevolucioProveed.setBounds(18, 0, 90, 14);
		pnDevolucionProveed.add(lblDevolucioProveed);
		
		txtDevolucionProveed = new JTextField();
		txtDevolucionProveed.setEditable(false);
		txtDevolucionProveed.setColumns(10);
		txtDevolucionProveed.setBounds(0, 18, 113, 20);
		txtDevolucionProveed.setText("0");
		pnDevolucionProveed.add(txtDevolucionProveed);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 51, 0));
		panel.setBounds(802, 20, 113, 38);
		pn4.add(panel);
		
		JLabel lblDevolucionClient = new JLabel("Dev-Cliente");
		lblDevolucionClient.setForeground(Color.WHITE);
		lblDevolucionClient.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDevolucionClient.setBackground(SystemColor.desktop);
		lblDevolucionClient.setBounds(24, 0, 78, 14);
		panel.add(lblDevolucionClient);
		
		txtDevolucionCliente = new JTextField();
		txtDevolucionCliente.setEditable(false);
		txtDevolucionCliente.setColumns(10);
		txtDevolucionCliente.setBounds(0, 18, 113, 20);
		txtDevolucionCliente.setText("0");
		panel.add(txtDevolucionCliente);
		
		JLabel lblNotaIngrese = new JLabel("NOTA: Ingrese el texto de busqueda rapida y el sistema traera las coincidencias en el nombre del articulo.");
		lblNotaIngrese.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNotaIngrese.setBounds(13, 161, 972, 14);
		contentPane.add(lblNotaIngrese);
		
		//Metodos que debe ejecutar la ventana al inicializarse
		listarLineaArticulos();
	}
	
	//Metodo que permite listar las lineas de articulos y desplegarlos en un combo box
	public void listarLineaArticulos() {
		DelegadoLineaArticulos delegadoLineaArticulos = new DelegadoLineaArticulos();
		cbLineaArticulos.removeAllItems();
		List<LineaArticulos> lineaArticulos = delegadoLineaArticulos.listarLineaArticulos();
		modeloLineaArticulos.addElement("--TODAS--");
		cbLineaArticulos.setModel(modeloLineaArticulos);
		
		for(LineaArticulos lineaArticulos1 : lineaArticulos){
			modeloLineaArticulos.addElement(new LineaArticulos(lineaArticulos1.getNombreL(), lineaArticulos1.getCodigo()));
			cbLineaArticulos.setModel(modeloLineaArticulos);
		}
	}
	//Metodo para listar los articulos de cada linea de articulos elegido 
	private void listarArticulos(String nombreLinea) {
		
		modeloArticulos.removeAllElements();
		DelegadoArticulo delegadoArticulo = new DelegadoArticulo();
		List<Articulo> articulos = delegadoArticulo.traerArticulosPorLineaArticulos(nombreLinea);
		modeloArticulos.addElement("--TODOS--");
		
		try{
			for(int i=0;i<= articulos.size();i++){
				
				modeloArticulos.addElement(articulos.get(i));
				cbArticulos.setModel(modeloArticulos);
			}
		}catch(Exception e){
			e.getMessage();
		}
	}
	//Metodo para validar el rango de fechas
	private void validarRangoFechas() {
		if(dchDesde.getCalendar()==null && dchHasta.getCalendar()==null){
			JOptionPane.showMessageDialog(null, "Debe ingesar un rango de fechas");
		}else{
			limpiarRegistrosTabla();
			listarRegistrosKardex();
			limpiarCantidadMovimientos();
			calcularCantidadMovimientos();
			habilitarODeshabilitarCheckBox();
		}
	}
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbArticulos() {
		modeloTbArticulos.addColumn("Fecha");
		modeloTbArticulos.addColumn("Movimiento");
		modeloTbArticulos.addColumn("Doc");
		modeloTbArticulos.addColumn("Proveedor");
		modeloTbArticulos.addColumn("Cod");
		modeloTbArticulos.addColumn("Linea");
		modeloTbArticulos.addColumn("Articulo");
		modeloTbArticulos.addColumn("Referencia");
		modeloTbArticulos.addColumn("Entrada");
		modeloTbArticulos.addColumn("Salida");
		modeloTbArticulos.addColumn("Costo Unit");
		modeloTbArticulos.addColumn("Costo Mov");
		modeloTbArticulos.addColumn("Costo Prom");
		modeloTbArticulos.addColumn("Costo Inv");
		modeloTbArticulos.addColumn("Sal.Total");
		modeloTbArticulos.addColumn("Descripcion");
		
		tbArticulos.setModel(modeloTbArticulos);
		
	}
	
	//Metodo para llenar la tabla cuando recibe como parametro una lista de registros kardex
	private void llenarTabla( List<KardexElectronico> listaRegistrosKardex) {
		
		if(tbArticulos.getModel().getColumnCount()==0){
			llenarColumnasTbArticulos();
		}
		
		String [] fila = new String[modeloTbArticulos.getColumnCount()];
		
		for(KardexElectronico registrosKardex : listaRegistrosKardex){
			
			fila[0]= convertirDateAString(registrosKardex.getFecha());
			fila[1]= registrosKardex.getMovimiento();
			fila[2]= String.valueOf(registrosKardex.getNroDocumento());
			fila[3]= registrosKardex.getProveedorUltimaCompra();
			fila[4]= String.valueOf(registrosKardex.getArticulo().getCodigo());
			fila[5]= registrosKardex.getArticulo().getLineaArticulos().getNombreL();
			fila[6]= registrosKardex.getArticulo().getNombre();
			fila[7]= registrosKardex.getArticulo().getReferencia();
			fila[8]= String.valueOf(registrosKardex.getCantidadEntrada());
			fila[9]= String.valueOf(registrosKardex.getCantidadSalida());
			fila[10]= formatearNumero(registrosKardex.getCostoUSalida());	
			fila[11]= formatearNumero(registrosKardex.getCostoTSalida());
			if("Compra".equals(registrosKardex.getMovimiento())||"Entrada".equals(registrosKardex.getMovimiento())||"Dev-Cliente".equals(registrosKardex.getMovimiento())){
				fila[10]= formatearNumero(registrosKardex.getCostoUEntrada());	
				fila[11]= formatearNumero(registrosKardex.getCostoTEntrada());
			}else{
				fila[10]= formatearNumero(registrosKardex.getCostoUSalida());	
				fila[11]= formatearNumero(registrosKardex.getCostoTSalida());
			}
			fila[12]= formatearNumero(registrosKardex.getCostoUSaldo()); 
			fila[13]= formatearNumero(registrosKardex.getCostoTSaldo());
			fila[14]= String.valueOf(registrosKardex.getCantidadSaldo());
			fila[15]= registrosKardex.getArticulo().getDescripcion();
			
			modeloTbArticulos.addRow(fila);
		}
		
		tbArticulos.setModel(modeloTbArticulos);
		
		ocultarColumnaKardex(3);
		ocultarColumnaKardex(4);
		ocultarColumnaKardex(5);
		ocultarColumnaKardex(7);
		ocultarColumnaKardex(13);
		ocultarColumnaKardex(15);
		
		colorearMovimientoEnTabla(tbArticulos);
		
	}
		
	//Metodo que permite la conversion de un dato de tipo date a uno de tipo string
	public String convertirDateAString(Date strFecha){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(strFecha);
		
		return fecha;
	}
	
	//Metodo para listar las compras de acuerdo a los parametros de fecha causacion y proveedor
	private void listarRegistrosKardex(){
		
		DelegadoKardexElectronico delegadoKardexElectronico = new DelegadoKardexElectronico();
		
		sobreescribirCbMovimiento();
		
		if("".equals(txtBusquedaRapida.getText())){
			if(cbMovimiento.getSelectedItem().equals("--TODOS--") && dchDesde.getDate()!=null && dchHasta.getDate()!=null && cbArticulos.getSelectedItem().equals("--TODOS--") && cbLineaArticulos.getSelectedItem().equals("--TODAS--")){
				listaRegistrosKardex = delegadoKardexElectronico.traerRegistrosKardexPorF(restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
				limpiarRegistrosTabla();
				llenarTabla(listaRegistrosKardex);
			}else{
				if(cbMovimiento.getSelectedItem().equals("--TODOS--") && dchDesde.getDate()!=null && dchHasta.getDate()!=null && cbArticulos.getSelectedItem().equals("--TODOS--") && cbLineaArticulos.getSelectedItem()!=""){
					listaRegistrosKardex = delegadoKardexElectronico.traerRegistrosKardexPorGF(cbLineaArticulos.getSelectedItem().toString(), restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
					limpiarRegistrosTabla();
					llenarTabla(listaRegistrosKardex);
				}else{
					if(cbMovimiento.getSelectedItem().equals("--TODOS--") && dchDesde.getDate()!=null && dchHasta.getDate()!=null && cbArticulos.getSelectedItem()!="" && cbLineaArticulos.getSelectedItem()!=""){
						listaRegistrosKardex = delegadoKardexElectronico.traerRegistrosKardexPorGAF(cbLineaArticulos.getSelectedItem().toString(), cbArticulos.getSelectedItem().toString(), restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
						limpiarRegistrosTabla();
						llenarTabla(listaRegistrosKardex);
					}else{
						if(cbMovimiento.getSelectedItem()!="" && dchDesde.getDate()!=null && dchHasta.getDate()!=null && cbArticulos.getSelectedItem().equals("--TODOS--") && cbLineaArticulos.getSelectedItem().equals("--TODAS--")){
							listaRegistrosKardex = delegadoKardexElectronico.traerRegistrosKardexPorMF(movimiento , restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
							limpiarRegistrosTabla();
							llenarTabla(listaRegistrosKardex);
						}else{
							if(cbMovimiento.getSelectedItem()!="" && dchDesde.getDate()!=null && dchHasta.getDate()!=null && cbArticulos.getSelectedItem().equals("--TODOS--") && cbLineaArticulos.getSelectedItem()!="" ){
								listaRegistrosKardex = delegadoKardexElectronico.traerRegistrosKardexPorMGF(movimiento , cbLineaArticulos.getSelectedItem().toString(), restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
								limpiarRegistrosTabla();
								llenarTabla(listaRegistrosKardex);
							}else{
								listaRegistrosKardex = delegadoKardexElectronico.traerRegistrosKardexPorMGAF(movimiento , cbLineaArticulos.getSelectedItem().toString(), cbArticulos.getSelectedItem().toString(), restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
								limpiarRegistrosTabla();
								llenarTabla(listaRegistrosKardex);
							}
						}
					}
				}
			}
		}else{
			if(cbMovimiento.getSelectedItem().equals("--TODOS--") && dchDesde.getDate()!=null && dchHasta.getDate()!=null && cbArticulos.getSelectedItem().equals("--TODOS--") && cbLineaArticulos.getSelectedItem().equals("--TODAS--") && txtBusquedaRapida.getText()!=""){
				listaRegistrosKardex = delegadoKardexElectronico.traerRegistrosKardexPorBF(txtBusquedaRapida.getText() ,restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
				limpiarRegistrosTabla();
				llenarTabla(listaRegistrosKardex);
			}else{
				if(cbMovimiento.getSelectedItem().equals("--TODOS--") && dchDesde.getDate()!=null && dchHasta.getDate()!=null && cbArticulos.getSelectedItem().equals("--TODOS--") && cbLineaArticulos.getSelectedItem()!=""){
					listaRegistrosKardex = delegadoKardexElectronico.traerRegistrosKardexPorBGF(cbLineaArticulos.getSelectedItem().toString() ,txtBusquedaRapida.getText() ,restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
					limpiarRegistrosTabla();
					llenarTabla(listaRegistrosKardex);
				}else{
					if(cbMovimiento.getSelectedItem().equals("--TODOS--") && dchDesde.getDate()!=null && dchHasta.getDate()!=null && cbArticulos.getSelectedItem()!="" && cbLineaArticulos.getSelectedItem()!=""){
						listaRegistrosKardex = delegadoKardexElectronico.traerRegistrosKardexPorBGF(cbLineaArticulos.getSelectedItem().toString() ,txtBusquedaRapida.getText() ,restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
						limpiarRegistrosTabla();
						llenarTabla(listaRegistrosKardex);
					}else{
						if(cbMovimiento.getSelectedItem()!="" && dchDesde.getDate()!=null && dchHasta.getDate()!=null && cbArticulos.getSelectedItem().equals("--TODOS--") && cbLineaArticulos.getSelectedItem().equals("--TODAS--") && txtBusquedaRapida.getText()!=""){
							listaRegistrosKardex = delegadoKardexElectronico.traerRegistrosKardexPorBMF(txtBusquedaRapida.getText(), movimiento ,restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
							limpiarRegistrosTabla();
							llenarTabla(listaRegistrosKardex);
						}else{
							if(cbMovimiento.getSelectedItem()!="" && dchDesde.getDate()!=null && dchHasta.getDate()!=null && cbArticulos.getSelectedItem().equals("--TODOS--") && cbLineaArticulos.getSelectedItem()!="" && txtBusquedaRapida.getText()!=""){
								listaRegistrosKardex = delegadoKardexElectronico.traerRegistrosKardexPorBMGF(txtBusquedaRapida.getText(), movimiento , cbLineaArticulos.getSelectedItem().toString(), restarDias(dchDesde.getDate()) , sumarDias(dchHasta.getDate()));
								limpiarRegistrosTabla();
								llenarTabla(listaRegistrosKardex);
							}
						}
					}
				}
			}
		}
	}
	//Metodo que permite al combo box movimiento sobreescribir en otras palabra los movimientos devolucion proveedor y devolucion cliente para su busqueda 
	private void sobreescribirCbMovimiento() {
		if("Devolucion Proveedor".equals(cbMovimiento.getSelectedItem().toString())){
			movimiento = "Dev-Proveed";
		}else{
			if("Devolucion Cliente".equals(cbMovimiento.getSelectedItem().toString())){
				movimiento = "Dev-Cliente";
			}else{
				movimiento = cbMovimiento.getSelectedItem().toString();
			}
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
	
	//Metodo para limpiar la tabla 
	private void limpiarRegistrosTabla(){
	   for (int i = 0; i < tbArticulos.getRowCount(); i++) {
	       modeloTbArticulos.removeRow(i);
	       i-=1;
	   }
	}
	
	//Metodo para convertir un numero corriente en formato de pesos y decimales
	private String formatearNumero(Double numero){
		NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es","CO"));
		return formato.format(numero); 
	}
	
	//Ventana para colorear las celdas de acuerdo al tipo de movimiento del kardex
	private void colorearMovimientoEnTabla(JTable tablaArticulos) {
		tablaArticulos.getColumn("Movimiento").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			if (String.valueOf(table.getValueAt(row, 1)).equals("Compra") )
		      {
				comp.setBackground(Color.getHSBColor(0.08f, 0.78f, 0.83f));
		      } else {
		    	  if(String.valueOf(table.getValueAt(row, 1)).equals("Dev-Proveed")){
		    		  comp.setBackground(Color.getHSBColor(1.19f, 0.26f, 0.77f));
		    	  }else{
		    		  if(String.valueOf(table.getValueAt(row, 1)).equals("Venta")){
		    			  comp.setBackground(Color.PINK);
			    	  }else{
			    		  if(String.valueOf(table.getValueAt(row, 1)).equals("Dev-Cliente")){
			    			  comp.setBackground(Color.GREEN);
				    	  }else{
				    		  if(String.valueOf(table.getValueAt(row, 1)).equals("Entrada")){
				    			  comp.setBackground(Color.YELLOW);
					    	  }else{
					    		  comp.setBackground(Color.LIGHT_GRAY);
					    	  }
				    	  }
			    	  }
		    	  }
		      }
	        return comp; 
			}}); 
	}
	//Metodo para ocultar las columnas que uno desea aplicar
	public void ocultarColumnaKardex(int indice){
		
		tbArticulos.getColumnModel().getColumn(indice).setWidth(0);
		tbArticulos.getColumnModel().getColumn(indice).setMaxWidth(0);
		tbArticulos.getColumnModel().getColumn(indice).setMinWidth(0);
		tbArticulos.getColumnModel().getColumn(indice).setPreferredWidth(0);
		tbArticulos.getColumnModel().getColumn(indice).setResizable(false);
		
	}
	//Metodo para mostrar las columnas que uno desea aplicar
	public void mostrarColumnaKardex(int indice, int maxAncho, int minAncho, int norAncho){
		
		tbArticulos.getColumnModel().getColumn(indice).setWidth(norAncho);
		tbArticulos.getColumnModel().getColumn(indice).setMaxWidth(maxAncho);
		tbArticulos.getColumnModel().getColumn(indice).setMinWidth(minAncho);
		tbArticulos.getColumnModel().getColumn(indice).setPreferredWidth(norAncho);
		tbArticulos.getColumnModel().getColumn(indice).setResizable(true);
		
	}
	//Metodo para calcular la cantidad de movimientos de la tabla articulos basado en el tipo de movimiento
	private void calcularCantidadMovimientos(){
		
		int contCompras = 0;
		int contVentas = 0;
		int contDevProveedor = 0;
		int contDevCliente = 0;
		int contEntradas = 0;
		int contSalidas = 0;
		
		
		for(int i=0; i<tbArticulos.getRowCount(); i++) {
			if(tbArticulos.getValueAt(i,1).equals("Compra")){
				contCompras +=1;
				txtCompras.setText(String.valueOf(contCompras));
			}else{
				if(tbArticulos.getValueAt(i,1).equals("Venta")){
					contVentas +=1;
					txtVentas.setText(String.valueOf(contVentas));
				}else{
					if(tbArticulos.getValueAt(i,1).equals("Dev-Proveed")){
						contDevProveedor +=1;
						txtDevolucionProveed.setText(String.valueOf(contDevProveedor));
					}else{
						if(tbArticulos.getValueAt(i,1).equals("Dev-Cliente")){
							contDevCliente +=1;
							txtDevolucionCliente.setText(String.valueOf(contDevCliente));
						}else{
							if(tbArticulos.getValueAt(i,1).equals("Entrada")){
								contEntradas +=1;
								txtEntradas.setText(String.valueOf(contEntradas));
							}else{
								contSalidas +=1;
								txtSalidas.setText(String.valueOf(contSalidas));
							}
						}
					}
				}
			}
		}
	}
	//Metodo para limpiar la cantidad de movimientos con el fin de mantener los calculos actualizados
	private void limpiarCantidadMovimientos() {
		txtCompras.setText("0");
		txtVentas.setText("0");
		txtDevolucionProveed.setText("0");
		txtDevolucionCliente.setText("0");
		txtEntradas.setText("0");
		txtSalidas.setText("0");
	}
	//Metodo que permite habilitar o deshabilitar los checkbox
	public void habilitarODeshabilitarCheckBox(){
		
		if(tbArticulos.getRowCount() == 0){
			chMostrarProveedor.setEnabled(false);
			chMostrarCodigo.setEnabled(false);
			chMostrarGrupo.setEnabled(false);
			chMostrarCodigo.setEnabled(false);
			chMostrarCostoInv.setEnabled(false);
			chMostrarReferencia.setEnabled(false);
			chMostrarDescripcion.setEnabled(false);
		}else{
			chMostrarProveedor.setEnabled(true);
			chMostrarCodigo.setEnabled(true);
			chMostrarGrupo.setEnabled(true);
			chMostrarCodigo.setEnabled(true);
			chMostrarCostoInv.setEnabled(true);
			chMostrarReferencia.setEnabled(true);
			chMostrarDescripcion.setEnabled(true);
		}
	}
	//Metodo para mostrar una especie de menu al interior de la tabla
	private void mostrarPopupTbArticulos(){
		JPopupMenu pmArticulos = new JPopupMenu();
		JMenuItem miVerDoc = new JMenuItem("Ver Documento");
		
		pmArticulos.add(miVerDoc);
		miVerDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verDocumento();
			}
		});
			
		tbArticulos.setComponentPopupMenu(pmArticulos);	
		
	}
	
	//Metodo para ver el documento en pdf
	private void verDocumento() {
		KardexElectronico registrosKardex= listaRegistrosKardex.get(filaSeleccionada);
		
	}
	//Metodo para imprimir el documento 
	private void imprimirDocumento(){
		
	}
}
