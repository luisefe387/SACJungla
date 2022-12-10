package co.com.jungla.sac.persistencia.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Proveedor
 *
 */
@Entity
public class Proveedor extends Persona implements Serializable {

	private String tipoProveedor;
	private String abreviatura;
	private String regimen;
	private String estado;

	@OneToMany(mappedBy = "proveedores")
	private List<CompraArticulos> compraArticulos;
	
	private static final long serialVersionUID = 1L;

	public Proveedor() {
		super();
	}
	
	public String getTipoProveedor() {
		return tipoProveedor;
	}

	public void setTipoProveedor(String tipoProveedor) {
		this.tipoProveedor = tipoProveedor;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getRegimen() {
		return regimen;
	}

	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}

	public List<CompraArticulos> getCompraArticulos() {
		return compraArticulos;
	}

	public void setCompraArticulos(List<CompraArticulos> compraArticulos) {
		this.compraArticulos = compraArticulos;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
