package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoEntradaOSalidaArticulo;
import co.com.jungla.sac.persistencia.entidades.EntradaOSalidaArticulo;


/**
 * Session Bean implementation class EjbEntradaOSalidaArticulo
 */
@Stateless
@LocalBean
public class EjbEntradaOSalidaArticulo implements EjbEntradaOSalidaArticuloRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoEntradaOSalidaArticulo daoEntradaOSalidaArticulo;
	
    public EjbEntradaOSalidaArticulo() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarEntradaOSalidaArticulo(EntradaOSalidaArticulo entradaOSalidaArticulo) {
		daoEntradaOSalidaArticulo.insertar(entradaOSalidaArticulo);
	}

	@Override
	public void actualizarEntradaOSalidaArticulo(EntradaOSalidaArticulo entradaOSalidaArticulo) {
		daoEntradaOSalidaArticulo.actualizar(entradaOSalidaArticulo);
	}

	@Override
	public void eliminarEntradaOSalidaArticulo(EntradaOSalidaArticulo entradaOSalidaArticulo) {
		daoEntradaOSalidaArticulo.eliminar(entradaOSalidaArticulo);
	}

	@Override
	public List<EntradaOSalidaArticulo> listarEntradaOSalidaArticulo() {
		List<EntradaOSalidaArticulo> EntradaOSalidaArticulos = daoEntradaOSalidaArticulo.listarTodo();
		return EntradaOSalidaArticulos;
	}

	@Override
	public EntradaOSalidaArticulo encontarPorEntradaOSalidaArticulo(String idEntradaOSalidaArticulo) {
		return daoEntradaOSalidaArticulo.encontrarPorLlave(idEntradaOSalidaArticulo);
	}

	@Override
	public EntradaOSalidaArticulo traerUltimaEntradaOSalida() {
		EntradaOSalidaArticulo entradaOSalidaArticulo = daoEntradaOSalidaArticulo.traerUltimaEntradaOSalida();
		return entradaOSalidaArticulo;
	}
}
