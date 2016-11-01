package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents Omise Link object.
 *
 * @see <a href="https://www.omise.co/links-api">Link API</a>
 */
public class Link extends Model {
    private long amount;
    private String currency;
    private boolean used;
    private boolean multiple;
    private String title;
    private String description;
    private ScopedList<Charge> charges;
    @JsonProperty("payment_uri")
    private String paymentUri;

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

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ScopedList<Charge> getCharges() {
        return charges;
    }

    public void setCharges(ScopedList<Charge> charges) {
        this.charges = charges;
    }

    public String getPaymentUri() {
        return paymentUri;
    }

    public void setPaymentUri(String paymentUri) {
        this.paymentUri = paymentUri;
    }

    public static class Create extends Params {
        public Create amount(long amount) {
            add("amount", Long.toString(amount));
            return this;
        }

        public Create currency(String currency) {
            add("currency", currency);
            return this;
        }

        public Create title(String title) {
            add("title", title);
            return this;
        }

        public Create description(String description) {
            add("description", description);
            return this;
        }

        public Create multiple(boolean multiple) {
            add("multiple", Boolean.toString(multiple));
            return this;
        }
    }
}
