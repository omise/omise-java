package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Source extends Model {

    private String type;
    private String flow;
    private long amount;
    private String currency;
    private String barcode;
    @JsonProperty("invoice_id")
    private String invoiceId;
    @JsonProperty("references")
    private Reference reference;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
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

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }

    public static class Create extends Params {

        @JsonProperty
        private long amount;
        @JsonProperty
        private String currency;
        @JsonProperty
        private String type;
        @JsonProperty
        private String barcode;
        @JsonProperty("invoice_id")
        private String invoiceId;

        public Create amount(long amount) {
            this.amount = amount;
            return this;
        }

        public Create currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Create type(String type) {
            this.type = type;
            return this;
        }

        public Create barcode(String barcode) {
            this.barcode = barcode;
            return this;
        }

        public Create invoiceId(String invoiceId) {
            this.invoiceId = invoiceId;
            return this;
        }
    }
}
