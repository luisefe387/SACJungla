package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.Municipio;

@Remote
public interface EjbMunicipioRemote {
	
	public void insertarMunicipio(Municipio municipio);
	
	public List<Municipio> listarMunicipios();
	
	public List<Municipio> listarMunicipiosPorDepartamento(String nombre);

}
