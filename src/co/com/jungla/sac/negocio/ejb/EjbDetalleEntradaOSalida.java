package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoDetalleEntradaOSalida;
import co.com.jungla.sac.persistencia.entidades.DetalleEntradaOSalida;


/**
 * Session Bean implementation class EjbDetalleEntradaOSalida
 */
@Stateless
@LocalBean
public class EjbDetalleEntradaOSalida implements EjbDetalleEntradaOSalidaRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoDetalleEntradaOSalida daoDetalleEntradaOSalida;
	
    public EjbDetalleEntradaOSalida() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarDetalleEntradaOSalida(DetalleEntradaOSalida detalleEntradaOSalida) {
		daoDetalleEntradaOSalida.insertar(detalleEntradaOSalida);
	}

	@Override
	public void actualizarDetalleEntradaOSalida(DetalleEntradaOSalida detalleEntradaOSalida) {
		daoDetalleEntradaOSalida.actualizar(detalleEntradaOSalida);
	}

	@Override
	public void eliminarDetalleEntradaOSalida(DetalleEntradaOSalida detalleEntradaOSalida) {
		daoDetalleEntradaOSalida.eliminar(detalleEntradaOSalida);
	}

	@Override
	public List<DetalleEntradaOSalida> listarDetalleEntradaOSalida() {
		List<DetalleEntradaOSalida> DetalleEntradaOSalidas = daoDetalleEntradaOSalida.listarTodo();
		return DetalleEntradaOSalidas;
	}

	@Override
	public DetalleEntradaOSalida encontarPorDetalleEntradaOSalida(String idDetalleEntradaOSalida) {
		return daoDetalleEntradaOSalida.encontrarPorLlave(idDetalleEntradaOSalida);
	}

	@Override
	public DetalleEntradaOSalida traerUltimoDetalleEntradaOSalida() {
		DetalleEntradaOSalida detalleEntradaOSalida = daoDetalleEntradaOSalida.traerUltimoDetalleEntradaOSalida();
		return detalleEntradaOSalida;
	}
}
