package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoOrdenCompraArticulos;
import co.com.jungla.sac.persistencia.entidades.OrdenCompraArticulos;

/**
 * Session Bean implementation class EjbCompraArticulos
 */
@Stateless
@LocalBean
public class EjbOrdenCompraArticulos implements EjbOrdenCompraArticulosRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoOrdenCompraArticulos daoOrdenCompraArticulos;
	
    public EjbOrdenCompraArticulos() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarOrdenCompraArticulos(
			OrdenCompraArticulos ordenCompraArticulos) {
		daoOrdenCompraArticulos.insertar(ordenCompraArticulos);
	}

	@Override
	public void actualizarOrdenCompraArticulos(
			OrdenCompraArticulos ordenCompraArticulos) {
		daoOrdenCompraArticulos.actualizar(ordenCompraArticulos);
	}

	@Override
	public void eliminarOrdenCompraArticulos(
			OrdenCompraArticulos ordenCompraArticulos) {
		daoOrdenCompraArticulos.eliminar(ordenCompraArticulos);
		
	}

	@Override
	public List<OrdenCompraArticulos> listarOrdenCompraArticulos() {
		List<OrdenCompraArticulos> ordenCompraArticulos = daoOrdenCompraArticulos.listarTodo();
		return ordenCompraArticulos;
	}

	@Override
	public List<OrdenCompraArticulos> traerCodigoOrdenCompra() {
		List<OrdenCompraArticulos> ordenCompraArticulos = daoOrdenCompraArticulos.traerCodigoOrdenCompra();
		return ordenCompraArticulos;
	}
	
	

	@Override
	public List<OrdenCompraArticulos> traerCodigoOrdenPorCompra(int idOrdenCompra) {
		List<OrdenCompraArticulos> ordenCompraArticulos = daoOrdenCompraArticulos.traerCodigoOrdenPorCompra(idOrdenCompra);
		return ordenCompraArticulos;
	}

	@Override
	public List<OrdenCompraArticulos> reportarOrdenesCompra() {
		List<OrdenCompraArticulos> ordenesCompraArticulos = daoOrdenCompraArticulos.reportarOrdenesCompra();
		return ordenesCompraArticulos;
	}

	@Override
	public List<OrdenCompraArticulos> reportarOrdenesPorPFEE(String nombre, Date inicioFechaE, Date finFechaE, String estado) {
		List<OrdenCompraArticulos> ordenesCompraArticulos = daoOrdenCompraArticulos.reportarOrdenesPorPFEE(nombre,inicioFechaE,finFechaE,estado);
		return ordenesCompraArticulos;
	}

	@Override
	public List<OrdenCompraArticulos> reportarOrdenesPorFE(Date inicioFechaE, Date finFechaE) {
		List<OrdenCompraArticulos> ordenesCompraArticulos = daoOrdenCompraArticulos.reportarOrdenesPorFE(inicioFechaE,finFechaE);
		return ordenesCompraArticulos;
	}

	@Override
	public List<OrdenCompraArticulos> reportarOrdenesPorPFE(String nombre, Date inicioFechaE, Date finFechaE) {
		List<OrdenCompraArticulos> ordenesCompraArticulos = daoOrdenCompraArticulos.reportarOrdenesPorPFE(nombre,inicioFechaE,finFechaE);
		return ordenesCompraArticulos;
	}

	@Override
	public List<OrdenCompraArticulos> reportarOrdenesPorFEE(Date inicioFechaE, Date finFechaE, String estado) {
		List<OrdenCompraArticulos> ordenesCompraArticulos = daoOrdenCompraArticulos.reportarOrdenesPorFEE(inicioFechaE,finFechaE,estado);
		return ordenesCompraArticulos;
	}
	
}
