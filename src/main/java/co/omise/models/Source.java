package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

import java.io.IOException;

/**
 * Represents Omise Source object.
 *
 * @see <a href="https://www.omise.co/source-api">Source API</a>
 */
public class Source extends Model {

    private SourceType type;
    private FlowType flow;
    private long amount;
    private String currency;
    private String barcode;
    private References references;
    @JsonProperty("store_id")
    private String storeId;
    @JsonProperty("store_name")
    private String storeName;
    @JsonProperty("terminal_id")
    private String terminalId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone_number")
    private String phoneNumber;
    
    public SourceType getType() {
        return type;
    }

    public void setType(SourceType type) {
        this.type = type;
    }

    public FlowType getFlow() {
        return flow;
    }

    public void setFlow(FlowType flow) {
        this.flow = flow;
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public References getReferences() {
        return references;
    }

    public void setReferences(References references) {
        this.references = references;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * The {@link RequestBuilder} class for creating a Source.
     */
    public static class CreateRequestBuilder extends RequestBuilder<Source> {
        @JsonProperty
        private long amount;
        @JsonProperty
        private String currency;
        @JsonProperty
        private SourceType type;
        @JsonProperty
        private String description;
        @JsonProperty
        private String barcode;
        @JsonProperty("store_id")
        private String storeId;
        @JsonProperty("store_name")
        private String storeName;
        @JsonProperty("terminal_id")
        private String terminalId;
        @JsonProperty("name")
        private String name;
        @JsonProperty("email")
        private String email;
        @JsonProperty("phone_number")
        private String phoneNumber;

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "sources");
        }

        @Override
        protected RequestBody payload() throws IOException {
            return serialize();
        }

        @Override
        protected ResponseType<Source> type() {
            return new ResponseType<>(Source.class);
        }

        public CreateRequestBuilder amount(long amount) {
            this.amount = amount;
            return this;
        }

        public CreateRequestBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public CreateRequestBuilder type(SourceType type) {
            this.type = type;
            return this;
        }

        public CreateRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CreateRequestBuilder barcode(String barcode) {
            this.barcode = barcode;
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

        public CreateRequestBuilder terminalId(String terminalId) {
            this.terminalId = terminalId;
            return this;
        }
        
        public Create name(String name) {
            this.name = name;
            return this;
        }
        
        public Create email(String email) {
            this.email = email;
            return this;
        }
        
        public Create phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
    }
}