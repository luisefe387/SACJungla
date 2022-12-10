package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.Cliente;

@Remote
public interface EjbClienteRemote {
	
	public void insertarCliente(Cliente cliente);
	
	public void actualizarCliente(Cliente cliente);
	
	public void eliminarCliente(Cliente cliente);
	
	public List<Cliente> listarCliente();
	
	public Cliente encontarPorCliente(String identificacion);
	
	public List <Cliente> traerClientePorIdentificacion(String identificacion);
	
	public List <Cliente> traerClientePorNombre(String nombre);
	
	public List <Cliente> traerClientePorIdentificacionYNombre(String nombre, String identificacion);
	
	public List<Cliente> traerUltimoCliente();
	
	public List <Cliente> traerClientePorIdentificacionS(String identificacion);
	
	public List <Cliente> traerClientePorDepartamento(String departamento);
	
	public List <Cliente> traerClientePorCiudad(String ciudad);
	
	public List <Cliente> traerClienteDeshabilitado(String estado);

}
