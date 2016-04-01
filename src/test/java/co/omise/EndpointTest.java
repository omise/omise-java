package co.omise;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

public class EndpointTest extends OmiseTest {
    private final ImmutableList<Endpoint> ENDPOINTS = Endpoint.all();

    private final String PKEY = "pkey_test_123";
    private final String SKEY = "skey_test_123";
    private final Config CONFIG = new Config(null, PKEY, SKEY);

    @Test
    public void testAll() {
        assertTrue(ENDPOINTS.contains(Endpoint.API));
        assertTrue(ENDPOINTS.contains(Endpoint.VAULT));
    }

    @Test
    public void testProperties() {
        for (Endpoint endpoint : ENDPOINTS) {
            assertNotNull(endpoint.scheme());
            assertNotNull(endpoint.host());
            assertNotNull(endpoint.certificateHash());
            assertNotNull(endpoint.authenticationKey(CONFIG));
        }
    }
}