package co.com.jungla.sac.persistencia.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Entity implementation class for Entity: TipoCliente
 *
 */
@Entity
public class TipoCliente implements Serializable {

	
	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idTipoCliente;
	private String descripcion;
	@OneToMany(mappedBy = "tipoClientes")
	private List<Cliente> clientes;
	
	private static final long serialVersionUID = 1L;

	public TipoCliente() {
		super();
	}
	
	public TipoCliente(String descripcion, int idTipoCliente){
		this.descripcion=descripcion;
		this.idTipoCliente = idTipoCliente;
	}

	public int getIdTipoCliente() {
		return idTipoCliente;
	}

	public void setIdTipoCliente(int idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	@Override
	public String toString() {
		return descripcion;
	}
}
