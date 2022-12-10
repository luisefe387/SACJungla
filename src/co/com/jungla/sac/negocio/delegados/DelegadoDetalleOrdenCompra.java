package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbDetalleOrdenCompraRemote;
import co.com.jungla.sac.persistencia.entidades.DetalleOrdenCompra;

public class DelegadoDetalleOrdenCompra implements EjbDetalleOrdenCompraRemote{
	
	EjbDetalleOrdenCompraRemote ejbDetalleOrdenCompraRemote;

	public void insertarDetalleOrdenCompra(DetalleOrdenCompra detalleOrdenCompra) {
		ejbDetalleOrdenCompraRemote
				.insertarDetalleOrdenCompra(detalleOrdenCompra);
	}

	public void actualizarDetalleOrdenCompra(
			DetalleOrdenCompra detalleOrdenCompra) {
		ejbDetalleOrdenCompraRemote
				.actualizarDetalleOrdenCompra(detalleOrdenCompra);
	}

	public void eliminarDetalleOrdenCompra(DetalleOrdenCompra detalleOrdenCompra) {
		ejbDetalleOrdenCompraRemote
				.eliminarDetalleOrdenCompra(detalleOrdenCompra);
	}

	public List<DetalleOrdenCompra> listarDetalleOrdenCompra() {
		return ejbDetalleOrdenCompraRemote.listarDetalleOrdenCompra();
	}

	public List<DetalleOrdenCompra> listarDetalleOrdenPorCodigoOrden(int codigo) {
		return ejbDetalleOrdenCompraRemote.listarDetalleOrdenPorCodigoOrden(codigo);
	}

	public DelegadoDetalleOrdenCompra() {
		try {
			ejbDetalleOrdenCompraRemote = (EjbDetalleOrdenCompraRemote) new InitialContext().lookup("java:global/SACJungla/EjbDetalleOrdenCompra!co.com.jungla.sac.negocio.ejb.EjbDetalleOrdenCompraRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
