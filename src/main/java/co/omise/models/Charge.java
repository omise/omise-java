package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

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
        public Create customer(String customer) {
            add("customer", customer);
            return this;
        }

        public Create card(String card) {
            add("card", card);
            return this;
        }

        public Create amount(long amount) {
            add("amount", Long.toString(amount));
            return this;
        }

        public Create currency(String currency) {
            add("currency", currency);
            return this;
        }

        public Create description(String description) {
            add("description", description);
            return this;
        }

        public Create capture(boolean capture) {
            add("capture", Boolean.toString(capture));
            return this;
        }

        public Create returnUri(String returnUri) {
            add("return_uri", returnUri);
            return this;
        }
    }

    public static class Update extends Params {
        public Update description(String description) {
            add("description", description);
            return this;
        }
    }
}
