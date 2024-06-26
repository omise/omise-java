package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.io.Serializable;

public class References implements Serializable {
    private String barcode;
    @JsonProperty("customer_amount")
    private long customerAmount;
    @JsonProperty("customer_currency")
    private String customerCurrency;
    @JsonProperty("customer_exchange_rate")
    private Double customerExchangeRate;
    @JsonProperty("device_id")
    private String deviceId;
    @JsonProperty("expires_at")
    private ZonedDateTime expiresAt;
    @JsonProperty("omise_tax_id")
    private String omiseTaxId;
    @JsonProperty("payment_code")
    private String paymentCode;
    @JsonProperty("reference_number_1")
    private String referenceNumber1;
    @JsonProperty("reference_number_2")
    private String referenceNumber2;
    @JsonProperty("va_code")
    private String vaCode;

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public long getCustomerAmount() {
        return this.customerAmount;
    }

    public void setCustomerAmount(long customerAmount) {
        this.customerAmount = customerAmount;
    }

    public String getCustomerCurrency() {
        return this.customerCurrency;
    }

    public void setCustomerCurrency(String customerCurrency) {
        this.customerCurrency = customerCurrency;
    }

    public Double getCustomerExchangeRate() {
        return this.customerExchangeRate;
    }

    public void setCustomerExchangeRate(Double customerExchangeRate) {
        this.customerExchangeRate = customerExchangeRate;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public ZonedDateTime getExpiresAt() {
        return this.expiresAt;
    }

    public void setExpiresAt(ZonedDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getOmiseTaxId() {
        return this.omiseTaxId;
    }

    public void setOmiseTaxId(String omiseTaxId) {
        this.omiseTaxId = omiseTaxId;
    }

    public String getPaymentCode() {
        return this.paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getReferenceNumber1() {
        return this.referenceNumber1;
    }

    public void setReferenceNumber1(String referenceNumber1) {
        this.referenceNumber1 = referenceNumber1;
    }

    public String getReferenceNumber2() {
        return this.referenceNumber2;
    }

    public void setReferenceNumber2(String referenceNumber2) {
        this.referenceNumber2 = referenceNumber2;
    }

    public String getVaCode() {
        return this.vaCode;
    }

    public void setVaCode(String vaCode) {
        this.vaCode = vaCode;
    }
}