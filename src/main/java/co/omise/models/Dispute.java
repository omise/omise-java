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
 * Represents Omise Dispute object.
 *
 * @see <a href="https://www.omise.co/disputes-api">Disputes API</a>
 */
public class Dispute extends Model {
    private long amount;
    private String currency;
    private DisputeStatus status;
    private String message;
    private String charge;
    private Map<String, Object> metadata;

    public Dispute() {
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

    public DisputeStatus getStatus() {
        return status;
    }

    public void setStatus(DisputeStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    /**
     * The {@link RequestBuilder} class for retrieving a particular Dispute.
     */
    public static class GetRequestBuilder extends RequestBuilder<Dispute> {
        private String disputeId;

        public GetRequestBuilder(String disputeId) {
            this.disputeId = disputeId;
        }

        @Override
        protected HttpUrl path() throws IOException {
            return buildUrl(Endpoint.API, "disputes", disputeId);
        }

        @Override
        protected ResponseType<Dispute> type() {
            return new ResponseType<>(Dispute.class);
        }
    }

    /**
     * The {@link RequestBuilder} class for retrieving all Disputes.
     */
    public static class ListRequestBuilder extends RequestBuilder<ScopedList<Dispute>> {

        private ScopedList.Options options;
        private DisputeStatus status;

        @Override
        protected HttpUrl path() {
            if (options == null) {
                options = new ScopedList.Options();
            }
            String status = this.status == null ? "" : this.status.name().toLowerCase();
            return new HttpUrlBuilder(Endpoint.API, "disputes", serializer())
                    .segments(status)
                    .params(options)
                    .build();
        }

        @Override
        protected ResponseType<ScopedList<Dispute>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Dispute>>() {
            });
        }

        public ListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }

        public ListRequestBuilder status(DisputeStatus status) {
            this.status = status;
            return this;
        }
    }

    /**
     * The {@link RequestBuilder} class for updating a Dispute.
     */
    public static class UpdateRequestBuilder extends RequestBuilder<Dispute> {
        private String disputeId;
        @JsonProperty
        private String message;
        @JsonProperty
        private Map<String, Object> metadata;

        public UpdateRequestBuilder(String disputeId) {
            this.disputeId = disputeId;
        }

        @Override
        protected HttpUrl path() throws IOException {
            return buildUrl(Endpoint.API, "disputes", disputeId);
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
        protected ResponseType<Dispute> type() {
            return new ResponseType<>(Dispute.class);
        }

        public UpdateRequestBuilder message(String message) {
            this.message = message;
            return this;
        }

        public UpdateRequestBuilder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
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

}
