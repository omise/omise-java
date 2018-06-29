package co.omise.live;

import co.omise.Client;
import co.omise.models.*;
import co.omise.requests.Request;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LiveTransferRequestTest extends BaseLiveTest {
    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveCreateTransfer() throws Exception {
        Client client = getLiveClient();
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("description", "DESCRIPTION");
        metadata.put("invoice_id", "inv_N1ayTWJ2FV");
        Recipient recipient = client.recipients().create(new Recipient.Create()
                .name("Omise-Java Recipient")
                .email("support@omise.co")
                .type(RecipientType.Individual)
                .bankAccount(new BankAccount.Params()
                        .name("Omise-Java Bank")
                        .number("7772-727-272")
                        .brand("kbank")));
        System.out.println("created recipient: " + recipient.getId());

        Request<Transfer> request = new Transfer.CreateRequestBuilder()
                .recipient(recipient.getId())
                .amount(10000)
                .metadata(metadata)
                .build();
        Transfer transfer = client.sendRequest(request, Transfer.class);
        System.out.println("created transfer: " + transfer.getId());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveTransferListGet() throws Exception {
        Request<Transfer> request = new Transfer.ListRequestBuilder()
                .build();

        ScopedList<Transfer> transfers = getLiveClient().sendRequest(request, new TypeReference<ScopedList<Transfer>>() {
        });

        assertEquals(20, transfers.getLimit());
        assertEquals(21, transfers.getTotal());

        Transfer transfer = transfers.getData().get(0);
        assertNotNull(transfer);
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveTransferListGetWithOption() throws Exception {
        ScopedList.Options options = new ScopedList.Options()
                .order(Ordering.Chronological)
                .limit(3);
        Request<Transfer> request = new Transfer.ListRequestBuilder()
                .options(options)
                .build();
        ScopedList<Transfer> transfers = getLiveClient().sendRequest(request, new TypeReference<ScopedList<Transfer>>() {
        });

        assertEquals(3, transfers.getLimit());
        assertEquals(21, transfers.getTotal());
        assertEquals(Ordering.Chronological, transfers.getOrder());

        Transfer transfer = transfers.getData().get(0);
        assertNotNull(transfer);
    }

    @Test
    @Ignore("only hit when test on live.")
    public void testLiveGetTransfer() throws Exception {
        Client client = getLiveClient();
        Request<Transfer> creatingTransferRequest = new Transfer.CreateRequestBuilder()
                .amount(10000)
                .build();
        Transfer expectedTransfer = client.sendRequest(creatingTransferRequest, Transfer.class);

        Request<Transfer> gettingTransferRequest = new Transfer.GetRequestBuilder(expectedTransfer.getId())
                .build();
        Transfer actualTransfer = client.sendRequest(gettingTransferRequest, Transfer.class);

        System.out.println("Transfer retrieved: " + actualTransfer.getId());
        assertEquals(expectedTransfer.getId(), actualTransfer.getId());
        assertEquals(expectedTransfer.getAmount(), actualTransfer.getAmount());
    }

    @Test
    @Ignore("only hit when test on live.")
    public void testLiveUpdateTransfer() throws Exception {
        Client client = getLiveClient();
        Request<Transfer> creatingTransferRequest = new Transfer.CreateRequestBuilder()
                .amount(10000)
                .build();
        Transfer expectedTransfer = client.sendRequest(creatingTransferRequest, Transfer.class);

        Request<Transfer> request = new Transfer.UpdateRequestBuilder(expectedTransfer.getId())
                .amount(20000)
                .build();
        Transfer actualTransfer = client.sendRequest(request, Transfer.class);

        System.out.println("Updated transfer: " + actualTransfer.getId());
        assertEquals(20000, actualTransfer.getAmount());
    }

    @Test
    @Ignore("only hit when test on live.")
    public void testLiveDestroyTransfer() throws Exception {
        Client client = getLiveClient();
        Request<Transfer> creatingTransferRequest = new Transfer.CreateRequestBuilder()
                .amount(10000)
                .build();
        Transfer expectedTransfer = client.sendRequest(creatingTransferRequest, Transfer.class);

        Request<Transfer> request = new Transfer.DestroyRequestBuilder(expectedTransfer.getId())
                .build();
        Transfer actualTransfer = client.sendRequest(request, Transfer.class);

        System.out.println("Destroy transfer: " + actualTransfer.getId());
        assertEquals(expectedTransfer.getId(), actualTransfer.getId());
        assertTrue(actualTransfer.isDeleted());
    }
}
