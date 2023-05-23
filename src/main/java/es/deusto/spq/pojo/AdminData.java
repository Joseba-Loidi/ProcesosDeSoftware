package es.deusto.spq.pojo;

public class AdminData {
    private String login;
    private String password;
    
    /**
     * Construye una nueva instancia de la clase AdminData con el inicio de sesión y contraseña especificados.
     * 
     * @param login    el inicio de sesión asociado al administrador
     * @param password la contraseña asociada al administrador
     */
    public AdminData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Constructor por defecto requerido para la serialización.
     */
    public AdminData() {
        // requerido para la serialización
    }

    /**
     * Obtiene el inicio de sesión asociado al administrador.
     * 
     * @return el inicio de sesión del administrador
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Establece el inicio de sesión para el administrador.
     * 
     * @param login el inicio de sesión a establecer
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Obtiene la contraseña asociada al administrador.
     * 
     * @return la contraseña del administrador
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Establece la contraseña para el administrador.
     * 
     * @param password la contraseña a establecer
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Devuelve una representación en forma de cadena de la instancia de AdminData.
     * 
     * @return una cadena que representa los datos administrativos
     */
    public String toString() {
        return "[login=" + login + ", password=" + password + "]";
    }
}
