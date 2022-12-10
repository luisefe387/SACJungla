package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import co.com.jungla.sac.persistencia.entidades.Cliente;
import co.com.jungla.sac.persistencia.entidades.Departamento;
import co.com.jungla.sac.persistencia.entidades.Municipio;
import co.com.jungla.sac.persistencia.entidades.TipoCliente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;

import javax.swing.JTextPane;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import co.com.jungla.sac.negocio.delegados.DelegadoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoDepartamento;
import co.com.jungla.sac.negocio.delegados.DelegadoMunicipio;
import co.com.jungla.sac.negocio.delegados.DelegadoTipoCliente;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro del cliente
 * @author Luis Fernando Pedroza T.
 * @version: 21/09/2016
 */
public class VentRegistrarCliente extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtIdentificacion;
	private JTextField txtIdentificacion_1;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtWeb;
	private JTextField txtNomComercial;
	private JTextField txtContacto;
	private JTextPane txaObservaciones;
	final JComboBox cbDepartamento;
	final JComboBox cbMunicipio;
	final JComboBox cbIdentificacion;
	final JComboBox cbTipoPersona;
	final JComboBox cbTipoCliente;
	final JLabel lbGuion;
	private JLabel lblSinpuntos;
	private JDateChooser dchFechaNacimiento;
	private DefaultComboBoxModel modeloDepartamento = new DefaultComboBoxModel();
	private DefaultComboBoxModel modeloMunicipio = new DefaultComboBoxModel();
	private DefaultComboBoxModel modeloTipoCliente = new DefaultComboBoxModel();
	
	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentRegistrarCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarCliente.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Nuevo Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 512, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdentificacion = new JLabel("Identificaci\u00F3n *");
		lblIdentificacion.setBackground(new Color(153, 204, 153));
		lblIdentificacion.setOpaque(true);
		lblIdentificacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIdentificacion.setBounds(10, 44, 107, 22);
		contentPane.add(lblIdentificacion);
		
		txtIdentificacion = new JTextField();
		txtIdentificacion.setBounds(186, 45, 107, 20);
		contentPane.add(txtIdentificacion);
		txtIdentificacion.setColumns(10);
		
		txtIdentificacion_1 = new JTextField();
		txtIdentificacion_1.setBounds(299, 45, 25, 20);
		contentPane.add(txtIdentificacion_1);
		txtIdentificacion_1.setColumns(10);
		
		final JLabel lblNombre = new JLabel("Nombre *");
		lblNombre.setBackground(new Color(153, 204, 153));
		lblNombre.setOpaque(true);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(10, 67, 107, 22);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(119, 68, 375, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n *");
		lblDireccion.setBackground(new Color(153, 204, 153));
		lblDireccion.setOpaque(true);
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDireccion.setBounds(10, 228, 107, 22);
		contentPane.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(119, 229, 353, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		//Boton para registrar el cliente
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setForeground(new Color(0, 51, 0));
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
			});
		btnRegistrar.setBounds(148, 430, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(259, 430, 89, 23);
		contentPane.add(btnCerrar);
		
		lbGuion = new JLabel("-");
		lbGuion.setBounds(294, 47, 9, 14);
		contentPane.add(lbGuion);
		
		JLabel lblTipoPersona = new JLabel("Tipo *");
		lblTipoPersona.setBackground(new Color(153, 204, 153));
		lblTipoPersona.setOpaque(true);
		lblTipoPersona.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipoPersona.setBounds(10, 21, 107, 22);
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
		cbTipoPersona.setModel(new DefaultComboBoxModel(new String[] {"PERSONA NATURAL", "PERSONA JURIDICA"}));
		cbTipoPersona.setBounds(119, 22, 184, 20);
		contentPane.add(cbTipoPersona);
		
		cbIdentificacion.setModel(new DefaultComboBoxModel(new String[] {"NIT", "CC", "CE", "TI"}));
		cbIdentificacion.setBounds(119, 45, 60, 20);
		contentPane.add(cbIdentificacion);
		
		//Evento en la que al elegir un tipo de persona despliegue en otro combo box los tipos de identificacion 
		cbTipoPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbTipoPersona.getSelectedItem().equals("PERSONA NATURAL")){
					cbIdentificacion.setModel(new DefaultComboBoxModel(new String[] {"NIT", "CC", "CE", "TI"}));
					cbIdentificacion.setBounds(119, 45, 60, 20);
					contentPane.add(cbIdentificacion);
					lblNombre.setText("Nombre");
				}else{
					cbIdentificacion.setModel(new DefaultComboBoxModel(new String[] {"NIT"}));
					cbIdentificacion.setBounds(119, 45, 60, 20);
					contentPane.add(cbIdentificacion);
					lblNombre.setText("Razón Social");
					
				}
			}
		});
		
		JLabel lblNomComercial = new JLabel("Nombre Comercial");
		lblNomComercial.setBackground(new Color(153, 204, 153));
		lblNomComercial.setOpaque(true);
		lblNomComercial.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNomComercial.setBounds(10, 90, 107, 22);
		contentPane.add(lblNomComercial);
		
		cbDepartamento = new JComboBox();
		//listar todos los municipios cada vez que se seleccione un departamento
		cbDepartamento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cbMunicipio.removeAllItems();
				listarMuncipios(cbDepartamento.getSelectedItem().toString());
			}
		});
		
		cbDepartamento.setBounds(119, 183, 184, 20);
		contentPane.add(cbDepartamento);
		
		cbMunicipio = new JComboBox();
		cbMunicipio.setBounds(119, 206, 184, 20);
		contentPane.add(cbMunicipio);
		
		JLabel lblDepartamento = new JLabel("Departamento *");
		lblDepartamento.setBackground(new Color(153, 204, 153));
		lblDepartamento.setOpaque(true);
		lblDepartamento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDepartamento.setBounds(10, 182, 107, 22);
		contentPane.add(lblDepartamento);
		
		JLabel lblCiudad = new JLabel("Ciudad *");
		lblCiudad.setBackground(new Color(153, 204, 153));
		lblCiudad.setOpaque(true);
		lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCiudad.setBounds(10, 205, 107, 22);
		contentPane.add(lblCiudad);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono *");
		lblTelefono.setBackground(new Color(153, 204, 153));
		lblTelefono.setOpaque(true);
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelefono.setBounds(10, 113, 107, 22);
		contentPane.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(119, 114, 375, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBackground(new Color(153, 204, 153));
		lblEmail.setOpaque(true);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(10, 251, 107, 22);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(119, 252, 353, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblWeb = new JLabel("Web");
		lblWeb.setBackground(new Color(153, 204, 153));
		lblWeb.setOpaque(true);
		lblWeb.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWeb.setBounds(10, 274, 107, 22);
		contentPane.add(lblWeb);
		
		txtWeb = new JTextField();
		txtWeb.setBounds(119, 275, 353, 20);
		contentPane.add(txtWeb);
		txtWeb.setColumns(10);
		
		JLabel lb_guion = new JLabel("-");
		lb_guion.setBounds(275, 464, 12, 14);
		contentPane.add(lb_guion);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBackground(new Color(153, 204, 153));
		lblObservaciones.setOpaque(true);
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaciones.setBounds(10, 320, 107, 91);
		contentPane.add(lblObservaciones);
		
		txaObservaciones = new JTextPane();
		txaObservaciones.setBounds(119, 321, 363, 89);
		contentPane.add(txaObservaciones);
		
		txtNomComercial = new JTextField();
		txtNomComercial.setBounds(119, 91, 375, 20);
		contentPane.add(txtNomComercial);
		txtNomComercial.setColumns(10);
		
		JLabel lblContacto = new JLabel("Contacto");
		lblContacto.setBackground(new Color(153, 204, 153));
		lblContacto.setOpaque(true);
		lblContacto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblContacto.setBounds(10, 136, 107, 22);
		contentPane.add(lblContacto);
		
		txtContacto = new JTextField();
		txtContacto.setBounds(119, 137, 375, 20);
		contentPane.add(txtContacto);
		txtContacto.setColumns(10);
		
		JLabel lblTipoCliente = new JLabel("Tipo de Cliente");
		lblTipoCliente.setBackground(new Color(153, 204, 153));
		lblTipoCliente.setOpaque(true);
		lblTipoCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipoCliente.setBounds(10, 159, 107, 22);
		contentPane.add(lblTipoCliente);
		
		cbTipoCliente = new JComboBox();
		//Evento para Crear tipo de cliente cuando este no existe en el combo box
		cbTipoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearTipoCliente();
			}
		});
		cbTipoCliente.setBounds(119, 160, 306, 20);
		contentPane.add(cbTipoCliente);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBackground(new Color(153, 204, 153));
		lblFechaNacimiento.setOpaque(true);
		lblFechaNacimiento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaNacimiento.setBounds(10, 297, 107, 22);
		contentPane.add(lblFechaNacimiento);
		
		dchFechaNacimiento = new JDateChooser();
		dchFechaNacimiento.setBounds(119, 298, 134, 20);
		contentPane.add(dchFechaNacimiento);
		
		lblSinpuntos = new JLabel("(Sin Puntos)");
		lblSinpuntos.setBounds(334, 48, 114, 14);
		contentPane.add(lblSinpuntos);
	
		//Metodos que debe ejecutar la ventana al inicializarse
		listarDepartamentos();
		listarTipoCliente();
	}
	
	//Metodo para listar todas la lista de tipos de cliente
	private void listarTipoCliente() {
		DelegadoTipoCliente delegadoTipoCliente = new DelegadoTipoCliente();
		List<TipoCliente> tipoClientes = delegadoTipoCliente.listarTipoCliente();
		modeloTipoCliente.addElement("--ELIJA TIPO DE CLIENTE--");
		cbTipoCliente.setModel(modeloTipoCliente);
		
		for(TipoCliente tipoCliente : tipoClientes){
			modeloTipoCliente.addElement(new TipoCliente (tipoCliente.getDescripcion(), tipoCliente.getIdTipoCliente()));
			cbTipoCliente.setModel(modeloTipoCliente);
		}
		modeloTipoCliente.addElement("--NUEVO TIPO DE CLIENTE--");
		cbTipoCliente.setModel(modeloTipoCliente);
	}
	//Metodo para listar todos los departamentos de Colombia
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
		
		modeloMunicipio.removeAllElements();
		DelegadoMunicipio delegadoMunicipio = new DelegadoMunicipio();
		List<Municipio> municipios = delegadoMunicipio.listarMunicipiosPorDepartamento(nombre);
		modeloMunicipio.addElement("--ELIJA CIUDAD--");
		
		try{
			for(int i=0;i<= municipios.size();i++){
				
				modeloMunicipio.addElement(municipios.get(i));
				cbMunicipio.setModel(modeloMunicipio);
			}
		}catch(Exception e){
			e.getMessage();
		}
	}
	//Metodo para crear los tipos de cliente luego de ser elegido dentro del combo box
	private void crearTipoCliente() {
		if(cbTipoCliente.getSelectedItem().equals("--NUEVO TIPO DE CLIENTE--")){
			VentRegistrarTipoCliente ventRegistrarTipoCliente = new VentRegistrarTipoCliente();
			cbTipoCliente.removeAllItems();
			ventRegistrarTipoCliente.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                	listarTipoCliente(); 
                }
            });
			ventRegistrarTipoCliente.setVisible(true);
		}
		
	}
	//Metodo que permite la validacion los datos ingresados para el registro del cliente
	private void validarDatos() {
		
		if((cbIdentificacion.getSelectedItem().equals("NIT") && (txtIdentificacion.getText().isEmpty()|| txtIdentificacion_1.getText().isEmpty()))||(cbIdentificacion.getSelectedItem()!="NIT" && txtIdentificacion.getText().isEmpty())||(txtNombre.getText().isEmpty())||(cbDepartamento.getSelectedItem().equals("--ELIJA DEPARTAMENTO--"))||(cbMunicipio.getSelectedItem().equals("")||cbMunicipio.getSelectedItem().equals("--ELIJA CIUDAD--")) || (txtDireccion.getText().equals("")||txtTelefono.getText().equals(""))){
			JOptionPane.showMessageDialog(null, "Debe llenar todos los campos obligatorios (*)");
		}else{
			abrirDialogoClienteRegistrado();
		}
		
	}
	//Metodo para limpiar los datos escritos
	private void limpiarDatos() {
		cbTipoPersona.setSelectedIndex(0);
		cbIdentificacion.setSelectedIndex(0);
		txtIdentificacion.setText("");
		txtIdentificacion_1.setText("");
		txtNombre.setText("");
		txtNomComercial.setText("");
		txtContacto.setText("");
		cbTipoCliente.setSelectedIndex(0);
		cbDepartamento.setSelectedIndex(0);
		txtDireccion.setText("");
		txtTelefono.setText("");
		txtEmail.setText("");
		txtWeb.setText("");
		dchFechaNacimiento.setCalendar(null);
		txaObservaciones.setText("");
	}
	//Metodo que permite el ingreso del cliente a la base de datos luego de haberse validado
	private void registrarCliente() {
		
		Cliente cliente = new Cliente();
		TipoCliente tipoCliente = new TipoCliente();
		DelegadoCliente delegadoCliente = new DelegadoCliente();
		Departamento departamento = new Departamento();
		Municipio municipio = new Municipio();
		Date fechaActual = new Date();
		Date fechaDeterminada = dchFechaNacimiento.getDate();
		
		if(fechaActual.compareTo(fechaDeterminada)>=0){
			cliente.setTipoPersona(cbTipoPersona.getSelectedItem().toString());
			if(cbIdentificacion.getSelectedItem().equals("NIT")){
				cliente.setIdentificacion(txtIdentificacion.getText()+lbGuion.getText()+txtIdentificacion_1.getText());
			}else{
				cliente.setIdentificacion(txtIdentificacion.getText());
			}
			cliente.setNombre(txtNombre.getText());
			cliente.setNombreComercial(txtNomComercial.getText());
			cliente.setTelefono(txtTelefono.getText());
			cliente.setContacto(txtContacto.getText());
			tipoCliente = (TipoCliente)cbTipoCliente.getSelectedItem();
			tipoCliente.setIdTipoCliente(tipoCliente.getIdTipoCliente());
			cliente.setTipoClientes(tipoCliente);
			departamento = (Departamento) cbDepartamento.getSelectedItem();
			departamento.setIdDepartamento(departamento.getIdDepartamento());
			municipio = (Municipio) cbMunicipio.getSelectedItem();
			municipio.setIdMunicipio(municipio.getIdMunicipio());
			municipio.setDepartamento(departamento);
			cliente.setMunicipio(municipio);
			cliente.setDireccion(txtDireccion.getText());
			cliente.setEmail(txtEmail.getText());
			cliente.setPaginaWeb(txtWeb.getText());
			cliente.setFechaNacimiento(fechaDeterminada);
			cliente.setObservaciones(txaObservaciones.getText());
			cliente.setEstado("Activo");
			
			delegadoCliente.insertarCliente(cliente);
			JOptionPane.showMessageDialog(null, "El Cliente se registro exitosamente");
			
		}else{
			JOptionPane.showMessageDialog(null, "La fecha determinada es mayor que la actual");
			dchFechaNacimiento.setCalendar(null);
		}
	}
	
	//Metodo para abrir ventanta de confirmacion de registro
	private void abrirDialogoConfirmacionRegistro(){
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de registrar este Cliente con: "+cbIdentificacion.getSelectedItem().toString()+". "+txtIdentificacion.getText().toString()+txtIdentificacion_1.getText().toString(), null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarCliente();
			limpiarDatos();
		}else{
		
		}
	}
	
	//Metodo para abrir ventana de dialogo informando sobre un cliente que ya se encuentra registrado basado en la informacion ingresada
	private void abrirDialogoClienteRegistrado(){
		DelegadoCliente delegadoCliente = new DelegadoCliente();
		Cliente identificacionEncontrada;
		try{
			if(cbIdentificacion.getSelectedItem().equals("NIT")){
				identificacionEncontrada= delegadoCliente.encontarPorCliente(txtIdentificacion.getText()+lbGuion.getText()+txtIdentificacion_1.getText());
				if(txtIdentificacion.getText()+lbGuion.getText()+txtIdentificacion_1.getText()==identificacionEncontrada.getIdentificacion()){
					JOptionPane.showMessageDialog(null, "El CLIENTE con identificación numero: "+identificacionEncontrada.getIdentificacion()+" ya existe. Por favor ingrese uno nuevo.");
					limpiarDatos();
				}
			}else{                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
				identificacionEncontrada= delegadoCliente.encontarPorCliente(txtIdentificacion.getText());
				if(txtIdentificacion.getText().equals(identificacionEncontrada.getIdentificacion())){
					JOptionPane.showMessageDialog(null, "El CLIENTE con identificación numero: "+identificacionEncontrada.getIdentificacion()+" ya existe. Por favor ingrese uno nuevo.");
					limpiarDatos();
				}
			}
		}catch(NullPointerException n){
			n.getMessage();
			abrirDialogoConfirmacionRegistro();
		}
	}
}
