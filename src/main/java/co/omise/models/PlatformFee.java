package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlatformFee {
    private long amount;
    private long fixed;
    private float percentage;

    public long getAmount() {
        return this.amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getFixed() {
        return this.fixed;
    }

    public void setFixed(long fixed) {
        this.fixed = fixed;
    }

    public float getPercentage() {
        return this.percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public static class Params extends co.omise.models.Params {
        @JsonProperty
        private long amount;

        @JsonProperty
        private long fixed;

        @JsonProperty
        private float percentage;

        public Params amount(long amount) {
            this.amount = amount;
            return this;
        }

        public Params fixed(long fixed) {
            this.fixed = fixed;
            return this;
        }

        public Params percentage(float percentage) {
            this.percentage = percentage;
            return this;
        }
    }
}