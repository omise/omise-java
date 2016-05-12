package co.omise.resources;

import okhttp3.OkHttpClient;

public class ChargeSpecificResource extends Resource {
    private final String chargeId;

    public ChargeSpecificResource(OkHttpClient httpClient, String chargeId) {
        super(httpClient);
        this.chargeId = chargeId;
    }

    public String chargeId() {
        return chargeId;
    }

    public RefundResource refunds() {
        return new RefundResource(httpClient(), chargeId);
    }
}
