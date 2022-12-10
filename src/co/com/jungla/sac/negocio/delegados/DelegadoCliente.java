package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbClienteRemote;
import co.com.jungla.sac.persistencia.entidades.Cliente;

public class DelegadoCliente implements EjbClienteRemote {

	EjbClienteRemote ejbClienteRemote;

	public void insertarCliente(Cliente cliente) {
		ejbClienteRemote.insertarCliente(cliente);
	}

	public void actualizarCliente(Cliente cliente) {
		ejbClienteRemote.actualizarCliente(cliente);
	}

	public void eliminarCliente(Cliente cliente) {
		ejbClienteRemote.eliminarCliente(cliente);
	}

	public List<Cliente> listarCliente() {
		return ejbClienteRemote.listarCliente();
	}

	public Cliente encontarPorCliente(String identificacion) {
		return ejbClienteRemote.encontarPorCliente(identificacion);
	}
	
	
	public List<Cliente> traerClientePorIdentificacion(String identificacion) {
		return ejbClienteRemote.traerClientePorIdentificacion(identificacion);
	}

	public List<Cliente> traerClientePorNombre(String nombre) {
		return ejbClienteRemote.traerClientePorNombre(nombre);
	}

	public List<Cliente> traerClientePorIdentificacionYNombre(String nombre,String identificacion) {
		return ejbClienteRemote.traerClientePorIdentificacionYNombre(nombre,identificacion);
	}

	public List<Cliente> traerUltimoCliente() {
		return ejbClienteRemote.traerUltimoCliente();
	}

	public List<Cliente> traerClientePorIdentificacionS(String identificacion) {
		return ejbClienteRemote.traerClientePorIdentificacionS(identificacion);
	}

	public List<Cliente> traerClientePorDepartamento(String departamento) {
		return ejbClienteRemote.traerClientePorDepartamento(departamento);
	}

	public List<Cliente> traerClientePorCiudad(String ciudad) {
		return ejbClienteRemote.traerClientePorCiudad(ciudad);
	}

	public List<Cliente> traerClienteDeshabilitado(String estado) {
		return ejbClienteRemote.traerClienteDeshabilitado(estado);
	}

	public DelegadoCliente() {
		try {
			ejbClienteRemote = (EjbClienteRemote) new InitialContext().lookup("java:global/SACJungla/EjbCliente!co.com.jungla.sac.negocio.ejb.EjbClienteRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
}
