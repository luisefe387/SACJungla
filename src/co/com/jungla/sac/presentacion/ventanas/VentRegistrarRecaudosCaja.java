package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Point;
import java.awt.Color;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import co.com.jungla.sac.persistencia.entidades.AnticipoCliente;
import co.com.jungla.sac.persistencia.entidades.Banco;
import co.com.jungla.sac.persistencia.entidades.Contabilizacion;
import co.com.jungla.sac.persistencia.entidades.DevolucionCliente;
import co.com.jungla.sac.persistencia.entidades.MedioPagoCliente;
import co.com.jungla.sac.persistencia.entidades.ReciboCaja;
import co.com.jungla.sac.persistencia.entidades.VentaArticulos;

import co.com.jungla.sac.negocio.delegados.DelegadoAnticipoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoBanco;
import co.com.jungla.sac.negocio.delegados.DelegadoContabilizacion;
import co.com.jungla.sac.negocio.delegados.DelegadoDevolucionCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoMedioPagoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoReciboCaja;
import co.com.jungla.sac.negocio.delegados.DelegadoVentaArticulos;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro del recaudo de caja por venta pendiente generando el recibo de caja, el medio de pago y su contabilizacion.
 * @author Luis Fernando Pedroza T.
 * @version: 04/09/2016
 */
public class VentRegistrarRecaudosCaja extends JDialog {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtDptoCiudad;
	private JTextField txtIdentificacion;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JFormattedTextField txtTotal;
	private JFormattedTextField txtEfectivo;
	private JFormattedTextField txtTarjetaCredito;
	private JFormattedTextField txtTarjetaDebito;
	private JFormattedTextField txtCheques;
	private JFormattedTextField txtNotasCredito;
	private JFormattedTextField txtAnticipos;
	private JFormattedTextField txtTotalRecibido;
	private JFormattedTextField txtConsignaciones;
	private JDateChooser dchFechaRecaudo;
	private JComboBox cbMedioPago;
	private JComboBox cbBancoDestino;
	private JComboBox cbFranquicia;
	private JButton btnRegistrar;
	private JButton btnAgregar;
	private JButton btnQuitar;
	private JTable tbRecaudos;
	private static JTable tbMedioPago;
	private JScrollPane scrMediosPago;
	private DefaultComboBoxModel modeloBancos = new DefaultComboBoxModel();
	static DefaultTableModel modeloTbMediosPagos = new DefaultTableModel();
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private int filaSeleccionada;
	private List<Banco> listaBancos;
	private static int fila;
	private ReciboCaja reciboCaja = new ReciboCaja();
	private List<ReciboCaja> ultimoRecibocaja;
	private JTextPane txpObservaciones;

	/**
	 * Metodo constructor sin parametros necesarios para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentRegistrarRecaudosCaja() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarRecaudosCaja.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Registrar Recaudos Caja");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1067, 538);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn6 = new JPanel();
		pn6.setBackground(UIManager.getColor("Button.background"));
		pn6.setLayout(null);
		pn6.setBorder(null);
		pn6.setBounds(811, 248, 234, 247);
		contentPane.add(pn6);
		
		JLabel lblCaja = new JLabel("");
		lblCaja.setIcon(new ImageIcon(VentRegistrarRecaudosCaja.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo caja.png")));
		lblCaja.setBounds(54, 6, 134, 135);
		pn6.add(lblCaja);
		
		JScrollPane srcObservaciones = new JScrollPane();
		srcObservaciones.setBounds(10, 163, 214, 77);
		pn6.add(srcObservaciones);
		
		txpObservaciones = new JTextPane();
		srcObservaciones.setViewportView(txpObservaciones);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaciones.setBounds(10, 147, 124, 14);
		pn6.add(lblObservaciones);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(13, 11, 1032, 61);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JPanel pnDptoCiudad = new JPanel();
		pnDptoCiudad.setLayout(null);
		pnDptoCiudad.setBackground(new Color(0, 51, 0));
		pnDptoCiudad.setBounds(469, 11, 201, 38);
		pn1.add(pnDptoCiudad);
		
		JLabel lblDptoCiudad = new JLabel("Departamento - Ciudad");
		lblDptoCiudad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDptoCiudad.setForeground(Color.WHITE);
		lblDptoCiudad.setBackground(Color.BLACK);
		lblDptoCiudad.setBounds(34, 0, 154, 14);
		pnDptoCiudad.add(lblDptoCiudad);
		
		txtDptoCiudad = new JTextField();
		txtDptoCiudad.setEditable(false);
		txtDptoCiudad.setBounds(0, 18, 201, 20);
		pnDptoCiudad.add(txtDptoCiudad);
		txtDptoCiudad.setColumns(10);
		
		JPanel pnCliente = new JPanel();
		pnCliente.setLayout(null);
		pnCliente.setBackground(new Color(0, 51, 0));
		pnCliente.setBounds(10, 11, 449, 38);
		pn1.add(pnCliente);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setForeground(Color.WHITE);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCliente.setBackground(Color.BLACK);
		lblCliente.setBounds(209, 0, 73, 14);
		pnCliente.add(lblCliente);
		
		txtIdentificacion = new JTextField();
		txtIdentificacion.setEditable(false);
		txtIdentificacion.setColumns(10);
		txtIdentificacion.setBounds(0, 18, 100, 20);
		pnCliente.add(txtIdentificacion);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(104, 18, 345, 20);
		pnCliente.add(txtNombre);
		
		JPanel pnDireccion = new JPanel();
		pnDireccion.setLayout(null);
		pnDireccion.setBackground(new Color(0, 51, 0));
		pnDireccion.setBounds(680, 11, 194, 38);
		pn1.add(pnDireccion);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setForeground(Color.WHITE);
		lblDireccin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDireccin.setBackground(Color.BLACK);
		lblDireccin.setBounds(69, 0, 112, 14);
		pnDireccion.add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(0, 18, 194, 20);
		pnDireccion.add(txtDireccion);
		
		JPanel pnTelefono = new JPanel();
		pnTelefono.setLayout(null);
		pnTelefono.setBackground(new Color(0, 51, 0));
		pnTelefono.setBounds(884, 11, 136, 38);
		pn1.add(pnTelefono);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelefono.setBackground(Color.BLACK);
		lblTelefono.setBounds(41, 0, 84, 14);
		pnTelefono.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(0, 18, 136, 20);
		pnTelefono.add(txtTelefono);
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(13, 83, 1032, 154);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JScrollPane scrRecaudos = new JScrollPane();
		scrRecaudos.setBounds(2, 2, 1028, 150);
		pn2.add(scrRecaudos);
		
		tbRecaudos = new JTable();
		tbRecaudos.setEnabled(false);
		tbRecaudos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbRecaudos.rowAtPoint(point);
		        tbRecaudos.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
			}
		});
		scrRecaudos.setViewportView(tbRecaudos);
      		
		JPanel pn4 = new JPanel();
		pn4.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn4.setBounds(13, 448, 554, 47);
		contentPane.add(pn4);
		pn4.setLayout(null);
		
		JButton btnAtras = new JButton("Ir Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAtras.setForeground(new Color(0, 51, 0));
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAtras.setBackground(UIManager.getColor("Button.background"));
		btnAtras.setBounds(10, 11, 105, 23);
		pn4.add(btnAtras);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
		});
		btnRegistrar.setForeground(new Color(0, 51, 0));
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegistrar.setBackground(UIManager.getColor("Button.background"));
		btnRegistrar.setBounds(125, 11, 134, 23);
		pn4.add(btnRegistrar);
		
		JLabel lblFechaRecaudo = new JLabel("Fecha de Recaudo");
		lblFechaRecaudo.setOpaque(true);
		lblFechaRecaudo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaRecaudo.setBackground(new Color(153, 204, 153));
		lblFechaRecaudo.setBounds(269, 12, 126, 22);
		pn4.add(lblFechaRecaudo);
		
		dchFechaRecaudo = new JDateChooser();
		dchFechaRecaudo.setBounds(397, 13, 134, 20);
		dchFechaRecaudo.setDate(new Date());
		pn4.add(dchFechaRecaudo);
		
		JPanel pn5 = new JPanel();
		pn5.setLayout(null);
		pn5.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		pn5.setBackground(Color.GRAY);
		pn5.setBounds(577, 248, 224, 247);
		contentPane.add(pn5);
		
		JLabel lblEfectivo = new JLabel("Efectivo");
		lblEfectivo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEfectivo.setBounds(10, 52, 46, 14);
		pn5.add(lblEfectivo);
		
		txtEfectivo = new JFormattedTextField();
		txtEfectivo.setBackground(new Color(255, 255, 51));
		txtEfectivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularTotalRecibido();
			}
		});
		formatearAMoneda(txtEfectivo);
		txtEfectivo.setColumns(10);
		txtEfectivo.setBounds(114, 49, 100, 20);
		pn5.add(txtEfectivo);
		
		txtTarjetaCredito = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtTarjetaCredito.setEditable(false);
		txtTarjetaCredito.setColumns(10);
		txtTarjetaCredito.setValue(0);
		txtTarjetaCredito.setBounds(114, 70, 100, 20);
		pn5.add(txtTarjetaCredito);
		
		JLabel lblTarjetaCredito = new JLabel("Tarjeta Cr\u00E9dito");
		lblTarjetaCredito.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTarjetaCredito.setBounds(10, 73, 100, 14);
		pn5.add(lblTarjetaCredito);
		
		JLabel lblTarjetaDebito = new JLabel("Tarjeta D\u00E9dito");
		lblTarjetaDebito.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTarjetaDebito.setBounds(10, 94, 100, 14);
		pn5.add(lblTarjetaDebito);
		
		txtTarjetaDebito = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtTarjetaDebito.setEditable(false);
		txtTarjetaDebito.setColumns(10);
		txtTarjetaDebito.setValue(0);
		txtTarjetaDebito.setBounds(114, 91, 100, 20);
		pn5.add(txtTarjetaDebito);
		
		JLabel lblCheques = new JLabel("Cheques");
		lblCheques.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCheques.setBounds(10, 115, 62, 14);
		pn5.add(lblCheques);
		
		txtCheques = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtCheques.setEditable(false);
		txtCheques.setColumns(10);
		txtCheques.setValue(0);
		txtCheques.setBounds(114, 112, 100, 20);
		pn5.add(txtCheques);
		
		JLabel lblNotaCredito = new JLabel("Nota Cr\u00E9dito");
		lblNotaCredito.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNotaCredito.setBounds(10, 157, 93, 14);
		pn5.add(lblNotaCredito);
		
		txtNotasCredito = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtNotasCredito.setEditable(false);
		txtNotasCredito.setColumns(10);
		txtNotasCredito.setValue(0);
		txtNotasCredito.setBounds(114, 154, 100, 20);
		pn5.add(txtNotasCredito);
		
		JLabel lblTotalRecibido = new JLabel("Total a Recibir");
		lblTotalRecibido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalRecibido.setBounds(10, 216, 93, 14);
		pn5.add(lblTotalRecibido);
		
		txtTotalRecibido = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtTotalRecibido.setEditable(false);
		txtTotalRecibido.setColumns(10);
		txtTotalRecibido.setValue(0);
		txtTotalRecibido.setBackground(new Color(51, 255, 102));
		txtTotalRecibido.setBounds(114, 213, 100, 20);
		pn5.add(txtTotalRecibido);
		
		JSeparator sp2 = new JSeparator();
		sp2.setBounds(10, 203, 204, 2);
		pn5.add(sp2);
		
		JLabel lblAnticipos = new JLabel("Anticipos");
		lblAnticipos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAnticipos.setBounds(10, 178, 93, 14);
		pn5.add(lblAnticipos);
		
		txtAnticipos = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtAnticipos.setEditable(false);
		txtAnticipos.setColumns(10);
		txtAnticipos.setValue(0);
		txtAnticipos.setBounds(114, 175, 100, 20);
		pn5.add(txtAnticipos);
		
		JSeparator sp = new JSeparator();
		sp.setBounds(10, 39, 204, 2);
		pn5.add(sp);
		
		JLabel lblTotalAPagar = new JLabel("Total a Pagar");
		lblTotalAPagar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalAPagar.setBounds(10, 14, 100, 14);
		pn5.add(lblTotalAPagar);
		
		txtTotal = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);
		txtTotal.setBounds(114, 11, 100, 20);
		pn5.add(txtTotal);
		
		JLabel lblConsignaciones = new JLabel("Consignaciones");
		lblConsignaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConsignaciones.setBounds(10, 136, 100, 14);
		pn5.add(lblConsignaciones);
		
		txtConsignaciones = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtConsignaciones.setEditable(false);
		txtConsignaciones.setColumns(10);
		txtConsignaciones.setValue(0);
		txtConsignaciones.setBounds(114, 133, 100, 20);
		pn5.add(txtConsignaciones);
		
		btnAgregar = new JButton("+");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarMedioPago();
			}
		});
		btnAgregar.setBounds(237, 414, 43, 23);
		contentPane.add(btnAgregar);
		
		btnQuitar = new JButton("-");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicializarMediosEliminados();
				quitarMedioPago();
			}
		});
		btnQuitar.setBounds(290, 414, 43, 23);
		contentPane.add(btnQuitar);
		
		JPanel pn3 = new JPanel();
		pn3.setLayout(null);
		pn3.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn3.setBounds(13, 248, 554, 155);
		contentPane.add(pn3);
		
		scrMediosPago = new JScrollPane();
		scrMediosPago.setBounds(2, 2, 550, 151);
		pn3.add(scrMediosPago);
		
		tbMedioPago = new JTable();
		scrMediosPago.setViewportView(tbMedioPago);
		
		cbMedioPago = new JComboBox();
		cbBancoDestino = new JComboBox();
		cbFranquicia = new JComboBox();
		
		//Metodo que debe ejecutar la ventana al inicializarse
		if(tbMedioPago.getModel().getColumnCount()==0){
			llenarColumnasTbMediosPagos();
		}
		incluirComboBoxes();
		
		setModal(true);
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
	
	//Metodo para calcular el valor total
	private void calcularTotal() {
		double netoAPagar = 0;
		double acumulador = 0;
		
		for(int i=0; i<tbRecaudos.getRowCount(); i++) {
			acumulador= Double.parseDouble(desformatearNumero(tbRecaudos.getValueAt(i,6).toString()));
			netoAPagar += acumulador;
			
		}
		
		txtTotal.setValue(netoAPagar) ;
	}
	
	//Metodo para calcular el total recibido
	private void calcularTotalRecibido(){
		double totalRecibido = Double.parseDouble(txtEfectivo.getValue().toString()) + Double.parseDouble(txtTarjetaCredito.getValue().toString()) + Double.parseDouble(txtTarjetaDebito.getValue().toString()) + Double.parseDouble(txtCheques.getValue().toString()) + Double.parseDouble(txtConsignaciones.getValue().toString()) + Double.parseDouble(txtNotasCredito.getValue().toString()) + Double.parseDouble(txtAnticipos.getValue().toString());
		if(totalRecibido <Double.parseDouble(txtTotal.getValue().toString())){
			if(tbRecaudos.getRowCount()>1){
				JOptionPane.showMessageDialog(null, "El valor a recibir es inferior al valor a pagar, si desea realizar un abono sobre estos documentos, debe presionar el boton IR ATRAS y elegir uno por uno");
				btnRegistrar.setEnabled(false);
			}else{
				JOptionPane.showMessageDialog(null, "El valor a recibir es inferior al valor a pagar, desea realizar un abono sobre este documento?");
				btnRegistrar.setEnabled(true);
			}
			
		}else{
			btnRegistrar.setEnabled(true);
		}
		txtTotalRecibido.setValue(totalRecibido);
	}
	
	//Metodo para calcular el efectivo teniendo en cuenta los demas medios de pago
	private void calcularEfectivo() {
		double efectivo = Double.parseDouble(txtTotal.getValue().toString()) - Double.parseDouble(txtTarjetaCredito.getValue().toString()) - Double.parseDouble(txtTarjetaDebito.getValue().toString()) - Double.parseDouble(txtCheques.getValue().toString()) - Double.parseDouble(txtConsignaciones.getValue().toString()) - Double.parseDouble(txtNotasCredito.getValue().toString()) - Double.parseDouble(txtAnticipos.getValue().toString());
		txtEfectivo.setValue(efectivo);
		if(efectivo <0){
			JOptionPane.showMessageDialog(null, "Ha excedido el valor total a pagar con la suma de todos los medios de pago y el pago debe ser exacto en esta utilidad de facturacion. La solucion es eliminar los medios de pago que exceden el valor de la venta");
			btnRegistrar.setEnabled(false);
			txtEfectivo.setBackground(Color.RED);
		}else{
			btnRegistrar.setEnabled(true);
			txtEfectivo.setBackground(new Color(255, 255, 51));
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
	//Metodo que permite la conversion de un dato de tipo date a uno de tipo string
	public String convertirDateAString(Date strFecha){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(strFecha);
		
		return fecha;
	}
	
	//Metodo para convertir un numero corriente en formato de pesos y decimales
	private String formatearNumero(Double numero){
		NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es","CO"));
		return formato.format(numero); 
	}
		
	//Metodo para listar los datos de los anticipos de clientes
	public void agregarDatosAnticipos(TableModel modeloRecaudosSeleccionados, String identificacion, String nombre, String dptoCiudad, String direccion, String telefono){
		tbRecaudos.setModel(modeloRecaudosSeleccionados);
		txtIdentificacion.setText(identificacion);
		txtNombre.setText(nombre);
		txtDptoCiudad.setText(dptoCiudad);
		txtDireccion.setText(direccion);
		txtTelefono.setText(telefono);
		calcularTotal();
		colorearDiasPlazoEnTabla(tbRecaudos);
		colorearFechaPagoVencidaEnTabla(tbRecaudos);
	}
	
	//Validar los datos ingresados para el registro del recaudo
	private void validarDatos(){
		try{
			if(txtNombre.getText().isEmpty()){
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
	
	//Metodo para abrir ventanta de confirmacion de registro	
	private void abrirDialogoConfirmacionRegistro() {
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de grabar esta Recibo de Caja?. Despúes de grabarlo NO podrá realizar ningún cambio sobre esta información.", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarReciboCaja();
		}else{
		
		}
	}
	
	//Metodo para generar un recibo de caja luego de haberse registrado el abono o reacudo para la venta pendiente
	private void registrarReciboCaja(){
		DelegadoReciboCaja delegadoReciboCaja = new DelegadoReciboCaja();
		DelegadoVentaArticulos delegadoVentaArticulos = new DelegadoVentaArticulos();
		List<ReciboCaja> reciboAModificar = new ArrayList<ReciboCaja>();
		
		reciboCaja.setEstadoActividad("Activo");
		reciboCaja.setFechaVenta(delegadoVentaArticulos.traerVentaPorCodigo(Integer.parseInt(tbRecaudos.getValueAt(0, 0).toString())).get(0).getFechaGeneracion());
		reciboCaja.setFechaRecaudo(dchFechaRecaudo.getDate());
		reciboCaja.setTotalDocs(Double.parseDouble(txtTotal.getValue().toString()));
		reciboCaja.setTotalRecibido(Double.parseDouble(txtEfectivo.getValue().toString()));
		reciboCaja.setTotalNCredito(Double.parseDouble(txtNotasCredito.getValue().toString()));
		reciboCaja.setFechaGeneracion(new Date());
		reciboCaja.setObservaciones(txpObservaciones.getText());
		
		delegadoReciboCaja.insertarReciboCaja(reciboCaja);
		ultimoRecibocaja = delegadoReciboCaja.traerUltimoReciboCaja();
		
		if(Double.parseDouble(txtTotalRecibido.getValue().toString())<Double.parseDouble(txtTotal.getValue().toString())){
			
			VentaArticulos ventaPendiente = delegadoVentaArticulos.traerVentaPorCodigo(Integer.parseInt(tbRecaudos.getValueAt(0, 0).toString())).get(0);
			List<ReciboCaja> recibosPorVenta = delegadoReciboCaja.traerRecibosCajaPorVenta(ventaPendiente.getIdVenta());
			
			reciboAModificar.add(ultimoRecibocaja.get(0));
			
			ventaPendiente.setReciboCaja(reciboAModificar);
			delegadoVentaArticulos.actualizarVentaArticulos(ventaPendiente);
				
			for(int i=0;i<recibosPorVenta.size();i++){
				reciboAModificar.add(recibosPorVenta.get(i));
				ventaPendiente.setReciboCaja(reciboAModificar);
				delegadoVentaArticulos.actualizarVentaArticulos(ventaPendiente);
			}
		}else{
			
			reciboAModificar.add(ultimoRecibocaja.get(0));
			
			for(int i=0; i<tbRecaudos.getRowCount();i++){
				VentaArticulos ventaPendiente = delegadoVentaArticulos.traerVentaPorCodigo(Integer.parseInt(tbRecaudos.getValueAt(i, 0).toString())).get(0);
				ventaPendiente.setEstadoPago("Cobrado");
				ventaPendiente.setReciboCaja(reciboAModificar);
				delegadoVentaArticulos.actualizarVentaArticulos(ventaPendiente);
			}
		}
		
		registarMedioPago();
		contabilizarRecaudoCaja();
		abrirDialogoReciboCajaRegistrado();
	}
	
	//Metodo para abrir el dialogo de registro satisfactorio del recaudo de caja
	private void abrirDialogoReciboCajaRegistrado() {
		String nl = System.getProperty("line.separator");
		if(Integer.parseInt(tbRecaudos.getValueAt(0, 3).toString())<0){
			int res = JOptionPane.showOptionDialog(null, "Ha quedado un Saldo Pendiente por: "+formatearNumero(Double.parseDouble(txtTotal.getValue().toString()) - Double.parseDouble(txtTotal.getValue().toString()))+nl+"Si Desea programar de nuevo la fecha limite para el proximo pago, presione Cambiar Fecha Limite", "Recibo de Caja Registrado", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Ir a Caja", "Imprimir Recibo","Cambiar Fecha Limite"}, "Ir a Caja");
			if(res==0){
				dispose();
				VentBuscarClienteRecaudosCaja ventBuscarClienteRecaudosCaja = new VentBuscarClienteRecaudosCaja();
				ventBuscarClienteRecaudosCaja.setVisible(true);
			}else{
				if(res==1){
					imprimirRecibo();
				}else{
					modificarFechaLimite();
					dispose();
					VentBuscarClienteRecaudosCaja ventBuscarClienteRecaudosCaja = new VentBuscarClienteRecaudosCaja();
					ventBuscarClienteRecaudosCaja.setVisible(true);
				}
			}
		}else{
			int res = JOptionPane.showOptionDialog(null, "Ha quedado un Saldo Pendiente por: "+formatearNumero(Double.parseDouble(txtTotal.getValue().toString()) - Double.parseDouble(txtTotal.getValue().toString())), "Recibo de Caja Registrado", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Ir a Caja", "Imprimir Recibo"}, "Ir a Caja");
			if(res==0){
				dispose();
				VentBuscarClienteRecaudosCaja ventBuscarClienteRecaudosCaja = new VentBuscarClienteRecaudosCaja();
				ventBuscarClienteRecaudosCaja.setVisible(true);
			}else{
				imprimirRecibo();
			}
		}
		
	}
	
	//Metodo que permite modificar la fecha limite de la venta elegida luego de haber abonado al saldo
	private void modificarFechaLimite() {
		VentModificarFechaLimite ventModificarFechaLimite = new VentModificarFechaLimite(Integer.parseInt(tbRecaudos.getValueAt(0, 0).toString()));
		ventModificarFechaLimite.setVisible(true);
		
	}

	//Metodo para imprimir el recaudo de caja
	private void imprimirRecibo() {
		
		
	}

	//Metodo para registrar los medios de pago
	private void registarMedioPago() {
		MedioPagoCliente medioPago = new MedioPagoCliente();
		DelegadoMedioPagoCliente delegadoMedioPago = new DelegadoMedioPagoCliente();
		Banco banco = new Banco();
		
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
		//medioPago.setBanco(null);
		medioPago.setNombreMedioPago("Efectivo");
		medioPago.setValor(Double.parseDouble(txtEfectivo.getValue().toString()));
		medioPago.setDocReferencia(0);
		medioPago.setFranquicia("");
		
		delegadoMedioPago.insertarMedioPago(medioPago);
	}
	
	//Metodo para agregar los datos de las devoluciones de clientes a la tabla de medios de pago
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
	
	//Metodo para cambiar el estado de la devolucion de pendiente a compensada
	private void cambiarEstadoDevolucionCliente(int idDevolucionCliente) {
		DevolucionCliente devolucionAModificar = new DevolucionCliente();
		DelegadoDevolucionCliente delegadoDevolucionCliente = new DelegadoDevolucionCliente();
		devolucionAModificar = delegadoDevolucionCliente.traerDevolucionClientePorCodigo(idDevolucionCliente).get(0);
		
		devolucionAModificar.setEstado("Compensada");
		delegadoDevolucionCliente.actualizarDevolucionCliente(devolucionAModificar);
	}
	
	//Metodo para cambiar el estado de la anticipo libre a usado
	private void cambiarEstadoAnticipoCliente(int idAnticipoCliente) {
		AnticipoCliente anticipoAModificar = new AnticipoCliente();
		DelegadoAnticipoCliente delegadoAnticipoCliente = new DelegadoAnticipoCliente();
		anticipoAModificar = delegadoAnticipoCliente.traerAnticipoClientePorCodigo(idAnticipoCliente).get(0);
		
		anticipoAModificar.setEstadoAnticipo("Usado");
		delegadoAnticipoCliente.actualizarAnticipoCliente(anticipoAModificar);
		
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
	
	//Metodo para colorear la columna de fecha pago vencida basandose en los dias vencidos
	private void colorearFechaPagoVencidaEnTabla(JTable tablaRecaudos) {
		tablaRecaudos.getColumn("Fecha Limite").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			if(Integer.parseInt(table.getValueAt(row, 3).toString())>0){
				comp.setBackground(Color.WHITE);
			}else{
				comp.setBackground(new Color(204, 102, 102));
			}
	        return comp; 
			}}); 
	}
	
	//Metodo para colorear la columna de dias plazo basandose en los dias vencidos
	private void colorearDiasPlazoEnTabla(JTable tablaRecaudos) {
		tablaRecaudos.getColumn("Días").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			if(Integer.parseInt(table.getValueAt(row, 3).toString())>0){
				comp.setBackground(Color.WHITE);
			}else{
				comp.setBackground(new Color(204, 102, 102));
			}
	        return comp; 
			}}); 
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
	
	//Metodo para contabilizar el recaudo de la venta pendiente
	private void contabilizarRecaudoCaja() {
		
		DelegadoContabilizacion delegadoContabilizacion = new DelegadoContabilizacion();                                                                                   
		Contabilizacion contabilizarACaja = new Contabilizacion();
		Contabilizacion contabilizarACliente = new Contabilizacion();
		
		
		contabilizarACaja.setCodigoCuenta(1105);
		contabilizarACaja.setFechaGeneracion(new Date());              
		contabilizarACaja.setNombreCuenta("Caja");
		contabilizarACaja.setSaldoDebito(Double.parseDouble(txtTotalRecibido.getValue().toString()));
		contabilizarACaja.setSaldoCredito(0);
			
		delegadoContabilizacion.insertarContabilizacion(contabilizarACaja);
		
		contabilizarACliente.setCodigoCuenta(1305);
		contabilizarACliente.setFechaGeneracion(new Date());              
		contabilizarACliente.setNombreCuenta("Clientes");
		contabilizarACliente.setSaldoDebito(0);
		contabilizarACliente.setSaldoCredito(Double.parseDouble(txtTotalRecibido.getValue().toString()));
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarACliente);
	}
}
