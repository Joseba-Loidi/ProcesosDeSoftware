package es.deusto.spq.pojo;

import java.io.Serializable;

import es.deusto.spq.server.jdo.Genero;
import es.deusto.spq.server.jdo.Pelicula;

public class PeliculaData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String titulo;
	private int minutos;
	private int valoracion;
	private Genero genero;
	
	
	
	public PeliculaData(String codigo, String titulo, int minutos, int valoracion, Genero genero) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.minutos = minutos;
		this.valoracion = valoracion;
		this.genero = genero;
	}


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
	
	public static Pelicula toPelicula(PeliculaData peliculaData) {
	    Genero genero = Genero.valueOf(peliculaData.getGenero().name());
	    Pelicula pelicula = new Pelicula(peliculaData.getCodigo(), peliculaData.getTitulo(), peliculaData.getMinutos(),
	            peliculaData.getValoracion(), genero);
	    return pelicula;
	}


	@Override
	public String toString() {
		return "PeliculaData [codigo=" + codigo + ", titulo=" + titulo + ", genero=" + genero + ", minutos=" + minutos
				+ ", valoracion=" + valoracion + "]";
	}
	
}
