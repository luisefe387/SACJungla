package co.com.jungla.sac.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: Cliente
 *
 */
@Entity
public class Vendedor extends Persona implements Serializable{

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaNacimiento;
	private String profesion;
	private String estadoCivil;
	private byte hijos;
	private String estado;
	private String sexo;
	@OneToMany(mappedBy = "vendedores")
	private List<VentaArticulos> ventaArticulos; 

	private static final long serialVersionUID = 1L;

	public Vendedor() {
		super();
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public byte getHijos() {
		return hijos;
	}

	public void setHijos(byte hijos) {
		this.hijos = hijos;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<VentaArticulos> getVentaArticulos() {
		return ventaArticulos;
	}

	public void setVentaArticulos(List<VentaArticulos> ventaArticulos) {
		this.ventaArticulos = ventaArticulos;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
}
