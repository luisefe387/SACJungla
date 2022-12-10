package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import co.com.jungla.sac.persistencia.entidades.Departamento;
import co.com.jungla.sac.persistencia.entidades.Municipio;
import co.com.jungla.sac.persistencia.entidades.Proveedor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JTextPane;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.UIManager;

import co.com.jungla.sac.negocio.delegados.DelegadoDepartamento;
import co.com.jungla.sac.negocio.delegados.DelegadoMunicipio;
import co.com.jungla.sac.negocio.delegados.DelegadoProveedor;
import java.awt.Toolkit;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro del proveedor
 * @author Luis Fernando Pedroza T.
 * @version: 21/09/2016
 */
public class VentRegistrarProveedor extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtIdentificacion;
	private JTextField txtIdentificacion_1;
	private JTextField txtNombre;
	private JTextField txtAbreviatura;
	private JTextField txtDireccion;
	private JTextPane txaObservaciones;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtWeb;
	final JLabel lbGuion;
	private JLabel lblSinpuntos;
	final JComboBox cbTipoProveedor;
	final JComboBox cbDepartamento;
	final JComboBox cbMunicipio;
	final JComboBox cbIdentificacion;
	final JComboBox cbRegimen;
	final JComboBox cbTipoPersona;
	private DefaultComboBoxModel modeloDepartamento = new DefaultComboBoxModel();
	private DefaultComboBoxModel modeloMunicipio = new DefaultComboBoxModel();
	private JTextField txtCelular;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentRegistrarProveedor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarProveedor.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Nuevo Proveedor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(80, 80, 512, 499);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbTipoProveedor = new JComboBox();
		cbTipoProveedor.setModel(new DefaultComboBoxModel(new String[] {"PROVEEDOR PARA COMPRAS DE ARTICULOS", "PROVEEDOR PARA GASTOS"}));
		cbTipoProveedor.setBounds(119, 22, 375, 20);
		contentPane.add(cbTipoProveedor);
		
		JLabel lblTipoProveedor = new JLabel("Proveedor *");
		lblTipoProveedor.setBackground(new Color(153, 204, 153));
		lblTipoProveedor.setOpaque(true);
		lblTipoProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipoProveedor.setBounds(10, 21, 107, 22);
		contentPane.add(lblTipoProveedor);
		
		JLabel lblIdentificacion = new JLabel("Identificaci\u00F3n *");
		lblIdentificacion.setBackground(new Color(153, 204, 153));
		lblIdentificacion.setOpaque(true);
		lblIdentificacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIdentificacion.setBounds(10, 67, 107, 22);
		contentPane.add(lblIdentificacion);
		
		txtIdentificacion = new JTextField();
		txtIdentificacion.setDocument(new LimitadorCaracteres());
		txtIdentificacion.setBounds(186, 68, 107, 20);
		contentPane.add(txtIdentificacion);
		txtIdentificacion.setColumns(10);
		
		txtIdentificacion_1 = new JTextField();
		txtIdentificacion_1.setDocument(new LimitadorCaracteres());
		txtIdentificacion_1.setBounds(299, 68, 25, 20);
		contentPane.add(txtIdentificacion_1);
		txtIdentificacion_1.setColumns(10);
		
		final JLabel lblNombre = new JLabel("Nombre *");
		lblNombre.setBackground(new Color(153, 204, 153));
		lblNombre.setOpaque(true);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(10, 113, 107, 22);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(119, 114, 375, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblAbreviatura = new JLabel("Abreviatura");
		lblAbreviatura.setBackground(new Color(153, 204, 153));
		lblAbreviatura.setOpaque(true);
		lblAbreviatura.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAbreviatura.setBounds(10, 136, 107, 22);
		contentPane.add(lblAbreviatura);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n *");
		lblDireccion.setBackground(new Color(153, 204, 153));
		lblDireccion.setOpaque(true);
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDireccion.setBounds(10, 205, 107, 22);
		contentPane.add(lblDireccion);
		
		txtAbreviatura = new JTextField();
		txtAbreviatura.setBounds(119, 137, 120, 20);
		contentPane.add(txtAbreviatura);
		txtAbreviatura.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(119, 206, 375, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setForeground(new Color(0, 51, 0));
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
			});
		btnRegistrar.setBounds(151, 430, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrar();
			}
		});
		btnCerrar.setBounds(262, 430, 89, 23);
		contentPane.add(btnCerrar);
		
		lbGuion = new JLabel("-");
		lbGuion.setBounds(294, 71, 9, 14);
		contentPane.add(lbGuion);
		
		JLabel lblTipoPersona = new JLabel("Tipo Persona*");
		lblTipoPersona.setOpaque(true);
		lblTipoPersona.setOpaque(true);
		lblTipoPersona.setBackground(new Color(153, 204, 153));
		lblTipoPersona.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipoPersona.setBounds(10, 44, 107, 22);
		contentPane.add(lblTipoPersona);
		
		cbTipoPersona = new JComboBox();
		cbIdentificacion = new JComboBox();
		//Evento del combo box en la que dependiendo del tipo de identificacion inhabilita la otra caja de texto en la que va el resto de numeros
		cbIdentificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbIdentificacion.getSelectedItem().equals("CC") || cbIdentificacion.getSelectedItem().equals("CE") || cbIdentificacion.getSelectedItem().equals("TI")){
					txtIdentificacion_1.setEditable(false);
					txtIdentificacion.setText("");
					txtIdentificacion_1.setText("");
				}else{
					txtIdentificacion_1.setEditable(true);
					txtIdentificacion.setText("");
					txtIdentificacion_1.setText("");
				}
			}
		});
		cbRegimen = new JComboBox();
		cbTipoPersona.setModel(new DefaultComboBoxModel(new String[] {"PERSONA NATURAL", "PERSONA JURIDICA"}));
		cbTipoPersona.setBounds(119, 45, 184, 20);
		contentPane.add(cbTipoPersona);
		
		cbIdentificacion.setModel(new DefaultComboBoxModel(new String[] {"NIT", "CC", "CE", "TI"}));
		cbIdentificacion.setBounds(119, 68, 60, 20);
		contentPane.add(cbIdentificacion);
		
		cbRegimen.setModel(new DefaultComboBoxModel(new String[] {"R\u00C9GIMEN SIMPLICADO", "R\u00C9GIMEN COM\u00DAN", "GRAN CONTRIBUYENTE", "NINGUNO"}));
		cbRegimen.setBounds(119, 91, 184, 20);
		contentPane.add(cbRegimen);
		
		//Evento en la que al elegir un tipo de persona despliegue en otro combo box los tipos de identificacion
		cbTipoPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbTipoPersona.getSelectedItem().equals("PERSONA NATURAL")){
					cbIdentificacion.setModel(new DefaultComboBoxModel(new String[] {"NIT", "CC", "CE", "TI"}));
					cbIdentificacion.setBounds(119, 68, 60, 20);
					contentPane.add(cbIdentificacion);
					cbRegimen.setModel(new DefaultComboBoxModel(new String[] {"R\u00E9gimen Simplicado", "R\u00E9gimen Com\u00FAn", "Gran Contribuyente", "Ninguno"}));
					cbRegimen.setBounds(119, 91, 184, 20);
					contentPane.add(cbRegimen);
					lblNombre.setText("Nombre");
				}else{
					cbIdentificacion.setModel(new DefaultComboBoxModel(new String[] {"NIT"}));
					cbIdentificacion.setBounds(119, 68, 60, 20);
					contentPane.add(cbIdentificacion);
					cbRegimen.setModel(new DefaultComboBoxModel(new String[] {"R\u00E9gimen Com\u00FAn", "Gran Contribuyente", "Ninguno"}));
					cbRegimen.setBounds(119, 91, 184, 20);
					contentPane.add(cbRegimen);
					lblNombre.setText("Razón Social");
					
				}
			}
		});
		
		JLabel lblRegimen = new JLabel("Regimen *");
		lblRegimen.setBackground(new Color(153, 204, 153));
		lblRegimen.setOpaque(true);
		lblRegimen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRegimen.setBounds(10, 90, 107, 22);
		contentPane.add(lblRegimen);
		
		cbDepartamento = new JComboBox();
		//listar todos los municipios cada vez que se seleccione un departamento
		cbDepartamento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cbMunicipio.removeAllItems();
				listarMuncipios(cbDepartamento.getSelectedItem().toString());
			}
		});
		
		cbDepartamento.setBounds(119, 160, 184, 20);
		contentPane.add(cbDepartamento);
		
		cbMunicipio = new JComboBox();
		cbMunicipio.setBounds(119, 183, 184, 20);
		contentPane.add(cbMunicipio);
		
		JLabel lblDepartamento = new JLabel("Departamento *");
		lblDepartamento.setOpaque(true);
		lblDepartamento.setBackground(new Color(153, 204, 153));
		lblDepartamento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDepartamento.setBounds(10, 159, 107, 22);
		contentPane.add(lblDepartamento);
		
		JLabel lblCiudad = new JLabel("Ciudad *");
		lblCiudad.setBackground(new Color(153, 204, 153));
		lblCiudad.setOpaque(true);
		lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCiudad.setBounds(10, 182, 107, 22);
		contentPane.add(lblCiudad);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono *");
		lblTelefono.setBackground(new Color(153, 204, 153));
		lblTelefono.setOpaque(true);
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelefono.setBounds(10, 228, 107, 22);
		contentPane.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setDocument(new LimitadorCaracteres());
		txtTelefono.setBounds(119, 229, 375, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBackground(new Color(153, 204, 153));
		lblEmail.setOpaque(true);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(10, 274, 107, 22);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(119, 275, 375, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblWeb = new JLabel("Web");
		lblWeb.setBackground(new Color(153, 204, 153));
		lblWeb.setOpaque(true);
		lblWeb.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWeb.setBounds(10, 297, 107, 22);
		contentPane.add(lblWeb);
		
		txtWeb = new JTextField();
		txtWeb.setBounds(119, 298, 375, 20);
		contentPane.add(txtWeb);
		txtWeb.setColumns(10);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBackground(new Color(153, 204, 153));
		lblObservaciones.setOpaque(true);
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaciones.setBounds(10, 320, 107, 91);
		contentPane.add(lblObservaciones);
		
		txaObservaciones = new JTextPane();
		txaObservaciones.setBounds(119, 321, 375, 89);
		contentPane.add(txaObservaciones);
		
		lblSinpuntos = new JLabel("(Sin Puntos)");
		lblSinpuntos.setBounds(334, 71, 114, 14);
		contentPane.add(lblSinpuntos);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setOpaque(true);
		lblCelular.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCelular.setBackground(new Color(153, 204, 153));
		lblCelular.setBounds(10, 251, 107, 22);
		contentPane.add(lblCelular);
		
		txtCelular = new JTextField();
		txtCelular.setDocument(new LimitadorCaracteres());
		txtCelular.setColumns(10);
		txtCelular.setBounds(119, 252, 375, 20);
		contentPane.add(txtCelular);
		
		//Metodo que debe ejecutar la ventana al inicializarse
		listarDepartamentos();
	}

	//Metodo que permite listar todas los departamentos registrados
	private void listarDepartamentos() {
		DelegadoDepartamento delegadoDepartamento = new DelegadoDepartamento();
		List<Departamento> departamentos = delegadoDepartamento.listarDepartamentos();
		modeloDepartamento.addElement("--ELIJA DEPARTAMENTO--");
		
		for(Departamento departamento : departamentos){
			modeloDepartamento.addElement(new Departamento(departamento.getNombre(), departamento.getIdDepartamento()));
			cbDepartamento.setModel(modeloDepartamento);
		}
		
	}
	
	//Metodo para listar los municipios de cada departamento elegido 
	private void listarMuncipios(String nombre) {
		
		try{
			modeloMunicipio.removeAllElements();
			DelegadoMunicipio delegadoMunicipio = new DelegadoMunicipio();
			List<Municipio> municipios = delegadoMunicipio.listarMunicipiosPorDepartamento(nombre);
			modeloMunicipio.addElement("--ELIJA CIUDAD--");
			
			for(Municipio municipio: municipios){
				
				modeloMunicipio.addElement(new Municipio(municipio.getNombre(), municipio.getIdMunicipio()));
				cbMunicipio.setModel(modeloMunicipio);
			}
		}catch(Exception e){
			e.getMessage();
		}
	}

	//Metodo que permite la validacion los datos ingresados para el registro del cliente
	private void validarDatos() {
		
		if((cbIdentificacion.getSelectedItem().equals("NIT") && (txtIdentificacion.getText().isEmpty()|| txtIdentificacion_1.getText().isEmpty()))||(cbIdentificacion.getSelectedItem()!="NIT" && txtIdentificacion.getText().isEmpty())||(txtNombre.getText().isEmpty())||(cbDepartamento.getSelectedItem().equals("--ELIJA DEPARTAMENTO--"))||(cbMunicipio.getSelectedItem().equals("")||cbMunicipio.getSelectedItem().equals("--ELIJA CIUDAD--")) || (txtDireccion.getText().equals("")||txtTelefono.getText().equals(""))){
			JOptionPane.showMessageDialog(null, "Debe llenar todos los campos obligatorios (*)");
		}else{
			abrirDialogoProveedorRegistrado();
		}
		
	}
	
	//Metodo para limpiar los datos escritos
	private void limpiarDatos() {
		cbTipoProveedor.setSelectedIndex(0);
		cbTipoPersona.setSelectedIndex(0);
		cbRegimen.setSelectedIndex(0);
		cbIdentificacion.setSelectedIndex(0);
		txtIdentificacion.setText("");
		txtIdentificacion_1.setText("");
		txtNombre.setText("");
		txtAbreviatura.setText("");
		cbDepartamento.setSelectedIndex(0);
		txtDireccion.setText("");
		txtTelefono.setText("");
		txtCelular.setText("");
		txtEmail.setText("");
		txtWeb.setText("");
		txaObservaciones.setText("");
	}

	//Metodo que permite el registro del proveedor a la base de datos luego de haberse validado
	private void registrarProveedor() {
		Proveedor proveedor = new Proveedor();
		DelegadoProveedor delegadoProveedor = new DelegadoProveedor();
		Departamento departamento = new Departamento();
		Municipio municipio = new Municipio();
		
		proveedor.setTipoProveedor(cbTipoProveedor.getSelectedItem().toString());
		proveedor.setTipoPersona(cbTipoPersona.getSelectedItem().toString());
		if(cbIdentificacion.getSelectedItem().equals("NIT")){
			proveedor.setIdentificacion(txtIdentificacion.getText()+lbGuion.getText()+txtIdentificacion_1.getText());
		}else{                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
			proveedor.setIdentificacion(txtIdentificacion.getText());
		}
		proveedor.setRegimen(cbRegimen.getSelectedItem().toString());
		proveedor.setNombre(txtNombre.getText().toString());
		proveedor.setAbreviatura(txtAbreviatura.getText());
		departamento = (Departamento) cbDepartamento.getSelectedItem();
		departamento.setIdDepartamento(departamento.getIdDepartamento());
		municipio = (Municipio) cbMunicipio.getSelectedItem();
		municipio.setIdMunicipio(municipio.getIdMunicipio());
		municipio.setDepartamento(departamento);
		proveedor.setMunicipio(municipio);
		proveedor.setDireccion(txtDireccion.getText());
		proveedor.setTelefono(txtTelefono.getText());
		proveedor.setCelular(txtCelular.getText());
		proveedor.setEmail(txtEmail.getText());
		proveedor.setPaginaWeb(txtWeb.getText());
		proveedor.setObservaciones(txaObservaciones.getText());
		proveedor.setEstado("Activo");
		
		delegadoProveedor.insertarProveedor(proveedor);
		
	}
	
	//Metodo para abrir un dialogo de registro exitoso
	private void abrirDialogoRegistroExistoso() {
		JOptionPane.showMessageDialog(null, "El Proveedor se registro exitosamente");
	}
	
	//Metodo para abrir dialogo de confirmacion de registro
	private void abrirDialogoConfirmacionRegistro(){
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de registrar este Proveedor con: "+cbIdentificacion.getSelectedItem().toString()+". "+txtIdentificacion.getText().toString()+txtIdentificacion_1.getText().toString(), null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarProveedor();
			abrirDialogoRegistroExistoso();
			limpiarDatos();
		}else{
		
		}
	}
	
	//Metodo para abrir ventana de dialogo informando sobre un proveedor que ya se encuentra registrado basado en la informacion ingresada
	private void abrirDialogoProveedorRegistrado(){
		DelegadoProveedor delegadoProveedor = new DelegadoProveedor();
		Proveedor identificacionEncontrada;
		try{
			if(cbIdentificacion.getSelectedItem().equals("NIT")){
				identificacionEncontrada= delegadoProveedor.encontrarPorIdentificacion(txtIdentificacion.getText()+lbGuion.getText()+txtIdentificacion_1.getText());
				if(txtIdentificacion.getText()+lbGuion.getText()+txtIdentificacion_1.getText()==identificacionEncontrada.getIdentificacion()){
					JOptionPane.showMessageDialog(null, "El PROVEEDOR con identificación numero: "+identificacionEncontrada.getIdentificacion()+" ya existe. Por favor ingrese uno nuevo.");
					limpiarDatos();
				}
			}else{                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
				identificacionEncontrada= delegadoProveedor.encontrarPorIdentificacion(txtIdentificacion.getText());
				if(txtIdentificacion.getText().equals(identificacionEncontrada.getIdentificacion())){
					JOptionPane.showMessageDialog(null, "El PROVEEDOR con identificación numero: "+identificacionEncontrada.getIdentificacion()+" ya existe. Por favor ingrese uno nuevo.");
					limpiarDatos();
				}
			}
		}catch(NullPointerException n){
			n.getMessage();
			abrirDialogoConfirmacionRegistro();
		}
	}
	
	//Metodo para cerrar la ventana
	private void cerrar() {
		dispose();
	}
}
