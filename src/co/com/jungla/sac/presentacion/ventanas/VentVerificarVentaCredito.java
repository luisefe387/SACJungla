package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
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

import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JScrollPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import co.com.jungla.sac.negocio.delegados.DelegadoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoContabilizacion;
import co.com.jungla.sac.negocio.delegados.DelegadoControlInventario;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleVenta;
import co.com.jungla.sac.negocio.delegados.DelegadoFormaPagoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoKardexElectronico;
import co.com.jungla.sac.negocio.delegados.DelegadoVendedor;
import co.com.jungla.sac.negocio.delegados.DelegadoVentaArticulos;

import co.com.jungla.sac.persistencia.entidades.Articulo;
import co.com.jungla.sac.persistencia.entidades.Cliente;
import co.com.jungla.sac.persistencia.entidades.Contabilizacion;
import co.com.jungla.sac.persistencia.entidades.ControlInventario;
import co.com.jungla.sac.persistencia.entidades.DetalleVenta;
import co.com.jungla.sac.persistencia.entidades.FormaPagoCliente;
import co.com.jungla.sac.persistencia.entidades.KardexElectronico;
import co.com.jungla.sac.persistencia.entidades.Vendedor;
import co.com.jungla.sac.persistencia.entidades.VentaArticulos;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro de la venta de articulos a credito, el movimiento de venta en el kardex electronico y su contabilizacion.
 * @author Luis Fernando Pedroza T.
 * @version: 19/09/2016
 */
public class VentVerificarVentaCredito extends JDialog {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextPane txpObservaciones;
	private JScrollPane srcObservaciones;
	private JTextField txtCliente;
	private JTextField txtPedido;
	private JTextField txtOrdenCompra;
	private JTextField txtVendedor;
	private JTextField txtFormaPago;
	private JTextField txtFechaLimite;
	private JTextField txtSubtotal;
	private JTextField txtDcto;
	private JTextField txtTotal;
	private TableModel modeloDetalles;
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private List<VentaArticulos> ultimaVentaArticulo;
	private DetalleVenta ultimoDetalleVenta;
	private VentaArticulos ventaArticulos = new VentaArticulos();
	private DelegadoControlInventario delegadoControlInventario = new DelegadoControlInventario();
	private Cliente cliente = new Cliente();
	private DelegadoVentaArticulos delegadoVentaArticulos = new DelegadoVentaArticulos();
	private DelegadoDetalleVenta delegadoDetalleVenta = new DelegadoDetalleVenta();
	private List <KardexElectronico> ultimoRegistroPorArticuloKardex1;
	private KardexElectronico ultimoRegistroPorArticuloKardex;
	private List<Cliente> datosCliente;
	private Vendedor datosVendedor;
	private List<FormaPagoCliente> datosFormaPago;
	private double totalCostoVenta;
	private String items;
	private Date fechaLimitePago;
	
	/**
	 * Metodo constructor con parametros necesarios para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentVerificarVentaCredito(String identificacionCliente, String identificacionVendedor, int idFormaPagoCliente, Date fechaLimitePago, String subtotal, String dctoCom, String totalVenta, TableModel modeloDetalles, String ordenCompra, String pedido, String items) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentVerificarVentaCredito.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Verificar los datos de la Venta a Crédito");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 463, 394);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBounds(10, 11, 433, 334);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCliente.setBounds(10, 27, 93, 14);
		pn1.add(lblCliente);
		
		JLabel lblPedido = new JLabel("Pedido");
		lblPedido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPedido.setBounds(10, 47, 87, 14);
		pn1.add(lblPedido);
		
		JLabel lblOrdenCompra = new JLabel("Orden Compra");
		lblOrdenCompra.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrdenCompra.setBounds(10, 67, 93, 14);
		pn1.add(lblOrdenCompra);
		
		JLabel lblVendedor0 = new JLabel("Vendedor");
		lblVendedor0.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVendedor0.setBounds(10, 87, 87, 14);
		pn1.add(lblVendedor0);
		
		JLabel lblFormaPago = new JLabel("Forma Pago");
		lblFormaPago.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFormaPago.setBounds(10, 107, 93, 14);
		pn1.add(lblFormaPago);
		
		JLabel lblFechaLimite = new JLabel("Fecha Limite");
		lblFechaLimite.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaLimite.setBounds(10, 127, 84, 14);
		pn1.add(lblFechaLimite);
		
		txtCliente = new JTextField();
		txtCliente.setEditable(false);
		txtCliente.setBounds(107, 25, 317, 20);
		pn1.add(txtCliente);
		txtCliente.setColumns(10);
		
		txtPedido = new JTextField();
		txtPedido.setEditable(false);
		txtPedido.setBounds(107, 45, 317, 20);
		pn1.add(txtPedido);
		txtPedido.setColumns(10);
		
		txtOrdenCompra = new JTextField();
		txtOrdenCompra.setEditable(false);
		txtOrdenCompra.setBounds(107, 65, 317, 20);
		pn1.add(txtOrdenCompra);
		txtOrdenCompra.setColumns(10);
		
		txtVendedor = new JTextField();
		txtVendedor.setEditable(false);
		txtVendedor.setBounds(107, 85, 317, 20);
		pn1.add(txtVendedor);
		txtVendedor.setColumns(10);
		
		txtFormaPago = new JTextField();
		txtFormaPago.setEditable(false);
		txtFormaPago.setBounds(107, 105, 317, 20);
		pn1.add(txtFormaPago);
		txtFormaPago.setColumns(10);
		
		txtFechaLimite = new JTextField();
		txtFechaLimite.setEditable(false);
		txtFechaLimite.setBounds(107, 125, 317, 20);
		pn1.add(txtFechaLimite);
		txtFechaLimite.setColumns(10);
		
		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSubtotal.setBounds(10, 167, 84, 14);
		pn1.add(lblSubtotal);
		
		txtSubtotal = new JTextField();
		txtSubtotal.setEditable(false);
		txtSubtotal.setBounds(107, 165, 317, 20);
		pn1.add(txtSubtotal);
		txtSubtotal.setColumns(10);
		
		JLabel lblDcto = new JLabel("Dcto");
		lblDcto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDcto.setBounds(10, 187, 84, 14);
		pn1.add(lblDcto);
		
		txtDcto = new JTextField();
		txtDcto.setEditable(false);
		txtDcto.setBounds(107, 185, 317, 20);
		pn1.add(txtDcto);
		txtDcto.setColumns(10);
		
		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotal.setBounds(10, 207, 93, 14);
		pn1.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setBounds(107, 205, 317, 20);
		pn1.add(txtTotal);
		txtTotal.setColumns(10);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaciones.setBounds(10, 227, 93, 14);
		pn1.add(lblObservaciones);
		
		srcObservaciones = new JScrollPane();
		srcObservaciones.setBounds(107, 226, 316, 49);
		pn1.add(srcObservaciones);
		
		txpObservaciones = new JTextPane();
		txpObservaciones.setBackground(UIManager.getColor("Button.shadow"));
		srcObservaciones.setViewportView(txpObservaciones);
		
		JSeparator sp1 = new JSeparator();
		sp1.setBackground(new Color(0, 51, 0));
		sp1.setBounds(10, 285, 414, 2);
		pn1.add(sp1);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.setBounds(265, 298, 106, 23);
		pn1.add(btnCerrar);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 155, 414, 2);
		pn1.add(sp);
		
		JButton btnRegistrarVenta = new JButton("Registrar Venta");
		btnRegistrarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirDialogoConfirmacionRegistro();
			}
		});
		btnRegistrarVenta.setForeground(new Color(0, 51, 0));
		btnRegistrarVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegistrarVenta.setBounds(64, 298, 135, 23);
		pn1.add(btnRegistrarVenta);
		
		//Metodo que debe ejecutar la ventana al inicializarse
		iniciarDatosVenta(identificacionCliente, identificacionVendedor, idFormaPagoCliente, subtotal, dctoCom, totalVenta, fechaLimitePago, ordenCompra, pedido);
		
		//Inicializar algunas variables
		this.modeloDetalles=modeloDetalles;
		this.items = items;
		
		
	}
	
	//Metodo para iniciar los datos de la venta a esta ventana
	private void iniciarDatosVenta(String identificacionCliente, String identificacionVendedor, int idFormaPagoCliente, String subtotal, String dctoCom, String totalVenta, Date fechaLimitePago, String ordenCompra, String pedido) {
		DelegadoCliente delegadoCliente = new DelegadoCliente();
		DelegadoVendedor delegadoVendedor = new DelegadoVendedor();
		DelegadoFormaPagoCliente delegadoFormaPagoCliente = new DelegadoFormaPagoCliente();
		
		datosCliente = delegadoCliente.traerClientePorIdentificacion(identificacionCliente);
		datosVendedor = delegadoVendedor.encontrarPorVendedor(identificacionVendedor);
		datosFormaPago = delegadoFormaPagoCliente.traerFormaPagoClientePorIdFormaPago(idFormaPagoCliente);
		
		txtCliente.setText(datosCliente.get(0).getNombre());
		if("".equals(ordenCompra)){
			txtOrdenCompra.setText("0");
		}else{
			txtOrdenCompra.setText(ordenCompra);
		}
		
		if("".equals(pedido)){
			txtPedido.setText("0");
		}else{
			txtPedido.setText(pedido);
		}
		txtVendedor.setText(datosVendedor.getNombre());
		txtFormaPago.setText(datosFormaPago.get(0).getDescripcion());
		txtFechaLimite.setText(convertirDateAString(fechaLimitePago));
		this.fechaLimitePago = fechaLimitePago;
		txtSubtotal.setText(subtotal);
		txtDcto.setText(dctoCom);
		txtTotal.setText(totalVenta);
	}
	
	//Metodo para convertir una fecha de tipo date a una cadena
	public String convertirDateAString(Date strFecha){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(strFecha);
		
		return fecha;
	}
	
	//Metodo para convertir un numero en formato de pesos y decimales en un numero corriente
	private String desformatearNumero(String numero){
		String numeroReemplazado=numero.replace("$", "");
		String numeroReemplazado1=numeroReemplazado.replace(".", "");
		String numeroReemplazado2=numeroReemplazado1.replace(",", ".");
		return numeroReemplazado2;
	}
	
	//Metodo para abrir ventanta de confirmacion de registro	
	private void abrirDialogoConfirmacionRegistro() {
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de grabar esta venta?", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarVenta();
		}else{
		
		}
	}
	
	//Metodo para registrar la venta a credito
	private void registrarVenta() {
		Vendedor vendedor = new Vendedor();
		FormaPagoCliente formaPagoCliente = new FormaPagoCliente();
		double acumulador = 0;
		
		ventaArticulos.setFechaGeneracion(new Date());
		cliente.setIdentificacion(datosCliente.get(0).getIdentificacion());
		ventaArticulos.setClientes(cliente);
		ventaArticulos.setOrdCompra(Integer.parseInt(txtOrdenCompra.getText()));
		ventaArticulos.setPedido(Integer.parseInt(txtPedido.getText()));
		vendedor.setIdentificacion(datosVendedor.getIdentificacion());
		ventaArticulos.setVendedores(vendedor);
		formaPagoCliente.setIdFormaPagoCliente(datosFormaPago.get(0).getIdFormaPagoCliente());
		ventaArticulos.setFormaPagoCliente(formaPagoCliente);
		ventaArticulos.setFechaLimitePago(fechaLimitePago);
		ventaArticulos.setDescuento(Double.parseDouble(txtDcto.getText()));
		for(int i=0; i< modeloDetalles.getRowCount();i++){
			acumulador = obtenerCostoArticulo(Integer.parseInt( modeloDetalles.getValueAt(i, 0).toString()));
			totalCostoVenta += acumulador;
		}
		ventaArticulos.setCostoVenta(totalCostoVenta);
		ventaArticulos.setSubtotal(Double.parseDouble(desformatearNumero(txtSubtotal.getText())));
		ventaArticulos.setTotalVenta(Double.parseDouble(desformatearNumero(txtTotal.getText())));
		ventaArticulos.setEstadoPago("Pendiente");
		ventaArticulos.setItems(Integer.parseInt(items));
		ventaArticulos.setObservaciones(txpObservaciones.getText());
		ventaArticulos.setEstadoActividad("Activo");
		
		delegadoVentaArticulos.insertarVentaArticulos(ventaArticulos);
		
		ultimaVentaArticulo = delegadoVentaArticulos.traerUltimaVentaArticulo();
		
		registrarDetalleVenta();
		generarReporteFactura();
		contabilizarVentaCredito();
		abrirDialogoVentaRegistrada();
	}

	//Metodo para abrir el dialogo de registro satisfactorioa de la venta
	private void abrirDialogoVentaRegistrada() {
		JOptionPane.showMessageDialog(null, "Se ha registrado esta venta satisfactoriamente con consecutivo N° "+ultimaVentaArticulo.get(0).getIdVenta());
		dispose();
		VentRegistrarVentaArticulos.limpiarDatos();
	}
	
	//Metodo para registra el detalle de la venta de articulos
	private void registrarDetalleVenta() {
		DetalleVenta detalleVenta = new DetalleVenta();
		Articulo articulo = new Articulo();
		
		for(int i=0; i< modeloDetalles.getRowCount();i++){
			ventaArticulos.setIdVenta(ultimaVentaArticulo.get(0).getIdVenta());
			detalleVenta.setVentaArticulos(ventaArticulos);
			articulo.setCodigo(Integer.parseInt(modeloDetalles.getValueAt(i, 0).toString()));
			detalleVenta.setArticulo(articulo);
			detalleVenta.setCantidad(Integer.parseInt(modeloDetalles.getValueAt(i, 3).toString()));
			detalleVenta.setCostoVentaUnitario(obtenerCostoArticulo(Integer.parseInt(modeloDetalles.getValueAt(i, 0).toString())));
			detalleVenta.setVlrUnitario(Double.parseDouble(desformatearNumero(modeloDetalles.getValueAt(i, 5).toString())));
			detalleVenta.setTotal(Double.parseDouble(desformatearNumero(modeloDetalles.getValueAt(i, 6).toString())));
			
			delegadoDetalleVenta.insertarDetalleVenta(detalleVenta);
			
			registrarDetalleVentaAlKardex();
		}
	}
	
	//Metodo para registrar el detalle de la venta al kardex electronico de articulos
	private void registrarDetalleVentaAlKardex() {
		KardexElectronico kardexElectronico = new KardexElectronico();
		DelegadoKardexElectronico delegadoKardexElectronico = new DelegadoKardexElectronico();
		List<ControlInventario> controlInventario;
		ControlInventario controlInventarioAModificar;
		Articulo articulo = new Articulo();
		
		ultimoDetalleVenta = delegadoDetalleVenta.traerUltimoRegistroDetalleVenta();
		
		if(delegadoKardexElectronico.traerUltimoRegistroKardexElectronicopPorArticulo(ultimoDetalleVenta.getArticulo().getCodigo())==null){
			
			ultimoRegistroPorArticuloKardex1 = delegadoKardexElectronico.traerUltimoRegistroKardexElectronicopPorArticulo(ultimoDetalleVenta.getArticulo().getCodigo());
			ultimoRegistroPorArticuloKardex = ultimoRegistroPorArticuloKardex1.get(0);
			
			kardexElectronico.setFecha(ultimoDetalleVenta.getVentaArticulos().getFechaGeneracion());
			kardexElectronico.setMovimiento("Venta");
			kardexElectronico.setNroDocumento(ultimoDetalleVenta.getVentaArticulos().getIdVenta());
			articulo.setCodigo(ultimoDetalleVenta.getArticulo().getCodigo());
			kardexElectronico.setArticulo(articulo);
			kardexElectronico.setCantidadEntrada(0);
			kardexElectronico.setCostoUEntrada(0);
			kardexElectronico.setCostoTEntrada(0);
			kardexElectronico.setCantidadSalida(ultimoDetalleVenta.getCantidad());
			kardexElectronico.setCostoUSalida(ultimoRegistroPorArticuloKardex.getCostoUSaldo());
			kardexElectronico.setCostoTSalida(ultimoDetalleVenta.getTotal());
			kardexElectronico.setCantidadSaldo(ultimoDetalleVenta.getCantidad());
			kardexElectronico.setCostoUSaldo(ultimoRegistroPorArticuloKardex.getCostoUSaldo());
			kardexElectronico.setCostoTSaldo(ultimoRegistroPorArticuloKardex.getCostoTSaldo());
			
			delegadoKardexElectronico.insertarKardexElectronico(kardexElectronico);
			
			controlInventario = delegadoControlInventario.traerContInventarioPorCodigoArticulo(ultimoDetalleVenta.getArticulo().getCodigo());
			controlInventarioAModificar = controlInventario.get(0);
			
			controlInventarioAModificar.setCostoPromedio(ultimoRegistroPorArticuloKardex.getCostoUSaldo());
			controlInventarioAModificar.setCantExistencia(ultimoDetalleVenta.getCantidad());
			controlInventarioAModificar.setTotalCostoInventario(ultimoRegistroPorArticuloKardex.getCostoTSaldo());
				
			delegadoControlInventario.actualizarControlInventario(controlInventarioAModificar);
		}else{
			
			ultimoRegistroPorArticuloKardex1 = delegadoKardexElectronico.traerUltimoRegistroKardexElectronicopPorArticulo(ultimoDetalleVenta.getArticulo().getCodigo());
			ultimoRegistroPorArticuloKardex = ultimoRegistroPorArticuloKardex1.get(0);
			
			kardexElectronico.setFecha(ultimoDetalleVenta.getVentaArticulos().getFechaGeneracion());
			kardexElectronico.setMovimiento("Venta");
			kardexElectronico.setNroDocumento(ultimoDetalleVenta.getVentaArticulos().getIdVenta());
			articulo.setCodigo(ultimoDetalleVenta.getArticulo().getCodigo());
			kardexElectronico.setArticulo(articulo);
			kardexElectronico.setCantidadEntrada(0);
			kardexElectronico.setCostoUEntrada(0);
			kardexElectronico.setCostoTEntrada(0);
			kardexElectronico.setCantidadSalida(ultimoDetalleVenta.getCantidad());
			kardexElectronico.setCostoUSalida(ultimoDetalleVenta.getVlrUnitario());
			kardexElectronico.setCostoTSalida(ultimoDetalleVenta.getTotal());
			kardexElectronico.setCantidadSaldo(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleVenta.getCantidad());
			kardexElectronico.setCostoUSaldo((ultimoRegistroPorArticuloKardex.getCostoTSaldo() - (ultimoRegistroPorArticuloKardex.getCostoUSaldo())*ultimoDetalleVenta.getCantidad())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleVenta.getCantidad()));
			kardexElectronico.setCostoTSaldo((ultimoRegistroPorArticuloKardex.getCostoTSaldo() - (ultimoRegistroPorArticuloKardex.getCostoUSaldo())*ultimoDetalleVenta.getCantidad())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleVenta.getCantidad())*(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleVenta.getCantidad()));
			kardexElectronico.setProveedorUltimaCompra("");
			
			delegadoKardexElectronico.insertarKardexElectronico(kardexElectronico);
				
			controlInventario = delegadoControlInventario.traerContInventarioPorCodigoArticulo(ultimoDetalleVenta.getArticulo().getCodigo());
			controlInventarioAModificar = controlInventario.get(0);
			
			controlInventarioAModificar.setCostoPromedio((ultimoRegistroPorArticuloKardex.getCostoTSaldo() - (ultimoRegistroPorArticuloKardex.getCostoUSaldo())*ultimoDetalleVenta.getCantidad())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleVenta.getCantidad()));
			controlInventarioAModificar.setCantExistencia(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleVenta.getCantidad());
			controlInventarioAModificar.setTotalCostoInventario((ultimoRegistroPorArticuloKardex.getCostoTSaldo() - ultimoDetalleVenta.getTotal()/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleVenta.getCantidad())*(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimoDetalleVenta.getCantidad())));
			
			delegadoControlInventario.actualizarControlInventario(controlInventarioAModificar);
		}
		
	}
	
	//Metodo para obtener el costo del articulo
	private double obtenerCostoArticulo(int codigoArticulo){
		DelegadoControlInventario delegadoControlInventario =new DelegadoControlInventario();
		ControlInventario costoArticulo =delegadoControlInventario.traerContInventarioPorCodigoArticulo(codigoArticulo).get(0);
		return costoArticulo.getCostoPromedio();
	}
	
	//Metodo para contabilizar la venta de contado
	private void contabilizarVentaCredito() {
		DelegadoContabilizacion delegadoContabilizacion = new DelegadoContabilizacion();                                                                                   
		Contabilizacion contabilizarAVenta = new Contabilizacion();                                                                 
		Contabilizacion contabilizarACliente = new Contabilizacion();
		Contabilizacion contabilizarACostoVenta = new Contabilizacion();
		Contabilizacion contabilizarAInventario = new Contabilizacion();
		
		contabilizarAVenta.setCodigoCuenta(4135);
		contabilizarAVenta.setFechaGeneracion(new Date());              
		contabilizarAVenta.setNombreCuenta("Comercio al por mayor y al por menor");
		contabilizarAVenta.setSaldoDebito(0);
		contabilizarAVenta.setSaldoCredito(Double.parseDouble(desformatearNumero(txtTotal.getText())));
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarAVenta);
		
		contabilizarACliente.setCodigoCuenta(1305);
		contabilizarACliente.setFechaGeneracion(new Date());              
		contabilizarACliente.setNombreCuenta("Clientes");
		contabilizarACliente.setSaldoDebito(Double.parseDouble(desformatearNumero(txtTotal.getText())));
		contabilizarACliente.setSaldoCredito(0);
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarACliente);
		
		contabilizarACostoVenta.setCodigoCuenta(6135);
		contabilizarACostoVenta.setFechaGeneracion(new Date());              
		contabilizarACostoVenta.setNombreCuenta("Comercio al por mayor y al por menor");
		contabilizarACostoVenta.setSaldoDebito(0);
		contabilizarACostoVenta.setSaldoCredito(totalCostoVenta);
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarACostoVenta);
		
		contabilizarAInventario.setCodigoCuenta(1435);
		contabilizarAInventario.setFechaGeneracion(new Date());              
		contabilizarAInventario.setNombreCuenta("Inventario de Mercancias");
		contabilizarAInventario.setSaldoDebito(totalCostoVenta);
		contabilizarAInventario.setSaldoCredito(0);
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarAInventario);
	}
	
	//Metodo para generar la factura en forma de reporte para luego ser impreso o guardado
	public void generarReporteFactura(){
		
	URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteFacturaVenta.jasper");
	 List<ReporteDetalle> lista = new ArrayList<ReporteDetalle>();
	 for(int i=0; i< modeloDetalles.getRowCount();i++){
		 
		 ReporteDetalle detalleVenta = new ReporteDetalle(Integer.parseInt(modeloDetalles.getValueAt(i, 0).toString()), modeloDetalles.getValueAt(i, 2).toString(), modeloDetalles.getValueAt(i, 4).toString(),Integer.parseInt(modeloDetalles.getValueAt(i, 3).toString()), modeloDetalles.getValueAt(i, 5).toString(), modeloDetalles.getValueAt(i, 6).toString());
		 lista.add(detalleVenta);
			
	 }
	        try {
	            JasperReport reporte= (JasperReport) JRLoader.loadObject(ubicacion);
	            Map<String, Object> parametros = new HashMap<String, Object>();
	            parametros.put("logoJungla", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png"));
	            parametros.put("firma", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/firma.jpg"));
	            parametros.put("idVenta", ultimaVentaArticulo.get(0).getIdVenta());
	            parametros.put("nombreCliente", ultimaVentaArticulo.get(0).getClientes().getNombre());
	            parametros.put("identCliente", ultimaVentaArticulo.get(0).getClientes().getIdentificacion());
	            parametros.put("direccion", ultimaVentaArticulo.get(0).getClientes().getDireccion());
	            parametros.put("telefono", ultimaVentaArticulo.get(0).getClientes().getTelefono());
	            parametros.put("ciudad", ultimaVentaArticulo.get(0).getClientes().getMunicipio().getNombre());
	            parametros.put("formaPago", ultimaVentaArticulo.get(0).getFormaPagoCliente().getDescripcion());
	            parametros.put("nombreVendedor", ultimaVentaArticulo.get(0).getVendedores().getNombre());
	            parametros.put("ordenCompra", String.valueOf(ultimaVentaArticulo.get(0).getOrdCompra()));
	            parametros.put("pedido", String.valueOf(ultimaVentaArticulo.get(0).getPedido()));
	            parametros.put("subtotal", formatearNumero(ultimaVentaArticulo.get(0).getSubtotal()));
	            parametros.put("descuento", formatearNumero(ultimaVentaArticulo.get(0).getDescuento()));
	            parametros.put("total", formatearNumero(ultimaVentaArticulo.get(0).getTotalVenta()));
	            parametros.put("items", String.valueOf(ultimaVentaArticulo.get(0).getItems()));
	            parametros.put("fechaFactura", convertirDateAString(ultimaVentaArticulo.get(0).getFechaGeneracion()));
	            parametros.put("fechaLimite", convertirDateAString(ultimaVentaArticulo.get(0).getFechaLimitePago()));
	            parametros.put("observaciones", ultimaVentaArticulo.get(0).getObservaciones());
	            parametros.put("anulado", "");
			    parametros.put("fechaAnulado", "");
	            
	            JasperPrint jprint= JasperFillManager.fillReport(reporte, parametros,new JRBeanCollectionDataSource(lista));
	            JasperViewer.viewReport(jprint,false);
	        } catch (JRException ex) {
	            Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
	
	//Metodo para convertir un numero corriente en formato de pesos y decimales
	private static String formatearNumero(Double numero){
		NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es","CO"));
		return formato.format(numero); 
	}
}
