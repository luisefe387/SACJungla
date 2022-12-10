package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

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
import co.com.jungla.sac.persistencia.entidades.Cliente;
import co.com.jungla.sac.persistencia.entidades.ControlInventario;
import co.com.jungla.sac.persistencia.entidades.Cotizacion;
import co.com.jungla.sac.persistencia.entidades.DetalleCotizacion;
import co.com.jungla.sac.persistencia.entidades.FormaPagoCliente;
import co.com.jungla.sac.persistencia.entidades.Persona;
import co.com.jungla.sac.persistencia.entidades.Vendedor;
import co.com.jungla.sac.persistencia.entidades.VentaArticulos;

import co.com.jungla.sac.negocio.delegados.DelegadoArticulo;
import co.com.jungla.sac.negocio.delegados.DelegadoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoControlInventario;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleCotizacion;
import co.com.jungla.sac.negocio.delegados.DelegadoFormaPagoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoVendedor;
import co.com.jungla.sac.negocio.delegados.DelegadoVentaArticulos;
import com.mxrck.autocompleter.TextAutoCompleter;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro de la venta de articulos, su movimiento de venta en el kardex electronico y su contabilizacion
 * @author Luis Fernando Pedroza T.
 * @version: 19/09/2016
 */
public class VentRegistrarVentaArticulos extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JPanel pnUe;
	private JTextField txtCodigo;
	private JTextField txtArticulo;
	private JTextField txtLinea;
	private JTextField txtUe;
	private JTextField txtCant;
	private JTextField txtSaldo;
	private JTextField txtSaldoPendiente;
	private static JTextField txtSubtotal;
	private static JTextField txtTotalVenta;
	private static JTextField txtOrden;
	private static JTextField txtPedido;
	private static JTextField txtIdentificacion;
	private static JTextField txtNombre;
	private static JTextField txtItems;
	private static JTextField txtDctoCom;
	private static JTextField txtConsecutivo;
	private static JFormattedTextField txtVlrUnitario;
	private static JDateChooser dchFecha;
	private static JDateChooser dchFechaLimitePago;
	private static JComboBox cbVendedor;
	private static JComboBox cbFormaPago;
	private DefaultComboBoxModel modeloVendedor = new DefaultComboBoxModel();
	private DefaultComboBoxModel modeloFormaPago = new DefaultComboBoxModel();
	static DefaultTableModel modeloTbArticulos = new DefaultTableModel();
	private static JTable tbArticulos;
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private Double subtotal;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentRegistrarVentaArticulos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarVentaArticulos.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Venta de Articulos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1030, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 0, 0)));
		pn1.setBounds(10, 11, 991, 62);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JPanel pnCliente = new JPanel();
		pnCliente.setBounds(318, 11, 449, 38);
		pn1.add(pnCliente);
		pnCliente.setBackground(new Color(0, 51, 0));
		pnCliente.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCliente.setBackground(SystemColor.desktop);
		lblCliente.setForeground(SystemColor.window);
		lblCliente.setBounds(123, 0, 73, 14);
		pnCliente.add(lblCliente);
		
		txtIdentificacion = new JTextField();
		txtIdentificacion.setEditable(false);
		txtIdentificacion.setBounds(0, 17, 100, 20);
		pnCliente.add(txtIdentificacion);
		txtIdentificacion.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(104, 17, 272, 20);
		pnCliente.add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCliente();
			}
		});
		btnBuscar.setForeground(new Color(0, 51, 0));
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnBuscar.setBounds(380, 17, 68, 20);
		pnCliente.add(btnBuscar);
		
		JPanel pnFecha = new JPanel();
		pnFecha.setLayout(null);
		pnFecha.setBackground(new Color(0, 51, 0));
		pnFecha.setBounds(203, 11, 105, 38);
		pn1.add(pnFecha);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setBackground(SystemColor.desktop);
		lblFecha.setBounds(36, 0, 48, 14);
		pnFecha.add(lblFecha);
		
		dchFecha = new JDateChooser();
		dchFecha.setDate(new Date());
		dchFecha.setBounds(0, 18, 104, 20);
		dchFecha.setEnabled(false);
		pnFecha.add(dchFecha);
		
		JPanel pnConsecutivo = new JPanel();
		pnConsecutivo.setLayout(null);
		pnConsecutivo.setBackground(new Color(0, 51, 0));
		pnConsecutivo.setBounds(116, 11, 77, 38);
		pn1.add(pnConsecutivo);
		
		JLabel lblConsecutivo = new JLabel("Consecutivo");
		lblConsecutivo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConsecutivo.setForeground(Color.WHITE);
		lblConsecutivo.setBackground(Color.BLACK);
		lblConsecutivo.setBounds(5, 0, 77, 14);
		pnConsecutivo.add(lblConsecutivo);
		
		txtConsecutivo = new JTextField();
		txtConsecutivo.setEditable(false);
		txtConsecutivo.setBackground(new Color(152, 251, 152));
		txtConsecutivo.setColumns(10);
		txtConsecutivo.setBounds(0, 18, 77, 20);
		txtConsecutivo.setText(String.valueOf(sumarConsecutivo()));
		pnConsecutivo.add(txtConsecutivo);
		
		JPanel pnSaldoPendiente = new JPanel();
		pnSaldoPendiente.setLayout(null);
		pnSaldoPendiente.setBackground(new Color(0, 51, 0));
		pnSaldoPendiente.setBounds(777, 11, 129, 38);
		pn1.add(pnSaldoPendiente);
		
		JLabel lblSaldoPendiente = new JLabel("Saldo");
		lblSaldoPendiente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSaldoPendiente.setForeground(Color.WHITE);
		lblSaldoPendiente.setBackground(Color.BLACK);
		lblSaldoPendiente.setBounds(47, 0, 44, 14);
		pnSaldoPendiente.add(lblSaldoPendiente);
		
		txtSaldoPendiente = new JTextField();
		txtSaldoPendiente.setEditable(false);
		txtSaldoPendiente.setColumns(10);
		txtSaldoPendiente.setBounds(0, 18, 129, 20);
		pnSaldoPendiente.add(txtSaldoPendiente);
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(null);
		pn2.setBorder(new LineBorder(new Color(0, 0, 0)));
		pn2.setBounds(10, 164, 991, 62);
		contentPane.add(pn2);
		
		JPanel pnCodigo = new JPanel();
		pnCodigo.setLayout(null);
		pnCodigo.setBackground(new Color(0, 51, 0));
		pnCodigo.setBounds(52, 11, 77, 38);
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
		pnArticulo.setBounds(139, 11, 254, 38);
		pn2.add(pnArticulo);
		
		JLabel lblArticulo = new JLabel("Articulo (Busqueda facil)");
		lblArticulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblArticulo.setForeground(Color.WHITE);
		lblArticulo.setBackground(SystemColor.desktop);
		lblArticulo.setBounds(61, 0, 160, 14);
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
		pnLinea.setBounds(403, 11, 155, 38);
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
		pnUe.setBounds(568, 11, 37, 38);
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
		pnCant.setBounds(615, 11, 53, 38);
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
		
		JPanel pnVlrUnitario = new JPanel();
		pnVlrUnitario.setLayout(null);
		pnVlrUnitario.setBackground(new Color(0, 51, 0));
		pnVlrUnitario.setBounds(678, 11, 110, 38);
		pn2.add(pnVlrUnitario);
		
		JLabel lblVlrUnitario = new JLabel("Vlr Unitario");
		lblVlrUnitario.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVlrUnitario.setForeground(Color.WHITE);
		lblVlrUnitario.setBackground(SystemColor.desktop);
		lblVlrUnitario.setBounds(29, 0, 75, 14);
		pnVlrUnitario.add(lblVlrUnitario);
		
		txtVlrUnitario = new JFormattedTextField();
		txtVlrUnitario.setColumns(10);
		formatearAMoneda(txtVlrUnitario);
		txtVlrUnitario.setBounds(0, 18, 110, 20);
		pnVlrUnitario.add(txtVlrUnitario);
		
		JPanel pnSaldo = new JPanel();
		pnSaldo.setLayout(null);
		pnSaldo.setBackground(new Color(0, 51, 0));
		pnSaldo.setBounds(798, 11, 79, 38);
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
		btnAgregarArticulo.setBounds(887, 11, 51, 38);
		pn2.add(btnAgregarArticulo);
		
		JPanel pn3 = new JPanel();
		pn3.setBorder(new LineBorder(new Color(0, 0, 0)));
		pn3.setBounds(10, 241, 991, 262);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JScrollPane scrArticulos = new JScrollPane();
		scrArticulos.setBounds(2, 2, 987, 258);
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
		pn4.setBorder(new LineBorder(new Color(0, 0, 0)));
		pn4.setBounds(10, 514, 991, 62);
		contentPane.add(pn4);
		pn4.setLayout(null);
		
		JPanel pnSubtotal = new JPanel();
		pnSubtotal.setLayout(null);
		pnSubtotal.setBackground(new Color(0, 51, 0));
		pnSubtotal.setBounds(86, 11, 104, 38);
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
		
		JPanel pnTotalVenta = new JPanel();
		pnTotalVenta.setLayout(null);
		pnTotalVenta.setBackground(new Color(0, 51, 0));
		pnTotalVenta.setBounds(314, 11, 104, 38);
		pn4.add(pnTotalVenta);
		
		JLabel lblTotalVenta = new JLabel("Total Venta");
		lblTotalVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalVenta.setForeground(Color.WHITE);
		lblTotalVenta.setBackground(SystemColor.desktop);
		lblTotalVenta.setBounds(21, 0, 79, 14);
		pnTotalVenta.add(lblTotalVenta);
		
		txtTotalVenta = new JTextField();
		txtTotalVenta.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTotalVenta.setForeground(new Color(152, 251, 152));
		txtTotalVenta.setBackground(new Color(153, 153, 102));
		txtTotalVenta.setColumns(10);
		txtTotalVenta.setText(formatearNumero(0.0));
		txtTotalVenta.setBounds(0, 18, 104, 20);
		txtTotalVenta.setEditable(false);
		pnTotalVenta.add(txtTotalVenta);
		
		//Funcionalidad del boton quitar articulo en la que elimina los articulos seleccionados
		JButton btnQuitarArticulo = new JButton("Quitar Articulo");
		btnQuitarArticulo.setForeground(new Color(0, 51, 0));
		btnQuitarArticulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuitarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarArticuloATbArticulos();
			}
		});
		btnQuitarArticulo.setBounds(521, 20, 117, 23);
		pn4.add(btnQuitarArticulo);
		
		JButton btnVerificarVenta = new JButton("Verificar Venta");
		btnVerificarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
		});
		btnVerificarVenta.setForeground(new Color(255, 0, 0));
		btnVerificarVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVerificarVenta.setBounds(669, 20, 140, 23);
		pn4.add(btnVerificarVenta);
		
		JPanel pnItems = new JPanel();
		pnItems.setLayout(null);
		pnItems.setBackground(new Color(0, 51, 0));
		pnItems.setBounds(428, 11, 60, 38);
		pn4.add(pnItems);
		
		JLabel lblItems = new JLabel("Items");
		lblItems.setForeground(Color.WHITE);
		lblItems.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblItems.setBackground(SystemColor.desktop);
		lblItems.setBounds(13, 0, 49, 14);
		pnItems.add(lblItems);
		
		txtItems = new JTextField();
		txtItems.setText("0");
		txtItems.setEditable(false);
		txtItems.setColumns(10);
		txtItems.setBounds(0, 18, 60, 20);
		pnItems.add(txtItems);
		
		JPanel pnDctoCom = new JPanel();
		pnDctoCom.setLayout(null);
		pnDctoCom.setBackground(new Color(0, 51, 0));
		pnDctoCom.setBounds(200, 11, 104, 38);
		pn4.add(pnDctoCom);
		
		JLabel lblDctoCom = new JLabel("Dcto Com.");
		lblDctoCom.setForeground(Color.WHITE);
		lblDctoCom.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDctoCom.setBackground(SystemColor.desktop);
		lblDctoCom.setBounds(32, 0, 60, 14);
		pnDctoCom.add(lblDctoCom);
		
		txtDctoCom = new JTextField();
		txtDctoCom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				calcularTotalVenta();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				calcularTotalVenta();
			}
		});
		txtDctoCom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_TAB ){
					calcularTotalVenta();
				}
			}
		});
		txtDctoCom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularTotalVenta();

			}
		});
		txtDctoCom.setText("0.0");
		txtDctoCom.setColumns(10);
		txtDctoCom.setBounds(0, 18, 86, 20);
		pnDctoCom.add(txtDctoCom);
		
		JLabel label_1 = new JLabel("%");
		label_1.setBounds(87, 17, 17, 22);
		pnDctoCom.add(label_1);
		
		JPanel pn5 = new JPanel();
		pn5.setLayout(null);
		pn5.setBorder(new LineBorder(new Color(0, 0, 0)));
		pn5.setBounds(10, 87, 991, 62);
		contentPane.add(pn5);
		
		JPanel pnVendedor = new JPanel();
		pnVendedor.setLayout(null);
		pnVendedor.setBackground(new Color(0, 51, 0));
		pnVendedor.setBounds(251, 11, 263, 38);
		pn5.add(pnVendedor);
		
		cbVendedor = new JComboBox();
		cbVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearVendedor();
			}
		});
		cbVendedor.setBounds(0, 16, 263, 22);
		pnVendedor.add(cbVendedor);
		
		JLabel lblVendedor = new JLabel("Vendedor");
		lblVendedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVendedor.setForeground(Color.WHITE);
		lblVendedor.setBackground(Color.BLACK);
		lblVendedor.setBounds(100, 0, 99, 14);
		pnVendedor.add(lblVendedor);
		
		JPanel pnOrden = new JPanel();
		pnOrden.setLayout(null);
		pnOrden.setBackground(new Color(0, 51, 0));
		pnOrden.setBounds(75, 11, 66, 38);
		pn5.add(pnOrden);
		
		JLabel lblOrden = new JLabel("Orden");
		lblOrden.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrden.setForeground(Color.WHITE);
		lblOrden.setBackground(Color.BLACK);
		lblOrden.setBounds(16, 0, 46, 14);
		pnOrden.add(lblOrden);
		
		txtOrden = new JTextField();
		txtOrden.setColumns(10);
		txtOrden.setBounds(0, 18, 66, 20);
		pnOrden.add(txtOrden);
		
		JPanel pnPedido = new JPanel();
		pnPedido.setLayout(null);
		pnPedido.setBackground(new Color(0, 51, 0));
		pnPedido.setBounds(151, 11, 90, 38);
		pn5.add(pnPedido);
		
		JLabel lblPedido = new JLabel("Pedido");
		lblPedido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPedido.setForeground(Color.WHITE);
		lblPedido.setBackground(Color.BLACK);
		lblPedido.setBounds(30, 0, 46, 14);
		pnPedido.add(lblPedido);
		
		txtPedido = new JTextField();
		txtPedido.setColumns(10);
		txtPedido.setBounds(0, 18, 90, 20);
		pnPedido.add(txtPedido);
		
		JPanel pnFormaDePago = new JPanel();
		pnFormaDePago.setLayout(null);
		pnFormaDePago.setBackground(new Color(0, 51, 0));
		pnFormaDePago.setBounds(524, 11, 263, 38);
		pn5.add(pnFormaDePago);
		
		cbFormaPago = new JComboBox();
		cbFormaPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearFormaPago();
				calcularDiasPorFormaPago();
			}
		});
		cbFormaPago.setBounds(0, 16, 263, 22);
		pnFormaDePago.add(cbFormaPago);
		
		JLabel lblFormaDePago = new JLabel("Forma de Pago");
		lblFormaDePago.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFormaDePago.setForeground(Color.WHITE);
		lblFormaDePago.setBackground(Color.BLACK);
		lblFormaDePago.setBounds(92, 0, 99, 14);
		pnFormaDePago.add(lblFormaDePago);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBackground(new Color(0, 51, 0));
		panel_8.setBounds(797, 11, 125, 38);
		pn5.add(panel_8);
		
		JLabel lblFechaLimitePago = new JLabel("Fecha Limite Pago");
		lblFechaLimitePago.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaLimitePago.setForeground(Color.WHITE);
		lblFechaLimitePago.setBackground(Color.BLACK);
		lblFechaLimitePago.setBounds(12, 0, 105, 14);
		panel_8.add(lblFechaLimitePago);
		
		dchFechaLimitePago = new JDateChooser();
		dchFechaLimitePago.setBounds(0, 18, 125, 20);
		validarFechaLimitePago();
		panel_8.add(dchFechaLimitePago);
		
		//Metodos que debe ejecutar la ventana al inicializarse
		listarVendedores();
		buscarArticuloPorNombre();
		listarFormaPagos();
		iniciarDatosPorDefecto();
		
	}
	
	//Metodo para dar valores iniciales a los datos por defecto
	private static void iniciarDatosPorDefecto(){
		DelegadoCliente delegadoCliente = new DelegadoCliente();
		List<Cliente> clientePorDefecto = delegadoCliente.traerClientePorIdentificacion("0000000001");
		txtIdentificacion.setText(clientePorDefecto.get(0).getIdentificacion());
		txtNombre.setText(clientePorDefecto.get(0).getNombre());
		cbVendedor.setSelectedIndex(1);
		cbFormaPago.setSelectedIndex(1);
		dchFechaLimitePago.setDate(new Date());
		
		limpiarTablaArticulos();
		if(tbArticulos.getModel().getColumnCount()==0){
			llenarColumnasTbArticulos();
		}
	}
	
	//Metodo para encontrar los datos del articulo incluyendo su linea mediante el codigo
	private void cargarDatosArticuloPorNombre() {
		try{
			DelegadoArticulo delegadoArticulo =new DelegadoArticulo();
			Articulo datosArticulo=delegadoArticulo.traerLineaUnidadArticulo(txtArticulo.getText()).get(0);
			if(datosArticulo.getEstado().equals("Inactivo")){
				JOptionPane.showMessageDialog(null, "El articulo "+datosArticulo.getNombre()+" se encuentra INACTIVO");
				txtArticulo.setText("");
			}else{
				if(datosArticulo.getControlInventario().getCantExistencia()==0){
					JOptionPane.showMessageDialog(null, "El articulo \""+datosArticulo.getNombre()+"\" ya no tiene existencias en el inventario");
					txtArticulo.setText("");
				}else{
					txtCodigo.setText(Integer.toString(datosArticulo.getCodigo()));
					txtLinea.setText(datosArticulo.getLineaArticulos().getNombreL());
					txtUe.setText(datosArticulo.getUnidadMedida().getAbreviatura().toString());
					txtVlrUnitario.setText(formatearNumero(datosArticulo.getControlInventario().getPrecioTotalVenta()));
					txtSaldo.setText(String.valueOf(datosArticulo.getControlInventario().getCantExistencia()));
				}
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
				if(datosArticuloInventario.getCantExistencia()==0){
					JOptionPane.showMessageDialog(null, "El articulo \""+datosArticulo.getNombre()+"\" ya no tiene existencias en el inventario");
					txtCodigo.setText("");
				}else{
					txtArticulo.setText(datosArticulo.getNombre());
					txtLinea.setText(datosArticulo.getLineaArticulos().getNombreL());
					txtUe.setText(datosArticulo.getUnidadMedida().getAbreviatura().toString());
					txtVlrUnitario.setValue(datosArticuloInventario.getPrecioTotalVenta());
					txtSaldo.setText(String.valueOf(datosArticuloInventario.getCantExistencia()));
				}
			}
			
		}catch(Exception ex){
			ex.getMessage();
			JOptionPane.showMessageDialog(null, "El codigo "+txtCodigo.getText()+" no existe");
			limpiarDatosArticulo();
		}
		
	}
	
	//Metodo para limpiar las cajas de texto en la que se consulto los atributos del articulo
	private void limpiarDatosArticulo() {
		txtCodigo.setText("");
		txtArticulo.setText("");
		txtLinea.setText("");
		txtUe.setText("");
		txtCant.setText("1");
		txtVlrUnitario.setValue(0);
		txtSaldo.setText("");
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
	
	//Metodo para abrir la ventana buscar cliente
	private void buscarCliente() {
		VentBuscarCliente ventBuscarCliente = new VentBuscarCliente();
		ventBuscarCliente.setVisible(true);
		setVisible(false);
	}
	
	//Metodo para agregar los datos de la venta desde la ventana buscar cliente
	public void agregarDatosAVentaArticulos(String identificacion, String nombre){
		txtIdentificacion.setText(identificacion);
		txtNombre.setText(nombre);
		calcularSaldoPendientePorCliente(identificacion);
	}
	
	//Metodo para convertir un numero corriente en formato de pesos y decimales
	private static String formatearNumero(Double numero){
		NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es","CO"));
		return formato.format(numero); 
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
	
	//Metodo para sumar el consecutivo del codigo de la venta y ser mostrado en la interfaz
	private static int sumarConsecutivo(){
		DelegadoVentaArticulos delegadoVentaArticulos = new DelegadoVentaArticulos();
		int consecutivo;
		if(delegadoVentaArticulos.traerUltimaVentaArticulo()==null){
			consecutivo = 1;
		}else{
			List<VentaArticulos> ultimaVenta = delegadoVentaArticulos.traerUltimaVentaArticulo();
			consecutivo = ultimaVenta.get(0).getIdVenta() + 1;
		}
		return consecutivo;		
	}
	
	//Metodo para listar los vendedores y desplegarlos en un combo box
	private void listarVendedores() {
		DelegadoVendedor delegadoVendedor = new DelegadoVendedor();
		List<Vendedor> vendedores = delegadoVendedor.listarVendedor();
		modeloVendedor.addElement("--ELIJA VENDEDOR--");
		cbVendedor.setModel(modeloVendedor);
		
		for(Vendedor vendedor : vendedores){
			modeloVendedor.addElement(new Persona (vendedor.getNombre(), vendedor.getIdentificacion()));
			cbVendedor.setModel(modeloVendedor);
		}
		
		modeloVendedor.addElement("--NUEVO VENDEDOR--");
		cbVendedor.setModel(modeloVendedor);
	}
	
	//Metodo para crear vendedor en caso de que no aparezca en el combo box
	private void crearVendedor() {
		if(cbVendedor.getSelectedItem().equals("--NUEVO VENDEDOR--")){
			VentRegistrarVendedor ventRegistrarVendedor = new VentRegistrarVendedor();
			cbVendedor.removeAllItems();
			ventRegistrarVendedor.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                	listarVendedores();  
                }
            });
			ventRegistrarVendedor.setVisible(true);
		}
	}
	
	//Metodo para listar formas de pagos y desplegarlos en un combo box
	public void listarFormaPagos() {
		DelegadoFormaPagoCliente delegadoFormaPagoCliente = new DelegadoFormaPagoCliente();
		List<FormaPagoCliente> formaPagos = delegadoFormaPagoCliente.listarFormaPagoCliente();
		modeloFormaPago.addElement("--ELIJA FORMA DE PAGO--");
		cbFormaPago.setModel(modeloFormaPago);
		
		for(FormaPagoCliente formaPago : formaPagos){
			modeloFormaPago.addElement(new FormaPagoCliente (formaPago.getDescripcion(), formaPago.getIdFormaPagoCliente()));
			cbFormaPago.setModel(modeloFormaPago);
		}
		modeloFormaPago.addElement("--NUEVA FORMA DE PAGO--");
		cbFormaPago.setModel(modeloFormaPago);
	}
	
	//Metodo para crear las formas de pago luego de ser elegido dentro del combo box
	private void crearFormaPago() {
		if(cbFormaPago.getSelectedItem().equals("--NUEVA FORMA DE PAGO--")){
			VentRegistrarFormaPagoCliente ventRegistrarFormaPago = new VentRegistrarFormaPagoCliente();
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
	
	//Metodo para calcular los dias de acuerdo a la forma de pago elegida en el combo box
	private void calcularDiasPorFormaPago() {
		try{
			DelegadoFormaPagoCliente delegadoFormaPagoCliente = new DelegadoFormaPagoCliente();
			List<FormaPagoCliente> formasPago = delegadoFormaPagoCliente.traerFormaPagoClientePorDescripcion(cbFormaPago.getSelectedItem().toString());
			dchFechaLimitePago.setDate(sumarDias(new Date(), formasPago.get(0).getDias()));
		}catch (Exception e) {
			e.getMessage();
		}
	}
	
	//Metodo para sumar dias a una fecha determinada
	public Date sumarDias(Date fechaAAplicar, int dias){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaAAplicar); 
		calendar.add(Calendar.DAY_OF_YEAR, dias);  
		return calendar.getTime(); 
	}
	
	//Metodo para validar los datos ingresados del articulo agregandolos a la tabla y a su vez calculando
	//la cantidad de items, el subtotal y el total
	private void validarIngresoArticuloATbArticulos() {
		if(txtCodigo.getText().equals("")||txtArticulo.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Debe Seleccionar el ARTICULO primero ingresando el codigo o articulo");
		}else{
			if("".equals(txtCant.getText())){
				JOptionPane.showMessageDialog(null, "Debe Ingresar la cantidad del ARTICULO");
			}else{
				if(txtVlrUnitario.getValue().equals(0)){
					JOptionPane.showMessageDialog(null, "Debe ingresar el VALOR UNITARIO para este item");
				}
				else{
					agregarArticuloATbArticulos();
					calcularSubtotal();
					calcularCantidadArticulos();
					calcularTotalVenta();
				}
			}
		}
	}
	
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private static void llenarColumnasTbArticulos() {
		modeloTbArticulos.addColumn("Codigo");
		modeloTbArticulos.addColumn("Linea");
		modeloTbArticulos.addColumn("Articulo");
		modeloTbArticulos.addColumn("Cant");
		modeloTbArticulos.addColumn("Unidad");
		modeloTbArticulos.addColumn("Vlr Unitario");
		modeloTbArticulos.addColumn("Total");
		
		tbArticulos.setModel(modeloTbArticulos);
	}
	
	//Metodo para agregar el articulo en la tabla
	private void agregarArticuloATbArticulos() {
		if(Integer.parseInt(txtCant.getText())>Integer.parseInt(txtSaldo.getText())){
			JOptionPane.showMessageDialog(null, "La cantidad ingresada para este articulo es superior a la que hay en existencia en Inventario.");
			txtCant.setText("");
		}else{
			Double total = (double) (Integer.parseInt(txtCant.getText())) *(Double.parseDouble(txtVlrUnitario.getValue().toString()));

			String [] fila = new String[modeloTbArticulos.getColumnCount()];
			Boolean aviso = false;
			
			fila[0]= txtCodigo.getText();
			fila[1]= txtLinea.getText();
			fila[2]= txtArticulo.getText();
			fila[3]= txtCant.getText();
			fila[4]= txtUe.getText();
			fila[5]= formatearNumero(Double.parseDouble(txtVlrUnitario.getValue().toString()));
			fila[6]= formatearNumero(total);
	        
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
	            	 double vrlUnitario = Double.parseDouble(desformatearNumero(String.valueOf(modelo.getValueAt(fila, 5))));
	            	 double total = cant*vrlUnitario; 
	            	 modelo.setValueAt(formatearNumero(total),fila, 6);	
	            	 calcularCantidadArticulos();
	            	 calcularSubtotal();
	            	 calcularTotalVenta();
	            	 
	            }catch(Exception ex){
	            	ex.getMessage();
	            }
            }
        }
		
	}
	
	//Metodo para quitar los articulos ya ingresados en la tabla
	private void quitarArticuloATbArticulos() {
		try{
			DefaultTableModel modeloArticuloAEliminar = (DefaultTableModel) tbArticulos.getModel();
			modeloArticuloAEliminar.removeRow(tbArticulos.getSelectedRow()); 
			calcularSubtotal();
			calcularCantidadArticulos();
			calcularTotalVenta();
		}catch(Exception e){
			e.getMessage();
			JOptionPane.showMessageDialog(null, "Debe seleccionar cualquier articulo para ser eliminado");
		}
	}
	
	//Metodo para convertir un numero en formato de pesos y decimales en un numero corriente
	private String desformatearNumero(String numero){
		String numeroReemplazado=numero.replace("$", "");
		String numeroReemplazado1=numeroReemplazado.replace(".", "");
		String numeroReemplazado2=numeroReemplazado1.replace(",", ".");
		return numeroReemplazado2;
	}
	
	//Metodo para calcular el subtotal de los articulos ingresados a la tabla 
	private void calcularSubtotal(){
		
		Double subtotal1 = (double) 0;
		
		for(int i=0; i<tbArticulos.getRowCount(); i++) {
			subtotal= Double.parseDouble(desformatearNumero(String.valueOf(tbArticulos.getValueAt(i,6))));
			subtotal1 += subtotal;
		}
		
		txtSubtotal.setText(formatearNumero(subtotal1));
	}
	
	//Metodo para calcular la cantidad de articulos registrados en la tabla
	private void calcularCantidadArticulos() {
		
		int cantidad = tbArticulos.getRowCount();
		txtItems.setText(Integer.toString(cantidad));
		
	}
	
	//Metodo para calcular el descuento de los articulos ingresados a la tabla para finalmente hallar el total de la venta
	private void calcularTotalVenta() {
		try{
			Double descuento = (1-(Double.parseDouble(txtDctoCom.getText())/100))* Double.parseDouble(desformatearNumero(txtSubtotal.getText()));
			txtTotalVenta.setText(formatearNumero(descuento));
		}catch(Exception e){
			e.getMessage();
			txtTotalVenta.setText(txtSubtotal.getText());
		}
		
	}
	
	//Metodo para limitar la fecha limite pago en solo dias futuros	a partir de la fecha actual
	private void validarFechaLimitePago() {
		dchFechaLimitePago.setSelectableDateRange(new Date(), null);
	}
	
	//Metodo para validar los datos ingresados de la venta incluyendo la tabla de articulos
	private void validarDatos() {
		
		if(txtIdentificacion.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Debe buscar el cliente por identificación o nombre");
		}else{
			if(txtNombre.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Debe buscar el cliente por identificación o nombre");
			}else{
				if(cbVendedor.getSelectedItem()=="--ELIJA VENDEDOR--"){
					JOptionPane.showMessageDialog(null, "Debe elegir un vendedor");
				}else{
					if(cbFormaPago.getSelectedItem()=="--ELIJA FORMA DE PAGO--"){
						JOptionPane.showMessageDialog(null, "Debe elegir una forma de pago");
					}else{
						if(dchFechaLimitePago.getDate()==null){
							JOptionPane.showMessageDialog(null, "Debe ingresar la fecha limite de pago");
						}else{
							if(tbArticulos.getRowCount() == 0 ){
								JOptionPane.showMessageDialog(null, "Debe ingresar articulos en la tabla");
							}else{
								verificarVenta();
							}
						}
					}
				} 
			}
		}
	}
		
	//Metodo para registrar los datos ingresados de la venta
	private void verificarVenta() {
		
		if("0000000001".equals(txtIdentificacion.getText()) && "CONTADO".equals(cbFormaPago.getSelectedItem().toString())){
			
			abrirVentVerificarVentaParaContado();
			
		}else{
			
			if(txtIdentificacion.getText()!="0000000001" && "CONTADO".equals(cbFormaPago.getSelectedItem().toString())){
				
				abrirVentVerificarVentaParaContado();
	
			}else{
				if("0000000001".equals(txtIdentificacion.getText()) && cbFormaPago.getSelectedItem().toString() != "CONTADO"){
					JOptionPane.showMessageDialog(null, "Se ha seleccionado el CLIENTE de VENTAS DE CONTADO Y/O MOSTRADOR y por esta razon no se puede generar esta VENTA CREDITO");
				}else{
					abrirVenVerificarVentaParaCredito();
				}
			}
			
		}
	}
	
	//Metodo para abrir la ventana verificar venta para forma de pago a credito
	private void abrirVenVerificarVentaParaCredito() {
		Persona vendedorElegido = (Persona) cbVendedor.getSelectedItem();
		FormaPagoCliente formaPagoElegido = (FormaPagoCliente) cbFormaPago.getSelectedItem();
		VentVerificarVentaCredito ventVerificarCredito = new VentVerificarVentaCredito(txtIdentificacion.getText(), vendedorElegido.getIdentificacion(), formaPagoElegido.getIdFormaPagoCliente(), dchFechaLimitePago.getDate(), txtSubtotal.getText(), txtDctoCom.getText(), txtTotalVenta.getText(), modeloTbArticulos, txtOrden.getText(),txtPedido.getText(), txtItems.getText());
		ventVerificarCredito.setVisible(true);
		
	}

	//Metodo para abrir la ventana verificar venta para forma de pago a contado
	private void abrirVentVerificarVentaParaContado() {
		Persona vendedorElegido = (Persona) cbVendedor.getSelectedItem();
		FormaPagoCliente formaPagoElegido = (FormaPagoCliente) cbFormaPago.getSelectedItem();
		VentVerificarVentaContado ventVerificarVenta = new VentVerificarVentaContado(txtIdentificacion.getText(), vendedorElegido.getIdentificacion(), formaPagoElegido.getIdFormaPagoCliente(), dchFechaLimitePago.getDate(), txtSubtotal.getText(), txtDctoCom.getText(), txtTotalVenta.getText(), modeloTbArticulos, txtOrden.getText(), txtPedido.getText(), txtItems.getText());
		ventVerificarVenta.setVisible(true);
	}
	
	//Metodo para limpiar los datos ya registrados
	public static void limpiarDatos() {
		txtConsecutivo.setText(String.valueOf(sumarConsecutivo()));
		dchFecha.setDate(new Date());
		iniciarDatosPorDefecto();
		txtOrden.setText("");
		txtPedido.setText("");
		limpiarTablaArticulos();
		txtSubtotal.setText(formatearNumero(0.0));
		txtDctoCom.setText("0.0");
		txtTotalVenta.setText(formatearNumero(0.0));
		txtItems.setText("0");
		txtVlrUnitario.setValue(0);
	}

	//Metodo para limpiar la tabla de articulos 
	private static void limpiarTablaArticulos() {
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
	
	//Metodo para agregar los datos de la cotizacion a la venta
	public void agregarDatosAVentas(Cotizacion datosCotizacion){

		DelegadoDetalleCotizacion delegadoDetalleCotizacion = new DelegadoDetalleCotizacion();
		List<DetalleCotizacion> detalleCotizacion= delegadoDetalleCotizacion.listarDetallePorCodigoCotizacion(datosCotizacion.getIdCotizacion());
			
		txtIdentificacion.setText(datosCotizacion.getIdentificacionCliente());
		txtNombre.setText(datosCotizacion.getNombreCliente());
		cbFormaPago.setSelectedIndex(1);
		cbVendedor.setSelectedIndex(1);
		dchFecha.setDate(new Date());
		txtSubtotal.setText(formatearNumero(datosCotizacion.getSubtotal()));
		txtDctoCom.setText(String.valueOf(datosCotizacion.getDescuento()));
		txtTotalVenta.setText(formatearNumero(datosCotizacion.getTotalCotizado()));
		txtItems.setText(Integer.toString(datosCotizacion.getItems()));
		llenarModeloDetalleCotizacion(detalleCotizacion);
		calcularSaldoPendientePorCliente(datosCotizacion.getIdentificacionCliente());
	}
	
	//Metodo para calcular el saldo pendiente del cliente
	private void calcularSaldoPendientePorCliente(String identificacionCliente) {
		double acumulador = 0;
		double saldoPendiente = 0;
		DelegadoVentaArticulos delegadoVentaArticulos = new DelegadoVentaArticulos();
		List<VentaArticulos> listaVentasPendientes = delegadoVentaArticulos.traerVentaPendientePorCliente(identificacionCliente, "Pendiente");
		
		for(VentaArticulos ventasPedientes : listaVentasPendientes){
			acumulador = ventasPedientes.getTotalVenta();
			saldoPendiente +=acumulador;
		}
		txtSaldoPendiente.setText(formatearNumero(saldoPendiente));
	}

	//Metodo para llenar la tabla donde van a estar los datos del detalle de la cotizacion
	public void llenarModeloDetalleCotizacion(List<DetalleCotizacion> listaDetalleCotizacion){
		
		String [] fila = new String[modeloTbArticulos.getColumnCount()];
		
		for(DetalleCotizacion detalles : listaDetalleCotizacion ){
			fila[0]= Integer.toString(detalles.getArticulo().getCodigo());
			fila[1]= detalles.getArticulo().getLineaArticulos().getNombreL();
			fila[2]= detalles.getArticulo().getNombre();
			fila[3]= Integer.toString(detalles.getCantidad());
			fila[4]= detalles.getArticulo().getUnidadMedida().getNombre();
			fila[5]= formatearNumero(detalles.getVlrUnitario());
			fila[6]= formatearNumero(detalles.getTotal());
			
			modeloTbArticulos.addRow(fila);
		}
		tbArticulos.setModel(modeloTbArticulos);
	}
}
