package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import co.com.jungla.sac.persistencia.entidades.VentaArticulos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.LineBorder;

import co.com.jungla.sac.negocio.delegados.DelegadoVentaArticulos;
import com.toedter.calendar.JDateChooser;

public class VentModificarFechaLimite extends JFrame {

	private JPanel contentPane;
	DefaultComboBoxModel modeloUnidadMedida = new DefaultComboBoxModel();
	DefaultComboBoxModel modeloLineaArticulos = new DefaultComboBoxModel();
	private JDateChooser dchFechaLimite;
	private JButton btnContinuar;
	private DelegadoVentaArticulos delegadoVentaArticulos = new DelegadoVentaArticulos();
	private VentaArticulos ventaPendienteAModificar;
	
	/**
	 * Create the frame.
	 */
	public VentModificarFechaLimite(final int idVenta) {
		setTitle("Nueva Fecha Limite");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 220, 133);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Boton para registrar el articulo 
		btnContinuar = new JButton("Continuar");
		btnContinuar.setForeground(new Color(0, 51, 0));
		btnContinuar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarFechaLimite(idVenta);
				dispose();

			}
		});
		btnContinuar.setBounds(55, 65, 100, 23);
		contentPane.add(btnContinuar);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 52, 192, 2);
		contentPane.add(sp);
		
		dchFechaLimite = new JDateChooser();
		dchFechaLimite.setBounds(24, 21, 162, 20);
		contentPane.add(dchFechaLimite);
		
		ventaPendienteAModificar = delegadoVentaArticulos.traerVentaPorCodigo(idVenta).get(0);
		dchFechaLimite.setDate(ventaPendienteAModificar.getFechaLimitePago());
		
	}
	//Metodo para modificar la fecha limite de la venta elegida
	private void modificarFechaLimite(int idVenta) {
		
		ventaPendienteAModificar.setFechaLimitePago(dchFechaLimite.getDate());
		delegadoVentaArticulos.actualizarVentaArticulos(ventaPendienteAModificar);
		
	}

	
}
