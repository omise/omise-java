package co.omise.requests;

import co.omise.models.OmiseException;
import co.omise.models.Recipient;
import co.omise.models.RecipientType;
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
}