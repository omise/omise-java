package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.Link;
import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class LinkResource extends Resource {
    public LinkResource(OkHttpClient httpClient) {
        super(httpClient);
    }

    public ScopedList<Link> list() throws IOException, OmiseException {
        return list(new ScopedList.Options());
    }

    public ScopedList<Link> list(ScopedList.Options options) throws IOException, OmiseException {
        return httpGet(urlFor("")).params(options).returns(new TypeReference<ScopedList<Link>>() {
        });
    }

    private HttpUrl urlFor(String linkId) {
        return buildUrl(Endpoint.API, "links", linkId);
    }
}