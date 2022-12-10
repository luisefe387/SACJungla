package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.SystemColor;
import java.awt.Color;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.com.jungla.sac.negocio.delegados.DelegadoArticulo;
import co.com.jungla.sac.negocio.delegados.DelegadoControlInventario;
import co.com.jungla.sac.negocio.delegados.DelegadoLineaArticulos;
import co.com.jungla.sac.persistencia.entidades.Articulo;
import co.com.jungla.sac.persistencia.entidades.ControlInventario;
import co.com.jungla.sac.persistencia.entidades.LineaArticulos;

import javax.swing.border.LineBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo la visualización del catalogo de articulos con foto.
 * @author Luis Fernando Pedroza T.
 * @version: 21/09/2016
 */
public class VentMostrarCatalogoArticulos extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JComboBox cbLineaArticulos;
	private JComboBox cbOrdenarPor;
	private DefaultComboBoxModel modeloLineaArticulos = new DefaultComboBoxModel();

	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private DelegadoArticulo delegadoArticulo = new DelegadoArticulo();
	private List<Articulo> listaArticulos;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentMostrarCatalogoArticulos() {
		setTitle("Cat\u00E1logo de Articulos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 789, 113);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 759, 65);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JPanel pnLineaArticulo = new JPanel();
		pnLineaArticulo.setBounds(10, 11, 305, 38);
		pn1.add(pnLineaArticulo);
		pnLineaArticulo.setBackground(new Color(0, 51, 0));
		pnLineaArticulo.setLayout(null);
		
		cbLineaArticulos = new JComboBox();
		cbLineaArticulos.setBounds(0, 16, 305, 22);
		pnLineaArticulo.add(cbLineaArticulos);
		
		JLabel lblLineaArticulo = new JLabel("Linea de Articulo");
		lblLineaArticulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLineaArticulo.setBackground(SystemColor.desktop);
		lblLineaArticulo.setForeground(SystemColor.window);
		lblLineaArticulo.setBounds(94, 0, 120, 14);
		pnLineaArticulo.add(lblLineaArticulo);
		
		JButton btnCatalogo = new JButton("Ver Cat\u00E1logo");
		btnCatalogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCatalogo();
			}
		});
		btnCatalogo.setForeground(new Color(0, 51, 0));
		btnCatalogo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCatalogo.setBounds(607, 18, 117, 23);
		pn1.add(btnCatalogo);
		
		JPanel pnOrdenarPor = new JPanel();
		pnOrdenarPor.setLayout(null);
		pnOrdenarPor.setBackground(new Color(0, 51, 0));
		pnOrdenarPor.setBounds(325, 11, 250, 38);
		pn1.add(pnOrdenarPor);
		
		cbOrdenarPor = new JComboBox();
		cbOrdenarPor.setModel(new DefaultComboBoxModel(new String[] {"Nombre de la Linea", "Nombre del Articulo"}));
		cbOrdenarPor.setBounds(0, 16, 250, 22);
		pnOrdenarPor.add(cbOrdenarPor);
		
		JLabel lblOrdenarPor = new JLabel("Ordenar Por:");
		lblOrdenarPor.setForeground(Color.WHITE);
		lblOrdenarPor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrdenarPor.setBackground(Color.BLACK);
		lblOrdenarPor.setBounds(94, 0, 86, 14);
		pnOrdenarPor.add(lblOrdenarPor);
		
		//Metodos que debe ejecutar la ventana al inicializarse
		listarLineaArticulos();
	}
	
	//Metodo que permite listar las lineas de articulos y desplegarlos en un combo box
	public void listarLineaArticulos() {
		DelegadoLineaArticulos delegadoLineaArticulos = new DelegadoLineaArticulos();
		List<LineaArticulos> lineaArticulos = delegadoLineaArticulos.listarLineaArticulos();
		modeloLineaArticulos.addElement("--TODAS LAS LINEAS--");
		cbLineaArticulos.setModel(modeloLineaArticulos);
		
		for(LineaArticulos lineaArticulos1 : lineaArticulos){
			modeloLineaArticulos.addElement(new LineaArticulos(lineaArticulos1.getNombreL(), lineaArticulos1.getCodigo()));
			cbLineaArticulos.setModel(modeloLineaArticulos);
		}
	}
	
	//Metodo que permite consultar la informacion del catalogo de productos mediante los filtros de busqueda
	private void verCatalogo() {
		
		if(cbLineaArticulos.getSelectedItem().equals("--TODAS LAS LINEAS--") && cbOrdenarPor.getSelectedItem().equals("Nombre de la Linea")){
			listaArticulos = delegadoArticulo.traerTodosArticulosYOrdenarPorNombreLinea();
			generarCatalogo(listaArticulos);
		}else{
			if(cbLineaArticulos.getSelectedItem().equals("--TODAS LAS LINEAS--") && cbOrdenarPor.getSelectedItem().equals("Nombre del Articulo")){
				listaArticulos = delegadoArticulo.traerTodosArticulosYOrdenarPorNombreArticulo();
				generarCatalogo(listaArticulos);
			}else{
				if(cbLineaArticulos.getSelectedItem()!="" && cbOrdenarPor.getSelectedItem().equals("Nombre de la Linea")){
					listaArticulos = delegadoArticulo.traerArticulosPorLineaYOrdenarPorNombreLinea(cbLineaArticulos.getSelectedItem().toString());
					generarCatalogo(listaArticulos);
				}else{
					listaArticulos = delegadoArticulo.traerArticulosPorLineaYOrdenarPorNombreArticulo(cbLineaArticulos.getSelectedItem().toString());
					generarCatalogo(listaArticulos);
				}
			}
		}

	}
	
	//Metodo  para generar las busquedas realizadas en un reporte
	private void generarCatalogo(List<Articulo> listaArticulos) {
	
	 DelegadoControlInventario delegadoControlInventario = new DelegadoControlInventario();
	 URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/catalogoArticulos.jasper");
	 List<ReporteCatalogoProductos> lista = new ArrayList<ReporteCatalogoProductos>();
	
	 
	 for(Articulo articulos : listaArticulos){
		 ControlInventario articuloInventario = delegadoControlInventario.traerContInventarioPorCodigoArticulo(articulos.getCodigo()).get(0);
		 ReporteCatalogoProductos catalogoArticulos = new ReporteCatalogoProductos(articulos.getCodigo(), articulos.getNombre(), articulos.getUnidadMedida().getNombre(), articulos.getPresentacion(), articulos.getDescripcion(), articulos.getReferencia(), articulos.getLineaArticulos().getNombreL(), this.getClass().getResourceAsStream(articuloInventario.getUrlFoto()), formatearNumero(articuloInventario.getPrecioTotalVenta()));
		 lista.add(catalogoArticulos);
	 }
        try {
            JasperReport reporte= (JasperReport) JRLoader.loadObject(ubicacion);
            
            JasperPrint jprint= JasperFillManager.fillReport(reporte, null,new JRBeanCollectionDataSource(lista));
            JasperViewer.viewReport(jprint,false);
        } catch (JRException ex) {
            Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	//Metodo para convertir un numero corriente en formato de pesos y decimales
	private String formatearNumero(Double numero){
		NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es","CO"));
		return formato.format(numero); 
	}
	
}
