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

public class VentMostrarEntradaOSalidaRegistrada extends JDialog {

	private JPanel contentPane;
	private JTextField txtItems;
	private JTextField txtCostoTotal;
	private JTextPane txpObservaciones;

	
	/**
	 * Create the frame.
	 */ 
	public VentMostrarEntradaOSalidaRegistrada(String items, String costoTotal, String observaciones, String concepto) {
		if("Entrada".equals(concepto)){
			setTitle("Verificar los datos de la Entrada");
		}else{
			setTitle("Verificar los datos de la Salida");
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 463, 219);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBounds(10, 11, 433, 170);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JLabel lblItems = new JLabel("Items");
		lblItems.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblItems.setBounds(10, 27, 93, 14);
		pn1.add(lblItems);
		
		JLabel lblCostoTotal = new JLabel("Costo Total");
		lblCostoTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCostoTotal.setBounds(10, 47, 87, 14);
		pn1.add(lblCostoTotal);
		
		txtItems = new JTextField();
		txtItems.setEditable(false);
		txtItems.setBounds(107, 25, 317, 20);
		pn1.add(txtItems);
		txtItems.setColumns(10);
		
		txtCostoTotal = new JTextField();
		txtCostoTotal.setEditable(false);
		txtCostoTotal.setBounds(107, 45, 317, 20);
		pn1.add(txtCostoTotal);
		txtCostoTotal.setColumns(10);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaciones.setBounds(10, 67, 93, 14);
		pn1.add(lblObservaciones);
		
		txpObservaciones = new JTextPane();
		txpObservaciones.setBackground(UIManager.getColor("Button.shadow"));
		txpObservaciones.setBounds(107, 65, 317, 49);
		pn1.add(txpObservaciones);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 125, 413, 2);
		pn1.add(sp);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setForeground(new Color(0, 51, 0));
		btnImprimir.setBackground(Color.YELLOW);
		btnImprimir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnImprimir.setBounds(53, 138, 117, 23);
		pn1.add(btnImprimir);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.setBounds(265, 138, 106, 23);
		pn1.add(btnCerrar);
		
		txtItems.setText(items);
		txtCostoTotal.setText(costoTotal);
		txpObservaciones.setText(observaciones);
	}
}
