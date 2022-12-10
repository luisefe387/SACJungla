package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoDetalleCotizacion;
import co.com.jungla.sac.persistencia.entidades.DetalleCotizacion;

/**
 * Session Bean implementation class EjbDetalleCotizacion
 */
@Stateless
@LocalBean
public class EjbDetalleCotizacion implements EjbDetalleCotizacionRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoDetalleCotizacion daoDetalleCotizacion;
	
    public EjbDetalleCotizacion() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarDetalleCotizacion(DetalleCotizacion detalleCotizacion) {
		daoDetalleCotizacion.insertar(detalleCotizacion);
		
	}

	@Override
	public void actualizarDetalleCotizacion(DetalleCotizacion detalleCotizacion) {
		daoDetalleCotizacion.actualizar(detalleCotizacion);
	}

	@Override
	public void eliminarDetalleCotizacion(DetalleCotizacion detalleCotizacion) {
		daoDetalleCotizacion.eliminar(detalleCotizacion);
	}

	@Override
	public List<DetalleCotizacion> listarDetallePorCodigoCotizacion(int idCotizacion) {
		List<DetalleCotizacion> detalleCotizacion = daoDetalleCotizacion.listarDetallePorCodigoCotizacion(idCotizacion);
		return detalleCotizacion;
	}
}
