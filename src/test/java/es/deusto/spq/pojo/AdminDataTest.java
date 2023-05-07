//package es.deusto.spq.pojo;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class AdminDataTest {
//
//    private AdminData admin;
//
//    @Before
//    public void setUp() throws Exception {
//        admin = new AdminData("admin", "admin123");
//    }
//
//    @Test
//    public void testGetLogin() {
//        assertEquals("admin", admin.getLogin());
//    }
//
//    @Test
//    public void testSetLogin() {
//        admin.setLogin("admin2");
//        assertEquals("admin2", admin.getLogin());
//    }
//
//    @Test
//    public void testGetPassword() {
//        assertEquals("admin123", admin.getPassword());
//    }
//
//    @Test
//    public void testSetPassword() {
//        admin.setPassword("admin456");
//        assertEquals("admin456", admin.getPassword());
//    }
//    
//    @Test
//    public void testConstructorVacio() {
//        AdminData admin = new AdminData();
//        assertNull(admin.getLogin());
//        assertNull(admin.getPassword());
//    }
//
//    @Test
//    public void testToString() {
//        assertEquals("[login=admin, password=admin123]", admin.toString());
//    }
//}
