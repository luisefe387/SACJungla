package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoKardexElectronico;
import co.com.jungla.sac.persistencia.entidades.KardexElectronico;

/**
 * Session Bean implementation class EjbKardexElectronico
 */
@Stateless
@LocalBean
public class EjbKardexElectronico implements EjbKardexElectronicoRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoKardexElectronico daoKardexElectronico;
	
    public EjbKardexElectronico() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarKardexElectronico(KardexElectronico kardexElectronico) {
		daoKardexElectronico.insertar(kardexElectronico);
	}

	@Override
	public void actualizarKardexElectronico(KardexElectronico kardexElectronico) {
		daoKardexElectronico.actualizar(kardexElectronico);
		
	}

	@Override
	public void eliminarKardexElectronico(KardexElectronico kardexElectronico) {
		daoKardexElectronico.eliminar(kardexElectronico);
		
	}

	@Override
	public List<KardexElectronico> listarKardexElectronico() {
		List<KardexElectronico> KardexElectronico = daoKardexElectronico.listarTodo();
		return KardexElectronico;
	}

	@Override
	public List<KardexElectronico> traerUltimoRegistroKardexElectronico() {
		List<KardexElectronico> KardexElectronico = daoKardexElectronico.traerUltimoRegistroKardexElectronico();
		return KardexElectronico;
	}

	@Override
	public List <KardexElectronico> traerUltimoRegistroKardexElectronicopPorArticulo(int codigo) {
		List <KardexElectronico> kardexElectronico = daoKardexElectronico.traerUltimoRegistroKardexElectronicopPorArticulo(codigo);
		return kardexElectronico;
	}

	
	/*@Override
	public List<KardexElectronico> traerUltimoRegistroKardexElectronicoESDPorArticulo(int codigo, String concepto) {
		List <KardexElectronico> kardexElectronico = daoKardexElectronico.traerUltimoRegistroKardexElectronicoESDPorArticulo(codigo, concepto);
		return kardexElectronico;
	}*/

	@Override
	public List<KardexElectronico> traerRegistrosKardexPorF(Date inicioFecha, Date finFecha) {
		List<KardexElectronico> KardexElectronico = daoKardexElectronico.traerRegistrosKardexPorF(inicioFecha, finFecha);
		return KardexElectronico;
	}

	@Override
	public List<KardexElectronico> traerRegistrosKardexPorMGAF(String movimiento, String linea, String articulo, Date inicioFecha, Date finFecha) {
		List<KardexElectronico> KardexElectronico = daoKardexElectronico.traerRegistrosKardexPorMGAF(movimiento, linea, articulo, inicioFecha, finFecha);
		return KardexElectronico;
	}

	@Override
	public List<KardexElectronico> traerRegistrosKardexPorMGF(String movimiento, String linea, Date inicioFecha, Date finFecha) {
		List<KardexElectronico> KardexElectronico = daoKardexElectronico.traerRegistrosKardexPorMGF(movimiento, linea, inicioFecha, finFecha);
		return KardexElectronico;
	}

	@Override
	public List<KardexElectronico> traerRegistrosKardexPorMF(String movimiento, Date inicioFecha, Date finFecha) {
		List<KardexElectronico> KardexElectronico = daoKardexElectronico.traerRegistrosKardexPorMF(movimiento, inicioFecha, finFecha);
		return KardexElectronico;
	}

	@Override
	public List<KardexElectronico> traerRegistrosKardexPorGF(String nombreL,Date inicioFecha, Date finFecha) {
		List<KardexElectronico> KardexElectronico = daoKardexElectronico.traerRegistrosKardexPorGF(nombreL, inicioFecha, finFecha);
		return KardexElectronico;
	}

	@Override
	public List<KardexElectronico> traerRegistrosKardexPorGAF(String nombreL,String nombre, Date inicioFecha, Date finFecha) {
		List<KardexElectronico> KardexElectronico = daoKardexElectronico.traerRegistrosKardexPorGAF(nombreL, nombre, inicioFecha, finFecha);
		return KardexElectronico;
	}

	@Override
	public List<KardexElectronico> traerRegistrosKardexPorBF(String nombre, Date inicioFecha, Date finFecha) {
		List<KardexElectronico> KardexElectronico = daoKardexElectronico.traerRegistrosKardexPorBF(nombre, inicioFecha, finFecha);
		return KardexElectronico;
	}

	@Override
	public List<KardexElectronico> traerRegistrosKardexPorBMF(String nombre,String movimiento, Date inicioFecha, Date finFecha) {
		List<KardexElectronico> KardexElectronico = daoKardexElectronico.traerRegistrosKardexPorBMF(nombre, movimiento, inicioFecha, finFecha);
		return KardexElectronico;
	}

	@Override
	public List<KardexElectronico> traerRegistrosKardexPorBMGF(String nombre, String movimiento, String nombreL, Date inicioFecha, Date finFecha) {
		List<KardexElectronico> KardexElectronico = daoKardexElectronico.traerRegistrosKardexPorBMGF(nombre, movimiento, nombreL, inicioFecha, finFecha);
		return KardexElectronico;
	}

	@Override
	public List<KardexElectronico> traerRegistrosKardexPorBGF(String nombreL,String nombre, Date inicioFecha, Date finFecha) {
		List<KardexElectronico> KardexElectronico = daoKardexElectronico.traerRegistrosKardexPorBGF(nombreL, nombre, inicioFecha, finFecha);
		return KardexElectronico;
	}

	@Override
	public List<KardexElectronico> traerVentaEnKardexParaDevVenta(int idVenta,String movimiento, int codigo) {
		List<KardexElectronico> KardexElectronico = daoKardexElectronico.traerVentaEnKardexParaDevVenta(idVenta, movimiento, codigo);
		return KardexElectronico;
	}
}
