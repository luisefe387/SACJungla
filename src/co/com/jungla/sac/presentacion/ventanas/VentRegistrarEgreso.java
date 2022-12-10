package co.com.jungla.sac.presentacion.ventanas;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import co.com.jungla.sac.persistencia.entidades.Contabilizacion;
import co.com.jungla.sac.persistencia.entidades.MedioPagoProv;
import co.com.jungla.sac.persistencia.entidades.Egreso;
import co.com.jungla.sac.persistencia.entidades.Persona;
import co.com.jungla.sac.persistencia.entidades.Proveedor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import co.com.jungla.sac.negocio.delegados.DelegadoContabilizacion;
import co.com.jungla.sac.negocio.delegados.DelegadoEgreso;
import co.com.jungla.sac.negocio.delegados.DelegadoMedioPagoProv;
import co.com.jungla.sac.negocio.delegados.DelegadoProveedor;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFormattedTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 * llevar a cabo el registro del egreso en el momento de pagarse alguna compra o cuenta por pagar de forma inmediata y su
 * contabilizacion.
 * @author Luis Fernando Pedroza T.
 * @version: 21/09/2016
 */
public class VentRegistrarEgreso extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtDocSoporte;
	private JTextField txtConcepto;
	private JFormattedTextField txtSubtotal;
	private JFormattedTextField txtOtros;
	private JFormattedTextField txtTotalPagado;
	private JFormattedTextField txtValorPago1;
	private JFormattedTextField txtValorPago2;
	private JTextPane txpObservaciones;
	private JComboBox cbProveedor;
	private JComboBox cbFormaPago1;
	private JComboBox cbFormaPago2;
	private JDateChooser dchFechaPago;
	private DefaultComboBoxModel modeloProveedor = new DefaultComboBoxModel();
	private DefaultComboBoxModel modeloFormaPago1 = new DefaultComboBoxModel();
	private DefaultComboBoxModel modeloFormaPago2 = new DefaultComboBoxModel();
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private List<Egreso> codigoEgreso;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentRegistrarEgreso() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarEgreso.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Registros de Gastos y/o Egresos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBackground(new Color(153, 204, 153));
		lblProveedor.setOpaque(true);
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProveedor.setBounds(11, 21, 107, 22);
		contentPane.add(lblProveedor);
		
		JLabel lblConcepto = new JLabel("Concepto");
		lblConcepto.setBackground(new Color(153, 204, 153));
		lblConcepto.setOpaque(true);
		lblConcepto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConcepto.setBounds(11, 44, 107, 22);
		contentPane.add(lblConcepto);
		
		JLabel lblFechaPago = new JLabel("Fecha Pago");
		lblFechaPago.setBackground(new Color(153, 204, 153));
		lblFechaPago.setOpaque(true);
		lblFechaPago.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaPago.setBounds(12, 90, 106, 22);
		contentPane.add(lblFechaPago);
		
		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setBackground(new Color(153, 204, 153));
		lblSubtotal.setOpaque(true);
		lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSubtotal.setBounds(12, 113, 106, 22);
		contentPane.add(lblSubtotal);
		
		JLabel lblOtros = new JLabel("Otros(+)");
		lblOtros.setBackground(new Color(153, 204, 153));
		lblOtros.setOpaque(true);
		lblOtros.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOtros.setBounds(12, 136, 106, 22);
		contentPane.add(lblOtros);
		
		JLabel lblTotalPagar = new JLabel("Total Pagado");
		lblTotalPagar.setBackground(new Color(153, 204, 153));
		lblTotalPagar.setOpaque(true);
		lblTotalPagar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalPagar.setBounds(12, 159, 106, 22);
		contentPane.add(lblTotalPagar);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBackground(new Color(153, 204, 153));
		lblObservaciones.setOpaque(true);
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaciones.setBounds(12, 228, 106, 44);
		contentPane.add(lblObservaciones);
		 
		JButton btnGuardarEgreso = new JButton("Registrar Egreso");
		btnGuardarEgreso.setForeground(new Color(0, 51, 0));
		btnGuardarEgreso.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGuardarEgreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
		});
		btnGuardarEgreso.setBounds(152, 296, 129, 23);
		contentPane.add(btnGuardarEgreso);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(308, 296, 89, 23);
		contentPane.add(btnCerrar);
		
		txtDocSoporte = new JTextField();
		txtDocSoporte.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validarDocSoporte();
			}
		});
		txtDocSoporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDocSoporte();
			}
		});
		txtDocSoporte.setBounds(119, 68, 138, 20);
		txtDocSoporte.setDocument(new LimitadorCaracteres());
		contentPane.add(txtDocSoporte);
		txtDocSoporte.setColumns(10);
		
		JLabel lblDocSoporte = new JLabel("Doc. Soporte");
		lblDocSoporte.setBackground(new Color(153, 204, 153));
		lblDocSoporte.setOpaque(true);
		lblDocSoporte.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDocSoporte.setBounds(12, 67, 106, 22);
		contentPane.add(lblDocSoporte);
		
		txpObservaciones = new JTextPane();
		txpObservaciones.setBounds(119, 229, 414, 41);
		contentPane.add(txpObservaciones);
		
		cbFormaPago1 = new JComboBox();
		cbFormaPago1.setBounds(119, 183, 275, 20);
		contentPane.add(cbFormaPago1);
		
		cbFormaPago2 = new JComboBox();
		cbFormaPago2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarFormaPago2();
			}
		});
		cbFormaPago2.setBounds(119, 206, 275, 20);
		contentPane.add(cbFormaPago2);
		
		JLabel lblFormaPago1 = new JLabel("Forma de Pago 1");
		lblFormaPago1.setBackground(new Color(153, 204, 153));
		lblFormaPago1.setOpaque(true);
		lblFormaPago1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFormaPago1.setBounds(12, 182, 106, 22);
		contentPane.add(lblFormaPago1);
		
		JLabel lblFormaPago2 = new JLabel("Forma de Pago 2");
		lblFormaPago2.setBackground(new Color(153, 204, 153));
		lblFormaPago2.setOpaque(true);
		lblFormaPago2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFormaPago2.setBounds(12, 205, 106, 22);
		contentPane.add(lblFormaPago2);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(11, 283, 521, 2);
		contentPane.add(sp);
		
		JSeparator sp1 = new JSeparator();
		sp1.setBackground(new Color(0, 51, 0));
		sp1.setBounds(12, 330, 520, 2);
		contentPane.add(sp1);
		
		JTextPane txpNota = new JTextPane();
		txpNota.setBackground(UIManager.getColor("Button.background"));
		txpNota.setContentType("text/html");
		txpNota.setText("<FONT FACE=\"Tahoma\" SIZE= 3><p align=\"justify\"><b>Nota:</b> Los gastos efectuados por CAJA MENOR se deben registrar por la utilidad Reembolso/Cuadre de Caja Menor en el men\u00FA.</p></FONT>");
		txpNota.setBounds(11, 334, 529, 56);
		contentPane.add(txpNota);
		
		dchFechaPago = new JDateChooser();
		dchFechaPago.setBounds(119, 91, 138, 20);
		validarFechaPago();
		contentPane.add(dchFechaPago);
		
		txtSubtotal = new JFormattedTextField();
		txtSubtotal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				calcularTotalPagado();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				calcularTotalPagado();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				calcularTotalPagado();
			}
		});
		txtSubtotal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				calcularTotalPagado();
			}
		});
		formatearAMoneda(txtSubtotal);
		txtSubtotal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calcularTotalPagado();
			}
		});
		txtSubtotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularTotalPagado();

			}
		});
		txtSubtotal.setBounds(119, 114, 138, 20);
		contentPane.add(txtSubtotal);
		
		txtOtros = new JFormattedTextField();
		txtOtros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				calcularTotalPagado();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				calcularTotalPagado();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				calcularTotalPagado();
			}
		});
		txtOtros.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				calcularTotalPagado();
			}
		});
		formatearAMoneda(txtOtros);
		txtOtros.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calcularTotalPagado();
			}
		});
		txtOtros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularTotalPagado();

			}
		});
		txtOtros.setBounds(119, 137, 138, 20);
		contentPane.add(txtOtros);
		
		txtTotalPagado = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtTotalPagado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularTotalPagado();
			}
		});
		txtTotalPagado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				calcularTotalPagado();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				calcularTotalPagado();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				calcularTotalPagado();
			}
		});
		txtTotalPagado.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calcularTotalPagado();
			}
		});
		txtTotalPagado.setValue(0);
		txtTotalPagado.setForeground(new Color(255, 0, 0));
		txtTotalPagado.setEditable(false);
		txtTotalPagado.setBounds(119, 160, 138, 20);
		
		contentPane.add(txtTotalPagado);
		
		txtValorPago1 = new JFormattedTextField();
		txtValorPago1.setEditable(false);
		formatearAMoneda(txtValorPago1);
		txtValorPago1.setBounds(394, 183, 138, 20);
		contentPane.add(txtValorPago1);
		
		txtValorPago2 = new JFormattedTextField();
		txtValorPago2.setEditable(false);
		formatearAMoneda(txtValorPago2);
		txtValorPago2.setBounds(394, 206, 138, 20);
		contentPane.add(txtValorPago2);
		
		cbProveedor = new JComboBox();
		cbProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		cbProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearProveedor();
			}
		});
		cbProveedor.setBounds(119, 22, 414, 20);
		contentPane.add(cbProveedor);
		
		txtConcepto = new JTextField();
		txtConcepto.setBounds(119, 45, 414, 20);
		contentPane.add(txtConcepto);
		txtConcepto.setColumns(10);
		
		//Metodos que debe ejecutar la ventana al inicializarse
		listarProveedores();
		listarMediosPago1();
		listarMediosPago2();
	}
	
	//Metodo para validar la fecha de pago del egreso
	private void validarFechaPago() {
		dchFechaPago.setSelectableDateRange(new Date(), null);
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
			ventRegistrarProveedor.setVisible(true);
			cbProveedor.removeAllItems();
			listarProveedores();
		}
		
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

	//Metodo para listar medios de pago y desplegarlos en un combo box
	private void listarMediosPago1() {
		DelegadoMedioPagoProv delegadoFormaPago = new DelegadoMedioPagoProv();
		List<MedioPagoProv> mediosPago = delegadoFormaPago.listarFormaPago();
		cbFormaPago1.setModel(modeloFormaPago1);
		
		for(MedioPagoProv medioPago : mediosPago){
			modeloFormaPago1.addElement(new MedioPagoProv (medioPago.getDescripcion(), medioPago.getIdMedioPagoProv()));
			cbFormaPago1.setModel(modeloFormaPago1);
		}
	}
		
	//Metodo para listar medios de pago y desplegarlos en un combo box
	private void listarMediosPago2() {
		DelegadoMedioPagoProv delegadoFormaPago = new DelegadoMedioPagoProv();
		List<MedioPagoProv> formaPagos = delegadoFormaPago.listarFormaPago();
		modeloFormaPago2.addElement("--NINGUNA--");
		cbFormaPago2.setModel(modeloFormaPago2);
		
		for(MedioPagoProv formaPago : formaPagos){
			modeloFormaPago2.addElement(new MedioPagoProv (formaPago.getDescripcion(), formaPago.getIdMedioPagoProv()));
			cbFormaPago2.setModel(modeloFormaPago2);
		}
	}
		
	//Metodo que valida los datos ingresados para su posterior registro 
	private void validarDatos() {
		if(cbProveedor.getSelectedItem().equals("--ELIJA PROVEEDOR--")){
			JOptionPane.showMessageDialog(null, "Debe buscar un proveedor para este egreso");
		}else{
			if(txtConcepto.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Debe elegir un concepto para este egreso");
			}else{
				if(txtDocSoporte.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Debe ingresar el documento de soporte para este egreso ya sea recibo, factura u otro");
				}else{
					if(dchFechaPago.getDate()==null){
						JOptionPane.showMessageDialog(null, "Debe ingresar la fecha en que se origino el egreso");
					}else{
						if(txtSubtotal.getValue().equals(0.0)){
							JOptionPane.showMessageDialog(null, "No puede registrar un egreso sin valor");
						}else{
							if(cbFormaPago1.getSelectedItem()!=null && cbFormaPago2.getSelectedItem()!="--NINGUNA--"){
								validarMediosDePago();
							}else{
								abrirDialogoConfirmacionRegistro();
							}
							
						}
					}
				}
			}
		}
	}
	
	//Metodo para validar los medios de pago en el proveedor
	private void validarMediosDePago() {
		if((Double.parseDouble(txtValorPago1.getValue().toString()) + Double.parseDouble(txtValorPago2.getValue().toString())) == Double.parseDouble(txtTotalPagado.getValue().toString())){
			abrirDialogoConfirmacionRegistro();
		}else{
			JOptionPane.showMessageDialog(null, "La suma de los VALORES de las FORMAS DE PAGO debe ser igual al total pagado: "+txtTotalPagado.getText(),"ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}

	//Metodo para abrir ventanta de confirmacion de registro
	private void abrirDialogoConfirmacionRegistro(){
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de grabar este egreso en el cual desembolsa "+txtTotalPagado.getText()+" ?", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarEgreso();
			contabilizarEgreso();
		}else{
		
		}
	}
	//Limpiar los datos escritos 
	private void limpiarDatos() {
		cbProveedor.setSelectedIndex(0);
		txtConcepto.setText("");
		txtDocSoporte.setText("");
		dchFechaPago.setCalendar(null);
		txtSubtotal.setValue(0);
		cbFormaPago1.setSelectedIndex(0);
		cbFormaPago2.setSelectedIndex(0);
		txtOtros.setValue(0);
		txtValorPago1.setValue(0);
		txtValorPago2.setValue(0);
		txpObservaciones.setText("");
	}
	
	//Metodo para convertir una fecha de tipo date a una cadena
	public String convertirDateAString(Date strFecha){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(strFecha);
		
		return fecha;
	}
	//Metodo para registrar el egreso a la base de datos luego de haberse validado
	private void registrarEgreso() {
		DelegadoEgreso delegadoEgreso = new DelegadoEgreso();
		Egreso egreso = new Egreso();
		
		Persona proveedor = (Persona) cbProveedor.getSelectedItem();
		egreso.setIdentificacionProv(proveedor.getIdentificacion());
		egreso.setConcepto(txtConcepto.getText());
		egreso.setDocSoporte(Integer.parseInt(txtDocSoporte.getText()));
		egreso.setFechaGeneracion(new Date());
		egreso.setFechaPago(dchFechaPago.getDate());
		egreso.setSubtotal(Double.parseDouble(txtSubtotal.getValue().toString()));
		egreso.setTotalPagado(Double.parseDouble(txtTotalPagado.getValue().toString()));
		egreso.setOtros(Double.parseDouble(txtOtros.getValue().toString()));
		MedioPagoProv formaPago1 = new MedioPagoProv();
		MedioPagoProv formaPago2 = new MedioPagoProv();
		formaPago1 = (MedioPagoProv)cbFormaPago1.getSelectedItem();
		formaPago1.setIdMedioPagoProv(formaPago1.getIdMedioPagoProv());
		egreso.setMedioPagoProv1(formaPago1);
		if(cbFormaPago2.getSelectedItem().equals("--NINGUNA--")){
			egreso.setMedioPagoProv2(null);
		}else{
			formaPago2 = (MedioPagoProv)cbFormaPago2.getSelectedItem();
			formaPago2.setIdMedioPagoProv(formaPago2.getIdMedioPagoProv());
			egreso.setMedioPagoProv2(formaPago2);
		}
		egreso.setValorPago1(Double.parseDouble(txtValorPago1.getValue().toString()));
		egreso.setValorPago2(Double.parseDouble(txtValorPago2.getValue().toString()));
		egreso.setObservaciones(txpObservaciones.getText());
		egreso.setEstadoActividad("Activo");
		
		delegadoEgreso.insertarEgreso(egreso);
		codigoEgreso= delegadoEgreso.traerCodigoEgreso();
		
		abrirVentanaEgresoRegistrado(String.valueOf(codigoEgreso.get(0).getIdEgreso()));
	}

	//Metodo para contabilizar el egreso	
	private void contabilizarEgreso() {
		DelegadoContabilizacion delegadoContabilizacion = new DelegadoContabilizacion();                                                                                   
		Contabilizacion contabilizarAEgreso = new Contabilizacion();                                                                 
		Contabilizacion contabilizarABanco = new Contabilizacion();
		Contabilizacion contabilizarACaja = new Contabilizacion();
		
		contabilizarAEgreso.setCodigoCuenta(5135);
		contabilizarAEgreso.setFechaGeneracion(new Date());              
		contabilizarAEgreso.setNombreCuenta("Gastos de Administración");
		contabilizarAEgreso.setSaldoDebito(codigoEgreso.get(0).getTotalPagado());
		contabilizarAEgreso.setSaldoCredito(0);
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarAEgreso);
		
		if("Bancos".equals(codigoEgreso.get(0).getMedioPagoProv1().getNombreCuenta())||"Bancos".equals(codigoEgreso.get(0).getMedioPagoProv2().getNombreCuenta()) ){
			contabilizarABanco.setCodigoCuenta(1110);
			contabilizarABanco.setFechaGeneracion(new Date());
			contabilizarABanco.setNombreCuenta("Bancos");
			contabilizarABanco.setSaldoDebito(0);
			contabilizarABanco.setSaldoCredito(codigoEgreso.get(0).getTotalPagado());
			
			delegadoContabilizacion.insertarContabilizacion(contabilizarABanco);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
			
		}
		
		if("Caja".equals(codigoEgreso.get(0).getMedioPagoProv1().getNombreCuenta())||"Caja".equals(codigoEgreso.get(0).getMedioPagoProv2().getNombreCuenta()) ){
			contabilizarACaja.setCodigoCuenta(1105);
			contabilizarACaja.setFechaGeneracion(new Date());
			contabilizarACaja.setNombreCuenta("Caja");
			contabilizarACaja.setSaldoDebito(0);
			contabilizarACaja.setSaldoCredito(codigoEgreso.get(0).getTotalPagado());
			
			delegadoContabilizacion.insertarContabilizacion(contabilizarACaja); 
		}
	}
	//Metodo para mostrar la ventada del egreso registrado
	private void abrirVentanaEgresoRegistrado(String idEgreso) {
		VentMostrarEgresoRegistrado ventMostrarEgresoRegistrado = new VentMostrarEgresoRegistrado(idEgreso, cbProveedor.getSelectedItem().toString(), txtConcepto.getText(), txtDocSoporte.getText(), convertirDateAString(dchFechaPago.getDate()), txtSubtotal.getText(), txtOtros.getText(), txtTotalPagado.getText(), txpObservaciones.getText(), cbFormaPago1.getSelectedItem().toString(),cbFormaPago2.getSelectedItem().toString());
		ventMostrarEgresoRegistrado.setVisible(true);
		limpiarDatos();
	}
		
	//Metodo para calcular el total pagado del egreso
	private void calcularTotalPagado() {
		if(cbFormaPago1.getSelectedItem()!=null && cbFormaPago2.getSelectedItem()!="--NINGUNA--"){
			double totalPagado = Double.parseDouble(txtSubtotal.getValue().toString()) + Double.parseDouble(txtOtros.getValue().toString());
			txtTotalPagado.setValue(totalPagado);
		}else{
			double totalPagado = Double.parseDouble(txtSubtotal.getValue().toString()) + Double.parseDouble(txtOtros.getValue().toString());
			txtTotalPagado.setValue(totalPagado);
			txtValorPago1.setValue(totalPagado);
			txtValorPago2.setValue(0);
		}
	}
	
	//Habilitar combo box de la forma de pago 2 para su puesta modificacion
	private void habilitarFormaPago2() {
		if(cbFormaPago2.getSelectedItem().equals("--NINGUNA--")){
			txtValorPago1.setEditable(false);
			txtValorPago2.setEditable(false);
			calcularTotalPagado();
		}else{
			JOptionPane.showMessageDialog(null, "Debe indicar el VALOR TOTAL PAGADO con cada una de las FORMAS de PAGO iniciando por la principal", "ATENCION",  JOptionPane.INFORMATION_MESSAGE);
			txtValorPago1.setValue(0);
			txtValorPago1.setEditable(true);
			txtValorPago2.setEditable(true);
		}
		
	}
	
	//Metodo para validar el documento de soporte en caso de que este exista
	private void validarDocSoporte(){
		DelegadoEgreso delegadoGastoYOEgreso = new DelegadoEgreso();
		try{
			List<Egreso> docSoporteEncontrada = delegadoGastoYOEgreso.traerEgresoPorDocSoporte(Integer.parseInt(txtDocSoporte.getText()));
			if(Integer.parseInt(txtDocSoporte.getText())==docSoporteEncontrada.get(0).getDocSoporte()){
				String nl = System.getProperty("line.separator");
				JOptionPane.showMessageDialog(null, "Se ha detectado un EGRESO registrado con el mismo documento de referencia, por favor revise los siguientes datos:"+nl+"N° EGRESO: "+docSoporteEncontrada.get(0).getIdEgreso()+nl+"FECHA REGISTRO: "+convertirDateAString(docSoporteEncontrada.get(0).getFechaGeneracion())+nl+"VALOR TOTAL: "+formatearNumero(docSoporteEncontrada.get(0).getTotalPagado()));
				txtDocSoporte.setText("");
			}else{
				
			}
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	//Metodo para convertir un numero corriente en formato de pesos y decimales
	private String formatearNumero(Double numero){
		NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es","CO"));
		return formato.format(numero); 
	}
}
