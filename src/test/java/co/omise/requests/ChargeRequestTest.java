package co.omise.requests;

import co.omise.models.AuthenticationType;
import co.omise.models.AuthorizationType;
import co.omise.models.Barcode;
import co.omise.models.Charge;
import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import co.omise.models.SourceType;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

public class ChargeRequestTest extends RequestTest {
    private final String CHARGE_ID = "chrg_test_4yq7duw15p9hdrjp8oq";
    private final String THREE_DS_CHARGE_ID = "chrg_test_3dsu8b8xyl0d3s";
    private final String NO_AUTH_CHARGE_ID = "chrg_test_noauth";

    @Test
    public void testGet() throws IOException, OmiseException {
        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder(CHARGE_ID).build();

        Charge charge = getTestRequester().sendRequest(getChargeRequest);

        assertRequested("GET", "/charges/" + CHARGE_ID, 200);
        assertEquals("chrg_test_4yq7duw15p9hdrjp8oq", charge.getId());
        assertEquals(100000L, charge.getAmount());
        assertEquals("thb", charge.getCurrency());
        assertEquals("trxn_test_4yq7duwb9jts1vxgqua", charge.getTransaction());
        assertEquals("Test advice", charge.getMerchantAdvice());
        assertEquals("Test advice code", charge.getMerchantAdviceCode());
        assertEquals(AuthenticationType.Passkey, charge.getAuthentication());
        assertEquals("PASSKEY", charge.getAuthenticatedBy());
        assertEquals(Collections.singletonList("email"), charge.getMissing3DSFields());
    }

    @Test
    public void testGetThreeDSCharge() throws IOException, OmiseException {
        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder(THREE_DS_CHARGE_ID).build();

        Charge charge = getTestRequester().sendRequest(getChargeRequest);

        assertRequested("GET", "/charges/" + THREE_DS_CHARGE_ID, 200);
        assertEquals(AuthenticationType.ThreeDS, charge.getAuthentication());
        assertEquals("3DS", charge.getAuthenticatedBy());
    }

    @Test
    public void testGetChargeWithoutAuthenticatedBy() throws IOException, OmiseException {
        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder(NO_AUTH_CHARGE_ID).build();

        Charge charge = getTestRequester().sendRequest(getChargeRequest);

        assertRequested("GET", "/charges/" + NO_AUTH_CHARGE_ID, 200);
        assertEquals(AuthenticationType.Passkey, charge.getAuthentication());
        assertNull(charge.getAuthenticatedBy());
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
        assertEquals(AuthenticationType.Passkey, charge.getAuthentication());
        assertEquals("PASSKEY", charge.getAuthenticatedBy());
    }

    @Test
    public void testCreateWithWebhooks() throws IOException, OmiseException {
        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(100000)
                        .currency("thb")
                        .webhookEndpoints(Collections.singletonList("https://webhook.site/123"))
                        .build();

        Charge charge = getTestRequester().sendRequest(createChargeRequest);

        assertRequested("POST", "/charges", 200);
        assertRequestBody("{\"amount\":100000,\"capture\":false,\"card\":null,\"currency\":\"thb\",\"customer\":null,\"description\":null,\"ip\":null,\"metadata\":null,\"reference\":null,\"source\":null,\"zero_interest_installments\":false,\"expires_at\":null,\"platform_fee\":null,\"return_uri\":null,\"authentication\":null,\"authorization_type\":null,\"webhook_endpoints\":[\"https://webhook.site/123\"],\"first_charge\":null,\"linked_account\":null,\"recurring_reason\":null,\"transaction_indicator\":null}");
        assertNotNull(charge);
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
                        .authentication(AuthenticationType.Passkey)
                        .authorizationType(AuthorizationType.PreAuth)
                        .returnUri("http://example.com/orders/345678/complete")
                        .firstCharge(CHARGE_ID)
                        .linkedAccount("acc_id")
                        .recurringReason("reason")
                        .transactionIndicator("trans_id")
                        .build();

        Charge charge = getTestRequester().sendRequest(createChargeRequest);

        assertRequested("POST", "/charges", 200);
        assertRequestBody("{\"amount\":100000,\"capture\":false,\"card\":null,\"currency\":\"thb\",\"customer\":null,\"description\":null,\"ip\":null,\"metadata\":null,\"reference\":null,\"source\":null,\"zero_interest_installments\":false,\"expires_at\":null,\"platform_fee\":null,\"return_uri\":\"http://example.com/orders/345678/complete\",\"authentication\":\"PASSKEY\",\"authorization_type\":\"pre_auth\",\"webhook_endpoints\":null,\"first_charge\":\"chrg_test_4yq7duw15p9hdrjp8oq\",\"linked_account\":\"acc_id\",\"recurring_reason\":\"reason\",\"transaction_indicator\":\"trans_id\"}");
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
        assertFalse(charge.isDisputable());
    }

    @Test
    public void testCapturable() throws IOException, OmiseException {
        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder(CHARGE_ID).build();

        Charge charge = getTestRequester().sendRequest(getChargeRequest);

        assertRequested("GET", "/charges/" + CHARGE_ID, 200);
        assertFalse(charge.isCapturable());
    }

    @Test
    public void testReversible() throws IOException, OmiseException {
        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder(CHARGE_ID).build();

        Charge charge = getTestRequester().sendRequest(getChargeRequest);

        assertRequested("GET", "/charges/" + CHARGE_ID, 200);
        assertFalse(charge.isReversible());
    }

    @Test
    public void testRefundable() throws IOException, OmiseException {
        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder(CHARGE_ID).build();

        Charge charge = getTestRequester().sendRequest(getChargeRequest);

        assertRequested("GET", "/charges/" + CHARGE_ID, 200);
        assertFalse(charge.isRefundable());
    }

}
