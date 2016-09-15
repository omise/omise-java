package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.Event;
import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class EventResource extends Resource {
    public EventResource(OkHttpClient httpClient) {
        super(httpClient);
    }

    public ScopedList<Event> list() throws IOException, OmiseException {
        return list(new ScopedList.Options());
    }

    public ScopedList<Event> list(ScopedList.Options options) throws IOException, OmiseException {
        return httpGet(urlFor("")).params(options).returns(new TypeReference<ScopedList<Event>>() {
        });
    }

    public Event get(String eventId) throws IOException, OmiseException {
        return httpGet(urlFor(eventId)).returns(new TypeReference<Event>() {
        });
    }

    private HttpUrl urlFor(String eventId) {
        return buildUrl(Endpoint.API, "events", eventId);
    }
}
