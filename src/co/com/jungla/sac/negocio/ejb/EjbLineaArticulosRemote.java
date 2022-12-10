package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.LineaArticulos;

@Remote
public interface EjbLineaArticulosRemote {
	
	public void insertarLineaArticulos(LineaArticulos lineaArticulos);
	
	public void actualizarLineaArticulos(LineaArticulos lineaArticulos);
	
	public void eliminarLineaArticulos(LineaArticulos lineaArticulos);
	
	public List<LineaArticulos> listarLineaArticulos();
	
	public LineaArticulos traerLineaArticulo(String nombre );

}
