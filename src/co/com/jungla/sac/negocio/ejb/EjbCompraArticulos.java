package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoCompraArticulos;
import co.com.jungla.sac.persistencia.entidades.CompraArticulos;

/**
 * Session Bean implementation class EjbCompraArticulos
 */
@Stateless
@LocalBean
public class EjbCompraArticulos implements EjbCompraArticulosRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoCompraArticulos daoCompraArticulos;
	
    public EjbCompraArticulos() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarCompraArticulos(CompraArticulos compraArticulos) {
		daoCompraArticulos.insertar(compraArticulos);
	}

	@Override
	public void actualizarCompraArticulos(CompraArticulos compraArticulos) {
		daoCompraArticulos.actualizar(compraArticulos);
	}

	@Override
	public void eliminarCompraArticulos(CompraArticulos compraArticulos) {
		daoCompraArticulos.eliminar(compraArticulos);
	}

	@Override
	public List<CompraArticulos> listarCompraArticulos() {
		List<CompraArticulos> compraArticulos = daoCompraArticulos.listarTodo();
		return compraArticulos;
	}

	@Override
	public List<CompraArticulos> traerCodigoCompra() {
		List<CompraArticulos> compraArticulos = daoCompraArticulos.traerCodigoCompra();
		return compraArticulos;
	}

	
	@Override
	public List<CompraArticulos> traerCompraPorFactProv(int factProv) {
		List<CompraArticulos> compraArticulos = daoCompraArticulos.traerCompraPorFactProv(factProv);
		return compraArticulos;
	}

	@Override
	public List<CompraArticulos> reportarComprasPorFE(Date inicioFechaE, Date finFechaE) {
		List<CompraArticulos> compraArticulos = daoCompraArticulos.reportarComprasPorFE(inicioFechaE,finFechaE);
		return compraArticulos;
	}

	@Override
	public List<CompraArticulos> reportarComprasPorPFE(String nombre, Date inicioFechaE, Date finFechaE) {
		List<CompraArticulos> compraArticulos = daoCompraArticulos.reportarComprasPorPFE(nombre,inicioFechaE,finFechaE);
		return compraArticulos;
	}

	@Override
	public CompraArticulos traerCompraPorCodigo(int idCompra) {
		CompraArticulos compraArticulos = daoCompraArticulos.traerCompraPorCodigo(idCompra);
		return compraArticulos;
	}

	@Override
	public CompraArticulos traerTodaCompraPorCodigo(int idCompra) {
		CompraArticulos compraArticulos = daoCompraArticulos.traerTodaCompraPorCodigo(idCompra);
		return compraArticulos;
	}

	
	
}
