package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DisputeStatus {
    @JsonProperty("open")Open,
    @JsonProperty("pending")Pending,
    @JsonProperty("won")Won,
    @JsonProperty("lost")Lost,
    @JsonProperty("closed")Closed,
}
