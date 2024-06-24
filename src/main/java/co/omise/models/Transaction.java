package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import java.time.ZonedDateTime;

/**
 * Transaction object
 *
 * @see <a href="https://www.omise.co/transactions-api">Transaction API</a>
 */
public class Transaction extends Model {
    private long amount;
    private String currency;
    private TransactionDirection direction;
    private String key;
    private String location;
    private String origin;
    @JsonProperty("transferable_at")
    private ZonedDateTime transferableAt;

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

    public TransactionDirection getDirection() {
        return this.direction;
    }

    public void setDirection(TransactionDirection direction) {
        this.direction = direction;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public ZonedDateTime getTransferableAt() {
        return this.transferableAt;
    }

    public void setTransferableAt(ZonedDateTime transferableAt) {
        this.transferableAt = transferableAt;
    }

    public static class GetRequestBuilder extends RequestBuilder<Transaction> {
        private final String transactionId;
        public GetRequestBuilder(String transactionId) {
            this.transactionId = transactionId;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "transactions", transactionId);
        }

        @Override
        protected ResponseType<Transaction> type() {
            return new ResponseType<>(Transaction.class);
        }
    }

    public static class ListRequestBuilder extends RequestBuilder<ScopedList<Transaction>> {
        private ScopedList.Options options;

        @Override
        protected HttpUrl path() {
            if (options == null) {
                options = new ScopedList.Options();
            }
            return new HttpUrlBuilder(Endpoint.API, "transactions", serializer())
                  .segments()
                  .params(options)
                  .build();
        }

        @Override
        protected ResponseType<ScopedList<Transaction>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Transaction>>() {});
        }

        public ListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }
}