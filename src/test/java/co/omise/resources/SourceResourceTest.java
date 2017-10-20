package co.omise.resources;

import co.omise.models.OmiseException;
import co.omise.models.Source;
import org.junit.Test;

import java.io.IOException;

public class SourceResourceTest extends ResourceTest {

    private final String CHARGE_ID = "src_test_5929boxipb803ak7o06";

    @Test
    public void testCreateAliPaySource() throws IOException, OmiseException {
        Source source = resource().create(new Source.Create()
                .amount(100000)
                .currency("thb")
                .type("alipay"));

        assertRequested("POST", "/sources", 200);
        assertEquals(100000L, source.getAmount());
        assertEquals("thb", source.getCurrency());
        assertEquals("alipay", source.getType());
//        assertEquals("xyz", source.getBarcode());
//        assertEquals("abc", source.getInvoiceId());
    }

    private SourceResource resource() {
        return new SourceResource(testClient());
    }
}
