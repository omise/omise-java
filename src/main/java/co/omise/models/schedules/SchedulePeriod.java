package co.omise.models.schedules;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SchedulePeriod {
    @JsonProperty("day")Day,
    @JsonProperty("week")Week,
    @JsonProperty("month")Month,
}