package co.com.jungla.sac.persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.CompraArticulos;

@Stateless
@LocalBean
public class DaoCompraArticulos extends DaoGeneral<CompraArticulos,String>{

	String estadoActividad= "Activo";
	
    public DaoCompraArticulos() {
    
    }

	@Override
	protected Class<CompraArticulos> getEntityClass() {
		return CompraArticulos.class;
	}
	
	public List <CompraArticulos> traerCodigoCompra() {
		TypedQuery<CompraArticulos> consulta = entityManager.createQuery(
				"SELECT ca FROM CompraArticulos ca order by ca.idCompra desc",
				CompraArticulos.class);
		return (List<CompraArticulos>) consulta.getResultList();
	}
	
	public CompraArticulos traerCompraPorCodigo(int idCompra) {
		Query consulta = entityManager.createQuery(
				"SELECT ca FROM CompraArticulos ca WHERE ca.estadoActividad =:estadoActividad AND ca.idCompra =:idCompra", CompraArticulos.class);
		consulta.setParameter("idCompra", idCompra);
		consulta.setParameter("estadoActividad", estadoActividad);
		return (CompraArticulos) consulta.getSingleResult();
	}
	
	public CompraArticulos traerTodaCompraPorCodigo(int idCompra) {
		Query consulta = entityManager.createQuery(
				"SELECT ca FROM CompraArticulos ca WHERE ca.idCompra =:idCompra", CompraArticulos.class);
		consulta.setParameter("idCompra", idCompra);
		return (CompraArticulos) consulta.getSingleResult();
	}
	
	public List <CompraArticulos> traerCompraPorFactProv(int factProv) {
		TypedQuery<CompraArticulos> consulta = entityManager.createQuery(
				"SELECT ca FROM CompraArticulos ca WHERE ca.estadoActividad =:estadoActividad AND ca.factProv =:factProv", CompraArticulos.class);
		consulta.setParameter("factProv", factProv);
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<CompraArticulos>) consulta.getResultList();
	}
	
	//Consulta  para reportar las compras por rango fechas de causacion
	public List <CompraArticulos> reportarComprasPorFE(Date inicioFechaE, Date finFechaE) {
		TypedQuery<CompraArticulos> consulta = entityManager.createQuery(
				"SELECT ca FROM CompraArticulos ca WHERE ca.estadoActividad =:estadoActividad AND ca.fechaCausacion BETWEEN :inicioFechaE AND :finFechaE",CompraArticulos.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<CompraArticulos>) consulta.getResultList();
	}
	
	//Consulta  para reportar las compras por proveedor y rango fechas de causacion 
	public List <CompraArticulos> reportarComprasPorPFE(String nombre, Date inicioFechaE, Date finFechaE) {
		TypedQuery<CompraArticulos> consulta = entityManager.createQuery(
				"SELECT ca FROM CompraArticulos ca JOIN ca.proveedores p WHERE ca.estadoActividad =:estadoActividad AND p.nombre =:nombre AND ca.fechaCausacion BETWEEN :inicioFechaE AND :finFechaE",CompraArticulos.class);
		consulta.setParameter("nombre", nombre);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("estadoActividad", estadoActividad);
		return (List<CompraArticulos>) consulta.getResultList();
	}
}
