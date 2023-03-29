package es.deusto.spq.pojo;

import es.deusto.spq.server.jdo.Genero;

public class PeliculaData {
	
	private String codigo;
	private String titulo;
	private Genero genero;
	private int minutos;
	private int valoracion;
	
	
	public PeliculaData() {
		
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public Genero getGenero() {
		return genero;
	}


	public void setGenero(Genero genero) {
		this.genero = genero;
	}


	public int getMinutos() {
		return minutos;
	}


	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}


	public int getValoracion() {
		return valoracion;
	}


	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}


	@Override
	public String toString() {
		return "PeliculaData [codigo=" + codigo + ", titulo=" + titulo + ", genero=" + genero + ", minutos=" + minutos
				+ ", valoracion=" + valoracion + "]";
	}
	
	

	
}
