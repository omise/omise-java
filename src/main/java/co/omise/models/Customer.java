package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents Omise Customer object.
 *
 * @see <a href="https://www.omise.co/customers-api">Charges API</a>
 */
public class Customer extends Model {
    @JsonProperty("default_card")
    private String defaultCard;
    private String email;
    private String description;
    private Map<String, Object> metadata;
    private ScopedList<Card> cards;

    public String getDefaultCard() {
        return defaultCard;
    }

    public void setDefaultCard(String defaultCard) {
        this.defaultCard = defaultCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public ScopedList<Card> getCards() {
        return cards;
    }

    public void setCards(ScopedList<Card> cards) {
        this.cards = cards;
    }

    /**
     * The {@link RequestBuilder} class for creating a Customer.
     */
    public static class CreateRequestBuilder extends RequestBuilder<Customer> {
        @JsonProperty
        private String email;
        @JsonProperty
        private String description;
        @JsonProperty
        private Map<String, Object> metadata;
        @JsonProperty
        private String card;

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "customers");
        }

        @Override
        protected RequestBody payload() throws IOException {
            return serialize();
        }

        @Override
        protected ResponseType<Customer> type() {
            return new ResponseType<>(Customer.class);
        }

        public CreateRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CreateRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CreateRequestBuilder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
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

        public CreateRequestBuilder card(String card) {
            this.card = card;
            return this;
        }
    }

    /**
     * The {@link RequestBuilder} class for retrieving a particular Customer.
     */
    public static class GetRequestBuilder extends RequestBuilder<Customer> {
        private String customerId;

        public GetRequestBuilder(String customerId) {
            this.customerId = customerId;
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

    /**
     * The {@link RequestBuilder} class for updating a particular Customer.
     */
    public static class UpdateRequestBuilder extends RequestBuilder<Customer> {
        private String customerId;

        @JsonProperty
        private String email;
        @JsonProperty
        private String description;
        @JsonProperty
        private Map<String, Object> metadata;
        @JsonProperty
        private String card;

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
        protected RequestBody payload() throws IOException {
            return serialize();
        }

        @Override
        protected ResponseType<Customer> type() {
            return new ResponseType<>(Customer.class);
        }


        public UpdateRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UpdateRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public UpdateRequestBuilder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
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

        public UpdateRequestBuilder card(String card) {
            this.card = card;
            return this;
        }
    }

    public static class DeleteRequestBuilder extends RequestBuilder<Customer> {
        private String customerId;

        public DeleteRequestBuilder(String customerId) {
            this.customerId = customerId;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "customers", customerId);
        }

        @Override
        protected ResponseType<Customer> type() {
            return new ResponseType<>(Customer.class);
        }

        @Override
        protected String method() {
            return DELETE;
        }
    }
}