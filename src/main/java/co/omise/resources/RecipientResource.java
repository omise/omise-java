package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.OmiseException;
import co.omise.models.Recipient;
import co.omise.models.ScopedList;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class RecipientResource extends Resource {
    public RecipientResource(OkHttpClient httpClient) {
        super(httpClient);
    }

    public ScopedList<Recipient> list() throws IOException, OmiseException {
        return list(new ScopedList.Options());
    }

    public ScopedList<Recipient> list(ScopedList.Options options) throws IOException, OmiseException {
        return httpGet(urlFor("")).params(options).returns(new TypeReference<ScopedList<Recipient>>() {
        });
    }

    public Recipient get(String recipientId) throws IOException, OmiseException {
        return httpGet(urlFor(recipientId)).returns(Recipient.class);
    }

    public Recipient update(String recipientId, Recipient.Params params) throws IOException, OmiseException {
        return httpPatch(urlFor(recipientId)).params(params).returns(Recipient.class);
    }

    public Recipient destroy(String recipientId) throws IOException, OmiseException {
        return httpDelete(urlFor(recipientId)).returns(Recipient.class);
    }

    private HttpUrl urlFor(String recipientId) {
        return buildUrl(Endpoint.API, "recipients", recipientId);
    }
}
