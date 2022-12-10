package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoDetalleDevolucionCliente;
import co.com.jungla.sac.persistencia.entidades.DetalleDevolucionCliente;

/**
 * Session Bean implementation class EjbDevolucionCliente
 */
@Stateless
@LocalBean
public class EjbDetalleDevolucionCliente implements EjbDetalleDevolucionClienteRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoDetalleDevolucionCliente daoDetalleDevolucionCliente;
	
    public EjbDetalleDevolucionCliente() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarDetalleDevolucionCliente(DetalleDevolucionCliente detalleDevolucionCliente) {
		daoDetalleDevolucionCliente.insertar(detalleDevolucionCliente);
		
	}

	@Override
	public void actualizarDetalleDevolucionCliente(DetalleDevolucionCliente detalleDevolucionCliente) {
		daoDetalleDevolucionCliente.actualizar(detalleDevolucionCliente);
	}

	@Override
	public void eliminarDetalleDevolucionCliente(DetalleDevolucionCliente detalleDevolucionCliente) {
		daoDetalleDevolucionCliente.eliminar(detalleDevolucionCliente);
	}

	@Override
	public List<DetalleDevolucionCliente> listarDetalleDevolucionCliente() {
		List<DetalleDevolucionCliente> detalleDevolucionCliente = daoDetalleDevolucionCliente.listarTodo();
		return detalleDevolucionCliente;
	}

	@Override
	public List <DetalleDevolucionCliente> traerUltimaDetalleDevolucionCliente() {
		List <DetalleDevolucionCliente> detalleDevolucionCliente = daoDetalleDevolucionCliente.traerultimaDetalleDevolucionCliente();
		return detalleDevolucionCliente;
	}

	@Override
	public Object traerCantidadDevuelta(int idVenta, int codigo) {
		Object cantidadDevuelta = daoDetalleDevolucionCliente.traerCantidadDevuelta(idVenta, codigo);
		return cantidadDevuelta;
	}

	@Override
	public List<DetalleDevolucionCliente> listarDetalleDevolucionClientePorIdentificacion(String identificacion) {
		List <DetalleDevolucionCliente> detalleDevolucionCliente = daoDetalleDevolucionCliente.listarDetalleDevolucionClientePorIdentificacion(identificacion);
		return detalleDevolucionCliente;
	}

	@Override
	public List<DetalleDevolucionCliente> traerDetalleDevolucionClientePorCodigo(int idDetalleDevolucionCliente) {
		List <DetalleDevolucionCliente> detalleDevolucionCliente = daoDetalleDevolucionCliente.traerDetalleDevolucionClientePorCodigo(idDetalleDevolucionCliente);
		return detalleDevolucionCliente;
	}

	@Override
	public List<DetalleDevolucionCliente> traerDetalleDevolucionClientePoridVenta(int idVenta) {
		List <DetalleDevolucionCliente> detalleDevolucionCliente = daoDetalleDevolucionCliente.traerDetalleDevolucionClientePoridVenta(idVenta);
		return detalleDevolucionCliente;
	}

	@Override
	public List<DetalleDevolucionCliente> listarDetallePorCodigoDevolucionCliente(int idDevolucionCliente) {
		List <DetalleDevolucionCliente> detalleDevolucionCliente = daoDetalleDevolucionCliente.listarDetallePorCodigoDevolucionCliente(idDevolucionCliente);
		return detalleDevolucionCliente;
	}
}
