package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbDetalleDevolucionProveedorRemote;
import co.com.jungla.sac.persistencia.entidades.DetalleDevolucionProveedor;

public class DelegadoDetalleDevolucionProveedor implements EjbDetalleDevolucionProveedorRemote{
	
	EjbDetalleDevolucionProveedorRemote ejbDetalleDevolucionProveedorRemote;

	public void insertarDetalleDevolucionProveedor(DetalleDevolucionProveedor detalleDevolucionProveedor) {
		ejbDetalleDevolucionProveedorRemote.insertarDetalleDevolucionProveedor(detalleDevolucionProveedor);
	}

	public void actualizarDetalleDevolucionProveedor(DetalleDevolucionProveedor detalleDevolucionProveedor) {
		ejbDetalleDevolucionProveedorRemote.actualizarDetalleDevolucionProveedor(detalleDevolucionProveedor);
	}

	public void eliminarDetalleDevolucionProveedor(DetalleDevolucionProveedor detalleDevolucionProveedor) {
		ejbDetalleDevolucionProveedorRemote.eliminarDetalleDevolucionProveedor(detalleDevolucionProveedor);
	}

	public List<DetalleDevolucionProveedor> listarDetalleDevolucionProveedor() {
		return ejbDetalleDevolucionProveedorRemote.listarDetalleDevolucionProveedor();
	}
	
	public List <DetalleDevolucionProveedor> traerUltimaDetalleDevolucionProveedor() {
		return ejbDetalleDevolucionProveedorRemote.traerUltimaDetalleDevolucionProveedor();
	}
	
	public Object traerCantidadDevuelta(int idCompra, int codigo) {
		return ejbDetalleDevolucionProveedorRemote.traerCantidadDevuelta(idCompra,codigo);
	}

	public List<DetalleDevolucionProveedor> listarDetalleDevolucionProveedorPorIdentificacion(String identificacion) {
		return ejbDetalleDevolucionProveedorRemote.listarDetalleDevolucionProveedorPorIdentificacion(identificacion);
	}
	
	public List<DetalleDevolucionProveedor> traerDetalleDevolucionProveedorPorCodigo(int idDetalleDevolucionProveedor) {
		return ejbDetalleDevolucionProveedorRemote.traerDetalleDevolucionProveedorPorCodigo(idDetalleDevolucionProveedor);
	}
	
	public List<DetalleDevolucionProveedor> traerDetalleDevolucionProveedorPoridCompra(int idCompra) {
		return ejbDetalleDevolucionProveedorRemote.traerDetalleDevolucionProveedorPoridCompra(idCompra);
	}

	public List<DetalleDevolucionProveedor> listarDetallePorCodigoDevolucionProveedor(int idDevolucionProveedor) {
		return ejbDetalleDevolucionProveedorRemote.listarDetallePorCodigoDevolucionProveedor(idDevolucionProveedor);
	}

	public DelegadoDetalleDevolucionProveedor() {
		try {
			ejbDetalleDevolucionProveedorRemote = (EjbDetalleDevolucionProveedorRemote) new InitialContext().lookup("java:global/SACJungla/EjbDetalleDevolucionProveedor!co.com.jungla.sac.negocio.ejb.EjbDetalleDevolucionProveedorRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
