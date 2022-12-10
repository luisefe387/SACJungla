package co.com.jungla.sac.presentacion.ventanas;

import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;


import co.com.jungla.sac.persistencia.entidades.ControlInventario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

import co.com.jungla.sac.negocio.delegados.DelegadoControlInventario;

public class VentModificarCantidadAlerta extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField txtCantAlerta;
	private List<ControlInventario> articuloElegidoInventario;
	DelegadoControlInventario delegadoControlInventario= new DelegadoControlInventario();
	

	/**
	 * Create the frame.
	 */
	public VentModificarCantidadAlerta(String codigoArticulo) {
		setTitle("Modificar Cantidad Alerta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 524, 137);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCantAlerta = new JLabel("Cantidad m\u00EDnima para generar la ALERTA");
		lblCantAlerta.setBackground(new Color(153, 204, 153));
		lblCantAlerta.setOpaque(true);
		lblCantAlerta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantAlerta.setBounds(10, 22, 248, 22);
		contentPane.add(lblCantAlerta);
		
		txtCantAlerta = new JFormattedTextField();
		txtCantAlerta.setBounds(260, 23, 86, 20);
		contentPane.add(txtCantAlerta);
		txtCantAlerta.setColumns(10);
		
		//Boton para registrar el articulo 
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setForeground(new Color(0, 51, 0));
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirDialogoConfirmacionRegistro();
			}
		});
		btnModificar.setBounds(151, 68, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(278, 68, 89, 23);
		contentPane.add(btnCerrar);
		
		JSeparator sp = new JSeparator();
		sp.setBounds(10, 55, 496, 2);
		contentPane.add(sp);
		
		JLabel lblUnidadMedida = new JLabel("");
		lblUnidadMedida.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUnidadMedida.setBackground(UIManager.getColor("Button.background"));
		lblUnidadMedida.setBounds(353, 26, 89, 14);
		contentPane.add(lblUnidadMedida);

		articuloElegidoInventario = delegadoControlInventario.traerContInventarioPorCodigoArticulo(Integer.parseInt(codigoArticulo));
		lblUnidadMedida.setText(String.valueOf(articuloElegidoInventario.get(0).getArticulo().getUnidadMedida().getAbreviatura()));
		
	}
	//Metodo para modificar el la cantidad minima para generar un alerta
	private void modificarCantidadAlerta() {

		ControlInventario articuloAModificarCantAlerta;
		articuloAModificarCantAlerta = articuloElegidoInventario.get(0);
		
		articuloAModificarCantAlerta.setCantAlerta(Integer.parseInt(txtCantAlerta.getText()));
		delegadoControlInventario.actualizarControlInventario(articuloAModificarCantAlerta);
	}
	
	//Metodo para abrir ventanta de confirmacion de registro
	private void abrirDialogoConfirmacionRegistro(){
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de modificar la información actual ?", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			modificarCantidadAlerta();
			dispose();
		}else{
		
		}
	}
}
