package co.omise.resources;

import co.omise.models.Recipient;
import co.omise.models.RecipientType;
import co.omise.models.ScopedList;
import org.junit.Test;

import java.io.IOException;

public class RecipientResourceTest extends ResourceTest {
    public static final String RECIPIENT_ID = "recp_test_50894vc13y8z4v51iuc";

    @Test
    public void testList() throws IOException {
        ScopedList<Recipient> list = resource().list();
        assertRequested("GET", "/recipients", 200);

        assertEquals(1, list.getTotal());
        assertEquals(20, list.getLimit());

        Recipient recipient = list.getData().get(0);
        assertEquals(RECIPIENT_ID, recipient.getId());
        assertEquals("john.doe@example.com", recipient.getEmail());
    }

    @Test
    public void testGet() throws IOException {
        Recipient recipient = resource().get(RECIPIENT_ID);
        assertRequested("GET", "/recipients/" + RECIPIENT_ID, 200);

        assertEquals(RECIPIENT_ID, recipient.getId());
        assertEquals("john.doe@example.com", recipient.getEmail());
        assertEquals(RecipientType.Individual, recipient.getType());
        assertEquals("6789", recipient.getBankAccount().getLastDigits());
    }

    @Test
    public void testCreate() throws IOException {
        Recipient recipient = resource().create(new Recipient.Create()
                .email("john.doe@example.com")
                .description("Default recipient"));
        assertRequested("POST", "/recipients", 200);

        assertEquals(RECIPIENT_ID, recipient.getId());
        assertEquals("john.doe@example.com", recipient.getEmail());
        assertEquals("6789", recipient.getBankAccount().getLastDigits());
        assertEquals("Default recipient", recipient.getDescription());
    }

    @Test
    public void testUpdate() throws IOException {
        Recipient recipient = resource().update(RECIPIENT_ID, new Recipient.Update()
                .email("john@doe.com")
                .name("john@doe.com"));
        assertRequested("PATCH", "/recipients/" + RECIPIENT_ID, 200);

        assertEquals(RECIPIENT_ID, recipient.getId());
        assertEquals("john@doe.com", recipient.getEmail());
        assertEquals("john@doe.com", recipient.getName());
    }

    @Test
    public void testDestroy() throws IOException {
        Recipient recipient = resource().destroy(RECIPIENT_ID);
        assertRequested("DELETE", "/recipients/" + RECIPIENT_ID, 200);

        assertEquals(RECIPIENT_ID, recipient.getId());
        assertTrue(recipient.isDeleted());
    }

    private RecipientResource resource() {
        return new RecipientResource(testClient());
    }
}