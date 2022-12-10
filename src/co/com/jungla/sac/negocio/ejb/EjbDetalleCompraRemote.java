package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.DetalleCompra;

@Remote
public interface EjbDetalleCompraRemote {

	public void insertarDetalleCompra(DetalleCompra detalleCompra);
	
	public void actualizarDetalleCompra(DetalleCompra detalleCompra);
	
	public void eliminarDetalleCompra(DetalleCompra detalleCompra);
	
	public List<DetalleCompra> listarDetalleCompra();
	
	public List <DetalleCompra> listarDetallePorCodigoCompra(int idCompra);
	
	public List <DetalleCompra> traerUltimoRegistroDetalleCompra();
}
