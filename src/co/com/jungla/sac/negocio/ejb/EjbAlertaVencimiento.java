package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoAlertaVencimiento;
import co.com.jungla.sac.persistencia.entidades.AlertaVencimiento;

/**
 * Session Bean implementation class EjbCompraArticulos
 */
@Stateless
@LocalBean
public class EjbAlertaVencimiento implements EjbAlertaVencimientoRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoAlertaVencimiento daoAlertaVencimiento;
	
    public EjbAlertaVencimiento() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarAlertaVencimiento(AlertaVencimiento alertaVencimiento) {
		daoAlertaVencimiento.insertar(alertaVencimiento);
		
	}

	@Override
	public void actualizarAlertaVencimiento(AlertaVencimiento alertaVencimiento) {
		daoAlertaVencimiento.actualizar(alertaVencimiento);
		
	}

	@Override
	public void eliminarAlertaVencimiento(AlertaVencimiento alertaVencimiento) {
		daoAlertaVencimiento.eliminar(alertaVencimiento);
		
	}

	@Override
	public List<AlertaVencimiento> traerUltimaAlertaVencimiento() {
		List<AlertaVencimiento> alertaVencimiento = daoAlertaVencimiento.traerUltimaAlertaVencimiento();
		return alertaVencimiento;
	}

	@Override
	public List<AlertaVencimiento> listarAlertaVencimientoPorIdAlerta(int idAlerta) {
		List<AlertaVencimiento> alertaVencimiento = daoAlertaVencimiento.listarAlertaVencimientoPorIdAlerta(idAlerta);
		return alertaVencimiento;
	}

	@Override
	public List<AlertaVencimiento> traerArticulosAVencerse(Date inicioFechaE,Date finFechaE) {
		List<AlertaVencimiento> alertaVencimiento = daoAlertaVencimiento.traerArticulosAVencerse(inicioFechaE, finFechaE);
		return alertaVencimiento;
	}
}
