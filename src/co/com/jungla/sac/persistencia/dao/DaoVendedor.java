package co.com.jungla.sac.persistencia.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.entidades.Vendedor;

/**
 * clase dao con los metodos que se necesitan para gestionar los vendedores
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoVendedor extends DaoGeneral<Vendedor,String> {

	/**
     * Metodo constructor sin parametros 
     */
    public DaoVendedor() {

    }

    /* 
   	 * Metodo que permite retornar la clase Vendedor
   	 */
	@Override
	protected Class<Vendedor> getEntityClass() {
		return Vendedor.class;
	}

}
