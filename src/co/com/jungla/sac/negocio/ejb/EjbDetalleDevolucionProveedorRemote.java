package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.DetalleDevolucionProveedor;

@Remote
public interface EjbDetalleDevolucionProveedorRemote {

	public void insertarDetalleDevolucionProveedor(DetalleDevolucionProveedor detalleDevolucionProveedor);
	
	public void actualizarDetalleDevolucionProveedor(DetalleDevolucionProveedor detalleDevolucionProveedor);
	
	public void eliminarDetalleDevolucionProveedor(DetalleDevolucionProveedor detalleDevolucionProveedor);
	
	public List<DetalleDevolucionProveedor> listarDetalleDevolucionProveedor();
	
	public List <DetalleDevolucionProveedor> traerUltimaDetalleDevolucionProveedor();
	
	public Object traerCantidadDevuelta(int idCompra, int codigo);
	
	public List <DetalleDevolucionProveedor> listarDetalleDevolucionProveedorPorIdentificacion(String identificacion);
	
	public List <DetalleDevolucionProveedor> traerDetalleDevolucionProveedorPorCodigo(int idDetalleDevolucionProveedor);
	
	public List <DetalleDevolucionProveedor> traerDetalleDevolucionProveedorPoridCompra(int idCompra);
	
	public List <DetalleDevolucionProveedor> listarDetallePorCodigoDevolucionProveedor(int idDevolucionProveedor);
	
	//public List <DetalleDevolucionProveedor> listarDetallePorCodigoCompra(int idCompra);
}
