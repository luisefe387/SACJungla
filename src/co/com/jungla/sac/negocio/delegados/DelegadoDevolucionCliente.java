package co.com.jungla.sac.negocio.delegados;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbDevolucionClienteRemote;
import co.com.jungla.sac.persistencia.entidades.DevolucionCliente;

public class DelegadoDevolucionCliente implements EjbDevolucionClienteRemote{
	
	EjbDevolucionClienteRemote ejbDevolucionClienteRemote;

	public void insertarDevolucionCliente(DevolucionCliente devolucionCliente) {
		ejbDevolucionClienteRemote.insertarDevolucionCliente(devolucionCliente);
	}

	public void actualizarDevolucionCliente(DevolucionCliente devolucionCliente) {
		ejbDevolucionClienteRemote.actualizarDevolucionCliente(devolucionCliente);
	}

	public void eliminarDevolucionCliente(DevolucionCliente devolucionCliente) {
		ejbDevolucionClienteRemote.eliminarDevolucionCliente(devolucionCliente);
	}

	public List<DevolucionCliente> listarDevolucionCliente() {
		return ejbDevolucionClienteRemote.listarDevolucionCliente();
	}
	
	public List <DevolucionCliente> traerUltimaDevolucionCliente() {
		return ejbDevolucionClienteRemote.traerUltimaDevolucionCliente();
	}
	
	public List<DevolucionCliente> listarDevolucionClientePorIdentificacion(String identificacion) {
		return ejbDevolucionClienteRemote.listarDevolucionClientePorIdentificacion(identificacion);
	}

	public List<DevolucionCliente> traerDevolucionClientePorCodigo(int idDevolucionCliente) {
		return ejbDevolucionClienteRemote.traerDevolucionClientePorCodigo(idDevolucionCliente);
	}

	public List<DevolucionCliente> reportarDevolucionesPorF(Date inicioFecha,Date finFecha) {
		return ejbDevolucionClienteRemote.reportarDevolucionesPorF(inicioFecha,finFecha);
	}
	
	public List<DevolucionCliente> reportarDevolucionesPorFC(Date inicioFecha,Date finFecha, String identificacion) {
		return ejbDevolucionClienteRemote.reportarDevolucionesPorFC(inicioFecha, finFecha, identificacion);
	}

	public List<DevolucionCliente> reportarDevolucionesPorFCE(Date inicioFecha,Date finFecha, String identificacion, String estado) {
		return ejbDevolucionClienteRemote.reportarDevolucionesPorFCE(inicioFecha, finFecha, identificacion, estado);
	}

	public List<DevolucionCliente> reportarDevolucionesPorFE(Date inicioFecha,Date finFecha, String estado) {
		return ejbDevolucionClienteRemote.reportarDevolucionesPorFE(inicioFecha, finFecha, estado);
	}

	public DelegadoDevolucionCliente() {
		try {
			ejbDevolucionClienteRemote = (EjbDevolucionClienteRemote) new InitialContext().lookup("java:global/SACJungla/EjbDevolucionCliente!co.com.jungla.sac.negocio.ejb.EjbDevolucionClienteRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
