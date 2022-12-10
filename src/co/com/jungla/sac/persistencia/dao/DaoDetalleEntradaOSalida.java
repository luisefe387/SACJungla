package co.com.jungla.sac.persistencia.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import co.com.jungla.sac.persistencia.entidades.DetalleEntradaOSalida;

/**
 * clase dao con los metodos que se necesitan para gestionar el detalle de la entrada o salida
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoDetalleEntradaOSalida extends DaoGeneral<DetalleEntradaOSalida,String>{

    /**
     * Metodo constructor sin parametros.  
     */
    public DaoDetalleEntradaOSalida() {
       
    }

    /* 
   	 * Metodo que permite retornar la clase DetalleEntradaOSalida
   	 */
	@Override
	protected Class<DetalleEntradaOSalida> getEntityClass() {
		return DetalleEntradaOSalida.class;
	}
	
	/**
	 * Metodo para traer el ultimo registro del detalle de la entrada o salida de los articulos
	 * @return un objeto de tipo DetalleEntradaOSalida con la informacion del ultimo registro del detalle
	 */
	public DetalleEntradaOSalida traerUltimoDetalleEntradaOSalida() {
		try{
			Query consulta = entityManager.createQuery(
					"SELECT dt FROM DetalleEntradaOSalida dt order by dt.idDetalleEntradaOSalida desc",
					DetalleEntradaOSalida.class);
			consulta.setMaxResults(1);
			return (DetalleEntradaOSalida) consulta.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

}
