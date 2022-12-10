package co.com.jungla.sac.persistencia.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.entidades.Persona;

/**
 * clase dao con los metodos que se necesitan para gestionar las personas
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoPersona extends DaoGeneral<Persona, String>{

	/**
     * Metodo constructor sin parametros 
     */
    public DaoPersona() {
       
    }

    /* 
   	 * Metodo que permite retornar la clase Persona
   	 */
	@Override
	protected Class<Persona> getEntityClass() {
		return Persona.class;
	}

}
