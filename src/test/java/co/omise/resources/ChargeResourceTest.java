package co.omise.resources;

import co.omise.models.Charge;
import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import co.omise.models.SourceType;
import co.omise.requests.Request;
import org.junit.Test;

import java.io.IOException;

public class ChargeResourceTest extends ResourceTest {
    private final String CHARGE_ID = "chrg_test_4yq7duw15p9hdrjp8oq";

    @Test
    public void testList() throws IOException, OmiseException {
        ScopedList<Charge> list = resource().list();
        assertRequested("GET", "/charges", 200);
        assertEquals(2, list.getTotal());
        assertEquals(20, list.getLimit());

        Charge charge = list.getData().get(0);
        assertEquals("charge", charge.getObject());
        assertEquals(CHARGE_ID, charge.getId());
    }

    @Test
    public void testCreate() throws IOException, OmiseException {
        Charge charge = resource().create(new Charge.Create()
                .amount(100000)
                .currency("thb")
                .description("Charge for order 3947"));
        assertRequested("POST", "/charges", 200);

        assertEquals("chrg_test_4yq7duw15p9hdrjp8oq", charge.getId());
        assertEquals(100000L, charge.getAmount());
        assertEquals("thb", charge.getCurrency());
        assertEquals("trxn_test_4yq7duwb9jts1vxgqua", charge.getTransaction());
    }

    @Test
    public void testCreateChargeFromSource() throws IOException, OmiseException {
        Charge charge = resource().create(new Charge.Create()
                .source("src_test_5929c3tjts3omoi7ti2")
                .amount(100000)
                .currency("thb")
                .returnUri("http://example.com/orders/345678/complete"));
        assertRequested("POST", "/charges", 200);

        assertEquals("chrg_test_4yq7duw15p9hdrjp8oq",charge.getId());
        assertEquals("src_test_5929c3tjts3omoi7ti2", charge.getSource().getId());
        assertEquals(SourceType.InternetBankingScb, charge.getSource().getType());
        assertEquals(100000L, charge.getAmount());
    }

    @Test
    public void testUpdate() throws IOException, OmiseException {
        Charge charge = resource().update(CHARGE_ID, new Charge.Update()
                .description("Charge for order 3947 (XXL)"));
        assertRequested("PATCH", "/charges/" + CHARGE_ID, 200);

        assertEquals("chrg_test_4yq7duw15p9hdrjp8oq", charge.getId());
        assertEquals("Charge for order 3947 (XXL)", charge.getDescription());
    }

    @Test
    public void testCapture() throws IOException, OmiseException {
        Charge charge = resource().capture(CHARGE_ID);
        assertRequested("POST", "/charges/" + CHARGE_ID + "/capture", 200);
        assertEquals("chrg_test_4yq7duw15p9hdrjp8oq", charge.getId());
        assertFalse(charge.isCapture());
        assertTrue(charge.isPaid());
    }

    private ChargeResource resource() {
        return new ChargeResource(testClient());
    }
}