package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.OmiseException;
import co.omise.models.Source;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class SourceResource extends Resource {

    public SourceResource(OkHttpClient httpClient) {
        super(httpClient);
    }

    public Source create(Source.Create params) throws IOException, OmiseException {
        return httpPost(url()).params(params).returns(Source.class);
    }

    private HttpUrl url() {
        return buildUrl(Endpoint.API, "sources");
    }

}
