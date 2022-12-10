package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.DevolucionProveedor;

@Remote
public interface EjbDevolucionProveedorRemote {

public void insertarDevolucionProveedor(DevolucionProveedor devolucionProveedor);
	
	public void actualizarDevolucionProveedor(DevolucionProveedor devolucionProveedor);
	
	public void eliminarDevolucionProveedor(DevolucionProveedor devolucionProveedor);
	
	public List<DevolucionProveedor> listarDevolucionProveedor();
	
	public List <DevolucionProveedor> traerUltimaDevolucionProveedor();
	
	public List <DevolucionProveedor> listarDevolucionProveedorPorIdentificacion(String identificacion);
	
	public List <DevolucionProveedor> traerDevolucionProveedorPorCodigo(int idDevolucionProveedor);
	
	public List <DevolucionProveedor> reportarDevolucionesPorF(Date inicioFecha, Date finFecha);
	
	public List <DevolucionProveedor> reportarDevolucionesPorFP(Date inicioFecha, Date finFecha, String identificacion );
	
	public List <DevolucionProveedor> reportarDevolucionesPorFPE(Date inicioFecha, Date finFecha, String identificacion, String estado );
	
	public List <DevolucionProveedor> reportarDevolucionesPorFE(Date inicioFecha, Date finFecha, String estado );
}
