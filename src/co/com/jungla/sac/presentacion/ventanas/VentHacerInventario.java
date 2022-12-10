package co.com.jungla.sac.presentacion.ventanas;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.SystemColor;
import java.awt.Color;
import java.util.List;

import co.com.jungla.sac.persistencia.entidades.Articulo;

import co.com.jungla.sac.negocio.delegados.DelegadoArticulo;
import com.mxrck.autocompleter.TextAutoCompleter;
import javax.swing.border.LineBorder;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class VentHacerInventario extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtArticulo;
	private JTextField txtLinea;
	private JTextField txtUe;
	private JTextField txtCant;
	private JTextField txtSaldo;
	private JTable tbArticulos;
	private JPanel pnUe;
	private DefaultTableModel modeloTbArticulos = new DefaultTableModel();
	private JTextField txtItems;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentHacerInventario frame = new VentHacerInventario();
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
	public VentHacerInventario() {
		setTitle("Hacer Conteo Inventario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 820, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 789, 62);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JPanel pnItems = new JPanel();
		pnItems.setLayout(null);
		pnItems.setBackground(new Color(0, 51, 0));
		pnItems.setBounds(525, 11, 60, 38);
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
		
		JButton btnQuitarArticulo = new JButton("Quitar Articulo");
		btnQuitarArticulo.setForeground(new Color(0, 51, 0));
		btnQuitarArticulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuitarArticulo.setBounds(33, 26, 117, 23);
		pn1.add(btnQuitarArticulo);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.RED);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGuardar.setBounds(160, 26, 101, 23);
		pn1.add(btnGuardar);
		
		JButton btnResetear = new JButton("Resetear");
		btnResetear.setForeground(new Color(0, 51, 0));
		btnResetear.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnResetear.setBounds(271, 26, 117, 23);
		pn1.add(btnResetear);
		
		JButton btnComparar = new JButton("Comparar");
		btnComparar.setForeground(new Color(0, 51, 0));
		btnComparar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnComparar.setBounds(398, 26, 117, 23);
		pn1.add(btnComparar);
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(null);
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 84, 789, 62);
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
		lblArticulo.setBounds(59, 0, 160, 14);
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
		
		JPanel pnSaldo = new JPanel();
		pnSaldo.setLayout(null);
		pnSaldo.setBackground(new Color(0, 51, 0));
		pnSaldo.setBounds(636, 11, 79, 38);
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
		btnAgregarArticulo.setBounds(725, 11, 51, 38);
		pn2.add(btnAgregarArticulo);
		
		JPanel pn3 = new JPanel();
		pn3.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn3.setBounds(10, 161, 789, 359);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JScrollPane scrArticulos = new JScrollPane();
		scrArticulos.setBounds(2, 2, 784, 354);
		pn3.add(scrArticulos);
		
		tbArticulos = new JTable(modeloTbArticulos);
		scrArticulos.setViewportView(tbArticulos);
		
		buscarArticuloPorNombre();
		llenarColumnasTbArticulos();
		
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
				txtCodigo.setText(Integer.toString(consulta.get(0).getCodigo()));
				txtLinea.setText(consulta.get(0).getLineaArticulos().getNombreL());
				txtUe.setText(consulta.get(0).getUnidadMedida().getAbreviatura().toString());
				txtSaldo.setText(String.valueOf(consulta.get(0).getControlInventario().getCantExistencia()));
			}
		}catch(Exception ex){
			ex.getMessage();
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
			}
			
		}catch(Exception ex){
			ex.getMessage();
			JOptionPane.showMessageDialog(null, "El codigo "+txtCodigo.getText()+" no existe");
			limpiarDatosArticulo();
		}
		
	}
	
	//Metodo para buscar un articulo mediante su nombre permitiendo autocompletar
	private void buscarArticuloPorNombre() {
		TextAutoCompleter textoAutocompletar = new TextAutoCompleter( txtArticulo);
		DelegadoArticulo delegadoArticulo = new DelegadoArticulo();
		List<Articulo> articulos = delegadoArticulo.listarArticulo();
		
		for(Articulo articulo : articulos){
			textoAutocompletar.addItem(articulo.getNombre().toString());
		}
	}
	
	//Metodo para validar los datos ingresados del articulo agregandolos a la tabla y a su vez calculando
	//la cantidad de items, el descuento, el subtotal y el total
	private void validarIngresoArticuloATbArticulos() {
		if(txtCodigo.getText().equals("")||txtArticulo.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Debe Seleccionar el ARTICULO primero ingresando el codigo o articulo");
		}else{
			agregarArticuloATbArticulos();
			calcularCantidadArticulos();
		}
	}
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbArticulos() {
		modeloTbArticulos.addColumn("Codigo");
		modeloTbArticulos.addColumn("Linea");
		modeloTbArticulos.addColumn("Articulo");
		modeloTbArticulos.addColumn("Cant");
		
		mostrarColumna(0, 100, 10, 70);
		mostrarColumna(3, 100, 10, 70);
		
		tbArticulos.setModel(modeloTbArticulos);
	}
	//Metodo para agregar el articulo en la tabla
	private void agregarArticuloATbArticulos() {
		
		String [] fila = new String[modeloTbArticulos.getColumnCount()];
		Boolean aviso = false;
		
		fila[0]= txtCodigo.getText();
		fila[1]= txtLinea.getText();
		fila[2]= txtArticulo.getText();
		fila[3]= txtCant.getText();
        
		for(int i = 0; i<tbArticulos.getRowCount(); i++){
			 
			if(tbArticulos.getValueAt(i,0).equals(txtCodigo.getText())){
				int sumarCantidad = Integer.parseInt(txtCant.getText()) + Integer.parseInt(tbArticulos.getValueAt(i, 3).toString());
				fila[0]= txtCodigo.getText();
				fila[1]= txtLinea.getText();
				fila[2]= txtArticulo.getText();
				fila[3]= String.valueOf(sumarCantidad);
				
				modeloTbArticulos.addRow(fila);
				modeloTbArticulos.removeRow(i);
				tbArticulos.setModel(modeloTbArticulos);

				//JOptionPane.showMessageDialog(null, "El codigo del articulo " + txtCodigo.getText() + " ya fue adicionado en la tabla por lo tanto modifique la cantidad.");
				aviso = true;
				limpiarDatosArticulo();
			}
		}
		
		if(aviso == false){
			modeloTbArticulos.addRow(fila);
			tbArticulos.setModel(modeloTbArticulos);
			limpiarDatosArticulo();
		}
	}
	
	
	//Metodo para limpiar las cajas de texto en la que se consulto los atributos del articulo
	private void limpiarDatosArticulo() {
		txtCodigo.setText("");
		txtArticulo.setText("");
		txtLinea.setText("");
		txtUe.setText("");
		txtCant.setText("1");
		txtSaldo.setText("");
	}
	
	//Metodo para calcular la cantidad de items registrados en la tabla
	private void calcularCantidadArticulos() {
		
		int cantidad = tbArticulos.getRowCount();
		txtItems.setText(Integer.toString(cantidad));
		
	}
	
	//Metodo para mostrar las columnas que uno desea aplicar
	public void mostrarColumna(int indice, int maxAncho, int minAncho, int norAncho){
		
		tbArticulos.getColumnModel().getColumn(indice).setWidth(norAncho);
		tbArticulos.getColumnModel().getColumn(indice).setMaxWidth(maxAncho);
		tbArticulos.getColumnModel().getColumn(indice).setMinWidth(minAncho);
		tbArticulos.getColumnModel().getColumn(indice).setPreferredWidth(norAncho);
		tbArticulos.getColumnModel().getColumn(indice).setResizable(true);
		
	}
	
	public void compararInventarioTempAReal(){
		
	}
	
	public void guardarInventarioTemporal(){
		
	}
	
	public void resetearInventarioTemporal(){
		
	}
}
