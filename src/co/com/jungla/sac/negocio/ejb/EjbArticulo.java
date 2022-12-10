package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoArticulo;
import co.com.jungla.sac.persistencia.entidades.Articulo;

/**
 * Session Bean implementation class EjbArticulo
 */
@Stateless
@LocalBean
public class EjbArticulo implements EjbArticuloRemote {

    /**
     * Default constructor. 
     */
	
	@EJB
	DaoArticulo daoArticulo;
	
    public EjbArticulo() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarArticulo(Articulo articulo) {
		daoArticulo.insertar(articulo);
	}

	@Override
	public void actualizarArticulo(Articulo articulo) {
		daoArticulo.actualizar(articulo);
	}

	@Override
	public void eliminarArticulo(Articulo articulo) {
		daoArticulo.eliminar(articulo);
	}

	@Override
	public List<Articulo> listarArticulo() {
		List<Articulo> articulos = daoArticulo.listarTodo();
		return articulos;
	}

	@Override
	public List<Articulo> traerLineaUnidadArticulo(String descripcion) {
		List<Articulo> consulta = daoArticulo.traerLineaUnidadArticulo(descripcion);
		return consulta;
	}

	@Override
	public List<Articulo> traerLineaUnidadArticuloPorCodigo(int codigo) {
		List<Articulo> consulta = daoArticulo.traerLineaUnidadArticuloPorCodigo(codigo);
		return consulta;
	}

	@Override
	public List<Articulo> traerArticulosPorLineaArticulos(String nombreLinea) {
		List<Articulo> articulos = daoArticulo.traerArticulosPorLineaArticulos(nombreLinea);
		return articulos;
	}

	@Override
	public List<Articulo> traerUltimoCodigoArticulo() {
		List<Articulo> articulos = daoArticulo.traerUltimoCodigoArticulo();
		return articulos;
	}

	@Override
	public List<Articulo> traerArticuloPorNombre(String nombre) {
		List<Articulo> articulos = daoArticulo.traerArticuloPorNombre(nombre);
		return articulos;
	}

	@Override
	public List<Articulo> traerArticuloPorReferencia(String referencia) {
		List<Articulo> articulos = daoArticulo.traerArticuloPorReferencia(referencia);
		return articulos;
	}

	@Override
	public Object contarArticulosPorLineaArticulos(String nombreL) {
		Object articulos = daoArticulo.contarArticulosPorLineaArticulos(nombreL);
		return articulos;
	}

	@Override
	public List<Articulo> traerTodosArticulosYOrdenarPorNombreLinea() {
		List<Articulo> articulos = daoArticulo.traerTodosArticulosYOrdenarPorNombreLinea();
		return articulos;
	}

	@Override
	public List<Articulo> traerArticulosPorLineaYOrdenarPorNombreLinea(String nombreL) {
		List<Articulo> articulos = daoArticulo.traerArticulosPorLineaYOrdenarPorNombreLinea(nombreL);
		return articulos;
	}

	@Override
	public List<Articulo> traerTodosArticulosYOrdenarPorNombreArticulo() {
		List<Articulo> articulos = daoArticulo.traerTodosArticulosYOrdenarPorNombreArticulo();
		return articulos;
	}

	@Override
	public List<Articulo> traerArticulosPorLineaYOrdenarPorNombreArticulo(String nombreL) {
		List<Articulo> articulos = daoArticulo.traerArticulosPorLineaYOrdenarPorNombreArticulo(nombreL);
		return articulos;
	}
}
