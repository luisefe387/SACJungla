package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import co.com.jungla.sac.persistencia.entidades.TipoCliente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;

import co.com.jungla.sac.negocio.delegados.DelegadoTipoCliente;

import java.awt.Color;

public class VentRegistrarTipoCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtDescripcion;

	/**
	 * Create the frame.
	 */
	public VentRegistrarTipoCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarTipoCliente.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Nuevo Tipo de Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 252, 169);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingrese el nuevo TIPO de CLIENTE.");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(21, 32, 194, 14);
		contentPane.add(lblNewLabel);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(33, 57, 165, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		//Evento del boton ingresar tipo de cliente
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setForeground(new Color(0, 51, 0));
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarRegistro();
			}
		});
		btnIngresar.setBounds(22, 98, 89, 23);
		contentPane.add(btnIngresar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(121, 98, 89, 23);
		contentPane.add(btnCerrar);
	}
	//Metodo que valida los datos ingresados para su posterior registro 
	private void validarRegistro() {
		if(txtDescripcion.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Debe ingresar la descripcion del tipo de cliente");
		}else{
			ingresarTipoCliente();
			limpiar();
		}
	}
	//Metodo que permite el ingreso del tipo de cliente a la base de datos luego de haberse validado
	private void ingresarTipoCliente() {
		TipoCliente tipoCliente = new TipoCliente();
		tipoCliente.setDescripcion(txtDescripcion.getText());
		DelegadoTipoCliente delegadoTipoCliente = new DelegadoTipoCliente();
		delegadoTipoCliente.insertarTipoCliente(tipoCliente);
		JOptionPane.showMessageDialog(null, "Se registro exitosamente");
	}
	//Limpiar los datos escritos 
	private void limpiar() {
		txtDescripcion.setText("");
	}
}
