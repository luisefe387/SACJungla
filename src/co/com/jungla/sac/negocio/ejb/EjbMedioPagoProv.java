package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoMedioPagoProv;
import co.com.jungla.sac.persistencia.entidades.MedioPagoProv;

/**
 * Session Bean implementation class EjbFormaPago
 */
@Stateless
@LocalBean
public class EjbMedioPagoProv implements EjbMedioPagoProvRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoMedioPagoProv daoFormaPago;
	
    public EjbMedioPagoProv() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarFormaPago(MedioPagoProv formaPago) {
		daoFormaPago.insertar(formaPago);
	}

	@Override
	public void actualizarFormaPago(MedioPagoProv formaPago) {
		daoFormaPago.actualizar(formaPago);
		
	}

	@Override
	public void eliminarFormaPago(MedioPagoProv formaPago) {
		daoFormaPago.eliminar(formaPago);
		
	}

	@Override
	public List<MedioPagoProv> listarFormaPago() {
		List<MedioPagoProv> formasPago = daoFormaPago.listarTodo();
		return formasPago;
	}

}
