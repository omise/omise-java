package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

public class TransactionFee {
    @JsonProperty("fee_flat")
    private float feeFlat;

    @JsonProperty("fee_rate")
    private float feeRate;

    @JsonProperty("vat_rate")
    private float vatRate;

    public float getFeeFlat() {
        return feeFlat;
    }

    public void setFeeFlat(float feeFlat) {
        this.feeFlat = feeFlat;
    }

    public float getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(float feeRate) {
        this.feeRate = feeRate;
    }

    public float getVatRate() {
        return vatRate;
    }

    public void setVatRate(float vatRate) {
        this.vatRate = vatRate;
    }
}
