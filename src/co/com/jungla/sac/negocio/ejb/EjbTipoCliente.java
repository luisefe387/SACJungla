package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoTipoCliente;
import co.com.jungla.sac.persistencia.entidades.TipoCliente;

/**
 * Session Bean implementation class TipoClienteEjb
 */
@Stateless
@LocalBean
public class EjbTipoCliente implements EjbRemoteTipoCliente {

    /**
     * Default constructor. 
     */
	@EJB
	DaoTipoCliente daotipoCliente;
	
    public EjbTipoCliente() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarTipoCliente(TipoCliente tipoCliente) {
		daotipoCliente.insertar(tipoCliente);
	}

	@Override
	public void actualizarTipoCliente(TipoCliente tipoCliente) {
		daotipoCliente.actualizar(tipoCliente);
	}

	@Override
	public void eliminarTipoCliente(TipoCliente tipoCliente) {
		daotipoCliente.eliminar(tipoCliente);
	}

	@Override
	public List<TipoCliente> listarTipoCliente() {
		// TODO Auto-generated method stub
		List<TipoCliente> tipoClientes = daotipoCliente.listarTodo();
		return tipoClientes;
	}

	

}
