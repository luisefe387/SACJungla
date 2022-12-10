package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.VentaArticulos;

@Remote
public interface EjbVentaArticulosRemote {

	public void insertarVentaArticulos(VentaArticulos ventaArticulos);
	
	public void actualizarVentaArticulos(VentaArticulos ventaArticulos);
	
	public void eliminarVentaArticulos(VentaArticulos ventaArticulos);
	
	public List<VentaArticulos> listarVentaArticulos();
	
	public List<VentaArticulos> traerUltimaVentaArticulo();
	
	public List<VentaArticulos> traerVentaPorCodigo(int idVenta);
	
	public List <VentaArticulos> reportarVentasPorF(Date inicioFechaE, Date finFechaE);
	
	public List <VentaArticulos> reportarVentasPorFF(Date inicioFechaE, Date finFechaE, String descripcion);
	
	public List <VentaArticulos> reportarVentasPorCF(Date inicioFechaE, Date finFechaE, String nombre);
	
	public List <VentaArticulos> reportarVentasPorCFF(Date inicioFechaE, Date finFechaE, String nombre, String descripcion);
	
	public List <VentaArticulos> reportarVentasPorVF(Date inicioFechaE, Date finFechaE, String nombre);
	
	public List <VentaArticulos> reportarVentasPorVCF(Date inicioFechaE, Date finFechaE, String nombre, String nombreV);
	
	public List <VentaArticulos> reportarVentasPorVFF(Date inicioFechaE, Date finFechaE, String nombre, String descripcion);
	
	public List <VentaArticulos> reportarVentasPorVCFF(Date inicioFechaE, Date finFechaE, String nombre, String nombreV, String descripcion);
	
	public List <VentaArticulos> traerVentaPendientePorCliente(String identificacion, String estadoPago);
	
	public List <VentaArticulos> traerVentasPendientesAVencerse(Date inicioFechaE, Date finFechaE, String estadoPago);
	
	public List <VentaArticulos> reportarVentasPendientesPorF(Date inicioFechaE, Date finFechaE, String estadoPago);
	
	public List <VentaArticulos> reportarVentasPendientesPorFV(Date inicioFechaE, Date finFechaE, String identificacion,String estadoPago);
	
	public List <VentaArticulos> reportarTodaVentasPendientesVencidas(String estadoPago, Date fechaActual);
	
	public List <VentaArticulos> traerVentasPorReciboCaja(int idReciboCaja);
	
	public List<VentaArticulos> traerTodasVentaPorCodigo(int idVenta);
}
