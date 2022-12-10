package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.UnidadMedida;

@Remote
public interface EjbUnidadMedidaRemote {

	public void insertarUnidadMedida(UnidadMedida unidadMedida);
	
	public void actualizarUnidadMedida(UnidadMedida unidadMedida);
	
	public void eliminarUnidadMedida(UnidadMedida unidadMedida);
	
	public List<UnidadMedida> listarUnidadMedida ();
	
	public UnidadMedida traerUnidadMedida(String nombre);
}
