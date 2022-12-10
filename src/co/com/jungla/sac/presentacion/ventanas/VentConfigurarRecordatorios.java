package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import co.com.jungla.sac.negocio.delegados.DelegadoRecordatorio;
import co.com.jungla.sac.persistencia.entidades.Recordatorio;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;

import javax.swing.JSeparator;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo la configuracion del recordatorio
 * @author Luis Fernando Pedroza T.
 * @version: 22/08/2016
 */
public class VentConfigurarRecordatorios extends JDialog {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtDiasVencimiento;
	private JTextField txtDiasCartera;
	private JTextField txtDiasCxP;
	private JComboBox cbMostrarInicio;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentConfigurarRecordatorios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentConfigurarRecordatorios.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Configurar Recordatorios");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 314, 204);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblArticulo = new JLabel("D\u00EDas Vencimiento");
		lblArticulo.setBackground(new Color(153, 204, 153));
		lblArticulo.setOpaque(true);
		lblArticulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblArticulo.setBounds(52, 45, 107, 22);
		contentPane.add(lblArticulo);
		
		JLabel lblCantidad = new JLabel("D\u00EDas Cartera");
		lblCantidad.setBackground(new Color(153, 204, 153));
		lblCantidad.setOpaque(true);
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantidad.setBounds(52, 68, 107, 22);
		contentPane.add(lblCantidad);
		 
		JButton btnGuardar = new JButton("Guardar ");
		btnGuardar.setForeground(new Color(0, 51, 0));
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
		});
		btnGuardar.setBounds(41, 138, 107, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(176, 138, 89, 23);
		contentPane.add(btnCerrar);
		
		JLabel lblDiasCxP = new JLabel("D\u00EDas CxP");
		lblDiasCxP.setBackground(new Color(153, 204, 153));
		lblDiasCxP.setOpaque(true);
		lblDiasCxP.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDiasCxP.setBounds(52, 91, 107, 22);
		contentPane.add(lblDiasCxP);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 125, 286, 2);
		contentPane.add(sp);
		
		txtDiasVencimiento = new JTextField();
		txtDiasVencimiento.setBounds(161, 46, 89, 20);
		contentPane.add(txtDiasVencimiento);
		txtDiasVencimiento.setColumns(10);
		
		txtDiasCartera = new JTextField();
		txtDiasCartera.setColumns(10);
		txtDiasCartera.setBounds(161, 69, 89, 20);
		contentPane.add(txtDiasCartera);
		
		txtDiasCxP = new JTextField();
		txtDiasCxP.setColumns(10);
		txtDiasCxP.setBounds(161, 92, 89, 20);
		contentPane.add(txtDiasCxP);
		
		cbMostrarInicio = new JComboBox();
		cbMostrarInicio.setModel(new DefaultComboBoxModel(new String[] {"Si", "No"}));
		cbMostrarInicio.setBounds(161, 23, 54, 20);
		contentPane.add(cbMostrarInicio);
		
		JLabel lblMostrarAlInicio = new JLabel("Mostrar al Inicio");
		lblMostrarAlInicio.setOpaque(true);
		lblMostrarAlInicio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMostrarAlInicio.setBackground(new Color(153, 204, 153));
		lblMostrarAlInicio.setBounds(52, 22, 107, 22);
		contentPane.add(lblMostrarAlInicio);
		
		inicializarDatosRecordatorio();
		setModal(true);
	
	}
	
	//Metodo para inicializar ultimos datos del recordatorio
	private void inicializarDatosRecordatorio() {
		DelegadoRecordatorio delegadoRecordatorio = new DelegadoRecordatorio();
		Recordatorio recordatorio = delegadoRecordatorio.listarRecordatorios().get(0);
		txtDiasCartera.setText(String.valueOf(recordatorio.getDiasCartera()));
		txtDiasCxP.setText(String.valueOf(recordatorio.getDiasCxP()));
		txtDiasVencimiento.setText(String.valueOf(recordatorio.getDiasVencimiento()));
		if(recordatorio.isMostrarAlInicio()==true){
			cbMostrarInicio.getModel().setSelectedItem("Si");
		}else{
			cbMostrarInicio.getModel().setSelectedItem("No");
		}
	}


	//Metodo para los datos ingresados
	private void validarDatos(){
		if(txtDiasVencimiento.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Debe ingresar el limite de dias para recordar el vencimiento del articulo");
		}else{
			if(txtDiasCartera.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Debe ingresar el limite de dias para recordar la cartera pendiente");
			}else{
				if(txtDiasCxP.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Debe ingresar el limite de dias para recordar las cuentas por pagar");
				}else{
					abrirDialogoConfirmacionConfiguracion();
				}
			}
		}
	}
	//Metodo para confirmar la configuracion
	private void abrirDialogoConfirmacionConfiguracion() {
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de guardar esta configuración?", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			configurarRecordatorios();
		}else{
		
		}
	}

	//Metodo para configurar los recordatorios
	private void configurarRecordatorios() {
		DelegadoRecordatorio delegadoRecordatorio = new DelegadoRecordatorio();
		
		Recordatorio recordatorioAModificar = delegadoRecordatorio.listarRecordatorios().get(0);
		recordatorioAModificar.setDiasVencimiento(Integer.parseInt(txtDiasVencimiento.getText()));
		recordatorioAModificar.setDiasCartera(Integer.parseInt(txtDiasCartera.getText()));
		recordatorioAModificar.setDiasCxP(Integer.parseInt(txtDiasCxP.getText()));
		if("Si".equals(cbMostrarInicio.getSelectedItem().toString())){
			recordatorioAModificar.setMostrarAlInicio(true);
		}else{
			recordatorioAModificar.setMostrarAlInicio(false);
		}
		
		delegadoRecordatorio.actualizarRecordatorio(recordatorioAModificar);
		
		JOptionPane.showMessageDialog(null, "La Configuración ha sido guardada satisfactoriamente");
		
		VentMostrarRecordatorios ventMostrarRecordatorios = new VentMostrarRecordatorios();
		ventMostrarRecordatorios.setVisible(true);
		dispose();
	}
}
