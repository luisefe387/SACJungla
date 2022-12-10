package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoProveedor;
import co.com.jungla.sac.persistencia.entidades.Proveedor;

/**
 * Session Bean implementation class EjbBanco
 */
@Stateless
@LocalBean
public class EjbProveedor implements EjbProveedorRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoProveedor daoProveedor;
	
    public EjbProveedor() {
      
    }

	@Override
	public void insertarProveedor(Proveedor proveedor) {
		daoProveedor.insertar(proveedor);
		
	}

	@Override
	public void actualizarProveedor(Proveedor proveedor) {
		daoProveedor.actualizar(proveedor);
		
	}

	@Override
	public void eliminarProveedor(Proveedor proveedor) {
		daoProveedor.eliminar(proveedor);
		
	}

	@Override
	public List<Proveedor> listarProveedor() {
		List <Proveedor> proveedores= daoProveedor.listarTodo();
		return proveedores;
	}

	
	@Override
	public List<Proveedor> traerProveedorPorNombre(String nombre) {
		List <Proveedor> proveedores= daoProveedor.traerProveedorPorNombre(nombre);
		return proveedores;
	}

	@Override
	public Proveedor encontrarPorIdentificacion(String identificacion) {
		Proveedor proveedor = daoProveedor.encontrarPorLlave(identificacion);
		return proveedor;
	}

	@Override
	public List<Proveedor> traerProveedorPorDepartamento(String departamento) {
		List <Proveedor> proveedores= daoProveedor.traerProveedorPorDepartamento(departamento);
		return proveedores;
	}

	@Override
	public List<Proveedor> traerProveedorPorCiudad(String ciudad) {
		List <Proveedor> proveedores= daoProveedor.traerProveedorPorCiudad(ciudad);
		return proveedores;
	}

	@Override
	public List<Proveedor> traerProveedorDeshabilitado(String estado) {
		List <Proveedor> proveedores= daoProveedor.traerProveedorDeshabilitado(estado);
		return proveedores;
	}

	@Override
	public List<Proveedor> traerProveedorPorIdentificacion(String identificacion) {
		List <Proveedor> proveedores= daoProveedor.traerProveedorPorIdentificacion(identificacion);
		return proveedores;
	}
	
	
}
