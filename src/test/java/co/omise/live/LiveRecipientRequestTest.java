package co.omise.live;

import co.omise.Client;
import co.omise.models.BankAccount;
import co.omise.models.OmiseException;
import co.omise.models.Recipient;
import co.omise.models.RecipientType;
import co.omise.requests.Request;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class LiveRecipientRequestTest extends BaseLiveTest {
    private Client client;

    @Before
    public void setup() throws Exception {
        client = getLiveClient();
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveCreateRecipient() throws IOException, OmiseException {
        Request<Recipient> request = new Recipient.CreateRequestBuilder()
                .name("John Doe")
                .email("john.doe@example.com")
                .description("Default recipient")
                .type(RecipientType.Individual)
                .bankAccount(new BankAccount.Params()
                        .brand("kbank")
                        .number("1234567890")
                        .name("SOMCHAI PRASERT"))
                .build();

        Recipient recipient = client.sendRequest(request);

        System.out.println("created recipient: " + recipient.getId());

        assertEquals("John Doe", recipient.getName());
        assertEquals("john.doe@example.com", recipient.getEmail());
        assertEquals("Default recipient", recipient.getDescription());
    }
}
