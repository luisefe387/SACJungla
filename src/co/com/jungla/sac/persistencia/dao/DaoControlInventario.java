package co.com.jungla.sac.persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.ControlInventario;

/**
 * clase dao con los metodos necesarios para gestionar el control inventario
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoControlInventario extends DaoGeneral<ControlInventario,String> {

    /**
     *  Metodo constructor sin parametros 
     */
    public DaoControlInventario() {
    }

    /* 
   	 * Metodo que permite retornar la clase ControlInventario
   	 */
	@Override
	protected Class<ControlInventario> getEntityClass() {
		return ControlInventario.class;
	}
	
	public List <ControlInventario> traerContInventarioPorCodigoArticulo(int codigo ) {
		TypedQuery<ControlInventario> consulta = entityManager.createQuery(
				"SELECT ci FROM ControlInventario ci JOIN ci.articulo a WHERE a.codigo = :codigo",
				ControlInventario.class);
		consulta.setParameter("codigo", codigo);
		return (List<ControlInventario>) consulta.getResultList();
	}
	
	//Metodo para traer todos los registros del inventario por proveedor ultima compra
	public List <ControlInventario> traerRegistrosInventarioPorP(String proveedorUltimaCompra ) {
		TypedQuery<ControlInventario> consulta = entityManager.createQuery(
				"SELECT ci FROM ControlInventario ci JOIN ci.articulo a JOIN a.lineaArticulos l WHERE ci.proveedorUltimaCompra =:proveedorUltimaCompra",ControlInventario.class);
		consulta.setParameter("proveedorUltimaCompra", proveedorUltimaCompra);
		List<ControlInventario> lista=(List<ControlInventario>)consulta.getResultList( );
		return lista;
	}
	
	//Metodo para traer todos los registros del inventario por linea de articulos y proveedor ultima compra
	public List <ControlInventario> traerRegistrosInventarioPorGP(String nombreL, String proveedorUltimaCompra ) {
		TypedQuery<ControlInventario> consulta = entityManager.createQuery(
				"SELECT ci FROM ControlInventario ci JOIN ci.articulo a JOIN a.lineaArticulos l WHERE l.nombreL =:nombreL AND ci.proveedorUltimaCompra =:proveedorUltimaCompra",ControlInventario.class);
		consulta.setParameter("proveedorUltimaCompra", proveedorUltimaCompra);
		consulta.setParameter("nombreL", nombreL);
		List<ControlInventario> lista=(List<ControlInventario>)consulta.getResultList( );
		return lista;
	}
		
	//Metodo para traer todos los registros del inventario por estado de los articulos
	public List <ControlInventario> traerRegistrosInventarioPorE(String estadoArticulo) {
		TypedQuery<ControlInventario> consulta = entityManager.createQuery(
				"SELECT ci FROM ControlInventario ci JOIN ci.articulo a WHERE "+estadoArticulo,ControlInventario.class);
		//consulta.setParameter("estado", estado);
		List<ControlInventario> lista=(List<ControlInventario>)consulta.getResultList( );
		return lista;
	}

	//Metodo para traer todos los registros del inventario por estado de los articulos y linea de articulos
	public List <ControlInventario> traerRegistrosInventarioPorEG(String estadoArticulo, String nombreL ) {
		TypedQuery<ControlInventario> consulta = entityManager.createQuery(
				"SELECT ci FROM ControlInventario ci JOIN ci.articulo a JOIN a.lineaArticulos l WHERE l.nombreL =:nombreL AND "+estadoArticulo,ControlInventario.class);
		consulta.setParameter("nombreL", nombreL);
		List<ControlInventario> lista=(List<ControlInventario>)consulta.getResultList( );
		return lista;
	}
	
	//Metodo para traer todos los registros del inventario por linea de articulos
	public List <ControlInventario> traerRegistrosInventarioPorG(String nombreL) {
		TypedQuery<ControlInventario> consulta = entityManager.createQuery(
				"SELECT ci FROM ControlInventario ci JOIN ci.articulo a JOIN a.lineaArticulos l WHERE l.nombreL =:nombreL",ControlInventario.class);
		consulta.setParameter("nombreL", nombreL);
		List<ControlInventario> lista=(List<ControlInventario>)consulta.getResultList( );
		return lista;
	}
	
	//Metodo para traer todos los registros del inventario por estado de los articulos ,linea de articulos y proveedor ultima compra
	public List <ControlInventario> traerRegistrosInventarioPorEGP(String estadoArticulo, String nombreL, String proveedorUltimaCompra ) {
		TypedQuery<ControlInventario> consulta = entityManager.createQuery(
				"SELECT ci FROM ControlInventario ci JOIN ci.articulo a JOIN a.lineaArticulos l WHERE l.nombreL =:nombreL AND ci.proveedorUltimaCompra =:proveedorUltimaCompra AND "+estadoArticulo,ControlInventario.class);
		consulta.setParameter("nombreL", nombreL);
		consulta.setParameter("proveedorUltimaCompra", proveedorUltimaCompra);
		List<ControlInventario> lista=(List<ControlInventario>)consulta.getResultList( );
		return lista;
	}
	
	//Metodo para traer todos los registros del inventario por estado de los articulos y proveedor ultima compra
	public List <ControlInventario> traerRegistrosInventarioPorEP(String estadoArticulo, String proveedorUltimaCompra) {
		TypedQuery<ControlInventario> consulta = entityManager.createQuery(
				"SELECT ci FROM ControlInventario ci JOIN ci.articulo a JOIN a.lineaArticulos l WHERE ci.proveedorUltimaCompra =:proveedorUltimaCompra AND "+estadoArticulo,ControlInventario.class);
		consulta.setParameter("proveedorUltimaCompra", proveedorUltimaCompra);
		List<ControlInventario> lista=(List<ControlInventario>)consulta.getResultList( );
		return lista;
	}
	
	//Metodo para traer todos los registros del inventario por busqueda rapida basado en las coincidencias de nombre articulo
	public List <ControlInventario> traerRegistrosInventarioPorB(String nombre) {
		TypedQuery<ControlInventario> consulta = entityManager.createQuery(
				"SELECT ci FROM ControlInventario ci JOIN ci.articulo a JOIN a.lineaArticulos l WHERE a.nombre LIKE :nombre",ControlInventario.class);
		consulta.setParameter("nombre", "%" + nombre + "%");
		List<ControlInventario> lista=(List<ControlInventario>)consulta.getResultList( );
		return lista;
	}
	
	//Metodo para traer todos los registros del inventario por busqueda rapida basado en las coincidencias de nombre articulo y proveedor ultima compra
	public List <ControlInventario> traerRegistrosInventarioPorBP(String nombre, String proveedorUltimaCompra) {
		TypedQuery<ControlInventario> consulta = entityManager.createQuery(
				"SELECT ci FROM ControlInventario ci JOIN ci.articulo a JOIN a.lineaArticulos l WHERE a.nombre LIKE :nombre AND ci.proveedorUltimaCompra =:proveedorUltimaCompra",ControlInventario.class);
		consulta.setParameter("nombre", "%" + nombre + "%");
		consulta.setParameter("proveedorUltimaCompra", proveedorUltimaCompra);
		List<ControlInventario> lista=(List<ControlInventario>)consulta.getResultList( );
		return lista;
	}
	
	//Metodo para traer todos los registros del inventario por busqueda rapida basado en las coincidencias de nombre articulo, proveedor ultima compra y nombre de linea de articulos
	public List <ControlInventario> traerRegistrosInventarioPorBGP(String nombre, String nombreL, String proveedorUltimaCompra) {
		TypedQuery<ControlInventario> consulta = entityManager.createQuery(
				"SELECT ci FROM ControlInventario ci JOIN ci.articulo a JOIN a.lineaArticulos l WHERE a.nombre LIKE :nombre AND l.nombreL =:nombreL AND ci.proveedorUltimaCompra =:proveedorUltimaCompra",ControlInventario.class);
		consulta.setParameter("nombre", "%" + nombre + "%");
		consulta.setParameter("proveedorUltimaCompra", proveedorUltimaCompra);
		consulta.setParameter("nombreL", nombreL);
		List<ControlInventario> lista=(List<ControlInventario>)consulta.getResultList( );
		return lista;
	}
	
	//Metodo para traer todos los registros del inventario por busqueda rapida basado en las coincidencias de nombre articulo y estado de articulos
	public List <ControlInventario> traerRegistrosInventarioPorBE(String nombre, String estadoArticulo) {
		TypedQuery<ControlInventario> consulta = entityManager.createQuery(
				"SELECT ci FROM ControlInventario ci JOIN ci.articulo a JOIN a.lineaArticulos l WHERE a.nombre LIKE :nombre AND "+estadoArticulo,ControlInventario.class);
		consulta.setParameter("nombre", "%" + nombre + "%");
		List<ControlInventario> lista=(List<ControlInventario>)consulta.getResultList( );
		return lista;
	}
	
	//Metodo para traer todos los registros del inventario por busqueda rapida basado en las coincidencias de nombre articulo y estado de articulos
	public List <ControlInventario> traerRegistrosInventarioPorBEG(String nombre, String estadoArticulo, String nombreL) {
		TypedQuery<ControlInventario> consulta = entityManager.createQuery(
				"SELECT ci FROM ControlInventario ci JOIN ci.articulo a JOIN a.lineaArticulos l WHERE a.nombre LIKE :nombre AND l.nombreL =:nombreL AND "+estadoArticulo,ControlInventario.class);
		consulta.setParameter("nombre", "%" + nombre + "%");
		consulta.setParameter("nombreL", nombreL);
		List<ControlInventario> lista=(List<ControlInventario>)consulta.getResultList( );
		return lista;
	}
	
	//Metodo para traer todos los registros del inventario por busqueda rapida basado en las coincidencias de nombre de linea
	public List <ControlInventario> traerRegistrosInventarioPorBG(String nombre, String nombreL) {
		TypedQuery<ControlInventario> consulta = entityManager.createQuery(
				"SELECT ci FROM ControlInventario ci JOIN ci.articulo a JOIN a.lineaArticulos l WHERE a.nombre LIKE :nombre AND l.nombreL =:nombreL",ControlInventario.class);
		consulta.setParameter("nombre", "%" + nombre + "%");
		consulta.setParameter("nombreL", nombreL);
		List<ControlInventario> lista=(List<ControlInventario>)consulta.getResultList( );
		return lista;
	}
		
	//Metodo para traer todos los registros del inventario por busqueda rapida basado en las coincidencias de nombre articulo, estado de articulos, linea y proveedor ultima compra
	public List <ControlInventario> traerRegistrosInventarioPorBEGP(String nombre, String estadoArticulo, String nombreL, String proveedorUltimaCompra) {
		TypedQuery<ControlInventario> consulta = entityManager.createQuery(
				"SELECT ci FROM ControlInventario ci JOIN ci.articulo a JOIN a.lineaArticulos l WHERE a.nombre LIKE :nombre AND l.nombreL =:nombreL AND ci.proveedorUltimaCompra =:proveedorUltimaCompra AND "+estadoArticulo,ControlInventario.class);
		consulta.setParameter("nombre", "%" + nombre + "%");
		consulta.setParameter("nombreL", nombreL);
		consulta.setParameter("proveedorUltimaCompra", proveedorUltimaCompra);
		List<ControlInventario> lista=(List<ControlInventario>)consulta.getResultList( );
		return lista;
	}
	
	//Metodo para traer todos los registros del inventario por busqueda rapida basado en las coincidencias de nombre articulo, estado de articulos y proveedor ultima compra
	public List <ControlInventario> traerRegistrosInventarioPorBEP(String nombre, String estadoArticulo, String proveedorUltimaCompra) {
		TypedQuery<ControlInventario> consulta = entityManager.createQuery(
				"SELECT ci FROM ControlInventario ci JOIN ci.articulo a JOIN a.lineaArticulos l WHERE a.nombre LIKE :nombre AND ci.proveedorUltimaCompra =:proveedorUltimaCompra AND "+estadoArticulo,ControlInventario.class);
		consulta.setParameter("nombre", "%" + nombre + "%");
		consulta.setParameter("proveedorUltimaCompra", proveedorUltimaCompra);
		List<ControlInventario> lista=(List<ControlInventario>)consulta.getResultList( );
		return lista;
	}
	
	//Metodo para traer todos los registros del inventario por busqueda rapida basado en las coincidencias de nombre articulo, estado de articulos y proveedor ultima compra
	public List <ControlInventario> traerRegistrosInventarioConExistencias() {
		TypedQuery<ControlInventario> consulta = entityManager.createQuery(
				"SELECT ci FROM ControlInventario ci JOIN ci.articulo a JOIN a.lineaArticulos l WHERE ci.cantExistencia <> 0 ",ControlInventario.class);
		List<ControlInventario> lista=(List<ControlInventario>)consulta.getResultList( );
		return lista;
	}
	
	//Metodo para traer los articulos en la que sus existencias son inferiores a la cantidad definida con una alerta de escasez
	public List <ControlInventario> traerRegistrosInventarioCantAlerta() {
		TypedQuery<ControlInventario> consulta = entityManager.createQuery(
				"SELECT ci FROM ControlInventario ci JOIN ci.articulo a JOIN a.lineaArticulos l JOIN a.unidadMedida u WHERE ci.cantExistencia <= ci.cantAlerta ",ControlInventario.class);
		List<ControlInventario> lista=(List<ControlInventario>)consulta.getResultList( );
		return lista;
	}
}
