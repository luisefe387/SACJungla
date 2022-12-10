package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.TipoCliente;

@Remote
public interface EjbRemoteTipoCliente {

	public void insertarTipoCliente(TipoCliente tipoCliente);
	
	public void actualizarTipoCliente(TipoCliente tipoCliente);
	
	public void eliminarTipoCliente(TipoCliente tipoCliente);
	
	public List<TipoCliente> listarTipoCliente();
}
