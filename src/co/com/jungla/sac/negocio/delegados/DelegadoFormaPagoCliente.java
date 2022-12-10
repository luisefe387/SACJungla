package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbFormaPagoClienteRemote;
import co.com.jungla.sac.persistencia.entidades.FormaPagoCliente;

public class DelegadoFormaPagoCliente implements EjbFormaPagoClienteRemote{

	EjbFormaPagoClienteRemote ejbFormaPagoClienteRemote;

	public void insertarFormaPagoCliente(FormaPagoCliente formaPagoCliente) {
		ejbFormaPagoClienteRemote.insertarFormaPagoCliente(formaPagoCliente);
	}

	public void actualizarFormaPagoCliente(FormaPagoCliente formaPagoCliente) {
		ejbFormaPagoClienteRemote.actualizarFormaPagoCliente(formaPagoCliente);
	}

	public void eliminarFormaPagoCliente(FormaPagoCliente formaPagoCliente) {
		ejbFormaPagoClienteRemote.eliminarFormaPagoCliente(formaPagoCliente);
	}

	public List<FormaPagoCliente> listarFormaPagoCliente() {
		return ejbFormaPagoClienteRemote.listarFormaPagoCliente();
	}

	public List<FormaPagoCliente> traerFormaPagoClientePorDescripcion(String descripcion) {
		return ejbFormaPagoClienteRemote.traerFormaPagoClientePorDescripcion(descripcion);
	}

	public List<FormaPagoCliente> traerFormaPagoClientePorIdFormaPago(int idFormaPagoCliente) {
		return ejbFormaPagoClienteRemote.traerFormaPagoClientePorIdFormaPago(idFormaPagoCliente);
	}

	public DelegadoFormaPagoCliente() {
		
		try {
			ejbFormaPagoClienteRemote = (EjbFormaPagoClienteRemote) new InitialContext().lookup("java:global/SACJungla/EjbFormaPagoCliente!co.com.jungla.sac.negocio.ejb.EjbFormaPagoClienteRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}