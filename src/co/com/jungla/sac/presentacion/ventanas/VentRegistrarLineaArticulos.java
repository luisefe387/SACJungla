package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import co.com.jungla.sac.persistencia.entidades.LineaArticulos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import co.com.jungla.sac.negocio.delegados.DelegadoLineaArticulos;

import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro de la linea de articulos
 * @author Luis Fernando Pedroza T.
 * @version: 21/09/2016
 */
public class VentRegistrarLineaArticulos extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtNombre;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentRegistrarLineaArticulos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarLineaArticulos.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Linea de Articulos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 252, 166);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingrese la nueva LINEA de ARTICULOS.");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(13, 30, 224, 14);
		contentPane.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(33, 57, 177, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		//Boton para registrar la linea de articulos
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setForeground(new Color(0, 51, 0));
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDato();
			}
		});
		btnIngresar.setBounds(27, 98, 89, 23);
		contentPane.add(btnIngresar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(126, 98, 89, 23);
		contentPane.add(btnCerrar);
	}
	
	//Metodo que valida los datos ingresados para su posterior registro
	private void validarDato() {
		if(txtNombre.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Debe ingresar el nombre de la linea de articulo");
		}else{
			abrirDialogoLineaArticuloRegistrada();
		}
	}
	
	//Metodo para abrir ventanta de confirmacion de registro
	private void abrirDialogoConfirmacionRegistro(){
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de registrar esta linea de articulos: "+txtNombre.getText().toString(), null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarLineaArticulo();
			limpiarDato();
		}else{
		
		}
	}
	
	//Metodo que permite el ingreso de la linea de articulos a la base de datos luego de haberse validado
	private void registrarLineaArticulo() {
		LineaArticulos lineaArticulos = new LineaArticulos();
		lineaArticulos.setNombreL(txtNombre.getText());
		lineaArticulos.setEstado("Activo");
		DelegadoLineaArticulos delegadoLineaArticulos = new DelegadoLineaArticulos();
		delegadoLineaArticulos.insertarLineaArticulos(lineaArticulos);
		JOptionPane.showMessageDialog(null, "Se registro exitosamente");
	}
	
	//Limpiar el dato escrito 
	private void limpiarDato() {
		txtNombre.setText("");
	}
	
	//Metodo para abrir ventana informando sobre una linea de articulo ya registrado con el mismo nombre
	private void abrirDialogoLineaArticuloRegistrada(){
		DelegadoLineaArticulos delegadoLineaArticulos = new DelegadoLineaArticulos();
		LineaArticulos nombreEncontrado;
		try{
			nombreEncontrado= delegadoLineaArticulos.traerLineaArticulo(txtNombre.getText().toString());
			if(txtNombre.getText().toString().equals(nombreEncontrado.getNombreL())){
				JOptionPane.showMessageDialog(null, "La LINEA DE ARTICULO con nombre: "+nombreEncontrado.getNombreL()+" ya existe. Por favor ingrese uno nuevo.");
				limpiarDato();
			}
		}catch(NullPointerException n){
			n.getMessage();
			abrirDialogoConfirmacionRegistro();
		}
	}
}
