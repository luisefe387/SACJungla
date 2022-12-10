package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbDetalleDevolucionClienteRemote;
import co.com.jungla.sac.persistencia.entidades.DetalleDevolucionCliente;

public class DelegadoDetalleDevolucionCliente implements EjbDetalleDevolucionClienteRemote{
	
	EjbDetalleDevolucionClienteRemote ejbDetalleDevolucionClienteRemote;

	public void insertarDetalleDevolucionCliente(DetalleDevolucionCliente detalleDevolucionCliente) {
		ejbDetalleDevolucionClienteRemote.insertarDetalleDevolucionCliente(detalleDevolucionCliente);
	}

	public void actualizarDetalleDevolucionCliente(DetalleDevolucionCliente detalleDevolucionCliente) {
		ejbDetalleDevolucionClienteRemote.actualizarDetalleDevolucionCliente(detalleDevolucionCliente);
	}

	public void eliminarDetalleDevolucionCliente(DetalleDevolucionCliente detalleDevolucionCliente) {
		ejbDetalleDevolucionClienteRemote.eliminarDetalleDevolucionCliente(detalleDevolucionCliente);
	}

	public List<DetalleDevolucionCliente> listarDetalleDevolucionCliente() {
		return ejbDetalleDevolucionClienteRemote.listarDetalleDevolucionCliente();
	}
	
	public List <DetalleDevolucionCliente> traerUltimaDetalleDevolucionCliente() {
		return ejbDetalleDevolucionClienteRemote.traerUltimaDetalleDevolucionCliente();
	}
	
	public Object traerCantidadDevuelta(int idVenta, int codigo) {
		return ejbDetalleDevolucionClienteRemote.traerCantidadDevuelta(idVenta,codigo);
	}

	public List<DetalleDevolucionCliente> listarDetalleDevolucionClientePorIdentificacion(String identificacion) {
		return ejbDetalleDevolucionClienteRemote.listarDetalleDevolucionClientePorIdentificacion(identificacion);
	}
	
	public List<DetalleDevolucionCliente> traerDetalleDevolucionClientePorCodigo(int idDetalleDevolucionCliente) {
		return ejbDetalleDevolucionClienteRemote.traerDetalleDevolucionClientePorCodigo(idDetalleDevolucionCliente);
	}
	
	public List<DetalleDevolucionCliente> traerDetalleDevolucionClientePoridVenta(int idVenta) {
		return ejbDetalleDevolucionClienteRemote.traerDetalleDevolucionClientePoridVenta(idVenta);
	}

	
	public List<DetalleDevolucionCliente> listarDetallePorCodigoDevolucionCliente(int idDevolucionCliente) {
		return ejbDetalleDevolucionClienteRemote.listarDetallePorCodigoDevolucionCliente(idDevolucionCliente);
	}

	public DelegadoDetalleDevolucionCliente() {
		try {
			ejbDetalleDevolucionClienteRemote = (EjbDetalleDevolucionClienteRemote) new InitialContext().lookup("java:global/SACJungla/EjbDetalleDevolucionCliente!co.com.jungla.sac.negocio.ejb.EjbDetalleDevolucionClienteRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
