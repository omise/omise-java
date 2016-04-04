package co.omise;

import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

import static co.omise.ConfigTest.API_VERSION;
import static co.omise.ConfigTest.PKEY;
import static co.omise.ConfigTest.SKEY;

public class ConfigurerTest extends OmiseTest {
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
        Request req = configure(new Request.Builder()
                .url("http://www.example.com")
                .build());

        assertEquals(config().userAgent(), req.header("User-Agent"));
    }

    @Test
    public void testApiVersion() throws IOException {
        Request req = configure(new Request.Builder()
                .url("https://www.example.com")
                .build());

        assertEquals(API_VERSION, req.header("Omise-Version"));
    }

    @Test
    public void testVaultRequest() throws IOException {
        Request req = configure(new Request.Builder()
                .url("https://vault.omise.co/tokens")
                .build());

        String authorization = req.header("Authorization");
        assertEquals(authorization, Credentials.basic(PKEY, "x"));
    }

    @Test
    public void testApiRequest() throws IOException {
        Request req = configure(new Request.Builder()
                .url("https://api.omise.co/charges")
                .build());

        String authorization = req.header("Authorization");
        assertEquals(authorization, Credentials.basic(SKEY, "x"));
    }

    private Request configure(Request req) {
        return configurer().configure(config(), req);
    }

    private Config config() {
        return ConfigTest.config();
    }

    private Configurer configurer() {
        return new Configurer(config());
    }
}
