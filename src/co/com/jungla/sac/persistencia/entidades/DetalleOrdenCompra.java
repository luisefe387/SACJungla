package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: DetalleCompra
 *
 */
@Entity

public class DetalleOrdenCompra implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idDetalleOrdenCompra;
	private int cantidad;
	private double costo;
	private double total;
	@ManyToOne
	private OrdenCompraArticulos ordenCompraArticulos;
	@ManyToOne
	private Articulo articulo;
	
	private static final long serialVersionUID = 1L;

	public DetalleOrdenCompra() {
		super();
	}

	public int getIdDetalleCompra() {
		return idDetalleOrdenCompra;
	}

	public void setIdDetalleCompra(int idDetalleCompra) {
		this.idDetalleOrdenCompra = idDetalleCompra;
	}

	public OrdenCompraArticulos getOrdenCompraArticulos() {
		return ordenCompraArticulos;
	}

	public void setOrdenCompraArticulos(OrdenCompraArticulos ordenCompraArticulos) {
		this.ordenCompraArticulos = ordenCompraArticulos;
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
