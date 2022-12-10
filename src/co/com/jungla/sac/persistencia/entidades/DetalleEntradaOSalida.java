package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: DetalleCompra
 *
 */
@Entity

public class DetalleEntradaOSalida implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idDetalleEntradaOSalida;
	private int cantidad;
	private double costoUnitario;
	private double total;
	@ManyToOne
	private EntradaOSalidaArticulo entradaOSalidaArticulo;
	@ManyToOne
	private Articulo articulo;
	
	private static final long serialVersionUID = 1L;

	public DetalleEntradaOSalida() {
		super();
	}

	public int getIdDetalleEntradaOSalida() {
		return idDetalleEntradaOSalida;
	}

	public void setIdDetalleEntradaOSalida(int idDetalleEntradaOSalida) {
		this.idDetalleEntradaOSalida = idDetalleEntradaOSalida;
	}

	public EntradaOSalidaArticulo getEntradaOSalidaArticulo() {
		return entradaOSalidaArticulo;
	}

	public void setEntradaOSalidaArticulo(
			EntradaOSalidaArticulo entradaOSalidaArticulo) {
		this.entradaOSalidaArticulo = entradaOSalidaArticulo;
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

	public double getCostoUnitario() {
		return costoUnitario;
	}

	public void setCostoUnitario(double costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
