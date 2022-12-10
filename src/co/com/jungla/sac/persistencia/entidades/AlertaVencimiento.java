package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * clase entidad para definir las caracteristicas y operaciones que tiene un alerta de vencimiento
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Entity
public class AlertaVencimiento implements Serializable {
	
	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idAlerta;
	private Articulo articulo;
	private int cantidad;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaVencimiento;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaGeneracion;
	private String observaciones;
	private String lote;
	
	private static final long serialVersionUID = 1L;

	public AlertaVencimiento() {
		super();
	}

	public int getIdAlerta() {
		return idAlerta;
	}

	public void setIdAlerta(int idAlerta) {
		this.idAlerta = idAlerta;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}
}
