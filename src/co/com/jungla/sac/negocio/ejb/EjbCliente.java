package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoCliente;
import co.com.jungla.sac.persistencia.entidades.Cliente;


/**
 * Session Bean implementation class EjbCliente
 */
@Stateless
@LocalBean
public class EjbCliente implements EjbClienteRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoCliente daoCliente;
	
    public EjbCliente() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarCliente(Cliente cliente) {
		daoCliente.insertar(cliente);
	}

	@Override
	public void actualizarCliente(Cliente cliente) {
		daoCliente.actualizar(cliente);
	}

	@Override
	public void eliminarCliente(Cliente cliente) {
		daoCliente.eliminar(cliente);
	}

	@Override
	public List<Cliente> listarCliente() {
		List<Cliente> clientes = daoCliente.listarTodo();
		return clientes;
	}

	@Override
	public Cliente encontarPorCliente(String identificacion) {
		return daoCliente.encontrarPorLlave(identificacion);
	}

	@Override
	public List<Cliente> traerClientePorIdentificacion(String identificacion) {
		List<Cliente> clientes = daoCliente.traerClientePorIdentificacion(identificacion);
		return clientes;
	}

	@Override
	public List<Cliente> traerClientePorNombre(String nombre) {
		List<Cliente> clientes = daoCliente.traerClientePorNombre(nombre);
		return clientes;
	}

	@Override
	public List<Cliente> traerClientePorIdentificacionYNombre(String nombre,String identificacion) {
		List<Cliente> clientes = daoCliente.traerClientePorIdentificacionYNombre(nombre, identificacion);
		return clientes;
	}

	@Override
	public List<Cliente> traerUltimoCliente() {
		List<Cliente> cliente = daoCliente.traerUltimoCliente();
		return cliente;
	}

	@Override
	public List<Cliente> traerClientePorIdentificacionS(String identificacion) {
		List<Cliente> cliente = daoCliente.traerClientePorIdentificacionS(identificacion);
		return cliente;
	}

	@Override
	public List<Cliente> traerClientePorDepartamento(String departamento) {
		List<Cliente> cliente = daoCliente.traerClientePorDepartamento(departamento);
		return cliente;
	}

	@Override
	public List<Cliente> traerClientePorCiudad(String ciudad) {
		List<Cliente> cliente = daoCliente.traerClientePorCiudad(ciudad);
		return cliente;
	}

	@Override
	public List<Cliente> traerClienteDeshabilitado(String estado) {
		List<Cliente> cliente = daoCliente.traerClienteDeshabilitado(estado);
		return cliente;
	}
	
	
}
