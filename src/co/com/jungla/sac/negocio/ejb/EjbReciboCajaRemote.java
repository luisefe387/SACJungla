package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.ReciboCaja;


@Remote
public interface EjbReciboCajaRemote {

	public void insertarReciboCaja(ReciboCaja reciboCaja);
	
	public void actualizarReciboCaja(ReciboCaja reciboCaja);
	
	public void eliminarReciboCaja(ReciboCaja reciboCaja);
	
	public List <ReciboCaja> traerUltimoReciboCaja();
	
	public List <ReciboCaja> traerRecibosCajaPorVenta(int idVenta);
	
	public List <ReciboCaja> reportarRecibosPorF(Date inicioFechaE, Date finFechaE);
	
	public List <ReciboCaja> reportarRecibosPorFV(String identificacion, Date inicioFechaE, Date finFechaE);
	
	public List <ReciboCaja> traerRecibosCajaPorIdRecibo(int idReciboCaja);
	
}
