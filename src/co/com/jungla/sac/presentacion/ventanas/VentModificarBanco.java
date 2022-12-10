package co.com.jungla.sac.presentacion.ventanas;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import co.com.jungla.sac.persistencia.entidades.Banco;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;

import co.com.jungla.sac.negocio.delegados.DelegadoBanco;

public class VentModificarBanco extends JDialog {

	private JPanel contentPane;
	private JTextField txtEntidadFinanciera;
	private JTextField txtNroCuenta;
	private JTextField txtTitular;
	private JComboBox cbEstado;
	private JTextField txtTipoCuenta;
	private Banco bancoElegido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentModificarBanco frame = new VentModificarBanco();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentModificarBanco() {
		setTitle("Modificar Banco");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 472, 246);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setForeground(new Color(0, 51, 0));
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
			
		});
		btnActualizar.setBounds(113, 179, 111, 23);
		contentPane.add(btnActualizar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(258, 179, 89, 23);
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
		
		JSeparator sp1 = new JSeparator();
		sp1.setBackground(new Color(0, 51, 0));
		sp1.setBounds(12, 166, 442, 2);
		contentPane.add(sp1);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(12, 120, 442, 2);
		contentPane.add(sp);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setOpaque(true);
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstado.setBackground(new Color(153, 204, 153));
		lblEstado.setBounds(12, 133, 117, 22);
		contentPane.add(lblEstado);
		
		cbEstado = new JComboBox();
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"Activo", "Inactivo"}));
		cbEstado.setBounds(131, 134, 135, 20);
		contentPane.add(cbEstado);
		
		txtTipoCuenta = new JTextField();
		txtTipoCuenta.setEditable(false);
		txtTipoCuenta.setColumns(10);
		txtTipoCuenta.setBounds(137, 22, 181, 20);
		contentPane.add(txtTipoCuenta);
		
		setModal(true);
	}
	//Metodo que valida los datos ingresados para su posterior actualizacion 
	private void validarDatos() {
		if(txtEntidadFinanciera.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Debe digitar el nombre de la entidad financiera");
		}else{
			if(txtNroCuenta.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Debe digitar el Nro de cuenta");
			}else{
				if(txtTitular.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Debe digitar el nombre del titular de la cuenta");
				}else{
					abrirDialogoActualizacion();
				}
			}
		}
	}
	//Metodo que permite la actualizacion del banco a la base de datos luego de haberse validado
	private void modificarBanco() {
		DelegadoBanco delegadoBanco = new DelegadoBanco();
		
		Banco bancoAModificar = bancoElegido;
		
		bancoAModificar.setEntidad(txtEntidadFinanciera.getText());
		bancoAModificar.setNroCuenta(txtNroCuenta.getText());
		bancoAModificar.setNombreTitular(txtTitular.getText());
		bancoAModificar.setEstado(cbEstado.getSelectedItem().toString());
		
		delegadoBanco.actualizarBanco(bancoAModificar);
		
	}
	
	//Metodo para abrir ventanta de actualizacion
	private void abrirDialogoActualizacion(){
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de actualizar este Banco", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			modificarBanco();
			dispose();
		}else{
		
		}
	}
	
	//Metodo para agregar los datos del banco
	public void agregarDatos(Banco bancoElegido){
		txtTipoCuenta.setText(bancoElegido.getTipoCuenta());
		txtEntidadFinanciera.setText(bancoElegido.getEntidad());
		txtNroCuenta.setText(bancoElegido.getNroCuenta());
		txtTitular.setText(bancoElegido.getNombreTitular());
		cbEstado.getModel().setSelectedItem(bancoElegido.getEstado());
		
		this.bancoElegido = bancoElegido;
	}
}
