package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: DetalleCompra
 *
 */
@Entity

public class DetalleDevolucionProveedor implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idDetalleDevolucionProveedor;
	private int codigoArticulo;
	private int cantidadDevuelta;
	private double costo;
	@ManyToOne
	private DevolucionProveedor devolucionProveedor;
	
	private static final long serialVersionUID = 1L;

	public DetalleDevolucionProveedor() {
		super();
	}

	public int getIdDetalleDevolucionProveedor() {
		return idDetalleDevolucionProveedor;
	}

	public void setIdDetalleDevolucionProveedor(int idDetalleDevolucionProveedor) {
		this.idDetalleDevolucionProveedor = idDetalleDevolucionProveedor;
	}

	public DevolucionProveedor getDevolucionProveedor() {
		return devolucionProveedor;
	}

	public void setDevolucionProveedor(DevolucionProveedor devolucionProveedor) {
		this.devolucionProveedor = devolucionProveedor;
	}

	public int getCantidadDevuelta() {
		return cantidadDevuelta;
	}

	public void setCantidadDevuelta(int cantidadDevuelta) {
		this.cantidadDevuelta = cantidadDevuelta;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public int getCodigoArticulo() {
		return codigoArticulo;
	}

	public void setCodigoArticulo(int codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}
}
