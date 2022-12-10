package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbProveedorRemote;
import co.com.jungla.sac.persistencia.entidades.Proveedor;

public class DelegadoProveedor implements EjbProveedorRemote{

	EjbProveedorRemote ejbProveedorRemote;

	public void insertarProveedor(Proveedor proveedor) {
		ejbProveedorRemote.insertarProveedor(proveedor);
	}

	public void actualizarProveedor(Proveedor proveedor) {
		ejbProveedorRemote.actualizarProveedor(proveedor);
	}

	public void eliminarProveedor(Proveedor proveedor) {
		ejbProveedorRemote.eliminarProveedor(proveedor);
	}

	public List<Proveedor> listarProveedor() {
		return ejbProveedorRemote.listarProveedor();
	}

	public Proveedor encontrarPorIdentificacion(String identificacion) {
		return ejbProveedorRemote.encontrarPorIdentificacion(identificacion);
	}
	
	public List<Proveedor> traerProveedorPorNombre(String nombre) {
		return ejbProveedorRemote.traerProveedorPorNombre(nombre);
	}

	public List<Proveedor> traerProveedorPorDepartamento(String departamento) {
		return ejbProveedorRemote.traerProveedorPorDepartamento(departamento);
	}

	public List<Proveedor> traerProveedorPorCiudad(String ciudad) {
		return ejbProveedorRemote.traerProveedorPorCiudad(ciudad);
	}

	public List<Proveedor> traerProveedorDeshabilitado(String estado) {
		return ejbProveedorRemote.traerProveedorDeshabilitado(estado);
	}

	public List<Proveedor> traerProveedorPorIdentificacion(String identificacion) {
		return ejbProveedorRemote.traerProveedorPorIdentificacion(identificacion);
	}

	public DelegadoProveedor() {
		
		try {
			ejbProveedorRemote = (EjbProveedorRemote) new InitialContext().lookup("java:global/SACJungla/EjbProveedor!co.com.jungla.sac.negocio.ejb.EjbProveedorRemote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
