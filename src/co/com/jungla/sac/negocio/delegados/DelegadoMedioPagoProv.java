package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbMedioPagoProvRemote;
import co.com.jungla.sac.persistencia.entidades.MedioPagoProv;

public class DelegadoMedioPagoProv implements EjbMedioPagoProvRemote{

	EjbMedioPagoProvRemote ejbMedioPagoProvRemote;

	public void insertarFormaPago(MedioPagoProv formaPago) {
		ejbMedioPagoProvRemote.insertarFormaPago(formaPago);
	}

	public void actualizarFormaPago(MedioPagoProv formaPago) {
		ejbMedioPagoProvRemote.actualizarFormaPago(formaPago);
	}

	public void eliminarFormaPago(MedioPagoProv formaPago) {
		ejbMedioPagoProvRemote.eliminarFormaPago(formaPago);
	}

	public List<MedioPagoProv> listarFormaPago() {
		return ejbMedioPagoProvRemote.listarFormaPago();
	}
	
public DelegadoMedioPagoProv() {
		
		try {
			ejbMedioPagoProvRemote = (EjbMedioPagoProvRemote) new InitialContext().lookup("java:global/SACJungla/EjbMedioPagoProv!co.com.jungla.sac.negocio.ejb.EjbMedioPagoProvRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}