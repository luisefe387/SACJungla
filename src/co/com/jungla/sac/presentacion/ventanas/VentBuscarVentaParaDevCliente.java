package co.com.jungla.sac.presentacion.ventanas;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import co.com.jungla.sac.persistencia.entidades.DetalleVenta;
import co.com.jungla.sac.persistencia.entidades.VentaArticulos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;

import javax.swing.JTextPane;
import javax.swing.JSeparator;

import co.com.jungla.sac.negocio.delegados.DelegadoDetalleVenta;
import co.com.jungla.sac.negocio.delegados.DelegadoVentaArticulos;

public class VentBuscarVentaParaDevCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtVentaConsec;

	/**
	 * Create the frame.
	 */
	
	public VentBuscarVentaParaDevCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentBuscarVentaParaDevCliente.class.getResource("/co/com/jungla/sac/presentacion/imagenes/Logo Jungla.png")));
		setTitle("Devoluciones a Clientes x Venta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 541, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVentaConsec = new JLabel("Venta Consec. #.");
		lblVentaConsec.setBackground(new Color(153, 204, 153));
		lblVentaConsec.setOpaque(true);
		lblVentaConsec.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVentaConsec.setBounds(10, 22, 107, 22);
		contentPane.add(lblVentaConsec);
		 
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.setForeground(new Color(0, 51, 0));
		btnContinuar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarRegitro();
			}
		});
		btnContinuar.setBounds(197, 68, 117, 23);
		contentPane.add(btnContinuar);
		
		txtVentaConsec = new JTextField();
		txtVentaConsec.setBounds(119, 23, 138, 20);
		txtVentaConsec.setDocument(new LimitadorCaracteres());
		contentPane.add(txtVentaConsec);
		
		JSeparator sp = new JSeparator();
		sp.setBackground(new Color(0, 51, 0));
		sp.setBounds(10, 55, 504, 2);
		contentPane.add(sp);
		
		JTextPane txtpnLasDevolucionesSe = new JTextPane();
		txtpnLasDevolucionesSe.setContentType("text/html");
		txtpnLasDevolucionesSe.setText("<FONT FACE=\"Tahoma\" SIZE= 3><br>Las <b>DEVOLUCIONES</b> se deben hacer en <b>2 casos</b> principalmente:<br>\r\n\r\n   <OL> <LI><p align=\"justify\"><FONT COLOR=\"red\"><b>CAMBIO DE MERCANCIA:</b></FONT> El cliente hizo una compra de <b>CONTADO</b> y ahora desea CAMBIAR uno de los art\u00EDculos por uno que este codificado DIFERENTE en el sistema. Las razones del cambio pueden ser por tallaje, gustos, inconformidad, etc.</p><br>\r\n   <LI><p align=\"justify\"> <FONT COLOR=\"red\"><b>DEVOLUCION DE MERCANCIA:</b></FONT> El cliente hizo una compra a <b>CREDITO</b> y ahora desea DEVOLVER uno de los art\u00EDculos por que se lo despacharon mal, o por no cumplir las especificaciones, o por averias, etc. En este caso se usa la devoluci\u00F3n con el objetivo de disminuir el SALDO de la FACTURA al aplicar la NOTA CREDITO resultante. </p></OL></FONT>");
		txtpnLasDevolucionesSe.setBounds(10, 102, 504, 211);
		contentPane.add(txtpnLasDevolucionesSe);
		
	}
	
	//Metodo que valida los datos ingresados para su posterior registro 
	private void validarRegitro() {
		if("".equals(txtVentaConsec.getText())){
			JOptionPane.showMessageDialog(null, "Debe digitar el numero de la venta en la que se realizara la devolución");
		}else{
			cargarADevolucionCliente(Integer.parseInt(txtVentaConsec.getText()));
		}
	}
	
	//Metodo para cargar los datos de la venta a la ventana de devolucion a cliente
	public void cargarADevolucionCliente(int idVenta){

		//try{
			DelegadoVentaArticulos delegadoVentaArticulos = new DelegadoVentaArticulos();
			VentRegistrarDevolucionCliente ventRegistrarDevolucionCliente = new VentRegistrarDevolucionCliente();
			VentaArticulos datosVenta= delegadoVentaArticulos.traerVentaPorCodigo(idVenta).get(0);
			DelegadoDetalleVenta delegadoDetalleVenta = new DelegadoDetalleVenta();
			List <DetalleVenta> detallesVenta= delegadoDetalleVenta.listarDetallePorCodigoVenta(datosVenta.getIdVenta());
			
			ventRegistrarDevolucionCliente.agregarDatosADevolucionCliente(datosVenta.getIdVenta(), datosVenta.getClientes().getNombre(), datosVenta.getClientes().getIdentificacion(),detallesVenta, datosVenta.getTotalVenta(), datosVenta.getItems(), datosVenta.getFechaGeneracion());
			ventRegistrarDevolucionCliente.setVisible(true);
			
			txtVentaConsec.setText("");
			this.dispose();
			
			
		/*}catch(Exception e){
			e.getMessage();
			JOptionPane.showMessageDialog(null, "La VENTA N° "+txtVentaConsec.getText()+" No existe");
			txtVentaConsec.setText("");
		}*/
	}
}
