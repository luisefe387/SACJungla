package co.com.jungla.sac.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.FetchType.EAGER;


/**
 * clase entidad con los atributos principales que debe llevar un cliente junto con los metodos get y set para devolver y definir la infomracion de sus atributos  
 * @author Luis Fernando Pedroza T.
 * @version: 18/09/2016
 */
@Entity
public class Cliente extends Persona implements Serializable{

	private String nombreComercial;
	private String contacto;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaNacimiento;
	/**
	 * el atributo estado indica el estado de actividad del cliente ya sea activo o inactivo
	 */
	private String estado;
	@ManyToOne
	private TipoCliente tipoClientes;
	@OneToMany(mappedBy = "clientes", fetch = EAGER)
	private List<VentaArticulos> ventaArticulos;

	private static final long serialVersionUID = 1L;

	public Cliente() {
		super();
	}
	
	/*metodos get y set de cada atributo del cliente indicando la devolucion y asignacion de la informacion que contiene cada atributo*/
	
	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}


	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}


	public TipoCliente getTipoClientes() {
		return tipoClientes;
	}

	public void setTipoClientes(TipoCliente tipoClientes) {
		this.tipoClientes = tipoClientes;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<VentaArticulos> getVentaArticulos() {
		return ventaArticulos;
	}

	public void setVentaArticulos(List<VentaArticulos> ventaArticulos) {
		this.ventaArticulos = ventaArticulos;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
