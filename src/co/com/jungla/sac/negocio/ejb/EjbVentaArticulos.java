package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoVentaArticulos;
import co.com.jungla.sac.persistencia.entidades.VentaArticulos;

/**
 * Session Bean implementation class EjbVentaArticulos
 */
@Stateless
@LocalBean
public class EjbVentaArticulos implements EjbVentaArticulosRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoVentaArticulos daoVentaArticulos;
	
    public EjbVentaArticulos() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarVentaArticulos(VentaArticulos ventaArticulos) {
		daoVentaArticulos.insertar(ventaArticulos);
	}

	@Override
	public void actualizarVentaArticulos(VentaArticulos ventaArticulos) {
		daoVentaArticulos.actualizar(ventaArticulos);
		
	}

	@Override
	public void eliminarVentaArticulos(VentaArticulos ventaArticulos) {
		daoVentaArticulos.eliminar(ventaArticulos);
		
	}

	@Override
	public List<VentaArticulos> listarVentaArticulos() {
		List<VentaArticulos> VentaArticuloss = daoVentaArticulos.listarTodo();
		return VentaArticuloss;
	}

	@Override
	public List<VentaArticulos> traerUltimaVentaArticulo() {
		List<VentaArticulos> ventaArticulos = daoVentaArticulos.traerUltimaVentaArticulo();
		return ventaArticulos;
	}

	
	@Override
	public List<VentaArticulos> traerVentaPorCodigo(int idVenta) {
		List<VentaArticulos> ventaArticulos = daoVentaArticulos.traerVentaPorCodigo(idVenta);
		return ventaArticulos;
	}

	@Override
	public List<VentaArticulos> reportarVentasPorF(Date inicioFechaE,Date finFechaE) {
		List<VentaArticulos> ventaArticulos = daoVentaArticulos.reportarVentasPorF(inicioFechaE, finFechaE);
		return ventaArticulos;
	}

	@Override
	public List<VentaArticulos> reportarVentasPorFF(Date inicioFechaE,Date finFechaE, String descripcion) {
		List<VentaArticulos> ventaArticulos = daoVentaArticulos.reportarVentasPorFF(inicioFechaE, finFechaE, descripcion);
		return ventaArticulos;
	}

	@Override
	public List<VentaArticulos> reportarVentasPorCF(Date inicioFechaE,Date finFechaE, String nombre) {
		List<VentaArticulos> ventaArticulos = daoVentaArticulos.reportarVentasPorCF(inicioFechaE, finFechaE, nombre);
		return ventaArticulos;
	}

	@Override
	public List<VentaArticulos> reportarVentasPorCFF(Date inicioFechaE,Date finFechaE, String nombre, String descripcion) {
		List<VentaArticulos> ventaArticulos = daoVentaArticulos.reportarVentasPorCFF(inicioFechaE, finFechaE, nombre, descripcion);
		return ventaArticulos;
	}

	@Override
	public List<VentaArticulos> reportarVentasPorVF(Date inicioFechaE,Date finFechaE, String nombre) {
		List<VentaArticulos> ventaArticulos = daoVentaArticulos.reportarVentasPorVF(inicioFechaE, finFechaE, nombre);
		return ventaArticulos;
	}

	@Override
	public List<VentaArticulos> reportarVentasPorVCF(Date inicioFechaE,Date finFechaE, String nombre, String nombreV) {
		List<VentaArticulos> ventaArticulos = daoVentaArticulos.reportarVentasPorVCF(inicioFechaE, finFechaE, nombre, nombreV);
		return ventaArticulos;
	}

	@Override
	public List<VentaArticulos> reportarVentasPorVFF(Date inicioFechaE,Date finFechaE, String nombre, String descripcion) {
		List<VentaArticulos> ventaArticulos = daoVentaArticulos.reportarVentasPorVFF(inicioFechaE, finFechaE, nombre, descripcion);
		return ventaArticulos;
	}

	@Override
	public List<VentaArticulos> reportarVentasPorVCFF(Date inicioFechaE,Date finFechaE, String nombre, String nombreV, String descripcion) {
		List<VentaArticulos> ventaArticulos = daoVentaArticulos.reportarVentasPorVCFF(inicioFechaE, finFechaE, nombre, nombreV, descripcion);
		return ventaArticulos;
	}

	@Override
	public List<VentaArticulos> traerVentaPendientePorCliente(String identificacion, String estadoPago) {
		List<VentaArticulos> ventaArticulos = daoVentaArticulos.traerVentaPendientePorCliente(identificacion, estadoPago);
		return ventaArticulos;
	}

	@Override
	public List<VentaArticulos> traerVentasPendientesAVencerse(Date inicioFechaE, Date finFechaE, String estadoPago) {
		List<VentaArticulos> ventaArticulos = daoVentaArticulos.traerVentasPendientesAVencerse(inicioFechaE, finFechaE, estadoPago);
		return ventaArticulos;
	}

	@Override
	public List<VentaArticulos> reportarVentasPendientesPorF(Date inicioFechaE,Date finFechaE, String estadoPago) {
		List<VentaArticulos> ventaArticulos = daoVentaArticulos.reportarVentasPendientesPorF(inicioFechaE, finFechaE, estadoPago);
		return ventaArticulos;
	}

	@Override
	public List<VentaArticulos> reportarVentasPendientesPorFV(Date inicioFechaE, Date finFechaE, String identificacion,String estadoPago) {
		List<VentaArticulos> ventaArticulos = daoVentaArticulos.reportarVentasPendientesPorFV(inicioFechaE, finFechaE, identificacion, estadoPago);
		return ventaArticulos;
	}

	@Override
	public List<VentaArticulos> reportarTodaVentasPendientesVencidas(String estadoPago, Date fechaActual) {
		List<VentaArticulos> ventaArticulos = daoVentaArticulos.reportarTodaVentasPendientesVencidas(estadoPago, fechaActual);
		return ventaArticulos;
	}

	@Override
	public List<VentaArticulos> traerVentasPorReciboCaja(int idReciboCaja) {
		List<VentaArticulos> ventaArticulos = daoVentaArticulos.traerVentasPorReciboCaja(idReciboCaja);
		return ventaArticulos;
	}

	@Override
	public List<VentaArticulos> traerTodasVentaPorCodigo(int idVenta) {
		List<VentaArticulos> ventaArticulos = daoVentaArticulos.traerTodasVentaPorCodigo(idVenta);
		return ventaArticulos;
	}
	
	
}
