package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.OmiseException;
import co.omise.models.schedules.Occurrence;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class OccurrenceResource extends Resource {
    public OccurrenceResource(OkHttpClient httpClient) {
        super(httpClient);
    }

    public Occurrence get(String occurrenceId) throws IOException, OmiseException {
        return httpGet(urlFor(occurrenceId)).returns(Occurrence.class);
    }

    private HttpUrl urlFor(String occurrenceId) {
        return buildUrl(Endpoint.API, "occurrences", occurrenceId);
    }
}
