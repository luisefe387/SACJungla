package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import co.com.jungla.sac.persistencia.entidades.Articulo;
import co.com.jungla.sac.persistencia.entidades.Contabilizacion;
import co.com.jungla.sac.persistencia.entidades.ControlInventario;
import co.com.jungla.sac.persistencia.entidades.DetalleVenta;
import co.com.jungla.sac.persistencia.entidades.DetalleDevolucionCliente;
import co.com.jungla.sac.persistencia.entidades.DevolucionCliente;
import co.com.jungla.sac.persistencia.entidades.KardexElectronico;
import co.com.jungla.sac.persistencia.entidades.VentaArticulos;

import co.com.jungla.sac.negocio.delegados.DelegadoContabilizacion;
import co.com.jungla.sac.negocio.delegados.DelegadoControlInventario;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleDevolucionCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleVenta;
import co.com.jungla.sac.negocio.delegados.DelegadoDevolucionCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoKardexElectronico;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

//import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTextPane;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro de la devolucion a los clientes por una venta efectuada anteriormente y su contabilizacion
 * @author Luis Fernando Pedroza T.
 * @version: 21/08/2016
 */
public class VentRegistrarDevolucionCliente extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtItem;
	private JTextField txtNombre;
	private JTextField txtIdentificacion;
	private JTextField txtTotal;
	private JTextField txtConsectivo;
	private JFormattedTextField txtDevolucion;
	private JDateChooser dchFecha;
	private JTable tbArticulos;
	private DefaultTableModel modeloTbArticulos = new DefaultTableModel();
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private List <DetalleDevolucionCliente> ultimaDetalleDevolucionCliente;
	private DelegadoKardexElectronico delegadoKardexElectronico = new DelegadoKardexElectronico();
	private List <KardexElectronico> ultimoRegistroPorArticuloKardex1;
	private KardexElectronico ultimoRegistroPorArticuloKardex;
	private DevolucionCliente ultimaDevolucionCliente;
	private DevolucionCliente devolucionCliente = new DevolucionCliente();
	private Date fechaVenta;
	private double totalCostoVenta;
	private JTextPane txpObservaciones;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentRegistrarDevolucionCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarDevolucionCliente.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Registrar Devolucion a Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 937, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 901, 62);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JPanel pnCliente = new JPanel();
		pnCliente.setBounds(252, 11, 388, 38);
		pn1.add(pnCliente);
		pnCliente.setBackground(new Color(0, 51, 0));
		pnCliente.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCliente.setBackground(SystemColor.desktop);
		lblCliente.setForeground(SystemColor.window);
		lblCliente.setBounds(168, 0, 73, 14);
		pnCliente.add(lblCliente);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(0, 18, 254, 20);
		pnCliente.add(txtNombre);
		
		txtIdentificacion = new JTextField();
		txtIdentificacion.setEditable(false);
		txtIdentificacion.setBounds(258, 18, 129, 20);
		pnCliente.add(txtIdentificacion);
		txtIdentificacion.setColumns(10);
		
		JPanel pnFecha = new JPanel();
		pnFecha.setLayout(null);
		pnFecha.setBackground(new Color(0, 51, 0));
		pnFecha.setBounds(97, 11, 145, 38);
		pn1.add(pnFecha);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setBackground(SystemColor.desktop);
		lblFecha.setBounds(57, 0, 60, 14);
		pnFecha.add(lblFecha);
		
		dchFecha = new JDateChooser();
		dchFecha.setDate(new Date());
		dchFecha.setBounds(0, 18, 144, 20);
		pnFecha.add(dchFecha);
		
		JPanel pnConsectivo = new JPanel();
		pnConsectivo.setLayout(null);
		pnConsectivo.setBackground(new Color(0, 51, 0));
		pnConsectivo.setBounds(10, 11, 77, 38);
		pn1.add(pnConsectivo);
		
		JLabel lblConsectivo = new JLabel("Venta");
		lblConsectivo.setForeground(Color.WHITE);
		lblConsectivo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConsectivo.setBackground(SystemColor.desktop);
		lblConsectivo.setBounds(22, 0, 52, 14);
		pnConsectivo.add(lblConsectivo);
		
		txtConsectivo = new JTextField();
		txtConsectivo.setEditable(false);
		txtConsectivo.setColumns(10);
		txtConsectivo.setDocument(new LimitadorCaracteres());
		txtConsectivo.setBounds(0, 18, 77, 20);
		pnConsectivo.add(txtConsectivo);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaciones.setBounds(650, 3, 221, 14);
		pn1.add(lblObservaciones);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(650, 20, 239, 34);
		pn1.add(scrollPane);
		
		txpObservaciones = new JTextPane();
		scrollPane.setViewportView(txpObservaciones);
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 109, 906, 188);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JScrollPane scrArticulos = new JScrollPane();
		scrArticulos.setBounds(2, 2, 902, 184);
		pn2.add(scrArticulos);
		
		tbArticulos = new JTable(modeloTbArticulos){
			public boolean isCellEditable(int rowIndex, int colIndex) {
				if (colIndex==6) {
			        return true;
			    }
	            return false;
	        }
		}
		;
		
		scrArticulos.setViewportView(tbArticulos);
		
		actualizarDevolucionCliente();

		JPanel pn3 = new JPanel();
		pn3.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn3.setBounds(10, 308, 906, 62);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JPanel pnSubtotal = new JPanel();
		pnSubtotal.setLayout(null);
		pnSubtotal.setBackground(new Color(0, 51, 0));
		pnSubtotal.setBounds(295, 11, 104, 38);
		pn3.add(pnSubtotal);
		
		JLabel lblSubtotal = new JLabel("Devoluci\u00F3n");
		lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSubtotal.setForeground(Color.WHITE);
		lblSubtotal.setBackground(SystemColor.desktop);
		lblSubtotal.setBounds(22, 0, 72, 14);
		pnSubtotal.add(lblSubtotal);
		
		txtDevolucion = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtDevolucion.setForeground(new Color(255, 0, 0));
		txtDevolucion.setBackground(new Color(255, 255, 51));
		txtDevolucion.setBounds(0, 18, 104, 20);
		txtDevolucion.setValue(0);
		txtDevolucion.setEditable(false);
		pnSubtotal.add(txtDevolucion);
		
		JPanel pnTotal = new JPanel();
		pnTotal.setLayout(null);
		pnTotal.setBackground(new Color(0, 51, 0));
		pnTotal.setBounds(111, 11, 104, 38);
		pn3.add(pnTotal);
		
		JLabel lblTotal = new JLabel("Total ");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setBackground(SystemColor.desktop);
		lblTotal.setBounds(36, 0, 53, 14);
		pnTotal.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setText((String) null);
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);
		txtTotal.setBounds(0, 18, 104, 20);
		pnTotal.add(txtTotal);
		
		JPanel pnItem = new JPanel();
		pnItem.setLayout(null);
		pnItem.setBackground(new Color(0, 51, 0));
		pnItem.setBounds(225, 11, 60, 38);
		pn3.add(pnItem);
		
		JLabel lblItem = new JLabel("Items");
		lblItem.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblItem.setForeground(Color.WHITE);
		lblItem.setBackground(SystemColor.desktop);
		lblItem.setBounds(14, 0, 49, 14);
		pnItem.add(lblItem);
		
		txtItem = new JTextField();
		txtItem.setText("0");
		txtItem.setColumns(10);
		txtItem.setBounds(0, 18, 60, 20);
		txtItem.setEditable(false);
		pnItem.add(txtItem);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(610, 20, 87, 23);
		pn3.add(btnCerrar);
		
		JButton btnGuardarDevolcion = new JButton("Guardar Devoluci\u00F3n");
		btnGuardarDevolcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();	
			}
		});
		btnGuardarDevolcion.setForeground(new Color(255, 0, 0));
		btnGuardarDevolcion.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGuardarDevolcion.setBounds(439, 20, 161, 23);
		pn3.add(btnGuardarDevolcion);
		
		JButton btnIrAtras = new JButton("Ir Atras");
		btnIrAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentBuscarVentaParaDevCliente ventBuscarVentaParaDevCliente= new VentBuscarVentaParaDevCliente();
				ventBuscarVentaParaDevCliente.setVisible(true);
				dispose();
			}
		});
		btnIrAtras.setForeground(new Color(0, 51, 0));
		btnIrAtras.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnIrAtras.setBounds(707, 20, 87, 23);
		pn3.add(btnIrAtras);
		
		JLabel lblNotaParaDigitar = new JLabel("NOTA: para digitar la CANTIDAD DEVUELTA del articulo seleccionado, dar clic en la celda repintada de AMARILLO");
		lblNotaParaDigitar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNotaParaDigitar.setBounds(10, 84, 772, 14);
		contentPane.add(lblNotaParaDigitar);
		
		//Metodo que debe ejecutar la ventana al inicializarse
		llenarColumnasTbArticulos();
		
	}

	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbArticulos() {
		modeloTbArticulos.addColumn("Codigo Art.");
		modeloTbArticulos.addColumn("Linea");
		modeloTbArticulos.addColumn("Articulo");
		modeloTbArticulos.addColumn("Und");
		modeloTbArticulos.addColumn("Cant");
		modeloTbArticulos.addColumn("Dev");
		modeloTbArticulos.addColumn("Devolución");
		modeloTbArticulos.addColumn("Vlr.Unit");
		modeloTbArticulos.addColumn("Total");
		modeloTbArticulos.addColumn("Cod. Detalle");
		
		tbArticulos.setModel(modeloTbArticulos);
	}
	//Metodo para actualizar la devolucion del articulo en la tabla
	private void actualizarDevolucionCliente() {
		
      //Evento que permite la modificacion de la tabla cada vez que esta es editada
      		tbArticulos.getModel().addTableModelListener(new TableModelListener() {
                  @Override
                  public void tableChanged(TableModelEvent evento) {
                      actualizarTabla(evento);
                  }
              });
	}
	
	//Metodo para calcular el total devuelto de los articulos a devolver
	private void calcularTotalDevolucion(){
		
		double totalDevolucion;
		double costoUnitario;
		double totalDevolucion1 = (double) 0;
		int cantADevolver;
		
		for(int i=0; i<tbArticulos.getRowCount(); i++) {
			
			cantADevolver= Integer.parseInt(String.valueOf(tbArticulos.getValueAt(i, 6)));
			costoUnitario = Double.parseDouble(desformatearNumero(String.valueOf(tbArticulos.getValueAt(i,7))));
			totalDevolucion= cantADevolver * costoUnitario;
			totalDevolucion1 += totalDevolucion;
		}
		
		txtDevolucion.setValue(totalDevolucion1);

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

	//Metodo para actualizar la tabla cada vez que haya un cambio en la misma
	private void actualizarTabla(TableModelEvent evento) {
		
		if (evento.getType() == TableModelEvent.UPDATE) {

            // Se obtiene el modelo de la tabla y la fila/columna que han cambiado.
            TableModel modelo = ((TableModel) (evento.getSource()));
            int fila = evento.getFirstRow();
            int columna = evento.getColumn();
           // Se aplica los calculos solamente a la columnas 3 (costo) y 4 (total)
            if (columna == 6) {
	            try{
	            	 int cantADevolver = Integer.parseInt(String.valueOf(modelo.getValueAt(fila, 6)));
	            	 int cantDevuelta = Integer.parseInt(String.valueOf(modelo.getValueAt(fila, 5)));
	            	 int cantCompra = Integer.parseInt(String.valueOf(modelo.getValueAt(fila, 4)));
	            	
	            	 if(cantADevolver>(cantCompra-cantDevuelta)){
	            		 JOptionPane.showMessageDialog(null, "Cantidad por encima de lo facturado");
	            		 modelo.setValueAt("0", fila, 6);
	            	 }else{
	            		 calcularTotalDevolucion();
	            	 }
	            	 
	            }catch(Exception ex){
	            	ex.getMessage();
	            	modelo.setValueAt("0", fila, 6);
           		 	JOptionPane.showMessageDialog(null, "Debe digitar alguna cantidad");
	            }
            }
        }
		
	}
	//Validar los datos ingresados
	private void validarDatos() {
		String nl = System.getProperty("line.separator");
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de grabar esta DEVOLUCION: "+txtDevolucion.getText()+nl, null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			try{
				registrarDevolucionCliente();
				contabilizarDevolucionCliente();
			}catch(Exception e){
				e.getMessage();
			}
		}else{
		
		}
	}	
	
	//Metodo para registrar los datos ingresados de la devolucion
	private void registrarDevolucionCliente() {
		DelegadoDevolucionCliente delegadoDevolucionCliente = new DelegadoDevolucionCliente();
		VentaArticulos ventaArticulos = new VentaArticulos();
		
		devolucionCliente.setConcepto("Dev-Cliente");
		devolucionCliente.setEstado("Pendiente");
		devolucionCliente.setFecha(dchFecha.getDate());
		devolucionCliente.setFechaRecaudo(null);
		devolucionCliente.setIdRecibo(0);
		devolucionCliente.setTotalDevolucion(Double.parseDouble(txtDevolucion.getValue().toString()));
		ventaArticulos.setIdVenta(Integer.parseInt(txtConsectivo.getText()));
		devolucionCliente.setVentaArticulos(ventaArticulos);
		devolucionCliente.setObservaciones(txpObservaciones.getText());

		delegadoDevolucionCliente.insertarDevolucionCliente(devolucionCliente);
		
		ultimaDevolucionCliente = delegadoDevolucionCliente.traerUltimaDevolucionCliente().get(0);
		
		registrarDetalleDevolucionCliente();
		abrirVentanaDevolucionRegistrada(Integer.parseInt(txtConsectivo.getText()));
	}
	
	//Metodo para abrir una ventana confirmando el exito del registro de la devolucion
	private void abrirVentanaDevolucionRegistrada(int idVenta) {
		VentMostrarDevolucionClienteRegistrada ventMostrarDevolucionClienteRegistrada =new VentMostrarDevolucionClienteRegistrada(ultimaDevolucionCliente, idVenta, fechaVenta);
		ventMostrarDevolucionClienteRegistrada.setVisible(true);
	}

	//Metodo para convertir una fecha de tipo date a una cadena
	public String convertirDateAString(Date strFecha){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(strFecha);
		
		return fecha;
	}
	//Metodo para registrar el detalle de la devolucion del cliente
	public void registrarDetalleDevolucionCliente(){
		DelegadoDetalleDevolucionCliente delegadoDetalleDevolucionCliente = new DelegadoDetalleDevolucionCliente();
		DetalleDevolucionCliente detalleDevolucionCliente = new DetalleDevolucionCliente();
		double acumulador = 0;
		
		for(int i=0; i< modeloTbArticulos.getRowCount();i++){
			
			if("0".equals(modeloTbArticulos.getValueAt(i, 6).toString())){
				
			}else{
				devolucionCliente.setIdDevolucionCliente(ultimaDevolucionCliente.getIdDevolucionCliente());
				detalleDevolucionCliente.setDevolucionCliente(devolucionCliente);
				detalleDevolucionCliente.setCodigoArticulo(Integer.parseInt(modeloTbArticulos.getValueAt(i, 0).toString()));
				detalleDevolucionCliente.setVlrUnitario(Double.parseDouble(desformatearNumero(modeloTbArticulos.getValueAt(i, 7).toString())));
				detalleDevolucionCliente.setCantidadDevuelta(Integer.parseInt(modeloTbArticulos.getValueAt(i, 6).toString()));
				
				for(int j=0; j< modeloTbArticulos.getRowCount();j++){
					acumulador = obtenerCostoArticulo(Integer.parseInt( modeloTbArticulos.getValueAt(i, 9).toString()))*Integer.parseInt( modeloTbArticulos.getValueAt(i, 4).toString());
					totalCostoVenta += acumulador;
				}
				
				delegadoDetalleDevolucionCliente.insertarDetalleDevolucionCliente(detalleDevolucionCliente);
				ultimaDetalleDevolucionCliente=delegadoDetalleDevolucionCliente.traerUltimaDetalleDevolucionCliente();
				
				registrarDetalleDevolucionClienteAlKardex();
			}
		}
		this.dispose();
		VentBuscarVentaParaDevCliente ventBuscarVentaParaDevCliente= new VentBuscarVentaParaDevCliente();
		ventBuscarVentaParaDevCliente.setVisible(true);
	}

	//Metodo para agregar los datos de la venta a la devolucion cliente
	public void agregarDatosADevolucionCliente(int idVenta, String nombre, String identificacion, List<DetalleVenta> listaDetalleVenta, double total, int items, Date fechaVenta){
		txtConsectivo.setText(Integer.toString(idVenta));
		txtNombre.setText(nombre);
		txtIdentificacion.setText(identificacion);
		txtTotal.setText(formatearNumero(total));
		txtItem.setText(Integer.toString(items));
		llenarModeloDetalleVenta(listaDetalleVenta);
		this.fechaVenta = fechaVenta;
	}
	
	//Metodo para llenar la lista de detalles de la venta incluyendo una columna para la cantidad devuelta
	public void llenarModeloDetalleVenta(List<DetalleVenta> listaDetalleVenta){
		
		DelegadoDetalleDevolucionCliente delegadoDetalleDevolucionCliente= new DelegadoDetalleDevolucionCliente();
		String [] fila = new String[modeloTbArticulos.getColumnCount()];
		
		for(DetalleVenta detallesVentas : listaDetalleVenta ){
			fila[0]= Integer.toString(detallesVentas.getArticulo().getCodigo());
			fila[1]= detallesVentas.getArticulo().getLineaArticulos().getNombreL();
			fila[2]= detallesVentas.getArticulo().getNombre();
			fila[3]= detallesVentas.getArticulo().getUnidadMedida().getNombre();
			fila[4]= Integer.toString(detallesVentas.getCantidad());
			try{
				fila[5]= delegadoDetalleDevolucionCliente.traerCantidadDevuelta(detallesVentas.getVentaArticulos().getIdVenta(), detallesVentas.getArticulo().getCodigo()).toString();
			}catch(NullPointerException nl){
				fila[5]="0";
				nl.getMessage();
			}
			
			fila[6]= "0";
			fila[7]= formatearNumero(detallesVentas.getVlrUnitario());
			fila[8]= formatearNumero(detallesVentas.getTotal());
			fila[9]= Integer.toString(detallesVentas.getIdDetalleVenta());
			
			modeloTbArticulos.addRow(fila);
		}
		tbArticulos.setModel(modeloTbArticulos);
		colorearDevolucionEnTabla(tbArticulos);
	}
	
	//Ventana para colorear la columna devolucion en la que se ingresar la cantidad a devolver
	private void colorearDevolucionEnTabla(JTable tablaDevoluciones) {
		tablaDevoluciones.getColumn("Devolución").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
	        comp.setBackground(Color.YELLOW);
	        return comp; 
			}}); 
	}
	
	//Metodo para registrar la devolucion de la venta al kardex electronico
	private void registrarDetalleDevolucionClienteAlKardex() {
		KardexElectronico kardexElectronico = new KardexElectronico();
		Articulo articulo = new Articulo();
		DelegadoControlInventario delegadoControlInventario = new DelegadoControlInventario();
		List<ControlInventario> controlInventario;
		ControlInventario controlInventarioAModificar;
		
		KardexElectronico ventaParaDevVenta = delegadoKardexElectronico.traerVentaEnKardexParaDevVenta(ultimaDetalleDevolucionCliente.get(0).getDevolucionCliente().getVentaArticulos().getIdVenta(), "Venta", ultimaDetalleDevolucionCliente.get(0).getCodigoArticulo()).get(0);
		
		ultimoRegistroPorArticuloKardex1 = delegadoKardexElectronico.traerUltimoRegistroKardexElectronicopPorArticulo(ultimaDetalleDevolucionCliente.get(0).getCodigoArticulo());
		ultimoRegistroPorArticuloKardex = ultimoRegistroPorArticuloKardex1.get(0);
		
		kardexElectronico.setFecha(ultimaDetalleDevolucionCliente.get(0).getDevolucionCliente().getFecha());
		kardexElectronico.setMovimiento(ultimaDetalleDevolucionCliente.get(0).getDevolucionCliente().getConcepto());
		kardexElectronico.setNroDocumento(ultimaDetalleDevolucionCliente.get(0).getDevolucionCliente().getIdDevolucionCliente());
		articulo.setCodigo(ultimaDetalleDevolucionCliente.get(0).getCodigoArticulo());
		kardexElectronico.setArticulo(articulo);
		kardexElectronico.setCantidadEntrada(ultimaDetalleDevolucionCliente.get(0).getCantidadDevuelta());
		kardexElectronico.setCostoUEntrada(ultimaDetalleDevolucionCliente.get(0).getVlrUnitario());
		kardexElectronico.setCostoTEntrada((ultimaDetalleDevolucionCliente.get(0).getVlrUnitario())*ultimaDetalleDevolucionCliente.get(0).getCantidadDevuelta());
		kardexElectronico.setCantidadSalida(0);
		kardexElectronico.setCostoUSalida(0);
		kardexElectronico.setCostoTSalida(0);
		kardexElectronico.setCantidadSaldo(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimaDetalleDevolucionCliente.get(0).getCantidadDevuelta());
		kardexElectronico.setCostoUSaldo((ultimoRegistroPorArticuloKardex.getCostoTSaldo() + (ventaParaDevVenta.getCostoUSaldo())*ultimaDetalleDevolucionCliente.get(0).getCantidadDevuelta())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimaDetalleDevolucionCliente.get(0).getCantidadDevuelta()));
		kardexElectronico.setCostoTSaldo((ultimoRegistroPorArticuloKardex.getCostoTSaldo() + (ventaParaDevVenta.getCostoUSaldo())*ultimaDetalleDevolucionCliente.get(0).getCantidadDevuelta())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimaDetalleDevolucionCliente.get(0).getCantidadDevuelta())*(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimaDetalleDevolucionCliente.get(0).getCantidadDevuelta()));
		kardexElectronico.setProveedorUltimaCompra("");
		
		delegadoKardexElectronico.insertarKardexElectronico(kardexElectronico);
		
		controlInventario = delegadoControlInventario.traerContInventarioPorCodigoArticulo(ultimaDetalleDevolucionCliente.get(0).getCodigoArticulo());
		controlInventarioAModificar = controlInventario.get(0);
		
		controlInventarioAModificar.setCostoPromedio((ultimoRegistroPorArticuloKardex.getCostoTSaldo() + (ventaParaDevVenta.getCostoUSaldo())*ultimaDetalleDevolucionCliente.get(0).getCantidadDevuelta())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimaDetalleDevolucionCliente.get(0).getCantidadDevuelta()));
		controlInventarioAModificar.setCantExistencia(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimaDetalleDevolucionCliente.get(0).getCantidadDevuelta());
		controlInventarioAModificar.setTotalCostoInventario((ultimoRegistroPorArticuloKardex.getCostoTSaldo() + (ventaParaDevVenta.getCostoUSaldo())*ultimaDetalleDevolucionCliente.get(0).getCantidadDevuelta())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimaDetalleDevolucionCliente.get(0).getCantidadDevuelta())*(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimaDetalleDevolucionCliente.get(0).getCantidadDevuelta()));
		
		delegadoControlInventario.actualizarControlInventario(controlInventarioAModificar);
	}
	
	//Metodo para contabilizar la devolucion a cliente
	private void contabilizarDevolucionCliente() {
		DelegadoContabilizacion delegadoContabilizacion = new DelegadoContabilizacion();                                                                                   
		Contabilizacion contabilizarAVenta = new Contabilizacion();                                                                 
		Contabilizacion contabilizarACliente = new Contabilizacion();
		Contabilizacion contabilizarACostoVenta = new Contabilizacion();
		Contabilizacion contabilizarAInventario = new Contabilizacion();
		
		contabilizarAVenta.setCodigoCuenta(4175);
		contabilizarAVenta.setFechaGeneracion(new Date());              
		contabilizarAVenta.setNombreCuenta("Devoluciones en Ventas");
		contabilizarAVenta.setSaldoDebito(ultimaDevolucionCliente.getTotalDevolucion());
		contabilizarAVenta.setSaldoCredito(0);
		
		contabilizarACliente.setCodigoCuenta(1305);
		contabilizarACliente.setFechaGeneracion(new Date());              
		contabilizarACliente.setNombreCuenta("Clientes");
		contabilizarACliente.setSaldoDebito(0);
		contabilizarACliente.setSaldoCredito(ultimaDevolucionCliente.getTotalDevolucion());
		
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
	
	//Metodo para obtener el costo del articulo
	private double obtenerCostoArticulo(int idDetalleVenta){
		DelegadoDetalleVenta delegadoDetalleVenta = new DelegadoDetalleVenta();
		DetalleVenta detalleVenta = delegadoDetalleVenta.traerDetallePorCodigoDetalle(idDetalleVenta).get(0);
		return detalleVenta.getCostoVentaUnitario();
	}
}
