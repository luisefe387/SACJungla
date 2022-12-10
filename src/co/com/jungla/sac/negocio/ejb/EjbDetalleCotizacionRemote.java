package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.DetalleCotizacion;

@Remote
public interface EjbDetalleCotizacionRemote {

	public void insertarDetalleCotizacion(DetalleCotizacion detalleCotizacion);
	
	public void actualizarDetalleCotizacion(DetalleCotizacion detalleCotizacion);
	
	public void eliminarDetalleCotizacion(DetalleCotizacion detalleCotizacion);
	
	public List <DetalleCotizacion> listarDetallePorCodigoCotizacion(int idCotizacion);
	
}
