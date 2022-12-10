package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.UIManager;
import javax.swing.JTextPane;

import co.com.jungla.sac.negocio.delegados.DelegadoDetalleDevolucionCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoDevolucionCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoMedioPagoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoReciboCaja;
import co.com.jungla.sac.negocio.delegados.DelegadoVentaArticulos;

import co.com.jungla.sac.persistencia.entidades.Banco;
import co.com.jungla.sac.persistencia.entidades.DetalleDevolucionCliente;
import co.com.jungla.sac.persistencia.entidades.DevolucionCliente;
import co.com.jungla.sac.persistencia.entidades.MedioPagoCliente;
import co.com.jungla.sac.persistencia.entidades.ReciboCaja;
import co.com.jungla.sac.persistencia.entidades.VentaArticulos;

public class VentMostrarDevolucionClienteRegistrada extends JDialog {

	private JPanel contentPane;
	private JTextField txtVenta;
	private JTextField txtNotaCredito;
	private JFormattedTextField txtTotalDevuelto;
	private List<ReciboCaja> ultimoRecibocaja;
	ReciboCaja reciboCaja= new ReciboCaja();
	private DevolucionCliente ultimaDevolucionCliente;
	private int idVenta;
	private Date fechaVenta;

	
	/**
	 * Create the frame.
	 */
	public VentMostrarDevolucionClienteRegistrada(DevolucionCliente ultimaDevolucionCliente, int idVenta, Date fechaVenta) {
		setTitle("Verificar los datos de la Compra");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 526);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 522, 478);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblProveedor = new JLabel("Venta");
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProveedor.setBounds(10, 13, 93, 14);
		panel.add(lblProveedor);
		
		JLabel lblFechaEntrega = new JLabel("Nota Cr\u00E9dito");
		lblFechaEntrega.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaEntrega.setBounds(10, 33, 93, 14);
		panel.add(lblFechaEntrega);
		
		JLabel lblItems = new JLabel("Total Devuelto");
		lblItems.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblItems.setBounds(10, 53, 93, 14);
		panel.add(lblItems);
		
		txtVenta = new JTextField();
		txtVenta.setEditable(false);
		txtVenta.setBounds(107, 11, 306, 20);
		panel.add(txtVenta);
		txtVenta.setColumns(10);
		
		txtNotaCredito = new JTextField();
		txtNotaCredito.setEditable(false);
		txtNotaCredito.setBounds(107, 31, 306, 20);
		panel.add(txtNotaCredito);
		txtNotaCredito.setColumns(10);
		
		txtTotalDevuelto = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtTotalDevuelto.setEditable(false);
		txtTotalDevuelto.setBounds(107, 51, 306, 20);
		panel.add(txtTotalDevuelto);
		txtTotalDevuelto.setColumns(10);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 431, 502, 2);
		panel.add(sp);
		
		JButton btnComprobante = new JButton("Aplicar Nota");
		btnComprobante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirDialogoConfirmacionRegistro();
			}
		});
		btnComprobante.setForeground(new Color(0, 51, 0));
		btnComprobante.setBackground(UIManager.getColor("Button.background"));
		btnComprobante.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnComprobante.setBounds(10, 444, 106, 23);
		panel.add(btnComprobante);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.setBounds(406, 444, 106, 23);
		panel.add(btnCerrar);
		
		txtVenta.setText(String.valueOf(idVenta));
		txtNotaCredito.setText(String.valueOf(ultimaDevolucionCliente.getIdDevolucionCliente()));
		txtTotalDevuelto.setValue(ultimaDevolucionCliente.getTotalDevolucion());
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 51, 0));
		separator.setBounds(10, 82, 502, 2);
		panel.add(separator);
		
		JButton btnImprimirNota = new JButton("Imprimir Nota");
		btnImprimirNota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirNotaCredito();
			}
		});
		btnImprimirNota.setForeground(new Color(0, 51, 0));
		btnImprimirNota.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnImprimirNota.setBounds(126, 444, 130, 23);
		panel.add(btnImprimirNota);
		
		JButton btnOtraDevolucin = new JButton("Otra Devoluci\u00F3n");
		btnOtraDevolucin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				VentBuscarVentaParaDevCliente ventBuscarVentaParaDevCliente = new VentBuscarVentaParaDevCliente();
				ventBuscarVentaParaDevCliente.setVisible(true);
			}
		});
		btnOtraDevolucin.setForeground(new Color(0, 51, 0));
		btnOtraDevolucin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOtraDevolucin.setBounds(266, 444, 130, 23);
		panel.add(btnOtraDevolucin);
		
		JTextPane txtpnlasdevolucionesSe = new JTextPane();
		txtpnlasdevolucionesSe.setContentType("text/html");
		txtpnlasdevolucionesSe.setText("<FONT FACE=\"Tahoma\" SIZE= 3>A partir de este momento se le pueden presentar 3 situaciones para saber que debe hacer con la NOTA CREDITO que se gener\u00F3 con la Devoluci\u00F3n:<br>\r\n\r\n   <OL> <LI><p align=\"justify\"><FONT COLOR=\"red\"><b>CAMBIO INMEDIATO: </b></FONT>Si se gener\u00F3 esta Devoluci\u00F3n en una venta de CONTADO y el cliente utilizar\u00E1 la NOTA CREDITO para CAMBIAR su(s) art\u00EDculo(s) por otro(s) en este mismo momento. El paso a seguir es generar la NUEVA VENTA normalmente y despu\u00E9s de VALIDAR debe elegir como MEDIO de PAGO la NOTA CREDITO correspondiente.</p>\r\n   <LI><p align=\"justify\"> <FONT COLOR=\"red\"><b>CAMBIO PARA DESPUES: </b></FONT>Si se gener\u00F3 esta Devoluci\u00F3n en una venta de CONTADO y el cliente utilizar\u00E1 la NOTA CREDITO para CAMBIAR su(s) art\u00EDculo(s) por otro(s) en otro momento por que hoy no hay lo que necesita o por cualquier otra raz\u00F3n. El paso a seguir es IMPRIMIR la NOTA CREDITO para que le sirva al cliente como comprobante de la devoluci\u00F3n y la pueda presentar al momento del cambio. El d\u00EDa que el cliente vuelva a hacer el cambio, se debe generar la NUEVA VENTA normalmente y despu\u00E9s de VALIDAR debe elegir como MEDIO de PAGO la NOTA CREDITO que el cliente presenta. </p>\r\n<LI><p align=\"justify\"><FONT COLOR=\"red\"><b>VENTAS CREDITO: </b></FONT>Si se gener\u00F3 esta Devoluci\u00F3n en una venta de CREDITO y la mercanc\u00EDa devuelta no tiene que necesariamente ser cambiada por otra. En este caso se recomienda usar el bot\u00F3n APLICAR NOTA para disminuir el SALDO de la deuda del cliente en esta Venta espec\u00EDfica. El sistema generar\u00E1 autom\u00E1ticamente un RECIBO de CAJA relacionando el pago aplicado.</p><br></OL></FONT>");
		txtpnlasdevolucionesSe.setBounds(10, 95, 502, 325);
		panel.add(txtpnlasdevolucionesSe);
		
		this.ultimaDevolucionCliente = ultimaDevolucionCliente;
		this.idVenta=idVenta;
		this.fechaVenta = fechaVenta;
	}
	
	//Metodo para generar un recibo de caja luego de haberse aplicado la nota credito como pago a la venta como tal
	private void registrarReciboCaja(){
		DelegadoReciboCaja delegadoReciboCaja = new DelegadoReciboCaja();
		VentaArticulos ventaArticulos = new VentaArticulos();
		DelegadoVentaArticulos delegadoVentaArticulos = new DelegadoVentaArticulos();
		VentaArticulos ventaDevolucion;
		
		reciboCaja.setEstadoActividad("Activo");
		reciboCaja.setFechaVenta(fechaVenta);
		reciboCaja.setFechaRecaudo(new Date());
		reciboCaja.setFechaGeneracion(new Date());
		reciboCaja.setTotalDocs(Double.parseDouble(txtTotalDevuelto.getValue().toString()));
		reciboCaja.setTotalRecibido(0);
		reciboCaja.setTotalNCredito(Double.parseDouble(txtTotalDevuelto.getValue().toString()));
		/*List<VentaArticulos>listaVentas = new ArrayList<VentaArticulos>();
		listaVentas.add(delegadoVentaArticulos.traerVentaPorCodigo(idVenta).get(0));
		reciboCaja.setVentaArticulos(listaVentas);*/
		
		delegadoReciboCaja.insertarReciboCaja(reciboCaja);
		ultimoRecibocaja = delegadoReciboCaja.traerUltimoReciboCaja();
		
		registarMedioPago();
		abrirDialogoNotaCreditoCompensada();
	}
	
	//Metodo para registrar la nota credito como medio de pago
	private void registarMedioPago() {
		MedioPagoCliente medioPago = new MedioPagoCliente();
		DelegadoMedioPagoCliente delegadoMedioPago = new DelegadoMedioPagoCliente();
			
		reciboCaja.setIdReciboCaja(ultimoRecibocaja.get(0).getIdReciboCaja());
		medioPago.setReciboCaja(reciboCaja);
		medioPago.setNombreMedioPago("Nota Crédito");
		medioPago.setValor(Double.parseDouble(txtTotalDevuelto.getValue().toString()));
		medioPago.setDocReferencia(0);
		medioPago.setDocReferencia(Integer.parseInt(txtNotaCredito.getText()));
		medioPago.setBanco(null);
		medioPago.setFranquicia("");
		
		delegadoMedioPago.insertarMedioPago(medioPago);
	}
	
	//Metodo para abrir ventanta de confirmacion de registro	
	private void abrirDialogoConfirmacionRegistro() {
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de Aplicar esta Nota Crédito para disminuir el saldo del cliente en esta venta?", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarReciboCaja();
		}else{
		
		}
	}
			
	//Metodo para abrir el dialogo de registro satisfactorioa de la venta
	private void abrirDialogoNotaCreditoCompensada() {
		cambiarEstadoNotaCredito();
		JOptionPane.showMessageDialog(null, "Se ha generado el RECIBO DE CAJA con consecutivo N° "+ultimoRecibocaja.get(0).getIdReciboCaja()+" satisfactoriamente en el cual queda compensada la NOTA CRÉDITO");
		dispose();
	}
	
	//Metodo para cambiar el estado de la devolucion cliente de pendiente a compensada
	private void cambiarEstadoNotaCredito(){
		DelegadoDevolucionCliente delegadoDevolucionCliente =new DelegadoDevolucionCliente();
		DevolucionCliente devolucionAModificar = new DevolucionCliente();
		devolucionAModificar = ultimaDevolucionCliente;
		
		devolucionAModificar.setEstado("Compensada");
		delegadoDevolucionCliente.actualizarDevolucionCliente(devolucionAModificar);
	}
	
	//Metodo para imprimir nota credito
	private void imprimirNotaCredito() {
		// TODO Auto-generated method stub
		
	}
}
