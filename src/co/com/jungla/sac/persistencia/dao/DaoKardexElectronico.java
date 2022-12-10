package co.com.jungla.sac.persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.KardexElectronico;

/**
 * clase dao con los metodos que se necesitan para gestionar el kardex electronico para los articulos
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoKardexElectronico extends DaoGeneral<KardexElectronico,String> {

    /**
     * Metodo constructor sin parametros. 
     */
    public DaoKardexElectronico() {
        
    }

    /* 
   	 * Metodo que permite retornar la clase KardexElectronico
   	 */
	@Override
	protected Class<KardexElectronico> getEntityClass() {
		return KardexElectronico.class;
	}
	
	//traer ultimo registros del kardex para las compras y devoluciones de compras realizadas
	public List <KardexElectronico> traerUltimoRegistroKardexElectronicopPorArticulo(int codigo) {
		TypedQuery<KardexElectronico> consulta = entityManager.createQuery(
				"SELECT k FROM KardexElectronico k JOIN k.articulo a WHERE a.codigo = :codigo order by k.idKardex desc", KardexElectronico.class);
		consulta.setParameter("codigo", codigo);
		if (consulta.getResultList() == null || consulta.getResultList().isEmpty()) {
	        return null;
	    }else{
	    	return (List<KardexElectronico>) consulta.getResultList();
	    }
	}
	
	public List <KardexElectronico> traerUltimoRegistroKardexElectronico() {
		TypedQuery<KardexElectronico> consulta = entityManager.createQuery(
				"SELECT k FROM KardexElectronico k order by k.idKardex desc", KardexElectronico.class);
		if (consulta.getResultList() == null || consulta.getResultList().isEmpty()) {
	        return null;
	    }else{
	    	return (List<KardexElectronico>) consulta.getResultList();
	    }
	}
	
	//Metodo para traer todos los registros del kardex por un determinado rango de fechas
	public List <KardexElectronico> traerRegistrosKardexPorF(Date inicioFecha, Date finFecha) {
		TypedQuery<KardexElectronico> consulta = entityManager.createQuery(
				"SELECT k FROM KardexElectronico k WHERE k.fecha BETWEEN :inicioFecha AND :finFecha",KardexElectronico.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		List<KardexElectronico> lista=(List<KardexElectronico>)consulta.getResultList( );
		return lista;
	}
	
	//Metodo para traer todos los registros del kardex por movimiento, grupo de articulo, articulo y un determinado rango de fechas
	public List <KardexElectronico> traerRegistrosKardexPorMGAF(String movimiento, String nombreL, String nombre, Date inicioFecha, Date finFecha) {
		TypedQuery<KardexElectronico> consulta = entityManager.createQuery(
				"SELECT k FROM KardexElectronico k JOIN k.articulo a JOIN a.lineaArticulos l WHERE k.movimiento =:movimiento AND l.nombreL =:nombreL AND a.nombre =:nombre AND k.fecha BETWEEN :inicioFecha AND :finFecha",KardexElectronico.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("movimiento", movimiento);
		consulta.setParameter("nombreL", nombreL);
		consulta.setParameter("nombre", nombre);
		List<KardexElectronico> lista=(List<KardexElectronico>)consulta.getResultList( );
		return lista;
	}
	
	//Metodo para traer todos los registros del kardex por movimiento, grupo de articulo y un determinado rango de fechas
	public List <KardexElectronico> traerRegistrosKardexPorMGF(String movimiento, String nombreL, Date inicioFecha, Date finFecha) {
		TypedQuery<KardexElectronico> consulta = entityManager.createQuery(
				"SELECT k FROM KardexElectronico k JOIN k.articulo a JOIN a.lineaArticulos l WHERE k.movimiento =:movimiento AND l.nombreL =:nombreL AND k.fecha BETWEEN :inicioFecha AND :finFecha",KardexElectronico.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("movimiento", movimiento);
		consulta.setParameter("nombreL", nombreL);
		List<KardexElectronico> lista=(List<KardexElectronico>)consulta.getResultList( );
		return lista;
	}
	
	//Metodo para traer todos los registros del kardex por movimiento y un determinado rango de fechas
	public List <KardexElectronico> traerRegistrosKardexPorMF(String movimiento, Date inicioFecha, Date finFecha) {
		TypedQuery<KardexElectronico> consulta = entityManager.createQuery(
				"SELECT k FROM KardexElectronico k JOIN k.articulo a JOIN a.lineaArticulos l WHERE k.movimiento =:movimiento AND k.fecha BETWEEN :inicioFecha AND :finFecha",KardexElectronico.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("movimiento", movimiento);
		List<KardexElectronico> lista=(List<KardexElectronico>)consulta.getResultList( );
		return lista;
	}
	
	//Metodo para traer todos los registros del kardex por grupo de articulo y un determinado rango de fechas
	public List <KardexElectronico> traerRegistrosKardexPorGF(String nombreL, Date inicioFecha, Date finFecha) {
		TypedQuery<KardexElectronico> consulta = entityManager.createQuery(
				"SELECT k FROM KardexElectronico k JOIN k.articulo a JOIN a.lineaArticulos l WHERE l.nombreL =:nombreL AND k.fecha BETWEEN :inicioFecha AND :finFecha",KardexElectronico.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("nombreL", nombreL);
		List<KardexElectronico> lista=(List<KardexElectronico>)consulta.getResultList( );
		return lista;
	}
	
	//Metodo para traer todos los registros del kardex por grupo de articulo, articulo y un determinado rango de fechas
	public List <KardexElectronico> traerRegistrosKardexPorGAF(String nombreL, String nombre, Date inicioFecha, Date finFecha) {
		TypedQuery<KardexElectronico> consulta = entityManager.createQuery(
				"SELECT k FROM KardexElectronico k JOIN k.articulo a JOIN a.lineaArticulos l WHERE l.nombreL =:nombreL AND a.nombre =:nombre AND  k.fecha BETWEEN :inicioFecha AND :finFecha",KardexElectronico.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("nombreL", nombreL);
		consulta.setParameter("nombre", nombre);
		List<KardexElectronico> lista=(List<KardexElectronico>)consulta.getResultList( );
		return lista;
	}
	
	//Metodo para traer todos los registros del kardex por busqueda rapida basado en las coincidencias de nombre articulo y un determinado rango de fechas
	public List <KardexElectronico> traerRegistrosKardexPorBF(String nombre, Date inicioFecha, Date finFecha) {
		TypedQuery<KardexElectronico> consulta = entityManager.createQuery(
				"SELECT k FROM KardexElectronico k JOIN k.articulo a JOIN a.lineaArticulos l WHERE a.nombre LIKE :nombre AND k.fecha BETWEEN :inicioFecha AND :finFecha", KardexElectronico.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("nombre", "%" + nombre + "%");
		return (List<KardexElectronico>) consulta.getResultList();
	}
	
	//Metodo para traer todos los registros del kardex por busqueda rapida basado en las coincidencias de nombre articulo, un movimiento y un determinado rango de fechas
	public List <KardexElectronico> traerRegistrosKardexPorBMF(String nombre, String movimiento, Date inicioFecha, Date finFecha) {
		TypedQuery<KardexElectronico> consulta = entityManager.createQuery(
				"SELECT k FROM KardexElectronico k JOIN k.articulo a JOIN a.lineaArticulos l WHERE k.movimiento =:movimiento AND a.nombre LIKE :nombre AND k.fecha BETWEEN :inicioFecha AND :finFecha", KardexElectronico.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("nombre", "%" + nombre + "%");
		consulta.setParameter("movimiento", movimiento);
		return (List<KardexElectronico>) consulta.getResultList();
	}
	
	//Metodo para traer todos los registros del kardex por busqueda rapida basado en las coincidencias de nombre articulo, un movimiento, una linea y un determinado rango de fechas
	public List <KardexElectronico> traerRegistrosKardexPorBMGF(String nombre, String movimiento, String nombreL, Date inicioFecha, Date finFecha) {
		TypedQuery<KardexElectronico> consulta = entityManager.createQuery(
				"SELECT k FROM KardexElectronico k JOIN k.articulo a JOIN a.lineaArticulos l WHERE k.movimiento =:movimiento AND l.nombreL =:nombreL AND a.nombre LIKE :nombre AND k.fecha BETWEEN :inicioFecha AND :finFecha", KardexElectronico.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("nombre", "%" + nombre + "%");
		consulta.setParameter("movimiento", movimiento);
		consulta.setParameter("nombreL", nombreL);
		return (List<KardexElectronico>) consulta.getResultList();
	}
	
	//Metodo para traer todos los registros del kardex por grupo de articulo, busqueda rapida articulo y un determinado rango de fechas
	public List <KardexElectronico> traerRegistrosKardexPorBGF(String nombreL, String nombre, Date inicioFecha, Date finFecha) {
		TypedQuery<KardexElectronico> consulta = entityManager.createQuery(
				"SELECT k FROM KardexElectronico k JOIN k.articulo a JOIN a.lineaArticulos l WHERE l.nombreL =:nombreL AND a.nombre LIKE :nombre AND k.fecha BETWEEN :inicioFecha AND :finFecha",KardexElectronico.class);
		consulta.setParameter("inicioFecha", inicioFecha,TemporalType.DATE);
		consulta.setParameter("finFecha", finFecha,TemporalType.DATE);
		consulta.setParameter("nombreL", nombreL);
		consulta.setParameter("nombre", "%" + nombre + "%");
		List<KardexElectronico> lista=(List<KardexElectronico>)consulta.getResultList( );
		return lista;
	}
	
	public List <KardexElectronico> traerVentaEnKardexParaDevVenta(int idVenta , String movimiento, int codigo) {
		TypedQuery<KardexElectronico> consulta = entityManager.createQuery(
				"SELECT k FROM KardexElectronico k JOIN k.articulo a WHERE k.nroDocumento =:idVenta AND k.movimiento =:movimiento AND a.codigo =:codigo",KardexElectronico.class);
		consulta.setParameter("idVenta", idVenta);
		consulta.setParameter("movimiento", movimiento);
		consulta.setParameter("codigo", codigo);
		if (consulta.getResultList() == null || consulta.getResultList().isEmpty()) {
	        return null;
	    }else{
	    	return (List<KardexElectronico>) consulta.getResultList();
	    }
	}
}
