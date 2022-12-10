package co.com.jungla.sac.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.*;

import static javax.persistence.InheritanceType.JOINED;

/**
 * Entity implementation class for Entity: Persona
 *
 */
@Entity
@Inheritance(strategy = JOINED)
public class Persona implements Serializable {

	@Id
	private String identificacion;
	private String tipoPersona;
	private String nombre;
	private String telefono;
	private String celular;
	private String direccion;
	private String email;
	private String paginaWeb;
	private String observaciones;
	@ManyToOne
	private Municipio municipio;
	
	private static final long serialVersionUID = 1L;

	public Persona() {
		super();
	}

	public Persona(String identificacion){
		this.identificacion = identificacion;
	}
	
	public Persona(String nombre, String identificacion){
		this.nombre=nombre;
		this.identificacion = identificacion;
	}
	
	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getPaginaWeb() {
		return paginaWeb;
	}

	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}
	
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Override
	public String toString() {
		return nombre;
	}
}
