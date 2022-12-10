package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: CompraArticulos
 *
 */
@Entity

public class KardexElectronico implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idKardex;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	private String movimiento;
	private int nroDocumento;
	private int cantidadEntrada;
	private double costoUEntrada;
	private double costoTEntrada;
	private int cantidadSalida;
	private double costoUSalida;
	private double costoTSalida;
	private int cantidadSaldo;
	private double costoUSaldo;
	private double costoTSaldo;
	private String proveedorUltimaCompra;
	@ManyToOne
	private Articulo articulo;

	private static final long serialVersionUID = 1L;

	public KardexElectronico() {
		super();
	}

	public int getIdKardex() {
		return idKardex;
	}

	public void setIdKardex(int idKardex) {
		this.idKardex = idKardex;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}

	public int getCantidadEntrada() {
		return cantidadEntrada;
	}

	public void setCantidadEntrada(int cantidadEntrada) {
		this.cantidadEntrada = cantidadEntrada;
	}

	public int getCantidadSalida() {
		return cantidadSalida;
	}

	public void setCantidadSalida(int cantidadSalida) {
		this.cantidadSalida = cantidadSalida;
	}

	public double getCostoUEntrada() {
		return costoUEntrada;
	}

	public void setCostoUEntrada(double costoUEntrada) {
		this.costoUEntrada = costoUEntrada;
	}

	public double getCostoTEntrada() {
		return costoTEntrada;
	}

	public void setCostoTEntrada(double costoTEntrada) {
		this.costoTEntrada = costoTEntrada;
	}

	public double getCostoUSalida() {
		return costoUSalida;
	}

	public void setCostoUSalida(double costoUSalida) {
		this.costoUSalida = costoUSalida;
	}

	public double getCostoTSalida() {
		return costoTSalida;
	}

	public void setCostoTSalida(double costoTSalida) {
		this.costoTSalida = costoTSalida;
	}

	public int getCantidadSaldo() {
		return cantidadSaldo;
	}

	public void setCantidadSaldo(int cantidadSaldo) {
		this.cantidadSaldo = cantidadSaldo;
	}

	public double getCostoUSaldo() {
		return costoUSaldo;
	}

	public void setCostoUSaldo(double costoUSaldo) {
		this.costoUSaldo = costoUSaldo;
	}

	public double getCostoTSaldo() {
		return costoTSaldo;
	}

	public void setCostoTSaldo(double costoTSaldo) {
		this.costoTSaldo = costoTSaldo;
	}

	public int getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
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
}
