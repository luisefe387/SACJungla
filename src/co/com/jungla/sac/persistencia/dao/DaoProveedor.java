package co.com.jungla.sac.persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.Proveedor;

/**
 * clase dao con los metodos que se necesitan para gestionar los proveedores
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoProveedor extends DaoGeneral<Proveedor, String>{

	/**
     * Metodo constructor sin parametros 
     */
    public DaoProveedor() {
      
    }

    /* 
   	 * Metodo que permite retornar la clase Proveedor
   	 */
	@Override
	protected Class<Proveedor> getEntityClass() {
		return Proveedor.class;
	}

	public List <Proveedor> traerProveedorPorNombre(String nombre) {
		TypedQuery<Proveedor> consulta = entityManager.createQuery(
				"SELECT p FROM Proveedor p WHERE p.nombre LIKE :nombre", Proveedor.class);
		consulta.setParameter("nombre", "%" + nombre + "%");
		return (List<Proveedor>) consulta.getResultList();
	}
	
	public List <Proveedor> traerProveedorPorIdentificacion(String identificacion) {
		TypedQuery<Proveedor> consulta = entityManager.createQuery(
				"SELECT p FROM Proveedor p WHERE p.identificacion = :identificacion", Proveedor.class);
		consulta.setParameter("identificacion", identificacion);
		return (List<Proveedor>) consulta.getResultList();
	}
	
	public List <Proveedor> traerProveedorPorDepartamento(String departamento) {
		TypedQuery<Proveedor> consulta = entityManager.createQuery(
				"SELECT p FROM Proveedor p WHERE p.departamento = :departamento", Proveedor.class);
		consulta.setParameter("departamento" , departamento);
		return (List<Proveedor>) consulta.getResultList();
	}
	
	public List <Proveedor> traerProveedorPorCiudad(String ciudad) {
		TypedQuery<Proveedor> consulta = entityManager.createQuery(
				"SELECT p FROM Proveedor p WHERE p.ciudad = :ciudad", Proveedor.class);
		consulta.setParameter("ciudad" , ciudad);
		return (List<Proveedor>) consulta.getResultList();
	}
	
	public List <Proveedor> traerProveedorDeshabilitado(String estado) {
		TypedQuery<Proveedor> consulta = entityManager.createQuery(
				"SELECT p FROM Proveedor p WHERE p.estado = :estado", Proveedor.class);
		consulta.setParameter("estado" , estado);
		return (List<Proveedor>) consulta.getResultList();
	}
}
