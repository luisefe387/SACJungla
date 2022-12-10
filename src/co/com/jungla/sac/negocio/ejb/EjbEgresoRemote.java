package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.Egreso;


@Remote
public interface EjbEgresoRemote {

	public void insertarEgreso(Egreso egreso);
	
	public void actualizarEgreso(Egreso egreso);
	
	public void eliminarEgreso(Egreso egreso);
	
	public List<Egreso> traerCodigoEgreso();
	
	public List <Egreso> traerEgresoPorDocSoporte(int docSoporte);
	
	public List <Egreso> traerEgresoPorCodigo(int idEgreso);
	
	public List <Egreso> reportarEgresosPorF(Date inicioFecha, Date finFecha);
	
	public List <Egreso> reportarEgresosPorFC(Date inicioFecha, Date finFecha, String concepto );
	
	public List <Egreso> reportarEgresosPorFCP(Date inicioFecha, Date finFecha, String identificacionProv, String concepto );
	
	public List <Egreso> reportarEgresosPorFP(Date inicioFecha, Date finFecha, String identificacionProv );
	
}
