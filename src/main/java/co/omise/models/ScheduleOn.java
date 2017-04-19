package co.omise.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

// key presence meaningful, must not send nulls or []s
public class ScheduleOn {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("weekdays")
    private int[] weekdays;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("days_of_month")
    private int[] daysOfMonth;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("weekday_of_month")
    private String weekdayOfMonth;

    public int[] getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(int[] weekdays) {
        this.weekdays = weekdays;
    }

    public int[] getDaysOfMonth() {
        return daysOfMonth;
    }

    public void setDaysOfMonth(int[] daysOfMonth) {
        this.daysOfMonth = daysOfMonth;
    }

    public String getWeekdayOfMonth() {
        return weekdayOfMonth;
    }

    public void setWeekdayOfMonth(String weekdayOfMonth) {
        this.weekdayOfMonth = weekdayOfMonth;
    }
}
