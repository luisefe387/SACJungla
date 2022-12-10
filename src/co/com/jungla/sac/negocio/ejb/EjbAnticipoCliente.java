package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoAnticipoCliente;
import co.com.jungla.sac.persistencia.entidades.AnticipoCliente;

/**
 * Session Bean implementation class EjbCompraArticulos
 */
@Stateless
@LocalBean
public class EjbAnticipoCliente implements EjbAnticipoClienteRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoAnticipoCliente daoAnticipoCliente;
	
    public EjbAnticipoCliente() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarAnticipoCliente(AnticipoCliente anticipoCliente) {
		daoAnticipoCliente.insertar(anticipoCliente);
	}

	@Override
	public void actualizarAnticipoCliente(AnticipoCliente anticipoCliente) {
		daoAnticipoCliente.actualizar(anticipoCliente);
	}

	@Override
	public void eliminarAnticipoCliente(AnticipoCliente anticipoCliente) {
		daoAnticipoCliente.eliminar(anticipoCliente);
	}

	@Override
	public List<AnticipoCliente> traerUltimoAnticipoCliente() {
		List<AnticipoCliente> anticipoCliente = daoAnticipoCliente.traerUltimoAnticipoCliente();
		return anticipoCliente;
	}

	@Override
	public List<AnticipoCliente> listarAnticipoClientePorIdentificacion(String identificacion) {
		List<AnticipoCliente> anticipoCliente = daoAnticipoCliente.listarAnticipoClientePorIdentificacion(identificacion);
		return anticipoCliente;
	}

	@Override
	public List<AnticipoCliente> traerAnticipoClientePorCodigo(int idAnticipoCliente) {
		List<AnticipoCliente> anticipoCliente = daoAnticipoCliente.traerAnticipoClientePorCodigo(idAnticipoCliente);
		return anticipoCliente;
	}
	
	/*@Override
	public List<AnticipoCliente> listarAnticipoClientePorIdentificacion(String identificacion) {
		List<AnticipoCliente> AnticipoCliente = daoAnticipoCliente.listarAnticipoClientePorIdentificacion(identificacion);
		return AnticipoCliente;
	}

	@Override
	public List<AnticipoCliente> traerAnticipoClientePorCodigo(int idAnticipoCliente) {
		List<AnticipoCliente> AnticipoCliente = daoAnticipoCliente.traerAnticipoClientePorCodigo(idAnticipoCliente);
		return AnticipoCliente;
	}

	@Override
	public List<AnticipoCliente> reportarAnticiposPorF(Date inicioFechaE,Date finFechaE) {
		List<AnticipoCliente> AnticipoCliente = daoAnticipoCliente.reportarAnticiposPorF(inicioFechaE, finFechaE);
		return AnticipoCliente;
	}

	@Override
	public List<AnticipoCliente> reportarAnticiposPorPF(Date inicioFechaE,Date finFechaE, String nombre) {
		List<AnticipoCliente> AnticipoCliente = daoAnticipoCliente.reportarAnticiposPorPF(inicioFechaE, finFechaE, nombre);
		return AnticipoCliente;
	}

	@Override
	public List<AnticipoCliente> reportarAnticiposPorEF(Date inicioFechaE,Date finFechaE, String estadoAnticipo) {
		List<AnticipoCliente> AnticipoCliente = daoAnticipoCliente.reportarAnticiposPorEF(inicioFechaE, finFechaE, estadoAnticipo);
		return AnticipoCliente;
	}

	@Override
	public List<AnticipoCliente> reportarAnticiposPorPEF(Date inicioFechaE,Date finFechaE, String nombre, String estadoAnticipo) {
		List<AnticipoCliente> AnticipoCliente = daoAnticipoCliente.reportarAnticiposPorPEF(inicioFechaE, finFechaE, nombre, estadoAnticipo);
		return AnticipoCliente;
	}*/
}
