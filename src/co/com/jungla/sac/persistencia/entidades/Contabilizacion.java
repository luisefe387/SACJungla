package co.com.jungla.sac.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * clase entidad con las caracteristicas principales para realizar una contabilizacion de todas las operaciones junto con los metodos get y set para devolver y definir la informacion de sus atributos  
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Entity
public class Contabilizacion implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idContabilizacion;
	/**
	 * fechaGeneracion, fecha en la se ejectua la contabilizacion de manera inmediata
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaGeneracion;
	/**
	 * codigoCuenta, atributo que indica el codigo de cuenta segun el plan unico de cuentas como medio de identificacion
	 */
	private int codigoCuenta;
	/**
	 * nombreCuenta, indica el nombre de la cuenta segun el plan unico de cuentas
	 */
	private String nombreCuenta;
	/**
	 * saldoDebito, indica que el valor de la cuenta se agrega si es de naturaleza debito
	 */
	private double saldoDebito;
	/**
	 * saldoCredito, indica que el valor de la cuenta se agrega si es de naturaleza credito
	 */
	private double saldoCredito;
	
	private static final long serialVersionUID = 1L;

	public Contabilizacion() {
		super();
	}
	
	/*metodos get y set de cada atributo de la contabilizacion indicando la devolucion y asignacion de la informacion que contiene cada 
	 * atributo*/
	
	public int getIdContabilizacion() {
		return idContabilizacion;
	}

	public void setIdContabilizacion(int idContabilizacion) {
		this.idContabilizacion = idContabilizacion;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public int getCodigoCuenta() {
		return codigoCuenta;
	}

	public void setCodigoCuenta(int codigoCuenta) {
		this.codigoCuenta = codigoCuenta;
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	public double getSaldoDebito() {
		return saldoDebito;
	}

	public void setSaldoDebito(double saldoDebito) {
		this.saldoDebito = saldoDebito;
	}

	public double getSaldoCredito() {
		return saldoCredito;
	}

	public void setSaldoCredito(double saldoCredito) {
		this.saldoCredito = saldoCredito;
	}
}
