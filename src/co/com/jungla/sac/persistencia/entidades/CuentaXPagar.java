package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: CuentaXPagar
 *
 */
@Entity
public class CuentaXPagar implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idCuentaXPagar;
	private String identificacionProv;
	private String concepto;
	private int docSoporte;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCausacion;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPago;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaGeneracion;
	private double otros;
	private double subtotal;
	private double totalPagar;
	private String observaciones;
	private int compra;
	private String estado;
	@OneToMany(mappedBy = "cuentaXPagar")
	private List<PagoAbonoCxP> abonosCXP;
	
	private static final long serialVersionUID = 1L;

	public CuentaXPagar() {
		super();
	}

	public int getIdCuentaXPagar() {
		return idCuentaXPagar;
	}

	public void setIdCuentaXPagar(int idCuentaXPagar) {
		this.idCuentaXPagar = idCuentaXPagar;
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

	public Date getFechaCausacion() {
		return fechaCausacion;
	}

	public void setFechaCausacion(Date fechaCausacion) {
		this.fechaCausacion = fechaCausacion;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public double getOtros() {
		return otros;
	}

	public void setOtros(double otros) {
		this.otros = otros;
	}

	public double getTotalPagar() {
		return totalPagar;
	}

	public void setTotalPagar(double totalPagar) {
		this.totalPagar = totalPagar;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public List<PagoAbonoCxP> getAbonosCXP() {
		return abonosCXP;
	}

	public void setAbonosCXP(List<PagoAbonoCxP> abonosCXP) {
		this.abonosCXP = abonosCXP;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
