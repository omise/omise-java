package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

/**
 * Represents Omise Occurrence object.
 *
 * @see <a href="https://www.omise.co/occurrences-api">Occurrence API</a>
 */
public class Occurrence extends Model {
    private String schedule;
    @JsonProperty("schedule_date")
    private DateTime scheduleDate;
    @JsonProperty("retry_date")
    private DateTime retryDate;
    @JsonProperty("processed_at")
    private DateTime processedAt;
    private OccurrenceStatus status;
    private String message;

    // TODO: Polymorphic
    private Object result;
}
