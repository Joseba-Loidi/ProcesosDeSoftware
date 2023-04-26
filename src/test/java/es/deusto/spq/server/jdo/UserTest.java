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
}
