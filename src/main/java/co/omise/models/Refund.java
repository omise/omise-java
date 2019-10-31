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
 * Refund object
 *
 * @see <a href="https://www.omise.co/refunds-api">Refund API</a>
 */
public class Refund extends Model {
    private long amount;
    private String charge;
    private String currency;
    @JsonProperty("funding_amount")
    private long fundingAmount;
    @JsonProperty("funding_currency")
    private String fundingCurrency;
    private String location;
    private Map<String, Object> metadata;
    private RefundStatus status;
    private String transaction;
    private boolean voided;

    public long getAmount() {
        return this.amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCharge() {
        return this.charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getFundingAmount() {
        return this.fundingAmount;
    }

    public void setFundingAmount(long fundingAmount) {
        this.fundingAmount = fundingAmount;
    }

    public String getFundingCurrency() {
        return this.fundingCurrency;
    }

    public void setFundingCurrency(String fundingCurrency) {
        this.fundingCurrency = fundingCurrency;
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

    public RefundStatus getStatus() {
        return this.status;
    }

    public void setStatus(RefundStatus status) {
        this.status = status;
    }

    public String getTransaction() {
        return this.transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public boolean isVoided() {
        return this.voided;
    }

    public void setVoided(boolean voided) {
        this.voided = voided;
    }

    public static class CreateRequestBuilder extends RequestBuilder<Refund> {
        private String chargeId;

        @JsonProperty
        private long amount;
        @JsonProperty
        private Map<String, Object> metadata;
        @JsonProperty("void")
        private boolean isVoid;
        public CreateRequestBuilder(String chargeId) {
            this.chargeId = chargeId;
        }

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "charges", chargeId, "refunds");
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

        public CreateRequestBuilder isVoid(boolean isVoid) {
            this.isVoid = isVoid;
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

    public static class GetRequestBuilder extends RequestBuilder<Refund> {
        private String chargeId;
        private String refundId;
        public GetRequestBuilder(String chargeId, String refundId) {
            this.chargeId = chargeId;
            this.refundId = refundId;
        }

        @Override
        protected String method() {
            return GET;
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

    public static class ListRequestBuilder extends RequestBuilder<ScopedList<Refund>> {
        private String chargeId;
        private ScopedList.Options options;
        public ListRequestBuilder(String chargeId) {
            this.chargeId = chargeId;
        }

        @Override
        protected String method() {
            return GET;
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
            return new ResponseType<>(new TypeReference<ScopedList<Refund>>() {});
        }

        public ListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }
}