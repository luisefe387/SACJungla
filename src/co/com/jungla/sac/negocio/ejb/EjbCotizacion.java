package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoCotizacion;
import co.com.jungla.sac.persistencia.entidades.Cotizacion;

/**
 * clase ejb con todos los metodos que realizan las funcionalidades o reglas de negocio corresponidentes a la cotizacion  utilizando
 * el daoCotizacion 
 * @author Luis Fernando Pedroza T.
 * @version: 01/09/2016
 */
@Stateless
@LocalBean
public class EjbCotizacion implements EjbCotizacionRemote {

	/**
	 * Variable de tipo DaoCotizacion para  utilizar sus metodos en este ejb
	 */
	@EJB
	DaoCotizacion daoCotizacion;
	
	
    /**
     * Metodo constructor sin parametros
     */
    public EjbCotizacion() {
  
    }

    /*Metodos cargados en este ejb con sus funcionalidades necesarias */
	@Override
	public void insertarCotizacion(Cotizacion cotizacion) {
		daoCotizacion.insertar(cotizacion);
	}

	@Override
	public void actualizarCotizacion(Cotizacion cotizacion) {
		daoCotizacion.actualizar(cotizacion);
		
	}

	@Override
	public void eliminarCotizacion(Cotizacion cotizacion) {
		daoCotizacion.eliminar(cotizacion);
		
	}

	@Override
	public List<Cotizacion> traerUltimaCotizacion() {
		List<Cotizacion> cotizacion = daoCotizacion.traerUltimaCotizacion();
		return cotizacion;
	}

	@Override
	public List<Cotizacion> traerCotizacionPorCodigo(int idCotizacion) {
		List<Cotizacion> cotizacion = daoCotizacion.traerCotizacionPorCodigo(idCotizacion);
		return cotizacion;
	}
	
	@Override
	public List<Cotizacion> traerCotizacionesPorIdentCliente(String identificacionCliente) {
		List<Cotizacion> cotizacion = daoCotizacion.traerCotizacionesPorIdentCliente(identificacionCliente);
		return cotizacion;
	}

	@Override
	public List<Cotizacion> traerCotizacionesPorNombreCliente(String nombreCliente) {
		List<Cotizacion> cotizacion = daoCotizacion.traerCotizacionesPorNombreCliente(nombreCliente);
		return cotizacion;
	}

	@Override
	public List<Cotizacion> listarCotizaciones() {
		List<Cotizacion> Cotizacions = daoCotizacion.listarTodo();
		return Cotizacions;
	}

	@Override
	public Cotizacion encontrarPorLlave(String llave) {
		Cotizacion cotizacion = daoCotizacion.encontrarPorLlave(llave);
		return cotizacion;
	}
	
	
}
