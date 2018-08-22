package co.omise.live;

import co.omise.Client;
import co.omise.models.*;
import co.omise.requests.Request;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

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

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveDeleteRecipient() throws IOException, OmiseException {
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

        Request<Recipient> deleteRequest = new Recipient.DeleteRequestBuilder(createdRecipient.getId())
                .build();

        Recipient deletedRecipient = client.sendRequest(deleteRequest);

        System.out.println("deleted recipient: " + deletedRecipient.getId());

        assertEquals(createdRecipient.getId(), deletedRecipient.getId());
        assertTrue(deletedRecipient.isDeleted());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveGetRecipientList() throws IOException, OmiseException {
        Request<ScopedList<Recipient>> request = new Recipient.ListRequestBuilder()
                .build();

        ScopedList<Recipient> recipients = client.sendRequest(request);

        assertEquals(20, recipients.getLimit());
        assertTrue(recipients.getData().size() > 0);

        Recipient recipient = recipients.getData().get(0);
        assertNotNull(recipient);
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveGetRecipientListWithOptions() throws IOException, OmiseException {
        Request<ScopedList<Recipient>> request = new Recipient.ListRequestBuilder()
                .options(new ScopedList.Options()
                        .limit(3)
                        .order(Ordering.Chronological))
                .build();

        ScopedList<Recipient> recipients = client.sendRequest(request);

        assertEquals(3, recipients.getLimit());
        assertTrue(recipients.getData().size() > 0);
        assertEquals(Ordering.Chronological, recipients.getOrder());

        Recipient recipient = recipients.getData().get(0);
        assertNotNull(recipient);
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveGetListCustomerWithOptions() throws IOException, OmiseException {
        Request<ScopedList<Customer>> request = new Customer.ListRequestBuilder()
                .options(new ScopedList.Options()
                        .limit(3)
                        .order(Ordering.Chronological))
                .build();

        ScopedList<Customer> customers = client.sendRequest(request);

        assertEquals(3, customers.getLimit());
        assertTrue(customers.getData().size() > 0);
        assertEquals(Ordering.Chronological, customers.getOrder());

        Customer customer = customers.getData().get(0);
        assertNotNull(customer);
    }
}