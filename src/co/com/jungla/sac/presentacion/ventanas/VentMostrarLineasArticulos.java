package co.com.jungla.sac.presentacion.ventanas;


import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Color;
import java.util.List;

import co.com.jungla.sac.persistencia.entidades.LineaArticulos;

import javax.swing.border.LineBorder;
import javax.swing.JFrame;
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

import co.com.jungla.sac.negocio.delegados.DelegadoArticulo;
import co.com.jungla.sac.negocio.delegados.DelegadoLineaArticulos;


public class VentMostrarLineasArticulos extends JFrame {

	private JPanel contentPane;
	private JTable tbLineas;
	private JTextField txtNumeroRegistros;
	private DefaultTableModel modeloTbLineas = new DefaultTableModel();
	private int filaSeleccionada;
	private List<LineaArticulos> listaLineasArticulos;


	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VentMostrarLineasArticulos() {
		setTitle("Listado de Lineas de Articulos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 578, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 548, 353);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JScrollPane scrLineas = new JScrollPane();
		scrLineas.setBounds(2, 2, 544, 349);
		pn1.add(scrLineas);
		
		tbLineas = new JTable();
		tbLineas.setToolTipText("Puede seleccionar cualquiera de las filas y presionar clic derecho para ver las acciones que puede realizar.");
		tbLineas.setEnabled(false);
		tbLineas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbLineas.rowAtPoint(point);
		        tbLineas.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		        mostrarPopupEnTabla();
			}
		});
		scrLineas.setViewportView(tbLineas);
		
		
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 375, 550, 61);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JPanel pnNumeroRegistros = new JPanel();
		pnNumeroRegistros.setLayout(null);
		pnNumeroRegistros.setBackground(new Color(0, 51, 0));
		pnNumeroRegistros.setBounds(43, 11, 150, 38);
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
		btnExportar.setBounds(224, 18, 140, 23);
		pn2.add(btnExportar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setForeground(new Color(0, 51, 0));
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVolver.setBounds(400, 18, 104, 23);
		pn2.add(btnVolver);
		
		llenarTabla();
		calcularCantidadItems();
	}
	
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbLineas() {
		modeloTbLineas.addColumn("Codigo");
		modeloTbLineas.addColumn("Linea");
		modeloTbLineas.addColumn("N° Articulos");
		modeloTbLineas.addColumn("Estado");
		
		tbLineas.setModel(modeloTbLineas);
	}
	
	//Metodo para llenar la tabla cuando recibe como parametro una lista de lineas articulos
	private void llenarTabla() {

		DelegadoArticulo delegadoArticulo = new DelegadoArticulo();
		DelegadoLineaArticulos delegadoLineaArticulos = new DelegadoLineaArticulos();
		
		listaLineasArticulos = delegadoLineaArticulos.listarLineaArticulos();
		
		if(tbLineas.getModel().getColumnCount()==0){
			llenarColumnasTbLineas();
		}
		
		String [] fila = new String[modeloTbLineas.getColumnCount()];
		
		for(LineaArticulos lineasArticulos : listaLineasArticulos){
			
			fila[0]= String.valueOf(lineasArticulos.getCodigo());
			fila[1]= lineasArticulos.getNombreL();
			fila[2]= limpiarCorchetes(delegadoArticulo.contarArticulosPorLineaArticulos(lineasArticulos.getNombreL()).toString());
			fila[3]= lineasArticulos.getEstado();
			
			modeloTbLineas.addRow(fila);
		}
		
		tbLineas.setModel(modeloTbLineas);
	}

	//Metodo que permite calcular la cantidad de registros
	private void calcularCantidadItems() {
		
		int cantidad = tbLineas.getRowCount();
		txtNumeroRegistros.setText(Integer.toString(cantidad));
		
	}
	
	//Metodo para mostrar el popup en la tabla	
	private void mostrarPopupEnTabla(){
		JPopupMenu pmLineas = new JPopupMenu();
		JMenuItem miModificar = new JMenuItem("Modificar");
		
		pmLineas.add(miModificar);
		miModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarLineaArticuloParaModificar();
			}
		});
			
		tbLineas.setComponentPopupMenu(pmLineas);	
	}
	
	//Metodo para enviar los datos del articulo a la ventana modificar articulo
	private void enviarLineaArticuloParaModificar() {
		VentModificarLineaArticulo ventModificarLineaArticulo = new VentModificarLineaArticulo();
		LineaArticulos lineaElegida = listaLineasArticulos.get(filaSeleccionada);
		ventModificarLineaArticulo.agregarDatos(lineaElegida);
		ventModificarLineaArticulo.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
            	limpiarTabla();
            	llenarTabla();
            }
        });
		ventModificarLineaArticulo.setVisible(true);
		
	}
		
	//Metodo para limpiar la tabla 
	private void limpiarTabla(){
       for (int i = 0; i < tbLineas.getRowCount(); i++) {
    	   modeloTbLineas.removeRow(i);
           i-=1;
       }
	}
	
	//Metodo para convertir un numero en formato de pesos y decimales en un numero corriente
	private String limpiarCorchetes(String numero){
		String numeroReemplazado=numero.replace("[", "");
		String numeroReemplazado1=numeroReemplazado.replace("]", "");
		return numeroReemplazado1;
	}
}
