package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Represents Omise Transfer object and contains all of its {@link RequestBuilder}.
 *
 * @see <a href="https://www.omise.co/transfers-api">Tranfers API</a>
 */
public class Transfer extends Model {
    private String recipient;
    @JsonProperty("bank_account")
    private BankAccount bankAccount;
    private boolean sent;
    private boolean paid;
    private long fee;
    @JsonProperty("fee_vat")
    private long feeVat;
    @JsonProperty("total_fee")
    private long totalFee;
    private long net;
    private long amount;
    private String currency;
    @JsonProperty("fail_fast")
    private boolean failFast;
    @JsonProperty("failure_code")
    private String failureCode;
    @JsonProperty("failure_message")
    private String failureMessage;
    private List<Transaction> transactions;
    private Map<String, Object> metadata;
    @JsonProperty("sent_at")
    private DateTime sentAt;
    @JsonProperty("paid_at")
    private DateTime paidAt;

    public Transfer() {
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public boolean isSent() {
        return sent;
    }

    public boolean isPaid() {
        return paid;
    }

    public long getFee() {
        return fee;
    }

    public long getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public boolean isFailFast() {
        return failFast;
    }

    public DateTime getSentAt() {
        return sentAt;
    }

    public DateTime getPaidAt() {
        return paidAt;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setSentAt(DateTime sentAt) {
        this.sentAt = sentAt;
    }

    public void setPaidAt(DateTime paidAt) {
        this.paidAt = paidAt;
    }

    public long getFeeVat() {
        return feeVat;
    }

    public void setFeeVat(long feeVat) {
        this.feeVat = feeVat;
    }

    public long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(long totalFee) {
        this.totalFee = totalFee;
    }

    public long getNet() {
        return net;
    }

    public void setNet(long net) {
        this.net = net;
    }

    /**
     * The {@link RequestBuilder} class for creating a transfer.
     */
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
            return POST;
        }

        @Override
        protected RequestBody payload() throws IOException {
            return serialize();
        }

        @Override
        protected ResponseType<Transfer> type() {
            return new ResponseType<>(Transfer.class);
        }
    }

    /**
     * The {@link RequestBuilder} class for updating a particular transfer.
     */
    public static class UpdateRequestBuilder extends RequestBuilder<Transfer> {
        private String transferId;
        @JsonProperty
        private long amount;
        @JsonProperty
        private Map<String, Object> metadata;

        public UpdateRequestBuilder(String transferId) {
            this.transferId = transferId;
        }

        public UpdateRequestBuilder amount(long amount) {
            this.amount = amount;
            return this;
        }

        public UpdateRequestBuilder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "transfers", transferId);
        }

        @Override
        protected String method() {
            return PATCH;
        }

        @Override
        protected RequestBody payload() throws IOException {
            return serialize();
        }

        @Override
        protected ResponseType<Transfer> type() {
            return new ResponseType<>(Transfer.class);
        }
    }

    /**
     * The {@link RequestBuilder} class for retrieving a particular transfer.
     */
    public static class GetRequestBuilder extends RequestBuilder<Transfer> {

        private String transferId;

        public GetRequestBuilder(String transferId) {
            this.transferId = transferId;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "transfers", transferId);
        }

        @Override
        protected ResponseType<Transfer> type() {
            return new ResponseType<>(Transfer.class);
        }
    }

    /**
     * The {@link RequestBuilder} class for retrieving all transfers that belong to an account.
     */
    public static class ListRequestBuilder extends RequestBuilder<ScopedList<Transfer>> {

        private ScopedList.Options options;

        public ListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }

        @Override
        protected HttpUrl path() {
            if (options == null) {
                options = new ScopedList.Options();
            }
            return buildUrl(Endpoint.API, "transfers", options);
        }

        @Override
        protected ResponseType<ScopedList<Transfer>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Transfer>>() {
            });
        }
    }

    /**
     * The {@link RequestBuilder} class for deleting a particular Transfer.
     */
    public static class DeleteRequestBuilder extends RequestBuilder<Transfer> {
        private String transferId;

        public DeleteRequestBuilder(String transferId) {
            this.transferId = transferId;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "transfers", transferId);
        }

        @Override
        protected String method() {
            return DELETE;
        }

        @Override
        protected ResponseType<Transfer> type() {
            return new ResponseType<>(Transfer.class);
        }
    }

    /**
     * The {@link RequestBuilder} class for destroying a particular transfer.
     */
    @Deprecated
    public static class DestroyRequestBuilder extends RequestBuilder<Transfer> {
        private String transferId;

        public DestroyRequestBuilder(String transferId) {
            this.transferId = transferId;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "transfers", transferId);
        }

        @Override
        protected String method() {
            return DELETE;
        }

        @Override
        protected ResponseType<Transfer> type() {
            return new ResponseType<>(Transfer.class);
        }
    }
}
