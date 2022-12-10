package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.MedioPagoProv;

@Remote
public interface EjbMedioPagoProvRemote {

	public void insertarFormaPago(MedioPagoProv formaPago);
	
	public void actualizarFormaPago(MedioPagoProv formaPago);
	
	public void eliminarFormaPago(MedioPagoProv formaPago);
	
	public List<MedioPagoProv> listarFormaPago();
}
