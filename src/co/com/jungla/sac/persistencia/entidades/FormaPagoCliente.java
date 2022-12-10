package co.com.jungla.sac.persistencia.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Entity implementation class for Entity: FormaPago
 *
 */
@Entity
public class FormaPagoCliente implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idFormaPagoCliente;
	private String descripcion;
	private int dias;
	@OneToMany(mappedBy = "formaPagoCliente")
	private List<VentaArticulos> ventaArticulos;
	
	private static final long serialVersionUID = 1L;

	public FormaPagoCliente() {
		super();
	}

	public FormaPagoCliente(String descripcion, int idFormaPagoCliente) {
		this.descripcion = descripcion;
		this.idFormaPagoCliente = idFormaPagoCliente;
	}

	public int getIdFormaPagoCliente() {
		return idFormaPagoCliente;
	}

	public void setIdFormaPagoCliente(int idFormaPagoCliente) {
		this.idFormaPagoCliente = idFormaPagoCliente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	@Override
	public String toString() {
		return descripcion;
	}

	public List<VentaArticulos> getVentaArticulos() {
		return ventaArticulos;
	}

	public void setVentaArticulos(List<VentaArticulos> ventaArticulos) {
		this.ventaArticulos = ventaArticulos;
	}
}
