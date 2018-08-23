package co.omise.requests;

import co.omise.models.OmiseException;
import co.omise.models.Receipt;
import org.junit.Test;

import java.io.IOException;

public class ReceiptRequestTest extends RequestTest {
    private static final String RECEIPT_ID = "rcpt_test_12345";

    @Test
    public void testGet() throws IOException, OmiseException {
        Request<Receipt> request = new Receipt.GetRequestBuilder(RECEIPT_ID)
                .build();

        Receipt receipt = getTestRequester().sendRequest(request);

        assertRequested("GET", "/receipts/" + RECEIPT_ID, 200);

        assertEquals(RECEIPT_ID, receipt.getId());
        assertEquals("John Doe", receipt.getCustomerName());
        assertEquals("Tax ID 1234", receipt.getCustomerTaxId());
        assertEquals(3906, receipt.getTotal());

    }

}
