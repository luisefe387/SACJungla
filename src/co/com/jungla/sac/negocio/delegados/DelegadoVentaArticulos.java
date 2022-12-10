package co.com.jungla.sac.negocio.delegados;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbVentaArticulosRemote;
import co.com.jungla.sac.persistencia.entidades.VentaArticulos;

public class DelegadoVentaArticulos implements EjbVentaArticulosRemote{

	EjbVentaArticulosRemote ejbVentaArticulosRemote;

	public void insertarVentaArticulos(VentaArticulos ventaArticulos) {
		ejbVentaArticulosRemote.insertarVentaArticulos(ventaArticulos);
	}

	public void actualizarVentaArticulos(VentaArticulos ventaArticulos) {
		ejbVentaArticulosRemote.actualizarVentaArticulos(ventaArticulos);
	}

	public void eliminarVentaArticulos(VentaArticulos ventaArticulos) {
		ejbVentaArticulosRemote.eliminarVentaArticulos(ventaArticulos);
	}

	public List<VentaArticulos> listarVentaArticulos() {
		return ejbVentaArticulosRemote.listarVentaArticulos();
	}

	public List<VentaArticulos> traerUltimaVentaArticulo() {
		return ejbVentaArticulosRemote.traerUltimaVentaArticulo();
	}
	
	public List<VentaArticulos> traerVentaPorCodigo(int idVenta) {
		return ejbVentaArticulosRemote.traerVentaPorCodigo(idVenta);
	}

	public List<VentaArticulos> reportarVentasPorF(Date inicioFechaE,Date finFechaE) {
		return ejbVentaArticulosRemote.reportarVentasPorF(inicioFechaE,finFechaE);
	}

	public List<VentaArticulos> reportarVentasPorFF(Date inicioFechaE,Date finFechaE, String descripcion) {
		return ejbVentaArticulosRemote.reportarVentasPorFF(inicioFechaE,finFechaE, descripcion);
	}

	public List<VentaArticulos> reportarVentasPorCF(Date inicioFechaE,Date finFechaE, String nombre) {
		return ejbVentaArticulosRemote.reportarVentasPorCF(inicioFechaE,finFechaE, nombre);
	}

	public List<VentaArticulos> reportarVentasPorCFF(Date inicioFechaE,Date finFechaE, String nombre, String descripcion) {
		return ejbVentaArticulosRemote.reportarVentasPorCFF(inicioFechaE,finFechaE, nombre, descripcion);
	}

	public List<VentaArticulos> reportarVentasPorVF(Date inicioFechaE,Date finFechaE, String nombre) {
		return ejbVentaArticulosRemote.reportarVentasPorVF(inicioFechaE,finFechaE, nombre);
	}

	public List<VentaArticulos> reportarVentasPorVCF(Date inicioFechaE,Date finFechaE, String nombre, String nombreV) {
		return ejbVentaArticulosRemote.reportarVentasPorVCF(inicioFechaE,finFechaE, nombre, nombreV);
	}

	public List<VentaArticulos> reportarVentasPorVFF(Date inicioFechaE,Date finFechaE, String nombre, String descripcion) {
		return ejbVentaArticulosRemote.reportarVentasPorVFF(inicioFechaE,finFechaE, nombre, descripcion);
	}

	public List<VentaArticulos> reportarVentasPorVCFF(Date inicioFechaE,Date finFechaE, String nombre, String nombreV, String descripcion) {
		return ejbVentaArticulosRemote.reportarVentasPorVCFF(inicioFechaE,finFechaE, nombre, nombreV, descripcion);
	}
	
	public List<VentaArticulos> traerVentaPendientePorCliente(String identificacion, String estadoPago) {
		return ejbVentaArticulosRemote.traerVentaPendientePorCliente(identificacion, estadoPago);
	}

	public List<VentaArticulos> traerVentasPendientesAVencerse(Date inicioFechaE, Date finFechaE, String estadoPago) {
		return ejbVentaArticulosRemote.traerVentasPendientesAVencerse(inicioFechaE, finFechaE, estadoPago);
	}

	public List<VentaArticulos> reportarVentasPendientesPorF(Date inicioFechaE,Date finFechaE, String estadoPago) {
		return ejbVentaArticulosRemote.reportarVentasPendientesPorF(inicioFechaE, finFechaE, estadoPago);
	}

	public List<VentaArticulos> reportarVentasPendientesPorFV(Date inicioFechaE, Date finFechaE, String identificacion,String estadoPago) {
		return ejbVentaArticulosRemote.reportarVentasPendientesPorFV(inicioFechaE, finFechaE, identificacion, estadoPago);
	}

	public List<VentaArticulos> reportarTodaVentasPendientesVencidas(String estadoPago, Date fechaActual) {
		return ejbVentaArticulosRemote.reportarTodaVentasPendientesVencidas(estadoPago, fechaActual);
	}

	public List<VentaArticulos> traerVentasPorReciboCaja(int idReciboCaja) {
		return ejbVentaArticulosRemote.traerVentasPorReciboCaja(idReciboCaja);
	}

	public List<VentaArticulos> traerTodasVentaPorCodigo(int idVenta) {
		return ejbVentaArticulosRemote.traerTodasVentaPorCodigo(idVenta);
	}

	public DelegadoVentaArticulos() {
		
		try {
			ejbVentaArticulosRemote = (EjbVentaArticulosRemote) new InitialContext().lookup("java:global/SACJungla/EjbVentaArticulos!co.com.jungla.sac.negocio.ejb.EjbVentaArticulosRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}