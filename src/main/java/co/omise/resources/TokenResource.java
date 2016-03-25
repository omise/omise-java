package co.omise.resources;

import co.omise.models.Token;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class TokenResource extends Resource {
    public TokenResource(OkHttpClient httpClient) {
        super(httpClient);
    }

    public Token get(String tokenId) throws IOException {
        return httpGet(urlFor(tokenId)).returns(Token.class);
    }

    public Token create(Token.Create params) throws IOException {
        return httpPost(urlFor("")).params(params).returns(Token.class);
    }

    private HttpUrl urlFor(String tokenId) {
        return vaultUrl("/tokens").addPathSegment(tokenId).build();
    }
}
