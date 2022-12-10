package co.com.jungla.sac.negocio.delegados;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbReciboCajaRemote;
import co.com.jungla.sac.persistencia.entidades.ReciboCaja;


public class DelegadoReciboCaja implements EjbReciboCajaRemote{
	
	EjbReciboCajaRemote ejbReciboCajaRemote;

	public void insertarReciboCaja(ReciboCaja reciboCaja) {
		ejbReciboCajaRemote.insertarReciboCaja(reciboCaja);
	}

	public void actualizarReciboCaja(ReciboCaja reciboCaja) {
		ejbReciboCajaRemote.actualizarReciboCaja(reciboCaja);
	}

	public void eliminarReciboCaja(ReciboCaja reciboCaja) {
		ejbReciboCajaRemote.eliminarReciboCaja(reciboCaja);
	}

	public List<ReciboCaja> traerUltimoReciboCaja() {
		return ejbReciboCajaRemote.traerUltimoReciboCaja();
	}

	public List<ReciboCaja> traerRecibosCajaPorVenta(int idVenta) {
		return ejbReciboCajaRemote.traerRecibosCajaPorVenta(idVenta);
	}
	
	public List<ReciboCaja> reportarRecibosPorF(Date inicioFechaE,Date finFechaE) {
		return ejbReciboCajaRemote.reportarRecibosPorF(inicioFechaE, finFechaE);
	}

	public List<ReciboCaja> reportarRecibosPorFV(String identificacion,Date inicioFechaE, Date finFechaE) {
		return ejbReciboCajaRemote.reportarRecibosPorFV(identificacion,inicioFechaE, finFechaE);
	}
	
	public List<ReciboCaja> traerRecibosCajaPorIdRecibo(int idReciboCaja) {
		return ejbReciboCajaRemote.traerRecibosCajaPorIdRecibo(idReciboCaja);
	}

	public DelegadoReciboCaja() {
		try {
			ejbReciboCajaRemote = (EjbReciboCajaRemote) new InitialContext().lookup("java:global/SACJungla/EjbReciboCaja!co.com.jungla.sac.negocio.ejb.EjbReciboCajaRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
