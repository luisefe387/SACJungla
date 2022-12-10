package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.CompraArticulos;


@Remote
public interface EjbCompraArticulosRemote {

	public void insertarCompraArticulos(CompraArticulos compraArticulos);
	
	public void actualizarCompraArticulos(CompraArticulos compraArticulos);
	
	public void eliminarCompraArticulos(CompraArticulos compraArticulos);
	
	public List<CompraArticulos> listarCompraArticulos();
	
	public List <CompraArticulos> reportarComprasPorFE(Date inicioFechaE, Date finFechaE);
	
	public List <CompraArticulos> reportarComprasPorPFE(String nombre, Date inicioFechaE, Date finFechaE);
	
	public List<CompraArticulos> traerCodigoCompra();
	
	public List<CompraArticulos> traerCompraPorFactProv(int factProv);
	
	//public CompraArticulos encontrarPorCodigoCompra(String idCompra);
	public CompraArticulos traerCompraPorCodigo(int idCompra);
	
	public CompraArticulos traerTodaCompraPorCodigo(int idCompra);
		
}
