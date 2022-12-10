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

public class OrdenCompraArticulos implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idOrdenCompra;
	private String identificacionProv;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaEntregaEsperada;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPagoPactado;
	private double totalOrdenCompra;
	private int item;
	private String estado;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaGeneracion;
	private String observaciones;
	private int compra;
	private String estadoActividad;
	@OneToMany(mappedBy = "ordenCompraArticulos", cascade = ALL, fetch = LAZY)
	private List<DetalleOrdenCompra> detalleOrdenCompra;
	
	private static final long serialVersionUID = 1L;

	public OrdenCompraArticulos() {
		super();
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getIdOrdenCompra() {
		return idOrdenCompra;
	}

	public void setIdOrdenCompra(int idOrdenCompra) {
		this.idOrdenCompra = idOrdenCompra;
	}

	public String getIdentificacionProv() {
		return identificacionProv;
	}

	public void setIdentificacionProv(String identificacionProv) {
		this.identificacionProv = identificacionProv;
	}

	public Date getFechaEntregaEsperada() {
		return fechaEntregaEsperada;
	}

	public void setFechaEntregaEsperada(Date fechaEntregaEsperada) {
		this.fechaEntregaEsperada = fechaEntregaEsperada;
	}

	public Date getFechaPagoPactado() {
		return fechaPagoPactado;
	}

	public void setFechaPagoPactado(Date fechaPagoPactado) {
		this.fechaPagoPactado = fechaPagoPactado;
	}

	public double getTotalOrdenCompra() {
		return totalOrdenCompra;
	}

	public void setTotalOrdenCompra(double totalOrdenCompra) {
		this.totalOrdenCompra = totalOrdenCompra;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<DetalleOrdenCompra> getDetalleOrdenCompra() {
		return detalleOrdenCompra;
	}

	public void setDetalleOrdenCompra(List<DetalleOrdenCompra> detalleOrdenCompra) {
		this.detalleOrdenCompra = detalleOrdenCompra;
	}

	public void setFechaGeneración(Date fechaGeneración) {
		this.fechaGeneracion = fechaGeneración;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public int getCompra() {
		return compra;
	}

	public void setCompra(int compra) {
		this.compra = compra;
	}

	public String getEstadoActividad() {
		return estadoActividad;
	}

	public void setEstadoActividad(String estadoActividad) {
		this.estadoActividad = estadoActividad;
	}
}
