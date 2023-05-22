package es.deusto.spq.server.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

/**
 * Clase que representa un alquiler de una película.
 */
@PersistenceCapable
public class Alquiler {
    @PrimaryKey
    protected String codPelicula;
    @PrimaryKey
    protected String loginUser;

    /**
     * Crea un nuevo objeto Alquiler con el código de la película y el nombre de usuario proporcionados.
     *
     * @param codPelicula El código de la película.
     * @param loginUser   El nombre de usuario.
     */
    public Alquiler(String codPelicula, String loginUser) {
        this.codPelicula = codPelicula;
        this.loginUser = loginUser;
    }
    
    /**
     * Constructor vacío de la clase Alquiler.
     */
    public Alquiler() {
        
    }

    /**
     * Obtiene el código de la película del alquiler.
     *
     * @return El código de la película.
     */
    public String getCodPelicula() {
        return codPelicula;
    }

    /**
     * Establece el código de la película del alquiler.
     *
     * @param codPelicula El código de la película.
     */
    public void setCodPelicula(String codPelicula) {
        this.codPelicula = codPelicula;
    }

    /**
     * Obtiene el nombre de usuario del alquiler.
     *
     * @return El nombre de usuario.
     */
    public String getLoginUser() {
        return loginUser;
    }

    /**
     * Establece el nombre de usuario del alquiler.
     *
     * @param loginUser El nombre de usuario.
     */
    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    /**
     * Devuelve una representación en forma de cadena del objeto Alquiler.
     *
     * @return Una cadena que representa el objeto Alquiler.
     */
    @Override
    public String toString() {
        return "Alquiler [codPelicula=" + codPelicula + ", loginUser=" + loginUser + "]";
    }
}


