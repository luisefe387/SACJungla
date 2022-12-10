package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoAnticipoProveedor;
import co.com.jungla.sac.persistencia.entidades.AnticipoProveedor;

/**
 * Session Bean implementation class EjbCompraArticulos
 */
@Stateless
@LocalBean
public class EjbAnticipoProveedor implements EjbAnticipoProveedorRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoAnticipoProveedor daoAnticipoProveedor;
	
    public EjbAnticipoProveedor() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarAnticipoProveedor(AnticipoProveedor anticipoProveedor) {
		daoAnticipoProveedor.insertar(anticipoProveedor);
	}

	@Override
	public void actualizarAnticipoProveedor(AnticipoProveedor anticipoProveedor) {
		daoAnticipoProveedor.actualizar(anticipoProveedor);
	}

	@Override
	public void eliminarAnticipoProveedor(AnticipoProveedor anticipoProveedor) {
		daoAnticipoProveedor.eliminar(anticipoProveedor);
	}

	@Override
	public List<AnticipoProveedor> traerCodigoAnticipoProveedor() {
		List<AnticipoProveedor> anticipoProveedor = daoAnticipoProveedor.traerCodigoAnticipoProveedor();
		return anticipoProveedor;
	}

	@Override
	public List<AnticipoProveedor> listarAnticipoProveedorPorIdentificacion(String identificacion) {
		List<AnticipoProveedor> anticipoProveedor = daoAnticipoProveedor.listarAnticipoProveedorPorIdentificacion(identificacion);
		return anticipoProveedor;
	}

	@Override
	public List<AnticipoProveedor> traerAnticipoProveedorPorCodigo(int idAnticipoProveedor) {
		List<AnticipoProveedor> anticipoProveedor = daoAnticipoProveedor.traerAnticipoProveedorPorCodigo(idAnticipoProveedor);
		return anticipoProveedor;
	}

	@Override
	public List<AnticipoProveedor> reportarAnticiposPorF(Date inicioFechaE,Date finFechaE) {
		List<AnticipoProveedor> anticipoProveedor = daoAnticipoProveedor.reportarAnticiposPorF(inicioFechaE, finFechaE);
		return anticipoProveedor;
	}

	@Override
	public List<AnticipoProveedor> reportarAnticiposPorPF(Date inicioFechaE,Date finFechaE, String nombre) {
		List<AnticipoProveedor> anticipoProveedor = daoAnticipoProveedor.reportarAnticiposPorPF(inicioFechaE, finFechaE, nombre);
		return anticipoProveedor;
	}

	@Override
	public List<AnticipoProveedor> reportarAnticiposPorEF(Date inicioFechaE,Date finFechaE, String estadoAnticipo) {
		List<AnticipoProveedor> anticipoProveedor = daoAnticipoProveedor.reportarAnticiposPorEF(inicioFechaE, finFechaE, estadoAnticipo);
		return anticipoProveedor;
	}

	@Override
	public List<AnticipoProveedor> reportarAnticiposPorPEF(Date inicioFechaE,Date finFechaE, String nombre, String estadoAnticipo) {
		List<AnticipoProveedor> anticipoProveedor = daoAnticipoProveedor.reportarAnticiposPorPEF(inicioFechaE, finFechaE, nombre, estadoAnticipo);
		return anticipoProveedor;
	}
}
