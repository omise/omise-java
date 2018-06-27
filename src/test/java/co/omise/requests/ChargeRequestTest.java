package co.omise.requests;

import co.omise.models.Charge;
import co.omise.models.OmiseException;
import co.omise.models.SourceType;
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

    @Test
    public void testCreate() throws IOException, OmiseException {
        Requester requester = getTestRequester();
        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(100000)
                        .currency("thb")
                        .description("Charge for order 3947")
                        .build();
        Charge charge = requester.sendRequest(createChargeRequest, Charge.class);

        assertRequested("POST", "/charges", 200);

        assertEquals(CHARGE_ID, charge.getId());
        assertEquals(100000L, charge.getAmount());
        assertEquals("thb", charge.getCurrency());
        assertEquals("trxn_test_4yq7duwb9jts1vxgqua", charge.getTransaction());
    }

    @Test
    public void testCreateChargeFromSource() throws IOException, OmiseException {
        Requester requester = getTestRequester();
        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .source("src_test_5929c3tjts3omoi7ti2")
                        .amount(100000)
                        .currency("thb")
                        .returnUri("http://example.com/orders/345678/complete")
                        .build();
        Charge charge = requester.sendRequest(createChargeRequest, Charge.class);

        assertRequested("POST", "/charges", 200);

        assertEquals(CHARGE_ID, charge.getId());
        assertEquals("src_test_5929c3tjts3omoi7ti2", charge.getSource().getId());
        assertEquals(SourceType.InternetBankingScb, charge.getSource().getType());
        assertEquals(100000L, charge.getAmount());
    }
}
