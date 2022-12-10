package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbRemoteTipoCliente;
import co.com.jungla.sac.persistencia.entidades.TipoCliente;

public class DelegadoTipoCliente implements EjbRemoteTipoCliente{

	private EjbRemoteTipoCliente ejbRemoteTipoCliente;

	public void insertarTipoCliente(TipoCliente tipoCliente) {
		ejbRemoteTipoCliente.insertarTipoCliente(tipoCliente);
	}

	public void actualizarTipoCliente(TipoCliente tipoCliente) {
		ejbRemoteTipoCliente.actualizarTipoCliente(tipoCliente);
	}

	public void eliminarTipoCliente(TipoCliente tipoCliente) {
		ejbRemoteTipoCliente.eliminarTipoCliente(tipoCliente);
	}

	public List<TipoCliente> listarTipoCliente() {
		return ejbRemoteTipoCliente.listarTipoCliente();
	}
	
public DelegadoTipoCliente() {
		
		try {
			ejbRemoteTipoCliente = (EjbRemoteTipoCliente) new InitialContext().lookup("java:global/SACJungla/EjbTipoCliente!co.com.jungla.sac.negocio.ejb.EjbRemoteTipoCliente");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
