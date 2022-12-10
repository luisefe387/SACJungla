package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbArticuloRemote;
import co.com.jungla.sac.persistencia.entidades.Articulo;

public class DelegadoArticulo implements EjbArticuloRemote{
	
	EjbArticuloRemote ejbArticuloRemote;

	public void insertarArticulo(Articulo articulo) {
		ejbArticuloRemote.insertarArticulo(articulo);
	}

	public void actualizarArticulo(Articulo articulo) {
		ejbArticuloRemote.actualizarArticulo(articulo);
	}

	public void eliminarArticulo(Articulo articulo) {
		ejbArticuloRemote.eliminarArticulo(articulo);
	}

	public List<Articulo> listarArticulo() {
		return ejbArticuloRemote.listarArticulo();
	}
	
	public List<Articulo> traerLineaUnidadArticulo(String descripcion) {
		return ejbArticuloRemote.traerLineaUnidadArticulo(descripcion);
	}

	public List<Articulo> traerLineaUnidadArticuloPorCodigo(int codigo) {
		return ejbArticuloRemote.traerLineaUnidadArticuloPorCodigo(codigo);
	}
	
	public List<Articulo> traerArticulosPorLineaArticulos(String nombreLinea) {
		return ejbArticuloRemote.traerArticulosPorLineaArticulos(nombreLinea);
	}

	public List<Articulo> traerUltimoCodigoArticulo() {
		return ejbArticuloRemote.traerUltimoCodigoArticulo();
	}

	public List<Articulo> traerArticuloPorNombre(String nombre) {
		return ejbArticuloRemote.traerArticuloPorNombre(nombre);
	}

	public List<Articulo> traerArticuloPorReferencia(String referencia) {
		return ejbArticuloRemote.traerArticuloPorReferencia(referencia);
	}
	
	public Object contarArticulosPorLineaArticulos(String nombreL) {
		return ejbArticuloRemote.contarArticulosPorLineaArticulos(nombreL);
	}
	
	public List<Articulo> traerTodosArticulosYOrdenarPorNombreLinea() {
		return ejbArticuloRemote.traerTodosArticulosYOrdenarPorNombreLinea();
	}

	public List<Articulo> traerArticulosPorLineaYOrdenarPorNombreLinea(String nombreL) {
		return ejbArticuloRemote.traerArticulosPorLineaYOrdenarPorNombreLinea(nombreL);
	}

	public List<Articulo> traerTodosArticulosYOrdenarPorNombreArticulo() {
		return ejbArticuloRemote.traerTodosArticulosYOrdenarPorNombreArticulo();
	}

	public List<Articulo> traerArticulosPorLineaYOrdenarPorNombreArticulo(String nombreL) {
		return ejbArticuloRemote.traerArticulosPorLineaYOrdenarPorNombreArticulo(nombreL);
	}

	public DelegadoArticulo() {
		try {
			ejbArticuloRemote = (EjbArticuloRemote) new InitialContext().lookup("java:global/SACJungla/EjbArticulo!co.com.jungla.sac.negocio.ejb.EjbArticuloRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
