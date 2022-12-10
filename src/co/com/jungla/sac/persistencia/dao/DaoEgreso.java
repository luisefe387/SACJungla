package co.com.jungla.sac.persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.Egreso;

/**
 * clase dao con los metodos que se necesitan para gestionar los egresos
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoEgreso extends DaoGeneral<Egreso,String>{

    /**
     * Metodo constructor sin parametros. 
     */
    public DaoEgreso() {
       
    }
    
    /* 
   	 * Metodo que permite retornar la clase Egreso
   	 */
	@Override
	protected Class<Egreso> getEntityClass() {
		return Egreso.class;
	}
	
	/**
	 * Metodo para traer el ultimo egreso registrado
	 * @return una lista con el la informacion del ultimo egreso
	 */
	public List <Egreso> traerCodigoEgreso() {
		TypedQuery<Egreso> consulta = entityManager.createQuery(
				"SELECT e FROM Egreso e order by e.idEgreso desc", Egreso.class);
		return (List<Egreso>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer la informacion del egreso mediante el numero de documento soporte
	 * @param docSoporte, parametro de tipo numerico que define el numero de documentos soporte
	 * @return una lista con la informacion del egreso encontrado
	 */
	public List <Egreso> traerEgresoPorDocSoporte(int docSoporte) {
		TypedQuery<Egreso> consulta = entityManager.createQuery(
				"SELECT e FROM Egreso e WHERE e.docSoporte =:docSoporte", Egreso.class);
		consulta.setParameter("docSoporte", docSoporte);
		return (List<Egreso>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer la informacion del egreso mediante el codigo
	 * @param idEgreso, parametro que define el codigo del egreso
	 * @return devuelve una lista de egresos
	 */
	public List <Egreso> traerEgresoPorCodigo(int idEgreso) {
		TypedQuery<Egreso> consulta = entityManager.createQuery(
				"SELECT e FROM Egreso e WHERE e.idEgreso =:idEgreso", Egreso.class);
		consulta.setParameter("idEgreso", idEgreso);
		return (List<Egreso>) consulta.getResultList();
	}
	
	
	/**
	 * Metodo para traer todos los registros de los egresos mediante un determinado rango de fechas
	 * @param inicioFecha, parametro que define la fecha inicial
	 * @param finFecha, parametro que define la feha final
	 * @return devuelve una lista de egresos basado en el rango de fecha
	 */
	public List <Egreso> reportarEgresosPorF(Date inicioFecha, Date finFecha) {
		TypedQuery<Egreso> consulta = entityManager.createQuery(
				"SELECT e FROM Egreso e WHERE e.fechaPago BETWEEN :inicioFecha AND :finFecha", Egreso.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		return (List<Egreso>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todos los registros de los egresos mediante un determinado rango de fechas y un concepto
	 * @param inicioFecha, parametro que define la fecha inicial
	 * @param finFecha, parametro que define la feha final
	 * @param concepto, parametro que define el concepto de la cuenta por pagar
	 * @return devuelve una lista de egresos basado en los paramentros establecidos
	 */
	public List <Egreso> reportarEgresosPorFC(Date inicioFecha, Date finFecha, String concepto ) {
		TypedQuery<Egreso> consulta = entityManager.createQuery(
				"SELECT e FROM Egreso e WHERE e.concepto LIKE :concepto AND e.fechaPago BETWEEN :inicioFecha AND :finFecha", Egreso.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("concepto", "%" + concepto + "%");
		return (List<Egreso>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todos los registros de los egresos mediante un determinado rango de fechas, un proveedor y un concepto
	 * @param inicioFecha, parametro que define la fecha inicial
	 * @param finFecha, parametro que define la feha final
	 * @param concepto, parametro que define el concepto de la cuenta por pagar
	 * @param identificacionProv, parametro que define la identificacion del proveedor
	 * @return devuelve una lista de egresos basado en los paramentros establecidos
	 */
	public List <Egreso> reportarEgresosPorFCP(Date inicioFecha, Date finFecha, String identificacionProv, String concepto ) {
		TypedQuery<Egreso> consulta = entityManager.createQuery(
				"SELECT e FROM Egreso e WHERE e.identificacionProv =:identificacionProv AND e.concepto LIKE :concepto AND e.fechaPago BETWEEN :inicioFecha AND :finFecha", Egreso.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("concepto", "%" + concepto + "%");
		consulta.setParameter("identificacionProv", identificacionProv);
		return (List<Egreso>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todos los egresos por pagar mediante un determinado rango de fechas y un proveedor
	 * @param inicioFecha, parametro que define la fecha inicial
	 * @param finFecha, parametro que define la feha final
	 * @param identificacionProv, parametro que define el la identificacion del proveedor
	 * @return devuelve una lista de los egresos basado en los paramentros establecidos
	 */
	public List <Egreso> reportarEgresosPorFP(Date inicioFecha, Date finFecha, String identificacionProv ) {
		TypedQuery<Egreso> consulta = entityManager.createQuery(
				"SELECT e FROM Egreso e WHERE e.identificacionProv =:identificacionProv AND e.fechaPago BETWEEN :inicioFecha AND :finFecha", Egreso.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("identificacionProv", identificacionProv);
		return (List<Egreso>) consulta.getResultList();
	}
}
