package es.deusto.spq.server.jdo;

import javax.jdo.annotations.PersistenceCapable;

import javax.jdo.annotations.PrimaryKey;
/**
 * Representa una película.
 */
@PersistenceCapable
public class Pelicula {
	@PrimaryKey
	private String codigo;
	private String titulo;
	private int minutos;
	private int valoracion;
	private Genero genero;
	
	 /**
     * Crea una instancia de Pelicula.
     */
	
	public Pelicula() {
	}
	
	/**
     * Crea una instancia de Pelicula con los atributos especificados.
     * @param codigo El código de la película.
     * @param titulo El título de la película.
     * @param minutos La duración en minutos de la película.
     * @param valoracion La valoración de la película.
     * @param genero El género de la película.
     */
	
	public Pelicula(String codigo, String titulo, int minutos, int valoracion, Genero genero) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.minutos = minutos;
		this.valoracion = valoracion;
		this.genero = genero;
	}
	
	 /**
     * Obtiene el código de la película.
     * @return El código de la película.
     */
	public String getCodigo() {
		return codigo;
	}
	
	/**
     * Establece el código de la película.
     * @param codigo El código de la película a establecer.
     */

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	/**
     * Obtiene el título de la película.
     * @return El título de la película.
     */

	public String getTitulo() {
		return titulo;
	}

	/**
     * Establece el título de la película.
     * @param titulo El título de la película a establecer.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene la duración en minutos de la película.
     * @return La duración en minutos de la película.
     */
    public int getMinutos() {
        return minutos;
    }

    /**
     * Establece la duración en minutos de la película.
     * @param minutos La duración en minutos de la película a establecer.
     */
    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    /**
     * Obtiene la valoración de la película.
     * @return La valoración de la película.
     */
    public int getValoracion() {
        return valoracion;
    }

    /**
     * Establece la valoración de la película.
     * @param valoracion La valoración de la película a establecer.
     */
    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    /**
     * Obtiene el género de la película.
     * @return El género de la película.
     */
    public Genero getGenero() {
        return genero;
    }

    /**
     * Establece el género de la película.
     * @param genero El género de la película a establecer.
     */
    public void setGenero(Genero genero) {
        this.genero = genero;
    }

	@Override
	public String toString() {
		return "Pelicula [codigo=" + codigo + ", titulo=" + titulo + ", minutos=" + minutos + ", valoracion="
				+ valoracion + ", genero=" + genero + "]";
	}
	
	
	
	
}
