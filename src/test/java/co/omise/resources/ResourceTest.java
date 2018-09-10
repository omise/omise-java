package co.omise.resources;

import co.omise.OmiseTest;
import co.omise.models.OmiseException;
import co.omise.models.Ordering;
import co.omise.models.ScopedList;
import co.omise.testutils.TestInterceptor;
import com.google.common.collect.ImmutableMap;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.Chronology;
import java.util.Map;

public class ResourceTest extends OmiseTest {
    private TestInterceptor interceptor;

    @Before
    public void setup() throws Exception {
        interceptor = new TestInterceptor(this);
    }

    Request lastRequest() {
        return interceptor.lastRequest();
    }

    @Test
    public void testListOptions() throws IOException, OmiseException {
        LocalDateTime from = LocalDateTime.of(1964, 1, 2, 12, 22);
        LocalDateTime to = LocalDateTime.of(1987, 2, 1, 19, 54);

        ScopedList.Options options = new ScopedList.Options()
                .order(Ordering.ReverseChronological)
                .from(from)
                .to(to)
                .offset(20)
                .limit(40);

        Map<String, String> expects = ImmutableMap.of(
                "order", "reverse_chronological",
                "from", "1964-01-02T12:22:00Z",
                "to", "1987-02-01T19:54:00Z",
                "offset", "20",
                "limit", "40"
        );

        DummyResource resource = new DummyResource(testClient());
        resource.list(options);

        Request request = lastRequest();
        for (Map.Entry<String, String> entry : expects.entrySet()) {
            assertEquals(entry.getValue(), request.url().queryParameter(entry.getKey()));
        }
    }

    protected OkHttpClient testClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    protected void assertRequested(String method, String path, int code) {
        Request request = interceptor.lastRequest();
        assertNotNull(request);
        assertEquals(method, request.method());
        assertEquals(path, request.url().encodedPath());

        Response response = interceptor.lastResponse();
        assertNotNull(response);
        assertEquals(code, response.code());
    }
}
