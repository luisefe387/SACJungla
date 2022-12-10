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

public class DevolucionProveedor implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idDevolucionProveedor;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	private String estado;
	private String concepto;
	private double totalDevolucion;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaEgreso;
	private int idEgreso;
	private String observaciones;
	@ManyToOne
	private CompraArticulos compraArticulos;
	@OneToMany(mappedBy = "devolucionProveedor", cascade = ALL, fetch = LAZY)
	private List<DetalleDevolucionProveedor> detalleDevolucionProveedor;
	
	private static final long serialVersionUID = 1L;

	public DevolucionProveedor() {
		super();
	}

	public int getIdDevolucionProveedor() {
		return idDevolucionProveedor;
	}

	public void setIdDevolucionProveedor(int idDevolucionProveedor) {
		this.idDevolucionProveedor = idDevolucionProveedor;
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

	public double getTotalDevolucion() {
		return totalDevolucion;
	}

	public void setTotalDevolucion(double totalDevolucion) {
		this.totalDevolucion = totalDevolucion;
	}

	public CompraArticulos getCompraArticulos() {
		return compraArticulos;
	}

	public void setCompraArticulos(CompraArticulos compraArticulos) {
		this.compraArticulos = compraArticulos;
	}

	public List<DetalleDevolucionProveedor> getDetalleDevolucionProveedor() {
		return detalleDevolucionProveedor;
	}

	public void setDetalleDevolucionProveedor(
			List<DetalleDevolucionProveedor> detalleDevolucionProveedor) {
		this.detalleDevolucionProveedor = detalleDevolucionProveedor;
	}

	public int getIdEgreso() {
		return idEgreso;
	}

	public void setIdEgreso(int idEgreso) {
		this.idEgreso = idEgreso;
	}

	public Date getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
