package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbDetalleCompraRemote;
import co.com.jungla.sac.persistencia.entidades.DetalleCompra;

public class DelegadoDetalleCompra implements EjbDetalleCompraRemote{
	
	EjbDetalleCompraRemote ejbDetalleCompraRemote;

	public void insertarDetalleCompra(DetalleCompra detalleCompra) {
		ejbDetalleCompraRemote.insertarDetalleCompra(detalleCompra);
	}

	public void actualizarDetalleCompra(DetalleCompra detalleCompra) {
		ejbDetalleCompraRemote.actualizarDetalleCompra(detalleCompra);
	}

	public void eliminarDetalleCompra(DetalleCompra detalleCompra) {
		ejbDetalleCompraRemote.eliminarDetalleCompra(detalleCompra);
	}

	public List<DetalleCompra> listarDetalleCompra() {
		return ejbDetalleCompraRemote.listarDetalleCompra();
	}
	
	
	public List<DetalleCompra> listarDetallePorCodigoCompra(int idCompra) {
		return ejbDetalleCompraRemote.listarDetallePorCodigoCompra(idCompra);
	}

	
	public List<DetalleCompra> traerUltimoRegistroDetalleCompra() {
		return ejbDetalleCompraRemote.traerUltimoRegistroDetalleCompra();
	}

	public DelegadoDetalleCompra() {
		try {
			ejbDetalleCompraRemote = (EjbDetalleCompraRemote) new InitialContext().lookup("java:global/SACJungla/EjbDetalleCompra!co.com.jungla.sac.negocio.ejb.EjbDetalleCompraRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
