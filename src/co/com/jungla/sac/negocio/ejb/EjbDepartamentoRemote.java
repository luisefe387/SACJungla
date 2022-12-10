package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.Departamento;

@Remote
public interface EjbDepartamentoRemote {

	public void insertarDepartamento(Departamento departamento);
	
	public List<Departamento> listarDepartamentos();
	
}
