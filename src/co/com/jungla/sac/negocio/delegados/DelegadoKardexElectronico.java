package co.com.jungla.sac.negocio.delegados;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.jungla.sac.negocio.ejb.EjbKardexElectronicoRemote;
import co.com.jungla.sac.persistencia.entidades.KardexElectronico;

public class DelegadoKardexElectronico implements EjbKardexElectronicoRemote{

	EjbKardexElectronicoRemote ejbKardexElectronicoRemote;

	public void insertarKardexElectronico(KardexElectronico kardexElectronico) {
		ejbKardexElectronicoRemote.insertarKardexElectronico(kardexElectronico);
	}

	public void actualizarKardexElectronico(KardexElectronico kardexElectronico) {
		ejbKardexElectronicoRemote.actualizarKardexElectronico(kardexElectronico);
	}

	public void eliminarKardexElectronico(KardexElectronico kardexElectronico) {
		ejbKardexElectronicoRemote.eliminarKardexElectronico(kardexElectronico);
	}

	public List<KardexElectronico> listarKardexElectronico() {
		return ejbKardexElectronicoRemote.listarKardexElectronico();
	}
	
	public List<KardexElectronico> traerUltimoRegistroKardexElectronico() {
		return ejbKardexElectronicoRemote.traerUltimoRegistroKardexElectronico();
	}

	public List <KardexElectronico> traerUltimoRegistroKardexElectronicopPorArticulo(int codigo) {
		return ejbKardexElectronicoRemote.traerUltimoRegistroKardexElectronicopPorArticulo(codigo);
	}
	
	/*public List<KardexElectronico> traerUltimoRegistroKardexElectronicoESDPorArticulo(int codigo, String concepto) {
		return ejbKardexElectronicoRemote.traerUltimoRegistroKardexElectronicoESDPorArticulo(codigo,concepto);
	}*/

	public List<KardexElectronico> traerRegistrosKardexPorF(Date inicioFecha, Date finFecha) {
		return ejbKardexElectronicoRemote.traerRegistrosKardexPorF(inicioFecha, finFecha);
	}

	public List<KardexElectronico> traerRegistrosKardexPorMGAF(String movimiento, String linea, String articulo, Date inicioFecha, Date finFecha) {
		return ejbKardexElectronicoRemote.traerRegistrosKardexPorMGAF(movimiento, linea, articulo, inicioFecha, finFecha);
	}

	public List<KardexElectronico> traerRegistrosKardexPorMGF(String movimiento, String linea, Date inicioFecha, Date finFecha) {
		return ejbKardexElectronicoRemote.traerRegistrosKardexPorMGF(movimiento, linea, inicioFecha, finFecha);
	}

	public List<KardexElectronico> traerRegistrosKardexPorMF(String movimiento, Date inicioFecha, Date finFecha) {
		return ejbKardexElectronicoRemote.traerRegistrosKardexPorMF(movimiento, inicioFecha, finFecha);
	}

	public List<KardexElectronico> traerRegistrosKardexPorGF(String nombreL,Date inicioFecha, Date finFecha) {
		return ejbKardexElectronicoRemote.traerRegistrosKardexPorGF(nombreL,inicioFecha, finFecha);
	}

	public List<KardexElectronico> traerRegistrosKardexPorGAF(String nombreL,String nombre, Date inicioFecha, Date finFecha) {
		return ejbKardexElectronicoRemote.traerRegistrosKardexPorGAF(nombreL,nombre, inicioFecha, finFecha);
	}

	public List<KardexElectronico> traerRegistrosKardexPorBF(String nombre, Date inicioFecha,Date finFecha) {
		return ejbKardexElectronicoRemote.traerRegistrosKardexPorBF(nombre, inicioFecha, finFecha);
	}

	public List<KardexElectronico> traerRegistrosKardexPorBMF(String nombre,String movimiento, Date inicioFecha, Date finFecha) {
		return ejbKardexElectronicoRemote.traerRegistrosKardexPorBMF(nombre,movimiento, inicioFecha, finFecha);
	}
	
	public List<KardexElectronico> traerRegistrosKardexPorBMGF(String nombre,String movimiento, String nombreL, Date inicioFecha, Date finFecha) {
		return ejbKardexElectronicoRemote.traerRegistrosKardexPorBMGF(nombre,movimiento, nombreL, inicioFecha, finFecha);
	}

	
	public List<KardexElectronico> traerRegistrosKardexPorBGF(String nombreL,String nombre, Date inicioFecha, Date finFecha) {
		return ejbKardexElectronicoRemote.traerRegistrosKardexPorBGF(nombreL,nombre, inicioFecha, finFecha);
	}

	public List<KardexElectronico> traerVentaEnKardexParaDevVenta(int idVenta,String movimiento, int codigo) {
		return ejbKardexElectronicoRemote.traerVentaEnKardexParaDevVenta(idVenta, movimiento, codigo);
	}

	public DelegadoKardexElectronico() {
		
		try {
			ejbKardexElectronicoRemote = (EjbKardexElectronicoRemote) new InitialContext().lookup("java:global/SACJungla/EjbKardexElectronico!co.com.jungla.sac.negocio.ejb.EjbKardexElectronicoRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}