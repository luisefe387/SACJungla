package co.com.jungla.sac.presentacion.ventanas;

public class ReporteDetalle {
	
	private int codigoArticulo;
	private String nombreArticulo;
	private String unidad;
	private int cantidad;
	private String vlrUnitario;
	private String vlrTotal;
	private String grupo;
	private String costoUnitario;
	private String costoTotal;
	
	private String nombreMedioPago;
	private String valorMedioPago;
	private String docReferencia;
	private String nombreBanco;
	private String nombreFranquicia;
	
	
	public ReporteDetalle(int codigoArticulo, String nombreArticulo,
			String unidad, int cantidad, String vlrUnitario, String vlrTotal) {
		this.codigoArticulo = codigoArticulo;
		this.nombreArticulo = nombreArticulo;
		this.unidad = unidad;
		this.cantidad = cantidad;
		this.vlrUnitario = vlrUnitario;
		this.vlrTotal = vlrTotal;
	}

	public ReporteDetalle(int codigoArticulo, String nombreArticulo,
			String unidad, int cantidad, String grupo, String costoUnitario,
			String costoTotal) {
		super();
		this.codigoArticulo = codigoArticulo;
		this.nombreArticulo = nombreArticulo;
		this.unidad = unidad;
		this.cantidad = cantidad;
		this.grupo = grupo;
		this.costoUnitario = costoUnitario;
		this.costoTotal = costoTotal;
	}

	public ReporteDetalle(String nombreMedioPago, String valorMedioPago,
			String docReferencia, String nombreBanco, String nombreFranquicia) {
		super();
		this.nombreMedioPago = nombreMedioPago;
		this.valorMedioPago = valorMedioPago;
		this.docReferencia = docReferencia;
		this.nombreBanco = nombreBanco;
		this.nombreFranquicia = nombreFranquicia;
	}

	public int getCodigoArticulo() {
		return codigoArticulo;
	}


	public void setCodigoArticulo(int codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}


	public String getNombreArticulo() {
		return nombreArticulo;
	}


	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}


	public String getUnidad() {
		return unidad;
	}


	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public String getVlrUnitario() {
		return vlrUnitario;
	}


	public void setVlrUnitario(String vlrUnitario) {
		this.vlrUnitario = vlrUnitario;
	}


	public String getVlrTotal() {
		return vlrTotal;
	}


	public void setVlrTotal(String vlrTotal) {
		this.vlrTotal = vlrTotal;
	}


	public String getGrupo() {
		return grupo;
	}


	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}


	public String getCostoUnitario() {
		return costoUnitario;
	}


	public void setCostoUnitario(String costoUnitario) {
		this.costoUnitario = costoUnitario;
	}


	public String getCostoTotal() {
		return costoTotal;
	}


	public void setCostoTotal(String costoTotal) {
		this.costoTotal = costoTotal;
	}

	public String getNombreMedioPago() {
		return nombreMedioPago;
	}

	public void setNombreMedioPago(String nombreMedioPago) {
		this.nombreMedioPago = nombreMedioPago;
	}

	public String getValorMedioPago() {
		return valorMedioPago;
	}

	public void setValorMedioPago(String valorMedioPago) {
		this.valorMedioPago = valorMedioPago;
	}

	public String getDocReferencia() {
		return docReferencia;
	}

	public void setDocReferencia(String docReferencia) {
		this.docReferencia = docReferencia;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public String getNombreFranquicia() {
		return nombreFranquicia;
	}

	public void setNombreFranquicia(String nombreFranquicia) {
		this.nombreFranquicia = nombreFranquicia;
	}
}
