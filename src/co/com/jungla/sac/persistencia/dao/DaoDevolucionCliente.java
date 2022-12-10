package co.com.jungla.sac.persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.DevolucionCliente;

/**
 * clase dao con los metodos que se necesitan para gestionar las devoluciones del cliente
 * @author Luis Fernando Pedroza T.
 * @version: 10/09/2016
 */
@Stateless
@LocalBean
public class DaoDevolucionCliente extends DaoGeneral<DevolucionCliente,String>{

	/**
     * Metodo constructor sin parametros 
     */
    public DaoDevolucionCliente() {
       
    }

    /* 
   	 * Metodo que permite retornar la clase DevolucionCliente
   	 */
	@Override
	protected Class<DevolucionCliente> getEntityClass() {
		return DevolucionCliente.class;
	}
	
	/**
	 * Metodo para traer la ultima devolucion del cliente
	 * @return una lista de objetos de tipo DevolucionCliente con la informacion de la ultima devolucion
	 */
	public List<DevolucionCliente> traerultimaDevolucionCliente() {
		TypedQuery<DevolucionCliente> consulta = entityManager.createQuery(
				"SELECT d FROM DevolucionCliente d order by d.idDevolucionCliente desc",
				DevolucionCliente.class);
		return (List<DevolucionCliente>) consulta.getResultList();
	}
	
	/**
	 * Metodo para listar las devoluciones del cliente mediante el numero de identificacion del cliente
	 * @param identificacion, parametro que define el numero de identificacion del cliente
	 * @return una lista con la informacion de las devoluciones del cliente seleccionado
	 */
	public List <DevolucionCliente> listarDevolucionClientePorIdentificacion(String identificacion) {
		TypedQuery<DevolucionCliente> consulta = entityManager.createQuery(
				"SELECT dc FROM DevolucionCliente dc JOIN dc.ventaArticulos va JOIN va.clientes c WHERE c.identificacion =:identificacion", DevolucionCliente.class);
		consulta.setParameter("identificacion", identificacion);
		return (List<DevolucionCliente>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer las devoluciones del cliente por codigo de la devolucion
	 * @param idDevolucionCliente, parametro que define el codigo de la devolucion cliente
	 * @return una lista de objetos de tipo DevolucionCliente con la informacion de la devolucion elegida
	 */
	public List <DevolucionCliente> traerDevolucionClientePorCodigo(int idDevolucionCliente) {
		TypedQuery<DevolucionCliente> consulta = entityManager.createQuery(
				"SELECT dc FROM DevolucionCliente dc WHERE dc.idDevolucionCliente =:idDevolucionCliente", DevolucionCliente.class);
		consulta.setParameter("idDevolucionCliente", idDevolucionCliente);
		return (List<DevolucionCliente>) consulta.getResultList();
	}
	
	
	/**
	 * Metodo para traer todos los registros de las devoluciones mediante un determinado rango e fechas
	 * @param inicioFecha, parametro que define la fecha inicial
	 * @param finFecha, parametro que define la feha final
	 * @return devuelve una lista de devoluciones basado en el rango de fecha
	 */
	public List <DevolucionCliente> reportarDevolucionesPorF(Date inicioFecha, Date finFecha) {
		TypedQuery<DevolucionCliente> consulta = entityManager.createQuery(
				"SELECT dc FROM DevolucionCliente dc WHERE dc.fecha BETWEEN :inicioFecha AND :finFecha", DevolucionCliente.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		return (List<DevolucionCliente>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todos los registros de las devoluciones mediante un determinado rango de fechas y un cliente
	 * @param inicioFecha, parametro que define la fecha inicial
	 * @param finFecha, parametro que define la feha final
	 * @param identificacion, parametro que define el numero de identificacion del cliente
	 * @return devuelve una lista de devoluciones basado en los paramentros establecidos
	 */
	public List <DevolucionCliente> reportarDevolucionesPorFC(Date inicioFecha, Date finFecha, String identificacion ) {
		TypedQuery<DevolucionCliente> consulta = entityManager.createQuery(
				"SELECT dc FROM DevolucionCliente dc JOIN dc.ventaArticulos va JOIN va.clientes c WHERE c.identificacion =:identificacion AND dc.fecha BETWEEN :inicioFecha AND :finFecha", DevolucionCliente.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("identificacion", identificacion);
		return (List<DevolucionCliente>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todos los registros de las devoluciones mediante un determinado rango de fechas, un cliente y un estado
	 * @param inicioFecha, parametro que define la fecha inicial
	 * @param finFecha, parametro que define la feha final
	 * @param identificacion, parametro que define el numero de identificacion del cliente
	 * @param estado, parametro que define el estado de la devolucion entre pendiente con compensada
	 * @return devuelve una lista de devoluciones basado en los paramentros establecidos
	 */
	public List <DevolucionCliente> reportarDevolucionesPorFCE(Date inicioFecha, Date finFecha, String identificacion, String estado ) {
		TypedQuery<DevolucionCliente> consulta = entityManager.createQuery(
				"SELECT dc FROM DevolucionCliente dc JOIN dc.ventaArticulos va JOIN va.clientes c WHERE c.identificacion =:identificacion AND dc.estado =:estado AND dc.fecha BETWEEN :inicioFecha AND :finFecha", DevolucionCliente.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("identificacion", identificacion);
		consulta.setParameter("estado", estado);
		return (List<DevolucionCliente>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todos los registros de las devoluciones mediante un determinado rango de fechas y un estado
	 * @param inicioFecha, parametro que define la fecha inicial
	 * @param finFecha, parametro que define la feha final
	 * @param estado, parametro que define el estado de la devolucion entre pendiente con compensada
	 * @return devuelve una lista de devoluciones basado en los paramentros establecidos
	 */
	public List <DevolucionCliente> reportarDevolucionesPorFE(Date inicioFecha, Date finFecha, String estado ) {
		TypedQuery<DevolucionCliente> consulta = entityManager.createQuery(
				"SELECT dc FROM DevolucionCliente dc JOIN dc.ventaArticulos va JOIN va.clientes c WHERE dc.estado =:estado AND dc.fecha BETWEEN :inicioFecha AND :finFecha", DevolucionCliente.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("estado", estado);
		return (List<DevolucionCliente>) consulta.getResultList();
	}
}
