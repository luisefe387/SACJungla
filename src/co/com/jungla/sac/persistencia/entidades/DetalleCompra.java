package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: DetalleCompra
 *
 */
@Entity

public class DetalleCompra implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idDetalleCompra;
	private int cantidad;
	private double costo;
	private double total;
	@ManyToOne
	private CompraArticulos compraArticulos;
	@ManyToOne
	private Articulo articulo;
	
	private static final long serialVersionUID = 1L;

	public DetalleCompra() {
		super();
	}

	public int getIdDetalleCompra() {
		return idDetalleCompra;
	}

	public void setIdDetalleCompra(int idDetalleCompra) {
		this.idDetalleCompra = idDetalleCompra;
	}

	public CompraArticulos getCompraArticulos() {
		return compraArticulos;
	}

	public void setCompraArticulos(CompraArticulos compraArticulos) {
		this.compraArticulos = compraArticulos;
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

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
