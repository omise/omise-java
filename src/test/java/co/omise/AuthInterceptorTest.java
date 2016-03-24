package co.omise;

import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

public class AuthInterceptorTest extends OmiseTest {
    public final String API_VERSION = "new-shiny-version";
    public final String PKEY = "pkey_test_123";
    public final String SKEY = "skey_test_123";

    @Test
    public void testCtor() {
        try {
            new AuthInterceptor(null);
            fail("exception expected");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testVaultRequest() throws IOException {
        Request req = new Request.Builder().url("https://vault.omise.co/tokens").build();
        req = interceptor().intercept(chain(req)).request();

        String authorization = req.header("Authorization");
        assertEquals(authorization, Credentials.basic(PKEY, "x"));
    }

    @Test
    public void testApiRequest() throws IOException {
        Request req = new Request.Builder().url("https://api.omise.co/charges").build();
        req = interceptor().intercept(chain(req)).request();

        String authorization = req.header("Authorization");
        assertEquals(authorization, Credentials.basic(SKEY, "x"));
    }

    private AuthInterceptor interceptor() {
        return new AuthInterceptor(new Config(API_VERSION, PKEY, SKEY));
    }

    private Interceptor.Chain chain(Request request) {
        return new MockChain(request);
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
