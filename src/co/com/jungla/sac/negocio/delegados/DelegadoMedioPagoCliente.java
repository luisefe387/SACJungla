package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbMedioPagoClienteRemote;
import co.com.jungla.sac.persistencia.entidades.MedioPagoCliente;

public class DelegadoMedioPagoCliente implements EjbMedioPagoClienteRemote{

	EjbMedioPagoClienteRemote ejbMedioPagoClienteRemote;

	public void insertarMedioPago(MedioPagoCliente medioPago) {
		ejbMedioPagoClienteRemote.insertarMedioPago(medioPago);
	}

	public void actualizarMedioPago(MedioPagoCliente medioPago) {
		ejbMedioPagoClienteRemote.actualizarMedioPago(medioPago);
	}

	public void eliminarMedioPago(MedioPagoCliente medioPago) {
		ejbMedioPagoClienteRemote.eliminarMedioPago(medioPago);
	}

	public List<MedioPagoCliente> listarMedioPago() {
		return ejbMedioPagoClienteRemote.listarMedioPago();
	}

	public List<MedioPagoCliente> listarMedioPagosPorCodigoReciboCaja(int idReciboCaja) {
		return ejbMedioPagoClienteRemote.listarMedioPagosPorCodigoReciboCaja(idReciboCaja);
	}

	public DelegadoMedioPagoCliente() {
		
		try {
			ejbMedioPagoClienteRemote = (EjbMedioPagoClienteRemote) new InitialContext().lookup("java:global/SACJungla/EjbMedioPagoCliente!co.com.jungla.sac.negocio.ejb.EjbMedioPagoClienteRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}