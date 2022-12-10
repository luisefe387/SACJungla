package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.CascadeType.ALL;

/**
 * clase entidad con las caracteristicas principales que debe llevar una compra junto con los metodos get y set para devolver y definir la informacion de sus atributos  
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Entity
public class CompraArticulos implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idCompra;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaEntrega;
	/**
	 * factProv es un atributo que indica el numero de la factura del proveedor a la cual se le esta comprando
	 */
	private int factProv;
	/**
	 * ordCompra es un atributo que indica el numero de la orden de compra a la cual se definira como compra si es el caso
	 */
	private int ordCompra;
	private double subtotal;
	private double descuento;
	private double totalCompra;
	/**
	 * item, indica el numero de items agregados en el detalle de la compra
	 */
	private int item;
	/**
	 * egreso, indica el numero de egreso que se genero luego de haberse pagado la compra de contado
	 */
	private int egreso;
	/**
	 * cuentaXPagar, indica el numero de la cuenta por Pagar que se genero luego de haberse realizo la compra a credito quedando pendiente de pago
	 */
	private int cuentaXPagar;
	/**
	 * estadoPago, es el estado de la compra ya sea pendiente o pagado
	 */
	private String estadoPago;
	/**
	 * fechaCausacion, es la fecha en que se causa o se produce la compra
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCausacion;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPago;
	/**
	 * fechaGeneracion, es la fecha inmediata de la compra
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaGeneracion;
	private String observaciones;
	private String estadoActividad;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAnulacion;
	@ManyToOne
	private Proveedor proveedores;
	/**
	 * medioPago, es un atributo de tipo MedioPagoProv en la que indica el numero del medio de pago utilzado en la compra ya sea en efectivo, cheque o con otro medio
	 */
	@ManyToOne
	private MedioPagoProv medioPagoProv;
	@OneToMany(mappedBy = "compraArticulos", cascade = ALL, fetch = LAZY)
	private List<DetalleCompra> detalleCompra;
	@OneToMany(mappedBy = "compraArticulos", cascade = ALL, fetch = LAZY)
	private List<DevolucionProveedor> devolucionProveedor;
	
	private static final long serialVersionUID = 1L;

	public CompraArticulos() {
		super();
	}

	/*metodos get y set de cada atributo de la compra indicando la devolucion y asignacion de la informacion que contiene cada 
	 * atributo*/
	
	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public Proveedor getProveedores() {
		return proveedores;
	}

	public void setProveedores(Proveedor proveedores) {
		this.proveedores = proveedores;
	}

	public List<DetalleCompra> getDetalleCompra() {
		return detalleCompra;
	}

	public void setDetalleCompra(List<DetalleCompra> detalleCompra) {
		this.detalleCompra = detalleCompra;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(double totalCompra) {
		this.totalCompra = totalCompra;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public MedioPagoProv getMedioPagoProv() {
		return medioPagoProv;
	}

	public void setMedioPagoProv(MedioPagoProv medioPagoProv) {
		this.medioPagoProv = medioPagoProv;
	}

	public int getFactProv() {
		return factProv;
	}

	public void setFactProv(int factProv) {
		this.factProv = factProv;
	}

	public int getOrdCompra() {
		return ordCompra;
	}

	public void setOrdCompra(int ordCompra) {
		this.ordCompra = ordCompra;
	}

	public String getEstadoPago() {
		return estadoPago;
	}

	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}

	public Date getFechaCausacion() {
		return fechaCausacion;
	}

	public void setFechaCausacion(Date fechaCausacion) {
		this.fechaCausacion = fechaCausacion;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public void agregarDetalle(DetalleCompra detalle){
		this.detalleCompra.add(detalle);
		detalle.setCompraArticulos(this);
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public int getEgreso() {
		return egreso;
	}

	public void setEgreso(int egreso) {
		this.egreso = egreso;
	}

	public int getCuentaXPagar() {
		return cuentaXPagar;
	}

	public void setCuentaXPagar(int cuentaXPagar) {
		this.cuentaXPagar = cuentaXPagar;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public List<DevolucionProveedor> getDevolucionProveedor() {
		return devolucionProveedor;
	}

	public void setDevolucionProveedor(List<DevolucionProveedor> devolucionProveedor) {
		this.devolucionProveedor = devolucionProveedor;
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
