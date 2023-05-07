//package es.deusto.spq.client;
//
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.Invocation;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.Status;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mockito;
//
//
//
//public class ClienteTest {
//	
//    private Client client;
//    private WebTarget webTarget;
//
//    @Before
//    public void setUp() {
//        String hostname = "localhost";
//        String port = "8080";
//        client = ClientBuilder.newClient();
//        webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
//    }
//
//    @Test
//    public void testConstructor() {
//        assertNotNull(client);
//        assertNotNull(webTarget);
//    }
//    
////    @Test
////    public void testRegisterAdmin() {
////        // Given
////        String login = "admin";
////        String password = "password";
////        WebTarget registerAdminWebTarget = webTarget.path("adminRegister");
////        Invocation.Builder invocationBuilder = registerAdminWebTarget.request(MediaType.APPLICATION_JSON);
////
////        AdminData adminData = new AdminData();
////        adminData.setLogin(login);
////        adminData.setPassword(password);
////
////        // When
////        Response response = invocationBuilder.post(Entity.entity(adminData, MediaType.APPLICATION_JSON));
////
////        // Then
////        assertEquals(Status.OK.getStatusCode(), response.getStatus());
////    }
//
//    
//}
//
//
