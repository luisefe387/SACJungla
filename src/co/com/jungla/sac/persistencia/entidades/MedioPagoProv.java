package co.com.jungla.sac.persistencia.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Entity implementation class for Entity: FormaPago
 *
 */
@Entity
public class MedioPagoProv implements Serializable {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = IDENTITY)
	private int idMedioPagoProv;
	private String descripcion;
	private String nombreCuenta;
	@OneToMany(mappedBy = "medioPagoProv")
	private List<CompraArticulos> compraArticulos;
	@OneToMany(mappedBy = "medioPagoProv")
	private List<AnticipoProveedor> anticipoProveedor;
	@OneToMany(mappedBy = "medioPagoProv1")
	private List<PagoAbonoCxP> abonoCXP1;
	@OneToMany(mappedBy = "medioPagoProv2")
	private List<PagoAbonoCxP> abonoCXP2;
	@OneToMany(mappedBy = "medioPagoProv1")
	private List<Egreso> gastoYOEgreso1;
	@OneToMany(mappedBy = "medioPagoProv2")
	private List<Egreso> gastoYOEgreso2;
	
	private static final long serialVersionUID = 1L;

	public MedioPagoProv() {
		super();
	}

	public MedioPagoProv(String descripcion, int idMedioPagoProv){
		this.descripcion=descripcion;
		this.idMedioPagoProv=idMedioPagoProv;
	}

	public int getIdMedioPagoProv() {
		return idMedioPagoProv;
	}

	public void setIdMedioPagoProv(int idMedioPagoProv) {
		this.idMedioPagoProv = idMedioPagoProv;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public List<CompraArticulos> getCompraArticulos() {
		return compraArticulos;
	}

	public void setCompraArticulos(List<CompraArticulos> compraArticulos) {
		this.compraArticulos = compraArticulos;
	}

	public List<PagoAbonoCxP> getAbonoCXP2() {
		return abonoCXP2;
	}

	public void setAbonoCXP2(List<PagoAbonoCxP> abonoCXP2) {
		this.abonoCXP2 = abonoCXP2;
	}

	public List<Egreso> getGastoYOEgreso1() {
		return gastoYOEgreso1;
	}

	public void setGastoYOEgreso1(List<Egreso> gastoYOEgreso1) {
		this.gastoYOEgreso1 = gastoYOEgreso1;
	}

	public List<Egreso> getGastoYOEgreso2() {
		return gastoYOEgreso2;
	}

	public void setGastoYOEgreso2(List<Egreso> gastoYOEgreso2) {
		this.gastoYOEgreso2 = gastoYOEgreso2;
	}

	public List<PagoAbonoCxP> getAbonoCXP1() {
		return abonoCXP1;
	}

	public void setAbonoCXP1(List<PagoAbonoCxP> abonoCXP1) {
		this.abonoCXP1 = abonoCXP1;
	}

	public List<AnticipoProveedor> getAnticipoProveedor() {
		return anticipoProveedor;
	}

	public void setAnticipoProveedor(List<AnticipoProveedor> anticipoProveedor) {
		this.anticipoProveedor = anticipoProveedor;
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	@Override
	public String toString() {
		return descripcion;
	}
}
