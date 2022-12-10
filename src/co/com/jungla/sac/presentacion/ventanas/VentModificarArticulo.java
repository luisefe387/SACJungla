package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;


import co.com.jungla.sac.persistencia.entidades.Articulo;
import co.com.jungla.sac.persistencia.entidades.UnidadMedida;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import co.com.jungla.sac.negocio.delegados.DelegadoArticulo;
import co.com.jungla.sac.negocio.delegados.DelegadoUnidadMedida;

import java.awt.SystemColor;
import java.util.List;

public class VentModificarArticulo extends JDialog {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtPresentacion;
	private JTextField txtDescripcion;
	private JTextField txtReferencia;
	public JComboBox cbUnidadMedida;
	DefaultComboBoxModel modeloUnidadMedida = new DefaultComboBoxModel();
	private JTextField txtCodigo;
	private JTextField txtLineaArticulos;
	private Articulo articuloElegido;
	private JComboBox cbEstado;
	
	/**
	 * Create the frame.
	 */
	public VentModificarArticulo() {
		setTitle("Modificar Articulo");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 524, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLineaArticulos = new JLabel("Linea de Articulos ");
		lblLineaArticulos.setBackground(new Color(153, 204, 153));
		lblLineaArticulos.setOpaque(true);
		lblLineaArticulos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLineaArticulos.setBounds(10, 78, 117, 22);
		contentPane.add(lblLineaArticulos);
		
		JLabel lblNombre = new JLabel("Nombre Art\u00EDculo ");
		lblNombre.setBackground(new Color(153, 204, 153));
		lblNombre.setOpaque(true);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(10, 157, 117, 22);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(129, 158, 375, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblUnidadMedida = new JLabel("Unidad de Medida ");
		lblUnidadMedida.setBackground(new Color(153, 204, 153));
		lblUnidadMedida.setOpaque(true);
		lblUnidadMedida.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUnidadMedida.setBounds(10, 180, 117, 22);
		contentPane.add(lblUnidadMedida);
		
		cbUnidadMedida = new JComboBox();
		cbUnidadMedida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearUnidadEmpaque();
			}
		});
		cbUnidadMedida.setBounds(129, 181, 241, 20);
		contentPane.add(cbUnidadMedida);
		
		JLabel lblPresentacion = new JLabel("Presentaci\u00F3n");
		lblPresentacion.setBackground(new Color(153, 204, 153));
		lblPresentacion.setOpaque(true);
		lblPresentacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPresentacion.setBounds(10, 227, 117, 22);
		contentPane.add(lblPresentacion);
		
		txtPresentacion = new JTextField();
		txtPresentacion.setBounds(129, 228, 375, 20);
		contentPane.add(txtPresentacion);
		txtPresentacion.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setBackground(new Color(153, 204, 153));
		lblDescripcion.setOpaque(true);
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescripcion.setBounds(10, 250, 117, 22);
		contentPane.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(129, 251, 375, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblReferencia = new JLabel("Referencia");
		lblReferencia.setBackground(new Color(153, 204, 153));
		lblReferencia.setOpaque(true);
		lblReferencia.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblReferencia.setBounds(10, 273, 117, 22);
		contentPane.add(lblReferencia);
		
		txtReferencia = new JTextField();
		txtReferencia.setBounds(129, 274, 241, 20);
		contentPane.add(txtReferencia);
		txtReferencia.setColumns(10);
		
		//Boton para registrar el articulo 
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setForeground(new Color(0, 51, 0));
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirDialogoActualizacion();
			}
		});
		btnModificar.setBounds(145, 366, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 51, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(272, 366, 89, 23);
		contentPane.add(btnCerrar);
		
		JSeparator sp1 = new JSeparator();
		sp1.setBackground(new Color(0, 51, 0));
		sp1.setBounds(10, 306, 494, 2);
		contentPane.add(sp1);
		
		JTextPane txpAtencion = new JTextPane();
		txpAtencion.setBackground(UIManager.getColor("Button.background"));
		txpAtencion.setText("ATENCI\u00D3N: Si desea modificar precios y cantidades, en inventario debe ingresar a: Inventario - Control de Inventarios");
		txpAtencion.setBounds(10, 11, 494, 38);
		contentPane.add(txpAtencion);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 214, 496, 2);
		contentPane.add(sp);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setOpaque(true);
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCodigo.setBackground(new Color(153, 204, 153));
		lblCodigo.setBounds(10, 55, 117, 22);
		contentPane.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(129, 56, 135, 20);
		contentPane.add(txtCodigo);
		
		txtLineaArticulos = new JTextField();
		txtLineaArticulos.setEditable(false);
		txtLineaArticulos.setColumns(10);
		txtLineaArticulos.setBounds(129, 79, 375, 20);
		contentPane.add(txtLineaArticulos);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setOpaque(true);
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstado.setBackground(new Color(153, 204, 153));
		lblEstado.setBounds(10, 319, 117, 22);
		contentPane.add(lblEstado);
		
		cbEstado = new JComboBox();
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"Activo", "Inactivo"}));
		cbEstado.setBounds(129, 320, 135, 20);
		contentPane.add(cbEstado);
		
		JSeparator sp2 = new JSeparator();
		sp2.setBackground(new Color(0, 51, 0));
		sp2.setBounds(10, 353, 494, 2);
		contentPane.add(sp2);
		
		JTextPane txpAtencion1 = new JTextPane();
		txpAtencion1.setText("ATENCI\u00D3N: El ARTICULO NO puede cambiarse de LINEA una vez se ha registrado. De ser necesario debe deshabilitarse el art\u00EDculo y crearse en la nueva linea.");
		txpAtencion1.setBackground(SystemColor.activeCaptionBorder);
		txpAtencion1.setBounds(129, 102, 375, 53);
		contentPane.add(txpAtencion1);
		
		listarUnidadMedida();
		
		setModal(true);
		
	}
	
	//Metodo que permite listar las unidades de medida y desplegarlos en un combo box
	public void listarUnidadMedida() {
		
		DelegadoUnidadMedida delegadoUnidadMedida = new DelegadoUnidadMedida();
		List<UnidadMedida> unidadesMedida = delegadoUnidadMedida.listarUnidadMedida();
		modeloUnidadMedida.addElement("--ELIJA UNIDAD DE EMPAQUE--");
		cbUnidadMedida.setModel(modeloUnidadMedida);
		
		for(UnidadMedida unidadMedida : unidadesMedida){
			modeloUnidadMedida.addElement(new UnidadMedida (unidadMedida.getNombre(), unidadMedida.getCodigo()));
			cbUnidadMedida.setModel(modeloUnidadMedida);
		}
		
		modeloUnidadMedida.addElement("--NUEVA UNIDAD DE EMPAQUE--");
		cbUnidadMedida.setModel(modeloUnidadMedida);
	}
	
	//Metodo para crear unidad de empaque en caso de que no aparezca en el combo box
	private void crearUnidadEmpaque() {
		if(cbUnidadMedida.getSelectedItem().equals("--NUEVA UNIDAD DE EMPAQUE--")){
			VentRegistrarUnidadMedida ventRegistrarUnidadMedida = new VentRegistrarUnidadMedida(/*VentRegistrarArticulo.this, true*/);
			cbUnidadMedida.removeAllItems();
			ventRegistrarUnidadMedida.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                	listarUnidadMedida();
                }
            });
			ventRegistrarUnidadMedida.setVisible(true);
		}
	}
	
	//Metodo que permite la modificacion del articulo a la base de datos luego de haberse validado
	private void modificarArticulo() {
		DelegadoArticulo delegadoArticulo = new DelegadoArticulo();
		UnidadMedida unidadMedida = new UnidadMedida();
		
		Articulo articuloAModificar = articuloElegido;
		
		articuloAModificar.setDescripcion(txtDescripcion.getText());
		articuloAModificar.setNombre(txtNombre.getText());
		articuloAModificar.setPresentacion(txtPresentacion.getText());
		articuloAModificar.setReferencia(txtReferencia.getText());
		unidadMedida = (UnidadMedida)cbUnidadMedida.getSelectedItem();
		unidadMedida.setCodigo(unidadMedida.getCodigo());
		articuloAModificar.setUnidadMedida(unidadMedida);
		articuloAModificar.setEstado(cbEstado.getSelectedItem().toString());
		
		delegadoArticulo.actualizarArticulo(articuloAModificar);
	}
	
	//Metodo para abrir ventanta de actualizacion
	private void abrirDialogoActualizacion(){
		int res = JOptionPane.showConfirmDialog(null, "Esta seguro de actualizar este articulo", null, JOptionPane.YES_NO_OPTION);
		if(res==0){
			modificarArticulo();
			dispose();
		}else{
		
		}
	}
	
	//Metodo para agregar los datos de los articulos
	public void agregarDatos(Articulo articuloElegido){
		txtCodigo.setText(String.valueOf(articuloElegido.getCodigo()));
		txtLineaArticulos.setText(articuloElegido.getLineaArticulos().getNombreL());
		txtNombre.setText(articuloElegido.getNombre());
		//codigoUnidadMedida = articuloElegido.getUnidadMedida().getCodigo();
		cbUnidadMedida.getModel().setSelectedItem(new UnidadMedida(articuloElegido.getUnidadMedida().getNombre(), articuloElegido.getUnidadMedida().getCodigo()));
		txtPresentacion.setText(articuloElegido.getPresentacion());
		txtReferencia.setText(articuloElegido.getReferencia());
		txtDescripcion.setText(articuloElegido.getDescripcion());
		this.articuloElegido = articuloElegido;
	}
	
	
}
