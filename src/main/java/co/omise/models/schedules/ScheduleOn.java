package co.omise.models.schedules;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ScheduleOn implements Serializable {
    @JsonProperty("days_of_month")
    private int[] daysOfMonth;
    @JsonProperty("weekday_of_month")
    private String weekdayOfMonth;
    private Weekdays[] weekdays;

    public int[] getDaysOfMonth() {
        return this.daysOfMonth;
    }

    public void setDaysOfMonth(int[] daysOfMonth) {
        this.daysOfMonth = daysOfMonth;
    }

    public String getWeekdayOfMonth() {
        return this.weekdayOfMonth;
    }

    public void setWeekdayOfMonth(String weekdayOfMonth) {
        this.weekdayOfMonth = weekdayOfMonth;
    }

    public Weekdays[] getWeekdays() {
        return this.weekdays;
    }

    public void setWeekdays(Weekdays[] weekdays) {
        this.weekdays = weekdays;
    }

    public static class Params extends co.omise.models.Params {
        @JsonProperty("days_of_month")
        private int[] daysOfMonth;

        @JsonProperty("weekday_of_month")
        private String weekdayOfMonth;

        @JsonProperty
        private Weekdays[] weekdays;

        public Params daysOfMonth(int... daysOfMonth) {
            this.daysOfMonth = daysOfMonth;
            return this;
        }

        public Params weekdayOfMonth(String weekdayOfMonth) {
            this.weekdayOfMonth = weekdayOfMonth;
            return this;
        }

        public Params weekdays(Weekdays... weekdays) {
            this.weekdays = weekdays;
            return this;
        }
    }
}