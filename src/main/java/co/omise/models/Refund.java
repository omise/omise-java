package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.Map;

/**
 * Represents Omise Refund object.
 *
 * @see <a href="https://www.omise.co/refunds-api">Refunds API</a>
 */
public class Refund extends Model {
    private long amount;
    private String currency;
    private String charge;
    private String transaction;
    private Map<String, Object> metadata;

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

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
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

    public static class CreateRequestBuilder extends RequestBuilder<Refund> {
        private String chargeId;
        @JsonProperty
        private long amount;
        @JsonProperty
        private Map<String, Object> metadata;

        @Override
        protected HttpUrl path() throws IOException {
            return buildUrl(Endpoint.API, "charges", chargeId, "refunds");
        }

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected RequestBody payload() throws IOException {
            return serialize();
        }

        public CreateRequestBuilder(String chargeId) {
            this.chargeId = chargeId;
        }

        public CreateRequestBuilder amount(long amount) {
            this.amount = amount;
            return this;
        }

        public CreateRequestBuilder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }
    }
}
