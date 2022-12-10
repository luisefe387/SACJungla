package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbCotizacionRemote;
import co.com.jungla.sac.persistencia.entidades.Cotizacion;

/**
 * clase delegado con todos los metodos corresponidentes a la cotizacion para servir como fachada e implementar la interface remota
 * @author Luis Fernando Pedroza T.
 * @version: 01/09/2016
 */
public class DelegadoCotizacion implements EjbCotizacionRemote{

	/**
	 * Variable de tipo EjbCotizacionRemote para  utilizar sus metodos en este delegado
	 */
	EjbCotizacionRemote ejbCotizacionRemote;

	/*Metodos traidos desde la interface remota con sus funcionalidades necesarias */
	public void insertarCotizacion(Cotizacion cotizacion) {
		ejbCotizacionRemote.insertarCotizacion(cotizacion);
	}

	public void actualizarCotizacion(Cotizacion cotizacion) {
		ejbCotizacionRemote.actualizarCotizacion(cotizacion);
	}

	public void eliminarCotizacion(Cotizacion cotizacion) {
		ejbCotizacionRemote.eliminarCotizacion(cotizacion);
	}

	public List<Cotizacion> traerUltimaCotizacion() {
		return ejbCotizacionRemote.traerUltimaCotizacion();
	}

	public List<Cotizacion> traerCotizacionPorCodigo(int idCotizacion) {
		return ejbCotizacionRemote.traerCotizacionPorCodigo(idCotizacion);
	}

	
	public List<Cotizacion> traerCotizacionesPorIdentCliente(String identificacionCliente) {
		return ejbCotizacionRemote.traerCotizacionesPorIdentCliente(identificacionCliente);
	}

	public List<Cotizacion> traerCotizacionesPorNombreCliente(String nombreCliente) {
		return ejbCotizacionRemote.traerCotizacionesPorNombreCliente(nombreCliente);
	}

	public List<Cotizacion> listarCotizaciones() {
		return ejbCotizacionRemote.listarCotizaciones();
	}

	
	public Cotizacion encontrarPorLlave(String llave) {
		return ejbCotizacionRemote.encontrarPorLlave(llave);
	}

	/**
	 * Metodo constructor para obtener la instancia de su ejb
	 */
	public DelegadoCotizacion() {
		
		try {
			ejbCotizacionRemote = (EjbCotizacionRemote) new InitialContext().lookup("java:global/SACJungla/EjbCotizacion!co.com.jungla.sac.negocio.ejb.EjbCotizacionRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}