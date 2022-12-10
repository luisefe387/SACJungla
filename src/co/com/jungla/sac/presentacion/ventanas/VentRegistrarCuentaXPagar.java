package co.com.jungla.sac.presentacion.ventanas;

import java.net.URL;
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

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import co.com.jungla.sac.persistencia.entidades.CompraArticulos;
import co.com.jungla.sac.persistencia.entidades.Contabilizacion;
import co.com.jungla.sac.persistencia.entidades.CuentaXPagar;
import co.com.jungla.sac.persistencia.entidades.Egreso;
import co.com.jungla.sac.persistencia.entidades.PagoAbonoCxP;
import co.com.jungla.sac.persistencia.entidades.Persona;
import co.com.jungla.sac.persistencia.entidades.Proveedor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import co.com.jungla.sac.negocio.delegados.DelegadoContabilizacion;
import co.com.jungla.sac.negocio.delegados.DelegadoCuentaXPagar;
import co.com.jungla.sac.negocio.delegados.DelegadoEgreso;
import co.com.jungla.sac.negocio.delegados.DelegadoPagoAbonoCxP;
import co.com.jungla.sac.negocio.delegados.DelegadoProveedor;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro de la cuenta por pagar y su contabilizacion
 * @author Luis Fernando Pedroza T.
 * @version: 21/09/2016
 */
public class VentRegistrarCuentaXPagar extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtDocSoporte;
	private JTextField txtConcepto;
	private JFormattedTextField txtSubtotal;
	private JFormattedTextField txtOtros;
	private JFormattedTextField txtTotalPagar;
	private JTextPane txpObservaciones;
	private JComboBox cbProveedor;
	private JDateChooser dchFechaCausacion;
	private JDateChooser dchFechaPago;
	private DefaultComboBoxModel modeloProveedor = new DefaultComboBoxModel();
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private List<CuentaXPagar> codigoCxP;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentRegistrarCuentaXPagar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarCuentaXPagar.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Registro de Cuenta X Pagar");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 532, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBackground(new Color(153, 204, 153));
		lblProveedor.setOpaque(true);
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProveedor.setBounds(10, 110, 107, 22);
		contentPane.add(lblProveedor);
		
		cbProveedor = new JComboBox();
		cbProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		//Evento en la que permite el registro de una linea cuando esta no existe en el combo box
		cbProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearProveedor();
			}
		});
		
            
		cbProveedor.setBounds(119, 111, 395, 20);
		contentPane.add(cbProveedor);
		
		JLabel lblConcepto = new JLabel("Concepto");
		lblConcepto.setBackground(new Color(153, 204, 153));
		lblConcepto.setOpaque(true);
		lblConcepto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConcepto.setBounds(10, 133, 107, 22);
		contentPane.add(lblConcepto);
		
		JLabel lblFechaCausacion = new JLabel("Fecha Causacion");
		lblFechaCausacion.setBackground(new Color(153, 204, 153));
		lblFechaCausacion.setOpaque(true);
		lblFechaCausacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaCausacion.setBounds(10, 179, 107, 22);
		contentPane.add(lblFechaCausacion);
		
		JLabel lblFechaPago = new JLabel("Fecha Pago");
		lblFechaPago.setBackground(new Color(153, 204, 153));
		lblFechaPago.setOpaque(true);
		lblFechaPago.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaPago.setBounds(10, 202, 107, 22);
		contentPane.add(lblFechaPago);
		
		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setBackground(new Color(153, 204, 153));
		lblSubtotal.setOpaque(true);
		lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSubtotal.setBounds(10, 225, 107, 22);
		contentPane.add(lblSubtotal);
		
		txtSubtotal = new JFormattedTextField();
		txtSubtotal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				calcularTotalPagar();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				calcularTotalPagar();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				calcularTotalPagar();
			}
		});
		txtSubtotal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				calcularTotalPagar();
			}
		});
		formatearAMoneda(txtSubtotal);
		txtSubtotal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calcularTotalPagar();
			}
		});
		txtSubtotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularTotalPagar();

			}
		});
		txtSubtotal.setBounds(119, 226, 138, 20);
		contentPane.add(txtSubtotal);
		
		JLabel lblOtros = new JLabel("Otros(+)");
		lblOtros.setBackground(new Color(153, 204, 153));
		lblOtros.setOpaque(true);
		lblOtros.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOtros.setBounds(10, 248, 107, 22);
		contentPane.add(lblOtros);
		
		txtOtros = new JFormattedTextField();
		txtOtros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				calcularTotalPagar();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				calcularTotalPagar();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				calcularTotalPagar();
			}
		});
		txtOtros.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				calcularTotalPagar();
			}
		});
		formatearAMoneda(txtOtros);
		txtOtros.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calcularTotalPagar();
			}
		});
		txtOtros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularTotalPagar();

			}
		});
		txtOtros.setBounds(119, 249, 138, 20);
		contentPane.add(txtOtros);
		
		JLabel lblTotalPagar = new JLabel("Total a Pagar");
		lblTotalPagar.setBackground(new Color(153, 204, 153));
		lblTotalPagar.setOpaque(true);
		lblTotalPagar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalPagar.setBounds(10, 271, 107, 22);
		contentPane.add(lblTotalPagar);
		
		txtTotalPagar = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtTotalPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularTotalPagar();
			}
		});
		txtTotalPagar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				calcularTotalPagar();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				calcularTotalPagar();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				calcularTotalPagar();
			}
		});
		txtTotalPagar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calcularTotalPagar();
			}
		});
		txtTotalPagar.setValue(0);
		txtTotalPagar.setForeground(new Color(255, 0, 0));
		txtTotalPagar.setEditable(false);
		txtTotalPagar.setBounds(119, 272, 138, 20);
		contentPane.add(txtTotalPagar);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBackground(new Color(153, 204, 153));
		lblObservaciones.setOpaque(true);
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaciones.setBounds(10, 294, 107, 87);
		contentPane.add(lblObservaciones);
		 
		JButton btnGuardarCxP = new JButton("Registrar CxP");
		btnGuardarCxP.setForeground(new Color(0, 51, 0));
		btnGuardarCxP.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGuardarCxP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
		});
		btnGuardarCxP.setBounds(136, 404, 117, 23);
		contentPane.add(btnGuardarCxP);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(292, 404, 89, 23);
		contentPane.add(btnCerrar);
		
		dchFechaCausacion = new JDateChooser();
		dchFechaCausacion.setBounds(119, 180, 138, 20);
		contentPane.add(dchFechaCausacion);
		
		dchFechaPago = new JDateChooser();
		dchFechaPago.setBounds(119, 203, 138, 20);
		contentPane.add(dchFechaPago);
		
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
		txtDocSoporte.setBounds(119, 157, 138, 20);
		txtDocSoporte.setDocument(new LimitadorCaracteres());
		contentPane.add(txtDocSoporte);
		
		JLabel lblDocSoporte = new JLabel("Doc. Soporte");
		lblDocSoporte.setBackground(new Color(153, 204, 153));
		lblDocSoporte.setOpaque(true);
		lblDocSoporte.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDocSoporte.setBounds(10, 156, 107, 22);
		contentPane.add(lblDocSoporte);
		
		txpObservaciones = new JTextPane();
		txpObservaciones.setBounds(119, 295, 395, 85);
		contentPane.add(txpObservaciones);
		
		JTextPane txpNota = new JTextPane();
		txpNota.setContentType("text/html");
		txpNota.setText("<FONT FACE=\"Tahoma\" SIZE= 3><p align=\"justify\"><b>NOTA: </b> Registre Aqu\u00ED todas las Cuentas x Pagar de bienes y servicios adquiridos por usted que NO sean COMPRAS de art\u00EDculos y/o materiales que hagan parte de su inventario de venta. Las Cuentas x Pagar resultantes de COMPRAS de INVENTARIO se gener\u00E1n automaticamente cuando usted graba una COMPRA por la interfase de COMPRAS ARTICULOS Y COMPRAS DE MATERIALES. </p></FONT>");
		txpNota.setBackground(UIManager.getColor("Button.background"));
		txpNota.setBounds(10, 3, 504, 96);
		contentPane.add(txpNota);
		
		txtConcepto = new JTextField();
		txtConcepto.setBounds(119, 134, 395, 20);
		contentPane.add(txtConcepto);
		txtConcepto.setColumns(10);
		
		//Metodos que debe ejecutar la ventana al inicializarse
		listarProveedores();
		
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
	//Metodo que valida los datos ingresados para su posterior registro 
	private void validarDatos() {
		if(cbProveedor.getSelectedItem().equals("--ELIJA PROVEEDOR--")){
			JOptionPane.showMessageDialog(null, "Debe elegir un proveedor");
		}else{
			if(txtConcepto.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Debe ingresar un concepto de pago");
			}else{
				if(txtDocSoporte.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Debe ingresar el documento de soporte para esta CxP ya sea recibo, factura u otro");
				}else{
					if(dchFechaCausacion.getDate()==null){
						JOptionPane.showMessageDialog(null, "Debe ingresar la fecha de causacion");
					}else{
						if(dchFechaPago.getDate()==null){
							JOptionPane.showMessageDialog(null, "Debe ingresar la fecha de pago");
						}else{
							if(txtSubtotal.getValue().equals(0)){
								JOptionPane.showMessageDialog(null, "No puede registrar una CxP sin valor");
							}else{
								validarFechas();
							}
						}
					}
				}
			}
		}
	}
	
	//Metodo para validar las fechas de causacion y de pago, evitando que logicamente la fecha de causacion sea inferior a la fecha de pago
	private void validarFechas() {
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

	//Metodo para abrir ventanta de confirmacion de registro
	private void abrirDialogoConfirmacionRegistro(){
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de grabar esta CxP", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarCxP();
			contabilizarCxP();
		}else{
		
		}
	}
	
	//Limpiar los datos escritos 
	private void limpiarDatos() {
		cbProveedor.setSelectedIndex(0);
		txtConcepto.setText("");
		txtDocSoporte.setText("");
		dchFechaCausacion.setCalendar(null);
		dchFechaPago.setCalendar(null);
		txtSubtotal.setValue(0);
		txtTotalPagar.setValue(0);
		txtOtros.setValue(0);
		txpObservaciones.setText("");
	}
	
	//Metodo para convertir una fecha de tipo date a una cadena
	public String convertirDateAString(Date strFecha){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(strFecha);
		
		return fecha;
	}
	
	//Metodo para registrar la cuenta por pagar a la base de datos luego de haberse validado
	private void registrarCxP() {
		DelegadoCuentaXPagar delegadoCuentaXPagar = new DelegadoCuentaXPagar();
		CuentaXPagar cuentaXPagar = new CuentaXPagar();
		
		Persona proveedor = (Persona) cbProveedor.getSelectedItem();
		cuentaXPagar.setIdentificacionProv(proveedor.getIdentificacion());
		cuentaXPagar.setConcepto(txtConcepto.getText());
		cuentaXPagar.setDocSoporte(Integer.parseInt(txtDocSoporte.getText()));
		cuentaXPagar.setFechaCausacion(dchFechaCausacion.getDate());
		cuentaXPagar.setFechaPago(dchFechaPago.getDate());
		cuentaXPagar.setSubtotal(Double.parseDouble(txtSubtotal.getValue().toString()));
		cuentaXPagar.setTotalPagar(Double.parseDouble(txtTotalPagar.getValue().toString()));
		cuentaXPagar.setOtros(Double.parseDouble(txtOtros.getValue().toString()));
		cuentaXPagar.setObservaciones(txpObservaciones.getText());
		cuentaXPagar.setEstado("Pendiente");
		cuentaXPagar.setCompra(0);
		cuentaXPagar.setFechaGeneracion(new Date());
		
		delegadoCuentaXPagar.insertarCuentaXPagar(cuentaXPagar);
		codigoCxP= delegadoCuentaXPagar.traerCodigoCuentaXPagar();
		
		abrirVentanaCxPRegistrada(String.valueOf(codigoCxP.get(0).getIdCuentaXPagar()));
		
	}
	//Metodo para confirmar el registro de la cuenta por pagar
	private void abrirVentanaCxPRegistrada(String idCuentaXPagar) {
		VentMostrarCxPRegistrada ventMostrarCxPRegistrada = new VentMostrarCxPRegistrada(idCuentaXPagar, cbProveedor.getSelectedItem().toString(), txtConcepto.getText(), txtDocSoporte.getText(), convertirDateAString(dchFechaCausacion.getDate()), convertirDateAString(dchFechaPago.getDate()), txtSubtotal.getText(), txtOtros.getText(), txtTotalPagar.getText(), txpObservaciones.getText());
		ventMostrarCxPRegistrada.setVisible(true);
		limpiarDatos();
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
	
	//Metodo para calcular el total a pagar de la cuenta por pagar
	private void calcularTotalPagar() {

		double totalPagado = Double.parseDouble(txtSubtotal.getValue().toString()) + Double.parseDouble(txtOtros.getValue().toString());
		txtTotalPagar.setValue(totalPagado);
		
	}
	//Metodo para validar el documento de soporte en caso de que este exista
	private void validarDocSoporte(){
		DelegadoCuentaXPagar delegadoCuentaXPagar = new DelegadoCuentaXPagar();
		try{
			List<CuentaXPagar> docSoporteEncontrada = delegadoCuentaXPagar.traerCxPPorDocSoporte(Integer.parseInt(txtDocSoporte.getText()));
			if(Integer.parseInt(txtDocSoporte.getText())==docSoporteEncontrada.get(0).getDocSoporte()){
				String nl = System.getProperty("line.separator");
				JOptionPane.showMessageDialog(null, "Se ha detectado una CUENTA X PAGAR registrada con el mismo documento de referencia, por favor revise los siguientes datos:"+nl+"N° CXP: "+docSoporteEncontrada.get(0).getIdCuentaXPagar()+nl+"FECHA REGISTRO: "+convertirDateAString(docSoporteEncontrada.get(0).getFechaGeneracion())+nl+"VALOR TOTAL: "+formatearNumero(docSoporteEncontrada.get(0).getTotalPagar()));
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
	
	//Metodo para contabilizar la cuenta por pagar
	private void contabilizarCxP() {
		DelegadoContabilizacion delegadoContabilizacion = new DelegadoContabilizacion();                                                                                   
		Contabilizacion contabilizarACxP = new Contabilizacion();                                                                 
		Contabilizacion contabilizarAConcepto = new Contabilizacion();
		
		contabilizarACxP.setCodigoCuenta(2335);
		contabilizarACxP.setFechaGeneracion(new Date());              
		contabilizarACxP.setNombreCuenta("Cuentas por Pagar");
		contabilizarACxP.setSaldoDebito(0);
		contabilizarACxP.setSaldoCredito(codigoCxP.get(0).getTotalPagar());
		
		contabilizarAConcepto.setCodigoCuenta(1110);
		contabilizarAConcepto.setFechaGeneracion(new Date());
		contabilizarAConcepto.setNombreCuenta("Bancos");
		contabilizarAConcepto.setSaldoDebito(codigoCxP.get(0).getTotalPagar());
		contabilizarAConcepto.setSaldoCredito(0);
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarACxP); 
		delegadoContabilizacion.insertarContabilizacion(contabilizarAConcepto); 
		
	}
}
