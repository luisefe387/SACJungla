package co.com.jungla.sac.persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.AnticipoCliente;


@Stateless
@LocalBean
public class DaoAnticipoCliente extends DaoGeneral<AnticipoCliente,String>{

    public DaoAnticipoCliente() {
    
    }

	@Override
	protected Class<AnticipoCliente> getEntityClass() {
		return AnticipoCliente.class;
	}
	
	//Consulta que permite traer el ultimo dato registrado del anticipo cliente
	public List <AnticipoCliente> traerUltimoAnticipoCliente() {
		TypedQuery<AnticipoCliente> consulta = entityManager.createQuery(
				"SELECT ac FROM AnticipoCliente ac order by ac.idAnticipoCliente desc", AnticipoCliente.class);
		return (List<AnticipoCliente>) consulta.getResultList();
	}
	
	//Consulta  para listar los anticipos a partir de la identificacion del cliente
	public List <AnticipoCliente> listarAnticipoClientePorIdentificacion(String identificacion) {
		TypedQuery<AnticipoCliente> consulta = entityManager.createQuery(
				"SELECT ac FROM AnticipoCliente ac JOIN ac.clientes c WHERE c.identificacion =:identificacion", AnticipoCliente.class);
		consulta.setParameter("identificacion", identificacion);
		return (List<AnticipoCliente>) consulta.getResultList();
	}
	
	public List <AnticipoCliente> traerAnticipoClientePorCodigo(int idAnticipoCliente) {
		TypedQuery<AnticipoCliente> consulta = entityManager.createQuery(
				"SELECT ac FROM AnticipoCliente ac WHERE ac.idAnticipoCliente =:idAnticipoCliente", AnticipoCliente.class);
		consulta.setParameter("idAnticipoCliente", idAnticipoCliente);
		return (List<AnticipoCliente>) consulta.getResultList();
	}
	
	/*public List <AnticipoCliente> traerAnticipoClientePorCodigo(int idAnticipoCliente) {
		TypedQuery<AnticipoCliente> consulta = entityManager.createQuery(
				"SELECT ap FROM AnticipoCliente ap WHERE ap.idAnticipoCliente =:idAnticipoCliente", AnticipoCliente.class);
		consulta.setParameter("idAnticipoCliente", idAnticipoCliente);
		return (List<AnticipoCliente>) consulta.getResultList();
	}
	
	public List <AnticipoCliente> reportarAnticiposPorF(Date inicioFechaE, Date finFechaE) {
		TypedQuery<AnticipoCliente> consulta = entityManager.createQuery(
				"SELECT ap FROM AnticipoCliente ap WHERE ap.fechaAnticipo BETWEEN :inicioFechaE AND :finFechaE",AnticipoCliente.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		return (List<AnticipoCliente>) consulta.getResultList();
	}
	
	public List <AnticipoCliente> reportarAnticiposPorPF(Date inicioFechaE, Date finFechaE, String nombre) {
		TypedQuery<AnticipoCliente> consulta = entityManager.createQuery(
				"SELECT ap FROM AnticipoCliente ap JOIN ap.proveedores p WHERE p.nombre =:nombre AND ap.fechaAnticipo BETWEEN :inicioFechaE AND :finFechaE",AnticipoCliente.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("nombre", nombre);
		return (List<AnticipoCliente>) consulta.getResultList();
	}
	
	public List <AnticipoCliente> reportarAnticiposPorEF(Date inicioFechaE, Date finFechaE, String estadoAnticipo) {
		TypedQuery<AnticipoCliente> consulta = entityManager.createQuery(
				"SELECT ap FROM AnticipoCliente ap WHERE ap.estadoAnticipo =:estadoAnticipo AND ap.fechaAnticipo BETWEEN :inicioFechaE AND :finFechaE",AnticipoCliente.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("estadoAnticipo", estadoAnticipo);
		return (List<AnticipoCliente>) consulta.getResultList();
	}
	
	public List <AnticipoCliente> reportarAnticiposPorPEF(Date inicioFechaE, Date finFechaE, String nombre, String estadoAnticipo) {
		TypedQuery<AnticipoCliente> consulta = entityManager.createQuery(
				"SELECT ap FROM AnticipoCliente ap JOIN ap.proveedores p WHERE p.nombre =:nombre AND ap.estadoAnticipo =:estadoAnticipo AND ap.fechaAnticipo BETWEEN :inicioFechaE AND :finFechaE",AnticipoCliente.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		consulta.setParameter("estadoAnticipo", estadoAnticipo);
		consulta.setParameter("nombre", nombre);
		return (List<AnticipoCliente>) consulta.getResultList();
	}*/
}
