package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.HashMap;
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
    private RefundStatus status;
    @JsonProperty("funding_amount")
    private long fundingAmount;
    @JsonProperty("funding_currency")
    private String fundingCurrency;

    public Refund() {
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

    public RefundStatus getStatus() {
        return status;
    }

    public void setStatus(RefundStatus status) {
        this.status = status;
    }

    public long getFundingAmount() {
        return fundingAmount;
    }

    public void setFundingAmount(long fundingAmount) {
        this.fundingAmount = fundingAmount;
    }

    public String getFundingCurrency() {
        return fundingCurrency;
    }

    public void setFundingCurrency(String fundingCurrency) {
        this.fundingCurrency = fundingCurrency;
    }

    /**
     * The {@link RequestBuilder} class for creating a Refund.
     */
    public static class CreateRequestBuilder extends RequestBuilder<Refund> {
        private String chargeId;
        @JsonProperty
        private long amount;
        @JsonProperty
        private Map<String, Object> metadata;

        public CreateRequestBuilder(String chargeId) {
            this.chargeId = chargeId;
        }

        @Override
        protected HttpUrl path() {
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

        @Override
        protected ResponseType<Refund> type() {
            return new ResponseType<>(Refund.class);
        }

        public CreateRequestBuilder amount(long amount) {
            this.amount = amount;
            return this;
        }

        public CreateRequestBuilder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
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

    /**
     * The {@link RequestBuilder} class for retrieving a particular Refund.
     */
    public static class GetRequestBuilder extends RequestBuilder<Refund> {
        private final String chargeId;
        private final String refundId;

        public GetRequestBuilder(String chargeId, String refundId) {
            this.chargeId = chargeId;
            this.refundId = refundId;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "charges", chargeId, "refunds", refundId);
        }

        @Override
        protected ResponseType<Refund> type() {
            return new ResponseType<>(Refund.class);
        }
    }

    /**
     * The {@link RequestBuilder} class for retrieving all Refunds that belong to a particular Charge.
     */
    public static class ListRequestBuilder extends RequestBuilder<ScopedList<Refund>> {
        private final String chargeId;
        private ScopedList.Options options;

        public ListRequestBuilder(String chargeId) {
            this.chargeId = chargeId;
        }

        @Override
        protected HttpUrl path() {
            if (options == null) {
                options = new ScopedList.Options();
            }
            return new HttpUrlBuilder(Endpoint.API, "charges", serializer())
                    .segments(chargeId, "refunds")
                    .params(options)
                    .build();
        }

        @Override
        protected ResponseType<ScopedList<Refund>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Refund>>() {
            });
        }

        public ListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }
}
