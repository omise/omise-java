package co.omise;

import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

public class ConfigurerTest extends OmiseTest {
    private final String JAVA_VERSION = System.getProperty("java.version");
    private final String API_VERSION = "new-shiny-version";
    private final String PKEY = "pkey_test_123";
    private final String SKEY = "skey_test_123";

    @Test
    public void testCtor() {
        try {
            new Configurer(null);
            fail("exception expected");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testUserAgent() throws IOException {
        Request req = intercept(new Request.Builder()
                .url("http://www.example.com")
                .build()).request();

        String ua = req.header("User-Agent");
        assertNotNull(ua);

        String[] parts = ua.split(" ");
        assertTrue(parts[0].startsWith("OmiseJava/"));
        assertEquals("OmiseAPI/" + API_VERSION, parts[1]);
        assertEquals("Java/" + JAVA_VERSION, parts[2]);
    }

    @Test
    public void testApiVersion() throws IOException {
        Request req = intercept(new Request.Builder()
                .url("https://www.example.com")
                .build()).request();

        assertEquals(API_VERSION, req.header("Omise-Version"));
    }

    @Test
    public void testVaultRequest() throws IOException {
        Request req = intercept(new Request.Builder()
                .url("https://vault.omise.co/tokens")
                .build()).request();

        String authorization = req.header("Authorization");
        assertEquals(authorization, Credentials.basic(PKEY, "x"));
    }

    @Test
    public void testApiRequest() throws IOException {
        Request req = intercept(new Request.Builder()
                .url("https://api.omise.co/charges")
                .build()).request();

        String authorization = req.header("Authorization");
        assertEquals(authorization, Credentials.basic(SKEY, "x"));
    }

    private Response intercept(Request req) throws IOException {
        return configurer().intercept(new MockChain(req));
    }

    private Configurer configurer() {
        return new Configurer(new Config(API_VERSION, PKEY, SKEY));
    }

    private class MockChain implements Interceptor.Chain {
        private final Request request;

        private MockChain(Request request) {
            this.request = request;
        }

        @Override
        public Request request() {
            return request;
        }

        @Override
        public Response proceed(Request request) throws IOException {
            return new Response.Builder()
                    .code(200)
                    .protocol(Protocol.HTTP_2)
                    .request(request)
                    .build();
        }

        @Override
        public Connection connection() {
            return null;
        }
    }
}
