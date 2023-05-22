package es.deusto.spq.client;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resources;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import es.deusto.spq.pojo.AdminData;
import es.deusto.spq.pojo.AlquilerData;
import es.deusto.spq.pojo.PeliculaData;
import es.deusto.spq.pojo.UserData;
import es.deusto.spq.server.Main;
import es.deusto.spq.server.Resource;
import es.deusto.spq.server.jdo.Genero;
import es.deusto.spq.server.jdo.Pelicula;
import es.deusto.spq.server.jdo.User;
import junit.framework.Assert;


public class ClienteTest {
	
	@Mock
    private Client clientMock;

	@Mock(answer=Answers.RETURNS_DEEP_STUBS)
    private WebTarget webTarget;

    @Mock
    private Resource resourceMock;
    
    @Mock
    private Invocation.Builder invocationBuilderMock;

    @Mock
    private Response responseMock;
    
    @Captor
    private ArgumentCaptor<Entity<UserData>> userDataEntityCaptor;
    
    @Captor
    private ArgumentCaptor<Entity<AdminData>> adminDataEntityCaptor;
    
    @Captor
    private ArgumentCaptor<Entity<AlquilerData>> alquilerDataEntityCaptor;
    
    @Captor
    private ArgumentCaptor<Entity<PeliculaData>> peliculaDataEntityCaptor;
    
    @Captor
    private ArgumentCaptor<Entity<String>> stringDataEntityCaptor;
    
    private Cliente cliente;
    
    
    private PersistenceManager persistenceManager;
    private Query<?> query;
    private Transaction tx;
    private ArrayList<Pelicula> listaPelicula = new ArrayList<>();
    

    @Before
    public void setup() {
    	MockitoAnnotations.openMocks(this);
    	
        try (MockedStatic<ClientBuilder> clientBuilder = Mockito.mockStatic(ClientBuilder.class)) {
            clientBuilder.when(ClientBuilder::newClient).thenReturn(clientMock);
            when(clientMock.target("http://localhost:8080/rest/resource")).thenReturn(webTarget);

            cliente = new Cliente("localhost", "8080");
        }
    }
    
    @Test
  public void testRegister() {
    	when(webTarget.path("register")).thenReturn(webTarget);

        Response response = Response.ok().build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
        assertTrue(cliente.registerUser("test-login", "passwd", "email"));

        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(userDataEntityCaptor.capture());
        assertEquals("test-login", userDataEntityCaptor.getValue().getEntity().getLogin());
        assertEquals("passwd", userDataEntityCaptor.getValue().getEntity().getPassword());
        assertEquals("email", userDataEntityCaptor.getValue().getEntity().getCorreo());
  }
    
    @Test
    public void testLogin() {
        when(webTarget.path("login")).thenReturn(webTarget);

        // Crear una respuesta simulada exitosa
        Response successResponse = Response.ok().build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(successResponse);

        // Llamar al método de prueba
        assertTrue(cliente.login("test-login", "passwd"));

        // Verificar que se haya llamado a post con los datos correctos
        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(userDataEntityCaptor.capture());
        assertEquals("test-login", userDataEntityCaptor.getValue().getEntity().getLogin());
        assertEquals("passwd", userDataEntityCaptor.getValue().getEntity().getPassword());
    }
    
    @Test
    public void testLoginInvalidCredentials() {
        when(webTarget.path("login")).thenReturn(webTarget);

        // Crear una respuesta simulada de credenciales no válidas
        Response errorResponse = Response.status(Response.Status.UNAUTHORIZED).build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(errorResponse);

        // Llamar al método de prueba
        assertFalse(cliente.login("test-login", "passwd"));

        // Verificar que se haya llamado a post con los datos correctos
        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(userDataEntityCaptor.capture());
        assertEquals("test-login", userDataEntityCaptor.getValue().getEntity().getLogin());
        assertEquals("passwd", userDataEntityCaptor.getValue().getEntity().getPassword());
    }
    
    @Test
    public void testLoginServerError() {
        when(webTarget.path("login")).thenReturn(webTarget);

        // Crear una respuesta simulada de error de servidor
        Response errorResponse = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(errorResponse);

        // Llamar al método de prueba
        assertFalse(cliente.login("test-login", "passwd"));

        // Verificar que se haya llamado a post con los datos correctos
        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(userDataEntityCaptor.capture());
        assertEquals("test-login", userDataEntityCaptor.getValue().getEntity().getLogin());
        assertEquals("passwd", userDataEntityCaptor.getValue().getEntity().getPassword());
    }
    
    @Test
    public void testLoginAdmin() {
        when(webTarget.path("loginAdmin")).thenReturn(webTarget);

        // Crear una respuesta simulada exitosa
        Response successResponse = Response.ok().build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(successResponse);

        // Llamar al método de prueba
        assertTrue(cliente.loginAdmin("test-login", "passwd"));

        // Verificar que se haya llamado a post con los datos correctos
        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(argThat(entity -> {
            AdminData adminData = (AdminData) entity.getEntity();
            return adminData.getLogin().equals("test-login") && adminData.getPassword().equals("passwd");
        }));
    }
    
    @Test
    public void testObtenerPelisServerError() {
        when(webTarget.path("getPeliculas")).thenReturn(webTarget);

        // Crear una respuesta simulada de error de servidor
        Response errorResponse = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any())).thenReturn(errorResponse);

        // Llamar al método de prueba
        List<Pelicula> resultado = cliente.obtenerPelis();

        // Verificar que se haya llamado a post sin enviar una entidad
        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(null);

        // Verificar el resultado
        assertNull(resultado);
    }
    
    @Test
    public void testRegisterUserWithError() {
        when(webTarget.path("register")).thenReturn(webTarget);

        Response response = Response.serverError().build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
        assertFalse(cliente.registerUser("test-login", "passwd", "email"));
        
        
        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(userDataEntityCaptor.capture());
        assertEquals("test-login", userDataEntityCaptor.getValue().getEntity().getLogin());
        assertEquals("passwd", userDataEntityCaptor.getValue().getEntity().getPassword());
        assertEquals("email", userDataEntityCaptor.getValue().getEntity().getCorreo());
    }
    
    @Test
    public void testRegisterAdmin() {
      	when(webTarget.path("adminRegister")).thenReturn(webTarget);

          Response response = Response.ok().build();
          when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
          assertTrue(cliente.registerAdmin("test-login", "passwd"));

          verify(webTarget.request(MediaType.APPLICATION_JSON)).post(adminDataEntityCaptor.capture());
          assertEquals("test-login", adminDataEntityCaptor.getValue().getEntity().getLogin());
          assertEquals("passwd", adminDataEntityCaptor.getValue().getEntity().getPassword());
          
    }
    
    @Test
    public void testRegisterAdminUserWithError() {
        when(webTarget.path("adminRegister")).thenReturn(webTarget);

        Response response = Response.serverError().build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
        assertFalse(cliente.registerAdmin("test-login", "passwd"));
        
        
        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(adminDataEntityCaptor.capture());
        assertEquals("test-login", adminDataEntityCaptor.getValue().getEntity().getLogin());
        assertEquals("passwd", adminDataEntityCaptor.getValue().getEntity().getPassword());

    }

    
    @Test
    public void testCrearAlquiler() {
    	when(webTarget.path("crearAlquiler")).thenReturn(webTarget);

        Response response = Response.ok().build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
        assertTrue(cliente.crearAlquiler("1", "1"));

        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(alquilerDataEntityCaptor.capture());
        assertEquals("1", alquilerDataEntityCaptor.getValue().getEntity().getCodPelicula());
        assertEquals("1", alquilerDataEntityCaptor.getValue().getEntity().getLoginUser());
    }
    
    public void testCrearAlquilerError() {
    	when(webTarget.path("crearAlquiler")).thenReturn(webTarget);

        Response response = Response.serverError().build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
        assertTrue(cliente.crearAlquiler("1", "1"));

        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(alquilerDataEntityCaptor.capture());
        assertEquals("1", alquilerDataEntityCaptor.getValue().getEntity().getCodPelicula());
        assertEquals("1", alquilerDataEntityCaptor.getValue().getEntity().getLoginUser());
    }
    
    @Test
    public void testAddPelicula() {
    	when(webTarget.path("addPelicula")).thenReturn(webTarget);

        Response response = Response.ok().build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
        assertTrue(cliente.addPelicula("1", "Pelicula", 0, 0, Genero.ACCION));

        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(peliculaDataEntityCaptor.capture());
        assertEquals("1", peliculaDataEntityCaptor.getValue().getEntity().getCodigo());
        assertEquals("Pelicula", peliculaDataEntityCaptor.getValue().getEntity().getTitulo());
        assertEquals(0, peliculaDataEntityCaptor.getValue().getEntity().getMinutos());
        assertEquals(0, peliculaDataEntityCaptor.getValue().getEntity().getValoracion());
        assertEquals(Genero.ACCION, peliculaDataEntityCaptor.getValue().getEntity().getGenero());
    }
    
    @Test
    public void testAddPeliculaError() {
    	when(webTarget.path("addPelicula")).thenReturn(webTarget);

        Response response = Response.ok().build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
        assertTrue(cliente.addPelicula("1", "Pelicula", 0, 0, Genero.ACCION));

        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(peliculaDataEntityCaptor.capture());
        assertEquals("1", peliculaDataEntityCaptor.getValue().getEntity().getCodigo());
        assertEquals("Pelicula", peliculaDataEntityCaptor.getValue().getEntity().getTitulo());
        assertEquals(0, peliculaDataEntityCaptor.getValue().getEntity().getMinutos());
        assertEquals(0, peliculaDataEntityCaptor.getValue().getEntity().getValoracion());
        assertEquals(Genero.ACCION, peliculaDataEntityCaptor.getValue().getEntity().getGenero());
    }
    
    @Test
    public void testFiltrarGenero() {
    	when(webTarget.path("/filtrarGenero")).thenReturn(webTarget);

        Response response = mock(Response.class);
        GenericType<ArrayList<Pelicula>> listType = new GenericType<ArrayList<Pelicula>>() {};
        
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
        when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
        
		when(response.readEntity(listType)).thenReturn((ArrayList<Pelicula>) listaPelicula );
        Cliente.filtrarGenero(Genero.ACCION);
        
    }
    
    @Test
    public void testFiltrarValoracion() {
    	when(webTarget.path("/filtrarValoracion")).thenReturn(webTarget);

        Response response = mock(Response.class);
        GenericType<ArrayList<Pelicula>> listType = new GenericType<ArrayList<Pelicula>>() {};
        
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
        when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
        
		when(response.readEntity(listType)).thenReturn((ArrayList<Pelicula>) listaPelicula );
        Cliente.filtrarValoracion(5);
        
    }
    
    @Test
    public void testFiltrarNombre() {
    	when(webTarget.path("/filtrarNombre")).thenReturn(webTarget);

        Response response = mock(Response.class);
        GenericType<ArrayList<Pelicula>> listType = new GenericType<ArrayList<Pelicula>>() {};
        
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
        when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
        
		when(response.readEntity(listType)).thenReturn((ArrayList<Pelicula>) listaPelicula );
        Cliente.filtrarNombre("Smile");
        
    }
    
    @Test
    public void testFiltrarUsuario() {
    	when(webTarget.path("/filtrarUsuario")).thenReturn(webTarget);

        Response response = mock(Response.class);
        GenericType<ArrayList<Pelicula>> listType = new GenericType<ArrayList<Pelicula>>() {};
        
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
        when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
        
		when(response.readEntity(listType)).thenReturn((ArrayList<Pelicula>) listaPelicula );
        Cliente.filtrarUsuario("iker");
        
    }
    
    @Test
    public void testGetLogin() {
        // Mocking input parameter
        String nombre = "iker";

        // Mocking the web target and invocation builder
        when(webTarget.path(any(String.class))).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilderMock);
        when(invocationBuilderMock.post(Entity.entity(nombre, MediaType.TEXT_PLAIN))).thenReturn(responseMock);
        when(responseMock.getStatus()).thenReturn(Response.Status.OK.getStatusCode());

        // Mocking the response entity
        User userMock = new User();
        // Set up the userMock with desired values

        when(responseMock.readEntity(new GenericType<User>(){})).thenReturn(userMock);

        // Calling the method under test
        User result = Cliente.getLogin(nombre);

        // Verifying the behavior and assertions
        verify(webTarget).path("getLogin");
        verify(webTarget).request(MediaType.APPLICATION_JSON);
        verify(invocationBuilderMock).post(Entity.entity(nombre, MediaType.TEXT_PLAIN));
        verify(responseMock).getStatus();
        verify(responseMock).readEntity(new GenericType<User>(){});

        assertEquals(userMock, result);
    }
    


    @Test
    public void testBorrarAlquiler() {
        // Mocking input parameters
        String codPelicula = "ABC123";
        String loginUser = "john.doe";

        // Mocking the web target and invocation builder
        when(webTarget.path(any(String.class))).thenReturn(webTarget);
        when(webTarget.resolveTemplate(any(String.class), any(String.class))).thenReturn(webTarget);
        when(webTarget.request()).thenReturn(invocationBuilderMock);
        when(invocationBuilderMock.delete()).thenReturn(Response.ok().build());

        // Calling the method under test
        boolean result = cliente.borrarAlquiler(codPelicula, loginUser);

        // Verifying the behavior and assertions
        verify(webTarget).path("borrarAlquiler/{codPelicula}/{loginUser}");
        verify(webTarget).resolveTemplate("codPelicula", codPelicula);
        verify(webTarget).resolveTemplate("loginUser", loginUser);
        verify(invocationBuilderMock).delete();

        assertEquals(true, result);
    }
    
    @Test
    public void testDeleteUser() {
        // Mocking input parameters
        String login = "john.doe";

        // Mocking the web target and invocation builder
        when(webTarget.path(any(String.class))).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilderMock);
        when(invocationBuilderMock.post(Entity.entity(login, MediaType.APPLICATION_JSON))).thenReturn(Response.ok().build());

        // Calling the method under test
        boolean result = cliente.deleteUser(login);

        // Verifying the behavior and assertions
        verify(webTarget).path("deleteUser");
        verify(webTarget).request(MediaType.APPLICATION_JSON);
        verify(invocationBuilderMock).post(Entity.entity(login, MediaType.APPLICATION_JSON));

        assertEquals(true, result);
    }
    
    @Test
    public void testEliminarPelicula() {
        // Mocking input parameters
        String codigo = "ABC123";
        String titulo = "Mi película";
        int minutos = 120;
        int valoracion = 4;
        Genero genero = Genero.ACCION;

        // Mocking the web target and response
        WebTarget deletePeliculaWebTarget = mock(WebTarget.class);
        Invocation.Builder invocationBuilder = mock(Invocation.Builder.class);
        Response response = Response.ok().build();

        // Mocking the method calls
        when(webTarget.path(any(String.class))).thenReturn(deletePeliculaWebTarget);
        when(deletePeliculaWebTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.delete()).thenReturn(response);

        // Calling the method under test
        boolean result = Cliente.eliminarPelicula(codigo, titulo, minutos, valoracion, genero);

        // Verifying the behavior and assertions
        verify(webTarget).path(codigo);
        verify(deletePeliculaWebTarget).request(MediaType.APPLICATION_JSON);
        verify(invocationBuilder).delete();

        assertEquals(true, result);
    }
    
    @Test
    public void testMainMethod() {
        String[] args = {"localhost", "8080"};
        Cliente.main(args);
    }


    
    

//    @Test
//    public void testFiltrarNombre() {
//        // Configurar el nombre de la película a filtrar
//        String nombrePelicula = "pelicula";
//
//        // Crear el objeto de la película simulada
//        Pelicula peliculaSimulada = new Pelicula();
//        peliculaSimulada.setTitulo(nombrePelicula);
//        
//
//        // Mockear el web target y la solicitud
//        WebTarget mockWebTarget = Mockito.mock(WebTarget.class);
//        Invocation.Builder mockInvocationBuilder = Mockito.mock(Invocation.Builder.class);
//        Response mockResponse = Mockito.mock(Response.class);
//
//        Mockito.when(webTarget.path("filtrarNombre")).thenReturn(mockWebTarget);
//        Mockito.when(mockWebTarget.request(MediaType.APPLICATION_JSON)).thenReturn(mockInvocationBuilder);
//        Mockito.when(mockInvocationBuilder.post(Entity.entity(nombrePelicula, MediaType.TEXT_PLAIN))).thenReturn(mockResponse);
//        Mockito.when(mockResponse.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
//        Mockito.when(mockResponse.readEntity(Pelicula.class)).thenReturn(peliculaSimulada);
//
//        // Llamar al método real para probarlo
//        Pelicula resultado = cliente.filtrarNombre(nombrePelicula);
//
//        // Verificar que el resultado no sea nulo
//        assertNull(resultado);
//
//        // Verificar que el resultado tiene el título esperado
//       // assertEquals(nombrePelicula, resultado.getTitulo());
//    }

    
//    @Test
//    public void testFiltrarNombre() {
//    	Pelicula pelicula = new Pelicula();
//    	String nombrePelicula = "pelicula";
//        pelicula.setTitulo(nombrePelicula);
//        
//        Response successResponse = Response.ok(pelicula).build();
//
//        when(webTarget.path("filtrarNombre")).thenReturn(webTarget);
//        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilderMock);
//        when(invocationBuilderMock.post(Entity.entity(nombrePelicula, MediaType.TEXT_PLAIN))).thenReturn(successResponse);
//       when(successResponse.getStatusInfo()).thenReturn(Response.Status.OK);
//       when(successResponse.readEntity(Pelicula.class)).thenReturn(pelicula);
//        
//        // Llamar al método de prueba
//        Pelicula resultado = cliente.filtrarNombre("pelicula");
//        
//   
//        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(stringDataEntityCaptor.capture());
//        
//        // Obtener la cadena capturada
//        String tituloCapturado = stringDataEntityCaptor.getValue().getEntity();
//
//        // Verificar que el título capturado es igual al título de la película
//        assertEquals("pelicula", tituloCapturado);
//
////        // Verificar que el resultado no sea nulo
////        assertNotNull(resultado);
////
////        // Verificar que el resultado es la película esperada
////        assertEquals(pelicula.getTitulo(), resultado.getTitulo());
//// 
//    }
//    


//  @Test
//  public void testFiltrarNombre() {
//  	// Datos de prueba
//      String nombre = "Toy Story";
//      Pelicula pelicula = new Pelicula();
//      pelicula.setTitulo(nombre);
//      
//      when(webTarget.path("filtrarNombre")).thenReturn(webTarget);
//      
//      Response response = mock(Response.class);
////      when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
////      when(response.readEntity(Pelicula.class)).thenReturn(pelicula);
////      
//      when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
//      Pelicula respuesta = cliente.filtrarNombre(nombre);
//      
//      verify(webTarget.request(MediaType.APPLICATION_JSON)).post(peliculaEntityCaptor.capture());
//      System.out.println(peliculaEntityCaptor);
//      //assertEquals(respuesta.getTitulo(), pelicula.getTitulo());
//      assertEquals(pelicula.getTitulo(), peliculaEntityCaptor.getValue().getEntity().getTitulo());
//  }



    
    
//    @Test
//    public void testFiltrarNombre() {
//        // create input for the method
//        String nombre = "Toy Story";
//        Pelicula pelicula = new Pelicula();
//        pelicula.setTitulo(nombre);
//        
//        when(webTargetMock.path("filtrarNombre")).thenReturn(webTargetMock);
//        // configure mock objects
//        when(resourceMock.filtrarNombre(nombre)).thenReturn(pelicula);
//
//        // Act
//        Pelicula resultado = cliente.filtrarNombre(nombre);
//
//        // Assert
//        assertEquals(nombre, resultado.getTitulo());
//    }
    
//    @Test
//    public void testFiltrarNombre() {
//    	// create input for the method
//        String nombre = "Toy Story";
//        Pelicula pelicula = new Pelicula();
//        pelicula.setTitulo(nombre);
//
//        // configure mock objects
//        when(webTargetMock.path("filtrarNombre")).thenReturn(webTargetMock);
//        when(webTargetMock.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilderMock);
//        when(invocationBuilderMock.post(any(Entity.class))).thenReturn(responseMock);
//        when(responseMock.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
//        when(responseMock.readEntity(Pelicula.class)).thenReturn(pelicula);
//
//        // Act
//        Pelicula resultado = cliente.filtrarNombre(nombre);
//
//        // Assert
//        assertEquals(nombre, resultado.getTitulo());
//    }
   
//    @Test
//    public void testConstructor() {
//        assertNotNull(client);
//        assertNotNull(webTarget);
//    }
//    

    
    
//    @Test
//    public void testFiltrarNombre() {
//        // create input for the method
//    	 String nombre = "Toy Story";
//         Pelicula pelicula = new Pelicula();
//         pelicula.setTitulo(nombre);
//
//         Response response = mock(Response.class);
//         when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
//         when(response.readEntity(Pelicula.class)).thenReturn(pelicula);
//
//         Invocation.Builder invocationBuilder = mock(Invocation.Builder.class);
//         when(webTarget.path("filtrarNombre")).thenReturn(webTarget);
//         when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
//         when(invocationBuilder.post(any(Entity.class))).thenReturn(response);
//
//         // Act
//         Pelicula resultado = cliente.filtrarNombre(nombre);
//
//         // Assert
//         assertEquals(nombre, resultado.getTitulo());
//    }
//
//    @Test
//    public void testFiltrarGenero() {
//        // create input for the method
//    	Genero genero = Genero.ACCION;
//
//        // configure mock objects
//        when(webTarget.path("filtrarGenero")).thenReturn(webTarget);
//        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
//        when(invocationBuilder.post(Entity.entity(genero, MediaType.TEXT_PLAIN))).thenReturn(response);
//        when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
//
//        // create expected output
//        List<Pelicula> expectedPelis = new ArrayList<>();
//        expectedPelis.add(new Pelicula("1", "pelicula1", 0, 0, Genero.ACCION));
//        expectedPelis.add(new Pelicula("2", "pelicula2", 0, 0, Genero.ACCION));
//
//        // configure response body
//        when(response.readEntity(new GenericType<List<Pelicula>>() {})).thenReturn(expectedPelis);
//
//        // call the method and check the output
//        List<Pelicula> pelis = cliente.filtrarGenero(genero);
//        assertEquals(expectedPelis, pelis);
//    }
//    
//    @Test
//    public void testFiltrarValoracion() {
//        // create input for the method
//        int valoracion = 4;
//
//        // configure mock objects
//        when(webTarget.path("filtrarValoracion")).thenReturn(webTarget);
//        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
//        when(invocationBuilder.post(Entity.entity(valoracion, MediaType.TEXT_PLAIN))).thenReturn(response);
//        when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
//
//        // create expected output
//        List<Pelicula> expectedPelis = new ArrayList<>();
//        expectedPelis.add(new Pelicula("1", "pelicula1", 0, 4, Genero.ACCION));
//        expectedPelis.add(new Pelicula("2", "pelicula2", 0, 4, Genero.ACCION));
//
//        // configure response body
//        when(response.readEntity(new GenericType<List<Pelicula>>() {})).thenReturn(expectedPelis);
//
//        // call the method and check the output
//        List<Pelicula> pelis = cliente.filtrarValoracion(valoracion);
//        assertEquals(expectedPelis, pelis);
//    }
    
//    @Test
//    public void testRegisterAdmin() {
//        // Given
//        String login = "admin";
//        String password = "password";
//        WebTarget registerAdminWebTarget = webTarget.path("adminRegister");
//        Invocation.Builder invocationBuilder = registerAdminWebTarget.request(MediaType.APPLICATION_JSON);
//
//        AdminData adminData = new AdminData();
//        adminData.setLogin(login);
//        adminData.setPassword(password);
//
//        // When
//        Response response = invocationBuilder.post(Entity.entity(adminData, MediaType.APPLICATION_JSON));
//
//        // Then
//        assertEquals(Status.OK.getStatusCode(), response.getStatus());
//    }

    
}

