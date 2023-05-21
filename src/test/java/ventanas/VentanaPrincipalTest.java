package ventanas;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import es.deusto.spq.server.Main;
import es.deusto.spq.server.jdo.User;

public class VentanaPrincipalTest {
	
	@Test
	public void test() {
		try {
			VentanaPrincipal VP = new VentanaPrincipal(new User("test", "test","test@deusto.es"));
			 assertNotNull(VP);
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}

	

	
}
