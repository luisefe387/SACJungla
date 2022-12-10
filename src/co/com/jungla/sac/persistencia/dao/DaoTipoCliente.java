package co.com.jungla.sac.persistencia.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.entidades.TipoCliente;

/**
 * clase dao con los metodos que se necesitan para gestionar los tipos de clientes
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoTipoCliente extends DaoGeneral<TipoCliente,String>{

    /**
     * Metodo constructor sin parametros.
     */
    public DaoTipoCliente() {

    }

    /* 
   	 * Metodo que permite retornar la clase TipoCliente
   	 */
	@Override
	protected Class<TipoCliente> getEntityClass() {
		return TipoCliente.class;
	}
   
}
