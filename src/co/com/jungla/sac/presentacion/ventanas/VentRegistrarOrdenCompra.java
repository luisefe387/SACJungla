package co.com.jungla.sac.presentacion.ventanas;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import co.com.jungla.sac.persistencia.entidades.Articulo;
import co.com.jungla.sac.persistencia.entidades.DetalleOrdenCompra;
import co.com.jungla.sac.persistencia.entidades.OrdenCompraArticulos;
import co.com.jungla.sac.persistencia.entidades.Persona;
import co.com.jungla.sac.persistencia.entidades.Proveedor;

import co.com.jungla.sac.negocio.delegados.DelegadoArticulo;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleOrdenCompra;
import co.com.jungla.sac.negocio.delegados.DelegadoOrdenCompraArticulos;
import co.com.jungla.sac.negocio.delegados.DelegadoProveedor;
import com.mxrck.autocompleter.TextAutoCompleter;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

//import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import javax.swing.JTextPane;


public class VentRegistrarOrdenCompra extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtArticulo;
	private JTextField txtLinea;
	private JTextField txtUe;
	private JTextField txtCant;
	private JFormattedTextField txtCosto;
	private JTextField txtSaldo;
	private JTable tbArticulos;
	private JTextField txtTotalOrden;
	private JTextField txtItem;
	private JPanel pnUe;
	private JComboBox cbProveedor;
	DefaultComboBoxModel modeloProveedor = new DefaultComboBoxModel();
	DefaultTableModel modeloTbArticulos = new DefaultTableModel();
	private Double subtotal;
	private JDateChooser dchFechaE;
	private JDateChooser dchFechaP;
	private JTextPane txaObservaciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentRegistrarOrdenCompra frame = new VentRegistrarOrdenCompra();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentRegistrarOrdenCompra() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarOrdenCompra.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Orden de Compra de Articulos");
		setBounds(80, 80, 937, 513);
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
		pnProveedor.setBounds(153, 11, 250, 38);
		pn1.add(pnProveedor);
		pnProveedor.setBackground(new Color(0, 51, 0));
		pnProveedor.setLayout(null);
		
		//Funcionalidad para crear el proveedor al desplegar el combo box
		cbProveedor = new JComboBox();
		cbProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearProveedor();
			}
		});
		cbProveedor.setBounds(0, 16, 250, 22);
		pnProveedor.add(cbProveedor);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProveedor.setBackground(SystemColor.desktop);
		lblProveedor.setForeground(SystemColor.window);
		lblProveedor.setBounds(94, 0, 73, 14);
		pnProveedor.add(lblProveedor);
		
		JPanel pnFechaE = new JPanel();
		pnFechaE.setLayout(null);
		pnFechaE.setBackground(new Color(0, 51, 0));
		pnFechaE.setBounds(413, 11, 164, 38);
		pn1.add(pnFechaE);
		
		JLabel lblFechaE = new JLabel("Fecha Entrega (Esperada)");
		lblFechaE.setForeground(Color.WHITE);
		lblFechaE.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaE.setBackground(Color.BLACK);
		lblFechaE.setBounds(9, 0, 158, 14);
		pnFechaE.add(lblFechaE);
		
		dchFechaE = new JDateChooser();
		dchFechaE.setBounds(0, 18, 164, 20);
		pnFechaE.add(dchFechaE);
		
		JPanel pnFechaP = new JPanel();
		pnFechaP.setLayout(null);
		pnFechaP.setBackground(new Color(0, 51, 0));
		pnFechaP.setBounds(587, 11, 164, 38);
		pn1.add(pnFechaP);
		
		JLabel lblFechaP = new JLabel("Fecha Pago (Pactado)");
		lblFechaP.setForeground(Color.WHITE);
		lblFechaP.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaP.setBackground(Color.BLACK);
		lblFechaP.setBounds(16, 0, 138, 14);
		pnFechaP.add(lblFechaP);
		
		dchFechaP = new JDateChooser();
		dchFechaP.setBounds(0, 18, 164, 20);
		pnFechaP.add(dchFechaP);
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(null);
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 84, 906, 62);
		contentPane.add(pn2);
		
		JPanel pnCodigo = new JPanel();
		pnCodigo.setLayout(null);
		pnCodigo.setBackground(new Color(0, 51, 0));
		pnCodigo.setBounds(10, 11, 77, 38);
		pn2.add(pnCodigo);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCodigo.setForeground(Color.WHITE);
		lblCodigo.setBackground(SystemColor.desktop);
		lblCodigo.setBounds(22, 0, 55, 14);
		pnCodigo.add(lblCodigo);
		
		txtCodigo = new JTextField();
		//Evento de teclado que permite traer la linea a la que pertenece el articulo mediante su codigo
		//al presionar enter o tab
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_TAB ){
					
					try{
						DelegadoArticulo delegadoArticulo =new DelegadoArticulo();
						List<Articulo> consulta=delegadoArticulo.traerLineaUnidadArticuloPorCodigo(Integer.parseInt(txtCodigo.getText()));
						txtArticulo.setText(consulta.get(0).getNombre());
						txtLinea.setText(consulta.get(0).getLineaArticulos().getNombreL());
						txtUe.setText(consulta.get(0).getUnidadMedida().getAbreviatura().toString());
					}catch(Exception ex){
						ex.getMessage();
						JOptionPane.showMessageDialog(null, "El codigo "+txtCodigo.getText()+" no existe");
						limpiarArticulos();
					}
					
				}
			}
		});
		txtCodigo.setBounds(0, 18, 77, 20);
		pnCodigo.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JPanel pnArticulo = new JPanel();
		pnArticulo.setLayout(null);
		pnArticulo.setBackground(new Color(0, 51, 0));
		pnArticulo.setBounds(97, 11, 254, 38);
		pn2.add(pnArticulo);
		
		JLabel lblArticulo = new JLabel("Articulo (Busqueda facil)");
		lblArticulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblArticulo.setForeground(Color.WHITE);
		lblArticulo.setBackground(SystemColor.desktop);
		lblArticulo.setBounds(59, 0, 160, 14);
		pnArticulo.add(lblArticulo);
		
		txtArticulo = new JTextField();
		//Evento de teclado que permite traer la linea a la que pertenece el articulo mediante su nombre
		//al presionar enter o tab
		txtArticulo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_TAB ){
					
					try{
						DelegadoArticulo delegadoArticulo =new DelegadoArticulo();
						List<Articulo> consulta=delegadoArticulo.traerLineaUnidadArticulo(txtArticulo.getText());
						txtCodigo.setText(Integer.toString(consulta.get(0).getCodigo()));
						txtLinea.setText(consulta.get(0).getLineaArticulos().getNombreL());
						txtUe.setText(consulta.get(0).getUnidadMedida().getAbreviatura().toString());
					}catch(Exception ex){
						ex.getMessage();
					}
					
				}
			}
		});
		txtArticulo.setBounds(0, 18, 254, 20);
		pnArticulo.add(txtArticulo);
		txtArticulo.setColumns(10);
		
		JPanel pnLinea = new JPanel();
		pnLinea.setBounds(361, 11, 155, 38);
		pn2.add(pnLinea);
		pnLinea.setLayout(null);
		pnLinea.setBackground(new Color(0, 51, 0));
		
		JLabel lblLinea = new JLabel("Linea");
		lblLinea.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLinea.setForeground(Color.WHITE);
		lblLinea.setBackground(SystemColor.desktop);
		lblLinea.setBounds(59, 0, 52, 14);
		pnLinea.add(lblLinea);
		
		txtLinea = new JTextField();
		txtLinea.setColumns(10);
		txtLinea.setBounds(0, 18, 155, 20);
		txtLinea.setEditable(false);
		pnLinea.add(txtLinea);
		
		pnUe = new JPanel();
		pnUe.setLayout(null);
		pnUe.setBackground(new Color(0, 51, 0));
		pnUe.setBounds(526, 11, 37, 38);
		pn2.add(pnUe);
		
		JLabel lblUe = new JLabel("UE");
		lblUe.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUe.setForeground(Color.WHITE);
		lblUe.setBackground(SystemColor.desktop);
		lblUe.setBounds(10, 0, 27, 14);
		pnUe.add(lblUe);
		
		txtUe = new JTextField();
		txtUe.setColumns(10);
		txtUe.setBounds(0, 18, 37, 20);
		txtUe.setEditable(false);
		pnUe.add(txtUe);
		
		JPanel pnCant = new JPanel();
		pnCant.setLayout(null);
		pnCant.setBackground(new Color(0, 51, 0));
		pnCant.setBounds(573, 11, 53, 38);
		pn2.add(pnCant);
		
		JLabel lblCant = new JLabel("Cant");
		lblCant.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCant.setForeground(Color.WHITE);
		lblCant.setBackground(SystemColor.desktop);
		lblCant.setBounds(10, 0, 43, 14);
		pnCant.add(lblCant);
		
		txtCant = new JTextField();
		txtCant.setColumns(10);
		txtCant.setBounds(0, 18, 53, 20);
		txtCant.setText("1");
		pnCant.add(txtCant);
		
		JPanel pnCostoTotal = new JPanel();
		pnCostoTotal.setLayout(null);
		pnCostoTotal.setBackground(new Color(0, 51, 0));
		pnCostoTotal.setBounds(636, 11, 110, 38);
		pn2.add(pnCostoTotal);
		
		JLabel lblCostoTotal = new JLabel("Costo");
		lblCostoTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCostoTotal.setForeground(Color.WHITE);
		lblCostoTotal.setBackground(SystemColor.desktop);
		lblCostoTotal.setBounds(40, 0, 60, 14);
		pnCostoTotal.add(lblCostoTotal);
		
		txtCosto = new JFormattedTextField();
		txtCosto.setValue(0);
		formatearAMoneda(txtCosto);
		//txtCosto.setColumns(0);
		txtCosto.setBounds(0, 18, 110, 20);
		pnCostoTotal.add(txtCosto);
		
		JPanel pnSaldo = new JPanel();
		pnSaldo.setLayout(null);
		pnSaldo.setBackground(new Color(0, 51, 0));
		pnSaldo.setBounds(756, 11, 79, 38);
		pn2.add(pnSaldo);
		
		JLabel lblSaldo = new JLabel("Saldo");
		lblSaldo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSaldo.setForeground(Color.WHITE);
		lblSaldo.setBackground(SystemColor.desktop);
		lblSaldo.setBounds(24, 0, 60, 14);
		pnSaldo.add(lblSaldo);
		
		txtSaldo = new JTextField();
		txtSaldo.setEditable(false);
		txtSaldo.setColumns(10);
		txtSaldo.setBounds(0, 18, 79, 20);
		pnSaldo.add(txtSaldo);
		
		//Boton para validar y agregar los articulos buscados en la tabla
		JButton btnAgregarArticulo = new JButton("+");
		btnAgregarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValidarIngresoArticulo();
			}
		});
		btnAgregarArticulo.setBounds(845, 11, 51, 38);
		pn2.add(btnAgregarArticulo);
		
		JPanel pn3 = new JPanel();
		pn3.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn3.setBounds(10, 161, 906, 166);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JScrollPane scrArticulos = new JScrollPane();
		scrArticulos.setBounds(2, 2, 902, 162);
		pn3.add(scrArticulos);
		
		tbArticulos = new JTable(modeloTbArticulos){
			//metodo que permite la no edicion de las columnas 3(Costo) y 4(Total)
			public boolean isCellEditable(int rowIndex, int colIndex) {
				if (colIndex==3) {
			        return true;
			    }
				if (colIndex==4) {
			        return true;
			    }
	            return false;
	        }
		}
		;
		
		scrArticulos.setViewportView(tbArticulos);
      		
		JPanel pn4 = new JPanel();
		pn4.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn4.setBounds(10, 409, 906, 62);
		contentPane.add(pn4);
		pn4.setLayout(null);
		
		JPanel pnTotalOrden = new JPanel();
		pnTotalOrden.setLayout(null);
		pnTotalOrden.setBackground(new Color(0, 51, 0));
		pnTotalOrden.setBounds(164, 11, 150, 38);
		pn4.add(pnTotalOrden);
		
		JLabel lblTotalOrden = new JLabel("Total Orden de Compra");
		lblTotalOrden.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalOrden.setForeground(Color.WHITE);
		lblTotalOrden.setBackground(SystemColor.desktop);
		lblTotalOrden.setBounds(10, 0, 143, 14);
		pnTotalOrden.add(lblTotalOrden);
		
		txtTotalOrden = new JTextField();
		txtTotalOrden.setText(formatearNumero(0.0));
		txtTotalOrden.setColumns(10);
		txtTotalOrden.setBounds(0, 18, 150, 20);
		txtTotalOrden.setEditable(false);
		pnTotalOrden.add(txtTotalOrden);
		
		JPanel pnItem = new JPanel();
		pnItem.setLayout(null);
		pnItem.setBackground(new Color(0, 51, 0));
		pnItem.setBounds(324, 11, 60, 38);
		pn4.add(pnItem);
		
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
		
		//Funcionalidad del boton quitar articulo en la que elimina los articulos seleccionados
		JButton btnQuitarArticulo = new JButton("Quitar Articulo");
		btnQuitarArticulo.setForeground(new Color(0, 51, 0));
		btnQuitarArticulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuitarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarArticulo();
			}
		});
		btnQuitarArticulo.setBounds(420, 20, 117, 23);
		pn4.add(btnQuitarArticulo);
		
		JButton btnGuardarOrdenC = new JButton("Guardar Orden de Compra");
		btnGuardarOrdenC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarOrdenCompra();
			}
		});
		btnGuardarOrdenC.setForeground(new Color(255, 0, 0));
		btnGuardarOrdenC.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGuardarOrdenC.setBounds(570, 20, 181, 23);
		pn4.add(btnGuardarOrdenC);
		
		JPanel pn5 = new JPanel();
		pn5.setLayout(null);
		pn5.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn5.setBounds(10, 336, 906, 62);
		contentPane.add(pn5);
		
		txaObservaciones = new JTextPane();
		txaObservaciones.setBounds(112, 11, 784, 40);
		pn5.add(txaObservaciones);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaciones.setBounds(10, 11, 92, 14);
		pn5.add(lblObservaciones);
		
		listarProveedores();
		buscarArticuloPorNombre();
		llenarColumnasTbArticulos();
	}
	//Metodo para buscar un articulo mediante su nombre
	private void buscarArticuloPorNombre() {
		TextAutoCompleter textoAutocompletar = new TextAutoCompleter( txtArticulo);
		DelegadoArticulo delegadoArticulo = new DelegadoArticulo();
		List<Articulo> articulos = delegadoArticulo.listarArticulo();
		
		for(Articulo articulo : articulos){
			textoAutocompletar.addItem(articulo.getNombre().toString());
		}
	}
	//Metodo para listar los proveedores y desplegarlos en un combo box
	private void listarProveedores() {
		DelegadoProveedor delegadoProveedor = new DelegadoProveedor();
		List<Proveedor> proveedores = delegadoProveedor.listarProveedor();
		modeloProveedor.addElement("--ELIJA PROVEEDOR--");
		cbProveedor.setModel(modeloProveedor);
		
		for(Proveedor proveedor : proveedores){
			modeloProveedor.addElement(new Persona (proveedor.getNombre(), proveedor.getIdentificacion()));
			cbProveedor.setModel(modeloProveedor);
		}
		
		modeloProveedor.addElement("--NUEVO PROVEEDOR--");
		cbProveedor.setModel(modeloProveedor);
		
	}
	//Metodo para crear proveedor en caso de que no aparezca en el combo box
	private void crearProveedor() {
		if(cbProveedor.getSelectedItem().equals("--NUEVO PROVEEDOR--")){
			VentRegistrarProveedor ventRegistrarProveedor = new VentRegistrarProveedor();
			cbProveedor.removeAllItems();
			ventRegistrarProveedor.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                	listarProveedores();  
                }
            });
			ventRegistrarProveedor.setVisible(true);
		}
	}
	//Metodo para validar los datos ingresados del articulo agregandolos a la tabla y a su vez calculando
	//la cantidad de items, el descuento, el subtotal y el total
	private void ValidarIngresoArticulo() {
		if(txtCodigo.getText().equals("")||txtArticulo.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Debe Seleccionar el ARTICULO primero ingresando el codigo o articulo");
		}else{
			if(txtCosto.getValue().equals(0)){
				JOptionPane.showMessageDialog(null, "Debe ingresar el COSTO para este item");
			}
			else{
				AgregarArticulo();
				calcularTotalOC();
				calcularCantidadArticulos();
			}
		}
	}
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbArticulos() {
		modeloTbArticulos.addColumn("Codigo");
		modeloTbArticulos.addColumn("Linea");
		modeloTbArticulos.addColumn("Articulo");
		modeloTbArticulos.addColumn("Cant");
		modeloTbArticulos.addColumn("Costo");
		modeloTbArticulos.addColumn("Total");
		
		tbArticulos.setModel(modeloTbArticulos);
	}
	//Metodo para agregar el articulo en la tabla
	private void AgregarArticulo() {
		
        
		Double total = (double) (Integer.parseInt(txtCant.getText())) *(Double.parseDouble(txtCosto.getValue().toString()));

		String [] fila = new String[modeloTbArticulos.getColumnCount()];
		Boolean aviso = false;
		
		fila[0]= txtCodigo.getText();
		fila[1]= txtLinea.getText();
		fila[2]= txtArticulo.getText();
		fila[3]= txtCant.getText();
		fila[4]= formatearNumero(Double.parseDouble(txtCosto.getValue().toString()));
		fila[5]= formatearNumero(total);
        
		for(int i = 0; i<tbArticulos.getRowCount(); i++){
			 
			if(tbArticulos.getValueAt(i,0).equals(txtCodigo.getText())){
				JOptionPane.showMessageDialog(null, "El codigo del articulo " + txtCodigo.getText() + " ya fue adicionado en la tabla por lo tanto modifique la cantidad.");
				aviso = true;
				limpiarArticulos();
			}
		}
		
		if(aviso == false){
			modeloTbArticulos.addRow(fila);
			tbArticulos.setModel(modeloTbArticulos);
			limpiarArticulos();
		}
		
      //Evento que permite la modificacion de la tabla cada vez que esta es editada
      		tbArticulos.getModel().addTableModelListener(new TableModelListener() {
                  @Override
                  public void tableChanged(TableModelEvent evento) {
                      actualizarTabla(evento);
                  }
              });
	}
	
	
	//Metodo para limpiar las cajas de texto en la que se consulto los atributos del articulo
	private void limpiarArticulos() {
		txtCodigo.setText("");
		txtArticulo.setText("");
		txtLinea.setText("");
		txtUe.setText("");
		txtCant.setText("1");
		txtCosto.setValue(0);
		txtSaldo.setText("");
	}
	//Metodo para quitar los articulos ya ingresados en la tabla
	private void quitarArticulo() {
		try{
			DefaultTableModel modeloArticuloAEliminar = (DefaultTableModel) tbArticulos.getModel();
			modeloArticuloAEliminar.removeRow(tbArticulos.getSelectedRow()); 
			calcularTotalOC();
			calcularCantidadArticulos();
		}catch(Exception e){
			e.getMessage();
			JOptionPane.showMessageDialog(null, "Debe seleccionar cualquier articulo para ser eliminado");
		}
	}
	//Metodo para calcular el total de la orden de compra
	private void calcularTotalOC(){
		
		Double subtotal1 = (double) 0;
		
		for(int i=0; i<tbArticulos.getRowCount(); i++) {
			subtotal= Double.parseDouble(desformatearNumero(String.valueOf(tbArticulos.getValueAt(i,5))));
			subtotal1 += subtotal;
		}
		
		txtTotalOrden.setText(formatearNumero(subtotal1));

	}

	//Metodo para calcular la cantidad de items registrados en la tabla
	private void calcularCantidadArticulos() {
		
		int cantidad = tbArticulos.getRowCount();
		txtItem.setText(Integer.toString(cantidad));
		
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
	//Metodo para convertir una fecha de tipo date a una cadena
	private String formatearFecha(Date fecha) {
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fechaFormateado = formateador.format(fecha);
		return fechaFormateado;
	}
	//Metodo para actualizar la tabla cada vez que haya un cambio en la misma
	private void actualizarTabla(TableModelEvent evento) {
		
		if (evento.getType() == TableModelEvent.UPDATE) {

            // Se obtiene el modelo de la tabla y la fila/columna que han cambiado.
            TableModel modelo = ((TableModel) (evento.getSource()));
            int fila = evento.getFirstRow();
            int columna = evento.getColumn();
           // Se aplica los calculos solamente a la columnas 3 (costo) y 4 (total)
            if (columna == 3 || columna == 4 ) {
	            try{
	            	 int cant = Integer.parseInt(String.valueOf(modelo.getValueAt(fila, 3)));
	            	 double costo = Double.parseDouble(desformatearNumero(String.valueOf(modelo.getValueAt(fila, 4))));
	            	 double total = cant*costo; 
	            	 modelo.setValueAt(formatearNumero(total),fila, 5);	
	            	 calcularCantidadArticulos();
	            	 calcularTotalOC();
	            	 
	            }catch(Exception ex){
	            	ex.getMessage();
	            }
            }
        }
		
	}
	
	//Metodo para validar los datos ingresados de la orden compra incluyendo la tabla de articulos
	private void validarOrdenCompra() {
		
		if(cbProveedor.getSelectedItem().equals("--ELIJA PROVEEDOR--")){
			JOptionPane.showMessageDialog(null, "Debe elegir el proveedor");
		}else{
			if(dchFechaE.getDate()==null){
				JOptionPane.showMessageDialog(null, "Debe ingresar una fecha de entrega esperada");
			}else{
				if(dchFechaP.getDate()==null){
					JOptionPane.showMessageDialog(null, "Debe ingresar una fecha de pago pactado");
				}else{
					if(tbArticulos.getRowCount() == 0 ){
						JOptionPane.showMessageDialog(null, "Debe ingresar articulos en la tabla");
					}else{
						try{
							int res = JOptionPane.showConfirmDialog(null, "Esta seguro de grabar esta orden de compra?", null, JOptionPane.YES_NO_OPTION);
							if(res==0){
								ingresarOrdenCompra();
							}else{
							
							}
						}catch(Exception ex){
							ex.getMessage();
						}
					}
				}
			} 
		}
	}
	//Metodo para registrar los datos validados de la orden de compra
	private void ingresarOrdenCompra() {
		DelegadoOrdenCompraArticulos delegadoOrdenCompraArticulos = new DelegadoOrdenCompraArticulos();
		DelegadoDetalleOrdenCompra delegadoDetalleOrdenCompra = new DelegadoDetalleOrdenCompra();
		OrdenCompraArticulos ordenCompraArticulos = new OrdenCompraArticulos();
		Persona proveedor = (Persona) cbProveedor.getSelectedItem();
		
		ordenCompraArticulos.setIdentificacionProv(proveedor.getIdentificacion());
		ordenCompraArticulos.setFechaEntregaEsperada(dchFechaE.getDate());
		ordenCompraArticulos.setFechaPagoPactado(dchFechaP.getDate());
		ordenCompraArticulos.setItem(Integer.parseInt(txtItem.getText()));
		ordenCompraArticulos.setTotalOrdenCompra(Double.parseDouble(desformatearNumero(txtTotalOrden.getText())));
		ordenCompraArticulos.setObservaciones(txaObservaciones.getText());
		ordenCompraArticulos.setFechaGeneración(new Date());
		ordenCompraArticulos.setEstado("Pendiente");
		ordenCompraArticulos.setCompra(0);
		ordenCompraArticulos.setEstadoActividad("Activo");
		
		delegadoOrdenCompraArticulos.insertarOrdenCompraArticulos(ordenCompraArticulos);
		List<OrdenCompraArticulos> codigoOrdenCompra=delegadoOrdenCompraArticulos.traerCodigoOrdenCompra();
		
		DetalleOrdenCompra detalleOrdenCompra = new DetalleOrdenCompra();
		Articulo articulo = new Articulo();
		List<DetalleOrdenCompra> detallesOrden = new ArrayList<DetalleOrdenCompra>();
		
		for(int i=0; i< modeloTbArticulos.getRowCount();i++){
			String codigo = modeloTbArticulos.getValueAt(i, 0).toString();
			String descripcion= modeloTbArticulos.getValueAt(i, 2).toString();
			String cantidad= modeloTbArticulos.getValueAt(i, 3).toString();
			String costo= modeloTbArticulos.getValueAt(i, 4).toString();
			String total= modeloTbArticulos.getValueAt(i, 5).toString();
			
			ordenCompraArticulos.setIdOrdenCompra(codigoOrdenCompra.get(0).getIdOrdenCompra());
			detalleOrdenCompra.setOrdenCompraArticulos(ordenCompraArticulos);
			articulo.setCodigo(Integer.parseInt(codigo));
			articulo.setDescripcion(descripcion);
			detalleOrdenCompra.setArticulo(articulo);
			detalleOrdenCompra.setCantidad(Integer.parseInt(cantidad));
			detalleOrdenCompra.setCosto(Double.parseDouble(desformatearNumero(costo)));
			detalleOrdenCompra.setTotal(Double.parseDouble(desformatearNumero(total)));
			detallesOrden.add(detalleOrdenCompra);
			delegadoDetalleOrdenCompra.insertarDetalleOrdenCompra(detalleOrdenCompra);
			
		}
		
		JOptionPane.showMessageDialog(null, "Se ha registrado esta orden de compra satisfactoriamente con consecutivo N° "+codigoOrdenCompra.get(0).getIdOrdenCompra());
		verificarOrdenCompra();
	}
	//Metodo para verificar la orden de compra con los datos ya registrados
	private void verificarOrdenCompra() {
		VentVerificarOrdenCompra ventVerificarOrdenCompra = new VentVerificarOrdenCompra(txtTotalOrden.getText(), formatearFecha(dchFechaE.getDate()), cbProveedor.getSelectedItem().toString(), txtItem.getText(),formatearFecha(dchFechaP.getDate()));
		ventVerificarOrdenCompra.setVisible(true);
		limpiar();
	}
	//Metodo para limpiar los datos ingresados de la orden de compra
	private void limpiar() {
		cbProveedor.setSelectedIndex(0);
		dchFechaE.setCalendar(null);
		dchFechaP.setCalendar(null);
		txaObservaciones.setText("");
		txtItem.setText("");
		txtTotalOrden.setText("");
		txtCosto.setValue(0);
		limpiarTabla();
	}
	//Metodo para limpiar la tabla cada vez que esta se llene
	private void limpiarTabla(){
	       for (int i = 0; i < tbArticulos.getRowCount(); i++) {
	           modeloTbArticulos.removeRow(i);
	           i-=1;
	       }
	   }
	//Metodo para formatear un numero y convertirlo en moneda peso
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
}
