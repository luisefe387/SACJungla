package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import co.com.jungla.sac.persistencia.entidades.Articulo;
import co.com.jungla.sac.persistencia.entidades.CompraArticulos;
import co.com.jungla.sac.persistencia.entidades.Contabilizacion;
import co.com.jungla.sac.persistencia.entidades.ControlInventario;
import co.com.jungla.sac.persistencia.entidades.CuentaXPagar;
import co.com.jungla.sac.persistencia.entidades.DetalleCompra;
import co.com.jungla.sac.persistencia.entidades.DetalleOrdenCompra;
import co.com.jungla.sac.persistencia.entidades.MedioPagoProv;
import co.com.jungla.sac.persistencia.entidades.Egreso;
import co.com.jungla.sac.persistencia.entidades.KardexElectronico;
import co.com.jungla.sac.persistencia.entidades.OrdenCompraArticulos;
import co.com.jungla.sac.persistencia.entidades.Persona;
import co.com.jungla.sac.persistencia.entidades.Proveedor;

import co.com.jungla.sac.negocio.delegados.DelegadoArticulo;
import co.com.jungla.sac.negocio.delegados.DelegadoCompraArticulos;
import co.com.jungla.sac.negocio.delegados.DelegadoContabilizacion;
import co.com.jungla.sac.negocio.delegados.DelegadoControlInventario;
import co.com.jungla.sac.negocio.delegados.DelegadoCuentaXPagar;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleCompra;
import co.com.jungla.sac.negocio.delegados.DelegadoEgreso;
import co.com.jungla.sac.negocio.delegados.DelegadoKardexElectronico;
import co.com.jungla.sac.negocio.delegados.DelegadoMedioPagoProv;
import co.com.jungla.sac.negocio.delegados.DelegadoOrdenCompraArticulos;
import co.com.jungla.sac.negocio.delegados.DelegadoProveedor;
import com.mxrck.autocompleter.TextAutoCompleter;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro de la compra de articulos, su movimiento de compra en el kardex electronico y su contabilizacion
 * @author Luis Fernando Pedroza T.
 * @version: 19/09/2016
 */
public class VentRegistrarCompraArticulos extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtArticulo;
	private JTextField txtLinea;
	private JTextField txtUe;
	private JTextField txtCant;
	private JTextField txtFactProv;
	private JTextField txtOrdenCompra;
	private JTextField txtSaldo;
	private JTextField txtSubtotal;
	private JTextField txtDctoCom;
	private JTextField txtTotalcompra;
	private JTextField txtItem;
	private JFormattedTextField txtCosto;
	private JFormattedTextField txtUltimoCosto;
	private JLabel lblObservaciones;
	private JPanel pnUe;
	private JPanel pnFormaPago;
	private JPanel pnEstadoPago;
	private JPanel pnFechaCausacion;
	private JPanel pnFechaPago;
	private JLabel lblEstadoPago;
	private JLabel lblFormaPago;
	private JLabel lblFechaCausacion;
	private JLabel lblFechaPago;
	private JRadioButton rdbtnCancelado;
	private JRadioButton rdbtnCuentaPagar;
	private JTextPane txaObservaciones;
	private JTable tbArticulos;
	private JComboBox cbProveedor;
	private JComboBox cbFormaPago;
	private JDateChooser dchFechaE;
	private JDateChooser dchFechaCausacion;
	private JDateChooser dchFechaPago;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private DefaultComboBoxModel modeloFormaPago = new DefaultComboBoxModel();
	private DefaultComboBoxModel modeloProveedor = new DefaultComboBoxModel();
	private DefaultTableModel modeloTbArticulos = new DefaultTableModel();
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private List<CompraArticulos> codigoCompra;
	private String idProveedor;
	private List<DetalleCompra> ultimoDetalleCompra;
	private CompraArticulos compraArticulos;
	private DelegadoCompraArticulos delegadoCompraArticulos = new DelegadoCompraArticulos();
	private DelegadoKardexElectronico delegadoKardexElectronico = new DelegadoKardexElectronico();
	private Proveedor proveedores;
	private Double subtotal;
	private List <KardexElectronico> ultimoRegistroPorArticuloKardex1;
	private KardexElectronico ultimoRegistroPorArticuloKardex;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentRegistrarCompraArticulos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarCompraArticulos.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Compra de Articulos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 1057, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 1027, 62);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JPanel pnProveedor = new JPanel();
		pnProveedor.setBounds(228, 11, 250, 38);
		pn1.add(pnProveedor);
		pnProveedor.setBackground(new Color(0, 51, 0));
		pnProveedor.setLayout(null);
		
		//Funcionalidad para crear el proveedor al desplegar el combo box
		cbProveedor = new JComboBox();
		cbProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearProveedor();
			}
		});
		cbProveedor.setBounds(0, 16, 250, 22);
		pnProveedor.add(cbProveedor);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProveedor.setBackground(SystemColor.desktop);
		lblProveedor.setForeground(SystemColor.window);
		lblProveedor.setBounds(94, 0, 73, 14);
		pnProveedor.add(lblProveedor);
		
		JPanel pnFechaE = new JPanel();
		pnFechaE.setLayout(null);
		pnFechaE.setBackground(new Color(0, 51, 0));
		pnFechaE.setBounds(662, 11, 145, 38);
		pn1.add(pnFechaE);
		
		JLabel lblFechaE = new JLabel("Fecha de Entrega");
		lblFechaE.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaE.setForeground(Color.WHITE);
		lblFechaE.setBackground(SystemColor.desktop);
		lblFechaE.setBounds(27, 0, 111, 14);
		pnFechaE.add(lblFechaE);
		
		dchFechaE = new JDateChooser();
		dchFechaE.setBounds(0, 18, 144, 20);
		pnFechaE.add(dchFechaE);
		
		JPanel pnFacProv = new JPanel();
		pnFacProv.setLayout(null);
		pnFacProv.setBackground(new Color(0, 51, 0));
		pnFacProv.setBounds(488, 11, 77, 38);
		pn1.add(pnFacProv);
		
		JLabel lblFactProv = new JLabel("Fact. Prov.");
		lblFactProv.setForeground(Color.WHITE);
		lblFactProv.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFactProv.setBackground(SystemColor.desktop);
		lblFactProv.setBounds(10, 0, 67, 14);
		pnFacProv.add(lblFactProv);
		
		txtFactProv = new JTextField();
		txtFactProv.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validarFactProv();
			}
		});
		txtFactProv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarFactProv();
			}
		});
		txtFactProv.setColumns(10);
		txtFactProv.setDocument(new LimitadorCaracteres());
		txtFactProv.setBounds(0, 18, 77, 20);
		pnFacProv.add(txtFactProv);
		
		JPanel pnOrdenCompra = new JPanel();
		pnOrdenCompra.setLayout(null);
		pnOrdenCompra.setBackground(new Color(0, 51, 0));
		pnOrdenCompra.setBounds(575, 11, 77, 38);
		pn1.add(pnOrdenCompra);
		
		JLabel lblOrdenCompra = new JLabel("Ord. Compra");
		lblOrdenCompra.setForeground(Color.WHITE);
		lblOrdenCompra.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrdenCompra.setBackground(new Color(0, 0, 0));
		lblOrdenCompra.setBounds(3, 0, 77, 14);
		pnOrdenCompra.add(lblOrdenCompra);
		
		txtOrdenCompra = new JTextField();
		txtOrdenCompra.setColumns(10);
		txtOrdenCompra.setDocument(new LimitadorCaracteres());
		txtOrdenCompra.setBounds(0, 18, 77, 20);
		pnOrdenCompra.add(txtOrdenCompra);
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(null);
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 84, 1027, 62);
		contentPane.add(pn2);
		
		JPanel pnCodigo = new JPanel();
		pnCodigo.setLayout(null);
		pnCodigo.setBackground(new Color(0, 51, 0));
		pnCodigo.setBounds(10, 11, 77, 38);
		pn2.add(pnCodigo);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCodigo.setForeground(Color.WHITE);
		lblCodigo.setBackground(SystemColor.desktop);
		lblCodigo.setBounds(22, 0, 55, 14);
		pnCodigo.add(lblCodigo);
		
		txtCodigo = new JTextField();
		//Evento de teclado que permite traer la linea a la que pertenece el articulo mediante su codigo
		//al presionar enter o tab
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_TAB ){
					cargarDatosArticuloPorCodigo();
				}
			}
		});
		txtCodigo.setBounds(0, 18, 77, 20);
		pnCodigo.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JPanel pnArticulo = new JPanel();
		pnArticulo.setLayout(null);
		pnArticulo.setBackground(new Color(0, 51, 0));
		pnArticulo.setBounds(97, 11, 254, 38);
		pn2.add(pnArticulo);
		
		JLabel lblArticulo = new JLabel("Articulo (Busqueda facil)");
		lblArticulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblArticulo.setForeground(Color.WHITE);
		lblArticulo.setBackground(SystemColor.desktop);
		lblArticulo.setBounds(59, 0, 160, 14);
		pnArticulo.add(lblArticulo);
		
		txtArticulo = new JTextField();
		//Evento de teclado que permite traer la linea a la que pertenece el articulo mediante su nombre
		//al presionar enter o tab
		txtArticulo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_TAB ){
					cargarDatosArticuloPorNombre();
				}
			}

		});
		txtArticulo.setBounds(0, 18, 254, 20);
		pnArticulo.add(txtArticulo);
		txtArticulo.setColumns(10);
		
		JPanel pnLinea = new JPanel();
		pnLinea.setBounds(361, 11, 155, 38);
		pn2.add(pnLinea);
		pnLinea.setLayout(null);
		pnLinea.setBackground(new Color(0, 51, 0));
		
		JLabel lblLinea = new JLabel("Linea");
		lblLinea.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLinea.setForeground(Color.WHITE);
		lblLinea.setBackground(SystemColor.desktop);
		lblLinea.setBounds(59, 0, 52, 14);
		pnLinea.add(lblLinea);
		
		txtLinea = new JTextField();
		txtLinea.setColumns(10);
		txtLinea.setBounds(0, 18, 155, 20);
		txtLinea.setEditable(false);
		pnLinea.add(txtLinea);
		
		pnUe = new JPanel();
		pnUe.setLayout(null);
		pnUe.setBackground(new Color(0, 51, 0));
		pnUe.setBounds(526, 11, 37, 38);
		pn2.add(pnUe);
		
		JLabel lblUe = new JLabel("UE");
		lblUe.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUe.setForeground(Color.WHITE);
		lblUe.setBackground(SystemColor.desktop);
		lblUe.setBounds(10, 0, 27, 14);
		pnUe.add(lblUe);
		
		txtUe = new JTextField();
		txtUe.setColumns(10);
		txtUe.setBounds(0, 18, 37, 20);
		txtUe.setEditable(false);
		pnUe.add(txtUe);
		
		JPanel pnCant = new JPanel();
		pnCant.setLayout(null);
		pnCant.setBackground(new Color(0, 51, 0));
		pnCant.setBounds(573, 11, 53, 38);
		pn2.add(pnCant);
		
		JLabel lblCant = new JLabel("Cant");
		lblCant.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCant.setForeground(Color.WHITE);
		lblCant.setBackground(SystemColor.desktop);
		lblCant.setBounds(10, 0, 43, 14);
		pnCant.add(lblCant);
		
		txtCant = new JTextField();
		txtCant.setColumns(10);
		txtCant.setBounds(0, 18, 53, 20);
		txtCant.setText("1");
		pnCant.add(txtCant);
		
		JPanel pnCostoTotal = new JPanel();
		pnCostoTotal.setLayout(null);
		pnCostoTotal.setBackground(new Color(0, 51, 0));
		pnCostoTotal.setBounds(636, 11, 110, 38);
		pn2.add(pnCostoTotal);
		
		JLabel lblCostoTotal = new JLabel("Costo");
		lblCostoTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCostoTotal.setForeground(Color.WHITE);
		lblCostoTotal.setBackground(SystemColor.desktop);
		lblCostoTotal.setBounds(40, 0, 60, 14);
		pnCostoTotal.add(lblCostoTotal);
		
		txtCosto = new JFormattedTextField();
		txtCosto.setValue(0);
		formatearAMoneda(txtCosto);
		txtCosto.setBounds(0, 18, 110, 20);
		pnCostoTotal.add(txtCosto);
		
		JPanel pnSaldo = new JPanel();
		pnSaldo.setLayout(null);
		pnSaldo.setBackground(new Color(0, 51, 0));
		pnSaldo.setBounds(876, 11, 79, 38);
		pn2.add(pnSaldo);
		
		JLabel lblSaldo = new JLabel("Saldo");
		lblSaldo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSaldo.setForeground(Color.WHITE);
		lblSaldo.setBackground(SystemColor.desktop);
		lblSaldo.setBounds(24, 0, 60, 14);
		pnSaldo.add(lblSaldo);
		
		txtSaldo = new JTextField();
		txtSaldo.setEditable(false);
		txtSaldo.setColumns(10);
		txtSaldo.setBounds(0, 18, 79, 20);
		pnSaldo.add(txtSaldo);
		
		//Boton para validar y agregar los articulos buscados en la tabla
		JButton btnAgregarArticulo = new JButton("+");
		btnAgregarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarIngresoArticuloATbArticulos();
			}
		});
		btnAgregarArticulo.setBounds(965, 11, 51, 38);
		pn2.add(btnAgregarArticulo);
		
		JPanel pnUltimoCosto = new JPanel();
		pnUltimoCosto.setLayout(null);
		pnUltimoCosto.setBackground(new Color(0, 51, 0));
		pnUltimoCosto.setBounds(756, 11, 110, 38);
		pn2.add(pnUltimoCosto);
		
		JLabel lblUltimoCosto = new JLabel("Ultimo Costo");
		lblUltimoCosto.setForeground(Color.WHITE);
		lblUltimoCosto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUltimoCosto.setBackground(SystemColor.desktop);
		lblUltimoCosto.setBounds(18, 0, 90, 14);
		pnUltimoCosto.add(lblUltimoCosto);
		
		txtUltimoCosto = new JFormattedTextField();
		txtUltimoCosto.setEditable(false);
		txtUltimoCosto.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
		txtUltimoCosto.setBounds(0, 18, 110, 20);
		pnUltimoCosto.add(txtUltimoCosto);
		
		JPanel pn3 = new JPanel();
		pn3.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn3.setBounds(10, 161, 1027, 166);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JScrollPane scrArticulos = new JScrollPane();
		scrArticulos.setBounds(2, 2, 1023, 162);
		pn3.add(scrArticulos);
		
		tbArticulos = new JTable(modeloTbArticulos){
			//metodo que permite la no edicion de las columnas 3(Costo) y 4(Total)
			public boolean isCellEditable(int rowIndex, int colIndex) {
				if (colIndex==3) {
			        return true;
			    }
				if (colIndex==4) {
			        return true;
			    }
	            return false;
	        }
		}
		;
		scrArticulos.setViewportView(tbArticulos);
      		
		JPanel pn4 = new JPanel();
		pn4.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn4.setBounds(10, 456, 1027, 62);
		contentPane.add(pn4);
		pn4.setLayout(null);
		
		JPanel pnSubtotal = new JPanel();
		pnSubtotal.setLayout(null);
		pnSubtotal.setBackground(new Color(0, 51, 0));
		pnSubtotal.setBounds(155, 11, 104, 38);
		pn4.add(pnSubtotal);
		
		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSubtotal.setForeground(Color.WHITE);
		lblSubtotal.setBackground(SystemColor.desktop);
		lblSubtotal.setBounds(32, 0, 60, 14);
		pnSubtotal.add(lblSubtotal);
		
		txtSubtotal = new JTextField();
		txtSubtotal.setColumns(10);
		txtSubtotal.setText(formatearNumero(0.0));
		txtSubtotal.setBounds(0, 18, 104, 20);
		txtSubtotal.setEditable(false);
		pnSubtotal.add(txtSubtotal);
		
		JPanel pnDctoCom = new JPanel();
		pnDctoCom.setLayout(null);
		pnDctoCom.setBackground(new Color(0, 51, 0));
		pnDctoCom.setBounds(269, 11, 104, 38);
		pn4.add(pnDctoCom);
		
		JLabel lblDctoCom = new JLabel("Dcto Com.");
		lblDctoCom.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDctoCom.setForeground(Color.WHITE);
		lblDctoCom.setBackground(SystemColor.desktop);
		lblDctoCom.setBounds(32, 0, 60, 14);
		pnDctoCom.add(lblDctoCom);
		
		txtDctoCom = new JTextField();
		txtDctoCom.addKeyListener(new KeyAdapter() {
			//Evento de teclado en la que calcula el descuento de los articulos registrados en la tabla
			//al presionar enter o tab
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_TAB ){
					calcularTotalCompra();
				}
			}
		});
		txtDctoCom.addMouseListener(new MouseAdapter() {
			//Eventos de mouse donde al hacer clic calcule el descuento de los articulos registrados
			@Override
			public void mousePressed(MouseEvent e) {
				calcularTotalCompra();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				calcularTotalCompra();
			}
		});
		
		txtDctoCom.setColumns(10);
		txtDctoCom.setBounds(0, 18, 86, 20);
		txtDctoCom.setText("0.0");
		pnDctoCom.add(txtDctoCom);
		
		JLabel lbPorcentaje = new JLabel("%");
		lbPorcentaje.setBounds(87, 17, 17, 22);
		pnDctoCom.add(lbPorcentaje);
		
		JPanel pnTotalCompra = new JPanel();
		pnTotalCompra.setLayout(null);
		pnTotalCompra.setBackground(new Color(0, 51, 0));
		pnTotalCompra.setBounds(383, 11, 104, 38);
		pn4.add(pnTotalCompra);
		
		JLabel lblTotalCompra = new JLabel("Total Compra");
		lblTotalCompra.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalCompra.setForeground(Color.WHITE);
		lblTotalCompra.setBackground(SystemColor.desktop);
		lblTotalCompra.setBounds(15, 0, 84, 14);
		pnTotalCompra.add(lblTotalCompra);
		
		txtTotalcompra = new JTextField();
		txtTotalcompra.setText(formatearNumero(0.0));
		txtTotalcompra.setBackground(new Color(153, 153, 102));
		txtTotalcompra.setForeground(new Color(152, 251, 152));
		txtTotalcompra.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTotalcompra.setColumns(10);
		txtTotalcompra.setBounds(0, 18, 104, 20);
		txtTotalcompra.setEditable(false);
		pnTotalCompra.add(txtTotalcompra);
		
		JPanel pnItem = new JPanel();
		pnItem.setLayout(null);
		pnItem.setBackground(new Color(0, 51, 0));
		pnItem.setBounds(497, 11, 60, 38);
		pn4.add(pnItem);
		
		JLabel lblItem = new JLabel("Item");
		lblItem.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblItem.setForeground(Color.WHITE);
		lblItem.setBackground(SystemColor.desktop);
		lblItem.setBounds(16, 0, 49, 14);
		pnItem.add(lblItem);
		
		txtItem = new JTextField();
		txtItem.setText("0");
		txtItem.setColumns(10);
		txtItem.setBounds(0, 18, 60, 20);
		txtItem.setEditable(false);
		pnItem.add(txtItem);
		
		//Funcionalidad del boton quitar articulo en la que elimina los articulos seleccionados
		JButton btnQuitarArticulo = new JButton("Quitar Articulo");
		btnQuitarArticulo.setForeground(new Color(0, 51, 0));
		btnQuitarArticulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuitarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarArticuloATbArticulos();
			}
		});
		btnQuitarArticulo.setBounds(590, 20, 117, 23);
		pn4.add(btnQuitarArticulo);
		
		JButton btnGuardarCompra = new JButton("Registrar Compra");
		btnGuardarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
				
			}	
		});
		btnGuardarCompra.setForeground(new Color(255, 0, 0));
		btnGuardarCompra.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGuardarCompra.setBounds(738, 20, 140, 23);
		pn4.add(btnGuardarCompra);
		
		JPanel pn5 = new JPanel();
		pn5.setLayout(null);
		pn5.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn5.setBounds(10, 338, 1027, 107);
		contentPane.add(pn5);
		
		txaObservaciones = new JTextPane();
		txaObservaciones.setBounds(196, 28, 279, 68);
		pn5.add(txaObservaciones);
		
		lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaciones.setBounds(196, 11, 92, 14);
		pn5.add(lblObservaciones);
		
		pnFechaPago = new JPanel();
		pnFechaPago.setLayout(null);
		pnFechaPago.setBackground(new Color(0, 51, 0));
		pnFechaPago.setBounds(665, 58, 145, 38);
		pn5.add(pnFechaPago);
		
		lblFechaPago = new JLabel("Fecha de Pago");
		lblFechaPago.setForeground(Color.WHITE);
		lblFechaPago.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaPago.setBackground(Color.BLACK);
		lblFechaPago.setBounds(33, 0, 105, 14);
		pnFechaPago.add(lblFechaPago);
		
		dchFechaPago = new JDateChooser();
		dchFechaPago.setBounds(0, 18, 144, 20);
		pnFechaPago.add(dchFechaPago);
		
		pnFechaCausacion = new JPanel();
		pnFechaCausacion.setLayout(null);
		pnFechaCausacion.setBackground(new Color(0, 51, 0));
		pnFechaCausacion.setBounds(665, 11, 145, 38);
		pn5.add(pnFechaCausacion);
		
		lblFechaCausacion = new JLabel("Fecha de Causaci\u00F3n");
		lblFechaCausacion.setForeground(Color.WHITE);
		lblFechaCausacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaCausacion.setBackground(Color.BLACK);
		lblFechaCausacion.setBounds(17, 0, 111, 14);
		pnFechaCausacion.add(lblFechaCausacion);
		
		dchFechaCausacion = new JDateChooser();
		dchFechaCausacion.setBounds(0, 18, 144, 20);
		pnFechaCausacion.add(dchFechaCausacion);
		
		
		pnFormaPago = new JPanel();
		pnFormaPago.setLayout(null);
		pnFormaPago.setBackground(new Color(0, 51, 0));
		pnFormaPago.setBounds(529, 11, 262, 38);
		pnFormaPago.setVisible(false);
		pn5.add(pnFormaPago);
		
		cbFormaPago = new JComboBox();
		cbFormaPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearFormaPago();
			}
		});
		cbFormaPago.setBounds(0, 16, 262, 22);
		cbFormaPago.setVisible(false);
		pnFormaPago.add(cbFormaPago);
		
		lblFormaPago = new JLabel("Forma de Pago");
		lblFormaPago.setForeground(Color.WHITE);
		lblFormaPago.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFormaPago.setBackground(Color.BLACK);
		lblFormaPago.setBounds(85, 0, 116, 14);
		lblFormaPago.setVisible(false);
		pnFormaPago.add(lblFormaPago);
		
		pnEstadoPago = new JPanel();
		pnEstadoPago.setForeground(new Color(0, 0, 0));
		pnEstadoPago.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		pnEstadoPago.setBounds(485, 28, 170, 68);
		pn5.add(pnEstadoPago);
		pnEstadoPago.setLayout(null);
		
		rdbtnCancelado = new JRadioButton("Cancelado");
		rdbtnCancelado.addActionListener(new ActionListener() {
			//Evento para visualizar y posicionar los elementos de la interfaz cuando se seleccione la opcion Cancelado en el radio button
			public void actionPerformed(ActionEvent e) {
				
				if(rdbtnCancelado.isSelected()){
					lblObservaciones.setBounds(10, 11, 92, 14);
					txaObservaciones.setBounds(10, 28, 279, 68);
					lblEstadoPago.setBounds(299, 11, 84, 14);
					pnEstadoPago.setBounds(299, 28, 170, 68);
					rdbtnCancelado.setBounds(6, 15, 93, 23);
					rdbtnCuentaPagar.setBounds(6, 38, 157, 23);
					pnFormaPago.setBounds(479, 11, 262, 38);
					lblFormaPago.setBounds(85, 0, 116, 14);
					cbFormaPago.setBounds(0, 16, 262, 22);
					lblFormaPago.setVisible(true);
					cbFormaPago.setVisible(true);
					pnFormaPago.setVisible(true);
					pnFechaCausacion.setBounds(751, 11, 145, 38);
					lblFechaCausacion.setBounds(17, 0, 111, 14);
					dchFechaCausacion.setBounds(0, 18, 144, 20);
					lblFechaPago.setBounds(33, 0, 105, 14);
					pnFechaPago.setBounds(751, 58, 145, 38);
					dchFechaPago.setBounds(0, 18, 144, 20);
				}
				JTextPane txpNota = new JTextPane();
				txpNota.setBackground(UIManager.getColor("Button.background"));
				txpNota.setContentType("text/html");
				txpNota.setText("<p align=\"justify\">Si la compra que usted va a registrar ya fue <b>pagada</b> entonces usted puede generar el <br>EGRESO correspondiente para su contabilidad. Para poder generar el Egreso de manera<br> autom\u00E1tica la compra debi\u00F3 haber sido cancelada en su totalidad, de lo contrario debe<br> dejarla como CUENTA x PAGAR y despu\u00E9s ingresar por [CARTERA - Egresos, Pagos y<br> CxP - 2. Pagar/Abonar una CxP ya registrada] y hacer el abono por el valor cancelado<br> llamando la CXP que genera esta compra.</p>");
				txpNota.setBounds(10, 189, 516, 136);
				contentPane.add(txpNota);
				
				JOptionPane.showMessageDialog(null, txpNota);
			}
		});
		buttonGroup.add(rdbtnCancelado);
		rdbtnCancelado.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnCancelado.setBounds(6, 15, 93, 23);
		pnEstadoPago.add(rdbtnCancelado);
		
		rdbtnCuentaPagar = new JRadioButton("Cuenta x Pagar (Pend)");
		rdbtnCuentaPagar.addActionListener(new ActionListener() {
			//Evento para visualizar y posicionar los elementos de la interfaz cuando se seleccione la opcion Cuenta x Pagar (Pend) en el radio button
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCuentaPagar.isSelected()){
					lblObservaciones.setBounds(146, 11, 92, 14);
					txaObservaciones.setBounds(146, 28, 279, 68);
					lblEstadoPago.setBounds(435, 11, 84, 14);
					pnEstadoPago.setBounds(435, 28, 170, 68);
					rdbtnCancelado.setBounds(6, 15, 93, 23);
					rdbtnCuentaPagar.setBounds(6, 38, 157, 23);
					lblFormaPago.setVisible(false);
					cbFormaPago.setVisible(false);
					pnFormaPago.setVisible(false);
					pnEstadoPago.setBounds(435, 28, 170, 68);
					lblFechaCausacion.setBounds(17, 0, 111, 14);
					pnFechaCausacion.setBounds(615, 11, 145, 38);
					dchFechaCausacion.setBounds(0, 18, 144, 20);
					lblFechaPago.setBounds(33, 0, 105, 14);
					pnFechaPago.setBounds(615, 58, 145, 38);
					dchFechaPago.setBounds(0, 18, 144, 20);
				}
			}
		});
		buttonGroup.add(rdbtnCuentaPagar);
		rdbtnCuentaPagar.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnCuentaPagar.setBounds(6, 38, 157, 23);
		pnEstadoPago.add(rdbtnCuentaPagar);
		
		rdbtnCuentaPagar.setSelected(true);
		
		lblEstadoPago = new JLabel("Estado Pago");
		lblEstadoPago.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstadoPago.setBounds(485, 11, 84, 14);
		pn5.add(lblEstadoPago);
		
		//Metodos que debe ejecutar la ventana al inicializarse
		listarProveedores();
		buscarArticuloPorNombre();
		llenarColumnasTbArticulos();
		listarFormaPagos();
		
	}
	
	//Metodo para encontrar los datos del articulo incluyendo su linea mediante el codigo
	private void cargarDatosArticuloPorNombre() {
		try{
			DelegadoArticulo delegadoArticulo =new DelegadoArticulo();
			DelegadoControlInventario delegadoControlInventario = new DelegadoControlInventario();
			Articulo datosArticulo=delegadoArticulo.traerLineaUnidadArticulo(txtArticulo.getText()).get(0);
			ControlInventario datosArticuloInventario = delegadoControlInventario.traerContInventarioPorCodigoArticulo(datosArticulo.getCodigo()).get(0);
			if(datosArticulo.getEstado().equals("Inactivo")){
				JOptionPane.showMessageDialog(null, "El articulo "+datosArticulo.getNombre()+" se encuentra INACTIVO");
				txtArticulo.setText("");
			}else{
				txtCodigo.setText(Integer.toString(datosArticulo.getCodigo()));
				txtLinea.setText(datosArticulo.getLineaArticulos().getNombreL());
				txtUe.setText(datosArticulo.getUnidadMedida().getAbreviatura().toString());
				txtUltimoCosto.setText(formatearNumero(datosArticuloInventario.getCostoUltimaCompra()));
				txtSaldo.setText(String.valueOf(datosArticuloInventario.getCantExistencia()));
			}
		}catch(Exception ex){
			ex.getMessage();
		}
	}
	
	//Metodo para encontrar los datos del articulo incluyendo su linea mediante el codigo
	private void cargarDatosArticuloPorCodigo() {
		
		try{
			DelegadoArticulo delegadoArticulo =new DelegadoArticulo();
			DelegadoControlInventario delegadoControlInventario = new DelegadoControlInventario();
			Articulo datosArticulo=delegadoArticulo.traerLineaUnidadArticuloPorCodigo(Integer.parseInt(txtCodigo.getText())).get(0);
			ControlInventario datosArticuloInventario = delegadoControlInventario.traerContInventarioPorCodigoArticulo(Integer.parseInt(txtCodigo.getText())).get(0);
			if(datosArticulo.getEstado().equals("Inactivo")){
				JOptionPane.showMessageDialog(null, "El articulo "+datosArticulo.getNombre()+" se encuentra INACTIVO");
				txtCodigo.setText("");
			}else{
				txtArticulo.setText(datosArticulo.getNombre());
				txtLinea.setText(datosArticulo.getLineaArticulos().getNombreL());
				txtUe.setText(datosArticulo.getUnidadMedida().getAbreviatura().toString());
				txtUltimoCosto.setText(formatearNumero(datosArticuloInventario.getCostoUltimaCompra()));
				txtSaldo.setText(String.valueOf(datosArticuloInventario.getCantExistencia()));
			}
		}catch(Exception ex){
			ex.getMessage();
			JOptionPane.showMessageDialog(null, "El codigo "+txtCodigo.getText()+" no existe");
			limpiarDatosArticulo();
		}
		
	}
	
	//Metodo para buscar un articulo mediante su nombre permitiendo autocompletar
	private void buscarArticuloPorNombre() {
		TextAutoCompleter textoAutocompletar = new TextAutoCompleter( txtArticulo);
		DelegadoArticulo delegadoArticulo = new DelegadoArticulo();
		List<Articulo> articulos = delegadoArticulo.listarArticulo();
		
		for(Articulo articulo : articulos){
			textoAutocompletar.addItem(articulo.getNombre().toString());
		}
	}
	//Metodo para listar los proveedores y desplegarlos en un combo box
	private void listarProveedores() {
		DelegadoProveedor delegadoProveedor = new DelegadoProveedor();
		List<Proveedor> proveedores = delegadoProveedor.listarProveedor();
		modeloProveedor.addElement("--ELIJA PROVEEDOR--");
		cbProveedor.setModel(modeloProveedor);
		
		for(Proveedor proveedor : proveedores){
			modeloProveedor.addElement(new Persona (proveedor.getNombre(), proveedor.getIdentificacion()));
			cbProveedor.setModel(modeloProveedor);
		}
		
		modeloProveedor.addElement("--NUEVO PROVEEDOR--");
		cbProveedor.setModel(modeloProveedor);
		
	}
	//Metodo para crear proveedor en caso de que no aparezca en el combo box
	private void crearProveedor() {
		if(cbProveedor.getSelectedItem().equals("--NUEVO PROVEEDOR--")){
			VentRegistrarProveedor ventRegistrarProveedor = new VentRegistrarProveedor();
			cbProveedor.removeAllItems();
			ventRegistrarProveedor.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                	listarProveedores();  
                }
            });
			ventRegistrarProveedor.setVisible(true);
		}
	}
	//Metodo para validar los datos ingresados del articulo agregandolos a la tabla y a su vez calculando
	//la cantidad de items, el descuento, el subtotal y el total
	private void validarIngresoArticuloATbArticulos() {
		if(txtCodigo.getText().equals("")||txtArticulo.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Debe Seleccionar el ARTICULO primero ingresando el codigo o articulo");
		}else{
			if(txtCosto.getValue().equals(0)){
				JOptionPane.showMessageDialog(null, "Debe ingresar el COSTO para este item");
			}
			else{
				agregarArticuloATbArticulos();
				calcularSubtotal();
				calcularCantidadArticulos();
				calcularTotalCompra();
			}
		}
	}
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbArticulos() {
		modeloTbArticulos.addColumn("Codigo");
		modeloTbArticulos.addColumn("Linea");
		modeloTbArticulos.addColumn("Articulo");
		modeloTbArticulos.addColumn("Cant");
		modeloTbArticulos.addColumn("Costo");
		modeloTbArticulos.addColumn("Total");
		
		tbArticulos.setModel(modeloTbArticulos);
	}
	//Metodo para agregar el articulo en la tabla
	private void agregarArticuloATbArticulos() {
        
		Double total = (double) (Integer.parseInt(txtCant.getText())) *(Double.parseDouble(txtCosto.getValue().toString()));

		String [] fila = new String[modeloTbArticulos.getColumnCount()];
		Boolean aviso = false;
		
		fila[0]= txtCodigo.getText();
		fila[1]= txtLinea.getText();
		fila[2]= txtArticulo.getText();
		fila[3]= txtCant.getText();
		fila[4]= formatearNumero(Double.parseDouble(txtCosto.getValue().toString()));
		fila[5]= formatearNumero(total);
        
		for(int i = 0; i<tbArticulos.getRowCount(); i++){
			 
			if(tbArticulos.getValueAt(i,0).equals(txtCodigo.getText())){
				JOptionPane.showMessageDialog(null, "El codigo del articulo " + txtCodigo.getText() + " ya fue adicionado en la tabla por lo tanto modifique la cantidad.");
				aviso = true;
				limpiarDatosArticulo();
			}
		}
		
		if(aviso == false){
			modeloTbArticulos.addRow(fila);
			tbArticulos.setModel(modeloTbArticulos);
			limpiarDatosArticulo();
		}
		
      //Evento que permite la modificacion de la tabla cada vez que esta es editada
      		tbArticulos.getModel().addTableModelListener(new TableModelListener() {
                  @Override
                  public void tableChanged(TableModelEvent evento) {
                      actualizarTbArticulos(evento);
                  }
              });
	}
	
	
	//Metodo para limpiar las cajas de texto en la que se consulto los atributos del articulo
	private void limpiarDatosArticulo() {
		txtCodigo.setText("");
		txtArticulo.setText("");
		txtLinea.setText("");
		txtUe.setText("");
		txtCant.setText("1");
		txtCosto.setValue(0);
		txtSaldo.setText("");
		txtUltimoCosto.setText("");
	}
	//Metodo para quitar los articulos ya ingresados en la tabla
	private void quitarArticuloATbArticulos() {
		try{
			DefaultTableModel modeloArticuloAEliminar = (DefaultTableModel) tbArticulos.getModel();
			modeloArticuloAEliminar.removeRow(tbArticulos.getSelectedRow()); 
			calcularSubtotal();
			calcularCantidadArticulos();
			calcularTotalCompra();
		}catch(Exception e){
			e.getMessage();
			JOptionPane.showMessageDialog(null, "Debe seleccionar cualquier articulo para ser eliminado");
		}
	}
	//Metodo para calcular el subtotal de los articulos ingresados a la tabla 
	private void calcularSubtotal(){
		
		Double subtotal1 = (double) 0;
		
		for(int i=0; i<tbArticulos.getRowCount(); i++) {
			subtotal= Double.parseDouble(desformatearNumero(String.valueOf(tbArticulos.getValueAt(i,5))));
			subtotal1 += subtotal;
		}
		
		txtSubtotal.setText(formatearNumero(subtotal1));

	}
	//Metodo para calcular el descuento de los articulos ingresados a la tabla para finalmente hallar el total de la compra
	private void calcularTotalCompra() {
		try{
			Double descuento = (1-(Double.parseDouble(txtDctoCom.getText())/100))* Double.parseDouble(desformatearNumero(txtSubtotal.getText()));
			txtTotalcompra.setText(formatearNumero(descuento));
		}catch(Exception e){
			e.getMessage();
			txtTotalcompra.setText(txtSubtotal.getText());
		}
		
	}
	//Metodo para calcular la cantidad de items registrados en la tabla
	private void calcularCantidadArticulos() {
		
		int cantidad = tbArticulos.getRowCount();
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

	//Metodo para actualizar la tabla cada vez que haya un cambio en la misma
	private void actualizarTbArticulos(TableModelEvent evento) {
		
		if (evento.getType() == TableModelEvent.UPDATE) {

            // Se obtiene el modelo de la tabla y la fila/columna que han cambiado.
            TableModel modelo = ((TableModel) (evento.getSource()));
            int fila = evento.getFirstRow();
            int columna = evento.getColumn();
           // Se aplica los calculos solamente a la columnas 3 (costo) y 4 (total)
            if (columna == 3 || columna == 4 ) {
	            try{
	            	 int cant = Integer.parseInt(String.valueOf(modelo.getValueAt(fila, 3)));
	            	 double costo = Double.parseDouble(desformatearNumero(String.valueOf(modelo.getValueAt(fila, 4))));
	            	 double total = cant*costo; 
	            	 modelo.setValueAt(formatearNumero(total),fila, 5);	
	            	 calcularCantidadArticulos();
	            	 calcularSubtotal();
	            	 calcularTotalCompra();
	            	 
	            }catch(Exception ex){
	            	ex.getMessage();
	            }
            }
        }
		
	}
	
	//Metodo para validar los datos ingresados de la compra incluyendo la tabla de articulos
	private void validarDatos() {
		
		if(cbProveedor.getSelectedItem()=="--ELIJA PROVEEDOR--"){
			JOptionPane.showMessageDialog(null, "Debe elegir el proveedor");
		}else{
			if(txtFactProv.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Debe ingresar el numero de la factura del proveedor");
			}else{
				if(dchFechaE.getDate()==null){
					JOptionPane.showMessageDialog(null, "Debe ingresar una fecha de entrega");
				}else{
					if(tbArticulos.getRowCount() == 0 ){
						JOptionPane.showMessageDialog(null, "Debe ingresar articulos en la tabla");
					}else{
						if(rdbtnCancelado.isSelected()==true){
							if(cbFormaPago.getSelectedItem().equals("--ELIJA FORMA DE PAGO--")){
								JOptionPane.showMessageDialog(null, "Debe elegir una forma de pago");
							}else{
								if(dchFechaCausacion.getDate()==null){
									JOptionPane.showMessageDialog(null, "Debe ingresar la fecha de causacion");
								}else{
									if(dchFechaPago.getDate()==null){
										JOptionPane.showMessageDialog(null, "Debe ingresar la fecha de pago");
									}else{
										validarFechasEstadoCancelado();
									}
								}
							}
						}else{
							if(rdbtnCuentaPagar.isSelected()==true){
								if(dchFechaCausacion.getDate()==null){
									JOptionPane.showMessageDialog(null, "Debe ingresar la fecha de causacion");
								}else{
									if(dchFechaPago.getDate()==null){
										JOptionPane.showMessageDialog(null, "Debe ingresar la fecha de pago");
									}else{
										validarFechasEstadoPendiente();
									}
								}
							}
						}
					}
				}
			} 
		}
	}
	//Metodo para validar las fechas de pago y causacion en estado de pago pendiente
	private void validarFechasEstadoPendiente() {
		String fechaCausacion = convertirDateAString(dchFechaCausacion.getDate());
		String fechaPago = convertirDateAString(dchFechaPago.getDate());
		String fechaActual = convertirDateAString(new Date());
		if(fechaActual.compareTo(fechaCausacion)>=0){
			if(fechaCausacion.compareTo(fechaPago)<=0){
				abrirDialogoConfirmacionRegistro();
			}else{
				JOptionPane.showMessageDialog(null, "No puede ingresar una FECHA de PAGO que sea anterior a la FECHA DE CAUSACION");
				dchFechaPago.setCalendar(null);
			}
		}else{
			JOptionPane.showMessageDialog(null, "No puede ingresar una fecha de causación futura");
			dchFechaCausacion.setCalendar(null);
		}
		
	}

	//Metodo para validar las fechas de pago y causacion en estado de pago cancelado
	private void validarFechasEstadoCancelado() {
		String fechaCausacion = convertirDateAString(dchFechaCausacion.getDate());
		String fechaPago = convertirDateAString(dchFechaPago.getDate());
		String fechaActual = convertirDateAString(new Date());
		if(fechaActual.compareTo(fechaCausacion)>=0){
			if(fechaCausacion.compareTo(fechaPago)<=0){
				if(fechaCausacion.compareTo(fechaPago)==0){
					abrirDialogoConfirmacionRegistro();
				}else{
					JOptionPane.showMessageDialog(null, "No puede ingresar una FECHA de PAGO FUTURA si desea registrar el EGRESO ahora mismo.");
					dchFechaPago.setCalendar(null);
				}
			}else{
				JOptionPane.showMessageDialog(null, "No puede ingresar una FECHA de PAGO que sea anterior a la FECHA DE CAUSACION.");
				dchFechaPago.setCalendar(null);
			}
		}else{
			JOptionPane.showMessageDialog(null, "No puede ingresar una fecha de causación futura.");
			dchFechaCausacion.setCalendar(null);
		}
		
	}
	
	
	//Metodo para abrir ventanta de confirmacion de registro
	private void abrirDialogoConfirmacionRegistro() {
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de grabar esta compra?", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarCompra();
		}else{
		
		}
	}

	//Metodo para listar formas de pagos y desplegarlos en un combo box
	public void listarFormaPagos() {
		DelegadoMedioPagoProv delegadoFormaPago = new DelegadoMedioPagoProv();
		List<MedioPagoProv> formaPagos = delegadoFormaPago.listarFormaPago();
		modeloFormaPago.addElement("--ELIJA FORMA DE PAGO--");
		cbFormaPago.setModel(modeloFormaPago);
		
		for(MedioPagoProv formaPago : formaPagos){
			modeloFormaPago.addElement(new MedioPagoProv (formaPago.getDescripcion(), formaPago.getIdMedioPagoProv()));
			cbFormaPago.setModel(modeloFormaPago);
		}
		modeloFormaPago.addElement("--NUEVA FORMA DE PAGO--");
		cbFormaPago.setModel(modeloFormaPago);
	}
	
	//Metodo para crear las formas de pago luego de ser elegido dentro del combo box
	private void crearFormaPago() {
		if(cbFormaPago.getSelectedItem().equals("--NUEVA FORMA DE PAGO--")){
			VentRegistrarMediosPagoProv ventRegistrarFormaPago = new VentRegistrarMediosPagoProv();
			cbFormaPago.removeAllItems();
			ventRegistrarFormaPago.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                	listarFormaPagos();  
                }
            });
			ventRegistrarFormaPago.setVisible(true);
		}
	}
	//Metodo para registrar los datos ingresados de la compra
	private void registrarCompra() {
		Date fechaActual = new Date();
		compraArticulos = new CompraArticulos();
		proveedores = new Proveedor();
		
		try{
			Persona proveedor = (Persona) cbProveedor.getSelectedItem();
			proveedores.setIdentificacion(proveedor.getIdentificacion());
		}catch(Exception e){
			proveedores.setIdentificacion(idProveedor);
			e.getMessage();
		}
		
		compraArticulos.setProveedores(proveedores);
		compraArticulos.setFechaEntrega(dchFechaE.getDate());
		compraArticulos.setItem(Integer.parseInt(txtItem.getText()));
		compraArticulos.setSubtotal(Double.parseDouble(desformatearNumero(txtSubtotal.getText())));
		compraArticulos.setDescuento(Double.parseDouble(txtDctoCom.getText()));
		compraArticulos.setTotalCompra(Double.parseDouble(desformatearNumero(txtTotalcompra.getText())));
		compraArticulos.setFactProv(Integer.parseInt(txtFactProv.getText()));
		if(txtOrdenCompra.getText().equals("")){
			compraArticulos.setOrdCompra(0);
		}else{
			compraArticulos.setOrdCompra(Integer.parseInt(txtOrdenCompra.getText()));
		}
		
		if(rdbtnCancelado.isSelected()){
			compraArticulos.setEstadoPago("Cancelado");
			MedioPagoProv medioPago = new MedioPagoProv();
			medioPago = (MedioPagoProv)cbFormaPago.getSelectedItem();
			medioPago.setIdMedioPagoProv(medioPago.getIdMedioPagoProv());
			compraArticulos.setMedioPagoProv(medioPago);
		}else if(rdbtnCuentaPagar.isSelected()){
			compraArticulos.setEstadoPago("Pendiente");
		}
		
		compraArticulos.setFechaCausacion(dchFechaCausacion.getDate());
		compraArticulos.setFechaPago(dchFechaPago.getDate());
		compraArticulos.setObservaciones(txaObservaciones.getText());
		compraArticulos.setFechaGeneracion(fechaActual);
		compraArticulos.setEstadoActividad("Activo");
		
		delegadoCompraArticulos.insertarCompraArticulos(compraArticulos);
		codigoCompra=delegadoCompraArticulos.traerCodigoCompra();
		
		registrarDetalleCompra();
		
		if(codigoCompra.get(0).getEstadoPago().equals("Pendiente")){
			registrarCxP();
			contabilizarCompraPendiente();
		}else{
			registrarEgreso();
			contabilizarCompraPagada();
		}
		
		cambiarEstadoOrdenCompra();
	}
	
	//Metodo para contabilizar la compra a credito, compra que estara pendiente de pago
	private void contabilizarCompraPendiente() {
		DelegadoContabilizacion delegadoContabilizacion = new DelegadoContabilizacion();                                                                                   
		Contabilizacion contabilizarACompra = new Contabilizacion();                                                                 
		Contabilizacion contabilizarAProveedor = new Contabilizacion();
		
		contabilizarACompra.setCodigoCuenta(1435);
		contabilizarACompra.setFechaGeneracion(new Date());              
		contabilizarACompra.setNombreCuenta("Inventario de Mercancias");
		contabilizarACompra.setSaldoDebito(codigoCompra.get(0).getTotalCompra());
		contabilizarACompra.setSaldoCredito(0);
		
		contabilizarAProveedor.setCodigoCuenta(2205);
		contabilizarAProveedor.setFechaGeneracion(new Date());              
		contabilizarAProveedor.setNombreCuenta("Proveedores Nacionales");
		contabilizarAProveedor.setSaldoDebito(0);
		contabilizarAProveedor.setSaldoCredito(codigoCompra.get(0).getTotalCompra());
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarACompra); 
		delegadoContabilizacion.insertarContabilizacion(contabilizarAProveedor); 
	}

	//Metodo para registrar el egreso cuando la compra queda pagada 
	private void registrarEgreso() {
		
		DelegadoEgreso delegadoEgreso = new DelegadoEgreso();
		Egreso egreso = new Egreso();
		
		egreso.setIdentificacionProv(proveedores.getIdentificacion());
		egreso.setConcepto("Compra");
		egreso.setDocSoporte(Integer.parseInt(txtFactProv.getText()));
		egreso.setFechaGeneracion(new Date());
		egreso.setFechaPago(dchFechaCausacion.getDate());
		egreso.setSubtotal(Double.parseDouble(desformatearNumero(txtSubtotal.getText())));
		egreso.setTotalPagado(Double.parseDouble(desformatearNumero(txtTotalcompra.getText())));
		egreso.setOtros(0.0);
		MedioPagoProv medioPago1 = new MedioPagoProv();
		medioPago1 = (MedioPagoProv)cbFormaPago.getSelectedItem();
		medioPago1.setIdMedioPagoProv(medioPago1.getIdMedioPagoProv());
		egreso.setMedioPagoProv1(medioPago1);
		egreso.setMedioPagoProv2(null);
		egreso.setValorPago1(Double.parseDouble(desformatearNumero(txtTotalcompra.getText())));
		egreso.setValorPago2(0.0);
		egreso.setObservaciones(txaObservaciones.getText());
		egreso.setIdCuentaXPagar(0);
		egreso.setEstadoActividad("Activo");
		
		delegadoEgreso.insertarEgreso(egreso);
		List<Egreso> codigoEgreso= delegadoEgreso.traerCodigoEgreso();
		
		compraArticulos.setEgreso(codigoEgreso.get(0).getIdEgreso());
		delegadoCompraArticulos.actualizarCompraArticulos(compraArticulos);
		
		egreso.setCompra(codigoCompra.get(0).getIdCompra());
		delegadoEgreso.actualizarEgreso(egreso);
		
		JOptionPane.showMessageDialog(null, "Se ha registrado esta compra satisfactoriamente con consecutivo N° "+codigoCompra.get(0).getIdCompra()+" ."+" La compra ha generado el egreso N° "+codigoEgreso.get(0).getIdEgreso());
		abrirVentanaCompraRegistrada("",String.valueOf(codigoEgreso.get(0).getIdEgreso()));
		
		
	}
	//Metodo para contabilizar la compra a contado, compra que ya esta cobrada
	private void contabilizarCompraPagada() {
		DelegadoContabilizacion delegadoContabilizacion = new DelegadoContabilizacion();                                                                                   
		Contabilizacion contabilizarACompra = new Contabilizacion();                                                                 
		Contabilizacion contabilizarABanco = new Contabilizacion();
		Contabilizacion contabilizarACaja = new Contabilizacion();
		
		contabilizarACompra.setCodigoCuenta(1435);
		contabilizarACompra.setFechaGeneracion(new Date());              
		contabilizarACompra.setNombreCuenta("Inventario de Mercancias");
		contabilizarACompra.setSaldoDebito(codigoCompra.get(0).getTotalCompra());
		contabilizarACompra.setSaldoCredito(0);
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarACompra);
		
		if("Banco".equals(codigoCompra.get(0).getMedioPagoProv().getNombreCuenta())){
			contabilizarABanco.setCodigoCuenta(1110);
			contabilizarABanco.setFechaGeneracion(new Date());
			contabilizarABanco.setNombreCuenta("Bancos");
			contabilizarABanco.setSaldoDebito(0);
			contabilizarABanco.setSaldoCredito(codigoCompra.get(0).getTotalCompra());
			
			delegadoContabilizacion.insertarContabilizacion(contabilizarABanco);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
		}else{
			contabilizarACaja.setCodigoCuenta(1105);
			contabilizarACaja.setFechaGeneracion(new Date());
			contabilizarACaja.setNombreCuenta("Caja");
			contabilizarACaja.setSaldoDebito(0);
			contabilizarACaja.setSaldoCredito(codigoCompra.get(0).getTotalCompra());
			
			delegadoContabilizacion.insertarContabilizacion(contabilizarACaja); 
		}
	}
	//Metodo para registrar la cuenta x pagar cuando la compra queda pendiente de pago
	private void registrarCxP() {
		
		DelegadoCuentaXPagar delegadoCuentaXPagar = new DelegadoCuentaXPagar();
		CuentaXPagar cuentaXPagar = new CuentaXPagar();
		
		cuentaXPagar.setIdentificacionProv(proveedores.getIdentificacion());
		cuentaXPagar.setConcepto("Compra");
		cuentaXPagar.setDocSoporte(Integer.parseInt(txtFactProv.getText()));
		cuentaXPagar.setFechaCausacion(dchFechaCausacion.getDate());
		cuentaXPagar.setFechaPago(dchFechaPago.getDate());
		cuentaXPagar.setFechaGeneracion(new Date());
		cuentaXPagar.setSubtotal(Double.parseDouble(desformatearNumero(txtSubtotal.getText())));
		cuentaXPagar.setTotalPagar(Double.parseDouble(desformatearNumero(txtTotalcompra.getText())));
		cuentaXPagar.setOtros(0);
		cuentaXPagar.setObservaciones(txaObservaciones.getText());
		cuentaXPagar.setEstado("Pendiente");
		
		delegadoCuentaXPagar.insertarCuentaXPagar(cuentaXPagar);
		
		List<CuentaXPagar> codigoCxP= delegadoCuentaXPagar.traerCodigoCuentaXPagar();
		
		compraArticulos.setCuentaXPagar(codigoCxP.get(0).getIdCuentaXPagar());
		delegadoCompraArticulos.actualizarCompraArticulos(compraArticulos);
		
		cuentaXPagar.setCompra(codigoCompra.get(0).getIdCompra());
		delegadoCuentaXPagar.actualizarCuentaXPagar(cuentaXPagar);
		
		JOptionPane.showMessageDialog(null, "Se ha registrado esta compra satisfactoriamente con consecutivo N° "+codigoCompra.get(0).getIdCompra()+" ."+" La compra ha generado la CxP N° "+codigoCxP.get(0).getIdCuentaXPagar());
		abrirVentanaCompraRegistrada(String.valueOf(codigoCxP.get(0).getIdCuentaXPagar()),"");
		
	}
	//Metodo para registrar el detalle compra
	private void registrarDetalleCompra() {
		DetalleCompra detalleCompra = new DetalleCompra();
		DelegadoDetalleCompra delegadoDetalleCompra = new DelegadoDetalleCompra();
		Articulo articulo = new Articulo();
		
		for(int i=0; i< modeloTbArticulos.getRowCount();i++){
			String codigo = modeloTbArticulos.getValueAt(i, 0).toString();
			String descripcion= modeloTbArticulos.getValueAt(i, 2).toString();
			String cantidad= modeloTbArticulos.getValueAt(i, 3).toString();
			String costo= modeloTbArticulos.getValueAt(i, 4).toString();
			String total= modeloTbArticulos.getValueAt(i, 5).toString();
			
			compraArticulos.setIdCompra(codigoCompra.get(0).getIdCompra());
			detalleCompra.setCompraArticulos(compraArticulos);
			articulo.setCodigo(Integer.parseInt(codigo));
			articulo.setDescripcion(descripcion);
			detalleCompra.setArticulo(articulo);
			detalleCompra.setCantidad(Integer.parseInt(cantidad));
			detalleCompra.setCosto(Double.parseDouble(desformatearNumero(costo)));
			detalleCompra.setTotal(Double.parseDouble(desformatearNumero(total)));
			
			delegadoDetalleCompra.insertarDetalleCompra(detalleCompra);
			
			registrarDetalleCompraAlKardex();
		}
		
	}

	//Metodo para registrar el detalle de la compra al kardex electronico de articulos
	private void registrarDetalleCompraAlKardex() {
		KardexElectronico kardexElectronico = new KardexElectronico();
		DelegadoDetalleCompra delegadoDetalleCompra = new DelegadoDetalleCompra();
		DelegadoControlInventario delegadoControlInventario = new DelegadoControlInventario();
		List<ControlInventario> controlInventario;
		ControlInventario controlInventarioAModificar;
		Articulo articulo = new Articulo();
		
		ultimoDetalleCompra = delegadoDetalleCompra.traerUltimoRegistroDetalleCompra();
		
		if(delegadoKardexElectronico.traerUltimoRegistroKardexElectronicopPorArticulo(ultimoDetalleCompra.get(0).getArticulo().getCodigo())==null){
			kardexElectronico.setFecha(ultimoDetalleCompra.get(0).getCompraArticulos().getFechaEntrega());
			kardexElectronico.setMovimiento("Compra");
			kardexElectronico.setNroDocumento(ultimoDetalleCompra.get(0).getCompraArticulos().getIdCompra());
			articulo.setCodigo(ultimoDetalleCompra.get(0).getArticulo().getCodigo());
			kardexElectronico.setArticulo(articulo);
			kardexElectronico.setCantidadEntrada(ultimoDetalleCompra.get(0).getCantidad());
			kardexElectronico.setCostoUEntrada(ultimoDetalleCompra.get(0).getCosto());
			kardexElectronico.setCostoTEntrada(ultimoDetalleCompra.get(0).getTotal());
			kardexElectronico.setCantidadSalida(0);
			kardexElectronico.setCostoUSalida(0);
			kardexElectronico.setCostoTSalida(0);
			kardexElectronico.setCantidadSaldo(ultimoDetalleCompra.get(0).getCantidad());
			kardexElectronico.setCostoUSaldo(ultimoDetalleCompra.get(0).getCosto());
			kardexElectronico.setCostoTSaldo(ultimoDetalleCompra.get(0).getTotal());
			kardexElectronico.setProveedorUltimaCompra(ultimoDetalleCompra.get(0).getCompraArticulos().getProveedores().getNombre());
			
			delegadoKardexElectronico.insertarKardexElectronico(kardexElectronico);
			
			controlInventario = delegadoControlInventario.traerContInventarioPorCodigoArticulo(ultimoDetalleCompra.get(0).getArticulo().getCodigo());
			controlInventarioAModificar = controlInventario.get(0);
			
			controlInventarioAModificar.setCostoPromedio(ultimoDetalleCompra.get(0).getCosto());
			controlInventarioAModificar.setCantExistencia(ultimoDetalleCompra.get(0).getCantidad());
			controlInventarioAModificar.setProveedorUltimaCompra(ultimoDetalleCompra.get(0).getCompraArticulos().getProveedores().getNombre());
			controlInventarioAModificar.setCostoUltimaCompra(ultimoDetalleCompra.get(0).getCosto());
			controlInventarioAModificar.setTotalCostoInventario(ultimoDetalleCompra.get(0).getTotal());
				
			delegadoControlInventario.actualizarControlInventario(controlInventarioAModificar);
		}else{
			
			ultimoRegistroPorArticuloKardex1 = delegadoKardexElectronico.traerUltimoRegistroKardexElectronicopPorArticulo(ultimoDetalleCompra.get(0).getArticulo().getCodigo());
			ultimoRegistroPorArticuloKardex = ultimoRegistroPorArticuloKardex1.get(0);
			
			kardexElectronico.setFecha(ultimoDetalleCompra.get(0).getCompraArticulos().getFechaEntrega());
			kardexElectronico.setMovimiento("Compra");
			kardexElectronico.setNroDocumento(ultimoDetalleCompra.get(0).getCompraArticulos().getIdCompra());
			articulo.setCodigo(ultimoDetalleCompra.get(0).getArticulo().getCodigo());
			kardexElectronico.setArticulo(articulo);
			kardexElectronico.setCantidadEntrada(ultimoDetalleCompra.get(0).getCantidad());
			kardexElectronico.setCostoUEntrada(ultimoDetalleCompra.get(0).getCosto());
			kardexElectronico.setCostoTEntrada(ultimoDetalleCompra.get(0).getTotal());
			kardexElectronico.setCantidadSalida(0);
			kardexElectronico.setCostoUSalida(0);
			kardexElectronico.setCostoTSalida(0);
			kardexElectronico.setCantidadSaldo(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimoDetalleCompra.get(0).getCantidad());
			kardexElectronico.setCostoUSaldo((ultimoRegistroPorArticuloKardex.getCostoTSaldo() + ultimoDetalleCompra.get(0).getTotal())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimoDetalleCompra.get(0).getCantidad()));
			kardexElectronico.setCostoTSaldo((ultimoRegistroPorArticuloKardex.getCostoTSaldo() + ultimoDetalleCompra.get(0).getTotal())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimoDetalleCompra.get(0).getCantidad())*(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimoDetalleCompra.get(0).getCantidad()));
			kardexElectronico.setProveedorUltimaCompra(ultimoDetalleCompra.get(0).getCompraArticulos().getProveedores().getNombre());
			
			delegadoKardexElectronico.insertarKardexElectronico(kardexElectronico);

			controlInventario = delegadoControlInventario.traerContInventarioPorCodigoArticulo(ultimoDetalleCompra.get(0).getArticulo().getCodigo());
			controlInventarioAModificar = controlInventario.get(0);
			
			controlInventarioAModificar.setCostoPromedio((ultimoRegistroPorArticuloKardex.getCostoTSaldo() + ultimoDetalleCompra.get(0).getTotal())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimoDetalleCompra.get(0).getCantidad()));
			controlInventarioAModificar.setCantExistencia(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimoDetalleCompra.get(0).getCantidad());
			controlInventarioAModificar.setProveedorUltimaCompra(ultimoDetalleCompra.get(0).getCompraArticulos().getProveedores().getNombre());
			controlInventarioAModificar.setCostoUltimaCompra(ultimoDetalleCompra.get(0).getCosto());
			controlInventarioAModificar.setTotalCostoInventario((ultimoRegistroPorArticuloKardex.getCostoTSaldo() + ultimoDetalleCompra.get(0).getTotal())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimoDetalleCompra.get(0).getCantidad())*(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimoDetalleCompra.get(0).getCantidad()));
			
			delegadoControlInventario.actualizarControlInventario(controlInventarioAModificar);
		}
	}

	//Metodo para mostrar los datos registrados de la compra a otra ventana para su puesta verificacion
	private void abrirVentanaCompraRegistrada(String idCuentaXPagar, String idCompEgreso) {
		VentMostrarCompraRegistrada ventMostrarCompraRegistrada= new VentMostrarCompraRegistrada(codigoCompra.get(0), modeloTbArticulos, idCuentaXPagar, idCompEgreso);
		ventMostrarCompraRegistrada.setVisible(true);
		limpiarDatos();
		
	}
	//Metodo para limpiar los datos ya registrados
	private void limpiarDatos() {
		cbProveedor.setSelectedIndex(0);
		txtFactProv.setText("");
		txtOrdenCompra.setText("");
		txtOrdenCompra.setBackground(Color.WHITE);
		dchFechaE.setCalendar(null);
		limpiarTablaArticulos();
		txaObservaciones.setText("");
		rdbtnCuentaPagar.setSelected(true);
		dchFechaCausacion.setCalendar(null);
		dchFechaPago.setCalendar(null);
		cbFormaPago.setSelectedIndex(0);
		txtSubtotal.setText(formatearNumero(0.0));
		txtDctoCom.setText("0.0");
		txtTotalcompra.setText(formatearNumero(0.0));
		txtItem.setText("0");
		txtCosto.setValue(0);
	}
	
	//Metodo para limpiar la tabla de articulos 
	private void limpiarTablaArticulos() {
		for (int i = 0; i < tbArticulos.getRowCount(); i++) {
	           modeloTbArticulos.removeRow(i);
	           i-=1;
	       }
	}

	//Metodo para convertir una fecha de tipo date a una cadena
	public String convertirDateAString(Date strFecha){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(strFecha);
		
		return fecha;
	}
	//Metodo para agregar los datos de la orden de compra a la compra
	public void agregarDatosACompras(int idOrden, Proveedor proveedor, Date fechaEntrega, double totalOrden, int items,  List<DetalleOrdenCompra> listaDetalleOrden, String observaciones, Date fechaPago){
		txtOrdenCompra.setText(Integer.toString(idOrden));
		cbProveedor.getModel().setSelectedItem(proveedor.getNombre());
		idProveedor = proveedor.getIdentificacion();
		dchFechaE.setDate(fechaEntrega);
		dchFechaPago.setDate(fechaPago);
		txtTotalcompra.setText(formatearNumero(totalOrden));
		txtSubtotal.setText(formatearNumero(totalOrden));
		txtItem.setText(Integer.toString(items));
		txaObservaciones.setText(observaciones);
		llenarModeloDetalleOrden(listaDetalleOrden);
		txtOrdenCompra.setEditable(false);
		txtOrdenCompra.setBackground(Color.GREEN);
	}
	//Metodo para llenar la tabla donde van a estar los datos del detalle de la orden de compra
	public void llenarModeloDetalleOrden(List<DetalleOrdenCompra> listaDetalleOrden){
		
		String [] fila = new String[modeloTbArticulos.getColumnCount()];
		
		for(DetalleOrdenCompra detalles : listaDetalleOrden ){
			fila[0]= Integer.toString(detalles.getArticulo().getCodigo());
			fila[1]= detalles.getArticulo().getLineaArticulos().getNombreL();
			fila[2]= detalles.getArticulo().getNombre();
			fila[3]= Integer.toString(detalles.getCantidad());
			fila[4]= formatearNumero(detalles.getCosto());
			fila[5]= formatearNumero(detalles.getTotal());
			
			modeloTbArticulos.addRow(fila);
		}
		tbArticulos.setModel(modeloTbArticulos);
	}
	//Metodo para formatear un numero y convertirlo en moneda peso
	private void formatearAMoneda(JFormattedTextField campoTexto) {
		
		campoTexto.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
		campoTexto.setValue(0);
		NumberFormat visNf = NumberFormat.getCurrencyInstance(); 
		NumberFormat ediNf = NumberFormat.getNumberInstance(new Locale("es","CO"));
		ediNf.setGroupingUsed(false);
		NumberFormatter visNt = new NumberFormatter(visNf);
		NumberFormatter ediNt = new NumberFormatter(ediNf);
		DefaultFormatterFactory currFactory = new DefaultFormatterFactory(visNt, visNt, ediNt);
		ediNt.setAllowsInvalid(true);
		
		campoTexto.setFormatterFactory(currFactory);
	}
	
	//Metodo para cambiar el estado de la orden de pendiente a entregado cuando se haya registrado la compra luego de haberse cargado una orden de compra en la utilidad compra
	private void cambiarEstadoOrdenCompra(){
		if(txtOrdenCompra.getBackground()==Color.GREEN){
			DelegadoOrdenCompraArticulos delegadoOrdenCompraArticulos = new DelegadoOrdenCompraArticulos();
			List<OrdenCompraArticulos> codigoOrden= delegadoOrdenCompraArticulos.traerCodigoOrdenPorCompra(Integer.parseInt(txtOrdenCompra.getText()));
			OrdenCompraArticulos ordenAModificar = codigoOrden.get(0);
			
			ordenAModificar.setCompra(codigoCompra.get(0).getIdCompra());
			ordenAModificar.setEstado("Entregado");
			delegadoOrdenCompraArticulos.actualizarOrdenCompraArticulos(ordenAModificar);
		}else{
			
		}
	}
	
	//Metodo para validar la factura del proveedor en caso de registrar una factura ya existente en otra compra
	private void validarFactProv(){
		DelegadoCompraArticulos delegadoCompraArticulos = new DelegadoCompraArticulos();
		try{
			List<CompraArticulos> factProvEncontrada = delegadoCompraArticulos.traerCompraPorFactProv(Integer.parseInt(txtFactProv.getText()));
			if(Integer.parseInt(txtFactProv.getText())==factProvEncontrada.get(0).getFactProv()){
				String nl = System.getProperty("line.separator");
				JOptionPane.showMessageDialog(null, "Se ha detectado una compra ingresada con la misma factura proveedor, por favor revise los siguientes datos:"+nl+"N° COMPRA: "+factProvEncontrada.get(0).getIdCompra()+nl+"FECHA REGISTRO: "+convertirDateAString(factProvEncontrada.get(0).getFechaGeneracion())+nl+"VALOR TOTAL: "+formatearNumero(factProvEncontrada.get(0).getTotalCompra()));
				txtFactProv.setText("");
			}else{
				
			}
		}catch(Exception e){
			e.getMessage();
		}
	}
}
