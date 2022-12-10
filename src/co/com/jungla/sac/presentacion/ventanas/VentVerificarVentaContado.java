package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import javax.swing.DefaultCellEditor;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTable;

import co.com.jungla.sac.persistencia.entidades.AnticipoCliente;
import co.com.jungla.sac.persistencia.entidades.Articulo;
import co.com.jungla.sac.persistencia.entidades.Banco;
import co.com.jungla.sac.persistencia.entidades.Cliente;
import co.com.jungla.sac.persistencia.entidades.Contabilizacion;
import co.com.jungla.sac.persistencia.entidades.ControlInventario;
import co.com.jungla.sac.persistencia.entidades.DetalleVenta;
import co.com.jungla.sac.persistencia.entidades.DevolucionCliente;
import co.com.jungla.sac.persistencia.entidades.FormaPagoCliente;
import co.com.jungla.sac.persistencia.entidades.KardexElectronico;
import co.com.jungla.sac.persistencia.entidades.MedioPagoCliente;
import co.com.jungla.sac.persistencia.entidades.ReciboCaja;
import co.com.jungla.sac.persistencia.entidades.Vendedor;
import co.com.jungla.sac.persistencia.entidades.VentaArticulos;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.UIManager;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import co.com.jungla.sac.negocio.delegados.DelegadoAnticipoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoBanco;
import co.com.jungla.sac.negocio.delegados.DelegadoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoContabilizacion;
import co.com.jungla.sac.negocio.delegados.DelegadoControlInventario;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleVenta;
import co.com.jungla.sac.negocio.delegados.DelegadoDevolucionCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoFormaPagoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoKardexElectronico;
import co.com.jungla.sac.negocio.delegados.DelegadoMedioPagoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoReciboCaja;
import co.com.jungla.sac.negocio.delegados.DelegadoVendedor;
import co.com.jungla.sac.negocio.delegados.DelegadoVentaArticulos;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro de la venta de articulos de contado, el recibo de caja, el movimiento de venta en el kardex electronico
 *  el medio de pago y su contabilizacion.
 * @author Luis Fernando Pedroza T.
 * @version: 19/09/2016
 */
public class VentVerificarVentaContado extends JDialog {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JPanel pnDatosVenta;
	private JScrollPane scrObservaciones;
	private JScrollPane srcMedioPago;
	private JTextField txtCliente;
	private JTextField txtDir;
	private JTextField txtSubtotal;
	private JTextField txtDescuento;
	private JTextField txtFechaLimite;
	private JTextField txtIdentificacion;
	private JTextField txtTel;
	private JTextField txtVendedor;
	private JTextField txtFormaPago;
	private JFormattedTextField txtTotal;
	private JFormattedTextField txtRecibido;
	private JFormattedTextField txtCambio;
	private JFormattedTextField txtEfectivo;
	private JFormattedTextField txtTarjetaCredito;
	private JFormattedTextField txtTarjetaDebito;
	private JFormattedTextField txtCheques;
	private JFormattedTextField txtConsignaciones;
	private JFormattedTextField txtTotalRecibido;
	public static JFormattedTextField txtNotasCredito;
	private JFormattedTextField txtAnticipos;
	private JTextPane txaObservaciones;
	private JLabel lblRecibido;
	private JLabel lblCambio;
	private JLabel lblObservaciones;
	private JComboBox cbIdentificacion;
	private final JComboBox cbMedioPago;
	private JComboBox cbBancoDestino;
	private JComboBox cbFranquicia;
	private JButton btnModificar;
	private JButton btnCancelar;
	private JButton btnAgregar;
	private JButton btnQuitar;
	private JButton btnGuardarVenta;
	private JButton btnVolver;
	private JRadioButton rbEfectivo;
	private JRadioButton rbOtroMedio;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static JTable tbMedioPago;
	private JSeparator spr3;
	static DefaultTableModel modeloTbMediosPagos = new DefaultTableModel();
	DefaultComboBoxModel modeloBancos = new DefaultComboBoxModel();
	private TableModel modeloDetalles;
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private static int fila;
	private String ordenCompra;
	private String pedido;
	private Date fechaLimitePago;
	private List<VentaArticulos> ultimaVentaArticulo;
	private DetalleVenta ultimoDetalleVenta;
	private VentaArticulos ventaArticulos = new VentaArticulos();
	private Cliente cliente = new Cliente();
	private DelegadoDetalleVenta delegadoDetalleVenta = new DelegadoDetalleVenta();
	private List <KardexElectronico> ultimoRegistroPorArticuloKardex1;
	private KardexElectronico ultimoRegistroPorArticuloKardex;
	private List<ReciboCaja> ultimoRecibocaja;
	private ReciboCaja reciboCaja = new ReciboCaja();
	private double totalCostoVenta;
	private String items;
	private List<Banco> listaBancos;
	private List<Cliente> datosCliente;
	private Vendedor datosVendedor;
	private List<FormaPagoCliente> datosFormaPago;

	/**
	 * Metodo constructor con parametros necesarios para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentVerificarVentaContado(String identificacionCliente, String identificacionVendedor, int idFormaPagoCliente, Date fechaLimitePago, String subtotal, String dctoCom, String totalVenta, TableModel modeloDetalles, String ordenCompra, String pedido, String items) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentVerificarVentaContado.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Verificar los datos de la Venta a Contado");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 793, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pnDatosVenta = new JPanel();
		pnDatosVenta.setBounds(10, 10, 765, 495);
		contentPane.add(pnDatosVenta);
		pnDatosVenta.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCliente.setBounds(10, 23, 62, 14);
		pnDatosVenta.add(lblCliente);
		
		JLabel lblIdentificacion = new JLabel("Identificaci\u00F3n");
		lblIdentificacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIdentificacion.setBounds(10, 45, 95, 14);
		pnDatosVenta.add(lblIdentificacion);
		
		JLabel lblDatosCliente = new JLabel("Datos Cliente");
		lblDatosCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDatosCliente.setBounds(10, 69, 95, 14);
		pnDatosVenta.add(lblDatosCliente);
		
		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSubtotal.setBounds(10, 168, 95, 14);
		pnDatosVenta.add(lblSubtotal);
		
		JLabel lblDcto = new JLabel("Dcto");
		lblDcto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDcto.setBounds(10, 190, 95, 14);
		pnDatosVenta.add(lblDcto);
		
		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotal.setBounds(10, 212, 95, 14);
		pnDatosVenta.add(lblTotal);
		
		JSeparator spr1 = new JSeparator();
		spr1.setForeground(new Color(0, 51, 0));
		spr1.setBounds(10, 160, 511, 2);
		pnDatosVenta.add(spr1);
		
		JLabel lblMedioPago = new JLabel("Medio de Pago");
		lblMedioPago.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMedioPago.setBounds(10, 243, 110, 38);
		pnDatosVenta.add(lblMedioPago);
		
		lblRecibido = new JLabel("Recibido");
		lblRecibido.setForeground(new Color(204, 0, 0));
		lblRecibido.setBackground(new Color(255, 255, 255));
		lblRecibido.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblRecibido.setBounds(10, 284, 110, 38);
		pnDatosVenta.add(lblRecibido);
		
		lblCambio = new JLabel("Cambio");
		lblCambio.setForeground(new Color(204, 0, 0));
		lblCambio.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblCambio.setBounds(10, 324, 110, 38);
		pnDatosVenta.add(lblCambio);
		
		txtCliente = new JTextField();
		txtCliente.setEditable(false);
		txtCliente.setBounds(185, 21, 336, 20);
		pnDatosVenta.add(txtCliente);
		txtCliente.setColumns(10);
		
		txtDir = new JTextField();
		txtDir.setEditable(false);
		txtDir.setBounds(351, 67, 170, 20);
		pnDatosVenta.add(txtDir);
		txtDir.setColumns(10);
		
		txtSubtotal = new JTextField();
		txtSubtotal.setEditable(false);
		txtSubtotal.setBounds(185, 166, 336, 20);
		pnDatosVenta.add(txtSubtotal);
		txtSubtotal.setColumns(10);
		
		txtDescuento = new JTextField();
		txtDescuento.setEditable(false);
		txtDescuento.setBounds(185, 188, 336, 20);
		pnDatosVenta.add(txtDescuento);
		txtDescuento.setColumns(10);
		
		txtTotal = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtTotal.setEditable(false);
		txtTotal.setBounds(185, 210, 336, 20);
		pnDatosVenta.add(txtTotal);
		txtTotal.setColumns(10);
		
		btnGuardarVenta = new JButton("Registrar Venta");
		btnGuardarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
		});
		btnGuardarVenta.setForeground(new Color(0, 51, 0));
		btnGuardarVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGuardarVenta.setBounds(242, 459, 158, 23);
		pnDatosVenta.add(btnGuardarVenta);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTablaMediosPago();
				dispose();
			}
		});
		btnVolver.setForeground(new Color(0, 51, 0));
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVolver.setBounds(432, 459, 89, 23);
		pnDatosVenta.add(btnVolver);
		
		JLabel lblFechaLimite = new JLabel("Fecha Limite");
		lblFechaLimite.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaLimite.setBounds(10, 135, 95, 14);
		pnDatosVenta.add(lblFechaLimite);
		
		txtFechaLimite = new JTextField();
		txtFechaLimite.setEditable(false);
		txtFechaLimite.setColumns(10);
		txtFechaLimite.setBounds(185, 133, 336, 20);
		pnDatosVenta.add(txtFechaLimite);
		
		txtIdentificacion = new JTextField();
		txtIdentificacion.setEditable(false);
		txtIdentificacion.setBounds(257, 43, 264, 20);
		pnDatosVenta.add(txtIdentificacion);
		txtIdentificacion.setColumns(10);
		
		JLabel lblDir = new JLabel("Dir.");
		lblDir.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDir.setBounds(327, 70, 29, 14);
		pnDatosVenta.add(lblDir);
		
		txtTel = new JTextField();
		txtTel.setEditable(false);
		txtTel.setBounds(209, 67, 108, 20);
		pnDatosVenta.add(txtTel);
		txtTel.setColumns(10);
		
		JLabel lblTel = new JLabel("Tel.");
		lblTel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTel.setBounds(185, 70, 29, 14);
		pnDatosVenta.add(lblTel);
		
		JLabel lblVendedor = new JLabel("Vendedor");
		lblVendedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVendedor.setBounds(10, 91, 95, 14);
		pnDatosVenta.add(lblVendedor);
		
		txtVendedor = new JTextField();
		txtVendedor.setEditable(false);
		txtVendedor.setBounds(185, 89, 336, 20);
		pnDatosVenta.add(txtVendedor);
		txtVendedor.setColumns(10);
		
		JLabel lblFormaPago = new JLabel("Forma de Pago");
		lblFormaPago.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFormaPago.setBounds(10, 113, 95, 14);
		pnDatosVenta.add(lblFormaPago);
		
		txtFormaPago = new JTextField();
		txtFormaPago.setEditable(false);
		txtFormaPago.setBounds(185, 111, 336, 20);
		pnDatosVenta.add(txtFormaPago);
		txtFormaPago.setColumns(10);
		
		txtRecibido = new JFormattedTextField();
		txtRecibido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				calcularCambio();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				calcularCambio();
			}
		});
		txtRecibido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularCambio();
			}
		});
		txtRecibido.setFont(new Font("Tahoma", Font.BOLD, 18));
		formatearAMoneda(txtRecibido);
		txtRecibido.setBounds(185, 284, 336, 38);
		pnDatosVenta.add(txtRecibido);
		txtRecibido.setColumns(10);
		
		txtCambio = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtCambio.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtCambio.setEditable(false);
		txtCambio.setBounds(185, 324, 336, 38);
		pnDatosVenta.add(txtCambio);
		txtCambio.setColumns(10);
		
		JSeparator spr2 = new JSeparator();
		spr2.setForeground(new Color(0, 51, 0));
		spr2.setBounds(10, 237, 511, 2);
		pnDatosVenta.add(spr2);
		
		spr3 = new JSeparator();
		spr3.setForeground(new Color(0, 51, 0));
		spr3.setBounds(10, 446, 745, 2);
		pnDatosVenta.add(spr3);
		
		JPanel pnMediosPago = new JPanel();
		pnMediosPago.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		pnMediosPago.setBackground(SystemColor.inactiveCaption);
		pnMediosPago.setBounds(531, 148, 224, 210);
		pnDatosVenta.add(pnMediosPago);
		pnMediosPago.setLayout(null);
		
		JLabel lbEfectivo = new JLabel("Efectivo");
		lbEfectivo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbEfectivo.setBounds(10, 14, 46, 14);
		pnMediosPago.add(lbEfectivo);
		
		txtEfectivo = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtEfectivo.setEditable(false);
		txtEfectivo.setColumns(10);
		txtEfectivo.setBounds(114, 11, 100, 20);
		pnMediosPago.add(txtEfectivo);
		
		txtTarjetaCredito = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtTarjetaCredito.setEditable(false);
		txtTarjetaCredito.setColumns(10);
		txtTarjetaCredito.setBounds(114, 32, 100, 20);
		pnMediosPago.add(txtTarjetaCredito);
		
		JLabel lblTarjetaCredito = new JLabel("Tarjeta Cr\u00E9dito");
		lblTarjetaCredito.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTarjetaCredito.setBounds(10, 35, 100, 14);
		pnMediosPago.add(lblTarjetaCredito);
		
		JLabel lblTarjetaDebito = new JLabel("Tarjeta D\u00E9dito");
		lblTarjetaDebito.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTarjetaDebito.setBounds(10, 56, 100, 14);
		pnMediosPago.add(lblTarjetaDebito);
		
		txtTarjetaDebito = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtTarjetaDebito.setEditable(false);
		txtTarjetaDebito.setColumns(10);
		txtTarjetaDebito.setBounds(114, 53, 100, 20);
		pnMediosPago.add(txtTarjetaDebito);
		
		JLabel lblCheques = new JLabel("Cheques");
		lblCheques.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCheques.setBounds(10, 77, 62, 14);
		pnMediosPago.add(lblCheques);
		
		txtCheques = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtCheques.setEditable(false);
		txtCheques.setColumns(10);
		txtCheques.setBounds(114, 74, 100, 20);
		pnMediosPago.add(txtCheques);
		
		JLabel lblConsignaciones = new JLabel("Consignaciones");
		lblConsignaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConsignaciones.setBounds(10, 98, 93, 14);
		pnMediosPago.add(lblConsignaciones);
		
		txtConsignaciones = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtConsignaciones.setEditable(false);
		txtConsignaciones.setColumns(10);
		txtConsignaciones.setBounds(114, 95, 100, 20);
		pnMediosPago.add(txtConsignaciones);
		
		JLabel lblTotalRecibido = new JLabel("Total Recibido");
		lblTotalRecibido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalRecibido.setBounds(10, 181, 81, 14);
		pnMediosPago.add(lblTotalRecibido);
		
		txtTotalRecibido = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtTotalRecibido.setBackground(new Color(51, 255, 102));
		txtTotalRecibido.setEditable(false);
		txtTotalRecibido.setColumns(10);
		txtTotalRecibido.setBounds(114, 178, 100, 20);
		pnMediosPago.add(txtTotalRecibido);
		
		JSeparator sp = new JSeparator();
		sp.setBounds(10, 168, 204, 2);
		pnMediosPago.add(sp);
		
		JLabel lblNotasCredito = new JLabel("Notas Cr\u00E9dito");
		lblNotasCredito.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNotasCredito.setBounds(10, 119, 93, 14);
		pnMediosPago.add(lblNotasCredito);
		
		txtNotasCredito = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtNotasCredito.setEditable(false);
		txtNotasCredito.setColumns(10);
		txtNotasCredito.setBounds(114, 116, 100, 20);
		pnMediosPago.add(txtNotasCredito);
		
		txtAnticipos = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtAnticipos.setEditable(false);
		txtAnticipos.setColumns(10);
		txtAnticipos.setBounds(114, 137, 100, 20);
		pnMediosPago.add(txtAnticipos);
		
		JLabel lblAnticipos = new JLabel("Anticipos");
		lblAnticipos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAnticipos.setBounds(10, 140, 93, 14);
		pnMediosPago.add(lblAnticipos);
		
		lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaciones.setBounds(10, 364, 110, 71);
		pnDatosVenta.add(lblObservaciones);
		
		JPanel pnMedioPago = new JPanel();
		pnMedioPago.setBackground(new Color(51, 204, 102));
		pnMedioPago.setBounds(185, 243, 336, 38);
		pnDatosVenta.add(pnMedioPago);
		pnMedioPago.setLayout(null);
		
		JLabel lblCaja = new JLabel("");
		lblCaja.setIcon(new ImageIcon(VentVerificarVentaContado.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo caja.png")));
		lblCaja.setBounds(577, 14, 134, 128);
		pnDatosVenta.add(lblCaja);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarCliente();
			}
		});
		btnModificar.setForeground(new Color(0, 51, 0));
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnModificar.setBounds(79, 21, 86, 20);
		pnDatosVenta.add(btnModificar);
		
		cbIdentificacion = new JComboBox();
		cbIdentificacion.setEnabled(false);
		cbIdentificacion.setModel(new DefaultComboBoxModel(new String[] {"CC", "NIT", "TI", "CE"}));
		cbIdentificacion.setBounds(185, 43, 62, 20);
		pnDatosVenta.add(cbIdentificacion);
		
		scrObservaciones = new JScrollPane();
		scrObservaciones.setBounds(185, 364, 570, 71);
		pnDatosVenta.add(scrObservaciones);
		
		txaObservaciones = new JTextPane();
		scrObservaciones.setViewportView(txaObservaciones);
		
		rbEfectivo = new JRadioButton("Efectivo");
		rbEfectivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbEfectivo.isSelected()){
					accionesEfectivo();
				}
			}
		});
		buttonGroup.add(rbEfectivo);
		rbEfectivo.setBackground(new Color(51, 204, 102));
		rbEfectivo.setFont(new Font("Tahoma", Font.BOLD, 15));
		rbEfectivo.setBounds(68, 7, 93, 23);
		pnMedioPago.add(rbEfectivo);
		
		rbOtroMedio = new JRadioButton("Otro Medio");
		rbOtroMedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbOtroMedio.isSelected()){
					accionesOtroMedio();
				}
			}
		});
		buttonGroup.add(rbOtroMedio);
		rbOtroMedio.setBackground(new Color(51, 204, 102));
		rbOtroMedio.setFont(new Font("Tahoma", Font.BOLD, 15));
		rbOtroMedio.setBounds(163, 7, 149, 23);
		pnMedioPago.add(rbOtroMedio);
		
	
		
		cbMedioPago = new JComboBox();
		cbBancoDestino = new JComboBox();
		cbFranquicia = new JComboBox();
        
		srcMedioPago = new JScrollPane();
		srcMedioPago.setBounds(10, 292, 511, 106);
		srcMedioPago.setVisible(false);
		pnDatosVenta.add(srcMedioPago);
		srcMedioPago.setViewportView(tbMedioPago);
		tbMedioPago = new JTable();
		tbMedioPago.setVisible(false);
		srcMedioPago.setViewportView(tbMedioPago);
		
		setModal(true);

		//Metodo que debe ejecutar la ventana al inicializarse
		iniciarDatosVenta(identificacionCliente, identificacionVendedor, idFormaPagoCliente, subtotal, dctoCom, totalVenta, fechaLimitePago);
		
		//Inicializar algunas variables
		this.modeloDetalles=modeloDetalles;
		this.ordenCompra = ordenCompra;
		this.pedido = pedido;
		this.items = items;
		
	}
	//Metodo para iniciar los datos de la venta a esta ventana
	private void iniciarDatosVenta(String identificacionCliente, String identificacionVendedor, int idFormaPagoCliente, String subtotal, String dctoCom, String totalVenta, Date fechaLimitePago) {
		DelegadoCliente delegadoCliente = new DelegadoCliente();
		DelegadoVendedor delegadoVendedor = new DelegadoVendedor();
		DelegadoFormaPagoCliente delegadoFormaPagoCliente = new DelegadoFormaPagoCliente();
		
		datosCliente = delegadoCliente.traerClientePorIdentificacion(identificacionCliente);
		datosVendedor = delegadoVendedor.encontrarPorVendedor(identificacionVendedor);
		datosFormaPago = delegadoFormaPagoCliente.traerFormaPagoClientePorIdFormaPago(idFormaPagoCliente);
		
		txtCliente.setText(datosCliente.get(0).getNombre());
		if("0000000001".equals(datosCliente.get(0).getIdentificacion())){
			cbIdentificacion.setVisible(false);
			txtIdentificacion.setEditable(false);
			txtIdentificacion.setBounds(185, 43, 336, 20);
			txtIdentificacion.setText(datosCliente.get(0).getIdentificacion());
			btnModificar.setVisible(true);
		}else{
			txtIdentificacion.setEditable(false);
			txtIdentificacion.setBounds(185, 43, 336, 20);
			cbIdentificacion.setVisible(false);
			txtIdentificacion.setText(datosCliente.get(0).getIdentificacion());
			btnModificar.setVisible(false);
			
		}
		txtDir.setText(datosCliente.get(0).getDireccion());
		txtTel.setText(datosCliente.get(0).getTelefono());
		txtVendedor.setText(datosVendedor.getNombre());
		txtFormaPago.setText(datosFormaPago.get(0).getDescripcion());
		txtFechaLimite.setText(convertirDateAString(fechaLimitePago));
		this.fechaLimitePago = fechaLimitePago;
		txtSubtotal.setText(subtotal);
		txtDescuento.setText(dctoCom);
		txtTotal.setValue(Double.parseDouble(desformatearNumero(totalVenta)));
		rbEfectivo.setSelected(true);
		txtEfectivo.setValue(Double.parseDouble(desformatearNumero(totalVenta)));
		txtRecibido.setValue(Double.parseDouble(txtEfectivo.getValue().toString()));
		txtTarjetaCredito.setValue(0);
		txtTarjetaDebito.setValue(0);
		txtCheques.setValue(0);
		txtConsignaciones.setValue(0);
		txtNotasCredito.setValue(0);
		txtAnticipos.setValue(0);
		calcularTotalRecibido();
		calcularCambio();
		
	}
	
	//Metodo para convertir una fecha de tipo date a una cadena
	public String convertirDateAString(Date strFecha){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(strFecha);
		
		return fecha;
	}
	
	//Metodo para modificar identificacion y nombre del cliente
	private void modificarCliente() {
		txtIdentificacion.setBounds(257, 43, 264, 20);
		txtIdentificacion.setText("");
		cbIdentificacion.setBounds(185, 43, 62, 20);
		cbIdentificacion.setVisible(true);
		cbIdentificacion.setEnabled(true);
		txtIdentificacion.setEditable(true);
		txtCliente.setText("");
		txtCliente.setEditable(true);
		btnModificar.setVisible(false);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarCliente();
			}
		});
		btnCancelar.setForeground(new Color(0, 51, 0));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnCancelar.setBounds(79, 21, 86, 20);
		pnDatosVenta.add(btnCancelar);
	}
	
	//Metodo para cancelar modificiacion del cliente
	private void cancelarCliente() {
		cbIdentificacion.setVisible(false);
		txtIdentificacion.setBounds(185, 43, 336, 20);
		txtIdentificacion.setEditable(false);
		txtCliente.setText(datosCliente.get(0).getNombre());
		txtIdentificacion.setText(datosCliente.get(0).getIdentificacion());
		txtCliente.setEditable(false);
		btnCancelar.setVisible(false);
		btnModificar.setVisible(true);
	}
	
	//Metodo que permite realizar un conjunto acciones cuando se es selccionado el medio de pago EFECTIVO
	private void accionesEfectivo() {
		srcMedioPago.setVisible(false);
		tbMedioPago.setVisible(false);
		btnAgregar.setVisible(false);
		btnQuitar.setVisible(false);
		lblRecibido.setForeground(new Color(204, 0, 0));
		lblRecibido.setBackground(new Color(255, 255, 255));
		lblRecibido.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblRecibido.setVisible(true);
		lblCambio.setForeground(new Color(204, 0, 0));
		lblCambio.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblCambio.setVisible(true);
		txtRecibido.setBounds(185, 284, 336, 38);
		txtRecibido.setVisible(true);
		txtCambio.setEditable(false);
		txtCambio.setBounds(185, 324, 336, 38);
		txtCambio.setVisible(true);
		lblObservaciones.setBounds(10, 364, 110, 71);
		scrObservaciones.setBounds(185, 364, 570, 71);
		scrObservaciones.setViewportView(txaObservaciones);
		scrObservaciones.setVisible(true);
		spr3.setBounds(10, 446, 745, 2);
		spr3.setVisible(true);
		btnVolver.setBounds(432, 459, 89, 23);
		btnVolver.setVisible(true);
		btnGuardarVenta.setBounds(242, 459, 158, 23);
		btnGuardarVenta.setVisible(true);
		setBounds(100, 100, 793, 542);
		pnDatosVenta.setBounds(10, 10, 765, 495);
		txtRecibido.setValue(Double.parseDouble(txtEfectivo.getValue().toString()));
	}
	
	//Metodo que permite realizar un conjunto acciones cuando se es selccionado OTRO MEDIO DE PAGO
	private void accionesOtroMedio() {
		txtRecibido.setVisible(false);
		txtCambio.setVisible(false);
		lblCambio.setVisible(false);
		lblRecibido.setVisible(false);
		srcMedioPago.setVisible(true);
		tbMedioPago.setVisible(true);
		btnAgregar = new JButton("+");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarMedioPago();
			}
		});
		btnAgregar.setBounds(232, 409, 43, 23);
		btnAgregar.setForeground(new Color(0, 51, 0));
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 11));
		pnDatosVenta.add(btnAgregar);
		btnQuitar = new JButton("-");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicializarMediosEliminados();
				quitarMedioPago();
			}

		});
		btnQuitar.setBounds(285, 410, 43, 23);
		btnQuitar.setForeground(new Color(0, 51, 0));
		btnQuitar.setFont(new Font("Tahoma", Font.BOLD, 11));
		pnDatosVenta.add(btnQuitar);
		scrObservaciones.setBounds(185, 444, 570, 71);
		lblObservaciones.setBounds(10, 444, 110, 71);
		spr3.setBounds(10, 526, 745, 2);
		btnGuardarVenta.setBounds(242, 539, 158, 23);
		btnVolver.setBounds(432, 539, 89, 23);
		pnDatosVenta.setBounds(10, 10, 765, 573);
		setBounds(100, 100, 793, 621);
		
		incluirComboBoxes();
	}
	
	//Metodo para agregar medio de pago a la tabla
	private void agregarMedioPago() {
		modeloTbMediosPagos.addRow(new Object[]{"","","","","",""});
		tbMedioPago.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent evento) {
                actualizarTbMedioPago(evento);
            }
        });
	}
	
	//Metodo para quitar medio de pago a la tabla
	private void quitarMedioPago() {
		
		try{
			modeloTbMediosPagos.removeRow(modeloTbMediosPagos.getRowCount()-1);
		}catch(Exception e){
			e.getMessage();
		}
		
		calcularTotalCheque();
		calcularTotalConsignacion();
		calcularTotalTarjetaCredito();
		calcularTotalTarjetaDebito();
		calcularTotalAnticipos();
		calcularTotalNotasCredito();
		calcularEfectivo();
		calcularTotalRecibido();
		
	}
	//Incializar los valores de los medios de pago cuando han sido quitados de la tabla
	private void inicializarMediosEliminados() {
		for(int i=0;i<modeloTbMediosPagos.getRowCount();i++){
			if(modeloTbMediosPagos.getValueAt(i, 0).toString() == "Tarjeta Credito"){
				txtTarjetaCredito.setValue(0);
				calcularEfectivo();
				calcularTotalRecibido();
			}else{
				if(modeloTbMediosPagos.getValueAt(i, 0).toString() == "Tarjeta Debito"){
					txtTarjetaDebito.setValue(0);
					calcularEfectivo();
					calcularTotalRecibido();
				}else{
					if(modeloTbMediosPagos.getValueAt(i, 0).toString() == "Cheque"){
						txtCheques.setValue(0);
						calcularEfectivo();
						calcularTotalRecibido();
					}else{
						if(modeloTbMediosPagos.getValueAt(i, 0).toString() == "Consignación"){
							txtConsignaciones.setValue(0);
							calcularEfectivo();
							calcularTotalRecibido();
						}else{
							if(modeloTbMediosPagos.getValueAt(i, 0).toString() == "Nota Crédito"){
								txtNotasCredito.setValue(0);
								calcularEfectivo();
								calcularTotalRecibido();
							}else{
								txtAnticipos.setValue(0);
								calcularEfectivo();
								calcularTotalRecibido();
							}
						}
					}
				}
			}
		}
	
	}

	//Metodo para actualizar la tabla medios de pago cada vez que se modifica una celda de dicha tabla
	private void actualizarTbMedioPago(TableModelEvent evento) {
		if (evento.getType() == TableModelEvent.UPDATE) {

            // Se obtiene el modelo de la tabla y la fila/columna que han cambiado.
			TableModel modelo = ((TableModel) (evento.getSource()));
            fila = evento.getFirstRow();
            int columna = evento.getColumn();
            
           // Se aplica los calculos solamente a la columnas 1
            if (columna == 1) {
	            try{
	            	if(modelo.getValueAt(fila, 0).toString() == "Tarjeta Credito"){
	            		
	            		calcularTotalTarjetaCredito();
	            		calcularEfectivo();
	            		calcularTotalRecibido();
	   
	            	 }else{
	            		 if(modelo.getValueAt(fila, 0).toString() == "Tarjeta Debito"){
	     
	            			 calcularTotalTarjetaDebito();
	            			 calcularEfectivo();
	 	            		 calcularTotalRecibido();
	            		 }else{
	            			 if(modelo.getValueAt(fila, 0).toString() == "Cheque"){
	            				 
	            				 calcularTotalCheque();
	            				 calcularEfectivo();
		 	            		 calcularTotalRecibido();

	            			 }else{
	            				 if(modelo.getValueAt(fila, 0).toString() == "Consignación"){
		            				 
	            					calcularTotalConsignacion();
		            				 calcularEfectivo();
			 	            		 calcularTotalRecibido();

		            			 }else{
		            				 if(modelo.getValueAt(fila, 0).toString() == "Nota Crédito"){
		            					
            							 calcularTotalNotasCredito();
			            				 calcularEfectivo();
				 	            		 calcularTotalRecibido();

			            			 }else{
			            				 
			            				 calcularTotalAnticipos();
			            				 calcularEfectivo();
				 	            		 calcularTotalRecibido();
			            				 
			            			 }
		            				 
		            			 }
	            				 
	            			 }
	            		 }
	            	 }
	            	 
	            }catch(Exception ex){
	            	ex.getMessage();
	            }
            }
        }
		
	}
	//Metodo para cargar los datos en la ventana de devolucion buscar cliente
	private void cargarADevolucionCliente() {
		
		try{
			DelegadoDevolucionCliente delegadoDevolucionCliente= new DelegadoDevolucionCliente();
			VentBuscarDevolucionCliente ventBuscarDevolucionCliente = new VentBuscarDevolucionCliente();
			List<DevolucionCliente> devoluciones = delegadoDevolucionCliente.listarDevolucionClientePorIdentificacion(txtIdentificacion.getText());
			ventBuscarDevolucionCliente.agregarDatosDevoluciones(devoluciones);
			ventBuscarDevolucionCliente.setVisible(true);
			
		}catch(NullPointerException nl){
			VentBuscarDevolucionCliente ventBuscarDevolucionCliente= new VentBuscarDevolucionCliente();
			ventBuscarDevolucionCliente.setVisible(true);
			nl.getMessage();
		}
		
	}
	
	//Metodo para cargar los datos en la ventana de anticipo buscar cliente
	private void cargarAAnticipoCliente() {
		
		try{
			DelegadoAnticipoCliente delegadoAnticipoCliente= new DelegadoAnticipoCliente();
			VentBuscarAnticipoCliente ventBuscarAnticipoCliente = new VentBuscarAnticipoCliente();
			List<AnticipoCliente> anticipos = delegadoAnticipoCliente.listarAnticipoClientePorIdentificacion(txtIdentificacion.getText());
			ventBuscarAnticipoCliente.agregarDatosAnticipos(anticipos);
			ventBuscarAnticipoCliente.setVisible(true);
			
		}catch(NullPointerException nl){
			VentBuscarAnticipoCliente ventBuscarAnticipoCliente = new VentBuscarAnticipoCliente();
			ventBuscarAnticipoCliente.setVisible(true);
			nl.getMessage();
		}
		
	}
	
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbMediosPagos() {
		modeloTbMediosPagos.addColumn("Medio Pago");
		modeloTbMediosPagos.addColumn("Valor");
		modeloTbMediosPagos.addColumn("Comprobante");
		modeloTbMediosPagos.addColumn("Banco Destino");
		modeloTbMediosPagos.addColumn("Franquicia");
		
		tbMedioPago.setModel(modeloTbMediosPagos);
		
	}
	//Metodo para incluir los combo box medio de pago, banco y franquicia a alguna celdas de la tabla		
	private void incluirComboBoxes(){
		
		limpiarTablaMediosPago();
		if(tbMedioPago.getModel().getColumnCount()==0){
			llenarColumnasTbMediosPagos();
		}
		cbBancoDestino.removeAllItems();
		DelegadoBanco delegadoBanco = new DelegadoBanco();
		listaBancos = delegadoBanco.listarBanco();
		TableColumn medioPago = tbMedioPago.getColumnModel().getColumn(0);
		TableColumn bancoDestino = tbMedioPago.getColumnModel().getColumn(3);
		TableColumn franquicia = tbMedioPago.getColumnModel().getColumn(4);
		cbMedioPago.setModel(new DefaultComboBoxModel(new String[] {"Tarjeta Credito", "Tarjeta Debito", "Cheque", "Consignación", "Nota Crédito", "Anticipo"}));
		cbMedioPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if("Nota Crédito".equals(cbMedioPago.getSelectedItem().toString())){
					cargarADevolucionCliente();
            		calcularTotalNotasCredito();
            		calcularEfectivo();
            		calcularTotalRecibido();
				}else{
					if("Anticipo".equals(cbMedioPago.getSelectedItem().toString())){
						cargarAAnticipoCliente();
						calcularTotalAnticipos();
						calcularEfectivo();
						calcularTotalRecibido();
					}
				}
			}
		});
		for(Banco bancosAElegir : listaBancos){
			modeloBancos.addElement(new Banco (bancosAElegir.getEntidad(), bancosAElegir.getIdBanco()));
			cbBancoDestino.setModel(modeloBancos);
		}
		cbFranquicia.setModel(new DefaultComboBoxModel(new String[] {"Maestro", "Visa", "Mastercard", "American Express", "Diners Club","Discover", "Visa Electron", "Otra"}));
		medioPago.setCellEditor(new DefaultCellEditor(cbMedioPago));
		franquicia.setCellEditor(new DefaultCellEditor(cbFranquicia));
		bancoDestino.setCellEditor(new DefaultCellEditor(cbBancoDestino));
		
	}
	
	//Metodo para formatear a moneda peso permitiendo la edicion y visualizacion
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
	
	//Metodo para convertir un numero en formato de pesos y decimales en un numero corriente
	private String quitarComasANumero(String numero){
		String numeroReemplazado=numero.replace(",", ".");
		return numeroReemplazado;
	}
	
	//Metodo para convertir un numero en formato de pesos y decimales en un numero corriente
	private String desformatearNumero(String numero){
		String numeroReemplazado=numero.replace("$", "");
		String numeroReemplazado1=numeroReemplazado.replace(".", "");
		String numeroReemplazado2=numeroReemplazado1.replace(",", ".");
		return numeroReemplazado2;
	}
	
	//Metodo para calcular el total recibido
	private void calcularTotalRecibido(){
		double totalRecibido = Double.parseDouble(txtEfectivo.getValue().toString()) + Double.parseDouble(txtTarjetaCredito.getValue().toString()) + Double.parseDouble(txtTarjetaDebito.getValue().toString()) + Double.parseDouble(txtCheques.getValue().toString()) + Double.parseDouble(txtConsignaciones.getValue().toString()) + Double.parseDouble(txtNotasCredito.getValue().toString()) + Double.parseDouble(txtAnticipos.getValue().toString());
		txtTotalRecibido.setValue(totalRecibido);
	}
	
	//Metodo para calcular el cambio o la devuelta
	private void calcularCambio(){
		double cambio = Double.parseDouble(txtRecibido.getValue().toString()) - Double.parseDouble(txtEfectivo.getValue().toString());
		if(cambio<0){
			txtCambio.setValue(0);
			btnGuardarVenta.setEnabled(false);
			calcularTotalRecibido();
		}else{
			txtCambio.setValue(cambio);
			btnGuardarVenta.setEnabled(true);
			calcularTotalRecibido();
		}
		
	}
	
	//Metodo para calcular el efectivo teniendo en cuenta los demas medios de pago
	private void calcularEfectivo() {
		double efectivo = Double.parseDouble(txtTotal.getValue().toString()) - Double.parseDouble(txtTarjetaCredito.getValue().toString()) - Double.parseDouble(txtTarjetaDebito.getValue().toString()) - Double.parseDouble(txtCheques.getValue().toString()) - Double.parseDouble(txtConsignaciones.getValue().toString()) - Double.parseDouble(txtNotasCredito.getValue().toString()) - Double.parseDouble(txtAnticipos.getValue().toString());
		txtEfectivo.setValue(efectivo);
		if(efectivo <0){
			JOptionPane.showMessageDialog(null, "Ha excedido el valor total a pagar con la suma de todos los medios de pago y el pago debe ser exacto en esta utilidad de facturacion. La solucion es eliminar los medios de pago que exceden el valor de la venta");
			btnGuardarVenta.setEnabled(false);
			txtEfectivo.setBackground(Color.RED);
		}else{
			btnGuardarVenta.setEnabled(true);
			txtEfectivo.setBackground(UIManager.getColor("Button.background"));
		}
	}
	
	//Metodo para calcular el total de tarjetas de credito agregados en la tabla
	private void calcularTotalTarjetaCredito(){
		double tarjetaCreditos = 0;
		double acumulador = 0;
		
		for(int i=0; i<tbMedioPago.getRowCount(); i++) {
			if("Tarjeta Credito".equals(tbMedioPago.getValueAt(i,0).toString())){
				acumulador= Double.parseDouble(quitarComasANumero(tbMedioPago.getValueAt(i,1).toString()));
				tarjetaCreditos += acumulador;
			}
			txtTarjetaCredito.setValue(tarjetaCreditos);
		}
	}
	
	//Metodo para calcular el total de consignaciones agregados en la tabla
	private void calcularTotalConsignacion() {
		double consignaciones = 0;
		double acumulador = 0;
		
		for(int i=0; i<tbMedioPago.getRowCount(); i++) {
			if("Consignación".equals(tbMedioPago.getValueAt(i,0).toString())){
				acumulador= Double.parseDouble(quitarComasANumero(tbMedioPago.getValueAt(i,1).toString()));
				consignaciones += acumulador;
			}
			txtConsignaciones.setValue(consignaciones);
		}
	}
	
	//Metodo para calcular el total de cheques agregados en la tabla
	private void calcularTotalCheque() {
		double cheques = 0;
		double acumulador = 0;
		
		for(int i=0; i<tbMedioPago.getRowCount(); i++) {
			if("Cheque".equals(tbMedioPago.getValueAt(i,0).toString())){
				acumulador= Double.parseDouble(quitarComasANumero(tbMedioPago.getValueAt(i,1).toString()));
				cheques += acumulador;
			}
			txtCheques.setValue(cheques);
		}
	}
	
	//Metodo para calcular el total de tarjetas de debito agregados en la tabla
	private void calcularTotalTarjetaDebito() {
		double tarjetaDebitos = 0;
		double acumulador = 0;
		
		for(int i=0; i<tbMedioPago.getRowCount(); i++) {
			if("Tarjeta Debito".equals(tbMedioPago.getValueAt(i,0).toString())){
				acumulador= Double.parseDouble(quitarComasANumero(tbMedioPago.getValueAt(i,1).toString()));
				tarjetaDebitos += acumulador;
			}
			txtTarjetaDebito.setValue(tarjetaDebitos);
		}
		
	}
	
	//Metodo para calcular el total de notas credito agregados en la tabla
	private void calcularTotalNotasCredito() {
		double notasCredito = 0;
		double acumulador = 0;
		try{
			for(int i=0; i<tbMedioPago.getRowCount(); i++) {
				if("Nota Crédito".equals(tbMedioPago.getValueAt(i,0).toString())){
					acumulador= Double.parseDouble(quitarComasANumero(tbMedioPago.getValueAt(i,1).toString()));
					notasCredito += acumulador;
				}
				txtNotasCredito.setValue(notasCredito);
			}
		}catch(Exception e){
			e.getMessage();
			modeloTbMediosPagos.setValueAt("",fila, 0);
		}
		
	}
	
	//Metodo para calcular el total de anticipos agregados en la tabla
	private void calcularTotalAnticipos() {
		double anticipos = 0;
		double acumulador = 0;
		try{
			for(int i=0; i<tbMedioPago.getRowCount(); i++) {
				if("Anticipo".equals(tbMedioPago.getValueAt(i,0).toString())){
					acumulador= Double.parseDouble(quitarComasANumero(tbMedioPago.getValueAt(i,1).toString()));
					anticipos += acumulador;
				}
				txtAnticipos.setValue(anticipos);
			}
		}catch(Exception e){
			e.getMessage();
			modeloTbMediosPagos.setValueAt("",fila, 0);
		}
	}
	
	//Validar los datos ingresados para el registro de la venta
	private void validarDatos(){
		try{
			if(txtCliente.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del cliente");
			}else{
				if(txtIdentificacion.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Debe ingresar la identificacion del cliente");
				}else{
					if("".equals(tbMedioPago.getValueAt(fila, 0).toString())){
						JOptionPane.showMessageDialog(null, "Debe elegir un medio de pago o sino presione el boton (-) para quitarlo");
					}else{
						if("".equals(tbMedioPago.getValueAt(fila, 1).toString())){
							JOptionPane.showMessageDialog(null, "Para todos los medios de pago debe ingresar el valor del medio elegido");
						}else{
							if(("Tarjeta Credito".equals(tbMedioPago.getValueAt(fila, 0).toString())||"Tarjeta Debito".equals(tbMedioPago.getValueAt(fila, 0).toString())||"Consignación".equals(tbMedioPago.getValueAt(fila, 0).toString()))&&"".equals(tbMedioPago.getValueAt(fila, 3).toString())){
								JOptionPane.showMessageDialog(null, "Para las tarjetas y consignaciones debe elegir la entidad correspondiente al medio de pago elegido");
							}else{
								if(("Tarjeta Credito".equals(tbMedioPago.getValueAt(fila, 0).toString())||"Tarjeta Debito".equals(tbMedioPago.getValueAt(fila, 0).toString()))&&"".equals(tbMedioPago.getValueAt(fila, 4).toString())){
									JOptionPane.showMessageDialog(null, "Unicamente para las tarjetas debe elegir la franquicia correspondiente al medio de pago elegido");
								}else{
									if("Cheque".equals(tbMedioPago.getValueAt(fila, 0).toString()) && (""!=tbMedioPago.getValueAt(fila, 3).toString()|| ""!=tbMedioPago.getValueAt(fila, 4).toString())){
										JOptionPane.showMessageDialog(null, "En el medio de pago \"Cheque\" no se debe elegir la entidad ni la franquicia");
										tbMedioPago.setValueAt("", fila, 3);
										tbMedioPago.setValueAt("", fila, 4);
									}else{
										if("Consignación".equals(tbMedioPago.getValueAt(fila, 0).toString()) && ""!=tbMedioPago.getValueAt(fila, 4).toString()){
											JOptionPane.showMessageDialog(null, "En el medio de pago \"Consignación\" no se debe elegir la franquicia");
											tbMedioPago.setValueAt("", fila, 4);
										}else{
											if("Nota Crédito".equals(tbMedioPago.getValueAt(fila, 0).toString()) && (""!=tbMedioPago.getValueAt(fila, 3).toString()|| ""!=tbMedioPago.getValueAt(fila, 4).toString())){
												JOptionPane.showMessageDialog(null, "En el medio de pago \"Nota Crédito\" no se debe elegir la entidad ni la franquicia");
												tbMedioPago.setValueAt("", fila, 3);
												tbMedioPago.setValueAt("", fila, 4);
											}else{
												if("Anticipo".equals(tbMedioPago.getValueAt(fila, 0).toString()) && (""!=tbMedioPago.getValueAt(fila, 3).toString()|| ""!=tbMedioPago.getValueAt(fila, 4).toString())){
													JOptionPane.showMessageDialog(null, "En el medio de pago \"Anticipo\" no se debe elegir la entidad ni la franquicia");
													tbMedioPago.setValueAt("", fila, 3);
													tbMedioPago.setValueAt("", fila, 4);
												}else{
													abrirDialogoConfirmacionRegistro();
												}
											}
											
										}
									}
									
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
			e.getMessage();
			abrirDialogoConfirmacionRegistro();
		}

	}
	//Metodo para registrar la venta de contado
	private void registrarVentaContado() {
		Vendedor vendedor = new Vendedor();
		FormaPagoCliente formaPagoCliente = new FormaPagoCliente();
		DelegadoVentaArticulos delegadoVentaArticulos = new DelegadoVentaArticulos();
		DelegadoReciboCaja delegadoReciboCaja = new DelegadoReciboCaja();
		List<VentaArticulos> ventaACrear = new ArrayList<VentaArticulos>();
		List<ReciboCaja> reciboACrear = new ArrayList<ReciboCaja>();
		double acumulador = 0;
		
		//Registrar venta
		ventaArticulos.setFechaGeneracion(new Date());
		cliente.setIdentificacion(txtIdentificacion.getText());
		ventaArticulos.setClientes(cliente);
		if("".equals(ordenCompra)){
			ventaArticulos.setOrdCompra(0);
		}else{
			ventaArticulos.setOrdCompra(Integer.parseInt(ordenCompra));
		}
		
		if("".equals(pedido)){
			ventaArticulos.setPedido(0);
		}else{
			ventaArticulos.setPedido(Integer.parseInt(pedido));
		}
		
		vendedor.setIdentificacion(datosVendedor.getIdentificacion());
		ventaArticulos.setVendedores(vendedor);
		formaPagoCliente.setIdFormaPagoCliente(datosFormaPago.get(0).getIdFormaPagoCliente());
		ventaArticulos.setFormaPagoCliente(formaPagoCliente);
		ventaArticulos.setFechaLimitePago(fechaLimitePago);
		ventaArticulos.setDescuento(Double.parseDouble(txtDescuento.getText()));
		for(int i=0; i< modeloDetalles.getRowCount();i++){
			acumulador = obtenerCostoArticulo(Integer.parseInt( modeloDetalles.getValueAt(i, 0).toString()))*Integer.parseInt( modeloDetalles.getValueAt(i, 3).toString());
			totalCostoVenta += acumulador;
		}
		ventaArticulos.setCostoVenta(totalCostoVenta);
		ventaArticulos.setSubtotal(Double.parseDouble(desformatearNumero(txtSubtotal.getText())));
		ventaArticulos.setTotalVenta(Double.parseDouble(desformatearNumero(txtTotal.getText())));
		ventaArticulos.setEstadoPago("Cobrado");
		ventaArticulos.setItems(Integer.parseInt(items));
		ventaArticulos.setObservaciones(txaObservaciones.getText());
		ventaArticulos.setEstadoActividad("Activo");
		
		//Registrar recibo de caja por cobro inmedidato
		reciboCaja.setEstadoActividad("Activo");
		reciboCaja.setFechaVenta(new Date());
		reciboCaja.setFechaRecaudo(new Date());
		reciboCaja.setTotalDocs(Double.parseDouble(txtTotal.getValue().toString()));
		reciboCaja.setTotalRecibido(Double.parseDouble(txtRecibido.getValue().toString()));
		reciboCaja.setTotalNCredito(Double.parseDouble(txtNotasCredito.getValue().toString()));
		reciboCaja.setFechaGeneracion(new Date());
		
		ventaACrear.add(ventaArticulos);
		reciboACrear.add(reciboCaja);
		
		ventaArticulos.setReciboCaja(reciboACrear);
		reciboCaja.setVentaArticulos(ventaACrear);
		
		delegadoVentaArticulos.insertarVentaArticulos(ventaArticulos);
		
		ultimaVentaArticulo = delegadoVentaArticulos.traerUltimaVentaArticulo();
		ultimoRecibocaja = delegadoReciboCaja.traerUltimoReciboCaja();
		
		registrarDetalleVenta();
		registarMedioPago();
		generarReporteFactura();
		contabilizarVentaContado();
		abrirDialogoVentaRegistrada();
	}
	
	
	//Metodo para registra el detalle de la venta de articulos
	private void registrarDetalleVenta() {
		DetalleVenta detalleVenta = new DetalleVenta();
		Articulo articulo = new Articulo();
		
		for(int i=0; i< modeloDetalles.getRowCount();i++){
			ventaArticulos.setIdVenta(ultimaVentaArticulo.get(0).getIdVenta());
			detalleVenta.setVentaArticulos(ventaArticulos);
			articulo.setCodigo(Integer.parseInt(modeloDetalles.getValueAt(i, 0).toString()));
			detalleVenta.setArticulo(articulo);
			detalleVenta.setCantidad(Integer.parseInt(modeloDetalles.getValueAt(i, 3).toString()));
			detalleVenta.setCostoVentaUnitario(obtenerCostoArticulo(Integer.parseInt(modeloDetalles.getValueAt(i, 0).toString())));
			detalleVenta.setVlrUnitario(Double.parseDouble(desformatearNumero(modeloDetalles.getValueAt(i, 5).toString())));
			detalleVenta.setTotal(Double.parseDouble(desformatearNumero(modeloDetalles.getValueAt(i, 6).toString())));
			
			delegadoDetalleVenta.insertarDetalleVenta(detalleVenta);
			
			registrarDetalleVentaAlKardex();
			
		}
	}
	
	//Metodo para registrar el detalle de la venta al kardex electronico de articulos
	private void registrarDetalleVentaAlKardex() {
		KardexElectronico kardexElectronico = new KardexElectronico();
		DelegadoKardexElectronico delegadoKardexElectronico = new DelegadoKardexElectronico();
		DelegadoControlInventario delegadoControlInventario = new DelegadoControlInventario();
		List<ControlInventario> controlInventario;
		ControlInventario controlInventarioAModificar;
		Articulo articulo = new Articulo();
		
		ultimoDetalleVenta = delegadoDetalleVenta.traerUltimoRegistroDetalleVenta();
		
		if(delegadoKardexElectronico.traerUltimoRegistroKardexElectronicopPorArticulo(ultimoDetalleVenta.getArticulo().getCodigo())==null){
			
			ultimoRegistroPorArticuloKardex1 = delegadoKardexElectronico.traerUltimoRegistroKardexElectronicopPorArticulo(ultimoDetalleVenta.getArticulo().getCodigo());
			ultimoRegistroPorArticuloKardex = ultimoRegistroPorArticuloKardex1.get(0);
			
			kardexElectronico.setFecha(ultimoDetalleVenta.getVentaArticulos().getFechaGeneracion());
			kardexElectronico.setMovimiento("Venta");
			kardexElectronico.setNroDocumento(ultimoDetalleVenta.getVentaArticulos().getIdVenta());
			articulo.setCodigo(ultimoDetalleVenta.getArticulo().getCodigo());
			kardexElectronico.setArticulo(articulo);
			kardexElectronico.setCantidadEntrada(0);
			kardexElectronico.setCostoUEntrada(0);
			kardexElectronico.setCostoTEntrada(0);
			kardexElectronico.setCantidadSalida(ultimoDetalleVenta.getCantidad());
			kardexElectronico.setCostoUSalida(ultimoRegistroPorArticuloKardex.getCostoUSaldo());
			kardexElectronico.setCostoTSalida(ultimoDetalleVenta.getTotal());
			kardexElectronico.setCantidadSaldo(ultimoDetalleVenta.getCantidad());
			kardexElectronico.setCostoUSaldo(ultimoRegistroPorArticuloKardex.getCostoUSaldo());
			kardexElectronico.setCostoTSaldo(ultimoRegistroPorArticuloKardex.getCostoTSaldo());
			
			delegadoKardexElectronico.insertarKardexElectronico(kardexElectronico);
			
			controlInventario = delegadoControlInventario.traerContInventarioPorCodigoArticulo(ultimoDetalleVenta.getArticulo().getCodigo());
			controlInventarioAModificar = controlInventario.get(0);
			
			controlInventarioAModificar.setCostoPromedio(ultimoRegistroPorArticuloKardex.getCostoUSaldo());
			controlInventarioAModificar.setCantExistencia(ultimoDetalleVenta.getCantidad());
			controlInventarioAModificar.setTotalCostoInventario(ultimoRegistroPorArticuloKardex.getCostoTSaldo());
				
			delegadoControlInventario.actualizarControlInventario(controlInventarioAModificar);
		}else{
			
			ultimoRegistroPorArticuloKardex1 = delegadoKardexElectronico.traerUltimoRegistroKardexElectronicopPorArticulo(ultimoDetalleVenta.getArticulo().getCodigo());
			ultimoRegistroPorArticuloKardex = ultimoRegistroPorArticuloKardex1.get(0);
			
			kardexElectronico.setFecha(ultimoDetalleVenta.getVentaArticulos().getFechaGeneracion());
			kardexElectronico.setMovimiento("Venta");
			kardexElectronico.setNroDocumento(ultimoDetalleVenta.getVentaArticulos().getIdVenta());
			articulo.setCodigo(ultimoDetalleVenta.getArticulo().getCodigo());
			kardexElectronico.setArticulo(articulo);
			kardexElectronico.setCantidadEntrada(0);
			kardexElectronico.setCostoUEntrada(0);
			kardexElectronico.setCostoTEntrada(0);
			kardexElectronico.setCantidadSalida(ultimoDetalleVenta.getCantidad());
			kardexElectronico.setCostoUSalida(ultimoDetalleVenta.getVlrUnitario());
			kardexElectronico.setCostoTSalida(ultimoDetalleVenta.getTotal());
			kardexElectronico.setCantidadSaldo(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleVenta.getCantidad());
			kardexElectronico.setCostoUSaldo((ultimoRegistroPorArticuloKardex.getCostoTSaldo() - (ultimoRegistroPorArticuloKardex.getCostoUSaldo())*ultimoDetalleVenta.getCantidad())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleVenta.getCantidad()));
			kardexElectronico.setCostoTSaldo((ultimoRegistroPorArticuloKardex.getCostoTSaldo() - ultimoDetalleVenta.getTotal()/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleVenta.getCantidad())*(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleVenta.getCantidad())));
			
			delegadoKardexElectronico.insertarKardexElectronico(kardexElectronico);
				
			controlInventario = delegadoControlInventario.traerContInventarioPorCodigoArticulo(ultimoDetalleVenta.getArticulo().getCodigo());
			controlInventarioAModificar = controlInventario.get(0);
			
			controlInventarioAModificar.setCostoPromedio((ultimoRegistroPorArticuloKardex.getCostoTSaldo() - (ultimoRegistroPorArticuloKardex.getCostoUSaldo())*ultimoDetalleVenta.getCantidad())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleVenta.getCantidad()));
			controlInventarioAModificar.setCantExistencia(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleVenta.getCantidad());
			controlInventarioAModificar.setTotalCostoInventario((ultimoRegistroPorArticuloKardex.getCostoTSaldo() - ultimoDetalleVenta.getTotal()/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleVenta.getCantidad())*(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleVenta.getCantidad())));
			
			delegadoControlInventario.actualizarControlInventario(controlInventarioAModificar);
		}
		
	}
	
	//Metodo para abrir ventanta de confirmacion de registro	
	private void abrirDialogoConfirmacionRegistro() {
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de grabar esta venta?", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarVentaContado();
		}else{
		
		}
	}
		
	//Metodo para abrir el dialogo de registro satisfactorioa de la venta
	private void abrirDialogoVentaRegistrada() {
		JOptionPane.showMessageDialog(null, "Se ha registrado esta venta satisfactoriamente con consecutivo N° "+ultimaVentaArticulo.get(0).getIdVenta()+" ."+" La venta ha generado un recibo de caja N° "+ultimoRecibocaja.get(0).getIdReciboCaja());
		dispose();
		VentRegistrarVentaArticulos.limpiarDatos();
	}
	
	//Metodo para registrar los medios de pago
	private void registarMedioPago() {
		MedioPagoCliente medioPago = new MedioPagoCliente();
		DelegadoMedioPagoCliente delegadoMedioPago = new DelegadoMedioPagoCliente();
		Banco banco = new Banco();
		
		if(tbMedioPago.getSelectedRowCount()==0){
			for(int i=0; i< modeloTbMediosPagos.getRowCount();i++){
				
				reciboCaja.setIdReciboCaja(ultimoRecibocaja.get(0).getIdReciboCaja());
				medioPago.setReciboCaja(reciboCaja);
				medioPago.setNombreMedioPago(modeloTbMediosPagos.getValueAt(i, 0).toString());
				medioPago.setValor(Double.parseDouble(quitarComasANumero(modeloTbMediosPagos.getValueAt(i, 1).toString())));
				if("".equals(modeloTbMediosPagos.getValueAt(i, 2).toString())){
					medioPago.setDocReferencia(0);
				}else{
					medioPago.setDocReferencia(Integer.parseInt(modeloTbMediosPagos.getValueAt(i, 2).toString()));
				}
				if("Tarjeta Credito".equals(modeloTbMediosPagos.getValueAt(i, 0).toString())||"Tarjeta Debito".equals(modeloTbMediosPagos.getValueAt(i, 0).toString())||"Consignación".equals(modeloTbMediosPagos.getValueAt(i, 0).toString())){
					Banco bancoCodigo = (Banco) modeloTbMediosPagos.getValueAt(i, 3);
					banco.setIdBanco(bancoCodigo.getIdBanco());
					medioPago.setBanco(banco);
				}else{
					medioPago.setBanco(null);
				}
				medioPago.setFranquicia(modeloTbMediosPagos.getValueAt(i, 4).toString());
				
				delegadoMedioPago.insertarMedioPago(medioPago);
				
				if("Nota Crédito".equals(modeloTbMediosPagos.getValueAt(i, 0).toString())){
					cambiarEstadoDevolucionCliente(Integer.parseInt(modeloTbMediosPagos.getValueAt(i, 2).toString()));
				}else{
					if("Anticipo".equals(modeloTbMediosPagos.getValueAt(i, 0).toString())){
						cambiarEstadoAnticipoCliente(Integer.parseInt(modeloTbMediosPagos.getValueAt(i, 2).toString()));
					}
				}
			}
			
			reciboCaja.setIdReciboCaja(ultimoRecibocaja.get(0).getIdReciboCaja());
			medioPago.setReciboCaja(reciboCaja);
			medioPago.setBanco(null);
			medioPago.setNombreMedioPago("Efectivo");
			medioPago.setValor(Double.parseDouble(txtEfectivo.getValue().toString()));
			medioPago.setDocReferencia(0);
			medioPago.setFranquicia("");
			
			delegadoMedioPago.insertarMedioPago(medioPago);
		}else{
			reciboCaja.setIdReciboCaja(ultimoRecibocaja.get(0).getIdReciboCaja());
			medioPago.setReciboCaja(reciboCaja);
			medioPago.setBanco(null);
			medioPago.setNombreMedioPago("Efectivo");
			medioPago.setValor(Double.parseDouble(txtEfectivo.getValue().toString()));
			medioPago.setDocReferencia(0);
			medioPago.setFranquicia("");
			
			delegadoMedioPago.insertarMedioPago(medioPago);
		}
		
	}
	
	//Metodo para cambiar el estado de la devolucion de pendiente a compensada
	private void cambiarEstadoDevolucionCliente(int idDevolucionCliente) {
		DevolucionCliente devolucionAModificar = new DevolucionCliente();
		DelegadoDevolucionCliente delegadoDevolucionCliente = new DelegadoDevolucionCliente();
		devolucionAModificar = delegadoDevolucionCliente.traerDevolucionClientePorCodigo(idDevolucionCliente).get(0);
		
		devolucionAModificar.setEstado("Compensada");
		devolucionAModificar.setIdRecibo(ultimoRecibocaja.get(0).getIdReciboCaja());
		devolucionAModificar.setFechaRecaudo(ultimoRecibocaja.get(0).getFechaRecaudo());
		delegadoDevolucionCliente.actualizarDevolucionCliente(devolucionAModificar);
	}
	
	//Metodo para cambiar el estado del anticipo de libre a usado
	private void cambiarEstadoAnticipoCliente(int idAnticipoCliente) {
		AnticipoCliente anticipoAModificar = new AnticipoCliente();
		DelegadoAnticipoCliente delegadoAnticipoCliente = new DelegadoAnticipoCliente();
		anticipoAModificar = delegadoAnticipoCliente.traerAnticipoClientePorCodigo(idAnticipoCliente).get(0);
		
		anticipoAModificar.setEstadoAnticipo("Usado");
		delegadoAnticipoCliente.actualizarAnticipoCliente(anticipoAModificar);
		
	}
	//Metodo para obtener el costo del articulo
	private double obtenerCostoArticulo(int codigoArticulo){
		DelegadoControlInventario delegadoControlInventario =new DelegadoControlInventario();
		ControlInventario costoArticulo =delegadoControlInventario.traerContInventarioPorCodigoArticulo(codigoArticulo).get(0);
		return costoArticulo.getCostoPromedio();
	}
	//Metodo para agregar los datos de la devolucion de clientes a la tabla de medios de pago
	public static void agregarDatosDevolucionATbMediosPago(DevolucionCliente devolucionElegida){
		
		for(int i=0; i<tbMedioPago.getRowCount(); i++){
		 if(String.valueOf(devolucionElegida.getIdDevolucionCliente()).equals(tbMedioPago.getValueAt(i, 2).toString())){
			 JOptionPane.showMessageDialog(null, "NO puede seleccionar una NOTA CRÉDITO mas de una vez.");
			 modeloTbMediosPagos.setValueAt("",fila, 0);
			 break;
				 
		 }else{
			 modeloTbMediosPagos.setValueAt(devolucionElegida.getTotalDevolucion(), fila , 1);
			 modeloTbMediosPagos.setValueAt(devolucionElegida.getIdDevolucionCliente(),fila , 2);
			 break;
		 }
		 
		}
	}
	
	//Metodo para agregar los datos del anticipos de clientes a la tabla de medios de pago
	public static void agregarDatosAnticipoATbMediosPago(AnticipoCliente anticipoElegido){
		
		for(int i=0; i<tbMedioPago.getRowCount(); i++){
			 if(String.valueOf(anticipoElegido.getIdAnticipoCliente()).equals(tbMedioPago.getValueAt(i, 2).toString())){
				 JOptionPane.showMessageDialog(null, "NO puede seleccionar un ANTICIPO mas de una vez.");
				 modeloTbMediosPagos.setValueAt("",fila, 0);
				 break;
					 
			 }else{
				 modeloTbMediosPagos.setValueAt(anticipoElegido.getValorAnticipo(), fila , 1);
				 modeloTbMediosPagos.setValueAt(anticipoElegido.getIdAnticipoCliente(),fila , 2);
				 break;
			 }
			 
		}
	}
	
	//Metodo para limpiar la tabla de medios de pago 
		private void limpiarTablaMediosPago() {
			tbMedioPago.setModel(new DefaultTableModel());
		}
	
	//Metodo para contabilizar la venta de contado
	private void contabilizarVentaContado() {
		DelegadoContabilizacion delegadoContabilizacion = new DelegadoContabilizacion();                                                                                   
		Contabilizacion contabilizarAVenta = new Contabilizacion();                                                                 
		Contabilizacion contabilizarABanco = new Contabilizacion();
		Contabilizacion contabilizarACaja = new Contabilizacion();
		Contabilizacion contabilizarAAnticipo = new Contabilizacion();
		Contabilizacion contabilizarADevolucion = new Contabilizacion();
		Contabilizacion contabilizarACostoVenta = new Contabilizacion();
		Contabilizacion contabilizarAInventario = new Contabilizacion();
		
		contabilizarAVenta.setCodigoCuenta(4135);
		contabilizarAVenta.setFechaGeneracion(new Date());              
		contabilizarAVenta.setNombreCuenta("Comercio al por mayor y al por menor");
		contabilizarAVenta.setSaldoDebito(0);
		contabilizarAVenta.setSaldoCredito(Double.parseDouble(txtTotalRecibido.getValue().toString()));
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarAVenta);
		
		if(Double.parseDouble(txtEfectivo.getValue().toString())!=0){
			contabilizarACaja.setCodigoCuenta(1105);
			contabilizarACaja.setFechaGeneracion(new Date());              
			contabilizarACaja.setNombreCuenta("Caja");
			contabilizarACaja.setSaldoDebito(Double.parseDouble(txtEfectivo.getValue().toString()));
			contabilizarACaja.setSaldoCredito(0);
			
			delegadoContabilizacion.insertarContabilizacion(contabilizarACaja);
		}else{
			if(Double.parseDouble(txtAnticipos.getValue().toString())!=0){
				contabilizarAAnticipo.setCodigoCuenta(2805);
				contabilizarAAnticipo.setFechaGeneracion(new Date());              
				contabilizarAAnticipo.setNombreCuenta("Anticipo a Clientes");
				contabilizarAAnticipo.setSaldoDebito(Double.parseDouble(txtAnticipos.getValue().toString()));
				contabilizarAAnticipo.setSaldoCredito(0);
				
				delegadoContabilizacion.insertarContabilizacion(contabilizarAAnticipo);
			}else{
				if(Double.parseDouble(txtNotasCredito.getValue().toString())!=0){
					contabilizarADevolucion.setCodigoCuenta(4175);
					contabilizarADevolucion.setFechaGeneracion(new Date());              
					contabilizarADevolucion.setNombreCuenta("Devoluciones en Ventas");
					contabilizarADevolucion.setSaldoDebito(Double.parseDouble(txtAnticipos.getValue().toString()));
					contabilizarADevolucion.setSaldoCredito(0);
					
					delegadoContabilizacion.insertarContabilizacion(contabilizarADevolucion);
				}else{
					contabilizarABanco.setCodigoCuenta(1110);
					contabilizarABanco.setFechaGeneracion(new Date());              
					contabilizarABanco.setNombreCuenta("Bancos");
					contabilizarABanco.setSaldoDebito(Double.parseDouble(txtTotalRecibido.getValue().toString())-Double.parseDouble(txtEfectivo.getValue().toString())-Double.parseDouble(txtAnticipos.getValue().toString())-Double.parseDouble(txtNotasCredito.getValue().toString()));
					contabilizarABanco.setSaldoCredito(0);
					
					delegadoContabilizacion.insertarContabilizacion(contabilizarABanco);
				}
			}
			
		}
		
		contabilizarACostoVenta.setCodigoCuenta(6135);
		contabilizarACostoVenta.setFechaGeneracion(new Date());              
		contabilizarACostoVenta.setNombreCuenta("Comercio al por mayor y al por menor");
		contabilizarACostoVenta.setSaldoDebito(0);
		contabilizarACostoVenta.setSaldoCredito(totalCostoVenta);
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarACostoVenta);
		
		contabilizarAInventario.setCodigoCuenta(1435);
		contabilizarAInventario.setFechaGeneracion(new Date());              
		contabilizarAInventario.setNombreCuenta("Inventario de Mercancias");
		contabilizarAInventario.setSaldoDebito(totalCostoVenta);
		contabilizarAInventario.setSaldoCredito(0);
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarAInventario);
	}
	//Metodo para generar la factura en forma de reporte para luego ser impreso o guardado
	public void generarReporteFactura(){
		
	URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteFacturaVenta.jasper");
	 List<ReporteDetalle> lista = new ArrayList<ReporteDetalle>();
	 for(int i=0; i< modeloDetalles.getRowCount();i++){
		 
		 ReporteDetalle detalleVenta = new ReporteDetalle(Integer.parseInt(modeloDetalles.getValueAt(i, 0).toString()), modeloDetalles.getValueAt(i, 2).toString(), modeloDetalles.getValueAt(i, 4).toString(),Integer.parseInt(modeloDetalles.getValueAt(i, 3).toString()), modeloDetalles.getValueAt(i, 5).toString(), modeloDetalles.getValueAt(i, 6).toString());
		 lista.add(detalleVenta);
			
	 }
	        try {
	            JasperReport reporte= (JasperReport) JRLoader.loadObject(ubicacion);
	            Map<String, Object> parametros = new HashMap<String, Object>();
	            parametros.put("logoJungla", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png"));
	            parametros.put("firma", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/firma.jpg"));
	            parametros.put("idVenta", ultimaVentaArticulo.get(0).getIdVenta());
	            parametros.put("nombreCliente", ultimaVentaArticulo.get(0).getClientes().getNombre());
	            parametros.put("identCliente", ultimaVentaArticulo.get(0).getClientes().getIdentificacion());
	            parametros.put("direccion", ultimaVentaArticulo.get(0).getClientes().getDireccion());
	            parametros.put("telefono", ultimaVentaArticulo.get(0).getClientes().getTelefono());
	            parametros.put("ciudad", ultimaVentaArticulo.get(0).getClientes().getMunicipio().getNombre());
	            parametros.put("formaPago", ultimaVentaArticulo.get(0).getFormaPagoCliente().getDescripcion());
	            parametros.put("nombreVendedor", ultimaVentaArticulo.get(0).getVendedores().getNombre());
	            parametros.put("ordenCompra", String.valueOf(ultimaVentaArticulo.get(0).getOrdCompra()));
	            parametros.put("pedido", String.valueOf(ultimaVentaArticulo.get(0).getPedido()));
	            parametros.put("subtotal", formatearNumero(ultimaVentaArticulo.get(0).getSubtotal()));
	            parametros.put("descuento", formatearNumero(ultimaVentaArticulo.get(0).getDescuento()));
	            parametros.put("total", formatearNumero(ultimaVentaArticulo.get(0).getTotalVenta()));
	            parametros.put("items", String.valueOf(ultimaVentaArticulo.get(0).getItems()));
	            parametros.put("fechaFactura", convertirDateAString(ultimaVentaArticulo.get(0).getFechaGeneracion()));
	            parametros.put("fechaLimite", convertirDateAString(ultimaVentaArticulo.get(0).getFechaLimitePago()));
	            parametros.put("observaciones", ultimaVentaArticulo.get(0).getObservaciones());
	            parametros.put("anulado", "");
			    parametros.put("fechaAnulado", "");
	            
	            JasperPrint jprint= JasperFillManager.fillReport(reporte, parametros,new JRBeanCollectionDataSource(lista));
	            JasperViewer.viewReport(jprint,false);
	        } catch (JRException ex) {
	            Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
	
	//Metodo para convertir un numero corriente en formato de pesos y decimales
	private static String formatearNumero(Double numero){
		NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es","CO"));
		return formato.format(numero); 
	}
}
