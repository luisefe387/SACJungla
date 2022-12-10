package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoPagoAbonoCxP;
import co.com.jungla.sac.persistencia.entidades.PagoAbonoCxP;

/**
 * Session Bean implementation class EjbDetalleCompra
 */
@Stateless
@LocalBean
public class EjbPagoAbonoCxP implements EjbPagoAbonoCxPRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoPagoAbonoCxP daoPagoAbonoCxP;
	
    public EjbPagoAbonoCxP() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarPagoAbonoCxP(PagoAbonoCxP pagoAbonoCxP) {
		daoPagoAbonoCxP.insertar(pagoAbonoCxP);
	}

	@Override
	public void actualizarPagoAbonoCxP(PagoAbonoCxP pagoAbonoCxP) {
		daoPagoAbonoCxP.actualizar(pagoAbonoCxP);
	}

	@Override
	public void eliminarPagoAbonoCxP(PagoAbonoCxP pagoAbonoCxP) {
		daoPagoAbonoCxP.eliminar(pagoAbonoCxP);
	}

	@Override
	public List<PagoAbonoCxP> listarPagoAbonoCxP() {
		List<PagoAbonoCxP> pagoAbonoCxP = daoPagoAbonoCxP.listarTodo();
		return pagoAbonoCxP;
	}

	@Override
	public List<PagoAbonoCxP> traerCodigoPagoAbonoCxP() {
		List<PagoAbonoCxP> pagoAbonoCxP = daoPagoAbonoCxP.traerCodigoPagoAbonoCxP();
		return pagoAbonoCxP;
	}

	@Override
	public List<PagoAbonoCxP> listarPagoAbonoCxPPorCodigoCxP(int idCuentaxPagar) {
		List<PagoAbonoCxP> pagoAbonoCxP = daoPagoAbonoCxP.listarPagoAbonoCxPPorCodigoCxP(idCuentaxPagar);
		return pagoAbonoCxP;
	}
}
