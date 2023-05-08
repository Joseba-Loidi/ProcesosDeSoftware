package es.deusto.spq.server.jdo;

import static org.junit.Assert.*;
import es.deusto.spq.server.jdo.Admin;
import org.junit.Before;
import org.junit.Test;

public class AdminTest {

	private Admin admin;
	
	@Before
	public void setUp() throws Exception {
		admin = new Admin("admin", "1234");
	}
	
	@Test
	public void testGetLogin() {
		assertEquals("admin", admin.getLogin());
	}
	
	@Test
	public void testGetPassword() {
		assertEquals("1234", admin.getPassword());
	}
	
	@Test
	public void testSetPassword() {
		admin.setPassword("5678");
		assertEquals("5678", admin.getPassword());
	}
	
	@Test
	public void testToString() {
		String expected = "Admin [login=admin, password=1234]";
		assertEquals(expected, admin.toString());
	}
}
