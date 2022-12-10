package co.com.jungla.sac.negocio.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoPersona;
import co.com.jungla.sac.persistencia.entidades.Persona;

/**
 * Session Bean implementation class EjbBanco
 */
@Stateless
@LocalBean
public class EjbPersona implements EjbPersonaRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoPersona daoPersona;
	
    public EjbPersona() {
      
    }

	@Override
	public void insertarPersona(Persona persona) {
		daoPersona.insertar(persona);
		
	}

	@Override
	public void actualizarPersona(Persona persona) {
		daoPersona.actualizar(persona);
		
	}

	@Override
	public void eliminarPersona(Persona persona) {
		daoPersona.eliminar(persona);
		
	}
}
