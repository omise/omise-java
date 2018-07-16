package co.omise.live;

import co.omise.Client;
import co.omise.models.OmiseException;
import co.omise.models.Refund;
import co.omise.requests.Request;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class LiveRefundRequestTest extends BaseLiveTest {

    private Client client;

    @Before
    public void setup() throws Exception {
        client = getLiveClient();
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveRefund() throws IOException, OmiseException {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("description", "DESCRIPTION");
        metadata.put("invoice_id", "inv_N1ayTWJ2FV");
        Request<Refund> request = new Refund.CreateRequestBuilder(getChargeRefundId())
                .amount(10000)
                .metadata(metadata)
                .build();
        Refund refund = client.sendRequest(request, Refund.class);

        System.out.println("created refund: " + refund.getId());

        assertEquals("DESCRIPTION", refund.getMetadata().get("description"));
        assertEquals("inv_N1ayTWJ2FV", refund.getMetadata().get("invoice_id"));
    }
}
