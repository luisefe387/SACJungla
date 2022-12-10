package co.com.jungla.sac.presentacion.ventanas;

import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import co.com.jungla.sac.negocio.delegados.DelegadoAlertaVencimiento;
import co.com.jungla.sac.negocio.delegados.DelegadoArticulo;
import co.com.jungla.sac.persistencia.entidades.AlertaVencimiento;
import co.com.jungla.sac.persistencia.entidades.Articulo;
import co.com.jungla.sac.persistencia.entidades.Persona;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro de la alerta de vencimiento
 * @author Luis Fernando Pedroza T.
 * @version: 22/08/2016
 */
public class VentRegistrarAlertaVencimiento extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JScrollPane srcObservaciones;
	private JTextPane txpObservaciones;
	private JTextField txtCantidad;
	private JTextField txtCodigoArticulo;
	private DefaultComboBoxModel modeloArticulo = new DefaultComboBoxModel();
	private JComboBox cbArticulo;
	private JDateChooser dchVencimiento;
	private JLabel lblUnidad;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentRegistrarAlertaVencimiento() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarAlertaVencimiento.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Registrar Alerta de Vencimiento");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 237);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblArticulo = new JLabel("Articulo");
		lblArticulo.setBackground(new Color(153, 204, 153));
		lblArticulo.setOpaque(true);
		lblArticulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblArticulo.setBounds(10, 22, 107, 22);
		contentPane.add(lblArticulo);
		
		cbArticulo = new JComboBox();
		cbArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DelegadoArticulo delegadoArticulo = new DelegadoArticulo();
				Articulo articulo = (Articulo) cbArticulo.getSelectedItem();
				txtCodigoArticulo.setText(String.valueOf(articulo.getCodigo()));
				lblUnidad.setText(delegadoArticulo.traerLineaUnidadArticuloPorCodigo(articulo.getCodigo()).get(0).getUnidadMedida().getAbreviatura());
			}
		});
		cbArticulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		cbArticulo.setBounds(119, 23, 300, 20);
		contentPane.add(cbArticulo);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBackground(new Color(153, 204, 153));
		lblCantidad.setOpaque(true);
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantidad.setBounds(10, 45, 107, 22);
		contentPane.add(lblCantidad);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBackground(new Color(153, 204, 153));
		lblObservaciones.setOpaque(true);
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaciones.setBounds(10, 91, 107, 45);
		contentPane.add(lblObservaciones);
		 
		JButton btnRegistarAlerta = new JButton("Registrar Alerta");
		btnRegistarAlerta.setForeground(new Color(0, 51, 0));
		btnRegistarAlerta.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegistarAlerta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
		});
		btnRegistarAlerta.setBounds(125, 160, 145, 23);
		contentPane.add(btnRegistarAlerta);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(298, 160, 89, 23);
		contentPane.add(btnCerrar);
		
		dchVencimiento = new JDateChooser();
		dchVencimiento.setBounds(119, 69, 138, 20);
		dchVencimiento.setSelectableDateRange(null, new Date());
		contentPane.add(dchVencimiento);
		
		JLabel lblVencimiento = new JLabel("Vencimiento");
		lblVencimiento.setBackground(new Color(153, 204, 153));
		lblVencimiento.setOpaque(true);
		lblVencimiento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVencimiento.setBounds(10, 68, 107, 22);
		contentPane.add(lblVencimiento);
		
		srcObservaciones = new JScrollPane();
		srcObservaciones.setBounds(119, 92, 352, 43);
		contentPane.add(srcObservaciones);
		
		txpObservaciones = new JTextPane();
		srcObservaciones.setViewportView(txpObservaciones);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(119, 46, 89, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 147, 501, 2);
		contentPane.add(sp);
		
		txtCodigoArticulo = new JTextField();
		txtCodigoArticulo.setEditable(false);
		txtCodigoArticulo.setBounds(422, 23, 89, 20);
		contentPane.add(txtCodigoArticulo);
		txtCodigoArticulo.setColumns(10);
		
		lblUnidad = new JLabel("");
		lblUnidad.setBackground(new Color(255, 102, 102));
		lblUnidad.setBounds(211, 45, 59, 20);
		contentPane.add(lblUnidad);
		
		//Metodos que se ejecutan al inicialiar la ventana
		listarArticulos();
		
	}
	
	//Metodo para listar los articulos y desplegarlos en un combo box
	private void listarArticulos() {
		DelegadoArticulo delegadoArticulo = new DelegadoArticulo();
		List<Articulo> articulos = delegadoArticulo.listarArticulo();
		modeloArticulo.addElement("--ELIJA ARTICULO--");
		cbArticulo.setModel(modeloArticulo);
		
		for(Articulo articulo : articulos){
			modeloArticulo.addElement(new Articulo (articulo.getNombre(), articulo.getCodigo()));
			cbArticulo.setModel(modeloArticulo);
		}
	}
	
	//Metodo para los datos ingresados
	private void validarDatos(){
		if("--ELIJA ARTICULO--".equals(cbArticulo.getSelectedItem().toString())){
			JOptionPane.showMessageDialog(null, "Debe elegir el articulo");
		}else{
			if("".equals(txtCantidad.getText())){
				JOptionPane.showMessageDialog(null, "Debe ingresar una cantidad");
			}else{
				if(dchVencimiento.getDate()==null){
					JOptionPane.showMessageDialog(null, "Debe ingresar una fecha de vencimiento");
				}else{
					abrirDialogoConfirmacionRegistro();
				}
			}
		}
	}
	//Metodo para confirmar el registro
	private void abrirDialogoConfirmacionRegistro() {
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de grabar esta alerta de vencimiento?", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarAlertaVencimiento();
		}else{
		
		}
	}

	//Metodo para registrar la alerta de vencimiento
	private void registrarAlertaVencimiento() {
		AlertaVencimiento alertaVencimiento = new AlertaVencimiento();
		DelegadoAlertaVencimiento delegadoAlertaVencimiento = new DelegadoAlertaVencimiento();
		Articulo articulos = new Articulo();
		Articulo articulo = (Articulo) cbArticulo.getSelectedItem();
		articulos.setCodigo(articulo.getCodigo());
		alertaVencimiento.setArticulo(articulos);
		alertaVencimiento.setCantidad(Integer.parseInt(txtCantidad.getText()));
		alertaVencimiento.setFechaVencimiento(dchVencimiento.getDate());
		alertaVencimiento.setObservaciones(txpObservaciones.getText());
		
		delegadoAlertaVencimiento.insertarAlertaVencimiento(alertaVencimiento);
		
		JOptionPane.showMessageDialog(null, "La Alerta de Vencimiento ha sido guardada satisfactoriamente");
	}
}
