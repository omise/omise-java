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

    public Card destroy(String cardId) throws IOException, OmiseException {
        return httpDelete(urlFor(cardId)).returns(Card.class);
    }

    private HttpUrl urlFor(String cardId) {
        return buildUrl(Endpoint.API, "customers", customerId, "cards", cardId);
    }
}
