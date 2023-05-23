package es.deusto.spq.server.jdo;

import java.util.Set;


import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Representa un usuario.
 */
@PersistenceCapable
public class User {
    @PrimaryKey
    String login = null;
    String password = null;
    String correo = null;
    
    /**
     * Crea una instancia de User con los atributos especificados.
     * @param login El nombre de usuario.
     * @param password La contraseña del usuario.
     * @param correo El correo electrónico del usuario.
     */
    public User(String login, String password, String correo) {
        this.login = login;
        this.password = password;
        this.correo = correo;
    }
    
    /**
     * Crea una instancia de User con valores predeterminados para los atributos.
     */
    public User() {
        this.login = "";
        this.password = "";
        this.correo = "";
    }
    
    /**
     * Obtiene el nombre de usuario.
     * @return El nombre de usuario.
     */
    public String getLogin() {
        return this.login;
    }
    
    /**
     * Establece el nombre de usuario.
     * @param login El nombre de usuario a establecer.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return this.password;
    }
    
    /**
     * Establece la contraseña del usuario.
     * @param password La contraseña del usuario a establecer.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Obtiene el correo electrónico del usuario.
     * @return El correo electrónico del usuario.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del usuario.
     * @param correo El correo electrónico del usuario a establecer.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "User [login=" + login + ", password=" + password + ", correo=" + correo + "]";
    }
}
