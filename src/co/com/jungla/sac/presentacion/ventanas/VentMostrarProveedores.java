package co.com.jungla.sac.presentacion.ventanas;


import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Color;
import java.util.List;

import co.com.jungla.sac.persistencia.entidades.Proveedor;

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

public class VentMostrarProveedores extends JDialog {

	private JPanel contentPane;
	private JTable tbProveedores;
	private JTextField txtNumeroRegistros;
	private DefaultTableModel modeloTbProveedores = new DefaultTableModel();
	private List<Proveedor> listaProveedores;
	
	private int filaSeleccionada;


	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public VentMostrarProveedores(List <Proveedor> listaProveedores) {
		setTitle("Listado de Proveedores");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 1279, 475);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 1245, 353);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JScrollPane scrProveedores = new JScrollPane();
		scrProveedores.setToolTipText("");
		scrProveedores.setBounds(2, 2, 1241, 349);
		pn1.add(scrProveedores);
		
		tbProveedores = new JTable();
		tbProveedores.setToolTipText("Puede seleccionar cualquiera de las filas y presionar clic derecho para ver las acciones que puede realizar.");
		tbProveedores.setEnabled(false);
		tbProveedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbProveedores.rowAtPoint(point);
		        tbProveedores.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		        mostrarPopupEnTabla();
			}
		});
		scrProveedores.setViewportView(tbProveedores);
		
		
		
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
		
		this.listaProveedores = listaProveedores;
		
		llenarTabla(this.listaProveedores);
		calcularCantidadItems();
		
		setModal(true);
		
	}
	
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbProveedores() {
		modeloTbProveedores.addColumn("Identificación");
		modeloTbProveedores.addColumn("Tipo");
		modeloTbProveedores.addColumn("Razón Social");
		modeloTbProveedores.addColumn("Nombre");
		modeloTbProveedores.addColumn("Regimen");
		modeloTbProveedores.addColumn("Tipo Proveedor");
		modeloTbProveedores.addColumn("Departamento");
		modeloTbProveedores.addColumn("Ciudad");
		modeloTbProveedores.addColumn("Dirección");
		modeloTbProveedores.addColumn("Telefono");
		modeloTbProveedores.addColumn("Celular");
		modeloTbProveedores.addColumn("Email");
		modeloTbProveedores.addColumn("Web");
		modeloTbProveedores.addColumn("Observaciones");
		modeloTbProveedores.addColumn("Estado");
		
		tbProveedores.setModel(modeloTbProveedores);
	}
	
	//Metodo para llenar la tabla cuando recibe como parametro una lista de proveedores
	private void llenarTabla( List <Proveedor> listaProveedores) {

		if(tbProveedores.getModel().getColumnCount()==0){
			llenarColumnasTbProveedores();
		}
		
		String [] fila = new String[modeloTbProveedores.getColumnCount()];
		
		for(Proveedor proveedores : listaProveedores){
			
			fila[0]= proveedores.getIdentificacion();
			fila[1]= proveedores.getTipoPersona();
			fila[2]= proveedores.getAbreviatura();
			fila[3]= proveedores.getNombre();
			fila[4]= proveedores.getRegimen();
			fila[5]= proveedores.getTipoProveedor();
			fila[6]= proveedores.getMunicipio().getDepartamento().getNombre();
			fila[7]= proveedores.getMunicipio().getNombre();
			fila[8]= proveedores.getDireccion();
			fila[9]= proveedores.getTelefono();
			fila[10]= proveedores.getCelular();
			fila[11]= proveedores.getEmail();
			fila[12]= proveedores.getPaginaWeb();
			fila[13]= proveedores.getObservaciones();
			fila[14]= proveedores.getEstado();
			
			modeloTbProveedores.addRow(fila);
		}
		
		tbProveedores.setModel(modeloTbProveedores);
	}
	
	//Metodo que permite calcular la cantidad de registros
	private void calcularCantidadItems() {
		
		int cantidad = tbProveedores.getRowCount();
		txtNumeroRegistros.setText(Integer.toString(cantidad));
		
	}
	
	//Metodo para mostrar el popup en la tabla	
	private void mostrarPopupEnTabla(){
		JPopupMenu pmProveedores = new JPopupMenu();
		JMenuItem miModificar = new JMenuItem("Modificar");
		
		pmProveedores.add(miModificar);
		miModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarProveedorParaModificar();
			}
		});
			
		tbProveedores.setComponentPopupMenu(pmProveedores);	
	}
	
	//Metodo para enviar los datos del proveedor a la ventana modificar proveedor
	private void enviarProveedorParaModificar() {
		VentModificarProveedor ventModificarProveedor = new VentModificarProveedor();
		Proveedor proveedorElegido = listaProveedores.get(filaSeleccionada);
		ventModificarProveedor.agregarDatos(proveedorElegido);
		ventModificarProveedor.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
            	limpiarTabla();
            	llenarTabla(listaProveedores);
            }
        });
		ventModificarProveedor.setVisible(true);
		
	}
	
	//Metodo para limpiar la tabla 
	private void limpiarTabla(){
	       for (int i = 0; i < tbProveedores.getRowCount(); i++) {
	    	   modeloTbProveedores.removeRow(i);
	           i-=1;
	       }
	 }
}
