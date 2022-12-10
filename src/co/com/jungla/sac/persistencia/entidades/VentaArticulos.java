package co.com.jungla.sac.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.MERGE;

/**
 * Entity implementation class for Entity: CompraArticulos
 *
 */
@Entity

public class VentaArticulos implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idVenta;
	private int pedido;
	private int ordCompra;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaLimitePago;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaGeneracion;
	private double subtotal;
	private double totalVenta;
	private int items;
	private double descuento;
	private double costoVenta;
	private String estadoPago;
	private String observaciones;
	private String estadoActividad;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAnulacion;
	@ManyToOne
	private Cliente clientes;
	@ManyToOne
	private Vendedor vendedores;
	@ManyToOne
	private FormaPagoCliente formaPagoCliente;
	@ManyToMany(cascade = { PERSIST, MERGE })
	private List<ReciboCaja> reciboCaja;
	@OneToMany(mappedBy = "ventaArticulos", cascade = ALL, fetch = LAZY)
	private List<DetalleVenta> detalleVenta;
	@OneToMany(mappedBy = "ventaArticulos", cascade = ALL, fetch = LAZY)
	private List<DevolucionCliente> devolucionCliente;
	
	private static final long serialVersionUID = 1L;

	public VentaArticulos() {
		super();
		
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public Cliente getClientes() {
		return clientes;
	}

	public void setClientes(Cliente clientes) {
		this.clientes = clientes;
	}

	public int getPedido() {
		return pedido;
	}

	public void setPedido(int pedido) {
		this.pedido = pedido;
	}

	public int getOrdCompra() {
		return ordCompra;
	}

	public void setOrdCompra(int ordCompra) {
		this.ordCompra = ordCompra;
	}

	public Vendedor getVendedores() {
		return vendedores;
	}

	public void setVendedores(Vendedor vendedores) {
		this.vendedores = vendedores;
	}

	public FormaPagoCliente getFormaPagoCliente() {
		return formaPagoCliente;
	}

	public void setFormaPagoCliente(FormaPagoCliente formaPagoCliente) {
		this.formaPagoCliente = formaPagoCliente;
	}

	public Date getFechaLimitePago() {
		return fechaLimitePago;
	}

	public void setFechaLimitePago(Date fechaLimitePago) {
		this.fechaLimitePago = fechaLimitePago;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public List<DetalleVenta> getDetalleVenta() {
		return detalleVenta;
	}

	public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(double totalVenta) {
		this.totalVenta = totalVenta;
	}

	public int getItems() {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public List<ReciboCaja> getReciboCaja() {
		return reciboCaja;
	}

	public void setReciboCaja(List<ReciboCaja> reciboCaja) {
		this.reciboCaja = reciboCaja;
	}

	public double getCostoVenta() {
		return costoVenta;
	}

	public void setCostoVenta(double costoVenta) {
		this.costoVenta = costoVenta;
	}

	public String getEstadoPago() {
		return estadoPago;
	}

	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public List<DevolucionCliente> getDevolucionCliente() {
		return devolucionCliente;
	}

	public void setDevolucionCliente(List<DevolucionCliente> devolucionCliente) {
		this.devolucionCliente = devolucionCliente;
	}

	public String getEstadoActividad() {
		return estadoActividad;
	}

	public void setEstadoActividad(String estadoActividad) {
		this.estadoActividad = estadoActividad;
	}

	public Date getFechaAnulacion() {
		return fechaAnulacion;
	}

	public void setFechaAnulacion(Date fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}
}
