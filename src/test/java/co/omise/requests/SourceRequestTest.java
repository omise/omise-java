package co.omise.requests;

import co.omise.models.OmiseException;
import co.omise.models.Source;
import co.omise.models.SourceType;
import co.omise.testutils.TestSourceRequestBuilder;
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

    @Test
    public void testCreateInstallment() throws IOException, OmiseException {
        Request<Source> request = new TestSourceRequestBuilder()
                .type(SourceType.InstallmentBay)
                .amount(500000)
                .currency("thb")
                .installmentTerm(4)
                .build();

        Source source = getTestRequester().sendRequest(request);

        assertRequested("POST", "/sources/installments", 200);
        assertEquals(500000L, source.getAmount());
        assertEquals(SourceType.InstallmentBay, source.getType());
        assertEquals(4, source.getInstallmentTerm());
        assertEquals("thb", source.getCurrency());
        assertEquals("redirect", source.getFlow().toString());
    }

    @Test
    public void testCreateMobileBanking() throws IOException, OmiseException {
        Request<Source> request = new TestSourceRequestBuilder()
                .type(SourceType.MobileBankingOCBCPAO)
                .platformType(PlatformType.iOS)
                .amount(500000)
                .currency("sgd")
                .build();

        Source source = getTestRequester().sendRequest(request);

        assertRequested("POST", "/sources/installments", 200);
        assertEquals(500000L, source.getAmount());
        assertEquals(SourceType.MobileBankingOCBCPAO, source.getType());
        assertEquals(PlatformType.iOS, source.getPlatformType());
        assertEquals("sgd", source.getCurrency());
    }
}
