package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoLineaArticulos;
import co.com.jungla.sac.persistencia.entidades.LineaArticulos;

/**
 * Session Bean implementation class EjbLineaArticulos
 */
@Stateless
@LocalBean
public class EjbLineaArticulos implements EjbLineaArticulosRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoLineaArticulos daoLineaArticulos;
	
    public EjbLineaArticulos() {
        
    }

	@Override
	public void insertarLineaArticulos(LineaArticulos lineaArticulos) {
		daoLineaArticulos.insertar(lineaArticulos);
	}

	@Override
	public void actualizarLineaArticulos(LineaArticulos lineaArticulos) {
		daoLineaArticulos.actualizar(lineaArticulos);
	}

	@Override
	public void eliminarLineaArticulos(LineaArticulos lineaArticulos) {
		daoLineaArticulos.eliminar(lineaArticulos);		
	}

	@Override
	public List<LineaArticulos> listarLineaArticulos() {
		List<LineaArticulos> lineaArticulos = daoLineaArticulos.listarTodo();
		return lineaArticulos;
	}

	@Override
	public LineaArticulos traerLineaArticulo(String nombre ) {
		LineaArticulos lineaArticulos = daoLineaArticulos.traerLineaArticulo(nombre);
		return lineaArticulos;
	}
}
