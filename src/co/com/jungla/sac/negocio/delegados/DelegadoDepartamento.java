package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbDepartamentoRemote;
import co.com.jungla.sac.persistencia.entidades.Departamento;

public class DelegadoDepartamento implements EjbDepartamentoRemote {

	EjbDepartamentoRemote ejbDepartamentoRemote;

	public void insertarDepartamento(Departamento departamento) {
		ejbDepartamentoRemote.insertarDepartamento(departamento);
	}

	public List<Departamento> listarDepartamentos() {
		return ejbDepartamentoRemote.listarDepartamentos();
	}
	
	public DelegadoDepartamento() {
		
		try {
			ejbDepartamentoRemote = (EjbDepartamentoRemote) new InitialContext().lookup("java:global/SACJungla/EjbDepartamento!co.com.jungla.sac.negocio.ejb.EjbDepartamentoRemote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
