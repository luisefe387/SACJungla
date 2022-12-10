package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoRecordatorio;
import co.com.jungla.sac.persistencia.entidades.Recordatorio;

/**
 * Session Bean implementation class EjbCompraArticulos
 */
@Stateless
@LocalBean
public class EjbRecordatorio implements EjbRecordatorioRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoRecordatorio daoRecordatorio;
	
    public EjbRecordatorio() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarRecordatorio(Recordatorio recordatorio) {
		daoRecordatorio.insertar(recordatorio);
		
	}

	@Override
	public void actualizarRecordatorio(Recordatorio recordatorio) {
		daoRecordatorio.actualizar(recordatorio);
		
	}

	@Override
	public void eliminarRecordatorio(Recordatorio recordatorio) {
		daoRecordatorio.eliminar(recordatorio);
		
	}

	@Override
	public List<Recordatorio> listarRecordatorios() {
		List<Recordatorio> listaRecordatorios = daoRecordatorio.listarTodo();
		return listaRecordatorios;
	}
}
