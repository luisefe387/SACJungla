package co.com.jungla.sac.persistencia.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import co.com.jungla.sac.persistencia.entidades.LineaArticulos;

/**
 * clase dao con los metodos que se necesitan para gestionar las lineas de articulos
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoLineaArticulos extends DaoGeneral<LineaArticulos,String> {

    /**
     * Metodo constructor sin parametros . 
     */
    public DaoLineaArticulos() {
      
    }
    
    /* 
   	 * Metodo que permite retornar la clase LineaArticulos
   	 */
	@Override
	protected Class<LineaArticulos> getEntityClass() {
		return LineaArticulos.class;
	}
	
	/**
	 * Metodo para traer la informacion de la linea de articulos mediante el nombre de la misma
	 * @param nombreL, parametro que define el nombre de la linea de articulo
	 * @return un objeto de tipo LineaArticulo con la informacion encontrada
	 */
	public LineaArticulos traerLineaArticulo(String nombreL ) {
		try{
			Query consulta = entityManager.createQuery(
					"SELECT la FROM LineaArticulos la WHERE la.nombreL = :nombreL",LineaArticulos.class);
			consulta.setParameter("nombreL", nombreL);
			return (LineaArticulos) consulta.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
}
