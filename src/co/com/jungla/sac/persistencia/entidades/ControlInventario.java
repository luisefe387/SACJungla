package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.*;

/**
 * clase entidad con las atributos principales para realizar un control del inventario de todos los articulos existentes junto con los metodos get y set para devolver y definir la informacion de sus atributos  
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Entity
public class ControlInventario implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idInventario;
	/**
	 * costoPromedio, indica el costo de producción por articulo basado en el costo total del articulo y su número total de unidades producidas
	 */
	private double costoPromedio;
	/**
	 * factorRentabilidad, indica
	 */
	private float factorRentabilidad;
	private double precioTotalVenta;
	/**
	 * rentabilidadCostoPromedio, es el atributo que indica el porcentaje que halla la rentabilidad del costo del articulo basado 
	 * en la diferencia entre su costo promedio y su precio de venta sobre su costo promedio 
	 */
	private float rentabilidadCostoPromedio;
	/**
	 * rentabilidadVenta, es un atributo que indica el porcentaje que halla la rentabilidad del precio del articulo basado en la 
	 * diferencia entre su precio de venta y su costo promedio sobre su precio de venta.
	 */
	private float rentabilidadVenta;
	/**
	 * cantExistencia, indica el número de existencias del articulo 
	 */
	private int cantExistencia;
	/**
	 * cantAlerta, indica el número límite de escasez del articulo 
	 */
	private int cantAlerta;
	private double totalCostoInventario;
	private String proveedorUltimaCompra;
	private double costoUltimaCompra;
	private String urlFoto;
	@OneToOne
	private Articulo articulo;
	
	private static final long serialVersionUID = 1L;

	public ControlInventario() {
		super();
	}

	/*metodos get y set de cada atributo de la contabilizacion indicando la devolucion y asignacion de la informacion que contiene cada 
	 * atributo*/
	
	public int getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(int idInventario) {
		this.idInventario = idInventario;
	}

	public double getCostoPromedio() {
		return costoPromedio;
	}

	public void setCostoPromedio(double costoPromedio) {
		this.costoPromedio = costoPromedio;
	}

	public float getFactorRentabilidad() {
		return factorRentabilidad;
	}

	public void setFactorRentabilidad(float factorRentabilidad) {
		this.factorRentabilidad = factorRentabilidad;
	}

	public double getPrecioTotalVenta() {
		return precioTotalVenta;
	}

	public void setPrecioTotalVenta(double precioTotalVenta) {
		this.precioTotalVenta = precioTotalVenta;
	}

	public float getRentabilidadCostoPromedio() {
		return rentabilidadCostoPromedio;
	}

	public void setRentabilidadCostoPromedio(float rentabilidadCostoPromedio) {
		this.rentabilidadCostoPromedio = rentabilidadCostoPromedio;
	}

	public float getRentabilidadVenta() {
		return rentabilidadVenta;
	}

	public void setRentabilidadVenta(float rentabilidadVenta) {
		this.rentabilidadVenta = rentabilidadVenta;
	}

	public int getCantExistencia() {
		return cantExistencia;
	}

	public void setCantExistencia(int cantExistencia) {
		this.cantExistencia = cantExistencia;
	}

	public int getCantAlerta() {
		return cantAlerta;
	}

	public void setCantAlerta(int cantAlerta) {
		this.cantAlerta = cantAlerta;
	}

	public double getTotalCostoInventario() {
		return totalCostoInventario;
	}

	public void setTotalCostoInventario(double totalCostoInventario) {
		this.totalCostoInventario = totalCostoInventario;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public String getProveedorUltimaCompra() {
		return proveedorUltimaCompra;
	}

	public void setProveedorUltimaCompra(String proveedorUltimaCompra) {
		this.proveedorUltimaCompra = proveedorUltimaCompra;
	}

	public double getCostoUltimaCompra() {
		return costoUltimaCompra;
	}

	public void setCostoUltimaCompra(double costoUltimaCompra) {
		this.costoUltimaCompra = costoUltimaCompra;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
}
