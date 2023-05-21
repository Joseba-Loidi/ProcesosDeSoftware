package es.deusto.spq.server.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.server.Main;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import static org.junit.Assert.*;

public class MainTest {

    private HttpServer server;

    @Before
    public void setUp() throws IOException {
        server = Main.startServer();
    }

    @After
    public void tearDown() {
        server.shutdownNow();
    }

    @Test
    public void testServerStartStop() throws IOException {
        assertTrue(server.isStarted());
        //assertFalse(server.shutdown());
    }

    @Test
    public void testBaseURI() {
        assertEquals("http://localhost:8080/rest/", Main.BASE_URI);
    }
}
