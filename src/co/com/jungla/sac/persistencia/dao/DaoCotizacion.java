package co.com.jungla.sac.persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.Cotizacion;

/**
 * clase dao con todos los metodos que realizan las consultas corresponidente a la cotizacion  
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoCotizacion extends DaoGeneral<Cotizacion,String> {

	/**
     * Metodo constructor sin parametros
     */
    public DaoCotizacion() {
     
    }

	/* 
	 * Metodo que permite retornar la clase Cotizacion
	 */
	@Override
	protected Class<Cotizacion> getEntityClass() {
		return Cotizacion.class;
	}
	
	/**
	 * Metodo para traer mediante una consulta la informacion de la ultima venta de articulo realizada
	 * @return una lista de objetos tipo VentaArticulo
	 */
	public List<Cotizacion> traerUltimaCotizacion() {
		TypedQuery<Cotizacion> consulta = entityManager.createQuery(
				"SELECT c FROM Cotizacion c order by c.idCotizacion desc", Cotizacion.class);
		if (consulta.getResultList() == null || consulta.getResultList().isEmpty()) {
	        return null;
	    }else{
	    	return (List<Cotizacion>) consulta.getResultList();
	    }
	}
	
	/**
	 * Metodo para traer la informacion de la cotizacion mediante su codigo
	 * @param idCotizacion, parametro que define el consecutivo o codigo de la cotizacion
	 * @return una lista de tipo Cotizacion con la informacion de la cotizacion elegida
	 */
	public List<Cotizacion> traerCotizacionPorCodigo(int idCotizacion) {
		TypedQuery<Cotizacion> consulta = entityManager.createQuery(
				"SELECT c FROM Cotizacion c WHERE c.idCotizacion = :idCotizacion", Cotizacion.class);
		consulta.setParameter("idCotizacion", idCotizacion);
		return (List<Cotizacion>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todas las cotizaciones activas de cada cliente aprovechando como filtro de busqueda el estado de uso de la cotizacion y la identificacion del cliente
	 * @param identificacionCliente, parametro de tipo string en la que define el numero de identificacion del cliente
	 * @return una lista de objetos de tipo Cotizacion representando las cotizaciones por cliente
	 */
	public List <Cotizacion> traerCotizacionesPorIdentCliente(String identificacionCliente) {
		TypedQuery<Cotizacion> consulta = entityManager.createQuery(
				"SELECT c FROM Cotizacion c  WHERE c.identificacionCliente LIKE :identificacionCliente", Cotizacion.class);
		consulta.setParameter("identificacionCliente", "%" + identificacionCliente + "%");
		return (List<Cotizacion>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todas las cotizaciones activas de cada cliente aprovechando como filtro de busqueda el estado de uso de la cotizacion y la identificacion del cliente
	 * @param nombreCliente, parametro de tipo string en la que define el nombre del cliente
	 * @return una lista de objetos de tipo Cotizacion representando las cotizaciones por cliente
	 */
	public List <Cotizacion> traerCotizacionesPorNombreCliente(String nombreCliente) {
		TypedQuery<Cotizacion> consulta = entityManager.createQuery(
				"SELECT c FROM Cotizacion c  WHERE c.nombreCliente LIKE :nombreCliente", Cotizacion.class);
		consulta.setParameter("nombreCliente", "%" + nombreCliente + "%");
		return (List<Cotizacion>) consulta.getResultList();
	}
}
