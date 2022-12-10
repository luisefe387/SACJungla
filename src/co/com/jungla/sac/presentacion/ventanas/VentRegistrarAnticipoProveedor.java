package co.com.jungla.sac.presentacion.ventanas;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import co.com.jungla.sac.persistencia.entidades.AnticipoProveedor;
import co.com.jungla.sac.persistencia.entidades.Contabilizacion;
import co.com.jungla.sac.persistencia.entidades.MedioPagoProv;
import co.com.jungla.sac.persistencia.entidades.Persona;
import co.com.jungla.sac.persistencia.entidades.Proveedor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import co.com.jungla.sac.negocio.delegados.DelegadoAnticipoProveedor;
import co.com.jungla.sac.negocio.delegados.DelegadoContabilizacion;
import co.com.jungla.sac.negocio.delegados.DelegadoMedioPagoProv;
import co.com.jungla.sac.negocio.delegados.DelegadoProveedor;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextPane;
import javax.swing.JSeparator;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro del anticipo a los proveedores cuando se va efectuar un pago de dinero y su contabilizacion
 * @author Luis Fernando Pedroza T.
 * @version: 21/09/2016
 */
public class VentRegistrarAnticipoProveedor extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextPane txpObservaciones;
	private JTextField txtConcepto;
	private JFormattedTextField txtValor;
	private JComboBox cbProveedor;
	private JComboBox cbFormaPago;
	private JDateChooser dchFechaAnticipo;
	private DefaultComboBoxModel modeloProveedor = new DefaultComboBoxModel();
	private DefaultComboBoxModel modeloFormaPago = new DefaultComboBoxModel();
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private List<AnticipoProveedor> codigoAnticipoProveedor;
	
	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentRegistrarAnticipoProveedor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarAnticipoProveedor.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Registrar Anticipo a Proveedores");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 490, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBackground(new Color(153, 204, 153));
		lblProveedor.setOpaque(true);
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProveedor.setBounds(10, 22, 107, 22);
		contentPane.add(lblProveedor);
		
		cbProveedor = new JComboBox();
		cbProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));

		cbProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearProveedor();
			}
		});
		
            
		cbProveedor.setBounds(119, 23, 352, 20);
		contentPane.add(cbProveedor);
		
		JLabel lblConcepto = new JLabel("Concepto");
		lblConcepto.setBackground(new Color(153, 204, 153));
		lblConcepto.setOpaque(true);
		lblConcepto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConcepto.setBounds(10, 45, 107, 22);
		contentPane.add(lblConcepto);
		
		JLabel lblFechaCausacion = new JLabel("Valor");
		lblFechaCausacion.setBackground(new Color(153, 204, 153));
		lblFechaCausacion.setOpaque(true);
		lblFechaCausacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaCausacion.setBounds(10, 91, 107, 22);
		contentPane.add(lblFechaCausacion);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBackground(new Color(153, 204, 153));
		lblObservaciones.setOpaque(true);
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaciones.setBounds(10, 114, 107, 45);
		contentPane.add(lblObservaciones);
		
		txtValor = new JFormattedTextField();
		formatearAMoneda(txtValor);
		txtValor.setBounds(119, 92, 138, 20);
		contentPane.add(txtValor);
		
		JLabel lblOrigenFondos = new JLabel("Origen Fondos");
		lblOrigenFondos.setBackground(new Color(153, 204, 153));
		lblOrigenFondos.setOpaque(true);
		lblOrigenFondos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrigenFondos.setBounds(10, 160, 107, 22);
		contentPane.add(lblOrigenFondos);
		 
		JButton btnGuardarAnticipo = new JButton("Registrar Anticipo");
		btnGuardarAnticipo.setForeground(new Color(0, 51, 0));
		btnGuardarAnticipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGuardarAnticipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
		});
		btnGuardarAnticipo.setBounds(103, 206, 145, 23);
		contentPane.add(btnGuardarAnticipo);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(276, 206, 89, 23);
		contentPane.add(btnCerrar);
		
		dchFechaAnticipo = new JDateChooser();
		dchFechaAnticipo.setBounds(119, 69, 138, 20);
		dchFechaAnticipo.setSelectableDateRange(null, new Date());
		contentPane.add(dchFechaAnticipo);
		
		JLabel lblFechaAnticipo = new JLabel("Fecha Anticipo");
		lblFechaAnticipo.setBackground(new Color(153, 204, 153));
		lblFechaAnticipo.setOpaque(true);
		lblFechaAnticipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaAnticipo.setBounds(10, 68, 107, 22);
		contentPane.add(lblFechaAnticipo);
		
		txpObservaciones = new JTextPane();
		txpObservaciones.setBounds(119, 115, 352, 43);
		contentPane.add(txpObservaciones);
		
		txtConcepto = new JTextField();
		txtConcepto.setEditable(false);
		txtConcepto.setBounds(119, 46, 352, 20);
		txtConcepto.setText("Anticipo y Avances a Proveedores");
		contentPane.add(txtConcepto);
		txtConcepto.setColumns(10);
		
		cbFormaPago = new JComboBox();
		cbFormaPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearMediosPago();
			}
		});
		cbFormaPago.setBounds(119, 161, 352, 20);
		contentPane.add(cbFormaPago);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 193, 461, 2);
		contentPane.add(sp);
		
		//Metodos que debe ejecutar la ventana al inicializarse
		listarProveedores();
		listarMediosPago();
		
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
	
	//Metodo para listar los medios de pago y desplegarlos en un combo box
	private void listarMediosPago() {
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
	
	//Metodo para crear los medios de pago luego de ser elegido dentro del combo box
		private void crearMediosPago() {
			if(cbFormaPago.getSelectedItem().equals("--NUEVA FORMA DE PAGO--")){
				VentRegistrarMediosPagoProv ventRegistrarFormaPago = new VentRegistrarMediosPagoProv();
				cbFormaPago.removeAllItems();
				ventRegistrarFormaPago.addWindowListener(new WindowAdapter() {
	                @Override
	                public void windowClosed(WindowEvent e) {
	                	listarMediosPago();  
	                }
	            });
				ventRegistrarFormaPago.setVisible(true);
			}
		}
	//Metodo que valida los datos ingresados para su posterior registro 
	private void validarDatos() {
		if(cbProveedor.getSelectedItem().equals("--ELIJA PROVEEDOR--")){
			JOptionPane.showMessageDialog(null, "Debe elegir un proveedor");
		}else{
			if(dchFechaAnticipo.getDate()==null){
				JOptionPane.showMessageDialog(null, "Debe ingresar la fecha de anticipación");
			}else{
				if(txtValor.getValue().equals(0)){
					JOptionPane.showMessageDialog(null, "No puede registrar un ANTICIPO PROVEEDOR sin valor");
				}else{
					if(cbFormaPago.getSelectedItem().equals("--ELIJA FORMA DE PAGO--")){
						JOptionPane.showMessageDialog(null, "Debe elegir un origen de fondos como forma de pago ");
					}else{
						abrirDialogoConfirmacionRegistro();
					}
					
				}
			}
		}
	}
	
	//Metodo para abrir ventanta de confirmacion de registro
	private void abrirDialogoConfirmacionRegistro(){
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de grabar este ANTICIPO PROVEEDOR ?", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarAnticipoProveedor();
			contabilizarAnticipoProveedor();
			limpiarDatos();
		}else{
		
		}
	}
	
	//Limpiar los datos escritos 
	private void limpiarDatos() {
		cbProveedor.setSelectedIndex(0);
		dchFechaAnticipo.setCalendar(null);
		txtValor.setValue(0);
		txpObservaciones.setText("");
		cbFormaPago.setSelectedIndex(0);
	}
	
	//Metodo para registrar el anticipo proveedor a la base de datos luego de haberse validado
	private void registrarAnticipoProveedor() {
		DelegadoAnticipoProveedor delegadoAnticipoProveedor = new DelegadoAnticipoProveedor();
		AnticipoProveedor anticipoProveedor = new AnticipoProveedor();
		
		Persona proveedor = (Persona) cbProveedor.getSelectedItem();
		anticipoProveedor.setIdentificacionProv(proveedor.getIdentificacion());
		anticipoProveedor.setConcepto(txtConcepto.getText());
		anticipoProveedor.setFechaAnticipo(dchFechaAnticipo.getDate());
		MedioPagoProv medioPago = new MedioPagoProv();
		medioPago = (MedioPagoProv)cbFormaPago.getSelectedItem();
		medioPago.setIdMedioPagoProv(medioPago.getIdMedioPagoProv());
		anticipoProveedor.setMedioPagoProv(medioPago);
		anticipoProveedor.setValorAnticipo(Double.parseDouble(txtValor.getValue().toString()));
		anticipoProveedor.setObservaciones(txpObservaciones.getText());
		anticipoProveedor.setFechaGeneracion(new Date());
		anticipoProveedor.setEstadoAnticipo("Pendiente");
		anticipoProveedor.setEstadoActividad("Activo");
		anticipoProveedor.setCxp(0);
		anticipoProveedor.setEgreso(0);
		anticipoProveedor.setFechaEgreso(null);
		
		delegadoAnticipoProveedor.insertarAnticipoProveedor(anticipoProveedor);
		codigoAnticipoProveedor= delegadoAnticipoProveedor.traerCodigoAnticipoProveedor();
		
		JOptionPane.showMessageDialog(null, "Se ha registrado el ANTICIPO de PROVEEDOR satisfactoriamente con consecutivo N°"+codigoAnticipoProveedor.get(0).getIdAnticipoProveedor());
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
	
	//Metodo para contabilizar la anticipo a proveedor
	private void contabilizarAnticipoProveedor() {
		DelegadoContabilizacion delegadoContabilizacion = new DelegadoContabilizacion();                                                                                   
		Contabilizacion contabilizarAAnticipo = new Contabilizacion();                                                                 
		Contabilizacion contabilizarABanco = new Contabilizacion();
		Contabilizacion contabilizarACaja = new Contabilizacion();
		
		contabilizarAAnticipo.setCodigoCuenta(1330);
		contabilizarAAnticipo.setFechaGeneracion(new Date());              
		contabilizarAAnticipo.setNombreCuenta("Anticipo a Proveedores");
		contabilizarAAnticipo.setSaldoDebito(codigoAnticipoProveedor.get(0).getValorAnticipo());
		contabilizarAAnticipo.setSaldoCredito(0);
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarAAnticipo);
		
		if("Banco".equals(codigoAnticipoProveedor.get(0).getMedioPagoProv().getNombreCuenta())){
			contabilizarABanco.setCodigoCuenta(1110);
			contabilizarABanco.setFechaGeneracion(new Date());
			contabilizarABanco.setNombreCuenta("Bancos");
			contabilizarABanco.setSaldoDebito(0);
			contabilizarABanco.setSaldoCredito(codigoAnticipoProveedor.get(0).getValorAnticipo());
			
			delegadoContabilizacion.insertarContabilizacion(contabilizarABanco);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
		}else{
			contabilizarACaja.setCodigoCuenta(1105);
			contabilizarACaja.setFechaGeneracion(new Date());
			contabilizarACaja.setNombreCuenta("Caja");
			contabilizarACaja.setSaldoDebito(0);
			contabilizarACaja.setSaldoCredito(codigoAnticipoProveedor.get(0).getValorAnticipo());
			
			delegadoContabilizacion.insertarContabilizacion(contabilizarACaja); 
		}
	}
}
