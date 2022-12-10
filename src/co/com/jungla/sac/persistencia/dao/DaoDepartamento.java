package co.com.jungla.sac.persistencia.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.entidades.Departamento;

/**
 * clase dao con los metodos que se necesitan para gestionar los departamentos
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoDepartamento extends DaoGeneral<Departamento,String>{

    /**
     * Metodo constructor sin parametros  
     */
    public DaoDepartamento() {
   
    }

    /* 
   	 * Metodo que permite retornar la clase Departamento
   	 */
	@Override
	protected Class<Departamento> getEntityClass() {
		return Departamento.class;
	}

}
