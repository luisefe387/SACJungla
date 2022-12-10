package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JPanel;
import javax.swing.JLabel;


import java.awt.Color;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import co.com.jungla.sac.persistencia.entidades.DevolucionProveedor;

import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class VentBuscarDevolucionProveedor extends JDialog {

	private JPanel contentPane;
	private static JTable tbDevoluciones;
	private DefaultTableModel modeloTbDevoluciones = new DefaultTableModel();
	private static int[] filasSeleccionadas; 
	private List <DevolucionProveedor> listaDevoluciones;
	private JButton btnContinuar;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VentBuscarDevolucionProveedor(VentRegistrarPagoAbonoCxP ventRegistrarPagoAbonoCxP, boolean modal) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentBuscarDevolucionProveedor.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Busqueda de NOTAS DEBITO");
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
		tbDevoluciones.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); 
		tbDevoluciones.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

		    //El método valueChange se debe sobreescribir obligatoriamente. 
		    //Se ejecuta automáticamente cada vez que se hace una nueva selección. 
		    @Override 
		    public void valueChanged(ListSelectionEvent e) { 
		        //Obtener el número de filas seleccionadas 
		    	filasSeleccionadas = tbDevoluciones.getSelectedRows();
		    } 
		});

		scrDevoluciones.setViewportView(tbDevoluciones);
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarPagoAbonoCxP.txtDevoluciones.setText(formatearNumero(calcularTotalDevolucion()));
				dispose();
			}
		});
		btnContinuar.setForeground(new Color(0, 51, 0));
		btnContinuar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnContinuar.setBounds(206, 244, 89, 23);
		contentPane.add(btnContinuar);
		
		JLabel lblNota = new JLabel("Seleccionar uno o mas datos de la tabla.");
		lblNota.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNota.setBounds(10, 22, 427, 14);
		contentPane.add(lblNota);
		
		setModal(modal);
		llenarColumnasTbArticulos();
		
	}

	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbArticulos() {
		
		modeloTbDevoluciones.addColumn("Nota Debito");
		modeloTbDevoluciones.addColumn("Compra");
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

	//Metodo para listar los datos de las devoluciones proveedores
	public void agregarDatosDevoluciones(List<DevolucionProveedor> listaDevoluciones){
		llenarTbDevoluciones(listaDevoluciones);
	}
	//Metodo para llenar la tabla de devoluciones
	private void llenarTbDevoluciones(List<DevolucionProveedor> listaDevoluciones) {
		
		this.listaDevoluciones=listaDevoluciones;
		
		String [] fila = new String[modeloTbDevoluciones.getColumnCount()];
		
			for(DevolucionProveedor devoluciones : listaDevoluciones){
				if("Pendiente".equals(devoluciones.getEstado())){
					fila[0]= String.valueOf(devoluciones.getIdDevolucionProveedor());
					fila[1]= String.valueOf(devoluciones.getCompraArticulos().getIdCompra());
					fila[2]= formatearNumero(devoluciones.getTotalDevolucion());
					fila[3]= convertirDateAString(devoluciones.getFecha());
				
					modeloTbDevoluciones.addRow(fila);
				}else{
					
				}
			}
			tbDevoluciones.setModel(modeloTbDevoluciones);
			habilitarBotonContinuar();
		
	}

	//Metodo para calcular el total de los anticipos del proveedor
	private double calcularTotalDevolucion(){
		
		double totalDevolucion= 0;
		try{
			for( int i = 0; i < filasSeleccionadas.length; i++){
				String valoresSeleccionados = desformatearNumero((String) tbDevoluciones.getValueAt(filasSeleccionadas[i], 2));
				totalDevolucion += Double.parseDouble(valoresSeleccionados);
			}
			
		}catch(NullPointerException nl){
			nl.getMessage();
		}
		return totalDevolucion;
	}
	
	//Metodo para convertir un numero en formato de pesos y decimales en un numero corriente
	private String desformatearNumero(String numero){
		String numeroReemplazado=numero.replace("$", "");
		String numeroReemplazado1=numeroReemplazado.replace(".", "");
		String numeroReemplazado2=numeroReemplazado1.replace(",", ".");
		return numeroReemplazado2;
	}
	
	//Metodo para habilitar el boton continuar cuando hayan datos en la tabla
	private void habilitarBotonContinuar() {
		if(tbDevoluciones.getRowCount()==0 && tbDevoluciones.getSelectedRow()==-1){
			btnContinuar.setEnabled(false);
		}else{
			btnContinuar.setEnabled(true);
		}
	}
	
	//Listar los idAnticipos y agregar a una lista estatica para ser utilizados por la ventana registrar pago abono
	public static ArrayList<String> listarIdDevolucionesSeleccionados(){
		ArrayList<String> idDevoucionesSeleccionados = new ArrayList<String>();
		idDevoucionesSeleccionados.clear();
		for( int i = 0; i < filasSeleccionadas.length; i++){
			idDevoucionesSeleccionados.add(tbDevoluciones.getValueAt(filasSeleccionadas[i], 0).toString());
		}
		return idDevoucionesSeleccionados;
	}
}
