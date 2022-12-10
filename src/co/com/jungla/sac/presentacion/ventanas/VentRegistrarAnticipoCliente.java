package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import co.com.jungla.sac.persistencia.entidades.AnticipoCliente;
import co.com.jungla.sac.persistencia.entidades.Banco;
import co.com.jungla.sac.persistencia.entidades.Contabilizacion;
import co.com.jungla.sac.persistencia.entidades.MedioPagoCliente;

import co.com.jungla.sac.negocio.delegados.DelegadoAnticipoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoBanco;
import co.com.jungla.sac.negocio.delegados.DelegadoContabilizacion;
import co.com.jungla.sac.negocio.delegados.DelegadoMedioPagoCliente;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.JTextPane;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro del anticipo a los clientes cuando se va efectuar un recaudo de dinero y su contabilizacion
 * @author Luis Fernando Pedroza T.
 * @version: 21/09/2016
 */
public class VentRegistrarAnticipoCliente extends JDialog {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtDptoCiudad;
	private JTextField txtIdentificacion;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JFormattedTextField txtEfectivo;
	private JFormattedTextField txtTarjetaCredito;
	private JFormattedTextField txtTarjetaDebito;
	private JFormattedTextField txtCheques;
	private JFormattedTextField txtConsignaciones;
	private JFormattedTextField txtTotalRecibido;
	private JTextPane txpObservaciones;
	private JComboBox cbMedioPago;
	private JComboBox cbBancoDestino;
	private JComboBox cbFranquicia;
	private JTable tbMediosPago;
	private JDateChooser dchFechaRecaudo;
	private DefaultComboBoxModel modeloBancos = new DefaultComboBoxModel();
	private DefaultTableModel modeloTbMediosPagos = new DefaultTableModel();

	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private int fila;
	private AnticipoCliente ultimoAnticipoCliente;
	private String identificacion;
	
	/**
	 * Metodo constructor con parametros necesarios para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentRegistrarAnticipoCliente(String identificacion, String nombre, String dptoCiudad, String direccion, String telefono) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarAnticipoCliente.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Registrar Anticipo a Clientes");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 994, 436);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(13, 11, 960, 61);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JPanel pnDptoCiudad = new JPanel();
		pnDptoCiudad.setLayout(null);
		pnDptoCiudad.setBackground(new Color(0, 51, 0));
		pnDptoCiudad.setBounds(396, 11, 201, 38);
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
		pnCliente.setBounds(10, 11, 376, 38);
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
		
		JPanel pnDireccion = new JPanel();
		pnDireccion.setLayout(null);
		pnDireccion.setBackground(new Color(0, 51, 0));
		pnDireccion.setBounds(607, 11, 194, 38);
		pn1.add(pnDireccion);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setForeground(Color.WHITE);
		lblDireccin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDireccin.setBackground(Color.BLACK);
		lblDireccin.setBounds(69, 0, 112, 14);
		pnDireccion.add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(0, 18, 194, 20);
		pnDireccion.add(txtDireccion);
		
		JPanel pnTelefono = new JPanel();
		pnTelefono.setLayout(null);
		pnTelefono.setBackground(new Color(0, 51, 0));
		pnTelefono.setBounds(811, 11, 136, 38);
		pn1.add(pnTelefono);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelefono.setBackground(Color.BLACK);
		lblTelefono.setBounds(41, 0, 84, 14);
		pnTelefono.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(0, 18, 136, 20);
		pnTelefono.add(txtTelefono);
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(13, 83, 960, 111);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JTextPane txpInstrucciones = new JTextPane();
		txpInstrucciones.setContentType("text/html");
		txpInstrucciones.setEditable(false);
		txpInstrucciones.setBackground(UIManager.getColor("Button.background"));
		txpInstrucciones.setText("<b>INSTRUCCIONES.</b><FONT FACE=\"Tahoma\" SIZE= 3><p align=\"justify\">\r\nIngrese toda la informaci\u00F3n del Anticipo Cliente como si fuera un recaudo normal. Cuando se haya generado en el sistema el documento con el cual se desea cruzar este anticipo debe ingresar a la CAJA y con el bot\u00F3n [+] agregue en los MEDIOS de PAGO la opci\u00F3n [Anticipo]. El sistema le traer\u00E1 todos los anticipos NO utilizados para ese cliente y usted puede seleccionar el que corresponda. ");
		txpInstrucciones.setBounds(10, 11, 940, 96);
		pn2.add(txpInstrucciones);
      		
		JPanel pn4 = new JPanel();
		pn4.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn4.setBounds(13, 347, 554, 47);
		contentPane.add(pn4);
		pn4.setLayout(null);
		
		JButton btnAtras = new JButton("Ir Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAtras.setForeground(new Color(0, 51, 0));
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAtras.setBackground(UIManager.getColor("Button.background"));
		btnAtras.setBounds(10, 11, 105, 23);
		pn4.add(btnAtras);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
		});
		btnRegistrar.setForeground(new Color(0, 51, 0));
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegistrar.setBackground(UIManager.getColor("Button.background"));
		btnRegistrar.setBounds(125, 11, 134, 23);
		pn4.add(btnRegistrar);
		
		JLabel lblFechaRecaudo = new JLabel("Fecha de Recaudo");
		lblFechaRecaudo.setOpaque(true);
		lblFechaRecaudo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaRecaudo.setBackground(new Color(153, 204, 153));
		lblFechaRecaudo.setBounds(269, 12, 126, 22);
		pn4.add(lblFechaRecaudo);
		
		dchFechaRecaudo = new JDateChooser();
		dchFechaRecaudo.setBounds(397, 13, 134, 20);
		validarFechaRecaudo();
		pn4.add(dchFechaRecaudo);
		
		JPanel pn5 = new JPanel();
		pn5.setLayout(null);
		pn5.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		pn5.setBackground(Color.GRAY);
		pn5.setBounds(577, 205, 224, 189);
		contentPane.add(pn5);
		
		JLabel lblEfectivo = new JLabel("Efectivo");
		lblEfectivo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEfectivo.setBounds(10, 24, 46, 14);
		pn5.add(lblEfectivo);
		
		txtEfectivo = new JFormattedTextField();
		txtEfectivo.setBackground(new Color(255, 255, 51));
		txtEfectivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularTotalRecibido();
			}
		});
		formatearAMoneda(txtEfectivo);
		txtEfectivo.setColumns(10);
		txtEfectivo.setBounds(114, 21, 100, 20);
		pn5.add(txtEfectivo);
		
		txtTarjetaCredito = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtTarjetaCredito.setEditable(false);
		txtTarjetaCredito.setColumns(10);
		txtTarjetaCredito.setValue(0);
		txtTarjetaCredito.setBounds(114, 42, 100, 20);
		pn5.add(txtTarjetaCredito);
		
		JLabel lblTarjetaCredito = new JLabel("Tarjeta Cr\u00E9dito");
		lblTarjetaCredito.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTarjetaCredito.setBounds(10, 45, 100, 14);
		pn5.add(lblTarjetaCredito);
		
		JLabel lblTarjetaDebito = new JLabel("Tarjeta D\u00E9dito");
		lblTarjetaDebito.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTarjetaDebito.setBounds(10, 66, 100, 14);
		pn5.add(lblTarjetaDebito);
		
		txtTarjetaDebito = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtTarjetaDebito.setEditable(false);
		txtTarjetaDebito.setColumns(10);
		txtTarjetaDebito.setValue(0);
		txtTarjetaDebito.setBounds(114, 63, 100, 20);
		pn5.add(txtTarjetaDebito);
		
		JLabel lblCheques = new JLabel("Cheques");
		lblCheques.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCheques.setBounds(10, 87, 62, 14);
		pn5.add(lblCheques);
		
		txtCheques = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtCheques.setEditable(false);
		txtCheques.setColumns(10);
		txtCheques.setValue(0);
		txtCheques.setBounds(114, 84, 100, 20);
		pn5.add(txtCheques);
		
		JLabel lblNotaCredito = new JLabel("Consignaciones");
		lblNotaCredito.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNotaCredito.setBounds(10, 108, 93, 14);
		pn5.add(lblNotaCredito);
		
		txtConsignaciones = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtConsignaciones.setEditable(false);
		txtConsignaciones.setColumns(10);
		txtConsignaciones.setValue(0);
		txtConsignaciones.setBounds(114, 105, 100, 20);
		pn5.add(txtConsignaciones);
		
		JLabel lblTotalRecibido = new JLabel("Total Recibido");
		lblTotalRecibido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalRecibido.setBounds(10, 151, 81, 14);
		pn5.add(lblTotalRecibido);
		
		txtTotalRecibido = new JFormattedTextField(NumberFormat.getCurrencyInstance(new Locale("es","CO")));
		txtTotalRecibido.setEditable(false);
		txtTotalRecibido.setColumns(10);
		txtTotalRecibido.setValue(0);
		txtTotalRecibido.setBackground(new Color(51, 255, 102));
		txtTotalRecibido.setBounds(114, 148, 100, 20);
		pn5.add(txtTotalRecibido);
		
		JSeparator sp2 = new JSeparator();
		sp2.setBounds(10, 139, 204, 2);
		pn5.add(sp2);
		
		JButton btnAgregar = new JButton("+");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarMedioPago();
			}
		});
		btnAgregar.setBounds(237, 313, 43, 23);
		contentPane.add(btnAgregar);
		
		JButton btnQuitar = new JButton("-");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarMedioPago();
			}
		});
		btnQuitar.setBounds(290, 313, 43, 23);
		contentPane.add(btnQuitar);
		
		JPanel pn3 = new JPanel();
		pn3.setLayout(null);
		pn3.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn3.setBounds(13, 205, 554, 97);
		contentPane.add(pn3);
		
		JScrollPane scrMediosPago = new JScrollPane();
		scrMediosPago.setBounds(2, 2, 550, 93);
		pn3.add(scrMediosPago);
		
		tbMediosPago = new JTable();
		scrMediosPago.setViewportView(tbMediosPago);
		
		cbMedioPago = new JComboBox();
		cbBancoDestino = new JComboBox();
		cbFranquicia = new JComboBox();
		
		txtTelefono.setText(telefono);
		txtIdentificacion.setText(identificacion);
		this.identificacion = identificacion;
		txtNombre.setText(nombre);
		txtDptoCiudad.setText(dptoCiudad);
		txtDireccion.setText(direccion);
		
		JScrollPane scrObservaciones = new JScrollPane();
		scrObservaciones.setBounds(811, 221, 162, 173);
		contentPane.add(scrObservaciones);
		
		txpObservaciones = new JTextPane();
		scrObservaciones.setViewportView(txpObservaciones);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaciones.setBounds(811, 205, 139, 14);
		contentPane.add(lblObservaciones);
		
		setModal(true);
		
		//Metodos que debe ejecutar la ventana al inicializarse
		llenarColumnasTbMediosPagos();
		incluirComboBoxes();
	}
	
	//Metodo para validar la fecha de recaudo
	private void validarFechaRecaudo() {
		dchFechaRecaudo.setDate(new Date());
		dchFechaRecaudo.setSelectableDateRange(new Date(), null);
	}
	
	//Metodo para agregar medio de pago a la tabla
	private void agregarMedioPago() {
		
		modeloTbMediosPagos.addRow(new Object[]{"","","","","",""});
		tbMediosPago.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent evento) {
                actualizarTbMedioPago(evento);
            }
        });
	}
	
	//Metodo para quitar medio de pago a la tabla
	private void quitarMedioPago() {
		
		try{
			modeloTbMediosPagos.removeRow(modeloTbMediosPagos.getRowCount()-1);
		}catch(Exception e){
			e.getMessage();
		}
		
		calcularTotalCheques();
		calcularTotalConsignaciones();
		calcularTotalTarjetaCredito();
		calcularTotalTarjetaDebito();
		calcularTotalRecibido();
		
	}
	
	//Metodo para actualizar la tabla medios de pago cada vez que se modifica una celda de dicha tabla
	private void actualizarTbMedioPago(TableModelEvent evento) {
		if (evento.getType() == TableModelEvent.UPDATE) {

            // Se obtiene el modelo de la tabla y la fila/columna que han cambiado.
            TableModel modelo = ((TableModel) (evento.getSource()));
            fila = evento.getFirstRow();
            int columna = evento.getColumn();
           // Se aplica los calculos solamente a la columnas 3 (costo) y 4 (total)
            if (columna ==1 ) {
	            try{
	            	if(modelo.getValueAt(fila, 0).toString() == "Tarjeta Credito"){
	            		
	            		calcularTotalTarjetaCredito();
	            		calcularTotalRecibido();
	   
	            	 }else{
	            		 if(modelo.getValueAt(fila, 0).toString() == "Tarjeta Debito"){
	     
	            			 calcularTotalTarjetaDebito();
	 	            		 calcularTotalRecibido();
	            		 }else{
	            			 if(modelo.getValueAt(fila, 0).toString() == "Cheque"){
	            				 
	            				 calcularTotalCheques();
		 	            		 calcularTotalRecibido();

	            			 }else{
	            				 
	            				 calcularTotalConsignaciones();
		 	            		 calcularTotalRecibido();
	            				 
	            			 }
	            		 }
	            	 }
	            	 
	            }catch(Exception ex){
	            	ex.getMessage();
	            }
            }
        }
		
	}
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbMediosPagos() {
		modeloTbMediosPagos.addColumn("Medio Pago");
		modeloTbMediosPagos.addColumn("Valor");
		modeloTbMediosPagos.addColumn("Comprobante");
		modeloTbMediosPagos.addColumn("Banco Destino");
		modeloTbMediosPagos.addColumn("Franquicia");
		
		tbMediosPago.setModel(modeloTbMediosPagos);
		
	}
	//Metodo para incluir los combo box medio de pago, banco y franquicia a alguna celdas de la tabla		
	private void incluirComboBoxes(){
		cbBancoDestino.removeAllItems();
		DelegadoBanco delegadoBanco = new DelegadoBanco();
		List<Banco> bancos = delegadoBanco.listarBanco();
		TableColumn medioPago = tbMediosPago.getColumnModel().getColumn(0);
		TableColumn bancoDestino = tbMediosPago.getColumnModel().getColumn(3);
		TableColumn franquicia = tbMediosPago.getColumnModel().getColumn(4);
		
		cbMedioPago.setModel(new DefaultComboBoxModel(new String[] {"Tarjeta Credito", "Tarjeta Debito", "Cheque", "Consignación"}));
		for(Banco bancosAElegir : bancos){
			modeloBancos.addElement(new Banco (bancosAElegir.getEntidad(), bancosAElegir.getIdBanco()));
			cbBancoDestino.setModel(modeloBancos);
		}
		cbFranquicia.setModel(new DefaultComboBoxModel(new String[] {"Maestro", "Visa", "Mastercard", "American Express", "Diners Club","Discover", "Visa Electron", "Otra"}));
		medioPago.setCellEditor(new DefaultCellEditor(cbMedioPago));
		franquicia.setCellEditor(new DefaultCellEditor(cbFranquicia));
		bancoDestino.setCellEditor(new DefaultCellEditor(cbBancoDestino));
		
	}
	
	//Validar los datos ingresados para el registro del anticipo
	private void validarDatos(){
		try{
			if(dchFechaRecaudo.getDate()==null){
				JOptionPane.showMessageDialog(null, "Debe ingresar la fecha de Recaudo");
			}else{
				if("".equals(tbMediosPago.getValueAt(fila, 0).toString())){
					JOptionPane.showMessageDialog(null, "Debe elegir un medio de pago o sino presione el boton (-) para quitarlo");
				}else{
					if("".equals(tbMediosPago.getValueAt(fila, 1).toString())){
						JOptionPane.showMessageDialog(null, "Para todos los medios de pago debe ingresar el valor del medio elegido");
					}else{
						if(("Tarjeta Credito".equals(tbMediosPago.getValueAt(fila, 0).toString())||"Tarjeta Debito".equals(tbMediosPago.getValueAt(fila, 0).toString())||"Consignación".equals(tbMediosPago.getValueAt(fila, 0).toString()))&&"".equals(tbMediosPago.getValueAt(fila, 3).toString())){
							JOptionPane.showMessageDialog(null, "Para las tarjetas y consignaciones debe elegir la entidad correspondiente al medio de pago elegido");
						}else{
							if(("Tarjeta Credito".equals(tbMediosPago.getValueAt(fila, 0).toString())||"Tarjeta Debito".equals(tbMediosPago.getValueAt(fila, 0).toString()))&&"".equals(tbMediosPago.getValueAt(fila, 4).toString())){
								JOptionPane.showMessageDialog(null, "Unicamente para las tarjetas debe elegir la franquicia correspondiente al medio de pago elegido");
							}else{
								if("Cheque".equals(tbMediosPago.getValueAt(fila, 0).toString()) && (""!=tbMediosPago.getValueAt(fila, 3).toString()|| ""!=tbMediosPago.getValueAt(fila, 4).toString())){
									JOptionPane.showMessageDialog(null, "En el medio de pago \"Cheque\" no se debe elegir la entidad ni la franquicia");
									tbMediosPago.setValueAt("", fila, 3);
									tbMediosPago.setValueAt("", fila, 4);
								}else{
									if("Consignación".equals(tbMediosPago.getValueAt(fila, 0).toString()) && ""!=tbMediosPago.getValueAt(fila, 4).toString()){
										JOptionPane.showMessageDialog(null, "En el medio de pago \"Consignación\" no se debe elegir la franquicia");
										tbMediosPago.setValueAt("", fila, 4);
									}else{
										abrirDialogoConfirmacionRegistro();
									}
								}
								
							}
						}
					}
				}
			}
					
				
			
		}catch(Exception e){
			e.getMessage();
			abrirDialogoConfirmacionRegistro();
		}

	}
	
	//Metodo para abrir ventanta de confirmacion de registro	
	private void abrirDialogoConfirmacionRegistro() {
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de grabar este anticipo?. Despues no podra realizar ningún cambio sobre esta información", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarAnticipoCliente();
			contabilizarAnticipoCliente();
		}else{
		
		}
	}
	
	//Metodo para abrir el dialogo de registro satisfactorio del anticipo
	private void abrirDialogoAnticipoRegistrado() {
		int res = JOptionPane.showOptionDialog(null, "Se ha registrado este ANTICIPO satisfactoriamente con consecutivo N° "+ultimoAnticipoCliente.getIdAnticipoCliente(), "Anticipo Cliente Registrado", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Ir a Caja", "Imprimir"}, "Ir a Caja");
		if(res==0){
			dispose();
		}else{
			imprimirAnticipo();
		}
	}
		
	private void imprimirAnticipo() {
		JOptionPane.showMessageDialog(null, "Imprimiendo");
	}

	//Metodo para registrar el anticipo cliente
	private void registrarAnticipoCliente(){
		DelegadoAnticipoCliente delegadoAnticipoCliente = new DelegadoAnticipoCliente();
		AnticipoCliente anticipoCliente = new AnticipoCliente();
		
		anticipoCliente.setIdentificacionCliente(identificacion);
		anticipoCliente.setEstadoActividad("Activo");
		anticipoCliente.setEstadoAnticipo("Libre");
		anticipoCliente.setFechaAnticipo(dchFechaRecaudo.getDate());
		anticipoCliente.setObservaciones(txpObservaciones.getText());
		anticipoCliente.setReciboCaja(0);
		anticipoCliente.setValorAnticipo(Double.parseDouble(txtTotalRecibido.getValue().toString()));
		
		delegadoAnticipoCliente.insertarAnticipoCliente(anticipoCliente);
		ultimoAnticipoCliente = delegadoAnticipoCliente.traerUltimoAnticipoCliente().get(0);
		
		registarMediosPago();
		abrirDialogoAnticipoRegistrado();
	}
	
	//Metodo para registrar los medios de pago correspondientes al anticipo
	private void registarMediosPago() {
		
		MedioPagoCliente medioPago = new MedioPagoCliente();
		DelegadoMedioPagoCliente delegadoMedioPago = new DelegadoMedioPagoCliente();
		
		Banco banco = new Banco();
		AnticipoCliente anticipoCliente = new AnticipoCliente();
		
		for(int i=0; i< modeloTbMediosPagos.getRowCount();i++){
			
			anticipoCliente.setIdAnticipoCliente(ultimoAnticipoCliente.getIdAnticipoCliente());
			medioPago.setAnticipoCliente(anticipoCliente);
			medioPago.setNombreMedioPago(modeloTbMediosPagos.getValueAt(i, 0).toString());
			medioPago.setValor(Double.parseDouble(quitarComasANumero(modeloTbMediosPagos.getValueAt(i, 1).toString())));
			if("".equals(modeloTbMediosPagos.getValueAt(i, 2).toString())){
				medioPago.setDocReferencia(0);
			}else{
				medioPago.setDocReferencia(Integer.parseInt(modeloTbMediosPagos.getValueAt(i, 2).toString()));
			}
			if(modeloTbMediosPagos.getValueAt(i, 3)==""){
				medioPago.setBanco(null);
			}else{
				Banco bancoCodigo = (Banco) modeloTbMediosPagos.getValueAt(i, 3);
				banco.setIdBanco(bancoCodigo.getIdBanco());
				medioPago.setBanco(banco);
			}
			
			medioPago.setFranquicia(modeloTbMediosPagos.getValueAt(i, 4).toString());
			
			delegadoMedioPago.insertarMedioPago(medioPago);
		}
		
		anticipoCliente.setIdAnticipoCliente(ultimoAnticipoCliente.getIdAnticipoCliente());
		medioPago.setAnticipoCliente(anticipoCliente);
		medioPago.setBanco(null);
		medioPago.setNombreMedioPago("Efectivo");
		medioPago.setValor(Double.parseDouble(txtEfectivo.getValue().toString()));
		medioPago.setDocReferencia(0);
		medioPago.setFranquicia("");
		
		delegadoMedioPago.insertarMedioPago(medioPago);
	}
	
	//Metodo para calcular el total recibido
	private void calcularTotalRecibido(){
		double totalRecibido = Double.parseDouble(txtEfectivo.getValue().toString()) + Double.parseDouble(txtTarjetaCredito.getValue().toString()) + Double.parseDouble(txtTarjetaDebito.getValue().toString()) + Double.parseDouble(txtCheques.getValue().toString()) + Double.parseDouble(txtConsignaciones.getValue().toString());
		txtTotalRecibido.setValue(totalRecibido);
	}
	
	//Metodo para calcular el total de tarjetas de credito agregados en la tabla
	private void calcularTotalTarjetaCredito(){
		double tarjetaCreditos = 0;
		double acumulador = 0;
		
		for(int i=0; i<tbMediosPago.getRowCount(); i++) {
			if("Tarjeta Credito".equals(tbMediosPago.getValueAt(i,0).toString())){
				acumulador= Double.parseDouble(quitarComasANumero(tbMediosPago.getValueAt(i,1).toString()));
				tarjetaCreditos += acumulador;
			}
			txtTarjetaCredito.setValue(tarjetaCreditos);
		}
	}
	
	//Metodo para calcular el total de consignaciones agregados en la tabla
	private void calcularTotalConsignaciones() {
		double consignaciones = 0;
		double acumulador = 0;
		
		for(int i=0; i<tbMediosPago.getRowCount(); i++) {
			if("Consignación".equals(tbMediosPago.getValueAt(i,0).toString())){
				acumulador= Double.parseDouble(quitarComasANumero(tbMediosPago.getValueAt(i,1).toString()));
				consignaciones += acumulador;
			}
			txtConsignaciones.setValue(consignaciones);
		}
	}
	
	//Metodo para calcular el total de cheques agregados en la tabla
	private void calcularTotalCheques() {
		double cheques = 0;
		double acumulador = 0;
		
		for(int i=0; i<tbMediosPago.getRowCount(); i++) {
			if("Cheque".equals(tbMediosPago.getValueAt(i,0).toString())){
				acumulador= Double.parseDouble(quitarComasANumero(tbMediosPago.getValueAt(i,1).toString()));
				cheques += acumulador;
			}
			txtCheques.setValue(cheques);
		}
	}
		
	//Metodo para calcular el total de tarjetas de debito agregados en la tabla
	private void calcularTotalTarjetaDebito() {
		double tarjetaDebitos = 0;
		double acumulador = 0;
		
		for(int i=0; i<tbMediosPago.getRowCount(); i++) {
			if("Tarjeta Debito".equals(tbMediosPago.getValueAt(i,0).toString())){
				acumulador= Double.parseDouble(quitarComasANumero(tbMediosPago.getValueAt(i,1).toString()));
				tarjetaDebitos += acumulador;
			}
			txtTarjetaDebito.setValue(tarjetaDebitos);
		}
		
	}
	
	//Metodo para formatear a moneda peso permitiendo la edicion y visualizacion
	private void formatearAMoneda(JFormattedTextField campoTexto) {
		
		campoTexto.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
		campoTexto.setValue(0);
		NumberFormat visNf = NumberFormat.getCurrencyInstance(); 
		NumberFormat ediNf = NumberFormat.getNumberInstance(new Locale("es","CO"));
		ediNf.setGroupingUsed(false);
		NumberFormatter visNt = new NumberFormatter(visNf);
		NumberFormatter ediNt = new NumberFormatter(ediNf);
		DefaultFormatterFactory currFactory = new DefaultFormatterFactory(visNt, visNt, ediNt);
		ediNt.setAllowsInvalid(true);
		
		campoTexto.setFormatterFactory(currFactory);
	}
	
	//Metodo para quitar las comas a un numero y reemplazarlos por punto
	private String quitarComasANumero(String numero){
		String numeroReemplazado=numero.replace(",", ".");
		return numeroReemplazado;
	}
	
	//Metodo para contabilizar la anticipo a cliente
	private void contabilizarAnticipoCliente() {
		DelegadoContabilizacion delegadoContabilizacion = new DelegadoContabilizacion();                                                                                   
		Contabilizacion contabilizarAAnticipo = new Contabilizacion();                                                                 
		Contabilizacion contabilizarABanco = new Contabilizacion();
		Contabilizacion contabilizarACaja = new Contabilizacion();
		
		contabilizarAAnticipo.setCodigoCuenta(2805);
		contabilizarAAnticipo.setFechaGeneracion(new Date());              
		contabilizarAAnticipo.setNombreCuenta("Anticipo a Clientes");
		contabilizarAAnticipo.setSaldoDebito(0);
		contabilizarAAnticipo.setSaldoCredito(ultimoAnticipoCliente.getValorAnticipo());
		
		delegadoContabilizacion.insertarContabilizacion(contabilizarAAnticipo);
		
		if(Double.parseDouble(txtEfectivo.getValue().toString())!=0){
			contabilizarACaja.setCodigoCuenta(1105);
			contabilizarACaja.setFechaGeneracion(new Date());              
			contabilizarACaja.setNombreCuenta("Caja");
			contabilizarACaja.setSaldoDebito(Double.parseDouble(txtEfectivo.getValue().toString()));
			contabilizarACaja.setSaldoCredito(0);
			
			delegadoContabilizacion.insertarContabilizacion(contabilizarACaja);
		}else{
			contabilizarABanco.setCodigoCuenta(1110);
			contabilizarABanco.setFechaGeneracion(new Date());              
			contabilizarABanco.setNombreCuenta("Bancos");
			contabilizarABanco.setSaldoDebito(Double.parseDouble(txtTotalRecibido.getValue().toString())-Double.parseDouble(txtEfectivo.getValue().toString()));
			contabilizarABanco.setSaldoCredito(0);
			
			delegadoContabilizacion.insertarContabilizacion(contabilizarABanco);
		}
	}
}
