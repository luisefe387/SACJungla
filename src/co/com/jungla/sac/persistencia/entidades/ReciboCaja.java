package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.MERGE;

/**
 * Entity implementation class for Entity: CuentaXPagar
 *
 */
@Entity
public class ReciboCaja implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idReciboCaja;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRecaudo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaGeneracion;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaVenta;
	private double totalDocs;
	private double totalRecibido;
	private double totalNCredito;
	private String estadoActividad;
	private String observaciones;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAnulacion;
	@ManyToMany(mappedBy = "reciboCaja", cascade = { PERSIST, MERGE })
	private List<VentaArticulos> ventaArticulos;
	@OneToMany(mappedBy = "reciboCaja")
	private List<MedioPagoCliente> medioPago;
	
	private static final long serialVersionUID = 1L;

	public ReciboCaja() {
		super();
	}

	public int getIdReciboCaja() {
		return idReciboCaja;
	}

	public void setIdReciboCaja(int idReciboCaja) {
		this.idReciboCaja = idReciboCaja;
	}

	public Date getFechaRecaudo() {
		return fechaRecaudo;
	}

	public void setFechaRecaudo(Date fechaRecaudo) {
		this.fechaRecaudo = fechaRecaudo;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public double getTotalDocs() {
		return totalDocs;
	}

	public void setTotalDocs(double totalDocs) {
		this.totalDocs = totalDocs;
	}

	public double getTotalRecibido() {
		return totalRecibido;
	}

	public void setTotalRecibido(double totalRecibido) {
		this.totalRecibido = totalRecibido;
	}

	public double getTotalNCredito() {
		return totalNCredito;
	}

	public void setTotalNCredito(double totalNCredito) {
		this.totalNCredito = totalNCredito;
	}

	public List<VentaArticulos> getVentaArticulos() {
		return ventaArticulos;
	}

	public void setVentaArticulos(List<VentaArticulos> ventaArticulos) {
		this.ventaArticulos = ventaArticulos;
	}

	public String getEstadoActividad() {
		return estadoActividad;
	}

	public void setEstadoActividad(String estadoActividad) {
		this.estadoActividad = estadoActividad;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public List<MedioPagoCliente> getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(List<MedioPagoCliente> medioPago) {
		this.medioPago = medioPago;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Date getFechaAnulacion() {
		return fechaAnulacion;
	}

	public void setFechaAnulacion(Date fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}
}
