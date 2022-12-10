package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JPanel;
import javax.swing.JLabel;


import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import co.com.jungla.sac.persistencia.entidades.DevolucionCliente;

import javax.swing.border.LineBorder;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;


public class VentBuscarDevolucionCliente extends JDialog {

	private JPanel contentPane;
	private static JTable tbDevoluciones;
	private DefaultTableModel modeloTbDevoluciones = new DefaultTableModel();
	private List <DevolucionCliente> listaDevoluciones;
	private JButton btnContinuar;
	private JButton btnCerrar;
	private int filaSeleccionada;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VentBuscarDevolucionCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentBuscarDevolucionCliente.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Busqueda de NOTAS CREDITO");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 501, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 45, 473, 188);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JScrollPane scrDevoluciones = new JScrollPane();
		scrDevoluciones.setBounds(2, 2, 468, 184);
		pn1.add(scrDevoluciones);
		
		tbDevoluciones = new JTable(modeloTbDevoluciones){
			//metodo que permite la no edicion de las columnas 3(Costo) y 4(Total)
			public boolean isCellEditable(int rowIndex, int colIndex) {
				if (colIndex==0 || colIndex==1 || colIndex==2 || colIndex==3) {
			        return false;
			    }
	            return false;
	        }
		};
		tbDevoluciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbDevoluciones.rowAtPoint(point);
		        tbDevoluciones.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
			}
		});
		scrDevoluciones.setViewportView(tbDevoluciones);
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarDevolucionParaAgregar();
			}
		});
		btnContinuar.setForeground(new Color(0, 51, 0));
		btnContinuar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnContinuar.setBounds(152, 244, 89, 23);
		contentPane.add(btnContinuar);
		
		JLabel lblNota = new JLabel("Seleccionar uno o mas datos de la tabla.");
		lblNota.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNota.setBounds(10, 22, 427, 14);
		contentPane.add(lblNota);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.setBounds(251, 244, 89, 23);
		contentPane.add(btnCerrar);
		
		setModal(true);
		
	}

	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbArticulos() {
		
		modeloTbDevoluciones.addColumn("Nota Crédito");
		modeloTbDevoluciones.addColumn("Venta");
		modeloTbDevoluciones.addColumn("Valor");
		modeloTbDevoluciones.addColumn("Fecha Generada");
		
		tbDevoluciones.setModel(modeloTbDevoluciones);
	}

	//Metodo para convertir un numero corriente en formato de pesos y decimales
	private String formatearNumero(Double numero){
		NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es","CO"));
		return formato.format(numero); 
	}

	//Metodo para convertir una fecha de tipo date a una cadena
	public String convertirDateAString(Date strFecha){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(strFecha);
		
		return fecha;
	}

	//Metodo para listar los datos de las devoluciones clientes
	public void agregarDatosDevoluciones(List<DevolucionCliente> listaDevoluciones){
		llenarTbDevoluciones(listaDevoluciones);
	}
	
	//Metodo para llenar la tabla de devoluciones
	private void llenarTbDevoluciones(List<DevolucionCliente> listaDevoluciones) {
		
		if(tbDevoluciones.getModel().getColumnCount()==0){
			llenarColumnasTbArticulos();
		}
		
		this.listaDevoluciones=listaDevoluciones;
		
		String [] fila = new String[modeloTbDevoluciones.getColumnCount()];
		
			for(DevolucionCliente devoluciones : listaDevoluciones){
				if("Pendiente".equals(devoluciones.getEstado())){
					fila[0]= String.valueOf(devoluciones.getIdDevolucionCliente());
					fila[1]= String.valueOf(devoluciones.getConcepto());
					fila[2]= formatearNumero((devoluciones.getTotalDevolucion()));
					fila[3]= convertirDateAString(devoluciones.getFecha());
				
					modeloTbDevoluciones.addRow(fila);
				}else{
					
				}
			}
			tbDevoluciones.setModel(modeloTbDevoluciones);
			habilitarBotonContinuar();
		
	}

	//Metodo para enviar los datos de la devolucion a la ventana venta de contado
	private void enviarDevolucionParaAgregar() {
		DevolucionCliente devolucionElegida = listaDevoluciones.get(filaSeleccionada);
		VentVerificarVentaContado.agregarDatosDevolucionATbMediosPago(devolucionElegida);
		dispose();
	}
	
	//Metodo para habilitar el boton continuar cuando hayan datos en la tabla
	private void habilitarBotonContinuar() {
		if(tbDevoluciones.getRowCount()==0 && tbDevoluciones.getSelectedRow()==-1){
			btnContinuar.setEnabled(false);
		}else{
			btnContinuar.setEnabled(true);
		}
	}
}
