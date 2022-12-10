package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoDetalleVenta;
import co.com.jungla.sac.persistencia.entidades.DetalleVenta;

/**
 * Session Bean implementation class EjbDetalleVenta
 */
@Stateless
@LocalBean
public class EjbDetalleVenta implements EjbDetalleVentaRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoDetalleVenta daoDetalleVenta;
	
    public EjbDetalleVenta() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarDetalleVenta(DetalleVenta detalleVenta) {
		daoDetalleVenta.insertar(detalleVenta);
		
	}

	@Override
	public void actualizarDetalleVenta(DetalleVenta detalleVenta) {
		daoDetalleVenta.actualizar(detalleVenta);
	}

	@Override
	public void eliminarDetalleVenta(DetalleVenta detalleVenta) {
		daoDetalleVenta.eliminar(detalleVenta);
	}

	@Override
	public List<DetalleVenta> listarDetalleVenta() {
		List<DetalleVenta> detalleVenta = daoDetalleVenta.listarTodo();
		return detalleVenta;
	}

	
	@Override
	public DetalleVenta traerUltimoRegistroDetalleVenta() {
		DetalleVenta detalleVenta = daoDetalleVenta.traerUltimoRegistroDetalleVenta();
		return detalleVenta;
	}

	@Override
	public List<DetalleVenta> listarDetallePorCodigoVenta(int idVenta) {
		List<DetalleVenta> detalleVenta = daoDetalleVenta.listarDetallePorCodigoVenta(idVenta);
		return detalleVenta;
	}

	@Override
	public List<DetalleVenta> traerDetallePorCodigoDetalle(int idDetalleVenta) {
		List<DetalleVenta> detalleVenta = daoDetalleVenta.traerDetallePorCodigoDetalle(idDetalleVenta);
		return detalleVenta;
	}
	
	
}
