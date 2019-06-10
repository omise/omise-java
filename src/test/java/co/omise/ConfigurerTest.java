package co.omise;

import okhttp3.Credentials;
import okhttp3.Request;
import org.junit.Test;

import static co.omise.ConfigTest.*;

public class ConfigurerTest extends OmiseTest {

    @Test
    public void testCtor() {
        try {
            new Configurer(null);
            fail("exception expected");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void testUserAgent() {
        Request req = configure(new Request.Builder()
                .url("http://api.omise.co")
                .build());

        assertEquals(config().userAgent(), req.header("User-Agent"));
    }

    @Test
    public void testApiVersion() {
        Request req = configure(new Request.Builder()
                .url("https://api.omise.co")
                .build());

        assertEquals(API_VERSION, req.header("Omise-Version"));
    }

    @Test
    public void testVaultRequest() {
        Request req = configure(new Request.Builder()
                .url("https://vault.omise.co/tokens")
                .build());

        String authorization = req.header("Authorization");
        assertEquals(authorization, Credentials.basic(PKEY, "x"));
    }

    @Test
    public void testApiRequest() {
        Request req = configure(new Request.Builder()
                .url("https://api.omise.co/charges")
                .build());

        String authorization = req.header("Authorization");
        assertEquals(authorization, Credentials.basic(SKEY, "x"));
    }

    private Request configure(Request req) {
        return Configurer.configure(config(), req);
    }

    private Config config() {
        return ConfigTest.config();
    }
}
