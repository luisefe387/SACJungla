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

import co.com.jungla.sac.persistencia.entidades.AnticipoProveedor;

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


public class VentBuscarAnticipoProveedor extends JDialog {

	private JPanel contentPane;
	private static JTable tbAnticipos;
	private DefaultTableModel modeloTbAnticipos = new DefaultTableModel();
	private static int[] filasSeleccionadas; 
	private List <AnticipoProveedor> listaAnticipos;
	private JButton btnContinuar;


	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param modal 
	 * @param ventRegistrarPagoAbonoCxP 
	 */
	public VentBuscarAnticipoProveedor(VentRegistrarPagoAbonoCxP ventRegistrarPagoAbonoCxP, boolean modal) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentBuscarAnticipoProveedor.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Busqueda de ANTICIPOS");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 455, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 45, 426, 188);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JScrollPane scrAnticipos = new JScrollPane();
		scrAnticipos.setBounds(2, 2, 421, 184);
		pn1.add(scrAnticipos);
		
		tbAnticipos = new JTable(modeloTbAnticipos){
			//metodo que permite la no edicion de las columnas 3(Costo) y 4(Total)
			public boolean isCellEditable(int rowIndex, int colIndex) {
				if (colIndex==0 || colIndex==1 || colIndex==2) {
			        return false;
			    }
	            return false;
	        }
		};
		tbAnticipos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); 
		tbAnticipos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

		    //El método valueChange se debe sobreescribir obligatoriamente. 
		    //Se ejecuta automáticamente cada vez que se hace una nueva selección. 
		    @Override 
		    public void valueChanged(ListSelectionEvent e) { 
		        //Obtener el número de filas seleccionadas 
		    	filasSeleccionadas = tbAnticipos.getSelectedRows();
		    } 
		});

		scrAnticipos.setViewportView(tbAnticipos);
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistrarPagoAbonoCxP.txtAnticipos.setText(formatearNumero(calcularTotalAnticipo()));
				listarIdAnticiposSeleccionados();
				dispose();
			}
		});
		btnContinuar.setForeground(new Color(0, 51, 0));
		btnContinuar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnContinuar.setBounds(183, 248, 89, 23);
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
		
		modeloTbAnticipos.addColumn("Anticipo");
		modeloTbAnticipos.addColumn("Valor");
		modeloTbAnticipos.addColumn("Fecha");
		
		tbAnticipos.setModel(modeloTbAnticipos);
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

	//Metodo para listar los datos de los anticipos del proveedores
	public void agregarDatosAnticipos(List<AnticipoProveedor> listaAnticipos){
		llenarTbAnticipos(listaAnticipos);
	}
	//Metodo para llenar la tabla de anticipos
	private void llenarTbAnticipos(List<AnticipoProveedor> listaAnticipos) {
		
		this.listaAnticipos=listaAnticipos;
		
		String [] fila = new String[modeloTbAnticipos.getColumnCount()];
		
			for(AnticipoProveedor anticipos : listaAnticipos ){
				if("Contado".equals(anticipos.getEstadoAnticipo())){
					fila[0]= String.valueOf(anticipos.getIdAnticipoProveedor());
					fila[1]= formatearNumero(anticipos.getValorAnticipo());
					fila[2]= convertirDateAString(anticipos.getFechaAnticipo());
				
					modeloTbAnticipos.addRow(fila);
				}else{
					
				}
			}
			tbAnticipos.setModel(modeloTbAnticipos);
			habilitarBotonContinuar();
	}

	//Metodo para calcular el total de los anticipos del proveedor
	private double calcularTotalAnticipo(){
		
		double totalAnticipo= 0;
		try{
			for( int i = 0; i < filasSeleccionadas.length; i++){
				String valoresSeleccionados = desformatearNumero((String) tbAnticipos.getValueAt(filasSeleccionadas[i], 1));
				totalAnticipo += Double.parseDouble(valoresSeleccionados);
			}
			
		}catch(NullPointerException nl){
			nl.getMessage();
		}
		return totalAnticipo;
	}
	//Listar los idAnticipos y agregar a una lista estatica para ser utilizados por la ventana registrar pago abono
	public static ArrayList<String> listarIdAnticiposSeleccionados(){
		ArrayList<String> idAnticiposSeleccionados = new ArrayList<String>();
		idAnticiposSeleccionados.clear();
		for( int i = 0; i < filasSeleccionadas.length; i++){
			idAnticiposSeleccionados.add(tbAnticipos.getValueAt(filasSeleccionadas[i], 0).toString());
		}
		return idAnticiposSeleccionados;
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
		if(this.tbAnticipos.getRowCount()==0 && this.tbAnticipos.getSelectedRow()==-1){
			btnContinuar.setEnabled(false);
		}else{
			btnContinuar.setEnabled(true);
		}
	}
}
