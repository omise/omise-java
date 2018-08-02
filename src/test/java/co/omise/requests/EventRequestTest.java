package co.omise.requests;

import co.omise.models.Event;
import co.omise.models.OmiseException;
import co.omise.models.Transfer;
import org.junit.Test;

import java.io.IOException;

public class EventRequestTest extends RequestTest {

    @Test
    public void testGet() throws IOException, OmiseException {
        Request<Event> request = new Event.GetRequestBuilder("evnt_test_526yctupnje5mbldskd").build();
        Event event = getTestRequester().sendRequest(request, Event.class);

        assertEquals("evnt_test_526yctupnje5mbldskd", event.getId());
        assertEquals("transfer.destroy", event.getKey());

        Transfer transfer = (Transfer) event.getData();
        assertEquals("transfer", transfer.getObject());
        assertEquals("trsf_test_526yctqob5djkckq88a", transfer.getId());
        assertTrue(transfer.isDeleted());
    }
}
