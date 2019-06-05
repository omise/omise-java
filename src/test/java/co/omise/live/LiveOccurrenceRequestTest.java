package co.omise.live;

import co.omise.Client;
import co.omise.models.OmiseException;
import co.omise.models.Ordering;
import co.omise.models.ScopedList;
import co.omise.models.schedules.Occurrence;
import co.omise.requests.Request;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LiveOccurrenceRequestTest extends BaseLiveTest {

    private Client client;

    // Required schedule object that have occurrences.
    private final String SCHEDULE_ID = "[SCHEDULE_ID]";

    @Before
    public void setUp() throws Exception {
        client = getLiveClient();
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveListOccurrenceGet() throws IOException, OmiseException {
        Request<ScopedList<Occurrence>> request = new Occurrence.ListRequestBuilder(SCHEDULE_ID).build();
        ScopedList<Occurrence> occurrences = client.sendRequest(request);

        System.out.println("get occurrence list: " + occurrences.getTotal());

        assertNotNull(occurrences);
        assertEquals(SCHEDULE_ID, occurrences.getData().get(0).getSchedule());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveListOccurrenceGetWithOption() throws IOException, OmiseException {
        ScopedList.Options options = new ScopedList.Options()
                .limit(3)
                .order(Ordering.Chronological);
        Request<ScopedList<Occurrence>> request = new Occurrence.ListRequestBuilder(SCHEDULE_ID)
                .options(options)
                .build();
        ScopedList<Occurrence> occurrences = client.sendRequest(request);

        System.out.println("get occurrence list: " + occurrences.getTotal());

        assertNotNull(occurrences);
        assertEquals(3, occurrences.getTotal());
        assertEquals(Ordering.Chronological, occurrences.getOrder());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveOccurrenceGet() throws IOException, OmiseException {
        Request<ScopedList<Occurrence>> scopedListRequest = new Occurrence.ListRequestBuilder(SCHEDULE_ID).build();
        ScopedList<Occurrence> occurrences = client.sendRequest(scopedListRequest);

        Occurrence expectedOccurrence = occurrences.getData().get(0);
        Request<Occurrence> occurrenceRequest = new Occurrence.GetRequestBuilder(expectedOccurrence.getId()).build();
        Occurrence actualOccurrence = client.sendRequest(occurrenceRequest);

        System.out.println("get occurrence: " + actualOccurrence.getId());

        assertNotNull(actualOccurrence);
        assertEquals(expectedOccurrence.getId(), actualOccurrence.getId());
    }
}
