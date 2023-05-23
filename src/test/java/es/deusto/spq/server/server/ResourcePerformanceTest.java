package es.deusto.spq.server.server;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.noconnor.junitperf.JUnitPerfRule;
import com.github.noconnor.junitperf.JUnitPerfTest;
import com.github.noconnor.junitperf.reporting.providers.HtmlReportGenerator;

import categories.PerformanceTest;
import es.deusto.spq.pojo.UserData;
import es.deusto.spq.server.Main;
import es.deusto.spq.server.jdo.Admin;
import es.deusto.spq.server.jdo.Genero;
import es.deusto.spq.server.jdo.Pelicula;
import es.deusto.spq.server.jdo.User;

@Category(PerformanceTest.class)
public class ResourcePerformanceTest {
	
	
	private static final PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
    
    private static HttpServer server;
    private WebTarget target;
    
    public PersistenceManager pm;
    public Transaction tx;
    

    @Rule
    public JUnitPerfRule perfTestRule = new JUnitPerfRule(new HtmlReportGenerator("target/junitperf/report.html"));

    @BeforeClass
    public static void prepareTests() throws Exception {
        // start the server
        server = Main.startServer();

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            pm.makePersistent(new User("Iñaki", "iñaki@gmail.com","Iñaki"));
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
    @JUnitPerfTest(threads = 1, durationMs = 5000)
    public void testRegisterUser() {
        UserData user = new UserData();
        user.setLogin(UUID.randomUUID().toString());
        user.setCorreo("juan@gmail.com");
        user.setPassword("1234");

        Response response = target.path("register")
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(user, MediaType.APPLICATION_JSON));

        assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
    }
    
    
    
    @Test
    @JUnitPerfTest(threads = 10, durationMs = 3000)
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
    @JUnitPerfTest(threads = 10, durationMs = 3000)
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
//    @JUnitPerfTest(threads = 1, durationMs = 3000)
//    public void testDeleteFilm() {
//    	Pelicula peli = new Pelicula();
//    	peli.setCodigo("codigo12");
//    	peli.setMinutos(10);
//    	peli.setTitulo("Test");
//    	peli.setValoracion(5);
//    	peli.setGenero(Genero.ACCION);
//    	
//    	try {
//            tx.begin();
//            pm.makePersistent(peli);
//            tx.commit();
//        } finally {
//            if (tx.isActive()) {
//                tx.rollback();
//            }
//            pm.close();
//        }	
//    	// Llamar al método deleteFilm para eliminar la película
//        Response response = target.path("codigo12")
//                .request()
//                .delete();
//        // Verificar que la respuesta sea exitosa (código 200)
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//
//    }
 
    
}
