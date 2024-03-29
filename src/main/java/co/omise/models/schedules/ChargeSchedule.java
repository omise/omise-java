package co.omise.models.schedules;

import co.omise.models.Model;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Charge Schedule object
 *
 * @see <a href="https://www.omise.co/charge-schedules-api">Charge Schedule API</a>
 */
public class ChargeSchedule extends Model {
    private long amount;
    private String card;
    private String currency;
    private String customer;
    @JsonProperty("default_card")
    private boolean defaultCard;
    private String description;
    private Map<String, Object> metadata;

    public long getAmount() {
        return this.amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCard() {
        return this.card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public boolean isDefaultCard() {
        return this.defaultCard;
    }

    public void setDefaultCard(boolean defaultCard) {
        this.defaultCard = defaultCard;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getMetadata() {
        return this.metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public static class Params extends co.omise.models.Params {
        @JsonProperty
        private long amount;

        @JsonProperty
        private String card;

        @JsonProperty
        private String currency;

        @JsonProperty
        private String customer;

        @JsonProperty
        private String description;

        @JsonProperty
        private Map<String, Object> metadata;

        public Params amount(long amount) {
            this.amount = amount;
            return this;
        }

        public Params card(String card) {
            this.card = card;
            return this;
        }

        public Params currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Params customer(String customer) {
            this.customer = customer;
            return this;
        }

        public Params description(String description) {
            this.description = description;
            return this;
        }
        public Params metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }
    }
}