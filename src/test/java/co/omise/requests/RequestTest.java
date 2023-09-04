package co.omise.requests;

import co.omise.OmiseTest;
import co.omise.Serializer;
import co.omise.testutils.TestInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import org.junit.Before;

import java.io.IOException;

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
}
