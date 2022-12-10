package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.Cotizacion;

/**
 * interface ejb remote con todos los metodos que realizan las consultas corresponidentes a la cotizacion y que serviran para acceder
 * al Ejb de forma remota  
 * @author Luis Fernando Pedroza T.
 * @version: 01/09/2016
 */
@Remote
public interface EjbCotizacionRemote {

	/*lista de metodos de la interface que implementara para las funcionalidad de la cotizacion*/
	public void insertarCotizacion(Cotizacion cotizacion);
	
	public void actualizarCotizacion(Cotizacion cotizacion);
	
	public void eliminarCotizacion(Cotizacion cotizacion);
	
	public List<Cotizacion> traerUltimaCotizacion();
	
	public List<Cotizacion> traerCotizacionPorCodigo(int idCotizacion);
	
	public List <Cotizacion> traerCotizacionesPorIdentCliente(String identificacionCliente);
	
	public List <Cotizacion> traerCotizacionesPorNombreCliente(String nombreCliente);
	
	public List<Cotizacion> listarCotizaciones();
	
	public Cotizacion encontrarPorLlave(String llave);
}
