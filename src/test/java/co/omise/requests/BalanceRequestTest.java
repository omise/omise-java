package co.omise.requests;

import co.omise.models.Balance;
import co.omise.models.OmiseException;
import org.junit.Test;

import java.io.IOException;

public class BalanceRequestTest extends RequestTest {
    @Test
    public void testGet() throws IOException, OmiseException {
        Requester requester = getTestRequester();
        Request<Balance> getBalanceRequest = new Balance.GetRequestBuilder().build();
        Balance balance = requester.sendRequest(getBalanceRequest);

        assertRequested("GET", "/balance", 200);

        assertEquals("balance", balance.getObject());
        assertEquals("/balance", balance.getLocation());
        assertEquals(12995317L, balance.getAvailable());
        assertEquals(96094L, balance.getTotal());
    }
}
