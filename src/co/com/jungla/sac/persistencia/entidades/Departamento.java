package co.com.jungla.sac.persistencia.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Departamento
 *
 */
@Entity

public class Departamento implements Serializable {

	@Id
	private int idDepartamento;
	private String nombre;
	@OneToMany(mappedBy = "departamento")
	private List<Municipio> municipios;
	
	private static final long serialVersionUID = 1L;

	public Departamento() {
		super();
	}

	public Departamento(String nombre, int idDepartamento){
		this.nombre=nombre;
		this.idDepartamento=idDepartamento;
	}
	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	@Override
	public String toString() {
		return nombre;
	}
}
