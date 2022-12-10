package co.com.jungla.sac.persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.MedioPagoCliente;

/**
 * clase dao con los metodos que se necesitan para gestionar los medios de pago que utilizan los clientes
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoMedioPagoCliente extends DaoGeneral<MedioPagoCliente,String> {

    /**
     * Metodo constructor sin parametros. 
     */
    public DaoMedioPagoCliente() {
        
    }

    /* 
   	 * Metodo que permite retornar la clase MedioPagoCliente
   	 */
	@Override
	protected Class<MedioPagoCliente> getEntityClass() {
		return MedioPagoCliente.class;
	}
	
	/**
	 * Metodo para listar los medios de pago mediante el codigo recibo de caja
	 * @param idReciboCaja, parametro que define el codigo del recibo de caja
	 * @return devuelve una lista de los medios de pago encontrado por el codigo del recibo de caja
	 */
	public List <MedioPagoCliente> listarMedioPagosPorCodigoReciboCaja(int idReciboCaja) {
		TypedQuery<MedioPagoCliente> consulta = entityManager.createQuery(
				"SELECT mp FROM MedioPagoCliente mp JOIN mp.ReciboCaja rc WHERE rc.idReciboCaja =:idReciboCaja",MedioPagoCliente.class);
		consulta.setParameter("idReciboCaja", idReciboCaja);
		List<MedioPagoCliente> lista=(List<MedioPagoCliente>)consulta.getResultList( );
		return lista;
	}

}
