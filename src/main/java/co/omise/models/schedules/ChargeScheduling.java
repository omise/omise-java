package co.omise.models.schedules;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChargeScheduling {
    private long amount;
    private String currency;
    private String customer;
    private String card;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public static class Params extends co.omise.models.Params {
        @JsonProperty
        private long amount;
        @JsonProperty
        private String currency;
        @JsonProperty
        private String description;
        @JsonProperty
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String customer;
        @JsonProperty
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String card;

        public Params amount(long amount) {
            this.amount = amount;
            return this;
        }

        public Params currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Params description(String description) {
            this.description = description;
            return this;
        }

        public Params customer(String customer) {
            this.customer = customer;
            return this;
        }

        public Params card(String card) {
            this.card = card;
            return this;
        }
    }
}
