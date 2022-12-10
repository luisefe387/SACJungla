package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoDevolucionCliente;
import co.com.jungla.sac.persistencia.entidades.DevolucionCliente;

/**
 * Session Bean implementation class EjbDevolucionCliente
 */
@Stateless
@LocalBean
public class EjbDevolucionCliente implements EjbDevolucionClienteRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoDevolucionCliente daoDevolucionCliente;
	
    public EjbDevolucionCliente() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarDevolucionCliente(DevolucionCliente devolucionCliente) {
		daoDevolucionCliente.insertar(devolucionCliente);
		
	}

	@Override
	public void actualizarDevolucionCliente(DevolucionCliente devolucionCliente) {
		daoDevolucionCliente.actualizar(devolucionCliente);
	}

	@Override
	public void eliminarDevolucionCliente(DevolucionCliente devolucionCliente) {
		daoDevolucionCliente.eliminar(devolucionCliente);
	}

	@Override
	public List<DevolucionCliente> listarDevolucionCliente() {
		List<DevolucionCliente> devolucionCliente = daoDevolucionCliente.listarTodo();
		return devolucionCliente;
	}

	@Override
	public List <DevolucionCliente> traerUltimaDevolucionCliente() {
		List <DevolucionCliente> devolucionCliente = daoDevolucionCliente.traerultimaDevolucionCliente();
		return devolucionCliente;
	}

	@Override
	public List<DevolucionCliente> listarDevolucionClientePorIdentificacion(String identificacion) {
		List <DevolucionCliente> devolucionCliente = daoDevolucionCliente.listarDevolucionClientePorIdentificacion(identificacion);;
		return devolucionCliente;
	}

	@Override
	public List<DevolucionCliente> traerDevolucionClientePorCodigo(int idDevolucionCliente) {
		List <DevolucionCliente> devolucionCliente = daoDevolucionCliente.traerDevolucionClientePorCodigo(idDevolucionCliente);;
		return devolucionCliente;
	}

	@Override
	public List<DevolucionCliente> reportarDevolucionesPorF(Date inicioFecha,Date finFecha) {
		List <DevolucionCliente> devolucionCliente = daoDevolucionCliente.reportarDevolucionesPorF(inicioFecha, finFecha);
		return devolucionCliente;
	}

	@Override
	public List<DevolucionCliente> reportarDevolucionesPorFC(Date inicioFecha,Date finFecha, String identificacion) {
		List <DevolucionCliente> devolucionCliente = daoDevolucionCliente.reportarDevolucionesPorFC(inicioFecha, finFecha, identificacion);
		return devolucionCliente;
	}

	@Override
	public List<DevolucionCliente> reportarDevolucionesPorFCE(Date inicioFecha,Date finFecha, String identificacion, String estado) {
		List <DevolucionCliente> devolucionCliente = daoDevolucionCliente.reportarDevolucionesPorFCE(inicioFecha, finFecha, identificacion, estado);
		return devolucionCliente;
	}

	@Override
	public List<DevolucionCliente> reportarDevolucionesPorFE(Date inicioFecha,Date finFecha, String estado) {
		List <DevolucionCliente> devolucionCliente = daoDevolucionCliente.reportarDevolucionesPorFE(inicioFecha, finFecha, estado);
		return devolucionCliente;
	}
}
