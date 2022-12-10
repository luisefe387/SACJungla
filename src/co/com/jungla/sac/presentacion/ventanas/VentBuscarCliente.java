package co.com.jungla.sac.presentacion.ventanas;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import co.com.jungla.sac.persistencia.entidades.Cliente;
import co.com.jungla.sac.persistencia.entidades.PagoAbonoCxP;
import co.com.jungla.sac.persistencia.entidades.CuentaXPagar;

import javax.swing.border.LineBorder;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import co.com.jungla.sac.negocio.delegados.DelegadoCliente;
import co.com.jungla.sac.negocio.delegados.DelegadoCuentaXPagar;
import co.com.jungla.sac.negocio.delegados.DelegadoPagoAbonoCxP;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;


public class VentBuscarCliente extends JDialog {

	private JPanel contentPane;
	private JTable tbClientes;
	private DefaultTableModel modeloTbClientes = new DefaultTableModel();
	private List<Cliente> listaClientes;
	private JTextField txtCliente;
	private JTextField txtIdentificacion;
	private JButton btnBuscar;
	private JButton btnContinuar;
	private int filaSeleccionada;
	VentRegistrarVentaArticulos ventRegistrarVentaArticulos = new VentRegistrarVentaArticulos();
	private List<Cliente> cliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentBuscarCliente frame = new VentBuscarCliente();
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
	public VentBuscarCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentBuscarCliente.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Opciones de Busqueda de Clientes");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 643, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 605, 112);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCliente.getText().equals("")&& txtIdentificacion.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Debe ingresar un valor valido en alguno de los campos de busqueda");
				}else{
					buscarCxP();
				}
				
			}
		});
		btnBuscar.setForeground(new Color(0, 51, 0));
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.setBounds(76, 77, 91, 23);
		pn1.add(btnBuscar);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setOpaque(true);
		lblCliente.setBackground(new Color(153, 204, 153));
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCliente.setBounds(18, 21, 100, 22);
		pn1.add(lblCliente);
		
		JLabel lblIdentificacion = new JLabel("Identificaci\u00F3n");
		lblIdentificacion.setOpaque(true);
		lblIdentificacion.setBackground(new Color(153, 204, 153));
		lblIdentificacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIdentificacion.setBounds(18, 44, 100, 22);
		pn1.add(lblIdentificacion);
		
		txtCliente = new JTextField();
		txtCliente.setBounds(120, 22, 458, 20);
		pn1.add(txtCliente);
		txtCliente.setColumns(10);
		
		txtIdentificacion = new JTextField();
		txtIdentificacion.setDocument(new LimitadorCaracteres());
		txtIdentificacion.setBounds(120, 45, 458, 20);
		pn1.add(txtIdentificacion);
		txtIdentificacion.setColumns(10);
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDatosAVentRegistrarVentaArticulos();
			}
		});
		btnContinuar.setEnabled(false);
		btnContinuar.setForeground(new Color(0, 51, 0));
		btnContinuar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnContinuar.setBounds(177, 78, 108, 23);
		pn1.add(btnContinuar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ventRegistrarVentaArticulos.setVisible(true);
			}
		});
		btnCancelar.setForeground(new Color(0, 51, 0));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelar.setBounds(426, 77, 101, 23);
		pn1.add(btnCancelar);
		
		JButton btnNuevoCliente = new JButton("Nuevo Cliente");
		btnNuevoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearCliente();

			}

		});
		btnNuevoCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNuevoCliente.setForeground(new Color(0, 51, 0));
		btnNuevoCliente.setBounds(295, 77, 121, 23);
		pn1.add(btnNuevoCliente);
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 134, 605, 321);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JScrollPane scrClientes = new JScrollPane();
		scrClientes.setBounds(2, 2, 601, 316);
		pn2.add(scrClientes);
		
		tbClientes = new JTable();
		tbClientes.setEnabled(false);
		tbClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbClientes.rowAtPoint(point);
		        tbClientes.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
			}
		});
		scrClientes.setViewportView(tbClientes);
		
		setModal(true);
		
		llenarColumnasTbOrdenesCompra();
	}
	
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbOrdenesCompra() {
		modeloTbClientes.addColumn("Identificacion");
		modeloTbClientes.addColumn("Cliente");
		modeloTbClientes.addColumn("Ciudad");
		
		tbClientes.setModel(modeloTbClientes);
	}
	
	//Metodo para listar los cliente de acuerdo a los parametros de nombre o identificacion
	private void buscarCxP(){
		DelegadoCliente delegadoCliente = new DelegadoCliente();
		
		if(!txtCliente.getText().equals("") &&/*|| */!txtIdentificacion.getText().equals("")){
			listaClientes= delegadoCliente.traerClientePorIdentificacionYNombre(txtCliente.getText(), txtIdentificacion.getText());
			limpiarTabla();
			llenarTabla(listaClientes);
			habilitarBotonContinuar();
			
		}
		
		if(!txtCliente.getText().equals("")){
			listaClientes = delegadoCliente.traerClientePorNombre(txtCliente.getText());
			limpiarTabla();
			llenarTabla(listaClientes);
			habilitarBotonContinuar();
		}
		
		if(!txtIdentificacion.getText().equals("")){

			listaClientes = delegadoCliente.traerClientePorIdentificacion(txtIdentificacion.getText());
			limpiarTabla();
			llenarTabla(listaClientes);
			habilitarBotonContinuar();
		}
	}
	
	//Metodo para habilitar el boton continuar cuando hayan datos en la tabla
	private void habilitarBotonContinuar() {
		if(this.tbClientes.getRowCount()==0 && this.tbClientes.getSelectedRow()==-1){
			btnContinuar.setEnabled(false);
		}else{
			btnContinuar.setEnabled(true);
		}
	}

	//Metodo para llenar la tabla cuando recibe como parametro una lista de cuentas por pagar
	private void llenarTabla( List<Cliente> listaClientes) {
		
		Object [] fila = new String[modeloTbClientes.getColumnCount()];
		
		for(Cliente clientes : listaClientes){
			fila[0]= String.valueOf(clientes.getIdentificacion());
			fila[1]= clientes.getNombre();
			fila[2]= clientes.getMunicipio().getNombre();
			
			modeloTbClientes.addRow(fila);
		}
		
		tbClientes.setModel(modeloTbClientes);
	}

	//Metodo que permite la conversion de un dato de tipo date a uno de tipo string
	public String convertirDateAString(Date strFecha){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(strFecha);
		
		return fecha;
	}
	//Metodo para limpiar la tabla 
	private void limpiarTabla(){
	       for (int i = 0; i < tbClientes.getRowCount(); i++) {
	           modeloTbClientes.removeRow(i);
	           i-=1;
	       }
	   }
	
	//Metodo para cargar los datos encontrados del cliente y ponerlos en la ventana de venta de articulos
	private void cargarDatosAVentRegistrarVentaArticulos(){
		/*CuentaXPagar datosCxP= listaCuentaXPagar.get(filaSeleccionada);
		DelegadoPagoAbonoCxP delegadoPagoAbonoCxP = new DelegadoPagoAbonoCxP();
		List <PagoAbonoCxP> pagoAbonoCXP= delegadoPagoAbonoCxP.listarPagoAbonoCxPPorCodigoCxP(datosCxP.getIdCuentaXPagar());
		ventRegistrarPagoAbonoCxP.setVisible(true);
		ventRegistrarPagoAbonoCxP.agregarDatosAPagoAbonoCxP(datosCxP.getIdCuentaXPagar(), datosCxP.getConcepto(), datosCxP.getDocSoporte(), datosCxP.getFechaCausacion(), datosCxP.getSubtotal(), datosCxP.getOtros(), datosCxP.getTotalPagar(), pagoAbonoCXP, datosCxP.getFechaPago(), datosCxP.getProveedores().getNombre(), Integer.parseInt(datosCxP.getProveedores().getIdentificacion()));
		dispose();*/
		
		Cliente datosCliente= listaClientes.get(filaSeleccionada);
		ventRegistrarVentaArticulos.setVisible(true);
		ventRegistrarVentaArticulos.agregarDatosAVentaArticulos(datosCliente.getIdentificacion(), datosCliente.getNombre());
		dispose();
	}
	//Metodo para crear cliente
	private void crearCliente() {
		dispose();
		VentRegistrarCliente ventRegistrarCliente = new VentRegistrarCliente();
		ventRegistrarCliente.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
            	DelegadoCliente delegadoCliente = new DelegadoCliente();
            	cliente = delegadoCliente.traerUltimoCliente();
            	ventRegistrarVentaArticulos.agregarDatosAVentaArticulos(cliente.get(0).getIdentificacion(), cliente.get(0).getNombre());
            	ventRegistrarVentaArticulos.setVisible(true);
            	//ventBuscarCliente.setVisible(true);  
            }
        });
		ventRegistrarCliente.setVisible(true);
	}
}	
		
