package co.com.jungla.sac.presentacion.ventanas;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import co.com.jungla.sac.persistencia.entidades.DetalleCompra;
import co.com.jungla.sac.persistencia.entidades.DevolucionProveedor;
import co.com.jungla.sac.persistencia.entidades.OrdenCompraArticulos;
import co.com.jungla.sac.persistencia.entidades.PagoAbonoCxP;

import co.com.jungla.sac.negocio.delegados.DelegadoDevolucionProveedor;
import co.com.jungla.sac.negocio.delegados.DelegadoOrdenCompraArticulos;
import co.com.jungla.sac.negocio.delegados.DelegadoPagoAbonoCxP;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

//import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JFormattedTextField;


public class VentBuscarAbonosACxP extends JDialog {

	private JPanel contentPane;
	private JTable tbAbonos;
	private DefaultTableModel modeloTbAbonos = new DefaultTableModel();
	private List <DevolucionProveedor> codigoDevolucionProveedor;
	private int filaSeleccionada;
	private List <PagoAbonoCxP> listaPagoAbonoCxP;


	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VentBuscarAbonosACxP() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentBuscarAbonosACxP.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Abonos Aplicados a la CXP  ");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 678, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 45, 651, 188);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JScrollPane scrAbonos = new JScrollPane();
		scrAbonos.setBounds(2, 2, 646, 184);
		pn2.add(scrAbonos);
		
		tbAbonos = new JTable();
		tbAbonos.setEnabled(false);
		tbAbonos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbAbonos.rowAtPoint(point);
		        tbAbonos.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		        mostrarPopupEnTabla();
			}
		});
		scrAbonos.setViewportView(tbAbonos);
		
		JButton button = new JButton("Cerrar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setForeground(new Color(0, 51, 0));
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBounds(292, 244, 89, 23);
		contentPane.add(button);
		
		JLabel lblNota = new JLabel("Para ANULAR el ABONO debe dar CLIC DERECHO sobre el dato seleccionado.");
		lblNota.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNota.setBounds(10, 22, 651, 14);
		contentPane.add(lblNota);
		
		setModal(true);
		
		llenarColumnasTbArticulos();
	}

	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbArticulos() {
		
		modeloTbAbonos.addColumn("Estado");
		modeloTbAbonos.addColumn("Codigo");
		modeloTbAbonos.addColumn("Abono");
		modeloTbAbonos.addColumn("Fecha Pago");
		modeloTbAbonos.addColumn("Fecha generacion");
		modeloTbAbonos.addColumn("Forma Pago");
		
		tbAbonos.setModel(modeloTbAbonos);
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

	//Metodo para agregar los datos de la orden de compra a la compra
	public void agregarDatosAbonos(List<PagoAbonoCxP> listaPagoAbonoCxP){
		
		this.listaPagoAbonoCxP=listaPagoAbonoCxP;
		
		String [] fila = new String[modeloTbAbonos.getColumnCount()];
		String nl = System.getProperty("line.separator");
		
		for(PagoAbonoCxP abonos : listaPagoAbonoCxP ){
			
			fila[0]= abonos.getEstadoAnular();
			fila[1]= Integer.toString(abonos.getIdAbono());
			fila[2]= formatearNumero(abonos.getPagoAbono());
			fila[3]= convertirDateAString(abonos.getFechaPago());
			fila[4]= convertirDateAString(abonos.getFechaGeneracion());
			if(abonos.getMedioPagoProv2()==null){
				fila[5]= abonos.getMedioPagoProv1().getDescripcion();
			}else{
				fila[5]= abonos.getMedioPagoProv1().getDescripcion()+","+ nl+ abonos.getMedioPagoProv2().getDescripcion();
			}
			
			modeloTbAbonos.addRow(fila);
		}
		tbAbonos.setModel(modeloTbAbonos);
		
		colorearEstadoEnTabla(tbAbonos);
	}
	
	//Ventana para colorear las celdas de los estados pendiente (rojo) y entregado (verde)
	private void colorearEstadoEnTabla(JTable tablaOrdenes) {
		tablaOrdenes.getColumn("Estado").setCellRenderer(new DefaultTableCellRenderer(){ 
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){ 
			Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
			boolean valid = String.valueOf(table.getValueAt(row, 0)).equals("Anulado");
	        comp.setBackground(valid ? Color.getHSBColor(0.08f, 0.78f, 0.83f) : Color.getHSBColor(1.19f, 0.26f, 0.77f));
	        return comp; 
			}}); 
	}
	
	private void mostrarPopupEnTabla(){
		JPopupMenu pmAbonos = new JPopupMenu();
		JMenuItem miAnular = new JMenuItem("Anular Abono");
		
		PagoAbonoCxP datosAbonos= listaPagoAbonoCxP.get(filaSeleccionada);
		
		if(datosAbonos.getEstadoAnular().equals("Vigente")){
			pmAbonos.add(miAnular);
			miAnular.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					anularAbono();
				}
			});
			
			tbAbonos.setComponentPopupMenu(pmAbonos);	
		}else{
			
		}
	}
	
	private void anularAbono() {
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de anular este ABONO ?", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			DelegadoPagoAbonoCxP delegadoPagoAbonoCxP = new DelegadoPagoAbonoCxP();; 
			PagoAbonoCxP abonoAModificar = listaPagoAbonoCxP.get(filaSeleccionada);
			abonoAModificar.setEstadoAnular("Anulado");
			delegadoPagoAbonoCxP.actualizarPagoAbonoCxP(abonoAModificar);
			JOptionPane.showMessageDialog(null, "El abono ha sido anulado satisfactoriamente");
			limpiarTabla();
			agregarDatosAbonos(listaPagoAbonoCxP);
		}else{
		
		}
	}

	//Metodo para limpiar la tabla de articulos 
	private void limpiarTabla() {
		for (int i = 0; i < tbAbonos.getRowCount(); i++) {
	           modeloTbAbonos.removeRow(i);
	           i-=1;
	       }
	}
}
