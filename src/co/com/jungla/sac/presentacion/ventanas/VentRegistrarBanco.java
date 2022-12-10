package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import co.com.jungla.sac.persistencia.entidades.Banco;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;

import co.com.jungla.sac.negocio.delegados.DelegadoBanco;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro del banco
 * @author Luis Fernando Pedroza T.
 * @version: 22/09/2016
 */
public class VentRegistrarBanco extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtEntidadFinanciera;
	private JTextField txtNroCuenta;
	private JTextField txtTitular;
	private JComboBox cbTipoCuenta;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentRegistrarBanco() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarBanco.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Nuevo Banco");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 472, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setForeground(new Color(0, 51, 0));
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
			
		});
		btnRegistrar.setBounds(123, 134, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(246, 134, 89, 23);
		contentPane.add(btnCerrar);
		
		txtEntidadFinanciera = new JTextField();
		txtEntidadFinanciera.setBounds(137, 45, 317, 20);
		contentPane.add(txtEntidadFinanciera);
		txtEntidadFinanciera.setColumns(10);
		
		JLabel lblTipoCuenta = new JLabel("Tipo de Cuenta");
		lblTipoCuenta.setOpaque(true);
		lblTipoCuenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipoCuenta.setBackground(new Color(153, 204, 153));
		lblTipoCuenta.setBounds(12, 21, 123, 22);
		contentPane.add(lblTipoCuenta);
		
		JLabel lblEntidadFinanciera = new JLabel("Entidad Financiera");
		lblEntidadFinanciera.setOpaque(true);
		lblEntidadFinanciera.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEntidadFinanciera.setBackground(new Color(153, 204, 153));
		lblEntidadFinanciera.setBounds(12, 44, 123, 22);
		contentPane.add(lblEntidadFinanciera);
		
		JLabel lblNroCuenta = new JLabel("Nro Cuenta");
		lblNroCuenta.setOpaque(true);
		lblNroCuenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNroCuenta.setBackground(new Color(153, 204, 153));
		lblNroCuenta.setBounds(12, 67, 123, 22);
		contentPane.add(lblNroCuenta);
		
		txtNroCuenta = new JTextField();
		txtNroCuenta.setColumns(10);
		txtNroCuenta.setBounds(137, 68, 317, 20);
		contentPane.add(txtNroCuenta);
		
		JLabel lblTitular = new JLabel("Titular");
		lblTitular.setOpaque(true);
		lblTitular.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitular.setBackground(new Color(153, 204, 153));
		lblTitular.setBounds(12, 90, 123, 22);
		contentPane.add(lblTitular);
		
		txtTitular = new JTextField();
		txtTitular.setColumns(10);
		txtTitular.setBounds(137, 91, 317, 20);
		contentPane.add(txtTitular);
		
		cbTipoCuenta = new JComboBox();
		cbTipoCuenta.setModel(new DefaultComboBoxModel(new String[] {"--ELIJA TIPO DE CUENTA--", "CUENTA DE AHORROS", "CUENTA CORRIENTE"}));
		cbTipoCuenta.setBounds(137, 21, 317, 22);
		contentPane.add(cbTipoCuenta);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 123, 444, 2);
		contentPane.add(sp);
	}
	
	//Metodo que valida los datos ingresados para su posterior registro 
	private void validarDatos() {
		if("--ELIJA TIPO DE CUENTA--".equals(cbTipoCuenta.getSelectedItem())){
			JOptionPane.showMessageDialog(null, "Debe elegir un tipo de cuenta");
		}else{
			if(txtEntidadFinanciera.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Debe digitar el nombre de la entidad financiera");
			}else{
				if(txtNroCuenta.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Debe digitar el Nro de cuenta");
				}else{
					if(txtTitular.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Debe digitar el nombre del titular de la cuenta");
					}else{
						abrirDialogoConfirmacionRegistro();
					}
				}
			}
		}
	}
	//Metodo que permite el registro del banco a la base de datos luego de haberse validado
	private void registrarBanco() {
		Banco banco = new Banco();
		DelegadoBanco delegadoBanco = new DelegadoBanco();
		String nl = System.getProperty("line.separator");
		
		banco.setTipoCuenta(cbTipoCuenta.getSelectedItem().toString());
		banco.setEntidad(txtEntidadFinanciera.getText());
		banco.setNroCuenta(txtNroCuenta.getText());
		banco.setNombreTitular(txtTitular.getText());
		banco.setEstado("Activo");
		banco.setSaldoActual(0);
		
		delegadoBanco.insertarBanco(banco);
		JOptionPane.showMessageDialog(null, "La ENTIDAD FINANCIERA ha sido guardada satisfactoriamente"+nl+"Para modificar la informacion registrada debe ingresar por el menu ventas - consultas - bancos");
		
	}
	//Limpiar los datos escritos 
	private void limpiarDatos() {
		cbTipoCuenta.setSelectedIndex(0);
		txtNroCuenta.setText("");
		txtEntidadFinanciera.setText("");
		txtTitular.setText("");
	}
	
	//Metodo para abrir ventanta de confirmacion de registro
	private void abrirDialogoConfirmacionRegistro(){
		int res = JOptionPane.showConfirmDialog(null, "Desea guardar este Banco?", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarBanco();
			limpiarDatos();
		}else{
		
		}
	}
}
