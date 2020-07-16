package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QrCode extends Model {
    private String email;
    @JsonProperty("mfa_provisioning_uri")
    private String mfaProvisioningUri;
    private String secret;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMfaProvisioningUri() {
        return this.mfaProvisioningUri;
    }

    public void setMfaProvisioningUri(String mfaProvisioningUri) {
        this.mfaProvisioningUri = mfaProvisioningUri;
    }

    public String getSecret() {
        return this.secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}