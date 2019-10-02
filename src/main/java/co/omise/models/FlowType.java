package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FlowType {
    @JsonProperty("offline")
    Offline,
    @JsonProperty("redirect")
    Redirect;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}