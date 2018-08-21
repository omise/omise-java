package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
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

    public static abstract class Params extends co.omise.models.Params {
        @JsonProperty
        private String email;
        @JsonProperty
        private String description;
        @JsonProperty
        private Map<String, Object> metadata;
        @JsonProperty
        private String card;

        public Params email(String email) {
            this.email = email;
            return this;
        }

        public Params description(String description) {
            this.description = description;
            return this;
        }

        public Params metadata(Map<String, Object> metadata) {
            // TODO: We should probably do an immutable copy here.
            this.metadata = metadata;
            return this;
        }

        public Params metadata(String key, Object value) {
            if (this.metadata == null) {
                this.metadata = Maps.newHashMap();
            }

            this.metadata.put(key, value);
            return this;
        }

        public Params card(String card) {
            this.card = card;
            return this;
        }
    }

    public static class Update extends Params {
    }

    //NEW

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
}