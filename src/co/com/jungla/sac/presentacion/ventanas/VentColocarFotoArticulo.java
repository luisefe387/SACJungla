package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.io.File;

import co.com.jungla.sac.negocio.delegados.DelegadoControlInventario;
import co.com.jungla.sac.persistencia.entidades.ControlInventario;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JTextField;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo la colocacion, la eliminacion de la foto del articulo para ser visualizado en el catalogo de articulos.
 * @author Luis Fernando Pedroza T.
 * @version: 03/09/2016
 */
public class VentColocarFotoArticulo extends JDialog {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtExaminar;
	private JButton btnColocarImagen;
	private JButton btnBorrarImagen;

	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private String rutaFoto;
	private String nombreFoto;
	private int codigoArticulo;
	private DelegadoControlInventario delegadoControlInventario = new DelegadoControlInventario();

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentColocarFotoArticulo(int codigoArticulo) {
		setTitle("Cargar Imagen");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 475, 194);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 445, 145);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		btnColocarImagen = new JButton("Colocar Imagen");
		btnColocarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colocarImagen();
			}
		});
		btnColocarImagen.setForeground(new Color(0, 51, 0));
		btnColocarImagen.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnColocarImagen.setBounds(10, 91, 146, 23);
		pn1.add(btnColocarImagen);
		
		btnBorrarImagen = new JButton("Borrar Imagen");
		btnBorrarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarImagen();
			}
		});
		btnBorrarImagen.setForeground(new Color(0, 51, 0));
		btnBorrarImagen.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBorrarImagen.setBounds(166, 91, 117, 23);
		pn1.add(btnBorrarImagen);
		
		txtExaminar = new JTextField();
		txtExaminar.setBounds(10, 57, 284, 20);
		pn1.add(txtExaminar);
		txtExaminar.setColumns(10);
		
		JButton btnCerrrar = new JButton("Cerrar");
		btnCerrrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrrar.setForeground(new Color(0, 51, 0));
		btnCerrrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrrar.setBounds(293, 91, 85, 23);
		pn1.add(btnCerrrar);
		
		JLabel lblSeleccioneLaImagen = new JLabel("Seleccione la imagen que desea subir en FULL RESOLUCION, el sistema generar\u00E1");
		lblSeleccioneLaImagen.setBounds(10, 11, 451, 14);
		pn1.add(lblSeleccioneLaImagen);
		
		JLabel lblAutomticamenteLaImagen = new JLabel("autom\u00E1ticamente la imagen de la dimensi\u00F3n necesaria.");
		lblAutomticamenteLaImagen.setBounds(70, 27, 336, 14);
		pn1.add(lblAutomticamenteLaImagen);
		
		JButton btnExaminar = new JButton("Examinar");
		btnExaminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				examinarFoto();
			}
		});
		btnExaminar.setForeground(new Color(0, 51, 0));
		btnExaminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExaminar.setBounds(304, 56, 102, 23);
		pn1.add(btnExaminar);
		
		setModal(true);
		this.codigoArticulo = codigoArticulo;
	}

	//Metodo para buscar la foto en el sistema y encontrar su ruta
	private void examinarFoto() {
		JFileChooser fs = new JFileChooser();
		fs.setDialogTitle("Examinar");
		fs.setFileFilter(new FileNameExtensionFilter("PNG File","png"));
		fs.setFileFilter(new FileNameExtensionFilter("JPEG File","jpg"));
		
		int option = fs.showOpenDialog(this);
		
		if(option == JFileChooser.APPROVE_OPTION ){
			File foto = fs.getSelectedFile();
			rutaFoto = foto.getPath();
			nombreFoto = foto.getName();
			txtExaminar.setText(rutaFoto);
		}
	}
	
	//Metodo para colocar la imagen cargada y guardarla en la carpeta de la aplicacion destinada para las imagenes
	private void colocarImagen() {
		
		if(txtExaminar.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Debe presionar el boton examinar para encontrar el origen de la imagen para el articulo");
		}else{
			String rutaParaGuardarEnBD = "/co/com/jungla/sac/presentacion/imagenes/"+nombreFoto;
			String rutaCopiarFoto = cambiarSlashes(System.getProperty("user.dir"))+"/src/co/com/jungla/sac/presentacion/imagenes/"+nombreFoto;
			
			ImageResizer.copyImage(rutaFoto, rutaCopiarFoto);
			ControlInventario articuloParaFoto = delegadoControlInventario.traerContInventarioPorCodigoArticulo(codigoArticulo).get(0);
			articuloParaFoto.setUrlFoto(rutaParaGuardarEnBD);
			delegadoControlInventario.actualizarControlInventario(articuloParaFoto);
			JOptionPane.showMessageDialog(null, "La imagen del articulo ya fue guardada");
			dispose();
		}
	}
	
	//Metodo para eliminar la imagen cargada y guardada en la carpeta imagenes
	private void eliminarImagen(){
		String rutaImagenAEliminar = cambiarSlashes(System.getProperty("user.dir"))+"/src"+delegadoControlInventario.traerContInventarioPorCodigoArticulo(codigoArticulo).get(0).getUrlFoto();
		File ruta = new File( rutaImagenAEliminar ); 
		if ( ruta.delete() ) {
			JOptionPane.showMessageDialog(null, "La imagen del articulo ya fue eliminada");
		}else{
			JOptionPane.showMessageDialog(null, "La imagen del articulo no puede eliminarse porque ya se elimino o nunca fue grabada");
		}
	}
	
	//Metodo para reemplazar los slashes por los backslashes
	private String cambiarSlashes(String ruta){
		String rutaReemplazada=ruta.replace("\\", "/");
		return rutaReemplazada;
	}
}
