package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;
import javax.swing.JDialog;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentVerificarOrdenCompra extends JDialog {

	private JPanel contentPane;
	private JTextField txtProveedor;
	private JTextField txtFechaEntregaE;
	private JTextField txtFechaPagoP;
	private JTextField txtItems;
	private JTextField txtTotalOC;
	VentRegistrarOrdenCompra ventRegistrarOrdenCompra = new VentRegistrarOrdenCompra();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VentVerificarOrdenCompra(String totalOC, String fechaEntregaE, String proveedor, String items, String fechaPagoP) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentVerificarOrdenCompra.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Verificar los datos de la Orden de Compra");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 488, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 460, 187);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProveedor.setBounds(10, 24, 106, 14);
		panel.add(lblProveedor);
		
		JLabel lblFechaEntregaE = new JLabel("Fecha Entrega Esperada");
		lblFechaEntregaE.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaEntregaE.setBounds(10, 44, 143, 14);
		panel.add(lblFechaEntregaE);
		
		JLabel lblFechaPagoP = new JLabel("Fecha Pago Pactado");
		lblFechaPagoP.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaPagoP.setBounds(10, 64, 130, 14);
		panel.add(lblFechaPagoP);
		
		JLabel lblItems = new JLabel("Items");
		lblItems.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblItems.setBounds(10, 84, 66, 14);
		panel.add(lblItems);
		
		JLabel lblTotalOC = new JLabel("Total OC");
		lblTotalOC.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalOC.setBounds(10, 104, 84, 14);
		panel.add(lblTotalOC);
		
		txtProveedor = new JTextField();
		txtProveedor.setEditable(false);
		txtProveedor.setBounds(151, 22, 281, 20);
		panel.add(txtProveedor);
		txtProveedor.setColumns(10);
		
		txtFechaEntregaE = new JTextField();
		txtFechaEntregaE.setEditable(false);
		txtFechaEntregaE.setBounds(151, 42, 281, 20);
		panel.add(txtFechaEntregaE);
		txtFechaEntregaE.setColumns(10);
		
		txtFechaPagoP = new JTextField();
		txtFechaPagoP.setEditable(false);
		txtFechaPagoP.setBounds(151, 62, 281, 20);
		panel.add(txtFechaPagoP);
		txtFechaPagoP.setColumns(10);
		
		txtItems = new JTextField();
		txtItems.setEditable(false);
		txtItems.setBounds(151, 82, 281, 20);
		panel.add(txtItems);
		txtItems.setColumns(10);
		
		txtTotalOC = new JTextField();
		txtTotalOC.setEditable(false);
		txtTotalOC.setBounds(151, 102, 281, 20);
		panel.add(txtTotalOC);
		txtTotalOC.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 51, 0));
		separator.setBounds(10, 133, 440, 2);
		panel.add(separator);
		
		JButton btnComprobante = new JButton("Imprimir");
		btnComprobante.setForeground(new Color(0, 51, 0));
		btnComprobante.setBackground(Color.YELLOW);
		btnComprobante.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnComprobante.setBounds(56, 146, 117, 23);
		panel.add(btnComprobante);
		
		JButton btnOtraCompra = new JButton("Cerrar");
		btnOtraCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOtraCompra.setForeground(new Color(0, 51, 0));
		btnOtraCompra.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOtraCompra.setBounds(287, 146, 117, 23);
		panel.add(btnOtraCompra);
		
		txtFechaEntregaE.setText(fechaEntregaE);
		txtProveedor.setText(proveedor);
		txtFechaPagoP.setText(fechaPagoP);
		txtTotalOC.setText(totalOC);
		txtItems.setText(items);
		
		setModal(true);
	}
	
	public void cerrar(){
		
	}
	/*//Metodo que permite asignar los datos enviados desde la ventana validar orden compra de articulos a la ventana para guardar la orden de compra
		public void setText(String totalOC, String fechaEntregaE, String proveedor, String items, String fechaPagoP){
			txtFechaEntregaE.setText(fechaEntregaE);
			txtProveedor.setText(proveedor);
			txtFechaPagoP.setText(fechaPagoP);
			txtTotalOC.setText(totalOC);
			txtItems.setText(items);
		}*/
}
