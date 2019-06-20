package co.omise.live;

import co.omise.Client;
import co.omise.models.*;
import co.omise.requests.Request;
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

        Request<Recipient> recipientRequest = new Recipient.CreateRequestBuilder()
                .name("Omise-Java Recipient")
                .email("support@omise.co")
                .type(RecipientType.Individual)
                .bankAccount(new BankAccount.Params()
                        .name("Omise-Java Bank")
                        .number("7772-727-272")
                        .brand("kbank"))
                .build();
        Recipient recipient = client.sendRequest(recipientRequest);

        System.out.println("created recipient: " + recipient.getId());

        Request<Transfer> request = new Transfer.CreateRequestBuilder()
                .recipient(recipient.getId())
                .amount(10000)
                .metadata(metadata)
                .build();
        Transfer transfer = client.sendRequest(request);

        System.out.println("created transfer: " + transfer.getId());
        assertEquals(10000, transfer.getAmount());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveTransferListGet() throws Exception {
        Request<ScopedList<Transfer>> request = new Transfer.ListRequestBuilder()
                .build();

        ScopedList<Transfer> transfers = getLiveClient().sendRequest(request);

        assertEquals(20, transfers.getLimit());
        assertTrue(transfers.getTotal() > 0);

        Transfer transfer = transfers.getData().get(0);
        assertNotNull(transfer);
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveTransferListGetWithOption() throws Exception {
        ScopedList.Options options = new ScopedList.Options()
                .order(Ordering.Chronological)
                .limit(3);
        Request<ScopedList<Transfer>> request = new Transfer.ListRequestBuilder()
                .options(options)
                .build();
        ScopedList<Transfer> transfers = getLiveClient().sendRequest(request);

        assertEquals(3, transfers.getLimit());
        assertTrue(transfers.getTotal() > 0);
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
        Transfer expectedTransfer = client.sendRequest(creatingTransferRequest);

        Request<Transfer> gettingTransferRequest = new Transfer.GetRequestBuilder(expectedTransfer.getId())
                .build();
        Transfer actualTransfer = client.sendRequest(gettingTransferRequest);

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
        Transfer expectedTransfer = client.sendRequest(creatingTransferRequest);

        Request<Transfer> request = new Transfer.UpdateRequestBuilder(expectedTransfer.getId())
                .amount(20000)
                .build();
        Transfer actualTransfer = client.sendRequest(request);

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
        Transfer expectedTransfer = client.sendRequest(creatingTransferRequest);

        Request<Transfer> request = new Transfer.DestroyRequestBuilder(expectedTransfer.getId())
                .build();
        Transfer actualTransfer = client.sendRequest(request);

        System.out.println("Destroy transfer: " + actualTransfer.getId());
        assertEquals(expectedTransfer.getId(), actualTransfer.getId());
        assertTrue(actualTransfer.isDeleted());
    }

    @Test
    @Ignore("only hit when test on live")
    public void testLiveGetTransferWithTransactions() throws Exception {
        Client client = getLiveClient();
        String transferId = "trsf_test_5d42nihd8nytnfya02r";

        Request<Transfer> request = new Transfer.GetRequestBuilder(transferId)
                .build();
        Transfer transfer = client.sendRequest(request);

        System.out.println("Transfer retrieved: " + transfer.getId());
        assertTrue(transfer.getTransactions().size() > 0);
    }
}
