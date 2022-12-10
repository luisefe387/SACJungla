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

import co.com.jungla.sac.persistencia.entidades.AnticipoCliente;

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


public class VentBuscarAnticipoCliente extends JDialog {

	private JPanel contentPane;
	private static JTable tbAnticipos;
	private DefaultTableModel modeloTbAnticipos = new DefaultTableModel();
	private List <AnticipoCliente> listaAnticipos;
	private JButton btnContinuar;
	private int filaSeleccionada;


	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 
	 */
	public VentBuscarAnticipoCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentBuscarAnticipoCliente.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
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
		tbAnticipos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbAnticipos.rowAtPoint(point);
		        tbAnticipos.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
			}
		});
		scrAnticipos.setViewportView(tbAnticipos);
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarAnticipoParaAgregar();
			}
		});
		btnContinuar.setForeground(new Color(0, 51, 0));
		btnContinuar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnContinuar.setBounds(123, 249, 89, 23);
		contentPane.add(btnContinuar);
		
		JLabel lblNota = new JLabel("Seleccionar uno o mas datos de la tabla.");
		lblNota.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNota.setBounds(10, 22, 427, 14);
		contentPane.add(lblNota);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.setBounds(222, 249, 89, 23);
		contentPane.add(btnCerrar);
		
		setModal(true);
		llenarColumnasTbArticulos();
		
	}
	
	//Metodo para enviar los datos del anticipo a la ventana venta de contado
	private void enviarAnticipoParaAgregar() {
		
		AnticipoCliente anticipoElegido = listaAnticipos.get(filaSeleccionada);
		VentVerificarVentaContado.agregarDatosAnticipoATbMediosPago(anticipoElegido);
		dispose();
		
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

	//Metodo para listar los datos de los anticipos de clientes
	public void agregarDatosAnticipos(List<AnticipoCliente> listaAnticipos){
		llenarTbAnticipos(listaAnticipos);
	}
	//Metodo para llenar la tabla de anticipos
	private void llenarTbAnticipos(List<AnticipoCliente> listaAnticipos) {
		
		this.listaAnticipos=listaAnticipos;
		
		String [] fila = new String[modeloTbAnticipos.getColumnCount()];
		
			for(AnticipoCliente anticipos : listaAnticipos ){
				if("Libre".equals(anticipos.getEstadoAnticipo())){
					fila[0]= String.valueOf(anticipos.getIdAnticipoCliente());
					fila[1]= formatearNumero(anticipos.getValorAnticipo());
					fila[2]= convertirDateAString(anticipos.getFechaAnticipo());
				
					modeloTbAnticipos.addRow(fila);
				}else{
					
				}
			}
			tbAnticipos.setModel(modeloTbAnticipos);
			habilitarBotonContinuar();
	}
	
	//Metodo para habilitar el boton continuar cuando hayan datos en la tabla
	private void habilitarBotonContinuar() {
		if(tbAnticipos.getRowCount()==0 && tbAnticipos.getSelectedRow()==-1){
			btnContinuar.setEnabled(false);
		}else{
			btnContinuar.setEnabled(true);
		}
	}
}
