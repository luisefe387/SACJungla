package co.com.jungla.sac.negocio.delegados;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbAlertaVencimientoRemote;
import co.com.jungla.sac.persistencia.entidades.AlertaVencimiento;


public class DelegadoAlertaVencimiento implements EjbAlertaVencimientoRemote{
	
	EjbAlertaVencimientoRemote ejbAlertaVencimientoRemote;
	

	public void insertarAlertaVencimiento(AlertaVencimiento alertaVencimiento) {
		ejbAlertaVencimientoRemote.insertarAlertaVencimiento(alertaVencimiento);
	}

	public void actualizarAlertaVencimiento(AlertaVencimiento alertaVencimiento) {
		ejbAlertaVencimientoRemote.actualizarAlertaVencimiento(alertaVencimiento);
	}

	public void eliminarAlertaVencimiento(AlertaVencimiento alertaVencimiento) {
		ejbAlertaVencimientoRemote.eliminarAlertaVencimiento(alertaVencimiento);
	}

	public List<AlertaVencimiento> traerUltimaAlertaVencimiento() {
		return ejbAlertaVencimientoRemote.traerUltimaAlertaVencimiento();
	}

	public List<AlertaVencimiento> listarAlertaVencimientoPorIdAlerta(int idAlerta) {
		return ejbAlertaVencimientoRemote.listarAlertaVencimientoPorIdAlerta(idAlerta);
	}

	
	public List<AlertaVencimiento> traerArticulosAVencerse(Date inicioFechaE,Date finFechaE) {
		return ejbAlertaVencimientoRemote.traerArticulosAVencerse(inicioFechaE,finFechaE);
	}

	public DelegadoAlertaVencimiento() {
		try {
			ejbAlertaVencimientoRemote = (EjbAlertaVencimientoRemote) new InitialContext().lookup("java:global/SACJungla/EjbAlertaVencimiento!co.com.jungla.sac.negocio.ejb.EjbAlertaVencimientoRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
