package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import org.joda.time.DateTime;

/**
 * Represents Omise Transaction object.
 *
 * @see <a href="https://www.omise.co/transactions-api">Transactions API</a>
 */
public class Transaction extends Model {
    private long amount;
    private String currency;
    private TransactionDirection direction;
    private String origin;
    private DateTime transferable;

    public Transaction() {
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

    public TransactionDirection getDirection() {
        return direction;
    }

    public void setDirection(TransactionDirection direction) {
        this.direction = direction;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public DateTime getTransferable() {
        return transferable;
    }

    public void setTransferable(DateTime transferable) {
        this.transferable = transferable;
    }

    /**
     * The {@link RequestBuilder} class for retrieving a particular transaction.
     */
    public static class GetRequestBuilder extends RequestBuilder<Transaction> {

        private String transactionId;

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

    /**
     * The {@link RequestBuilder} class for retrieving all transactions that belong to an account.
     */
    public static class ListRequestBuilder extends RequestBuilder<ScopedList<Transaction>> {

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
            return buildUrl(Endpoint.API, "transactions", options);
        }

        @Override
        protected ResponseType<ScopedList<Transaction>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Transaction>>() {
            });
        }
    }
}
