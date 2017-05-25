package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;

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

    public static class Create extends Params {
    }

    public static class Update extends Params {
    }
}

