package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import javax.swing.JDialog;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import co.com.jungla.sac.negocio.delegados.DelegadoArticulo;
import co.com.jungla.sac.persistencia.entidades.Articulo;
import co.com.jungla.sac.persistencia.entidades.CompraArticulos;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  mostrar la informacion registrada de la compra
 * @author Luis Fernando Pedroza T.
 * @version: 21/09/2016
 */
public class VentMostrarCompraRegistrada extends JDialog {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtProveedor;
	private JTextField txtFechaeEntrega;
	private JTextField txtItems;
	private JTextField txtSubtotal;
	private JTextField txtDescuento;
	private JTextField txtTotalCompra;
	private JTextField txtFacturaProv;
	private JTextField txtOrdenCompra;
	private JTextField txtCxP;
	private JTextField txtIdCompra;
	private JTextField txtEgreso;
	private TableModel modeloDetalles;
	private CompraArticulos codigoCompra;

	/**
	 * Metodo constructor con los parametros necesarios para mostrar la informacion en la ventana de la compra registrada
	 */ 
	public VentMostrarCompraRegistrada(CompraArticulos codigoCompra, TableModel modeloDetalles, String idCuentaXPagar, String idEgreso) {
		setTitle("Verificar los datos de la Compra");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 369);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 423, 318);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProveedor.setBounds(10, 27, 93, 14);
		panel.add(lblProveedor);
		
		JLabel lblFechaEntrega = new JLabel("Fecha entrega");
		lblFechaEntrega.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaEntrega.setBounds(10, 47, 93, 14);
		panel.add(lblFechaEntrega);
		
		JLabel lblItems = new JLabel("Items");
		lblItems.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblItems.setBounds(10, 67, 66, 14);
		panel.add(lblItems);
		
		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSubtotal.setBounds(10, 87, 66, 14);
		panel.add(lblSubtotal);
		
		JLabel lblDcto = new JLabel("Dcto. Com.");
		lblDcto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDcto.setBounds(10, 107, 84, 14);
		panel.add(lblDcto);
		
		JLabel lblTotalCompra = new JLabel("Total Compra");
		lblTotalCompra.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalCompra.setBounds(10, 127, 84, 14);
		panel.add(lblTotalCompra);
		
		txtProveedor = new JTextField();
		txtProveedor.setEditable(false);
		txtProveedor.setBounds(107, 25, 306, 20);
		panel.add(txtProveedor);
		txtProveedor.setColumns(10);
		
		txtFechaeEntrega = new JTextField();
		txtFechaeEntrega.setEditable(false);
		txtFechaeEntrega.setBounds(107, 45, 306, 20);
		panel.add(txtFechaeEntrega);
		txtFechaeEntrega.setColumns(10);
		
		txtItems = new JTextField();
		txtItems.setEditable(false);
		txtItems.setBounds(107, 65, 306, 20);
		panel.add(txtItems);
		txtItems.setColumns(10);
		
		txtSubtotal = new JTextField();
		txtSubtotal.setEditable(false);
		txtSubtotal.setBounds(107, 85, 306, 20);
		panel.add(txtSubtotal);
		txtSubtotal.setColumns(10);
		
		txtDescuento = new JTextField();
		txtDescuento.setEditable(false);
		txtDescuento.setBounds(107, 105, 306, 20);
		panel.add(txtDescuento);
		txtDescuento.setColumns(10);
		
		txtTotalCompra = new JTextField();
		txtTotalCompra.setEditable(false);
		txtTotalCompra.setBounds(107, 125, 306, 20);
		panel.add(txtTotalCompra);
		txtTotalCompra.setColumns(10);
		
		JLabel lblFacturaProv = new JLabel("Factura Prov.");
		lblFacturaProv.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFacturaProv.setBounds(10, 147, 84, 14);
		panel.add(lblFacturaProv);
		
		txtFacturaProv = new JTextField();
		txtFacturaProv.setEditable(false);
		txtFacturaProv.setBounds(107, 145, 306, 20);
		panel.add(txtFacturaProv);
		txtFacturaProv.setColumns(10);
		
		JLabel lblOrdenCompra = new JLabel("Orden Compra");
		lblOrdenCompra.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrdenCompra.setBounds(10, 167, 84, 14);
		panel.add(lblOrdenCompra);
		
		txtOrdenCompra = new JTextField();
		txtOrdenCompra.setEditable(false);
		txtOrdenCompra.setBounds(107, 165, 306, 20);
		panel.add(txtOrdenCompra);
		txtOrdenCompra.setColumns(10);
		
		JLabel lblCuentaXPagar = new JLabel("Cuenta x Pagar");
		lblCuentaXPagar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCuentaXPagar.setBounds(10, 187, 93, 14);
		panel.add(lblCuentaXPagar);
		
		txtCxP = new JTextField();
		txtCxP.setEditable(false);
		txtCxP.setBounds(107, 185, 306, 20);
		panel.add(txtCxP);
		txtCxP.setColumns(10);
		
		JLabel lblNCompra = new JLabel("N. Compra");
		lblNCompra.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNCompra.setBounds(10, 207, 84, 14);
		panel.add(lblNCompra);
		
		txtIdCompra = new JTextField();
		txtIdCompra.setEditable(false);
		txtIdCompra.setBounds(107, 205, 306, 20);
		panel.add(txtIdCompra);
		txtIdCompra.setColumns(10);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 256, 403, 2);
		panel.add(sp);
		
		JButton btnComprobante = new JButton("Comprobante");
		btnComprobante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarReporteFactura();
			}
		});
		btnComprobante.setForeground(new Color(0, 51, 0));
		btnComprobante.setBackground(Color.YELLOW);
		btnComprobante.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnComprobante.setBounds(53, 269, 117, 23);
		panel.add(btnComprobante);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.setBounds(263, 269, 106, 23);
		panel.add(btnCerrar);
		
		txtEgreso = new JTextField();
		txtEgreso.setText("<dynamic>");
		txtEgreso.setEditable(false);
		txtEgreso.setColumns(10);
		txtEgreso.setBounds(107, 225, 306, 20);
		panel.add(txtEgreso);
		
		JLabel lblEgreso = new JLabel("Egreso");
		lblEgreso.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEgreso.setBounds(10, 227, 84, 14);
		panel.add(lblEgreso);
		
		txtProveedor.setText(codigoCompra.getProveedores().getNombre());
		txtFechaeEntrega.setText(convertirDateAString(codigoCompra.getFechaEntrega()));
		txtItems.setText(String.valueOf(codigoCompra.getItem()));
		txtSubtotal.setText(formatearNumero(codigoCompra.getSubtotal()));
		txtDescuento.setText(String.valueOf(codigoCompra.getDescuento()));
		txtTotalCompra.setText(formatearNumero(codigoCompra.getTotalCompra()));
		txtFacturaProv.setText(String.valueOf(codigoCompra.getFactProv()));
		txtOrdenCompra.setText(String.valueOf(codigoCompra.getOrdCompra()));
		txtCxP.setText(idCuentaXPagar);
		txtIdCompra.setText(String.valueOf(codigoCompra.getIdCompra()));
		txtEgreso.setText(idEgreso);
		this.modeloDetalles=modeloDetalles;
		this.codigoCompra = codigoCompra;
	}
	
	//Metodo para generar la factura en forma de reporte para luego ser impreso o guardado
	public void generarReporteFactura(){
	 DelegadoArticulo delegadoArticulo = new DelegadoArticulo();
	 URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteFacturaCompra.jasper");
	 List<ReporteDetalle> lista = new ArrayList<ReporteDetalle>();
	 
	 for(int i=0; i< modeloDetalles.getRowCount();i++){
		 Articulo articulo = delegadoArticulo.traerLineaUnidadArticuloPorCodigo(Integer.parseInt(modeloDetalles.getValueAt(i, 0).toString())).get(0);
		 
		 ReporteDetalle detalleCompra = new ReporteDetalle(articulo.getCodigo(),articulo.getNombre().toString(), articulo.getUnidadMedida().getAbreviatura(), Integer.parseInt(modeloDetalles.getValueAt(i, 3).toString()),articulo.getLineaArticulos().getNombreL(),modeloDetalles.getValueAt(i, 4).toString(), modeloDetalles.getValueAt(i, 5).toString());
		 lista.add(detalleCompra);
			
	 }
	        try {
	            JasperReport reporte= (JasperReport) JRLoader.loadObject(ubicacion);
	            Map<String, Object> parametros = new HashMap<String, Object>();
	            parametros.put("logoJungla", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png"));
	            parametros.put("idCompra", codigoCompra.getIdCompra());
	            parametros.put("nombreProveedor", codigoCompra.getProveedores().getNombre());
	            parametros.put("fechaGeneracion", convertirDateAString(codigoCompra.getFechaGeneracion()));
	            parametros.put("identProveedor", codigoCompra.getProveedores().getIdentificacion());
	            parametros.put("fechaCausado", convertirDateAString(codigoCompra.getFechaCausacion()));
	            parametros.put("factProv", String.valueOf(codigoCompra.getFactProv()));
	            parametros.put("ordenCompra", String.valueOf(codigoCompra.getOrdCompra()));
	            parametros.put("fechaPago", convertirDateAString(codigoCompra.getFechaPago()));
	            parametros.put("fechaEntrega", convertirDateAString(codigoCompra.getFechaEntrega()));
	            parametros.put("subtotal", formatearNumero(codigoCompra.getSubtotal()));
	            parametros.put("descuento", formatearNumero(codigoCompra.getDescuento()));
	            parametros.put("total", formatearNumero(codigoCompra.getTotalCompra()));
	            parametros.put("items", String.valueOf(codigoCompra.getItem()));
	            parametros.put("observaciones", codigoCompra.getObservaciones());
	            parametros.put("anulado", "");
			    parametros.put("fechaAnulado", "");
	            
	            JasperPrint jprint= JasperFillManager.fillReport(reporte, parametros,new JRBeanCollectionDataSource(lista));
	            JasperViewer.viewReport(jprint,false);
	        } catch (JRException ex) {
	            Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
	
	//Metodo para convertir una fecha de tipo date a una cadena
	public String convertirDateAString(Date strFecha){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(strFecha);
		
		return fecha;
	}
	
	//Metodo para convertir un numero corriente en formato de pesos y decimales
	private String formatearNumero(Double numero){
		NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es","CO"));
		return formato.format(numero); 
	}
}
