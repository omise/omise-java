package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import okhttp3.HttpUrl;

/**
 * Represents Omise Event object.
 *
 * @see <a href="https://www.omise.co/events-api">Events API</a>
 */
public class Event<T extends Model> extends Model {
    private String key;

    private T data;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * The {@link RequestBuilder} class for retrieving a particular Event.
     */
    public static class GetRequestBuilder extends RequestBuilder<Event> {
        private String eventId;

        public GetRequestBuilder(String eventId) {
            this.eventId = eventId;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "events", eventId);
        }
    }
}