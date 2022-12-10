package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoCuentaXPagar;
import co.com.jungla.sac.persistencia.entidades.CuentaXPagar;

/**
 * Session Bean implementation class EjbCompraArticulos
 */
@Stateless
@LocalBean
public class EjbCuentaXPagar implements EjbCuentaXPagarRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoCuentaXPagar daoCuentaXPagar;
	
    public EjbCuentaXPagar() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarCuentaXPagar(CuentaXPagar cuentaXPagar) {
		daoCuentaXPagar.insertar(cuentaXPagar);
	}

	@Override
	public void actualizarCuentaXPagar(CuentaXPagar cuentaXPagar) {
		daoCuentaXPagar.actualizar(cuentaXPagar);
	}

	@Override
	public void eliminarCuentaXPagar(CuentaXPagar cuentaXPagar) {
		daoCuentaXPagar.eliminar(cuentaXPagar);
	}

	@Override
	public List<CuentaXPagar> traerCodigoCuentaXPagar() {
		List<CuentaXPagar> cuentaXPagar = daoCuentaXPagar.traerCodigoCuentaXPagar();
		return cuentaXPagar;
	}

	@Override
	public List<CuentaXPagar> traerCxPPorDocSoporte(int docSoporte) {
		List<CuentaXPagar> cuentaXPagar = daoCuentaXPagar.traerCxPPorDocSoporte(docSoporte);
		return cuentaXPagar;
	}

	
	@Override
	public List<CuentaXPagar> traerCxPPorDocSoporte_1(String docSoporte) {
		List<CuentaXPagar> cuentaXPagar = daoCuentaXPagar.traerCxPPorDocSoporte_1(docSoporte);
		return cuentaXPagar;
	}

	@Override
	public List<CuentaXPagar> traerCxPPorProveedor(String nombre) {
		List<CuentaXPagar> cuentaXPagar = daoCuentaXPagar.traerCxPPorProveedor(nombre);
		return cuentaXPagar;
	}

	@Override
	public List<CuentaXPagar> traerCxPPorProveedorydocSoporte(String nombre, int docSoporte) {
		List<CuentaXPagar> cuentaXPagar = daoCuentaXPagar.traerCxPPorProveedorydocSoporte(nombre, docSoporte);
		return cuentaXPagar;
	}

	@Override
	public List<CuentaXPagar> traerCuentaXPagarPorCodigo(int idCuentaXPagar) {
		List<CuentaXPagar> cuentaXPagar = daoCuentaXPagar.traerCuentaXPagarPorCodigo(idCuentaXPagar);
		return cuentaXPagar;
	}

	@Override
	public List<CuentaXPagar> traerCxPAVencerse(Date inicioFechaE,Date finFechaE, String estado) {
		List<CuentaXPagar> cuentaXPagar = daoCuentaXPagar.traerCxPAVencerse(inicioFechaE, finFechaE, estado);
		return cuentaXPagar;
	}

	@Override
	public List<CuentaXPagar> reportarCuentaXPagarPorF(Date inicioFecha,Date finFecha) {
		List<CuentaXPagar> cuentaXPagar = daoCuentaXPagar.reportarCuentaXPagarPorF(inicioFecha, finFecha);
		return cuentaXPagar;	}

	@Override
	public List<CuentaXPagar> reportarCuentaXPagarPorFC(Date inicioFecha,Date finFecha, String concepto) {
		List<CuentaXPagar> cuentaXPagar = daoCuentaXPagar.reportarCuentaXPagarPorFC(inicioFecha, finFecha, concepto);
		return cuentaXPagar;
	}

	@Override
	public List<CuentaXPagar> reportarCuentaXPagarPorFCP(Date inicioFecha,Date finFecha, String identificacionProv, String concepto) {
		List<CuentaXPagar> cuentaXPagar = daoCuentaXPagar.reportarCuentaXPagarPorFCP(inicioFecha, finFecha, identificacionProv, concepto);
		return cuentaXPagar;
	}

	@Override
	public List<CuentaXPagar> reportarCuentaXPagarPorFP(Date inicioFecha,Date finFecha, String identificacionProv) {
		List<CuentaXPagar> cuentaXPagar = daoCuentaXPagar.reportarCuentaXPagarPorFP(inicioFecha, finFecha, identificacionProv);
		return cuentaXPagar;
	}

	@Override
	public List<CuentaXPagar> reportarTodasCxPVencidas(Date fechaActual) {
		List<CuentaXPagar> cuentaXPagar = daoCuentaXPagar.reportarTodasCxPVencidas(fechaActual);
		return cuentaXPagar;
	}
}
