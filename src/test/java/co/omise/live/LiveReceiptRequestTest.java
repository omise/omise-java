package co.omise.live;

import co.omise.models.Ordering;
import co.omise.models.Receipt;
import co.omise.models.ScopedList;
import co.omise.requests.Request;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LiveReceiptRequestTest extends BaseLiveTest {

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveGetReceiptList() throws Exception {
        Request<ScopedList<Receipt>> request = new Receipt.ListRequestBuilder()
                .build();

        ScopedList<Receipt> receipts = getLiveClient().sendRequest(request);

        // The assertion here are not complete (comparing to other tests) because receipts api does not return any data in test mode
        assertNotNull(receipts);
        assertEquals(20, receipts.getLimit());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveGetReceiptListWithOptions() throws Exception {
        Request<ScopedList<Receipt>> request = new Receipt.ListRequestBuilder()
                .options(new ScopedList.Options()
                        .limit(3)
                        .order(Ordering.Chronological))
                .build();

        ScopedList<Receipt> receipts = getLiveClient().sendRequest(request);

        // The assertion here are not complete (comparing to other tests) because receipts api does not return any data in test mode
        assertNotNull(receipts);
        assertEquals(3, receipts.getLimit());
        assertEquals(Ordering.Chronological, receipts.getOrder());
    }
}