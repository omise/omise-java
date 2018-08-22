package co.omise.requests;

import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import co.omise.models.schedules.Occurrence;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class OccurrenceRequestTest extends RequestTest {

    private Requester requester;
    private final String SCHEDULE_ID = "schd_test_59wupnlq9lej6bi12i8";
    private final String OCCURRENCE_ID = "occu_test_59wupnlrayrqccw6lob";

    @Before
    public void setUp() {
        requester = getTestRequester();
    }

    @Test
    public void testGet() throws IOException, OmiseException {
        Request<Occurrence> request = new Occurrence.GetRequestBuilder(OCCURRENCE_ID).build();

        Occurrence occurrence = requester.sendRequest(request);

        assertRequested("GET", "/occurrences/" + OCCURRENCE_ID, 200);

        assertEquals(OCCURRENCE_ID, occurrence.getId());
        assertEquals(SCHEDULE_ID, occurrence.getSchedule());
    }

    @Test
    public void testList() throws IOException, OmiseException {
        Request<ScopedList<Occurrence>> request = new Occurrence.ListRequestBuilder(SCHEDULE_ID).build();

        ScopedList<Occurrence> scheduleList = requester.sendRequest(request);

        assertRequested("GET", "/schedules/" + SCHEDULE_ID + "/occurrences", 200);
        assertEquals(9, scheduleList.getTotal());
    }

}
