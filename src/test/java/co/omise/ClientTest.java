package co.omise;

import co.omise.models.Balance;
import co.omise.models.OmiseError;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class ClientTest extends OmiseTest {
    @Test
    public void testCtor() {
        try {
            new Client(null);
            fail("exception expected");
        } catch (NullPointerException e) {
        }
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveError() throws IOException {
        try {
            new Client("skey_test_123").account().get();
        } catch (OmiseError e) {
            assertEquals("authentication_failure", e.getCode());
        }
    }

    @Test
    @Ignore("adds your skey here to do a live test.")
    public void testLiveFetch() throws IOException {
        Client client = new Client("skey_test_123");
        Balance balance = client.balance().get();
        assertEquals(16741576, balance.getTotal());
    }
}
