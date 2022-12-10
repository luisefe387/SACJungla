package co.com.jungla.sac.persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.ReciboCaja;

/**
 * clase dao con los metodos que se necesitan para gestionar los recibos de caja
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoReciboCaja extends DaoGeneral<ReciboCaja,String>{

	String estadoActividad= "Activo";
	/**
     * Metodo constructor sin parametros 
     */
    public DaoReciboCaja() {

    }
    
    /* 
   	 * Metodo que permite retornar la clase ReciboCaja
   	 */
	@Override
	protected Class<ReciboCaja> getEntityClass() {
		return ReciboCaja.class;
	}
	
	/**
	 * Metodo para traer el ultimo recibo de caja generado en la base de datos
	 * @return en una lista de tipo ReciboCaja el ultimo recibo de caja
	 */
	public List <ReciboCaja> traerUltimoReciboCaja() {
		TypedQuery<ReciboCaja> consulta = entityManager.createQuery(
				"SELECT rc FROM ReciboCaja rc order by rc.idReciboCaja desc", ReciboCaja.class);
		return (List<ReciboCaja>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer el recibo de caja por su codigo
	 * @param idReciboCaja, paramentro que define el codigo del recibo de caja
	 * @return devuelve el recibo de caja encontrado por el codigo ingresado
	 */
	public List <ReciboCaja> traerRecibosCajaPorIdRecibo(int idReciboCaja) {
		TypedQuery<ReciboCaja> consulta = entityManager.createQuery(
				"SELECT rc FROM ReciboCaja rc JOIN rc.ventaArticulos va WHERE rc.idReciboCaja =:idReciboCaja AND rc.estadoActividad =:estadoActividad", ReciboCaja.class);
		consulta.setParameter("idReciboCaja", idReciboCaja);
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<ReciboCaja>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todos los recibos de caja asociados a la venta de articulos
	 * @param idVenta, parametro que define el numero de la venta
	 * @return una lista de recibos encontrados por medio de la venta
	 */
	public List <ReciboCaja> traerRecibosCajaPorVenta(int idVenta) {
		TypedQuery<ReciboCaja> consulta = entityManager.createQuery(
				"SELECT rc FROM ReciboCaja rc JOIN rc.ventaArticulos va WHERE rc.estadoActividad =:estadoActividad AND va.idVenta =:idVenta", ReciboCaja.class);
		consulta.setParameter("idVenta", idVenta);
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<ReciboCaja>) consulta.getResultList();
	}
	
	public List <ReciboCaja> reportarRecibosPorF(Date inicioFechaE, Date finFechaE) {
		TypedQuery<ReciboCaja> consulta = entityManager.createQuery(
				"SELECT rc FROM ReciboCaja rc WHERE rc.estadoActividad =:estadoActividad AND rc.fechaRecaudo BETWEEN :inicioFechaE AND :finFechaE", ReciboCaja.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<ReciboCaja>) consulta.getResultList();
	}
	
	public List <ReciboCaja> reportarRecibosPorFV(String identificacion, Date inicioFechaE, Date finFechaE) {
		TypedQuery<ReciboCaja> consulta = entityManager.createQuery(
				"SELECT rc FROM ReciboCaja rc JOIN rc.ventaArticulos va JOIN va.vendedores v WHERE rc.estadoActividad =:estadoActividad AND v.identificacion =:identificacion AND rc.fechaRecaudo BETWEEN :inicioFechaE AND :finFechaE", ReciboCaja.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("identificacion", identificacion);
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<ReciboCaja>) consulta.getResultList();
	}
	
	
}
