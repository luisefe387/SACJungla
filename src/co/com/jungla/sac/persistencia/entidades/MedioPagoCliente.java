package co.com.jungla.sac.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Entity implementation class for Entity: FormaPago
 *
 */
@Entity
public class MedioPagoCliente implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idMedioPagoCliente;
	private String nombreMedioPago;
	private double valor;
	private int docReferencia;
	private String franquicia;
	@ManyToOne
	private Banco banco;
	@ManyToOne
	private ReciboCaja reciboCaja;
	@ManyToOne
	private AnticipoCliente anticipoCliente;
	
	private static final long serialVersionUID = 1L;

	public MedioPagoCliente() {
		super();
	}

	
	public MedioPagoCliente(String nombreMedioPago, double valor, int docReferencia,Banco banco,String franquicia) {
		super();
		this.nombreMedioPago = nombreMedioPago;
		this.valor = valor;
		this.docReferencia = docReferencia;
		this.franquicia = franquicia;
		this.banco = banco;
	}

	public int getIdMedioPagoCliente() {
		return idMedioPagoCliente;
	}

	public void setIdMedioPagoCliente(int idMedioPagoCliente) {
		this.idMedioPagoCliente = idMedioPagoCliente;
	}


	public ReciboCaja getReciboCaja() {
		return reciboCaja;
	}

	public void setReciboCaja(ReciboCaja reciboCaja) {
		this.reciboCaja = reciboCaja;
	}

	public String getNombreMedioPago() {
		return nombreMedioPago;
	}


	public void setNombreMedioPago(String nombreMedioPago) {
		this.nombreMedioPago = nombreMedioPago;
	}


	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getDocReferencia() {
		return docReferencia;
	}

	public void setDocReferencia(int docReferencia) {
		this.docReferencia = docReferencia;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public String getFranquicia() {
		return franquicia;
	}

	public void setFranquicia(String franquicia) {
		this.franquicia = franquicia;
	}

	public AnticipoCliente getAnticipoCliente() {
		return anticipoCliente;
	}

	public void setAnticipoCliente(AnticipoCliente anticipoCliente) {
		this.anticipoCliente = anticipoCliente;
	}
}
