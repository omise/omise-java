package co.omise.requests;

import co.omise.models.OmiseException;
import co.omise.models.Receipt;
import co.omise.models.Recipient;
import co.omise.models.ScopedList;
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

    @Test
    public void testList() throws IOException, OmiseException{
        Request<ScopedList<Receipt>> request = new Receipt.ListRequestBuilder()
                .build();
        ScopedList<Receipt> receipts = getTestRequester().sendRequest(request);

        assertRequested("GET", "/receipts", 200);

        assertEquals(1, receipts.getTotal());
        assertEquals(20, receipts.getLimit());

        Receipt receipt = receipts.getData().get(0);
        assertEquals(RECEIPT_ID, receipt.getId());
        assertEquals("john@omise.co", receipt.getCustomerEmail());
    }
}