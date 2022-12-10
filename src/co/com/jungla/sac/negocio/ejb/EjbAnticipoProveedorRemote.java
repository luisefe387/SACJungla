package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.AnticipoProveedor;


@Remote
public interface EjbAnticipoProveedorRemote {

	public void insertarAnticipoProveedor(AnticipoProveedor anticipoProveedor);
	
	public void actualizarAnticipoProveedor(AnticipoProveedor anticipoProveedor);
	
	public void eliminarAnticipoProveedor(AnticipoProveedor anticipoProveedor);
	
	public List<AnticipoProveedor> traerCodigoAnticipoProveedor();
	
	public List <AnticipoProveedor> listarAnticipoProveedorPorIdentificacion(String identificacion);
	
	public List <AnticipoProveedor> traerAnticipoProveedorPorCodigo(int idAnticipoProveedor);
	
	public List <AnticipoProveedor> reportarAnticiposPorF(Date inicioFechaE, Date finFechaE);
	
	public List <AnticipoProveedor> reportarAnticiposPorPF(Date inicioFechaE, Date finFechaE, String nombre);
	
	public List <AnticipoProveedor> reportarAnticiposPorEF(Date inicioFechaE, Date finFechaE, String estadoAnticipo);
	
	public List <AnticipoProveedor> reportarAnticiposPorPEF(Date inicioFechaE, Date finFechaE, String nombre, String estadoAnticipo);
	
	/*public List<OrdenCompraArticulos> listarOrdenCompraArticulos();
	
	public List<OrdenCompraArticulos> reportarOrdenesCompra();
	
	public List <OrdenCompraArticulos> reportarOrdenesPorPFEE(String nombre, Date inicioFechaE, Date finFechaE, String estado);
	
	public List <OrdenCompraArticulos> reportarOrdenesPorFE(Date inicioFechaE, Date finFechaE);
	
	public List <OrdenCompraArticulos> reportarOrdenesPorPFE(String nombre, Date inicioFechaE, Date finFechaE);
	
	public List <OrdenCompraArticulos> reportarOrdenesPorFEE(Date inicioFechaE, Date finFechaE, String estado);*/
}
