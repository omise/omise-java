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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dispute object
 *
 * @see <a href="https://www.omise.co/disputes-api">Dispute API</a>
 */
public class Dispute extends Model {
    @JsonProperty("admin_message")
    private String adminMessage;
    private long amount;
    private String charge;
    @JsonProperty("closed_at")
    private DateTime closedDate;
    private String currency;
    private ScopedList<Document> documents;
    @JsonProperty("funding_amount")
    private long fundingAmount;
    @JsonProperty("funding_currency")
    private String fundingCurrency;
    private String location;
    private String message;
    private Map<String, Object> metadata;
    @JsonProperty("reason_code")
    private DisputeReasonCode reasonCode;
    @JsonProperty("reason_message")
    private String reasonMessage;
    private DisputeStatus status;
    private List<Transaction> transactions;

    public String getAdminMessage() {
        return this.adminMessage;
    }

    public void setAdminMessage(String adminMessage) {
        this.adminMessage = adminMessage;
    }

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

    public DateTime getClosedDate() {
        return this.closedDate;
    }

    public void setClosedDate(DateTime closedDate) {
        this.closedDate = closedDate;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public ScopedList<Document> getDocuments() {
        return this.documents;
    }

    public void setDocuments(ScopedList<Document> documents) {
        this.documents = documents;
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

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getMetadata() {
        return this.metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public DisputeReasonCode getReasonCode() {
        return this.reasonCode;
    }

    public void setReasonCode(DisputeReasonCode reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getReasonMessage() {
        return this.reasonMessage;
    }

    public void setReasonMessage(String reasonMessage) {
        this.reasonMessage = reasonMessage;
    }

    public DisputeStatus getStatus() {
        return this.status;
    }

    public void setStatus(DisputeStatus status) {
        this.status = status;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public static class ClosedRequestBuilder extends RequestBuilder<Dispute> {

        @Override
        protected String method() {
            return GET;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "disputes", "closed");
        }

        @Override
        protected ResponseType<Dispute> type() {
            return new ResponseType<>(Dispute.class);
        }
    }

    public static class ListRequestBuilder extends RequestBuilder<ScopedList<Dispute>> {
        private DisputeStatus status;
        private ScopedList.Options options;

        @Override
        protected String method() {
            return GET;
        }

        @Override
        protected HttpUrl path() {
            if (options == null) {
                options = new ScopedList.Options();
            }
            return new HttpUrlBuilder(Endpoint.API, "disputes", serializer())
                  .segments(this.status == null ? "" : this.status.name().toLowerCase())
                  .params(options)
                  .build();
        }

        @Override
        protected ResponseType<ScopedList<Dispute>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Dispute>>() {});
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

    public static class OpenRequestBuilder extends RequestBuilder<Dispute> {

        @Override
        protected String method() {
            return GET;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "disputes", "open");
        }

        @Override
        protected ResponseType<Dispute> type() {
            return new ResponseType<>(Dispute.class);
        }
    }

    public static class PendingRequestBuilder extends RequestBuilder<Dispute> {

        @Override
        protected String method() {
            return GET;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "disputes", "pending");
        }

        @Override
        protected ResponseType<Dispute> type() {
            return new ResponseType<>(Dispute.class);
        }
    }

    public static class GetRequestBuilder extends RequestBuilder<Dispute> {
        private String disputeId;
        public GetRequestBuilder(String disputeId) {
            this.disputeId = disputeId;
        }

        @Override
        protected String method() {
            return GET;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "disputes", disputeId);
        }

        @Override
        protected ResponseType<Dispute> type() {
            return new ResponseType<>(Dispute.class);
        }
    }

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
        protected String method() {
            return PATCH;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "disputes", disputeId);
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

        @Override
        protected RequestBody payload() throws IOException  {
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

    public static class CloseRequestBuilder extends RequestBuilder<Dispute> {
        private String disputeId;

        @JsonProperty
        private DisputeStatus status;
        public CloseRequestBuilder(String disputeId) {
            this.disputeId = disputeId;
        }

        @Override
        protected String method() {
            return PATCH;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "disputes", disputeId, "close");
        }

        @Override
        protected ResponseType<Dispute> type() {
            return new ResponseType<>(Dispute.class);
        }

        public CloseRequestBuilder status(DisputeStatus status) {
            this.status = status;
            return this;
        }

        @Override
        protected RequestBody payload() throws IOException  {
            return serialize();
        }
    }

    public static class AcceptRequestBuilder extends RequestBuilder<Dispute> {
        private String disputeId;
        public AcceptRequestBuilder(String disputeId) {
            this.disputeId = disputeId;
        }

        @Override
        protected String method() {
            return PATCH;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "disputes", disputeId, "accept");
        }

        @Override
        protected ResponseType<Dispute> type() {
            return new ResponseType<>(Dispute.class);
        }
    }

    public static class CreateDisputeRequestBuilder extends RequestBuilder<Dispute> {
        private String chargeId;
        public CreateDisputeRequestBuilder(String chargeId) {
            this.chargeId = chargeId;
        }

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "charges", chargeId, "disputes");
        }

        @Override
        protected ResponseType<Dispute> type() {
            return new ResponseType<>(Dispute.class);
        }
    }
}