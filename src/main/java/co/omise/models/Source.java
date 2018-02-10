package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    public static class Create extends Params {

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

        public Create amount(long amount) {
            this.amount = amount;
            return this;
        }

        public Create currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Create type(SourceType type) {
            this.type = type;
            return this;
        }

        public Create description(String description) {
            this.description = description;
            return this;
        }

        public Create barcode(String barcode) {
            this.barcode = barcode;
            return this;
        }

        public Create storeId(String storeId) {
            this.storeId = storeId;
            return this;
        }

        public Create storeName(String storeName) {
            this.storeName = storeName;
            return this;
        }

        public Create terminalId(String terminalId) {
            this.terminalId = terminalId;
            return this;
        }
    }
}
