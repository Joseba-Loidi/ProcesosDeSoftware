package es.deusto.spq.server.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Pelicula {
	@PrimaryKey
	String codigo;
	String titulo;
	int minutos;
	int valoracion;

}
