package co.com.jungla.sac.persistencia.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.entidades.Recordatorio;


@Stateless
@LocalBean
public class DaoRecordatorio extends DaoGeneral<Recordatorio,String>{

    public DaoRecordatorio() {
    
    }

	@Override
	protected Class<Recordatorio> getEntityClass() {
		return Recordatorio.class;
	}
}
