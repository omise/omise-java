package co.omise;

import org.junit.Test;

public class ClientTest extends OmiseTest {
    @Test
    public void testCtor() {
        Client client = new Client("skey_test_123");
        assertConfig(client, null, null, "skey_test_123");

        client = new Client("pkey_test_123", "skey_test_123");
        assertConfig(client, null, "pkey_test_123", "skey_test_123");

        client = new Client("new-shiny-version", "pkey_test_123", "skey_test_123");
        assertConfig(client, "new-shiny-version", "pkey_test_123", "skey_test_123");

        try {
            new Client(null);
            fail("exception expected");
        } catch (NullPointerException e) {
        }
    }

    private void assertConfig(Client client, String apiVersion, String publicKey, String secretKey) {
        assertNotNull(client);
        assertEquals(apiVersion, client.getApiVersion());
        assertEquals(publicKey, client.getPublicKey());
        assertEquals(secretKey, client.getSecretKey());
    }
}
