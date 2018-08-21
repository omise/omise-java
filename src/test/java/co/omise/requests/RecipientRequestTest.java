package co.omise.requests;

import co.omise.models.OmiseException;
import co.omise.models.Recipient;
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
}