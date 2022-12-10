package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.*;

/**
 * clase entidad para definir las caracteristicas y operaciones que tiene un recordatorio
 * @author Luis Fernando Pedroza T.
 * @version: 18/08/2016
 */
@Entity
public class Recordatorio implements Serializable {
	
	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idAlertas;
	private int diasVencimiento;
	private int diasCartera;
	private int diasCxP;
	private boolean mostrarAlInicio;
	
	private static final long serialVersionUID = 1L;

	public Recordatorio() {
		super();
	}

	public int getIdAlertas() {
		return idAlertas;
	}

	public void setIdAlertas(int idAlertas) {
		this.idAlertas = idAlertas;
	}

	public int getDiasVencimiento() {
		return diasVencimiento;
	}

	public void setDiasVencimiento(int diasVencimiento) {
		this.diasVencimiento = diasVencimiento;
	}

	public int getDiasCartera() {
		return diasCartera;
	}

	public void setDiasCartera(int diasCartera) {
		this.diasCartera = diasCartera;
	}

	public int getDiasCxP() {
		return diasCxP;
	}

	public void setDiasCxP(int diasCxP) {
		this.diasCxP = diasCxP;
	}

	public boolean isMostrarAlInicio() {
		return mostrarAlInicio;
	}

	public void setMostrarAlInicio(boolean mostrarAlInicio) {
		this.mostrarAlInicio = mostrarAlInicio;
	}
}
