package es.deusto.spq.server.jdo;

import static org.junit.Assert.*;
import es.deusto.spq.server.jdo.Admin;
import org.junit.Before;
import org.junit.Test;

public class AdminTest {

	@Test
    public void testGetLogin() {
        // Create an instance of Admin
        Admin admin = new Admin("admin123", "password");

        // Call the tested method
        String login = admin.getLogin();

        // Verify that the login is correct
        assertEquals("admin123", login);
    }

    @Test
    public void testGetPassword() {
        // Create an instance of Admin
        Admin admin = new Admin("admin123", "password");

        // Call the tested method
        String password = admin.getPassword();

        // Verify that the password is correct
        assertEquals("password", password);
    }

    @Test
    public void testSetPassword() {
        // Create an instance of Admin
        Admin admin = new Admin("admin123", "password");

        // Call the tested method
        admin.setPassword("newpassword");

        // Verify that the password is updated correctly
        assertEquals("newpassword", admin.getPassword());
    }

    @Test
    public void testToString() {
        // Create an instance of Admin
        Admin admin = new Admin("admin123", "password");

        // Call the tested method
        String toStringResult = admin.toString();

        // Verify that the toString result is correct
        assertEquals("Admin [login=admin123, password=password]", toStringResult);
    }
}
