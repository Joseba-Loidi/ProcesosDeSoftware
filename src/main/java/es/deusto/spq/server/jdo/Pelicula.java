package es.deusto.spq.server.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Pelicula {
	@PrimaryKey
	private String codigo;
	private String titulo;
	private int minutos;
	private int valoracion;
	private Genero genero;
	
	public Pelicula() {
	}
	
	public Pelicula(String codigo, String titulo, int minutos, int valoracion, Genero genero) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.minutos = minutos;
		this.valoracion = valoracion;
		this.genero = genero;
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

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Pelicula [codigo=" + codigo + ", titulo=" + titulo + ", minutos=" + minutos + ", valoracion="
				+ valoracion + ", genero=" + genero + "]";
	}
	
	
	
	
}
