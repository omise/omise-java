package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FlowType {
    @JsonProperty("offline")
    Offline,
    @JsonProperty("redirect")
    Redirect,
    @JsonProperty("app_redirect")
    Offsite;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}