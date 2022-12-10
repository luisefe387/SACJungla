package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.MedioPagoCliente;

@Remote
public interface EjbMedioPagoClienteRemote {

	public void insertarMedioPago(MedioPagoCliente medioPago);
	
	public void actualizarMedioPago(MedioPagoCliente medioPago);
	
	public void eliminarMedioPago(MedioPagoCliente medioPago);
	
	public List<MedioPagoCliente> listarMedioPago();
	
	public List <MedioPagoCliente> listarMedioPagosPorCodigoReciboCaja(int idReciboCaja);
}
