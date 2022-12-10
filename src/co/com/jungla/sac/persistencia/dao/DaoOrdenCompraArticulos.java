package co.com.jungla.sac.persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.OrdenCompraArticulos;

/**
 * clase dao con los metodos que se necesitan para gestionar las ordenes de compra de articulos
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoOrdenCompraArticulos extends DaoGeneral<OrdenCompraArticulos,String>{

	/**
     * Metodo constructor sin parametros 
     */
    public DaoOrdenCompraArticulos() {
    }

    /* 
   	 * Metodo que permite retornar la clase OrdenCompraArticulos
   	 */
	@Override
	protected Class<OrdenCompraArticulos> getEntityClass() {
		return OrdenCompraArticulos.class;
	}
	
	public List <OrdenCompraArticulos> traerCodigoOrdenCompra() {
		TypedQuery<OrdenCompraArticulos> consulta = entityManager.createQuery(
				"SELECT oc FROM OrdenCompraArticulos oc order by oc.idOrdenCompra desc", OrdenCompraArticulos.class);
		return (List<OrdenCompraArticulos>) consulta.getResultList();
	}
	
	public List <OrdenCompraArticulos> traerCodigoOrdenPorCompra(int idOrdenCompra) {
		TypedQuery<OrdenCompraArticulos> consulta = entityManager.createQuery(
				"SELECT oc FROM OrdenCompraArticulos oc WHERE oc.idOrdenCompra =:idOrdenCompra", OrdenCompraArticulos.class);
		consulta.setParameter("idOrdenCompra", idOrdenCompra);
		return (List<OrdenCompraArticulos>) consulta.getResultList();
	}
	
	//Consulta  en la que recibe el codigo del articulo y retorna los datos de ese articulo
	public List <OrdenCompraArticulos> reportarOrdenesCompra() {
		TypedQuery<OrdenCompraArticulos> consulta = entityManager.createQuery(
				//"SELECT oc FROM OrdenCompraArticulos oc JOIN oc.proveedores p",OrdenCompraArticulos.class);
				"SELECT oc FROM OrdenCompraArticulos oc", OrdenCompraArticulos.class);
		List<OrdenCompraArticulos> lista=(List<OrdenCompraArticulos>)consulta.getResultList( );
		//consulta.getResultList().getClass().getCanonicalName();
		return lista;
	}
		
	//Consulta  para reportar las ordenes de compra por proveedor, rango fechas de entrega y estado
	public List <OrdenCompraArticulos> reportarOrdenesPorPFEE(String nombre, Date inicioFechaE, Date finFechaE, String estado) {
		TypedQuery<OrdenCompraArticulos> consulta = entityManager.createQuery(
				"SELECT oc FROM OrdenCompraArticulos oc JOIN oc.proveedores p WHERE p.nombre =:nombre AND oc.fechaEntregaEsperada BETWEEN :inicioFechaE AND :finFechaE AND oc.estado =:estado",OrdenCompraArticulos.class);
		consulta.setParameter("nombre", nombre);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("estado", estado);
		List<OrdenCompraArticulos> lista=(List<OrdenCompraArticulos>)consulta.getResultList( );
		return lista;
	}
	
	//Consulta  para reportar las ordenes de compra por rango fechas de entrega
	public List <OrdenCompraArticulos> reportarOrdenesPorFE(Date inicioFechaE, Date finFechaE) {
		TypedQuery<OrdenCompraArticulos> consulta = entityManager.createQuery(
				"SELECT oc FROM OrdenCompraArticulos oc WHERE oc.fechaEntregaEsperada BETWEEN :inicioFechaE AND :finFechaE",OrdenCompraArticulos.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		List<OrdenCompraArticulos> lista=(List<OrdenCompraArticulos>)consulta.getResultList( );
		return lista;
	}
		
		
	//Consulta  para reportar las ordenes de compra por proveedor, rango fechas de entrega y estado
	public List <OrdenCompraArticulos> reportarOrdenesPorPFE(String nombre, Date inicioFechaE, Date finFechaE) {
		TypedQuery<OrdenCompraArticulos> consulta = entityManager.createQuery(
				"SELECT oc FROM OrdenCompraArticulos oc JOIN oc.proveedores p WHERE p.nombre =:nombre AND oc.fechaEntregaEsperada BETWEEN :inicioFechaE AND :finFechaE",OrdenCompraArticulos.class);
		consulta.setParameter("nombre", nombre);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		List<OrdenCompraArticulos> lista=(List<OrdenCompraArticulos>)consulta.getResultList( );
		return lista;
	}
	
	//Consulta  para reportar las ordenes de compra por proveedor, rango fechas de entrega y estado
	public List <OrdenCompraArticulos> reportarOrdenesPorFEE(Date inicioFechaE, Date finFechaE, String estado) {
		TypedQuery<OrdenCompraArticulos> consulta = entityManager.createQuery(
				"SELECT oc FROM OrdenCompraArticulos oc WHERE oc.fechaEntregaEsperada BETWEEN :inicioFechaE AND :finFechaE AND oc.estado =:estado",OrdenCompraArticulos.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("estado", estado);
		List<OrdenCompraArticulos> lista=(List<OrdenCompraArticulos>)consulta.getResultList( );
		return lista;
	}
}
