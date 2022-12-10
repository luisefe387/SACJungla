package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import co.com.jungla.sac.persistencia.entidades.LineaArticulos;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.LineBorder;

import co.com.jungla.sac.negocio.delegados.DelegadoLineaArticulos;

public class VentModificarLineaArticulo extends JDialog {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtCodigo;
	private LineaArticulos lineaElegida;
	private JComboBox cbEstado;
	
	/**
	 * Create the frame.
	 */
	public VentModificarLineaArticulo() {
		setTitle("Modificar Linea Articulo");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 524, 204);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre Art\u00EDculo ");
		lblNombre.setBackground(new Color(153, 204, 153));
		lblNombre.setOpaque(true);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(10, 45, 117, 22);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(129, 45, 375, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		//Boton para registrar el articulo 
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setForeground(new Color(0, 51, 0));
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirDialogoActualizacion();
			}
		});
		btnModificar.setBounds(147, 138, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(274, 138, 89, 23);
		contentPane.add(btnCerrar);
		
		JSeparator sp1 = new JSeparator();
		sp1.setBackground(new Color(0, 51, 0));
		sp1.setBounds(10, 78, 494, 2);
		contentPane.add(sp1);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setOpaque(true);
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCodigo.setBackground(new Color(153, 204, 153));
		lblCodigo.setBounds(10, 22, 117, 22);
		contentPane.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(129, 23, 135, 20);
		contentPane.add(txtCodigo);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setOpaque(true);
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstado.setBackground(new Color(153, 204, 153));
		lblEstado.setBounds(10, 91, 117, 22);
		contentPane.add(lblEstado);
		
		cbEstado = new JComboBox();
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"Activo", "Inactivo"}));
		cbEstado.setBounds(129, 92, 135, 20);
		contentPane.add(cbEstado);
		
		JSeparator sp2 = new JSeparator();
		sp2.setBackground(new Color(0, 51, 0));
		sp2.setBounds(10, 125, 494, 2);
		contentPane.add(sp2);
		
		setModal(true);
		
	}
	
	//Metodo que permite el ingreso del articulo a la base de datos luego de haberse validado
	private void modificarLineaArticulo() {
		DelegadoLineaArticulos delegadoLineaArticulos = new DelegadoLineaArticulos();
		
		LineaArticulos lineaAModificar = lineaElegida;
		
		lineaAModificar.setNombreL(txtNombre.getText());
		lineaAModificar.setEstado(cbEstado.getSelectedItem().toString());
		
		delegadoLineaArticulos.actualizarLineaArticulos(lineaAModificar);
	}
	
	//Metodo para abrir ventanta de confirmacion de registro
	private void abrirDialogoActualizacion(){
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de actualizar la siguiente Linea de Articulos", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			modificarLineaArticulo();
			dispose();
		}else{
		
		}
	}
	
	//Metodo para agregar los datos de la orden de compra a la compra
	public void agregarDatos(LineaArticulos lineaElegida){
		txtCodigo.setText(String.valueOf(lineaElegida.getCodigo()));
		txtNombre.setText(lineaElegida.getNombreL());
		cbEstado.getModel().setSelectedItem(lineaElegida.getEstado());
		this.lineaElegida = lineaElegida;
	}
	
	
}
