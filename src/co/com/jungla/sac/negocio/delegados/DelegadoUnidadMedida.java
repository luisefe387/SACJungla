package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbUnidadMedidaRemote;
import co.com.jungla.sac.persistencia.entidades.UnidadMedida;

public class DelegadoUnidadMedida implements EjbUnidadMedidaRemote{
	
	EjbUnidadMedidaRemote ejbUnidadMedidaRemote;

	public void insertarUnidadMedida(UnidadMedida unidadMedida) {
		ejbUnidadMedidaRemote.insertarUnidadMedida(unidadMedida);
	}

	public void actualizarUnidadMedida(UnidadMedida unidadMedida) {
		ejbUnidadMedidaRemote.actualizarUnidadMedida(unidadMedida);
	}

	public void eliminarUnidadMedida(UnidadMedida unidadMedida) {
		ejbUnidadMedidaRemote.eliminarUnidadMedida(unidadMedida);
	}

	public List<UnidadMedida> listarUnidadMedida() {
		return ejbUnidadMedidaRemote.listarUnidadMedida();
	}
	
	public UnidadMedida traerUnidadMedida(String nombre) {
		return ejbUnidadMedidaRemote.traerUnidadMedida(nombre);
	}

	public DelegadoUnidadMedida() {
		try {
			ejbUnidadMedidaRemote = (EjbUnidadMedidaRemote) new InitialContext().lookup("java:global/SACJungla/EjbUnidadMedida!co.com.jungla.sac.negocio.ejb.EjbUnidadMedidaRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
