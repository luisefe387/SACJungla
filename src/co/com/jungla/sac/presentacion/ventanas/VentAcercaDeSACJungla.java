package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 * llevar a cabo la vualizacion de la informacion del programa
 * @author Luis Fernando Pedroza T.
 * @version: 21/09/2016
 */
public class VentAcercaDeSACJungla extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
		
	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentAcercaDeSACJungla() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentAcercaDeSACJungla.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Ventana Acerca Del Programa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 489, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txpAtencion = new JTextPane();
		txpAtencion.setEditable(false);
		txpAtencion.setFont(new Font("Tahoma", Font.BOLD, 16));
		txpAtencion.setBackground(UIManager.getColor("Button.background"));
		txpAtencion.setText("Sistema Administrativo dise\u00F1ado para la Empresa de Bordados Industriales \"Jungla\" Armenia");
		txpAtencion.setBounds(18, 78, 453, 51);
		contentPane.add(txpAtencion);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 190, 461, 2);
		contentPane.add(sp);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.setBounds(185, 203, 100, 23);
		contentPane.add(btnCerrar);
		
		JTextPane txtpnSacjungla = new JTextPane();
		txtpnSacjungla.setEditable(false);
		txtpnSacjungla.setText("SACJungla");
		txtpnSacjungla.setFont(new Font("Tahoma", Font.BOLD, 24));
		txtpnSacjungla.setBackground(UIManager.getColor("Button.background"));
		txtpnSacjungla.setBounds(170, 28, 157, 39);
		contentPane.add(txtpnSacjungla);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(20, 11, 94, 56);
		contentPane.add(lblNewLabel);
		
		JTextPane txtpnAutorLuisFernando = new JTextPane();
		txtpnAutorLuisFernando.setEditable(false);
		txtpnAutorLuisFernando.setText("Autor: Luis Fernando Pedroza Taborda");
		txtpnAutorLuisFernando.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtpnAutorLuisFernando.setBackground(UIManager.getColor("Button.background"));
		txtpnAutorLuisFernando.setBounds(75, 140, 377, 39);
		contentPane.add(txtpnAutorLuisFernando);
		
	}
}
