package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;
import org.joda.time.LocalDate;

import java.io.IOException;

public class Receipt extends Model {
    @JsonProperty("adjustment_transaction")
    private String adjustmentTransaction;
    @JsonProperty("charge_fee")
    private long chargeFee;
    @JsonProperty("company_address")
    private String companyAddress;
    @JsonProperty("company_name")
    private String companyName;
    @JsonProperty("company_tax_id")
    private String companyTaxId;
    @JsonProperty("credit_note")
    private boolean creditNote;
    private String currency;
    @JsonProperty("customer_address")
    private String customerAddress;
    @JsonProperty("customer_email")
    private String customerEmail;
    @JsonProperty("customer_name")
    private String customerName;
    @JsonProperty("customer_statement_name")
    private String customerStatementName;
    @JsonProperty("customer_tax_id")
    private String customerTaxId;
    @JsonProperty("issued_on")
    private LocalDate issuedDate;
    private String location;
    private String number;
    private long subtotal;
    private long total;
    @JsonProperty("transfer_fee")
    private long transferFee;
    private long vat;
    @JsonProperty("voided_fee")
    private long voidedFee;
    private long wht;

    public String getAdjustmentTransaction() {
        return this.adjustmentTransaction;
    }

    public void setAdjustmentTransaction(String adjustmentTransaction) {
        this.adjustmentTransaction = adjustmentTransaction;
    }

    public long getChargeFee() {
        return this.chargeFee;
    }

    public void setChargeFee(long chargeFee) {
        this.chargeFee = chargeFee;
    }

    public String getCompanyAddress() {
        return this.companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyTaxId() {
        return this.companyTaxId;
    }

    public void setCompanyTaxId(String companyTaxId) {
        this.companyTaxId = companyTaxId;
    }

    public boolean isCreditNote() {
        return this.creditNote;
    }

    public void setCreditNote(boolean creditNote) {
        this.creditNote = creditNote;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomerAddress() {
        return this.customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return this.customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerStatementName() {
        return this.customerStatementName;
    }

    public void setCustomerStatementName(String customerStatementName) {
        this.customerStatementName = customerStatementName;
    }

    public String getCustomerTaxId() {
        return this.customerTaxId;
    }

    public void setCustomerTaxId(String customerTaxId) {
        this.customerTaxId = customerTaxId;
    }

    public LocalDate IssuedDate() {
        return this.issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getSubtotal() {
        return this.subtotal;
    }

    public void setSubtotal(long subtotal) {
        this.subtotal = subtotal;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTransferFee() {
        return this.transferFee;
    }

    public void setTransferFee(long transferFee) {
        this.transferFee = transferFee;
    }

    public long getVat() {
        return this.vat;
    }

    public void setVat(long vat) {
        this.vat = vat;
    }

    public long getVoidedFee() {
        return this.voidedFee;
    }

    public void setVoidedFee(long voidedFee) {
        this.voidedFee = voidedFee;
    }

    public long getWht() {
        return this.wht;
    }

    public void setWht(long wht) {
        this.wht = wht;
    }

    public static class ListRequestBuilder extends RequestBuilder<ScopedList<Receipt>> {
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
            return new HttpUrlBuilder(Endpoint.API, "receipts", serializer())
                  .segments()
                  .params(options)
                  .build();
        }

        @Override
        protected ResponseType<ScopedList<Receipt>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Receipt>>() {});
        }

        public ListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }

    public static class GetRequestBuilder extends RequestBuilder<Receipt> {
        private String receiptId;
        public GetRequestBuilder(String receiptId) {
            this.receiptId = receiptId;
        }

        @Override
        protected String method() {
            return GET;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "receipts", receiptId);
        }

        @Override
        protected ResponseType<Receipt> type() {
            return new ResponseType<>(Receipt.class);
        }
    }
}