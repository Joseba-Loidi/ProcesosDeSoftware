package es.deusto.spq.server.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.UUID;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import categories.IntegrationTest;
import es.deusto.spq.pojo.UserData;
import es.deusto.spq.server.Main;
import es.deusto.spq.server.jdo.Admin;
import es.deusto.spq.server.jdo.Genero;
import es.deusto.spq.server.jdo.Pelicula;
import es.deusto.spq.server.jdo.User;

@Category(IntegrationTest.class)
public class ResourceIntegrationTest {
private static final PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
    
	public PersistenceManager pm = pmf.getPersistenceManager();
    private static HttpServer server;
    private WebTarget target;

    @BeforeClass
    public static void prepareTests() throws Exception {
        // start the server
        server = Main.startServer();

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            pm.makePersistent(new User("Iñaki1", "Iñaki@gmail.com", "Iñaki"));
            pm.makePersistent(new Admin("admin10","admin10"));
            pm.makePersistent(new Pelicula("codigo123", "Pelicula de prueba", 120, 7, Genero.AVENTURA));
            
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    @Before
    public void setUp() {
        // create the client
        Client c = ClientBuilder.newClient();
        target = c.target(Main.BASE_URI).path("resource");
    }

    @AfterClass
    public static void tearDownServer() throws Exception {
        server.shutdown();

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            pm.newQuery(User.class).deletePersistentAll();
            pm.newQuery(Pelicula.class).deletePersistentAll();
            pm.newQuery(Admin.class).deletePersistentAll();
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
    @Test
    public void testRegisterUser() {
        UserData userdata = new UserData();
        userdata.setLogin(UUID.randomUUID().toString());
        userdata.setPassword("Sancet");
        userdata.setCorreo("Sancet@gmail.com");

        Response response = target.path("register")
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(userdata, MediaType.APPLICATION_JSON));

        assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
    }
    @Test
    public void testRegistrarAdmin() {
    	Admin admin = new Admin();
    	admin.setLogin(UUID.randomUUID().toString());
    	admin.setPassword("admin6");
    	
    	Response response = target.path("adminRegister")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(admin, MediaType.APPLICATION_JSON));

        assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
    }
    
    @Test
    public void testAddPelicula() {
    	Pelicula peli = new Pelicula();
    	peli.setCodigo("codigo123");
    	peli.setMinutos(10);
    	peli.setTitulo("Test");
    	peli.setValoracion(8);
    	
    	Response response = target.path("addPelicula")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(peli, MediaType.APPLICATION_JSON));

        assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
    }
    
//    @Test
//    public void testDeleteFilm() {
//    	Pelicula peli = new Pelicula();
//    	peli.setCodigo("codigo123");
//    	peli.setMinutos(10);
//    	peli.setTitulo("Test");
//    	peli.setValoracion(5);
//    	peli.setGenero(Genero.ACCION);
//    	
//    	pm.makePersistent(peli);
//    	// Llamar al método deleteFilm para eliminar la película
//        Response response = target.path("codigo123")
//                .request()
//                .delete();
//        // Verificar que la respuesta sea exitosa (código 200)
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//
//        // Verificar que la película ha sido eliminada
//        assertNull(pm.getObjectById(Pelicula.class, "codigo123"));
//    }
//    @Test
//    public void testObtenerPeliculas() {
//        // Llamar al método obtenerPeliculas
//        Response response = target.path("getPeliculas")
//                .request()
//                .post(null); // No se envía ningún cuerpo en la solicitud
//
//        // Verificar que la respuesta sea exitosa (código 200)
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//
//        // Obtener la lista de películas de la respuesta
//        List<Pelicula> peliculasObtenidas = response.readEntity(new GenericType<List<Pelicula>>() {});
//
//        // Verificar que se hayan obtenido las películas correctas
//        assertEquals(1, peliculasObtenidas.size());
//    }
//    @Test
//    public void testObtenerUsuarios() {
//        // Llamar al método obtenerPeliculas
//        Response response = target.path("getUsuarios")
//                .request()
//                .post(null); // No se envía ningún cuerpo en la solicitud
//
//        // Verificar que la respuesta sea exitosa (código 200)
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//
//        // Obtener la lista de películas de la respuesta
//        List<Pelicula> usuariosObtenidos = response.readEntity(new GenericType<List<Pelicula>>() {});
//
//        // Verificar que se hayan obtenido las películas correctas
//        assertEquals(1, usuariosObtenidos.size());
//    }
//    @Test
//    public void testLogin() {
//        // Crear un objeto User con datos de usuario válidos
//        UserData user = new UserData();
//        user.setLogin("Iñaki");
//        user.setCorreo("Iñaki@gmail.com");
//        user.setPassword("Iñaki");
//        
//        // Llamar al método login
//        Response response = target.path("login")
//                .request()
//                .post(Entity.entity(user, MediaType.APPLICATION_JSON));
//
//        // Verificar que la respuesta sea exitosa (código 200)
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//    }
//    @Test
//    public void testLoginAdmin() {
//        Admin admin = new Admin();
//        admin.setLogin("admin6");
//        admin.setPassword("admin6");
//        
//        // Llamar al método loginAdmin
//        Response response = target.path("loginAdmin")
//                .request()
//                .post(Entity.entity(admin, MediaType.APPLICATION_JSON));
//
//        // Verificar que la respuesta sea exitosa (código 200)
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//    }

}
