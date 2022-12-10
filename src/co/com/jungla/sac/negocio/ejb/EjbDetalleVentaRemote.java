package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.DetalleVenta;

@Remote
public interface EjbDetalleVentaRemote {

	public void insertarDetalleVenta(DetalleVenta detalleVenta);
	
	public void actualizarDetalleVenta(DetalleVenta detalleVenta);
	
	public void eliminarDetalleVenta(DetalleVenta detalleVenta);
	
	public List<DetalleVenta> listarDetalleVenta();
	
	public List <DetalleVenta> listarDetallePorCodigoVenta(int idVenta);
	
	public DetalleVenta traerUltimoRegistroDetalleVenta();
	
	public List <DetalleVenta> traerDetallePorCodigoDetalle(int idDetalleVenta);
}
