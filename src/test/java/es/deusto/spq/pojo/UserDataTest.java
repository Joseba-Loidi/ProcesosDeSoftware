package es.deusto.spq.pojo;

import static org.junit.Assert.*;

import org.junit.Test;

import es.deusto.spq.server.jdo.User;

public class UserDataTest {

	@Test
	public void testGetSetLogin() {
		UserData userData = new UserData();
		String expectedLogin = "johndoe";
		userData.setLogin(expectedLogin);
		assertEquals(expectedLogin, userData.getLogin());
	}

	@Test
	public void testGetSetPassword() {
		UserData userData = new UserData();
		String expectedPassword = "password123";
		userData.setPassword(expectedPassword);
		assertEquals(expectedPassword, userData.getPassword());
	}

	@Test
	public void testGetSetCorreo() {
		UserData userData = new UserData();
		String expectedCorreo = "johndoe@example.com";
		userData.setCorreo(expectedCorreo);
		assertEquals(expectedCorreo, userData.getCorreo());
	}

	@Test
	public void testToString() {
		String expectedString = "UserData [login=johndoe, password=password123, correo=johndoe@example.com]";
		UserData userData = new UserData("johndoe", "password123", "johndoe@example.com");
		assertEquals(expectedString, userData.toString());
	}
	
    @Test
    public void testConstructorVacio() {
        UserData userData = new UserData();
        assertEquals(null, userData.getLogin());
        assertEquals(null, userData.getPassword());
        assertEquals(null, userData.getCorreo());
    }
 // Test constructor con parámetros
    @Test
    public void testConstructorConParametros() {
    	UserData userData = new UserData("usuario1", "pass123", "usuario1@example.com");
        assertNotNull(userData);
        assertEquals("usuario1", userData.getLogin());
        assertEquals("pass123", userData.getPassword());
        assertEquals("usuario1@example.com", userData.getCorreo());
    }

}
