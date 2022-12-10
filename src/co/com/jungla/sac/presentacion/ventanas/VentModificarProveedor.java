package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import co.com.jungla.sac.persistencia.entidades.Departamento;
import co.com.jungla.sac.persistencia.entidades.Municipio;
import co.com.jungla.sac.persistencia.entidades.Persona;
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
import javax.swing.JSeparator;

import co.com.jungla.sac.negocio.delegados.DelegadoDepartamento;
import co.com.jungla.sac.negocio.delegados.DelegadoMunicipio;
import co.com.jungla.sac.negocio.delegados.DelegadoPersona;
import co.com.jungla.sac.negocio.delegados.DelegadoProveedor;

public class VentModificarProveedor extends JDialog {

	private JPanel contentPane;
	private JTextField txtIdentificacion;
	private JTextField txtNombre;
	private JTextField txtAbreviatura;
	private JTextField txtDireccion;
	private JTextPane txaObservaciones;
	final JComboBox cbTipoProveedor;
	final JComboBox cbDepartamento;
	final JComboBox cbMunicipio;
	final JComboBox cbRegimen;
	final JComboBox cbTipoPersona;
	DefaultComboBoxModel modeloDepartamento = new DefaultComboBoxModel();
	DefaultComboBoxModel modeloMunicipio = new DefaultComboBoxModel();
	DefaultComboBoxModel modeloFormaPago = new DefaultComboBoxModel();
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtWeb;
	private JComboBox cbEstado;
	private Proveedor proveedorElegido;
	private JTextField txtCelular;
	
	private int idMunicipio;
	private int idDepartamento;

	/**
	 * Create the frame.
	 */
	public VentModificarProveedor() {
		setTitle("Modificar Proveedor");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(80, 80, 512, 547);
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
		txtIdentificacion.setEditable(false);
		txtIdentificacion.setBounds(119, 68, 138, 20);
		contentPane.add(txtIdentificacion);
		txtIdentificacion.setColumns(10);
		
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
		
		//Boton registrar proveedor
		JButton btnRegistrar = new JButton("Actualizar");
		btnRegistrar.setForeground(new Color(0, 51, 0));
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
			});
		btnRegistrar.setBounds(135, 482, 110, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrar();
			}
		});
		btnCerrar.setBounds(267, 482, 89, 23);
		contentPane.add(btnCerrar);
		
		JLabel lblTipoPersona = new JLabel("Tipo Persona*");
		lblTipoPersona.setOpaque(true);
		lblTipoPersona.setOpaque(true);
		lblTipoPersona.setBackground(new Color(153, 204, 153));
		lblTipoPersona.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipoPersona.setBounds(10, 44, 107, 22);
		contentPane.add(lblTipoPersona);
		
		cbTipoPersona = new JComboBox();
		cbRegimen = new JComboBox();
		cbTipoPersona.setModel(new DefaultComboBoxModel(new String[] {"PERSONA NATURAL", "PERSONA JURIDICA"}));
		cbTipoPersona.setBounds(119, 45, 184, 20);
		contentPane.add(cbTipoPersona);
		
		cbRegimen.setModel(new DefaultComboBoxModel(new String[] {"R\u00C9GIMEN SIMPLICADO", "R\u00C9GIMEN COM\u00DAN", "GRAN CONTRIBUYENTE", "NINGUNO"}));
		cbRegimen.setBounds(119, 91, 184, 20);
		contentPane.add(cbRegimen);
		
		//Evento en la que al elegir un tipo de persona despliegue en otro combo box los tipos de identificacion
		cbTipoPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbTipoPersona.getSelectedItem().equals("PERSONA NATURAL")){
					cbRegimen.setModel(new DefaultComboBoxModel(new String[] {"R\u00E9gimen Simplicado", "R\u00E9gimen Com\u00FAn", "Gran Contribuyente", "Ninguno"}));
					cbRegimen.setBounds(119, 91, 184, 20);
					contentPane.add(cbRegimen);
					lblNombre.setText("Nombre");
				}else{
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
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 51, 0));
		separator.setBounds(10, 468, 476, 3);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 51, 0));
		separator_1.setBounds(10, 422, 476, 2);
		contentPane.add(separator_1);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setOpaque(true);
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstado.setBackground(new Color(153, 204, 153));
		lblEstado.setBounds(10, 435, 117, 22);
		contentPane.add(lblEstado);
		
		cbEstado = new JComboBox();
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"Activo", "Inactivo"}));
		cbEstado.setBounds(129, 436, 135, 20);
		contentPane.add(cbEstado);
		
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
		
		//Lista todos los departamentos 
		listarDepartamentos();
		
		setModal(true);
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

	//Metodo que permite la validacion los datos ingresados para la actualizacion del proveedor
	private void validarDatos() {
		
		if(txtNombre.getText().isEmpty()||cbDepartamento.getSelectedItem().equals("--ELIJA DEPARTAMENTO--")||cbMunicipio.getSelectedItem().equals("")||cbMunicipio.getSelectedItem().equals("--ELIJA CIUDAD--") || txtDireccion.getText().equals("")||txtTelefono.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Debe llenar todos los campos obligatorios (*)");
		}else{
			abrirDialogoActualizacion();
		}
		
	}

	//Metodo que permite la modificacion del proveedor a la base de datos luego de haberse validado
	private void modificarProveedor() {
		DelegadoProveedor delegadoProveedor = new DelegadoProveedor();
		DelegadoPersona delegadoPersona = new DelegadoPersona();
		
		Persona personaAModificar = proveedorElegido;
		Proveedor proveedorAModificar = proveedorElegido;
		Departamento departamento = new Departamento();
		Municipio municipio = new Municipio();
		
		proveedorAModificar.setTipoProveedor(cbTipoProveedor.getSelectedItem().toString());
		personaAModificar.setTipoPersona(cbTipoPersona.getSelectedItem().toString());
		proveedorAModificar.setRegimen(cbRegimen.getSelectedItem().toString());
		personaAModificar.setNombre(txtNombre.getText().toString());
		proveedorAModificar.setAbreviatura(txtAbreviatura.getText());
		
		try{
			departamento = (Departamento) cbDepartamento.getSelectedItem();
			departamento.setIdDepartamento(departamento.getIdDepartamento());
		}catch(Exception e){
			departamento.setIdDepartamento(idDepartamento);
			e.getMessage();
		}
		
		try{
			municipio = (Municipio) cbMunicipio.getSelectedItem();
			municipio.setIdMunicipio(municipio.getIdMunicipio());
		}catch(Exception e){
			municipio.setIdMunicipio(idMunicipio);
			e.getMessage();
		}
		
		municipio.setDepartamento(departamento);
		personaAModificar.setMunicipio(municipio);
		personaAModificar.setDireccion(txtDireccion.getText());
		personaAModificar.setTelefono(txtTelefono.getText());
		personaAModificar.setCelular(txtCelular.getText());
		personaAModificar.setEmail(txtEmail.getText());
		personaAModificar.setPaginaWeb(txtWeb.getText());
		personaAModificar.setObservaciones(txaObservaciones.getText());
		proveedorAModificar.setEstado(cbEstado.getSelectedItem().toString());
		
		delegadoProveedor.actualizarProveedor(proveedorAModificar);
		delegadoPersona.actualizarPersona(personaAModificar);
		
		JOptionPane.showMessageDialog(null, "El Proveedor se modifico exitosamente");
		
	}
	
	//Metodo para abrir ventanta de actualizacion
	private void abrirDialogoActualizacion(){
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de modificar este Proveedor", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			modificarProveedor();
			dispose();
		}else{
		
		}
	}
	
	private void cerrar() {
		dispose();
	}
	
	//Metodo para agregar los datos de los proveedores
	public void agregarDatos(Proveedor proveedorElegido){
		cbTipoProveedor.getModel().setSelectedItem(proveedorElegido.getTipoProveedor());
		cbTipoPersona.getModel().setSelectedItem(proveedorElegido.getTipoPersona());
		txtIdentificacion.setText(proveedorElegido.getIdentificacion().toString());
		cbRegimen.getModel().setSelectedItem(proveedorElegido.getRegimen());
		txtNombre.setText(proveedorElegido.getNombre());
		txtAbreviatura.setText(proveedorElegido.getAbreviatura());
		cbDepartamento.getModel().setSelectedItem(proveedorElegido.getMunicipio().getDepartamento().getNombre());
		idDepartamento = proveedorElegido.getMunicipio().getDepartamento().getIdDepartamento();
		cbMunicipio.getModel().setSelectedItem(proveedorElegido.getMunicipio().getNombre());
		idMunicipio = proveedorElegido.getMunicipio().getIdMunicipio();
		txtDireccion.setText(proveedorElegido.getDireccion());
		txtTelefono.setText(proveedorElegido.getTelefono());
		txtCelular.setText(proveedorElegido.getCelular());
		txtEmail.setText(proveedorElegido.getEmail());
		txtWeb.setText(proveedorElegido.getPaginaWeb());
		txaObservaciones.setText(proveedorElegido.getObservaciones());
		cbEstado.getModel().setSelectedItem(proveedorElegido.getEstado());
		
		this.proveedorElegido = proveedorElegido;
	}
}
