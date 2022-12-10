package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

/**
 * clase entidad para definir los atributos y metodos que tiene un articulo o producto.
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Entity

public class Articulo implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int codigo;
	private String nombre;
	private String presentacion;
	private String descripcion;
	private String referencia; 
	private String estado;
	@OneToOne(mappedBy = "articulo")
	private ControlInventario controlInventario;
	@ManyToOne
	private LineaArticulos lineaArticulos;
	@ManyToOne
	private UnidadMedida unidadMedida;
	@OneToMany(mappedBy = "articulo", cascade = ALL, fetch = LAZY)
	private List<KardexElectronico> kardexElectronico;
	@OneToMany(mappedBy = "articulo")
	private List<DetalleCompra> detalleCompra;
	@OneToMany(mappedBy = "articulo")
	private List<DetalleOrdenCompra> detalleOrdenCompra;
	@OneToMany(mappedBy = "articulo")
	private List<DetalleVenta> detalleVenta;
	@OneToMany(mappedBy = "articulo")
	private List<AlertaVencimiento> alertasVencimiento;
	@OneToMany(mappedBy = "articulo")
	private List<DetalleEntradaOSalida> detalleEntradaOSalida;
	
	private static final long serialVersionUID = 1L;

	public Articulo() {
		super();
	}

	/**
	 * Constructor en donde se asignan el nombre y el codigo del articulo
	 * @param nombre parametro que define el nombre del articulo
	 * @param codigo parametro que define el codigo del articulo
	 */
	public Articulo(String nombre, int codigo){
		this.nombre=nombre;
		this.codigo=codigo;
	}
	
	/**
	 * Metodo para devolver el codigo del articulo
	 * @return un numero que indica el codigo del articulo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Metodo para asignar el codigo del articulo
	 * @param codigo parametro de tipo numerico
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Metodo para devolver el nombre del articulo
	 * @return una cadena de texto
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo para asignar el nombre del articulo
	 * @param nombre parametro que indica una cadena de texto
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo para devolver la linea de articulos a al que pertenece el articulo
	 * @return un objeto de tipo LineaArticulos
	 */
	public LineaArticulos getLineaArticulos() {
		return lineaArticulos;
	}

	/**
	 * Metodo para definir la Linea de Articulos a la que pertenecera el articulo
	 * @param lineaArticulos parametro de tipo LineaArticulos que indica el grupo del articulo
	 */
	public void setLineaArticulos(LineaArticulos lineaArticulos) {
		this.lineaArticulos = lineaArticulos;
	}

	/**
	 * Metodo para devolver la unidad de medida del articulo
	 * @return un objeto de tipo UnidadMedida
	 */
	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}

	/**
	 * Metodo para asignar la unidad del medida del articulo
	 * @param unidadMedida parametro de tipo UnidadMedida que indica la unidad de medida del articulo
	 */
	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	/**
	 * Metodo para devolver la presentacion del articulo
	 * @return un cadena de texto que indica la informacion acerca de la presentacion del articulo
	 */
	public String getPresentacion() {
		return presentacion;
	}

	/**
	 * Metodo para definir la presentacion del articulo
	 * @param presentacion parametro de tipo string indicando la informacion sobre la presentacion del articulo
	 */
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	/**
	 * Metodo para devolver la descripcion del articulo
	 * @return una cadena con la informacion suficiente del articulo
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Metodo para definir la descripcion del articulo
	 * @param descripcion parametro que define la descripcion del articulo
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Metodo que  devuelve la referencia del articulo
	 * @return un dato alfanumerico
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * Metodo que define la referencia del articulo
	 * @param referencia parametro de tipo alfanumerico en la asigna la referencia del articulo
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * Metodo que devuelve el estado de actividad del articulo
	 * @return un texto indicando el estado del articulo ya sea activo o inactivo
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Metodo para definir el estado de actividad del articulo
	 * @param estado parametro que asigna el estado del articulo ya sea activo o inactivo
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Metodo para devolver una lista del detalle compra en la que estan los articulos agregados
	 * @return una lista de tipo DetalleCompra
	 */
	public List<DetalleCompra> getDetalleCompra() {
		return detalleCompra;
	}

	/**
	 * Metodo para asignar la lista del detalle compra en la que se agregaran los articulos
	 * @param detalleCompra parametro que indica una lista de objetos de tipo detalleCompra
	 */
	public void setDetalleCompra(List<DetalleCompra> detalleCompra) {
		this.detalleCompra = detalleCompra;
	}

	/**
	 * Metodo para devolver el control inventario en la que estaran todos los articulos en el inventario
	 * @return un objeto de tipo ControlInventario
	 */
	public ControlInventario getControlInventario() {
		return controlInventario;
	}

	/**
	 * Metodo para asignar el control inventario de los articulos
	 * @param controlInventario parametro de tipo ControlInventario 
	 */
	public void setControlInventario(ControlInventario controlInventario) {
		this.controlInventario = controlInventario;
	}

	/**
	 * Metodo que devuelve el kardex electronnico en la que se agregaran los articulos que entran y salen del inventario
	 * @return un objeto de tipo KardexElectronico
	 */
	public List<KardexElectronico> getKardexElectronico() {
		return kardexElectronico;
	}

	/**
	 * Metodo para asignar el kardex electronico en la que se van agregar los articulos al inventario
	 * @param kardexElectronico parametro de tipo KardexElectronico
	 */
	public void setKardexElectronico(List<KardexElectronico> kardexElectronico) {
		this.kardexElectronico = kardexElectronico;
	}
	
	
	/**
	 * Metodo para devolver el detalle de las entradas y salidas de los articulos
	 * @return un dato de tipo DetalleEntradaOSalida
	 */
	public List<DetalleEntradaOSalida> getDetalleEntradaOSalida() {
		return detalleEntradaOSalida;
	}

	/**
	 * Metodo para asignar los articulos al detalle de entradas o salidas 
	 * @param detalleEntradaOSalida parametro de tipo DetalleEntradaOSalida
	 */
	public void setDetalleEntradaOSalida(
			List<DetalleEntradaOSalida> detalleEntradaOSalida) {
		this.detalleEntradaOSalida = detalleEntradaOSalida;
	}

	/**
	 * Metodo para devolver los articulos agregados al detalle de la orden de compra
	 * @return dato de tipo DetalleOrdenCompra
	 */
	public List<DetalleOrdenCompra> getDetalleOrdenCompra() {
		return detalleOrdenCompra;
	}

	/**
	 * Metodo para definir los articulos que se agregaran al detalle orden compra
	 * @param detalleOrdenCompra parametro de tipo DetalleOrdenCompra
	 */
	public void setDetalleOrdenCompra(List<DetalleOrdenCompra> detalleOrdenCompra) {
		this.detalleOrdenCompra = detalleOrdenCompra;
	}

	/**
	 * Metodo para devolver los articulos agregados al detalle de la venta
	 * @return dato de tipo DetalleVenta
	 */
	public List<DetalleVenta> getDetalleVenta() {
		return detalleVenta;
	}

	/**
	 * Metodo para definir los articulos que se agregaran al detalle venta
	 * @param detalleVenta parametro de tipo DetalleVenta
	 */
	public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}
	
	public List<AlertaVencimiento> getAlertasVencimiento() {
		return alertasVencimiento;
	}

	public void setAlertasVencimiento(List<AlertaVencimiento> alertasVencimiento) {
		this.alertasVencimiento = alertasVencimiento;
	}

	@Override
	public String toString() {
		return nombre;
	}
}
