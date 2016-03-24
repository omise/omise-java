package co.omise.resources;

import co.omise.models.Card;
import co.omise.models.ScopedList;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class CardResource extends Resource {
    private final String customerId;

    public CardResource(OkHttpClient httpClient, String customerId) {
        super(httpClient);
        this.customerId = customerId;
    }

    public ScopedList<Card> list() {
        // TODO: return request("GET", "/customers/" + customerId + "/cards", null, ScopedList.class);
        return null;
    }

    public Card get(String cardId) throws IOException {
        return request("GET", urlFor(cardId), null, Card.class);
    }

    public Card update(String cardId, Card.Update update) throws IOException {
        return request("POST", urlFor(cardId), update, Card.class);
    }

    public Card destroy(String cardId) throws IOException {
        return request("DELETE", urlFor(cardId), null, Card.class);
    }

    private String urlFor(String cardId) {
        String url = "/customers/" + customerId + "/cards";
        if (cardId != null) {
            url += "/" + cardId;
        }

        return url;
    }
}
