package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: UnidadMedida
 *
 */
@Entity

public class UnidadMedida implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int codigo;
	private String nombre;
	private String abreviatura;
	@OneToMany(mappedBy = "unidadMedida")
	private List<Articulo> articulos;
	
	private static final long serialVersionUID = 1L;

	public UnidadMedida() {
		super();
	}
	
	public UnidadMedida(String nombre, int codigo){
		this.nombre=nombre;
		this.codigo=codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
}
