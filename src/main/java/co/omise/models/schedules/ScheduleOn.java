package co.omise.models.schedules;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

// key presence meaningful, must not send nulls or []s
public class ScheduleOn {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("weekdays")
    private Weekdays[] weekdays;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("days_of_month")
    private int[] daysOfMonth;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("weekday_of_month")
    private String weekdayOfMonth;

    public Weekdays[] getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(Weekdays[] weekdays) {
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

    public static class Params extends co.omise.models.Params {
        @JsonProperty("weekdays")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Weekdays[] weekdays;
        @JsonProperty("days_of_month")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private int[] daysOfMonth;
        @JsonProperty("weekday_of_month")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String weekdayOfMonth;

        /**
         * Specify that a schedule should run only on certain weekdays.
         *
         * @param weekdays A list of {@link Weekdays} values.
         *                 @return itself, for chaining.
         */
        public Params weekdays(Weekdays... weekdays) {
            this.weekdays = weekdays;
            return this;
        }

        /**
         * Specify that a schedule should run only on specific date each month.
         *
         * @param days The month date to run.
         *             Specify 1 to run every month's start and 28th for every month's end.
         * @return itself, for chaining.
         */
        public Params daysOfMonth(int... days) {
            this.daysOfMonth = days;
            return this;
        }

        /**
         * Specify that a schedule should run only on the ordinalized weekday of each month.
         *
         * @param weekdayOfMonth Ordinalized weekday of month in underscore form.
         *                       For example `2nd_monday` or `last_friday`.
         * @return itself, for chaining.
         */
        public Params weekdayOfMonth(String weekdayOfMonth) {
            this.weekdayOfMonth = weekdayOfMonth;
            return this;
        }
    }
}
