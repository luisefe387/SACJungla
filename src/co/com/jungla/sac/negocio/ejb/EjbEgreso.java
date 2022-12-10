package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoEgreso;
import co.com.jungla.sac.persistencia.entidades.Egreso;

/**
 * Session Bean implementation class EjbCompraArticulos
 */
@Stateless
@LocalBean
public class EjbEgreso implements EjbEgresoRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoEgreso daoEgreso;
	
    public EjbEgreso() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarEgreso(Egreso egreso) {
		daoEgreso.insertar(egreso);
	}

	@Override
	public void actualizarEgreso(Egreso egreso) {
		daoEgreso.actualizar(egreso);
	}

	@Override
	public void eliminarEgreso(Egreso egreso) {
		daoEgreso.eliminar(egreso);
	}

	@Override
	public List<Egreso> traerCodigoEgreso() {
		List<Egreso> egreso = daoEgreso.traerCodigoEgreso();
		return egreso;
	}

	@Override
	public List<Egreso> traerEgresoPorDocSoporte(int docSoporte) {
		List<Egreso> egreso = daoEgreso.traerEgresoPorDocSoporte(docSoporte);
		return egreso;
	}

	@Override
	public List<Egreso> traerEgresoPorCodigo(int idEgreso) {
		List<Egreso> egreso = daoEgreso.traerEgresoPorCodigo(idEgreso);
		return egreso;
	}

	@Override
	public List<Egreso> reportarEgresosPorF(Date inicioFecha, Date finFecha) {
		List<Egreso> egreso = daoEgreso.reportarEgresosPorF(inicioFecha, finFecha);
		return egreso;
	}

	@Override
	public List<Egreso> reportarEgresosPorFC(Date inicioFecha, Date finFecha,String concepto) {
		List<Egreso> egreso = daoEgreso.reportarEgresosPorFC(inicioFecha, finFecha, concepto);
		return egreso;
	}

	@Override
	public List<Egreso> reportarEgresosPorFCP(Date inicioFecha, Date finFecha,String identificacionProv, String concepto) {
		List<Egreso> egreso = daoEgreso.reportarEgresosPorFCP(inicioFecha, finFecha, identificacionProv, concepto);
		return egreso;
	}

	@Override
	public List<Egreso> reportarEgresosPorFP(Date inicioFecha, Date finFecha,String identificacionProv) {
		List<Egreso> egreso = daoEgreso.reportarEgresosPorFP(inicioFecha, finFecha, identificacionProv);
		return egreso;
	}
}
