package co.omise.models.schedules;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OccurrenceStatus {
    @JsonProperty("skipped")Skipped,
    @JsonProperty("failed")Failed,
    @JsonProperty("successful")Successful,
}
