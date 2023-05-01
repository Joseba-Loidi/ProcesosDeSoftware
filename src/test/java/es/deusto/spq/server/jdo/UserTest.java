package es.deusto.spq.server.jdo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private User user1;
	private User user2;
	private User user3;
	
	@Before
	public void setUp() throws Exception {
		user1 = new User("jack", "jack", "jack@gmail.com");
		user2 = new User("antonio", "antonio", "antonio@gmail.com");
		user3 = new User("angela", "angela", "angela@gmail.com");
	}

	@Test
	public void testGetLogin() {
		assertEquals(user1.getLogin(), "jack");
		assertEquals(user2.getLogin(), "antonio");
		assertEquals(user3.getLogin(), "angela");
	}

	@Test
	public void testGetPassword() {
		assertEquals(user1.getPassword(), "jack");
		assertEquals(user2.getPassword(), "antonio");
		assertEquals(user3.getPassword(), "angela");
	}
	
	@Test
	public void testGetCorreo() {
		assertEquals(user1.getCorreo(), "jack@gmail.com");
		assertEquals(user2.getCorreo(), "antonio@gmail.com");
		assertEquals(user3.getCorreo(), "angela@gmail.com");
	}

	@Test
	public void testNotNull() {
		assertNotNull(user1);
		assertNotNull(user2);
		assertNotNull(user3);
	}
	   // Test constructor vacío
    @Test
    public void testConstructorVacio() {
        User user = new User();
        assertNotNull(user);
        assertEquals("", user.getLogin());
        assertEquals("", user.getPassword());
        assertEquals("", user.getCorreo());
    }
    
    // Test constructor con parámetros
    @Test
    public void testConstructorConParametros() {
        User user = new User("usuario1", "pass123", "usuario1@example.com");
        assertNotNull(user);
        assertEquals("usuario1", user.getLogin());
        assertEquals("pass123", user.getPassword());
        assertEquals("usuario1@example.com", user.getCorreo());
    }
    
    // Test getter y setter de password
    @Test
    public void testGetSetPassword() {
        User user = new User();
        user.setPassword("pass456");
        assertEquals("pass456", user.getPassword());
    }
    
    // Test getter y setter de correo
    @Test
    public void testGetSetCorreo() {
        User user = new User();
        user.setCorreo("usuario2@example.com");
        assertEquals("usuario2@example.com", user.getCorreo());
    }
}
