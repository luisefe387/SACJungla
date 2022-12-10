package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * clase entidad para definir las caracteristicas y operaciones que tiene un anticipo o adelanto al proveedor
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Entity
public class AnticipoProveedor implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idAnticipoProveedor;
	private String identificacionProv;
	private String concepto;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAnticipo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaGeneracion;
	private double valorAnticipo;
	private int cxp;
	private int egreso;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaEgreso;
	private String observaciones;
	private String estadoAnticipo;
	private String estadoActividad;
	@ManyToOne
	private MedioPagoProv medioPagoProv;
	
	private static final long serialVersionUID = 1L;

	public AnticipoProveedor() {
		super();
	}

	/**
	 * Metodo para devolver el numero del anticipo 
	 * @return un numero de anticipo del proveedor
	 */
	public int getIdAnticipoProveedor() {
		return idAnticipoProveedor;
	}

	/**
	 * Metodo para para definir el numero del anticipo 
	 * @param idAnticipoProveedor parametro que define el numero de anticipo del proveedor
	 */
	public void setIdAnticipoProveedor(int idAnticipoProveedor) {
		this.idAnticipoProveedor = idAnticipoProveedor;
	}

	/**
	 * Metodo para retornar el concepto o motivo del anticipo
	 * @return una cadena de texto indicando el concepto del anticipo
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Metodo para definir el concepto del anticipo
	 * @param concepto parametro que define una cadena de texto indicando el concepto del anticipo
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
	 * Metodo para devolver la fecha del anticipo
	 * @return un Date indicando la fecha del anticipo
	 */
	public Date getFechaAnticipo() {
		return fechaAnticipo;
	}

	/**
	 * Metodo para definir la fecha del anticipo
	 * @param fechaAnticipo parametro que define la fecha que va a tener el anticipo
	 */
	public void setFechaAnticipo(Date fechaAnticipo) {
		this.fechaAnticipo = fechaAnticipo;
	}

	/**
	 * Metodo para devolver la fecha inmediata del anticipo
	 * @return un date que indica la fecha inmediata en que se creo el anticipo
	 */
	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	/**
	 * Metodo para definir la fecha de generacion del anticipo 
	 * @param fechaGeneracion parametro que define la fecha inmediata en que se creo el anticipo
	 */
	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	/**
	 * Metodo que devuelve el valor del anticipo
	 * @return un numero que indica el valor del anticipo
	 */
	public double getValorAnticipo() {
		return valorAnticipo;
	}

	/**
	 * Metodo para definir el valor del anticipo
	 * @param valorAnticipo parametro que define el valor del anticipo
	 */
	public void setValorAnticipo(double valorAnticipo) {
		this.valorAnticipo = valorAnticipo;
	}

	/**
	 * Metodo para devolver las observaciones del anticipo
	 * @return un texto con las observaciones realizadas acerca del anticipo
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * Metodo para definir las observaciones del anticipo
	 * @param observaciones parametro que define la informacion adicional del anticipo
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Metodo que devuelve el estado del anticipo
	 * @return un texto indicando el estado del anticipo ya sea pendiente o descontado
	 */
	public String getEstadoAnticipo() {
		return estadoAnticipo;
	}

	/**
	 * Metodo para definir el estado del anticipo
	 * @param estadoAnticipo parametro que define el estado del anticipo ya sea pendiente o descontado
	 */
	public void setEstadoAnticipo(String estadoAnticipo) {
		this.estadoAnticipo = estadoAnticipo;
	}

	/**
	 * Metodo que devuelve un numero de cuenta por pagar
	 * @return el numero de la cuenta por pagar sobre la que fue descontado el anticipo
	 */
	public int getCxp() {
		return cxp;
	}

	/**
	 * Metodo para definir una cuenta por pagar
	 * @param cxp parametro que define el numero de la cuenta por pagar sobre la que se va a descontar el anticipo
	 */
	public void setCxp(int cxp) {
		this.cxp = cxp;
	}

	/**
	 * Metodo para definir el numero de egreso
	 * @return el numero del egreso sobre la que fue utilizado el anticipo al realizarse un pago total de la cuenta por pagar
	 */
	public int getEgreso() {
		return egreso;
	}

	/**
	 * Metodo para asignar el numero del egreso
	 * @param egreso parametro que define el numero del egreso sobre el que se va utilizar el anticipo al pagar la totalidad de la cuenta por pagar
	 */
	public void setEgreso(int egreso) {
		this.egreso = egreso;
	}

	/**
	 * Metodo para devolver la fecha del egreso
	 * @return un date indicando la fecha del egreso en la que se desconto el anticipo
	 */
	public Date getFechaEgreso() {
		return fechaEgreso;
	}

	/**
	 * Metodo para definir la fecha del egreso
	 * @param fechaEgreso parametro que define la fecha del egreso en la que se va a descontar el anticipo
	 */
	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	/**
	 * Metodo que devuelve el estado de actividad del anticipo
	 * @return un texto indicando el estado de actividad del anticipo ya sea activo o inactivo
	 */
	public String getEstadoActividad() {
		return estadoActividad;
	}

	/**
	 * Metodo para definir el estado del anticipo
	 * @param estadoActividad parametro que define el estado del anticipo ya sea activo o inactivo
	 */
	public void setEstadoActividad(String estadoActividad) {
		this.estadoActividad = estadoActividad;
	}

	/**
	 * Metodo que devuelve la identificacion del proveedor
	 * @return el numero de identificacion del proveedor
	 */
	public String getIdentificacionProv() {
		return identificacionProv;
	}

	/**
	 * Metodo para asignar la identificacion del proveedor
	 * @param identificacionProv parametro que define el numero de identificacion del proveedor
	 */
	public void setIdentificacionProv(String identificacionProv) {
		this.identificacionProv = identificacionProv;
	}

	/**
	 * Metodo que devuelve el medio de pago utilizado para realizar el anticipo
	 * @return un objeto de tipo MedioPago, medio de pago que se utilizo para pagar al proveedor
	 */
	public MedioPagoProv getMedioPagoProv() {
		return medioPagoProv;
	}

	/**
	 * Metodo para definir el medio de pago utilzado para el anticipo
	 * @param medioPagoProv parametro que define el objeto medio de pago que se va utilzar para pagar al proveedor
	 */
	public void setMedioPagoProv(MedioPagoProv medioPagoProv) {
		this.medioPagoProv = medioPagoProv;
	}
}
