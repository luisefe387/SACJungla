package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.Banco;

@Remote
public interface EjbBancoRemote {

	public void insertarBanco(Banco banco);
	
	public void actualizarBanco(Banco banco);
	
	public void eliminarBanco(Banco banco);
	
	public List<Banco> listarBanco();
}
