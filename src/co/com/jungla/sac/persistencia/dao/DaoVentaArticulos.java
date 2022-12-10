package co.com.jungla.sac.persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.ReciboCaja;
import co.com.jungla.sac.persistencia.entidades.VentaArticulos;

/**
 * clase dao con todos los metodos que realizan las consultas corresponidente a la venta de articulos  
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoVentaArticulos extends DaoGeneral<VentaArticulos,String> {

	String estadoActividad= "Activo";
	/**
     * Metodo constructor sin parametros
     */
    public DaoVentaArticulos() {
     
    }

	/* 
	 * Metodo que permite retornar la clase VentaArticulos
	 */
	@Override
	protected Class<VentaArticulos> getEntityClass() {
		return VentaArticulos.class;
	}
	
	/**
	 * Metodo para traer mediante una consulta la informacion de la ultima venta de articulo realizada
	 * @return una lista de objetos tipo VentaArticulo
	 */
	public List<VentaArticulos> traerUltimaVentaArticulo() {
		TypedQuery<VentaArticulos> consulta = entityManager.createQuery(
				"SELECT va FROM VentaArticulos va order by va.idVenta desc", VentaArticulos.class);
		if (consulta.getResultList() == null || consulta.getResultList().isEmpty()) {
	        return null;
	    }else{
	    	return (List<VentaArticulos>) consulta.getResultList();
	    }
	}
	
	/**
	 * Metodo para traer la informacion de la venta de articulos mediante su codigo
	 * @param idVenta, parametro que define el consecutivo o codigo de la venta
	 * @return una lista de tipo VentaArticulos con la informacion de la venta elegida
	 */
	public List<VentaArticulos> traerVentaPorCodigo(int idVenta) {
		TypedQuery<VentaArticulos> consulta = entityManager.createQuery(
				"SELECT va FROM VentaArticulos va WHERE va.idVenta =:idVenta AND va.estadoActividad =:estadoActividad", VentaArticulos.class);
		consulta.setParameter("idVenta", idVenta);
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<VentaArticulos>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer la informacion de la venta de articulos mediante su codigo
	 * @param idVenta, parametro que define el consecutivo o codigo de la venta
	 * @return una lista de tipo VentaArticulos con la informacion de la venta elegida
	 */
	public List<VentaArticulos> traerTodasVentaPorCodigo(int idVenta) {
		TypedQuery<VentaArticulos> consulta = entityManager.createQuery(
				"SELECT va FROM VentaArticulos va WHERE va.idVenta =:idVenta", VentaArticulos.class);
		consulta.setParameter("idVenta", idVenta);
		return (List<VentaArticulos>) consulta.getResultList();
	}
	
	/**
	 * Metodo para reportar la informacion de las ventas mediante un rango de fechas (inicio y fin)
	 * @param inicioFechaE, parametro de tipo Date que define la fecha de inicio
	 * @param finFechaE, parametro de tipo Date que define la fecha de fin
	 * @return retorna una lista con todas las ventas elegidas de acuerdo a un rango de fechas
	 */
	public List <VentaArticulos> reportarVentasPorF(Date inicioFechaE, Date finFechaE) {
		TypedQuery<VentaArticulos> consulta = entityManager.createQuery(
				"SELECT va FROM VentaArticulos va WHERE va.estadoActividad =:estadoActividad AND va.fechaGeneracion BETWEEN :inicioFechaE AND :finFechaE",VentaArticulos.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<VentaArticulos>) consulta.getResultList();
	}
	
	/**
	 * Metodo que con una consulta, trae la informacion de todas las ventas mediante un rango de fechas y una descripcion de la 
	 * forma de pago realizada por el cliente
	 * @param inicioFechaE, parametro de tipo Date que define la fecha de inicio
	 * @param finFechaE, parametro de tipo Date que define la fecha de fin
	 * @param descripcion, parametro de tipo string que define la descripcion de la forma de pago utilizada por el cliente
	 * @return una lista de objetos de tipo VentaArticulos con la informacion de las ventas encontradas
	 */
	public List <VentaArticulos> reportarVentasPorFF(Date inicioFechaE, Date finFechaE, String descripcion) {
		TypedQuery<VentaArticulos> consulta = entityManager.createQuery(
				"SELECT va FROM VentaArticulos va JOIN va.formaPagoCliente fc WHERE va.estadoActividad =:estadoActividad AND fc.descripcion =:descripcion AND va.fechaGeneracion BETWEEN :inicioFechaE AND :finFechaE",VentaArticulos.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("descripcion", "%" + descripcion + "%");
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<VentaArticulos>) consulta.getResultList();
	}
	
	/**
	 * Metodo que permite la realizacion de una consulta en la que se busca todas las ventas mediante un rango de fechas y el 
	 * nombre del cliente
	 * @param inicioFechaE, parametro de tipo Date que define la fecha de inicio 
	 * @param finFechaE, parametro de tipo Date que define la fecha de fin
	 * @param nombre, parametro de tipo string que define el nombre del cliente
	 * @return una lista de todas las ventas encontradas de acuerdo a los parametros establecidos
	 */
	public List <VentaArticulos> reportarVentasPorCF(Date inicioFechaE, Date finFechaE, String nombre) {
		TypedQuery<VentaArticulos> consulta = entityManager.createQuery(
				"SELECT va FROM VentaArticulos va JOIN va.clientes c WHERE va.estadoActividad =:estadoActividad AND c.nombre =:nombre AND va.fechaGeneracion BETWEEN :inicioFechaE AND :finFechaE",VentaArticulos.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("nombre", nombre);
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<VentaArticulos>) consulta.getResultList();
	}
	
	/**
	 * Metodo para encontrar todas las ventas por un rango de fechas, el nombre del cliente y la descricpcion de la forma de pago 
	 * utilizada por el cliente
	 * @param inicioFechaE, parametro de tipo Date que define la fecha de inicio 
	 * @param finFechaE, parametro de tipo Date que define la fecha de fin
	 * @param nombre, parametro de tipo string que define el nombre del cliente
	 * @param descripcion, parametro de tipo string que define la descripcion de la forma de pago utilizada por el cliente
	 * @return una lista de objetos de tipo VentaArticulos con la informacion de las ventas encontradas
	 */
	public List <VentaArticulos> reportarVentasPorCFF(Date inicioFechaE, Date finFechaE, String nombre, String descripcion) {
		TypedQuery<VentaArticulos> consulta = entityManager.createQuery(
				"SELECT va FROM VentaArticulos va JOIN va.clientes c JOIN va.formaPagoCliente fc WHERE va.estadoActividad =:estadoActividad AND c.nombre =:nombre AND fc.descripcion =:descripcion AND va.fechaGeneracion BETWEEN :inicioFechaE AND :finFechaE",VentaArticulos.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("nombre", nombre);
		consulta.setParameter("descripcion", "%" + descripcion + "%");
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<VentaArticulos>) consulta.getResultList();
	}
	
	/**
	 * Metodo para encontrar todas las ventas mediante un rango de fechas y el nombre del vendedor
	 * @param inicioFechaE, parametro de tipo Date que define la fecha de inicio 
	 * @param finFechaE, parametro de tipo Date que define la fecha de fin
	 * @param nombre, parametro de tipo string que define el nombre del vendedor
	 * @return una lista de todas las ventas encontradas de acuerdo a los parametros establecidos
	 */
	public List <VentaArticulos> reportarVentasPorVF(Date inicioFechaE, Date finFechaE, String nombre) {
		TypedQuery<VentaArticulos> consulta = entityManager.createQuery(
				"SELECT va FROM VentaArticulos va JOIN va.vendedores v WHERE va.estadoActividad =:estadoActividad AND v.nombre =:nombre AND va.fechaGeneracion BETWEEN :inicioFechaE AND :finFechaE",VentaArticulos.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("nombre", nombre);
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<VentaArticulos>) consulta.getResultList();
	}
	
	/**
	 * Metodo para reportar con un consulta todas las ventas mediante un rango de fechas el nombre del cliente y el nombre del vendedor
	 * @param inicioFechaE, parametro de tipo Date que define la fecha de inicio 
	 * @param finFechaE, parametro de tipo Date que define la fecha de fin
	 * @param nombre, parametro de tipo string que define el nombre del cliente
	 * @param nombreV, parametro de tipo string que define el nombre del vendedor
	 * @return una lista de objetos de tipo VentaArticulos con la informacion de las ventas encontradas
	 */
	public List <VentaArticulos> reportarVentasPorVCF(Date inicioFechaE, Date finFechaE, String nombre, String nombreV) {
		TypedQuery<VentaArticulos> consulta = entityManager.createQuery(
				"SELECT va FROM VentaArticulos va JOIN va.clientes c JOIN va.vendedores v WHERE va.estadoActividad =:estadoActividad AND c.nombre =:nombre AND v.nombre =:nombreV AND va.fechaGeneracion BETWEEN :inicioFechaE AND :finFechaE",VentaArticulos.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("nombre", nombre);
		consulta.setParameter("nombre", nombreV);
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<VentaArticulos>) consulta.getResultList();
	}
	
	/**
	 * Metodo para encontrar todas las ventas por un rango de fechas, el nombre del vendedor y la descricpcion de la forma de pago 
	 * utilizada por el cliente
	 * @param inicioFechaE, parametro de tipo Date que define la fecha de inicio 
	 * @param finFechaE, parametro de tipo Date que define la fecha de fin
	 * @param nombre, parametro de tipo string que define el nombre del vendedor
	 * @param descripcion, parametro de tipo string que define la descripcion de la forma de pago utilizada por el cliente
	 * @return una lista de objetos de tipo VentaArticulos con la informacion de las ventas encontradas
	 */
	public List <VentaArticulos> reportarVentasPorVFF(Date inicioFechaE, Date finFechaE, String nombre, String descripcion) {
		TypedQuery<VentaArticulos> consulta = entityManager.createQuery(
				"SELECT va FROM VentaArticulos va JOIN va.vendedores v JOIN va.formaPagoCliente fc WHERE va.estadoActividad =:estadoActividad AND v.nombre =:nombre AND fc.descripcion =:descripcion AND va.fechaGeneracion BETWEEN :inicioFechaE AND :finFechaE",VentaArticulos.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("nombre", nombre);
		consulta.setParameter("descripcion", "%" + descripcion + "%");
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<VentaArticulos>) consulta.getResultList();
	}
	
	/**
	 * Metodo para reportar todas aquellas ventas utilizando como filtro de busqueda un rango de fechas, el nombre del vendedor, el nombre del cliente y la descricpcion de la forma de pago 
	 * utilizada por el cliente
	 * @param inicioFechaE, parametro de tipo Date que define la fecha de inicio 
	 * @param finFechaE, parametro de tipo Date que define la fecha de fin
	 * @param nombre, parametro de tipo string que define el nombre del vendedor
	 * @param nombre, parametro de tipo string que define el nombre del cliente
	 * @param descripcion, parametro de tipo string que define la descripcion de la forma de pago utilizada por el cliente
	 * @return una lista de objetos de tipo VentaArticulos con la informacion de las ventas encontradas por los filtros de busqueda
	 */
	public List <VentaArticulos> reportarVentasPorVCFF(Date inicioFechaE, Date finFechaE, String nombre, String nombreV, String descripcion) {
		TypedQuery<VentaArticulos> consulta = entityManager.createQuery(
				"SELECT va FROM VentaArticulos va JOIN va.clientes c JOIN va.vendedores v JOIN va.formaPagoCliente fc WHERE va.estadoActividad =:estadoActividad AND c.nombre =:nombre AND v.nombre =:nombreV AND fc.descripcion =:descripcion AND va.fechaGeneracion BETWEEN :inicioFechaE AND :finFechaE",VentaArticulos.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("nombre", nombre);
		consulta.setParameter("nombre", nombreV);
		consulta.setParameter("descripcion", "%" + descripcion + "%");
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<VentaArticulos>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todas las ventas pendientes de cada cliente aprovechando como filtro de busqueda el estado de pago de la venta y la identificacion del cliente
	 * @param identificacion, parametro de tipo string en la que define el numero de identificacion del cliente
	 * @param estadoPago, parametro de tipo string en la que define el estado de pago (pendiente o cobrado)
	 * @return una lista de objetos de tipo VentaArticulos representando las ventas pendientes por cliente
	 */
	public List <VentaArticulos> traerVentaPendientePorCliente(String identificacion, String estadoPago) {
		TypedQuery<VentaArticulos> consulta = entityManager.createQuery(
				"SELECT va FROM VentaArticulos va JOIN va.clientes c WHERE va.estadoActividad =:estadoActividad AND va.estadoPago =:estadoPago AND c.identificacion =:identificacion ", VentaArticulos.class);
		consulta.setParameter("identificacion", identificacion);
		consulta.setParameter("estadoPago", estadoPago);
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<VentaArticulos>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer las ventas pendientes que se van a vencer
	 * @param inicioFechaE, parametro que define la fecha inicial
	 * @param finFechaE, parametro que define la fecha final
	 * @param estadoPago, el estado de la venta en este caso pendiente
	 * @return devuelve una lista de ventas pendientes cumpliendo dichos parametros
	 */
	public List <VentaArticulos> traerVentasPendientesAVencerse(Date inicioFechaE, Date finFechaE, String estadoPago) {
		TypedQuery<VentaArticulos> consulta = entityManager.createQuery(
				"SELECT va FROM VentaArticulos va JOIN va.clientes c JOIN va.formaPagoCliente fc WHERE va.estadoActividad =:estadoActividad AND va.estadoPago =:estadoPago AND va.fechaLimitePago BETWEEN :inicioFechaE AND :finFechaE",VentaArticulos.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("estadoPago", estadoPago);
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<VentaArticulos>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todas las ventas pendientes de acuerdo a un rango de fecha
	 * @param inicioFechaE, parametro que define la fecha inicial
	 * @param finFechaE, parametro que define la fecha final
	 * @param estadoPago, el estado de la venta en este caso pendiente
	 * @return devuelve una lista de ventas pendientes cumpliendo dichos parametros
	 */
	public List <VentaArticulos> reportarVentasPendientesPorF(Date inicioFechaE, Date finFechaE, String estadoPago) {
		TypedQuery<VentaArticulos> consulta = entityManager.createQuery(
				"SELECT va FROM VentaArticulos va JOIN va.clientes c JOIN va.vendedores v JOIN va.formaPagoCliente fc WHERE va.estadoActividad =:estadoActividad AND va.estadoPago =:estadoPago AND va.fechaLimitePago BETWEEN :inicioFechaE AND :finFechaE",VentaArticulos.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("estadoPago", estadoPago);
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<VentaArticulos>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todas las ventas pendientes de acuerdo a un rango de fecha y a un vendedor
	 * @param inicioFechaE, parametro que define la fecha inicial
	 * @param finFechaE, parametro que define la fecha final
	 * @param identificacion, parametro que define el numero de identificacion del vendedor
	 * @param estadoPago, parametro que define el estado de la venta en este caso pendiente
	 * @return devuelve una lista de ventas pendientes cumpliendo dichos parametros
	 */
	public List <VentaArticulos> reportarVentasPendientesPorFV(Date inicioFechaE, Date finFechaE, String identificacion,String estadoPago) {
		TypedQuery<VentaArticulos> consulta = entityManager.createQuery(
				"SELECT va FROM VentaArticulos va JOIN va.clientes c JOIN va.vendedores v JOIN va.formaPagoCliente fc WHERE va.estadoActividad =:estadoActividad AND va.estadoPago =:estadoPago AND v.identificacion =:identificacion AND va.fechaLimitePago BETWEEN :inicioFechaE AND :finFechaE",VentaArticulos.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("estadoPago", estadoPago);
		consulta.setParameter("identificacion", identificacion);
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<VentaArticulos>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todas las ventas pendientes vencidas
	 * @param estadoPago, parametro que define el estado de la venta en este caso pendiente
	 * @param fechaActual, parametro que define la fecha actual
	 * @return devuelve una lista de ventas pendientes cumpliendo dichos parametros
	 */
	public List <VentaArticulos> reportarTodaVentasPendientesVencidas(String estadoPago, Date fechaActual) {
		TypedQuery<VentaArticulos> consulta = entityManager.createQuery(
				"SELECT va FROM VentaArticulos va JOIN va.clientes c JOIN va.vendedores v JOIN va.formaPagoCliente fc WHERE va.estadoActividad =:estadoActividad AND va.estadoPago =:estadoPago AND datediff(:fechaActual, va.fechaLimitePago) < 0",VentaArticulos.class);
		consulta.setParameter("estadoPago", estadoPago);
		consulta.setParameter("fechaActual", fechaActual,TemporalType.DATE);
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<VentaArticulos>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer todas las ventas asociados al recibo de caja
	 * @param idReciboCaja, parametro que define el numero del recibo de caja
	 * @return una lista de ventas encontradas por medio del recibo de caja
	 */
	public List <VentaArticulos> traerVentasPorReciboCaja(int idReciboCaja) {
		TypedQuery<VentaArticulos> consulta = entityManager.createQuery(
				"SELECT va FROM VentaArticulos va JOIN va.reciboCaja rc WHERE va.estadoActividad =:estadoActividad AND rc.idReciboCaja =:idReciboCaja", VentaArticulos.class);
		consulta.setParameter("idReciboCaja", idReciboCaja);
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<VentaArticulos>) consulta.getResultList();
	}

}
