package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.OrdenCompraArticulos;


@Remote
public interface EjbOrdenCompraArticulosRemote {

	public void insertarOrdenCompraArticulos(OrdenCompraArticulos ordenCompraArticulos);
	
	public void actualizarOrdenCompraArticulos(OrdenCompraArticulos ordenCompraArticulos);
	
	public void eliminarOrdenCompraArticulos(OrdenCompraArticulos ordenCompraArticulos);
	
	public List<OrdenCompraArticulos> listarOrdenCompraArticulos();
	
	public List<OrdenCompraArticulos> traerCodigoOrdenCompra();
	
	public List<OrdenCompraArticulos> traerCodigoOrdenPorCompra(int idOrdenCompra);
	
	public List<OrdenCompraArticulos> reportarOrdenesCompra();
	
	public List <OrdenCompraArticulos> reportarOrdenesPorPFEE(String nombre, Date inicioFechaE, Date finFechaE, String estado);
	
	public List <OrdenCompraArticulos> reportarOrdenesPorFE(Date inicioFechaE, Date finFechaE);
	
	public List <OrdenCompraArticulos> reportarOrdenesPorPFE(String nombre, Date inicioFechaE, Date finFechaE);
	
	public List <OrdenCompraArticulos> reportarOrdenesPorFEE(Date inicioFechaE, Date finFechaE, String estado);
}
