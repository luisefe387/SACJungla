package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoBanco;
import co.com.jungla.sac.persistencia.entidades.Banco;

/**
 * Session Bean implementation class EjbBanco
 */
@Stateless
@LocalBean
public class EjbBanco implements EjbBancoRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoBanco daoBanco;
	
    public EjbBanco() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarBanco(Banco banco) {
		daoBanco.insertar(banco);
	}

	@Override
	public void actualizarBanco(Banco banco) {
		daoBanco.actualizar(banco);
		
	}

	@Override
	public void eliminarBanco(Banco banco) {
		daoBanco.eliminar(banco);
		
	}

	@Override
	public List<Banco> listarBanco() {
		List<Banco> bancos = daoBanco.listarTodo();
		return bancos;
	}

}
