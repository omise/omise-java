package co.omise.models.schedules;

import co.omise.Endpoint;
import co.omise.models.Model;
import co.omise.models.ScopedList;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Instant;

/**
 * Represents Omise Occurrence object.
 *
 * @see <a href="https://www.omise.co/occurrences-api">Occurrence API</a>
 */
public class Occurrence extends Model {
    private String schedule;
    @JsonProperty("schedule_date")
    private LocalDate scheduleDate;
    @JsonProperty("retry_date")
    private LocalDate retryDate;
    @JsonProperty("processed_at")
    private Instant processedAt;
    private OccurrenceStatus status;
    private String message;
    private String result;

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public LocalDate getRetryDate() {
        return retryDate;
    }

    public void setRetryDate(LocalDate retryDate) {
        this.retryDate = retryDate;
    }

    public Instant getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(Instant processedAt) {
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

    /**
     * The {@link RequestBuilder} class for retrieving all occurrences that belong to a schedule.
     */
    public static class ListRequestBuilder extends RequestBuilder<ScopedList<Occurrence>> {

        private String scheduleId;
        private ScopedList.Options options;

        public ListRequestBuilder(String scheduleId) {
            this.scheduleId = scheduleId;
        }

        @Override
        protected HttpUrl path() throws IOException {
            if (options == null) {
                options = new ScopedList.Options();
            }

            return new HttpUrlBuilder(Endpoint.API, "schedules", serializer())
                    .segments(scheduleId, "occurrences")
                    .params(options)
                    .build();
        }

        @Override
        protected ResponseType<ScopedList<Occurrence>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Occurrence>>() {});
        }

        public Occurrence.ListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }

    /**
     * The {@link RequestBuilder} class for retrieving a particular occurrence.
     */
    public static class GetRequestBuilder extends RequestBuilder<Occurrence>{
        private String occurrenceId;

        public GetRequestBuilder(String occurrenceId) {
            this.occurrenceId= occurrenceId;
        }

        @Override
        protected HttpUrl path() throws IOException {
            return buildUrl(Endpoint.API, "occurrences", occurrenceId);
        }

        @Override
        protected ResponseType<Occurrence> type() {
            return new ResponseType<>(Occurrence.class);
        }
    }
}
