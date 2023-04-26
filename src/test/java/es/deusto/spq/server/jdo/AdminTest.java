package es.deusto.spq.server.jdo;

import static org.junit.Assert.*;
import es.deusto.spq.server.jdo.Admin;
import org.junit.Before;
import org.junit.Test;

public class AdminTest {

	private Admin admin1;
	private Admin admin2;
	private Admin admin3;
	
	@Before
	public void setUp() throws Exception {
		admin1= new Admin("juan", "juan");
		admin2= new Admin("pedro", "pedro");
		admin3= new Admin("amaia", "amaia");
	}

	@Test
	public void testgetLogin() {
		assertEquals(admin1.getLogin(), "juan");
		assertEquals(admin2.getLogin(), "pedro");
		assertEquals(admin3.getLogin(), "amaia");
	}
	
	@Test
	public void testgetPassword() {
		assertEquals(admin1.getPassword(), "juan");
		assertEquals(admin2.getPassword(), "pedro");
		assertEquals(admin3.getPassword(), "amaia");
	}
	@Test
	public void testNotNull() {
		assertNotNull(admin1);
		assertNotNull(admin2);
		assertNotNull(admin3);
	}
}
