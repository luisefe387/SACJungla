package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.Contabilizacion;

@Remote
public interface EjbContabilizacionRemote {

	public void insertarContabilizacion(Contabilizacion contabilizacion);
	
	public void actualizarContabilizacion(Contabilizacion contabilizacion);
	
	public void eliminarContabilizacion(Contabilizacion contabilizacion);
	
	public List<Contabilizacion> listarContabilizacion();
}
