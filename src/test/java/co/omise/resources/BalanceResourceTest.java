package co.omise.resources;

import co.omise.models.Balance;
import org.junit.Test;

import java.io.IOException;

public class BalanceResourceTest extends ResourceTest {
    @Test
    public void testGet() throws IOException {
        Balance balance = resource().get();
        assertRequested("GET", "/balance", 200);

        assertEquals("balance", balance.getObject());
        assertEquals("/balance", balance.getLocation());
        assertEquals(12995317L, balance.getAvailable());
        assertEquals(96094L, balance.getTotal());
    }

    private BalanceResource resource() {
        return new BalanceResource(testClient());
    }
}