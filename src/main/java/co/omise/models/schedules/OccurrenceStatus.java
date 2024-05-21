package co.omise.models.schedules;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OccurrenceStatus {
    @JsonProperty("failed")
    Failed,
    @JsonProperty("scheduled")
    Scheduled,
    @JsonProperty("skipped")
    Skipped,
    @JsonProperty("successful")
    Successful
}