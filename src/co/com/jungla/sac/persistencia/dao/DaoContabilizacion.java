package co.com.jungla.sac.persistencia.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.entidades.Contabilizacion;

/**
 * clase dao con los metodos que se necesitan para gestionar la contabilizacion de las operaciones mercantiles
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoContabilizacion extends DaoGeneral<Contabilizacion,String> {

    /**
     * Metodo constructor sin parametros 
     */
    public DaoContabilizacion() {
       
    }

    /* 
   	 * Metodo que permite retornar la clase Contabilizacion
   	 */
	@Override
	protected Class<Contabilizacion> getEntityClass() {
		return Contabilizacion.class;
	}

}
