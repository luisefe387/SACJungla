package co.com.jungla.sac.negocio.delegados;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbCuentaXPagarRemote;
import co.com.jungla.sac.persistencia.entidades.CuentaXPagar;


public class DelegadoCuentaXPagar implements EjbCuentaXPagarRemote{
	
	EjbCuentaXPagarRemote ejbCuentaXPagarRemote;

	public void insertarCuentaXPagar(CuentaXPagar cuentaXPagar) {
		ejbCuentaXPagarRemote.insertarCuentaXPagar(cuentaXPagar);
	}

	public void actualizarCuentaXPagar(CuentaXPagar cuentaXPagar) {
		ejbCuentaXPagarRemote.actualizarCuentaXPagar(cuentaXPagar);
	}

	public void eliminarCuentaXPagar(CuentaXPagar cuentaXPagar) {
		ejbCuentaXPagarRemote.eliminarCuentaXPagar(cuentaXPagar);
	}

	public List<CuentaXPagar> traerCodigoCuentaXPagar() {
		return ejbCuentaXPagarRemote.traerCodigoCuentaXPagar();
	}
	
	public List<CuentaXPagar> traerCxPPorDocSoporte(int docSoporte) {
		return ejbCuentaXPagarRemote.traerCxPPorDocSoporte(docSoporte);
	}

	public List<CuentaXPagar> traerCxPPorDocSoporte_1(String docSoporte) {
		return ejbCuentaXPagarRemote.traerCxPPorDocSoporte_1(docSoporte);
	}
	
	public List<CuentaXPagar> traerCxPPorProveedor(String nombre) {
		return ejbCuentaXPagarRemote.traerCxPPorProveedor(nombre);
	}

	public List<CuentaXPagar> traerCxPPorProveedorydocSoporte(String nombre, int docSoporte) {
		return ejbCuentaXPagarRemote.traerCxPPorProveedorydocSoporte(nombre, docSoporte);
	}
	
	public List<CuentaXPagar> traerCuentaXPagarPorCodigo(int idCuentaXPagar) {
		return ejbCuentaXPagarRemote.traerCuentaXPagarPorCodigo(idCuentaXPagar);
	}

	public List<CuentaXPagar> traerCxPAVencerse(Date inicioFechaE,Date finFechaE, String estado) {
		return ejbCuentaXPagarRemote.traerCxPAVencerse(inicioFechaE, finFechaE,estado);
	}
	
	public List<CuentaXPagar> reportarCuentaXPagarPorF(Date inicioFecha,Date finFecha) {
		return ejbCuentaXPagarRemote.reportarCuentaXPagarPorF(inicioFecha,finFecha);
	}

	public List<CuentaXPagar> reportarCuentaXPagarPorFC(Date inicioFecha,Date finFecha, String concepto) {
		return ejbCuentaXPagarRemote.reportarCuentaXPagarPorFC(inicioFecha,finFecha, concepto);
	}

	public List<CuentaXPagar> reportarCuentaXPagarPorFCP(Date inicioFecha,Date finFecha, String identificacionProv, String concepto) {
		return ejbCuentaXPagarRemote.reportarCuentaXPagarPorFCP(inicioFecha,finFecha, identificacionProv, concepto);
	}

	public List<CuentaXPagar> reportarCuentaXPagarPorFP(Date inicioFecha,Date finFecha, String identificacionProv) {
		return ejbCuentaXPagarRemote.reportarCuentaXPagarPorFP(inicioFecha,finFecha, identificacionProv);
	}

	public List<CuentaXPagar> reportarTodasCxPVencidas(Date fechaActual) {
		return ejbCuentaXPagarRemote.reportarTodasCxPVencidas(fechaActual);
	}

	public DelegadoCuentaXPagar() {
		try {
			ejbCuentaXPagarRemote = (EjbCuentaXPagarRemote) new InitialContext().lookup("java:global/SACJungla/EjbCuentaXPagar!co.com.jungla.sac.negocio.ejb.EjbCuentaXPagarRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
