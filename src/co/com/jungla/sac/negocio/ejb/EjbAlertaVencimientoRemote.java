package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.AlertaVencimiento;


@Remote
public interface EjbAlertaVencimientoRemote {

	public void insertarAlertaVencimiento(AlertaVencimiento alertaVencimiento);
	
	public void actualizarAlertaVencimiento(AlertaVencimiento alertaVencimiento);
	
	public void eliminarAlertaVencimiento(AlertaVencimiento alertaVencimiento);
	
	public List<AlertaVencimiento> traerUltimaAlertaVencimiento();
	
	public List <AlertaVencimiento> listarAlertaVencimientoPorIdAlerta(int idAlerta);
	
	public List <AlertaVencimiento> traerArticulosAVencerse(Date inicioFechaE, Date finFechaE);
}
