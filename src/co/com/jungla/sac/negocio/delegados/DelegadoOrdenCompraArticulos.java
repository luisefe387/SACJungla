package co.com.jungla.sac.negocio.delegados;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbOrdenCompraArticulosRemote;
import co.com.jungla.sac.persistencia.entidades.OrdenCompraArticulos;


public class DelegadoOrdenCompraArticulos implements EjbOrdenCompraArticulosRemote{
	
	EjbOrdenCompraArticulosRemote ejbOrdenCompraArticulosRemote;

	public void insertarOrdenCompraArticulos(OrdenCompraArticulos ordenCompraArticulos) {
		ejbOrdenCompraArticulosRemote.insertarOrdenCompraArticulos(ordenCompraArticulos);
	}

	public void actualizarOrdenCompraArticulos(OrdenCompraArticulos ordenCompraArticulos) {
		ejbOrdenCompraArticulosRemote.actualizarOrdenCompraArticulos(ordenCompraArticulos);
	}

	public void eliminarOrdenCompraArticulos( OrdenCompraArticulos ordenCompraArticulos) {
		ejbOrdenCompraArticulosRemote.eliminarOrdenCompraArticulos(ordenCompraArticulos);
	}

	public List<OrdenCompraArticulos> listarOrdenCompraArticulos() {
		return ejbOrdenCompraArticulosRemote.listarOrdenCompraArticulos();
	}

	public List<OrdenCompraArticulos> traerCodigoOrdenCompra() {
		return ejbOrdenCompraArticulosRemote.traerCodigoOrdenCompra();
	}


	public List<OrdenCompraArticulos> traerCodigoOrdenPorCompra(int idOrdenCompra) {
		return ejbOrdenCompraArticulosRemote.traerCodigoOrdenPorCompra(idOrdenCompra);
	}

	public List<OrdenCompraArticulos> reportarOrdenesCompra() {
		return ejbOrdenCompraArticulosRemote.reportarOrdenesCompra();
	}
	
	
	public List<OrdenCompraArticulos> reportarOrdenesPorPFEE(String nombre, Date inicioFechaE, Date finFechaE, String estado) {
		return ejbOrdenCompraArticulosRemote.reportarOrdenesPorPFEE(nombre, inicioFechaE, finFechaE, estado);
	}

	
	public List<OrdenCompraArticulos> reportarOrdenesPorFE(Date inicioFechaE, Date finFechaE) {
		return ejbOrdenCompraArticulosRemote.reportarOrdenesPorFE(inicioFechaE, finFechaE);
	}
	
	
	public List<OrdenCompraArticulos> reportarOrdenesPorPFE(String nombre, Date inicioFechaE, Date finFechaE) {
		return ejbOrdenCompraArticulosRemote.reportarOrdenesPorPFE(nombre, inicioFechaE, finFechaE);
	}
	
	

	public List<OrdenCompraArticulos> reportarOrdenesPorFEE(Date inicioFechaE,
			Date finFechaE, String estado) {
		return ejbOrdenCompraArticulosRemote.reportarOrdenesPorFEE(
				inicioFechaE, finFechaE, estado);
	}

	public DelegadoOrdenCompraArticulos() {
		try {
			ejbOrdenCompraArticulosRemote = (EjbOrdenCompraArticulosRemote) new InitialContext().lookup("java:global/SACJungla/EjbOrdenCompraArticulos!co.com.jungla.sac.negocio.ejb.EjbOrdenCompraArticulosRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
