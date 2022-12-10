package co.com.jungla.sac.presentacion.ventanas;

import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import co.com.jungla.sac.persistencia.entidades.Articulo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.LineBorder;

import co.com.jungla.sac.negocio.delegados.DelegadoArticulo;

public class VentModificarNombreArticulo extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField txtNombre;
	private List<Articulo> articuloElegidoInventario;
	DelegadoArticulo delegadoArticulo= new DelegadoArticulo();
	

	/**
	 * Create the frame.
	 */
	public VentModificarNombreArticulo(String codigoArticulo) {
		setTitle("Modificar Nombre del Articulo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 524, 137);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nuevo Nombre");
		lblNombre.setBackground(new Color(153, 204, 153));
		lblNombre.setOpaque(true);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(10, 22, 97, 22);
		contentPane.add(lblNombre);
		
		txtNombre = new JFormattedTextField();
		txtNombre.setBounds(116, 23, 390, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
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

		articuloElegidoInventario = delegadoArticulo.traerLineaUnidadArticuloPorCodigo(Integer.parseInt(codigoArticulo));
		
	}
	//Metodo para modificar el nombre del articulo
	private void modificarNombreArticulo() {

		Articulo articuloAModificarNombre;
		articuloAModificarNombre = articuloElegidoInventario.get(0);
		
		articuloAModificarNombre.setNombre(txtNombre.getText());
		delegadoArticulo.actualizarArticulo(articuloAModificarNombre);
	}
	
	//Metodo para abrir ventanta de confirmacion de registro
	private void abrirDialogoConfirmacionRegistro(){
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de modificar la información actual ?", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			modificarNombreArticulo();
			dispose();
		}else{
		
		}
	}
}
