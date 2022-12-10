package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: DetalleCompra
 *
 */
@Entity

public class DetalleDevolucionCliente implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idDetalleDevolucionCliente;
	private int codigoArticulo;
	private int cantidadDevuelta;
	private double vlrUnitario;
	@ManyToOne
	private DevolucionCliente devolucionCliente;
	
	private static final long serialVersionUID = 1L;

	public DetalleDevolucionCliente() {
		super();
	}

	public int getIdDetalleDevolucionCliente() {
		return idDetalleDevolucionCliente;
	}

	public void setIdDetalleDevolucionCliente(int idDetalleDevolucionCliente) {
		this.idDetalleDevolucionCliente = idDetalleDevolucionCliente;
	}

	public int getCantidadDevuelta() {
		return cantidadDevuelta;
	}

	public void setCantidadDevuelta(int cantidadDevuelta) {
		this.cantidadDevuelta = cantidadDevuelta;
	}

	public double getVlrUnitario() {
		return vlrUnitario;
	}

	public void setVlrUnitario(double vlrUnitario) {
		this.vlrUnitario = vlrUnitario;
	}

	public DevolucionCliente getDevolucionCliente() {
		return devolucionCliente;
	}

	public void setDevolucionCliente(DevolucionCliente devolucionCliente) {
		this.devolucionCliente = devolucionCliente;
	}

	public int getCodigoArticulo() {
		return codigoArticulo;
	}

	public void setCodigoArticulo(int codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}
}
