package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Color;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import co.com.jungla.sac.persistencia.entidades.AnticipoCliente;
import co.com.jungla.sac.persistencia.entidades.Cliente;
import co.com.jungla.sac.persistencia.entidades.DevolucionCliente;
import co.com.jungla.sac.persistencia.entidades.ReciboCaja;
import co.com.jungla.sac.persistencia.entidades.VentaArticulos;

import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.Font;
import javax.swing.UIManager;

import co.com.jungla.sac.negocio.delegados.DelegadoAnticipoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoDevolucionCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoReciboCaja;
import co.com.jungla.sac.negocio.delegados.DelegadoVentaArticulos;


public class VentMostrarRecaudosCaja extends JFrame {

	private JPanel contentPane;
	private JTable tbRecaudos;
	private JFormattedTextField txtNetoAPagar;
	DefaultTableModel modeloTbRecaudos = new DefaultTableModel();
	private JTextField txtDptoCiudad;
	private List<VentaArticulos> listaVentasPendientes;
	private int filaSeleccionada;
	private JTextField txtIdentificacion;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JFormattedTextField txtRecaudado;
	private JFormattedTextField txtPendiente;
	private JTextField txtItems;
	private JFormattedTextField txtAnticipos;
	private JFormattedTextField txtNotasCred;
	private static int[] filasSeleccionadas; 


	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public VentMostrarRecaudosCaja() {
		setTitle("Mostrar Recaudos Caja");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1132, 455);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(13, 11, 1093, 61);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JPanel pnDptoCiudad = new JPanel();
		pnDptoCiudad.setLayout(null);
		pnDptoCiudad.setBackground(new Color(0, 51, 0));
		pnDptoCiudad.setBounds(469, 11, 201, 38);
		pn1.add(pnDptoCiudad);
		
		JLabel lblDptoCiudad = new JLabel("Departamento - Ciudad");
		lblDptoCiudad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDptoCiudad.setForeground(Color.WHITE);
		lblDptoCiudad.setBackground(Color.BLACK);
		lblDptoCiudad.setBounds(34, 0, 154, 14);
		pnDptoCiudad.add(lblDptoCiudad);
		
		txtDptoCiudad = new JTextField();
		txtDptoCiudad.setEditable(false);
		txtDptoCiudad.setBounds(0, 18, 201, 20);
		pnDptoCiudad.add(txtDptoCiudad);
		txtDptoCiudad.setColumns(10);
		
		JPanel pnCliente = new JPanel();
		pnCliente.setLayout(null);
		pnCliente.setBackground(new Color(0, 51, 0));
		pnCliente.setBounds(10, 11, 449, 38);
		pn1.add(pnCliente);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setForeground(Color.WHITE);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCliente.setBackground(Color.BLACK);
		lblCliente.setBounds(209, 0, 73, 14);
		pnCliente.add(lblCliente);
		
		txtIdentificacion = new JTextField();
		txtIdentificacion.setEditable(false);
		txtIdentificacion.setColumns(10);
		txtIdentificacion.setBounds(0, 18, 100, 20);
		pnCliente.add(txtIdentificacion);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(104, 18, 272, 20);
		pnCliente.add(txtNombre);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCliente();
			}
		});
		btnBuscar.setForeground(new Color(0, 51, 0));
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnBuscar.setBounds(380, 18, 68, 20);
		pnCliente.add(btnBuscar);
		
		JPanel pnDireccion = new JPanel();
		pnDireccion.setLayout(null);
		pnDireccion.setBackground(new Color(0, 51, 0));
		pnDireccion.setBounds(680, 11, 219, 38);
		pn1.add(pnDireccion);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setForeground(Color.WHITE);
		lblDireccin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDireccin.setBackground(Color.BLACK);
		lblDireccin.setBounds(81, 0, 112, 14);
		pnDireccion.add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(0, 18, 219, 20);
		pnDireccion.add(txtDireccion);
		
		JPanel pnTelefono = new JPanel();
		pnTelefono.setLayout(null);
		pnTelefono.setBackground(new Color(0, 51, 0));
		pnTelefono.setBounds(909, 11, 171, 38);
		pn1.add(pnTelefono);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelefono.setBackground(Color.BLACK);
		lblTelefono.setBounds(55, 0, 84, 14);
		pnTelefono.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(0, 18, 171, 20);
		pnTelefono.add(txtTelefono);
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(13, 83, 1093, 187);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JScrollPane scrRecaudos = new JScrollPane();
		scrRecaudos.setBounds(2, 2, 1089, 183);
		pn2.add(scrRecaudos);
		
		//tbRecaudos = new JTable();
		//tbRecaudos.setEnabled(false);
		tbRecaudos = new JTable(modeloTbRecaudos){
			//metodo que permite la no edicion de las columnas 3(Costo) y 4(Total)
			public boolean isCellEditable(int rowIndex, int colIndex) {
	            return false;
	        }
		};
		tbRecaudos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); 
		tbRecaudos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

		    //El método valueChange se debe sobreescribir obligatoriamente. 
		    //Se ejecuta automáticamente cada vez que se hace una nueva selección. 
		    @Override 
		    public void valueChanged(ListSelectionEvent e) { 
		        //Obtener el número de filas seleccionadas 
		    	filasSeleccionadas = tbRecaudos.getSelectedRows();
		    	
		    } 
		});
		/*tbRecuados.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbRecuados.rowAtPoint(point);
		        tbRecuados.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		        mostrarPopupTbArticulos();
			}
		});*/
		scrRecaudos.setViewportView(tbRecaudos);
      		
		JPanel pn3 = new JPanel();
		pn3.setBorder(new LineBorder(new Color(0, 0, 0)));
		pn3.setBounds(13, 340, 1093, 61);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JPanel pnNetoAPagar = new JPanel();
		pnNetoAPagar.setLayout(null);
		pnNetoAPagar.setBackground(new Color(0, 51, 0));
		pnNetoAPagar.setBounds(10, 11, 119, 38);
		pn3.add(pnNetoAPagar);
		
		JLabel lblNetoAPagar = new JLabel("Neto a Pagar");
		lblNetoAPagar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNetoAPagar.setForeground(Color.WHITE);
		lblNetoAPagar.setBackground(SystemColor.desktop);
		lblNetoAPagar.setBounds(25, 0, 84, 14);
		pnNetoAPagar.add(lblNetoAPagar);
		
		txtNetoAPagar = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtNetoAPagar.setColumns(10);
		txtNetoAPagar.setBounds(0, 18, 119, 20);
		txtNetoAPagar.setEditable(false);
		txtNetoAPagar.setValue(0);
		pnNetoAPagar.add(txtNetoAPagar);
		
		JPanel pnRecaudado = new JPanel();
		pnRecaudado.setLayout(null);
		pnRecaudado.setBackground(new Color(0, 51, 0));
		pnRecaudado.setBounds(139, 11, 119, 38);
		pn3.add(pnRecaudado);
		
		JLabel lblRecaudado = new JLabel("Recaudado");
		lblRecaudado.setForeground(Color.WHITE);
		lblRecaudado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRecaudado.setBackground(Color.BLACK);
		lblRecaudado.setBounds(25, 0, 84, 14);
		pnRecaudado.add(lblRecaudado);
		
		txtRecaudado = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtRecaudado.setValue(0);
		txtRecaudado.setEditable(false);
		txtRecaudado.setColumns(10);
		txtRecaudado.setBounds(0, 18, 119, 20);
		pnRecaudado.add(txtRecaudado);
		
		JPanel pnPendiente = new JPanel();
		pnPendiente.setLayout(null);
		pnPendiente.setBackground(new Color(0, 51, 0));
		pnPendiente.setBounds(268, 11, 119, 38);
		pn3.add(pnPendiente);
		
		JLabel lblPendiente = new JLabel("Pendiente");
		lblPendiente.setForeground(Color.WHITE);
		lblPendiente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPendiente.setBackground(Color.BLACK);
		lblPendiente.setBounds(28, 0, 75, 14);
		pnPendiente.add(lblPendiente);
		
		txtPendiente =new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtPendiente.setValue(0);
		txtPendiente.setEditable(false);
		txtPendiente.setColumns(10);
		txtPendiente.setBounds(0, 18, 119, 20);
		pnPendiente.add(txtPendiente);
		
		JPanel pnItems = new JPanel();
		pnItems.setLayout(null);
		pnItems.setBackground(new Color(0, 51, 0));
		pnItems.setBounds(397, 11, 73, 38);
		pn3.add(pnItems);
		
		JLabel lblItems = new JLabel("Items");
		lblItems.setForeground(Color.WHITE);
		lblItems.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblItems.setBackground(Color.BLACK);
		lblItems.setBounds(20, 0, 48, 14);
		pnItems.add(lblItems);
		
		txtItems = new JTextField();
		txtItems.setText("0");
		txtItems.setEditable(false);
		txtItems.setColumns(10);
		txtItems.setBounds(0, 18, 73, 20);
		pnItems.add(txtItems);
		
		JPanel pnDepositos = new JPanel();
		pnDepositos.setLayout(null);
		pnDepositos.setBackground(new Color(0, 51, 0));
		pnDepositos.setBounds(480, 11, 119, 38);
		pn3.add(pnDepositos);
		
		JLabel lbAnticipos = new JLabel("Anticipos");
		lbAnticipos.setForeground(Color.WHITE);
		lbAnticipos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbAnticipos.setBackground(Color.BLACK);
		lbAnticipos.setBounds(29, 0, 69, 14);
		pnDepositos.add(lbAnticipos);
		
		txtAnticipos = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtAnticipos.setValue(0);
		txtAnticipos.setEditable(false);
		txtAnticipos.setColumns(10);
		txtAnticipos.setBounds(0, 18, 119, 20);
		pnDepositos.add(txtAnticipos);
		
		JPanel pnNotasCred = new JPanel();
		pnNotasCred.setLayout(null);
		pnNotasCred.setBackground(new Color(0, 51, 0));
		pnNotasCred.setBounds(609, 11, 97, 38);
		pn3.add(pnNotasCred);
		
		JLabel lblNotasCred = new JLabel("Notas Cred");
		lblNotasCred.setForeground(Color.WHITE);
		lblNotasCred.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNotasCred.setBackground(Color.BLACK);
		lblNotasCred.setBounds(15, 0, 77, 14);
		pnNotasCred.add(lblNotasCred);
		
		txtNotasCred = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtNotasCred.setValue(0);
		txtNotasCred.setEditable(false);
		txtNotasCred.setColumns(10);
		txtNotasCred.setBounds(0, 18, 97, 20);
		pnNotasCred.add(txtNotasCred);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarDatos();
			}
		});
		btnLimpiar.setForeground(new Color(0, 51, 0));
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLimpiar.setBackground(UIManager.getColor("Button.background"));
		btnLimpiar.setBounds(760, 18, 119, 23);
		pn3.add(btnLimpiar);
		
		JButton btnRecaudarSeleccionados = new JButton("Recaudar Seleccionados");
		btnRecaudarSeleccionados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if(llenarModeloRecuadosSeleccionados()==null){
					abrirVentanaRegistrarRecaudos();
				//}
			}
		});
		btnRecaudarSeleccionados.setForeground(new Color(0, 51, 0));
		btnRecaudarSeleccionados.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRecaudarSeleccionados.setBackground(UIManager.getColor("Button.background"));
		btnRecaudarSeleccionados.setBounds(889, 18, 190, 23);
		pn3.add(btnRecaudarSeleccionados);
		
		JLabel lblNota = new JLabel("Si se va a crear un Anticipo Cliente para un documento que aun no ha sido generado en el sistema presione el bot\u00F3n a continuaci\u00F3n.");
		lblNota.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNota.setBounds(186, 281, 747, 14);
		contentPane.add(lblNota);
		
		JButton btnCrearAnticipo = new JButton("Crear Anticipo");
		btnCrearAnticipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaRegistrarAnticipoCliente();
			}
		});
		btnCrearAnticipo.setForeground(new Color(0, 51, 0));
		btnCrearAnticipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCrearAnticipo.setBackground(SystemColor.activeCaptionBorder);
		btnCrearAnticipo.setBounds(488, 306, 135, 23);
		contentPane.add(btnCrearAnticipo);
	}
	
	//Metodo para agregar los datos de la cuenta por cobrar desde la ventana buscar cliente a esta ventana
	public void agregarDatosARecuadosCaja(Cliente cliente ){
		//DelegadoCuentaXCobrar delegadoCuentaXCobrar = new DelegadoCuentaXCobrar();
		DelegadoVentaArticulos delegadoVentaArticulos = new DelegadoVentaArticulos();
		
		txtIdentificacion.setText(cliente.getIdentificacion());
		txtNombre.setText(cliente.getNombre());
		txtDptoCiudad.setText(cliente.getMunicipio().getDepartamento().getNombre()+" - "+cliente.getMunicipio().getNombre());
		txtDireccion.setText(cliente.getDireccion());
		txtTelefono.setText(cliente.getTelefono());
		
		listaVentasPendientes = delegadoVentaArticulos.traerVentaPendientePorCliente(cliente.getIdentificacion(),"Pendiente");
		
		llenarTabla(listaVentasPendientes);
		txtNetoAPagar.setValue(calcularTotalNetoAPagar());
		txtPendiente.setValue(calcularTotalPendiente());
		txtRecaudado.setValue(calcularTotalRecaudado());
		calcularCantidadCuentaxCobrar();
		txtAnticipos.setValue(calcularTotalAnticipos(cliente.getIdentificacion()));
		txtNotasCred.setValue(calcularTotalNotasCredito(cliente.getIdentificacion()));
		verNumeroAnticipos();
		verNumeroDevoluciones();
	}
	
	//Metodo para abrir la ventana buscar cliente
	private void buscarCliente() {
		VentBuscarClienteRecaudosCaja ventBuscarClienteRecaudosCaja = new VentBuscarClienteRecaudosCaja();
		ventBuscarClienteRecaudosCaja.setVisible(true);
		setVisible(false);
	}
	
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbArticulos() {
		modeloTbRecaudos.addColumn("Venta");
		modeloTbRecaudos.addColumn("Generado");
		modeloTbRecaudos.addColumn("Fecha Limite");
		modeloTbRecaudos.addColumn("Días");
		modeloTbRecaudos.addColumn("Valor Doc");
		modeloTbRecaudos.addColumn("Saldo");
		modeloTbRecaudos.addColumn("F. Ultimo Pago");
		modeloTbRecaudos.addColumn("Recibos");
		modeloTbRecaudos.addColumn("Forma Pago");
		modeloTbRecaudos.addColumn("Vendedor");
		
		tbRecaudos.setModel(modeloTbRecaudos);
	}
	
	//Metodo para llenar la tabla cuando recibe como parametro una lista de cuentas por cobrar
	private void llenarTabla( List<VentaArticulos> listaVentasPendientes) {
		
		DelegadoReciboCaja delegadoReciboCaja = new DelegadoReciboCaja();
		double acumulador = 0;
		double totalRecibosCaja = 0;
		Date ultimaFechaPago = null;
		
		if(tbRecaudos.getModel().getColumnCount()==0){
			llenarColumnasTbArticulos();
		}
		
		String [] fila = new String[modeloTbRecaudos.getColumnCount()];
		
		for(VentaArticulos ventasPendientes : listaVentasPendientes){
			
			List<ReciboCaja> listaRecibosCajaPorVenta = delegadoReciboCaja.traerRecibosCajaPorVenta(ventasPendientes.getIdVenta());
			fila[0]= String.valueOf(ventasPendientes.getIdVenta());
			fila[1]= convertirDateAString(ventasPendientes.getFechaGeneracion());
			fila[2]= convertirDateAString(ventasPendientes.getFechaLimitePago());
			fila[3]= String.valueOf(calcularDiasPlazo(ventasPendientes.getFechaLimitePago(), new Date()));
			fila[4]= formatearNumero(ventasPendientes.getTotalVenta());
			
			for(ReciboCaja recibos : listaRecibosCajaPorVenta){
				fila[7]= String.valueOf(recibos.getIdReciboCaja()+" "); 
				System.out.println(recibos.getIdReciboCaja());
				acumulador= recibos.getTotalRecibido();
				totalRecibosCaja += acumulador;
				ultimaFechaPago = recibos.getFechaRecaudo();
				
			}
			
			fila[5]= formatearNumero(ventasPendientes.getTotalVenta()-totalRecibosCaja);
			
			if(ultimaFechaPago == null){
				fila[6]= "";
			}else{
				fila[6]= convertirDateAString(ultimaFechaPago);
			}
			fila[8]= ventasPendientes.getFormaPagoCliente().getDescripcion();
			fila[9]= ventasPendientes.getVendedores().getNombre();
			
			modeloTbRecaudos.addRow(fila);
		}
		
		tbRecaudos.setModel(modeloTbRecaudos);
		colorearDiasPlazoEnTabla(tbRecaudos);
		colorearFechaPagoVencidaEnTabla(tbRecaudos);
	}
	
	//Metodo que permite la conversion de un dato de tipo date a uno de tipo string
	public String convertirDateAString(Date strFecha){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(strFecha);
		
		return fecha;
	}
	
	//Metodo para limpiar la tabla 
	private void limpiarRegistrosTabla(){
	   for (int i = 0; i < tbRecaudos.getRowCount(); i++) {
	       modeloTbRecaudos.removeRow(i);
	       i-=1;
	   }
	}
	
	//Metodo para calcular los dias entre la fecha de pago y la actual
	private int calcularDiasPlazo(Date fechaPago, Date fechaActual){
		long diferencia_fechas = fechaPago.getTime() - fechaActual.getTime();
		long dias = diferencia_fechas/(1000*60*60*24);
		return (int)dias;
	}
	//Metodo para limpiar todos los datos de la ventana
	private void limpiarDatos(){
		txtIdentificacion.setText("");
		txtNombre.setText("");
		txtDptoCiudad.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		limpiarRegistrosTabla();
		txtNetoAPagar.setValue(0);
		txtRecaudado.setValue(0);
		txtPendiente.setValue(0);
		txtItems.setText("0");
		txtAnticipos.setValue(0);
		txtNotasCred.setValue(0);
	}
	
	//Metodo para convertir un numero corriente en formato de pesos y decimales
	private String formatearNumero(Double numero){
		NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es","CO"));
		return formato.format(numero); 
	}
	
	//Metodo para convertir un numero en formato de pesos y decimales en un numero corriente
	private String desformatearNumero(String numero){
		String numeroReemplazado=numero.replace("$", "");
		String numeroReemplazado1=numeroReemplazado.replace(".", "");
		String numeroReemplazado2=numeroReemplazado1.replace(",", ".");
		return numeroReemplazado2;
	}
	
	//Metodo para calcular el total neto a pagar de las cuentas x cobrar por cliente
	private double calcularTotalNetoAPagar() {
		double netoAPagar = 0;
		double acumulador = 0;
		
		for(int i=0; i<tbRecaudos.getRowCount(); i++) {
			acumulador= Double.parseDouble(desformatearNumero(tbRecaudos.getValueAt(i,4).toString()));
			netoAPagar += acumulador;
			
		}
		
		return netoAPagar;
	}
	
	//Metodo para calcular el total pendiente de las cuentas x cobrar por cliente
	private double calcularTotalPendiente() {
		double totalPendiente = 0;
		double acumulador = 0;
		
		for(int i=0; i<tbRecaudos.getRowCount(); i++) {
			acumulador= Double.parseDouble(desformatearNumero(tbRecaudos.getValueAt(i,5).toString()));
			totalPendiente += acumulador;
			
		}
		
		return totalPendiente;
	}
	
	//Metodo para calcular el total recaudado de las cuentas x cobrar por cliente
	private double calcularTotalRecaudado() {
		double totalRecaudado = calcularTotalNetoAPagar() - calcularTotalPendiente();
		return totalRecaudado;
	}
	
	//Metodo que permite calcular la cantidad de cuentas x cobrar encontradas
	private void calcularCantidadCuentaxCobrar() {
		
		int cantidad = tbRecaudos.getRowCount();
		txtItems.setText(Integer.toString(cantidad));
		
	}
	
	//Metodo para calcular el total de anticipos del cliente que estan libres de uso
	private double calcularTotalAnticipos(String identificacion) {
		DelegadoAnticipoCliente delegadoAnticipoCliente = new DelegadoAnticipoCliente();
		List<AnticipoCliente> listaAnticiposCliente = delegadoAnticipoCliente.listarAnticipoClientePorIdentificacion(identificacion);
		double acumulador = 0;
		double totalAnticipos = 0;
		
		for(AnticipoCliente anticipos : listaAnticiposCliente){
			if("Inactivo"!=anticipos.getEstadoActividad() && "Usado"!=anticipos.getEstadoAnticipo()){
				acumulador = anticipos.getValorAnticipo();
				totalAnticipos +=acumulador;
			}
		}
		
		return totalAnticipos;
	}
	
	//Metodo para calcular el total de notas credito del cliente que estan pendientes
	private double calcularTotalNotasCredito(String identificacion) {
		DelegadoDevolucionCliente delegadoDevolucionCliente = new DelegadoDevolucionCliente();
		List<DevolucionCliente> listaNotasCredito = delegadoDevolucionCliente.listarDevolucionClientePorIdentificacion(identificacion);
		double acumulador = 0;
		double totalNotasCredito = 0;
		int numeroNotasCredito = 0;
		
		for(DevolucionCliente notasCredito : listaNotasCredito){
			if("Pendiente"!=notasCredito.getEstado()){
				acumulador = (notasCredito.getTotalDevolucion());
				totalNotasCredito +=acumulador;
				numeroNotasCredito++;
			}
		}
		if(numeroNotasCredito == 0){
		}else{
			JOptionPane.showMessageDialog(null, "Existen " +numeroNotasCredito+" NOTAS CRÉDITOS para descontarse con este cliente. Por favor tenga este dato en cuenta en los medios de pago.","ATENCION",JOptionPane.INFORMATION_MESSAGE);
		}
		
		return totalNotasCredito;
	}
	
	//Metodo para mostrar el numero de anticipos que tiene el cliente elegido
	private void verNumeroAnticipos(){
		DelegadoAnticipoCliente delegadoAnticipoCliente = new DelegadoAnticipoCliente();
		List<AnticipoCliente> listaAnticiposCliente = delegadoAnticipoCliente.listarAnticipoClientePorIdentificacion(txtIdentificacion.getText());
		int numeroAnticipos = 0;
		
		for(AnticipoCliente anticipos : listaAnticiposCliente){
			if("Inactivo"!=anticipos.getEstadoActividad() && "Usado"!=anticipos.getEstadoAnticipo()){
				numeroAnticipos++;
			}
		}
		
		if(numeroAnticipos == 0){
		}else{
			JOptionPane.showMessageDialog(null, "Existen " +numeroAnticipos+" ANTICIPOS para descontarse con este cliente. Por favor tenga este dato en cuenta en los medios de pago.","ATENCION",JOptionPane.INFORMATION_MESSAGE);
		}
	}
		
	//Metodo para mostrar el numero de devoluciones que tiene el cliente elegido
	private void verNumeroDevoluciones(){
		DelegadoDevolucionCliente delegadoDevolucionCliente = new DelegadoDevolucionCliente();
		List<DevolucionCliente> listaNotasCredito = delegadoDevolucionCliente.listarDevolucionClientePorIdentificacion(txtIdentificacion.getText());
		int numeroNotasCredito = 0;
		
		for(DevolucionCliente notasCredito : listaNotasCredito){
			if("Pendiente"!=notasCredito.getEstado()){
				numeroNotasCredito++;
			}
		}
		if(numeroNotasCredito == 0){
		}else{
			JOptionPane.showMessageDialog(null, "Existen " +numeroNotasCredito+" NOTAS CRÉDITOS para descontarse con este cliente. Por favor tenga este dato en cuenta en los medios de pago.","ATENCION",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private TableModel llenarModeloRecuadosSeleccionados(){
		DefaultTableModel modeloRecaudosSeleccionados = new DefaultTableModel();
		DelegadoReciboCaja delegadoReciboCaja = new DelegadoReciboCaja();
		double acumulador = 0;
		double totalRecibosCaja = 0;
		
		modeloRecaudosSeleccionados.addColumn("Venta");
		modeloRecaudosSeleccionados.addColumn("Generado");
		modeloRecaudosSeleccionados.addColumn("Fecha Limite");
		modeloRecaudosSeleccionados.addColumn("Días");
		modeloRecaudosSeleccionados.addColumn("Valor Total");
		modeloRecaudosSeleccionados.addColumn("Valor Recibos");
		modeloRecaudosSeleccionados.addColumn("Saldo");
		modeloRecaudosSeleccionados.addColumn("Recibos");
		modeloRecaudosSeleccionados.addColumn("Forma Pago");
		
		String [] fila = new String[modeloRecaudosSeleccionados.getColumnCount()];
		try{
			for( int i = 0; i < filasSeleccionadas.length; i++){
				
				List<ReciboCaja> listaRecibosCajaPorVenta = delegadoReciboCaja.traerRecibosCajaPorVenta(Integer.parseInt(tbRecaudos.getValueAt(filasSeleccionadas[i], 0).toString()));
				
				fila[0]= tbRecaudos.getValueAt(filasSeleccionadas[i], 0).toString();
				fila[1]= tbRecaudos.getValueAt(filasSeleccionadas[i], 1).toString();
				fila[2]= tbRecaudos.getValueAt(filasSeleccionadas[i], 2).toString();
				fila[3]= tbRecaudos.getValueAt(filasSeleccionadas[i], 3).toString();
				fila[4]= tbRecaudos.getValueAt(filasSeleccionadas[i], 4).toString();
				
				for(ReciboCaja recibos : listaRecibosCajaPorVenta){
					fila[7]= String.valueOf(recibos.getIdReciboCaja()+" ");
					acumulador= recibos.getTotalRecibido();
					totalRecibosCaja += acumulador;
				}
				
				fila[5]= formatearNumero(totalRecibosCaja);
				fila[6]= formatearNumero(Double.parseDouble(desformatearNumero(tbRecaudos.getValueAt(filasSeleccionadas[i], 4).toString()))-totalRecibosCaja);
				
				fila[8]= tbRecaudos.getValueAt(filasSeleccionadas[i], 8).toString();
				
				modeloRecaudosSeleccionados.addRow(fila);
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Debe elegir al menos 1 documento para pagar.");
		}
		return modeloRecaudosSeleccionados;
			
		
		
	}
	
	//Metodo para enviar los datos a la ventana de anticipos para su registro
	private void abrirVentanaRegistrarAnticipoCliente(){
		VentRegistrarAnticipoCliente ventRegistrarAnticipoCliente = new VentRegistrarAnticipoCliente(txtIdentificacion.getText(), txtNombre.getText(), txtDptoCiudad.getText(), txtDireccion.getText(), txtTelefono.getText());
		ventRegistrarAnticipoCliente.setVisible(true);
		txtNetoAPagar.setValue(calcularTotalNetoAPagar());
		txtPendiente.setValue(calcularTotalPendiente());
		txtRecaudado.setValue(calcularTotalRecaudado());
		calcularCantidadCuentaxCobrar();
		txtAnticipos.setValue(calcularTotalAnticipos(txtIdentificacion.getText()));
		txtNotasCred.setValue(calcularTotalNotasCredito(txtIdentificacion.getText()));
	}
	
	private void abrirVentanaRegistrarRecaudos(){
		VentRegistrarRecaudosCaja ventRegistrarRecaudosCaja = new VentRegistrarRecaudosCaja();
		ventRegistrarRecaudosCaja.agregarDatosAnticipos(llenarModeloRecuadosSeleccionados(), txtIdentificacion.getText(), txtNombre.getText(),txtDptoCiudad.getText(),txtDireccion.getText(), txtTelefono.getText());
		ventRegistrarRecaudosCaja.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
            	dispose();  
            }
        });
		ventRegistrarRecaudosCaja.setVisible(true);
	}
	
	//Metodo para colorear la columna de fecha pago vencida basandose en los dias vencidos
	private void colorearFechaPagoVencidaEnTabla(JTable tablaVentas) {
		tablaVentas.getColumn("Fecha Limite").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			if(Integer.parseInt(table.getValueAt(row, 3).toString())>0){
				comp.setBackground(Color.WHITE);
			}else{
				comp.setBackground(new Color(204, 102, 102));
			}
	        return comp; 
			}}); 
	}
	
	//Metodo para colorear la columna de dias plazo basandose en los dias vencidos
	private void colorearDiasPlazoEnTabla(JTable tablaVentas) {
		tablaVentas.getColumn("Días").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			if(Integer.parseInt(table.getValueAt(row, 3).toString())>0){
				comp.setBackground(Color.WHITE);
			}else{
				comp.setBackground(new Color(204, 102, 102));
			}
	        return comp; 
			}}); 
	}
}
