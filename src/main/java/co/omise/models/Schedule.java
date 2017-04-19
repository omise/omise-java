package co.omise.models;

import org.joda.time.DateTime;

/**
 * Represents Omise Schedule object.
 *
 * @see <a href="https://www.omise.co/schedules-api">Schedule API</a>
 */
public class Schedule extends Model {
    private ScheduleStatus status;
    private int every;
    private SchedulePeriod period;
    private String inWords;
    private ScheduleOn on;
    private DateTime startDate;
    private DateTime endDate;

    private ChargeScheduleDetails charge;
    private TransferScheduleDetails transfer;

    // TODO: occurrences
    // private ScopedList<Occurrence> occurrences;
}
