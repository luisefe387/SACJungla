package co.com.jungla.sac.persistencia.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.entidades.MedioPagoProv;

/**
 * clase dao con los metodos que se necesitan para gestionar los medios de pago que utilizan los proveedores
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoMedioPagoProv extends DaoGeneral<MedioPagoProv,String> {

	/**
     * Metodo constructor sin parametros. 
     */
    public DaoMedioPagoProv() {

    }

    /* 
   	 * Metodo que permite retornar la clase MedioPagoProv
   	 */
	@Override
	protected Class<MedioPagoProv> getEntityClass() {
		return MedioPagoProv.class;
	}

}
