package es.deusto.spq.pojo;

public class UserData {

    private String login;
    private String password;
    private String correo;

    /**
     * Crea una instancia de UserData con el nombre de usuario, la contraseña y el correo electrónico proporcionados.
     *
     * @param login    El nombre de usuario.
     * @param password La contraseña.
     * @param correo   El correo electrónico.
     */
    public UserData(String login, String password, String correo) {
        this.login = login;
        this.password = password;
        this.correo = correo;
    }

    /**
     * Crea una instancia de UserData sin parámetros.
     */
    public UserData() {
        // requerido por la serialización
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param login El nombre de usuario.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Obtiene la contraseña.
     *
     * @return La contraseña.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Establece la contraseña.
     *
     * @param password La contraseña.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el correo electrónico.
     *
     * @return El correo electrónico.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico.
     *
     * @param correo El correo electrónico.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Devuelve una representación en cadena de la clase UserData.
     *
     * @return Una representación en cadena de la clase UserData.
     */
    @Override
    public String toString() {
        return "UserData [login=" + login + ", password=" + password + ", correo=" + correo + "]";
    }
}
