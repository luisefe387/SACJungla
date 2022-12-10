package co.com.jungla.sac.negocio.delegados;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbDevolucionProveedorRemote;
import co.com.jungla.sac.persistencia.entidades.DevolucionProveedor;

public class DelegadoDevolucionProveedor implements EjbDevolucionProveedorRemote{
	
	EjbDevolucionProveedorRemote ejbDevolucionProveedorRemote;

	public void insertarDevolucionProveedor(DevolucionProveedor devolucionProveedor) {
		ejbDevolucionProveedorRemote.insertarDevolucionProveedor(devolucionProveedor);
	}

	public void actualizarDevolucionProveedor(DevolucionProveedor devolucionProveedor) {
		ejbDevolucionProveedorRemote.actualizarDevolucionProveedor(devolucionProveedor);
	}

	public void eliminarDevolucionProveedor(DevolucionProveedor devolucionProveedor) {
		ejbDevolucionProveedorRemote.eliminarDevolucionProveedor(devolucionProveedor);
	}

	public List<DevolucionProveedor> listarDevolucionProveedor() {
		return ejbDevolucionProveedorRemote.listarDevolucionProveedor();
	}
	
	public List <DevolucionProveedor> traerUltimaDevolucionProveedor() {
		return ejbDevolucionProveedorRemote.traerUltimaDevolucionProveedor();
	}
	
	public List<DevolucionProveedor> listarDevolucionProveedorPorIdentificacion(String identificacion) {
		return ejbDevolucionProveedorRemote.listarDevolucionProveedorPorIdentificacion(identificacion);
	}

	public List<DevolucionProveedor> traerDevolucionProveedorPorCodigo(int idDevolucionProveedor) {
		return ejbDevolucionProveedorRemote.traerDevolucionProveedorPorCodigo(idDevolucionProveedor);
	}

	public List<DevolucionProveedor> reportarDevolucionesPorF(Date inicioFecha,Date finFecha) {
		return ejbDevolucionProveedorRemote.reportarDevolucionesPorF(inicioFecha, finFecha);
	}

	public List<DevolucionProveedor> reportarDevolucionesPorFP(Date inicioFecha, Date finFecha, String identificacion) {
		return ejbDevolucionProveedorRemote.reportarDevolucionesPorFP(inicioFecha, finFecha, identificacion);
	}

	public List<DevolucionProveedor> reportarDevolucionesPorFPE(Date inicioFecha, Date finFecha, String identificacion,String estado) {
		return ejbDevolucionProveedorRemote.reportarDevolucionesPorFPE(inicioFecha, finFecha, identificacion, estado);
	}

	public List<DevolucionProveedor> reportarDevolucionesPorFE(Date inicioFecha, Date finFecha, String estado) {
		return ejbDevolucionProveedorRemote.reportarDevolucionesPorFE(inicioFecha, finFecha, estado);
	}

	public DelegadoDevolucionProveedor() {
		try {
			ejbDevolucionProveedorRemote = (EjbDevolucionProveedorRemote) new InitialContext().lookup("java:global/SACJungla/EjbDevolucionProveedor!co.com.jungla.sac.negocio.ejb.EjbDevolucionProveedorRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
