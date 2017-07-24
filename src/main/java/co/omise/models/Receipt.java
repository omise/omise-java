package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents Omise Receipt object.
 *
 * @see <a href="https://www.omise.co/receipt-api">Receipts API</a>
 */
/*
number	string The receipt number.
customer_name	string The customer's name.
customer_address	string The customer's address.
customer_tax_id	string The customer's tax id.
customer_email	string The customer's email.
customer_statement_name	string The customer's statement name.
company_name	string Omise company name
company_address	string Omise address
company_tax_id	string Omise tax id
charge_fee	integer The charge fee in the smallest currency unit (e.g. satang, cent).
voided_fee	integer The voided fee in the smallest currency unit (e.g. satang, cent).
transfer_fee	integer The transfer fee in the smallest currency unit (e.g. satang, cent).
subtotal	integer The result of (charge_fee - voided_fee) + transfer_fee.
vat	integer The Value-Added Tax of subtotal.
wht	integer The Withholding Tax of subtotal.
total	integer The total amount in the smallest currency unit (e.g. satang, cent, ...).
credit_note	boolean Whether this is a negative (true) or positive (false) fee subunit.
currency	string The currency as its lower-cased international 3-letter code, defined by the ISO 4217 standard.*/
public class Receipt extends Model {
    private String number;
    @JsonProperty("customer_name")
    private String customerName;
    @JsonProperty("customer_address")
    private String customerAddress;
    @JsonProperty("customer_tax_id")
    private String customerTaxId;
    @JsonProperty("customer_email")
    private String customerEmail;
    @JsonProperty("customer_statement")
    private String customerStatement;
    @JsonProperty("company_name")
    private String companyName;
    @JsonProperty("company_address")
    private String companyAddress;
    @JsonProperty("company_tax_id")
    private String companyTaxId;
    @JsonProperty("charge_fee")
    private long chargeFee;
    @JsonProperty("voided_fee")
    private long voidedFee;
    @JsonProperty("transfer_fee")
    private long transferFee;
    private long subtotal;
    private long vat;
    private long wht;
    private long total;
    @JsonProperty("credit_note")
    private boolean creditNote;
    private String currency;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerTaxId() {
        return customerTaxId;
    }

    public void setCustomerTaxId(String customerTaxId) {
        this.customerTaxId = customerTaxId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerStatement() {
        return customerStatement;
    }

    public void setCustomerStatement(String customerStatement) {
        this.customerStatement = customerStatement;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyTaxId() {
        return companyTaxId;
    }

    public void setCompanyTaxId(String companyTaxId) {
        this.companyTaxId = companyTaxId;
    }

    public long getChargeFee() {
        return chargeFee;
    }

    public void setChargeFee(long chargeFee) {
        this.chargeFee = chargeFee;
    }

    public long getVoidedFee() {
        return voidedFee;
    }

    public void setVoidedFee(long voidedFee) {
        this.voidedFee = voidedFee;
    }

    public long getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(long transferFee) {
        this.transferFee = transferFee;
    }

    public long getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(long subtotal) {
        this.subtotal = subtotal;
    }

    public long getVat() {
        return vat;
    }

    public void setVat(long vat) {
        this.vat = vat;
    }

    public long getWht() {
        return wht;
    }

    public void setWht(long wht) {
        this.wht = wht;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public boolean isCreditNote() {
        return creditNote;
    }

    public void setCreditNote(boolean creditNote) {
        this.creditNote = creditNote;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
