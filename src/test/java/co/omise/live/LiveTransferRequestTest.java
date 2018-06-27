package co.omise.live;

import co.omise.Client;
import co.omise.models.BankAccount;
import co.omise.models.Recipient;
import co.omise.models.RecipientType;
import co.omise.models.Transfer;
import co.omise.requests.Request;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class LiveTransferRequestTest extends BaseLiveTest {
    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveTransfer() throws Exception {
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
    @Ignore("only hit when test on live.")
    public void testGetLiveTransfer() throws Exception {
        Request<Transfer> request = new Transfer.GetRequestBuilder()
                .id(getTransferId())
                .build();
        Transfer transfer = getLiveClient().sendRequest(request, Transfer.class);

        System.out.println("Transfer retrieved: " + transfer);

        assertEquals(getTransferId(), transfer.getId());
    }
}
