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

    public Token get(String tokenId) throws IOException, OmiseException {
        return httpGet(urlFor(tokenId)).returns(Token.class);
    }

    public Token create(Token.Create params) throws IOException, OmiseException {
        return httpPost(urlFor("")).params(params).returns(Token.class);
    }

    private HttpUrl urlFor(String tokenId) {
        return buildUrl(Endpoint.VAULT, "tokens", tokenId);
    }
}
