package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.CuentaXPagar;


@Remote
public interface EjbCuentaXPagarRemote {

	public void insertarCuentaXPagar(CuentaXPagar cuentaXPagar);
	
	public void actualizarCuentaXPagar(CuentaXPagar cuentaXPagar);
	
	public void eliminarCuentaXPagar(CuentaXPagar cuentaXPagar);
	
	public List<CuentaXPagar> traerCodigoCuentaXPagar();
	
	public List <CuentaXPagar> traerCxPPorDocSoporte(int docSoporte);
	
	public List <CuentaXPagar> traerCxPPorDocSoporte_1(String docSoporte);
	
	public List <CuentaXPagar> traerCxPPorProveedor(String nombre);
	
	public List <CuentaXPagar> traerCxPPorProveedorydocSoporte(String nombre, int docSoporte);
	
	public List <CuentaXPagar> traerCuentaXPagarPorCodigo(int idCuentaXPagar);
	
	public List <CuentaXPagar> traerCxPAVencerse(Date inicioFechaE, Date finFechaE, String estado);
	
	public List <CuentaXPagar> reportarCuentaXPagarPorF(Date inicioFecha, Date finFecha);
	
	public List <CuentaXPagar> reportarCuentaXPagarPorFC(Date inicioFecha, Date finFecha, String concepto );
	
	public List <CuentaXPagar> reportarCuentaXPagarPorFCP(Date inicioFecha, Date finFecha, String identificacionProv, String concepto );
	
	public List <CuentaXPagar> reportarCuentaXPagarPorFP(Date inicioFecha, Date finFecha, String identificacionProv );
	
	public List <CuentaXPagar> reportarTodasCxPVencidas(Date fechaActual);
}
