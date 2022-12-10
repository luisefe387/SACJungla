package co.com.jungla.sac.persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.FormaPagoCliente;

/**
 * clase dao con los metodos que se necesitan para gestionar las formas de pago cliente
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoFormaPagoCliente extends DaoGeneral<FormaPagoCliente,String> {

	/**
     * Metodo constructor sin parametros 
     */
    public DaoFormaPagoCliente() {
    
    }

    /* 
   	 * Metodo que permite retornar la clase Banco
   	 */
	@Override
	protected Class<FormaPagoCliente> getEntityClass() {
		return FormaPagoCliente.class;
	}
	
	/**
	 * Metodo para traer la forma de pago utilizada por el cliente mediante la descripcion de la misma
	 * @param descripcion, parametro que define la descripcion de la forma de pago utilzada por el cliente
	 * @return una lista con la informacion de la forma de pago elegida
	 */
	public List <FormaPagoCliente> traerFormaPagoClientePorDescripcion(String descripcion) {
		TypedQuery<FormaPagoCliente> consulta = entityManager.createQuery(
				"SELECT f FROM FormaPagoCliente f WHERE f.descripcion =:descripcion", FormaPagoCliente.class);
		consulta.setParameter("descripcion", descripcion);
		return (List <FormaPagoCliente>) consulta.getResultList();
	}
	
	/**
	 * Metodo para traer la forma de pago utilizada por el cliente mediante el codigo de la forma de pago
	 * @param idFormaPagoCliente, parametro que define el numero de forma de pago
	 * @return una lista de tipo FormaPagoCliente en donde esta la infomracion de la forma de pago encontrado
	 */
	public List <FormaPagoCliente> traerFormaPagoClientePorIdFormaPago(int idFormaPagoCliente) {
		TypedQuery<FormaPagoCliente> consulta = entityManager.createQuery(
				"SELECT f FROM FormaPagoCliente f WHERE f.idFormaPagoCliente =:idFormaPagoCliente", FormaPagoCliente.class);
		consulta.setParameter("idFormaPagoCliente", idFormaPagoCliente);
		return (List <FormaPagoCliente>) consulta.getResultList();
	}

}
