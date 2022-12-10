package co.com.jungla.sac.negocio.delegados;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbAnticipoProveedorRemote;
import co.com.jungla.sac.persistencia.entidades.AnticipoProveedor;


public class DelegadoAnticipoProveedor implements EjbAnticipoProveedorRemote{
	
	EjbAnticipoProveedorRemote ejbAnticipoProveedorRemote;

	public void insertarAnticipoProveedor(AnticipoProveedor anticipoProveedor) {
		ejbAnticipoProveedorRemote.insertarAnticipoProveedor(anticipoProveedor);
	}

	public void actualizarAnticipoProveedor(AnticipoProveedor anticipoProveedor) {
		ejbAnticipoProveedorRemote.actualizarAnticipoProveedor(anticipoProveedor);
	}

	public void eliminarAnticipoProveedor(AnticipoProveedor anticipoProveedor) {
		ejbAnticipoProveedorRemote.eliminarAnticipoProveedor(anticipoProveedor);
	}

	public List<AnticipoProveedor> traerCodigoAnticipoProveedor() {
		return ejbAnticipoProveedorRemote.traerCodigoAnticipoProveedor();
	}
	
	public List<AnticipoProveedor> listarAnticipoProveedorPorIdentificacion(String identificacion) {
		return ejbAnticipoProveedorRemote.listarAnticipoProveedorPorIdentificacion(identificacion);
	}

	public List<AnticipoProveedor> traerAnticipoProveedorPorCodigo(int idAnticipoProveedor) {
		return ejbAnticipoProveedorRemote.traerAnticipoProveedorPorCodigo(idAnticipoProveedor);
	}

	public List<AnticipoProveedor> reportarAnticiposPorF(Date inicioFechaE,Date finFechaE) {
		return ejbAnticipoProveedorRemote.reportarAnticiposPorF(inicioFechaE,finFechaE);
	}

	public List<AnticipoProveedor> reportarAnticiposPorPF(Date inicioFechaE,Date finFechaE, String nombre) {
		return ejbAnticipoProveedorRemote.reportarAnticiposPorPF(inicioFechaE,finFechaE, nombre);
	}

	public List<AnticipoProveedor> reportarAnticiposPorEF(Date inicioFechaE,Date finFechaE, String estadoAnticipo) {
		return ejbAnticipoProveedorRemote.reportarAnticiposPorEF(inicioFechaE,finFechaE, estadoAnticipo);
	}

	public List<AnticipoProveedor> reportarAnticiposPorPEF(Date inicioFechaE,Date finFechaE, String nombre, String estadoAnticipo) {
		return ejbAnticipoProveedorRemote.reportarAnticiposPorPEF(inicioFechaE,finFechaE, nombre, estadoAnticipo);
	}

	public DelegadoAnticipoProveedor() {
		try {
			ejbAnticipoProveedorRemote = (EjbAnticipoProveedorRemote) new InitialContext().lookup("java:global/SACJungla/EjbAnticipoProveedor!co.com.jungla.sac.negocio.ejb.EjbAnticipoProveedorRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
