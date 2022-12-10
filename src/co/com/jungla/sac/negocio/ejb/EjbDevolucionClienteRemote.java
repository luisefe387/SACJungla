package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.DevolucionCliente;

@Remote
public interface EjbDevolucionClienteRemote {

	public void insertarDevolucionCliente(DevolucionCliente devolucionCliente);
	
	public void actualizarDevolucionCliente(DevolucionCliente devolucionCliente);
	
	public void eliminarDevolucionCliente(DevolucionCliente devolucionCliente);
	
	public List<DevolucionCliente> listarDevolucionCliente();
	
	public List <DevolucionCliente> traerUltimaDevolucionCliente();
	
	public List <DevolucionCliente> listarDevolucionClientePorIdentificacion(String identificacion);
	
	public List <DevolucionCliente> traerDevolucionClientePorCodigo(int idDevolucionCliente);
	
	public List <DevolucionCliente> reportarDevolucionesPorF(Date inicioFecha, Date finFecha);
	
	public List <DevolucionCliente> reportarDevolucionesPorFC(Date inicioFecha, Date finFecha, String identificacion );
	
	public List <DevolucionCliente> reportarDevolucionesPorFCE(Date inicioFecha, Date finFecha, String identificacion, String estado );
	
	public List <DevolucionCliente> reportarDevolucionesPorFE(Date inicioFecha, Date finFecha, String estado );
}
