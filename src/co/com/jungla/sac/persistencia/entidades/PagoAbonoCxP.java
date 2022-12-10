package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: AbonoCXP
 *
 */
@Entity

public class PagoAbonoCxP implements Serializable {

	
	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idAbono;
	private double totalAbonado;
	private double totalDevoluciones;
	private double totalAnticipos;
	private double saldoPagar;
	private double pagoAbono;
	private String estadoAnular;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaGeneracion;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPago;
	private double valorPago1;
	private double valorPago2;
	private String observaciones;
	@ManyToOne
	private CuentaXPagar cuentaXPagar;
	@ManyToOne
	private MedioPagoProv medioPagoProv1;
	@ManyToOne
	private MedioPagoProv medioPagoProv2;

	private static final long serialVersionUID = 1L;

	public PagoAbonoCxP() {
		super();
	}

	public int getIdAbono() {
		return idAbono;
	}

	public void setIdAbono(int idAbono) {
		this.idAbono = idAbono;
	}

	public CuentaXPagar getCuentaXPagar() {
		return cuentaXPagar;
	}

	public void setCuentaXPagar(CuentaXPagar cuentaXPagar) {
		this.cuentaXPagar = cuentaXPagar;
	}

	public double getTotalAbonado() {
		return totalAbonado;
	}

	public void setTotalAbonado(double totalAbonado) {
		this.totalAbonado = totalAbonado;
	}

	public double getTotalDevoluciones() {
		return totalDevoluciones;
	}

	public void setTotalDevoluciones(double totalDevoluciones) {
		this.totalDevoluciones = totalDevoluciones;
	}

	public double getTotalAnticipos() {
		return totalAnticipos;
	}

	public void setTotalAnticipos(double totalAnticipos) {
		this.totalAnticipos = totalAnticipos;
	}

	public double getSaldoPagar() {
		return saldoPagar;
	}

	public void setSaldoPagar(double saldoPagar) {
		this.saldoPagar = saldoPagar;
	}

	public double getPagoAbono() {
		return pagoAbono;
	}

	public void setPagoAbono(double pagoAbono) {
		this.pagoAbono = pagoAbono;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getEstadoAnular() {
		return estadoAnular;
	}

	public void setEstadoAnular(String estadoAnular) {
		this.estadoAnular = estadoAnular;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public MedioPagoProv getMedioPagoProv1() {
		return medioPagoProv1;
	}

	public void setMedioPagoProv1(MedioPagoProv medioPagoProv1) {
		this.medioPagoProv1 = medioPagoProv1;
	}

	public MedioPagoProv getMedioPagoProv2() {
		return medioPagoProv2;
	}

	public void setMedioPagoProv2(MedioPagoProv medioPagoProv2) {
		this.medioPagoProv2 = medioPagoProv2;
	}

	public double getValorPago1() {
		return valorPago1;
	}

	public void setValorPago1(double valorPago1) {
		this.valorPago1 = valorPago1;
	}

	public double getValorPago2() {
		return valorPago2;
	}

	public void setValorPago2(double valorPago2) {
		this.valorPago2 = valorPago2;
	}
}
