package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ScheduleStatus {
    @JsonProperty("active")Active,
    @JsonProperty("expiring")Expiring,
    @JsonProperty("expired")Expired,
    @JsonProperty("deleted")Deleted,
    @JsonProperty("suspended")Suspended,
}
