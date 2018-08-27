package co.omise;

import org.junit.Test;

public class EndpointTest extends OmiseTest {
    private final String PKEY = "pkey_test_123";
    private final String SKEY = "skey_test_123";
    private final Config CONFIG = new Config(null, PKEY, SKEY);

    @Test
    public void testAll() {
        assertTrue(Endpoint.getAllEndpoints().contains(Endpoint.API));
        assertTrue(Endpoint.getAllEndpoints().contains(Endpoint.VAULT));
    }

    @Test
    public void testByHost() {
        for (Endpoint endpoint : Endpoint.getAllEndpoints()) {
            assertEquals(endpoint, Endpoint.getAllEndpointsByHost().get(endpoint.host()));
        }
    }

    @Test
    public void testProperties() {
        for (Endpoint endpoint : Endpoint.getAllEndpoints()) {
            assertNotNull(endpoint.scheme());
            assertNotNull(endpoint.host());
            assertNotNull(endpoint.certificateHash());
            assertNotNull(endpoint.authenticationKey(CONFIG));
        }
    }
}