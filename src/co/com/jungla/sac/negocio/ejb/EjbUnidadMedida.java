package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoUnidadMedida;
import co.com.jungla.sac.persistencia.entidades.UnidadMedida;

/**
 * Session Bean implementation class EjbUnidadMedida
 */
@Stateless
@LocalBean
public class EjbUnidadMedida implements EjbUnidadMedidaRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoUnidadMedida daoUnidadMedida;
	
    public EjbUnidadMedida() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarUnidadMedida(UnidadMedida unidadMedida) {
		daoUnidadMedida.insertar(unidadMedida);	
	}

	@Override
	public void actualizarUnidadMedida(UnidadMedida unidadMedida) {
		daoUnidadMedida.actualizar(unidadMedida);
	}

	@Override
	public void eliminarUnidadMedida(UnidadMedida unidadMedida) {
		daoUnidadMedida.eliminar(unidadMedida);
	}

	@Override
	public List<UnidadMedida> listarUnidadMedida() {
		List<UnidadMedida> unidadesMedida = daoUnidadMedida.listarTodo();
		return unidadesMedida;
	}

	@Override
	public UnidadMedida traerUnidadMedida(String nombre) {
		UnidadMedida unidadMedida = daoUnidadMedida.traerUnidadMedida(nombre);
		return unidadMedida;
	}
}
