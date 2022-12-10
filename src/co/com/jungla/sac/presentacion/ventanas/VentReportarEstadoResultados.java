package co.com.jungla.sac.presentacion.ventanas;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;


import co.com.jungla.sac.persistencia.entidades.Articulo;
import co.com.jungla.sac.persistencia.entidades.ControlInventario;
import co.com.jungla.sac.persistencia.entidades.LineaArticulos;
import co.com.jungla.sac.persistencia.entidades.UnidadMedida;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.SystemColor;

import com.toedter.calendar.JDateChooser;

public class VentReportarEstadoResultados extends JFrame {

	private JPanel contentPane;
	DefaultComboBoxModel modeloUnidadMedida = new DefaultComboBoxModel();
	DefaultComboBoxModel modeloLineaArticulos = new DefaultComboBoxModel();
	
	/**
	 * Create the frame.
	 */
	public VentReportarEstadoResultados() {
		setTitle("Estado de Resultados (PyG)");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 379, 193);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Boton para registrar el articulo 
		JButton btnMostrarPyG = new JButton("Mostrar PyG");
		btnMostrarPyG.setForeground(new Color(0, 51, 0));
		btnMostrarPyG.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMostrarPyG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//mostrarPyG();
			}
		});
		btnMostrarPyG.setBounds(36, 121, 127, 23);
		contentPane.add(btnMostrarPyG);
		
		JButton btnExportarPyG = new JButton("Exportar PyG a Excel");
		btnExportarPyG.setForeground(new Color(0, 51, 0));
		btnExportarPyG.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExportarPyG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExportarPyG.setBounds(173, 121, 167, 23);
		contentPane.add(btnExportarPyG);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 105, 353, 3);
		contentPane.add(sp);
		
		JPanel pnRangoDiasPyG = new JPanel();
		pnRangoDiasPyG.setLayout(null);
		pnRangoDiasPyG.setBackground(new Color(0, 51, 0));
		pnRangoDiasPyG.setBounds(85, 22, 198, 72);
		contentPane.add(pnRangoDiasPyG);
		
		JLabel lblRangoDeDas = new JLabel("Rango de D\u00EDas del PyG");
		lblRangoDeDas.setForeground(Color.WHITE);
		lblRangoDeDas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRangoDeDas.setBackground(Color.BLACK);
		lblRangoDeDas.setBounds(37, 0, 144, 14);
		pnRangoDiasPyG.add(lblRangoDeDas);
		
		JDateChooser dchDesde = new JDateChooser();
		dchDesde.setBounds(49, 18, 144, 20);
		pnRangoDiasPyG.add(dchDesde);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setForeground(Color.WHITE);
		lblDesde.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDesde.setBackground(Color.BLACK);
		lblDesde.setBounds(8, 21, 50, 14);
		pnRangoDiasPyG.add(lblDesde);
		
		JDateChooser dchHasta = new JDateChooser();
		dchHasta.setBounds(49, 45, 144, 20);
		pnRangoDiasPyG.add(dchHasta);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setForeground(Color.WHITE);
		lblHasta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHasta.setBackground(Color.BLACK);
		lblHasta.setBounds(8, 48, 50, 14);
		pnRangoDiasPyG.add(lblHasta);
		
	}
	
	/*private void mostrarPyG() {
		URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/estadoResultados.jasper");
		List<ReporteDetalle> lista = new ArrayList<ReporteDetalle>();
		
        try {
            JasperReport reporte= (JasperReport) JRLoader.loadObject(ubicacion);
            Map<String, Object> parametros = new HashMap<String, Object>();
            
            parametros.put("fechaInicio", ultimaVentaArticulo.get(0).getIdVenta());
            parametros.put("fechaFinal", ultimaVentaArticulo.get(0).getClientes().getNombre());
            parametros.put("ventasTotales", ultimaVentaArticulo.get(0).getClientes().getIdentificacion());
            parametros.put("devolucionesVentas", ultimaVentaArticulo.get(0).getClientes().getDireccion());
            parametros.put("ventasNetas", ultimaVentaArticulo.get(0).getClientes().getTelefono());
            parametros.put("costoVentas", ultimaVentaArticulo.get(0).getClientes().getMunicipio().getNombre());
            parametros.put("utilidadBrutaVentas", ultimaVentaArticulo.get(0).getFormaPagoCliente().getDescripcion());
            parametros.put("gastosEnTotal", ultimaVentaArticulo.get(0).getVendedores().getNombre());
            parametros.put("utilidadNeta", String.valueOf(ultimaVentaArticulo.get(0).getOrdCompra()));
            
            JasperPrint jprint= JasperFillManager.fillReport(reporte, parametros,new JREmptyDataSource());
            JasperViewer.viewReport(jprint,false);
        } catch (JRException ex) {
            Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}*/
}
