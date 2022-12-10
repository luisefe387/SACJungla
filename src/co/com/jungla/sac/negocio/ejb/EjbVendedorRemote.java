package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.Vendedor;

@Remote
public interface EjbVendedorRemote {

	public void insertarVendedor(Vendedor vendedor);
	
	public void actualizarVendedor(Vendedor vendedor);
	
	public void eliminarVendedor(Vendedor vendedor);
	
	public List<Vendedor> listarVendedor();
	
	public Vendedor encontrarPorVendedor(String identificacion);
}
