package co.omise.requests;

import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import co.omise.models.Transfer;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TransferRequestTest extends RequestTest {
    private static final String TRANSFER_ID = "trsf_test_4yqacz8t3cbipcj766u";

    @Test
    public void testList() throws IOException, OmiseException, IllegalAccessException {
        Request<ScopedList<Transfer>> request = new Transfer.ListRequestBuilder()
                .build();
        ScopedList<Transfer> list = getTestRequester().sendRequest(request);
        assertRequested("GET", "/transfers", 200);

        assertEquals(20, list.getLimit());
        assertEquals(1, list.getTotal());

        Transfer transfer = list.getData().get(0);
        assertEquals(TRANSFER_ID, transfer.getId());
        assertEquals(192188L, transfer.getAmount());
    }

    @Test
    public void testGet() throws IOException, OmiseException, IllegalAccessException {
        Request<Transfer> request = new Transfer.GetRequestBuilder(TRANSFER_ID)
                .build();
        Transfer transfer = getTestRequester().sendRequest(request);
        assertRequested("GET", "/transfers/" + TRANSFER_ID, 200);

        assertEquals(TRANSFER_ID, transfer.getId());
        assertEquals(192188L, transfer.getAmount());
        assertEquals("6789", transfer.getBankAccount().getLastDigits());
        assertEquals("JOHN DOE", transfer.getBankAccount().getName());
    }

    @Test
    public void testCreate() throws IOException, OmiseException, IllegalAccessException {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("description", "DESCRIPTION");
        metadata.put("invoice_id", "inv_N1ayTWJ2FV");

        Request<Transfer> request = new Transfer.CreateRequestBuilder()
                .amount(192188)
                .metadata(metadata)
                .build();
        Transfer transfer = getTestRequester().sendRequest(request);
        assertRequested("POST", "/transfers", 200);

        assertEquals(TRANSFER_ID, transfer.getId());
        assertEquals(192188L, transfer.getAmount());
        assertEquals("DESCRIPTION", transfer.getMetadata().get("description"));
        assertEquals("inv_N1ayTWJ2FV", transfer.getMetadata().get("invoice_id"));
    }

    @Test
    public void testUpdate() throws IOException, OmiseException, IllegalAccessException {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("description", "DESCRIPTION");
        metadata.put("invoice_id", "inv_N1ayTWJ2FV");
        Request<Transfer> request = new Transfer.UpdateRequestBuilder(TRANSFER_ID)
                .amount(192189)
                .metadata(metadata)
                .build();
        Transfer transfer = getTestRequester().sendRequest(request);
        assertRequested("PATCH", "/transfers/" + TRANSFER_ID, 200);

        assertEquals(TRANSFER_ID, transfer.getId());
        assertEquals(192189L, transfer.getAmount());
        assertEquals("DESCRIPTION", transfer.getMetadata().get("description"));
        assertEquals("inv_N1ayTWJ2FV", transfer.getMetadata().get("invoice_id"));
    }

    @Test
    public void testDestroy() throws IOException, OmiseException, IllegalAccessException {
        Request<Transfer> request = new Transfer.DestroyRequestBuilder(TRANSFER_ID)
                .build();
        Transfer transfer = getTestRequester().sendRequest(request);
        assertRequested("DELETE", "/transfers/" + TRANSFER_ID, 200);
        assertEquals(TRANSFER_ID, transfer.getId());
        assertTrue(transfer.isDeleted());
    }
}
