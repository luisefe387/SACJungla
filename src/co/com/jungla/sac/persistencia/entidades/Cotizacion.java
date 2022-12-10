package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.CascadeType.ALL;

/**
 * clase entidad con los atributos principales que debe llevar una cotizacion junto con los metodos get y set para devolver 
 * y definir la informacion de sus atributos  
 * @author Luis Fernando Pedroza T.
 * @version: 01/09/2016
 */
@Entity
public class Cotizacion implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idCotizacion;
	private String identificacionCliente;
	private String nombreCliente;
	private String contacto;
	private String ciudad;
	private String direccion;
	private String telefono;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	private double subtotal;
	private double descuento;
	private double totalCotizado;
	private int items;
	private int diasValidez;
	private String observaciones;
	@OneToMany(mappedBy = "cotizacion", cascade = ALL, fetch = LAZY)
	private List<DetalleCotizacion> detalleCotizacion;
	
	private static final long serialVersionUID = 1L;

	public Cotizacion() {
		super();
	}

	/*metodos get y set de cada atributo de la cotizacion indicando la devolucion y asignacion de la informacion que contiene cada atributo*/
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public int getIdCotizacion() {
		return idCotizacion;
	}

	public void setIdCotizacion(int idCotizacion) {
		this.idCotizacion = idCotizacion;
	}

	public String getIdentificacionCliente() {
		return identificacionCliente;
	}

	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<DetalleCotizacion> getDetalleCotizacion() {
		return detalleCotizacion;
	}

	public void setDetalleCotizacion(List<DetalleCotizacion> detalleCotizacion) {
		this.detalleCotizacion = detalleCotizacion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getTotalCotizado() {
		return totalCotizado;
	}

	public void setTotalCotizado(double totalCotizado) {
		this.totalCotizado = totalCotizado;
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

	public int getItems() {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
	}

	public int getDiasValidez() {
		return diasValidez;
	}

	public void setDiasValidez(int diasValidez) {
		this.diasValidez = diasValidez;
	}
}
