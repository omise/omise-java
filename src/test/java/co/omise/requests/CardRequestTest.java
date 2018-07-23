package co.omise.requests;

import co.omise.models.Card;
import co.omise.models.OmiseException;
import org.junit.Test;

import java.io.IOException;

public class CardRequestTest extends RequestTest {
    private final String CUSTOMER_ID = "cust_test_4yq6txdpfadhbaqnwp3";
    private final String CARD_ID = "card_test_4yq6tuucl9h4erukfl0";

    @Test
    public void testGet() throws IOException, OmiseException {

        Request<Card> request = new Card.GetRequestBuilder(CARD_ID, CUSTOMER_ID).build();
        Card card = getTestRequester().sendRequest(request, Card.class);

        assertRequested("GET", "/customers/" + CUSTOMER_ID + "/cards/" + CARD_ID, 200);

        assertEquals("card", card.getObject());
        assertEquals("card_test_4yq6tuucl9h4erukfl0", card.getId());
        assertEquals("JOHN DOE", card.getName());
        assertEquals("4242", card.getLastDigits());
    }
}