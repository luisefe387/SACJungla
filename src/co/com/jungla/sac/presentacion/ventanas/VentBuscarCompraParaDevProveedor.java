package co.com.jungla.sac.presentacion.ventanas;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import co.com.jungla.sac.persistencia.entidades.CompraArticulos;
import co.com.jungla.sac.persistencia.entidades.DetalleCompra;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;

import javax.swing.JTextPane;
import javax.swing.JSeparator;

import co.com.jungla.sac.negocio.delegados.DelegadoCompraArticulos;
import co.com.jungla.sac.negocio.delegados.DelegadoDetalleCompra;

public class VentBuscarCompraParaDevProveedor extends JFrame {

	private JPanel contentPane;
	private JTextField txtCompraArt;

	/**
	 * Create the frame.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentBuscarCompraParaDevProveedor frame = new VentBuscarCompraParaDevProveedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentBuscarCompraParaDevProveedor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentBuscarCompraParaDevProveedor.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Devoluciones a Proveedores x Compra");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 541, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCompraArt = new JLabel("Compra Art. #.");
		lblCompraArt.setBackground(new Color(153, 204, 153));
		lblCompraArt.setOpaque(true);
		lblCompraArt.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCompraArt.setBounds(10, 22, 107, 22);
		contentPane.add(lblCompraArt);
		 
		JButton btnGuardarCxP = new JButton("Continuar");
		btnGuardarCxP.setForeground(new Color(0, 51, 0));
		btnGuardarCxP.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGuardarCxP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarRegitro();
			}
		});
		btnGuardarCxP.setBounds(197, 68, 117, 23);
		contentPane.add(btnGuardarCxP);
		
		txtCompraArt = new JTextField();
		txtCompraArt.setBounds(119, 23, 138, 20);
		txtCompraArt.setDocument(new LimitadorCaracteres());
		contentPane.add(txtCompraArt);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 51, 0));
		separator.setBounds(10, 55, 504, 2);
		contentPane.add(separator);
		
		JTextPane txtpnLasDevolucionesSe = new JTextPane();
		txtpnLasDevolucionesSe.setContentType("text/html");
		txtpnLasDevolucionesSe.setText("<FONT FACE=\"Tahoma\" SIZE= 3><br>Las <b>DEVOLUCIONES</b> se deben hacer en <b>2 casos</b> principalmente:<br>\r\n\r\n   <OL> <LI><p align=\"justify\"><FONT COLOR=\"red\"><b>CAMBIO DE MERCANCIA:</b></FONT> Usted hizo una compra de <b>CONTADO</b> al proveedor y desea CAMBIAR uno de los art\u00EDculos por uno que est\u00E1 codificado DIFERENTE en el sistema. Las razones del cambio pueden ser por tallaje, gustos, inconformidad, etc.</p><br>\r\n   <LI><p align=\"justify\"> <FONT COLOR=\"red\"><b>DEVOLUCION DE MERCANCIA:</b></FONT> Usted hizo una compra a <b>CREDITO</b> al proveedor y ahora desea DEVOLVER uno de los art\u00EDculos por que se lo despacharon mal, o por no cumplir las especificaciones, o por averias, etc. En este caso se usa la devoluci\u00F3n con el objetivo de disminuir el SALDO de la CXP al aplicar la NOTA DEBITO resultante. </p></OL></FONT>");
		txtpnLasDevolucionesSe.setBounds(10, 102, 504, 211);
		contentPane.add(txtpnLasDevolucionesSe);
		
	}
	
	//Metodo que valida los datos ingresados para su posterior registro 
	private void validarRegitro() {
		if("".equals(txtCompraArt.getText())){
			JOptionPane.showMessageDialog(null, "Debe digitar el numero de la compra en la que ser realizara la devolución");
		}else{
			cargarADevolucionProveedor(Integer.parseInt(txtCompraArt.getText()));
		}
	}
	
	//Metodo para cargar los datos de la compra a la ventana de devolucion a proveedor
	public void cargarADevolucionProveedor(int idCompra){

		try{
			DelegadoCompraArticulos delegadoCompraArticulos = new DelegadoCompraArticulos();
			VentRegistrarDevolucionProveedor ventRegistrarDevolucionProveedor = new VentRegistrarDevolucionProveedor();
			CompraArticulos datosCompra= delegadoCompraArticulos.traerCompraPorCodigo(idCompra);
			DelegadoDetalleCompra delegadoDetalleCompra = new DelegadoDetalleCompra();
			List <DetalleCompra> detallesCompra= delegadoDetalleCompra.listarDetallePorCodigoCompra(datosCompra.getIdCompra());
			
			ventRegistrarDevolucionProveedor.agregarDatosADevolucionProveedor(datosCompra.getIdCompra(), datosCompra.getProveedores().getNombre(), datosCompra.getProveedores().getIdentificacion(),detallesCompra, datosCompra.getTotalCompra(), datosCompra.getItem());
			ventRegistrarDevolucionProveedor.setVisible(true);
			
			txtCompraArt.setText("");
			this.dispose();
			
			
		}catch(Exception e){
			e.getMessage();
			JOptionPane.showMessageDialog(null, "La COMPRA N° "+txtCompraArt.getText()+" No existe");
			txtCompraArt.setText("");
		}
	}
}
