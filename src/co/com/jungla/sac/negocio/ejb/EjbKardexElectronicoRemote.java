package co.com.jungla.sac.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.jungla.sac.persistencia.entidades.KardexElectronico;

@Remote
public interface EjbKardexElectronicoRemote {

	public void insertarKardexElectronico(KardexElectronico kardexElectronico);
	
	public void actualizarKardexElectronico(KardexElectronico kardexElectronico);
	
	public void eliminarKardexElectronico(KardexElectronico kardexElectronico);
	
	public List<KardexElectronico> listarKardexElectronico();
	
	public List <KardexElectronico> traerUltimoRegistroKardexElectronico();
	
	public List <KardexElectronico> traerUltimoRegistroKardexElectronicopPorArticulo(int codigo);
	
	//public List <KardexElectronico> traerUltimoRegistroKardexElectronicoESDPorArticulo(int codigo, String concepto);
	
	public List <KardexElectronico> traerRegistrosKardexPorF(Date inicioFecha, Date finFecha);
	
	public List <KardexElectronico> traerRegistrosKardexPorMGAF(String movimiento, String linea, String articulo, Date inicioFecha, Date finFecha);
	
	public List <KardexElectronico> traerRegistrosKardexPorMGF(String movimiento, String linea, Date inicioFecha, Date finFecha);
	
	public List <KardexElectronico> traerRegistrosKardexPorMF(String movimiento, Date inicioFecha, Date finFecha);
	
	public List <KardexElectronico> traerRegistrosKardexPorGF(String nombreL, Date inicioFecha, Date finFecha);
	
	public List <KardexElectronico> traerRegistrosKardexPorGAF(String nombreL, String nombre, Date inicioFecha, Date finFecha);
	
	public List <KardexElectronico> traerRegistrosKardexPorBF(String nombre, Date inicioFecha, Date finFecha);
	
	public List <KardexElectronico> traerRegistrosKardexPorBMF(String nombre, String movimiento, Date inicioFecha, Date finFecha);
	
	public List <KardexElectronico> traerRegistrosKardexPorBMGF(String nombre, String movimiento, String nombreL, Date inicioFecha, Date finFecha);
	
	public List <KardexElectronico> traerRegistrosKardexPorBGF(String nombreL, String nombre, Date inicioFecha, Date finFecha);
	
	public List <KardexElectronico> traerVentaEnKardexParaDevVenta(int idVenta , String movimiento, int codigo);
}
