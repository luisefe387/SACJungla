package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoDetalleDevolucionProveedor;
import co.com.jungla.sac.persistencia.entidades.DetalleDevolucionProveedor;

/**
 * Session Bean implementation class EjbDevolucionProveedor
 */
@Stateless
@LocalBean
public class EjbDetalleDevolucionProveedor implements EjbDetalleDevolucionProveedorRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoDetalleDevolucionProveedor daoDetalleDevolucionProveedor;
	
    public EjbDetalleDevolucionProveedor() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarDetalleDevolucionProveedor(DetalleDevolucionProveedor detalleDevolucionProveedor) {
		daoDetalleDevolucionProveedor.insertar(detalleDevolucionProveedor);
		
	}

	@Override
	public void actualizarDetalleDevolucionProveedor(DetalleDevolucionProveedor detalleDevolucionProveedor) {
		daoDetalleDevolucionProveedor.actualizar(detalleDevolucionProveedor);
	}

	@Override
	public void eliminarDetalleDevolucionProveedor(DetalleDevolucionProveedor detalleDevolucionProveedor) {
		daoDetalleDevolucionProveedor.eliminar(detalleDevolucionProveedor);
	}

	@Override
	public List<DetalleDevolucionProveedor> listarDetalleDevolucionProveedor() {
		List<DetalleDevolucionProveedor> detalleDevolucionProveedor = daoDetalleDevolucionProveedor.listarTodo();
		return detalleDevolucionProveedor;
	}

	@Override
	public List <DetalleDevolucionProveedor> traerUltimaDetalleDevolucionProveedor() {
		List <DetalleDevolucionProveedor> detalleDevolucionProveedor = daoDetalleDevolucionProveedor.traerultimaDetalleDevolucionProveedor();
		return detalleDevolucionProveedor;
	}

	@Override
	public Object traerCantidadDevuelta(int idCompra, int codigo) {
		Object cantidadDevuelta = daoDetalleDevolucionProveedor.traerCantidadDevuelta(idCompra, codigo);
		return cantidadDevuelta;
	}

	@Override
	public List<DetalleDevolucionProveedor> listarDetalleDevolucionProveedorPorIdentificacion(String identificacion) {
		List <DetalleDevolucionProveedor> detalleDevolucionProveedor = daoDetalleDevolucionProveedor.listarDetalleDevolucionProveedorPorIdentificacion(identificacion);
		return detalleDevolucionProveedor;
	}

	@Override
	public List<DetalleDevolucionProveedor> traerDetalleDevolucionProveedorPorCodigo(int idDetalleDevolucionProveedor) {
		List <DetalleDevolucionProveedor> detalleDevolucionProveedor = daoDetalleDevolucionProveedor.traerDetalleDevolucionProveedorPorCodigo(idDetalleDevolucionProveedor);
		return detalleDevolucionProveedor;
	}

	@Override
	public List<DetalleDevolucionProveedor> traerDetalleDevolucionProveedorPoridCompra(int idCompra) {
		List <DetalleDevolucionProveedor> detalleDevolucionProveedor = daoDetalleDevolucionProveedor.traerDetalleDevolucionProveedorPoridVenta(idCompra);
		return detalleDevolucionProveedor;
	}

	@Override
	public List<DetalleDevolucionProveedor> listarDetallePorCodigoDevolucionProveedor(int idDevolucionProveedor) {
		List <DetalleDevolucionProveedor> detalleDevolucionProveedor = daoDetalleDevolucionProveedor.listarDetallePorCodigoDevolucionProveedor(idDevolucionProveedor);
		return detalleDevolucionProveedor;
	}
	
	
}
