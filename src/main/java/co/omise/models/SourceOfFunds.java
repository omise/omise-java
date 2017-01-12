package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SourceOfFunds {
    @JsonProperty("card")Card,
    @JsonProperty("offsite")Offsite,
}
