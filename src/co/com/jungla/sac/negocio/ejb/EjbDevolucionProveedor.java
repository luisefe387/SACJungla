package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoDevolucionProveedor;
import co.com.jungla.sac.persistencia.entidades.DevolucionProveedor;
import co.com.jungla.sac.persistencia.entidades.DevolucionProveedor;

/**
 * Session Bean implementation class EjbDevolucionProveedor
 */
@Stateless
@LocalBean
public class EjbDevolucionProveedor implements EjbDevolucionProveedorRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoDevolucionProveedor daoDevolucionProveedor;
	
    public EjbDevolucionProveedor() {
        // TODO Auto-generated constructor stub
    }

    @Override
	public void insertarDevolucionProveedor(DevolucionProveedor devolucionProveedor) {
		daoDevolucionProveedor.insertar(devolucionProveedor);
		
	}

	@Override
	public void actualizarDevolucionProveedor(DevolucionProveedor devolucionProveedor) {
		daoDevolucionProveedor.actualizar(devolucionProveedor);
	}

	@Override
	public void eliminarDevolucionProveedor(DevolucionProveedor devolucionProveedor) {
		daoDevolucionProveedor.eliminar(devolucionProveedor);
	}

	@Override
	public List<DevolucionProveedor> listarDevolucionProveedor() {
		List<DevolucionProveedor> devolucionProveedor = daoDevolucionProveedor.listarTodo();
		return devolucionProveedor;
	}

	@Override
	public List <DevolucionProveedor> traerUltimaDevolucionProveedor() {
		List <DevolucionProveedor> devolucionProveedor = daoDevolucionProveedor.traerultimaDevolucionProveedor();
		return devolucionProveedor;
	}

	@Override
	public List<DevolucionProveedor> listarDevolucionProveedorPorIdentificacion(String identificacion) {
		List <DevolucionProveedor> devolucionProveedor = daoDevolucionProveedor.listarDevolucionProveedorPorIdentificacion(identificacion);;
		return devolucionProveedor;
	}

	@Override
	public List<DevolucionProveedor> traerDevolucionProveedorPorCodigo(int idDevolucionProveedor) {
		List <DevolucionProveedor> devolucionProveedor = daoDevolucionProveedor.traerDevolucionProveedorPorCodigo(idDevolucionProveedor);;
		return devolucionProveedor;
	}

	@Override
	public List<DevolucionProveedor> reportarDevolucionesPorF(Date inicioFecha,Date finFecha) {
		List <DevolucionProveedor> devolucionProveedor = daoDevolucionProveedor.reportarDevolucionesPorF(inicioFecha, finFecha);
		return devolucionProveedor;
	}

	@Override
	public List<DevolucionProveedor> reportarDevolucionesPorFP(Date inicioFecha, Date finFecha, String identificacion) {
		List <DevolucionProveedor> devolucionProveedor = daoDevolucionProveedor.reportarDevolucionesPorFP(inicioFecha, finFecha, identificacion);
		return devolucionProveedor;
	}

	@Override
	public List<DevolucionProveedor> reportarDevolucionesPorFPE(Date inicioFecha, Date finFecha, String identificacion,String estado) {
		List <DevolucionProveedor> devolucionProveedor = daoDevolucionProveedor.reportarDevolucionesPorFPE(inicioFecha, finFecha, identificacion, estado);
		return devolucionProveedor;
	}

	@Override
	public List<DevolucionProveedor> reportarDevolucionesPorFE(Date inicioFecha, Date finFecha, String estado) {
		List <DevolucionProveedor> devolucionProveedor = daoDevolucionProveedor.reportarDevolucionesPorFE(inicioFecha, finFecha, estado);
		return devolucionProveedor;
	}
	
	
}
