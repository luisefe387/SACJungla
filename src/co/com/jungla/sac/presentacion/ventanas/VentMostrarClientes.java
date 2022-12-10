package co.com.jungla.sac.presentacion.ventanas;


import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import co.com.jungla.sac.persistencia.entidades.Cliente;
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

public class VentMostrarClientes extends JDialog {

	private JPanel contentPane;
	private JTable tbClientes;
	private JTextField txtNumeroRegistros;
	private DefaultTableModel modeloTbClientes = new DefaultTableModel();
	private List<Cliente> listaClientes;
	
	private int filaSeleccionada;


	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public VentMostrarClientes(List <Cliente> listaClientes) {
		setTitle("Listado de Proveedores");
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
		
		JScrollPane scrProveedores = new JScrollPane();
		scrProveedores.setBounds(2, 2, 1241, 349);
		pn1.add(scrProveedores);
		
		tbClientes = new JTable();
		tbClientes.setToolTipText("Puede seleccionar cualquiera de las filas y presionar clic derecho para ver las acciones que puede realizar.");
		tbClientes.setEnabled(false);
		tbClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbClientes.rowAtPoint(point);
		        tbClientes.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		        mostrarPopupEnTabla();
			}
		});
		scrProveedores.setViewportView(tbClientes);
		
		
		
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
		
		this.listaClientes = listaClientes;
		
		llenarTabla(this.listaClientes);
		calcularCantidadItems();
		
		setModal(true);
		
	}
	
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbArticulos() {
		modeloTbClientes.addColumn("Identificación");
		modeloTbClientes.addColumn("Tipo");
		modeloTbClientes.addColumn("Nombre");
		modeloTbClientes.addColumn("Contacto");
		modeloTbClientes.addColumn("Tipo Cliente");
		modeloTbClientes.addColumn("Nom. Comercial");
		modeloTbClientes.addColumn("Departamento");
		modeloTbClientes.addColumn("Ciudad");
		modeloTbClientes.addColumn("Dirección");
		modeloTbClientes.addColumn("Telefonos");
		modeloTbClientes.addColumn("Email");
		modeloTbClientes.addColumn("Web");
		modeloTbClientes.addColumn("Fecha Nac.");
		modeloTbClientes.addColumn("Observaciones");
		modeloTbClientes.addColumn("Estado");
		
		tbClientes.setModel(modeloTbClientes);
	}
	
	//Metodo para llenar la tabla cuando recibe como parametro una lista de proveedores
	private void llenarTabla( List <Cliente> listaClientes) {

		if(tbClientes.getModel().getColumnCount()==0){
			llenarColumnasTbArticulos();
		}
		
		String [] fila = new String[modeloTbClientes.getColumnCount()];
		
		for(Cliente clientes : listaClientes){
			
			fila[0]= clientes.getIdentificacion();
			fila[1]= clientes.getTipoPersona();
			fila[2]= clientes.getNombre();
			fila[3]= clientes.getContacto();
			fila[4]= clientes.getTipoClientes().getDescripcion();
			fila[5]= clientes.getNombreComercial();
			fila[6]= clientes.getMunicipio().getDepartamento().getNombre();
			fila[7]= clientes.getMunicipio().getNombre();
			fila[8]= clientes.getDireccion();
			fila[9]= clientes.getTelefono();
			fila[10]= clientes.getEmail();
			fila[11]= clientes.getPaginaWeb();
			fila[12]= convertirDateAString(clientes.getFechaNacimiento());
			fila[13]= clientes.getObservaciones();
			fila[14]= clientes.getEstado();
			
			modeloTbClientes.addRow(fila);
		}
		
		tbClientes.setModel(modeloTbClientes);
	}
	
	//Metodo que permite calcular la cantidad de registros
	private void calcularCantidadItems() {
		
		int cantidad = tbClientes.getRowCount();
		txtNumeroRegistros.setText(Integer.toString(cantidad));
		
	}
	
	//Metodo para mostrar el popup en la tabla	
	private void mostrarPopupEnTabla(){
		JPopupMenu pmClientes = new JPopupMenu();
		JMenuItem miModificar = new JMenuItem("Modificar");
		
		pmClientes.add(miModificar);
		miModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarClienteParaModificar();
			}
		});
			
		tbClientes.setComponentPopupMenu( pmClientes);	
	}
	
	//Metodo para enviar los datos del proveedor a la ventana modificar proveedor
	private void enviarClienteParaModificar() {
		VentModificarCliente ventModificarCliente = new VentModificarCliente();
		Cliente clienteElegido = listaClientes.get(filaSeleccionada);
		ventModificarCliente.agregarDatos(clienteElegido);
		ventModificarCliente.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
            	limpiarTabla();
            	llenarTabla(listaClientes);
            }
        });
		ventModificarCliente.setVisible(true);
		
	}
	
	//Metodo para limpiar la tabla 
	private void limpiarTabla(){
	       for (int i = 0; i < tbClientes.getRowCount(); i++) {
	    	   modeloTbClientes.removeRow(i);
	           i-=1;
	       }
	 }
	
	//Metodo para convertir una fecha de tipo date a una cadena
	public String convertirDateAString(Date strFecha){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(strFecha);
		
		return fecha;
	}
}
