package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.Recordatorio;


@Remote
public interface EjbRecordatorioRemote {

	public void insertarRecordatorio(Recordatorio recordatorio);
	
	public void actualizarRecordatorio(Recordatorio recordatorio);
	
	public void eliminarRecordatorio(Recordatorio recordatorio);
	
	public List<Recordatorio> listarRecordatorios();
}
