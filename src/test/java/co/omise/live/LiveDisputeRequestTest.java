package co.omise.live;

import co.omise.Client;
import co.omise.models.*;
import co.omise.requests.Request;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LiveDisputeRequestTest extends BaseLiveTest {

    private final String LIVETEST_DISPUTE = "[YOUR_DISPUTE]";

    private Client client;

    @Before
    public void setup() throws Exception {
        client = getLiveClient();
    }

    @Test
//    @Ignore("only hit the network when we need to.")
    public void testLiveGetDisputeList() throws IOException, OmiseException {
        Request<ScopedList<Dispute>> request = new Dispute.ListRequestBuilder()
                .status(DisputeStatus.Lost)
                .options(new ScopedList.Options().order(Ordering.ReverseChronological)).build();
        ScopedList<Dispute> disputes = client.sendRequest(request, new TypeReference<ScopedList<Dispute>>() {});

        System.out.println("retrieved dispute list total no.: " + disputes.getTotal());

        assertNotNull(disputes.getData().get(0));
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveGetDispute() throws IOException, OmiseException {
        Request<Dispute> request = new Dispute.GetRequestBuilder(LIVETEST_DISPUTE).build();
        Dispute actualDispute = client.sendRequest(request, Dispute.class);

        System.out.println("Retrieved dispute: " + actualDispute.getId());

        assertEquals(LIVETEST_DISPUTE, actualDispute.getId());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveDispute() throws IOException, OmiseException {
        Request<Dispute> request = new Dispute.UpdateRequestBuilder(LIVETEST_DISPUTE)
                .message("Proofs and other information...")
                .metadata("description", "DESCRIPTION")
                .metadata("invoice_id", "inv_N1ayTWJ2FV")
                .build();
        Dispute dispute = client.sendRequest(request, Dispute.class);

        System.out.println("updated dispute: " + dispute.getId());

        assertEquals("DESCRIPTION", dispute.getMetadata().get("description"));
        assertEquals("inv_N1ayTWJ2FV", dispute.getMetadata().get("invoice_id"));
    }

}
