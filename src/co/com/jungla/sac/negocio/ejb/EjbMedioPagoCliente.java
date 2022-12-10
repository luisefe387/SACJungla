package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoMedioPagoCliente;
import co.com.jungla.sac.persistencia.entidades.MedioPagoCliente;

/**
 * Session Bean implementation class EjbMedioPago
 */
@Stateless
@LocalBean
public class EjbMedioPagoCliente implements EjbMedioPagoClienteRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoMedioPagoCliente daoMedioPagoCliente;
	
    public EjbMedioPagoCliente() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarMedioPago(MedioPagoCliente medioPago) {
		daoMedioPagoCliente.insertar(medioPago);
	}

	@Override
	public void actualizarMedioPago(MedioPagoCliente medioPago) {
		daoMedioPagoCliente.actualizar(medioPago);
		
	}

	@Override
	public void eliminarMedioPago(MedioPagoCliente medioPago) {
		daoMedioPagoCliente.eliminar(medioPago);
		
	}

	@Override
	public List<MedioPagoCliente> listarMedioPago() {
		List<MedioPagoCliente> mediosPago = daoMedioPagoCliente.listarTodo();
		return mediosPago;
	}

	@Override
	public List<MedioPagoCliente> listarMedioPagosPorCodigoReciboCaja(int idReciboCaja) {
		List<MedioPagoCliente> mediosPago = daoMedioPagoCliente.listarMedioPagosPorCodigoReciboCaja(idReciboCaja);
		return mediosPago;
	}
}
