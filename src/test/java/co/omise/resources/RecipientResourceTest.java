package co.omise.resources;

import co.omise.models.OmiseException;
import co.omise.models.Recipient;
import co.omise.models.RecipientType;
import co.omise.models.ScopedList;
import org.junit.Test;

import java.io.IOException;

public class RecipientResourceTest extends ResourceTest {
    public static final String RECIPIENT_ID = "recp_test_50894vc13y8z4v51iuc";

    @Test
    public void testList() throws IOException, OmiseException {
        ScopedList<Recipient> list = resource().list();
        assertRequested("GET", "/recipients", 200);

        assertEquals(1, list.getTotal());
        assertEquals(20, list.getLimit());

        Recipient recipient = list.getData().get(0);
        assertEquals(RECIPIENT_ID, recipient.getId());
        assertEquals("john.doe@example.com", recipient.getEmail());
    }

    @Test
    public void testDestroy() throws IOException, OmiseException {
        Recipient recipient = resource().destroy(RECIPIENT_ID);
        assertRequested("DELETE", "/recipients/" + RECIPIENT_ID, 200);

        assertEquals(RECIPIENT_ID, recipient.getId());
        assertTrue(recipient.isDeleted());
    }

    private RecipientResource resource() {
        return new RecipientResource(testClient());
    }
}