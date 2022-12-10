package co.com.jungla.sac.persistencia.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import co.com.jungla.sac.persistencia.entidades.EntradaOSalidaArticulo;

/**
 * clase dao con los metodos que se necesitan para gestionar las entradas o salidas de los articulos
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoEntradaOSalidaArticulo extends DaoGeneral<EntradaOSalidaArticulo,String>{

    /**
     * Metodo constructor sin parametros. 
     */
    public DaoEntradaOSalidaArticulo() {
  
    }

    /* 
   	 * Metodo que permite retornar la clase EntradaOSalidaSArticulo
   	 */
	@Override
	protected Class<EntradaOSalidaArticulo> getEntityClass() {
		return EntradaOSalidaArticulo.class;
	}
	
	/**
	 * Metodo que permite traer el ultimo registro de la entrada o salida del articulo
	 * @return un objeto de tipo EntradaOSalidaArticulo donde esta almacenada la informacion de la ultima entrada o salida 
	 */
	public EntradaOSalidaArticulo traerUltimaEntradaOSalida() {
		try{
			Query consulta = entityManager.createQuery(
					"SELECT es FROM EntradaOSalidaArticulo es order by es.idEntradaOSalida desc",
					EntradaOSalidaArticulo.class);
			consulta.setMaxResults(1);
			return (EntradaOSalidaArticulo) consulta.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
}
