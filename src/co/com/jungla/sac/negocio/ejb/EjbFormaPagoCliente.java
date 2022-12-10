package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoFormaPagoCliente;
import co.com.jungla.sac.persistencia.entidades.FormaPagoCliente;

/**
 * Session Bean implementation class EjbFormaPagoCliente
 */
@Stateless
@LocalBean
public class EjbFormaPagoCliente implements EjbFormaPagoClienteRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoFormaPagoCliente daoFormaPagoCliente;
	
    public EjbFormaPagoCliente() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarFormaPagoCliente(FormaPagoCliente formaPagoCliente) {
		daoFormaPagoCliente.insertar(formaPagoCliente);
	}

	@Override
	public void actualizarFormaPagoCliente(FormaPagoCliente formaPagoCliente) {
		daoFormaPagoCliente.actualizar(formaPagoCliente);
		
	}

	@Override
	public void eliminarFormaPagoCliente(FormaPagoCliente formaPagoCliente) {
		daoFormaPagoCliente.eliminar(formaPagoCliente);
		
	}

	@Override
	public List<FormaPagoCliente> listarFormaPagoCliente() {
		List<FormaPagoCliente> formasPago = daoFormaPagoCliente.listarTodo();
		return formasPago;
	}

	@Override
	public List<FormaPagoCliente> traerFormaPagoClientePorDescripcion(String descripcion) {
		List<FormaPagoCliente> formasPago = daoFormaPagoCliente.traerFormaPagoClientePorDescripcion(descripcion);
		return formasPago;
	}

	@Override
	public List<FormaPagoCliente> traerFormaPagoClientePorIdFormaPago(int idFormaPagoCliente) {
		List<FormaPagoCliente> formasPago = daoFormaPagoCliente.traerFormaPagoClientePorIdFormaPago(idFormaPagoCliente);
		return formasPago;
	}
}
