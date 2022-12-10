package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.PagoAbonoCxP;


@Remote
public interface EjbPagoAbonoCxPRemote {

	public void insertarPagoAbonoCxP(PagoAbonoCxP pagoAbonoCxP);
	
	public void actualizarPagoAbonoCxP(PagoAbonoCxP pagoAbonoCxP);
	
	public void eliminarPagoAbonoCxP(PagoAbonoCxP pagoAbonoCxP);
	
	public List<PagoAbonoCxP> listarPagoAbonoCxP();
	
	public List <PagoAbonoCxP> listarPagoAbonoCxPPorCodigoCxP(int idCuentaxPagar);
	
	public List <PagoAbonoCxP> traerCodigoPagoAbonoCxP();
}
