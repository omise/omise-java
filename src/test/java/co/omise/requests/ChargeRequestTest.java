package co.omise.requests;

import co.omise.models.Charge;
import co.omise.models.OmiseException;
import org.junit.Test;

import java.io.IOException;

public class ChargeRequestTest extends RequestTest {
    private final String CHARGE_ID = "chrg_test_4yq7duw15p9hdrjp8oq";

    @Test
    public void testGet() throws IOException, OmiseException {
        Requester requester = getTestRequester();
        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder(CHARGE_ID).build();
        Charge charge = requester.sendRequest(getChargeRequest, Charge.class);

        assertRequested("GET", "/charges/" + CHARGE_ID, 200);
        assertEquals("chrg_test_4yq7duw15p9hdrjp8oq", charge.getId());
        assertEquals(100000L, charge.getAmount());
        assertEquals("thb", charge.getCurrency());
        assertEquals("trxn_test_4yq7duwb9jts1vxgqua", charge.getTransaction());
    }
}
