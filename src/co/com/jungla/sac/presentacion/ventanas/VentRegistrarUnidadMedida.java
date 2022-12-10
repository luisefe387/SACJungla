package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import co.com.jungla.sac.persistencia.entidades.UnidadMedida;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;

import co.com.jungla.sac.negocio.delegados.DelegadoUnidadMedida;

import java.awt.Color;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro de la unidad de medida
 * @author Luis Fernando Pedroza T.
 * @version: 21/09/2016
 */
public class VentRegistrarUnidadMedida extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtAbreviatura;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentRegistrarUnidadMedida() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarUnidadMedida.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Nueva Unidad de Empaque");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 218);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingrese la nueva UNIDAD de EMPAQUE.");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(45, 31, 233, 14);
		contentPane.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(119, 84, 194, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		//Evento del boton ingresar unidad de medida
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setForeground(new Color(0, 51, 0));
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
		});
		btnIngresar.setBounds(70, 149, 89, 23);
		contentPane.add(btnIngresar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(169, 149, 89, 23);
		contentPane.add(btnCerrar);
		
		JLabel lblYSuAbreviatura = new JLabel("y su Abreviatura correspondiente");
		lblYSuAbreviatura.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblYSuAbreviatura.setBounds(61, 47, 210, 14);
		contentPane.add(lblYSuAbreviatura);
		
		JLabel lblUnidad = new JLabel("Unidad");
		lblUnidad.setBackground(new Color(153, 204, 153));
		lblUnidad.setOpaque(true);
		lblUnidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUnidad.setBounds(22, 83, 94, 22);
		contentPane.add(lblUnidad);
		
		JLabel lblAbreviatura = new JLabel("Abreviatura");
		lblAbreviatura.setBackground(new Color(153, 204, 153));
		lblAbreviatura.setOpaque(true);
		lblAbreviatura.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAbreviatura.setBounds(22, 106, 94, 22);
		contentPane.add(lblAbreviatura);
		
		txtAbreviatura = new JTextField();
		txtAbreviatura.setBounds(119, 107, 89, 20);
		contentPane.add(txtAbreviatura);
		txtAbreviatura.setColumns(10);
		
	}
	//Metodo que valida los datos ingresados para su posterior registro 
	private void validarDatos() {
		if(txtNombre.getText().isEmpty()|| txtAbreviatura.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Debe ingresar el nombre de la unidad de medida");
		}else{
			abrirDialogoUnidadMedidaRegistrada();
		}
	}
	//Metodo que permite el ingreso de la unidad de medida a la base de datos luego de haberse validado
	private void registrarUnidadMedida() {
		UnidadMedida unidadMedida = new UnidadMedida();
		unidadMedida.setNombre(txtNombre.getText());
		unidadMedida.setAbreviatura(txtAbreviatura.getText());
		
		DelegadoUnidadMedida delegadoUnidadMedida = new DelegadoUnidadMedida();
		delegadoUnidadMedida.insertarUnidadMedida(unidadMedida);
		
		JOptionPane.showMessageDialog(null, "Se registro exitosamente");
	}
	
	//Limpiar los datos escritos
	private void limpiarDatos() {
		txtNombre.setText("");
		txtAbreviatura.setText("");
	}
	
	//Metodo para abrir ventanta de confirmacion de registro
	private void abrirDialogoConfirmacionRegistro(){
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de registrar esta unidad de medida: "+txtNombre.getText().toString()+" - "+txtAbreviatura.getText().toString(), null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarUnidadMedida();
			limpiarDatos();
		}else{
		
		}
	}
	
	//Metodo para abrir ventana informando sobre una unidad de medida ya registradoa con el mismo nombre
	private void abrirDialogoUnidadMedidaRegistrada(){
		DelegadoUnidadMedida delegadoUnidadMedida = new DelegadoUnidadMedida();
		UnidadMedida unidadEncontrado;
		try{
			unidadEncontrado= delegadoUnidadMedida.traerUnidadMedida(txtNombre.getText().toString());
			if(txtNombre.getText().toString().equals(unidadEncontrado.getNombre())){
				JOptionPane.showMessageDialog(null, "La UNIDAD DE MEDIDA: "+unidadEncontrado.getNombre()+" ya existe. Por favor ingrese uno nuevo.");
				limpiarDatos();
			}
		}catch(NullPointerException n){
			n.getMessage();
			abrirDialogoConfirmacionRegistro();
		}
	}
}
