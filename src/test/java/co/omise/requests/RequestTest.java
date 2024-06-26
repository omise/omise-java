package co.omise.requests;

import co.omise.OmiseTest;
import co.omise.Serializer;
import co.omise.models.Charge;
import co.omise.models.OmiseException;
import co.omise.models.Ordering;
import co.omise.models.ScopedList;
import co.omise.testutils.TestInterceptor;
import com.google.common.collect.ImmutableMap;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.*;
import java.util.Map;

public class RequestTest extends OmiseTest {
    private TestInterceptor interceptor;

    @Before
    public void setup() {
        interceptor = new TestInterceptor(this);
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

    protected void assertRequestBody(String expectedRequestBody) {
        Request request = interceptor.lastRequest();
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            final String actualRequestBody = buffer.readUtf8();
            assertEquals(expectedRequestBody, actualRequestBody);
        } catch (final IOException e) {
            e.printStackTrace();
            fail("Error occurred.");
        }
    }

    Requester getTestRequester() {
        return new RequesterImpl(testClient(), Serializer.defaultSerializer());
    }

    Request lastRequest() {
        return interceptor.lastRequest();
    }

    @Test
    public void testListOptions() throws IOException, OmiseException {
        LocalDateTime from = LocalDateTime.of(1964, 1, 2, 12, 22);
        LocalDateTime to = LocalDateTime.of(1987, 2, 1, 19, 54);
        ZonedDateTime fromZonedDateTime = ZonedDateTime.of(from, ZoneOffset.UTC);
        ZonedDateTime toZonedDateTime = ZonedDateTime.of(to, ZoneOffset.UTC);

        ScopedList.Options options = new ScopedList.Options()
                .order(Ordering.ReverseChronological)
                .from(fromZonedDateTime)
                .to(toZonedDateTime)
                .offset(20)
                .limit(40);

        Map<String, String> expects = ImmutableMap.of(
                "order", "reverse_chronological",
                "from", "1964-01-02T12:22:00Z",
                "to", "1987-02-01T19:54:00Z",
                "offset", "20",
                "limit", "40"
        );

        co.omise.requests.Request<ScopedList<Charge>> chargeRequest = new DummyRequestBuilder().options(options).build();
        getTestRequester().sendRequest(chargeRequest);

        Request request = lastRequest();
        for (Map.Entry<String, String> entry : expects.entrySet()) {
            assertEquals(entry.getValue(), request.url().queryParameter(entry.getKey()));
        }
    }
}
