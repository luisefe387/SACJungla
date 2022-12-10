package co.com.jungla.sac.presentacion.ventanas;

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

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;


import co.com.jungla.sac.negocio.delegados.DelegadoCompraArticulos;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleCompra;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleVenta;
import co.com.jungla.sac.negocio.delegados.DelegadoEgreso;
import co.com.jungla.sac.negocio.delegados.DelegadoReciboCaja;
import co.com.jungla.sac.negocio.delegados.DelegadoVentaArticulos;
import co.com.jungla.sac.persistencia.entidades.Articulo;
import co.com.jungla.sac.persistencia.entidades.CompraArticulos;
import co.com.jungla.sac.persistencia.entidades.ControlInventario;
import co.com.jungla.sac.persistencia.entidades.DetalleCompra;
import co.com.jungla.sac.persistencia.entidades.DetalleVenta;
import co.com.jungla.sac.persistencia.entidades.Egreso;
import co.com.jungla.sac.persistencia.entidades.LineaArticulos;
import co.com.jungla.sac.persistencia.entidades.ReciboCaja;
import co.com.jungla.sac.persistencia.entidades.UnidadMedida;
import co.com.jungla.sac.persistencia.entidades.VentaArticulos;

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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.SystemColor;

public class VentImprimirDocumentos extends JFrame {

	private JPanel contentPane;
	private JTextField txtConsecutivo;
	DefaultComboBoxModel modeloUnidadMedida = new DefaultComboBoxModel();
	DefaultComboBoxModel modeloLineaArticulos = new DefaultComboBoxModel();
	public JComboBox cbDocumentos;

	
	/**
	 * Create the frame.
	 */
	public VentImprimirDocumentos() {
		setTitle("Impresi\u00F3n de Documentos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 324, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDocumentos = new JLabel("Tipo Documento");
		lblDocumentos.setBackground(new Color(153, 204, 153));
		lblDocumentos.setOpaque(true);
		lblDocumentos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDocumentos.setBounds(10, 22, 117, 22);
		contentPane.add(lblDocumentos);
		
		cbDocumentos = new JComboBox();
		cbDocumentos.setModel(new DefaultComboBoxModel(new String[] {"Factura de Venta", "Recibo de Caja", "Compra", "Nota Credito Cliente", "Nota Debito Prov", "Egreso", "Cuenta x Pagar"}));
		cbDocumentos.setBounds(129, 23, 165, 20);
		contentPane.add(cbDocumentos);
		
		JLabel lblConsecutivo = new JLabel("Consecutivo");
		lblConsecutivo.setBackground(new Color(153, 204, 153));
		lblConsecutivo.setOpaque(true);
		lblConsecutivo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConsecutivo.setBounds(10, 45, 117, 22);
		contentPane.add(lblConsecutivo);
		
		txtConsecutivo = new JTextField();
		txtConsecutivo.setBounds(129, 46, 100, 20);
		contentPane.add(txtConsecutivo);
		txtConsecutivo.setColumns(10);
		
		//Boton para registrar el articulo 
		JButton btnVistaPrevia = new JButton("Vista Previa de Impresi\u00F3n");
		btnVistaPrevia.setForeground(new Color(0, 51, 0));
		btnVistaPrevia.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVistaPrevia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarRegitro();
			}
		});
		btnVistaPrevia.setBounds(50, 91, 205, 23);
		contentPane.add(btnVistaPrevia);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 78, 284, 2);
		contentPane.add(sp);
		
	}
	
	//Metodo que valida los datos ingresados para su posterior registro 
	private void validarRegitro() {
		if(txtConsecutivo.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Debe ingresar el número del documento a anular");
		}else{
			imprimirDocumento();
			limpiar();
		}	
	}
	
	//Metodo que permite la anulación de los documentos elegidos
	private void imprimirDocumento() {
		
		DelegadoVentaArticulos delegadoVentaArticulos = new DelegadoVentaArticulos();
		DelegadoCompraArticulos delegadoCompraArticulos = new DelegadoCompraArticulos();
		DelegadoReciboCaja delegadoReciboCaja = new DelegadoReciboCaja();
		DelegadoEgreso delegadoEgreso = new DelegadoEgreso();
		
		if("Factura de Venta".equals(cbDocumentos.getSelectedItem().toString())){
			try{
				VentaArticulos facturaAImprimir = delegadoVentaArticulos.traerTodasVentaPorCodigo(Integer.parseInt(txtConsecutivo.getText())).get(0);
				imprimirFacturaVenta(facturaAImprimir);
			}catch(Exception e){
				e.getMessage();
				JOptionPane.showMessageDialog(null, "La Fatura de venta no existe por lo tanto no se puede visualizar para imprimir");
			}
			
			
			
		}else{
			if("Recibo de Caja".equals(cbDocumentos.getSelectedItem().toString())){
				ReciboCaja reciboAModificar = delegadoReciboCaja.traerRecibosCajaPorIdRecibo(Integer.parseInt(txtConsecutivo.getText())).get(0);
				
			}else{
				if("Compra".equals(cbDocumentos.getSelectedItem().toString())){
					try{
						CompraArticulos compraAImprimir = delegadoCompraArticulos.traerTodaCompraPorCodigo(Integer.parseInt(txtConsecutivo.getText()));
						imprimirCompra(compraAImprimir);
					}catch(Exception e){
						e.getMessage();
						JOptionPane.showMessageDialog(null, "La Compra no existe por lo tanto no se puede visualizar para imprimir");
					}
					
				}else{
					if("Egreso".equals(cbDocumentos.getSelectedItem().toString())){
						Egreso egresoAModificar = delegadoEgreso.traerEgresoPorCodigo(Integer.parseInt(txtConsecutivo.getText())).get(0);
						
					}else{
						if("Nota Credito Cliente".equals(cbDocumentos.getSelectedItem().toString())){
							
						}else{
							if("Nota Debito Prov".equals(cbDocumentos.getSelectedItem().toString())){
								
							}else{
								if("Cuenta x Pagar".equals(cbDocumentos.getSelectedItem().toString())){
									
								}else{
								
								}
							}
						}
					}
				}
			}
		}
		
	}
	//Metodo para imprimir la factura de venta
	private void imprimirFacturaVenta(VentaArticulos facturaAImprimir) {
		DelegadoDetalleVenta delegadoDetalleVenta = new DelegadoDetalleVenta();
		 URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteFacturaVenta.jasper");
		 List<ReporteDetalle> lista = new ArrayList<ReporteDetalle>();
		 
		 List<DetalleVenta> listaDetalleVenta = delegadoDetalleVenta.listarDetallePorCodigoVenta(facturaAImprimir.getIdVenta()); 
		 
		 for(DetalleVenta detalles : listaDetalleVenta){
			 ReporteDetalle detalleVenta = new ReporteDetalle(detalles.getArticulo().getCodigo(),detalles.getArticulo().getNombre(), detalles.getArticulo().getUnidadMedida().getAbreviatura(), detalles.getCantidad() , formatearNumero(detalles.getVlrUnitario()), formatearNumero(detalles.getTotal()));
			 lista.add(detalleVenta);
		 }
		 
       try {
		    JasperReport reporte= (JasperReport) JRLoader.loadObject(ubicacion);
		    Map<String, Object> parametros = new HashMap<String, Object>();
		    parametros.put("logoJungla", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png"));
		    parametros.put("firma", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/firma.jpg"));
		    parametros.put("idVenta", facturaAImprimir.getIdVenta());
		    parametros.put("nombreCliente", facturaAImprimir.getClientes().getNombre());
		    parametros.put("identCliente", facturaAImprimir.getClientes().getIdentificacion());
		    parametros.put("direccion", facturaAImprimir.getClientes().getDireccion());
		    parametros.put("telefono", facturaAImprimir.getClientes().getTelefono());
		    parametros.put("ciudad", facturaAImprimir.getClientes().getMunicipio().getNombre());
		    parametros.put("formaPago", facturaAImprimir.getFormaPagoCliente().getDescripcion());
		    parametros.put("nombreVendedor", facturaAImprimir.getVendedores().getNombre());
		    parametros.put("ordenCompra", String.valueOf(facturaAImprimir.getOrdCompra()));
		    parametros.put("pedido", String.valueOf(facturaAImprimir.getPedido()));
		    parametros.put("subtotal", formatearNumero(facturaAImprimir.getSubtotal()));
		    parametros.put("descuento", formatearNumero(facturaAImprimir.getDescuento()));
		    parametros.put("total", formatearNumero(facturaAImprimir.getTotalVenta()));
		    parametros.put("items", String.valueOf(facturaAImprimir.getItems()));
		    parametros.put("fechaFactura", convertirDateAString(facturaAImprimir.getFechaGeneracion()));
		    parametros.put("fechaLimite", convertirDateAString(facturaAImprimir.getFechaLimitePago()));
		    parametros.put("observaciones", facturaAImprimir.getObservaciones());
		    if("Inactivo".equals(facturaAImprimir.getEstadoActividad())){
		    	parametros.put("anulado", "ANULADO");
				parametros.put("fechaAnulado", convertirDateAString(facturaAImprimir.getFechaAnulacion()));
		    }else{
		    	parametros.put("anulado", "");
				parametros.put("fechaAnulado", "");
		    }
		   
		    JasperPrint jprint= JasperFillManager.fillReport(reporte, parametros,new JRBeanCollectionDataSource(lista));
		    JasperViewer.viewReport(jprint,false);
		} catch (JRException ex) {
		    Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
	//Metodo para visualizar la factura de compra en forma de reporte para luego ser impreso o guardado
	private void imprimirCompra(CompraArticulos compraAImprimir){

	 DelegadoDetalleCompra delegadoDetalleCompra = new DelegadoDetalleCompra();
	 URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteFacturaCompra.jasper");
	 List<ReporteDetalle> lista = new ArrayList<ReporteDetalle>();
	 
	 List<DetalleCompra> listaDetalleCompra = delegadoDetalleCompra.listarDetallePorCodigoCompra(compraAImprimir.getIdCompra()); 
	 
	 for(DetalleCompra detalles : listaDetalleCompra){
		 ReporteDetalle detalleCompra = new ReporteDetalle(detalles.getArticulo().getCodigo(),detalles.getArticulo().getNombre(), detalles.getArticulo().getUnidadMedida().getAbreviatura(), detalles.getCantidad() , detalles.getArticulo().getLineaArticulos().getNombreL(), formatearNumero(detalles.getCosto()), formatearNumero(detalles.getTotal()));
		 lista.add(detalleCompra);
	 }
	        try {
	            JasperReport reporte= (JasperReport) JRLoader.loadObject(ubicacion);
	            Map<String, Object> parametros = new HashMap<String, Object>();
	            parametros.put("logoJungla", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png"));
	            parametros.put("idCompra", compraAImprimir.getIdCompra());
	            parametros.put("nombreProveedor", compraAImprimir.getProveedores().getNombre());
	            parametros.put("fechaGeneracion", convertirDateAString(compraAImprimir.getFechaGeneracion()));
	            parametros.put("identProveedor", compraAImprimir.getProveedores().getIdentificacion());
	            parametros.put("fechaCausado", convertirDateAString(compraAImprimir.getFechaCausacion()));
	            parametros.put("factProv", String.valueOf(compraAImprimir.getFactProv()));
	            parametros.put("ordenCompra", String.valueOf(compraAImprimir.getOrdCompra()));
	            parametros.put("fechaPago", convertirDateAString(compraAImprimir.getFechaPago()));
	            parametros.put("fechaEntrega", convertirDateAString(compraAImprimir.getFechaEntrega()));
	            parametros.put("subtotal", formatearNumero(compraAImprimir.getSubtotal()));
	            parametros.put("descuento", formatearNumero(compraAImprimir.getDescuento()));
	            parametros.put("total", formatearNumero(compraAImprimir.getTotalCompra()));
	            parametros.put("items", String.valueOf(compraAImprimir.getItem()));
	            parametros.put("observaciones", compraAImprimir.getObservaciones());
	            if("Inactivo".equals(compraAImprimir.getEstadoActividad())){
	            	parametros.put("anulado", "ANULADO");
				    parametros.put("fechaAnulado", convertirDateAString(compraAImprimir.getFechaAnulacion()));
	            }else{
	            	 parametros.put("anulado", "ANULADO");
	 			    parametros.put("fechaAnulado", "");
	            }
	        
	            JasperPrint jprint= JasperFillManager.fillReport(reporte, parametros,new JRBeanCollectionDataSource(lista));
	            JasperViewer.viewReport(jprint,false);
	        } catch (JRException ex) {
	            Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}

	//Limpiar los datos escritos 
	private void limpiar() {
		cbDocumentos.setSelectedIndex(0);
		txtConsecutivo.setText("");
	}
	
	
	//Metodo para convertir un numero corriente en formato de pesos y decimales
	private String formatearNumero(Double numero){
		NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es","CO"));
		return formato.format(numero); 
	}

	
	//Metodo que permite la conversion de un dato de tipo date a uno de tipo string
	public String convertirDateAString(Date strFecha){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(strFecha);
		
		return fecha;
	}
}
