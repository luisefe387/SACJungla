package co.com.jungla.sac.presentacion.ventanas;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;


import co.com.jungla.sac.persistencia.entidades.Articulo;
import co.com.jungla.sac.persistencia.entidades.ControlInventario;
import co.com.jungla.sac.persistencia.entidades.LineaArticulos;
import co.com.jungla.sac.persistencia.entidades.UnidadMedida;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import co.com.jungla.sac.negocio.delegados.DelegadoArticulo;
import co.com.jungla.sac.negocio.delegados.DelegadoControlInventario;
import co.com.jungla.sac.negocio.delegados.DelegadoLineaArticulos;
import co.com.jungla.sac.negocio.delegados.DelegadoUnidadMedida;

import java.awt.SystemColor;
import java.awt.Toolkit;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro del articulo incluyendo tambien su ingreso al inventario
 * @author Luis Fernando Pedroza T.
 * @version: 21/09/2016
 */
public class VentRegistrarArticulo extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtPresentacion;
	private JTextField txtDescripcion;
	private JTextField txtReferencia;
	private JTextField txtRV;
	private JTextField txtRC;
	private JFormattedTextField txtCostoTotal;
	private JFormattedTextField txtPrecioVenta;
	public JComboBox cbUnidadMedida;
	public JComboBox cbLineaArticulos;
	private DefaultComboBoxModel modeloUnidadMedida = new DefaultComboBoxModel();
	private DefaultComboBoxModel modeloLineaArticulos = new DefaultComboBoxModel();
	
	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentRegistrarArticulo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarArticulo.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Nuevo Articulo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 524, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLineaArticulos = new JLabel("Linea de Articulos *");
		lblLineaArticulos.setBackground(new Color(153, 204, 153));
		lblLineaArticulos.setOpaque(true);
		lblLineaArticulos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLineaArticulos.setBounds(10, 21, 117, 22);
		contentPane.add(lblLineaArticulos);
		
		cbLineaArticulos = new JComboBox();
		//Evento en la que permite el registro de una linea cuando esta no existe en el combo box
		cbLineaArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearLineaArticulos();
			}
		});
 
		cbLineaArticulos.setBounds(129, 22, 375, 20);
		contentPane.add(cbLineaArticulos);
		
		JLabel lblNombre = new JLabel("Nombre Art\u00EDculo *");
		lblNombre.setBackground(new Color(153, 204, 153));
		lblNombre.setOpaque(true);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(10, 103, 117, 22);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(129, 104, 375, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblUnidadMedida = new JLabel("Unidad de Medida *");
		lblUnidadMedida.setBackground(new Color(153, 204, 153));
		lblUnidadMedida.setOpaque(true);
		lblUnidadMedida.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUnidadMedida.setBounds(10, 126, 117, 22);
		contentPane.add(lblUnidadMedida);
		
		cbUnidadMedida = new JComboBox();
		//Evento en la que permite el registro de una unidad de medida cuando esta no existe en el combo box
		cbUnidadMedida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearUnidadEmpaque();
			}
		});
		cbUnidadMedida.setBounds(129, 127, 375, 20);
		contentPane.add(cbUnidadMedida);
		
		JLabel lblCostoTotal = new JLabel("Costo Total *");
		lblCostoTotal.setBackground(new Color(153, 204, 153));
		lblCostoTotal.setOpaque(true);
		lblCostoTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCostoTotal.setBounds(10, 203, 117, 22);
		contentPane.add(lblCostoTotal);
		
		txtCostoTotal = new JFormattedTextField();
		formatearAMoneda(txtCostoTotal);
		txtCostoTotal.setBounds(129, 204, 190, 20);
		contentPane.add(txtCostoTotal);
		
		JLabel lblPresentacion = new JLabel("Presentaci\u00F3n");
		lblPresentacion.setBackground(new Color(153, 204, 153));
		lblPresentacion.setOpaque(true);
		lblPresentacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPresentacion.setBounds(10, 368, 117, 22);
		contentPane.add(lblPresentacion);
		
		txtPresentacion = new JTextField();
		txtPresentacion.setBounds(129, 369, 375, 20);
		contentPane.add(txtPresentacion);
		txtPresentacion.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setBackground(new Color(153, 204, 153));
		lblDescripcion.setOpaque(true);
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescripcion.setBounds(10, 391, 117, 22);
		contentPane.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(129, 392, 375, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblReferencia = new JLabel("Referencia");
		lblReferencia.setBackground(new Color(153, 204, 153));
		lblReferencia.setOpaque(true);
		lblReferencia.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblReferencia.setBounds(10, 414, 117, 22);
		contentPane.add(lblReferencia);
		
		txtReferencia = new JTextField();
		txtReferencia.setBounds(129, 415, 241, 20);
		contentPane.add(txtReferencia);
		txtReferencia.setColumns(10);
		
		//Boton para registrar el articulo 
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setForeground(new Color(0, 51, 0));
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarRegitro();
			}
		});
		btnRegistrar.setBounds(152, 457, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(279, 457, 89, 23);
		contentPane.add(btnCerrar);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 331, 494, 2);
		contentPane.add(sp);
		
		JLabel lblDatos = new JLabel("Datos Opcionales");
		lblDatos.setOpaque(true);
		lblDatos.setForeground(new Color(0, 0, 255));
		lblDatos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDatos.setBounds(10, 344, 158, 14);
		contentPane.add(lblDatos);
		
		JTextPane txpAtencion = new JTextPane();
		txpAtencion.setBackground(UIManager.getColor("Button.background"));
		txpAtencion.setText("ATENCI\u00D3N: El ARTICULO NO puede cambiarse de LINEA una vez se ha registrado. De ser necesario debe deshabilitarse el art\u00EDculo y crearse en el nuevo grupo.");
		txpAtencion.setBounds(129, 45, 375, 48);
		contentPane.add(txpAtencion);
		
		JTextPane txpInformacion = new JTextPane();
		txpInformacion.setBackground(UIManager.getColor("Button.background"));
		txpInformacion.setText("Esta informaci\u00F3n es s\u00F3lo para establecer los PRECIOS de VENTA y/o Rentabilidades. El COSTO real se establecer\u00E1 al momento de ingresar las COMPRAS y/o Entradas.");
		txpInformacion.setBounds(10, 166, 496, 34);
		contentPane.add(txpInformacion);
		
		JSeparator sp1 = new JSeparator();
		sp1.setBackground(new Color(0, 51, 0));
		sp1.setBounds(10, 158, 496, 2);
		contentPane.add(sp1);
		
		JPanel pnPrecioVenta = new JPanel();
		pnPrecioVenta.setLayout(null);
		pnPrecioVenta.setBackground(new Color(0, 51, 0));
		pnPrecioVenta.setBounds(71, 282, 152, 38);
		contentPane.add(pnPrecioVenta);
		
		JLabel lblPrecioVenta = new JLabel("Precio Venta");
		lblPrecioVenta.setForeground(Color.WHITE);
		lblPrecioVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrecioVenta.setBackground(SystemColor.desktop);
		lblPrecioVenta.setBounds(40, 0, 90, 14);
		pnPrecioVenta.add(lblPrecioVenta);
		
		txtPrecioVenta = new JFormattedTextField();
		txtPrecioVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularRentabilidadCostoYVenta();
			}
		});
		txtPrecioVenta.setColumns(10);
		formatearAMoneda(txtPrecioVenta);
		txtPrecioVenta.setBounds(0, 18, 152, 20);
		pnPrecioVenta.add(txtPrecioVenta);
		
		JSeparator sp2 = new JSeparator();
		sp2.setBackground(new Color(0, 51, 0));
		sp2.setBounds(10, 236, 496, 2);
		contentPane.add(sp2);
		
		JTextPane txpBasado = new JTextPane();
		txpBasado.setBackground(UIManager.getColor("Button.background"));
		txpBasado.setText("Basado en el COSTO TOTAL puede ingresar cualquiera de los valores para ajustar el PRECIO DE VENTA y asi encontrar una rentabilidad apropiada.");
		txpBasado.setBounds(10, 244, 496, 34);
		contentPane.add(txpBasado);
		
		JPanel pnRV = new JPanel();
		pnRV.setLayout(null);
		pnRV.setBackground(new Color(0, 51, 0));
		pnRV.setBounds(233, 282, 104, 38);
		contentPane.add(pnRV);
		
		JLabel lblRV = new JLabel("% R / V");
		lblRV.setForeground(Color.WHITE);
		lblRV.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRV.setBackground(SystemColor.desktop);
		lblRV.setBounds(30, 0, 60, 14);
		pnRV.add(lblRV);
		
		txtRV = new JTextField();
		txtRV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularPrecioVentaAPartirRV();
			}
		});
		txtRV.setText("0");
		txtRV.setColumns(10);
		txtRV.setBounds(0, 18, 86, 20);
		pnRV.add(txtRV);
		
		JLabel lblPorcentaje1 = new JLabel("%");
		lblPorcentaje1.setForeground(new Color(255, 255, 255));
		lblPorcentaje1.setBounds(87, 17, 17, 22);
		pnRV.add(lblPorcentaje1);
		
		JPanel pnRC = new JPanel();
		pnRC.setLayout(null);
		pnRC.setBackground(new Color(0, 51, 0));
		pnRC.setBounds(347, 282, 104, 38);
		contentPane.add(pnRC);
		
		JLabel lblRC = new JLabel("% R / C");
		lblRC.setForeground(Color.WHITE);
		lblRC.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRC.setBackground(SystemColor.desktop);
		lblRC.setBounds(30, 0, 60, 14);
		pnRC.add(lblRC);
		
		txtRC = new JTextField();
		txtRC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularPrecioVentaAPartirRC();
			}
		});
		txtRC.setText("0");
		txtRC.setColumns(10);
		txtRC.setBounds(0, 18, 86, 20);
		pnRC.add(txtRC);
		
		JLabel lblPorcentaje = new JLabel("%");
		lblPorcentaje.setForeground(new Color(255, 255, 255));
		lblPorcentaje.setBounds(87, 17, 17, 22);
		pnRC.add(lblPorcentaje);
		
		//al iniciarse la ventana automaticamente cargue las lineas de articulos y la unidades de medida
		listarLineaArticulos();
		listarUnidadMedida();
	}
	
	//Metodo que permite listar las unidades de medida y desplegarlos en un combo box
	public void listarUnidadMedida() {
		
		DelegadoUnidadMedida delegadoUnidadMedida = new DelegadoUnidadMedida();
		List<UnidadMedida> unidadesMedida = delegadoUnidadMedida.listarUnidadMedida();
		modeloUnidadMedida.addElement("--ELIJA UNIDAD DE EMPAQUE--");
		cbUnidadMedida.setModel(modeloUnidadMedida);
		
		for(UnidadMedida unidadMedida : unidadesMedida){
			modeloUnidadMedida.addElement(new UnidadMedida (unidadMedida.getNombre(), unidadMedida.getCodigo()));
			cbUnidadMedida.setModel(modeloUnidadMedida);
		}
		
		modeloUnidadMedida.addElement("--NUEVA UNIDAD DE EMPAQUE--");
		cbUnidadMedida.setModel(modeloUnidadMedida);
	}
	
	//Metodo que permite listar las lineas de articulos y desplegarlos en un combo box
	public void listarLineaArticulos() {
		DelegadoLineaArticulos delegadoLineaArticulos = new DelegadoLineaArticulos();
		List<LineaArticulos> lineaArticulos = delegadoLineaArticulos.listarLineaArticulos();
		modeloLineaArticulos.addElement("--ELIJA LINEA--");
		cbLineaArticulos.setModel(modeloLineaArticulos);
		
		for(LineaArticulos lineaArticulos1 : lineaArticulos){
			modeloLineaArticulos.addElement(new LineaArticulos(lineaArticulos1.getNombreL(), lineaArticulos1.getCodigo()));
			cbLineaArticulos.setModel(modeloLineaArticulos);
		}
		
		modeloLineaArticulos.addElement("--NUEVA LINEA--");
		cbLineaArticulos.setModel(modeloLineaArticulos);
	}
	
	//Metodo para crear linea de articulo en caso de que no aparezca en el combo box
	private void crearLineaArticulos() {
		if(cbLineaArticulos.getSelectedItem().equals("--NUEVA LINEA--")){
			VentRegistrarLineaArticulos ventRegistrarLineaArticulos = new VentRegistrarLineaArticulos(/*VentRegistrarArticulo.this, true*/);
			cbLineaArticulos.removeAllItems();
			ventRegistrarLineaArticulos.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    listarLineaArticulos();  
                }
            });
			ventRegistrarLineaArticulos.setVisible(true);
		}
	}
	
	//Metodo para crear unidad de empaque en caso de que no aparezca en el combo box
	private void crearUnidadEmpaque() {
		if(cbUnidadMedida.getSelectedItem().equals("--NUEVA UNIDAD DE EMPAQUE--")){
			VentRegistrarUnidadMedida ventRegistrarUnidadMedida = new VentRegistrarUnidadMedida(/*VentRegistrarArticulo.this, true*/);
			cbUnidadMedida.removeAllItems();
			ventRegistrarUnidadMedida.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                	listarUnidadMedida();
                }
            });
			ventRegistrarUnidadMedida.setVisible(true);
		}
	}
	//Metodo que valida los datos ingresados para su posterior registro 
	private void validarRegitro() {
		if(cbLineaArticulos.getSelectedItem().equals("--ELIJA LINEA--")){
			JOptionPane.showMessageDialog(null, "Debe elegir una linea de articulo");
		}else{
			if(txtNombre.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de articulo");
			}else{
				if(cbUnidadMedida.getSelectedItem().equals("--ELIJA UNIDAD DE EMPAQUE--")){
					JOptionPane.showMessageDialog(null, "Debe elegir una unidad de medida para el articulo");
				}else{
					if(txtCostoTotal.getValue().equals(0)){
						JOptionPane.showMessageDialog(null, "Debe ingresar el costo total del articulo");
					}else{
						try
						{
							abrirDialogoConfirmacionRegistro();
						}catch(NumberFormatException nf){
							nf.getMessage();
							JOptionPane.showMessageDialog(null, "El costo total debe ser un valor numerico");
						}
					}
				}
			}
		}
	}
	//Limpiar los datos escritos 
	private void limpiarDatos() {
		cbLineaArticulos.setSelectedIndex(0);
		txtNombre.setText("");
		cbUnidadMedida.setSelectedIndex(0);
		txtCostoTotal.setValue(0);
		txtDescripcion.setText("");
		txtPresentacion.setText("");
		txtReferencia.setText("");
		txtRC.setText("0");
		txtRV.setText("0");
		txtPrecioVenta.setValue(0);
	}
	//Metodo que permite el ingreso del articulo a la base de datos luego de haberse validado
	private void registrarArticulo() {
		DelegadoArticulo delegadoArticulo = new DelegadoArticulo();
		Articulo articulo = new Articulo();
		LineaArticulos lineaArticulos = new LineaArticulos();
		UnidadMedida unidadMedida = new UnidadMedida();
		
		articulo.setDescripcion(txtDescripcion.getText());
		articulo.setEstado("Activo");
		lineaArticulos = (LineaArticulos)cbLineaArticulos.getSelectedItem();
		lineaArticulos.setCodigo(lineaArticulos.getCodigo());
		articulo.setLineaArticulos(lineaArticulos);
		articulo.setNombre(txtNombre.getText());
		articulo.setPresentacion(txtPresentacion.getText());
		articulo.setReferencia(txtReferencia.getText());
		unidadMedida = (UnidadMedida)cbUnidadMedida.getSelectedItem();
		unidadMedida.setCodigo(unidadMedida.getCodigo());
		articulo.setUnidadMedida(unidadMedida);
		
		delegadoArticulo.insertarArticulo(articulo);
		
		JOptionPane.showMessageDialog(null, "Se registro exitosamente");
		
		registrarAControlInventario();
	}
	//Metodo para registrar el articulo al control del inventario
	private void registrarAControlInventario() {
		DelegadoControlInventario delegadoControlInventario = new DelegadoControlInventario();
		DelegadoArticulo delegadoArticulo = new DelegadoArticulo();
		ControlInventario controlInventario = new ControlInventario();
		Articulo articulo = new Articulo();
		List<Articulo> codigoArticulo;
		
		codigoArticulo= delegadoArticulo.traerUltimoCodigoArticulo();
		
		articulo.setCodigo(codigoArticulo.get(0).getCodigo());
		controlInventario.setArticulo(articulo);
		controlInventario.setCantAlerta(0);
		controlInventario.setCantExistencia(0);
		controlInventario.setCostoPromedio(Double.parseDouble(txtCostoTotal.getValue().toString()));
		controlInventario.setFactorRentabilidad(calcularFactorRentabilidad());
		controlInventario.setPrecioTotalVenta(Double.parseDouble(txtPrecioVenta.getValue().toString()));
		controlInventario.setRentabilidadCostoPromedio(Float.parseFloat(txtRC.getText()));
		controlInventario.setRentabilidadVenta(Float.parseFloat(txtRV.getText()));
		controlInventario.setTotalCostoInventario(0);
		controlInventario.setProveedorUltimaCompra("");
		controlInventario.setCostoUltimaCompra(Double.parseDouble(txtCostoTotal.getValue().toString()));
		controlInventario.setUrlFoto("");
		
		delegadoControlInventario.insertarControlInventario(controlInventario);
		
	}
	//Metodo para formatear a moneda peso permitiendo la edicion y visualizacion
	private void formatearAMoneda(JFormattedTextField campoTexto) {
		
		campoTexto.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
		campoTexto.setValue(0);
		NumberFormat visNf = NumberFormat.getCurrencyInstance(); 
		NumberFormat ediNf = NumberFormat.getNumberInstance(new Locale("es","CO"));
		ediNf.setGroupingUsed(false);
		NumberFormatter visNt = new NumberFormatter(visNf);
		NumberFormatter ediNt = new NumberFormatter(ediNf);
		DefaultFormatterFactory currFactory = new DefaultFormatterFactory(visNt, visNt, ediNt);
		ediNt.setAllowsInvalid(true);
		
		campoTexto.setFormatterFactory(currFactory);
	}
	//Metodo para abrir ventanta de confirmacion de registro
	private void abrirDialogoConfirmacionRegistro(){
		String nl = System.getProperty("line.separator");
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de registrar este articulo en el grupo de "+cbLineaArticulos.getSelectedItem().toString()+nl+nl+"Atencion: Toda la información del artículo es modificable excepto la LINEA a la que pertenece", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarArticulo();
			limpiarDatos();
		}else{
		
		}
	}
	//Metodo para calcular los porcentajes de rentabilidades de costo y venta basandose en el costo total y el precio de venta
	private void calcularRentabilidadCostoYVenta() {
		if(Double.parseDouble(txtCostoTotal.getValue().toString())==0){
			JOptionPane.showMessageDialog(null, "Debe digitar el costo total para realizar los calculos");
			txtPrecioVenta.setValue(0);
		}else{
			float rv = (float) (((Double.parseDouble(txtPrecioVenta.getValue().toString())-(Double.parseDouble(txtCostoTotal.getValue().toString())))/Double.parseDouble(txtPrecioVenta.getValue().toString()))*100);
			float rc = (float) (((Double.parseDouble(txtPrecioVenta.getValue().toString())-Double.parseDouble(txtCostoTotal.getValue().toString()))/Double.parseDouble(txtCostoTotal.getValue().toString()))*100);
			txtRV.setText(String.valueOf(rv));
			txtRC.setText(String.valueOf(rc));
		}
	}
	//Metodo para calcular el precio de venta a partir del porcentaje de rentabilidad venta y el costo total
	private void calcularPrecioVentaAPartirRV(){
		if(Double.parseDouble(txtCostoTotal.getValue().toString())==0){
			JOptionPane.showMessageDialog(null, "Debe digitar el costo total para realizar los calculos");
			txtRV.setText("0");
		}else{
			try{
				double precioVenta = Double.parseDouble(txtCostoTotal.getValue().toString())/(1-(Float.parseFloat(txtRV.getText())/100));
				float rc = (float) (((precioVenta-Double.parseDouble(txtCostoTotal.getValue().toString()))/Double.parseDouble(txtCostoTotal.getValue().toString()))*100);
				txtPrecioVenta.setValue(precioVenta);
				txtRC.setText(String.valueOf(rc));
			}catch(NumberFormatException nb){
				JOptionPane.showMessageDialog(null, "Formato de numero no valido");
				nb.getMessage();
			}
		}
		
	}
	//Metodo para calcular el precio de venta a partir del porcentaje de rentabilidad costo y el costo total
	private void calcularPrecioVentaAPartirRC(){
		if(Double.parseDouble(txtCostoTotal.getValue().toString())==0){
			JOptionPane.showMessageDialog(null, "Debe digitar el costo total para realizar los calculos");
			txtRC.setText("0");
		}else{
			try{
				double precioVenta = Double.parseDouble(txtCostoTotal.getValue().toString())*((Float.parseFloat(txtRV.getText())/100)+1);
				float rv = (float) (((precioVenta-Double.parseDouble(txtCostoTotal.getValue().toString()))/precioVenta)*100);
				txtPrecioVenta.setValue(precioVenta);
				txtRV.setText(String.valueOf(rv));
			}catch(NumberFormatException nb){
				JOptionPane.showMessageDialog(null, "Formato de numero no valido");
				nb.getMessage();
			}
		}
	}
	//Metodo para calcular el factor de rentabilidad basado en el precio de venta y costo total
	private float calcularFactorRentabilidad(){
		float factorRent = (float) (Double.parseDouble(txtPrecioVenta.getValue().toString())/Double.parseDouble(txtCostoTotal.getValue().toString()));
		return factorRent;
	}
}
