package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.EntradaOSalidaArticulo;

@Remote
public interface EjbEntradaOSalidaArticuloRemote {
	
	public void insertarEntradaOSalidaArticulo(EntradaOSalidaArticulo entradaOSalidaArticulo);
	
	public void actualizarEntradaOSalidaArticulo(EntradaOSalidaArticulo entradaOSalidaArticulo);
	
	public void eliminarEntradaOSalidaArticulo(EntradaOSalidaArticulo entradaOSalidaArticulo);
	
	public List<EntradaOSalidaArticulo> listarEntradaOSalidaArticulo();
	
	public EntradaOSalidaArticulo encontarPorEntradaOSalidaArticulo(String idEntradaOSalidaArticulo);
	
	public EntradaOSalidaArticulo traerUltimaEntradaOSalida();

}
