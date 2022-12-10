package co.com.jungla.sac.persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.DetalleCotizacion;

/**
 * clase dao con los metodos que se necesitan para gestionar el detalle de las cotizaciones
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoDetalleCotizacion extends DaoGeneral<DetalleCotizacion,String>{

    /**
     * Metodo constructor sin parametros.  
     */
    public DaoDetalleCotizacion() {
        
    }

    /* 
   	 * Metodo que permite retornar la clase DetalleCotizacion
   	 */
	@Override
	protected Class<DetalleCotizacion> getEntityClass() {
		return DetalleCotizacion.class;
	}
	
	/**
	 * Metodo para traer el ultimo registro del detalle de la cotizacion
	 * @return una lista con la informacion del ultimo detalle de la cotizacion
	 */
	public DetalleCotizacion traerUltimoRegistroDetalleCotizacion() {
		try{
			Query consulta = entityManager.createQuery(
					"SELECT dc FROM DetalleCotizacion dc order by dc.idDetalleCotizacion desc",
					DetalleCotizacion.class);
			consulta.setMaxResults(1);
			return (DetalleCotizacion) consulta.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	/**
	 * Metodo para reportar el detalle de la cotizacion mediante el codigo de cotizacion
	 * @param idCotizacion, parametro que define el codigo de la cotizacion
	 * @return una lista en donde esta el detalle de la cotizacion seleccionada
	 */
	public List <DetalleCotizacion> listarDetallePorCodigoCotizacion(int idCotizacion) {
		TypedQuery<DetalleCotizacion> consulta = entityManager.createQuery(
				"SELECT dc FROM DetalleCotizacion dc JOIN dc.cotizacion c WHERE c.idCotizacion =:idCotizacion",DetalleCotizacion.class);
		consulta.setParameter("idCotizacion", idCotizacion);
		List<DetalleCotizacion> lista=(List<DetalleCotizacion>)consulta.getResultList( );
		return lista;
	}

}
