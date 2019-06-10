package co.omise.requests;

import co.omise.models.Forex;
import co.omise.models.OmiseException;
import org.junit.Test;

import java.io.IOException;

public class ForexRequestTest extends RequestTest {

    @Test
    public void testGet() throws IOException, OmiseException {
        Request<Forex> request = new Forex.GetRequestBuilder("usd")
                .build();

        Forex forex = getTestRequester().sendRequest(request);

        assertRequested("GET", "/forex/usd", 200);

        assertEquals("USD", forex.getBase());
        assertEquals("THB", forex.getQuote());
    }
}