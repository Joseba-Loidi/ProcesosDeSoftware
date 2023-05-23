package es.deusto.spq.pojo;

import java.io.Serializable;

import es.deusto.spq.server.jdo.Genero;
import es.deusto.spq.server.jdo.Pelicula;

/**
 * La clase PeliculaData representa los datos de una película.
 */
public class PeliculaData implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String codigo;
    private String titulo;
    private int minutos;
    private int valoracion;
    private Genero genero;

    /**
     * Crea una instancia de PeliculaData con el código, el título, la duración en minutos, la valoración y el género proporcionados.
     *
     * @param codigo     El código de la película.
     * @param titulo     El título de la película.
     * @param minutos    La duración en minutos de la película.
     * @param valoracion La valoración de la película.
     * @param genero     El género de la película.
     */
    public PeliculaData(String codigo, String titulo, int minutos, int valoracion, Genero genero) {
        super();
        this.codigo = codigo;
        this.titulo = titulo;
        this.minutos = minutos;
        this.valoracion = valoracion;
        this.genero = genero;
    }

    /**
     * Crea una instancia de PeliculaData sin parámetros.
     */
    public PeliculaData() {

    }

    /**
     * Obtiene el código de la película.
     *
     * @return El código de la película.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código de la película.
     *
     * @param codigo El código de la película.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el título de la película.
     *
     * @return El título de la película.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título de la película.
     *
     * @param titulo El título de la película.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el género de la película.
     *
     * @return El género de la película.
     */
    public Genero getGenero() {
        return genero;
    }

    /**
     * Establece el género de la película.
     *
     * @param genero El género de la película.
     */
    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    /**
     * Obtiene la duración en minutos de la película.
     *
     * @return La duración en minutos de la película.
     */
    public int getMinutos() {
        return minutos;
    }

    /**
     * Establece la duración en minutos de la película.
     *
     * @param minutos La duración en minutos de la película.
     */
    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    /**
     * Obtiene la valoración de la película.
     *
     * @return La valoración de la película.
     */
    public int getValoracion() {
        return valoracion;
    }

    /**
     * Establece la valoración de la película.
     *
     * @param valoracion La valoración de la película.
     */
    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    /**
     * Convierte una instancia de PeliculaData a una instancia de Pelicula.
     *
     * @param peliculaData La instancia de PeliculaData a convertir.
     * @return Una instancia de Pelicula convertida.
     */
    public static Pelicula toPelicula(PeliculaData peliculaData) {
        Genero genero = Genero.valueOf(peliculaData.getGenero().name());
        Pelicula pelicula = new Pelicula(peliculaData.getCodigo(), peliculaData.getTitulo(), peliculaData.getMinutos(),
                peliculaData.getValoracion(), genero);
        return pelicula;
    }

    /**
     * Devuelve una representación en cadena de la clase PeliculaData.
     *
     * @return Una representación en cadena de la clase PeliculaData.
     */
    @Override
    public String toString() {
        return "PeliculaData [codigo=" + codigo + ", titulo=" + titulo + ", genero=" + genero + ", minutos=" + minutos
                + ", valoracion=" + valoracion + "]";
    }
}
