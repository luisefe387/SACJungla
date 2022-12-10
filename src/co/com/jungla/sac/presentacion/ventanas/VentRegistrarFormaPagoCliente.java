package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import co.com.jungla.sac.persistencia.entidades.FormaPagoCliente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;

import co.com.jungla.sac.negocio.delegados.DelegadoFormaPagoCliente;

import java.awt.Color;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro de la forma de pago al cliente y su contabilizacion
 * @author Luis Fernando Pedroza T.
 * @version: 22/09/2016
 */
public class VentRegistrarFormaPagoCliente extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDias;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentRegistrarFormaPagoCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarFormaPagoCliente.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Nueva Forma de Pago a Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 364, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngrese = new JLabel("Ingrese la nueva FORMA de PAGO.");
		lblIngrese.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIngrese.setBounds(80, 22, 200, 14);
		contentPane.add(lblIngrese);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(82, 52, 264, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		//Evento del boton ingresar Forma de pago
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setForeground(new Color(0, 51, 0));
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
			
		});
		btnRegistrar.setBounds(71, 105, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(194, 105, 89, 23);
		contentPane.add(btnCerrar);
		
		txtDias = new JTextField();
		txtDias.setBounds(82, 75, 89, 20);
		txtDias.setText("0");
		txtDias.setDocument(new LimitadorCaracteres());
		contentPane.add(txtDias);
		txtDias.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setOpaque(true);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBackground(new Color(153, 204, 153));
		lblNombre.setBounds(10, 51, 70, 22);
		contentPane.add(lblNombre);
		
		JLabel lblDias = new JLabel("Dias");
		lblDias.setOpaque(true);
		lblDias.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDias.setBackground(new Color(153, 204, 153));
		lblDias.setBounds(10, 74, 70, 22);
		contentPane.add(lblDias);
	}
	
	//Metodo que valida los datos ingresados para su posterior registro 
	private void validarDatos() {
		if(txtNombre.getText().isEmpty() || txtDias.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Debe digitar el nombre de la forma de pago y los dias");
		}else{
			abrirDialogoConfirmacionRegistro();
		}
	}
	
	//Metodo que permite el ingreso de la forma de pago a la base de datos luego de haberse validado
	private void registrarFormaPago() {
		FormaPagoCliente formaPago = new FormaPagoCliente();
		DelegadoFormaPagoCliente delegadoFormaPagoCliente = new DelegadoFormaPagoCliente();
		
			formaPago.setDescripcion(txtNombre.getText());
			formaPago.setDias(Integer.parseInt(txtDias.getText()));
			delegadoFormaPagoCliente.insertarFormaPagoCliente(formaPago);
			JOptionPane.showMessageDialog(null, "La FORMA de PAGO ha sido guardada satisfactoriamente ");
		
	}
	
	//Limpiar los datos escritos 
	private void limpiarDatos() {
		txtNombre.setText("");
		txtDias.setText("");
	}
	
	//Metodo para abrir ventanta de confirmacion de registro
	private void abrirDialogoConfirmacionRegistro(){
		int res = JOptionPane.showConfirmDialog(null, "Desea guardar esta FORMA de PAGO?", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarFormaPago();
			limpiarDatos();
		}else{
		
		}
	}
}
