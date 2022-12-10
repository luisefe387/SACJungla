package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JDialog;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  mostrar la informacion registrada del egreso
 * @author Luis Fernando Pedroza T.
 * @version: 21/09/2016
 */
public class VentMostrarEgresoRegistrado extends JDialog {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtEgreso;
	private JTextField txtProveedor;
	private JTextField txtConcepto;
	private JTextField txtDocSoporte;
	private JTextField txtFechaPago;
	private JTextField txtSubtotal;
	private JTextField txtOtros;
	private JTextField txtValorPagado;
	private JTextField txtFormaPago1;
	private JTextField txtFormaPago2;
	private JTextPane txpObservaciones;

	/**
	 * Metodo constructor con los parametros necesarios para mostrar la informacion en la ventana del egreso registrado
	 */ 
	public VentMostrarEgresoRegistrado(String idGastoYEgreso, String proveedor, String concepto, String docSoporte, String fechaPago, String subtotal, String otros, String totalPagado, String observaciones, String formaPago1, String formaPago2) {
		setTitle("Verificar los datos del Egreso");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 463, 386);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBounds(10, 11, 433, 337);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JLabel lblEgreso = new JLabel("Egreso N\u00B0");
		lblEgreso.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEgreso.setBounds(10, 27, 93, 14);
		pn1.add(lblEgreso);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProveedor.setBounds(10, 47, 87, 14);
		pn1.add(lblProveedor);
		
		JLabel lblConcepto = new JLabel("Concepto");
		lblConcepto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConcepto.setBounds(10, 67, 66, 14);
		pn1.add(lblConcepto);
		
		JLabel lblDocSoporte = new JLabel("Doc. Soporte");
		lblDocSoporte.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDocSoporte.setBounds(10, 87, 87, 14);
		pn1.add(lblDocSoporte);
		
		JLabel lblFechaPago = new JLabel("Fecha Pago");
		lblFechaPago.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaPago.setBounds(10, 107, 84, 14);
		pn1.add(lblFechaPago);
		
		txtEgreso = new JTextField();
		txtEgreso.setEditable(false);
		txtEgreso.setBounds(107, 25, 317, 20);
		pn1.add(txtEgreso);
		txtEgreso.setColumns(10);
		
		txtProveedor = new JTextField();
		txtProveedor.setEditable(false);
		txtProveedor.setBounds(107, 45, 317, 20);
		pn1.add(txtProveedor);
		txtProveedor.setColumns(10);
		
		txtConcepto = new JTextField();
		txtConcepto.setEditable(false);
		txtConcepto.setBounds(107, 65, 317, 20);
		pn1.add(txtConcepto);
		txtConcepto.setColumns(10);
		
		txtDocSoporte = new JTextField();
		txtDocSoporte.setEditable(false);
		txtDocSoporte.setBounds(107, 85, 317, 20);
		pn1.add(txtDocSoporte);
		txtDocSoporte.setColumns(10);
		
		txtFechaPago = new JTextField();
		txtFechaPago.setEditable(false);
		txtFechaPago.setBounds(107, 105, 317, 20);
		pn1.add(txtFechaPago);
		txtFechaPago.setColumns(10);
		
		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSubtotal.setBounds(10, 127, 84, 14);
		pn1.add(lblSubtotal);
		
		txtSubtotal = new JTextField();
		txtSubtotal.setEditable(false);
		txtSubtotal.setBounds(107, 125, 317, 20);
		pn1.add(txtSubtotal);
		txtSubtotal.setColumns(10);
		
		JLabel lblOtros = new JLabel("Otros");
		lblOtros.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOtros.setBounds(10, 147, 84, 14);
		pn1.add(lblOtros);
		
		txtOtros = new JTextField();
		txtOtros.setEditable(false);
		txtOtros.setBounds(107, 145, 317, 20);
		pn1.add(txtOtros);
		txtOtros.setColumns(10);
		
		JLabel lblValorPagado = new JLabel("Valor Pagado");
		lblValorPagado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValorPagado.setBounds(10, 167, 93, 14);
		pn1.add(lblValorPagado);
		
		txtValorPagado = new JTextField();
		txtValorPagado.setEditable(false);
		txtValorPagado.setBounds(107, 165, 317, 20);
		pn1.add(txtValorPagado);
		txtValorPagado.setColumns(10);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaciones.setBounds(10, 227, 93, 14);
		pn1.add(lblObservaciones);
		
		txpObservaciones = new JTextPane();
		txpObservaciones.setBackground(UIManager.getColor("Button.shadow"));
		txpObservaciones.setBounds(107, 225, 317, 49);
		pn1.add(txpObservaciones);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 285, 413, 2);
		pn1.add(sp);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setForeground(new Color(0, 51, 0));
		btnImprimir.setBackground(Color.YELLOW);
		btnImprimir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnImprimir.setBounds(53, 298, 117, 23);
		pn1.add(btnImprimir);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.setBounds(265, 298, 106, 23);
		pn1.add(btnCerrar);
		
		JLabel lblFormaPago1 = new JLabel("Forma Pago 1");
		lblFormaPago1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFormaPago1.setBounds(10, 187, 93, 14);
		pn1.add(lblFormaPago1);
		
		txtFormaPago1 = new JTextField();
		txtFormaPago1.setText("<dynamic>");
		txtFormaPago1.setEditable(false);
		txtFormaPago1.setColumns(10);
		txtFormaPago1.setBounds(107, 185, 317, 20);
		pn1.add(txtFormaPago1);
		
		JLabel lblFormaPago2 = new JLabel("Forma Pago 2");
		lblFormaPago2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFormaPago2.setBounds(10, 207, 93, 14);
		pn1.add(lblFormaPago2);
		
		txtFormaPago2 = new JTextField();
		txtFormaPago2.setText("<dynamic>");
		txtFormaPago2.setEditable(false);
		txtFormaPago2.setColumns(10);
		txtFormaPago2.setBounds(107, 205, 317, 20);
		pn1.add(txtFormaPago2);
		
		txtEgreso.setText(idGastoYEgreso);
		txtProveedor.setText(proveedor);
		txtConcepto.setText(concepto);
		txtDocSoporte.setText(docSoporte);
		txtFechaPago.setText(fechaPago);
		txtSubtotal.setText(subtotal);
		txtOtros.setText(otros);
		txtValorPagado.setText(totalPagado);
		txpObservaciones.setText(observaciones);
		txtFormaPago1.setText(formaPago1);
		txtFormaPago2.setText(formaPago2);
	}
}
