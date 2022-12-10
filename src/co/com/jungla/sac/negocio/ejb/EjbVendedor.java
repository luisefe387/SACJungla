package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoVendedor;
import co.com.jungla.sac.persistencia.entidades.Vendedor;

/**
 * Session Bean implementation class EjbVendedor
 */
@Stateless
@LocalBean
public class EjbVendedor implements EjbVendedorRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoVendedor daoVendedor;
	
    public EjbVendedor() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarVendedor(Vendedor Vendedor) {
		daoVendedor.insertar(Vendedor);
	}

	@Override
	public void actualizarVendedor(Vendedor Vendedor) {
		daoVendedor.actualizar(Vendedor);
		
	}

	@Override
	public void eliminarVendedor(Vendedor Vendedor) {
		daoVendedor.eliminar(Vendedor);
		
	}

	@Override
	public List<Vendedor> listarVendedor() {
		List<Vendedor> Vendedor = daoVendedor.listarTodo();
		return Vendedor;
	}

	@Override
	public Vendedor encontrarPorVendedor(String identificacion) {
		Vendedor vendedor = daoVendedor.encontrarPorLlave(identificacion);
		return vendedor;
	}
	
	

}
