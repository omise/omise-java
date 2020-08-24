package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DisputeStatus {
    @JsonProperty("closed")
    Closed,
    @JsonProperty("lost")
    Lost,
    @JsonProperty("open")
    Open,
    @JsonProperty("pending")
    Pending,
    @JsonProperty("won")
    Won;
}