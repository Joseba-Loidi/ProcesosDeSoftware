package es.deusto.spq.pojo;

public class AlquilerData {
    protected String codPelicula;
    protected String LoginUser;

    /**
     * Crea una instancia de AlquilerData con el código de la película y el nombre de usuario de inicio de sesión proporcionados.
     *
     * @param codPelicula El código de la película.
     * @param loginUser   El nombre de usuario de inicio de sesión.
     */
    public AlquilerData(String codPelicula, String loginUser) {
        super();
        this.codPelicula = codPelicula;
        this.LoginUser = loginUser;
    }

    /**
     * Crea una instancia de AlquilerData sin parámetros.
     */
    public AlquilerData() {

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
     * Obtiene el nombre de usuario de inicio de sesión del alquiler.
     *
     * @return El nombre de usuario de inicio de sesión.
     */
    public String getLoginUser() {
        return LoginUser;
    }

    /**
     * Establece el nombre de usuario de inicio de sesión del alquiler.
     *
     * @param loginUser El nombre de usuario de inicio de sesión.
     */
    public void setLoginUser(String loginUser) {
        LoginUser = loginUser;
    }

    /**
     * Devuelve una representación en cadena de la clase AlquilerData.
     *
     * @return Una representación en cadena de la clase AlquilerData.
     */
    @Override
    public String toString() {
        return "Alquiler [codPelicula=" + codPelicula + ", LoginUser=" + LoginUser + "]";
    }
}
