package co.omise.resources;

import co.omise.models.Card;
import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import org.junit.Test;

import java.io.IOException;

public class CardResourceTest extends ResourceTest {
    private final String CUSTOMER_ID = "cust_test_4yq6txdpfadhbaqnwp3";
    private final String CARD_ID = "card_test_4yq6tuucl9h4erukfl0";

    @Test
    public void testList() throws IOException, OmiseException {
        ScopedList<Card> list = resource().list();
        assertEquals(1, list.getTotal());
        assertEquals(20, list.getLimit());

        Card card = list.getData().get(0);
        assertRequested("GET", "/customers/" + CUSTOMER_ID + "/cards", 200);

        assertEquals("card", card.getObject());
        assertEquals("card_test_4yq6tuucl9h4erukfl0", card.getId());
        assertEquals("4242", card.getLastDigits());
        assertEquals("Visa", card.getBrand());
    }

    @Test
    public void testDestroy() throws IOException, OmiseException {
        Card card = resource().destroy(CARD_ID);
        assertRequested("DELETE", "/customers/" + CUSTOMER_ID + "/cards/" + CARD_ID, 200);

        assertTrue(card.isDeleted());
        assertEquals("card_test_4yq6tuucl9h4erukfl0", card.getId());
    }

    private CardResource resource() {
        return new CardResource(testClient(), CUSTOMER_ID);
    }
}