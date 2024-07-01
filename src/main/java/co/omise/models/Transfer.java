package co.omise.models;

import co.omise.Endpoint;
import co.omise.models.schedules.Schedule;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;
import java.time.ZonedDateTime;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Transfer object
 *
 * @see <a href="https://www.omise.co/transfers-api">Transfer API</a>
 */
public class Transfer extends Model {
    private long amount;
    @JsonProperty("bank_account")
    private BankAccount bankAccount;
    private String currency;
    @JsonProperty("fail_fast")
    private boolean failFast;
    @JsonProperty("failure_code")
    private String failureCode;
    @JsonProperty("failure_message")
    private String failureMessage;
    private long fee;
    @JsonProperty("fee_vat")
    private long feeVat;
    private String location;
    private Map<String, Object> metadata;
    private long net;
    private boolean paid;
    @JsonProperty("paid_at")
    private ZonedDateTime paidAt;
    private String recipient;
    private String schedule;
    private boolean sendable;
    private boolean sent;
    @JsonProperty("sent_at")
    private ZonedDateTime sentAt;
    @JsonProperty("total_fee")
    private long totalFee;
    private List<Transaction> transactions;

    public long getAmount() {
        return this.amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public BankAccount getBankAccount() {
        return this.bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isFailFast() {
        return this.failFast;
    }

    public void setFailFast(boolean failFast) {
        this.failFast = failFast;
    }

    public String getFailureCode() {
        return this.failureCode;
    }

    public void setFailureCode(String failureCode) {
        this.failureCode = failureCode;
    }

    public String getFailureMessage() {
        return this.failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

    public long getFee() {
        return this.fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public long getFeeVat() {
        return this.feeVat;
    }

    public void setFeeVat(long feeVat) {
        this.feeVat = feeVat;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, Object> getMetadata() {
        return this.metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public long getNet() {
        return this.net;
    }

    public void setNet(long net) {
        this.net = net;
    }

    public boolean isPaid() {
        return this.paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public ZonedDateTime getPaidAt() {
        return this.paidAt;
    }

    public void setPaidAt(ZonedDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSchedule() {
        return this.schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public boolean isSendable() {
        return this.sendable;
    }

    public void setSendable(boolean sendable) {
        this.sendable = sendable;
    }

    public boolean isSent() {
        return this.sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public ZonedDateTime getSentAt() {
        return this.sentAt;
    }

    public void setSentAt(ZonedDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public long getTotalFee() {
        return this.totalFee;
    }

    public void setTotalFee(long totalFee) {
        this.totalFee = totalFee;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public static class DeleteRequestBuilder extends RequestBuilder<Transfer> {
        private final String transferId;
        public DeleteRequestBuilder(String transferId) {
            this.transferId = transferId;
        }

        @Override
        protected String method() {
            return DELETE;
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

    public static class GetRequestBuilder extends RequestBuilder<Transfer> {
        private final String transferId;
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

    public static class UpdateRequestBuilder extends RequestBuilder<Transfer> {
        private final String transferId;

        @JsonProperty
        private long amount;
        @JsonProperty
        private Map<String, Object> metadata;
        public UpdateRequestBuilder(String transferId) {
            this.transferId = transferId;
        }

        @Override
        protected String method() {
            return PATCH;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "transfers", transferId);
        }

        @Override
        protected ResponseType<Transfer> type() {
            return new ResponseType<>(Transfer.class);
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
        protected RequestBody payload() throws IOException {
            return serialize();
        }

        public UpdateRequestBuilder metadata(String key, Object value) {
            HashMap<String, Object> tempMap = new HashMap<>();
            if (metadata != null) {
                tempMap.putAll(metadata);
            }
            tempMap.put(key, value);

            this.metadata = new HashMap<>(tempMap);
            return this;
        }
    }

    public static class ListRequestBuilder extends RequestBuilder<ScopedList<Transfer>> {
        private ScopedList.Options options;

        @Override
        protected HttpUrl path() {
            if (options == null) {
                options = new ScopedList.Options();
            }
            return new HttpUrlBuilder(Endpoint.API, "transfers", serializer())
                  .segments()
                  .params(options)
                  .build();
        }

        @Override
        protected ResponseType<ScopedList<Transfer>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Transfer>>() {});
        }

        public ListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }

    public static class CreateRequestBuilder extends RequestBuilder<Transfer> {

        @JsonProperty
        private long amount;
        @JsonProperty("fail_fast")
        private boolean failFast;
        @JsonProperty
        private Map<String, Object> metadata;
        @JsonProperty
        private String recipient;

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "transfers");
        }

        @Override
        protected ResponseType<Transfer> type() {
            return new ResponseType<>(Transfer.class);
        }

        public CreateRequestBuilder amount(long amount) {
            this.amount = amount;
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

        public CreateRequestBuilder recipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        @Override
        protected RequestBody payload() throws IOException {
            return serialize();
        }

        public CreateRequestBuilder metadata(String key, Object value) {
            HashMap<String, Object> tempMap = new HashMap<>();
            if (metadata != null) {
                tempMap.putAll(metadata);
            }
            tempMap.put(key, value);

            this.metadata = new HashMap<>(tempMap);
            return this;
        }
    }

    public static class ListSchedulesRequestBuilder extends RequestBuilder<ScopedList<Schedule>> {
        private ScopedList.Options options;

        @Override
        protected HttpUrl path() {
            if (options == null) {
                options = new ScopedList.Options();
            }
            return new HttpUrlBuilder(Endpoint.API, "transfers", serializer())
                  .segments("schedules")
                  .params(options)
                  .build();
        }

        @Override
        protected ResponseType<ScopedList<Schedule>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Schedule>>() {});
        }

        public ListSchedulesRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }

    public static class MarkAsPaidRequestBuilder extends RequestBuilder<Transfer> {
        private final String transferId;
        public MarkAsPaidRequestBuilder(String transferId) {
            this.transferId = transferId;
        }

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "transfers", transferId, "mark_as_paid");
        }

        @Override
        protected ResponseType<Transfer> type() {
            return new ResponseType<>(Transfer.class);
        }
    }

    public static class MarkAsSentRequestBuilder extends RequestBuilder<Transfer> {
        private final String transferId;
        public MarkAsSentRequestBuilder(String transferId) {
            this.transferId = transferId;
        }

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "transfers", transferId, "mark_as_sent");
        }

        @Override
        protected ResponseType<Transfer> type() {
            return new ResponseType<>(Transfer.class);
        }
    }
}