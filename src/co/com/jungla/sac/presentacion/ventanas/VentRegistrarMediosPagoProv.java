package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import co.com.jungla.sac.persistencia.entidades.MedioPagoProv;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import co.com.jungla.sac.negocio.delegados.DelegadoMedioPagoProv;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro del medio de pago para el proveedor
 * @author Luis Fernando Pedroza T.
 * @version: 21/09/2016
 */
public class VentRegistrarMediosPagoProv extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtBanco;
	private JTextField txtNroCuenta;
	private JTextField txtCaja;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbCaja;
	private JRadioButton rdbBanco;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentRegistrarMediosPagoProv() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarMediosPagoProv.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Nuevo Medio de Pago a Proveedores");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 525, 227);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngrese = new JLabel("Ingrese la nueva FORMA de PAGO.");
		lblIngrese.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIngrese.setBounds(158, 22, 200, 14);
		contentPane.add(lblIngrese);
		
		txtBanco = new JTextField();
		txtBanco.setBounds(158, 52, 270, 20);
		contentPane.add(txtBanco);
		txtBanco.setColumns(10);
		//Evento del boton ingresar Forma de pago
		JButton btnIngresar = new JButton("Registrar");
		btnIngresar.setForeground(new Color(0, 51, 0));
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
			
		});
		btnIngresar.setBounds(158, 153, 89, 23);
		contentPane.add(btnIngresar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(281, 153, 89, 23);
		contentPane.add(btnCerrar);
		
		rdbBanco = new JRadioButton("Por Banco");
		rdbBanco.setBackground(new Color(153, 204, 153));
		rdbBanco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBanco.setEditable(true);
				txtNroCuenta.setEditable(true);
				txtCaja.setEditable(false);
				txtCaja.setText("");
			}
		});
		buttonGroup.add(rdbBanco);
		rdbBanco.setBounds(22, 61, 107, 22);
		rdbBanco.setSelected(true);
		contentPane.add(rdbBanco);
		
		rdbCaja = new JRadioButton("Por Caja");
		rdbCaja.setBackground(new Color(153, 204, 153));
		rdbCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBanco.setEditable(false);
				txtNroCuenta.setEditable(false);
				txtCaja.setEditable(true);
				txtBanco.setText("");
				txtNroCuenta.setText("");
			}
		});
		buttonGroup.add(rdbCaja);
		rdbCaja.setBounds(22, 111, 107, 22);
		contentPane.add(rdbCaja);
		
		txtNroCuenta = new JTextField();
		txtNroCuenta.setBounds(158, 75, 181, 20);
		contentPane.add(txtNroCuenta);
		txtNroCuenta.setColumns(10);
		
		JLabel lblNombreBanco = new JLabel("Nombre Banco");
		lblNombreBanco.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNombreBanco.setForeground(new Color(0, 153, 204));
		lblNombreBanco.setBounds(432, 55, 78, 14);
		contentPane.add(lblNombreBanco);
		
		JLabel lblNumeroDeCuenta = new JLabel("Numero de Cuenta");
		lblNumeroDeCuenta.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNumeroDeCuenta.setForeground(new Color(0, 153, 204));
		lblNumeroDeCuenta.setBounds(343, 79, 109, 14);
		contentPane.add(lblNumeroDeCuenta);
		
		txtCaja = new JTextField();
		txtCaja.setEditable(false);
		txtCaja.setBounds(158, 112, 270, 20);
		contentPane.add(txtCaja);
		txtCaja.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNombre.setForeground(new Color(0, 153, 204));
		lblNombre.setBounds(432, 115, 57, 14);
		contentPane.add(lblNombre);
	}
	//Metodo que valida los datos ingresados para su posterior registro 
	private void validarDatos() {
		if(rdbBanco.isSelected() && (txtBanco.getText().isEmpty() || txtNroCuenta.getText().isEmpty())){
			JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del banco y el nro de cuenta");
		}else{
			if(rdbCaja.isSelected() && txtCaja.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Debe ingresar el nombre de caja");
			}else{
				abrirDialogoConfirmacionRegistro();
			}
		}
	}
	//Metodo que permite el ingreso del medio de pago a la base de datos luego de haberse validado
	private void registrarMedioPago() {
		MedioPagoProv formaPago = new MedioPagoProv();
		DelegadoMedioPagoProv delegadoFormaPago = new DelegadoMedioPagoProv();
		if(rdbBanco.isSelected()){
			formaPago.setDescripcion("Banco: "+txtBanco.getText()+" - "+txtNroCuenta.getText());
			formaPago.setNombreCuenta("Banco");
			delegadoFormaPago.insertarFormaPago(formaPago);
			JOptionPane.showMessageDialog(null, "La FORMA de PAGO ha sido guardada satisfactoriamente ");
		}
		
		if(rdbCaja.isSelected()){
			formaPago.setDescripcion("Caja: "+txtCaja.getText());
			formaPago.setNombreCuenta("Caja");
			delegadoFormaPago.insertarFormaPago(formaPago);
			JOptionPane.showMessageDialog(null, "La FORMA de PAGO ha sido guardada satisfactoriamente ");
		}
		
	}
	//Limpiar los datos escritos 
	private void limpiarDatos() {
		txtBanco.setText("");
		txtNroCuenta.setText("");
		txtCaja.setText("");
	}
	
	//Metodo para abrir ventanta de confirmacion de registro
	private void abrirDialogoConfirmacionRegistro(){
		int res = JOptionPane.showConfirmDialog(null, "Desea guardar esta FORMA de PAGO?", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarMedioPago();
			limpiarDatos();
		}else{
		
		}
	}
}
