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
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.SystemColor;

import co.com.jungla.sac.negocio.delegados.DelegadoArticulo;
import co.com.jungla.sac.negocio.delegados.DelegadoControlInventario;
import co.com.jungla.sac.negocio.delegados.DelegadoLineaArticulos;
import co.com.jungla.sac.negocio.delegados.DelegadoUnidadMedida;
import com.toedter.calendar.JDateChooser;

public class VentReportarBalanceGeneral extends JFrame {

	private JPanel contentPane;
	DefaultComboBoxModel modeloUnidadMedida = new DefaultComboBoxModel();
	DefaultComboBoxModel modeloLineaArticulos = new DefaultComboBoxModel();
	
	/**
	 * Create the frame.
	 */
	public VentReportarBalanceGeneral() {
		setTitle("Balance General");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 379, 193);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGenerarBalance = new JButton("Generar Balance");
		btnGenerarBalance.setForeground(new Color(0, 51, 0));
		btnGenerarBalance.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGenerarBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//generarBalanceGeneral();
			}
		});
		btnGenerarBalance.setBounds(20, 121, 127, 23);
		contentPane.add(btnGenerarBalance);
		
		JButton btnExportarBalance = new JButton("Exportar Balance a Excel");
		btnExportarBalance.setForeground(new Color(0, 51, 0));
		btnExportarBalance.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExportarBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExportarBalance.setBounds(157, 121, 204, 23);
		contentPane.add(btnExportarBalance);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 105, 353, 3);
		contentPane.add(sp);
		
		JPanel pnPeriodo = new JPanel();
		pnPeriodo.setLayout(null);
		pnPeriodo.setBackground(new Color(0, 51, 0));
		pnPeriodo.setBounds(85, 22, 198, 72);
		contentPane.add(pnPeriodo);
		
		JLabel lblPeriodo = new JLabel("Periodo");
		lblPeriodo.setForeground(Color.WHITE);
		lblPeriodo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPeriodo.setBackground(Color.BLACK);
		lblPeriodo.setBounds(77, 0, 78, 14);
		pnPeriodo.add(lblPeriodo);
		
		JDateChooser dchDesde = new JDateChooser();
		dchDesde.setBounds(49, 18, 144, 20);
		pnPeriodo.add(dchDesde);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setForeground(Color.WHITE);
		lblDesde.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDesde.setBackground(Color.BLACK);
		lblDesde.setBounds(8, 21, 50, 14);
		pnPeriodo.add(lblDesde);
		
		JDateChooser dchHasta = new JDateChooser();
		dchHasta.setBounds(49, 45, 144, 20);
		pnPeriodo.add(dchHasta);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setForeground(Color.WHITE);
		lblHasta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHasta.setBackground(Color.BLACK);
		lblHasta.setBounds(8, 48, 50, 14);
		pnPeriodo.add(lblHasta);
		
	}
	
	/*private void generarBalanceGeneral() {
		URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/balanceGeneral.jasper");
		List<ReporteDetalle> lista = new ArrayList<ReporteDetalle>();
		
        try {
            JasperReport reporte= (JasperReport) JRLoader.loadObject(ubicacion);
            Map<String, Object> parametros = new HashMap<String, Object>();
            
            parametros.put("fechaInicio", ultimaVentaArticulo.get(0).getIdVenta());
            parametros.put("fechaFinal", ultimaVentaArticulo.get(0).getClientes().getNombre());
            parametros.put("cajaYBancos", ultimaVentaArticulo.get(0).getClientes().getIdentificacion());
            parametros.put("clientes", ultimaVentaArticulo.get(0).getClientes().getDireccion());
            parametros.put("inventarios", ultimaVentaArticulo.get(0).getClientes().getTelefono());
            parametros.put("anticiposProveedores", ultimaVentaArticulo.get(0).getClientes().getMunicipio().getNombre());
            parametros.put("activosFijos", ultimaVentaArticulo.get(0).getFormaPagoCliente().getDescripcion());
            parametros.put("totalActivo", ultimaVentaArticulo.get(0).getVendedores().getNombre());
            parametros.put("proveedores", String.valueOf(ultimaVentaArticulo.get(0).getOrdCompra()));
            parametros.put("cuentasPorPagar", String.valueOf(ultimaVentaArticulo.get(0).getPedido()));
            parametros.put("anticiposClientes", formatearNumero(ultimaVentaArticulo.get(0).getSubtotal()));
            parametros.put("totalPasivo", formatearNumero(ultimaVentaArticulo.get(0).getDescuento()));
            parametros.put("capital", formatearNumero(ultimaVentaArticulo.get(0).getTotalVenta()));
            parametros.put("utilidadesAcumuladas", String.valueOf(ultimaVentaArticulo.get(0).getItems()));
            parametros.put("totalPatrimonio", convertirDateAString(ultimaVentaArticulo.get(0).getFechaGeneracion()));
            parametros.put("totalPasivoYpatrimonio", convertirDateAString(ultimaVentaArticulo.get(0).getFechaLimitePago()));
            
            JasperPrint jprint= JasperFillManager.fillReport(reporte, parametros,new JREmptyDataSource());
            JasperViewer.viewReport(jprint,false);
        } catch (JRException ex) {
            Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}*/
}
