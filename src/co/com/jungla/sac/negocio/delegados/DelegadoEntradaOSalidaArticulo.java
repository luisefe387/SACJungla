package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbEntradaOSalidaArticuloRemote;
import co.com.jungla.sac.persistencia.entidades.EntradaOSalidaArticulo;

public class DelegadoEntradaOSalidaArticulo implements EjbEntradaOSalidaArticuloRemote {

	EjbEntradaOSalidaArticuloRemote ejbEntradaOSalidaArticuloRemote;

	public void insertarEntradaOSalidaArticulo(EntradaOSalidaArticulo entradaOSalidaArticulo) {
		ejbEntradaOSalidaArticuloRemote.insertarEntradaOSalidaArticulo(entradaOSalidaArticulo);
	}

	public void actualizarEntradaOSalidaArticulo(EntradaOSalidaArticulo entradaOSalidaArticulo) {
		ejbEntradaOSalidaArticuloRemote.actualizarEntradaOSalidaArticulo(entradaOSalidaArticulo);
	}

	public void eliminarEntradaOSalidaArticulo(EntradaOSalidaArticulo entradaOSalidaArticulo) {
		ejbEntradaOSalidaArticuloRemote.eliminarEntradaOSalidaArticulo(entradaOSalidaArticulo);
	}

	public List<EntradaOSalidaArticulo> listarEntradaOSalidaArticulo() {
		return ejbEntradaOSalidaArticuloRemote.listarEntradaOSalidaArticulo();
	}

	public EntradaOSalidaArticulo encontarPorEntradaOSalidaArticulo(String idEntradaOSalidaArticulo) {
		return ejbEntradaOSalidaArticuloRemote.encontarPorEntradaOSalidaArticulo(idEntradaOSalidaArticulo);
	}
	
	public EntradaOSalidaArticulo traerUltimaEntradaOSalida() {
		return ejbEntradaOSalidaArticuloRemote.traerUltimaEntradaOSalida();
	}

	public DelegadoEntradaOSalidaArticulo() {
		try {
			ejbEntradaOSalidaArticuloRemote = (EjbEntradaOSalidaArticuloRemote) new InitialContext().lookup("java:global/SACJungla/EjbEntradaOSalidaArticulo!co.com.jungla.sac.negocio.ejb.EjbEntradaOSalidaArticuloRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
}
