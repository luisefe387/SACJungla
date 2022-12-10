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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import co.com.jungla.sac.negocio.delegados.DelegadoCompraArticulos;
import co.com.jungla.sac.negocio.delegados.DelegadoContabilizacion;
import co.com.jungla.sac.negocio.delegados.DelegadoControlInventario;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleCompra;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleVenta;
import co.com.jungla.sac.negocio.delegados.DelegadoEgreso;
import co.com.jungla.sac.negocio.delegados.DelegadoKardexElectronico;
import co.com.jungla.sac.negocio.delegados.DelegadoReciboCaja;
import co.com.jungla.sac.negocio.delegados.DelegadoVentaArticulos;
import co.com.jungla.sac.persistencia.entidades.Articulo;
import co.com.jungla.sac.persistencia.entidades.CompraArticulos;
import co.com.jungla.sac.persistencia.entidades.Contabilizacion;
import co.com.jungla.sac.persistencia.entidades.ControlInventario;
import co.com.jungla.sac.persistencia.entidades.DetalleCompra;
import co.com.jungla.sac.persistencia.entidades.DetalleVenta;
import co.com.jungla.sac.persistencia.entidades.Egreso;
import co.com.jungla.sac.persistencia.entidades.KardexElectronico;
import co.com.jungla.sac.persistencia.entidades.ReciboCaja;
import co.com.jungla.sac.persistencia.entidades.VentaArticulos;

import javax.swing.JScrollPane;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 * llevar a cabo el la anulacion de los diferentes documentos como la factura de venta, la compra, el egreso, el recibo de caja
 * @author Luis Fernando Pedroza T.
 * @version: 21/09/2016
 */
public class VentAnularDocumentos extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtConsecutivo;
	
	private JButton btnAnular;
	private JTextPane txpObservaciones;
	private JComboBox cbDocumentos;
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private DelegadoContabilizacion delegadoContabilizacion = new DelegadoContabilizacion();
	
	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentAnularDocumentos() {
		setTitle("Anulaci\u00F3n de Documentos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 319, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDocumentos = new JLabel("Tipo Documento");
		lblDocumentos.setBackground(new Color(153, 204, 153));
		lblDocumentos.setOpaque(true);
		lblDocumentos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDocumentos.setBounds(10, 68, 117, 22);
		contentPane.add(lblDocumentos);
		
		cbDocumentos = new JComboBox();
		cbDocumentos.setModel(new DefaultComboBoxModel(new String[] {"Factura de Venta", "Recibo de Caja", "Compra", "Nota Credito Cliente", "Nota Debito Prov", "Egreso", "Cuenta x Pagar"}));
		cbDocumentos.setBounds(129, 69, 165, 20);
		contentPane.add(cbDocumentos);
		
		JLabel lblConsecutivo = new JLabel("Consecutivo");
		lblConsecutivo.setBackground(new Color(153, 204, 153));
		lblConsecutivo.setOpaque(true);
		lblConsecutivo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConsecutivo.setBounds(10, 91, 117, 22);
		contentPane.add(lblConsecutivo);
		
		txtConsecutivo = new JTextField();
		txtConsecutivo.setBounds(129, 92, 100, 20);
		contentPane.add(txtConsecutivo);
		txtConsecutivo.setColumns(10);
		
		//Boton para registrar el articulo 
		btnAnular = new JButton("Anular");
		btnAnular.setForeground(new Color(0, 51, 0));
		btnAnular.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAnular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarRegitro();
			}
		});
		btnAnular.setBounds(50, 228, 100, 23);
		contentPane.add(btnAnular);
		
		JTextPane txpAtencion = new JTextPane();
		txpAtencion.setBackground(UIManager.getColor("Button.background"));
		txpAtencion.setText("ATENCI\u00D3N: Hay documentos que no se podran anular si se ha realizado algun pago sobre la misma.");
		txpAtencion.setBounds(10, 20, 284, 39);
		contentPane.add(txpAtencion);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 215, 284, 2);
		contentPane.add(sp);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.setBounds(160, 228, 100, 23);
		contentPane.add(btnCerrar);
		
		JScrollPane srcObservaciones = new JScrollPane();
		srcObservaciones.setBounds(10, 143, 281, 61);
		contentPane.add(srcObservaciones);
		
		txpObservaciones = new JTextPane();
		srcObservaciones.setViewportView(txpObservaciones);
		
		JLabel lblObservaciones = new JLabel("Motivo de Anulaci\u00F3n");
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaciones.setBounds(10, 124, 140, 14);
		contentPane.add(lblObservaciones);
		
	}
	
	//Metodo que valida los datos ingresados para su posterior registro 
	private void validarRegitro() {
		if(txtConsecutivo.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Debe ingresar el número del documento a anular");
		}else{
			abrirDialogoConfirmacionAnulacion();
		}	
	}
	
	//Metodo que permite la anulación de los documentos elegidos
	private void anularDocumento() {
		
		DelegadoVentaArticulos delegadoVentaArticulos = new DelegadoVentaArticulos();
		DelegadoCompraArticulos delegadoCompraArticulos = new DelegadoCompraArticulos();
		DelegadoReciboCaja delegadoReciboCaja = new DelegadoReciboCaja();
		DelegadoEgreso delegadoEgreso = new DelegadoEgreso();
		
		if("Factura de Venta".equals(cbDocumentos.getSelectedItem().toString())){
			VentaArticulos facturaAModificar = delegadoVentaArticulos.traerVentaPorCodigo(Integer.parseInt(txtConsecutivo.getText())).get(0);
			if("Inactiva".equals(facturaAModificar.getEstadoActividad())){
				JOptionPane.showMessageDialog(null, "La factura ya fue anulada");
			}else{
				if(delegadoReciboCaja.traerRecibosCajaPorVenta(facturaAModificar.getIdVenta()).isEmpty()){
					facturaAModificar.setEstadoActividad("Inactivo");
					facturaAModificar.setObservaciones(txpObservaciones.getText());
					facturaAModificar.setFechaAnulacion(new Date());
					delegadoVentaArticulos.actualizarVentaArticulos(facturaAModificar);
					
					contabilizarFacturaAnulada(facturaAModificar);
					//rientegrarArticulosAlInventario(facturaAModificar);
					
					JOptionPane.showMessageDialog(null, "La Factura se anuló y los articulos fueron reintegrados al inventario");
					                                                                                   
				}else{
					JOptionPane.showMessageDialog(null, "Esta factura no se puede anular porque ya se efectuo uno o mas abonos");
				}
			}
		}else{
			if("Recibo de Caja".equals(cbDocumentos.getSelectedItem().toString())){
				ReciboCaja reciboAModificar = delegadoReciboCaja.traerRecibosCajaPorIdRecibo(Integer.parseInt(txtConsecutivo.getText())).get(0);
				if("Inactiva".equals(reciboAModificar.getEstadoActividad())){
					JOptionPane.showMessageDialog(null, "El recibo ya fue anulado");
				}else{
					reciboAModificar.setEstadoActividad("Inactivo");
					reciboAModificar.setObservaciones(txpObservaciones.getText());
					reciboAModificar.setFechaAnulacion(new Date());
					
					delegadoReciboCaja.actualizarReciboCaja(reciboAModificar);
					
					contabilizarReciboAnulado(reciboAModificar);
					cambiarEstadoPagoFacturaPorReciboAnulado(reciboAModificar);
					
					JOptionPane.showMessageDialog(null, "El Recibo se ha anulado");
					
				}
			}else{
				if("Compra".equals(cbDocumentos.getSelectedItem().toString())){
					CompraArticulos compraAModificar = delegadoCompraArticulos.traerCompraPorCodigo(Integer.parseInt(txtConsecutivo.getText()));
					if("Inactiva".equals(compraAModificar.getEstadoActividad())){
						JOptionPane.showMessageDialog(null, "La compra ya fue anulada");
					}else{
						String nl = System.getProperty("line.separator");
						if(compraAModificar.getEgreso()!=0){
							JOptionPane.showMessageDialog(null, "La COMPRA ya ha sido PAGADA al proveedor con el EGRESO N°" +compraAModificar.getEgreso()+" por lo cual NO puede ser anulada"+nl+nl+"Debe anular primer el Egreso y luego la compra.");
						}else{
							compraAModificar.setEstadoActividad("Inactivo");
							compraAModificar.setObservaciones(txpObservaciones.getText());
							compraAModificar.setFechaAnulacion(new Date());
							
							delegadoCompraArticulos.actualizarCompraArticulos(compraAModificar);
							
							contabilizarCompraAnulada(compraAModificar);
							//restarArticulosAlInventario(compraAModificar);
							
							JOptionPane.showMessageDialog(null, "La Compra se anuló y los articulos fueron restados del inventario");
						}
					}			
				}else{
					if("Egreso".equals(cbDocumentos.getSelectedItem().toString())){
						Egreso egresoAModificar = delegadoEgreso.traerEgresoPorCodigo(Integer.parseInt(txtConsecutivo.getText())).get(0);
						if("Inactiva".equals(egresoAModificar.getEstadoActividad())){
							JOptionPane.showMessageDialog(null, "El Egreso ya fue anulado");
						}else{
							egresoAModificar.setEstadoActividad("Inactivo");
							egresoAModificar.setFechaAnulacion(new Date());
							egresoAModificar.setObservaciones(txpObservaciones.getText());
							
							delegadoEgreso.actualizarEgreso(egresoAModificar);
							
							contabilizarEgresoAnulado(egresoAModificar);
							cambiarEstadoPagoCompraPorEgreso(egresoAModificar);
							
							JOptionPane.showMessageDialog(null, "El Egreso ha sido anulado");
						}
						
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
	
	//Metodo para cambiar el estado de pago de la compra por el egreso anulado
	private void cambiarEstadoPagoCompraPorEgreso(Egreso egresoAModificar) {
		DelegadoCompraArticulos delegadoCompraArticulos =new DelegadoCompraArticulos();
		CompraArticulos compraAModificar = delegadoCompraArticulos.traerCompraPorCodigo(egresoAModificar.getCompra());
		
		compraAModificar.setEstadoPago("Pendiente");
		delegadoCompraArticulos.actualizarCompraArticulos(compraAModificar);
		
	}

	//Metodo para contabilizar el egreso anulado
	private void contabilizarEgresoAnulado(Egreso egresoAModificar) {
		DelegadoContabilizacion delegadoContabilizacion = new DelegadoContabilizacion();                                                                                   
		Contabilizacion contabilizarAEgreso = new Contabilizacion();                                                                 
		Contabilizacion contabilizarACaja = new Contabilizacion();
		
		contabilizarAEgreso.setCodigoCuenta(5135);
		contabilizarAEgreso.setFechaGeneracion(new Date());              
		contabilizarAEgreso.setNombreCuenta("Gastos de Administración");
		contabilizarAEgreso.setSaldoDebito(0);
		contabilizarAEgreso.setSaldoCredito(egresoAModificar.getTotalPagado());
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarAEgreso);
		
		contabilizarACaja.setCodigoCuenta(1105);
		contabilizarACaja.setFechaGeneracion(new Date());
		contabilizarACaja.setNombreCuenta("Caja");
		contabilizarACaja.setSaldoDebito(egresoAModificar.getTotalPagado());
		contabilizarACaja.setSaldoCredito(0);
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarACaja); 
	
		
	}

	//Metodo para restar los articulos al inventario de la compra anulada
	private void restarArticulosAlInventario(CompraArticulos compraAModificar) {
		KardexElectronico kardexElectronico = new KardexElectronico();
		DelegadoKardexElectronico delegadoKardexElectronico = new DelegadoKardexElectronico();
		DelegadoDetalleCompra delegadoDetalleCompra = new DelegadoDetalleCompra();
		DelegadoControlInventario delegadoControlInventario = new DelegadoControlInventario();
		List <KardexElectronico> ultimoRegistroPorArticuloKardex1;
		KardexElectronico ultimoRegistroPorArticuloKardex;
		List<DetalleCompra> ultimoDetalleCompra;
		List<ControlInventario> controlInventario;
		ControlInventario controlInventarioAModificar;
		Articulo articulo = new Articulo();
		
		ultimoDetalleCompra = delegadoDetalleCompra.listarDetallePorCodigoCompra(compraAModificar.getIdCompra());
			
		ultimoRegistroPorArticuloKardex1 = delegadoKardexElectronico.traerUltimoRegistroKardexElectronicopPorArticulo(ultimoDetalleCompra.get(0).getArticulo().getCodigo());
		ultimoRegistroPorArticuloKardex = ultimoRegistroPorArticuloKardex1.get(0);
		
		kardexElectronico.setFecha(ultimoDetalleCompra.get(0).getCompraArticulos().getFechaEntrega());
		kardexElectronico.setMovimiento("Compra Anulada");
		kardexElectronico.setNroDocumento(ultimoDetalleCompra.get(0).getCompraArticulos().getIdCompra());
		articulo.setCodigo(ultimoDetalleCompra.get(0).getArticulo().getCodigo());
		kardexElectronico.setArticulo(articulo);
		kardexElectronico.setCantidadEntrada(0);
		kardexElectronico.setCostoUEntrada(0);
		kardexElectronico.setCostoTEntrada(0);
		kardexElectronico.setCantidadSalida(ultimoDetalleCompra.get(0).getCantidad());
		kardexElectronico.setCostoUSalida(ultimoDetalleCompra.get(0).getCosto());
		kardexElectronico.setCostoTSalida(ultimoDetalleCompra.get(0).getTotal());
		kardexElectronico.setCantidadSaldo(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleCompra.get(0).getCantidad());
		kardexElectronico.setCostoUSaldo((ultimoRegistroPorArticuloKardex.getCostoTSaldo() - ultimoDetalleCompra.get(0).getTotal())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleCompra.get(0).getCantidad()));
		kardexElectronico.setCostoTSaldo((ultimoRegistroPorArticuloKardex.getCostoTSaldo() - ultimoDetalleCompra.get(0).getTotal())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleCompra.get(0).getCantidad())*(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleCompra.get(0).getCantidad()));
		kardexElectronico.setProveedorUltimaCompra(ultimoDetalleCompra.get(0).getCompraArticulos().getProveedores().getNombre());
		
		delegadoKardexElectronico.insertarKardexElectronico(kardexElectronico);

		controlInventario = delegadoControlInventario.traerContInventarioPorCodigoArticulo(ultimoDetalleCompra.get(0).getArticulo().getCodigo());
		controlInventarioAModificar = controlInventario.get(0);
		
		controlInventarioAModificar.setCostoPromedio((ultimoRegistroPorArticuloKardex.getCostoTSaldo() - ultimoDetalleCompra.get(0).getTotal())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleCompra.get(0).getCantidad()));
		controlInventarioAModificar.setCantExistencia(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleCompra.get(0).getCantidad());
		controlInventarioAModificar.setProveedorUltimaCompra(ultimoDetalleCompra.get(0).getCompraArticulos().getProveedores().getNombre());
		controlInventarioAModificar.setCostoUltimaCompra(ultimoDetalleCompra.get(0).getCosto());
		controlInventarioAModificar.setTotalCostoInventario((ultimoRegistroPorArticuloKardex.getCostoTSaldo() - ultimoDetalleCompra.get(0).getTotal())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleCompra.get(0).getCantidad())*(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleCompra.get(0).getCantidad()));
		
		delegadoControlInventario.actualizarControlInventario(controlInventarioAModificar);
		
		
	}

	//Metodo para contabililizar la compra anulada
	private void contabilizarCompraAnulada(CompraArticulos compraAModificar) {
		DelegadoContabilizacion delegadoContabilizacion = new DelegadoContabilizacion();                                                                                   
		Contabilizacion contabilizarACompra = new Contabilizacion();                                                                 
		Contabilizacion contabilizarAProveedor = new Contabilizacion();
		
		contabilizarACompra.setCodigoCuenta(1435);
		contabilizarACompra.setFechaGeneracion(new Date());              
		contabilizarACompra.setNombreCuenta("Inventario de Mercancias");
		contabilizarACompra.setSaldoDebito(0);
		contabilizarACompra.setSaldoCredito(compraAModificar.getTotalCompra());
		
		contabilizarAProveedor.setCodigoCuenta(2205);
		contabilizarAProveedor.setFechaGeneracion(new Date());              
		contabilizarAProveedor.setNombreCuenta("Proveedores Nacionales");
		contabilizarAProveedor.setSaldoDebito(compraAModificar.getTotalCompra());
		contabilizarAProveedor.setSaldoCredito(0);
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarACompra); 
		delegadoContabilizacion.insertarContabilizacion(contabilizarAProveedor); 
		
	}

	//Metodo para cambiar el estado de pagola factura producto de un recibo anulado
	private void cambiarEstadoPagoFacturaPorReciboAnulado(ReciboCaja reciboAModificar) {
		DelegadoVentaArticulos delegadoVentaArticulos = new DelegadoVentaArticulos();
		List<VentaArticulos> ventasConRecibo = delegadoVentaArticulos.traerVentasPorReciboCaja(reciboAModificar.getIdReciboCaja());
		for(VentaArticulos ventasAModificar : ventasConRecibo){
			ventasAModificar.setEstadoActividad("Pendiente");
			delegadoVentaArticulos.actualizarVentaArticulos(ventasAModificar);
		}
	}

	//Metodo para contabilizar el recibo de caja anulado
	private void contabilizarReciboAnulado(ReciboCaja reciboAModificar) {
		DelegadoContabilizacion delegadoContabilizacion = new DelegadoContabilizacion();                                                                                   
		Contabilizacion contabilizarACaja = new Contabilizacion();
		Contabilizacion contabilizarACliente = new Contabilizacion();
		
		contabilizarACaja.setCodigoCuenta(1105);
		contabilizarACaja.setFechaGeneracion(new Date());              
		contabilizarACaja.setNombreCuenta("Caja");
		contabilizarACaja.setSaldoDebito(0);
		contabilizarACaja.setSaldoCredito(reciboAModificar.getTotalRecibido());
			
		delegadoContabilizacion.insertarContabilizacion(contabilizarACaja);
		
		contabilizarACliente.setCodigoCuenta(1305);
		contabilizarACliente.setFechaGeneracion(new Date());              
		contabilizarACliente.setNombreCuenta("Clientes");
		contabilizarACliente.setSaldoDebito(reciboAModificar.getTotalRecibido());
		contabilizarACliente.setSaldoCredito(0);
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarACliente);
		
	}

	/*//Metodo para reintegrar los articulos al inventario de la factura de venta anulada
	private void rientegrarArticulosAlInventario(VentaArticulos facturaAModificar) {
		KardexElectronico kardexElectronico = new KardexElectronico();
		DelegadoKardexElectronico delegadoKardexElectronico = new DelegadoKardexElectronico();
		DelegadoControlInventario delegadoControlInventario = new DelegadoControlInventario();
		DelegadoDetalleVenta delegadoDetalleVenta = new DelegadoDetalleVenta();
		List <KardexElectronico> ultimoRegistroPorArticuloKardex1;
		KardexElectronico ultimoRegistroPorArticuloKardex;
		List<ControlInventario> controlInventario;
		ControlInventario controlInventarioAModificar;
		Articulo articulo = new Articulo();
		
		List<DetalleVenta> listaDetalleVenta = delegadoDetalleVenta.listarDetallePorCodigoVenta(facturaAModificar.getIdVenta());
			
		ultimoRegistroPorArticuloKardex1 = delegadoKardexElectronico.traerUltimoRegistroKardexElectronicopPorArticulo(ultimoDetalleVenta.getArticulo().getCodigo());
		ultimoRegistroPorArticuloKardex = ultimoRegistroPorArticuloKardex1.get(0);
		
		for(DetalleVenta detalles:listaDetalleVenta){
			kardexElectronico.setFecha(detalles.getVentaArticulos().getFechaGeneracion());
			kardexElectronico.setMovimiento("Venta Anulada");
			kardexElectronico.setNroDocumento(detalles.getVentaArticulos().getIdVenta());
			articulo.setCodigo(detalles.getArticulo().getCodigo());
			kardexElectronico.setArticulo(articulo);
			kardexElectronico.setCantidadEntrada(detalles.getCantidad());
			kardexElectronico.setCostoUEntrada(detalles.getVlrUnitario());
			kardexElectronico.setCostoTEntrada(detalles.getTotal());
			kardexElectronico.setCantidadSalida(0);
			kardexElectronico.setCostoUSalida(0);
			kardexElectronico.setCostoTSalida(0);
			kardexElectronico.setCantidadSaldo(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + detalles.getCantidad());
			kardexElectronico.setCostoUSaldo((ultimoRegistroPorArticuloKardex.getCostoTSaldo() + (ultimoRegistroPorArticuloKardex.getCostoUSaldo())*detalles.getCantidad())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + detalles.getCantidad()));
			kardexElectronico.setCostoTSaldo((ultimoRegistroPorArticuloKardex.getCostoTSaldo() + detalles.getTotal()/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + detalles.getCantidad())*(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + detalles.getCantidad())));
			kardexElectronico.setProveedorUltimaCompra("");
			
			delegadoKardexElectronico.insertarKardexElectronico(kardexElectronico);
				
			controlInventario = delegadoControlInventario.traerContInventarioPorCodigoArticulo(detalles.getArticulo().getCodigo());
			controlInventarioAModificar = controlInventario.get(0);
			
			controlInventarioAModificar.setCostoPromedio((ultimoRegistroPorArticuloKardex.getCostoTSaldo() + (ultimoRegistroPorArticuloKardex.getCostoUSaldo())*detalles.getCantidad())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + detalles.getCantidad()));
			controlInventarioAModificar.setCantExistencia(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + detalles.getCantidad());
			controlInventarioAModificar.setTotalCostoInventario((ultimoRegistroPorArticuloKardex.getCostoTSaldo() + detalles.getTotal()/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + detalles.getCantidad())*(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + detalles.getCantidad())));
			
			delegadoControlInventario.actualizarControlInventario(controlInventarioAModificar);
		}
		
	}*/

	//Metodo para contabilizar la factura de venta anulada
	private void contabilizarFacturaAnulada(VentaArticulos facturaAModificar) {
		Contabilizacion contabilizarAVenta = new Contabilizacion();                                                                 
		Contabilizacion contabilizarACliente = new Contabilizacion();
		Contabilizacion contabilizarACostoVenta = new Contabilizacion();
		Contabilizacion contabilizarAInventario = new Contabilizacion();
		
		contabilizarAVenta.setCodigoCuenta(4135);
		contabilizarAVenta.setFechaGeneracion(new Date());              
		contabilizarAVenta.setNombreCuenta("Comercio al por mayor y al por menor");
		contabilizarAVenta.setSaldoDebito(facturaAModificar.getTotalVenta());
		contabilizarAVenta.setSaldoCredito(0);
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarAVenta);
		
		contabilizarACliente.setCodigoCuenta(1305);
		contabilizarACliente.setFechaGeneracion(new Date());              
		contabilizarACliente.setNombreCuenta("Clientes");
		contabilizarACliente.setSaldoDebito(0);
		contabilizarACliente.setSaldoCredito(facturaAModificar.getTotalVenta());
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarACliente);
		
		contabilizarACostoVenta.setCodigoCuenta(6135);
		contabilizarACostoVenta.setFechaGeneracion(new Date());              
		contabilizarACostoVenta.setNombreCuenta("Comercio al por mayor y al por menor");
		contabilizarACostoVenta.setSaldoDebito(facturaAModificar.getCostoVenta());
		contabilizarACostoVenta.setSaldoCredito(0);
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarACostoVenta);
		
		contabilizarAInventario.setCodigoCuenta(1435);
		contabilizarAInventario.setFechaGeneracion(new Date());              
		contabilizarAInventario.setNombreCuenta("Inventario de Mercancias");
		contabilizarAInventario.setSaldoDebito(0);
		contabilizarAInventario.setSaldoCredito(facturaAModificar.getCostoVenta());
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarAInventario);
		
	}

	//Limpiar los datos escritos 
	private void limpiar() {
		cbDocumentos.setSelectedIndex(0);
		txtConsecutivo.setText("");
		txpObservaciones.setText("");
		
	}
	
	//Metodo para abrir ventanta de confirmacion de la anulacion
	private void abrirDialogoConfirmacionAnulacion(){
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de anular este documento "+cbDocumentos.getSelectedItem().toString(), null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			anularDocumento();
			limpiar();
		}else{
		
		}
	}
}
