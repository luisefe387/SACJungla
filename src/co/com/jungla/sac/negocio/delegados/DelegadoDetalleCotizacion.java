package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbDetalleCotizacionRemote;
import co.com.jungla.sac.persistencia.entidades.DetalleCotizacion;

public class DelegadoDetalleCotizacion implements EjbDetalleCotizacionRemote{
	
	EjbDetalleCotizacionRemote ejbDetalleCotizacionRemote;

	public void insertarDetalleCotizacion(DetalleCotizacion detalleCotizacion) {
		ejbDetalleCotizacionRemote.insertarDetalleCotizacion(detalleCotizacion);
	}

	public void actualizarDetalleCotizacion(DetalleCotizacion detalleCotizacion) {
		ejbDetalleCotizacionRemote.actualizarDetalleCotizacion(detalleCotizacion);
	}

	public void eliminarDetalleCotizacion(DetalleCotizacion detalleCotizacion) {
		ejbDetalleCotizacionRemote.eliminarDetalleCotizacion(detalleCotizacion);
	}

	public List<DetalleCotizacion> listarDetallePorCodigoCotizacion(int idCotizacion) {
		return ejbDetalleCotizacionRemote.listarDetallePorCodigoCotizacion(idCotizacion);
	}

	public DelegadoDetalleCotizacion() {
		try {
			ejbDetalleCotizacionRemote = (EjbDetalleCotizacionRemote) new InitialContext().lookup("java:global/SACJungla/EjbDetalleCotizacion!co.com.jungla.sac.negocio.ejb.EjbDetalleCotizacionRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
