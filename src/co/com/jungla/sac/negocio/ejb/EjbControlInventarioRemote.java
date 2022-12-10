package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.ControlInventario;

@Remote
public interface EjbControlInventarioRemote {

	public void insertarControlInventario(ControlInventario controlInventario);
	
	public void actualizarControlInventario(ControlInventario controlInventario);
	
	public void eliminarControlInventario(ControlInventario controlInventario);
	
	public List<ControlInventario> listarControlInventario();
	
	public List <ControlInventario> traerContInventarioPorCodigoArticulo(int codigo );
	
	public List <ControlInventario> traerRegistrosInventarioPorP(String proveedorUltimaCompra);
	
	public List <ControlInventario> traerRegistrosInventarioPorGP(String nombreL, String proveedorUltimaCompra);
	
	public List <ControlInventario> traerRegistrosInventarioPorE(String estadoArticulo);
	
	public List <ControlInventario> traerRegistrosInventarioPorEG(String estadoArticulo, String nombreL);
	
	public List <ControlInventario> traerRegistrosInventarioPorG(String nombreL);
	
	public List <ControlInventario> traerRegistrosInventarioPorEGP(String estadoArticulo, String nombreL, String proveedorUltimaCompra);
	
	public List <ControlInventario> traerRegistrosInventarioPorB(String nombre);
	
	public List <ControlInventario> traerRegistrosInventarioPorBP(String nombre, String proveedorUltimaCompra);
	
	public List <ControlInventario> traerRegistrosInventarioPorBGP(String nombre, String nombreL, String proveedorUltimaCompra);
	
	public List <ControlInventario> traerRegistrosInventarioPorBE(String nombre, String estadoArticulo);
	
	public List <ControlInventario> traerRegistrosInventarioPorBEG(String nombre, String estadoArticulo, String nombreL);
	
	public List <ControlInventario> traerRegistrosInventarioPorBG(String nombre, String nombreL);
	
	public List <ControlInventario> traerRegistrosInventarioPorBEGP(String nombre, String estadoArticulo, String nombreL, String proveedorUltimaCompra);
	
	public List <ControlInventario> traerRegistrosInventarioPorBEP(String nombre, String estadoArticulo, String proveedorUltimaCompra);
	
	public List <ControlInventario> traerRegistrosInventarioPorEP(String estadoArticulo, String proveedorUltimaCompra);
	
	public List <ControlInventario> traerRegistrosInventarioConExistencias();
	
	public List <ControlInventario> traerRegistrosInventarioCantAlerta();
}
