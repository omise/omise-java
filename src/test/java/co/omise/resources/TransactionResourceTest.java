package co.omise.resources;

import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import co.omise.models.Transaction;
import co.omise.models.TransactionType;
import org.junit.Test;

import java.io.IOException;

public class TransactionResourceTest extends ResourceTest {
    private static final String TRANSACTION_ID = "trxn_test_4yq7duwb9jts1vxgqua";

    @Test
    public void testList() throws IOException, OmiseException {
        ScopedList<Transaction> list = resource().list();
        assertRequested("GET", "/transactions", 200);

        assertEquals(2, list.getTotal());
        assertEquals(20, list.getLimit());

        Transaction transaction = list.getData().get(0);
        assertEquals(TRANSACTION_ID, transaction.getId());
        assertEquals(TransactionType.Credit, transaction.getType());
        assertEquals(96094L, transaction.getAmount());
        assertEquals("THB", transaction.getCurrency());
    }

    @Test
    public void testGet() throws IOException, OmiseException {
        Transaction transaction = resource().get(TRANSACTION_ID);
        assertRequested("GET", "/transactions/" + TRANSACTION_ID, 200);

        assertEquals(TRANSACTION_ID, transaction.getId());
        assertEquals(TransactionType.Credit, transaction.getType());
        assertEquals(96094L, transaction.getAmount());
        assertEquals("THB", transaction.getCurrency());
    }

    private TransactionResource resource() {
        return new TransactionResource(testClient());
    }
}