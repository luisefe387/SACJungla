package co.com.jungla.sac.persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.Municipio;


/**
 * clase dao con los metodos que se necesitan para gestionar los municipios
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoMunicipio extends DaoGeneral<Municipio,String>{

	/**
     * Metodo constructor sin parametros 
     */
    public DaoMunicipio() {
      
    }

    /* 
   	 * Metodo que permite retornar la clase Municipio
   	 */
	@Override
	protected Class<Municipio> getEntityClass() {
		return Municipio.class;
	}
	
	/**
	 * Metodo para traer los municipios por departamento
	 * @param nombre, parametro que define el nombre del departamento
	 * @return una lista de municipios con toda su informacion
	 */
	public List <Municipio> municipiosPorDepartamento(String nombre ) {
		TypedQuery<Municipio> consulta = entityManager.createQuery(
				"SELECT m FROM Departamento d INNER JOIN d.municipios m WHERE d.nombre = :nombre",
				Municipio.class);
		consulta.setParameter("nombre", nombre);
		return (List<Municipio>) consulta.getResultList();
	}
}
