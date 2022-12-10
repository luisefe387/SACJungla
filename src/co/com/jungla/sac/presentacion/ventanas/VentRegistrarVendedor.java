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
import co.com.jungla.sac.persistencia.entidades.Vendedor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import co.com.jungla.sac.negocio.delegados.DelegadoDepartamento;
import co.com.jungla.sac.negocio.delegados.DelegadoMunicipio;
import co.com.jungla.sac.negocio.delegados.DelegadoVendedor;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro del vendedor
 * @author Luis Fernando Pedroza T.
 * @version: 21/09/2016
 */
public class VentRegistrarVendedor extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtIdentificacion;
	private JTextField txtIdentificacion_1;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtProfesion;
	private JTextField txtInformacionPersonal;
	private JTextField txtInformacionComplementaria;
	private JLabel lblEstadoCivil;
	private JLabel lblNroHijos;
	private JLabel lblsinPuntos;
	final JLabel lbGuion;
	final JComboBox cbDepartamento;
	final JComboBox cbMunicipio;
	final JComboBox cbIdentificacion;
	private JComboBox cbSexo;
	private JComboBox cbEstadoCivil;
	private JComboBox cbNroHijos;
	private JSeparator sp;
	private JDateChooser dchFechaNacimiento;
	private DefaultComboBoxModel modeloDepartamento = new DefaultComboBoxModel();
	private DefaultComboBoxModel modeloMunicipio = new DefaultComboBoxModel();
	
	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentRegistrarVendedor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarVendedor.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Nuevo Vendedor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 512, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdentificacion = new JLabel("Identificaci\u00F3n ");
		lblIdentificacion.setBackground(new Color(153, 204, 153));
		lblIdentificacion.setOpaque(true);
		lblIdentificacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIdentificacion.setBounds(10, 52, 107, 22);
		contentPane.add(lblIdentificacion);
		
		txtIdentificacion = new JTextField();
		txtIdentificacion.setBounds(186, 53, 107, 20);
		contentPane.add(txtIdentificacion);
		txtIdentificacion.setColumns(10);
		
		txtIdentificacion_1 = new JTextField();
		txtIdentificacion_1.setBounds(299, 53, 25, 20);
		contentPane.add(txtIdentificacion_1);
		txtIdentificacion_1.setColumns(10);
		
		final JLabel lblNombre = new JLabel("Nombre ");
		lblNombre.setBackground(new Color(153, 204, 153));
		lblNombre.setOpaque(true);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(10, 75, 107, 22);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(119, 76, 375, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n ");
		lblDireccion.setBackground(new Color(153, 204, 153));
		lblDireccion.setOpaque(true);
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDireccion.setBounds(10, 213, 107, 22);
		contentPane.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(119, 214, 353, 20);
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
		btnRegistrar.setBounds(154, 394, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(265, 394, 89, 23);
		contentPane.add(btnCerrar);
		
		lbGuion = new JLabel("-");
		lbGuion.setBounds(294, 55, 9, 14);
		contentPane.add(lbGuion);
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
		
		cbIdentificacion.setModel(new DefaultComboBoxModel(new String[] {"NIT", "CC", "CE", "TI"}));
		cbIdentificacion.setBounds(119, 53, 60, 20);
		contentPane.add(cbIdentificacion);
		
		cbDepartamento = new JComboBox();
		//listar todos los municipios cada vez que se seleccione un departamento
		cbDepartamento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cbMunicipio.removeAllItems();
				listarMuncipios(cbDepartamento.getSelectedItem().toString());
			}
		});
		
		cbDepartamento.setBounds(119, 168, 184, 20);
		contentPane.add(cbDepartamento);
		
		cbMunicipio = new JComboBox();
		cbMunicipio.setBounds(119, 191, 184, 20);
		contentPane.add(cbMunicipio);
		
		JLabel lblDepartamento = new JLabel("Departamento ");
		lblDepartamento.setBackground(new Color(153, 204, 153));
		lblDepartamento.setOpaque(true);
		lblDepartamento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDepartamento.setBounds(10, 167, 107, 22);
		contentPane.add(lblDepartamento);
		
		JLabel lblCiudad = new JLabel("Ciudad ");
		lblCiudad.setBackground(new Color(153, 204, 153));
		lblCiudad.setOpaque(true);
		lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCiudad.setBounds(10, 190, 107, 22);
		contentPane.add(lblCiudad);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono ");
		lblTelefono.setBackground(new Color(153, 204, 153));
		lblTelefono.setOpaque(true);
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelefono.setBounds(10, 121, 107, 22);
		contentPane.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(119, 122, 375, 20);
		txtTelefono.setDocument(new LimitadorCaracteres());
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBackground(new Color(153, 204, 153));
		lblEmail.setOpaque(true);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(10, 277, 107, 22);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(119, 278, 353, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblProfesion = new JLabel("Profesi\u00F3n");
		lblProfesion.setBackground(new Color(153, 204, 153));
		lblProfesion.setOpaque(true);
		lblProfesion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProfesion.setBounds(10, 300, 107, 22);
		contentPane.add(lblProfesion);
		
		txtProfesion = new JTextField();
		txtProfesion.setBounds(119, 301, 353, 20);
		contentPane.add(txtProfesion);
		txtProfesion.setColumns(10);
		
		JLabel lb_guion = new JLabel("-");
		lb_guion.setBounds(275, 464, 12, 14);
		contentPane.add(lb_guion);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBackground(new Color(153, 204, 153));
		lblSexo.setOpaque(true);
		lblSexo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSexo.setBounds(10, 144, 107, 22);
		contentPane.add(lblSexo);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBackground(new Color(153, 204, 153));
		lblFechaNacimiento.setOpaque(true);
		lblFechaNacimiento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaNacimiento.setBounds(10, 98, 107, 22);
		contentPane.add(lblFechaNacimiento);
		
		dchFechaNacimiento = new JDateChooser();
		dchFechaNacimiento.setBounds(119, 99, 134, 20);
		contentPane.add(dchFechaNacimiento);
		
		txtInformacionPersonal = new JTextField();
		txtInformacionPersonal.setForeground(new Color(0, 0, 0));
		txtInformacionPersonal.setHorizontalAlignment(SwingConstants.CENTER);
		txtInformacionPersonal.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtInformacionPersonal.setText("INFORMACION PERSONAL");
		txtInformacionPersonal.setBackground(new Color(0, 153, 51));
		txtInformacionPersonal.setEditable(false);
		txtInformacionPersonal.setBounds(10, 21, 484, 20);
		contentPane.add(txtInformacionPersonal);
		txtInformacionPersonal.setColumns(10);
		
		txtInformacionComplementaria = new JTextField();
		txtInformacionComplementaria.setText("INFORMACION COMPLEMENTARIA");
		txtInformacionComplementaria.setHorizontalAlignment(SwingConstants.CENTER);
		txtInformacionComplementaria.setForeground(Color.BLACK);
		txtInformacionComplementaria.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtInformacionComplementaria.setEditable(false);
		txtInformacionComplementaria.setColumns(10);
		txtInformacionComplementaria.setBackground(new Color(0, 153, 51));
		txtInformacionComplementaria.setBounds(10, 246, 484, 20);
		contentPane.add(txtInformacionComplementaria);
		
		cbSexo = new JComboBox();
		cbSexo.setModel(new DefaultComboBoxModel(new String[] {"--ELIJA OPCION--", "FEMENINO", "MASCULINO"}));
		cbSexo.setBounds(119, 144, 149, 22);
		contentPane.add(cbSexo);
		
		lblEstadoCivil = new JLabel("Estado Civil");
		lblEstadoCivil.setOpaque(true);
		lblEstadoCivil.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstadoCivil.setBackground(new Color(153, 204, 153));
		lblEstadoCivil.setBounds(10, 323, 107, 22);
		contentPane.add(lblEstadoCivil);
		
		lblNroHijos = new JLabel("Nro Hijos");
		lblNroHijos.setOpaque(true);
		lblNroHijos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNroHijos.setBackground(new Color(153, 204, 153));
		lblNroHijos.setBounds(10, 346, 107, 22);
		contentPane.add(lblNroHijos);
		
		cbNroHijos = new JComboBox();
		cbNroHijos.setModel(new DefaultComboBoxModel(new String[] {"--ELIJA OPCION--", "1", "2", "3", "4", "5", "M\u00C1S"}));
		cbNroHijos.setBounds(119, 346, 134, 22);
		contentPane.add(cbNroHijos);
		
		sp = new JSeparator();
		sp.setBounds(10, 381, 484, 2);
		contentPane.add(sp);
		
		cbEstadoCivil = new JComboBox();
		cbEstadoCivil.setModel(new DefaultComboBoxModel(new String[] {"--ELIJA OPCION--", "SOLTERO", "CASADO", "DIVORCIADO", "VIUDO", "UNION LIBRE"}));
		cbEstadoCivil.setBounds(119, 323, 134, 22);
		contentPane.add(cbEstadoCivil);
		
		lblsinPuntos = new JLabel("(Sin Puntos)");
		lblsinPuntos.setBounds(334, 56, 114, 14);
		contentPane.add(lblsinPuntos);
		
		//Metodo que debe ejecutar la ventana al inicializarse
		listarDepartamentos();

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
	
	//Metodo que permite la validacion los datos ingresados para el registro del vendedor
	private void validarDatos() {
		
		if((cbIdentificacion.getSelectedItem().equals("NIT") && (txtIdentificacion.getText().isEmpty()|| txtIdentificacion_1.getText().isEmpty()))||(cbIdentificacion.getSelectedItem()!="NIT" && txtIdentificacion.getText().isEmpty())||(txtNombre.getText().isEmpty())||(cbDepartamento.getSelectedItem().equals("--ELIJA DEPARTAMENTO--"))||(cbMunicipio.getSelectedItem().equals("")||cbMunicipio.getSelectedItem().equals("--ELIJA CIUDAD--")) || (txtDireccion.getText().equals("")||txtTelefono.getText().equals(""))||cbSexo.getSelectedItem().equals("--ELIJA OPCION--")||dchFechaNacimiento.getCalendar()==null){
			JOptionPane.showMessageDialog(null, "Debe llenar toda la Información Personal");
		}else{
			abrirDialogoVendedorRegistrado();
		}
		
	}
	//Metodo para limpiar los datos escritos
	private void limpiarDatos() {
		cbSexo.setSelectedIndex(0);
		cbIdentificacion.setSelectedIndex(0);
		txtIdentificacion.setText("");
		txtIdentificacion_1.setText("");
		txtNombre.setText("");
		cbEstadoCivil.setSelectedIndex(0);
		cbNroHijos.setSelectedIndex(0);
		cbDepartamento.setSelectedIndex(0);
		txtDireccion.setText("");
		txtTelefono.setText("");
		txtEmail.setText("");
		txtProfesion.setText("");
		dchFechaNacimiento.setCalendar(null);
	}
	
	//Metodo que permite el registro del vendedor a la base de datos luego de haberse validado
	private void registrarVendedor() {
		
		Vendedor vendedor = new Vendedor();
		DelegadoVendedor delegadoVendedor = new DelegadoVendedor();
		Date fechaActual = new Date();
		Date fechaDeterminada = dchFechaNacimiento.getDate();
		Departamento departamento = new Departamento();
		Municipio municipio = new Municipio();
		
		if(fechaActual.compareTo(fechaDeterminada)>=0){
			if(cbIdentificacion.getSelectedItem().equals("NIT")){
				vendedor.setIdentificacion(txtIdentificacion.getText()+lbGuion.getText()+txtIdentificacion_1.getText());
			}else{                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
				vendedor.setIdentificacion(txtIdentificacion.getText());
			}
			vendedor.setNombre(txtNombre.getText().toString());
			departamento = (Departamento) cbDepartamento.getSelectedItem();
			departamento.setIdDepartamento(departamento.getIdDepartamento());
			municipio = (Municipio) cbMunicipio.getSelectedItem();
			municipio.setIdMunicipio(municipio.getIdMunicipio());
			municipio.setDepartamento(departamento);
			vendedor.setMunicipio(municipio);
			vendedor.setDireccion(txtDireccion.getText());
			vendedor.setTelefono(txtTelefono.getText());
			vendedor.setEmail(txtEmail.getText());
			vendedor.setSexo(cbSexo.getSelectedItem().toString());
			vendedor.setFechaNacimiento(dchFechaNacimiento.getDate());
			vendedor.setEstado("Activo");
			vendedor.setEstadoCivil(cbEstadoCivil.getSelectedItem().toString());
			vendedor.setHijos(Byte.parseByte(cbNroHijos.getSelectedItem().toString()));
			vendedor.setProfesion(txtProfesion.getText());
			
			delegadoVendedor.insertarVendedor(vendedor);
			JOptionPane.showMessageDialog(null, "El Vendedor se registro exitosamente");

		}else{
			JOptionPane.showMessageDialog(null, "La fecha determinada es mayor que la actual");
			dchFechaNacimiento.setCalendar(null);
		}
	}
	
	//Metodo para abrir ventanta de confirmacion de registro
	private void abrirDialogoConfirmacionRegistro(){
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de registrar este Vendedor con: "+cbIdentificacion.getSelectedItem().toString()+". "+txtIdentificacion.getText().toString()+txtIdentificacion_1.getText().toString(), null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarVendedor();
			limpiarDatos();
		}else{
		
		}
	}
	
	//Metodo para abrir ventana de dialogo informando sobre un vendedor que ya se encuentra registrado basado en la informacion ingresada
	private void abrirDialogoVendedorRegistrado(){
		DelegadoVendedor delegadoVendedor = new DelegadoVendedor();
		Vendedor identificacionEncontrada;
		try{
			if(cbIdentificacion.getSelectedItem().equals("NIT")){
				identificacionEncontrada= delegadoVendedor.encontrarPorVendedor(txtIdentificacion.getText()+lbGuion.getText()+txtIdentificacion_1.getText());
				if(txtIdentificacion.getText()+lbGuion.getText()+txtIdentificacion_1.getText()==identificacionEncontrada.getIdentificacion()){
					JOptionPane.showMessageDialog(null, "El VENDEDOR con identificación numero: "+identificacionEncontrada.getIdentificacion()+" ya existe. Por favor ingrese uno nuevo.");
					limpiarDatos();
				}
			}else{                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
				identificacionEncontrada= delegadoVendedor.encontrarPorVendedor(txtIdentificacion.getText());
				if(txtIdentificacion.getText().equals(identificacionEncontrada.getIdentificacion())){
					JOptionPane.showMessageDialog(null, "El VENDEDOR con identificación numero: "+identificacionEncontrada.getIdentificacion()+" ya existe. Por favor ingrese uno nuevo.");
					limpiarDatos();
				}
			}
		}catch(NullPointerException n){
			n.getMessage();
			abrirDialogoConfirmacionRegistro();
		}
	}
}
