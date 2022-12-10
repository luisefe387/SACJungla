package co.com.jungla.sac.persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.DetalleVenta;

/**
 * clase dao con los metodos que se necesitan para gestionar el detalle de las ventas
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoDetalleVenta extends DaoGeneral<DetalleVenta,String>{

    /**
     * Metodo constructor sin parametros.  
     */
    public DaoDetalleVenta() {
        
    }

    /* 
   	 * Metodo que permite retornar la clase DetalleVenta
   	 */
	@Override
	protected Class<DetalleVenta> getEntityClass() {
		return DetalleVenta.class;
	}
	
	/**
	 * Metodo para traer el ultimo registro del detalle de la venta
	 * @return una lista con la informacion del ultimo detalle de venta
	 */
	public DetalleVenta traerUltimoRegistroDetalleVenta() {
		try{
			Query consulta = entityManager.createQuery(
					"SELECT dv FROM DetalleVenta dv order by dv.idDetalleVenta desc",
					DetalleVenta.class);
			consulta.setMaxResults(1);
			return (DetalleVenta) consulta.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	/**
	 * Metodo para reportar el detalle de venta mediante el codigo de venta
	 * @param idVenta, parametro que define el codigo de venta
	 * @return una lista en donde esta el detalle de la venta seleccionada
	 */
	public List <DetalleVenta> listarDetallePorCodigoVenta(int idVenta) {
		TypedQuery<DetalleVenta> consulta = entityManager.createQuery(
				"SELECT d FROM DetalleVenta d JOIN d.ventaArticulos va WHERE va.idVenta =:idVenta",DetalleVenta.class);
		consulta.setParameter("idVenta", idVenta);
		List<DetalleVenta> lista=(List<DetalleVenta>)consulta.getResultList( );
		return lista;
	}
	
	/**
	 * Metodo para traer el detalle de venta mediante su codigo
	 * @param idDetalleVenta, parametro que define el codigo de venta
	 * @return una lista en donde esta el detalle seleccionada
	 */
	public List <DetalleVenta> traerDetallePorCodigoDetalle(int idDetalleVenta) {
		TypedQuery<DetalleVenta> consulta = entityManager.createQuery(
				"SELECT d FROM DetalleVenta d WHERE d.idDetalleVenta =:idDetalleVenta",DetalleVenta.class);
		consulta.setParameter("idDetalleVenta", idDetalleVenta);
		List<DetalleVenta> lista=(List<DetalleVenta>)consulta.getResultList( );
		return lista;
	}

}
