package co.omise.requests;

import co.omise.models.OmiseException;
import co.omise.models.Recipient;
import co.omise.models.RecipientType;
import co.omise.models.ScopedList;
import org.junit.Test;

import java.io.IOException;

public class RecipientRequestTest extends RequestTest {
    private static final String RECIPIENT_ID = "recp_test_50894vc13y8z4v51iuc";

    @Test
    public void testCreate() throws IOException, OmiseException {
        Request<Recipient> request = new Recipient.CreateRequestBuilder()
                .email("john.doe@example.com")
                .description("Default recipient")
                .build();
        Recipient recipient = getTestRequester().sendRequest(request);

        assertRequested("POST", "/recipients", 200);

        assertEquals(RECIPIENT_ID, recipient.getId());
        assertEquals("john.doe@example.com", recipient.getEmail());
        assertEquals("6789", recipient.getBankAccount().getLastDigits());
        assertEquals("Default recipient", recipient.getDescription());
    }

    @Test
    public void testGet() throws IOException, OmiseException {
        Request<Recipient> request = new Recipient.GetRequestBuilder(RECIPIENT_ID)
                .build();
        Recipient recipient = getTestRequester().sendRequest(request);

        assertRequested("GET", "/recipients/" + RECIPIENT_ID, 200);

        assertEquals(RECIPIENT_ID, recipient.getId());
        assertEquals("john.doe@example.com", recipient.getEmail());
        assertEquals(RecipientType.Individual, recipient.getType());
        assertEquals("6789", recipient.getBankAccount().getLastDigits());
    }

    @Test
    public void testUpdate() throws IOException, OmiseException {
        Request<Recipient> request = new Recipient.UpdateRequestBuilder(RECIPIENT_ID)
                .email("john@doe.com")
                .name("john@doe.com")
                .build();
        Recipient recipient = getTestRequester().sendRequest(request);

        assertRequested("PATCH", "/recipients/" + RECIPIENT_ID, 200);

        assertEquals(RECIPIENT_ID, recipient.getId());
        assertEquals("john@doe.com", recipient.getEmail());
        assertEquals("john@doe.com", recipient.getName());
    }

    @Test
    public void testDestroy() throws IOException, OmiseException {
        Request<Recipient> request = new Recipient.DeleteRequestBuilder(RECIPIENT_ID).build();
        Recipient recipient = getTestRequester().sendRequest(request);

        assertRequested("DELETE", "/recipients/" + RECIPIENT_ID, 200);

        assertEquals(RECIPIENT_ID, recipient.getId());
        assertTrue(recipient.isDeleted());
    }

    @Test
    public void testList() throws IOException, OmiseException {
        Request<ScopedList<Recipient>> request = new Recipient.ListRequestBuilder()
                .build();
        ScopedList<Recipient> recipients = getTestRequester().sendRequest(request);

        assertRequested("GET", "/recipients", 200);

        assertEquals(1, recipients.getTotal());
        assertEquals(20, recipients.getLimit());

        Recipient recipient = recipients.getData().get(0);
        assertEquals(RECIPIENT_ID, recipient.getId());
        assertEquals("john.doe@example.com", recipient.getEmail());
    }
}