package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.Map;

/**
 * Represents Omise Token object.
 *
 * @see <a href="https://www.omise.co/tokens-api">Tokens API</a>
 */
public class Transfer extends Model {
    private String recipient;
    @JsonProperty("bank_account")
    private BankAccount bankAccount;
    private boolean sent;
    private boolean paid;
    private long fee;
    private long amount;
    private String currency;
    @JsonProperty("fail_fast")
    private boolean failFast;
    @JsonProperty("failure_code")
    private String failureCode;
    @JsonProperty("failure_message")
    private String failureMessage;
    private String transaction;
    private Map<String, Object> metadata;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
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

    public boolean failFast() {
        return failFast;
    }

    public void setFailFast(boolean failFast) {
        this.failFast = failFast;
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

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public static class CreateRequestBuilder extends RequestBuilder<Transfer> {

        @JsonProperty
        private long amount;
        @JsonProperty
        private String recipient;
        @JsonProperty("fail_fast")
        private boolean failFast;
        @JsonProperty
        private Map<String, Object> metadata;

        public CreateRequestBuilder amount(long amount) {
            this.amount = amount;
            return this;
        }

        public CreateRequestBuilder recipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public CreateRequestBuilder failFast(boolean failFast) {
            this.failFast = failFast;
            return this;
        }

        public CreateRequestBuilder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "transfers");
        }

        @Override
        protected String method() {
            return "POST";
        }

        @Override
        protected RequestBody payload() throws IOException {
            return serialize();
        }
    }

    public static class UpdateRequestBuilder extends RequestBuilder<Transfer> {

        @Override
        protected HttpUrl path() {
            return null;
        }
    }

    public static class GetRequestBuilder extends RequestBuilder<Transfer> {

        private String id;

        public GetRequestBuilder id(String id) {
            this.id = id;
            return this;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "transfers", id);
        }
    }

    public static class DestroyRequestBuilder extends RequestBuilder<Transfer> {

        @Override
        protected HttpUrl path() {
            return null;
        }
    }

    public static class Create extends Params {
        @JsonProperty
        private long amount;
        @JsonProperty
        private String recipient;
        @JsonProperty("fail_fast")
        private boolean failFast;
        @JsonProperty
        private Map<String, Object> metadata;

        public Create amount(long amount) {
            this.amount = amount;
            return this;
        }

        public Create recipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public Create failFast(boolean failFast) {
            this.failFast = failFast;
            return this;
        }

        public Create metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }
    }

    public static class Update extends Params {
        @JsonProperty
        private long amount;
        @JsonProperty
        private Map<String, Object> metadata;

        public Update amount(long amount) {
            this.amount = amount;
            return this;
        }

        public Update metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }
    }
}

