package co.com.jungla.sac.negocio.delegados;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbPagoAbonoCxPRemote;
import co.com.jungla.sac.persistencia.entidades.PagoAbonoCxP;

public class DelegadoPagoAbonoCxP implements EjbPagoAbonoCxPRemote{
	
	EjbPagoAbonoCxPRemote ejbPagoAbonoCxPRemote;


	public void insertarPagoAbonoCxP(PagoAbonoCxP pagoAbonoCxP) {
		ejbPagoAbonoCxPRemote.insertarPagoAbonoCxP(pagoAbonoCxP);
	}

	public void actualizarPagoAbonoCxP(PagoAbonoCxP pagoAbonoCxP) {
		ejbPagoAbonoCxPRemote.actualizarPagoAbonoCxP(pagoAbonoCxP);
	}

	public void eliminarPagoAbonoCxP(PagoAbonoCxP pagoAbonoCxP) {
		ejbPagoAbonoCxPRemote.eliminarPagoAbonoCxP(pagoAbonoCxP);
	}

	public List<PagoAbonoCxP> listarPagoAbonoCxP() {
		return ejbPagoAbonoCxPRemote.listarPagoAbonoCxP();
	}

	public List<PagoAbonoCxP> listarPagoAbonoCxPPorCodigoCxP(int idCuentaxPagar) {
		return ejbPagoAbonoCxPRemote.listarPagoAbonoCxPPorCodigoCxP(idCuentaxPagar);
	}

	public List<PagoAbonoCxP> traerCodigoPagoAbonoCxP() {
		return ejbPagoAbonoCxPRemote.traerCodigoPagoAbonoCxP();
	}

	public DelegadoPagoAbonoCxP() {
		try {
			ejbPagoAbonoCxPRemote = (EjbPagoAbonoCxPRemote) new InitialContext().lookup("java:global/SACJungla/EjbPagoAbonoCxP!co.com.jungla.sac.negocio.ejb.EjbPagoAbonoCxPRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
