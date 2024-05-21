package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ChargeStatus {
    @JsonProperty("expired")
    Expired,
    @JsonProperty("failed")
    Failed,
    @JsonProperty("pending")
    Pending,
    @JsonProperty("reversed")
    Reversed,
    @JsonProperty("successful")
    Successful,
    @JsonProperty("unknown")
    Unknown
}