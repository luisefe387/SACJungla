package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbDetalleVentaRemote;
import co.com.jungla.sac.persistencia.entidades.DetalleVenta;

public class DelegadoDetalleVenta implements EjbDetalleVentaRemote{
	
	EjbDetalleVentaRemote ejbDetalleVentaRemote;

	public void insertarDetalleVenta(DetalleVenta detalleVenta) {
		ejbDetalleVentaRemote.insertarDetalleVenta(detalleVenta);
	}

	public void actualizarDetalleVenta(DetalleVenta detalleVenta) {
		ejbDetalleVentaRemote.actualizarDetalleVenta(detalleVenta);
	}

	public void eliminarDetalleVenta(DetalleVenta detalleVenta) {
		ejbDetalleVentaRemote.eliminarDetalleVenta(detalleVenta);
	}

	public List<DetalleVenta> listarDetalleVenta() {
		return ejbDetalleVentaRemote.listarDetalleVenta();
	}
	
	public List<DetalleVenta> listarDetallePorCodigoVenta(int idVenta) {
		return ejbDetalleVentaRemote.listarDetallePorCodigoVenta(idVenta);
	}
	
	public DetalleVenta traerUltimoRegistroDetalleVenta() {
		return ejbDetalleVentaRemote.traerUltimoRegistroDetalleVenta();
	}
	
	public List<DetalleVenta> traerDetallePorCodigoDetalle(int idDetalleVenta) {
		return ejbDetalleVentaRemote.traerDetallePorCodigoDetalle(idDetalleVenta);
	}

	public DelegadoDetalleVenta() {
		try {
			ejbDetalleVentaRemote = (EjbDetalleVentaRemote) new InitialContext().lookup("java:global/SACJungla/EjbDetalleVenta!co.com.jungla.sac.negocio.ejb.EjbDetalleVentaRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
