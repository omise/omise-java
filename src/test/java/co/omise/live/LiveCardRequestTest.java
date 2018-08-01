package co.omise.live;

import co.omise.Client;
import co.omise.models.Card;
import co.omise.models.OmiseException;
import co.omise.models.Ordering;
import co.omise.models.ScopedList;
import co.omise.requests.Request;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

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

    @Test
    @Ignore("only hit the network when we need to.")
    public void getCardList_success() throws IOException, OmiseException {
        Request<ScopedList<Card>> request =
                new Card.ListRequestBuilder(CUSTOMER_ID)
                        .options(new ScopedList.Options()
                                .limit(10)
                                .order(Ordering.Chronological))
                        .build();
        ScopedList<Card> cards = client.sendRequest(request, new TypeReference<ScopedList<Card>>() {
        });
        for (Card card : cards.getData()) {
            System.out.println(card.getId() + " : " + card.getLastDigits());
        }

        // These can change (card specific details, like size or the details of the first card)
        assertNotNull(cards);
        assertEquals(3, cards.getData().size());
        assertEquals(10, cards.getLimit());
        assertEquals(Ordering.Chronological, cards.getOrder());
        assertNotNull(cards.getData().get(0));
        assertEquals("4242", cards.getData().get(0).getLastDigits());
    }

    @Test
    @Ignore("only hit the network when need to.")
    public void deleteCard_success() throws IOException, OmiseException {
        Request<Card> request =
                new Card.DeleteRequestBuilder(CARD_ID, CUSTOMER_ID)
                        .build();
        Card card = client.sendRequest(request, Card.class);

        assertNotNull(card);
        assertEquals(CARD_ID, card.getId());
        assertTrue(card.isDeleted());
    }
}