package co.omise.live;

import co.omise.Client;
import co.omise.models.Dispute;
import co.omise.models.OmiseException;
import co.omise.requests.Request;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LiveDisputeRequestTest extends BaseLiveTest {

    private final String LIVETEST_DISPUTE = "[YOUR_DISPUTE]";

    private Client client;

    @Before
    public void setup() throws Exception {
        client = getLiveClient();
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
