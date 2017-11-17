package co.omise.models;


import com.fasterxml.jackson.annotation.JsonProperty;

public enum FlowType {
    @JsonProperty("redirect") Redirect,
    @JsonProperty("offline") Offline;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
