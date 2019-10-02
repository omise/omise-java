package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RecipientType {
    @JsonProperty("corporation")
    Corporation,
    @JsonProperty("individual")
    Individual;
}