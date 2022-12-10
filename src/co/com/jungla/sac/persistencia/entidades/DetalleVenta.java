package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: DetalleCompra
 *
 */
@Entity

public class DetalleVenta implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idDetalleVenta;
	private int cantidad;
	private double vlrUnitario;
	private double costoVentaUnitario;
	private double total;
	@ManyToOne
	private Articulo articulo;
	@ManyToOne
	private VentaArticulos ventaArticulos;
	
	private static final long serialVersionUID = 1L;

	public DetalleVenta(Articulo articulo, int cantidad, double vlrUnitario, double total) {
		this.articulo=articulo;
		this.cantidad=cantidad;
		this.vlrUnitario = vlrUnitario;
		this.total=total;
	}
	
	public DetalleVenta() {
		super();
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getIdDetalleVenta() {
		return idDetalleVenta;
	}

	public void setIdDetalleVenta(int idDetalleVenta) {
		this.idDetalleVenta = idDetalleVenta;
	}

	public VentaArticulos getVentaArticulos() {
		return ventaArticulos;
	}

	public void setVentaArticulos(VentaArticulos ventaArticulos) {
		this.ventaArticulos = ventaArticulos;
	}

	public double getVlrUnitario() {
		return vlrUnitario;
	}

	public void setVlrUnitario(double vlrUnitario) {
		this.vlrUnitario = vlrUnitario;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getCostoVentaUnitario() {
		return costoVentaUnitario;
	}

	public void setCostoVentaUnitario(double costoVentaUnitario) {
		this.costoVentaUnitario = costoVentaUnitario;
	}
}
