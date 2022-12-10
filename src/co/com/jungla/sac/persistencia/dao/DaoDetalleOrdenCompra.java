package co.com.jungla.sac.persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.DetalleOrdenCompra;

/**
 * clase dao con los metodos que se necesitan para gestionar el detalle de las ordenes de compra
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoDetalleOrdenCompra extends DaoGeneral<DetalleOrdenCompra,String>{

    /**
     * Metodo constructor sin parametros.  
     */
    public DaoDetalleOrdenCompra() {
       
    }

    /* 
   	 * Metodo que permite retornar la clase DetalleOrdenCompra
   	 */
	@Override
	protected Class<DetalleOrdenCompra> getEntityClass() {
		return DetalleOrdenCompra.class;
	}
	
	/**
	 * Metodo para reportar el detalle de la orden de compra mediante el codigo de la orden de compra
	 * @param idOrdenCompra, parametro que define el numero de la orden de compra
	 * @return devuelve una lista del detalle de la orden de compra elegida
	 */
	public List <DetalleOrdenCompra> listarDetalleOrdenPorCodigoOrden(int idOrdenCompra) {
		TypedQuery<DetalleOrdenCompra> consulta = entityManager.createQuery(
				"SELECT do FROM DetalleOrdenCompra do JOIN do.ordenCompraArticulos oc WHERE oc.idOrdenCompra =:idOrdenCompra",DetalleOrdenCompra.class);
		consulta.setParameter("idOrdenCompra", idOrdenCompra);
		List<DetalleOrdenCompra> lista=(List<DetalleOrdenCompra>)consulta.getResultList( );
		return lista;
	}

}
