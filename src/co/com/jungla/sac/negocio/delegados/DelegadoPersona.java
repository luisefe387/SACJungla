package co.com.jungla.sac.negocio.delegados;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbPersonaRemote;
import co.com.jungla.sac.persistencia.entidades.Persona;

public class DelegadoPersona implements EjbPersonaRemote{

	EjbPersonaRemote ejbPersonaRemote;

	public void insertarPersona(Persona persona) {
		ejbPersonaRemote.insertarPersona(persona);
	}

	public void actualizarPersona(Persona persona) {
		ejbPersonaRemote.actualizarPersona(persona);
	}

	public void eliminarPersona(Persona persona) {
		ejbPersonaRemote.eliminarPersona(persona);
	}

	public DelegadoPersona() {
		
		try {
			ejbPersonaRemote = (EjbPersonaRemote) new InitialContext().lookup("java:global/SACJungla/EjbPersona!co.com.jungla.sac.negocio.ejb.EjbPersonaRemote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
