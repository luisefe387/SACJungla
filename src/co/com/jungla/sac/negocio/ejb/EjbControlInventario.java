package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoControlInventario;
import co.com.jungla.sac.persistencia.entidades.ControlInventario;

/**
 * Session Bean implementation class EjbControlInventario
 */
@Stateless
@LocalBean
public class EjbControlInventario implements EjbControlInventarioRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoControlInventario daoControlInventario;
	
    public EjbControlInventario() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertarControlInventario(ControlInventario controlInventario) {
		daoControlInventario.insertar(controlInventario);
	}

	@Override
	public void actualizarControlInventario(ControlInventario controlInventario) {
		daoControlInventario.actualizar(controlInventario);
		
	}

	@Override
	public void eliminarControlInventario(ControlInventario controlInventario) {
		daoControlInventario.eliminar(controlInventario);
		
	}

	@Override
	public List<ControlInventario> listarControlInventario() {
		List<ControlInventario> controlInventario = daoControlInventario.listarTodo();
		return controlInventario;
	}

	@Override
	public List<ControlInventario> traerContInventarioPorCodigoArticulo(int codigo) {
		List<ControlInventario> controlInventario = daoControlInventario.traerContInventarioPorCodigoArticulo(codigo);
		return controlInventario;
	}

	@Override
	public List<ControlInventario> traerRegistrosInventarioPorP(String proveedorUltimaCompra) {
		List<ControlInventario> controlInventario = daoControlInventario.traerRegistrosInventarioPorP(proveedorUltimaCompra);
		return controlInventario;
	}

	@Override
	public List<ControlInventario> traerRegistrosInventarioPorGP(String nombreL, String proveedorUltimaCompra) {
		List<ControlInventario> controlInventario = daoControlInventario.traerRegistrosInventarioPorGP(nombreL, proveedorUltimaCompra);
		return controlInventario;
	}
	
	@Override
	public List<ControlInventario> traerRegistrosInventarioPorE(String estadoArticulo) {
		List<ControlInventario> controlInventario = daoControlInventario.traerRegistrosInventarioPorE(estadoArticulo);
		return controlInventario;
	}

	@Override
	public List<ControlInventario> traerRegistrosInventarioPorEG(String estadoArticulo, String nombreL) {
		List<ControlInventario> controlInventario = daoControlInventario.traerRegistrosInventarioPorEG(estadoArticulo, nombreL);
		return controlInventario;
	}
	
	@Override
	public List<ControlInventario> traerRegistrosInventarioPorG(String nombreL) {
		List<ControlInventario> controlInventario = daoControlInventario.traerRegistrosInventarioPorG(nombreL);
		return controlInventario;
	}

	@Override
	public List<ControlInventario> traerRegistrosInventarioPorEGP(String estadoArticulo, String nombreL, String proveedorUltimaCompra) {
		List<ControlInventario> controlInventario = daoControlInventario.traerRegistrosInventarioPorEGP(estadoArticulo, nombreL, proveedorUltimaCompra);
		return controlInventario;
	}

	@Override
	public List<ControlInventario> traerRegistrosInventarioPorB(String nombre) {
		List<ControlInventario> controlInventario = daoControlInventario.traerRegistrosInventarioPorB(nombre);
		return controlInventario;
	}

	@Override
	public List<ControlInventario> traerRegistrosInventarioPorBP(String nombre,String proveedorUltimaCompra) {
		List<ControlInventario> controlInventario = daoControlInventario.traerRegistrosInventarioPorBP(nombre, proveedorUltimaCompra);
		return controlInventario;
	}

	@Override
	public List<ControlInventario> traerRegistrosInventarioPorBGP(String nombre, String nombreL, String proveedorUltimaCompra) {
		List<ControlInventario> controlInventario = daoControlInventario.traerRegistrosInventarioPorBGP(nombre, nombreL, proveedorUltimaCompra);
		return controlInventario;
	}

	@Override
	public List<ControlInventario> traerRegistrosInventarioPorBE(String nombre,String estadoArticulo) {
		List<ControlInventario> controlInventario = daoControlInventario.traerRegistrosInventarioPorBE(nombre, estadoArticulo);
		return controlInventario;
	}

	@Override
	public List<ControlInventario> traerRegistrosInventarioPorBEG(String nombre, String estadoArticulo, String nombreL) {
		List<ControlInventario> controlInventario = daoControlInventario.traerRegistrosInventarioPorBEG(nombre, estadoArticulo, nombreL);
		return controlInventario;
	}

	@Override
	public List<ControlInventario> traerRegistrosInventarioPorBEGP(String nombre, String estadoArticulo, String nombreL,String proveedorUltimaCompra) {
		List<ControlInventario> controlInventario = daoControlInventario.traerRegistrosInventarioPorBEGP(nombre, estadoArticulo, nombreL, proveedorUltimaCompra);
		return controlInventario;
	}

	@Override
	public List<ControlInventario> traerRegistrosInventarioPorBG(String nombre,String nombreL) {
		List<ControlInventario> controlInventario = daoControlInventario.traerRegistrosInventarioPorBG(nombre, nombreL);
		return controlInventario;
	}

	@Override
	public List<ControlInventario> traerRegistrosInventarioPorBEP(String nombre, String estadoArticulo, String proveedorUltimaCompra) {
		List<ControlInventario> controlInventario = daoControlInventario.traerRegistrosInventarioPorBEP(nombre, estadoArticulo, proveedorUltimaCompra);
		return controlInventario;
	}

	@Override
	public List<ControlInventario> traerRegistrosInventarioPorEP(String estadoArticulo, String proveedorUltimaCompra) {
		List<ControlInventario> controlInventario = daoControlInventario.traerRegistrosInventarioPorEP(estadoArticulo, proveedorUltimaCompra);
		return controlInventario;
	}

	@Override
	public List<ControlInventario> traerRegistrosInventarioConExistencias() {
		List<ControlInventario> controlInventario = daoControlInventario.traerRegistrosInventarioConExistencias();
		return controlInventario;
	}

	@Override
	public List<ControlInventario> traerRegistrosInventarioCantAlerta() {
		List<ControlInventario> controlInventario = daoControlInventario.traerRegistrosInventarioCantAlerta();
		return controlInventario;
	}
}
