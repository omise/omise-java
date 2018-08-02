package co.omise.resources;

import co.omise.models.Event;
import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import co.omise.models.Transfer;
import org.junit.Test;

import java.io.IOException;

public class EventResourceTest extends ResourceTest {
    @Test
    public void testList() throws IOException, OmiseException {
        ScopedList<Event> list = resource().list();
        assertRequested("GET", "/events", 200);

        assertEquals(20, list.getLimit());
        assertEquals(301, list.getTotal());

        Event event = list.getData().get(0);
        assertEquals("evnt_test_5232t5tlhjwh7nbi14g", event.getId());
        assertEquals("customer.create", event.getKey());
    }

    private EventResource resource() {
        return new EventResource(testClient());
    }
}