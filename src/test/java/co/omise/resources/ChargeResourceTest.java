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

    private ChargeResource resource() {
        return new ChargeResource(testClient());
    }
}