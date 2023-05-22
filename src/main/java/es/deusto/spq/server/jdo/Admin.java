package es.deusto.spq.server.jdo;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

/**
 * Clase que representa a un administrador.
 */
@PersistenceCapable
public class Admin {
    @PrimaryKey
    String login = null;
    String password = null;
    
    /**
     * Crea un nuevo objeto Admin con el nombre de usuario y la contraseña proporcionados.
     *
     * @param login    El nombre de usuario del administrador.
     * @param password La contraseña del administrador.
     */
    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
    }
    
    /**
     * Constructor vacío de la clase Admin.
     */
    public Admin() {
        this.login = "";
        this.password = "";
    }
    
    /**
     * Obtiene el nombre de usuario del administrador.
     *
     * @return El nombre de usuario.
     */
    public String getLogin() {
        return this.login;
    }
    
    /**
     * Obtiene la contraseña del administrador.
     *
     * @return La contraseña.
     */
    public String getPassword() {
        return this.password;
    }
    
    /**
     * Establece la contraseña del administrador.
     *
     * @param password La contraseña a establecer.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Establece el nombre de usuario del administrador.
     *
     * @param login El nombre de usuario a establecer.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Devuelve una representación en forma de cadena del objeto Admin.
     *
     * @return Una cadena que representa el objeto Admin.
     */
    @Override
    public String toString() {
        return "Admin [login=" + login + ", password=" + password + "]";
    }
}
