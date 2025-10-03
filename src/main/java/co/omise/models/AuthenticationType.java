package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AuthenticationType {
    @JsonProperty("3DS")
    THREE_DS("3DS"),
    @JsonProperty("PASSKEY")
    PASSKEY("PASSKEY");

    private final String wireValue;

    AuthenticationType(String wireValue) {
        this.wireValue = wireValue;
    }

    public String getWireValue() {
        return wireValue;
    }
}
