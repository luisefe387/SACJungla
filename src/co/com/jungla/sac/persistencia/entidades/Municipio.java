package co.com.jungla.sac.persistencia.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Municipio
 *
 */
@Entity

public class Municipio implements Serializable {

	@Id
	private int idMunicipio;
	private String nombre;
	@ManyToOne
	private Departamento departamento;
	@OneToMany(mappedBy = "municipio")
	private List<Persona> persona;
	
	private static final long serialVersionUID = 1L;

	public Municipio() {
		super();
	}

	public Municipio(String nombre, int idMunicipio) {
		this.nombre=nombre;
		this.idMunicipio=idMunicipio;
	}
	
	public int getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public List<Persona> getPersona() {
		return persona;
	}

	public void setPersona(List<Persona> persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return nombre;
	}
}
