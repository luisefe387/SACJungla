package co.com.jungla.sac.persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.AnticipoProveedor;

@Stateless
@LocalBean
public class DaoAnticipoProveedor extends DaoGeneral<AnticipoProveedor,String>{

    public DaoAnticipoProveedor() {
      
    }

	@Override
	protected Class<AnticipoProveedor> getEntityClass() {
		return AnticipoProveedor.class;
	}
	
	//Consulta que permite traer el ultimo dato registrado del anticipo proveedor
	public List <AnticipoProveedor> traerCodigoAnticipoProveedor() {
		TypedQuery<AnticipoProveedor> consulta = entityManager.createQuery(
				"SELECT ap FROM AnticipoProveedor ap order by ap.idAnticipoProveedor desc", AnticipoProveedor.class);
		return (List<AnticipoProveedor>) consulta.getResultList();
	}
	
	public List <AnticipoProveedor> traerAnticipoProveedorPorCodigo(int idAnticipoProveedor) {
		TypedQuery<AnticipoProveedor> consulta = entityManager.createQuery(
				"SELECT ap FROM AnticipoProveedor ap WHERE ap.idAnticipoProveedor =:idAnticipoProveedor", AnticipoProveedor.class);
		consulta.setParameter("idAnticipoProveedor", idAnticipoProveedor);
		return (List<AnticipoProveedor>) consulta.getResultList();
	}
	
	//Consulta  para listar los anticipos a partir de la identificacion del proveedor
	public List <AnticipoProveedor> listarAnticipoProveedorPorIdentificacion(String identificacion) {
		TypedQuery<AnticipoProveedor> consulta = entityManager.createQuery(
				"SELECT a FROM AnticipoProveedor a JOIN a.proveedores p WHERE p.identificacion =:identificacion", AnticipoProveedor.class);
		consulta.setParameter("identificacion", identificacion);
		return (List<AnticipoProveedor>) consulta.getResultList();
	}
	
	public List <AnticipoProveedor> reportarAnticiposPorF(Date inicioFechaE, Date finFechaE) {
		TypedQuery<AnticipoProveedor> consulta = entityManager.createQuery(
				"SELECT ap FROM AnticipoProveedor ap WHERE ap.fechaAnticipo BETWEEN :inicioFechaE AND :finFechaE",AnticipoProveedor.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		return (List<AnticipoProveedor>) consulta.getResultList();
	}
	
	public List <AnticipoProveedor> reportarAnticiposPorPF(Date inicioFechaE, Date finFechaE, String nombre) {
		TypedQuery<AnticipoProveedor> consulta = entityManager.createQuery(
				"SELECT ap FROM AnticipoProveedor ap JOIN ap.proveedores p WHERE p.nombre =:nombre AND ap.fechaAnticipo BETWEEN :inicioFechaE AND :finFechaE",AnticipoProveedor.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("nombre", nombre);
		return (List<AnticipoProveedor>) consulta.getResultList();
	}
	
	public List <AnticipoProveedor> reportarAnticiposPorEF(Date inicioFechaE, Date finFechaE, String estadoAnticipo) {
		TypedQuery<AnticipoProveedor> consulta = entityManager.createQuery(
				"SELECT ap FROM AnticipoProveedor ap WHERE ap.estadoAnticipo =:estadoAnticipo AND ap.fechaAnticipo BETWEEN :inicioFechaE AND :finFechaE",AnticipoProveedor.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("estadoAnticipo", estadoAnticipo);
		return (List<AnticipoProveedor>) consulta.getResultList();
	}
	
	public List <AnticipoProveedor> reportarAnticiposPorPEF(Date inicioFechaE, Date finFechaE, String nombre, String estadoAnticipo) {
		TypedQuery<AnticipoProveedor> consulta = entityManager.createQuery(
				"SELECT ap FROM AnticipoProveedor ap JOIN ap.proveedores p WHERE p.nombre =:nombre AND ap.estadoAnticipo =:estadoAnticipo AND ap.fechaAnticipo BETWEEN :inicioFechaE AND :finFechaE",AnticipoProveedor.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("estadoAnticipo", estadoAnticipo);
		consulta.setParameter("nombre", nombre);
		return (List<AnticipoProveedor>) consulta.getResultList();
	}
}
