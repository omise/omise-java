package co.omise.live;

import co.omise.Client;
import co.omise.models.*;
import co.omise.requests.Request;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LiveTransactionRequestTest extends BaseLiveTest {

    private Client client;

    @Before
    public void setUp() throws Exception {
        client = getLiveClient();
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveGetTransaction() throws IOException, OmiseException {
        Token token = client.tokens().create(new Token.Create()
                .card(new Card.Create()
                        .name("John Deo")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2020)));
        Request<Charge> createChargeRequest = new Charge.CreateRequestBuilder()
                .amount(10000)
                .currency("thb")
                .card(token.getId())
                .build();
        Charge charge = client.sendRequest(createChargeRequest, Charge.class);

        Request<Transaction> request = new Transaction.GetRequestBuilder(charge.getTransaction()).build();
        Transaction transaction = client.sendRequest(request, Transaction.class);

        System.out.println("Retrieved transaction: " + transaction.getId());

        assertEquals(charge.getTransaction(), transaction.getId());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveListTransaction() throws IOException, OmiseException {
        Request<ScopedList<Transaction>> request = new Transaction.ListRequestBuilder().build();
        ScopedList<Transaction> transactions = client.sendRequest(request, new TypeReference<ScopedList<Transaction>>() {});

        assertEquals(20, transactions.getLimit());
        Transaction transaction = transactions.getData().get(0);
        assertNotNull(transaction);
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveListTransactionWithOption() throws IOException, OmiseException {
        ScopedList.Options options = new ScopedList.Options()
                .limit(3)
                .order(Ordering.Chronological);
        Request<ScopedList<Transaction>> request = new Transaction.ListRequestBuilder().options(options).build();
        ScopedList<Transaction> transactions = client.sendRequest(request, new TypeReference<ScopedList<Transaction>>() {});

        assertEquals(3, transactions.getLimit());
        assertEquals(Ordering.Chronological, transactions.getOrder());
        Transaction transaction = transactions.getData().get(0);
        assertNotNull(transaction);
    }

}
