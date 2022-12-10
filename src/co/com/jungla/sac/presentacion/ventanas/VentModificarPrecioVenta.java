package co.com.jungla.sac.presentacion.ventanas;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;


import co.com.jungla.sac.persistencia.entidades.ControlInventario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

import co.com.jungla.sac.negocio.delegados.DelegadoControlInventario;

public class VentModificarPrecioVenta extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField txtCostoPromedio;
	private JFormattedTextField txtUltimaCompra;
	private JFormattedTextField txtNuevoPrecio;
	DefaultComboBoxModel modeloUnidadMedida = new DefaultComboBoxModel();
	DefaultComboBoxModel modeloLineaArticulos = new DefaultComboBoxModel();
	private JTextField txtPrecioSugerido;
	private List<ControlInventario> articuloElegidoInventario;
	DelegadoControlInventario delegadoControlInventario= new DelegadoControlInventario();
	

	/**
	 * Create the frame.
	 */
	public VentModificarPrecioVenta(String codigoArticulo) {
		setTitle("Modificar Precio de Venta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 524, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCostoPromedio = new JLabel("Sobre el COSTO PROMEDIO");
		lblCostoPromedio.setBackground(new Color(153, 204, 153));
		lblCostoPromedio.setOpaque(true);
		lblCostoPromedio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCostoPromedio.setBounds(10, 91, 211, 22);
		contentPane.add(lblCostoPromedio);
		
		txtCostoPromedio = new JFormattedTextField();
		formatearAMoneda(txtCostoPromedio);
		txtCostoPromedio.setEditable(false);
		txtCostoPromedio.setBounds(223, 92, 144, 20);
		contentPane.add(txtCostoPromedio);
		txtCostoPromedio.setColumns(10);
		
		JLabel lblUltimaCompra = new JLabel("Sobre la ULTIMA COMPRA");
		lblUltimaCompra.setBackground(new Color(153, 204, 153));
		lblUltimaCompra.setOpaque(true);
		lblUltimaCompra.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUltimaCompra.setBounds(10, 114, 211, 22);
		contentPane.add(lblUltimaCompra);
		
		txtUltimaCompra = new JFormattedTextField();
		formatearAMoneda(txtUltimaCompra);
		txtUltimaCompra.setEditable(false);
		txtUltimaCompra.setBounds(223, 115, 144, 20);
		contentPane.add(txtUltimaCompra);
		txtUltimaCompra.setColumns(10);
		
		JLabel lblNuevoPrecio = new JLabel("Establecer Nuevo PRECIO DE VENTA");
		lblNuevoPrecio.setBackground(new Color(153, 204, 153));
		lblNuevoPrecio.setOpaque(true);
		lblNuevoPrecio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNuevoPrecio.setBounds(10, 137, 211, 22);
		contentPane.add(lblNuevoPrecio);
		
		txtNuevoPrecio = new JFormattedTextField();
		formatearAMoneda(txtNuevoPrecio);
		txtNuevoPrecio.setBounds(223, 138, 144, 20);
		contentPane.add(txtNuevoPrecio);
		txtNuevoPrecio.setColumns(10);
		
		//Boton para registrar el articulo 
		JButton btnRegistrar = new JButton("Modificar");
		btnRegistrar.setForeground(new Color(0, 51, 0));
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirDialogoConfirmacionRegistro();
			}
		});
		btnRegistrar.setBounds(151, 183, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(278, 183, 89, 23);
		contentPane.add(btnCerrar);
		
		JTextPane txpAtencion = new JTextPane();
		txpAtencion.setEditable(false);
		txpAtencion.setContentType("text/html");
		txpAtencion.setBackground(UIManager.getColor("Button.background"));
		txpAtencion.setText("<FONT FACE=\"Tahoma\" SIZE= 3><p align=\"justify\"><B>ATENCI\u00D3N:</b> El sistema le sugiere el PRECIO de VENTA para este art\u00EDculo basado en el FACTOR RENTABILIDAD.");
		txpAtencion.setBounds(10, 4, 494, 52);
		contentPane.add(txpAtencion);
		
		txtPrecioSugerido = new JTextField();
		txtPrecioSugerido.setForeground(new Color(0, 0, 0));
		txtPrecioSugerido.setEditable(false);
		txtPrecioSugerido.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtPrecioSugerido.setBackground(new Color(0, 153, 51));
		txtPrecioSugerido.setText("Precio Sugerido por el Sistema");
		txtPrecioSugerido.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrecioSugerido.setBounds(10, 60, 494, 20);
		contentPane.add(txtPrecioSugerido);
		txtPrecioSugerido.setColumns(10);
		
		JSeparator sp = new JSeparator();
		sp.setBounds(10, 170, 496, 2);
		contentPane.add(sp);
		
		JLabel lblUnidadMedida = new JLabel("");
		lblUnidadMedida.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUnidadMedida.setBackground(UIManager.getColor("Button.background"));
		lblUnidadMedida.setBounds(377, 141, 89, 14);
		contentPane.add(lblUnidadMedida);

		articuloElegidoInventario = delegadoControlInventario.traerContInventarioPorCodigoArticulo(Integer.parseInt(codigoArticulo));
		txtCostoPromedio.setValue(articuloElegidoInventario.get(0).getCostoPromedio()*articuloElegidoInventario.get(0).getFactorRentabilidad());
		txtUltimaCompra.setValue(articuloElegidoInventario.get(0).getCostoUltimaCompra()*articuloElegidoInventario.get(0).getFactorRentabilidad());
		lblUnidadMedida.setText(String.valueOf(articuloElegidoInventario.get(0).getArticulo().getUnidadMedida().getAbreviatura()));
		txtNuevoPrecio.setValue(articuloElegidoInventario.get(0).getPrecioTotalVenta());
		
	}
	//Metodo para modificar el precio de vento por uno nuevo
	private void modificarPrecioVenta() {
		ControlInventario articuloAModificarPrecio;
		articuloAModificarPrecio = articuloElegidoInventario.get(0);
		
		articuloAModificarPrecio.setPrecioTotalVenta(Double.parseDouble(txtNuevoPrecio.getText()));
		delegadoControlInventario.actualizarControlInventario(articuloAModificarPrecio);
		
	}
	
	//Metodo para abrir ventanta de confirmacion de registro
	private void abrirDialogoConfirmacionRegistro(){
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de modificar la información actual ?", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			modificarPrecioVenta();
			dispose();
		}else{
		
		}
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
}
