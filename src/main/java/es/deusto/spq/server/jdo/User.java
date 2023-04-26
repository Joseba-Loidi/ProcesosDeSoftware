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

@PersistenceCapable
public class User {
	@PrimaryKey
	String login=null;
	String password=null;
	String correo=null;
	
	@Persistent(mappedBy="user", dependentElement="true")
	@Join
	List<Pelicula> peliculas = new ArrayList<>();
	
	public User(String login, String password, String correo) {
		this.login = login;
		this.password = password;
		this.correo = correo;
	}
	
	public User() {
		this.login = "";
		this.password = "";
		this.correo = "";
	}

	public void alquilarPelicula(Pelicula pelicula) {
		peliculas.add(pelicula);
	}

	public void devolverPelicula(Pelicula pelicula) {
		peliculas.remove(pelicula);
	}

	public String getLogin() {
		return this.login;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	 
	 public List<Pelicula> getPeliculas() {
		return peliculas;
	}
	 
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password + ", correo=" + correo + ", peliculas=" + peliculas
				+ "]";
	}

}
