package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: LineaArticulos
 *
 */
@Entity

public class LineaArticulos implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int codigo;
	private String nombreL;
	private String estado;
	@OneToMany(mappedBy = "lineaArticulos")
	private List<Articulo> articulos;
	
	private static final long serialVersionUID = 1L;

	public LineaArticulos() {
		super();
	}

	public LineaArticulos(String nombreL, int codigo){
		this.nombreL=nombreL;
		this.codigo=codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombreL() {
		return nombreL;
	}

	public void setNombreL(String nombreL) {
		this.nombreL = nombreL;
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return nombreL;
	}
}
