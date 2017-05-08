package co.omise.models.schedules;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Weekdays {
    @JsonProperty("monday")Monday,
    @JsonProperty("tuesday")Tuesday,
    @JsonProperty("wednesday")Wednesday,
    @JsonProperty("thursday")Thursday,
    @JsonProperty("friday")Friday,
    @JsonProperty("saturday")Saturday,
    @JsonProperty("sunday")Sunday,
}
