package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Color;
import java.util.List;

import co.com.jungla.sac.persistencia.entidades.Articulo;

import javax.swing.border.LineBorder;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class VentMostrarArticulos extends JDialog {

	private JPanel contentPane;
	private JTable tbArticulos;
	private JTextField txtNumeroRegistros;
	private DefaultTableModel modeloTbArticulos = new DefaultTableModel();
	private int filaSeleccionada;
	private List<Articulo> listaArticulos;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VentMostrarArticulos(List <Articulo> listaArticulos) {
		setTitle("Listado de Articulos");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 1279, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 1245, 353);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JScrollPane scrArticulos = new JScrollPane();
		scrArticulos.setBounds(2, 2, 1241, 349);
		pn1.add(scrArticulos);
		
		tbArticulos = new JTable();
		tbArticulos.setToolTipText("Puede seleccionar cualquiera de las filas y presionar clic derecho para ver las acciones que puede realizar.");
		tbArticulos.setEnabled(false);
		tbArticulos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbArticulos.rowAtPoint(point);
		        tbArticulos.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		        mostrarPopupEnTabla();
			}
		});
		scrArticulos.setViewportView(tbArticulos);
		
		
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 375, 1245, 61);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JPanel pnNumeroRegistros = new JPanel();
		pnNumeroRegistros.setLayout(null);
		pnNumeroRegistros.setBackground(new Color(0, 51, 0));
		pnNumeroRegistros.setBounds(409, 11, 150, 38);
		pn2.add(pnNumeroRegistros);
		
		JLabel lblNumeroRegistros = new JLabel("Numero de registros");
		lblNumeroRegistros.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNumeroRegistros.setForeground(Color.WHITE);
		lblNumeroRegistros.setBackground(SystemColor.desktop);
		lblNumeroRegistros.setBounds(18, 0, 128, 14);
		pnNumeroRegistros.add(lblNumeroRegistros);
		
		txtNumeroRegistros = new JTextField();
		txtNumeroRegistros.setColumns(10);
		txtNumeroRegistros.setBounds(0, 18, 150, 20);
		txtNumeroRegistros.setEditable(false);
		pnNumeroRegistros.add(txtNumeroRegistros);
		
		JButton btnExportar = new JButton("Exportar a Excel");
		btnExportar.setForeground(new Color(0, 51, 0));
		btnExportar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExportar.setBounds(590, 18, 140, 23);
		pn2.add(btnExportar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setForeground(new Color(0, 51, 0));
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVolver.setBounds(766, 18, 104, 23);
		pn2.add(btnVolver);
		
		this.listaArticulos = listaArticulos;
		
		llenarTabla(this.listaArticulos);
		calcularCantidadItems();
		
		setModal(true);
		
	}
	
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbArticulos() {
		modeloTbArticulos.addColumn("Codigo");
		modeloTbArticulos.addColumn("Linea");
		modeloTbArticulos.addColumn("Articulo");
		modeloTbArticulos.addColumn("UE");
		modeloTbArticulos.addColumn("Presentación");
		modeloTbArticulos.addColumn("Referencia");
		modeloTbArticulos.addColumn("Descripción");
		modeloTbArticulos.addColumn("Estado");
		
		tbArticulos.setModel(modeloTbArticulos);
	}
	
	//Metodo para llenar la tabla cuando recibe como parametro una lista de articulos
	private void llenarTabla( List <Articulo> listaArticulos) {

		if(tbArticulos.getModel().getColumnCount()==0){
			llenarColumnasTbArticulos();
		}
		
		String [] fila = new String[modeloTbArticulos.getColumnCount()];
		
		for(Articulo articulos : listaArticulos){
			
			fila[0]= String.valueOf(articulos.getCodigo());
			fila[1]= articulos.getLineaArticulos().getNombreL();
			fila[2]= articulos.getNombre();
			fila[3]= articulos.getUnidadMedida().getNombre();
			fila[4]= articulos.getPresentacion();
			fila[5]= articulos.getReferencia();
			fila[6]= articulos.getDescripcion();
			fila[7]= articulos.getEstado();
			
			modeloTbArticulos.addRow(fila);
		}
		
		tbArticulos.setModel(modeloTbArticulos);
	}
	
	//Metodo que permite calcular la cantidad de registros
	private void calcularCantidadItems() {
		
		int cantidad = tbArticulos.getRowCount();
		txtNumeroRegistros.setText(Integer.toString(cantidad));
		
	}
	
	//Metodo para mostrar el popup en la tabla	
	private void mostrarPopupEnTabla(){
		JPopupMenu pmArticulos = new JPopupMenu();
		JMenuItem miModificar = new JMenuItem("Modificar");
		
		pmArticulos.add(miModificar);
		miModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarArticuloParaModificar();
			}
		});
			
		tbArticulos.setComponentPopupMenu(pmArticulos);	
	}
	
	//Metodo para enviar los datos del articulo a la ventana modificar articulo
	private void enviarArticuloParaModificar() {
		VentModificarArticulo ventModificarArticulo = new VentModificarArticulo();
		Articulo articuloElegido = listaArticulos.get(filaSeleccionada);
		ventModificarArticulo.agregarDatos(articuloElegido);
		ventModificarArticulo.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
            	limpiarTabla();
            	llenarTabla(listaArticulos);
            }
        });
		ventModificarArticulo.setVisible(true);
		
	}
	
	//Metodo para limpiar la tabla 
	private void limpiarTabla(){
	       for (int i = 0; i < tbArticulos.getRowCount(); i++) {
	    	   modeloTbArticulos.removeRow(i);
	           i-=1;
	       }
	 }
}
