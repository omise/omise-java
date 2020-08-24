package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Ordering {
    @JsonProperty("chronological")
    Chronological,
    @JsonProperty("reverse_chronological")
    ReverseChronological;
}