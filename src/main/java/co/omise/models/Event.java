package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;

import java.util.List;

/**
 * Event object
 *
 * @see <a href="https://www.omise.co/events-api">Event API</a>
 */
public class Event<T extends Model> extends Model {
    private T data;
    private String key;
    private String location;
    @JsonProperty("webhook_deliveries")
    private List<WebhookDelivery> webhookDeliveries;

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<WebhookDelivery> getWebhookDeliveries() {
        return this.webhookDeliveries;
    }

    public void setWebhookDeliveries(List<WebhookDelivery> webhookDeliveries) {
        this.webhookDeliveries = webhookDeliveries;
    }

    public static class GetRequestBuilder extends RequestBuilder<Event> {
        private final String eventId;
        public GetRequestBuilder(String eventId) {
            this.eventId = eventId;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "events", eventId);
        }

        @Override
        protected ResponseType<Event> type() {
            return new ResponseType<>(Event.class);
        }
    }

    public static class ListRequestBuilder extends RequestBuilder<ScopedList<Event>> {
        private ScopedList.Options options;

        @Override
        protected HttpUrl path() {
            if (options == null) {
                options = new ScopedList.Options();
            }
            return new HttpUrlBuilder(Endpoint.API, "events", serializer())
                  .segments()
                  .params(options)
                  .build();
        }

        @Override
        protected ResponseType<ScopedList<Event>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Event>>() {});
        }

        public ListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }
}