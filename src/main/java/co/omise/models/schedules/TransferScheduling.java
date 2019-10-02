package co.omise.models.schedules;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class TransferScheduling implements Serializable {
    private long amount;
    private String currency;
    @JsonProperty("percentage_of_balance")
    private float percentageOfBalance;
    private String recipient;

    public long getAmount() {
        return this.amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getPercentageOfBalance() {
        return this.percentageOfBalance;
    }

    public void setPercentageOfBalance(float percentageOfBalance) {
        this.percentageOfBalance = percentageOfBalance;
    }

    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public static class Params extends co.omise.models.Params {
        @JsonProperty
        private long amount;

        @JsonProperty
        private String currency;

        @JsonProperty("percentage_of_balance")
        private float percentageOfBalance;

        @JsonProperty
        private String recipient;

        public Params amount(long amount) {
            this.amount = amount;
            return this;
        }

        public Params currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Params percentageOfBalance(float percentageOfBalance) {
            this.percentageOfBalance = percentageOfBalance;
            return this;
        }

        public Params recipient(String recipient) {
            this.recipient = recipient;
            return this;
        }
    }
}