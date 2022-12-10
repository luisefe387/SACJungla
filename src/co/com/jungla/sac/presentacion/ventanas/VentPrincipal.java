package co.com.jungla.sac.presentacion.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.StringTokenizer;

import co.com.jungla.sac.negocio.delegados.DelegadoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoDepartamento;
import co.com.jungla.sac.negocio.delegados.DelegadoFormaPagoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoMunicipio;
import co.com.jungla.sac.negocio.delegados.DelegadoRecordatorio;
import co.com.jungla.sac.negocio.delegados.DelegadoTipoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoVendedor;

import co.com.jungla.sac.persistencia.entidades.Cliente;
import co.com.jungla.sac.persistencia.entidades.Departamento;
import co.com.jungla.sac.persistencia.entidades.FormaPagoCliente;
import co.com.jungla.sac.persistencia.entidades.Municipio;
import co.com.jungla.sac.persistencia.entidades.Recordatorio;
import co.com.jungla.sac.persistencia.entidades.TipoCliente;
import co.com.jungla.sac.persistencia.entidades.Vendedor;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

/**
 * clase ventana para definir los atributos y metodos para la construccion de la ventana principal de la aplicacion
 * @author Luis Fernando Pedroza T.
 * @version: 19/09/2016
 */
public class VentPrincipal extends JFrame {

	private JPanel contentPane;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentPrincipal frame = new VentPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentPrincipal.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("SACJungla (Sistema Administrativo para la Empesa de Bordados Industriales  \"Jungla\" Armenia)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(20, 20, 1101, 699);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar mbPrincipal = new JMenuBar();
		mbPrincipal.setForeground(new Color(0, 51, 0));
		mbPrincipal.setBorderPainted(false);
		mbPrincipal.setBounds(0, 0, 980, 23);
		contentPane.add(mbPrincipal);
		
		JMenu mnCompras = new JMenu("Compras");
		mnCompras.setForeground(new Color(0, 51, 0));
		mnCompras.setFont(new Font("Tahoma", Font.BOLD, 14));
		mbPrincipal.add(mnCompras);
		
		JMenu mnRegistros = new JMenu("Registros");
		mnRegistros.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnRegistros.setForeground(new Color(0, 51, 0));
		mnCompras.add(mnRegistros);
		
		JMenuItem mntmNuevoProveedor = new JMenuItem("Nuevo Proveedor");
		mntmNuevoProveedor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmNuevoProveedor.setForeground(new Color(0, 51, 0));
		mnRegistros.add(mntmNuevoProveedor);
		
		JMenuItem mntmNuevaLineaArticulo = new JMenuItem("Nueva Linea de Articulos");
		mntmNuevaLineaArticulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmNuevaLineaArticulo.setForeground(new Color(0, 51, 0));
		mnRegistros.add(mntmNuevaLineaArticulo);
		
		JMenuItem mntmNuevoArticulo = new JMenuItem("Nuevo Articulo");
		mntmNuevoArticulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmNuevoArticulo.setForeground(new Color(0, 51, 0));
		mnRegistros.add(mntmNuevoArticulo);
		
		JMenuItem mntmNuevaFormaPagoProv = new JMenuItem("Nuevo Medio de Pago (Prov)");
		mntmNuevaFormaPagoProv.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmNuevaFormaPagoProv.setForeground(new Color(0, 51, 0));
		mnRegistros.add(mntmNuevaFormaPagoProv);
		
		JMenuItem mntmNuevaUnidadMedida = new JMenuItem("Nueva Unidad de Medida");
		mntmNuevaUnidadMedida.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmNuevaUnidadMedida.setForeground(new Color(0, 51, 0));
		mnRegistros.add(mntmNuevaUnidadMedida);
		mntmNuevaUnidadMedida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarUnidadMedida ventRegistrarUnidadMedida = new VentRegistrarUnidadMedida();
				ventRegistrarUnidadMedida.setVisible(true);
			}
		});
		mntmNuevaFormaPagoProv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarMediosPagoProv ventRegistrarFormaPago = new VentRegistrarMediosPagoProv();
				ventRegistrarFormaPago.setVisible(true);
			}
		});
		mntmNuevoArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarArticulo ventRegistrarArticulo = new VentRegistrarArticulo();
				ventRegistrarArticulo.setVisible(true);
			}
		});
		mntmNuevaLineaArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarLineaArticulos ventRegistrarLineaArticulos = new VentRegistrarLineaArticulos(/*null, true*/);
				ventRegistrarLineaArticulos.setVisible(true);
			}
		});
		mntmNuevoProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarProveedor ventRegistrarProveedor = new VentRegistrarProveedor();
				ventRegistrarProveedor.setVisible(true);
			}
		});
		
		JMenuItem mntmCompraArticulos = new JMenuItem("COMPRA DE ARTICULOS");
		mntmCompraArticulos.setForeground(new Color(0, 51, 0));
		mntmCompraArticulos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnCompras.add(mntmCompraArticulos);
		mntmCompraArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarCompraArticulos ventRegistrarCompraArticulos = new VentRegistrarCompraArticulos();
				ventRegistrarCompraArticulos.setVisible(true);
			}
		});
		
		JMenuItem mntmOrdenCompras = new JMenuItem("Orden de Compras");
		mntmOrdenCompras.setForeground(new Color(0, 51, 0));
		mntmOrdenCompras.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnCompras.add(mntmOrdenCompras);
		mntmOrdenCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarOrdenCompra ventRegistrarOrdenCompra = new VentRegistrarOrdenCompra();
				ventRegistrarOrdenCompra.setVisible(true);
			}
		});
		
		JMenu mnEgresoPagoCxP = new JMenu("Egresos,  Pagos y CXP");
		mnEgresoPagoCxP.setForeground(new Color(0, 51, 0));
		mnEgresoPagoCxP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnCompras.add(mnEgresoPagoCxP);
		
		JMenuItem mntmRegistrarEgreso = new JMenuItem("Registrar un egreso/gasto con pago inmediato");
		mntmRegistrarEgreso.setForeground(new Color(0, 51, 0));
		mntmRegistrarEgreso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmRegistrarEgreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarEgreso ventRegistrarGastoYOEgreso = new VentRegistrarEgreso();
				ventRegistrarGastoYOEgreso.setVisible(true);
			}
		});
		mnEgresoPagoCxP.add(mntmRegistrarEgreso);
		
		JMenuItem mntmPagoAbonoCxP = new JMenuItem("Pagar/Abonar una CXP ya registrada");
		mntmPagoAbonoCxP.setForeground(new Color(0, 51, 0));
		mntmPagoAbonoCxP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmPagoAbonoCxP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentBuscarCxP ventBuscarCxP = new VentBuscarCxP();
				ventBuscarCxP.setVisible(true);
			}
		});
		mnEgresoPagoCxP.add(mntmPagoAbonoCxP);
		
		JMenuItem mntmRegistrarCxp = new JMenuItem("Registrar una CXP para pago futuro");
		mntmRegistrarCxp.setForeground(new Color(0, 51, 0));
		mntmRegistrarCxp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmRegistrarCxp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarCuentaXPagar ventRegistrarCuentaXPagar = new VentRegistrarCuentaXPagar();
				ventRegistrarCuentaXPagar.setVisible(true);
			}
		});
		mnEgresoPagoCxP.add(mntmRegistrarCxp);
		
		JMenuItem mntmRegistrarAnticipoProv = new JMenuItem("Registrar Anticipo a Proveedores");
		mntmRegistrarAnticipoProv.setForeground(new Color(0, 51, 0));
		mntmRegistrarAnticipoProv.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmRegistrarAnticipoProv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarAnticipoProveedor ventRegistrarAnticipoProveedor = new VentRegistrarAnticipoProveedor();
				ventRegistrarAnticipoProveedor.setVisible(true);
			}
		});
		mnEgresoPagoCxP.add(mntmRegistrarAnticipoProv);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Devoluciones de Proveedores (Nota Debito)");
		mntmNewMenuItem.setForeground(new Color(0, 51, 0));
		mntmNewMenuItem.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentBuscarCompraParaDevProveedor ventBuscarCompraParaDevProveedor = new VentBuscarCompraParaDevProveedor();
				ventBuscarCompraParaDevProveedor.setVisible(true);
			}
		});
		mnCompras.add(mntmNewMenuItem);
		
		JSeparator sp = new JSeparator();
		mnCompras.add(sp);
		
		JMenu mnConsultasCompras = new JMenu("Consultas");
		mnConsultasCompras.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnConsultasCompras.setForeground(new Color(0, 51, 0));
		mnCompras.add(mnConsultasCompras);
		
		JMenuItem mntmConsultaArticulos = new JMenuItem("Articulos");
		mntmConsultaArticulos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmConsultaArticulos.setForeground(new Color(0, 51, 0));
		mntmConsultaArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentConsultarArticulos ventConsultarArticulos = new VentConsultarArticulos();
				ventConsultarArticulos.setVisible(true);
			}
		});
		mnConsultasCompras.add(mntmConsultaArticulos);
		
		JMenuItem mntmConsultaLineas = new JMenuItem("Lineas");
		mntmConsultaLineas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmConsultaLineas.setForeground(new Color(0, 51, 0));
		mntmConsultaLineas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentMostrarLineasArticulos ventMostrarLineasArticulos = new VentMostrarLineasArticulos();
				ventMostrarLineasArticulos.setVisible(true);
			}
		});
		mnConsultasCompras.add(mntmConsultaLineas);
		
		JMenuItem mntmConsultaProveedores = new JMenuItem("Proveedores");
		mntmConsultaProveedores.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmConsultaProveedores.setForeground(new Color(0, 51, 0));
		mntmConsultaProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentConsultarProveedores ventConsultarProveedores= new VentConsultarProveedores();
				ventConsultarProveedores.setVisible(true);
			}
		});
		mnConsultasCompras.add(mntmConsultaProveedores);
		
		JMenu mnReportesCompras = new JMenu("Reportes");
		mnReportesCompras.setForeground(new Color(0, 51, 0));
		mnReportesCompras.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnCompras.add(mnReportesCompras);
		
		JMenuItem mntReporteCompras = new JMenuItem("Reporte de Compras");
		mntReporteCompras.setForeground(new Color(0, 51, 0));
		mntReporteCompras.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnReportesCompras.add(mntReporteCompras);
		mntReporteCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentReportarCompra ventReportarCompra = new VentReportarCompra();
				ventReportarCompra.setVisible(true);
			}
		});
		
		JMenuItem mntmReporteOrdenes = new JMenuItem("Reporte de Ordenes a Compras");
		mntmReporteOrdenes.setForeground(new Color(0, 51, 0));
		mntmReporteOrdenes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnReportesCompras.add(mntmReporteOrdenes);
		mntmReporteOrdenes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentReportarOrdenCompra ventReportarOrdenCompra = new VentReportarOrdenCompra();
				ventReportarOrdenCompra.setVisible(true);
			}
		});
		
		JMenuItem mntmReporteCxP = new JMenuItem("Reporte de Cuentas X Pagar");
		mntmReporteCxP.setForeground(new Color(0, 51, 0));
		mntmReporteCxP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmReporteCxP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentReportarCuentaXPagar ventReportarCuentaXPagar = new VentReportarCuentaXPagar();
				ventReportarCuentaXPagar.setVisible(true);
			}
		});
		mnReportesCompras.add(mntmReporteCxP);
		
		JMenuItem mntmReporteEgresos = new JMenuItem("Reporte de Egresos (Gastos y Pagos)");
		mntmReporteEgresos.setForeground(new Color(0, 51, 0));
		mntmReporteEgresos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmReporteEgresos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentReportarEgreso ventReportarEgreso = new VentReportarEgreso();
				ventReportarEgreso.setVisible(true);
			}
		});
		mnReportesCompras.add(mntmReporteEgresos);
		
		JMenuItem mntmReporteNotasDebito = new JMenuItem("Reporte de Notas D\u00E9bito");
		mntmReporteNotasDebito.setForeground(new Color(0, 51, 0));
		mntmReporteNotasDebito.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmReporteNotasDebito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentReportarNotasDebito ventReportarNotasDebito = new VentReportarNotasDebito();
				ventReportarNotasDebito.setVisible(true);
			}
		});
		mnReportesCompras.add(mntmReporteNotasDebito);
		
		JMenuItem mntmReporteAnticiposProv = new JMenuItem("Reporte de Anticipos a Proveedores");
		mntmReporteAnticiposProv.setForeground(new Color(0, 51, 0));
		mntmReporteAnticiposProv.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmReporteAnticiposProv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentReportarAnticipoProveedor ventReportarAnticipoProveedor = new VentReportarAnticipoProveedor();
				ventReportarAnticipoProveedor.setVisible(true);
			}
		});
		mnReportesCompras.add(mntmReporteAnticiposProv);
		
		JMenu mnInventario = new JMenu("Inventario");
		mnInventario.setForeground(new Color(0, 51, 0));
		mnInventario.setFont(new Font("Tahoma", Font.BOLD, 14));
		mbPrincipal.add(mnInventario);
		
		JMenuItem mntmEntradaArticulos = new JMenuItem("Entrada de Articulos al Inventario");
		mntmEntradaArticulos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmEntradaArticulos.setForeground(new Color(0, 51, 0));
		mntmEntradaArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarEntradaArticulos ventRegistrarEntradaArticulos = new VentRegistrarEntradaArticulos();
				ventRegistrarEntradaArticulos.setVisible(true);
			}
		});
		mnInventario.add(mntmEntradaArticulos);
		
		JMenuItem mntmSalidaArticulos = new JMenuItem("Salida de Articulos al Inventario");
		mntmSalidaArticulos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmSalidaArticulos.setForeground(new Color(0, 51, 0));
		mntmSalidaArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarSalidaArticulos ventRegistrarSalidaArticulos = new VentRegistrarSalidaArticulos();
				ventRegistrarSalidaArticulos.setVisible(true);
			}
		});
		mnInventario.add(mntmSalidaArticulos);
		
		JMenuItem mntmControlInventarios = new JMenuItem("Control de Inventarios");
		mntmControlInventarios.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmControlInventarios.setForeground(new Color(0, 51, 0));
		mntmControlInventarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentControlInventario ventControlInventario = new VentControlInventario();
				ventControlInventario.setVisible(true);
			}
		});
		
		JMenuItem mntmRealizarInventario = new JMenuItem("Realizar Inventario");
		mntmRealizarInventario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmRealizarInventario.setForeground(new Color(0, 51, 0));
		mntmRealizarInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentHacerInventario ventHacerInventario = new VentHacerInventario();
				ventHacerInventario.setVisible(true);
			}
		});
		mnInventario.add(mntmRealizarInventario);
		mnInventario.add(mntmControlInventarios);
		
		JSeparator sp1 = new JSeparator();
		mnInventario.add(sp1);
		
		JMenuItem mntmKardexElectronico = new JMenuItem("KARDEX Electronico para Articulos");
		mntmKardexElectronico.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmKardexElectronico.setForeground(new Color(0, 51, 0));
		mntmKardexElectronico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentMostrarKardexElectronicoArticulos ventkardexElectronico = new VentMostrarKardexElectronicoArticulos();
				ventkardexElectronico.setVisible(true);
			}
		});
		mnInventario.add(mntmKardexElectronico);
		
		JMenu mnReportes = new JMenu("Reportes");
		mnReportes.setForeground(new Color(0, 51, 0));
		mnReportes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnInventario.add(mnReportes);
		
		JMenuItem mntmReporteSalidaArticulos = new JMenuItem("Reporte de Salida de Articulos");
		mntmReporteSalidaArticulos.setForeground(new Color(0, 51, 0));
		mntmReporteSalidaArticulos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmReporteSalidaArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentReportarSalidaArticulos ventReportarSalidaArticulos = new VentReportarSalidaArticulos();
				ventReportarSalidaArticulos.setVisible(true);
			}
		});
		mnReportes.add(mntmReporteSalidaArticulos);
		
		JMenuItem mntmReporteEntradaArticulos = new JMenuItem("Reporte de Entrada de Articulos");
		mntmReporteEntradaArticulos.setForeground(new Color(0, 51, 0));
		mntmReporteEntradaArticulos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmReporteEntradaArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentReportarEntradaArticulos ventReportarEntradasArticulos = new VentReportarEntradaArticulos();
				ventReportarEntradasArticulos.setVisible(true);
			}
		});
		mnReportes.add(mntmReporteEntradaArticulos);
		
		JMenu mnVentas = new JMenu("Ventas");
		mnVentas.setForeground(new Color(0, 51, 0));
		mnVentas.setFont(new Font("Tahoma", Font.BOLD, 14));
		mbPrincipal.add(mnVentas);
		
		JMenu mnRegistrosVentas = new JMenu("Registros");
		mnRegistrosVentas.setForeground(new Color(0, 51, 0));
		mnRegistrosVentas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnVentas.add(mnRegistrosVentas);
		
		JMenuItem mntmNuevoTipoCliente = new JMenuItem("Nuevo Tipo de Cliente");
		mntmNuevoTipoCliente.setForeground(new Color(0, 51, 0));
		mntmNuevoTipoCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmNuevoTipoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarTipoCliente ventRegistrarTipoCliente = new VentRegistrarTipoCliente();
				ventRegistrarTipoCliente.setVisible(true);
			}
		});
		mnRegistrosVentas.add(mntmNuevoTipoCliente);
		
		JMenuItem mntmNuevoCliente = new JMenuItem("Nuevo Cliente");
		mntmNuevoCliente.setForeground(new Color(0, 51, 0));
		mntmNuevoCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmNuevoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarCliente ventRegistrarCliente = new VentRegistrarCliente();
				ventRegistrarCliente.setVisible(true);
			}
		});
		mnRegistrosVentas.add(mntmNuevoCliente);
		
		JMenuItem mntmNuevaFormaPagoClie = new JMenuItem("Nueva Forma Pago (Cliente)");
		mntmNuevaFormaPagoClie.setForeground(new Color(0, 51, 0));
		mntmNuevaFormaPagoClie.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmNuevaFormaPagoClie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarFormaPagoCliente ventRegistrarFormaPagoCliente = new VentRegistrarFormaPagoCliente();
				ventRegistrarFormaPagoCliente.setVisible(true);
			}
		});
		mnRegistrosVentas.add(mntmNuevaFormaPagoClie);
		
		JMenuItem mntmNuevoBanco = new JMenuItem("Nuevo Banco");
		mntmNuevoBanco.setForeground(new Color(0, 51, 0));
		mntmNuevoBanco.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmNuevoBanco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarBanco ventRegistrarBanco = new VentRegistrarBanco();
				ventRegistrarBanco.setVisible(true);
			}
		});
		mnRegistrosVentas.add(mntmNuevoBanco);
		
		JMenuItem mntmNuevoVendedor = new JMenuItem("Nuevo Vendedor");
		mntmNuevoVendedor.setForeground(new Color(0, 51, 0));
		mntmNuevoVendedor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmNuevoVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarVendedor ventRegistrarVendedor = new VentRegistrarVendedor();
				ventRegistrarVendedor.setVisible(true);
			}
		});
		mnRegistrosVentas.add(mntmNuevoVendedor);
		
		JMenuItem mntmFacturar = new JMenuItem("VENTA DE ARTICULOS");
		mntmFacturar.setForeground(new Color(0, 51, 0));
		mntmFacturar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmFacturar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarVentaArticulos ventaRegistrarVentaArticulos = new VentRegistrarVentaArticulos();
				ventaRegistrarVentaArticulos.setVisible(true);
			}
		});
		mnVentas.add(mntmFacturar);
		
		JMenuItem mntmCajaRecaudos = new JMenuItem("CAJA / RECAUDOS");
		mntmCajaRecaudos.setForeground(new Color(0, 51, 0));
		mntmCajaRecaudos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmCajaRecaudos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentBuscarClienteRecaudosCaja ventBuscarClienteRecaudosCaja = new VentBuscarClienteRecaudosCaja();
				ventBuscarClienteRecaudosCaja.setVisible(true);
			}
		});
		mnVentas.add(mntmCajaRecaudos);
		
		JMenuItem mntmDevolucionesClientes = new JMenuItem("Devoluciones de Cliente (Nota Credito)");
		mntmDevolucionesClientes.setForeground(new Color(0, 51, 0));
		mntmDevolucionesClientes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmDevolucionesClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentBuscarVentaParaDevCliente ventaParaDevCliente = new VentBuscarVentaParaDevCliente();
				ventaParaDevCliente.setVisible(true);
			}
		});
		mnVentas.add(mntmDevolucionesClientes);
		
		JMenuItem mntmCotizaciones = new JMenuItem("Cotizaciones");
		mntmCotizaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentBuscarClienteCotizacion ventBuscarClienteCotizacion = new VentBuscarClienteCotizacion();
				ventBuscarClienteCotizacion.setVisible(true);
				/*VentRegistrarCotizacion ventRegistrarCotizacion = new VentRegistrarCotizacion();
				ventRegistrarCotizacion.setVisible(true);*/
			}
		});
		mntmCotizaciones.setForeground(new Color(0, 51, 0));
		mntmCotizaciones.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnVentas.add(mntmCotizaciones);
		
		JSeparator sp2 = new JSeparator();
		mnVentas.add(sp2);
		
		JMenu mnConsultasVentas = new JMenu("Consultas");
		mnConsultasVentas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnConsultasVentas.setForeground(new Color(0, 51, 0));
		mnVentas.add(mnConsultasVentas);
		
		JMenuItem mntmConsultaBancos = new JMenuItem("Bancos");
		mntmConsultaBancos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmConsultaBancos.setForeground(new Color(0, 51, 0));
		mntmConsultaBancos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentMostrarBancos ventMostrarBancos = new VentMostrarBancos();
				ventMostrarBancos.setVisible(true);
			}
		});
		mnConsultasVentas.add(mntmConsultaBancos);
		
		JMenuItem mntmConsultaClientes = new JMenuItem("Clientes");
		mntmConsultaClientes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmConsultaClientes.setForeground(new Color(0, 51, 0));
		mntmConsultaClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentConsultarClientes ventConsultarClientes = new VentConsultarClientes();
				ventConsultarClientes.setVisible(true);
			}
		});
		mnConsultasVentas.add(mntmConsultaClientes);
		
		JMenuItem mntmConsultaCotizaciones = new JMenuItem("Cotizaciones");
		mntmConsultaCotizaciones.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmConsultaCotizaciones.setForeground(new Color(0, 51, 0));
		mntmConsultaCotizaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentConsultarCotizaciones ventConsultarCotizaciones = new VentConsultarCotizaciones();
				ventConsultarCotizaciones.setVisible(true);
			}
		});
		mnConsultasVentas.add(mntmConsultaCotizaciones);
		
		JMenu mnReportesVentas = new JMenu("Reportes");
		mnReportesVentas.setForeground(new Color(0, 51, 0));
		mnReportesVentas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnVentas.add(mnReportesVentas);
		
		JMenuItem mntmReporteVentas = new JMenuItem("Reporte de Ventas");
		mntmReporteVentas.setForeground(new Color(0, 51, 0));
		mntmReporteVentas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmReporteVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentReportarVenta ventReportarVenta = new VentReportarVenta();
				ventReportarVenta.setVisible(true);
			}
		});
		mnReportesVentas.add(mntmReporteVentas);
		
		JMenuItem mntmReporteCarteraPendiente = new JMenuItem("Reporte de Cartera Pendiente");
		mntmReporteCarteraPendiente.setForeground(new Color(0, 51, 0));
		mntmReporteCarteraPendiente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmReporteCarteraPendiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentReportarCarteraPendiente ventReportarCarteraPendiente = new VentReportarCarteraPendiente();
				ventReportarCarteraPendiente.setVisible(true);
			}
		});
		mnReportesVentas.add(mntmReporteCarteraPendiente);
		
		JMenuItem mntmReporteRecibosCaja = new JMenuItem("Reporte de Recibos Caja");
		mntmReporteRecibosCaja.setForeground(new Color(0, 51, 0));
		mntmReporteRecibosCaja.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmReporteRecibosCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentReportarRecibosCaja ventReportarRecibosCaja = new VentReportarRecibosCaja();
				ventReportarRecibosCaja.setVisible(true);
			}
		});
		mnReportesVentas.add(mntmReporteRecibosCaja);
		
		JMenuItem mntmReporteNotasCredito = new JMenuItem("Reporte de Notas Cr\u00E9dito");
		mntmReporteNotasCredito.setForeground(new Color(0, 51, 0));
		mntmReporteNotasCredito.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmReporteNotasCredito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentReportarNotasCredito ventReportarNotasCredito = new VentReportarNotasCredito();
				ventReportarNotasCredito.setVisible(true);
			}
		});
		mnReportesVentas.add(mntmReporteNotasCredito);
		
		JMenu mnUtilidades = new JMenu("Utilidades");
		mnUtilidades.setForeground(new Color(0, 51, 0));
		mnUtilidades.setFont(new Font("Tahoma", Font.BOLD, 14));
		mbPrincipal.add(mnUtilidades);
		
		JMenuItem mntmEstadoResultados = new JMenuItem("Estado de Resultados (PyG)");
		mntmEstadoResultados.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmEstadoResultados.setForeground(new Color(0, 51, 0));
		mntmEstadoResultados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentReportarEstadoResultados ventReportarEstadoResultados = new VentReportarEstadoResultados();
				ventReportarEstadoResultados.setVisible(true);
			}
		});
		
		JMenuItem mntmBalanceGeneral = new JMenuItem("Balance General");
		mntmBalanceGeneral.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmBalanceGeneral.setForeground(new Color(0, 51, 0));
		mntmBalanceGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentReportarBalanceGeneral ventReportarBalanceGeneral = new VentReportarBalanceGeneral();
				ventReportarBalanceGeneral.setVisible(true);
			}
		});
		mnUtilidades.add(mntmBalanceGeneral);
		mnUtilidades.add(mntmEstadoResultados);
		
		JMenuItem mntmCatalogoArticulos = new JMenuItem("Catalogo de Articulos con Foto");
		mntmCatalogoArticulos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmCatalogoArticulos.setForeground(new Color(0, 51, 0));
		mntmCatalogoArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentMostrarCatalogoArticulos ventMostrarCatalogoArticulos = new VentMostrarCatalogoArticulos();
				ventMostrarCatalogoArticulos.setVisible(true);
			}
		});
		mnUtilidades.add(mntmCatalogoArticulos);
		
		JMenuItem mntmImprimirDocumentos = new JMenuItem("Imprimir documentos");
		mntmImprimirDocumentos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmImprimirDocumentos.setForeground(new Color(0, 51, 0));
		mntmImprimirDocumentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentImprimirDocumentos ventImprimirDocumentos = new VentImprimirDocumentos();
				ventImprimirDocumentos.setVisible(true);
			}
		});
		mnUtilidades.add(mntmImprimirDocumentos);
		
		JMenuItem mntmAnularDocumentos = new JMenuItem("Anular Documentos");
		mntmAnularDocumentos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mntmAnularDocumentos.setForeground(new Color(0, 51, 0));
		mntmAnularDocumentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentAnularDocumentos ventAnularDocumentos = new VentAnularDocumentos();
				ventAnularDocumentos.setVisible(true);
			}
		});
		mnUtilidades.add(mntmAnularDocumentos);
		
		JMenuItem mntmRegistrarAlertasDe = new JMenuItem("Registrar Alertas de Vencimiento");
		mntmRegistrarAlertasDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarAlertaVencimiento ventRegistrarAlertaVencimiento = new VentRegistrarAlertaVencimiento();
				ventRegistrarAlertaVencimiento.setVisible(true);
			}
		});
		mntmRegistrarAlertasDe.setForeground(new Color(0, 51, 0));
		mntmRegistrarAlertasDe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnUtilidades.add(mntmRegistrarAlertasDe);
		
		JMenuItem mntmConfigurarRecordatorios = new JMenuItem("Configurar Recordatorios");
		mntmConfigurarRecordatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentConfigurarRecordatorios ventConfigurarRecordatorios = new VentConfigurarRecordatorios();
				ventConfigurarRecordatorios.setVisible(true);
			}
		});
		mntmConfigurarRecordatorios.setForeground(new Color(0, 51, 0));
		mntmConfigurarRecordatorios.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnUtilidades.add(mntmConfigurarRecordatorios);
		
		JMenuItem mntmVerRecordatorios = new JMenuItem("Ver Recordatorios");
		mntmVerRecordatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentMostrarRecordatorios ventMostrarRecordatorios = new VentMostrarRecordatorios();
				ventMostrarRecordatorios.setVisible(true);
			}
		});
		mntmVerRecordatorios.setForeground(new Color(0, 51, 0));
		mntmVerRecordatorios.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnUtilidades.add(mntmVerRecordatorios);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		mnAyuda.setForeground(new Color(0, 51, 0));
		mnAyuda.setFont(new Font("Tahoma", Font.BOLD, 14));
		mbPrincipal.add(mnAyuda);
		
		JMenuItem mntmContenidoPrograma = new JMenuItem("Contenido del Programa");
		mntmContenidoPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ejecutarAyuda();
			
		    }              
		});
		mntmContenidoPrograma.setForeground(new Color(0, 51, 0));
		mntmContenidoPrograma.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnAyuda.add(mntmContenidoPrograma);
		
		JSeparator sp4 = new JSeparator();
		mnAyuda.add(sp4);
		
		JMenuItem mntmAcercaPrograma = new JMenuItem("Acerca del Programa");
		mntmAcercaPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentAcercaDeSACJungla ventAcercaDeSACJungla = new VentAcercaDeSACJungla();
				ventAcercaDeSACJungla.setVisible(true);
			}
		});
		mntmAcercaPrograma.setForeground(new Color(0, 51, 0));
		mntmAcercaPrograma.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnAyuda.add(mntmAcercaPrograma);
		
		JPanel pnPrincipal = new JPanel();
		pnPrincipal.setBackground(new Color(212, 208, 200));
		pnPrincipal.setBorder(new LineBorder(new Color(0, 51, 0)));
		pnPrincipal.setBounds(0, 103, 1093, 569);
		contentPane.add(pnPrincipal);
		pnPrincipal.setLayout(null);
		
		JLabel lblPnPrincipal = new JLabel("");
		lblPnPrincipal.setIcon(new ImageIcon(VentPrincipal.class.getResource("/co/com/jungla/sac/presentacion/imagenes/logotipo sacjungla.png")));
		lblPnPrincipal.setBounds(0, 0, 1093, 569);
		pnPrincipal.add(lblPnPrincipal);
		
		JPanel pnIconos = new JPanel();
		pnIconos.setBackground(new Color(0, 51, 0));
		pnIconos.setBounds(0, 30, 980, 73);
		contentPane.add(pnIconos);
		pnIconos.setLayout(null);
		
		JButton btnIconoProveedores = new JButton("");
		btnIconoProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarProveedor ventRegistrarProveedor = new VentRegistrarProveedor();
				ventRegistrarProveedor.setVisible(true);
			}
		});
		btnIconoProveedores.setBackground(new Color(193, 193, 164));
		btnIconoProveedores.setIcon(new ImageIcon(VentPrincipal.class.getResource("/co/com/jungla/sac/presentacion/imagenes/icono registrar proveedores.png")));
		btnIconoProveedores.setBounds(10, 10, 54, 54);
		btnIconoProveedores.setToolTipText("Registrar Proveedor");
		pnIconos.add(btnIconoProveedores);
		
		JButton btnIconoArticulos = new JButton("");
		btnIconoArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarArticulo ventRegistrarArticulo = new VentRegistrarArticulo();
				ventRegistrarArticulo.setVisible(true);
			}
		});
		btnIconoArticulos.setBackground(new Color(193, 193, 164));
		btnIconoArticulos.setIcon(new ImageIcon(VentPrincipal.class.getResource("/co/com/jungla/sac/presentacion/imagenes/icono registrar articulos.png")));
		btnIconoArticulos.setBounds(74, 10, 54, 54);
		btnIconoArticulos.setToolTipText("Registrar Articulo");
		pnIconos.add(btnIconoArticulos);
		
		JButton btnIconoCompras = new JButton("");
		btnIconoCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarCompraArticulos ventRegistrarCompraArticulos = new VentRegistrarCompraArticulos();
				ventRegistrarCompraArticulos.setVisible(true);
			}
		});
		btnIconoCompras.setIcon(new ImageIcon(VentPrincipal.class.getResource("/co/com/jungla/sac/presentacion/imagenes/icono registrar compra articulos.png")));
		btnIconoCompras.setBackground(new Color(193, 193, 164));
		btnIconoCompras.setBounds(134, 10, 54, 54);
		btnIconoCompras.setToolTipText("Registrar Compra de Articulos");
		pnIconos.add(btnIconoCompras);
		
		JButton btnIconoClientes = new JButton("");
		btnIconoClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarCliente ventRegistrarCliente = new VentRegistrarCliente();
				ventRegistrarCliente.setVisible(true);
			}
		});
		btnIconoClientes.setIcon(new ImageIcon(VentPrincipal.class.getResource("/co/com/jungla/sac/presentacion/imagenes/icono registrar cliente.png")));
		btnIconoClientes.setBackground(new Color(193, 193, 164));
		btnIconoClientes.setBounds(254, 10, 54, 54);
		btnIconoClientes.setToolTipText("Registrar Cliente");
		pnIconos.add(btnIconoClientes);
		
		JButton btnIconoVentas = new JButton("");
		btnIconoVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarVentaArticulos ventaRegistrarVentaArticulos = new VentRegistrarVentaArticulos();
				ventaRegistrarVentaArticulos.setVisible(true);
			}
		});
		btnIconoVentas.setIcon(new ImageIcon(VentPrincipal.class.getResource("/co/com/jungla/sac/presentacion/imagenes/icono registrar venta articulos.png")));
		btnIconoVentas.setBackground(new Color(193, 193, 164));
		btnIconoVentas.setBounds(314, 10, 54, 54);
		btnIconoVentas.setToolTipText("Registrar Venta de Articulos");
		pnIconos.add(btnIconoVentas);
		
		JButton btnIconoDevoluciones = new JButton("");
		btnIconoDevoluciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarDevolucionCliente ventRegistrarDevolucionCliente = new VentRegistrarDevolucionCliente();
				ventRegistrarDevolucionCliente.setVisible(true);
			}
		});
		btnIconoDevoluciones.setIcon(new ImageIcon(VentPrincipal.class.getResource("/co/com/jungla/sac/presentacion/imagenes/icono registrar devolucion cliente.png")));
		btnIconoDevoluciones.setBackground(new Color(193, 193, 164));
		btnIconoDevoluciones.setBounds(374, 10, 54, 54);
		btnIconoDevoluciones.setToolTipText("Registrar Devolucion a Cliente");
		pnIconos.add(btnIconoDevoluciones);
		
		JButton btnIconoRecaudos = new JButton("");
		btnIconoRecaudos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarRecaudosCaja ventRegistrarRecaudosCaja = new VentRegistrarRecaudosCaja();
				ventRegistrarRecaudosCaja.setVisible(true);
			}
		});
		btnIconoRecaudos.setIcon(new ImageIcon(VentPrincipal.class.getResource("/co/com/jungla/sac/presentacion/imagenes/icono realizar cuadre de caja.png")));
		btnIconoRecaudos.setBackground(new Color(193, 193, 164));
		btnIconoRecaudos.setBounds(434, 10, 54, 54);
		btnIconoRecaudos.setToolTipText("Recaudar Facturas a Crédito");
		pnIconos.add(btnIconoRecaudos);
		
		JButton btnIconoKardexElectronico = new JButton("");
		btnIconoKardexElectronico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentMostrarKardexElectronicoArticulos ventMostrarKardexElectronicoArticulos = new VentMostrarKardexElectronicoArticulos();
				ventMostrarKardexElectronicoArticulos.setVisible(true);
			}
		});
		btnIconoKardexElectronico.setIcon(new ImageIcon(VentPrincipal.class.getResource("/co/com/jungla/sac/presentacion/imagenes/icono kardex articulos.png")));
		btnIconoKardexElectronico.setBackground(new Color(193, 193, 164));
		btnIconoKardexElectronico.setBounds(494, 10, 54, 54);
		btnIconoKardexElectronico.setToolTipText("Mostrar Kardex Electronico");
		pnIconos.add(btnIconoKardexElectronico);
		
		JButton btnIconoControlInventario = new JButton("");
		btnIconoControlInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentControlInventario ventControlInventario = new VentControlInventario();
				ventControlInventario.setVisible(true);
			}
		});
		btnIconoControlInventario.setIcon(new ImageIcon(VentPrincipal.class.getResource("/co/com/jungla/sac/presentacion/imagenes/icono control inventarios.png")));
		btnIconoControlInventario.setBackground(new Color(193, 193, 164));
		btnIconoControlInventario.setBounds(554, 10, 54, 54);
		btnIconoControlInventario.setToolTipText("Control de Inventarios");
		pnIconos.add(btnIconoControlInventario);
		
		JButton btnIconoReporteCxP = new JButton("");
		btnIconoReporteCxP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentReportarCuentaXPagar ventReportarCuentaXPagar = new VentReportarCuentaXPagar();
				ventReportarCuentaXPagar.setVisible(true);
			}
		});
		btnIconoReporteCxP.setIcon(new ImageIcon(VentPrincipal.class.getResource("/co/com/jungla/sac/presentacion/imagenes/icono reporte cuentas x pagar.png")));
		btnIconoReporteCxP.setBackground(new Color(193, 193, 164));
		btnIconoReporteCxP.setBounds(614, 10, 54, 54);
		btnIconoReporteCxP.setToolTipText("Reporte de Cuentas por Pagar");
		pnIconos.add(btnIconoReporteCxP);
		
		JButton btnIconoCarteraPendiente = new JButton("");
		btnIconoCarteraPendiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentReportarCarteraPendiente ventReportarCarteraPendiente = new VentReportarCarteraPendiente();
				ventReportarCarteraPendiente.setVisible(true);
			}
		});
		btnIconoCarteraPendiente.setIcon(new ImageIcon(VentPrincipal.class.getResource("/co/com/jungla/sac/presentacion/imagenes/icono reporte resumen cliente.png")));
		btnIconoCarteraPendiente.setBackground(new Color(193, 193, 164));
		btnIconoCarteraPendiente.setBounds(674, 10, 54, 54);
		btnIconoCarteraPendiente.setToolTipText("Reporte Cartera Pendiente");
		pnIconos.add(btnIconoCarteraPendiente);
		
		JButton btnIconoReporteVentas = new JButton("");
		btnIconoReporteVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentReportarVenta ventReportarVenta = new VentReportarVenta();
				ventReportarVenta.setVisible(true);
			}
		});
		btnIconoReporteVentas.setIcon(new ImageIcon(VentPrincipal.class.getResource("/co/com/jungla/sac/presentacion/imagenes/icono reporte ventas.png")));
		btnIconoReporteVentas.setBackground(new Color(193, 193, 164));
		btnIconoReporteVentas.setBounds(734, 10, 54, 54);
		btnIconoReporteVentas.setToolTipText("Reporte Ventas");
		pnIconos.add(btnIconoReporteVentas);
		
		JButton btnIconoImprimirDocumentos = new JButton("");
		btnIconoImprimirDocumentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentImprimirDocumentos ventImprimirDocumentos = new VentImprimirDocumentos();
				ventImprimirDocumentos.setVisible(true);
			}
		});
		btnIconoImprimirDocumentos.setIcon(new ImageIcon(VentPrincipal.class.getResource("/co/com/jungla/sac/presentacion/imagenes/icono imprimir documentos.png")));
		btnIconoImprimirDocumentos.setBackground(new Color(193, 193, 164));
		btnIconoImprimirDocumentos.setBounds(794, 10, 54, 54);
		btnIconoImprimirDocumentos.setToolTipText("Imprimir Documentos");
		pnIconos.add(btnIconoImprimirDocumentos);
		
		JButton btnIconoVerAyudas = new JButton("");
		btnIconoVerAyudas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ejecutarAyuda();
			}
		});
		btnIconoVerAyudas.setIcon(new ImageIcon(VentPrincipal.class.getResource("/co/com/jungla/sac/presentacion/imagenes/icono ver ayudas.png")));
		btnIconoVerAyudas.setBackground(new Color(193, 193, 164));
		btnIconoVerAyudas.setBounds(854, 10, 54, 54);
		btnIconoVerAyudas.setToolTipText("Ayuda");
		pnIconos.add(btnIconoVerAyudas);
		
		JButton btnIconoCuentasxPagar = new JButton("");
		btnIconoCuentasxPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarCuentaXPagar ventRegistrarCuentaXPagar = new VentRegistrarCuentaXPagar();
				ventRegistrarCuentaXPagar.setVisible(true);
			}
		});
		btnIconoCuentasxPagar.setIcon(new ImageIcon(VentPrincipal.class.getResource("/co/com/jungla/sac/presentacion/imagenes/icono registrar egresos.png")));
		btnIconoCuentasxPagar.setBackground(new Color(193, 193, 164));
		btnIconoCuentasxPagar.setBounds(194, 10, 54, 54);
		btnIconoCuentasxPagar.setToolTipText("Registrar Cuentas por Pagar");
		pnIconos.add(btnIconoCuentasxPagar);
		
		JButton btnIconoSalir = new JButton("");
		btnIconoSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnIconoSalir.setIcon(new ImageIcon(VentPrincipal.class.getResource("/co/com/jungla/sac/presentacion/imagenes/icono exit.png")));
		btnIconoSalir.setBackground(new Color(193, 193, 164));
		btnIconoSalir.setBounds(914, 10, 54, 54);
		btnIconoSalir.setToolTipText("Cerrar SACJungla");
		pnIconos.add(btnIconoSalir);
		
		JLabel lblPnIconos = new JLabel("");
		lblPnIconos.setIcon(new ImageIcon(VentPrincipal.class.getResource("/co/com/jungla/sac/presentacion/imagenes/pnIconosTodos.png")));
		lblPnIconos.setBounds(0, 0, 980, 73);
		pnIconos.add(lblPnIconos);
		
		JLabel lblLogoJungla = new JLabel("");
		lblLogoJungla.setIcon(new ImageIcon("C:\\Users\\Luisefe387\\Desktop\\Logo Jungla Mas Peque\u00F1o2.png"));
		lblLogoJungla.setBounds(985, 0, 106, 103);
		contentPane.add(lblLogoJungla);
		
		//Metodos que debe ejecutar la ventana al inicializarse
		crearDepartamentosPorDefecto();
		crearMunicipiosPorDefecto();
		crearFormasPagoPorDefecto();
		crearTipoClientePorDefecto();
		crearClientePorDefecto();
		crearVendedorPorDefecto();
		crearRecordatorioPorDefecto();
	}

	//Metodo para crear el vendedor por defecto
	private void crearVendedorPorDefecto() {
		Vendedor vendedor = new Vendedor();
		Departamento departamento = new Departamento();
		Municipio municipio = new Municipio();
		
		DelegadoVendedor delegadoVendedor = new DelegadoVendedor();
		if(delegadoVendedor.listarVendedor().isEmpty()){
			vendedor.setIdentificacion("10949338120");
			vendedor.setNombre("Jose Gregorio Gonzales");
			vendedor.setFechaNacimiento(new Date());
			vendedor.setTelefono("");
			vendedor.setSexo("MASCULINO");
			departamento.setIdDepartamento(24);
			municipio.setIdMunicipio(26);
			municipio.setDepartamento(departamento);
			vendedor.setMunicipio(municipio);
			vendedor.setDireccion("");
			vendedor.setEmail("");
			vendedor.setProfesion("Comerciante");
			vendedor.setEstadoCivil("CASADO");
			vendedor.setHijos(Byte.parseByte("1"));
			vendedor.setEstado("Activo");
			
			delegadoVendedor.insertarVendedor(vendedor);
		}
	}

	//Metodo para crear las formas de pago del cliente por defecto
	private void crearFormasPagoPorDefecto(){
		DelegadoFormaPagoCliente delegadoFormaPagoCliente = new DelegadoFormaPagoCliente();
		FormaPagoCliente formaPagoCliente = new FormaPagoCliente();
		
		if(delegadoFormaPagoCliente.listarFormaPagoCliente().isEmpty()){
			String [] formasPago ={"CONTADO","CREDITO","CONSIGNACION","CHEQUE A FECHA","CREDITO 15 DIAS", "CREDITO 30 DIAS","CREDITO 60 DIAS"};
			int []	dias={0,0,0,0,15,30,60};
			
			for(int i=0;i<formasPago.length;i++){
				formaPagoCliente.setDescripcion(formasPago[i]);
				formaPagoCliente.setDias(dias[i]);
				delegadoFormaPagoCliente.insertarFormaPagoCliente(formaPagoCliente);
			}
		}
		
	}
	
	//Metodo para crear el tipo de cliente por defecto
	private void crearTipoClientePorDefecto(){
		TipoCliente tipoCliente = new TipoCliente();
		DelegadoTipoCliente delegadoTipoCliente =new DelegadoTipoCliente();
		
		if(delegadoTipoCliente.listarTipoCliente().isEmpty()){
			tipoCliente.setDescripcion("PARTICULAR");
			delegadoTipoCliente.insertarTipoCliente(tipoCliente);
		}
		
	}
	//Metodo para crear un cliente por defecto, es decir, un cliente general para clientes que no necesiten estar registrados
	private void crearClientePorDefecto(){
		Cliente cliente = new Cliente();
		TipoCliente tipoCliente = new TipoCliente();
		Departamento departamento = new Departamento();
		Municipio municipio = new Municipio();
		
		DelegadoCliente delegadoCliente = new DelegadoCliente();
		if(delegadoCliente.listarCliente().isEmpty()){
			cliente.setIdentificacion("0000000001");
			cliente.setNombre("VENTAS DE CONTADO Y/O MOSTRADOR");
			cliente.setNombreComercial("");
			cliente.setTelefono("");
			cliente.setContacto("");
			tipoCliente.setIdTipoCliente(1);
			cliente.setTipoClientes(tipoCliente);
			departamento.setIdDepartamento(24);
			municipio.setIdMunicipio(26);
			municipio.setDepartamento(departamento);
			cliente.setMunicipio(municipio);
			cliente.setDireccion("");
			cliente.setEmail("");
			cliente.setPaginaWeb("");
			cliente.setFechaNacimiento(new Date());
			cliente.setObservaciones("");
			cliente.setEstado("Activo");
			
			delegadoCliente.insertarCliente(cliente);
			
		}
	}
	//Metodo para crear los departamentos por defecto al iniciar la aplicacion
	private void crearDepartamentosPorDefecto(){
		DelegadoDepartamento delegadoDepartamento = new DelegadoDepartamento();
		Departamento departamento = new Departamento();
	
		if(delegadoDepartamento.listarDepartamentos().isEmpty()){
			String [][] departamentos ={ {"1", "AMAZONAS"},{"2", "ANTIOQUIA"},{"3", "ARAUCA"},{"4", "ATLÁNTICO"},
					 {"5", "BOLÍVAR"},{"6", "BOYACÁ"}, {"7", "CALDAS"},{"8", "CAQUETÁ"},{"9", "CASANARE"},{"10", "CAUCA"},
					 {"11", "CESAR"},{"12", "CHOCÓ"},{"13", "CÓRDOBA"},{"14", "CUNDINAMARCA"},{"15", "GUAINÍA"},{"16", "GUAVIARE"},
					 {"17", "HUILA"},{"18", "LA GUAJIRA"},{"19", "MAGDALENA"},{"20", "META"},{"21", "NARIÑO"},{"22", "NORTE DE SANTANDER"},
					 {"23", "PUTUMAYO"},{"24", "QUINDÍO"},{"25", "RISARALDA"},{"26", "SAN ANDRÉS Y ROVIDENCIA"},{"27", "SANTANDER"},
					 {"28", "SUCRE"},{"29", "TOLIMA"},{"30", "VALLE DEL CAUCA"},{"31", "VAUPÉS"},{"32", "VICHADA"}};
			
			for(int i=0;i<departamentos.length;i++){
				departamento.setIdDepartamento(Integer.parseInt(departamentos[i][0].toString()));
				departamento.setNombre(departamentos[i][1]);
				
				delegadoDepartamento.insertarDepartamento(departamento);
			}
		}
	}
	//Metodo para crear los municipios por defecto al iniciar la aplicacion
	private void crearMunicipiosPorDefecto(){
		DelegadoMunicipio delegadoMunicipio = new DelegadoMunicipio();
		Municipio municipio = new Municipio();
		Departamento departamento = new Departamento();
		if(delegadoMunicipio.listarMunicipios().isEmpty()){
			try{
				File archivoBuscarRuta = new File("Ficheros\\municipios.txt");
				String rutaEncontrada =archivoBuscarRuta.getAbsolutePath(); 
				File archivo = new File(rutaEncontrada);
				
				if(archivo.exists()){
					BufferedReader br = new BufferedReader(new FileReader(archivo));
					String linea;
					while((linea = br.readLine())!= null){
						StringTokenizer st = new StringTokenizer(linea,",");
						int codigoMunicipio = Integer.parseInt(st.nextToken().trim());
						String nombreMunicipio = st.nextToken().trim();
						int codigoDepartamento = Integer.parseInt(st.nextToken().trim());
						
						municipio.setIdMunicipio(codigoMunicipio);
						municipio.setNombre(nombreMunicipio);
						departamento.setIdDepartamento(codigoDepartamento);
						municipio.setDepartamento(departamento);
						
						delegadoMunicipio.insertarMunicipio(municipio);
					}
				}else{
					JOptionPane.showMessageDialog(null, "El archivo no existe");
				}
			}catch(Exception x){
				x.getMessage();
			}
		}
	}
	
	//Metodo para crear los datos inciales de los recordatorios
	private void crearRecordatorioPorDefecto(){
		Recordatorio recordatorio = new Recordatorio();
		DelegadoRecordatorio delegadoRecordatorio =new DelegadoRecordatorio();
		
		if(delegadoRecordatorio.listarRecordatorios().isEmpty()){
			recordatorio.setDiasCartera(10);
			recordatorio.setDiasCxP(10);
			recordatorio.setDiasVencimiento(30);
			recordatorio.setMostrarAlInicio(true);
			
			delegadoRecordatorio.insertarRecordatorio(recordatorio);
		}
	}
	
	//Metodo para ejecutar la ayuda
	private void ejecutarAyuda() {
		
		String file = new String (cambiarSlashes(System.getProperty("user.dir")) + "/src/co/com/jungla/sac/presentacion/ayudas/ayudaSACJungla.chm");

        try{
            Process p = Runtime.getRuntime().exec("cmd /c" +file);
        }
        catch(Exception ev){
            System.out.print("Error al abrir ayuda");
        }
	}
	
	//Metodo para reemplazar los slashes por los backslashes
	private String cambiarSlashes(String ruta){
		String rutaReemplazada=ruta.replace("\\", "/");
		return rutaReemplazada;
	}
}
