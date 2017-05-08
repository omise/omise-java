package co.omise.models.schedules;

import co.omise.models.Model;
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
    private String result;

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public DateTime getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(DateTime scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public DateTime getRetryDate() {
        return retryDate;
    }

    public void setRetryDate(DateTime retryDate) {
        this.retryDate = retryDate;
    }

    public DateTime getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(DateTime processedAt) {
        this.processedAt = processedAt;
    }

    public OccurrenceStatus getStatus() {
        return status;
    }

    public void setStatus(OccurrenceStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
