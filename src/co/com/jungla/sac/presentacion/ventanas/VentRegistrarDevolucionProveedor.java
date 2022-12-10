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
import co.com.jungla.sac.persistencia.entidades.CompraArticulos;
import co.com.jungla.sac.persistencia.entidades.Contabilizacion;
import co.com.jungla.sac.persistencia.entidades.ControlInventario;
import co.com.jungla.sac.persistencia.entidades.DetalleCompra;
import co.com.jungla.sac.persistencia.entidades.DetalleDevolucionProveedor;
import co.com.jungla.sac.persistencia.entidades.DevolucionProveedor;
import co.com.jungla.sac.persistencia.entidades.KardexElectronico;

import co.com.jungla.sac.negocio.delegados.DelegadoContabilizacion;
import co.com.jungla.sac.negocio.delegados.DelegadoControlInventario;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleDevolucionProveedor;
import co.com.jungla.sac.negocio.delegados.DelegadoDevolucionProveedor;
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

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTextPane;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro de la devolucion a los proveedores por una compra efectuada anteriormente y su contabilizacion
 * @author Luis Fernando Pedroza T.
 * @version: 21/09/2016
 */
public class VentRegistrarDevolucionProveedor extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtIdentificacion;
	private JTextField txtTotal;
	private JTextField txtItem;
	private JTextField txtCompra;
	private JFormattedTextField txtDevolucion;
	private JDateChooser dchFecha;
	private JTable tbArticulos;
	private DefaultTableModel modeloTbArticulos = new DefaultTableModel();
	
	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private DevolucionProveedor ultimaDevolucionProveedor;
	private List <DetalleDevolucionProveedor> ultimaDetalleDevolucionProveedor;
	private DevolucionProveedor devolucionProveedor = new DevolucionProveedor();
	private DelegadoKardexElectronico delegadoKardexElectronico = new DelegadoKardexElectronico();
	private List <KardexElectronico> ultimoRegistroPorArticuloKardex1;
	private KardexElectronico ultimoRegistroPorArticuloKardex;
	private JTextPane txpObservaciones;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentRegistrarDevolucionProveedor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarDevolucionProveedor.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Registrar Devolucion a Proveedor");
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
		
		JPanel pnProveedor = new JPanel();
		pnProveedor.setBounds(252, 11, 388, 38);
		pn1.add(pnProveedor);
		pnProveedor.setBackground(new Color(0, 51, 0));
		pnProveedor.setLayout(null);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProveedor.setBackground(SystemColor.desktop);
		lblProveedor.setForeground(SystemColor.window);
		lblProveedor.setBounds(158, 0, 73, 14);
		pnProveedor.add(lblProveedor);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(0, 18, 254, 20);
		pnProveedor.add(txtNombre);
		
		txtIdentificacion = new JTextField();
		txtIdentificacion.setEditable(false);
		txtIdentificacion.setBounds(258, 18, 129, 20);
		pnProveedor.add(txtIdentificacion);
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
		
		JPanel pnCompra = new JPanel();
		pnCompra.setLayout(null);
		pnCompra.setBackground(new Color(0, 51, 0));
		pnCompra.setBounds(10, 11, 77, 38);
		pn1.add(pnCompra);
		
		JLabel lblFactProv = new JLabel("Compra");
		lblFactProv.setForeground(Color.WHITE);
		lblFactProv.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFactProv.setBackground(SystemColor.desktop);
		lblFactProv.setBounds(17, 0, 58, 14);
		pnCompra.add(lblFactProv);
		
		txtCompra = new JTextField();
		txtCompra.setEditable(false);
		txtCompra.setColumns(10);
		txtCompra.setDocument(new LimitadorCaracteres());
		txtCompra.setBounds(0, 18, 77, 20);
		pnCompra.add(txtCompra);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaciones.setBounds(650, 3, 221, 14);
		pn1.add(lblObservaciones);
		
		JScrollPane srcObservaciones = new JScrollPane();
		srcObservaciones.setBounds(650, 20, 239, 34);
		pn1.add(srcObservaciones);
		
		txpObservaciones = new JTextPane();
		srcObservaciones.setViewportView(txpObservaciones);
		
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
      	
		actualizarDevolucionProveedor();
		
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
		txtDevolucion.setBackground(new Color(127, 165, 62));
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
		
		JLabel lblItem = new JLabel("Item");
		lblItem.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblItem.setForeground(Color.WHITE);
		lblItem.setBackground(SystemColor.desktop);
		lblItem.setBounds(16, 0, 49, 14);
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
				VentBuscarCompraParaDevProveedor ventBuscarCompraParaDevProveedor = new VentBuscarCompraParaDevProveedor();
				ventBuscarCompraParaDevProveedor.setVisible(true);
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
		modeloTbArticulos.addColumn("Costo.Unit");
		modeloTbArticulos.addColumn("Total");
		modeloTbArticulos.addColumn("Cod. Detalle");
		
		tbArticulos.setModel(modeloTbArticulos);
	}
	//Metodo para actualizar la devolucion del articulo en la tabla
	private void actualizarDevolucionProveedor() {
		
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
				registrarDevolucionProveedor();
				contabilizarDevolucionProveedor();
			}catch(Exception e){
				e.getMessage();
			}
		}else{
		
		}
	}	

	//Metodo para registrar los datos ingresados de la devolucion
	private void registrarDevolucionProveedor() {
		DelegadoDevolucionProveedor delegadoDevolucionProveedor = new DelegadoDevolucionProveedor();

		CompraArticulos compraArticulos = new CompraArticulos();
			
		devolucionProveedor.setConcepto("Dev-Proveed");
		devolucionProveedor.setEstado("Pendiente");
		devolucionProveedor.setFecha(dchFecha.getDate());
		devolucionProveedor.setTotalDevolucion(Double.parseDouble(txtDevolucion.getValue().toString()));
		compraArticulos.setIdCompra(Integer.parseInt(txtCompra.getText()));
		devolucionProveedor.setCompraArticulos(compraArticulos);
		devolucionProveedor.setObservaciones(txpObservaciones.getText());
		
		delegadoDevolucionProveedor.insertarDevolucionProveedor(devolucionProveedor);
		
		ultimaDevolucionProveedor=delegadoDevolucionProveedor.traerUltimaDevolucionProveedor().get(0);
		
		registrarDetalleDevolucionProveedor();
		
		String nl = System.getProperty("line.separator");
		JOptionPane.showMessageDialog(null, "Se gener\u00F3 una nota debito de manera autom\u00E1tica. Ahora deber\u00E1 elegir\r\nalguna CXP en el sistema para aplicar esta NOTA DEBITO como saldo a su favor. Para ello utilizar el\r\nbot\u00F3n buscar que se encuentra al lado de la casilla de Devoluciones cuando entre a pagar la CXP.\r\n"+nl+"Compra: "+ultimaDevolucionProveedor.getCompraArticulos().getIdCompra()+nl+"Nota Debito: "+ultimaDevolucionProveedor.getIdDevolucionProveedor()+nl+"Total Devuelto: "+formatearNumero(ultimaDevolucionProveedor.getTotalDevolucion()));
		
	}
	//Metodo para registrar el detalle de la devolucion
	private void registrarDetalleDevolucionProveedor() {
		DelegadoDetalleDevolucionProveedor delegadoDetalleDevolucionProveedor = new DelegadoDetalleDevolucionProveedor();
		DetalleDevolucionProveedor detalleDevolucionProveedor = new DetalleDevolucionProveedor();
		
		for(int i=0; i< modeloTbArticulos.getRowCount();i++){
			
			if("0".equals(modeloTbArticulos.getValueAt(i, 6).toString())){
				
			}else{
				devolucionProveedor.setIdDevolucionProveedor(ultimaDevolucionProveedor.getIdDevolucionProveedor());
				detalleDevolucionProveedor.setDevolucionProveedor(devolucionProveedor);
				detalleDevolucionProveedor.setCodigoArticulo(Integer.parseInt(modeloTbArticulos.getValueAt(i, 0).toString()));
				detalleDevolucionProveedor.setCosto(Double.parseDouble(desformatearNumero(modeloTbArticulos.getValueAt(i, 7).toString())));
				detalleDevolucionProveedor.setCantidadDevuelta(Integer.parseInt(modeloTbArticulos.getValueAt(i, 6).toString()));
				
				delegadoDetalleDevolucionProveedor.insertarDetalleDevolucionProveedor(detalleDevolucionProveedor);
				ultimaDetalleDevolucionProveedor=delegadoDetalleDevolucionProveedor.traerUltimaDetalleDevolucionProveedor();
				
				registrarDetalleDevolucionProveedorAlKardex();
			}
		}
		this.dispose();
		VentBuscarCompraParaDevProveedor ventBuscarCompraParaDevProveedor = new VentBuscarCompraParaDevProveedor();
		ventBuscarCompraParaDevProveedor.setVisible(true);
		
	}

	//Metodo para contabilizar la devolucion a proveedor
	private void contabilizarDevolucionProveedor() {
		DelegadoContabilizacion delegadoContabilizacion = new DelegadoContabilizacion();                                                                                   
		Contabilizacion contabilizarACompra = new Contabilizacion();                                                                 
		Contabilizacion contabilizarAProveedor = new Contabilizacion();
		Contabilizacion contabilizarACaja = new Contabilizacion();
		Contabilizacion contabilizarABanco = new Contabilizacion();
		
		contabilizarACompra.setCodigoCuenta(1435);
		contabilizarACompra.setFechaGeneracion(new Date());              
		contabilizarACompra.setNombreCuenta("Inventario de Mercancias");
		contabilizarACompra.setSaldoDebito(0);
		contabilizarACompra.setSaldoCredito(ultimaDevolucionProveedor.getTotalDevolucion());
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarACompra); 
		
		if("Banco".equals(ultimaDevolucionProveedor.getCompraArticulos().getMedioPagoProv().getNombreCuenta())){
			contabilizarABanco.setCodigoCuenta(2205);
			contabilizarABanco.setFechaGeneracion(new Date());              
			contabilizarABanco.setNombreCuenta("Banco");
			contabilizarABanco.setSaldoDebito(ultimaDevolucionProveedor.getTotalDevolucion());
			contabilizarABanco.setSaldoCredito(0);
			
			delegadoContabilizacion.insertarContabilizacion(contabilizarABanco); 
		}else{
			if("Caja".equals(ultimaDevolucionProveedor.getCompraArticulos().getMedioPagoProv().getNombreCuenta())){
				contabilizarACaja.setCodigoCuenta(2205);
				contabilizarACaja.setFechaGeneracion(new Date());              
				contabilizarACaja.setNombreCuenta("Caja");
				contabilizarACaja.setSaldoDebito(ultimaDevolucionProveedor.getTotalDevolucion());
				contabilizarACaja.setSaldoCredito(0);
				
				delegadoContabilizacion.insertarContabilizacion(contabilizarACaja); 
			}else{
				contabilizarAProveedor.setCodigoCuenta(2205);
				contabilizarAProveedor.setFechaGeneracion(new Date());              
				contabilizarAProveedor.setNombreCuenta("Proveedores Nacionales");
				contabilizarAProveedor.setSaldoDebito(ultimaDevolucionProveedor.getTotalDevolucion());
				contabilizarAProveedor.setSaldoCredito(0);
				
				delegadoContabilizacion.insertarContabilizacion(contabilizarAProveedor); 
			}
		}
		
	}

	//Metodo para convertir una fecha de tipo date a una cadena
	public String convertirDateAString(Date strFecha){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(strFecha);
		
		return fecha;
	}

	//Metodo para agregar los datos de la devolucion desde la ventana buscar compra para ejecutar la devolucion
	public void agregarDatosADevolucionProveedor(int idCompra, String nombre, String identificacion, List<DetalleCompra> listaDetalleCompra, double total, int items){
		txtCompra.setText(Integer.toString(idCompra));
		txtNombre.setText(nombre);
		txtIdentificacion.setText(identificacion);
		txtTotal.setText(formatearNumero(total));
		txtItem.setText(Integer.toString(items));
		llenarModeloDetalleCompra(listaDetalleCompra);
	}
	
	//Metodo para llenar la lista de detalles de la compra incluyendo una columna para la cantidad devuelta
	public void llenarModeloDetalleCompra(List<DetalleCompra> listaDetalleCompra){
		
		DelegadoDetalleDevolucionProveedor delegadoDetalleDevolucionProveedor = new DelegadoDetalleDevolucionProveedor();
		String [] fila = new String[modeloTbArticulos.getColumnCount()];
		
		for(DetalleCompra detallesCompras : listaDetalleCompra ){
			fila[0]= Integer.toString(detallesCompras.getArticulo().getCodigo());
			fila[1]= detallesCompras.getArticulo().getLineaArticulos().getNombreL();
			fila[2]= detallesCompras.getArticulo().getNombre();
			fila[3]= detallesCompras.getArticulo().getUnidadMedida().getNombre();
			fila[4]= Integer.toString(detallesCompras.getCantidad());
			try{
				fila[5]= delegadoDetalleDevolucionProveedor.traerCantidadDevuelta(detallesCompras.getCompraArticulos().getIdCompra(), detallesCompras.getArticulo().getCodigo()).toString();
			}catch(NullPointerException nl){
				fila[5]="0";
				nl.getMessage();
			}
			
			fila[6]= "0";
			fila[7]= formatearNumero(detallesCompras.getCosto());
			fila[8]= formatearNumero(detallesCompras.getTotal());
			fila[9]= Integer.toString(detallesCompras.getIdDetalleCompra());
			
			modeloTbArticulos.addRow(fila);
		}
		tbArticulos.setModel(modeloTbArticulos);
		colorearDevolucionEnTabla(tbArticulos);
	}
	
	//Ventana para colorear las celdas de los estados pendiente (rojo) y entregado (verde)
	private void colorearDevolucionEnTabla(JTable tablaDevoluciones) {
		tablaDevoluciones.getColumn("Devolución").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			//boolean valid = String.valueOf(table.getValueAt(row, 6));
	        comp.setBackground(new Color(127, 165, 62));
	        return comp; 
			}}); 
	}
	
	//Metodo para registrar la devolucion de la compra al kardex electronico
	private void registrarDetalleDevolucionProveedorAlKardex() {
		KardexElectronico kardexElectronico = new KardexElectronico();
		Articulo articulo = new Articulo();
		DelegadoControlInventario delegadoControlInventario = new DelegadoControlInventario();
		List<ControlInventario> controlInventario;
		ControlInventario controlInventarioAModificar;
		
		ultimoRegistroPorArticuloKardex1 = delegadoKardexElectronico.traerUltimoRegistroKardexElectronicopPorArticulo(ultimaDetalleDevolucionProveedor.get(0).getCodigoArticulo());
		ultimoRegistroPorArticuloKardex = ultimoRegistroPorArticuloKardex1.get(0);
		
		kardexElectronico.setFecha(ultimaDetalleDevolucionProveedor.get(0).getDevolucionProveedor().getFecha());
		kardexElectronico.setMovimiento(ultimaDetalleDevolucionProveedor.get(0).getDevolucionProveedor().getConcepto());
		kardexElectronico.setNroDocumento(ultimaDetalleDevolucionProveedor.get(0).getDevolucionProveedor().getIdDevolucionProveedor());
		articulo.setCodigo(ultimaDetalleDevolucionProveedor.get(0).getCodigoArticulo());
		kardexElectronico.setArticulo(articulo);
		kardexElectronico.setCantidadEntrada(0);
		kardexElectronico.setCostoUEntrada(0);
		kardexElectronico.setCostoTEntrada(0);
		kardexElectronico.setCantidadSalida(ultimaDetalleDevolucionProveedor.get(0).getCantidadDevuelta());
		kardexElectronico.setCostoUSalida(ultimaDetalleDevolucionProveedor.get(0).getCosto());
		kardexElectronico.setCostoTSalida((ultimaDetalleDevolucionProveedor.get(0).getCosto())*ultimaDetalleDevolucionProveedor.get(0).getCantidadDevuelta());
		kardexElectronico.setCantidadSaldo(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimaDetalleDevolucionProveedor.get(0).getCantidadDevuelta());
		kardexElectronico.setCostoUSaldo((ultimoRegistroPorArticuloKardex.getCostoTSaldo() - (ultimaDetalleDevolucionProveedor.get(0).getCosto())*ultimaDetalleDevolucionProveedor.get(0).getCantidadDevuelta())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimaDetalleDevolucionProveedor.get(0).getCantidadDevuelta()));
		kardexElectronico.setCostoTSaldo((ultimoRegistroPorArticuloKardex.getCostoTSaldo() - (ultimaDetalleDevolucionProveedor.get(0).getCosto())*ultimaDetalleDevolucionProveedor.get(0).getCantidadDevuelta())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimaDetalleDevolucionProveedor.get(0).getCantidadDevuelta())*(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimaDetalleDevolucionProveedor.get(0).getCantidadDevuelta()));
		kardexElectronico.setProveedorUltimaCompra(ultimaDetalleDevolucionProveedor.get(0).getDevolucionProveedor().getCompraArticulos().getProveedores().getNombre());
		
		delegadoKardexElectronico.insertarKardexElectronico(kardexElectronico);
		
		controlInventario = delegadoControlInventario.traerContInventarioPorCodigoArticulo(ultimaDetalleDevolucionProveedor.get(0).getCodigoArticulo());
		controlInventarioAModificar = controlInventario.get(0);
		
		controlInventarioAModificar.setCostoPromedio((ultimoRegistroPorArticuloKardex.getCostoTSaldo() - (ultimaDetalleDevolucionProveedor.get(0).getCosto())*ultimaDetalleDevolucionProveedor.get(0).getCantidadDevuelta())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimaDetalleDevolucionProveedor.get(0).getCantidadDevuelta()));
		controlInventarioAModificar.setCantExistencia(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimaDetalleDevolucionProveedor.get(0).getCantidadDevuelta());
		controlInventarioAModificar.setTotalCostoInventario((ultimoRegistroPorArticuloKardex.getCostoTSaldo() - (ultimaDetalleDevolucionProveedor.get(0).getCosto())*ultimaDetalleDevolucionProveedor.get(0).getCantidadDevuelta())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimaDetalleDevolucionProveedor.get(0).getCantidadDevuelta())*(ultimoRegistroPorArticuloKardex.getCantidadSaldo() - ultimaDetalleDevolucionProveedor.get(0).getCantidadDevuelta()));
		
		delegadoControlInventario.actualizarControlInventario(controlInventarioAModificar);
	}
}
