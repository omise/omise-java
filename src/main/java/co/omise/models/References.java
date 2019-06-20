package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class References implements Serializable {

    @JsonProperty("va_code")
    private String vaCode;
    @JsonProperty("omise_tax_id")
    private String omiseTaxId;
    @JsonProperty("reference_number_1")
    private String referenceNumber1;
    @JsonProperty("reference_number_2")
    private String referenceNumber2;
    private String barcode;
    @JsonProperty("expires_at")
    private Date expiresAt;

    public String getOmiseTaxId() {
        return omiseTaxId;
    }

    public void setOmiseTaxId(String omiseTaxId) {
        this.omiseTaxId = omiseTaxId;
    }

    public String getReferenceNumber1() {
        return referenceNumber1;
    }

    public void setReferenceNumber1(String referenceNumber1) {
        this.referenceNumber1 = referenceNumber1;
    }

    public String getReferenceNumber2() {
        return referenceNumber2;
    }

    public void setReferenceNumber2(String referenceNumber2) {
        this.referenceNumber2 = referenceNumber2;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getVaCode() {
        return vaCode;
    }

    public void setVaCode(String vaCode) {
        this.vaCode = vaCode;
    }
}
