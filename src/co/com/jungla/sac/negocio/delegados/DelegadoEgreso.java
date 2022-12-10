package co.com.jungla.sac.negocio.delegados;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbEgresoRemote;
import co.com.jungla.sac.persistencia.entidades.Egreso;


public class DelegadoEgreso implements EjbEgresoRemote{
	
	EjbEgresoRemote ejbEgresoRemote;

	public void insertarEgreso(Egreso egreso) {
		ejbEgresoRemote.insertarEgreso(egreso);
	}

	public void actualizarEgreso(Egreso egreso) {
		ejbEgresoRemote.actualizarEgreso(egreso);
	}

	public void eliminarEgreso(Egreso egreso) {
		ejbEgresoRemote.eliminarEgreso(egreso);
	}

	public List<Egreso> traerCodigoEgreso() {
		return ejbEgresoRemote.traerCodigoEgreso();
	}

	public List<Egreso> traerEgresoPorDocSoporte(int docSoporte) {
		return ejbEgresoRemote.traerEgresoPorDocSoporte(docSoporte);
	}

	public List<Egreso> traerEgresoPorCodigo(int idEgreso) {
		return ejbEgresoRemote.traerEgresoPorCodigo(idEgreso);
	}
	
	public List<Egreso> reportarEgresosPorF(Date inicioFecha, Date finFecha) {
		return ejbEgresoRemote.reportarEgresosPorF(inicioFecha, finFecha);
	}

	public List<Egreso> reportarEgresosPorFC(Date inicioFecha, Date finFecha,String concepto) {
		return ejbEgresoRemote.reportarEgresosPorFC(inicioFecha, finFecha,concepto);
	}

	public List<Egreso> reportarEgresosPorFCP(Date inicioFecha, Date finFecha,String identificacionProv, String concepto) {
		return ejbEgresoRemote.reportarEgresosPorFCP(inicioFecha, finFecha,identificacionProv, concepto);
	}

	public List<Egreso> reportarEgresosPorFP(Date inicioFecha, Date finFecha,String identificacionProv) {
		return ejbEgresoRemote.reportarEgresosPorFP(inicioFecha, finFecha,identificacionProv);
	}

	public DelegadoEgreso() {
		try {
			ejbEgresoRemote = (EjbEgresoRemote) new InitialContext().lookup("java:global/SACJungla/EjbEgreso!co.com.jungla.sac.negocio.ejb.EjbEgresoRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
