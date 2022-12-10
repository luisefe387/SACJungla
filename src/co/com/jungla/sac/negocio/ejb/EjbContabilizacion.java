package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoContabilizacion;
import co.com.jungla.sac.persistencia.entidades.Contabilizacion;

/**
 * Session Bean implementation class EjbContabilizacion
 */
@Stateless
@LocalBean
public class EjbContabilizacion implements EjbContabilizacionRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoContabilizacion daoContabilizacion;
	
    public EjbContabilizacion() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarContabilizacion(Contabilizacion contabilizacion) {
		daoContabilizacion.insertar(contabilizacion);
	}

	@Override
	public void actualizarContabilizacion(Contabilizacion contabilizacion) {
		daoContabilizacion.actualizar(contabilizacion);
		
	}

	@Override
	public void eliminarContabilizacion(Contabilizacion contabilizacion) {
		daoContabilizacion.eliminar(contabilizacion);
		
	}

	@Override
	public List<Contabilizacion> listarContabilizacion() {
		List<Contabilizacion> contabilizaciones = daoContabilizacion.listarTodo();
		return contabilizaciones;
	}

}
