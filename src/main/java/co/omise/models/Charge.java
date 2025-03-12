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
 * Charge object
 *
 * @see <a href="https://www.omise.co/charges-api">Charge API</a>
 */
public class Charge extends Model {
    private long amount;
    @JsonProperty("authorize_uri")
    private String authorizeUri;
    private boolean authorized;
    private String branch;
    private boolean capturable;
    private boolean capture;
    private Card card;
    private String currency;
    private String customer;
    private String description;
    private String device;
    private boolean disputable;
    private Dispute dispute;
    private boolean expired;
    @JsonProperty("expired_at")
    private ZonedDateTime expiredAt;
    @JsonProperty("expires_at")
    private ZonedDateTime expiresAt;
    @JsonProperty("failure_code")
    private String failureCode;
    @JsonProperty("failure_message")
    private String failureMessage;
    private long fee;
    @JsonProperty("fee_vat")
    private long feeVat;
    @JsonProperty("funding_amount")
    private long fundingAmount;
    @JsonProperty("funding_currency")
    private String fundingCurrency;
    private long interest;
    @JsonProperty("interest_vat")
    private long interestVat;
    private String ip;
    private String link;
    private String location;
    private Map<String, Object> metadata;
    private long net;
    private boolean paid;
    @JsonProperty("paid_at")
    private ZonedDateTime paidAt;
    @JsonProperty("platform_fee")
    private PlatformFee platformFee;
    private boolean refundable;
    @JsonProperty("refunded_amount")
    private long refundedAmount;
    @JsonProperty("transaction_fees")
    private TransactionFee transactionFees;
    private ScopedList<Refund> refunds;
    @JsonProperty("return_uri")
    private String returnUri;
    private boolean reversed;
    @JsonProperty("reversed_at")
    private ZonedDateTime reversedAt;
    private boolean reversible;
    private String schedule;
    private Source source;
    private ChargeStatus status;
    private String terminal;
    private String transaction;
    private boolean voided;
    @JsonProperty("zero_interest_installments")
    private boolean zeroInterestInstallments;
    @JsonProperty("authorization_type")
    private AuthorizationType authorizationType;
    @JsonProperty("authorized_amount")
    private long authorizedAmount;
    @JsonProperty("captured_amount")
    private long capturedAmount;
    @JsonProperty("webhook_endpoints")
    private List<String> webhookEndpoints;
    @JsonProperty("merchant_advice")
    private String merchantAdvice;
    @JsonProperty("merchant_advice_code")
    private String merchantAdviceCode;

    public long getAmount() {
        return this.amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getAuthorizeUri() {
        return this.authorizeUri;
    }

    public void setAuthorizeUri(String authorizeUri) {
        this.authorizeUri = authorizeUri;
    }

    public boolean isAuthorized() {
        return this.authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public String getBranch() {
        return this.branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public boolean isCapturable() {
        return this.capturable;
    }

    public void setCapturable(boolean capturable) {
        this.capturable = capturable;
    }

    public boolean isCapture() {
        return this.capture;
    }

    public void setCapture(boolean capture) {
        this.capture = capture;
    }

    public Card getCard() {
        return this.card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDevice() {
        return this.device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public boolean isDisputable() {
        return this.disputable;
    }

    public void setDisputable(boolean disputable) {
        this.disputable = disputable;
    }

    public Dispute getDispute() {
        return this.dispute;
    }

    public void setDispute(Dispute dispute) {
        this.dispute = dispute;
    }

    public boolean isExpired() {
        return this.expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public ZonedDateTime getExpiredAt() {
        return this.expiredAt;
    }

    public void setExpiredAt(ZonedDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

    public ZonedDateTime getExpiresAt() {
        return this.expiresAt;
    }

    public void setExpiresAt(ZonedDateTime expiresAt) {
        this.expiresAt = expiresAt;
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

    public long getInterest() {
        return this.interest;
    }

    public void setInterest(long interest) {
        this.interest = interest;
    }

    public long getInterestVat() {
        return this.interestVat;
    }

    public void setInterestVat(long interestVat) {
        this.interestVat = interestVat;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public PlatformFee getPlatformFee() {
        return this.platformFee;
    }

    public void setPlatformFee(PlatformFee platformFee) {
        this.platformFee = platformFee;
    }

    public boolean isRefundable() {
        return this.refundable;
    }

    public void setRefundable(boolean refundable) {
        this.refundable = refundable;
    }

    public long getRefundedAmount() {
        return this.refundedAmount;
    }

    public void setRefundedAmount(long refundedAmount) {
        this.refundedAmount = refundedAmount;
    }

    public ScopedList<Refund> getRefunds() {
        return this.refunds;
    }

    public void setRefunds(ScopedList<Refund> refunds) {
        this.refunds = refunds;
    }

    public String getReturnUri() {
        return this.returnUri;
    }

    public void setReturnUri(String returnUri) {
        this.returnUri = returnUri;
    }

    public boolean isReversed() {
        return this.reversed;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    public ZonedDateTime getReversedAt() {
        return this.reversedAt;
    }

    public void setReversedAt(ZonedDateTime reversedAt) {
        this.reversedAt = reversedAt;
    }

    public boolean isReversible() {
        return this.reversible;
    }

    public void setReversible(boolean reversible) {
        this.reversible = reversible;
    }

    public String getSchedule() {
        return this.schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Source getSource() {
        return this.source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public ChargeStatus getStatus() {
        return this.status;
    }

    public void setStatus(ChargeStatus status) {
        this.status = status;
    }

    public String getTerminal() {
        return this.terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
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

    public boolean isZeroInterestInstallments() {
        return this.zeroInterestInstallments;
    }

    public void setZeroInterestInstallments(boolean zeroInterestInstallments) {
        this.zeroInterestInstallments = zeroInterestInstallments;
    }

    public TransactionFee getTransactionFees() {
        return transactionFees;
    }

    public void setTransactionFees(TransactionFee transactionFees) {
        this.transactionFees = transactionFees;
    }

    public AuthorizationType getAuthorizationType() {
        return authorizationType;
    }

    public void setAuthorizationType(AuthorizationType authorizationType) {
        this.authorizationType = authorizationType;
    }

    public long getAuthorizedAmount() {
        return authorizedAmount;
    }

    public void setAuthorizedAmount(long authorizedAmount) {
        this.authorizedAmount = authorizedAmount;
    }

    public long getCapturedAmount() {
        return capturedAmount;
    }

    public void setCapturedAmount(long capturedAmount) {
        this.capturedAmount = capturedAmount;
    }

    public List<String> getWebhookEndpoints() {
        return webhookEndpoints;
    }

    public void setWebhookEndpoints(List<String> webhookEndpoints) {
        this.webhookEndpoints = webhookEndpoints;
    }

    public String getMerchantAdvice() {
        return merchantAdvice;
    }

    public void setMerchantAdvice(String merchantAdvice) {
        this.merchantAdvice = merchantAdvice;
    }

    public String getMerchantAdviceCode() {
        return merchantAdviceCode;
    }

    public void setMerchantAdviceCode(String merchantAdviceCode) {
        this.merchantAdviceCode = merchantAdviceCode;
    }

    public static class ListRequestBuilder extends RequestBuilder<ScopedList<Charge>> {
        private ScopedList.Options options;

        @Override
        protected HttpUrl path() {
            if (options == null) {
                options = new ScopedList.Options();
            }
            return new HttpUrlBuilder(Endpoint.API, "charges", serializer())
                  .segments()
                  .params(options)
                  .build();
        }

        @Override
        protected ResponseType<ScopedList<Charge>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Charge>>() {});
        }

        public ListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }

    public static class CreateRequestBuilder extends RequestBuilder<Charge> {
        @JsonProperty("zero_interest_installments")
        private boolean zeroInterestInstallments;

        @JsonProperty
        private long amount;
        @JsonProperty
        private boolean capture;
        @JsonProperty
        private String card;
        @JsonProperty
        private String currency;
        @JsonProperty
        private String customer;
        @JsonProperty
        private String description;
        @JsonProperty("expires_at")
        private ZonedDateTime expiresAt;
        @JsonProperty
        private String ip;
        @JsonProperty
        private Map<String, Object> metadata;
        @JsonProperty("platform_fee")
        private PlatformFee.Params platformFee;
        @JsonProperty
        private String reference;
        @JsonProperty("return_uri")
        private String returnUri;
        @JsonProperty
        private String source;
        @JsonProperty("authorization_type")
        private AuthorizationType authorizationType;
        @JsonProperty("webhook_endpoints")
        private List<String> webhookEndpoints;
        @JsonProperty("first_charge")
        private String firstCharge;
        @JsonProperty("linked_account")
        private String linkedAccount;
        @JsonProperty("recurring_reason")
        private String recurringReason;
        @JsonProperty("transaction_indicator")
        private String transactionIndicator;

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "charges");
        }

        @Override
        protected ResponseType<Charge> type() {
            return new ResponseType<>(Charge.class);
        }

        public CreateRequestBuilder amount(long amount) {
            this.amount = amount;
            return this;
        }

        public CreateRequestBuilder capture(boolean capture) {
            this.capture = capture;
            return this;
        }

        public CreateRequestBuilder card(String card) {
            this.card = card;
            return this;
        }

        public CreateRequestBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public CreateRequestBuilder customer(String customer) {
            this.customer = customer;
            return this;
        }

        public CreateRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CreateRequestBuilder expiresAt(ZonedDateTime expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public CreateRequestBuilder ip(String ip) {
            this.ip = ip;
            return this;
        }

        public CreateRequestBuilder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }

        public CreateRequestBuilder platformFee(PlatformFee.Params platformFee) {
            this.platformFee = platformFee;
            return this;
        }

        public CreateRequestBuilder reference(String reference) {
            this.reference = reference;
            return this;
        }

        public CreateRequestBuilder returnUri(String returnUri) {
            this.returnUri = returnUri;
            return this;
        }

        public CreateRequestBuilder source(String source) {
            this.source = source;
            return this;
        }

        public CreateRequestBuilder zeroInterestInstallments(boolean zeroInterestInstallments) {
            this.zeroInterestInstallments = zeroInterestInstallments;
            return this;
        }

        public CreateRequestBuilder authorizationType(AuthorizationType authorizationType) {
            this.authorizationType = authorizationType;
            return this;
        }

        public CreateRequestBuilder webhookEndpoints(List<String> webhookEndpoints) {
            this.webhookEndpoints = webhookEndpoints;
            return this;
        }

        public CreateRequestBuilder firstCharge(String firstCharge) {
            this.firstCharge = firstCharge;
            return this;
        }

        public CreateRequestBuilder linkedAccount(String linkedAccount) {
            this.linkedAccount = linkedAccount;
            return this;
        }

        public CreateRequestBuilder recurringReason(String recurringReason) {
            this.recurringReason = recurringReason;
            return this;
        }

        public CreateRequestBuilder transactionIndicator(String transactionIndicator) {
            this.transactionIndicator = transactionIndicator;
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

    public static class ListEventsRequestBuilder extends RequestBuilder<ScopedList<Event>> {
        private final String chargeId;
        private ScopedList.Options options;
        public ListEventsRequestBuilder(String chargeId) {
            this.chargeId = chargeId;
        }

        @Override
        protected HttpUrl path() {
            if (options == null) {
                options = new ScopedList.Options();
            }
            return new HttpUrlBuilder(Endpoint.API, "charges", serializer())
                  .segments(chargeId, "events")
                  .params(options)
                  .build();
        }

        @Override
        protected ResponseType<ScopedList<Event>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Event>>() {});
        }

        public ListEventsRequestBuilder options(ScopedList.Options options) {
            this.options = options;
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
            return new HttpUrlBuilder(Endpoint.API, "charges", serializer())
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

    public static class GetRequestBuilder extends RequestBuilder<Charge> {
        private final String chargeId;
        public GetRequestBuilder(String chargeId) {
            this.chargeId = chargeId;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "charges", chargeId);
        }

        @Override
        protected ResponseType<Charge> type() {
            return new ResponseType<>(Charge.class);
        }
    }

    public static class UpdateRequestBuilder extends RequestBuilder<Charge> {
        private final String chargeId;

        @JsonProperty
        private String description;
        @JsonProperty
        private Map<String, Object> metadata;
        public UpdateRequestBuilder(String chargeId) {
            this.chargeId = chargeId;
        }

        @Override
        protected String method() {
            return PATCH;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "charges", chargeId);
        }

        @Override
        protected ResponseType<Charge> type() {
            return new ResponseType<>(Charge.class);
        }

        public UpdateRequestBuilder description(String description) {
            this.description = description;
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

    public static class CaptureRequestBuilder extends RequestBuilder<Charge> {
        private final String chargeId;

        @JsonProperty("capture_amount")
        private long captureAmount;

        public CaptureRequestBuilder(String chargeId) {
            this.chargeId = chargeId;
        }

        public CaptureRequestBuilder captureAmount(long captureAmount) {
            this.captureAmount = captureAmount;
            return this;
        }

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "charges", chargeId, "capture");
        }

        @Override
        protected ResponseType<Charge> type() {
            return new ResponseType<>(Charge.class);
        }

        @Override
        protected RequestBody payload() throws IOException {
            return serialize();
        }
    }

    public static class ExpireRequestBuilder extends RequestBuilder<Charge> {
        private final String chargeId;
        public ExpireRequestBuilder(String chargeId) {
            this.chargeId = chargeId;
        }

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "charges", chargeId, "expire");
        }

        @Override
        protected ResponseType<Charge> type() {
            return new ResponseType<>(Charge.class);
        }
    }

    public static class MarkAsFailedRequestBuilder extends RequestBuilder<Charge> {
        private final String chargeId;
        public MarkAsFailedRequestBuilder(String chargeId) {
            this.chargeId = chargeId;
        }

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "charges", chargeId, "mark_as_failed");
        }

        @Override
        protected ResponseType<Charge> type() {
            return new ResponseType<>(Charge.class);
        }
    }

    public static class MarkAsPaidRequestBuilder extends RequestBuilder<Charge> {
        private final String chargeId;
        public MarkAsPaidRequestBuilder(String chargeId) {
            this.chargeId = chargeId;
        }

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "charges", chargeId, "mark_as_paid");
        }

        @Override
        protected ResponseType<Charge> type() {
            return new ResponseType<>(Charge.class);
        }
    }

    public static class ReverseRequestBuilder extends RequestBuilder<Charge> {
        private final String chargeId;
        public ReverseRequestBuilder(String chargeId) {
            this.chargeId = chargeId;
        }

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "charges", chargeId, "reverse");
        }

        @Override
        protected ResponseType<Charge> type() {
            return new ResponseType<>(Charge.class);
        }
    }
}