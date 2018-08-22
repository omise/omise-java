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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveGetRecipient() throws IOException, OmiseException {
        Request<Recipient> createRequest = new Recipient.CreateRequestBuilder()
                .name("John Doe")
                .email("john.doe@example.com")
                .description("Default recipient")
                .type(RecipientType.Individual)
                .bankAccount(new BankAccount.Params()
                        .brand("kbank")
                        .number("1234567890")
                        .name("SOMCHAI PRASERT"))
                .build();

        Recipient createdRecipient = client.sendRequest(createRequest);

        System.out.println("created recipient: " + createdRecipient.getId());

        Request<Recipient> retrieveRequest = new Recipient.GetRequestBuilder(createdRecipient.getId())
                .build();

        Recipient retrievedRecipient = client.sendRequest(retrieveRequest);

        assertEquals(createdRecipient.getId(), retrievedRecipient.getId());
        assertEquals(createdRecipient.getName(), retrievedRecipient.getName());
        assertEquals(createdRecipient.getBankAccount().getBrand(), retrievedRecipient.getBankAccount().getBrand());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveUpdateRecipient() throws IOException, OmiseException {
        Request<Recipient> createRequest = new Recipient.CreateRequestBuilder()
                .name("John Doe")
                .email("john.doe@example.com")
                .description("Default recipient")
                .type(RecipientType.Individual)
                .bankAccount(new BankAccount.Params()
                        .brand("kbank")
                        .number("1234567890")
                        .name("SOMCHAI PRASERT"))
                .build();

        Recipient createdRecipient = client.sendRequest(createRequest);

        System.out.println("created recipient: " + createdRecipient.getId());

        Request<Recipient> updateRequest = new Recipient.UpdateRequestBuilder(createdRecipient.getId())
                .name("John Doe")
                .email("john.doe@example.com")
                .description("Default recipient")
                .type(RecipientType.Individual)
                .bankAccount(new BankAccount.Params()
                        .brand("kbank")
                        .number("1234567890")
                        .name("John Doe"))
                .build();

        Recipient updatedRecipient = client.sendRequest(updateRequest);

        System.out.println("updated recipient: " + updatedRecipient.getId());

        assertEquals(createdRecipient.getId(), updatedRecipient.getId());
        assertNotEquals(createdRecipient.getBankAccount().getName(), updatedRecipient.getBankAccount().getName());
        assertEquals("John Doe", updatedRecipient.getBankAccount().getName());
    }
}
