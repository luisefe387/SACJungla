package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoDetalleCompra;
import co.com.jungla.sac.persistencia.entidades.DetalleCompra;
import co.com.jungla.sac.persistencia.entidades.DetalleOrdenCompra;

/**
 * Session Bean implementation class EjbDetalleCompra
 */
@Stateless
@LocalBean
public class EjbDetalleCompra implements EjbDetalleCompraRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoDetalleCompra daoDetalleCompra;
	
    public EjbDetalleCompra() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarDetalleCompra(DetalleCompra detalleCompra) {
		daoDetalleCompra.insertar(detalleCompra);
		
	}

	@Override
	public void actualizarDetalleCompra(DetalleCompra detalleCompra) {
		daoDetalleCompra.actualizar(detalleCompra);
	}

	@Override
	public void eliminarDetalleCompra(DetalleCompra detalleCompra) {
		daoDetalleCompra.eliminar(detalleCompra);
	}

	@Override
	public List<DetalleCompra> listarDetalleCompra() {
		List<DetalleCompra> detalleCompra = daoDetalleCompra.listarTodo();
		return detalleCompra;
	}

	
	@Override
	public List<DetalleCompra> traerUltimoRegistroDetalleCompra() {
		List<DetalleCompra> detalleCompra = daoDetalleCompra.traerUltimoRegistroDetalleCompra();
		return detalleCompra;
	}

	@Override
	public List<DetalleCompra> listarDetallePorCodigoCompra(int idCompra) {
		List<DetalleCompra> detalleCompra = daoDetalleCompra.listarDetallePorCodigoCompra(idCompra);
		return detalleCompra;
	}
}
