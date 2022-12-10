package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoReciboCaja;
import co.com.jungla.sac.persistencia.entidades.ReciboCaja;

/**
 * Session Bean implementation class EjbCompraArticulos
 */
@Stateless
@LocalBean
public class EjbReciboCaja implements EjbReciboCajaRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoReciboCaja daoReciboCaja;
	
    public EjbReciboCaja() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarReciboCaja(ReciboCaja reciboCaja) {
		daoReciboCaja.insertar(reciboCaja);
	}

	@Override
	public void actualizarReciboCaja(ReciboCaja reciboCaja) {
		daoReciboCaja.actualizar(reciboCaja);
	}

	@Override
	public void eliminarReciboCaja(ReciboCaja reciboCaja) {
		daoReciboCaja.eliminar(reciboCaja);
	}

	@Override
	public List<ReciboCaja> traerUltimoReciboCaja() {
		List<ReciboCaja> reciboCaja = daoReciboCaja.traerUltimoReciboCaja();
		return reciboCaja;
	}

	@Override
	public List<ReciboCaja> traerRecibosCajaPorVenta(int idVenta) {
		List<ReciboCaja> reciboCaja = daoReciboCaja.traerRecibosCajaPorVenta(idVenta);
		return reciboCaja;
	}

	@Override
	public List<ReciboCaja> reportarRecibosPorF(Date inicioFechaE,Date finFechaE) {
		List<ReciboCaja> reciboCaja = daoReciboCaja.reportarRecibosPorF(inicioFechaE, finFechaE);
		return reciboCaja;
	}

	@Override
	public List<ReciboCaja> reportarRecibosPorFV(String identificacion,Date inicioFechaE, Date finFechaE) {
		List<ReciboCaja> reciboCaja = daoReciboCaja.reportarRecibosPorFV(identificacion, inicioFechaE, finFechaE);
		return reciboCaja;
	}

	@Override
	public List<ReciboCaja> traerRecibosCajaPorIdRecibo(int idReciboCaja) {
		List<ReciboCaja> reciboCaja = daoReciboCaja.traerRecibosCajaPorIdRecibo(idReciboCaja);
		return reciboCaja;
	}
	
	
}
