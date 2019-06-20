package co.omise.live;

import co.omise.Client;
import co.omise.models.Charge;
import co.omise.models.Event;
import co.omise.models.Ordering;
import co.omise.models.ScopedList;
import co.omise.requests.Request;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LiveEventRequestTest extends BaseLiveTest {
    private static final String EVENT_ID = "YOUR_EVENT";

    private Client client;

    @Before
    public void setup() throws Exception {
        client = getLiveClient();
    }

    @Test
    @Ignore("only hit when test on live.")
    public void testGetEventSuccess() throws Exception {
        Request<Event> request = new Event.GetRequestBuilder(EVENT_ID).build();
        Event event = client.sendRequest(request);

        System.out.printf("Event retrieved: %s", event.getId());

        assertNotNull(event);
        // These can break depending on the EVENT_ID that's been picked
        assertEquals(EVENT_ID, event.getId());
        assertTrue(event.getData() instanceof Charge);
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveEventListGet() throws Exception {
        Request<ScopedList<Event>> request = new Event.ListRequestBuilder().build();

        ScopedList<Event> events = client.sendRequest(request);

        assertEquals(20, events.getLimit());
        assertTrue( events.getTotal() > 0);

        Event event = events.getData().get(0);
        assertNotNull(event);
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveEventListGet_withOptions() throws Exception {
        Request<ScopedList<Event>> request =
                new Event.ListRequestBuilder()
                        .options(new ScopedList.Options()
                                .limit(5)
                                .order(Ordering.ReverseChronological))
                        .build();

        ScopedList<Event> events = client.sendRequest(request);

        assertEquals(5, events.getLimit());
        assertEquals(5, events.getData().size());
        assertEquals(Ordering.ReverseChronological, events.getOrder());
        assertTrue(events.getTotal() > 0);

        Event event = events.getData().get(0);
        assertNotNull(event);
    }
}