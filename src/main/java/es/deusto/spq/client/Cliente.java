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

import es.deusto.spq.pojo.DirectMessage;
import es.deusto.spq.pojo.MessageData;
import es.deusto.spq.pojo.PeliculaData;
import es.deusto.spq.pojo.UserData;
import es.deusto.spq.server.jdo.Genero;
import es.deusto.spq.server.jdo.Pelicula;
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

	public Cliente(String hostname, String port) {
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
	}

	public static void registerUser(String login, String password, String correo) {
		WebTarget registerUserWebTarget = webTarget.path("register");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);

		UserData userData = new UserData();
		userData.setLogin(login);
		userData.setPassword(password);
		userData.setCorreo(correo);
		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		} else {
			logger.info("User correctly registered" + userData.toString());
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

	public static boolean login(String login, String password) {
		boolean inicio = false;
		logger.info("HOLA CLIENTE");
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
			logger.info("Login de usuario: " + login + " realizado correctamente");
			inicio = true;
		}
		return inicio;
	}

	public void addPelicula(String codigo, String titulo, int minutos, int valoracion, Genero genero) {

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
		}
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
			logger.info("Film correctly registered" + pelis.toString());
			return pelis;
		}
	}

	public void sayMessage(String login, String password, String message) {
		WebTarget sayHelloWebTarget = webTarget.path("sayMessage");
		Invocation.Builder invocationBuilder = sayHelloWebTarget.request(MediaType.APPLICATION_JSON);

		DirectMessage directMessage = new DirectMessage();
		UserData userData = new UserData();
		userData.setLogin(login);
		userData.setPassword(password);

		directMessage.setUserData(userData);

		MessageData messageData = new MessageData();
		messageData.setMessage(message);
		directMessage.setMessageData(messageData);

		Response response = invocationBuilder.post(Entity.entity(directMessage, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		} else {
			String responseMessage = response.readEntity(String.class);
			logger.info("* Message coming from the server: '{}'", responseMessage);
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
//		exampleClient.addPelicula(CODIGO, TITULO, MINUTOS, VALORACION, GENERO);
//
//		exampleClient.sayMessage(USER, PASSWORD, "This is a test!...");
//		exampleClient.sayMessage(USER, PASSWORD, "VIDEOCLUB");
//
//		exampleClient.login(USER, PASSWORD);

//		VentanaRegistro v1 = new VentanaRegistro();
//		v1.setVisible(true);
		VentanaInicioSesion v1 = new VentanaInicioSesion();
		v1.setVisible(true);
		VentanaAdmin v2 = new VentanaAdmin();
		v2.setVisible(true);
	}
}