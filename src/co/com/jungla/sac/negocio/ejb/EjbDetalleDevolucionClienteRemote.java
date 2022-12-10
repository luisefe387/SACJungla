package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.DetalleDevolucionCliente;

@Remote
public interface EjbDetalleDevolucionClienteRemote {

	public void insertarDetalleDevolucionCliente(DetalleDevolucionCliente detalleDevolucionCliente);
	
	public void actualizarDetalleDevolucionCliente(DetalleDevolucionCliente detalleDevolucionCliente);
	
	public void eliminarDetalleDevolucionCliente(DetalleDevolucionCliente detalleDevolucionCliente);
	
	public List<DetalleDevolucionCliente> listarDetalleDevolucionCliente();
	
	public List <DetalleDevolucionCliente> traerUltimaDetalleDevolucionCliente();
	
	public Object traerCantidadDevuelta(int idVenta, int codigo);
	
	public List <DetalleDevolucionCliente> listarDetalleDevolucionClientePorIdentificacion(String identificacion);
	
	public List <DetalleDevolucionCliente> traerDetalleDevolucionClientePorCodigo(int idDetalleDevolucionCliente);
	
	public List <DetalleDevolucionCliente> traerDetalleDevolucionClientePoridVenta(int idVenta);
	
	public List <DetalleDevolucionCliente> listarDetallePorCodigoDevolucionCliente(int idDevolucionCliente);
	
}
