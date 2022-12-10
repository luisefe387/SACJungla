package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbAnticipoClienteRemote;
import co.com.jungla.sac.persistencia.entidades.AnticipoCliente;


public class DelegadoAnticipoCliente implements EjbAnticipoClienteRemote{
	
	EjbAnticipoClienteRemote ejbAnticipoClienteRemote;

	public void insertarAnticipoCliente(AnticipoCliente anticipoCliente) {
		ejbAnticipoClienteRemote.insertarAnticipoCliente(anticipoCliente);
	}

	public void actualizarAnticipoCliente(AnticipoCliente anticipoCliente) {
		ejbAnticipoClienteRemote.actualizarAnticipoCliente(anticipoCliente);
	}

	public void eliminarAnticipoCliente(AnticipoCliente anticipoCliente) {
		ejbAnticipoClienteRemote.eliminarAnticipoCliente(anticipoCliente);
	}

	public List<AnticipoCliente> traerUltimoAnticipoCliente() {
		return ejbAnticipoClienteRemote.traerUltimoAnticipoCliente();
	}
	
	public List<AnticipoCliente> listarAnticipoClientePorIdentificacion(String identificacion) {
		return ejbAnticipoClienteRemote.listarAnticipoClientePorIdentificacion(identificacion);
	}
	
	public List<AnticipoCliente> traerAnticipoClientePorCodigo(int idAnticipoCliente) {
		return ejbAnticipoClienteRemote.traerAnticipoClientePorCodigo(idAnticipoCliente);
	}

	/*public List<AnticipoCliente> listarAnticipoClientePorIdentificacion(String identificacion) {
		return ejbAnticipoClienteRemote.listarAnticipoClientePorIdentificacion(identificacion);
	}

	public List<AnticipoCliente> traerAnticipoClientePorCodigo(int idAnticipoCliente) {
		return ejbAnticipoClienteRemote.traerAnticipoClientePorCodigo(idAnticipoCliente);
	}

	public List<AnticipoCliente> reportarAnticiposPorF(Date inicioFechaE,Date finFechaE) {
		return ejbAnticipoClienteRemote.reportarAnticiposPorF(inicioFechaE,finFechaE);
	}

	public List<AnticipoCliente> reportarAnticiposPorPF(Date inicioFechaE,Date finFechaE, String nombre) {
		return ejbAnticipoClienteRemote.reportarAnticiposPorPF(inicioFechaE,finFechaE, nombre);
	}

	public List<AnticipoCliente> reportarAnticiposPorEF(Date inicioFechaE,Date finFechaE, String estadoAnticipo) {
		return ejbAnticipoClienteRemote.reportarAnticiposPorEF(inicioFechaE,finFechaE, estadoAnticipo);
	}

	public List<AnticipoCliente> reportarAnticiposPorPEF(Date inicioFechaE,Date finFechaE, String nombre, String estadoAnticipo) {
		return ejbAnticipoClienteRemote.reportarAnticiposPorPEF(inicioFechaE,finFechaE, nombre, estadoAnticipo);
	}*/

	public DelegadoAnticipoCliente() {
		try {
			ejbAnticipoClienteRemote = (EjbAnticipoClienteRemote) new InitialContext().lookup("java:global/SACJungla/EjbAnticipoCliente!co.com.jungla.sac.negocio.ejb.EjbAnticipoClienteRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
