package co.com.jungla.sac.persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.PagoAbonoCxP;

/**
 * clase dao con los metodos que se necesitan para gestionar los abonos a cuentas por pagar
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Stateless
@LocalBean
public class DaoPagoAbonoCxP extends DaoGeneral<PagoAbonoCxP,String>{

    /**
     * Metodo constructor sin parametros
     */
    public DaoPagoAbonoCxP() {
    }

    /* 
   	 * Metodo que permite retornar la clase PagoAbonoCxP
   	 */
	@Override
	protected Class<PagoAbonoCxP> getEntityClass() {
		return PagoAbonoCxP.class;
	}
	 
	/**
	 * Metodo que con una consulta permite traer el ultimo dato registrado del pago abono
	 * @return una lista con la informacion del ultimo abono
	 */
	public List <PagoAbonoCxP> traerCodigoPagoAbonoCxP() {
		TypedQuery<PagoAbonoCxP> consulta = entityManager.createQuery(
				"SELECT pa FROM PagoAbonoCxP pa order by pa.idAbono desc", PagoAbonoCxP.class);
		return (List<PagoAbonoCxP>) consulta.getResultList();
	}
	
	/**
	 * Metodo para para listar los abonos a cuentas por pagar mediante el codigo de la cuenta por pagar
	 * @param idCuentaXPagar, parametro que define el numero de cuenta por pagar
	 * @return devuelve una lista con la informacion del abono encontrado con el codigo de la cuenta por pagar
	 */
	public List <PagoAbonoCxP> listarPagoAbonoCxPPorCodigoCxP(int idCuentaXPagar) {
		TypedQuery<PagoAbonoCxP> consulta = entityManager.createQuery(
				"SELECT pa FROM PagoAbonoCxP pa JOIN pa.cuentaXPagar c WHERE c.idCuentaXPagar =:idCuentaXPagar",PagoAbonoCxP.class);
		consulta.setParameter("idCuentaXPagar", idCuentaXPagar);
		List<PagoAbonoCxP> lista=(List<PagoAbonoCxP>)consulta.getResultList( );
		return lista;
	}
}
