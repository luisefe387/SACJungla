package co.com.jungla.sac.presentacion.ventanas;

import java.io.InputStream;

public class ReporteCatalogoProductos {
	
	private int codigoArticulo;
	private String nombreArticulo;
	private String unidad;
	private String presentacion;
	private String descripcion;
	private String referencia;
	private String lineaArticulo;
	private InputStream urlFoto;
	private String precio;
	
	
	public ReporteCatalogoProductos(int codigoArticulo, String nombreArticulo,
			String unidad, String presentacion, String descripcion,
			String referencia, String lineaArticulo, InputStream urlFoto, String precio) {
		this.codigoArticulo = codigoArticulo;
		this.nombreArticulo = nombreArticulo;
		this.unidad = unidad;
		this.presentacion = presentacion;
		this.descripcion = descripcion;
		this.referencia = referencia;
		this.lineaArticulo = lineaArticulo;
		this.urlFoto = urlFoto;
		this.precio = precio;
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


	public String getPresentacion() {
		return presentacion;
	}


	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getReferencia() {
		return referencia;
	}


	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}


	public String getLineaArticulo() {
		return lineaArticulo;
	}

	public void setLineaArticulo(String lineaArticulo) {
		this.lineaArticulo = lineaArticulo;
	}


	public InputStream getUrlFoto() {
		return urlFoto;
	}


	public void setUrlFoto(InputStream urlFoto) {
		this.urlFoto = urlFoto;
	}


	public String getPrecio() {
		return precio;
	}


	public void setPrecio(String precio) {
		this.precio = precio;
	}
}
