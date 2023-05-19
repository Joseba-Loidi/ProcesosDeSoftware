package es.deusto.spq.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Cliente {

	protected static final Logger logger = LogManager.getLogger();

	private static final String USER = "dipina";
	private static final String PASSWORD = "dipina";
	private static final String CORREO = "dipina@gmail.com";

	private static final String SUPER_USER = "admin";
	private static final String S_PASSWORD = "admin";

	private static final String CODIGO = "193ja";
	private static final String TITULO = "Iker y los 7 enenitos";
	private static final int MINUTOS = 120;
	private static final int VALORACION = 8;
	private static final Genero GENERO = Genero.ACCION;

	private Client client;
	private static WebTarget webTarget;
	
	private Resource resource;
	
	public void setResource(Resource resource) {
        this.resource = resource;
    }
	public void setWebTarget(WebTarget web) {
        webTarget = web;
    }

	public Cliente(String hostname, String port) {
		
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
	}

	public boolean registerUser(String login, String password, String correo) {
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

	public void registerAdmin(String login, String password) {
		WebTarget registerAdminWebTarget = webTarget.path("adminRegister");
		Invocation.Builder invocationBuilder = registerAdminWebTarget.request(MediaType.APPLICATION_JSON);

		AdminData AdminData = new AdminData();
		AdminData.setLogin(login);
		AdminData.setPassword(password);
		Response response = invocationBuilder.post(Entity.entity(AdminData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		
		} else {
			logger.info("Admin correctly registered");
		}
	}
	
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
	
//	public static boolean deleteAlquiler(String codPelicula, String loginUser) {
//	    boolean deleted = false;
//	    WebTarget deleteAlquilerWebTarget = webTarget.path("deleteAlquiler");
//	    Invocation.Builder invocationBuilder = deleteAlquilerWebTarget.request(MediaType.APPLICATION_JSON);
//	    //Puede que haya que crear un objeto JSON JSONObject jsonInput = new JSONObject(); jsonInput.put("codPelicula", codPelicula); jsonInput.put("loginUser", loginUser); y pasarlo asi en el invocation builder. Hace falta libreria
//	    Response response = invocationBuilder.post(Entity.entity(codPelicula + ";" + loginUser, MediaType.APPLICATION_JSON));
//	    if (response.getStatus() != Status.OK.getStatusCode()) {
//	        logger.error("Error connecting with the server. Code: {}", response.getStatus());
//	    } else {
//	        logger.info("Alquiler correctly deleted");
//	        deleted = true;
//	    }
//	    return deleted;
//	}
	
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
//		exampleClient.registerUser(USER, PASSWORD, CORREO);
//		exampleClient.registerAdmin(SUPER_USER, S_PASSWORD);
//
//		exampleClient.eliminarPelicula(CODIGO, TITULO, MINUTOS, VALORACION, GENERO);
//
//		exampleClient.sayMessage(USER, PASSWORD, "This is a test!...");
//		exampleClient.sayMessage(USER, PASSWORD, "VIDEOCLUB");
//
//		exampleClient.login(USER, PASSWORD);

//		VentanaRegistro v1 = new VentanaRegistro();
//		v1.setVisible(true);
		VentanaInicioSesion v1 = new VentanaInicioSesion();
		v1.setVisible(true);
//		VentanaAdmin v2 = new VentanaAdmin();
	//	v2.setVisible(true);
	}
	
	 public boolean crearAlquiler(String codPelicula, String loginUser) {
		   WebTarget crearAlquilerWebTarget = webTarget.path("crearAlquiler");
		   Invocation.Builder invocationBuilder = crearAlquilerWebTarget.request(MediaType.APPLICATION_JSON);

		   Alquiler alquiler = new Alquiler(codPelicula, loginUser);

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