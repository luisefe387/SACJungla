package co.com.jungla.sac.persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.CuentaXPagar;

/**
 * clase dao con los metodos que se necesitan para gestionar las cuentas por pagar
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoCuentaXPagar extends DaoGeneral<CuentaXPagar,String>{

    /**
     * Metodo constructor sin parametros. 
     */
    public DaoCuentaXPagar() {
  
    }

    /* 
   	 * Metodo que permite retornar la clase Banco
   	 */
	@Override
	protected Class<CuentaXPagar> getEntityClass() {
		return CuentaXPagar.class;
	}
	
	public List <CuentaXPagar> traerCodigoCuentaXPagar() {
		TypedQuery<CuentaXPagar> consulta = entityManager.createQuery(
				"SELECT c FROM CuentaXPagar c order by c.idCuentaXPagar desc", CuentaXPagar.class);
		return (List<CuentaXPagar>) consulta.getResultList();
	}
	
	public List <CuentaXPagar> traerCuentaXPagarPorCodigo(int idCuentaXPagar) {
		TypedQuery<CuentaXPagar> consulta = entityManager.createQuery(
				"SELECT cp FROM CuentaXPagar cp WHERE cp.idCuentaXPagar =:idCuentaXPagar", CuentaXPagar.class);
		consulta.setParameter("idCuentaXPagar", idCuentaXPagar);
		return (List<CuentaXPagar>) consulta.getResultList();
	}
	
	public List <CuentaXPagar> traerCxPPorDocSoporte(int docSoporte) {
		TypedQuery<CuentaXPagar> consulta = entityManager.createQuery(
				"SELECT c FROM CuentaXPagar c WHERE c.docSoporte =:docSoporte", CuentaXPagar.class);
		consulta.setParameter("docSoporte", docSoporte);
		return (List<CuentaXPagar>) consulta.getResultList();
	}
	
	public List <CuentaXPagar> traerCxPPorDocSoporte_1(String docSoporte) {
		TypedQuery<CuentaXPagar> consulta = entityManager.createQuery(
				"SELECT c FROM CuentaXPagar c WHERE c.docSoporte LIKE :docSoporte", CuentaXPagar.class);
		consulta.setParameter("docSoporte", "%" + docSoporte + "%");
		return (List<CuentaXPagar>) consulta.getResultList();
	}
	
	public List <CuentaXPagar> traerCxPPorProveedorydocSoporte(String nombre, int docSoporte) {
		TypedQuery<CuentaXPagar> consulta = entityManager.createQuery(
				"SELECT c FROM CuentaXPagar c JOIN c.proveedores p WHERE p.nombre LIKE :nombre AND c.docSoporte LIKE :docSoporte", CuentaXPagar.class);
		consulta.setParameter("nombre", "%" + nombre + "%");
		consulta.setParameter("docSoporte", "%" + docSoporte + "%");
		return (List<CuentaXPagar>) consulta.getResultList();
	}
	
	public List <CuentaXPagar> traerCxPPorProveedor(String nombre) {
		TypedQuery<CuentaXPagar> consulta = entityManager.createQuery(
				"SELECT c FROM CuentaXPagar c JOIN c.proveedores p WHERE p.nombre LIKE :nombre", CuentaXPagar.class);
		consulta.setParameter("nombre", "%" + nombre + "%");
		return (List<CuentaXPagar>) consulta.getResultList();
	}
	
	/**
	 * Metodo para encontrar las cuentas por pagar a punto de vencerse de acuerdo a los dias
	 * @param inicioFechaE, parametro que define la fecha inicial
	 * @param finFechaE, parametro que define la fecha final
	 * @param estado, parametro que define el estado de pago de la cuenta por pagar
	 * @return
	 */
	public List <CuentaXPagar> traerCxPAVencerse(Date inicioFechaE, Date finFechaE, String estado) {
		TypedQuery<CuentaXPagar> consulta = entityManager.createQuery(
				"SELECT cp FROM CuentaXPagar cp WHERE cp.estado =:estado AND cp.fechaPago BETWEEN :inicioFechaE AND :finFechaE",CuentaXPagar.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("estado", estado);
		return (List<CuentaXPagar>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todos los registros de las cuentas por pagar mediante un determinado rangode fechas
	 * @param inicioFecha, parametro que define la fecha inicial
	 * @param finFecha, parametro que define la feha final
	 * @return devuelve una lista de cuentas por pagar basado en el rango de fecha
	 */
	public List <CuentaXPagar> reportarCuentaXPagarPorF(Date inicioFecha, Date finFecha) {
		TypedQuery<CuentaXPagar> consulta = entityManager.createQuery(
				"SELECT cp FROM CuentaXPagar cp WHERE cp.fechaPago BETWEEN :inicioFecha AND :finFecha", CuentaXPagar.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		return (List<CuentaXPagar>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todos los registros de las cuentas por pagar mediante un determinado rango de fechas y un concepto
	 * @param inicioFecha, parametro que define la fecha inicial
	 * @param finFecha, parametro que define la feha final
	 * @param concepto, parametro que define el concepto de la cuenta por pagar
	 * @return devuelve una lista de cuentas por pagar basado en los paramentros establecidos
	 */
	public List <CuentaXPagar> reportarCuentaXPagarPorFC(Date inicioFecha, Date finFecha, String concepto ) {
		TypedQuery<CuentaXPagar> consulta = entityManager.createQuery(
				"SELECT cp FROM CuentaXPagar cp WHERE cp.concepto =:concepto AND cp.fechaPago BETWEEN :inicioFecha AND :finFecha", CuentaXPagar.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("concepto", concepto);
		return (List<CuentaXPagar>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todos los registros de las cuentas por pagar mediante un determinado rango de fechas, un proveedor y un concepto
	 * @param inicioFecha, parametro que define la fecha inicial
	 * @param finFecha, parametro que define la feha final
	 * @param concepto, parametro que define el concepto de la cuenta por pagar
	 * @param identificacionProv, parametro que define la identificacion del proveedor
	 * @return devuelve una lista de cuentas por pagar basado en los paramentros establecidos
	 */
	public List <CuentaXPagar> reportarCuentaXPagarPorFCP(Date inicioFecha, Date finFecha, String identificacionProv, String concepto ) {
		TypedQuery<CuentaXPagar> consulta = entityManager.createQuery(
				"SELECT cp FROM CuentaXPagar cp WHERE cp.identificacionProv =:identificacionProv AND cp.concepto =:concepto AND cp.fechaPago BETWEEN :inicioFecha AND :finFecha", CuentaXPagar.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("concepto", concepto);
		consulta.setParameter("identificacionProv", identificacionProv);
		return (List<CuentaXPagar>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todos los registros de las cuentas por pagar mediante un determinado rango de fechas y un proveedor
	 * @param inicioFecha, parametro que define la fecha inicial
	 * @param finFecha, parametro que define la feha final
	 * @param identificacionProv, parametro que define el la identificacion del proveedor
	 * @return devuelve una lista de devoluciones basado en los paramentros establecidos
	 */
	public List <CuentaXPagar> reportarCuentaXPagarPorFP(Date inicioFecha, Date finFecha, String identificacionProv ) {
		TypedQuery<CuentaXPagar> consulta = entityManager.createQuery(
				"SELECT cp FROM CuentaXPagar cp WHERE cp.identificacionProv =:identificacionProv AND cp.fechaPago BETWEEN :inicioFecha AND :finFecha", CuentaXPagar.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("identificacionProv", identificacionProv);
		return (List<CuentaXPagar>) consulta.getResultList();
	}
	
	/**
	 * Metodo para encontrar las cuentas por pagar vencidas
	 * @param fechaActual, parametro que define la fecha actual
	 * @return devuelve una lista de cuentas por pagar vencidas hasta la fecha
	 */
	public List <CuentaXPagar> reportarTodasCxPVencidas(Date fechaActual) {
		String estado = "Pendiente";
		TypedQuery<CuentaXPagar> consulta = entityManager.createQuery(
				"SELECT cp FROM CuentaXPagar cp WHERE cp.estado =:estado AND datediff(:fechaActual, cp.fechaPago) < 0",CuentaXPagar.class);
		consulta.setParameter("fechaActual", fechaActual,TemporalType.DATE);
		consulta.setParameter("estado", estado);
		return (List<CuentaXPagar>) consulta.getResultList();
	}
}
