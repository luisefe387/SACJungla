package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbRecordatorioRemote;
import co.com.jungla.sac.persistencia.entidades.Recordatorio;


public class DelegadoRecordatorio implements EjbRecordatorioRemote{
	
	EjbRecordatorioRemote ejbRecordatorioRemote;
	

	public void insertarRecordatorio(Recordatorio recordatorio) {
		ejbRecordatorioRemote.insertarRecordatorio(recordatorio);
	}

	public void actualizarRecordatorio(Recordatorio recordatorio) {
		ejbRecordatorioRemote.actualizarRecordatorio(recordatorio);
	}

	public void eliminarRecordatorio(Recordatorio recordatorio) {
		ejbRecordatorioRemote.eliminarRecordatorio(recordatorio);
	}
	
	public List<Recordatorio> listarRecordatorios() {
		return ejbRecordatorioRemote.listarRecordatorios();
	}

	public DelegadoRecordatorio() {
		try {
			ejbRecordatorioRemote = (EjbRecordatorioRemote) new InitialContext().lookup("java:global/SACJungla/EjbRecordatorio!co.com.jungla.sac.negocio.ejb.EjbRecordatorioRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
