package co.omise.resources;

import okhttp3.OkHttpClient;

public class CustomerSpecificResource extends Resource {
    private final String customerId;

    private final CardResource cards;

    public CustomerSpecificResource(OkHttpClient httpClient, String customerId) {
        super(httpClient);
        this.customerId = customerId;
        cards = new CardResource(httpClient, customerId);
    }

    public String customerId() {
        return customerId;
    }

    public CardResource cards() {
        return cards;
    }
}
