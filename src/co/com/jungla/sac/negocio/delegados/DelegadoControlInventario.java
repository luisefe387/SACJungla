package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbControlInventarioRemote;
import co.com.jungla.sac.persistencia.entidades.ControlInventario;

public class DelegadoControlInventario implements EjbControlInventarioRemote{

	EjbControlInventarioRemote ejbControlInventarioRemote;

	public void insertarControlInventario(ControlInventario controlInventario) {
		ejbControlInventarioRemote.insertarControlInventario(controlInventario);
	}

	public void actualizarControlInventario(ControlInventario controlInventario) {
		ejbControlInventarioRemote.actualizarControlInventario(controlInventario);
	}

	public void eliminarControlInventario(ControlInventario controlInventario) {
		ejbControlInventarioRemote.eliminarControlInventario(controlInventario);
	}

	public List<ControlInventario> listarControlInventario() {
		return ejbControlInventarioRemote.listarControlInventario();
	}
	
	public List<ControlInventario> traerContInventarioPorCodigoArticulo(int codigo) {
			return ejbControlInventarioRemote.traerContInventarioPorCodigoArticulo(codigo);
	}
	
	public List<ControlInventario> traerRegistrosInventarioPorP(String proveedorUltimaCompra) {
		return ejbControlInventarioRemote.traerRegistrosInventarioPorP(proveedorUltimaCompra);
	}

	public List<ControlInventario> traerRegistrosInventarioPorGP(String nombreL, String proveedorUltimaCompra) {
		return ejbControlInventarioRemote.traerRegistrosInventarioPorGP(nombreL, proveedorUltimaCompra);
	}

	public List<ControlInventario> traerRegistrosInventarioPorE(String estadoArticulo) {
		return ejbControlInventarioRemote.traerRegistrosInventarioPorE(estadoArticulo);
	}

	public List<ControlInventario> traerRegistrosInventarioPorEG(String estadoArticulo, String nombreL) {
		return ejbControlInventarioRemote.traerRegistrosInventarioPorEG(estadoArticulo, nombreL);
	}
	
	public List<ControlInventario> traerRegistrosInventarioPorG(String nombreL) {
		return ejbControlInventarioRemote.traerRegistrosInventarioPorG(nombreL);
	}

	public List<ControlInventario> traerRegistrosInventarioPorEGP(String estadoArticulo, String nombreL, String proveedorUltimaCompra) {
		return ejbControlInventarioRemote.traerRegistrosInventarioPorEGP(estadoArticulo, nombreL, proveedorUltimaCompra);
	}

	
	/*public List<ControlInventario> traerRegistrosInventario(String estadoArticulo, String nombreL, String proveedorUltimaCompra) {
		return ejbControlInventarioRemote.traerRegistrosInventario(estadoArticulo, nombreL, proveedorUltimaCompra);
	}*/

	public List<ControlInventario> traerRegistrosInventarioPorB(String nombre) {
		return ejbControlInventarioRemote.traerRegistrosInventarioPorB(nombre);
	}

	public List<ControlInventario> traerRegistrosInventarioPorBP(String nombre,String proveedorUltimaCompra) {
		return ejbControlInventarioRemote.traerRegistrosInventarioPorBP(nombre,proveedorUltimaCompra);
	}

	public List<ControlInventario> traerRegistrosInventarioPorBGP(String nombre, String nombreL, String proveedorUltimaCompra) {
		return ejbControlInventarioRemote.traerRegistrosInventarioPorBGP(nombre, nombreL, proveedorUltimaCompra);
	}

	public List<ControlInventario> traerRegistrosInventarioPorBE(String nombre,String estadoArticulo) {
		return ejbControlInventarioRemote.traerRegistrosInventarioPorBE(nombre,estadoArticulo);
	}

	public List<ControlInventario> traerRegistrosInventarioPorBEG(String nombre, String estadoArticulo, String nombreL) {
		return ejbControlInventarioRemote.traerRegistrosInventarioPorBEG(nombre, estadoArticulo, nombreL);
	}
	
	public List<ControlInventario> traerRegistrosInventarioPorBG(String nombre,String nombreL) {
		return ejbControlInventarioRemote.traerRegistrosInventarioPorBG(nombre,nombreL);
	}

	public List<ControlInventario> traerRegistrosInventarioPorBEGP(String nombre, String estadoArticulo, String nombreL,String proveedorUltimaCompra) {
		return ejbControlInventarioRemote.traerRegistrosInventarioPorBEGP(nombre, estadoArticulo, nombreL, proveedorUltimaCompra);
	}

	
	public List<ControlInventario> traerRegistrosInventarioPorBEP(String nombre, String estadoArticulo, String proveedorUltimaCompra) {
		return ejbControlInventarioRemote.traerRegistrosInventarioPorBEP(nombre, estadoArticulo, proveedorUltimaCompra);
	}

	public List<ControlInventario> traerRegistrosInventarioPorEP(String estadoArticulo, String proveedorUltimaCompra) {
		return ejbControlInventarioRemote.traerRegistrosInventarioPorEP(estadoArticulo, proveedorUltimaCompra);
	}

	public List<ControlInventario> traerRegistrosInventarioConExistencias() {
		return ejbControlInventarioRemote.traerRegistrosInventarioConExistencias();
	}

	public List<ControlInventario> traerRegistrosInventarioCantAlerta() {
		return ejbControlInventarioRemote.traerRegistrosInventarioCantAlerta();
	}

	public DelegadoControlInventario() {
		
		try {
			ejbControlInventarioRemote = (EjbControlInventarioRemote) new InitialContext().lookup("java:global/SACJungla/EjbControlInventario!co.com.jungla.sac.negocio.ejb.EjbControlInventarioRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}