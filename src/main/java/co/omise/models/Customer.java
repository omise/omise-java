package co.omise.models;

import co.omise.Endpoint;
import co.omise.models.schedules.Schedule;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Customer object
 *
 * @see <a href="https://www.omise.co/customers-api">Customer API</a>
 */
public class Customer extends Model {
    private ScopedList<Card> cards;
    @JsonProperty("default_card")
    private String defaultCard;
    private String description;
    private String email;
    private String location;
    private Map<String, Object> metadata;

    public ScopedList<Card> getCards() {
        return this.cards;
    }

    public void setCards(ScopedList<Card> cards) {
        this.cards = cards;
    }

    public String getDefaultCard() {
        return this.defaultCard;
    }

    public void setDefaultCard(String defaultCard) {
        this.defaultCard = defaultCard;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, Object> getMetadata() {
        return this.metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public static class DeleteRequestBuilder extends RequestBuilder<Customer> {
        private final String customerId;
        public DeleteRequestBuilder(String customerId) {
            this.customerId = customerId;
        }

        @Override
        protected String method() {
            return DELETE;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "customers", customerId);
        }

        @Override
        protected ResponseType<Customer> type() {
            return new ResponseType<>(Customer.class);
        }
    }

    public static class GetRequestBuilder extends RequestBuilder<Customer> {
        private final String customerId;
        public GetRequestBuilder(String customerId) {
            this.customerId = customerId;
        }

        @Override
        protected String method() {
            return GET;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "customers", customerId);
        }

        @Override
        protected ResponseType<Customer> type() {
            return new ResponseType<>(Customer.class);
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class UpdateRequestBuilder extends RequestBuilder<Customer> {
        private final String customerId;

        @JsonProperty
        private String card;
        @JsonProperty("default_card")
        private String defaultCard;
        @JsonProperty
        private String description;
        @JsonProperty
        private String email;
        @JsonProperty
        private Map<String, Object> metadata;
        public UpdateRequestBuilder(String customerId) {
            this.customerId = customerId;
        }

        @Override
        protected String method() {
            return PATCH;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "customers", customerId);
        }

        @Override
        protected ResponseType<Customer> type() {
            return new ResponseType<>(Customer.class);
        }

        public UpdateRequestBuilder card(String card) {
            this.card = card;
            return this;
        }

        public UpdateRequestBuilder defaultCard(String defaultCard) {
            this.defaultCard = defaultCard;
            return this;
        }

        public UpdateRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public UpdateRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UpdateRequestBuilder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }

        @Override
        protected RequestBody payload() throws IOException {
            return serialize();
        }

        public UpdateRequestBuilder metadata(String key, Object value) {
            HashMap<String, Object> tempMap = new HashMap<>();
            if (metadata != null) {
                tempMap.putAll(metadata);
            }
            tempMap.put(key, value);

            this.metadata = new HashMap<>(tempMap);
            return this;
        }
    }

    public static class ListRequestBuilder extends RequestBuilder<ScopedList<Customer>> {
        private ScopedList.Options options;

        @Override
        protected String method() {
            return GET;
        }

        @Override
        protected HttpUrl path() {
            if (options == null) {
                options = new ScopedList.Options();
            }
            return new HttpUrlBuilder(Endpoint.API, "customers", serializer())
                  .segments()
                  .params(options)
                  .build();
        }

        @Override
        protected ResponseType<ScopedList<Customer>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Customer>>() {});
        }

        public ListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }

    public static class CreateRequestBuilder extends RequestBuilder<Customer> {

        @JsonProperty
        private String card;
        @JsonProperty
        private String description;
        @JsonProperty
        private String email;
        @JsonProperty
        private Map<String, Object> metadata;

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "customers");
        }

        @Override
        protected ResponseType<Customer> type() {
            return new ResponseType<>(Customer.class);
        }

        public CreateRequestBuilder card(String card) {
            this.card = card;
            return this;
        }

        public CreateRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CreateRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CreateRequestBuilder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }

        @Override
        protected RequestBody payload() throws IOException {
            return serialize();
        }

        public CreateRequestBuilder metadata(String key, Object value) {
            HashMap<String, Object> tempMap = new HashMap<>();
            if (metadata != null) {
                tempMap.putAll(metadata);
            }
            tempMap.put(key, value);

            this.metadata = new HashMap<>(tempMap);
            return this;
        }
    }

    public static class ListSchedulesRequestBuilder extends RequestBuilder<ScopedList<Schedule>> {
        private final String customerId;
        private ScopedList.Options options;
        public ListSchedulesRequestBuilder(String customerId) {
            this.customerId = customerId;
        }

        @Override
        protected String method() {
            return GET;
        }

        @Override
        protected HttpUrl path() {
            if (options == null) {
                options = new ScopedList.Options();
            }
            return new HttpUrlBuilder(Endpoint.API, "customers", serializer())
                  .segments(customerId, "schedules")
                  .params(options)
                  .build();
        }

        @Override
        protected ResponseType<ScopedList<Schedule>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Schedule>>() {});
        }

        public ListSchedulesRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }
}