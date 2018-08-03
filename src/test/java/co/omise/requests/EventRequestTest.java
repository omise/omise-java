package co.omise.requests;

import co.omise.models.Event;
import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import co.omise.models.Transfer;
import org.junit.Test;

import java.io.IOException;

public class EventRequestTest extends RequestTest {

    @Test
    public void testGet() throws IOException, OmiseException {
        Request<Event> request = new Event.GetRequestBuilder("evnt_test_526yctupnje5mbldskd").build();
        Event event = getTestRequester().sendRequest(request);

        assertEquals("evnt_test_526yctupnje5mbldskd", event.getId());
        assertEquals("transfer.destroy", event.getKey());

        Transfer transfer = (Transfer) event.getData();
        assertEquals("transfer", transfer.getObject());
        assertEquals("trsf_test_526yctqob5djkckq88a", transfer.getId());
        assertTrue(transfer.isDeleted());
    }

    @Test
    public void testList() throws IOException, OmiseException {
        Request<ScopedList<Event>> request = new Event.ListRequestBuilder().build();
        ScopedList<Event> events = getTestRequester().sendRequest(request);

        assertRequested("GET", "/events", 200);

        assertEquals(20, events.getLimit());
        assertEquals(301, events.getTotal());

        Event event = events.getData().get(0);
        assertEquals("evnt_test_5232t5tlhjwh7nbi14g", event.getId());
        assertEquals("customer.create", event.getKey());
    }
}
