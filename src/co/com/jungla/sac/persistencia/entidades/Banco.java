package co.com.jungla.sac.persistencia.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.CascadeType.ALL;

/**
 * clase entidad con los atributos principales que debe llevar un banco junto con los metodos get y set para devolver y definir la infomracion de sus atributos  
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Entity
public class Banco implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idBanco;
	/**
	 * El tipo de cuenta indica que se puede elegir entre una cuennta de ahorros o una cuenta corriente
	 */
	private String tipoCuenta; 
	/**
	 * la entidad indica el nombre del banco
	 */
	private String entidad;
	private String nroCuenta;
	private String nombreTitular; 
	/**
	 * el estado indica valga la redundancia el estado de actividad del banco ya sea activo o inactivo
	 */
	private String estado;
	/**
	 * El saldo actual indica el valor total de lo que ha recaudado el banco
	 */
	private double saldoActual;
	@OneToMany(mappedBy = "banco", cascade = ALL)
	private List<MedioPagoCliente> medioPago;
	
	private static final long serialVersionUID = 1L;

	public Banco() {
		super();
	}

	/**
	 * Constructor para definir el nombre del banco y su codigo
	 * @param entidad parametro de tipo string que define el nombre de la entidad financiera
	 * @param idBanco parametro de tipo numerico que define el codigo del banco
	 */
	public Banco(String entidad, int idBanco) {
		this.entidad = entidad;
		this.idBanco = idBanco;
	}
	
	/*metodos get y set de cada atributo del banco indicando la devolucion y asignacion de la informacion que contiene cada atributo*/
	
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual;
	}

	public int getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
	}
	
	public List<MedioPagoCliente> getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(List<MedioPagoCliente> medioPago) {
		this.medioPago = medioPago;
	}
	
	//Metodo que devuelve el nombre de la entidad del banco en tipo String
	@Override
	public String toString() {
		return entidad;
	}
}
