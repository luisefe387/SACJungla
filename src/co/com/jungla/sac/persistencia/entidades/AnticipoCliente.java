package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * clase entidad para definir las caracteristicas y operaciones que tiene un anticipo o adelanto al cliente
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Entity
public class AnticipoCliente implements Serializable {
	
	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idAnticipoCliente;
	private String identificacionCliente;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAnticipo;
	private double valorAnticipo;
	private int reciboCaja;
	private String observaciones;
	private String estadoAnticipo;
	private String estadoActividad;
	
	@OneToMany(mappedBy = "anticipoCliente")
	private List<MedioPagoCliente> medioPago;
	
	private static final long serialVersionUID = 1L;

	public AnticipoCliente() {
		super();
	}

	/**
	 * Metodo para devolver el anticipo del cliente
	 * @return el numero del anticipo del cliente
	 */
	public int getIdAnticipoCliente() {
		return idAnticipoCliente;
	}

	/**
	 * Metodo para definir el anticipo del cliente
	 * @param idAnticipoCliente parametro que define el numero del anticipo del cliente
	 */
	public void setIdAnticipoCliente(int idAnticipoCliente) {
		this.idAnticipoCliente = idAnticipoCliente;
	}
	
	/**
	 * Metodo que devuelve la identificacion del cliente
	 * @return el numero de la identificacion del cliente
	 */
	public String getIdentificacionCliente() {
		return identificacionCliente;
	}

	/**
	 * Metodo que define la identificacion del cliente
	 * @param identificacionCliente parametro que define el numero de identificacion del cliente 
	 */
	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}

	/**
	 * Metodo que devuelve la fecha del anticipo
	 * @return un date indicando la fecha del anticipo
	 */
	public Date getFechaAnticipo() {
		return fechaAnticipo;
	}

	/**
	 * Metodo para definir la fecha del anticipo
	 * @param fechaAnticipo parametro que define la fecha del anticipo
	 */
	public void setFechaAnticipo(Date fechaAnticipo) {
		this.fechaAnticipo = fechaAnticipo;
	}

	/**
	 * Metodo para devolver el valor del anticipo
	 * @return un numero indicando el valor del anticipo
	 */
	public double getValorAnticipo() {
		return valorAnticipo;
	}

	/**
	 * metodo para asiganr el valor del anticipo
	 * @param valorAnticipo parametro que define el valor del Anticipo
	 */
	public void setValorAnticipo(double valorAnticipo) {
		this.valorAnticipo = valorAnticipo;
	}

	/**
	 * Metodo para devolver el recibo de caja
	 * @return  el numero del recibo de caja
	 */
	public int getReciboCaja() {
		return reciboCaja;
	}

	/**
	 * Metodo para definir el recibo de caja
	 * @param reciboCaja parametro que define el numero del recibo de caja
	 */
	public void setReciboCaja(int reciboCaja) {
		this.reciboCaja = reciboCaja;
	}

	/**
	 * Metodo para devolver las observaciones realizadas en el anticipo
	 * @return una cadena de texto indicando las observaciones del anticipo
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * Metodo para definir las observaciones del anticipo
	 * @param observaciones parametro que define las observaciones que se hagan con respecto al anticipo
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Metodo para devolver una lista de medios de pago 
	 * @return devuelve una lista de tipo medio de pago
	 */
	public List<MedioPagoCliente> getMedioPago() {
		return medioPago;
	}

	/**
	 * Metodo para definir una lista de medios de pago
	 * @param medioPago parametro que  define la lista de objetos medios de pago
	 */
	public void setMedioPago(List<MedioPagoCliente> medioPago) {
		this.medioPago = medioPago;
	}

	/**
	 * Metodo para devolver el estado del anticipo
	 * @return uan cadena de texto indicando el estado del anticipo ya sea libre o usado
	 */
	public String getEstadoAnticipo() {
		return estadoAnticipo;
	}

	/**
	 * Metodo para definir el estado del anticipo
	 * @param estadoAnticipo parametro que define el estado del anticipo ya sea libre o usado
	 */
	public void setEstadoAnticipo(String estadoAnticipo) {
		this.estadoAnticipo = estadoAnticipo;
	}

	/**
	 * Metodo para devolver el estado de actividad del anticipo
	 * @return una cadena de texto indicando el estado de actividad del anticipo ya sea activo o inactivo
	 */
	public String getEstadoActividad() {
		return estadoActividad;
	}

	/**
	 * Metodo para definir el estado de actividad del anticipo
	 * @param estadoActividad parametro que define el estado de actividad del anticipo ya sea activo o inactivo
	 */
	public void setEstadoActividad(String estadoActividad) {
		this.estadoActividad = estadoActividad;
	}
}
