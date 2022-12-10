package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.AnticipoCliente;


@Remote
public interface EjbAnticipoClienteRemote {

	public void insertarAnticipoCliente(AnticipoCliente anticipoCliente);
	
	public void actualizarAnticipoCliente(AnticipoCliente anticipoCliente);
	
	public void eliminarAnticipoCliente(AnticipoCliente anticipoCliente);
	
	public List<AnticipoCliente> traerUltimoAnticipoCliente();
	
	public List <AnticipoCliente> listarAnticipoClientePorIdentificacion(String identificacion);
	
	public List <AnticipoCliente> traerAnticipoClientePorCodigo(int idAnticipoCliente);
	
	/*public List <AnticipoCliente> listarAnticipoClientePorIdentificacion(String identificacion);
	
	public List <AnticipoCliente> traerAnticipoClientePorCodigo(int idAnticipoCliente);
	
	public List <AnticipoCliente> reportarAnticiposPorF(Date inicioFechaE, Date finFechaE);
	
	public List <AnticipoCliente> reportarAnticiposPorPF(Date inicioFechaE, Date finFechaE, String nombre);
	
	public List <AnticipoCliente> reportarAnticiposPorEF(Date inicioFechaE, Date finFechaE, String estadoAnticipo);
	
	public List <AnticipoCliente> reportarAnticiposPorPEF(Date inicioFechaE, Date finFechaE, String nombre, String estadoAnticipo);
	
	/*public List<OrdenCompraArticulos> listarOrdenCompraArticulos();
	
	public List<OrdenCompraArticulos> reportarOrdenesCompra();
	
	public List <OrdenCompraArticulos> reportarOrdenesPorPFEE(String nombre, Date inicioFechaE, Date finFechaE, String estado);
	
	public List <OrdenCompraArticulos> reportarOrdenesPorFE(Date inicioFechaE, Date finFechaE);
	
	public List <OrdenCompraArticulos> reportarOrdenesPorPFE(String nombre, Date inicioFechaE, Date finFechaE);
	
	public List <OrdenCompraArticulos> reportarOrdenesPorFEE(Date inicioFechaE, Date finFechaE, String estado);*/
}
