package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AuthenticationType {
    @JsonProperty("3DS")
    THREE_DS,
    @JsonProperty("PASSKEY")
    PASSKEY
}
