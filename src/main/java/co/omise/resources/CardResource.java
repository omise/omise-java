package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.Card;
import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class CardResource extends Resource {
    private final String customerId;

    public CardResource(OkHttpClient httpClient, String customerId) {
        super(httpClient);
        this.customerId = customerId;
    }

    public String customerId() {
        return customerId;
    }

    public ScopedList<Card> list() throws IOException, OmiseException {
        return list(new ScopedList.Options());
    }

    public ScopedList<Card> list(ScopedList.Options options) throws IOException, OmiseException {
        return httpGet(urlFor("")).params(options).returns(new TypeReference<ScopedList<Card>>() {
        });
    }

    public Card get(String cardId) throws IOException, OmiseException {
        return httpGet(urlFor(cardId)).returns(Card.class);
    }

    public Card update(String cardId, Card.Update update) throws IOException, OmiseException {
        return httpPatch(urlFor(cardId)).params(update).returns(Card.class);
    }

    public Card destroy(String cardId) throws IOException, OmiseException {
        return httpDelete(urlFor(cardId)).returns(Card.class);
    }

    private HttpUrl urlFor(String cardId) {
        return buildUrl(Endpoint.API, "customers", customerId, "cards", cardId);
    }
}
