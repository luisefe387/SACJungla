package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Toolkit;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.com.jungla.sac.persistencia.entidades.Articulo;
import co.com.jungla.sac.persistencia.entidades.Cliente;
import co.com.jungla.sac.persistencia.entidades.ControlInventario;
import co.com.jungla.sac.persistencia.entidades.Cotizacion;
import co.com.jungla.sac.persistencia.entidades.DetalleCotizacion;

import co.com.jungla.sac.negocio.delegados.DelegadoArticulo;
import co.com.jungla.sac.negocio.delegados.DelegadoControlInventario;
import co.com.jungla.sac.negocio.delegados.DelegadoCotizacion;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleCotizacion;
import com.mxrck.autocompleter.TextAutoCompleter;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JTextPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo el registro de  la cotizacion
 * @author Luis Fernando Pedroza T.
 * @version: 31/09/2016
 */
public class VentRegistrarCotizacion extends JFrame {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JPanel pnUe;
	private JTextField txtCodigo;
	private JTextField txtArticulo;
	private JTextField txtLinea;
	private JTextField txtUe;
	private JTextField txtCant;
	private JTextField txtSaldo;
	private JTextField txtSubtotal;
	private JTextField txtTotalCotizado;
	private JTextField txtContacto;
	private JTextField txtIdentificacion;
	private JTextField txtNombre;
	private JTextField txtItems;
	private JTextField txtDctoCom;
	private JTextField txtCiudad;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtDiasValidez;
	private JFormattedTextField txtVlrUnitario;
	private JDateChooser dchFecha;
	private JScrollPane srcObservaciones;
	private JTextPane txpObservaciones;
	private JTable tbArticulos;
	private DefaultTableModel modeloTbArticulos = new DefaultTableModel();

	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private Double subtotal;
	private Cotizacion cotizacion;
	private DelegadoCotizacion delegadoCotizacion= new DelegadoCotizacion();
	private Cotizacion ultimaCotizacion;
	
	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentRegistrarCotizacion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentRegistrarCotizacion.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Cotizaci\u00F3n");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1030, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 0, 0)));
		pn1.setBounds(10, 11, 991, 62);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JPanel pnCliente = new JPanel();
		pnCliente.setBounds(262, 11, 449, 38);
		pn1.add(pnCliente);
		pnCliente.setBackground(new Color(0, 51, 0));
		pnCliente.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCliente.setBackground(SystemColor.desktop);
		lblCliente.setForeground(SystemColor.window);
		lblCliente.setBounds(123, 0, 73, 14);
		pnCliente.add(lblCliente);
		
		txtIdentificacion = new JTextField();
		txtIdentificacion.setEditable(false);
		txtIdentificacion.setBounds(0, 17, 100, 20);
		pnCliente.add(txtIdentificacion);
		txtIdentificacion.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(104, 17, 272, 20);
		pnCliente.add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCliente();
			}
		});
		btnBuscar.setForeground(new Color(0, 51, 0));
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnBuscar.setBounds(380, 17, 68, 20);
		pnCliente.add(btnBuscar);
		
		JPanel pnFecha = new JPanel();
		pnFecha.setLayout(null);
		pnFecha.setBackground(new Color(0, 51, 0));
		pnFecha.setBounds(147, 11, 105, 38);
		pn1.add(pnFecha);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setBackground(SystemColor.desktop);
		lblFecha.setBounds(36, 0, 48, 14);
		pnFecha.add(lblFecha);
		
		dchFecha = new JDateChooser();
		dchFecha.setDate(new Date());
		dchFecha.setBounds(0, 18, 104, 20);
		dchFecha.setEnabled(false);
		pnFecha.add(dchFecha);
		
		JPanel pnContacto = new JPanel();
		pnContacto.setLayout(null);
		pnContacto.setBackground(new Color(0, 51, 0));
		pnContacto.setBounds(721, 11, 129, 38);
		pn1.add(pnContacto);
		
		JLabel lblContacto = new JLabel("Contacto");
		lblContacto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblContacto.setForeground(Color.WHITE);
		lblContacto.setBackground(Color.BLACK);
		lblContacto.setBounds(37, 0, 69, 14);
		pnContacto.add(lblContacto);
		
		txtContacto = new JTextField();
		txtContacto.setEditable(false);
		txtContacto.setColumns(10);
		txtContacto.setBounds(0, 18, 129, 20);
		pnContacto.add(txtContacto);
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(null);
		pn2.setBorder(new LineBorder(new Color(0, 0, 0)));
		pn2.setBounds(10, 149, 991, 62);
		contentPane.add(pn2);
		
		JPanel pnCodigo = new JPanel();
		pnCodigo.setLayout(null);
		pnCodigo.setBackground(new Color(0, 51, 0));
		pnCodigo.setBounds(52, 11, 77, 38);
		pn2.add(pnCodigo);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCodigo.setForeground(Color.WHITE);
		lblCodigo.setBackground(SystemColor.desktop);
		lblCodigo.setBounds(22, 0, 55, 14);
		pnCodigo.add(lblCodigo);
		
		txtCodigo = new JTextField();
		//Evento de teclado que permite traer la linea a la que pertenece el articulo mediante su codigo
		//al presionar enter o tab
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_TAB ){
					cargarDatosArticuloPorCodigo();
				}
			}
		});
		txtCodigo.setBounds(0, 18, 77, 20);
		pnCodigo.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JPanel pnArticulo = new JPanel();
		pnArticulo.setLayout(null);
		pnArticulo.setBackground(new Color(0, 51, 0));
		pnArticulo.setBounds(139, 11, 254, 38);
		pn2.add(pnArticulo);
		
		JLabel lblArticulo = new JLabel("Articulo (Busqueda facil)");
		lblArticulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblArticulo.setForeground(Color.WHITE);
		lblArticulo.setBackground(SystemColor.desktop);
		lblArticulo.setBounds(61, 0, 160, 14);
		pnArticulo.add(lblArticulo);
		
		txtArticulo = new JTextField();
		//Evento de teclado que permite traer la linea a la que pertenece el articulo mediante su nombre
		//al presionar enter o tab
		txtArticulo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_TAB ){
					cargarDatosArticuloPorNombre();
				}
			}
		});
		txtArticulo.setBounds(0, 18, 254, 20);
		pnArticulo.add(txtArticulo);
		txtArticulo.setColumns(10);
		
		JPanel pnLinea = new JPanel();
		pnLinea.setBounds(403, 11, 155, 38);
		pn2.add(pnLinea);
		pnLinea.setLayout(null);
		pnLinea.setBackground(new Color(0, 51, 0));
		
		JLabel lblLinea = new JLabel("Linea");
		lblLinea.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLinea.setForeground(Color.WHITE);
		lblLinea.setBackground(SystemColor.desktop);
		lblLinea.setBounds(59, 0, 52, 14);
		pnLinea.add(lblLinea);
		
		txtLinea = new JTextField();
		txtLinea.setColumns(10);
		txtLinea.setBounds(0, 18, 155, 20);
		txtLinea.setEditable(false);
		pnLinea.add(txtLinea);
		
		pnUe = new JPanel();
		pnUe.setLayout(null);
		pnUe.setBackground(new Color(0, 51, 0));
		pnUe.setBounds(568, 11, 37, 38);
		pn2.add(pnUe);
		
		JLabel lblUe = new JLabel("UE");
		lblUe.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUe.setForeground(Color.WHITE);
		lblUe.setBackground(SystemColor.desktop);
		lblUe.setBounds(10, 0, 27, 14);
		pnUe.add(lblUe);
		
		txtUe = new JTextField();
		txtUe.setColumns(10);
		txtUe.setBounds(0, 18, 37, 20);
		txtUe.setEditable(false);
		pnUe.add(txtUe);
		
		JPanel pnCant = new JPanel();
		pnCant.setLayout(null);
		pnCant.setBackground(new Color(0, 51, 0));
		pnCant.setBounds(615, 11, 53, 38);
		pn2.add(pnCant);
		
		JLabel lblCant = new JLabel("Cant");
		lblCant.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCant.setForeground(Color.WHITE);
		lblCant.setBackground(SystemColor.desktop);
		lblCant.setBounds(10, 0, 43, 14);
		pnCant.add(lblCant);
		
		txtCant = new JTextField();
		txtCant.setColumns(10);
		txtCant.setBounds(0, 18, 53, 20);
		txtCant.setText("1");
		pnCant.add(txtCant);
		
		JPanel pnVlrUnitario = new JPanel();
		pnVlrUnitario.setLayout(null);
		pnVlrUnitario.setBackground(new Color(0, 51, 0));
		pnVlrUnitario.setBounds(678, 11, 110, 38);
		pn2.add(pnVlrUnitario);
		
		JLabel lblVlrUnitario = new JLabel("Vlr Unitario");
		lblVlrUnitario.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVlrUnitario.setForeground(Color.WHITE);
		lblVlrUnitario.setBackground(SystemColor.desktop);
		lblVlrUnitario.setBounds(29, 0, 75, 14);
		pnVlrUnitario.add(lblVlrUnitario);
		
		txtVlrUnitario = new JFormattedTextField();
		txtVlrUnitario.setColumns(10);
		formatearAMoneda(txtVlrUnitario);
		txtVlrUnitario.setBounds(0, 18, 110, 20);
		pnVlrUnitario.add(txtVlrUnitario);
		
		JPanel pnSaldo = new JPanel();
		pnSaldo.setLayout(null);
		pnSaldo.setBackground(new Color(0, 51, 0));
		pnSaldo.setBounds(798, 11, 79, 38);
		pn2.add(pnSaldo);
		
		JLabel lblSaldo = new JLabel("Saldo");
		lblSaldo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSaldo.setForeground(Color.WHITE);
		lblSaldo.setBackground(SystemColor.desktop);
		lblSaldo.setBounds(24, 0, 60, 14);
		pnSaldo.add(lblSaldo);
		
		txtSaldo = new JTextField();
		txtSaldo.setEditable(false);
		txtSaldo.setColumns(10);
		txtSaldo.setBounds(0, 18, 79, 20);
		pnSaldo.add(txtSaldo);
		
		//Boton para validar y agregar los articulos buscados en la tabla
		JButton btnAgregarArticulo = new JButton("+");
		btnAgregarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarIngresoArticuloATbArticulos();
			}
		});
		btnAgregarArticulo.setBounds(887, 11, 51, 38);
		pn2.add(btnAgregarArticulo);
		
		JPanel pn3 = new JPanel();
		pn3.setBorder(new LineBorder(new Color(0, 0, 0)));
		pn3.setBounds(10, 218, 991, 220);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JScrollPane scrArticulos = new JScrollPane();
		scrArticulos.setBounds(2, 2, 987, 216);
		pn3.add(scrArticulos);
		
		tbArticulos = new JTable(modeloTbArticulos){
			//metodo que permite la no edicion de las columnas 3(Costo) y 4(Total)
			public boolean isCellEditable(int rowIndex, int colIndex) {
				if (colIndex==3) {
			        return true;
			    }
				if (colIndex==4) {
			        return true;
			    }
	            return false;
	        }
		}
		;
		
		scrArticulos.setViewportView(tbArticulos);
      		
		JPanel pn4 = new JPanel();
		pn4.setBorder(new LineBorder(new Color(0, 0, 0)));
		pn4.setBounds(10, 514, 991, 62);
		contentPane.add(pn4);
		pn4.setLayout(null);
		
		JPanel pnSubtotal = new JPanel();
		pnSubtotal.setLayout(null);
		pnSubtotal.setBackground(new Color(0, 51, 0));
		pnSubtotal.setBounds(56, 11, 104, 38);
		pn4.add(pnSubtotal);
		
		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSubtotal.setForeground(Color.WHITE);
		lblSubtotal.setBackground(SystemColor.desktop);
		lblSubtotal.setBounds(32, 0, 60, 14);
		pnSubtotal.add(lblSubtotal);
		
		txtSubtotal = new JTextField();
		txtSubtotal.setColumns(10);
		txtSubtotal.setBounds(0, 18, 104, 20);
		txtSubtotal.setEditable(false);
		pnSubtotal.add(txtSubtotal);
		
		JPanel pnTotalCotizado = new JPanel();
		pnTotalCotizado.setLayout(null);
		pnTotalCotizado.setBackground(new Color(0, 51, 0));
		pnTotalCotizado.setBounds(284, 11, 104, 38);
		pn4.add(pnTotalCotizado);
		
		JLabel lblTotalCotizado = new JLabel("Total Cotizado");
		lblTotalCotizado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalCotizado.setForeground(Color.WHITE);
		lblTotalCotizado.setBackground(SystemColor.desktop);
		lblTotalCotizado.setBounds(11, 0, 94, 14);
		pnTotalCotizado.add(lblTotalCotizado);
		
		txtTotalCotizado = new JTextField();
		txtTotalCotizado.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTotalCotizado.setForeground(new Color(152, 251, 152));
		txtTotalCotizado.setBackground(new Color(153, 153, 102));
		txtTotalCotizado.setColumns(10);
		txtTotalCotizado.setBounds(0, 18, 104, 20);
		txtTotalCotizado.setEditable(false);
		pnTotalCotizado.add(txtTotalCotizado);
		
		//Funcionalidad del boton quitar articulo en la que elimina los articulos seleccionados
		JButton btnQuitarArticulo = new JButton("Quitar Articulo");
		btnQuitarArticulo.setForeground(new Color(0, 51, 0));
		btnQuitarArticulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuitarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarArticuloATbArticulos();
			}
		});
		btnQuitarArticulo.setBounds(605, 20, 117, 23);
		pn4.add(btnQuitarArticulo);
		
		JButton btnVerificarVenta = new JButton("Registrar");
		btnVerificarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
		});
		btnVerificarVenta.setForeground(new Color(255, 0, 0));
		btnVerificarVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVerificarVenta.setBounds(732, 20, 110, 23);
		pn4.add(btnVerificarVenta);
		
		JPanel pnItems = new JPanel();
		pnItems.setLayout(null);
		pnItems.setBackground(new Color(0, 51, 0));
		pnItems.setBounds(507, 11, 60, 38);
		pn4.add(pnItems);
		
		JLabel lblItems = new JLabel("Items");
		lblItems.setForeground(Color.WHITE);
		lblItems.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblItems.setBackground(SystemColor.desktop);
		lblItems.setBounds(13, 0, 49, 14);
		pnItems.add(lblItems);
		
		txtItems = new JTextField();
		txtItems.setText("0");
		txtItems.setEditable(false);
		txtItems.setColumns(10);
		txtItems.setBounds(0, 18, 60, 20);
		pnItems.add(txtItems);
		
		JPanel pnDctoCom = new JPanel();
		pnDctoCom.setLayout(null);
		pnDctoCom.setBackground(new Color(0, 51, 0));
		pnDctoCom.setBounds(170, 11, 104, 38);
		pn4.add(pnDctoCom);
		
		JLabel lblDctoCom = new JLabel("Dcto Com.");
		lblDctoCom.setForeground(Color.WHITE);
		lblDctoCom.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDctoCom.setBackground(SystemColor.desktop);
		lblDctoCom.setBounds(32, 0, 60, 14);
		pnDctoCom.add(lblDctoCom);
		
		txtDctoCom = new JTextField();
		txtDctoCom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				calcularTotalCotizacion();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				calcularTotalCotizacion();
			}
		});
		txtDctoCom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_TAB ){
					calcularTotalCotizacion();
				}
			}
		});
		txtDctoCom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularTotalCotizacion();

			}
		});
		txtDctoCom.setText("0.0");
		txtDctoCom.setColumns(10);
		txtDctoCom.setBounds(0, 18, 86, 20);
		pnDctoCom.add(txtDctoCom);
		
		JLabel label_1 = new JLabel("%");
		label_1.setBounds(87, 17, 17, 22);
		pnDctoCom.add(label_1);
		
		JPanel pnDiasValidez = new JPanel();
		pnDiasValidez.setLayout(null);
		pnDiasValidez.setBackground(new Color(0, 51, 0));
		pnDiasValidez.setBounds(398, 11, 104, 38);
		pn4.add(pnDiasValidez);
		
		JLabel lblValidezDias = new JLabel("Validez (Dias)");
		lblValidezDias.setForeground(Color.WHITE);
		lblValidezDias.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValidezDias.setBackground(SystemColor.desktop);
		lblValidezDias.setBounds(14, 0, 85, 14);
		pnDiasValidez.add(lblValidezDias);
		
		txtDiasValidez = new JTextField();
		txtDiasValidez.setColumns(10);
		txtDiasValidez.setText("1");
		txtDiasValidez.setBounds(0, 18, 104, 20);
		pnDiasValidez.add(txtDiasValidez);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.setBounds(852, 20, 89, 23);
		pn4.add(btnCerrar);
		
		JPanel pn5 = new JPanel();
		pn5.setLayout(null);
		pn5.setBorder(new LineBorder(new Color(0, 0, 0)));
		pn5.setBounds(10, 80, 991, 62);
		contentPane.add(pn5);
		
		JPanel pnCiudad = new JPanel();
		pnCiudad.setLayout(null);
		pnCiudad.setBackground(new Color(0, 51, 0));
		pnCiudad.setBounds(192, 11, 201, 38);
		pn5.add(pnCiudad);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setForeground(Color.WHITE);
		lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCiudad.setBackground(Color.BLACK);
		lblCiudad.setBounds(82, 0, 74, 14);
		pnCiudad.add(lblCiudad);
		
		txtCiudad = new JTextField();
		txtCiudad.setEditable(false);
		txtCiudad.setColumns(10);
		txtCiudad.setBounds(0, 18, 201, 20);
		pnCiudad.add(txtCiudad);
		
		JPanel pnDireccion = new JPanel();
		pnDireccion.setLayout(null);
		pnDireccion.setBackground(new Color(0, 51, 0));
		pnDireccion.setBounds(403, 11, 219, 38);
		pn5.add(pnDireccion);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setForeground(Color.WHITE);
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDireccion.setBackground(Color.BLACK);
		lblDireccion.setBounds(81, 0, 112, 14);
		pnDireccion.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(0, 18, 219, 20);
		pnDireccion.add(txtDireccion);
		
		JPanel pnTelefono = new JPanel();
		pnTelefono.setLayout(null);
		pnTelefono.setBackground(new Color(0, 51, 0));
		pnTelefono.setBounds(632, 11, 171, 38);
		pn5.add(pnTelefono);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelefono.setBackground(Color.BLACK);
		lblTelefono.setBounds(55, 0, 84, 14);
		pnTelefono.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(0, 18, 171, 20);
		pnTelefono.add(txtTelefono);
		
		JPanel pnObservaciones = new JPanel();
		pnObservaciones.setLayout(null);
		pnObservaciones.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnObservaciones.setBounds(10, 445, 991, 62);
		contentPane.add(pnObservaciones);
		
		srcObservaciones = new JScrollPane();
		srcObservaciones.setBounds(107, 5, 874, 52);
		pnObservaciones.add(srcObservaciones);
		
		txpObservaciones = new JTextPane();
		srcObservaciones.setViewportView(txpObservaciones);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaciones.setBounds(10, 5, 100, 53);
		pnObservaciones.add(lblObservaciones);
		
		buscarArticuloPorNombre();
		llenarColumnasTbArticulos();
	}
	
	//Metodo para encontrar los datos del articulo incluyendo su linea mediante el nombre
	private void cargarDatosArticuloPorNombre() {
		try{
			DelegadoArticulo delegadoArticulo =new DelegadoArticulo();
			DelegadoControlInventario delegadoControlInventario = new DelegadoControlInventario();
			Articulo datosArticulo=delegadoArticulo.traerLineaUnidadArticulo(txtArticulo.getText()).get(0);
			ControlInventario datosArticuloInventario = delegadoControlInventario.traerContInventarioPorCodigoArticulo(Integer.parseInt(txtCodigo.getText())).get(0);
			if(datosArticulo.getEstado().equals("Inactivo")){
				JOptionPane.showMessageDialog(null, "El articulo "+datosArticulo.getNombre()+" se encuentra INACTIVO");
				txtArticulo.setText("");
			}else{
				if(datosArticuloInventario.getCantExistencia()==0){
					JOptionPane.showMessageDialog(null, "El articulo \""+datosArticulo.getNombre()+"\" ya no tiene existencias en el inventario");
					txtArticulo.setText("");
				}else{
					txtCodigo.setText(Integer.toString(datosArticulo.getCodigo()));
					txtLinea.setText(datosArticulo.getLineaArticulos().getNombreL());
					txtUe.setText(datosArticulo.getUnidadMedida().getAbreviatura().toString());
					txtVlrUnitario.setText(formatearNumero(datosArticuloInventario.getPrecioTotalVenta()));
					txtSaldo.setText(String.valueOf(datosArticuloInventario.getCantExistencia()));
				}
			}
		}catch(Exception ex){
			ex.getMessage();
		}
	}
	
	//Metodo para encontrar los datos del articulo incluyendo su linea mediante el codigo
	private void cargarDatosArticuloPorCodigo() {
		try{
			DelegadoArticulo delegadoArticulo =new DelegadoArticulo();
			DelegadoControlInventario delegadoControlInventario = new DelegadoControlInventario();
			Articulo datosArticulo=delegadoArticulo.traerLineaUnidadArticuloPorCodigo(Integer.parseInt(txtCodigo.getText())).get(0);
			ControlInventario datosArticuloInventario = delegadoControlInventario.traerContInventarioPorCodigoArticulo(Integer.parseInt(txtCodigo.getText())).get(0);
			
			if(datosArticulo.getEstado().equals("Inactivo")){
				JOptionPane.showMessageDialog(null, "El articulo "+datosArticulo.getNombre()+" se encuentra INACTIVO");
				txtCodigo.setText("");
			}else{
				if(datosArticuloInventario.getCantExistencia()==0){
					JOptionPane.showMessageDialog(null, "El articulo \""+datosArticulo.getNombre()+"\" ya no tiene existencias en el inventario");
					txtCodigo.setText("");
				}else{
					txtArticulo.setText(datosArticulo.getNombre());
					txtLinea.setText(datosArticulo.getLineaArticulos().getNombreL());
					txtUe.setText(datosArticulo.getUnidadMedida().getAbreviatura().toString());
					txtVlrUnitario.setValue(datosArticuloInventario.getPrecioTotalVenta());
					txtSaldo.setText(String.valueOf(datosArticuloInventario.getCantExistencia()));
				}
			}
			
		}catch(Exception ex){
			ex.getMessage();
			JOptionPane.showMessageDialog(null, "El codigo "+txtCodigo.getText()+" no existe");
			limpiarDatosArticulo();
		}
		
	}
		
	//Metodo para limpiar las cajas de texto en la que se consulto los atributos del articulo
	private void limpiarDatosArticulo() {
		txtCodigo.setText("");
		txtArticulo.setText("");
		txtLinea.setText("");
		txtUe.setText("");
		txtCant.setText("1");
		txtVlrUnitario.setValue(0);
		txtSaldo.setText("");
	}
		
	//Metodo para buscar un articulo mediante su nombre permitiendo autocompletar
	private void buscarArticuloPorNombre() {
		TextAutoCompleter textoAutocompletar = new TextAutoCompleter( txtArticulo);
		DelegadoArticulo delegadoArticulo = new DelegadoArticulo();
		List<Articulo> articulos = delegadoArticulo.listarArticulo();
		
		for(Articulo articulo : articulos){
			textoAutocompletar.addItem(articulo.getNombre().toString());
		}
	}
	//Metodo para abrir la ventana buscar cliente
	private void buscarCliente() {
		VentBuscarClienteCotizacion ventBuscarClienteCotizacion = new VentBuscarClienteCotizacion();
		ventBuscarClienteCotizacion.setVisible(true);
		setVisible(false);
		
	}
	
	//Metodo para agregar los datos de la cotizacion desde la ventana buscar cliente
	public void agregarDatosACotizacion(Cliente datosCliente){
		txtIdentificacion.setText(datosCliente.getIdentificacion());
		txtNombre.setText(datosCliente.getNombre());
		txtContacto.setText(datosCliente.getContacto());
		txtCiudad.setText(datosCliente.getMunicipio().getNombre());
		txtDireccion.setText(datosCliente.getDireccion());
		txtTelefono.setText(datosCliente.getTelefono());
		
		txtIdentificacion.setEditable(false);
		txtNombre.setEditable(false);
		txtContacto.setEditable(false);
		txtCiudad.setEditable(false);
		txtDireccion.setEditable(false);
		txtTelefono.setEditable(false);
	}
	
	//Metodo para convertir un numero corriente en formato de pesos y decimales
	private String formatearNumero(Double numero){
		NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es","CO"));
		return formato.format(numero); 
	}
	
	//Metodo para formatear un numero y convertirlo en moneda peso
	private void formatearAMoneda(JFormattedTextField campoTexto) {
		
		campoTexto.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
		campoTexto.setValue(0);
		NumberFormat visNf = NumberFormat.getCurrencyInstance(); 
		NumberFormat ediNf = NumberFormat.getNumberInstance(new Locale("es","CO"));
		ediNf.setGroupingUsed(false);
		NumberFormatter visNt = new NumberFormatter(visNf);
		NumberFormatter ediNt = new NumberFormatter(ediNf);
		DefaultFormatterFactory currFactory = new DefaultFormatterFactory(visNt, visNt, ediNt);
		ediNt.setAllowsInvalid(true);
		
		campoTexto.setFormatterFactory(currFactory);
	}
	
	//Metodo para sumar dias a una fecha determinada
	public Date sumarDias(Date fechaAAplicar, int dias){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaAAplicar); 
		calendar.add(Calendar.DAY_OF_YEAR, dias);  
		return calendar.getTime(); 
	}
	
	//Metodo para validar los datos ingresados del articulo agregandolos a la tabla y a su vez calculando
	//la cantidad de items, el descuento, el subtotal y el total
	private void validarIngresoArticuloATbArticulos() {
		if(txtCodigo.getText().equals("")||txtArticulo.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Debe Seleccionar el ARTICULO primero ingresando el codigo o articulo");
		}else{
			if(txtVlrUnitario.getValue().equals(0)){
				JOptionPane.showMessageDialog(null, "Debe ingresar el VALOR UNITARIO para este item");
			}
			else{
				agregarArticuloATbArticulos();
				calcularSubtotal();
				calcularCantidadArticulos();
				calcularTotalCotizacion();
			}
		}
	}
	
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbArticulos() {
		modeloTbArticulos.addColumn("Codigo");
		modeloTbArticulos.addColumn("Linea");
		modeloTbArticulos.addColumn("Articulo");
		modeloTbArticulos.addColumn("Cant");
		modeloTbArticulos.addColumn("Unidad");
		modeloTbArticulos.addColumn("Vlr Unitario");
		modeloTbArticulos.addColumn("Total");
		
		tbArticulos.setModel(modeloTbArticulos);
	}
	
	//Metodo para agregar el articulo en la tabla
	private void agregarArticuloATbArticulos() {
        
		Double total = (double) (Integer.parseInt(txtCant.getText())) *(Double.parseDouble(txtVlrUnitario.getValue().toString()));

		String [] fila = new String[modeloTbArticulos.getColumnCount()];
		Boolean aviso = false;
		
		fila[0]= txtCodigo.getText();
		fila[1]= txtLinea.getText();
		fila[2]= txtArticulo.getText();
		fila[3]= txtCant.getText();
		fila[4]= txtUe.getText();
		fila[5]= formatearNumero(Double.parseDouble(txtVlrUnitario.getValue().toString()));
		fila[6]= formatearNumero(total);
        
		for(int i = 0; i<tbArticulos.getRowCount(); i++){
			 
			if(tbArticulos.getValueAt(i,0).equals(txtCodigo.getText())){
				JOptionPane.showMessageDialog(null, "El codigo del articulo " + txtCodigo.getText() + " ya fue adicionado en la tabla por lo tanto modifique la cantidad.");
				aviso = true;
				limpiarDatosArticulo();
			}
		}
		
		if(aviso == false){
			modeloTbArticulos.addRow(fila);
			tbArticulos.setModel(modeloTbArticulos);
			limpiarDatosArticulo();
		}
		
      //Evento que permite la modificacion de la tabla cada vez que esta es editada
      		tbArticulos.getModel().addTableModelListener(new TableModelListener() {
                  @Override
                  public void tableChanged(TableModelEvent evento) {
                      actualizarTbArticulos(evento);
                  }
              });
	}
	
	//Metodo para actualizar la tabla cada vez que haya un cambio en la misma
	private void actualizarTbArticulos(TableModelEvent evento) {
		
		if (evento.getType() == TableModelEvent.UPDATE) {

            // Se obtiene el modelo de la tabla y la fila/columna que han cambiado.
            TableModel modelo = ((TableModel) (evento.getSource()));
            int fila = evento.getFirstRow();
            int columna = evento.getColumn();
           // Se aplica los calculos solamente a la columnas 3 (costo) y 4 (total)
            if (columna == 3 || columna == 4 ) {
	            try{
	            	 int cant = Integer.parseInt(String.valueOf(modelo.getValueAt(fila, 3)));
	            	 double vrlUnitario = Double.parseDouble(desformatearNumero(String.valueOf(modelo.getValueAt(fila, 5))));
	            	 double total = cant*vrlUnitario; 
	            	 modelo.setValueAt(formatearNumero(total),fila, 6);	
	            	 calcularCantidadArticulos();
	            	 calcularSubtotal();
	            	 calcularTotalCotizacion();
	            	 
	            }catch(Exception ex){
	            	ex.getMessage();
	            }
            }
        }
		
	}
	
	//Metodo para quitar los articulos ya ingresados en la tabla
	private void quitarArticuloATbArticulos() {
		try{
			DefaultTableModel modeloArticuloAEliminar = (DefaultTableModel) tbArticulos.getModel();
			modeloArticuloAEliminar.removeRow(tbArticulos.getSelectedRow()); 
			calcularSubtotal();
			calcularCantidadArticulos();
			calcularTotalCotizacion();
		}catch(Exception e){
			e.getMessage();
			JOptionPane.showMessageDialog(null, "Debe seleccionar cualquier articulo para ser eliminado");
		}
	}
	
	//Metodo para convertir un numero en formato de pesos y decimales en un numero corriente
	private String desformatearNumero(String numero){
		String numeroReemplazado=numero.replace("$", "");
		String numeroReemplazado1=numeroReemplazado.replace(".", "");
		String numeroReemplazado2=numeroReemplazado1.replace(",", ".");
		return numeroReemplazado2;
	}
	
	//Metodo para calcular el subtotal de los articulos ingresados a la tabla 
	private void calcularSubtotal(){
		
		Double subtotal1 = (double) 0;
		
		for(int i=0; i<tbArticulos.getRowCount(); i++) {
			subtotal= Double.parseDouble(desformatearNumero(String.valueOf(tbArticulos.getValueAt(i,6))));
			subtotal1 += subtotal;
		}
		
		txtSubtotal.setText(formatearNumero(subtotal1));
	}
	
	//Metodo para calcular la cantidad de items registrados en la tabla
	private void calcularCantidadArticulos() {
		
		int cantidad = tbArticulos.getRowCount();
		txtItems.setText(Integer.toString(cantidad));
		
	}
	
	//Metodo para calcular el descuento de los articulos ingresados a la tabla para finalmente hallar el total de la cotizacion
	private void calcularTotalCotizacion() {
		try{
			Double descuento = (1-(Double.parseDouble(txtDctoCom.getText())/100))* Double.parseDouble(desformatearNumero(txtSubtotal.getText()));
			txtTotalCotizado.setText(formatearNumero(descuento));
		}catch(Exception e){
			e.getMessage();
			txtTotalCotizado.setText(txtSubtotal.getText());
		}
		
	}
	
	//Metodo para validar los datos ingresados de la cotizacion incluyendo la tabla de articulos
	private void validarDatos() {
		
		if(txtIdentificacion.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Debe buscar o digitar el cliente por identificación o nombre");
		}else{
			if(txtNombre.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Debe buscar o digitar el cliente por identificación o nombre");
			}else{
				if(txtDiasValidez.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Debe digitar los dias de validez");
				}else{
					if(tbArticulos.getRowCount() == 0 ){
						JOptionPane.showMessageDialog(null, "Debe ingresar articulos en la tabla");
					}else{
						abrirDialogoConfirmacionRegistro();
					}
				}
			}
		}
	}
	
	//Metodo para abrir ventanta de confirmacion de registro
	private void abrirDialogoConfirmacionRegistro() {
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de grabar esta cotización?", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			registrarCotizacion();
		}else{
		
		}
	}

	//Metodo para registrar los datos ingresados de la cotizacion
	private void registrarCotizacion() {
		
		cotizacion = new Cotizacion();
		
		cotizacion.setFecha(dchFecha.getDate());
		cotizacion.setIdentificacionCliente(txtIdentificacion.getText().toString());
		cotizacion.setNombreCliente(txtNombre.getText());
		cotizacion.setCiudad(txtCiudad.getText());
		cotizacion.setContacto(txtContacto.getText());
		cotizacion.setDireccion(txtDireccion.getText());
		cotizacion.setTelefono(txtTelefono.getText());
		cotizacion.setItems(Integer.parseInt(txtItems.getText()));
		cotizacion.setSubtotal(Double.parseDouble(desformatearNumero(txtSubtotal.getText())));
		cotizacion.setDescuento(Double.parseDouble(txtDctoCom.getText()));
		cotizacion.setTotalCotizado(Double.parseDouble(desformatearNumero(txtTotalCotizado.getText())));
		cotizacion.setDiasValidez(Integer.parseInt(txtDiasValidez.getText()));
		cotizacion.setObservaciones(txpObservaciones.getText());
		
		delegadoCotizacion.insertarCotizacion(cotizacion);
		ultimaCotizacion= delegadoCotizacion.traerUltimaCotizacion().get(0);
		
		registrarDetalleCotizacion();
		JOptionPane.showMessageDialog(null, "Se ha registrado ésta COTIZACION satisfactoriamente con consecutivo: N° "+ultimaCotizacion.getIdCotizacion());
		limpiarDatos();
		generarCotizacion();
	}
	
	//Metodo para registrar el detalle cotizacion
	private void registrarDetalleCotizacion() {
		DetalleCotizacion detalleCotizacion = new DetalleCotizacion();
		DelegadoDetalleCotizacion delegadoDetalleCotizacion = new DelegadoDetalleCotizacion();
		Articulo articulo = new Articulo();
		
		for(int i=0; i< modeloTbArticulos.getRowCount();i++){
			
			cotizacion.setIdCotizacion(ultimaCotizacion.getIdCotizacion());
			detalleCotizacion.setCotizacion(cotizacion);
			articulo.setCodigo(Integer.parseInt(modeloTbArticulos.getValueAt(i, 0).toString()));
			articulo.setDescripcion(modeloTbArticulos.getValueAt(i, 2).toString());
			detalleCotizacion.setArticulo(articulo);
			detalleCotizacion.setCantidad(Integer.parseInt(modeloTbArticulos.getValueAt(i, 3).toString()));
			detalleCotizacion.setVlrUnitario(Double.parseDouble(desformatearNumero(modeloTbArticulos.getValueAt(i, 5).toString())));
			detalleCotizacion.setTotal(Double.parseDouble(desformatearNumero(modeloTbArticulos.getValueAt(i, 6).toString())));
			
			delegadoDetalleCotizacion.insertarDetalleCotizacion(detalleCotizacion);
		}
		
	}

	//Metodo para limpiar los datos ya registrados
	private void limpiarDatos() {
		
		txtIdentificacion.setText("");
		txtNombre.setText("");
		dchFecha.setDate(new Date());
		txtContacto.setText("");
		txtCiudad.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		limpiarTablaArticulos();
		txpObservaciones.setText("");
		txtSubtotal.setText(formatearNumero(0.0));
		txtDctoCom.setText("0.0");
		txtTotalCotizado.setText(formatearNumero(0.0));
		txtItems.setText("0");
		
		txtIdentificacion.setEditable(true);
		txtNombre.setEditable(true);
		txtContacto.setEditable(true);
		txtCiudad.setEditable(true);
		txtDireccion.setEditable(true);
		txtTelefono.setEditable(true);
	}
	
	//Metodo para limpiar la tabla de articulos 
	private void limpiarTablaArticulos() {
		for (int i = 0; i < tbArticulos.getRowCount(); i++) {
	           modeloTbArticulos.removeRow(i);
	           i-=1;
	       }
	}
	
	//Metodo para convertir una fecha de tipo date a una cadena
	public String convertirDateAString(Date strFecha){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formateador.format(strFecha);
		
		return fecha;
	}
	
	//Metodo para visualizar la cotizacion como un reporte para ser impreso o guardado
	private void generarCotizacion() {
		
		 URL ubicacion = getClass().getResource("/co/com/jungla/sac/presentacion/reportes/reporteCotizacion.jasper");
		 List<ReporteDetalle> lista = new ArrayList<ReporteDetalle>();
		 
		 for(int i=0; i< modeloTbArticulos.getRowCount();i++){
			 
			 ReporteDetalle detalleCotizacion= new ReporteDetalle(Integer.parseInt(modeloTbArticulos.getValueAt(i, 0).toString()), modeloTbArticulos.getValueAt(i, 2).toString(), modeloTbArticulos.getValueAt(i, 4).toString(),Integer.parseInt(modeloTbArticulos.getValueAt(i, 3).toString()), modeloTbArticulos.getValueAt(i, 5).toString(), modeloTbArticulos.getValueAt(i, 6).toString());
			 lista.add(detalleCotizacion);
				
		 }
	        try {
	            JasperReport reporte= (JasperReport) JRLoader.loadObject(ubicacion);
	            Map<String, Object> parametros = new HashMap<String, Object>();
	            parametros.put("logoJungla", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png"));
	            parametros.put("firma", this.getClass().getResourceAsStream("/co/com/jungla/sac/presentacion/imagenes/firma.jpg"));
	            parametros.put("idCotizacion", cotizacion.getIdCotizacion());
	            parametros.put("nombreCliente", cotizacion.getNombreCliente());
	            parametros.put("identCliente", cotizacion.getIdentificacionCliente());
	            parametros.put("direccion", cotizacion.getDireccion());
	            parametros.put("telefono", cotizacion.getTelefono());
	            parametros.put("ciudad", cotizacion.getCiudad());
	            parametros.put("contacto", cotizacion.getContacto());
	            parametros.put("subtotal", formatearNumero(cotizacion.getSubtotal()));
	            parametros.put("descuento", formatearNumero(cotizacion.getDescuento()));
	            parametros.put("total", formatearNumero(cotizacion.getTotalCotizado()));
	            parametros.put("items", String.valueOf(cotizacion.getItems()));
	            parametros.put("fecha", convertirDateAString(cotizacion.getFecha()));
	            parametros.put("observaciones", cotizacion.getObservaciones());
	            parametros.put("diasValidez", String.valueOf(cotizacion.getDiasValidez()));
	            
	            JasperPrint jprint= JasperFillManager.fillReport(reporte, parametros,new JRBeanCollectionDataSource(lista));
	            JasperViewer.viewReport(jprint,false);
	        } catch (JRException ex) {
	            Logger.getLogger(VentVerificarVentaContado.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
}
