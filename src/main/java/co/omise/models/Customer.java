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

    public static class Create extends CustomerParams {
    }

    public static class Update extends CustomerParams {
    }

    public static abstract class CustomerParams extends Params {
        public CustomerParams email(String email) {
            add("email", email);
            return this;
        }

        public CustomerParams description(String description) {
            add("description", description);
            return this;
        }

        public CustomerParams card(String card) {
            add("card", card);
            return this;
        }
    }
}

