package co.com.jungla.sac.negocio.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.jungla.sac.persistencia.dao.DaoMunicipio;
import co.com.jungla.sac.persistencia.entidades.Municipio;

/**
 * Session Bean implementation class EjbMunicipio
 */
@Stateless
@LocalBean
public class EjbMunicipio implements EjbMunicipioRemote {

    /**
     * Default constructor. 
     */
	@EJB
	DaoMunicipio daoMunicipio;
	
    public EjbMunicipio() {
        
    }

	@Override
	public void insertarMunicipio(Municipio municipio) {
		daoMunicipio.insertar(municipio);
	}

	@Override
	public List<Municipio> listarMunicipios() {
		return daoMunicipio.listarTodo();
	}

	@Override
	public List<Municipio> listarMunicipiosPorDepartamento(String nombre) {
		return daoMunicipio.municipiosPorDepartamento(nombre);
	}

}
