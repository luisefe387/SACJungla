package co.com.jungla.sac.persistencia.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.entidades.Banco;

/**
 * clase dao con los metodos que se necesitan para gestionar los bancos
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoBanco extends DaoGeneral<Banco,String> {

	/**
     * Metodo constructor sin parametros 
     */
    public DaoBanco() {
    	
    }
    
    /* 
   	 * Metodo que permite retornar la clase Banco
   	 */
	@Override
	protected Class<Banco> getEntityClass() {
		return Banco.class;
	}

}
