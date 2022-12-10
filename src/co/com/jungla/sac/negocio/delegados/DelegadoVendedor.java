package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbVendedorRemote;
import co.com.jungla.sac.persistencia.entidades.Vendedor;

public class DelegadoVendedor implements EjbVendedorRemote{

	EjbVendedorRemote ejbVendedorRemote;

	public void insertarVendedor(Vendedor vendedor) {
		ejbVendedorRemote.insertarVendedor(vendedor);
	}

	public void actualizarVendedor(Vendedor vendedor) {
		ejbVendedorRemote.actualizarVendedor(vendedor);
	}

	public void eliminarVendedor(Vendedor vendedor) {
		ejbVendedorRemote.eliminarVendedor(vendedor);
	}

	public List<Vendedor> listarVendedor() {
		return ejbVendedorRemote.listarVendedor();
	}

	public Vendedor encontrarPorVendedor(String identificacion) {
		return ejbVendedorRemote.encontrarPorVendedor(identificacion);
	}

	public DelegadoVendedor() {
		
		try {
			ejbVendedorRemote = (EjbVendedorRemote) new InitialContext().lookup("java:global/SACJungla/EjbVendedor!co.com.jungla.sac.negocio.ejb.EjbVendedorRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}