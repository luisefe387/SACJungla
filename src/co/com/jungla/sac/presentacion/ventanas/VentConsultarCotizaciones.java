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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.LineBorder;

import co.com.jungla.sac.negocio.delegados.DelegadoCotizacion;
import co.com.jungla.sac.persistencia.entidades.Cotizacion;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo la consulta de las cotizacion partiendo de varios filtros de busqueda
 * @author Luis Fernando Pedroza T.
 * @version: 01/09/2016
 */
public class VentConsultarCotizaciones extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtConsulta;
	public JComboBox cbFiltro;
	private JComboBox cbDatos;
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private VentMostrarCotizaciones ventMostrarCotizaciones;
	private DelegadoCotizacion delegadoCotizacion = new DelegadoCotizacion();
	
	
	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentConsultarCotizaciones() {
		setTitle("Consulta de Cotizaciones");
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
		cbFiltro.setModel(new DefaultComboBoxModel(new String[] {"--ELIJA FILTRO--", "Consecutivo", "Identificaci\u00F3n", "Cliente", "Fecha", "TODO"}));
		cbFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elegirFiltro();
			}
		});
 
		cbFiltro.setBounds(129, 23, 123, 20);
		contentPane.add(cbFiltro);
		
		JButton btnMostrarConsulta = new JButton("Mostrar Consulta");
		btnMostrarConsulta.setForeground(new Color(0, 51, 0));
		btnMostrarConsulta.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMostrarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarConsulta();
			}
		});
		btnMostrarConsulta.setBounds(155, 68, 165, 23);
		contentPane.add(btnMostrarConsulta);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 55, 476, 2);
		contentPane.add(sp);
		
		cbDatos = new JComboBox();
		cbDatos.setBounds(262, 22, 224, 22);
		cbDatos.setVisible(false);
		contentPane.add(cbDatos);
		
		txtConsulta = new JTextField();
		txtConsulta.setBounds(262, 23, 224, 20);
		txtConsulta.setVisible(false);
		contentPane.add(txtConsulta);
		txtConsulta.setColumns(10);
	}
	
	//Metodo para elegir filtro para realizar la consulta
	private void elegirFiltro() {
		if("Consecutivo".equals(cbFiltro.getSelectedItem().toString())){
			cbDatos.setVisible(false);
			txtConsulta.setVisible(true);
			txtConsulta.setText("");
		}else{
			if("Identificación".equals(cbFiltro.getSelectedItem().toString())){
				cbDatos.setVisible(false);
				txtConsulta.setVisible(true);
				txtConsulta.setText("");
			}else{
				if("Cliente".equals(cbFiltro.getSelectedItem().toString())){
					cbDatos.setVisible(false);
					txtConsulta.setVisible(true);
					txtConsulta.setText("");
				}else{
					cbDatos.setVisible(false);
					txtConsulta.setVisible(false);
					txtConsulta.setText("1");
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
		if("Consecutivo".equals(cbFiltro.getSelectedItem().toString())){
			List<Cotizacion> cotizacionesPorConsecutivo = delegadoCotizacion.traerCotizacionPorCodigo(Integer.parseInt(txtConsulta.getText().toString()));
			ventMostrarCotizaciones = new VentMostrarCotizaciones(cotizacionesPorConsecutivo);
			ventMostrarCotizaciones.setVisible(true);
		}else{
			if("Identificación".equals(cbFiltro.getSelectedItem().toString())){
				List<Cotizacion> cotizacionesPorIdentificacion = delegadoCotizacion.traerCotizacionesPorIdentCliente(txtConsulta.getText().toString());
				ventMostrarCotizaciones = new VentMostrarCotizaciones(cotizacionesPorIdentificacion);
				ventMostrarCotizaciones.setVisible(true);
			}else{
				if("Cliente".equals(cbFiltro.getSelectedItem().toString())){
					List<Cotizacion> clientesPorNombre = delegadoCotizacion.traerCotizacionesPorNombreCliente(txtConsulta.getText().toString());
					ventMostrarCotizaciones = new VentMostrarCotizaciones(clientesPorNombre);
					ventMostrarCotizaciones.setVisible(true);
				}else{
					List<Cotizacion> todosCotizaciones = delegadoCotizacion.listarCotizaciones();
					ventMostrarCotizaciones = new VentMostrarCotizaciones(todosCotizaciones);
					ventMostrarCotizaciones.setVisible(true);
				}
			}
		}
		
	}
}
