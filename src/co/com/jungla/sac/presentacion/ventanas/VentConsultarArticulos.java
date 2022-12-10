package co.com.jungla.sac.presentacion.ventanas;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;


import co.com.jungla.sac.negocio.delegados.DelegadoArticulo;
import co.com.jungla.sac.negocio.delegados.DelegadoLineaArticulos;
import co.com.jungla.sac.persistencia.entidades.Articulo;
import co.com.jungla.sac.persistencia.entidades.LineaArticulos;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class VentConsultarArticulos extends JFrame {

	private JPanel contentPane;
	DefaultComboBoxModel modeloUnidadMedida = new DefaultComboBoxModel();
	DefaultComboBoxModel modeloLineaArticulos = new DefaultComboBoxModel();
	public JComboBox cbFiltro;
	private JComboBox cbLineaArticulos;
	private JButton btnMostrarConsulta;
	private JTextField txtConsulta;
	private DelegadoArticulo delegadoArticulo = new DelegadoArticulo();
	private VentMostrarArticulos ventMostrarArticulos;
	
	/**
	 * Create the frame.
	 */
	public VentConsultarArticulos() {
		setTitle("Consulta de Articulos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 504, 136);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFiltro = new JLabel("Filtro");
		lblFiltro.setBackground(new Color(153, 204, 153));
		lblFiltro.setOpaque(true);
		lblFiltro.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFiltro.setBounds(10, 22, 117, 22);
		contentPane.add(lblFiltro);
		
		cbFiltro = new JComboBox();
		cbFiltro.setModel(new DefaultComboBoxModel(new String[] {"--ELIJA FILTRO--", "Linea", "Articulo", "Codigo", "Referencia", "TODO"}));
		//Evento en la que permite el registro de una linea cuando esta no existe en el combo box
		cbFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elegirFiltro();
			}
		});
 
		cbFiltro.setBounds(129, 23, 123, 20);
		contentPane.add(cbFiltro);
		
		//Boton para registrar el articulo 
		btnMostrarConsulta = new JButton("Mostrar Consulta");
		btnMostrarConsulta.setForeground(new Color(0, 51, 0));
		btnMostrarConsulta.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMostrarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarConsulta();
			}
		});
		btnMostrarConsulta.setBounds(169, 68, 165, 23);
		contentPane.add(btnMostrarConsulta);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 55, 476, 2);
		contentPane.add(sp);
		
		cbLineaArticulos = new JComboBox();
		cbLineaArticulos.setBounds(262, 22, 224, 22);
		cbLineaArticulos.setVisible(false);
		contentPane.add(cbLineaArticulos);
		
		txtConsulta = new JTextField();
		txtConsulta.setBounds(262, 23, 224, 20);
		txtConsulta.setVisible(false);
		contentPane.add(txtConsulta);
		txtConsulta.setColumns(10);
	}
	//Metodo para elegir filtro para realizar la consulta
	private void elegirFiltro() {
		if("Linea".equals(cbFiltro.getSelectedItem().toString())){
			DelegadoLineaArticulos delegadoLineaArticulos = new DelegadoLineaArticulos();
			cbLineaArticulos.setVisible(true);
			List<LineaArticulos> lineaArticulos = delegadoLineaArticulos.listarLineaArticulos();
			
			for(LineaArticulos lineas : lineaArticulos){
				modeloLineaArticulos.addElement(new LineaArticulos(lineas.getNombreL(), lineas.getCodigo()));
				cbLineaArticulos.setModel(modeloLineaArticulos);
			}
			txtConsulta.setText("1");
		}else{
			if("Articulo".equals(cbFiltro.getSelectedItem().toString())){
				cbLineaArticulos.setVisible(false);
				txtConsulta.setVisible(true);
				txtConsulta.setText("");
			}else{
				if("Codigo".equals(cbFiltro.getSelectedItem().toString())){
					cbLineaArticulos.setVisible(false);
					txtConsulta.setVisible(true);
					txtConsulta.setText("");
				}else{
					if("Referencia".equals(cbFiltro.getSelectedItem().toString())){
						cbLineaArticulos.setVisible(false);
						txtConsulta.setVisible(true);
						txtConsulta.setText("");
					}else{
						cbLineaArticulos.setVisible(false);
						txtConsulta.setVisible(false);
						txtConsulta.setText("1");
					}
				}
			}
		}
		
	}
	
	//Metodo que valida los datos ingresados para su posterior consulta
	private void validarConsulta() {
		if(cbFiltro.getSelectedItem().equals("--ELIJA FILTRO--")){
			JOptionPane.showMessageDialog(null, "No ha elegido ningún filtro");
		}else{
			if("".equals(txtConsulta.getText().toString())){
				JOptionPane.showMessageDialog(null, "Debe digitar alguna información");
			}else{
				realizarConsulta();
			}
		}
	}
	
	//Metodo que permite realizar la consulta a la base de datos luego de haberse validado
	private void realizarConsulta() {
		if("Linea".equals(cbFiltro.getSelectedItem().toString())){
			List <Articulo> articulosPorLinea = delegadoArticulo.traerArticulosPorLineaArticulos(cbLineaArticulos.getSelectedItem().toString());
			ventMostrarArticulos  = new VentMostrarArticulos(articulosPorLinea); 
			ventMostrarArticulos.setVisible(true);
		}else{
			if("Articulo".equals(cbFiltro.getSelectedItem().toString())){
				List <Articulo> articulosPorNombre = delegadoArticulo.traerArticuloPorNombre(txtConsulta.getText().toString());
				ventMostrarArticulos  = new VentMostrarArticulos(articulosPorNombre); 
				ventMostrarArticulos.setVisible(true);
			}else{
				if("Codigo".equals(cbFiltro.getSelectedItem().toString())){
					List <Articulo> articulosPorCodigo = delegadoArticulo.traerLineaUnidadArticuloPorCodigo(Integer.parseInt(txtConsulta.getText().toString()));
					ventMostrarArticulos  = new VentMostrarArticulos(articulosPorCodigo); 
					ventMostrarArticulos.setVisible(true);
				}else{
					if("Referencia".equals(cbFiltro.getSelectedItem().toString())){
						List <Articulo> articulosPorReferencia = delegadoArticulo.traerArticuloPorReferencia(txtConsulta.getText().toString());
						ventMostrarArticulos  = new VentMostrarArticulos(articulosPorReferencia); 
						ventMostrarArticulos.setVisible(true);
					}else{
						List <Articulo> todosArticulos = delegadoArticulo.listarArticulo();
						ventMostrarArticulos  = new VentMostrarArticulos(todosArticulos); 
						ventMostrarArticulos.setVisible(true);
					}
				}
			}
		}
	}
}
