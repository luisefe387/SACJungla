package co.com.jungla.sac.persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import co.com.jungla.sac.persistencia.entidades.AlertaVencimiento;


@Stateless
@LocalBean
public class DaoAlertaVencimiento extends DaoGeneral<AlertaVencimiento,String>{

    public DaoAlertaVencimiento() {
    
    }

	@Override
	protected Class<AlertaVencimiento> getEntityClass() {
		return AlertaVencimiento.class;
	}
	
	//Consulta que permite traer el ultimo dato registrado del anticipo cliente
	public List <AlertaVencimiento> traerUltimaAlertaVencimiento() {
		TypedQuery<AlertaVencimiento> consulta = entityManager.createQuery(
				"SELECT av FROM AlertaVencimiento av order by av.idAlertaVencimiento desc", AlertaVencimiento.class);
		return (List<AlertaVencimiento>) consulta.getResultList();
	}
	
	//Consulta  para listar los anticipos a partir de la identificacion del cliente
	public List <AlertaVencimiento> listarAlertaVencimientoPorIdAlerta(int idAlerta) {
		TypedQuery<AlertaVencimiento> consulta = entityManager.createQuery(
				"SELECT av FROM AlertaVencimiento av WHERE av.idAlerta =:idAlerta", AlertaVencimiento.class);
		consulta.setParameter("identificacion", idAlerta);
		return (List<AlertaVencimiento>) consulta.getResultList();
	}
	
	/**
	 * Metodo para encontrar los articulos que se venceran en determinado dias
	 * @param inicioFechaE, parametro que define la fecha inicial
	 * @param finFechaE, parametro que define la fecha final
	 * @return
	 */
	public List <AlertaVencimiento> traerArticulosAVencerse(Date inicioFechaE, Date finFechaE) {
		TypedQuery<AlertaVencimiento> consulta = entityManager.createQuery(
				"SELECT av FROM AlertaVencimiento av JOIN av.articulo a JOIN a.controlInventario ci WHERE av.fechaVencimiento BETWEEN :inicioFechaE AND :finFechaE",AlertaVencimiento.class);
		consulta.setParameter("inicioFechaE", inicioFechaE,TemporalType.DATE);
		consulta.setParameter("finFechaE", finFechaE,TemporalType.DATE);
		return (List<AlertaVencimiento>) consulta.getResultList();
	}
}
