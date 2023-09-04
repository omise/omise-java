package co.omise.requests;

import co.omise.models.AuthorizationType;
import co.omise.models.Barcode;
import co.omise.models.Charge;
import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import co.omise.models.SourceType;
import okio.Buffer;
import org.junit.Test;

import java.io.IOException;

public class ChargeRequestTest extends RequestTest {
    private final String CHARGE_ID = "chrg_test_4yq7duw15p9hdrjp8oq";

    @Test
    public void testGet() throws IOException, OmiseException {
        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder(CHARGE_ID).build();

        Charge charge = getTestRequester().sendRequest(getChargeRequest);

        assertRequested("GET", "/charges/" + CHARGE_ID, 200);
        assertEquals("chrg_test_4yq7duw15p9hdrjp8oq", charge.getId());
        assertEquals(100000L, charge.getAmount());
        assertEquals("thb", charge.getCurrency());
        assertEquals("trxn_test_4yq7duwb9jts1vxgqua", charge.getTransaction());
    }

    @Test
    public void testCreate() throws IOException, OmiseException {
        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(100000)
                        .currency("thb")
                        .description("Charge for order 3947")
                        .build();

        Charge charge = getTestRequester().sendRequest(createChargeRequest);

        assertRequested("POST", "/charges", 200);

        assertEquals(CHARGE_ID, charge.getId());
        assertEquals(100000L, charge.getAmount());
        assertEquals("thb", charge.getCurrency());
        assertEquals("trxn_test_4yq7duwb9jts1vxgqua", charge.getTransaction());
    }

    @Test
    public void testCreateChargeFromSource() throws IOException, OmiseException {
        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .source("src_test_5929c3tjts3omoi7ti2")
                        .amount(100000)
                        .currency("thb")
                        .returnUri("http://example.com/orders/345678/complete")
                        .build();

        Charge charge = getTestRequester().sendRequest(createChargeRequest);

        assertRequested("POST", "/charges", 200);

        assertEquals(CHARGE_ID, charge.getId());
        assertEquals("src_test_5929c3tjts3omoi7ti2", charge.getSource().getId());
        assertEquals(SourceType.InternetBankingScb, charge.getSource().getType());
        assertEquals(Barcode.class, charge.getSource().getScannableCode().getClass());
        assertEquals(100000L, charge.getAmount());
    }

    @Test
    public void testCreatePartialCaptureCharge() throws IOException, OmiseException {
        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(100000)
                        .currency("thb")
                        .capture(false)
                        .authorizationType(AuthorizationType.PreAuth)
                        .returnUri("http://example.com/orders/345678/complete")
                        .build();

        Charge charge = getTestRequester().sendRequest(createChargeRequest);

        assertRequested("POST", "/charges", 200);
        assertRequestBody("{\"amount\":100000,\"capture\":false,\"card\":null,\"currency\":\"thb\",\"customer\":null,\"description\":null,\"ip\":null,\"metadata\":null,\"reference\":null,\"source\":null,\"zero_interest_installments\":false,\"expires_at\":null,\"platform_fee\":null,\"return_uri\":\"http://example.com/orders/345678/complete\",\"authorization_type\":\"pre_auth\"}");
        assertNotNull(charge);
    }

    @Test
    public void testUpdate() throws IOException, OmiseException {
        Request<Charge> updateChargeRequest =
                new Charge.UpdateRequestBuilder(CHARGE_ID)
                        .description("Charge for order 3947 (XXL)")
                        .build();

        Charge charge = getTestRequester().sendRequest(updateChargeRequest);

        assertRequested("PATCH", "/charges/" + CHARGE_ID, 200);
        assertEquals(CHARGE_ID, charge.getId());
        assertEquals("Charge for order 3947 (XXL)", charge.getDescription());
    }

    @Test
    public void testCapture() throws IOException, OmiseException {
        Request<Charge> captureChargeRequest =
                new Charge.CaptureRequestBuilder(CHARGE_ID)
                        .build();
        Charge charge = getTestRequester().sendRequest(captureChargeRequest);

        assertRequested("POST", "/charges/" + CHARGE_ID + "/capture", 200);
        assertEquals(CHARGE_ID, charge.getId());
        assertFalse(charge.isCapture());
        assertTrue(charge.isPaid());
    }

    @Test
    public void testPartialCapture() throws IOException, OmiseException {
        Request<Charge> captureChargeRequest =
                new Charge.CaptureRequestBuilder(CHARGE_ID)
                        .captureAmount(100000)
                        .build();

        Charge charge = getTestRequester().sendRequest(captureChargeRequest);

        assertRequested("POST", "/charges/" + CHARGE_ID + "/capture", 200);
        assertRequestBody("{\"capture_amount\":100000}");
        assertEquals(CHARGE_ID, charge.getId());
        assertFalse(charge.isCapture());
        assertTrue(charge.isPaid());
    }

    @Test
    public void testReverse() throws IOException, OmiseException {
        Request<Charge> reverseChargeRequest =
                new Charge.ReverseRequestBuilder(CHARGE_ID)
                        .build();
        Charge charge = getTestRequester().sendRequest(reverseChargeRequest);

        assertRequested("POST", "/charges/" + CHARGE_ID + "/reverse", 200);
        assertEquals(CHARGE_ID, charge.getId());
        assertTrue(charge.isReversed());
    }

    @Test
    public void testList() throws IOException, OmiseException {
        Request<ScopedList<Charge>> listChargeRequest =
                new Charge.ListRequestBuilder().build();
        ScopedList<Charge> list = getTestRequester().sendRequest(listChargeRequest);

        assertRequested("GET", "/charges", 200);
        assertEquals(2, list.getTotal());
        assertEquals(20, list.getLimit());

        Charge charge = list.getData().get(0);
        assertEquals("charge", charge.getObject());
        assertEquals(CHARGE_ID, charge.getId());
    }

    @Test
    public void testDisputable() throws IOException, OmiseException {
        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder(CHARGE_ID).build();

        Charge charge = getTestRequester().sendRequest(getChargeRequest);

        assertRequested("GET", "/charges/" + CHARGE_ID, 200);
        assertEquals(false, charge.isDisputable());
    }

    @Test
    public void testCapturable() throws IOException, OmiseException {
        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder(CHARGE_ID).build();

        Charge charge = getTestRequester().sendRequest(getChargeRequest);

        assertRequested("GET", "/charges/" + CHARGE_ID, 200);
        assertEquals(false, charge.isCapturable());
    }

    @Test
    public void testReversible() throws IOException, OmiseException {
        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder(CHARGE_ID).build();

        Charge charge = getTestRequester().sendRequest(getChargeRequest);

        assertRequested("GET", "/charges/" + CHARGE_ID, 200);
        assertEquals(false, charge.isReversible());
    }

    @Test
    public void testRefundable() throws IOException, OmiseException {
        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder(CHARGE_ID).build();

        Charge charge = getTestRequester().sendRequest(getChargeRequest);

        assertRequested("GET", "/charges/" + CHARGE_ID, 200);
        assertEquals(false, charge.isRefundable());
    }

}
