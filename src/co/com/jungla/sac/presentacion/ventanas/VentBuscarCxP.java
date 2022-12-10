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

import co.com.jungla.sac.negocio.delegados.DelegadoCuentaXPagar;
import co.com.jungla.sac.negocio.delegados.DelegadoPagoAbonoCxP;
import co.com.jungla.sac.negocio.delegados.DelegadoProveedor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;


public class VentBuscarCxP extends JDialog {

	private JPanel contentPane;
	private JTable tbCxP;
	private DefaultTableModel modeloTbClientes = new DefaultTableModel();
	private List<CuentaXPagar> listaCuentaXPagar;
	private JTextField txtProveedor;
	private JTextField txtDocRef;
	private JButton btnBuscar;
	private JButton btnContinuar;
	private int filaSeleccionada;
	VentRegistrarPagoAbonoCxP ventRegistrarPagoAbonoCxP = new VentRegistrarPagoAbonoCxP();
	private DelegadoProveedor delegadoProveedor = new DelegadoProveedor();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentBuscarCxP frame = new VentBuscarCxP();
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
	public VentBuscarCxP() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentBuscarCxP.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Opciones de Busqueda de CXP");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 1049, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 1013, 64);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtProveedor.getText().equals("")&& txtDocRef.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Debe ingresar un valor valido en alguno de los campos de busqueda");
				}else{
					buscarCxP();
				}
				
			}
		});
		btnBuscar.setForeground(new Color(0, 51, 0));
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.setBounds(675, 21, 91, 23);
		pn1.add(btnBuscar);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setOpaque(true);
		lblProveedor.setBackground(new Color(153, 204, 153));
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProveedor.setBounds(18, 21, 100, 22);
		pn1.add(lblProveedor);
		
		JLabel lblDocRef = new JLabel("Doc. Referencia");
		lblDocRef.setOpaque(true);
		lblDocRef.setBackground(new Color(153, 204, 153));
		lblDocRef.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDocRef.setBounds(439, 21, 100, 22);
		pn1.add(lblDocRef);
		
		txtProveedor = new JTextField();
		txtProveedor.setBounds(120, 22, 295, 20);
		pn1.add(txtProveedor);
		txtProveedor.setColumns(10);
		
		txtDocRef = new JTextField();
		txtDocRef.setDocument(new LimitadorCaracteres());
		txtDocRef.setBounds(541, 22, 113, 20);
		pn1.add(txtDocRef);
		txtDocRef.setColumns(10);
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDatosAVentRegistrarPagoAbono();
			}
		});
		btnContinuar.setEnabled(false);
		btnContinuar.setForeground(new Color(0, 51, 0));
		btnContinuar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnContinuar.setBounds(776, 22, 108, 23);
		pn1.add(btnContinuar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setForeground(new Color(0, 51, 0));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelar.setBounds(894, 22, 101, 23);
		pn1.add(btnCancelar);
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 87, 1013, 321);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JScrollPane scrCxP = new JScrollPane();
		scrCxP.setBounds(2, 2, 1009, 316);
		pn2.add(scrCxP);
		
		tbCxP = new JTable();
		tbCxP.setEnabled(false);
		tbCxP.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbCxP.rowAtPoint(point);
		        tbCxP.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
			}
		});
		scrCxP.setViewportView(tbCxP);
		
		setModal(true);
		
		llenarColumnasTbOrdenesCompra();
	}
	
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbOrdenesCompra() {
		modeloTbClientes.addColumn("CxP");
		modeloTbClientes.addColumn("Factura");
		modeloTbClientes.addColumn("Proveedor");
		modeloTbClientes.addColumn("Identificación");
		modeloTbClientes.addColumn("Concepto");
		modeloTbClientes.addColumn("Causada");
		modeloTbClientes.addColumn("Fecha Pago");
		modeloTbClientes.addColumn("Dias");
		modeloTbClientes.addColumn("Total");
		
		tbCxP.setModel(modeloTbClientes);
	}
	
	//Metodo para listar las cuentas por cobrar de acuerdo a los parametros de proveedor y documento de referencia
	private void buscarCxP(){
		DelegadoCuentaXPagar delegadoCuentaXPagar = new DelegadoCuentaXPagar();
		
		if(!txtProveedor.getText().equals("") &&/*|| */!txtDocRef.getText().equals("")){
			listaCuentaXPagar= delegadoCuentaXPagar.traerCxPPorProveedorydocSoporte(txtProveedor.getText(), Integer.parseInt(txtDocRef.getText()));
			limpiarTabla();
			llenarTabla(listaCuentaXPagar);
			habilitarBotonContinuar();
			
		}
		
		if(!txtProveedor.getText().equals("")){
			listaCuentaXPagar = delegadoCuentaXPagar.traerCxPPorProveedor(txtProveedor.getText());
			limpiarTabla();
			llenarTabla(listaCuentaXPagar);
			habilitarBotonContinuar();
		}
		
		if(!txtDocRef.getText().equals("")){

			listaCuentaXPagar = delegadoCuentaXPagar.traerCxPPorDocSoporte_1(txtDocRef.getText());
			limpiarTabla();
			llenarTabla(listaCuentaXPagar);
			habilitarBotonContinuar();
		}
	}
	
	//Metodo para habilitar el boton continuar cuando hayan datos en la tabla
	private void habilitarBotonContinuar() {
		if(this.tbCxP.getRowCount()==0 && this.tbCxP.getSelectedRow()==-1){
			btnContinuar.setEnabled(false);
		}else{
			btnContinuar.setEnabled(true);
		}
	}

	//Metodo para llenar la tabla cuando recibe como parametro una lista de cuentas por pagar
	private void llenarTabla( List<CuentaXPagar> listaCxP) {
		
		Object [] fila = new String[modeloTbClientes.getColumnCount()];
		
		
		for(CuentaXPagar cuentasCxP : listaCxP){
			fila[0]= String.valueOf(cuentasCxP.getIdCuentaXPagar());
			fila[1]= String.valueOf(cuentasCxP.getDocSoporte());
			fila[2]= delegadoProveedor.encontrarPorIdentificacion(String.valueOf(cuentasCxP.getIdentificacionProv())).getNombre();
			fila[3]= cuentasCxP.getIdentificacionProv();
			fila[4]= cuentasCxP.getConcepto();
			fila[5]= convertirDateAString(cuentasCxP.getFechaCausacion());
			fila[6]= convertirDateAString(cuentasCxP.getFechaPago());
			fila[7]= String.valueOf(calcularDiasPlazo(cuentasCxP.getFechaPago(), new Date()));		
			fila[8]= formatearNumero(cuentasCxP.getTotalPagar());;
			
			modeloTbClientes.addRow(fila);
		}
		
		tbCxP.setModel(modeloTbClientes);
	}

	//Metodo para convertir un numero corriente en formato de pesos y decimales
	private String formatearNumero(Double numero){
		NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es","CO"));
		return formato.format(numero); 
	}

	//Metodo que permite la conversion de un dato de tipo date a uno de tipo string
	public String convertirDateAString(Date strFecha){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(strFecha);
		
		return fecha;
	}
	//Metodo para limpiar la tabla 
	private void limpiarTabla(){
	       for (int i = 0; i < tbCxP.getRowCount(); i++) {
	           modeloTbClientes.removeRow(i);
	           i-=1;
	       }
	   }
	
	//Metodo para calcular los dias entre la fecha de pago y la actual
	private int calcularDiasPlazo(Date fechaPago, Date fechaActual){
		long diferencia_fechas = fechaPago.getTime() - fechaActual.getTime();
		long dias = diferencia_fechas/(1000*60*60*24);
		return (int)dias;
	}
	//Metodo para cargar los datos encontrados de la cuenta por pagar y ponerlos en la ventana de pago abono
	private void cargarDatosAVentRegistrarPagoAbono(){
		CuentaXPagar datosCxP= listaCuentaXPagar.get(filaSeleccionada);
		DelegadoPagoAbonoCxP delegadoPagoAbonoCxP = new DelegadoPagoAbonoCxP();
		List <PagoAbonoCxP> pagoAbonoCXP= delegadoPagoAbonoCxP.listarPagoAbonoCxPPorCodigoCxP(datosCxP.getIdCuentaXPagar());
		ventRegistrarPagoAbonoCxP.setVisible(true);
		ventRegistrarPagoAbonoCxP.agregarDatosAPagoAbonoCxP(datosCxP.getIdCuentaXPagar(), datosCxP.getConcepto(), datosCxP.getDocSoporte(), datosCxP.getFechaCausacion(), datosCxP.getSubtotal(), datosCxP.getOtros(), datosCxP.getTotalPagar(), pagoAbonoCXP, datosCxP.getFechaPago(), delegadoProveedor.encontrarPorIdentificacion(String.valueOf(datosCxP.getIdentificacionProv())).getNombre(), datosCxP.getIdentificacionProv());
		dispose();
	}
}	
		
