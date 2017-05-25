package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Represents Omise Charge object.
 *
 * @see <a href="https://www.omise.co/charges-api">Charges API</a>
 */
public class Charge extends Model {
    private ChargeStatus status;
    private long amount;
    private String currency;
    private String description;
    private Map<String, Object> metadata;
    private boolean capture;
    private boolean authorized;
    private boolean reversed;
    private boolean paid;
    private String transaction;
    @JsonProperty("source_of_fund")
    private SourceOfFunds sourceOfFund;
    private Card card;
    private long refunded;
    private ScopedList<Refund> refunds;
    @JsonProperty("failure_code")
    private String failureCode;
    @JsonProperty("failure_message")
    private String failureMessage;
    private String customer;
    private String ip;
    private Dispute dispute;
    @JsonProperty("return_uri")
    private String returnUri;
    @JsonProperty("authorize_uri")
    private String authorizeUri;
    private OffsiteTypes offsite;

    public ChargeStatus getStatus() {
        return status;
    }

    public void setStatus(ChargeStatus status) {
        this.status = status;
    }

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

    public boolean isCapture() {
        return capture;
    }

    public void setCapture(boolean capture) {
        this.capture = capture;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public boolean isReversed() {
        return reversed;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public SourceOfFunds getSourceOfFund() {
        return sourceOfFund;
    }

    public void setSourceOfFund(SourceOfFunds sourceOfFund) {
        this.sourceOfFund = sourceOfFund;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public long getRefunded() {
        return refunded;
    }

    public void setRefunded(long refunded) {
        this.refunded = refunded;
    }

    public ScopedList<Refund> getRefunds() {
        return refunds;
    }

    public void setRefunds(ScopedList<Refund> refunds) {
        this.refunds = refunds;
    }

    public String getFailureCode() {
        return failureCode;
    }

    public void setFailureCode(String failureCode) {
        this.failureCode = failureCode;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Dispute getDispute() {
        return dispute;
    }

    public void setDispute(Dispute dispute) {
        this.dispute = dispute;
    }

    public String getReturnUri() {
        return returnUri;
    }

    public void setReturnUri(String returnUri) {
        this.returnUri = returnUri;
    }

    public String getAuthorizeUri() {
        return authorizeUri;
    }

    public void setAuthorizeUri(String authorizeUri) {
        this.authorizeUri = authorizeUri;
    }

    public OffsiteTypes getOffsite() {
        return offsite;
    }

    public void setOffsite(OffsiteTypes offsite) {
        this.offsite = offsite;
    }

    public static class Create extends Params {
        @JsonProperty
        private String customer;
        @JsonProperty
        private String card;
        @JsonProperty
        private long amount;
        @JsonProperty
        private String currency;
        @JsonProperty
        private String description;
        @JsonProperty
        private Map<String, Object> metadata;
        @JsonProperty
        private Boolean capture;
        @JsonProperty
        private OffsiteTypes offsite;
        @JsonProperty("return_uri")
        private String returnUri;

        public Create customer(String customer) {
            this.customer = customer;
            return this;
        }

        public Create card(String card) {
            this.card = card;
            return this;
        }

        public Create amount(long amount) {
            this.amount = amount;
            return this;
        }

        public Create currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Create description(String description) {
            this.description = description;
            return this;
        }

        public Create metadata(Map<String, Object> metadata) {
            // TODO: We should probably do an immutable copy here.
            this.metadata = metadata;
            return this;
        }

        public Create metadata(String key, Object value) {
            if (this.metadata == null) {
                this.metadata = Maps.newHashMap();
            }

            this.metadata.put(key, value);
            return this;
        }

        public Create capture(Boolean capture) {
            this.capture = capture;
            return this;
        }

        public Create offsite(OffsiteTypes offsite) {
            this.offsite = offsite;
            return this;
        }

        public Create returnUri(String returnUri) {
            this.returnUri = returnUri;
            return this;
        }
    }

    public static class Update extends Params {
        @JsonProperty
        private String description;
        @JsonProperty
        private Map<String, Object> metadata;

        public Update description(String description) {
            this.description = description;
            return this;
        }

        public Update metadata(Map<String, Object> metadata) {
            // TODO: We should probably do an immutable copy here.
            this.metadata = metadata;
            return this;
        }

        public Update metadata(String key, Object value) {
            if (this.metadata == null) {
                this.metadata = Maps.newHashMap();
            }

            this.metadata.put(key, value);
            return this;
        }
    }
}
