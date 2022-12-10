package co.com.jungla.sac.persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.DetalleDevolucionProveedor;

/**
 * clase dao con los metodos que se necesitan para gestionar el detalle de la devolucion proveedor
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoDetalleDevolucionProveedor extends DaoGeneral<DetalleDevolucionProveedor,String>{

    /**
     * Metodo constructor sin parametros. 
     */
    public DaoDetalleDevolucionProveedor() {

    }

    /* 
   	 * Metodo que permite retornar la clase DetalleDevolucionProveedor
   	 */
	@Override
	protected Class<DetalleDevolucionProveedor> getEntityClass() {
		return DetalleDevolucionProveedor.class;
	}
	
	public List<DetalleDevolucionProveedor> traerultimaDetalleDevolucionProveedor() {
		TypedQuery<DetalleDevolucionProveedor> consulta = entityManager.createQuery(
				"SELECT d FROM DetalleDevolucionProveedor d order by d.idDetalleDevolucionProveedor desc",
				DetalleDevolucionProveedor.class);
		return (List<DetalleDevolucionProveedor>) consulta.getResultList();
	}
	
	public List <DetalleDevolucionProveedor> traerDetalleDevolucionProveedorPorCodigo(int idDetalleDevolucionProveedor) {
		TypedQuery<DetalleDevolucionProveedor> consulta = entityManager.createQuery(
				"SELECT dp FROM DetalleDevolucionProveedor dp WHERE dp.idDetalleDevolucionProveedor =:idDetalleDevolucionProveedor", DetalleDevolucionProveedor.class);
		consulta.setParameter("idDetalleDevolucionProveedor", idDetalleDevolucionProveedor);
		return (List<DetalleDevolucionProveedor>) consulta.getResultList();
	}
	
	
	public List <DetalleDevolucionProveedor> listarDetalleDevolucionProveedorPorIdentificacion(String identificacion) {
		TypedQuery<DetalleDevolucionProveedor> consulta = entityManager.createQuery(
				"SELECT dp FROM DetalleDevolucionProveedor dp JOIN dp.detalleCompra dt JOIN dt.compraArticulos ca JOIN ca.proveedores p WHERE p.identificacion =:identificacion", DetalleDevolucionProveedor.class);
		consulta.setParameter("identificacion", identificacion);
		return (List<DetalleDevolucionProveedor>) consulta.getResultList();
	}
	
	public List <DetalleDevolucionProveedor> listarDetallePorCodigoDevolucionProveedor(int idDevolucionProveedor) {
		TypedQuery<DetalleDevolucionProveedor> consulta = entityManager.createQuery(
				"SELECT dt FROM DetalleDevolucionProveedor dt JOIN dt.devolucionProveedor d JOIN d.compraArticulos ca WHERE d.idDevolucionProveedor =:idDevolucionProveedor",DetalleDevolucionProveedor.class);
		consulta.setParameter("idDevolucionProveedor", idDevolucionProveedor);
		List<DetalleDevolucionProveedor> lista=(List<DetalleDevolucionProveedor>)consulta.getResultList( );
		return lista;
	}
	
	public Object traerCantidadDevuelta(int idCompra, int codigo) {
		Query consulta = entityManager.createQuery(
				"SELECT sum(dtp.cantidadDevuelta) FROM DetalleDevolucionProveedor dtp JOIN dtp.devolucionProveedor dp JOIN dp.compraArticulos c JOIN c.detalleCompra dc JOIN dc.articulo a WHERE c.idCompra =:idCompra and a.codigo =:codigo",DetalleDevolucionProveedor.class);
		consulta.setParameter("idCompra", idCompra);
		consulta.setParameter("codigo", codigo);
		return consulta.getSingleResult();
	}
	
	public List <DetalleDevolucionProveedor> traerDetalleDevolucionProveedorPoridVenta(int idCompra) {
		TypedQuery<DetalleDevolucionProveedor> consulta = entityManager.createQuery(
				"SELECT dp FROM DetalleDevolucionProveedor dp JOIN dp.detalleCompra dt JOIN dt.compraArticulos ca WHERE ca.idCompra =:idCompra", DetalleDevolucionProveedor.class);
		consulta.setParameter("idCompra", idCompra);
		return (List<DetalleDevolucionProveedor>) consulta.getResultList();
	}
	
	
	/*//Consulta  para reportar las ordenes de compra por proveedor, rango fechas de entrega y estado
	public List <DevolucionProveedor> listarDetallePorCodigoCompra(int idCompra) {
		Query consulta = entityManager.createQuery(
				"SELECT d FROM DevolucionProveedor d JOIN d.DevolucionProveedor ca WHERE ca.idCompra =:idCompra",DevolucionProveedor.class);
		consulta.setParameter("idCompra", idCompra);
		List<DevolucionProveedor> lista=(List<DevolucionProveedor>)consulta.getResultList( );
		return lista;
	}*/

}
