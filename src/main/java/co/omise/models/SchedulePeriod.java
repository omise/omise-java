package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SchedulePeriod {
    @JsonProperty("day")day,
    @JsonProperty("week")week,
    @JsonProperty("month")month,
}
