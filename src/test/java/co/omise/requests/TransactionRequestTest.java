package co.omise.requests;

import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import co.omise.models.Transaction;
import co.omise.models.TransactionType;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;

import java.io.IOException;

public class TransactionRequestTest extends RequestTest {
    private static final String TRANSACTION_ID = "trxn_test_4yq7duwb9jts1vxgqua";

    @Test
    public void testList() throws IOException, OmiseException {
        Request<Transaction> request = new Transaction.ListRequestBuilder().build();
        ScopedList<Transaction> list = getTestRequester().sendRequest(request, new TypeReference<ScopedList<Transaction>>() {});
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
        Request<Transaction> request = new Transaction.GetRequestBuilder(TRANSACTION_ID).build();
        Transaction transaction = getTestRequester().sendRequest(request, Transaction.class);
        assertRequested("GET", "/transactions/" + TRANSACTION_ID, 200);

        assertEquals(TRANSACTION_ID, transaction.getId());
        assertEquals(TransactionType.Credit, transaction.getType());
        assertEquals(96094L, transaction.getAmount());
        assertEquals("THB", transaction.getCurrency());
    }
}
