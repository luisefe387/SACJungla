package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbLineaArticulosRemote;
import co.com.jungla.sac.persistencia.entidades.LineaArticulos;

public class DelegadoLineaArticulos implements EjbLineaArticulosRemote {

	EjbLineaArticulosRemote ejbLineaArticulosRemote;

	public void insertarLineaArticulos(LineaArticulos lineaArticulos) {
		ejbLineaArticulosRemote.insertarLineaArticulos(lineaArticulos);
	}

	public void actualizarLineaArticulos(LineaArticulos lineaArticulos) {
		ejbLineaArticulosRemote.actualizarLineaArticulos(lineaArticulos);
	}

	public void eliminarLineaArticulos(LineaArticulos lineaArticulos) {
		ejbLineaArticulosRemote.eliminarLineaArticulos(lineaArticulos);
	}

	public List<LineaArticulos> listarLineaArticulos() {
		return ejbLineaArticulosRemote.listarLineaArticulos();
	}
	
	public LineaArticulos traerLineaArticulo(String nombre) {
		return ejbLineaArticulosRemote.traerLineaArticulo(nombre);
	}

	public DelegadoLineaArticulos(){
		
		try {
			ejbLineaArticulosRemote = (EjbLineaArticulosRemote) new InitialContext().lookup("java:global/SACJungla/EjbLineaArticulos!co.com.jungla.sac.negocio.ejb.EjbLineaArticulosRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
}
