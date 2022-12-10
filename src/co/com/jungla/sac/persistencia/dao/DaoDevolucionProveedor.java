package co.com.jungla.sac.persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.DevolucionProveedor;
import co.com.jungla.sac.persistencia.entidades.DevolucionProveedor;

/**
 * clase dao con los metodos que se necesitan para gestionar las devoluciones del proveedor
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoDevolucionProveedor extends DaoGeneral<DevolucionProveedor,String>{

    /**
     * Metodo constructor sin parametros. 
     */
    public DaoDevolucionProveedor() {
        
    }

    /* 
   	 * Metodo que permite retornar la clase DevolucionProveedor
   	 */
	@Override
	protected Class<DevolucionProveedor> getEntityClass() {
		return DevolucionProveedor.class;
	}
	
	/**
	 * Metodo para traer la ultima devolucion del proveedor
	 * @return una lista de objetos de tipo DevolucionProveedor con la informacion de la ultima devolucion
	 */
	public List<DevolucionProveedor> traerultimaDevolucionProveedor() {
		TypedQuery<DevolucionProveedor> consulta = entityManager.createQuery(
				"SELECT d FROM DevolucionProveedor d order by d.idDevolucionProveedor desc",
				DevolucionProveedor.class);
		return (List<DevolucionProveedor>) consulta.getResultList();
	}
	
	/**
	 * Metodo para listar las devoluciones del proveedor mediante el numero de identificacion del proveedor
	 * @param identificacion, parametro que define el numero de identificacion del proveedor
	 * @return una lista con la informacion de las devoluciones del proveedor seleccionado
	 */
	public List <DevolucionProveedor> listarDevolucionProveedorPorIdentificacion(String identificacion) {
		TypedQuery<DevolucionProveedor> consulta = entityManager.createQuery(
				"SELECT dp FROM DevolucionProveedor dp JOIN dp.compraArticulos ca JOIN ca.proveedores p WHERE p.identificacion =:identificacion", DevolucionProveedor.class);
		consulta.setParameter("identificacion", identificacion);
		return (List<DevolucionProveedor>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer las devoluciones del proveedor por codigo de la devolucion
	 * @param idDevolucionProveedor, parametro que define el codigo de la devolucion proveedor
	 * @return una lista de objetos de tipo DevolucionProveedor con la informacion de la devolucion elegida
	 */
	public List <DevolucionProveedor> traerDevolucionProveedorPorCodigo(int idDevolucionProveedor) {
		TypedQuery<DevolucionProveedor> consulta = entityManager.createQuery(
				"SELECT dp FROM DevolucionProveedor dp WHERE dp.idDevolucionProveedor =:idDevolucionProveedor", DevolucionProveedor.class);
		consulta.setParameter("idDevolucionProveedor", idDevolucionProveedor);
		return (List<DevolucionProveedor>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todos los registros de las devoluciones mediante un determinado rango e fechas
	 * @param inicioFecha, parametro que define la fecha inicial
	 * @param finFecha, parametro que define la feha final
	 * @return devuelve una lista de devoluciones basado en el rango de fecha
	 */
	public List <DevolucionProveedor> reportarDevolucionesPorF(Date inicioFecha, Date finFecha) {
		TypedQuery<DevolucionProveedor> consulta = entityManager.createQuery(
				"SELECT dp FROM DevolucionProveedor dp WHERE dp.fecha BETWEEN :inicioFecha AND :finFecha", DevolucionProveedor.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		return (List<DevolucionProveedor>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todos los registros de las devoluciones mediante un determinado rango de fechas y un cliente
	 * @param inicioFecha, parametro que define la fecha inicial
	 * @param finFecha, parametro que define la feha final
	 * @param identificacion, parametro que define el numero de identificacion del cliente
	 * @return devuelve una lista de devoluciones basado en los paramentros establecidos
	 */
	public List <DevolucionProveedor> reportarDevolucionesPorFP(Date inicioFecha, Date finFecha, String identificacion ) {
		TypedQuery<DevolucionProveedor> consulta = entityManager.createQuery(
				"SELECT dp FROM DevolucionProveedor dp JOIN dp.compraArticulos ca JOIN ca.proveedores p WHERE p.identificacion =:identificacion AND dp.fecha BETWEEN :inicioFecha AND :finFecha", DevolucionProveedor.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("identificacion", identificacion);
		return (List<DevolucionProveedor>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todos los registros de las devoluciones mediante un determinado rango de fechas, un cliente y un estado
	 * @param inicioFecha, parametro que define la fecha inicial
	 * @param finFecha, parametro que define la feha final
	 * @param identificacion, parametro que define el numero de identificacion del cliente
	 * @param estado, parametro que define el estado de la devolucion entre pendiente con compensada
	 * @return devuelve una lista de devoluciones basado en los paramentros establecidos
	 */
	public List <DevolucionProveedor> reportarDevolucionesPorFPE(Date inicioFecha, Date finFecha, String identificacion, String estado ) {
		TypedQuery<DevolucionProveedor> consulta = entityManager.createQuery(
				"SELECT dp FROM DevolucionProveedor dp JOIN dp.compraArticulos ca JOIN ca.proveedores p WHERE p.identificacion =:identificacion AND dp.estado =:estado AND dp.fecha BETWEEN :inicioFecha AND :finFecha", DevolucionProveedor.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("identificacion", identificacion);
		consulta.setParameter("estado", estado);
		return (List<DevolucionProveedor>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todos los registros de las devoluciones mediante un determinado rango de fechas y un estado
	 * @param inicioFecha, parametro que define la fecha inicial
	 * @param finFecha, parametro que define la feha final
	 * @param estado, parametro que define el estado de la devolucion entre pendiente con compensada
	 * @return devuelve una lista de devoluciones basado en los paramentros establecidos
	 */
	public List <DevolucionProveedor> reportarDevolucionesPorFE(Date inicioFecha, Date finFecha, String estado ) {
		TypedQuery<DevolucionProveedor> consulta = entityManager.createQuery(
				"SELECT dp FROM DevolucionProveedor dp JOIN dp.compraArticulos ca JOIN ca.proveedores p WHERE dp.estado =:estado AND dp.fecha BETWEEN :inicioFecha AND :finFecha", DevolucionProveedor.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("estado", estado);
		return (List<DevolucionProveedor>) consulta.getResultList();
	}
}
