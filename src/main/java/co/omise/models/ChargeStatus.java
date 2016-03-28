package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ChargeStatus {
    @JsonProperty("failed")Failed,
    @JsonProperty("pending")Pending,
    @JsonProperty("successful")Successful,
}
