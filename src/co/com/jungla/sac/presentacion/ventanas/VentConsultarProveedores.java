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

import co.com.jungla.sac.persistencia.entidades.Departamento;
import co.com.jungla.sac.persistencia.entidades.LineaArticulos;
import co.com.jungla.sac.persistencia.entidades.Municipio;
import co.com.jungla.sac.persistencia.entidades.Proveedor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.LineBorder;

import co.com.jungla.sac.negocio.delegados.DelegadoDepartamento;
import co.com.jungla.sac.negocio.delegados.DelegadoMunicipio;
import co.com.jungla.sac.negocio.delegados.DelegadoProveedor;

public class VentConsultarProveedores extends JFrame {

	private JPanel contentPane;
	DefaultComboBoxModel modeloDepartamentos = new DefaultComboBoxModel();
	DefaultComboBoxModel modeloMunicipios = new DefaultComboBoxModel();
	private JTextField txtConsulta;
	public JComboBox cbFiltro;
	private JComboBox cbDatos;
	private JComboBox cbMunicipios;
	private VentMostrarProveedores ventMostrarProveedores;
	private DelegadoProveedor delegadoProveedor = new DelegadoProveedor();
	
	
	/**
	 * Create the frame.
	 */
	public VentConsultarProveedores() {
		setTitle("Consulta de Proveedores");
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
		cbFiltro.setModel(new DefaultComboBoxModel(new String[] {"--ELIJA FILTRO--", "Identificaci\u00F3n", "Nombre", "Departamento", "Ciudad", "Deshabilitados", "TODO"}));
		//Evento en la que permite el registro de una linea cuando esta no existe en el combo box
		cbFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elegirFiltro();
			}
		});
 
		cbFiltro.setBounds(129, 23, 123, 20);
		contentPane.add(cbFiltro);
		
		//Boton para registrar el articulo 
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
		
		cbMunicipios = new JComboBox();
		cbMunicipios.setBounds(262, 22, 224, 22);
		cbMunicipios.setVisible(false);
		contentPane.add(cbMunicipios);
		
		txtConsulta = new JTextField();
		txtConsulta.setBounds(262, 23, 224, 20);
		txtConsulta.setVisible(false);
		contentPane.add(txtConsulta);
		txtConsulta.setColumns(10);
	}
	
	//Metodo para elegir filtro para realizar la consulta
	private void elegirFiltro() {
		if("Identificación".equals(cbFiltro.getSelectedItem().toString())){
			cbDatos.setVisible(false);
			txtConsulta.setVisible(true);
			txtConsulta.setText("");
		}else{
			if("Nombre".equals(cbFiltro.getSelectedItem().toString())){
				cbDatos.setVisible(false);
				txtConsulta.setVisible(true);
				txtConsulta.setText("");
			}else{
				if("Departamento".equals(cbFiltro.getSelectedItem().toString())){
					DelegadoDepartamento delegadoDepartamento = new DelegadoDepartamento();
					cbDatos.setVisible(true);
					txtConsulta.setVisible(false);
					txtConsulta.setText("1");
					cbDatos.removeAllItems();
					List<Departamento> departamentos = delegadoDepartamento.listarDepartamentos();
					
					for(Departamento departamentosAElegir : departamentos){
						modeloDepartamentos.addElement(new LineaArticulos(departamentosAElegir.getNombre(), departamentosAElegir.getIdDepartamento()));
						cbDatos.setModel(modeloDepartamentos);
					}
				}else{
					if("Ciudad".equals(cbFiltro.getSelectedItem().toString())){
						DelegadoMunicipio delegadoMunicipio = new DelegadoMunicipio();
						cbDatos.setVisible(true);
						cbDatos.removeAllItems();
						txtConsulta.setVisible(false);
						txtConsulta.setText("1");
						List<Municipio> municipios = delegadoMunicipio.listarMunicipios();
						
						for(Municipio municipiosAElegir : municipios){
							modeloMunicipios.addElement(new LineaArticulos(municipiosAElegir.getNombre(), municipiosAElegir.getIdMunicipio()));
							cbDatos.setModel(modeloMunicipios);
						}
					}else{
						if("Deshabilitados".equals(cbFiltro.getSelectedItem().toString())){
							cbDatos.setVisible(false);
							txtConsulta.setVisible(false);
							txtConsulta.setText("1");
						}else{
							cbDatos.setVisible(false);
							txtConsulta.setVisible(false);
							txtConsulta.setText("1");
						}
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
		if("Identificación".equals(cbFiltro.getSelectedItem().toString())){
			List<Proveedor> proveedoresPorIdentificacion = delegadoProveedor.traerProveedorPorIdentificacion(txtConsulta.getText().toString());
			ventMostrarProveedores = new VentMostrarProveedores(proveedoresPorIdentificacion);
			ventMostrarProveedores.setVisible(true);
		}else{
			if("Nombre".equals(cbFiltro.getSelectedItem().toString())){
				List<Proveedor> proveedoresPorNombre = delegadoProveedor.traerProveedorPorNombre(txtConsulta.getText().toString());
				ventMostrarProveedores = new VentMostrarProveedores(proveedoresPorNombre);
				ventMostrarProveedores.setVisible(true);
			}else{
				if("Departamento".equals(cbFiltro.getSelectedItem().toString())){
					List<Proveedor> proveedoresPorDepartamento = delegadoProveedor.traerProveedorPorDepartamento(cbDatos.getSelectedItem().toString());
					ventMostrarProveedores = new VentMostrarProveedores(proveedoresPorDepartamento);
					ventMostrarProveedores.setVisible(true);
				}else{
					if("Ciudad".equals(cbFiltro.getSelectedItem().toString())){
						List<Proveedor> proveedoresPorCiudad = delegadoProveedor.traerProveedorPorCiudad(cbDatos.getSelectedItem().toString());
						ventMostrarProveedores = new VentMostrarProveedores(proveedoresPorCiudad);
						ventMostrarProveedores.setVisible(true);
					}else{
						if("Deshabilitados".equals(cbFiltro.getSelectedItem().toString())){
							List<Proveedor> proveedoresDeshabilitados = delegadoProveedor.traerProveedorDeshabilitado("Inactivo");
							ventMostrarProveedores = new VentMostrarProveedores(proveedoresDeshabilitados);
							ventMostrarProveedores.setVisible(true);
						}else{
							List<Proveedor> todosProveedores = delegadoProveedor.listarProveedor();
							ventMostrarProveedores = new VentMostrarProveedores(todosProveedores);
							ventMostrarProveedores.setVisible(true);
						}
					}
				}
			}
		}
		
	}
}
