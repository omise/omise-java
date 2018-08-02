package co.omise.live;

import co.omise.Client;
import co.omise.models.*;
import co.omise.requests.Request;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class LiveEventRequestTest extends BaseLiveTest {

    private static final String EVENT_ID = "YOUR_EVENT";

    @Test
    @Ignore("only hit when test on live.")
    public void testGetEventSuccess() throws Exception {
        Client client = getLiveClient();

        Request<Event> request = new Event.GetRequestBuilder(EVENT_ID).build();
        Event event = client.sendRequest(request, Event.class);

        System.out.printf("Event retrieved: %s", event.getId());

        assertNotNull(event);
        // These can break depending on the EVENT_ID that's been picked
        assertEquals(EVENT_ID, event.getId());
        assertTrue(event.getData() instanceof Charge);
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveEventListGet() throws Exception {
        Client client = getLiveClient();

        Request<ScopedList<Event>> request = new Event.ListRequestBuilder().build();


        ScopedList<Event> events = client.sendRequest(request, new TypeReference<ScopedList<Event>>() {
        });

        assertEquals(20, events.getLimit());
        assertEquals(114, events.getTotal()); // This can easily break as you add events,

        Event event = events.getData().get(0);
        assertNotNull(event);
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveEventListGet_withOptions() throws Exception {
        Client client = getLiveClient();

        Request<ScopedList<Event>> request =
                new Event.ListRequestBuilder()
                        .options(new ScopedList.Options()
                                .limit(5)
                                .order(Ordering.ReverseChronological))
                        .build();


        ScopedList<Event> events = client.sendRequest(request, new TypeReference<ScopedList<Event>>() {
        });

        assertEquals(5, events.getLimit());
        assertEquals(5, events.getData().size());
        assertEquals(Ordering.ReverseChronological, events.getOrder());
        assertEquals(114, events.getTotal()); // This can easily break as you add events,

        Event event = events.getData().get(0);
        assertNotNull(event);
    }
}