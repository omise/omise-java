package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RecipientType {
    @JsonProperty("individual")Individual,
    @JsonProperty("corporation")Corporation,
}
