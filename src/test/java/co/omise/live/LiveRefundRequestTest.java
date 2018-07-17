package co.omise.live;

import co.omise.Client;
import co.omise.models.OmiseException;
import co.omise.models.Refund;
import co.omise.models.ScopedList;
import co.omise.requests.Request;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LiveRefundRequestTest extends BaseLiveTest {

    private final String LIVETEST_CHARGE = "[YOUR_CHARGE]";
    private final String LIVETEST_REFUND = "[YOUR_REFUND]";

    private Client client;

    @Before
    public void setup() throws Exception {
        client = getLiveClient();
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveCreateRefund() throws IOException, OmiseException {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("description", "DESCRIPTION");
        metadata.put("invoice_id", "inv_N1ayTWJ2FV");
        Request<Refund> request = new Refund.CreateRequestBuilder(LIVETEST_CHARGE)
                .amount(10000)
                .metadata(metadata)
                .build();
        Refund refund = client.sendRequest(request, Refund.class);

        System.out.println("created refund: " + refund.getId());

        assertEquals("DESCRIPTION", refund.getMetadata().get("description"));
        assertEquals("inv_N1ayTWJ2FV", refund.getMetadata().get("invoice_id"));
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveGetRefund() throws IOException, OmiseException {
        Request<Refund> request = new Refund.GetRequestBuilder(LIVETEST_CHARGE, LIVETEST_REFUND).build();
        Refund refund = client.sendRequest(request, Refund.class);

        System.out.println("retrieved refund: " + refund.getId());

        assertEquals(LIVETEST_REFUND, refund.getId());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveGetRefundList() throws IOException, OmiseException {
        Request<ScopedList<Refund>> request = new Refund.ListRequestBuilder(LIVETEST_CHARGE).build();
        ScopedList<Refund> refunds = client.sendRequest(request, new TypeReference<ScopedList<Refund>>() {});

        System.out.println("retrieved refund list total no.: " + refunds.getTotal());

        assertNotNull(refunds.getData().get(0));
    }
}
