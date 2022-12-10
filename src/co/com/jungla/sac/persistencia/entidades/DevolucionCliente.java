package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: DetalleCompra
 *
 */
@Entity

public class DevolucionCliente implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idDevolucionCliente;
	private int idRecibo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRecaudo;
	private String estado;
	private String concepto;
	private double totalDevolucion;
	private String observaciones;
	@ManyToOne
	private VentaArticulos ventaArticulos;
	@OneToMany(mappedBy = "devolucionCliente", cascade = ALL, fetch = LAZY)
	private List<DetalleDevolucionCliente> detalleDevolucionCliente;
	
	private static final long serialVersionUID = 1L;

	public DevolucionCliente() {
		super();
	}

	public int getIdDevolucionCliente() {
		return idDevolucionCliente;
	}

	public void setIdDevolucionCliente(int idDevolucionCliente) {
		this.idDevolucionCliente = idDevolucionCliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public List<DetalleDevolucionCliente> getDetalleDevolucionCliente() {
		return detalleDevolucionCliente;
	}

	public void setDetalleDevolucionCliente(List<DetalleDevolucionCliente> detalleDevolucionCliente) {
		this.detalleDevolucionCliente = detalleDevolucionCliente;
	}

	public double getTotalDevolucion() {
		return totalDevolucion;
	}

	public void setTotalDevolucion(double totalDevolucion) {
		this.totalDevolucion = totalDevolucion;
	}

	public VentaArticulos getVentaArticulos() {
		return ventaArticulos;
	}

	public void setVentaArticulos(VentaArticulos ventaArticulos) {
		this.ventaArticulos = ventaArticulos;
	}

	public int getIdRecibo() {
		return idRecibo;
	}

	public void setIdRecibo(int idRecibo) {
		this.idRecibo = idRecibo;
	}

	public Date getFechaRecaudo() {
		return fechaRecaudo;
	}

	public void setFechaRecaudo(Date fechaRecaudo) {
		this.fechaRecaudo = fechaRecaudo;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
