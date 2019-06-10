package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RefundStatus {
    @JsonProperty("pending") Pending,
    @JsonProperty("closed") Closed
}
