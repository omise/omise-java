package co.omise.live;

import co.omise.Client;
import co.omise.models.Charge;
import co.omise.models.Event;
import co.omise.requests.Request;
import org.junit.Ignore;
import org.junit.Test;

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
}