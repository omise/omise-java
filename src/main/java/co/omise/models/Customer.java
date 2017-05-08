package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

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
        private String card;

        public Params email(String email) {
            this.email = email;
            return this;
        }

        public Params description(String description) {
            this.description = description;
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

