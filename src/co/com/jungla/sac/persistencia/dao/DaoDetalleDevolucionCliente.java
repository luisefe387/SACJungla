package co.com.jungla.sac.persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.DetalleDevolucionCliente;

/**
 * clase dao con los metodos que se necesitan para gestionar el detalle de la devolucion cliente
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoDetalleDevolucionCliente extends DaoGeneral<DetalleDevolucionCliente,String>{

    /**
     * Metodo constructor sin parametros. 
     */
    public DaoDetalleDevolucionCliente() {

    }

    /* 
   	 * Metodo que permite retornar la clase DetalleDevolucionCliente
   	 */
	@Override
	protected Class<DetalleDevolucionCliente> getEntityClass() {
		return DetalleDevolucionCliente.class;
	}
	
	public List<DetalleDevolucionCliente> traerultimaDetalleDevolucionCliente() {
		TypedQuery<DetalleDevolucionCliente> consulta = entityManager.createQuery(
				"SELECT d FROM DetalleDevolucionCliente d order by d.idDetalleDevolucionCliente desc",
				DetalleDevolucionCliente.class);
		return (List<DetalleDevolucionCliente>) consulta.getResultList();
	}
	
	
	public List <DetalleDevolucionCliente> listarDetallePorCodigoDevolucionCliente(int idDevolucionCliente) {
		TypedQuery<DetalleDevolucionCliente> consulta = entityManager.createQuery(
				"SELECT dt FROM DetalleDevolucionCliente dt JOIN dt.devolucionCliente d JOIN d.ventaArticulos va WHERE d.idDevolucionCliente =:idDevolucionCliente",DetalleDevolucionCliente.class);
		consulta.setParameter("idDevolucionCliente", idDevolucionCliente);
		List<DetalleDevolucionCliente> lista=(List<DetalleDevolucionCliente>)consulta.getResultList( );
		return lista;
	}
	
	public List <DetalleDevolucionCliente> traerDetalleDevolucionClientePorCodigo(int idDetalleDevolucionCliente) {
		TypedQuery<DetalleDevolucionCliente> consulta = entityManager.createQuery(
				"SELECT dc FROM DetalleDevolucionCliente dc WHERE dc.idDetalleDevolucionCliente =:idDetalleDevolucionCliente", DetalleDevolucionCliente.class);
		consulta.setParameter("idDetalleDevolucionCliente", idDetalleDevolucionCliente);
		return (List<DetalleDevolucionCliente>) consulta.getResultList();
	}
	
	
	public List <DetalleDevolucionCliente> listarDetalleDevolucionClientePorIdentificacion(String identificacion) {
		TypedQuery<DetalleDevolucionCliente> consulta = entityManager.createQuery(
				"SELECT dc FROM DetalleDevolucionCliente dc JOIN dc.detalleVenta dt JOIN dt.ventaArticulos va JOIN va.clientes c WHERE c.identificacion =:identificacion", DetalleDevolucionCliente.class);
		consulta.setParameter("identificacion", identificacion);
		return (List<DetalleDevolucionCliente>) consulta.getResultList();
	}
	
	public Object traerCantidadDevuelta(int idVenta, int codigo) {
		Query consulta = entityManager.createQuery(
				"SELECT sum(dtc.cantidadDevuelta) FROM DetalleDevolucionCliente dtc JOIN dtc.devolucionCliente dc JOIN dc.ventaArticulos v JOIN v.detalleVenta dv JOIN dv.articulo a WHERE v.idVenta =:idVenta and a.codigo =:codigo",DetalleDevolucionCliente.class);
		consulta.setParameter("idVenta", idVenta);
		consulta.setParameter("codigo", codigo);
		return consulta.getSingleResult();
	}
	
	public List <DetalleDevolucionCliente> traerDetalleDevolucionClientePoridVenta(int idVenta) {
		TypedQuery<DetalleDevolucionCliente> consulta = entityManager.createQuery(
				"SELECT dc FROM DetalleDevolucionCliente dc JOIN dc.detalleVenta dt JOIN dt.ventaArticulos va WHERE va.idVenta =:idVenta", DetalleDevolucionCliente.class);
		consulta.setParameter("idVenta", idVenta);
		return (List<DetalleDevolucionCliente>) consulta.getResultList();
	}
	
	
	/*//Consulta  para reportar las ordenes de compra por proveedor, rango fechas de entrega y estado
	public List <DevolucionCliente> listarDetallePorCodigoCompra(int idCompra) {
		Query consulta = entityManager.createQuery(
				"SELECT d FROM DevolucionCliente d JOIN d.DevolucionCliente ca WHERE ca.idCompra =:idCompra",DevolucionCliente.class);
		consulta.setParameter("idCompra", idCompra);
		List<DevolucionCliente> lista=(List<DevolucionCliente>)consulta.getResultList( );
		return lista;
	}*/

}
