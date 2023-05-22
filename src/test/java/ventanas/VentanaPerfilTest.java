package ventanas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.junit.Test;
import org.mockito.Mockito;

import es.deusto.spq.server.jdo.User;


public class VentanaPerfilTest{	
	User user;
	VentanaPrincipal ventanaPrincipal;
	
	@Test
	public void test() {
		try {
			VentanaPerfil vp = new VentanaPerfil(new User("test", "test","test@deusto.es"));
			 assertNotNull(vp);
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
	
	}

