package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.Forex;
import co.omise.models.OmiseException;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class ForexResource extends Resource {
    public ForexResource(OkHttpClient httpClient) {
        super(httpClient);
    }

    public Forex get(String currency) throws IOException, OmiseException {
        return httpGet(urlFor(currency)).returns(Forex.class);
    }

    private HttpUrl urlFor(String currency) {
        return buildUrl(Endpoint.API, "forex", currency);
    }
}
