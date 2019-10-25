package co.omise.models.schedules;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Weekdays {
    @JsonProperty("friday")
    Friday,
    @JsonProperty("monday")
    Monday,
    @JsonProperty("saturday")
    Saturday,
    @JsonProperty("sunday")
    Sunday,
    @JsonProperty("thursday")
    Thursday,
    @JsonProperty("tuesday")
    Tuesday,
    @JsonProperty("wednesday")
    Wednesday;
}