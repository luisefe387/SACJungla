package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbBancoRemote;
import co.com.jungla.sac.persistencia.entidades.Banco;

public class DelegadoBanco implements EjbBancoRemote{

	EjbBancoRemote ejbBancoRemote;

	public void insertarBanco(Banco banco) {
		ejbBancoRemote.insertarBanco(banco);
	}

	public void actualizarBanco(Banco banco) {
		ejbBancoRemote.actualizarBanco(banco);
	}

	public void eliminarBanco(Banco banco) {
		ejbBancoRemote.eliminarBanco(banco);
	}

	public List<Banco> listarBanco() {
		return ejbBancoRemote.listarBanco();
	}

	public DelegadoBanco() {
		
		try {
			ejbBancoRemote = (EjbBancoRemote) new InitialContext().lookup("java:global/SACJungla/EjbBanco!co.com.jungla.sac.negocio.ejb.EjbBancoRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}