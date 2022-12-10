package co.com.jungla.sac.negocio.ejb;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.Persona;

@Remote
public interface EjbPersonaRemote {

public void insertarPersona(Persona persona);
	
	public void actualizarPersona(Persona persona);
	
	public void eliminarPersona(Persona persona);
}
