package es.deusto.spq.client;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.ws.rs.client.Client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import es.deusto.spq.pojo.PeliculaData;
import es.deusto.spq.pojo.UserData;
import es.deusto.spq.server.Resource;
import es.deusto.spq.server.jdo.Alquiler;
import es.deusto.spq.server.jdo.Genero;
import es.deusto.spq.server.jdo.Pelicula;
import es.deusto.spq.server.jdo.User;
import ventanas.VentanaAdmin;
import ventanas.VentanaInicioSesion;
//import ventanas.VentanaRegistro;
import es.deusto.spq.pojo.AdminData;
import es.deusto.spq.pojo.AlquilerData;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Cliente {

	protected static final Logger logger = LogManager.getLogger();


	private Client client;
	private static WebTarget webTarget;
	
	
	/**
	 * Crea una instancia de Cliente que se conecta a un servidor REST en el host y puerto especificados.
	 *
	 * @param hostname El nombre del host del servidor REST.
	 * @param port     El número de puerto del servidor REST.
	 */
	
	public Cliente(String hostname, String port) {
		
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
	}
	
	/**
	 * Registra un nuevo usuario recibiendo su login, correo y contraseña.
	 *
	 * @param login    Nombre de usuario.
	 * @param password Contraseña.
	 * @param correo   Correo.
	 * @return {@code true} Si se registra correctamente, {@code false} si hay algún problema.
	 */

	public static boolean registerUser(String login, String password, String correo) {
		WebTarget registerUserWebTarget = webTarget.path("register");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);

		UserData userData = new UserData();
		userData.setLogin(login);
		userData.setPassword(password);
		userData.setCorreo(correo);
		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
			return false;
		} else {
			logger.info("User correctly registered" + userData.toString());
			return true;
		}
	}
	
	/**
	 * Registra un nuevo Administrador recibiendo su login y contraseña.
	 *
	 * @param login    Nombre de usuario.
	 * @param password Contraseña.
	 * @return {@code true} Si se registra correctamente, {@code false} si hay algún problema.
	 */

	public static boolean registerAdmin(String login, String password) {
		WebTarget registerAdminWebTarget = webTarget.path("adminRegister");
		Invocation.Builder invocationBuilder = registerAdminWebTarget.request(MediaType.APPLICATION_JSON);

		AdminData AdminData = new AdminData();
		AdminData.setLogin(login);
		AdminData.setPassword(password);
		Response response = invocationBuilder.post(Entity.entity(AdminData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
			return false;
		} else {
			logger.info("Admin correctly registered");
			return true;
		}
	}
	/**
	 * Recibe el login de un usuario y lo borra de la BBDD
	 *
	 * @param login    Nombre de usuario.
	 * @return {@code true} Si se elimina correctamente, {@code false} si hay algún problema.
	 */
	
	public static boolean deleteUser(String login) {
		boolean b = false;
		WebTarget deleteUserWebTarget = webTarget.path("deleteUser");
		Invocation.Builder invocationBuilder = deleteUserWebTarget.request(MediaType.APPLICATION_JSON);

		
		Response response = invocationBuilder.post(Entity.entity(login, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		} else {
			logger.info("User correctly deleted");
			b = true;
		}
		return b;
	}
	/**
	 * Recibe el codigo de una película y el login de un usuario. Busca el alquiler correspondiente y lo borra de la BBDD
	 * @param codPelicula código de la película.
	 * @param loginUser   Nombre de usuario.
	 * @return {@code true} Si se elimina correctamente, {@code false} si hay algún problema.
	 */
	
	public static boolean borrarAlquiler(String codPelicula, String loginUser) {
	    WebTarget borrarAlquilerWebTarget = webTarget.path("borrarAlquiler/{codPelicula}/{loginUser}")
	        .resolveTemplate("codPelicula", codPelicula)
	        .resolveTemplate("loginUser", loginUser);
	    Invocation.Builder invocationBuilder = borrarAlquilerWebTarget.request();

	    Response response = invocationBuilder.delete();
	    if (response.getStatus() != Status.OK.getStatusCode()) {
	        logger.error("Error connecting with the server. Code: {}", response.getStatus());
	        return false;
	    } else {
	        logger.info("Alquiler deleted: {} - {}", codPelicula, loginUser);
	        return true;
	    }
	}
	
	/**
	 * recibe login y contraseña de un usuario, y si está en la BBDD accede a la ventana principal.
	 *
	 * @param login    Nombre de usuario.
	 * @param password Contraseña.
	 * @return {@code true} Si entra correctamente, {@code false} si hay algún problema.
	 */
	
	public static boolean login(String login, String password) {
		boolean inicio = false;
		WebTarget registerAdminWebTarget = webTarget.path("login");
		// registerAdminWebTarget = registerAdminWebTarget.queryParam(login)
		// .queryParam(password);
		Invocation.Builder invocationBuilder = registerAdminWebTarget.request(MediaType.APPLICATION_JSON);

		UserData userData = new UserData();
		userData.setLogin(login);
		userData.setPassword(password);
		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));

		// Response response = invocationBuilder.get();

		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());

		} else {
			logger.info("Login de usuario " + login + " realizado correctamente");
			inicio = true;
		}
		return inicio;
			
	}
	
	/**
	 * recibe un nombre y busca al usuario en la BBDD.
	 *
	 * @param nombre    Nombre de usuario.
	 * @return {@code true} Si entra correctamente, {@code false} si hay algún problema.
	 */
	
	public static User getLogin(String nombre) {
		WebTarget getLogin = webTarget.path("getLogin");
		Invocation.Builder invocationBuilder = getLogin.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(nombre, MediaType.TEXT_PLAIN)); // Agregar el parámetro al cuerpo de la petición
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
			return null;
		} else {
			GenericType<User> listType = new GenericType<User>(){};
            User user = response.readEntity(listType);
			return user;
		}
	}
	
	/**
	 * recibe login y contraseña de un administrador, y si está en la BBDD accede a la ventana admin.
	 *
	 * @param login    Nombre de usuario.
	 * @param password Contraseña.
	 * @return {@code true} Si entra correctamente, {@code false} si hay algún problema.
	 */
	
	
	public static boolean loginAdmin(String login, String password) {
		boolean inicio = false;
		WebTarget registerAdminWebTarget = webTarget.path("loginAdmin");

		Invocation.Builder invocationBuilder = registerAdminWebTarget.request(MediaType.APPLICATION_JSON);

		AdminData adminData = new AdminData();
		adminData.setLogin(login);
		adminData.setPassword(password);
		Response response = invocationBuilder.post(Entity.entity(adminData, MediaType.APPLICATION_JSON));

		// Response response = invocationBuilder.get();

		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());

		} else {
			logger.info("Login de admin: " + login + " realizado correctamente");
			inicio = true;
		}
		return inicio;
			
	}
	
	/**
	 * Agrega una película con los datos proporcionados.
	 *
	 * @param codigo     El código de la película.
	 * @param titulo     El título de la película.
	 * @param minutos    La duración en minutos de la película.
	 * @param valoracion La valoración de la película.
	 * @param genero     El género de la película.
	 * @return {@code true} si la película se agregó correctamente, {@code false} en caso contrario.
	 */

	public static boolean addPelicula(String codigo, String titulo, int minutos, int valoracion, Genero genero) {
		boolean inicio = false;
		WebTarget registerAdminWebTarget = webTarget.path("addPelicula");
		Invocation.Builder invocationBuilder = registerAdminWebTarget.request(MediaType.APPLICATION_JSON);

		PeliculaData PeliculaData = new PeliculaData();
		PeliculaData.setCodigo(codigo);
		PeliculaData.setTitulo(titulo);
		PeliculaData.setMinutos(minutos);
		PeliculaData.setValoracion(valoracion);
		PeliculaData.setGenero(genero);

		
		Response response = invocationBuilder.post(Entity.entity(PeliculaData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		} else {
			logger.info("Film correctly registered");
			inicio = true;
		}
		return inicio;
	}
	
	/**
	 * Elimina una película con los datos proporcionados.
	 *
	 * @param codigo     El código de la película.
	 * @param titulo     El título de la película.
	 * @param minutos    La duración en minutos de la película.
	 * @param valoracion La valoración de la película.
	 * @param genero     El género de la película.
	 * @return {@code true} si la película se agregó correctamente, {@code false} en caso contrario.
	 */
	
	public static boolean eliminarPelicula(String codigo, String titulo, int minutos, int valoracion, Genero genero) {
		boolean inicio = false;
		
		PeliculaData peliculaData = new PeliculaData();
		peliculaData.setCodigo(codigo);
		peliculaData.setTitulo(titulo);
		peliculaData.setMinutos(minutos);
		peliculaData.setValoracion(valoracion);
		peliculaData.setGenero(genero);

		System.out.println(peliculaData);
		Response response1 = webTarget.path(codigo).request(MediaType.APPLICATION_JSON).delete();
		
		if (response1.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response1.getStatus());
		} else {
			logger.info("Film correctly deleted");
			inicio = true;
		}
		return inicio;
	}

	/**
	 * Obtiene una lista de películas.
	 *
	 * @return Una lista de películas o {@code null} si ocurrió un error al conectar con el servidor.
	 */

	public static List<Pelicula> obtenerPelis() {
		WebTarget getPeliculaWebTarget = webTarget.path("getPeliculas");
		Invocation.Builder invocationBuilder = getPeliculaWebTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(null);
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
			return null;
		} else {
			GenericType<List<Pelicula>> listType = new GenericType<List<Pelicula>>(){};
            List<Pelicula> pelis = response.readEntity(listType);
			return pelis;
		}
	}
	
	/**
	 * Obtiene una lista de usuarios.
	 *
	 * @return Una lista de usuarios o {@code null} si ocurrió un error al conectar con el servidor.
	 */
	
	public static List<User> obtenerUsuarios() {
		WebTarget getUsuariosWebTarget = webTarget.path("getUsuarios");
		Invocation.Builder invocationBuilder = getUsuariosWebTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(null);
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
			return null;
		} else {
			GenericType<List<User>> listType = new GenericType<List<User>>(){};
            List<User> usuarios = response.readEntity(listType);
			return usuarios;
		}
	}
	
	/**
	 * Filtra una película por nombre.
	 *
	 * @param nombre El nombre de la película a filtrar.
	 * @return La película filtrada o {@code null} si ocurrió un error al conectar con el servidor.
	 */
	
	public static Pelicula filtrarNombre(String nombre) {
		 webTarget = webTarget.path("filtrarNombre");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(nombre, MediaType.TEXT_PLAIN)); // Agregar el parámetro al cuerpo de la petición
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
			return null;
		} else {
			GenericType<Pelicula> listType = new GenericType<Pelicula>(){};
            Pelicula peli = response.readEntity(listType);
			return peli;
		}
	}
	
	/**
	 * Filtra películas por género.
	 *
	 * @param genero El género utilizado para filtrar las películas.
	 * @return Una lista de películas filtradas por género o {@code null} si ocurrió un error al conectar con el servidor.
	 */
	
	public static List<Pelicula> filtrarGenero(Genero genero) {
		WebTarget getFiltrarNombre = webTarget.path("filtrarGenero");
		Invocation.Builder invocationBuilder = getFiltrarNombre.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(genero, MediaType.TEXT_PLAIN)); // Agregar el parámetro al cuerpo de la petición
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
			return null;
		} else {
			GenericType<List<Pelicula>> listType = new GenericType<List<Pelicula>>(){};
            List<Pelicula> pelis = response.readEntity(listType);
			return pelis;
		}
	}
	
	/**
	 * Filtra películas por valoración.
	 *
	 * @param valoracion La valoración para filtrar las películas.
	 * @return Una lista de películas filtradas por valoración o {@code null} si ocurrió un error al conectar con el servidor.
	 */
	
	public static List<Pelicula> filtrarValoracion(int valoracion) {
		WebTarget getFiltrarValoracion = webTarget.path("filtrarValoracion");
		Invocation.Builder invocationBuilder = getFiltrarValoracion.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(valoracion, MediaType.TEXT_PLAIN)); // Agregar el parámetro al cuerpo de la petición
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
			return null;
		} else {
			GenericType<List<Pelicula>> listType = new GenericType<List<Pelicula>>(){};
            List<Pelicula> pelis = response.readEntity(listType);
			return pelis;
		}
	}
	
	/**
	 * Busca las peliculas alquiladas de un usuario.
	 *
	 * @param user Usuario del que buscar los alquileres.
	 * @return Una lista de películas alquiladas por el usuario o {@code null} si ocurrió un error al conectar con el servidor.
	 */
	
	public static List<Pelicula> filtrarUsuario(String user) {
		
		WebTarget getFiltrarUsuario = webTarget.path("filtrarUsuario");
		
		Invocation.Builder invocationBuilder = getFiltrarUsuario.request(MediaType.APPLICATION_JSON);
		
		System.out.println(user.toString());
		Response response = invocationBuilder.post(Entity.entity(user, MediaType.TEXT_PLAIN)); // Agregar el parámetro al cuerpo de la petición
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
			return null;
		} else {
			GenericType<List<Pelicula>> listType = new GenericType<List<Pelicula>>(){};
            List<Pelicula> pelis = response.readEntity(listType);
			return pelis;
		}
	}
	

	public static void main(String[] args) {
		if (args.length != 2) {
			logger.info("Use: java Client.Client [host] [port]");
			System.exit(0);
		}

		String hostname = args[0];
		String port = args[1];

		Cliente exampleClient = new Cliente(hostname, port);
//		v1.setVisible(true);
		VentanaInicioSesion v1 = new VentanaInicioSesion();
		v1.setVisible(true);
//		VentanaAdmin v2 = new VentanaAdmin();
	//	v2.setVisible(true);
	}
	
	/**
	 * Crea un nuevo alquiler para una película y un usuario específicos.
	 *
	 * @param codPelicula El código de la película a alquilar.
	 * @param loginUser   El nombre de usuario del usuario que realiza el alquiler.
	 * @return {@code true} si el alquiler se crea correctamente, {@code false} en caso contrario.
	 */
	
	
	 public static boolean crearAlquiler(String codPelicula, String loginUser) {
		   WebTarget crearAlquilerWebTarget = webTarget.path("crearAlquiler");
		   Invocation.Builder invocationBuilder = crearAlquilerWebTarget.request(MediaType.APPLICATION_JSON);

		   AlquilerData alquiler = new AlquilerData(codPelicula, loginUser);

		   Response response = invocationBuilder.post(Entity.entity(alquiler, MediaType.APPLICATION_JSON));
		   if (response.getStatus() != Status.OK.getStatusCode()) {
		       logger.error("Error connecting with the server. Code: {}", response.getStatus());
		       return false;
		   } else {
		       logger.info("Alquiler created: {}", alquiler);
		       return true;
		   }
		}
}