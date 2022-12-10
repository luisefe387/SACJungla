package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.DetalleEntradaOSalida;

@Remote
public interface EjbDetalleEntradaOSalidaRemote {
	
	public void insertarDetalleEntradaOSalida(DetalleEntradaOSalida detalleEntradaOSalida);
	
	public void actualizarDetalleEntradaOSalida(DetalleEntradaOSalida detalleEntradaOSalida);
	
	public void eliminarDetalleEntradaOSalida(DetalleEntradaOSalida detalleEntradaOSalida);
	
	public List<DetalleEntradaOSalida> listarDetalleEntradaOSalida();
	
	public DetalleEntradaOSalida encontarPorDetalleEntradaOSalida(String idDetalleEntradaOSalida);
	
	public DetalleEntradaOSalida traerUltimoDetalleEntradaOSalida();

}
