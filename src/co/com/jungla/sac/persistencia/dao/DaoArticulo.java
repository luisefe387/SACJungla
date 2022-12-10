package co.com.jungla.sac.persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.Articulo;

/**
 * clase dao con todos los metodos que realizan las consultas correspondientes a los articulos
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoArticulo extends DaoGeneral<Articulo,String> {

	String estado = "Activo";
	
    /**
     * Metodo constructor sin parametros
     */
    public DaoArticulo() {
    }

    /* 
	 * Metodo que permite retornar la clase Articulo
	 */
	@Override
	protected Class<Articulo> getEntityClass() {
		return Articulo.class;
	}
	
	/**
	 * Metodo realizar una consulta en la que recibe el nombre del articulo y retorna los datos de ese articulo
	 * @param nombre, parametro que define el nombre del articulo
	 * @return retorna toda la informacion de ese articulo elegido
	 */
	public List <Articulo> traerLineaUnidadArticulo(String nombre ) {
		TypedQuery<Articulo> consulta = entityManager.createQuery(
				"SELECT a FROM Articulo a INNER JOIN a.controlInventario ci JOIN a.lineaArticulos l INNER JOIN a.unidadMedida u WHERE a.nombre = :nombre",
				Articulo.class);
		consulta.setParameter("nombre", nombre);
		return (List<Articulo>) consulta.getResultList();
	}
	
	/**
	 * Metodo que realiza una consulta para traer los datos del articulo mediante el codigo del articulo
	 * @param codigo, parametro que define el codigo del articulo
	 * @return retorna un lista de articulos con toda la informacion de ese articulo elegido
	 */
	public List <Articulo> traerLineaUnidadArticuloPorCodigo(int codigo ) {
		TypedQuery<Articulo> consulta = entityManager.createQuery(
				"SELECT a FROM Articulo a JOIN a.lineaArticulos l JOIN a.unidadMedida u JOIN a.controlInventario ci WHERE a.codigo = :codigo",
				Articulo.class);
		consulta.setParameter("codigo", codigo);
		return (List<Articulo>) consulta.getResultList();
	}
	
	/**
	 * Metodo que permite traer la informacion de los articulos por medio del nombre de la linea de articulos
	 * @param nombreL, parametro que define el nombre de la linea de articulos
	 * @return una lista de articulos encontrados
	 */
	public List <Articulo> traerArticulosPorLineaArticulos(String nombreL ) {
		TypedQuery<Articulo> consulta = entityManager.createQuery(
				"SELECT a FROM LineaArticulos l INNER JOIN l.articulos a WHERE l.nombreL = :nombreL",
				Articulo.class);
		consulta.setParameter("nombreL", nombreL);
		return (List<Articulo>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer mediante una consulta la informacion del ultimo articulo registrado
	 * @return una lista con la informacion del ultimo articulo
	 */
	public List <Articulo> traerUltimoCodigoArticulo() {
		TypedQuery<Articulo> consulta = entityManager.createQuery(
				"SELECT a FROM Articulo a order by a.codigo desc", Articulo.class);
		return (List<Articulo>) consulta.getResultList();
	}
	
	/**
	 * Metodo que con una consulta obtiene el articulo por nombre, encontrando aquellos articulos que concuerden con cualquier 
	 * palabra del nombre definido en el parametro
	 * @param nombre, parametro que define el nombre del articulo
	 * @return retorna una lista de tipo Articulo con los articulos encontrados de acuerdo al filtro de busqueda establecido
	 */
	public List <Articulo> traerArticuloPorNombre(String nombre) {
		TypedQuery<Articulo> consulta = entityManager.createQuery(
				"SELECT a FROM Articulo a WHERE a.nombre LIKE :nombre", Articulo.class);
		consulta.setParameter("nombre", "%" + nombre + "%");
		return (List<Articulo>) consulta.getResultList();
	}
	
	/**
	 * Metodo que con una consulta obtiene el articulo por referencia, encontrando aquellos articulos que concuerden con cualquier 
	 * palabra de la referencia definida en el parametro
	 * @param referencia, parametro de tipo string que define la referencia del articulo
	 * @return una lista con los articulos encontrados
	 */
	public List <Articulo> traerArticuloPorReferencia(String referencia) {
		TypedQuery<Articulo> consulta = entityManager.createQuery(
				"SELECT a FROM Articulo a WHERE a.referencia LIKE :referencia", Articulo.class);
		consulta.setParameter("referencia", "%" + referencia + "%");
		return (List<Articulo>) consulta.getResultList();
	}
	
	/**
	 * Metodo que permite saber cuantos articulos existen en cada linea de articulos.
	 * @param nombreL, parametro que define el nombre de la linea
	 * @return un objetc con el numero de articulos contados por linea
	 */
	public Object contarArticulosPorLineaArticulos(String nombreL ) {
		TypedQuery<Articulo> consulta = entityManager.createQuery(
				"SELECT count(a) FROM LineaArticulos l INNER JOIN l.articulos a WHERE l.nombreL = :nombreL",
				Articulo.class);
		consulta.setParameter("nombreL", nombreL);
		return (Object) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer mediante una consulta la informacion de todos los articulos ordenandolos por nombre de linea
	 * @return una lista con la informacion del los articulos ordenados por el nombre de la linea
	 */
	public List <Articulo> traerTodosArticulosYOrdenarPorNombreLinea() {
		TypedQuery<Articulo> consulta = entityManager.createQuery(
				"SELECT a FROM Articulo a JOIN a.lineaArticulos l WHERE a.estado =:estado order by l.nombreL asc", Articulo.class);
		consulta.setParameter("estado", estado);
		return (List<Articulo>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer mediante una consulta la informacion de los articulos por linea ordenandolos por su nombre de linea
	 * @return una lista con la informacion del los articulos por linea ordenados por el nombre de la linea
	 */
	public List <Articulo> traerArticulosPorLineaYOrdenarPorNombreLinea(String nombreL) {
		TypedQuery<Articulo> consulta = entityManager.createQuery(
				"SELECT a FROM Articulo a JOIN a.lineaArticulos l WHERE l.nombreL = :nombreL AND a.estado =:estado order by l.nombreL asc", Articulo.class);
		consulta.setParameter("nombreL", nombreL);
		consulta.setParameter("estado", estado);
		return (List<Articulo>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer mediante una consulta la informacion de todos los articulos ordenandolos por su nombre
	 * @return una lista con la informacion del los articulos ordenados por su nombre
	 */
	public List <Articulo> traerTodosArticulosYOrdenarPorNombreArticulo() {
		TypedQuery<Articulo> consulta = entityManager.createQuery(
				"SELECT a FROM Articulo a JOIN a.lineaArticulos l WHERE a.estado =:estado order by a.nombre asc", Articulo.class);
		consulta.setParameter("estado", estado);
		return (List<Articulo>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer mediante una consulta la informacion de los articulos por linea ordenandolos por nombre de articulo
	 * @return una lista con la informacion del los articulos por linea ordenados por el nombre de la linea
	 */
	public List <Articulo> traerArticulosPorLineaYOrdenarPorNombreArticulo(String nombreL) {
		TypedQuery<Articulo> consulta = entityManager.createQuery(
				"SELECT a FROM Articulo a JOIN a.lineaArticulos l WHERE l.nombreL = :nombreL AND a.estado =:estado order by a.nombre asc", Articulo.class);
		consulta.setParameter("nombreL", nombreL);
		consulta.setParameter("estado", estado);
		return (List<Articulo>) consulta.getResultList();
	}
}
