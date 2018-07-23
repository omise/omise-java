package co.omise.live;

import co.omise.Client;
import co.omise.models.Card;
import co.omise.models.OmiseException;
import co.omise.requests.Request;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class LiveCardRequestTest extends BaseLiveTest {
    private Client client;
    private final String CUSTOMER_ID = "[YOUR_CUSTOMER_ID]";
    private final String CARD_ID = "[YOUR_CARD_ID]";

    @Before
    public void setup() throws Exception {
        client = getLiveClient();
    }

    @Test
    @Ignore("only hit when test on live.")
    public void getCard_success() throws Exception {
        Request<Card> request = new Card.GetRequestBuilder(
                CARD_ID, CUSTOMER_ID).build();
        Card card = client.sendRequest(request, Card.class);

        System.out.println("Card retrieved: " + card.getId());

        assertNotNull(card);
        assertEquals(CARD_ID, card.getId());
        assertEquals("4242", card.getLastDigits());
    }

    @Test
    @Ignore("only hit when test on live.")
    public void updateCard_success() throws IOException, OmiseException {
        Request<Card> request = new Card.UpdateRequestBuilder(
                CARD_ID, CUSTOMER_ID)
                .name("JOHN W. DOE")
                .expirationYear(2022)
                .expirationMonth(11)
                .build();

        Card card = client.sendRequest(request, Card.class);

        System.out.println("Card update: " + card + " to name: " + card.getName());

        assertNotNull(card);
        assertEquals(CARD_ID, card.getId());
        assertEquals("JOHN W. DOE", card.getName());
        assertEquals(2022, card.getExpirationYear());
        assertEquals(11, card.getExpirationMonth());
    }

}
