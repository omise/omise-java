package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.OmiseException;
import co.omise.models.Token;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class TokenResource extends Resource {
    public TokenResource(OkHttpClient httpClient) {
        super(httpClient);
    }

}
