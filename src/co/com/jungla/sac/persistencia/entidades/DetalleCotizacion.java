package co.com.jungla.sac.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.*;

/**
 * clase entidad con los atributos principales que debe llevar el detalle de la cotizacion junto con los metodos get y set para devolver 
 * y definir la informacion de sus atributos  
 * @author Luis Fernando Pedroza T.
 * @version: 01/09/2016
 */
@Entity
public class DetalleCotizacion implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idDetalleCotizacion;
	private int cantidad;
	private double vlrUnitario;
	private double total;
	@ManyToOne
	private Cotizacion cotizacion;
	@ManyToOne
	private Articulo articulo;
	
	private static final long serialVersionUID = 1L;

	public DetalleCotizacion() {
		super();
	}

	/*metodos get y set de cada atributo del detalle cotizacion indicando la devolucion y asignacion de la informacion que contiene cada atributo*/
	public int getIdDetalleCotizacion() {
		return idDetalleCotizacion;
	}


	public void setIdDetalleCotizacion(int idDetalleCotizacion) {
		this.idDetalleCotizacion = idDetalleCotizacion;
	}


	public Cotizacion getCotizacion() {
		return cotizacion;
	}


	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}


	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getVlrUnitario() {
		return vlrUnitario;
	}

	public void setVlrUnitario(double vlrUnitario) {
		this.vlrUnitario = vlrUnitario;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
