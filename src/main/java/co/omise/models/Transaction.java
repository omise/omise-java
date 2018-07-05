package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import okhttp3.HttpUrl;
import org.joda.time.DateTime;

import java.io.IOException;

/**
 * Represents Omise Transaction object.
 *
 * @see <a href="https://www.omise.co/transactions-api">Transactions API</a>
 */
public class Transaction extends Model {
    private long amount;
    private String currency;
    private TransactionType type;
    private String source;
    private DateTime transferable;

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

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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
        protected HttpUrl path() throws IOException {
            return buildUrl(Endpoint.API, "transactions", transactionId);
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
        protected HttpUrl path() throws IOException {
            if (options == null) {
                options = new ScopedList.Options();
            }
            return buildUrl(Endpoint.API, "transactions", options);
        }
    }
}
