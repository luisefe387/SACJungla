package co.com.jungla.sac.presentacion.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import co.com.jungla.sac.persistencia.entidades.Articulo;
import co.com.jungla.sac.persistencia.entidades.ControlInventario;
import co.com.jungla.sac.persistencia.entidades.DetalleEntradaOSalida;
import co.com.jungla.sac.persistencia.entidades.EntradaOSalidaArticulo;
import co.com.jungla.sac.persistencia.entidades.KardexElectronico;
import co.com.jungla.sac.persistencia.entidades.LineaArticulos;

import co.com.jungla.sac.negocio.delegados.DelegadoArticulo;
import co.com.jungla.sac.negocio.delegados.DelegadoControlInventario;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleEntradaOSalida;
import co.com.jungla.sac.negocio.delegados.DelegadoEntradaOSalidaArticulo;
import co.com.jungla.sac.negocio.delegados.DelegadoKardexElectronico;
import com.mxrck.autocompleter.TextAutoCompleter;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JFormattedTextField;
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
import java.awt.Font;
import javax.swing.JTextPane;


public class VentRegistrarEntradaArticulos extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtArticulo;
	private JTextField txtLinea;
	private JTextField txtUe;
	private JTextField txtCant;
	private JFormattedTextField txtCosto;
	private JTextField txtSaldo;
	private JTable tbArticulos;
	private JPanel pnUe;
	DefaultTableModel modeloTbArticulos = new DefaultTableModel();
	private JTextField txtTotalCosto;
	private JTextField txtItems;
	private JTextPane txpObservaciones;
	private EntradaOSalidaArticulo codigoEntrada;
	private DetalleEntradaOSalida ultimoDetalleEntrada;
	EntradaOSalidaArticulo entradaArticulo = new EntradaOSalidaArticulo();
	private DelegadoKardexElectronico delegadoKardexElectronico = new DelegadoKardexElectronico();
	private List <KardexElectronico> ultimoRegistroPorArticuloKardex1;
	private KardexElectronico ultimoRegistroPorArticuloKardex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentRegistrarEntradaArticulos frame = new VentRegistrarEntradaArticulos();
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
	public VentRegistrarEntradaArticulos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarEntradaArticulos.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Entrada de Articulos del Inventario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 937, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
					cargarDatosArticuloPorCodigo();
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
		lblArticulo.setBounds(61, 0, 160, 14);
		pnArticulo.add(lblArticulo);
		
		txtArticulo = new JTextField();
		//Evento de teclado que permite traer la linea a la que pertenece el articulo mediante su nombre
		//al presionar enter o tab
		txtArticulo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_TAB ){
					cargarDatosArticuloPorNombre();
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
		txtCosto.setColumns(10);
		formatearAMoneda(txtCosto);
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
				validarIngresoArticuloATbArticulos();
			}
		});
		btnAgregarArticulo.setBounds(845, 11, 51, 38);
		pn2.add(btnAgregarArticulo);
		
		JPanel pn3 = new JPanel();
		pn3.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn3.setBounds(10, 157, 906, 205);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JScrollPane scrArticulos = new JScrollPane();
		scrArticulos.setBounds(2, 2, 900, 201);
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
		
		JPanel pn1 = new JPanel();
		pn1.setLayout(null);
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 906, 62);
		contentPane.add(pn1);
		
		JPanel pnTotalCosto = new JPanel();
		pnTotalCosto.setLayout(null);
		pnTotalCosto.setBackground(new Color(0, 51, 0));
		pnTotalCosto.setBounds(10, 11, 140, 38);
		pn1.add(pnTotalCosto);
		
		JLabel lblTotalCosto = new JLabel("Total Costo");
		lblTotalCosto.setForeground(Color.WHITE);
		lblTotalCosto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalCosto.setBackground(SystemColor.desktop);
		lblTotalCosto.setBounds(38, 0, 84, 14);
		pnTotalCosto.add(lblTotalCosto);
		
		txtTotalCosto = new JTextField();
		txtTotalCosto.setColumns(10);
		txtTotalCosto.setText(formatearNumero(0.0));
		txtTotalCosto.setEditable(false);
		txtTotalCosto.setBounds(0, 18, 140, 20);
		pnTotalCosto.add(txtTotalCosto);
		
		JButton btnQuitarArticulo = new JButton("Quitar Articulo");
		btnQuitarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarArticuloATbArticulos();
			}
		});
		btnQuitarArticulo.setForeground(new Color(0, 51, 0));
		btnQuitarArticulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuitarArticulo.setBounds(245, 17, 117, 23);
		pn1.add(btnQuitarArticulo);
		
		JButton btnRegistrarEntrada = new JButton("Registrar Entrada");
		btnRegistrarEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
		});
		btnRegistrarEntrada.setForeground(new Color(255, 0, 0));
		btnRegistrarEntrada.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegistrarEntrada.setBounds(384, 17, 140, 23);
		pn1.add(btnRegistrarEntrada);
		
		JPanel pnItems = new JPanel();
		pnItems.setLayout(null);
		pnItems.setBackground(new Color(0, 51, 0));
		pnItems.setBounds(160, 11, 60, 38);
		pn1.add(pnItems);
		
		JLabel lblItems = new JLabel("Item");
		lblItems.setForeground(Color.WHITE);
		lblItems.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblItems.setBackground(SystemColor.desktop);
		lblItems.setBounds(16, 0, 44, 14);
		pnItems.add(lblItems);
		
		txtItems = new JTextField();
		txtItems.setText("0");
		txtItems.setEditable(false);
		txtItems.setColumns(10);
		txtItems.setBounds(0, 18, 60, 20);
		pnItems.add(txtItems);
		
		JScrollPane scrObservaciones = new JScrollPane();
		scrObservaciones.setBounds(544, 18, 352, 38);
		pn1.add(scrObservaciones);
		
		txpObservaciones = new JTextPane();
		scrObservaciones.setViewportView(txpObservaciones);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaciones.setBounds(544, 3, 96, 14);
		pn1.add(lblObservaciones);
		
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
	
	
	//Metodo para encontrar los datos del articulo incluyendo su linea mediante el codigo
	private void cargarDatosArticuloPorCodigo() {
		try{
			DelegadoArticulo delegadoArticulo =new DelegadoArticulo();
			List<Articulo> consulta=delegadoArticulo.traerLineaUnidadArticuloPorCodigo(Integer.parseInt(txtCodigo.getText()));
			if(consulta.get(0).getEstado().equals("Inactivo")){
				JOptionPane.showMessageDialog(null, "El articulo "+consulta.get(0).getNombre()+" se encuentra INACTIVO");
				txtCodigo.setText("");
			}else{
				txtArticulo.setText(consulta.get(0).getNombre());
				txtLinea.setText(consulta.get(0).getLineaArticulos().getNombreL());
				txtUe.setText(consulta.get(0).getUnidadMedida().getAbreviatura().toString());
				txtSaldo.setText(String.valueOf(consulta.get(0).getControlInventario().getCantExistencia()));
				txtCosto.setValue(consulta.get(0).getControlInventario().getCostoPromedio());
			}
			
		}catch(Exception ex){
			ex.getMessage();
			JOptionPane.showMessageDialog(null, "El codigo "+txtCodigo.getText()+" no existe");
			limpiarDatosArticulo();
		}
		
	}
	
	//Metodo para encontrar los datos del articulo incluyendo su linea mediante el codigo
	private void cargarDatosArticuloPorNombre() {
		try{
			DelegadoArticulo delegadoArticulo =new DelegadoArticulo();
			List<Articulo> consulta=delegadoArticulo.traerLineaUnidadArticulo(txtArticulo.getText());
			if(consulta.get(0).getEstado().equals("Inactivo")){
				JOptionPane.showMessageDialog(null, "El articulo "+consulta.get(0).getNombre()+" se encuentra INACTIVO");
				txtArticulo.setText("");
			}else{
				txtArticulo.setText(consulta.get(0).getNombre());
				txtLinea.setText(consulta.get(0).getLineaArticulos().getNombreL());
				txtUe.setText(consulta.get(0).getUnidadMedida().getAbreviatura().toString());
				txtSaldo.setText(String.valueOf(consulta.get(0).getControlInventario().getCantExistencia()));
				txtCosto.setValue(consulta.get(0).getControlInventario().getCostoPromedio());
			}
		}catch(Exception ex){
			ex.getMessage();
		}
	}
	
	//Metodo para limpiar las cajas de texto en la que se consulto los atributos del articulo
	private void limpiarDatosArticulo() {
		txtCodigo.setText("");
		txtArticulo.setText("");
		txtLinea.setText("");
		txtUe.setText("");
		txtCant.setText("1");
		txtCosto.setValue(0);
		txtSaldo.setText("");
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
	
	//Metodo para validar los datos ingresados del articulo agregandolos a la tabla y a su vez calculando
	//la cantidad de items, el descuento, el subtotal y el total
	private void validarIngresoArticuloATbArticulos() {
		if(txtCodigo.getText().equals("")||txtArticulo.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Debe Seleccionar el ARTICULO primero ingresando el codigo o articulo");
		}else{
			agregarArticuloATbArticulos();
			calcularCantidadArticulos();
			calcularTotalCosto();
		}
	}
	//Metodo para validar los datos ingresados de la entrada incluyendo la tabla de articulos
	private void validarDatos() {
		if(tbArticulos.getRowCount() == 0 ){
			JOptionPane.showMessageDialog(null, "Debe ingresar articulos en la tabla");
		}else{
			abrirDialogoConfirmacionRegistro();
		}
	}
	
	//Metodo para calcular la cantidad de items registrados en la tabla
	private void calcularCantidadArticulos() {
		
		int cantidad = tbArticulos.getRowCount();
		txtItems.setText(Integer.toString(cantidad));
		
	}
	
	//Metodo para calcular el total costo
	private void calcularTotalCosto(){
		Double totalCosto;
		Double totalCosto1 = (double) 0;
		
		for(int i=0; i<tbArticulos.getRowCount(); i++) {
			totalCosto= Double.parseDouble(desformatearNumero(String.valueOf(tbArticulos.getValueAt(i,5))));
			totalCosto1 += totalCosto;
		}
		
		txtTotalCosto.setText(formatearNumero(totalCosto1));
	}
	
	//Metodo para agregar el articulo en la tabla
	private void agregarArticuloATbArticulos() {
		
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
				limpiarDatosArticulo();
			}
		}
		
		if(aviso == false){
			modeloTbArticulos.addRow(fila);
			tbArticulos.setModel(modeloTbArticulos);
			limpiarDatosArticulo();
		}
		
      //Evento que permite la modificacion de la tabla cada vez que esta es editada
      		tbArticulos.getModel().addTableModelListener(new TableModelListener() {
                  @Override
                  public void tableChanged(TableModelEvent evento) {
                      actualizarTbArticulos(evento);
                  }
              });
	}
	
	//Metodo para actualizar la tabla cada vez que haya un cambio en la misma
	private void actualizarTbArticulos(TableModelEvent evento) {
		
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
	            	 calcularTotalCosto();
	            	 
	            }catch(Exception ex){
	            	ex.getMessage();
	            }
            }
        }
		
	}
	//Metodo para convertir un numero corriente en formato de pesos y decimales
	private String formatearNumero(Double numero){
		NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es","CO"));
		return formato.format(numero); 
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
	
	//Metodo para quitar los articulos ya ingresados en la tabla
	private void quitarArticuloATbArticulos() {
		try{
			DefaultTableModel modeloArticuloAEliminar = (DefaultTableModel) tbArticulos.getModel();
			modeloArticuloAEliminar.removeRow(tbArticulos.getSelectedRow()); 
			calcularCantidadArticulos();
			calcularTotalCosto();
		}catch(Exception e){
			e.getMessage();
			JOptionPane.showMessageDialog(null, "Debe seleccionar cualquier articulo para ser eliminado");
		}
	}
	//Metodo para registrar la entrada del articulos
	private void registrarEntradaArticulo() {
		DelegadoEntradaOSalidaArticulo delegadoEntradaArticulo = new DelegadoEntradaOSalidaArticulo();
		
		entradaArticulo.setConcepto("Entrada");
		entradaArticulo.setFecha(new Date());
		entradaArticulo.setCantTotalArticulos(Integer.parseInt(txtItems.getText()));
		entradaArticulo.setTotalCosto(Double.parseDouble(desformatearNumero(txtTotalCosto.getText())));
		entradaArticulo.setObservaciones(txpObservaciones.getText());
		
		delegadoEntradaArticulo.insertarEntradaOSalidaArticulo(entradaArticulo);
		
		codigoEntrada = delegadoEntradaArticulo.traerUltimaEntradaOSalida();
		
		registrarDetalleEntrada();
		abrirVentanaEntradaRegistrada();
		
	}
	//Metodo apra registrar el detalle de la entrada
	private void registrarDetalleEntrada() {
		DelegadoDetalleEntradaOSalida delegadoDetalleEntrada = new DelegadoDetalleEntradaOSalida();
		DetalleEntradaOSalida detalleEntrada = new DetalleEntradaOSalida();
		Articulo articulo = new Articulo();
		LineaArticulos lineaArticulos = new LineaArticulos();
		
		for(int i=0; i< modeloTbArticulos.getRowCount();i++){
			
			entradaArticulo.setIdEntradaOSalida(codigoEntrada.getIdEntradaOSalida());
			detalleEntrada.setEntradaOSalidaArticulo(entradaArticulo);
			lineaArticulos.setNombreL(modeloTbArticulos.getValueAt(i, 1).toString());
			articulo.setCodigo(Integer.parseInt(modeloTbArticulos.getValueAt(i, 0).toString()));
			articulo.setDescripcion(modeloTbArticulos.getValueAt(i, 2).toString());
			articulo.setLineaArticulos(lineaArticulos);
			detalleEntrada.setArticulo(articulo);
			detalleEntrada.setCantidad(Integer.parseInt(modeloTbArticulos.getValueAt(i, 3).toString()));
			detalleEntrada.setCostoUnitario(Double.parseDouble(desformatearNumero(modeloTbArticulos.getValueAt(i, 4).toString())));
			detalleEntrada.setTotal(Double.parseDouble(desformatearNumero(modeloTbArticulos.getValueAt(i, 5).toString())));
			
			delegadoDetalleEntrada.insertarDetalleEntradaOSalida(detalleEntrada);
			
			registrarDetalleEntradaAlKardex();
		}
	}
	//Metodo para registrar el detalle de la entrada al kardex electronico de articulos
	private void registrarDetalleEntradaAlKardex() {
		KardexElectronico kardexElectronico = new KardexElectronico();
		DelegadoDetalleEntradaOSalida delegadoDetalleEntradaOSalida = new DelegadoDetalleEntradaOSalida();
		DelegadoControlInventario delegadoControlInventario = new DelegadoControlInventario();
		List<ControlInventario> controlInventario;
		ControlInventario controlInventarioAModificar;
		Articulo articulo = new Articulo();
		
		ultimoDetalleEntrada = delegadoDetalleEntradaOSalida.traerUltimoDetalleEntradaOSalida();
		
		if(delegadoKardexElectronico.traerUltimoRegistroKardexElectronicopPorArticulo(ultimoDetalleEntrada.getArticulo().getCodigo())==null){
			kardexElectronico.setFecha(ultimoDetalleEntrada.getEntradaOSalidaArticulo().getFecha());
			kardexElectronico.setMovimiento(ultimoDetalleEntrada.getEntradaOSalidaArticulo().getConcepto());
			kardexElectronico.setNroDocumento(ultimoDetalleEntrada.getEntradaOSalidaArticulo().getIdEntradaOSalida());
			articulo.setCodigo(ultimoDetalleEntrada.getArticulo().getCodigo());
			kardexElectronico.setArticulo(articulo);
			kardexElectronico.setCantidadEntrada(ultimoDetalleEntrada.getCantidad());
			kardexElectronico.setCostoUEntrada(ultimoDetalleEntrada.getCostoUnitario());
			kardexElectronico.setCostoTEntrada(ultimoDetalleEntrada.getTotal());
			kardexElectronico.setCantidadSalida(0);
			kardexElectronico.setCostoUSalida(0);
			kardexElectronico.setCostoTSalida(0);
			kardexElectronico.setCantidadSaldo(ultimoDetalleEntrada.getCantidad());
			kardexElectronico.setCostoUSaldo(ultimoDetalleEntrada.getCostoUnitario());
			kardexElectronico.setCostoTSaldo(ultimoDetalleEntrada.getTotal());
			kardexElectronico.setProveedorUltimaCompra("");
			
			delegadoKardexElectronico.insertarKardexElectronico(kardexElectronico);
			
			controlInventario = delegadoControlInventario.traerContInventarioPorCodigoArticulo(ultimoDetalleEntrada.getArticulo().getCodigo());
			controlInventarioAModificar = controlInventario.get(0);
			
			controlInventarioAModificar.setCostoPromedio(ultimoDetalleEntrada.getCostoUnitario());
			controlInventarioAModificar.setCantExistencia(ultimoDetalleEntrada.getCantidad());
			controlInventarioAModificar.setTotalCostoInventario(ultimoDetalleEntrada.getTotal());
				
			delegadoControlInventario.actualizarControlInventario(controlInventarioAModificar);
		}else{
			
			ultimoRegistroPorArticuloKardex1 = delegadoKardexElectronico.traerUltimoRegistroKardexElectronicopPorArticulo(ultimoDetalleEntrada.getArticulo().getCodigo());
			ultimoRegistroPorArticuloKardex = ultimoRegistroPorArticuloKardex1.get(0);
			
			kardexElectronico.setFecha(ultimoDetalleEntrada.getEntradaOSalidaArticulo().getFecha());
			kardexElectronico.setMovimiento(ultimoDetalleEntrada.getEntradaOSalidaArticulo().getConcepto());
			kardexElectronico.setNroDocumento(ultimoDetalleEntrada.getEntradaOSalidaArticulo().getIdEntradaOSalida());
			articulo.setCodigo(ultimoDetalleEntrada.getArticulo().getCodigo());
			kardexElectronico.setArticulo(articulo);
			kardexElectronico.setCantidadEntrada(ultimoDetalleEntrada.getCantidad());
			kardexElectronico.setCostoUEntrada(ultimoDetalleEntrada.getCostoUnitario());
			kardexElectronico.setCostoTEntrada(ultimoDetalleEntrada.getTotal());
			kardexElectronico.setCantidadSalida(0);
			kardexElectronico.setCostoUSalida(0);
			kardexElectronico.setCostoTSalida(0);
			kardexElectronico.setCantidadSaldo(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimoDetalleEntrada.getCantidad());
			kardexElectronico.setCostoUSaldo((ultimoRegistroPorArticuloKardex.getCostoTSaldo() + ultimoDetalleEntrada.getTotal())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimoDetalleEntrada.getCantidad()));
			kardexElectronico.setCostoTSaldo((ultimoRegistroPorArticuloKardex.getCostoTSaldo() + ultimoDetalleEntrada.getTotal())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimoDetalleEntrada.getCantidad())*(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimoDetalleEntrada.getCantidad()));
			kardexElectronico.setProveedorUltimaCompra("");
			
			delegadoKardexElectronico.insertarKardexElectronico(kardexElectronico);
				
			controlInventario = delegadoControlInventario.traerContInventarioPorCodigoArticulo(ultimoDetalleEntrada.getArticulo().getCodigo());
			controlInventarioAModificar = controlInventario.get(0);
			
			controlInventarioAModificar.setCostoPromedio((ultimoRegistroPorArticuloKardex.getCostoTSaldo() + ultimoDetalleEntrada.getTotal())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimoDetalleEntrada.getCantidad()));
			controlInventarioAModificar.setCantExistencia(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimoDetalleEntrada.getCantidad());
			controlInventarioAModificar.setTotalCostoInventario((ultimoRegistroPorArticuloKardex.getCostoTSaldo() + ultimoDetalleEntrada.getTotal())/(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimoDetalleEntrada.getCantidad())*(ultimoRegistroPorArticuloKardex.getCantidadSaldo() + ultimoDetalleEntrada.getCantidad()));
			
			delegadoControlInventario.actualizarControlInventario(controlInventarioAModificar);
		}
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
		String nl = System.getProperty("line.separator");
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de grabar esta ENTRADA de ARTICULOS del Inventario?"+nl+nl+"Costo Total: "+txtTotalCosto.getText()+nl+"Items: "+txtItems.getText(), null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarEntradaArticulo();
		}else{
		
		}
	}
	
	//Metodo para mostrar los datos registrados de la compra a otra ventana para su puesta verificacion
	private void abrirVentanaEntradaRegistrada() {
		VentMostrarEntradaOSalidaRegistrada ventMostrarEntradaRegistrada= new VentMostrarEntradaOSalidaRegistrada(txtItems.getText(), txtTotalCosto.getText(), txpObservaciones.getText(),"Entrada");
		ventMostrarEntradaRegistrada.setVisible(true);
		limpiarDatos();
		
	}
	//Metodo para limpiar los datos ya registrados
	private void limpiarDatos() {
		limpiarTablaArticulos();
		txpObservaciones.setText("");
		txtItems.setText("0");
		txtTotalCosto.setText(formatearNumero(0.0));
	}
	//Metodo para limpiar la tabla de articulos 
	private void limpiarTablaArticulos() {
		for (int i = 0; i < tbArticulos.getRowCount(); i++) {
	           modeloTbArticulos.removeRow(i);
	           i-=1;
	       }
	}
}
