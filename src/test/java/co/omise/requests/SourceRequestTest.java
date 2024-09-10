package co.omise.requests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.omise.models.OmiseException;
import co.omise.models.Shipping;
import co.omise.models.Source;
import co.omise.models.SourceType;
import co.omise.testutils.TestSourceRequestBuilder;
import org.junit.Test;

import java.io.IOException;

public class SourceRequestTest extends RequestTest {

    @Test
    public void testCreate() throws IOException, OmiseException {

        Shipping shipping = new Shipping();
        shipping.country = "TH";
        shipping.postalCode = "000000";
        shipping.street1 = "Street";

        Request<Source> request = new Source.CreateRequestBuilder()
                .amount(100000)
                .currency("thb")
                .type(SourceType.Alipay)
                .billing(shipping)
                .promotionCode("code")
                .build();

        Source source = getTestRequester().sendRequest(request);
        assertRequestBody("{\"amount\":100000,\"bank\":null,\"barcode\":null,\"currency\":\"thb\",\"email\":null,\"ip\":null,\"name\":null,\"type\":\"alipay\",\"shipping\":null,\"billing\":{\"street1\":\"Street\",\"street2\":null,\"city\":null,\"state\":null,\"country\":\"TH\",\"postal_code\":\"000000\"},\"installment_term\":0,\"mobile_number\":null,\"store_id\":null,\"store_name\":null,\"terminal_id\":null,\"zero_interest_installments\":false,\"platform_type\":null,\"phone_number\":null,\"items\":null,\"promotion_code\":\"code\"}");

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
    public void testWeChatPaySourceIpMustBeNullWhenEmpty() throws IOException {
        Request<Source> request = new TestSourceRequestBuilder()
                .type(SourceType.WeChatPay)
                .amount(500000)
                .currency("thb")
                .build();

        // The key must exist in the request with a value of null
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(request.getPayloadToString());

        assertTrue(rootNode.has("ip"));
        assertNull(rootNode.get("ip").textValue());

    }

    @Test
    public void testWeChatPaySourceIpMustHaveValueWhenPassed() throws IOException {
        String ip = "127.0.0.1";
        Request<Source> request = new TestSourceRequestBuilder()
                .type(SourceType.WeChatPay)
                .amount(500000)
                .ip(ip)
                .currency("thb")
                .build();

        // The key must exist in the request with the passed value
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(request.getPayloadToString());

        assertTrue(rootNode.has("ip"));
        assertEquals(rootNode.get("ip").textValue(), ip);

    }
}
