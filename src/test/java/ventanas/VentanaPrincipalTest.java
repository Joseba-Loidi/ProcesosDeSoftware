package ventanas;

import org.junit.Test;

import es.deusto.spq.server.jdo.User;

public class VentanaPrincipalTest {
	
	@Test
	public void test() {
		try {
			VentanaPrincipal VP = new VentanaPrincipal(new User("test", "test","test@deusto.es"));
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
	
	

	
}
