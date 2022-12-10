package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoDetalleCompra;
import co.com.jungla.sac.persistencia.dao.DaoDetalleOrdenCompra;
import co.com.jungla.sac.persistencia.entidades.DetalleCompra;
import co.com.jungla.sac.persistencia.entidades.DetalleOrdenCompra;
import co.com.jungla.sac.persistencia.entidades.OrdenCompraArticulos;

/**
 * Session Bean implementation class EjbDetalleCompra
 */
@Stateless
@LocalBean
public class EjbDetalleOrdenCompra implements EjbDetalleOrdenCompraRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoDetalleOrdenCompra daoDetalleOrdenCompra;
	
    public EjbDetalleOrdenCompra() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarDetalleOrdenCompra(DetalleOrdenCompra detalleOrdenCompra) {
		daoDetalleOrdenCompra.insertar(detalleOrdenCompra);
	}

	@Override
	public void actualizarDetalleOrdenCompra(
			DetalleOrdenCompra detalleOrdenCompra) {
		daoDetalleOrdenCompra.actualizar(detalleOrdenCompra);
	}

	@Override
	public void eliminarDetalleOrdenCompra(DetalleOrdenCompra detalleOrdenCompra) {
		daoDetalleOrdenCompra.eliminar(detalleOrdenCompra);
	}

	@Override
	public List<DetalleOrdenCompra> listarDetalleOrdenCompra() {
		List<DetalleOrdenCompra> detalleOrdenCompra = daoDetalleOrdenCompra.listarTodo();
		return detalleOrdenCompra;
	}

	@Override
	public List<DetalleOrdenCompra> listarDetalleOrdenPorCodigoOrden(int codigo) {
		List<DetalleOrdenCompra> detalleOrdenCompra = daoDetalleOrdenCompra.listarDetalleOrdenPorCodigoOrden(codigo);
		return detalleOrdenCompra;
	}
}
