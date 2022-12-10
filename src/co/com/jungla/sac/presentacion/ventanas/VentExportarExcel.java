package co.com.jungla.sac.presentacion.ventanas;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JTextField;

/**
 * clase ventana para definir los atributos y metodos tanto para la construccion de la ventana como para las funcionalidades para
 *  llevar a cabo la exportacion a excel sobre la tabla elegida
 * @author Luis Fernando Pedroza T.
 * @version: 03/09/2016
 */
public class VentExportarExcel extends JDialog {

	/*Atributos para construir la ventana*/
	private JPanel contentPane;
	private JTextField txtExaminar;
	private JButton btnGuardar;
	private JTable tbGeneral;

	/*Atributos para ejecutar las funcionalidades de la ventana*/
	private String nombreArchivo;

	/**
	 * Metodo constructor sin parametros para inicicalizar todos los elementos de la ventana y funcionalidades
	 */
	public VentExportarExcel(JTable tbGeneral, String nombreArchivo) {
		setTitle("Guardar Archivo Excel");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 475, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 445, 106);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarAExcel();
			}
		});
		btnGuardar.setForeground(new Color(0, 51, 0));
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGuardar.setBounds(41, 57, 146, 23);
		pn1.add(btnGuardar);
		
		txtExaminar = new JTextField();
		txtExaminar.setBounds(10, 23, 284, 20);
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
		btnCerrrar.setBounds(209, 57, 85, 23);
		pn1.add(btnCerrrar);
		
		JButton btnExaminar = new JButton("Examinar");
		btnExaminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				examinarExcel();
			}
		});
		btnExaminar.setForeground(new Color(0, 51, 0));
		btnExaminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExaminar.setBounds(304, 22, 102, 23);
		pn1.add(btnExaminar);
		
		setModal(true);
		this.tbGeneral = tbGeneral;
		this.nombreArchivo = nombreArchivo;
	}

	//Metodo para buscar el destino del archivo a guardar
	private void examinarExcel() {
		JFileChooser fs = new JFileChooser();
		fs.setDialogTitle("Examinar");
		fs.setFileFilter(new FileNameExtensionFilter("EXCEL File","xls"));
		fs.setSelectedFile(new File(nombreArchivo));
		
		int option = fs.showSaveDialog(this);
		
		if(option == JFileChooser.APPROVE_OPTION ){
			File archivoExcel = fs.getSelectedFile();
			String rutaArchivo = archivoExcel.toString();

			txtExaminar.setText(rutaArchivo);
		}
	}
	
	//Metodo para guardar el archivo en formato excel
	private void guardarAExcel() {
		
		if(txtExaminar.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Debe presionar el boton examinar para buscar el destino de este archivo a guardar");
		}else{
			
			 try {      	 
		           List<JTable> tabla = new ArrayList<JTable>();
		           tabla.add(tbGeneral);
		           //-------------------
		           ExportacionAExcel reporteExcel = new ExportacionAExcel(tabla, new File(txtExaminar.getText()+".xls"));
		           if (reporteExcel.export()) {
		               JOptionPane.showMessageDialog(null, "El archivo fue exportado a Excel y guardado en la ruta especificada");
		           }
		       } catch (Exception ex) {
		           ex.printStackTrace();
		       }
		         convertirAExcel();
		         dispose();
		}
	}
	
	//Metodo para convertir el archivo a excel
	public void convertirAExcel()
	 {
		 try {
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+txtExaminar.getText()+".xls");
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
	 }
}
