package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class Charge extends Model {
    private ChargeStatus status;
    private long amount;
    private String currency;
    private String description;
    private boolean capture;
    private boolean authorized;
    private boolean paid;
    private String transaction;
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
    private String returnUri;
    private String authorizeUri;

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

    public static class Create extends Params {
        private String customer;
        private String card;
        private long amount;
        private String currency;
        private String description;
        private boolean capture = true;
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

        public Create capture(boolean capture) {
            this.capture = capture;
            return this;
        }

        public Create returnUri(String returnUri) {
            this.returnUri = returnUri;
            return this;
        }

        @Override
        public ImmutableMap<String, String> query() {
            return null;
        }

        @Override
        public RequestBody body() {
            return new FormBody.Builder()
                    .add("customer", customer)
                    .add("card", card)
                    .add("amount", Long.toString(amount))
                    .add("currency", currency)
                    .add("description", description)
                    .add("capture", Boolean.toString(capture))
                    .add("return_uri", returnUri)
                    .build();
        }
    }

    public static class Update extends Params {
        private String description;

        public Update description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public ImmutableMap<String, String> query() {
            return null;
        }

        @Override
        public RequestBody body() {
            return new FormBody.Builder()
                    .add("description", description)
                    .build();
        }
    }
}
