package co.com.jungla.sac.negocio.delegados;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbCompraArticulosRemote;
import co.com.jungla.sac.persistencia.entidades.CompraArticulos;

public class DelegadoCompraArticulos implements EjbCompraArticulosRemote{
	
	EjbCompraArticulosRemote ejbCompraArticulosRemote;

	public void insertarCompraArticulos(CompraArticulos compraArticulos) {
		ejbCompraArticulosRemote.insertarCompraArticulos(compraArticulos);
	}

	public void actualizarCompraArticulos(CompraArticulos compraArticulos) {
		ejbCompraArticulosRemote.actualizarCompraArticulos(compraArticulos);
	}

	public void eliminarCompraArticulos(CompraArticulos compraArticulos) {
		ejbCompraArticulosRemote.eliminarCompraArticulos(compraArticulos);
	}

	public List<CompraArticulos> listarCompraArticulos() {
		return ejbCompraArticulosRemote.listarCompraArticulos();
	}
	
	public List<CompraArticulos> traerCodigoCompra() {
		return ejbCompraArticulosRemote.traerCodigoCompra();
	}
	
	public List<CompraArticulos> traerCompraPorFactProv(int factProv) {
		return ejbCompraArticulosRemote.traerCompraPorFactProv(factProv);
	}

	public List<CompraArticulos> reportarComprasPorFE(Date inicioFechaE, Date finFechaE) {
		return ejbCompraArticulosRemote.reportarComprasPorFE(inicioFechaE, finFechaE);
	}

	public List<CompraArticulos> reportarComprasPorPFE(String nombre, Date inicioFechaE, Date finFechaE) {
		return ejbCompraArticulosRemote.reportarComprasPorPFE(nombre, inicioFechaE, finFechaE);
	}

	/*public CompraArticulos encontrarPorCodigoCompra(String idCompra) {
		return ejbCompraArticulosRemote.encontrarPorCodigoCompra(idCompra);
	}*/

	public CompraArticulos traerCompraPorCodigo(int idCompra) {
		return ejbCompraArticulosRemote.traerCompraPorCodigo(idCompra);
	}
	
	public CompraArticulos traerTodaCompraPorCodigo(int idCompra) {
		return ejbCompraArticulosRemote.traerTodaCompraPorCodigo(idCompra);
	}

	public DelegadoCompraArticulos() {
		try {
			ejbCompraArticulosRemote = (EjbCompraArticulosRemote) new InitialContext().lookup("java:global/SACJungla/EjbCompraArticulos!co.com.jungla.sac.negocio.ejb.EjbCompraArticulosRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	
}
