package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoDepartamento;
import co.com.jungla.sac.persistencia.entidades.Departamento;

/**
 * Session Bean implementation class EjbDepartamento
 */
@Stateless
@LocalBean
public class EjbDepartamento implements EjbDepartamentoRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoDepartamento daoDepartamento;
	
    public EjbDepartamento() {
        // TODO Auto-generated constructor stub
    }

    
	@Override
	public void insertarDepartamento(Departamento departamento) {
		daoDepartamento.insertar(departamento);
	}


	@Override
	public List<Departamento> listarDepartamentos() {
		List<Departamento> departamentos = daoDepartamento.listarTodo();
		return departamentos;
	}

}
