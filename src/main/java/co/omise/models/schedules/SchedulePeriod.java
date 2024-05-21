package co.omise.models.schedules;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SchedulePeriod {
    @JsonProperty("day")
    Day,
    @JsonProperty("month")
    Month,
    @JsonProperty("week")
    Week
}