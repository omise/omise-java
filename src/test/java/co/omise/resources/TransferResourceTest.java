package co.omise.resources;

import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import co.omise.models.Transfer;
import org.junit.Test;

import java.io.IOException;

public class TransferResourceTest extends ResourceTest {
    private static final String TRANSFER_ID = "trsf_test_4yqacz8t3cbipcj766u";

    @Test
    public void testList() throws IOException, OmiseException {
        ScopedList<Transfer> list = resource().list();
        assertRequested("GET", "/transfers", 200);

        assertEquals(20, list.getLimit());
        assertEquals(1, list.getTotal());

        Transfer transfer = list.getData().get(0);
        assertEquals(TRANSFER_ID, transfer.getId());
        assertEquals(192188L, transfer.getAmount());
    }

    @Test
    public void testGet() throws IOException, OmiseException {
        Transfer transfer = resource().get(TRANSFER_ID);
        assertRequested("GET", "/transfers/" + TRANSFER_ID, 200);

        assertEquals(TRANSFER_ID, transfer.getId());
        assertEquals(192188L, transfer.getAmount());
        assertEquals("6789", transfer.getBankAccount().getLastDigits());
        assertEquals("JOHN DOE", transfer.getBankAccount().getName());
    }

    @Test
    public void testCreate() throws IOException, OmiseException {
        Transfer transfer = resource().create(new Transfer.Create().amount(192188));
        assertRequested("POST", "/transfers", 200);

        assertEquals(TRANSFER_ID, transfer.getId());
        assertEquals(192188L, transfer.getAmount());
    }

    @Test
    public void testUpdate() throws IOException, OmiseException {
        Transfer transfer = resource().update(TRANSFER_ID, new Transfer.Update().amount(192189));
        assertRequested("PATCH", "/transfers/" + TRANSFER_ID, 200);

        assertEquals(TRANSFER_ID, transfer.getId());
        assertEquals(192189L, transfer.getAmount());
    }

    private TransferResource resource() {
        return new TransferResource(testClient());
    }
}