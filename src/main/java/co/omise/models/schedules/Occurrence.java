package co.omise.models.schedules;

import co.omise.Endpoint;
import co.omise.models.Model;
import co.omise.models.ScopedList;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.io.IOException;

public class Occurrence extends Model {
    private String location;
    private String message;
    @JsonProperty("processed_at")
    private DateTime processedAt;
    private String result;
    @JsonProperty("retry_on")
    private LocalDate retryDate;
    private String schedule;
    @JsonProperty("scheduled_on")
    private LocalDate scheduleDate;
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

    public DateTime getProcessedAt() {
        return this.processedAt;
    }

    public void setProcessedAt(DateTime processedAt) {
        this.processedAt = processedAt;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDate getRetryDate() {
        return this.retryDate;
    }

    public void setRetryDate(LocalDate retryDate) {
        this.retryDate = retryDate;
    }

    public String getSchedule() {
        return this.schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public LocalDate getScheduleDate() {
        return this.scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public OccurrenceStatus getStatus() {
        return this.status;
    }

    public void setStatus(OccurrenceStatus status) {
        this.status = status;
    }

    public static class GetRequestBuilder extends RequestBuilder<Occurrence> {
        private String occurrenceId;
        public GetRequestBuilder(String occurrenceId) {
            this.occurrenceId = occurrenceId;
        }

        @Override
        protected String method() {
            return GET;
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
        private String scheduleId;
        private ScopedList.Options options;
        public ListRequestBuilder(String scheduleId) {
            this.scheduleId = scheduleId;
        }

        @Override
        protected String method() {
            return GET;
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