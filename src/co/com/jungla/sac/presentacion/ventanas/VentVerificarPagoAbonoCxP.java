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
import javax.swing.JTextPane;

public class VentVerificarPagoAbonoCxP extends JDialog {

	private JPanel contentPane;
	private JTextField txtCxP;
	private JTextField txtAbono;
	private JTextField txtEgreso;
	private JTextField txtFechaPago;
	private JTextField txtSubtotal;
	private JTextField txtOtros;
	private JTextField txtTotalCxP;
	private JTextField txtDevoluciones;
	private JTextField txtAnticipos;
	private JTextField txtValorAbono;
	private JTextField txtSaldo;
	private JTextField txtFormaPago1;
	private JTextField txtFormaPago2;

	
	/**
	 * Create the frame.
	 */
	
	public VentVerificarPagoAbonoCxP(String idCxP, String idAbono, int idEgreso, String fechaPago, String subtotal, String otros, String totalCxP, String devoluciones, String anticipos, String pagoAbono, String saldo, String formaPago1, String formaPago2, String observaciones) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentVerificarPagoAbonoCxP.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Verificar los datos del Pago/Abono ");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 447);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 423, 396);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblProveedor = new JLabel("CxP N\u00B0");
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProveedor.setBounds(10, 27, 93, 14);
		panel.add(lblProveedor);
		
		JLabel lblFechaEntrega = new JLabel("Abono N\u00B0");
		lblFechaEntrega.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaEntrega.setBounds(10, 47, 93, 14);
		panel.add(lblFechaEntrega);
		
		JLabel lblItems = new JLabel("Egreso N\u00B0");
		lblItems.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblItems.setBounds(10, 67, 66, 14);
		panel.add(lblItems);
		
		JLabel lblSubtotal = new JLabel("Fecha Pago");
		lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSubtotal.setBounds(10, 87, 84, 14);
		panel.add(lblSubtotal);
		
		JLabel lblDcto = new JLabel("Subtotal");
		lblDcto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDcto.setBounds(10, 107, 84, 14);
		panel.add(lblDcto);
		
		JLabel lblTotalCompra = new JLabel("Otros");
		lblTotalCompra.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalCompra.setBounds(10, 127, 84, 14);
		panel.add(lblTotalCompra);
		
		txtCxP = new JTextField();
		txtCxP.setEditable(false);
		txtCxP.setBounds(107, 25, 306, 20);
		panel.add(txtCxP);
		txtCxP.setColumns(10);
		
		txtAbono = new JTextField();
		txtAbono.setEditable(false);
		txtAbono.setBounds(107, 45, 306, 20);
		panel.add(txtAbono);
		txtAbono.setColumns(10);
		
		txtEgreso = new JTextField();
		txtEgreso.setEditable(false);
		txtEgreso.setBounds(107, 65, 306, 20);
		panel.add(txtEgreso);
		txtEgreso.setColumns(10);
		
		txtFechaPago = new JTextField();
		txtFechaPago.setEditable(false);
		txtFechaPago.setBounds(107, 85, 306, 20);
		panel.add(txtFechaPago);
		txtFechaPago.setColumns(10);
		
		txtSubtotal = new JTextField();
		txtSubtotal.setEditable(false);
		txtSubtotal.setBounds(107, 105, 306, 20);
		panel.add(txtSubtotal);
		txtSubtotal.setColumns(10);
		
		txtOtros = new JTextField();
		txtOtros.setEditable(false);
		txtOtros.setBounds(107, 125, 306, 20);
		panel.add(txtOtros);
		txtOtros.setColumns(10);
		
		JLabel lblFacturaProv = new JLabel("Total CxP");
		lblFacturaProv.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFacturaProv.setBounds(10, 147, 84, 14);
		panel.add(lblFacturaProv);
		
		txtTotalCxP = new JTextField();
		txtTotalCxP.setEditable(false);
		txtTotalCxP.setBounds(107, 145, 306, 20);
		panel.add(txtTotalCxP);
		txtTotalCxP.setColumns(10);
		
		JLabel lblOrdenCompra = new JLabel("Devoluciones");
		lblOrdenCompra.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrdenCompra.setBounds(10, 167, 84, 14);
		panel.add(lblOrdenCompra);
		
		txtDevoluciones = new JTextField();
		txtDevoluciones.setEditable(false);
		txtDevoluciones.setBounds(107, 165, 306, 20);
		panel.add(txtDevoluciones);
		txtDevoluciones.setColumns(10);
		
		JLabel lblCuentaXPagar = new JLabel("Anticipos");
		lblCuentaXPagar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCuentaXPagar.setBounds(10, 187, 93, 14);
		panel.add(lblCuentaXPagar);
		
		txtAnticipos = new JTextField();
		txtAnticipos.setEditable(false);
		txtAnticipos.setBounds(107, 185, 306, 20);
		panel.add(txtAnticipos);
		txtAnticipos.setColumns(10);
		
		JLabel lblNCompra = new JLabel("Valor Abono");
		lblNCompra.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNCompra.setBounds(10, 207, 84, 14);
		panel.add(lblNCompra);
		
		txtValorAbono = new JTextField();
		txtValorAbono.setEditable(false);
		txtValorAbono.setBounds(107, 205, 306, 20);
		panel.add(txtValorAbono);
		txtValorAbono.setColumns(10);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 346, 403, 2);
		panel.add(sp);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.setBounds(263, 359, 106, 23);
		panel.add(btnCerrar);
		
		txtSaldo = new JTextField();
		txtSaldo.setText("<dynamic>");
		txtSaldo.setEditable(false);
		txtSaldo.setColumns(10);
		txtSaldo.setBounds(107, 225, 306, 20);
		panel.add(txtSaldo);
		
		JLabel lblEgreso = new JLabel("Saldo");
		lblEgreso.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEgreso.setBounds(10, 227, 84, 14);
		panel.add(lblEgreso);
		
		JLabel lblFormaPago = new JLabel("Forma Pago 1");
		lblFormaPago.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFormaPago.setBounds(10, 247, 84, 14);
		panel.add(lblFormaPago);
		
		txtFormaPago1 = new JTextField();
		txtFormaPago1.setText("<dynamic>");
		txtFormaPago1.setEditable(false);
		txtFormaPago1.setColumns(10);
		txtFormaPago1.setBounds(107, 245, 306, 20);
		panel.add(txtFormaPago1);
		
		JLabel lblFormaPago_1 = new JLabel("Forma Pago 2");
		lblFormaPago_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFormaPago_1.setBounds(10, 267, 84, 14);
		panel.add(lblFormaPago_1);
		
		txtFormaPago2 = new JTextField();
		txtFormaPago2.setText("<dynamic>");
		txtFormaPago2.setEditable(false);
		txtFormaPago2.setColumns(10);
		txtFormaPago2.setBounds(107, 265, 306, 20);
		panel.add(txtFormaPago2);
		
		JLabel label_2 = new JLabel("Observaciones");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(10, 287, 93, 14);
		panel.add(label_2);
		
		JTextPane txpObservaciones = new JTextPane();
		txpObservaciones.setText("<dynamic>");
		txpObservaciones.setBackground(Color.GRAY);
		txpObservaciones.setBounds(107, 286, 306, 49);
		panel.add(txpObservaciones);
		
		txtCxP.setText(idCxP);
		txtAbono.setText(idAbono);
		txtEgreso.setText(String.valueOf(idEgreso));
		txtFechaPago.setText(fechaPago);
		txtSubtotal.setText(subtotal);
		txtOtros.setText(otros);
		txtTotalCxP.setText(totalCxP);
		txtDevoluciones.setText(devoluciones);
		txtAnticipos.setText(anticipos);
		txtValorAbono.setText(pagoAbono);
		txtSaldo.setText(saldo);
		txtFormaPago1.setText(formaPago1);
		txtFormaPago2.setText(formaPago2);
		txpObservaciones.setText(observaciones);
		
		if(idEgreso == 0){
			JButton btnImprimirPagoAbono = new JButton("Imprimir Abono");
			btnImprimirPagoAbono.setForeground(new Color(0, 51, 0));
			btnImprimirPagoAbono.setBackground(Color.YELLOW);
			btnImprimirPagoAbono.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnImprimirPagoAbono.setBounds(53, 359, 141, 23);
			panel.add(btnImprimirPagoAbono);
		}else{
			JButton btnImprimirPagoAbono = new JButton("Imprimir Egreso");
			btnImprimirPagoAbono.setForeground(new Color(0, 51, 0));
			btnImprimirPagoAbono.setBackground(Color.YELLOW);
			btnImprimirPagoAbono.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnImprimirPagoAbono.setBounds(53, 359, 141, 23);
			panel.add(btnImprimirPagoAbono);
		}
			
	}
}
