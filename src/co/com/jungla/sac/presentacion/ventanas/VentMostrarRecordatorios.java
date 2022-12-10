package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import co.com.jungla.sac.persistencia.entidades.AlertaVencimiento;
import co.com.jungla.sac.persistencia.entidades.ControlInventario;
import co.com.jungla.sac.persistencia.entidades.CuentaXPagar;
import co.com.jungla.sac.persistencia.entidades.PagoAbonoCxP;
import co.com.jungla.sac.persistencia.entidades.ReciboCaja;
import co.com.jungla.sac.persistencia.entidades.VentaArticulos;

import co.com.jungla.sac.negocio.delegados.DelegadoAlertaVencimiento;
import co.com.jungla.sac.negocio.delegados.DelegadoControlInventario;
import co.com.jungla.sac.negocio.delegados.DelegadoCuentaXPagar;
import co.com.jungla.sac.negocio.delegados.DelegadoPagoAbonoCxP;
import co.com.jungla.sac.negocio.delegados.DelegadoProveedor;
import co.com.jungla.sac.negocio.delegados.DelegadoReciboCaja;
import co.com.jungla.sac.negocio.delegados.DelegadoRecordatorio;
import co.com.jungla.sac.negocio.delegados.DelegadoVentaArticulos;

import javax.swing.border.LineBorder;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Component;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTabbedPane;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo la visualizacion de los recordatorios de las cuentas por pagar, cartera pendiente, vencimientos
 *  de los productos y alertas del inventario
 * @author Luis Fernando Pedroza T.
 * @version: 19/09/2016
 */
public class VentMostrarRecordatorios extends JDialog {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTable tbInventario;
	private JTable tbCxP;
	private JTable tbCartera;
	private JTable tbVencimiento;
	private DefaultTableModel modeloTbInventario = new DefaultTableModel();
	private DefaultTableModel modeloTbCxP = new DefaultTableModel();
	private DefaultTableModel modeloTbCartera = new DefaultTableModel();
	private DefaultTableModel modeloTbVencimiento= new DefaultTableModel();
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private List<ControlInventario> listaArticulosInventario;
	private List<VentaArticulos> listaVentasPendientes;
	private List<CuentaXPagar> listaCxP;
	private List<AlertaVencimiento> listaVencimiento;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentMostrarRecordatorios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentMostrarRecordatorios.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Recordatorios");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 909, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		contentPane.add(tabbedPane, "name_27904920652039");
		
		JPanel pnInventario = new JPanel();
		tabbedPane.addTab("Alerta Inventario", null, pnInventario, null);
		pnInventario.setLayout(null);
		
		JScrollPane srcInventario = new JScrollPane();
		srcInventario.setBounds(10, 11, 872, 343);
		pnInventario.add(srcInventario);
		
		tbInventario = new JTable();
		srcInventario.setViewportView(tbInventario);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setBounds(284, 378, 91, 23);
		pnInventario.add(btnCerrar);
		
		JButton btnConfigurar = new JButton("Configurar Recordatorios");
		btnConfigurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				configurarRecordatorios();
			}
		});
		btnConfigurar.setForeground(new Color(0, 51, 0));
		btnConfigurar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConfigurar.setBounds(434, 378, 191, 23);
		pnInventario.add(btnConfigurar);
		
		JPanel pnCxP = new JPanel();
		tabbedPane.addTab("Cuentas x Pagar < días", null, pnCxP, null);
		pnCxP.setLayout(null);
		
		JScrollPane srcCxP = new JScrollPane();
		srcCxP.setBounds(10, 11, 872, 343);
		pnCxP.add(srcCxP);
		
		tbCxP = new JTable();
		srcCxP.setViewportView(tbCxP);
		
		JButton btnCerrar_1 = new JButton("Cerrar");
		btnCerrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar_1.setForeground(new Color(0, 51, 0));
		btnCerrar_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar_1.setBounds(284, 378, 91, 23);
		pnCxP.add(btnCerrar_1);
		
		JButton btnConfigurar_1 = new JButton("Configurar Recordatorios");
		btnConfigurar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				configurarRecordatorios();
			}
		});
		btnConfigurar_1.setForeground(new Color(0, 51, 0));
		btnConfigurar_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConfigurar_1.setBounds(434, 378, 191, 23);
		pnCxP.add(btnConfigurar_1);
		
		JPanel pnCartera = new JPanel();
		tabbedPane.addTab("Cartera Pendiente< días", null, pnCartera, null);
		pnCartera.setLayout(null);
		
		JScrollPane srcCartera = new JScrollPane();
		srcCartera.setBounds(10, 11, 872, 343);
		pnCartera.add(srcCartera);
		
		tbCartera = new JTable();
		srcCartera.setViewportView(tbCartera);
		
		JButton btnCerrar_2 = new JButton("Cerrar");
		btnCerrar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar_2.setForeground(new Color(0, 51, 0));
		btnCerrar_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar_2.setBounds(284, 378, 91, 23);
		pnCartera.add(btnCerrar_2);
		
		JButton btnConfigurar_2 = new JButton("Configurar Recordatorios");
		btnConfigurar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				configurarRecordatorios();
			}
		});
		btnConfigurar_2.setForeground(new Color(0, 51, 0));
		btnConfigurar_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConfigurar_2.setBounds(434, 378, 191, 23);
		pnCartera.add(btnConfigurar_2);
		
		JPanel pnVencimiento = new JPanel();
		tabbedPane.addTab("Vencimiento Articulos < días", null, pnVencimiento, null);
		pnVencimiento.setLayout(null);
		
		JScrollPane srcVencimiento = new JScrollPane();
		srcVencimiento.setBounds(10, 11, 872, 343);
		pnVencimiento.add(srcVencimiento);
		
		tbVencimiento = new JTable();
		srcVencimiento.setViewportView(tbVencimiento);
		
		JButton btnCerrar_3 = new JButton("Cerrar");
		btnCerrar_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar_3.setForeground(new Color(0, 51, 0));
		btnCerrar_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar_3.setBounds(284, 378, 91, 23);
		pnVencimiento.add(btnCerrar_3);
		
		JButton btnConfigurar_3 = new JButton("Configurar Recordatorios");
		btnConfigurar_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				configurarRecordatorios();
			}
		});
		btnConfigurar_3.setForeground(new Color(0, 51, 0));
		btnConfigurar_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConfigurar_3.setBounds(434, 378, 191, 23);
		pnVencimiento.add(btnConfigurar_3);
		
		//Metodos que debe ejecutar la ventana al inicializarse
		mostrarDatosInventario();
		mostrarDatosCartera();	
		mostrarDatosCxP();
		mostrarDatosVencimiento();
		
		
		setModal(true);
	}
	
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbInventario() {
		modeloTbInventario.addColumn("Codigo");
		modeloTbInventario.addColumn("Grupo");
		modeloTbInventario.addColumn("Articulo");
		modeloTbInventario.addColumn("UE");
		modeloTbInventario.addColumn("Presentación");
		modeloTbInventario.addColumn("Alerta Existencia");
		
		tbInventario.setModel(modeloTbInventario);
	}
	
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbCartera() {
		modeloTbCartera.addColumn("Cliente");
		modeloTbCartera.addColumn("Ciudad");
		modeloTbCartera.addColumn("Telefonos");
		modeloTbCartera.addColumn("Factura");
		modeloTbCartera.addColumn("FechaLimite");
		modeloTbCartera.addColumn("Dias");
		modeloTbCartera.addColumn("Vlr Neto");
		modeloTbCartera.addColumn("Saldo");
		modeloTbCartera.addColumn("Forma Pago");
		modeloTbCartera.addColumn("Vendedor");
		
		tbCartera.setModel(modeloTbCartera);
	}
	
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbCxP() {
		modeloTbCxP.addColumn("CxP");
		modeloTbCxP.addColumn("Causado");
		modeloTbCxP.addColumn("Fecha Pago");
		modeloTbCxP.addColumn("Dias");
		modeloTbCxP.addColumn("Proveedor");
		modeloTbCxP.addColumn("Concepto");
		modeloTbCxP.addColumn("Doc. Soporte");
		modeloTbCxP.addColumn("Compra");
		modeloTbCxP.addColumn("Total");
		modeloTbCxP.addColumn("Saldo");
		
		tbCxP.setModel(modeloTbCxP);
	}
	
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbVencimientos() {
		modeloTbVencimiento.addColumn("Codigo");
		modeloTbVencimiento.addColumn("Grupo");
		modeloTbVencimiento.addColumn("Ultimo Proveedor");
		modeloTbVencimiento.addColumn("Articulo");
		modeloTbVencimiento.addColumn("Lote");
		modeloTbVencimiento.addColumn("Cantidad");
		modeloTbVencimiento.addColumn("Fecha Vencimiento");
		modeloTbVencimiento.addColumn("Dias");
		modeloTbVencimiento.addColumn("Generado");
		modeloTbVencimiento.addColumn("Observacion");
		
		tbVencimiento.setModel(modeloTbVencimiento);
	}
	
	//Metodo para listar las compras de acuerdo a los parametros de fecha causacion y proveedor
	private void mostrarDatosInventario(){
		
		DelegadoControlInventario delegadoControlInventario = new DelegadoControlInventario();	
		
		listaArticulosInventario = delegadoControlInventario.traerRegistrosInventarioCantAlerta();
		//limpiarTabla();
		llenarTablaArticulosInventario(listaArticulosInventario);
	}
	
	//Metodo para listar las compras de acuerdo a los parametros de fecha causacion y proveedor
	private void mostrarDatosCartera(){
		
		DelegadoVentaArticulos delegadoVentaArticulos = new DelegadoVentaArticulos();
		DelegadoRecordatorio delegadoRecordatorio = new DelegadoRecordatorio();
		
		int diasCartera = delegadoRecordatorio.listarRecordatorios().get(0).getDiasCartera();
	
		listaVentasPendientes= delegadoVentaArticulos.traerVentasPendientesAVencerse(restarDias(new Date()), sumarDias(new Date(), diasCartera), "Pendiente");
		//limpiarTabla();
		llenarTablaCartera(listaVentasPendientes);
	}
	
	//Metodo para listar las compras de acuerdo a los parametros de fecha causacion y proveedor
	private void mostrarDatosCxP(){
		
		DelegadoCuentaXPagar delegadoCuentaXPagar = new DelegadoCuentaXPagar();
		DelegadoRecordatorio delegadoRecordatorio = new DelegadoRecordatorio();
		
		int diasCxP = delegadoRecordatorio.listarRecordatorios().get(0).getDiasCxP();
	
		listaCxP= delegadoCuentaXPagar.traerCxPAVencerse(restarDias(new Date()), sumarDias(new Date(), diasCxP), "Pendiente");
		//limpiarTabla();
		llenarTablaCxP(listaCxP);
	}
	
	//Metodo para listar las compras de acuerdo a los parametros de fecha causacion y proveedor
	private void mostrarDatosVencimiento(){
		
		DelegadoAlertaVencimiento delegadoAlertaVencimiento = new DelegadoAlertaVencimiento();
		DelegadoRecordatorio delegadoRecordatorio = new DelegadoRecordatorio();
		
		int diasVencimiento = delegadoRecordatorio.listarRecordatorios().get(0).getDiasVencimiento();
	
		listaVencimiento= delegadoAlertaVencimiento.traerArticulosAVencerse(restarDias(new Date()), sumarDias(new Date(), diasVencimiento));
		//limpiarTabla();
		llenarTablaVencimientos(listaVencimiento);
	}
		
	//Metodo para llenar la tabla cuando recibe como parametro una lista de compras
	private void llenarTablaArticulosInventario( List<ControlInventario> listaArticulosInventario) {
		
		if(tbInventario.getModel().getColumnCount()==0){
			llenarColumnasTbInventario();
		}
		
		String [] fila = new String[modeloTbInventario.getColumnCount()];
		
		for(ControlInventario articulosInventario : listaArticulosInventario){
			
			fila[0]= String.valueOf(articulosInventario.getArticulo().getCodigo());
			fila[1]= articulosInventario.getArticulo().getLineaArticulos().getNombreL();
			fila[2]= articulosInventario.getArticulo().getNombre();
			fila[3]= String.valueOf(articulosInventario.getArticulo().getUnidadMedida().getAbreviatura());
			fila[4]= articulosInventario.getArticulo().getPresentacion();
			fila[5]= Integer.toString(articulosInventario.getCantAlerta());
			
			modeloTbInventario.addRow(fila);
		}
		
		tbInventario.setModel(modeloTbInventario);
		
		colorearCantidadAlertaEnTabla(tbInventario);
		
	}

	//Metodo para llenar la tabla cuando recibe como parametro una lista de compras
	private void llenarTablaCartera( List<VentaArticulos> listarVentasPendientes) {
		
		if(tbCartera.getModel().getColumnCount()==0){
			llenarColumnasTbCartera();
		}
		
		DelegadoReciboCaja delegadoReciboCaja = new DelegadoReciboCaja();
		String [] fila = new String[modeloTbCartera.getColumnCount()];
		
		double acumulador = 0;
		double totalRecibosCaja = 0;
		
		for(VentaArticulos ventasPendientes : listaVentasPendientes){
			List<ReciboCaja> listaRecibosCajaPorVenta = delegadoReciboCaja.traerRecibosCajaPorVenta(ventasPendientes.getIdVenta());
			fila[0]= ventasPendientes.getClientes().getNombre();
			fila[1]= ventasPendientes.getClientes().getMunicipio().getNombre();
			fila[2]= ventasPendientes.getClientes().getTelefono();
			fila[3]= String.valueOf(ventasPendientes.getIdVenta());
			fila[4]= convertirDateAString(ventasPendientes.getFechaLimitePago());
			fila[5]= String.valueOf(calcularDiasPlazo(ventasPendientes.getFechaLimitePago(), new Date()));
			fila[6]= formatearNumero(ventasPendientes.getTotalVenta());
			for(ReciboCaja recibos : listaRecibosCajaPorVenta){
				acumulador= recibos.getTotalRecibido();
				totalRecibosCaja += acumulador;
			}
			fila[7]= formatearNumero(ventasPendientes.getTotalVenta()-totalRecibosCaja);
			fila[8]= ventasPendientes.getFormaPagoCliente().getDescripcion();
			fila[9]= ventasPendientes.getVendedores().getNombre();
			
			modeloTbCartera.addRow(fila);
		}
		
		tbCartera.setModel(modeloTbCartera);
		
		colorearDiasEnTabla(tbCartera);
		
	}
	
	//Metodo para llenar la tabla cuando recibe como parametro una lista de compras
	private void llenarTablaCxP( List<CuentaXPagar> listarCxP) {
		
		if(tbCxP.getModel().getColumnCount()==0){
			llenarColumnasTbCxP();
		}
		double acumulador = 0;
		double totalPagoAbono = 0;
		DelegadoProveedor delegadoProveedor = new DelegadoProveedor();
		DelegadoPagoAbonoCxP delegadoPagoAbonoCxP = new DelegadoPagoAbonoCxP();
		String [] fila = new String[modeloTbCxP.getColumnCount()];
		
		for(CuentaXPagar cuentasXPagar : listaCxP){
			List<PagoAbonoCxP> listaPagoAbono = delegadoPagoAbonoCxP.listarPagoAbonoCxPPorCodigoCxP(cuentasXPagar.getIdCuentaXPagar());
			fila[0]= String.valueOf(cuentasXPagar.getIdCuentaXPagar());
			fila[1]= convertirDateAString(cuentasXPagar.getFechaCausacion());
			fila[2]= convertirDateAString(cuentasXPagar.getFechaPago());
			fila[3]= String.valueOf(calcularDiasPlazo(cuentasXPagar.getFechaPago(), new Date()));
			fila[4]= delegadoProveedor.traerProveedorPorIdentificacion(cuentasXPagar.getIdentificacionProv()).get(0).getNombre();
			fila[5]= cuentasXPagar.getConcepto();
			fila[6]= String.valueOf(cuentasXPagar.getDocSoporte());
			fila[7]= String.valueOf(cuentasXPagar.getCompra()); 
			fila[8]= formatearNumero(cuentasXPagar.getTotalPagar());
			for(PagoAbonoCxP pagoAbonos : listaPagoAbono){
				acumulador= pagoAbonos.getPagoAbono();
				totalPagoAbono += acumulador;
			}
			fila[9]= formatearNumero(cuentasXPagar.getTotalPagar()- totalPagoAbono);
			
			modeloTbCxP.addRow(fila);
		}
		
		tbCxP.setModel(modeloTbCxP);
		
		colorearDiasEnTabla(tbCxP);
		
	}
	
	//Metodo para llenar la tabla cuando recibe como parametro una lista de compras
	private void llenarTablaVencimientos( List<AlertaVencimiento> listarVencimientos) {
		
		if(tbVencimiento.getModel().getColumnCount()==0){
			llenarColumnasTbVencimientos();
		}
		
		String [] fila = new String[modeloTbVencimiento.getColumnCount()];
		
		for(AlertaVencimiento alertasVencimiento : listaVencimiento){
			fila[0]= String.valueOf(alertasVencimiento.getArticulo().getCodigo());
			fila[1]= alertasVencimiento.getArticulo().getControlInventario().getProveedorUltimaCompra();
			fila[2]= alertasVencimiento.getArticulo().getLineaArticulos().getNombreL();
			fila[3]= alertasVencimiento.getArticulo().getNombre();
			fila[4]= alertasVencimiento.getLote();
			fila[5]= String.valueOf(alertasVencimiento.getCantidad());
			fila[6]= convertirDateAString(alertasVencimiento.getFechaVencimiento());
			fila[7]= String.valueOf(calcularDiasPlazo(alertasVencimiento.getFechaVencimiento(), new Date()));
			fila[8]= convertirDateAString(alertasVencimiento.getFechaGeneracion());
			fila[9]= alertasVencimiento.getObservaciones();
			
			modeloTbVencimiento.addRow(fila);
		}
		
		tbVencimiento.setModel(modeloTbVencimiento);
		
		colorearDiasEnTabla(tbVencimiento);
		
	}
	/*private void mostrarPopupEnTabla(){
		JPopupMenu pmArticulos = new JPopupMenu();
		JMenuItem miVerCompra = new JMenuItem("Ver Detalle Compra");
		JMenuItem miVerEgreso = new JMenuItem("Ver Egreso");
		JMenuItem miVerCxP = new JMenuItem("Ver CXP");
		
		CompraArticulos datosCompra= listaCompraArticulos.get(filaSeleccionada);
		if(datosCompra.getEstadoPago().equals("Cancelado")){
			pmArticulos.add(miVerCompra);
			pmArticulos.add(miVerEgreso);
			//tbCompras.add(pmArticulos);
			tbCompras.setComponentPopupMenu(pmArticulos);
		}else{
			pmArticulos.add(miVerCompra);
			pmArticulos.add(miVerCxP);
			//tbCompras.add(pmArticulos);
			tbCompras.setComponentPopupMenu(pmArticulos);
		}
			
			
		
	}*/

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
	
	//Metodo para limpiar la tabla 
	private void limpiarTabla(){
	       for (int i = 0; i < tbInventario.getRowCount(); i++) {
	    	   modeloTbInventario.removeRow(i);
	           i-=1;
	       }
	 }
	
	//Metodo para sumar dias a una fecha determinada
	public Date sumarDias(Date fechaAAplicar, int dias){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaAAplicar); 
		calendar.add(Calendar.DAY_OF_YEAR, dias+1);  
		return calendar.getTime(); 
	}
	
	//Metodo para restar dias a una fecha determinada
	public Date restarDias(Date fechaAAplicar){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaAAplicar); 
		calendar.add(Calendar.DAY_OF_YEAR, -1);  
		return calendar.getTime(); 
	}
	
	//Ventana para colorear las celdas de los estados pendiente (rojo) y cancelado (verde)
	private void colorearDiasEnTabla(JTable tabla) {
		tabla.getColumn("Dias").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			comp.setBackground(new Color(255, 102, 102));
	        return comp; 
			}}); 
	}
	
	
	//Ventana para colorear las celdas de los estados pendiente (rojo) y cancelado (verde)
	private void colorearCantidadAlertaEnTabla(JTable tabla) {
		tabla.getColumn("Alerta Existencia").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			comp.setBackground(new Color(255, 102, 102));
	        return comp; 
			}}); 
	}
	
	//Metodo para calcular los dias entre la fecha de pago y la actual
	private int calcularDiasPlazo(Date fechaPago, Date fechaActual){
		long diferencia_fechas = fechaPago.getTime() - fechaActual.getTime();
		long dias = diferencia_fechas/(1000*60*60*24);
		return (int)dias;
	}
	
	//Metodo para abrir la ventana configurar recordotrios y hacer los cambios respectivos
	private void configurarRecordatorios() {
		VentConfigurarRecordatorios ventConfigurarRecordatorios = new VentConfigurarRecordatorios();
		ventConfigurarRecordatorios.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
            	mostrarDatosInventario();
        		mostrarDatosCartera();	
        		mostrarDatosCxP();
        		mostrarDatosVencimiento();
            }
        });
		ventConfigurarRecordatorios.setVisible(true);
		
	}
}
