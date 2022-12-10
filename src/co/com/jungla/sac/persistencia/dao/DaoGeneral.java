package co.com.jungla.sac.persistencia.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * clase abstracta dao con los metodos mas importantes para la gestion de las entidades en general
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
public abstract class DaoGeneral <Entidad, LLave extends Serializable>{

	/**
	 * Atributo que permite la interacción con la base dedatos, en donde la anotacion hace referencia al nombre del proyecto
	 */
	@PersistenceContext(unitName="SACJungla")
	protected EntityManager entityManager;
	
	/**
	 * Metodo abtracto para retornar la clase de la entidad
	 * @return devuelve la clase entidad
	 */
	protected abstract Class<Entidad> getEntityClass();
	
	/**
	 * Metodo para insertar las entidades en la base de datos
	 * @param entidad, parametro que define el nombre de la entidad a insertar
	 */
	public void insertar(Entidad entidad){
		entityManager.persist(entidad);
	}
	
	/**
	 * Metodo para actualizar los datos de las entidades en la base de datos
	 * @param entidad, parametro que define el nombre de la entidad a actualizar
	 */
	public void actualizar(Entidad entidad){
		entityManager.merge(entidad);
	}
	
	/**
	 * Metodo para eliminar la entidad de la base de datos
	 * @param entidad parametro que define el nombre de la entidad a eliminar
	 */
	public void eliminar(Entidad entidad){
		entityManager.remove(entityManager.merge(entidad));
	}
	
	/**
	 * Metodo para encontrar la entidad mediante la llave primaria
	 * @param llave, parametro de tipo string que define el nombre de la llave primaria de la entidad
	 * @return devuelve la entidad con toda su informacion
	 */
	public Entidad encontrarPorLlave(String llave){
		try{
			return entityManager.find(getEntityClass(), llave);
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * Metodo para listar todos los registros de una determinada Entidad
	 * @return una lista de tipo Entidad con todos sus datos
	 */
	public List<Entidad> listarTodo() {
		List<Entidad> entidades = entityManager.createQuery(
				"select entidad from " + getEntityClass().getSimpleName()
						+ " entidad ", getEntityClass()).getResultList();
		return entidades;
	}
}
