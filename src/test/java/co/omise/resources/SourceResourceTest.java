package co.omise.resources;

import co.omise.models.OmiseException;
import co.omise.models.Source;

import co.omise.models.SourceType;
import org.junit.Test;

import java.io.IOException;

public class SourceResourceTest extends ResourceTest {

    @Test
    public void testCreate() throws IOException, OmiseException {
        Source source = resource().create(new Source.Create()
                .amount(100000)
                .currency("thb")
                .type(SourceType.Alipay));

        assertRequested("POST", "/sources", 200);
        assertEquals(100000L, source.getAmount());
        assertEquals("thb", source.getCurrency());
        assertEquals("redirect", source.getFlow().toString());
        assertEquals("alipay", source.getType().toString());
    }

    private SourceResource resource() {
        return new SourceResource(testClient());
    }
}
