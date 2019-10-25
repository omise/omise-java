package co.omise.models.schedules;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ScheduleStatus {
    @JsonProperty("active")
    Active,
    @JsonProperty("deleted")
    Deleted,
    @JsonProperty("expired")
    Expired,
    @JsonProperty("expiring")
    Expiring,
    @JsonProperty("running")
    Running,
    @JsonProperty("suspended")
    Suspended;
}