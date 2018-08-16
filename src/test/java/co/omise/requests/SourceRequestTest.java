package co.omise.requests;

import co.omise.models.OmiseException;
import co.omise.models.Source;
import co.omise.models.SourceType;
import org.junit.Test;

import java.io.IOException;

public class SourceRequestTest extends RequestTest {

    @Test
    public void testCreate() throws IOException, OmiseException {
        Request<Source> request = new Source.CreateRequestBuilder()
                .amount(100000)
                .currency("thb")
                .type(SourceType.Alipay)
                .build();

        Source source = getTestRequester().sendRequest(request);

        assertRequested("POST", "/sources", 200);
        assertEquals(100000L, source.getAmount());
        assertEquals("thb", source.getCurrency());
        assertEquals("redirect", source.getFlow().toString());
        assertEquals("alipay", source.getType().toString());
    }
}