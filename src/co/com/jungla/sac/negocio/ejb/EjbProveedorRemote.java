package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.Proveedor;


@Remote
public interface EjbProveedorRemote {

public void insertarProveedor(Proveedor proveedor);
	
	public void actualizarProveedor(Proveedor proveedor);
	
	public void eliminarProveedor(Proveedor proveedor);
	
	public List<Proveedor> listarProveedor();
	
	public Proveedor encontrarPorIdentificacion(String identificacion);
	
	public List <Proveedor> traerProveedorPorNombre(String nombre);
	
	public List <Proveedor> traerProveedorPorDepartamento(String departamento);
	
	public List <Proveedor> traerProveedorPorCiudad(String ciudad);
	
	public List <Proveedor> traerProveedorDeshabilitado(String estado);
	
	public List <Proveedor> traerProveedorPorIdentificacion(String identificacion);
}
