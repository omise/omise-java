package co.omise.requests;

import co.omise.models.*;
import co.omise.resources.DisputeResource;
import co.omise.resources.ResourceTest;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DisputeRequestTest extends RequestTest {
    private static final String DISPUTE_ID = "dspt_test_5089off452g5m5te7xs";

    @Test
    public void testList() throws IOException, OmiseException {
        ScopedList<Dispute> list = resource().list();
        assertRequested("GET", "/disputes", 200);

        assertEquals(1, list.getTotal());
        assertEquals(20, list.getLimit());
    }

    @Test
    public void testListWithStatus() throws IOException, OmiseException {
        ScopedList<Dispute> list = resource().list(DisputeStatus.Closed);
        assertRequested("GET", "/disputes/closed", 200);
        assertEquals(DisputeStatus.Won, list.getData().get(0).getStatus());

        list = resource().list(DisputeStatus.Open);
        assertRequested("GET", "/disputes/open", 200);
        assertEquals(DisputeStatus.Open, list.getData().get(0).getStatus());

        list = resource().list(DisputeStatus.Pending);
        assertRequested("GET", "/disputes/pending", 200);
        assertEquals(DisputeStatus.Pending, list.getData().get(0).getStatus());
    }

    @Test
    public void testGet() throws IOException, OmiseException {
        Dispute dispute = resource().get(DISPUTE_ID);
        assertRequested("GET", "/disputes/" + DISPUTE_ID, 200);

        assertEquals("dspt_test_5089off452g5m5te7xs", dispute.getId());
        assertEquals(100000L, dispute.getAmount());
        assertEquals("thb", dispute.getCurrency());
        assertEquals("chrg_test_5089odjlzg9j7tw4i1q", dispute.getCharge());
        assertEquals("DESCRIPTION", dispute.getMetadata().get("description"));
        assertEquals("inv_N1ayTWJ2FV", dispute.getMetadata().get("invoice_id"));
    }

    @Test
    public void testUpdate() throws IOException, OmiseException {
        Request<Dispute> request = new Dispute.UpdateRequestBuilder(DISPUTE_ID)
                .message("Your dispute message")
                .metadata("description", "DESCRIPTION")
                .metadata("invoice_id", "inv_N1ayTWJ2FV")
                .build();

        Dispute dispute = getTestRequester().sendRequest(request, Dispute.class);

        assertRequested("PATCH", "/disputes/" + DISPUTE_ID, 200);

        assertEquals("dspt_test_5089off452g5m5te7xs", dispute.getId());
        assertEquals("Your dispute message", dispute.getMessage());

        assertEquals("DESCRIPTION", dispute.getMetadata().get("description"));
        assertEquals("inv_N1ayTWJ2FV", dispute.getMetadata().get("invoice_id"));
    }

    private DisputeResource resource() {
        return new DisputeResource(testClient());
    }
}
