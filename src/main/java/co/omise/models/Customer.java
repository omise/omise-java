package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import okhttp3.FormBody;
import okhttp3.RequestBody;

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
        private String email;
        private String description;
        private String card;

        public CustomerParams email(String email) {
            this.email = email;
            return this;
        }

        public CustomerParams description(String description) {
            this.description = description;
            return this;
        }

        public CustomerParams card(String card) {
            this.card = card;
            return this;
        }

        @Override
        public ImmutableMap<String, String> query() {
            return null;
        }

        @Override
        public RequestBody body() {
            return new FormBody.Builder()
                    .add("email", email)
                    .add("description", description)
                    .add("card", card)
                    .build();
        }
    }
}

