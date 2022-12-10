package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbContabilizacionRemote;
import co.com.jungla.sac.persistencia.entidades.Contabilizacion;

public class DelegadoContabilizacion implements EjbContabilizacionRemote{

	EjbContabilizacionRemote ejbContabilizacionRemote;

	public void insertarContabilizacion(Contabilizacion contabilizacion) {
		ejbContabilizacionRemote.insertarContabilizacion(contabilizacion);
	}

	public void actualizarContabilizacion(Contabilizacion contabilizacion) {
		ejbContabilizacionRemote.actualizarContabilizacion(contabilizacion);
	}

	public void eliminarContabilizacion(Contabilizacion contabilizacion) {
		ejbContabilizacionRemote.eliminarContabilizacion(contabilizacion);
	}

	public List<Contabilizacion> listarContabilizacion() {
		return ejbContabilizacionRemote.listarContabilizacion();
	}

	public DelegadoContabilizacion() {
		
		try {
			ejbContabilizacionRemote = (EjbContabilizacionRemote) new InitialContext().lookup("java:global/SACJungla/EjbContabilizacion!co.com.jungla.sac.negocio.ejb.EjbContabilizacionRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}