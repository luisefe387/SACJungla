package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.CascadeType.ALL;

/**
 * Entity implementation class for Entity: CompraArticulos
 *
 */
@Entity

public class EntradaOSalidaArticulo implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idEntradaOSalida;
	private String concepto;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	private double totalCosto;
	private int cantTotalArticulos;
	private String observaciones;
	@OneToMany(mappedBy = "entradaOSalidaArticulo", cascade = ALL, fetch = LAZY)
	private List<DetalleEntradaOSalida> detalleEntradaOSalida;

	private static final long serialVersionUID = 1L;

	public EntradaOSalidaArticulo() {
		super();
	}

	public int getIdEntradaOSalida() {
		return idEntradaOSalida;
	}

	public void setIdEntradaOSalida(int idEntradaOSalida) {
		this.idEntradaOSalida = idEntradaOSalida;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getTotalCosto() {
		return totalCosto;
	}

	public void setTotalCosto(double totalCosto) {
		this.totalCosto = totalCosto;
	}

	public int getCantTotalArticulos() {
		return cantTotalArticulos;
	}

	public void setCantTotalArticulos(int cantTotalArticulos) {
		this.cantTotalArticulos = cantTotalArticulos;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public List<DetalleEntradaOSalida> getDetalleEntradaOSalida() {
		return detalleEntradaOSalida;
	}

	public void setDetalleEntradaOSalida(
			List<DetalleEntradaOSalida> detalleEntradaOSalida) {
		this.detalleEntradaOSalida = detalleEntradaOSalida;
	}
}
