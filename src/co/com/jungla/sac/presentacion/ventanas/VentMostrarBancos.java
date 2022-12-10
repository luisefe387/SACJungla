package co.com.jungla.sac.presentacion.ventanas;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import co.com.jungla.sac.persistencia.entidades.Banco;

import javax.swing.border.LineBorder;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import co.com.jungla.sac.negocio.delegados.DelegadoBanco;

public class VentMostrarBancos extends JFrame {

	private JPanel contentPane;
	private JTable tbBancos;
	private JTextField txtNumeroRegistros;
	private DefaultTableModel modeloTbBancos = new DefaultTableModel();
	private List<Banco> listaBancos;
	
	private int filaSeleccionada;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentMostrarBancos frame = new VentMostrarBancos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentMostrarBancos() {
		setTitle("Listado de Bancos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 952, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn1.setBounds(10, 11, 924, 353);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JScrollPane scrBancos = new JScrollPane();
		scrBancos.setBounds(2, 2, 920, 349);
		pn1.add(scrBancos);
		
		tbBancos = new JTable();
		tbBancos.setToolTipText("Puede seleccionar cualquiera de las filas y presionar clic derecho para ver las acciones que puede realizar.");
		tbBancos.setEnabled(false);
		tbBancos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
		        filaSeleccionada = tbBancos.rowAtPoint(point);
		        tbBancos.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		        mostrarPopupEnTabla();
			}
		});
		scrBancos.setViewportView(tbBancos);
		
		
		
		JPanel pn2 = new JPanel();
		pn2.setBorder(new LineBorder(new Color(0, 51, 0)));
		pn2.setBounds(10, 375, 924, 61);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JPanel pnNumeroRegistros = new JPanel();
		pnNumeroRegistros.setLayout(null);
		pnNumeroRegistros.setBackground(new Color(0, 51, 0));
		pnNumeroRegistros.setBounds(241, 11, 150, 38);
		pn2.add(pnNumeroRegistros);
		
		JLabel lblNumeroRegistros = new JLabel("Numero de registros");
		lblNumeroRegistros.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNumeroRegistros.setForeground(Color.WHITE);
		lblNumeroRegistros.setBackground(SystemColor.desktop);
		lblNumeroRegistros.setBounds(18, 0, 128, 14);
		pnNumeroRegistros.add(lblNumeroRegistros);
		
		txtNumeroRegistros = new JTextField();
		txtNumeroRegistros.setColumns(10);
		txtNumeroRegistros.setBounds(0, 18, 150, 20);
		txtNumeroRegistros.setEditable(false);
		pnNumeroRegistros.add(txtNumeroRegistros);
		
		JButton btnExportar = new JButton("Exportar a Excel");
		btnExportar.setForeground(new Color(0, 51, 0));
		btnExportar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExportar.setBounds(422, 18, 140, 23);
		pn2.add(btnExportar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setForeground(new Color(0, 51, 0));
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVolver.setBounds(598, 18, 104, 23);
		pn2.add(btnVolver);
		
		llenarTabla();
		calcularCantidadItems();
	}
	
	//Metodo para llenar la cabecera de la tabla con sus nombres correspondientes
	private void llenarColumnasTbLineas() {
		modeloTbBancos.addColumn("Codigo");
		modeloTbBancos.addColumn("Entidad");
		modeloTbBancos.addColumn("Cuenta");
		modeloTbBancos.addColumn("Tipo");
		modeloTbBancos.addColumn("Titular");
		modeloTbBancos.addColumn("Saldo Actual");
		modeloTbBancos.addColumn("Estado");
		
		tbBancos.setModel(modeloTbBancos);
	}
	
	//Metodo para llenar la tabla cuando recibe como parametro una lista de lineas articulos
	private void llenarTabla() {

		DelegadoBanco delegadoBanco = new DelegadoBanco();
		
		listaBancos = delegadoBanco.listarBanco();
		
		if(tbBancos.getModel().getColumnCount()==0){
			llenarColumnasTbLineas();
		}
		
		String [] fila = new String[modeloTbBancos.getColumnCount()];
		
		for(Banco bancos : listaBancos){
			
			fila[0]= String.valueOf(bancos.getIdBanco());
			fila[1]= bancos.getEntidad();
			fila[2]= bancos.getNroCuenta();
			fila[3]= bancos.getTipoCuenta();
			fila[4]= bancos.getNombreTitular();
			fila[5]= formatearNumero(bancos.getSaldoActual());
			fila[6]= bancos.getEstado();
			
			modeloTbBancos.addRow(fila);
		}
		
		tbBancos.setModel(modeloTbBancos);
	}

	//Metodo que permite calcular la cantidad de registros
	private void calcularCantidadItems() {
		
		int cantidad = tbBancos.getRowCount();
		txtNumeroRegistros.setText(Integer.toString(cantidad));
		
	}
	
	//Metodo para mostrar el popup en la tabla	
	private void mostrarPopupEnTabla(){
		JPopupMenu pmBancos = new JPopupMenu();
		JMenuItem miModificar = new JMenuItem("Modificar");
		
		pmBancos.add(miModificar);
		miModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarBancoParaModificar();
			}
		});
			
		tbBancos.setComponentPopupMenu(pmBancos);	
	}
	
	//Metodo para enviar los datos del articulo a la ventana modificar articulo
	private void enviarBancoParaModificar() {
		VentModificarBanco ventModificarBanco = new VentModificarBanco();
		Banco bancoElegido = listaBancos.get(filaSeleccionada);
		ventModificarBanco.agregarDatos(bancoElegido);
		ventModificarBanco.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
            	limpiarTabla();
            	llenarTabla();
            }
        });
		ventModificarBanco.setVisible(true);
		
	}
		
	//Metodo para limpiar la tabla 
	private void limpiarTabla(){
       for (int i = 0; i < tbBancos.getRowCount(); i++) {
    	   modeloTbBancos.removeRow(i);
           i-=1;
       }
	}
	
	//Metodo para convertir un numero corriente en formato de pesos y decimales
	private String formatearNumero(Double numero){
		NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es","CO"));
		return formato.format(numero); 
	}
}
