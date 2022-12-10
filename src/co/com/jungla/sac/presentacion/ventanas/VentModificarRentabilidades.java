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


import co.com.jungla.sac.persistencia.entidades.ControlInventario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

import co.com.jungla.sac.negocio.delegados.DelegadoControlInventario;

public class VentModificarRentabilidades extends JFrame {

	private JPanel contentPane;
	private JTextField txtRC;
	private JTextField txtRV;
	private JTextField txtFactorRent;
	DefaultComboBoxModel modeloUnidadMedida = new DefaultComboBoxModel();
	DefaultComboBoxModel modeloLineaArticulos = new DefaultComboBoxModel();
	public JComboBox cbAjustePrecio;
	public JComboBox cbAplicarCambios;
	private JTextField txtAcciones;
	private List<ControlInventario> articuloElegidoInventario;
	private List<ControlInventario> lineaInventario;
	private List<ControlInventario> todoElInventario;
	DelegadoControlInventario delegadoControlInventario= new DelegadoControlInventario();
	

	/**
	 * Create the frame.
	 */
	public VentModificarRentabilidades(String codigoArticulo) {
		setTitle("Modificar Factor de Rentabilidad");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 524, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAplicarCambios = new JLabel("Quiero aplicar los cambios a");
		lblAplicarCambios.setBackground(new Color(153, 204, 153));
		lblAplicarCambios.setOpaque(true);
		lblAplicarCambios.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAplicarCambios.setBounds(10, 253, 184, 22);
		contentPane.add(lblAplicarCambios);
		
		cbAplicarCambios = new JComboBox();
		cbAplicarCambios.setModel(new DefaultComboBoxModel(new String[] {"Solo a este ARTICULO", "A toda la LINEA", "A todo el INVENTARIO"}));
		cbAplicarCambios.setBounds(196, 254, 308, 20);
		contentPane.add(cbAplicarCambios);
		
		JLabel lblAjustePrecio = new JLabel("Quiero aplicar el");
		lblAjustePrecio.setBackground(new Color(153, 204, 153));
		lblAjustePrecio.setOpaque(true);
		lblAjustePrecio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAjustePrecio.setBounds(10, 276, 184, 22);
		contentPane.add(lblAjustePrecio);
		
		cbAjustePrecio = new JComboBox();
		cbAjustePrecio.setModel(new DefaultComboBoxModel(new String[] {"No ajuste del PRECIO", "Ajuste del PRECIO automaticamente"}));
		cbAjustePrecio.setBounds(196, 277, 308, 20);
		contentPane.add(cbAjustePrecio);
		
		JLabel lblRC = new JLabel("RENTABILIDAD / COSTO PROM");
		lblRC.setBackground(new Color(153, 204, 153));
		lblRC.setOpaque(true);
		lblRC.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRC.setBounds(10, 143, 184, 22);
		contentPane.add(lblRC);
		
		txtRC = new JTextField();
		txtRC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularRvyFactRentPartirRc();
			}
		});
		txtRC.setBounds(196, 144, 109, 20);
		contentPane.add(txtRC);
		txtRC.setColumns(10);
		
		JLabel lblRV = new JLabel("RENTABILIDAD / PRECIO VENTA");
		lblRV.setBackground(new Color(153, 204, 153));
		lblRV.setOpaque(true);
		lblRV.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRV.setBounds(10, 166, 184, 22);
		contentPane.add(lblRV);
		
		txtRV = new JTextField();
		txtRV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularRcyFactRentPartirRv();
			}
		});
		txtRV.setBounds(196, 167, 109, 20);
		contentPane.add(txtRV);
		txtRV.setColumns(10);
		
		JLabel lblFactorRent = new JLabel("FACTOR DE RENTABILIDAD");
		lblFactorRent.setBackground(new Color(153, 204, 153));
		lblFactorRent.setOpaque(true);
		lblFactorRent.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFactorRent.setBounds(10, 189, 184, 22);
		contentPane.add(lblFactorRent);
		
		txtFactorRent = new JTextField();
		txtFactorRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularRvyRCAPartirFactRent();
				
			}
		});
		txtFactorRent.setBounds(196, 190, 109, 20);
		contentPane.add(txtFactorRent);
		txtFactorRent.setColumns(10);
		
		//Boton para registrar el articulo 
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setForeground(new Color(0, 51, 0));
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirDialogoConfirmacionRegistro();
			}
		});
		btnModificar.setBounds(151, 322, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(278, 322, 89, 23);
		contentPane.add(btnCerrar);
		
		JTextPane txpAtencion = new JTextPane();
		txpAtencion.setEditable(false);
		txpAtencion.setContentType("text/html");
		txpAtencion.setBackground(UIManager.getColor("Button.background"));
		txpAtencion.setText("<FONT FACE=\"Tahoma\" SIZE= 3><p align=\"justify\"><B>ATENCI\u00D3N:</b> Este FACTOR se basa en el COSTO PROMEDIO y es muy \u00FAtil para mantener el control de los precios de sus art\u00EDculos en su inventario. Ademas como valor referencia en esta utilidad. Los art\u00EDculos que est\u00E9n por debajo de este factor saldr\u00E1n remarcados en <FONT COLOR=\"red\">fondo rojo</FONT> y con esa alerta visual usted puede tomar medidas correctivas.<br><br>\r\n\r\nPara modificar el FACTOR de RENTABILIDAD puede usar cualquiera de las 3 opciones siguientes seg\u00FAn su conocimiento.</p></FONT>");
		txpAtencion.setBounds(10, 4, 494, 134);
		contentPane.add(txpAtencion);
		
		txtAcciones = new JTextField();
		txtAcciones.setForeground(new Color(0, 0, 0));
		txtAcciones.setEditable(false);
		txtAcciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtAcciones.setBackground(new Color(0, 153, 51));
		txtAcciones.setText("ACCIONES A APLICAR");
		txtAcciones.setHorizontalAlignment(SwingConstants.CENTER);
		txtAcciones.setBounds(10, 222, 494, 20);
		contentPane.add(txtAcciones);
		txtAcciones.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 309, 496, 2);
		contentPane.add(separator);
		
		JLabel lblDecimales = new JLabel("(Puede usar decimales con [.]) ");
		lblDecimales.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDecimales.setBounds(331, 147, 173, 14);
		contentPane.add(lblDecimales);
		
		JLabel lblPorcentaje = new JLabel("%");
		lblPorcentaje.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPorcentaje.setForeground(new Color(0, 0, 0));
		lblPorcentaje.setBounds(308, 143, 17, 22);
		contentPane.add(lblPorcentaje);
		
		JLabel lblPorcentaje1 = new JLabel("%");
		lblPorcentaje1.setForeground(Color.BLACK);
		lblPorcentaje1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPorcentaje1.setBounds(308, 166, 17, 22);
		contentPane.add(lblPorcentaje1);
		
		JLabel lblDecimales1 = new JLabel("(Puede usar decimales con [.]) ");
		lblDecimales1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDecimales1.setBounds(331, 170, 173, 14);
		contentPane.add(lblDecimales1);
		
		JLabel lblDecimales2 = new JLabel("(Puede usar decimales con [.]) ");
		lblDecimales2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDecimales2.setBounds(315, 193, 173, 14);
		contentPane.add(lblDecimales2);
		
		articuloElegidoInventario = delegadoControlInventario.traerContInventarioPorCodigoArticulo(Integer.parseInt(codigoArticulo));
		txtFactorRent.setText(String.valueOf(articuloElegidoInventario.get(0).getFactorRentabilidad()));
		txtRC.setText(String.valueOf(articuloElegidoInventario.get(0).getRentabilidadCostoPromedio()));
		txtRV.setText(String.valueOf(articuloElegidoInventario.get(0).getRentabilidadVenta()));
		
		calcularRvyRCAPartirFactRent();
	}
	//Metodo para modificar las rentabilidades como el factor de rentabilidad, rentabiliadad/costo promedio y rentabilidad/precio venta
	private void modificarRentabilidades(){
		DelegadoControlInventario delegadoControlInventario = new DelegadoControlInventario();
		ControlInventario articuloAModificarRentabilidades;
		articuloAModificarRentabilidades = articuloElegidoInventario.get(0);
		
		lineaInventario = delegadoControlInventario.traerRegistrosInventarioPorG(articuloAModificarRentabilidades.getArticulo().getLineaArticulos().getNombreL());
		todoElInventario = delegadoControlInventario.listarControlInventario();
		
		if(cbAplicarCambios.getSelectedItem().equals("Solo a este ARTICULO")){
			if(cbAjustePrecio.getSelectedItem().equals("No ajuste del PRECIO")){
				articuloAModificarRentabilidades.setFactorRentabilidad(Float.parseFloat(txtFactorRent.getText()));
				delegadoControlInventario.actualizarControlInventario(articuloAModificarRentabilidades);
			}else{
				articuloAModificarRentabilidades.setFactorRentabilidad(Float.parseFloat(txtFactorRent.getText()));
				articuloAModificarRentabilidades.setRentabilidadCostoPromedio(Float.parseFloat(txtRC.getText()));
				articuloAModificarRentabilidades.setRentabilidadVenta(Float.parseFloat(txtRV.getText()));
				articuloAModificarRentabilidades.setPrecioTotalVenta(articuloAModificarRentabilidades.getCostoPromedio()*Float.parseFloat(txtFactorRent.getText()));
				delegadoControlInventario.actualizarControlInventario(articuloAModificarRentabilidades);
			}
			
		}else{
			if(cbAplicarCambios.getSelectedItem().equals("A toda la LINEA")){
				if(cbAjustePrecio.getSelectedItem().equals("No ajuste del PRECIO")){
					for(ControlInventario lineaAModificarFactor : lineaInventario){
						lineaAModificarFactor.setFactorRentabilidad(Float.parseFloat(txtFactorRent.getText()));
						delegadoControlInventario.actualizarControlInventario(lineaAModificarFactor);
					}
				}else{
					for(ControlInventario lineaAModificarFactor : lineaInventario){
						lineaAModificarFactor.setFactorRentabilidad(Float.parseFloat(txtFactorRent.getText()));
						lineaAModificarFactor.setRentabilidadCostoPromedio(Float.parseFloat(txtRC.getText()));
						lineaAModificarFactor.setRentabilidadVenta(Float.parseFloat(txtRV.getText()));
						lineaAModificarFactor.setPrecioTotalVenta(lineaAModificarFactor.getCostoPromedio()*Float.parseFloat(txtFactorRent.getText()));
						delegadoControlInventario.actualizarControlInventario(lineaAModificarFactor);
					}
				}
			}else{
				if(cbAjustePrecio.getSelectedItem().equals("No ajuste del PRECIO")){
					for(ControlInventario inventarioAModificar : todoElInventario){
						inventarioAModificar.setFactorRentabilidad(Float.parseFloat(txtFactorRent.getText()));
						delegadoControlInventario.actualizarControlInventario(inventarioAModificar);
					}
				}else{
					for(ControlInventario inventarioAModificar : lineaInventario){
						inventarioAModificar.setFactorRentabilidad(Float.parseFloat(txtFactorRent.getText()));
						inventarioAModificar.setRentabilidadCostoPromedio(Float.parseFloat(txtRC.getText()));
						inventarioAModificar.setRentabilidadVenta(Float.parseFloat(txtRV.getText()));
						inventarioAModificar.setPrecioTotalVenta(inventarioAModificar.getCostoPromedio()*Float.parseFloat(txtFactorRent.getText()));
						delegadoControlInventario.actualizarControlInventario(inventarioAModificar);
					}
				}
			}
		}
	}
	//Metodo para calcular la rentabilidad/costo promedio y rentabilidad/precio venta a partir del costo con el factor de rentabilidad
	private void calcularRvyRCAPartirFactRent() {
		float costoFactor = (float) (Float.parseFloat(txtFactorRent.getText())*articuloElegidoInventario.get(0).getCostoPromedio());
		float rv = (float) (((costoFactor-articuloElegidoInventario.get(0).getCostoPromedio())/costoFactor)*100);
		float rc = (float) (((costoFactor-articuloElegidoInventario.get(0).getCostoPromedio())/articuloElegidoInventario.get(0).getCostoPromedio())*100);
		
		txtRC.setText(String.valueOf(rc));
		txtRV.setText(String.valueOf(rv));
	}
	
	//Metodo para calcular la rentabilidad/costo promedio y el factor de rentabilidad a partir del costo con rentabilidad/precio venta
	private void calcularRcyFactRentPartirRv() {
		float costoRV = (float) (articuloElegidoInventario.get(0).getCostoPromedio()/(1-(Float.parseFloat(txtRV.getText())/100)));
		float factorRent = (float) (costoRV/articuloElegidoInventario.get(0).getCostoPromedio());
		float rc = (float) (((costoRV-articuloElegidoInventario.get(0).getCostoPromedio())/articuloElegidoInventario.get(0).getCostoPromedio())*100);
		
		txtRC.setText(String.valueOf(rc));
		txtFactorRent.setText(String.valueOf(factorRent));
	}
	
	//Metodo para calcular la rentabilidad/costo promedio y rentabilidad/precio venta a partir del costo con el factor de rentabilidad
	private void calcularRvyFactRentPartirRc() {
		float costoRC = (float) articuloElegidoInventario.get(0).getCostoPromedio()*((Float.parseFloat(txtRC.getText())/100)+1);
		float factorRent = (float) (costoRC/articuloElegidoInventario.get(0).getCostoPromedio());
		float rv = (float) (((costoRC-articuloElegidoInventario.get(0).getCostoPromedio())/costoRC)*100);
		
		txtRV.setText(String.valueOf(rv));
		txtFactorRent.setText(String.valueOf(factorRent));
	}
	
	//Metodo para abrir ventanta de confirmacion de registro
	private void abrirDialogoConfirmacionRegistro(){
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de modificar la información actual ?", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			modificarRentabilidades();
			dispose();
		}else{
		
		}
	}
}
