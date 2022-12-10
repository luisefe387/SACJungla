package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbDetalleEntradaOSalidaRemote;
import co.com.jungla.sac.persistencia.entidades.DetalleEntradaOSalida;

public class DelegadoDetalleEntradaOSalida implements EjbDetalleEntradaOSalidaRemote {

	EjbDetalleEntradaOSalidaRemote ejbDetalleEntradaOSalidaRemote;

	public void insertarDetalleEntradaOSalida(DetalleEntradaOSalida detalleEntradaOSalida) {
		ejbDetalleEntradaOSalidaRemote.insertarDetalleEntradaOSalida(detalleEntradaOSalida);
	}

	public void actualizarDetalleEntradaOSalida(DetalleEntradaOSalida detalleEntradaOSalida) {
		ejbDetalleEntradaOSalidaRemote.actualizarDetalleEntradaOSalida(detalleEntradaOSalida);
	}

	public void eliminarDetalleEntradaOSalida(DetalleEntradaOSalida detalleEntradaOSalida) {
		ejbDetalleEntradaOSalidaRemote.eliminarDetalleEntradaOSalida(detalleEntradaOSalida);
	}

	public List<DetalleEntradaOSalida> listarDetalleEntradaOSalida() {
		return ejbDetalleEntradaOSalidaRemote.listarDetalleEntradaOSalida();
	}

	public DetalleEntradaOSalida encontarPorDetalleEntradaOSalida(String idDetalleEntradaOSalida) {
		return ejbDetalleEntradaOSalidaRemote.encontarPorDetalleEntradaOSalida(idDetalleEntradaOSalida);
	}
	
	public DetalleEntradaOSalida traerUltimoDetalleEntradaOSalida() {
		return ejbDetalleEntradaOSalidaRemote.traerUltimoDetalleEntradaOSalida();
	}

	public DelegadoDetalleEntradaOSalida() {
		try {
			ejbDetalleEntradaOSalidaRemote = (EjbDetalleEntradaOSalidaRemote) new InitialContext().lookup("java:global/SACJungla/EjbDetalleEntradaOSalida!co.com.jungla.sac.negocio.ejb.EjbDetalleEntradaOSalidaRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
}
