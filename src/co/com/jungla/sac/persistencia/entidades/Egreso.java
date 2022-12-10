package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: CuentaXPagar
 *
 */
@Entity
public class Egreso implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idEgreso;
	private String identificacionProv;
	private String concepto;
	private int docSoporte;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPago;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaGeneracion;
	private double otros;
	private double subtotal;
	private double totalPagado;
	private String observaciones;
	private int compra;
	private double valorPago1;
	private double valorPago2;
	private int idCuentaXPagar;
	private String estadoActividad;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAnulacion;
	@ManyToOne
	private MedioPagoProv medioPagoProv1;
	@ManyToOne
	private MedioPagoProv medioPagoProv2;
	
	private static final long serialVersionUID = 1L;

	public Egreso() {
		super();
	}

	public int getIdEgreso() {
		return idEgreso;
	}

	public void setIdEgreso(int idEgreso) {
		this.idEgreso = idEgreso;
	}

	public String getIdentificacionProv() {
		return identificacionProv;
	}

	public void setIdentificacionProv(String identificacionProv) {
		this.identificacionProv = identificacionProv;
	}

	public int getCompra() {
		return compra;
	}

	public void setCompra(int compra) {
		this.compra = compra;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public int getDocSoporte() {
		return docSoporte;
	}

	public void setDocSoporte(int docSoporte) {
		this.docSoporte = docSoporte;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public double getOtros() {
		return otros;
	}

	public void setOtros(double otros) {
		this.otros = otros;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotalPagado() {
		return totalPagado;
	}

	public void setTotalPagado(double totalPagado) {
		this.totalPagado = totalPagado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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

	public int getIdCuentaXPagar() {
		return idCuentaXPagar;
	}

	public void setIdCuentaXPagar(int idCuentaXPagar) {
		this.idCuentaXPagar = idCuentaXPagar;
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
