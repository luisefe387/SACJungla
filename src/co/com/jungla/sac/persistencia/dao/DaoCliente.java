package co.com.jungla.sac.persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.Cliente;

@Stateless
@LocalBean
public class DaoCliente extends DaoGeneral<Cliente,String>{

    public DaoCliente() {
       
    }

	@Override
	protected Class<Cliente> getEntityClass() {
		return Cliente.class;
	}
	
	public List<Cliente> traerUltimoCliente() {
		TypedQuery<Cliente> consulta = entityManager.createQuery(
				"SELECT cl FROM Cliente cl order by cl.identificacion desc", Cliente.class);
		if (consulta.getResultList() == null || consulta.getResultList().isEmpty()) {
	        return null;
	    }else{
	    	return (List<Cliente>) consulta.getResultList();
	    }
	}
	
	public List <Cliente> traerClientePorIdentificacionS(String identificacion) {
		TypedQuery<Cliente> consulta = entityManager.createQuery(
				"SELECT c FROM Cliente c WHERE c.identificacion = :identificacion", Cliente.class);
		consulta.setParameter("identificacion", identificacion);
		return (List<Cliente>) consulta.getResultList();
	}
	
	public List <Cliente> traerClientePorIdentificacion(String identificacion) {
		TypedQuery<Cliente> consulta = entityManager.createQuery(
				"SELECT c FROM Cliente c WHERE c.identificacion LIKE :identificacion", Cliente.class);
		consulta.setParameter("identificacion", "%" + identificacion + "%");
		return (List<Cliente>) consulta.getResultList();
	}
	
	public List <Cliente> traerClientePorNombre(String nombre) {
		TypedQuery<Cliente> consulta = entityManager.createQuery(
				"SELECT c FROM Cliente c WHERE c.nombre LIKE :nombre", Cliente.class);
		consulta.setParameter("nombre", "%" + nombre + "%");
		return (List<Cliente>) consulta.getResultList();
	}
	
	public List <Cliente> traerClientePorIdentificacionYNombre(String nombre, String identificacion) {
		TypedQuery<Cliente> consulta = entityManager.createQuery(
				"SELECT c FROM Cliente c  WHERE c.nombre LIKE :nombre AND c.identificacion LIKE :identificacion", Cliente.class);
		consulta.setParameter("nombre", "%" + nombre + "%");
		consulta.setParameter("identificacion", "%" + identificacion+ "%");
		return (List<Cliente>) consulta.getResultList();
	}
	
	public List <Cliente> traerClientePorDepartamento(String departamento) {
		TypedQuery<Cliente> consulta = entityManager.createQuery(
				"SELECT c FROM Cliente c WHERE c.departamento = :departamento", Cliente.class);
		consulta.setParameter("departamento" , departamento);
		return (List<Cliente>) consulta.getResultList();
	}
	
	public List <Cliente> traerClientePorCiudad(String ciudad) {
		TypedQuery<Cliente> consulta = entityManager.createQuery(
				"SELECT c FROM Cliente c WHERE c.ciudad = :ciudad", Cliente.class);
		consulta.setParameter("ciudad" , ciudad);
		return (List<Cliente>) consulta.getResultList();
	}
	
	public List <Cliente> traerClienteDeshabilitado(String estado) {
		TypedQuery<Cliente> consulta = entityManager.createQuery(
				"SELECT c FROM Cliente c WHERE c.estado = :estado", Cliente.class);
		consulta.setParameter("estado" , estado);
		return (List<Cliente>) consulta.getResultList();
	}

}
