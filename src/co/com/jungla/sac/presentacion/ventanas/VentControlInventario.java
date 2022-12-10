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
import java.util.List;
import java.util.Locale;

import co.com.jungla.sac.persistencia.entidades.Articulo;
import co.com.jungla.sac.persistencia.entidades.ControlInventario;
import co.com.jungla.sac.persistencia.entidades.LineaArticulos;
import co.com.jungla.sac.persistencia.entidades.Persona;
import co.com.jungla.sac.persistencia.entidades.Proveedor;

import co.com.jungla.sac.negocio.delegados.DelegadoArticulo;
import co.com.jungla.sac.negocio.delegados.DelegadoControlInventario;
import co.com.jungla.sac.negocio.delegados.DelegadoLineaArticulos;
import co.com.jungla.sac.negocio.delegados.DelegadoProveedor;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.ImageIcon;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo un control de los articulos del inventario, como su precio, su costo, sus rentabilidades y demas datos.
 * @author Luis Fernando Pedroza T.
 * @version: 21/09/2016
 */
public class VentControlInventario extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtNroArticulos;
	private JTextField txtCantidad;
	private JTextField txtBusquedaArticulo;
	private JTextField txtNroGrupos;
	private JTextField txtCostoInv;
	private JTextField txtEnAlerta;
	private JTextField txtCostoVenta;
	private JComboBox cbEstadoArticulos;
	private JComboBox cbLineaArticulos;
	private JComboBox cbProveedor;
	private JCheckBox chArticulosExistencias;
	private JCheckBox chUltimaCompra;
	private JCheckBox chDatosOpcionales;
	private JTable tbArticulos;
	private DefaultComboBoxModel modeloProveedor = new DefaultComboBoxModel();
	private DefaultComboBoxModel modeloLineaArticulos = new DefaultComboBoxModel();
	private DefaultTableModel modeloTbArticulos = new DefaultTableModel();
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private int filaSeleccionada;
	private List<ControlInventario> listaRegistrosInventario;
	private String estadoArticulo;
	private List<Articulo> articuloElegidoInventario;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentControlInventario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentControlInventario.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Control Inventarios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 1331, 570);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(13, 11, 1300, 133);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JPanel pnEstadoArticulos = new JPanel();
		pnEstadoArticulos.setBounds(116, 11, 315, 38);
		pn1.add(pnEstadoArticulos);
		pnEstadoArticulos.setBackground(new Color(0, 51, 0));
		pnEstadoArticulos.setLayout(null);
		
		//Funcionalidad para crear el proveedor al desplegar el combo box
		cbEstadoArticulos = new JComboBox();
		cbEstadoArticulos.setModel(new DefaultComboBoxModel(new String[] {"--TODOS LOS ARTICULOS--", "POR DEBAJO DEL SALDO MINIMO", "CON PRECIO VENTA < FACTOR RENTABILIDAD", "CON PRECIO DE VENTA INFERIOR AL COSTO", "ARTICULOS DESHABILITADOS"}));
		cbEstadoArticulos.setBounds(0, 16, 315, 22);
		pnEstadoArticulos.add(cbEstadoArticulos);
		
		JLabel lblEstadoArticulos = new JLabel("Estado Articulos");
		lblEstadoArticulos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstadoArticulos.setBackground(SystemColor.desktop);
		lblEstadoArticulos.setForeground(SystemColor.window);
		lblEstadoArticulos.setBounds(110, 0, 91, 14);
		pnEstadoArticulos.add(lblEstadoArticulos);
		
		JPanel pnGrupoArticulos = new JPanel();
		pnGrupoArticulos.setLayout(null);
		pnGrupoArticulos.setBackground(new Color(0, 51, 0));
		pnGrupoArticulos.setBounds(444, 11, 289, 38);
		pn1.add(pnGrupoArticulos);
		
		cbLineaArticulos = new JComboBox();
		cbLineaArticulos.setBounds(0, 16, 289, 22);
		pnGrupoArticulos.add(cbLineaArticulos);
		
		JLabel lblGrupoDeArticulos = new JLabel("Linea de Articulos");
		lblGrupoDeArticulos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGrupoDeArticulos.setForeground(Color.WHITE);
		lblGrupoDeArticulos.setBackground(SystemColor.desktop);
		lblGrupoDeArticulos.setBounds(96, 0, 115, 14);
		pnGrupoArticulos.add(lblGrupoDeArticulos);
		
		JPanel pnProveedorUltimaCompra = new JPanel();
		pnProveedorUltimaCompra.setLayout(null);
		pnProveedorUltimaCompra.setBackground(new Color(0, 51, 0));
		pnProveedorUltimaCompra.setBounds(743, 11, 325, 38);
		pn1.add(pnProveedorUltimaCompra);
		
		cbProveedor = new JComboBox();
		cbProveedor.setBounds(0, 16, 325, 22);
		pnProveedorUltimaCompra.add(cbProveedor);
		
		JLabel lblProveedorDeLa = new JLabel("Proveedor de la \u00FAltima compra");
		lblProveedorDeLa.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProveedorDeLa.setForeground(Color.WHITE);
		lblProveedorDeLa.setBackground(SystemColor.desktop);
		lblProveedorDeLa.setBounds(80, 0, 188, 14);
		pnProveedorUltimaCompra.add(lblProveedorDeLa);
		
		JButton btnMostrarInventario = new JButton("Mostrar el Inventario");
		btnMostrarInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarRegistrosInventario();
				habilitarODeshabilitarCheckBox();
			}
		});
		btnMostrarInventario.setForeground(new Color(0, 51, 0));
		btnMostrarInventario.setBackground(new Color(153, 255, 102));
		btnMostrarInventario.setIcon(null);
		btnMostrarInventario.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMostrarInventario.setBounds(505, 65, 157, 23);
		pn1.add(btnMostrarInventario);
		
		chArticulosExistencias = new JCheckBox("Solo art\u00EDculos con existencias");
		chArticulosExistencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarArticulosConExistencias();
			}
		});
		chArticulosExistencias.setFont(new Font("Tahoma", Font.BOLD, 11));
		chArticulosExistencias.setBounds(236, 99, 194, 23);
		chArticulosExistencias.setEnabled(false);
		pn1.add(chArticulosExistencias);
		
		chUltimaCompra = new JCheckBox("Ultima Compra (Costo y Proveedor)");
		chUltimaCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chUltimaCompra.isSelected()){
					mostrarColumnaKardex(6, 250, 10, 100);
					mostrarColumnaKardex(7, 200, 10, 90);
				}else{
					ocultarColumnaKardex(6);
					ocultarColumnaKardex(7);
				}
			}
		});
		chUltimaCompra.setFont(new Font("Tahoma", Font.BOLD, 11));
		chUltimaCompra.setBounds(442, 99, 240, 23);
		chUltimaCompra.setEnabled(false);
		pn1.add(chUltimaCompra);
		
		JButton btnAyuda = new JButton("Ayuda");
		btnAyuda.setForeground(new Color(0, 0, 0));
		btnAyuda.setBackground(new Color(255, 255, 0));
		btnAyuda.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAyuda.setBounds(704, 65, 91, 23);
		pn1.add(btnAyuda);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("D:\\LUISEFE387\\WORKSPACE\\SACJungla\\Imagenes\\Logo Inventario.png"));
		label_1.setBounds(1102, 37, 91, 67);
		pn1.add(label_1);
		
		chDatosOpcionales = new JCheckBox("Datos opcionales (Referencia, Presentacion y Descripcion)");
		chDatosOpcionales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chDatosOpcionales.isSelected()){
					mostrarColumnaKardex(15, 250, 10, 90);
					mostrarColumnaKardex(16, 200, 10, 90);
					mostrarColumnaKardex(17, 200, 10, 90);
				}else{
					ocultarColumnaKardex(15);
					ocultarColumnaKardex(16);
					ocultarColumnaKardex(17);
				}
			}
		});
		chDatosOpcionales.setFont(new Font("Tahoma", Font.BOLD, 11));
		chDatosOpcionales.setBounds(684, 99, 363, 23);
		chDatosOpcionales.setEnabled(false);
		pn1.add(chDatosOpcionales);
		
		JPanel pn3 = new JPanel();
		pn3.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn3.setBounds(13, 155, 1300, 272);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JScrollPane scrArticulos = new JScrollPane();
		scrArticulos.setBounds(2, 2, 1295, 267);
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
		pn4.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn4.setBounds(13, 438, 1300, 79);
		contentPane.add(pn4);
		pn4.setLayout(null);
		
		JPanel pnNroArticulos = new JPanel();
		pnNroArticulos.setLayout(null);
		pnNroArticulos.setBackground(new Color(0, 51, 0));
		pnNroArticulos.setBounds(133, 20, 85, 38);
		pn4.add(pnNroArticulos);
		
		JLabel lblNroArticulos = new JLabel("Nro Articulos");
		lblNroArticulos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNroArticulos.setForeground(Color.WHITE);
		lblNroArticulos.setBackground(SystemColor.desktop);
		lblNroArticulos.setBounds(7, 0, 82, 14);
		pnNroArticulos.add(lblNroArticulos);
		
		txtNroArticulos = new JTextField();
		txtNroArticulos.setColumns(10);
		txtNroArticulos.setBounds(0, 18, 85, 20);
		txtNroArticulos.setEditable(false);
		pnNroArticulos.add(txtNroArticulos);
		
		JPanel pnCantidad = new JPanel();
		pnCantidad.setLayout(null);
		pnCantidad.setBackground(new Color(0, 51, 0));
		pnCantidad.setBounds(323, 20, 85, 38);
		pn4.add(pnCantidad);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantidad.setForeground(Color.WHITE);
		lblCantidad.setBackground(SystemColor.desktop);
		lblCantidad.setBounds(17, 0, 56, 14);
		pnCantidad.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(0, 18, 85, 20);
		txtCantidad.setEditable(false);
		pnCantidad.add(txtCantidad);
		
		//Funcionalidad del boton quitar articulo en la que elimina los articulos seleccionados
		JButton btnExcel = new JButton("Excel");
		btnExcel.setForeground(new Color(0, 51, 0));
		btnExcel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExcel.setBounds(1078, 11, 85, 23);
		pn4.add(btnExcel);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setForeground(new Color(0, 51, 0));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalir.setBounds(1078, 45, 85, 23);
		pn4.add(btnSalir);
		
		JPanel pnNroGrupos = new JPanel();
		pnNroGrupos.setLayout(null);
		pnNroGrupos.setBackground(new Color(0, 51, 0));
		pnNroGrupos.setBounds(228, 20, 85, 38);
		pn4.add(pnNroGrupos);
		
		JLabel lblNroGrupos = new JLabel("Nro Grupos");
		lblNroGrupos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNroGrupos.setForeground(Color.WHITE);
		lblNroGrupos.setBackground(SystemColor.desktop);
		lblNroGrupos.setBounds(12, 0, 72, 14);
		pnNroGrupos.add(lblNroGrupos);
		
		txtNroGrupos = new JTextField();
		txtNroGrupos.setEditable(false);
		txtNroGrupos.setColumns(10);
		txtNroGrupos.setBounds(0, 18, 85, 20);
		pnNroGrupos.add(txtNroGrupos);
		
		JPanel pnCostoInv = new JPanel();
		pnCostoInv.setLayout(null);
		pnCostoInv.setBackground(new Color(0, 51, 0));
		pnCostoInv.setBounds(418, 20, 188, 38);
		pn4.add(pnCostoInv);
		
		JLabel lblCostoInv = new JLabel("Costo INV.");
		lblCostoInv.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCostoInv.setForeground(Color.WHITE);
		lblCostoInv.setBackground(SystemColor.desktop);
		lblCostoInv.setBounds(63, 0, 67, 14);
		pnCostoInv.add(lblCostoInv);
		
		txtCostoInv = new JTextField();
		txtCostoInv.setEditable(false);
		txtCostoInv.setColumns(10);
		txtCostoInv.setBounds(0, 18, 188, 20);
		pnCostoInv.add(txtCostoInv);
		
		JPanel pnEnAlerta = new JPanel();
		pnEnAlerta.setLayout(null);
		pnEnAlerta.setBackground(new Color(0, 51, 0));
		pnEnAlerta.setBounds(616, 20, 85, 38);
		pn4.add(pnEnAlerta);
		
		JLabel lblEnAlerta = new JLabel("En ALERTA");
		lblEnAlerta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnAlerta.setForeground(Color.WHITE);
		lblEnAlerta.setBackground(SystemColor.desktop);
		lblEnAlerta.setBounds(15, 0, 66, 14);
		pnEnAlerta.add(lblEnAlerta);
		
		txtEnAlerta = new JTextField();
		txtEnAlerta.setBackground(new Color(204, 102, 102));
		txtEnAlerta.setEditable(false);
		txtEnAlerta.setColumns(10);
		txtEnAlerta.setBounds(0, 18, 85, 20);
		pnEnAlerta.add(txtEnAlerta);
		
		JPanel pnCostoVenta = new JPanel();
		pnCostoVenta.setLayout(null);
		pnCostoVenta.setBackground(new Color(0, 51, 0));
		pnCostoVenta.setBounds(711, 20, 94, 38);
		pn4.add(pnCostoVenta);
		
		JLabel lblCostoVenta = new JLabel("Costo>Venta");
		lblCostoVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCostoVenta.setForeground(Color.WHITE);
		lblCostoVenta.setBackground(SystemColor.desktop);
		lblCostoVenta.setBounds(10, 0, 81, 14);
		pnCostoVenta.add(lblCostoVenta);
		
		txtCostoVenta = new JTextField();
		txtCostoVenta.setBackground(new Color(204, 102, 102));
		txtCostoVenta.setEditable(false);
		txtCostoVenta.setColumns(10);
		txtCostoVenta.setBounds(0, 18, 94, 20);
		pnCostoVenta.add(txtCostoVenta);
		
		JPanel pjnArticulo = new JPanel();
		pjnArticulo.setBounds(815, 20, 243, 38);
		pn4.add(pjnArticulo);
		pjnArticulo.setLayout(null);
		pjnArticulo.setBackground(new Color(0, 51, 0));
		
		JLabel lblArticulo = new JLabel("Articulo (Busqueda facil)");
		lblArticulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblArticulo.setForeground(Color.WHITE);
		lblArticulo.setBackground(SystemColor.desktop);
		lblArticulo.setBounds(53, 0, 160, 14);
		pjnArticulo.add(lblArticulo);
		
		txtBusquedaArticulo = new JTextField();
		txtBusquedaArticulo.setColumns(10);
		txtBusquedaArticulo.setBounds(0, 18, 243, 20);
		pjnArticulo.add(txtBusquedaArticulo);
		
		//Metodos que debe ejecutar la ventana al inicializarse
		listarLineaArticulos();
		listarProveedores();
	}
	
	//Metodo que permite listar las lineas de articulos y desplegarlos en un combo box
	private void listarLineaArticulos() {
		DelegadoLineaArticulos delegadoLineaArticulos = new DelegadoLineaArticulos();
		cbLineaArticulos.removeAllItems();
		List<LineaArticulos> lineaArticulos = delegadoLineaArticulos.listarLineaArticulos();
		modeloLineaArticulos.addElement("--TODAS LAS LINEAS--");
		cbLineaArticulos.setModel(modeloLineaArticulos);
		
		for(LineaArticulos lineaArticulos1 : lineaArticulos){
			modeloLineaArticulos.addElement(new LineaArticulos(lineaArticulos1.getNombreL(), lineaArticulos1.getCodigo()));
			cbLineaArticulos.setModel(modeloLineaArticulos);
		}
	}
	
	//Metodo para listar los proveedores y desplegarlos en un combo box
	private void listarProveedores() {
		DelegadoProveedor delegadoProveedor = new DelegadoProveedor();
		List<Proveedor> proveedores = delegadoProveedor.listarProveedor();
		modeloProveedor.addElement("--CUALQUIER PROVEEDOR--");
		cbProveedor.setModel(modeloProveedor);
		
		for(Proveedor proveedor : proveedores){
			modeloProveedor.addElement(new Persona (proveedor.getNombre(), proveedor.getIdentificacion()));
			cbProveedor.setModel(modeloProveedor);
		}
	}
	
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbArticulos() {
		modeloTbArticulos.addColumn("Codigo");
		modeloTbArticulos.addColumn("Linea");
		modeloTbArticulos.addColumn("Articulo");
		modeloTbArticulos.addColumn("UE");
		modeloTbArticulos.addColumn("Costo Prom");
		modeloTbArticulos.addColumn("Fact. Rent");
		modeloTbArticulos.addColumn("Prov Ult Compra");
		modeloTbArticulos.addColumn("Costo Ult Compra");
		modeloTbArticulos.addColumn("Venta");
		modeloTbArticulos.addColumn("% R/C");
		modeloTbArticulos.addColumn("% R/V");
		modeloTbArticulos.addColumn("Cant existencia");
		modeloTbArticulos.addColumn("Cant alerta");
		modeloTbArticulos.addColumn("Costo Inv");
		modeloTbArticulos.addColumn("Activo");
		modeloTbArticulos.addColumn("Referencia");
		modeloTbArticulos.addColumn("Presentacion");
		modeloTbArticulos.addColumn("Descripcion");
		
		tbArticulos.setModel(modeloTbArticulos);
		
	}
	
	//Metodo para llenar la tabla cuando recibe como parametro una lista de articulos 
	private void llenarTabla( List<ControlInventario> listaRegistrosInventario) {
		
		if(tbArticulos.getModel().getColumnCount()==0){
			llenarColumnasTbArticulos();
		}
		
		String [] fila = new String[modeloTbArticulos.getColumnCount()];
		
		for(ControlInventario registrosInventario : listaRegistrosInventario){
			
			fila[0]= String.valueOf(registrosInventario.getArticulo().getCodigo());
			fila[1]= registrosInventario.getArticulo().getLineaArticulos().getNombreL();
			fila[2]= registrosInventario.getArticulo().getNombre();
			fila[3]= registrosInventario.getArticulo().getUnidadMedida().getAbreviatura();
			fila[4]= formatearNumero(registrosInventario.getCostoPromedio());
			fila[5]= String.valueOf(registrosInventario.getFactorRentabilidad());
			fila[6]= registrosInventario.getProveedorUltimaCompra();
			fila[7]= formatearNumero(registrosInventario.getCostoUltimaCompra());
			fila[8]= formatearNumero(registrosInventario.getPrecioTotalVenta());
			fila[9]= String.valueOf(registrosInventario.getRentabilidadCostoPromedio());
			fila[10]= String.valueOf(registrosInventario.getRentabilidadVenta());	
			fila[11]= String.valueOf(registrosInventario.getCantExistencia());
			fila[12]= String.valueOf(registrosInventario.getCantAlerta()); 
			fila[13]= formatearNumero(registrosInventario.getTotalCostoInventario());
			fila[14]= registrosInventario.getArticulo().getEstado();
			fila[15]= registrosInventario.getArticulo().getReferencia();
			fila[16]= registrosInventario.getArticulo().getPresentacion();
			fila[17]= registrosInventario.getArticulo().getDescripcion();
			
			modeloTbArticulos.addRow(fila);
		}
		
		tbArticulos.setModel(modeloTbArticulos);
		
		mostrarColumnaKardex(0, 70, 10, 50);
		mostrarColumnaKardex(3, 70, 10, 40);
		mostrarColumnaKardex(5, 90, 10, 70);
		mostrarColumnaKardex(9, 90, 10, 70);
		mostrarColumnaKardex(10, 90, 10, 70);
		mostrarColumnaKardex(11, 90, 10, 60);
		mostrarColumnaKardex(12, 90, 10, 60);
		mostrarColumnaKardex(14, 90, 10, 50);
		
		ocultarColumnaKardex(6);
		ocultarColumnaKardex(7);
		ocultarColumnaKardex(15);
		ocultarColumnaKardex(16);
		ocultarColumnaKardex(17);
		
		colorearFactorRentabilidadEnTabla(tbArticulos);
		colorearCantExistenciaAlertaEnTabla(tbArticulos);
		colorearPrecioPorDebajoEnTabla(tbArticulos);
		colorearCantidadAlertaEnTabla(tbArticulos);
		colorearRCEnTabla(tbArticulos);
		colorearRVEnTabla(tbArticulos);
		colorearEstadoArticuloEnTabla(tbArticulos);
	}
	
	//Metodo para convertir un numero corriente en formato de pesos y decimales
	private String formatearNumero(Double numero){
		NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es","CO"));
		return formato.format(numero); 
	}
	
	//Metodo para listar los registros de inventario de acuerdo a los parametros de estado de articulos, linea de articulos, proveedor ultima compra y busqueda por articulos
	private void listarRegistrosInventario(){
		
		DelegadoControlInventario delegadoControlInventario = new DelegadoControlInventario();
		
		sobreescribirEstadoArticulos(cbEstadoArticulos.getSelectedItem().toString());
		
		if("".equals(txtBusquedaArticulo.getText())){
		
			if(cbEstadoArticulos.getSelectedItem().equals("--TODOS LOS ARTICULOS--") && cbLineaArticulos.getSelectedItem().equals("--TODAS LAS LINEAS--") && cbProveedor.getSelectedItem().equals("--CUALQUIER PROVEEDOR--")){
				listaRegistrosInventario = delegadoControlInventario.listarControlInventario();
				limpiarRegistrosTabla();
				llenarTabla(listaRegistrosInventario);
			}else{
				if(cbEstadoArticulos.getSelectedItem().equals("--TODOS LOS ARTICULOS--") && cbLineaArticulos.getSelectedItem()!="" && cbProveedor.getSelectedItem().equals("--CUALQUIER PROVEEDOR--")){
					listaRegistrosInventario = delegadoControlInventario.traerRegistrosInventarioPorG(cbLineaArticulos.getSelectedItem().toString());
					limpiarRegistrosTabla();
					llenarTabla(listaRegistrosInventario);
				}else{
					if(cbEstadoArticulos.getSelectedItem().equals("--TODOS LOS ARTICULOS--") && cbLineaArticulos.getSelectedItem().equals("--TODAS LAS LINEAS--") && cbProveedor.getSelectedItem()!=""){
						listaRegistrosInventario = delegadoControlInventario.traerRegistrosInventarioPorP(cbProveedor.getSelectedItem().toString());
						limpiarRegistrosTabla();
						llenarTabla(listaRegistrosInventario);
					}else{
						if(cbEstadoArticulos.getSelectedItem().equals("--TODOS LOS ARTICULOS--") && cbLineaArticulos.getSelectedItem()!="" && cbProveedor.getSelectedItem()!=""){
							listaRegistrosInventario = delegadoControlInventario.traerRegistrosInventarioPorGP(cbLineaArticulos.getSelectedItem().toString(), cbProveedor.getSelectedItem().toString());
							limpiarRegistrosTabla();
							llenarTabla(listaRegistrosInventario);
						}else{
							if(cbEstadoArticulos.getSelectedItem()!="" && cbLineaArticulos.getSelectedItem().equals("--TODAS LAS LINEAS--") && cbProveedor.getSelectedItem().equals("--CUALQUIER PROVEEDOR--")){
								listaRegistrosInventario = delegadoControlInventario.traerRegistrosInventarioPorE(estadoArticulo);
								limpiarRegistrosTabla();
								llenarTabla(listaRegistrosInventario);
							}else{
								if(cbEstadoArticulos.getSelectedItem()!="" && cbLineaArticulos.getSelectedItem()!="" && cbProveedor.getSelectedItem().equals("--CUALQUIER PROVEEDOR--")){
									listaRegistrosInventario = delegadoControlInventario.traerRegistrosInventarioPorEG(estadoArticulo, cbLineaArticulos.getSelectedItem().toString());
									limpiarRegistrosTabla();
									llenarTabla(listaRegistrosInventario);
								}else{
									if(cbEstadoArticulos.getSelectedItem()!="" && cbLineaArticulos.getSelectedItem().equals("--TODAS LAS LINEAS--") && cbProveedor.getSelectedItem()!=""){
										listaRegistrosInventario = delegadoControlInventario.traerRegistrosInventarioPorEP(estadoArticulo, cbProveedor.getSelectedItem().toString());
										limpiarRegistrosTabla();
										llenarTabla(listaRegistrosInventario);
									}else{
										listaRegistrosInventario = delegadoControlInventario.traerRegistrosInventarioPorEGP(estadoArticulo, cbLineaArticulos.getSelectedItem().toString(), cbProveedor.getSelectedItem().toString());
										limpiarRegistrosTabla();
										llenarTabla(listaRegistrosInventario);
									}
								}
							}
						}
					}
				}

			}
		}else{
			
			if(cbEstadoArticulos.getSelectedItem().equals("--TODOS LOS ARTICULOS--") && cbLineaArticulos.getSelectedItem().equals("--TODAS LAS LINEAS--") && cbProveedor.getSelectedItem().equals("--CUALQUIER PROVEEDOR--")){
				listaRegistrosInventario = delegadoControlInventario.traerRegistrosInventarioPorB(txtBusquedaArticulo.getText());
				limpiarRegistrosTabla();
				llenarTabla(listaRegistrosInventario);
			}else{
				if(cbEstadoArticulos.getSelectedItem().equals("--TODOS LOS ARTICULOS--") && cbLineaArticulos.getSelectedItem()!="" && cbProveedor.getSelectedItem().equals("--CUALQUIER PROVEEDOR--")){
					listaRegistrosInventario = delegadoControlInventario.traerRegistrosInventarioPorBG(txtBusquedaArticulo.getText(),cbLineaArticulos.getSelectedItem().toString());
					limpiarRegistrosTabla();
					llenarTabla(listaRegistrosInventario);
				}else{
					if(cbEstadoArticulos.getSelectedItem().equals("--TODOS LOS ARTICULOS--") && cbLineaArticulos.getSelectedItem().equals("--TODAS LAS LINEAS--") && cbProveedor.getSelectedItem()!=""){
						listaRegistrosInventario = delegadoControlInventario.traerRegistrosInventarioPorBP(txtBusquedaArticulo.getText(),cbProveedor.getSelectedItem().toString());
						limpiarRegistrosTabla();
						llenarTabla(listaRegistrosInventario);
					}else{
						if(cbEstadoArticulos.getSelectedItem().equals("--TODOS LOS ARTICULOS--") && cbLineaArticulos.getSelectedItem()!="" && cbProveedor.getSelectedItem()!=""){
							listaRegistrosInventario = delegadoControlInventario.traerRegistrosInventarioPorBGP(txtBusquedaArticulo.getText(),cbLineaArticulos.getSelectedItem().toString(), cbProveedor.getSelectedItem().toString());
							limpiarRegistrosTabla();
							llenarTabla(listaRegistrosInventario);
						}else{
							if(cbEstadoArticulos.getSelectedItem()!="" && cbLineaArticulos.getSelectedItem().equals("--TODAS LAS LINEAS--") && cbProveedor.getSelectedItem().equals("--CUALQUIER PROVEEDOR--")){
								listaRegistrosInventario = delegadoControlInventario.traerRegistrosInventarioPorBE(txtBusquedaArticulo.getText(),estadoArticulo);
								limpiarRegistrosTabla();
								llenarTabla(listaRegistrosInventario);
							}else{
								if(cbEstadoArticulos.getSelectedItem()!="" && cbLineaArticulos.getSelectedItem()!="" && cbProveedor.getSelectedItem().equals("--CUALQUIER PROVEEDOR--")){
									listaRegistrosInventario = delegadoControlInventario.traerRegistrosInventarioPorBEG(txtBusquedaArticulo.getText(),estadoArticulo, cbLineaArticulos.getSelectedItem().toString());
									limpiarRegistrosTabla();
									llenarTabla(listaRegistrosInventario);
								}else{
									if(cbEstadoArticulos.getSelectedItem()!="" && cbLineaArticulos.getSelectedItem().equals("--TODAS LAS LINEAS--") && cbProveedor.getSelectedItem()!=""){
										listaRegistrosInventario = delegadoControlInventario.traerRegistrosInventarioPorBEP(txtBusquedaArticulo.getText(),estadoArticulo, cbProveedor.getSelectedItem().toString());
										limpiarRegistrosTabla();
										llenarTabla(listaRegistrosInventario);
									}else{
										listaRegistrosInventario = delegadoControlInventario.traerRegistrosInventarioPorBEGP(txtBusquedaArticulo.getText(),estadoArticulo, cbLineaArticulos.getSelectedItem().toString(), cbProveedor.getSelectedItem().toString());
										limpiarRegistrosTabla();
										llenarTabla(listaRegistrosInventario);
									}
								}
							}
						}
					}
				}
			}
		}
		calcularNroArticulosInventario();
		calcularNroLineasInventario();
		calcularCantidadArticulosInventario();
		calcularCostoInventario();
		calcularCantidadArticulosEnAlerta();
		calcularCostoMayorAPrecio();
	}
	//Metofdo para sobrrescirbir el estado de los articulos
	private void sobreescribirEstadoArticulos(String estado) {
		
		if(estado.equals("CON PRECIO VENTA < FACTOR RENTABILIDAD")){
			estadoArticulo = "ci.precioTotalVenta < (ci.factorRentabilidad * ci.costoPromedio)";
		}
		
		if(estado.equals("POR DEBAJO DEL SALDO MINIMO")){
			estadoArticulo = "ci.cantExistencia < ci.cantAlerta";
		}
		
		if(estado.equals("CON PRECIO DE VENTA INFERIOR AL COSTO")){
			estadoArticulo = "ci.precioTotalVenta < ci.costoPromedio";
		}
		
		if(estado.equals("ARTICULOS DESHABILITADOS")){
			estadoArticulo = "a.estado = \"Inactivo\"";
		}
	}

	//Metodo para limpiar la tabla 
	private void limpiarRegistrosTabla(){
	   for (int i = 0; i < tbArticulos.getRowCount(); i++) {
	       modeloTbArticulos.removeRow(i);
	       i-=1;
	   }
	}
	
	//Metodo que permite habilitar o deshabilitar los checkbox
	public void habilitarODeshabilitarCheckBox(){
		
		if(tbArticulos.getRowCount() == 0){
			chArticulosExistencias.setEnabled(false);
			chDatosOpcionales.setEnabled(false);
			chUltimaCompra.setEnabled(false);
		}else{
			chArticulosExistencias.setEnabled(true);
			chDatosOpcionales.setEnabled(true);
			chUltimaCompra.setEnabled(true);
		}
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
	//Metodo para mostrar los articulos con existencia al activar el checkbox
	private void mostrarArticulosConExistencias() {
		DelegadoControlInventario delegadoControlInventario = new DelegadoControlInventario();
		List<ControlInventario> registrosConExistencias = delegadoControlInventario.traerRegistrosInventarioConExistencias();
		if(chArticulosExistencias.isSelected()){
			limpiarRegistrosTabla();
			llenarTabla(registrosConExistencias);
			
			calcularNroArticulosInventario();
			calcularNroLineasInventario();
			calcularCantidadArticulosInventario();
			calcularCostoInventario();
			calcularCantidadArticulosEnAlerta();
			calcularCostoMayorAPrecio();
		}else{
			listarRegistrosInventario();
		}
		
	}
	
	//Metodo para mostrar una especie de menu al interior de la tabla
	private void mostrarPopupTbArticulos(){
		JPopupMenu pmArticulos = new JPopupMenu();
		JMenuItem miModNom = new JMenuItem("Modificar Nombre del Articulo");
		JMenuItem miFactRent = new JMenuItem("Modificar Factor Rentabilidad");
		JMenuItem miPrecioVenta = new JMenuItem("Modificar Precio de Venta");
		JMenuItem miCantAlerta = new JMenuItem("Modificar Cantidad Minima para generar Alerta");
		JMenuItem miEstado = new JMenuItem("Activar o Inactivar Articulo");
		JMenuItem miFoto = new JMenuItem("Colocar Foto para el Articulo");
		
		pmArticulos.add(miModNom);
		miModNom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarNombreArticulo();
			}
		});
		
		pmArticulos.add(miFactRent);
		miFactRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarFactorRentabilidad();
			}
		});
		
		pmArticulos.add(miPrecioVenta);
		miPrecioVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarPrecioVenta();
			}
		});
		
		pmArticulos.add(miCantAlerta);
		miCantAlerta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarCantidadAlerta();
			}
		});
		
		pmArticulos.add(miEstado);
		miEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarEstadoArticulo();
			}
		});
		
		pmArticulos.add(miFoto);
		miFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colocarFotoArticulo();
			}
		});
			
		tbArticulos.setComponentPopupMenu(pmArticulos);	
	}
	
	//Metodo para colocar una imagen o foto del articulo
	private void colocarFotoArticulo() {
		VentColocarFotoArticulo ventColocarFotoArticulo = new VentColocarFotoArticulo(Integer.parseInt(tbArticulos.getValueAt(filaSeleccionada, 0).toString()));
		ventColocarFotoArticulo.setVisible(true);
		
	}
	//Metodo para modificar el nombre del articulo
	private void modificarNombreArticulo() {
		VentModificarNombreArticulo ventModificarNombreArticulo= new VentModificarNombreArticulo(tbArticulos.getValueAt(filaSeleccionada, 0).toString());
		ventModificarNombreArticulo.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosed(WindowEvent e) {
	            listarRegistrosInventario();  
	        }
	    });
		ventModificarNombreArticulo.setVisible(true);
	}
	//Metodo para abrir la ventana de modificacion y actualizar las rentabiliadades en el inventario
	private void modificarFactorRentabilidad() {
		VentModificarRentabilidades ventModificarRentabilidades= new VentModificarRentabilidades(tbArticulos.getValueAt(filaSeleccionada, 0).toString());
		ventModificarRentabilidades.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosed(WindowEvent e) {
	            listarRegistrosInventario();  
	        }
	    });
		ventModificarRentabilidades.setVisible(true);
	}
	//Metodo para abrir la ventana de modificacion y actualizar el precio de venta
	private void modificarPrecioVenta() {
		VentModificarPrecioVenta ventModificarPrecioVenta= new VentModificarPrecioVenta(tbArticulos.getValueAt(filaSeleccionada, 0).toString());
		ventModificarPrecioVenta.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosed(WindowEvent e) {
	            listarRegistrosInventario();  
	        }
	    });
		ventModificarPrecioVenta.setVisible(true);
	}
	//Metodo para modificar la cantidad minima de existencia del articulo para generar una alerta
	private void modificarCantidadAlerta() {
		VentModificarCantidadAlerta ventModificarCantidadAlerta= new VentModificarCantidadAlerta(tbArticulos.getValueAt(filaSeleccionada, 0).toString());
		ventModificarCantidadAlerta.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosed(WindowEvent e) {
	            listarRegistrosInventario();  
	        }
	    });
		ventModificarCantidadAlerta.setVisible(true);
	}
	//Metodo para modificar el estado del articulo
	private void modificarEstadoArticulo() {
		DelegadoArticulo delegadoArticulo= new DelegadoArticulo();
		articuloElegidoInventario = delegadoArticulo.traerLineaUnidadArticuloPorCodigo(Integer.parseInt(tbArticulos.getValueAt(filaSeleccionada, 0).toString()));
		String estado="";
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de cambiar el estado "+articuloElegidoInventario.get(0).getEstado()+" del articulo", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			if("Activo".equals(articuloElegidoInventario.get(0).getEstado())){
				estado = "Inactivo";
			}else{
				estado = "Activo";
			}
			
			Articulo articuloAModificarNombre;
			articuloAModificarNombre = articuloElegidoInventario.get(0);
			
			articuloAModificarNombre.setEstado(estado);
			delegadoArticulo.actualizarArticulo(articuloAModificarNombre);
			listarRegistrosInventario();
		}else{
		
		}
	}
	
	//Metodo para colorear la columna del factor de rentabilidad
	private void colorearFactorRentabilidadEnTabla(JTable tablaArticulos) {
		tablaArticulos.getColumn("Fact. Rent").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			comp.setBackground(new Color(204, 255, 204));
	        return comp; 
			}}); 
	}
	
	//Metodo para colorear rojo la columna del la cantidad de existencia en la que hay una alerta por escasez
	private void colorearCantExistenciaAlertaEnTabla(JTable tablaArticulos) {
		tablaArticulos.getColumn("Cant existencia").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			if(Integer.parseInt(String.valueOf(table.getValueAt(row, 11)))< Integer.parseInt(String.valueOf(table.getValueAt(row, 12)))){
				comp.setBackground(new Color(204, 102, 102));
			}else{
				comp.setBackground(new Color(204, 204, 255));
			}
	        return comp; 
			}}); 
	}
	
	//Metodo para colorear rojo la columna del precio de venta que este por debajo del costo promedio * el factor de rentabilidad
	private void colorearPrecioPorDebajoEnTabla(JTable tablaArticulos) {
		tablaArticulos.getColumn("Venta").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			if(Double.parseDouble(desformatearNumero(String.valueOf(table.getValueAt(row, 4))))*Float.parseFloat(String.valueOf(table.getValueAt(row, 5)))> Double.parseDouble(desformatearNumero(String.valueOf(table.getValueAt(row, 8))))){
				comp.setBackground(new Color(204, 102, 102));
			}else{
				comp.setBackground(new Color(153, 204, 255));
			}
	        return comp; 
			}}); 
	}
	
	//Metodo para colorear la columna de cant alerta
	private void colorearCantidadAlertaEnTabla(JTable tablaArticulos) {
		tablaArticulos.getColumn("Cant alerta").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			comp.setBackground(new Color(255, 255, 204));
	        return comp; 
			}}); 
	}
	
	//Metodo para colorear la columna RV
	private void colorearRVEnTabla(JTable tablaArticulos) {
		tablaArticulos.getColumn("% R/V").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			comp.setBackground(new Color(153, 204, 255));
	        return comp; 
			}}); 
	}
	
	//Metodo para colorear la columna RC
	private void colorearRCEnTabla(JTable tablaArticulos) {
		tablaArticulos.getColumn("% R/C").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			comp.setBackground(new Color(153, 204, 255));
	        return comp; 
			}}); 
	}
	
	//Metodo para colorear la columna del factor de rentabilidad
	private void colorearEstadoArticuloEnTabla(JTable tablaArticulos) {
		tablaArticulos.getColumn("Activo").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			comp.setBackground(new Color(204, 255, 204));
	        return comp; 
			}}); 
	}
	
	//Metodo para convertir un numero en formato de pesos y decimales en un numero corriente
	private String desformatearNumero(String numero){
		String numeroReemplazado=numero.replace("$", "");
		String numeroReemplazado1=numeroReemplazado.replace(".", "");
		String numeroReemplazado2=numeroReemplazado1.replace(",", ".");
		return numeroReemplazado2;
	}
	//Metodo para calcular el nro de articulos activos en el inventario
	private void calcularNroArticulosInventario(){
		int nroArticulos =0;
		for(int i=0; i<tbArticulos.getRowCount(); i++){
			if(tbArticulos.getValueAt(i, 14).equals("Activo")){
				nroArticulos++;
			}
		}
		txtNroArticulos.setText(Integer.toString(nroArticulos));
	}
	
	//Metodo para calcular el nro de articulos activos en el inventario
	private void calcularNroLineasInventario(){
		DelegadoLineaArticulos delegadoLineaArticulos = new DelegadoLineaArticulos();
		List<LineaArticulos> lineas = delegadoLineaArticulos.listarLineaArticulos();
		
		int nroLineas=0;
		
		for(int j=0; j<lineas.size();j++){
			for(int i=0; i<tbArticulos.getRowCount(); i++){
				if(tbArticulos.getValueAt(i, 14).equals("Activo")&&tbArticulos.getValueAt(i, 1).equals(lineas.get(j).getNombreL())){
					nroLineas++;
					break;
				}
			}
		}
		
		txtNroGrupos.setText(Integer.toString(nroLineas));
	}
	
	//Metodo para calcular el nro de articulos activos en el inventario
	private void calcularCantidadArticulosInventario(){;
		int sumatoriaCantidad =0;
		for(int i=0; i<tbArticulos.getRowCount(); i++){
			if(tbArticulos.getValueAt(i, 14).equals("Activo")){
				sumatoriaCantidad +=Integer.parseInt(tbArticulos.getValueAt(i, 11).toString());
			}
		}
		txtCantidad.setText(Integer.toString(sumatoriaCantidad));
	}
	
	//Metodo para calcular el acumulado del costo del inventario total
	private void calcularCostoInventario(){
		double sumatoriaCostoInventario = 0;
		
		for(int i=0; i<tbArticulos.getRowCount(); i++){
			if(tbArticulos.getValueAt(i, 14).equals("Activo")){
				sumatoriaCostoInventario +=Double.parseDouble((desformatearNumero(tbArticulos.getValueAt(i, 13).toString())));
			}
		}
		txtCostoInv.setText(formatearNumero((sumatoriaCostoInventario)));
	}
	
	//Metodo para calcular el numero de articulos que estan en alerta por escases
	private void calcularCantidadArticulosEnAlerta(){
		int nroArticulosAlerta = 0;
		
		for(int i=0; i<tbArticulos.getRowCount(); i++){
			if(tbArticulos.getValueAt(i, 14).equals("Activo") && Integer.parseInt(String.valueOf(tbArticulos.getValueAt(i, 11)))< Integer.parseInt(String.valueOf(tbArticulos.getValueAt(i, 12)))){
				nroArticulosAlerta++;
			}
		}
		txtEnAlerta.setText(String.valueOf(nroArticulosAlerta));
	}
	
	//Metodo para calcular el numero de articulos que tiene un costo promedio mayor al precio de venta
	private void calcularCostoMayorAPrecio(){
		int nroArticulosCostoMayorAPrecio = 0;
		
		for(int i=0; i<tbArticulos.getRowCount(); i++){
			if(tbArticulos.getValueAt(i, 14).equals("Activo") && Double.parseDouble(desformatearNumero(tbArticulos.getValueAt(i, 4).toString()))> Double.parseDouble(desformatearNumero(tbArticulos.getValueAt(i, 8).toString()))){
				nroArticulosCostoMayorAPrecio++;
			}
		}
		txtCostoVenta.setText(String.valueOf(nroArticulosCostoMayorAPrecio));
	}
}
