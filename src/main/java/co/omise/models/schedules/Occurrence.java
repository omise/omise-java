package co.omise.models.schedules;

import co.omise.Endpoint;
import co.omise.models.Model;
import co.omise.models.ScopedList;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;

import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * Occurrence object
 *
 * @see <a href="https://www.omise.co/occurrences-api">Occurrence API</a>
 */
public class Occurrence extends Model {
    private String location;
    private String message;
    @JsonProperty("processed_at")
    private ZonedDateTime processedAt;
    private String result;
    @JsonProperty("retry_on")
    private LocalDate retryOn;
    private String schedule;
    @JsonProperty("scheduled_on")
    private LocalDate scheduledOn;
    private OccurrenceStatus status;

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ZonedDateTime getProcessedAt() {
        return this.processedAt;
    }

    public void setProcessedAt(ZonedDateTime processedAt) {
        this.processedAt = processedAt;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDate getRetryOn() {
        return this.retryOn;
    }

    public void setRetryOn(LocalDate retryOn) {
        this.retryOn = retryOn;
    }

    public String getSchedule() {
        return this.schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public LocalDate getScheduledOn() {
        return this.scheduledOn;
    }

    public void setScheduledOn(LocalDate scheduledOn) {
        this.scheduledOn = scheduledOn;
    }

    public OccurrenceStatus getStatus() {
        return this.status;
    }

    public void setStatus(OccurrenceStatus status) {
        this.status = status;
    }

    public static class GetRequestBuilder extends RequestBuilder<Occurrence> {
        private final String occurrenceId;
        public GetRequestBuilder(String occurrenceId) {
            this.occurrenceId = occurrenceId;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "occurrences", occurrenceId);
        }

        @Override
        protected ResponseType<Occurrence> type() {
            return new ResponseType<>(Occurrence.class);
        }
    }

    public static class ListRequestBuilder extends RequestBuilder<ScopedList<Occurrence>> {
        private final String scheduleId;
        private ScopedList.Options options;
        public ListRequestBuilder(String scheduleId) {
            this.scheduleId = scheduleId;
        }

        @Override
        protected HttpUrl path() {
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

        public ListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }
}