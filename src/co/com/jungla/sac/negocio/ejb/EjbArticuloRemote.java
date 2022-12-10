package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.Articulo;

@Remote
public interface EjbArticuloRemote {

	public void insertarArticulo(Articulo articulo);
	
	public void actualizarArticulo(Articulo articulo);
	
	public void eliminarArticulo(Articulo articulo);
	
	public List<Articulo> listarArticulo();
	
	public List<Articulo> traerLineaUnidadArticulo(String descripcion);
	
	public List<Articulo> traerLineaUnidadArticuloPorCodigo(int codigo);
	
	public List <Articulo> traerArticulosPorLineaArticulos(String nombreLinea );
	
	public List <Articulo> traerUltimoCodigoArticulo();
	
	public List <Articulo> traerArticuloPorNombre(String nombre);
	
	public List <Articulo> traerArticuloPorReferencia(String referencia);
	
	public Object contarArticulosPorLineaArticulos(String nombreL);
	
	public List <Articulo> traerTodosArticulosYOrdenarPorNombreLinea();
	
	public List <Articulo> traerArticulosPorLineaYOrdenarPorNombreLinea(String nombreL);
	
	public List <Articulo> traerTodosArticulosYOrdenarPorNombreArticulo();
	
	public List <Articulo> traerArticulosPorLineaYOrdenarPorNombreArticulo(String nombreL);
}
