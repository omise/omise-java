package co.omise.models.schedules;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ScheduleStatus {
    @JsonProperty("running") Running,
    @JsonProperty("expiring") Expiring,
    @JsonProperty("expired") Expired,
    @JsonProperty("deleted") Deleted,
    @JsonProperty("suspended") Suspended,
}
