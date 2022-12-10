package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.DetalleOrdenCompra;
import co.com.jungla.sac.persistencia.entidades.OrdenCompraArticulos;


@Remote
public interface EjbDetalleOrdenCompraRemote {

	public void insertarDetalleOrdenCompra(DetalleOrdenCompra detalleOrdenCompra);
	
	public void actualizarDetalleOrdenCompra(DetalleOrdenCompra detalleOrdenCompra);
	
	public void eliminarDetalleOrdenCompra(DetalleOrdenCompra detalleOrdenCompra);
	
	public List<DetalleOrdenCompra> listarDetalleOrdenCompra();
	
	public List <DetalleOrdenCompra> listarDetalleOrdenPorCodigoOrden(int codigo);
}
