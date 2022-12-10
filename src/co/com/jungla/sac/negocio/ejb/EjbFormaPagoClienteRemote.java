package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.FormaPagoCliente;

@Remote
public interface EjbFormaPagoClienteRemote {

	public void insertarFormaPagoCliente(FormaPagoCliente formaPagoCliente);
	
	public void actualizarFormaPagoCliente(FormaPagoCliente formaPagoCliente);
	
	public void eliminarFormaPagoCliente(FormaPagoCliente formaPagoCliente);
	
	public List<FormaPagoCliente> listarFormaPagoCliente();
	
	public List <FormaPagoCliente> traerFormaPagoClientePorDescripcion(String descripcion);
	
	public List <FormaPagoCliente> traerFormaPagoClientePorIdFormaPago(int idFormaPagoCliente);
}
