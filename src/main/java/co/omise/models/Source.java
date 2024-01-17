package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Source object
 *
 * @see <a href="https://www.omise.co/sources-api">Source API</a>
 */
public class Source extends Model {
    private long amount;
    private String bank;
    private String barcode;
    @JsonProperty("charge_status")
    private ChargeStatus chargeStatus;
    private String currency;
    private String email;
    private String ip;
    private FlowType flow;
    @JsonProperty("installment_term")
    private long installmentTerm;
    private String location;
    @JsonProperty("mobile_number")
    private String mobileNumber;
    private String name;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private References references;
    @JsonProperty("scannable_code")
    private Barcode scannableCode;
    @JsonProperty("store_id")
    private String storeId;
    @JsonProperty("store_name")
    private String storeName;
    @JsonProperty("terminal_id")
    private String terminalId;
    private SourceType type;
    @JsonProperty("zero_interest_installments")
    private boolean zeroInterestInstallments;
    @JsonProperty("platform_type")
    private PlatformType platformType;

    public long getAmount() {
        return this.amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getBank() { return this.bank; }

    public void setBank(String bank) { this.bank = bank; }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public ChargeStatus getChargeStatus() {
        return this.chargeStatus;
    }

    public void setChargeStatus(ChargeStatus chargeStatus) {
        this.chargeStatus = chargeStatus;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEmail() {
        return this.email;
    }

        public String getIp() {
        return this.ip;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }

    public FlowType getFlow() {
        return this.flow;
    }

    public void setFlow(FlowType flow) {
        this.flow = flow;
    }

    public long getInstallmentTerm() {
        return this.installmentTerm;
    }

    public void setInstallmentTerm(long installmentTerm) {
        this.installmentTerm = installmentTerm;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public References getReferences() {
        return this.references;
    }

    public void setReferences(References references) {
        this.references = references;
    }

    public Barcode getScannableCode() {
        return this.scannableCode;
    }

    public void setScannableCode(Barcode scannableCode) {
        this.scannableCode = scannableCode;
    }

    public String getStoreId() {
        return this.storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getTerminalId() {
        return this.terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public SourceType getType() {
        return this.type;
    }

    public void setType(SourceType type) {
        this.type = type;
    }

    public boolean isZeroInterestInstallments() {
        return this.zeroInterestInstallments;
    }

    public void setZeroInterestInstallments(boolean zeroInterestInstallments) {
        this.zeroInterestInstallments = zeroInterestInstallments;
    }

    public PlatformType getPlatformType() {
        return this.platformType;
    }

    public void setPlatformType(PlatformType platformType) {
        this.platformType = platformType;
    }

    public static class CreateRequestBuilder extends RequestBuilder<Source> {

        @JsonProperty
        private long amount;
        @JsonProperty
        private String bank;
        @JsonProperty
        private String barcode;
        @JsonProperty
        private String currency;
        @JsonProperty
        private String email;
        @JsonProperty
        private String ip;
        @JsonProperty("installment_term")
        private long installmentTerm;
        @JsonProperty("mobile_number")
        private String mobileNumber;
        @JsonProperty
        private String name;
        @JsonProperty("store_id")
        private String storeId;
        @JsonProperty("store_name")
        private String storeName;
        @JsonProperty("terminal_id")
        private String terminalId;
        @JsonProperty
        private SourceType type;
        @JsonProperty("zero_interest_installments")
        private boolean zeroInterestInstallments;
        @JsonProperty("platform_type")
        private PlatformType platformType;
        @JsonProperty
        private Shipping shipping;
        @JsonProperty("phone_number")
        private String phoneNumber;
        @JsonProperty("items")
        private List<Item> items;

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "sources");
        }

        @Override
        protected ResponseType<Source> type() {
            return new ResponseType<>(Source.class);
        }

        public CreateRequestBuilder amount(long amount) {
            this.amount = amount;
            return this;
        }

        public CreateRequestBuilder bank(String bank) {
            this.bank = bank;
            return this;
        }

        public CreateRequestBuilder barcode(String barcode) {
            this.barcode = barcode;
            return this;
        }

        public CreateRequestBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public CreateRequestBuilder email(String email) {
            this.email = email;
            return this;
        }
        public CreateRequestBuilder ip(String ip) {
            this.ip = ip;
            return this;
        }

        public CreateRequestBuilder installmentTerm(long installmentTerm) {
            this.installmentTerm = installmentTerm;
            return this;
        }

        public CreateRequestBuilder mobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        public CreateRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CreateRequestBuilder storeId(String storeId) {
            this.storeId = storeId;
            return this;
        }

        public CreateRequestBuilder storeName(String storeName) {
            this.storeName = storeName;
            return this;
        }

        public CreateRequestBuilder shipping(Shipping shipping) {
            this.shipping = shipping;
            return this;
        }

        public CreateRequestBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CreateRequestBuilder addItem(Item item) {
            if(this.items == null) {
                this.items = new ArrayList<Item>();
            }
            this.items.add(item);
            return this;
        }

        public CreateRequestBuilder terminalId(String terminalId) {
            this.terminalId = terminalId;
            return this;
        }

        public CreateRequestBuilder type(SourceType type) {
            this.type = type;
            return this;
        }

        public CreateRequestBuilder zeroInterestInstallments(boolean zeroInterestInstallments) {
            this.zeroInterestInstallments = zeroInterestInstallments;
            return this;
        }

        public CreateRequestBuilder platformType(PlatformType platformType) {
            this.platformType = platformType;
            return this;
        }

        @Override
        protected RequestBody payload() throws IOException {
            return serialize();
        }
    }

    public static class GetRequestBuilder extends RequestBuilder<Source> {
        private String sourceId;
        public GetRequestBuilder(String sourceId) {
            this.sourceId = sourceId;
        }

        @Override
        protected String method() {
            return GET;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "sources", sourceId);
        }

        @Override
        protected ResponseType<Source> type() {
            return new ResponseType<>(Source.class);
        }
    }
}