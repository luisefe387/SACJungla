package co.com.jungla.sac.persistencia.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import co.com.jungla.sac.persistencia.entidades.UnidadMedida;

/**
 * clase dao con los metodos que se necesitan para gestionar las unidades de medida
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoUnidadMedida extends DaoGeneral<UnidadMedida,String>{

    /**
     * Metodo constructor sin parametros. 
     */
    public DaoUnidadMedida() {

    }

    /* 
   	 * Metodo que permite retornar la clase UnidadMedida
   	 */
	@Override
	protected Class<UnidadMedida> getEntityClass() {
		return UnidadMedida.class;
	}
	
	/**
	 * Metodo para traer la unidad de medida por medio de su nombre
	 * @param nombre, parametro que define el nombre de la unidad de medida
	 * @return un objeto de tipo UnidadMedida con su informacion 
	 */
	public UnidadMedida traerUnidadMedida(String nombre ) {
		try{
			Query consulta = entityManager.createQuery(
					"SELECT um FROM UnidadMedida um WHERE um.nombre = :nombre",UnidadMedida.class);
			consulta.setParameter("nombre", nombre);
			return (UnidadMedida) consulta.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
}
