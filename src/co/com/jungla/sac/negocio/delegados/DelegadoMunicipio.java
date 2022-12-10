package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbMunicipioRemote;
import co.com.jungla.sac.persistencia.entidades.Municipio;

public class DelegadoMunicipio implements EjbMunicipioRemote{
	
	EjbMunicipioRemote ejbMunicipioRemote;

	
	public void insertarMunicipio(Municipio municipio) {
		ejbMunicipioRemote.insertarMunicipio(municipio);
	}

	
	public List<Municipio> listarMunicipios() {
		return ejbMunicipioRemote.listarMunicipios();
	}

	public List<Municipio> listarMunicipiosPorDepartamento(String nombre) {
		return ejbMunicipioRemote.listarMunicipiosPorDepartamento(nombre);
	}
	
	public DelegadoMunicipio() {
		
		try {
			ejbMunicipioRemote = (EjbMunicipioRemote) new InitialContext().lookup("java:global/SACJungla/EjbMunicipio!co.com.jungla.sac.negocio.ejb.EjbMunicipioRemote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
