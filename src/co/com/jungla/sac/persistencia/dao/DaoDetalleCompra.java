package co.com.jungla.sac.persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.DetalleCompra;

/**
 * clase dao con los metodos que se necesitan para gestionar el detalle de las compras
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoDetalleCompra extends DaoGeneral<DetalleCompra,String>{

    /**
     * Metodo constructor sin parametros. 
     */
    public DaoDetalleCompra() {
     
    }

    /* 
   	 * Metodo que permite retornar la clase DetalleCompra
   	 */
	@Override
	protected Class<DetalleCompra> getEntityClass() {
		return DetalleCompra.class;
	}
	
	public List <DetalleCompra> traerUltimoRegistroDetalleCompra() {
		TypedQuery<DetalleCompra> consulta = entityManager.createQuery(
				"SELECT dc FROM DetalleCompra dc order by dc.idDetalleCompra desc", DetalleCompra.class);
		return (List<DetalleCompra>) consulta.getResultList();
	}
	
	//Consulta  para reportar las ordenes de compra por proveedor, rango fechas de entrega y estado
	public List <DetalleCompra> listarDetallePorCodigoCompra(int idCompra) {
		TypedQuery<DetalleCompra> consulta = entityManager.createQuery(
				"SELECT d FROM DetalleCompra d JOIN d.compraArticulos ca WHERE ca.idCompra =:idCompra",DetalleCompra.class);
		consulta.setParameter("idCompra", idCompra);
		List<DetalleCompra> lista=(List<DetalleCompra>)consulta.getResultList( );
		return lista;
	}

}
