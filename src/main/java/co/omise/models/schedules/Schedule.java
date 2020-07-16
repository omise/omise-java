package co.omise.models.schedules;

import co.omise.Endpoint;
import co.omise.models.Model;
import co.omise.models.ScopedList;
import co.omise.models.schedules.Schedule;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.util.List;

/**
 * Schedule object
 *
 * @see <a href="https://www.omise.co/schedules-api">Schedule API</a>
 */
public class Schedule extends Model {
    private boolean active;
    private ChargeSchedule charge;
    @JsonProperty("end_on")
    private LocalDate endDate;
    @JsonProperty("ended_at")
    private DateTime endedAt;
    private long every;
    @JsonProperty("in_words")
    private String inWords;
    private String location;
    @JsonProperty("next_occurrences_on")
    private List<String> nextOccurrencesDates;
    private ScopedList<Occurrence> occurrences;
    private ScheduleOn on;
    private SchedulePeriod period;
    @JsonProperty("start_on")
    private LocalDate startDate;
    private ScheduleStatus status;
    private TransferSchedule transfer;

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ChargeSchedule getCharge() {
        return this.charge;
    }

    public void setCharge(ChargeSchedule charge) {
        this.charge = charge;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public DateTime getEndedAt() {
        return this.endedAt;
    }

    public void setEndedAt(DateTime endedAt) {
        this.endedAt = endedAt;
    }

    public long getEvery() {
        return this.every;
    }

    public void setEvery(long every) {
        this.every = every;
    }

    public String getInWords() {
        return this.inWords;
    }

    public void setInWords(String inWords) {
        this.inWords = inWords;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getNextOccurrencesDates() {
        return this.nextOccurrencesDates;
    }

    public void setNextOccurrencesDates(List<String> nextOccurrencesDates) {
        this.nextOccurrencesDates = nextOccurrencesDates;
    }

    public ScopedList<Occurrence> getOccurrences() {
        return this.occurrences;
    }

    public void setOccurrences(ScopedList<Occurrence> occurrences) {
        this.occurrences = occurrences;
    }

    public ScheduleOn getOn() {
        return this.on;
    }

    public void setOn(ScheduleOn on) {
        this.on = on;
    }

    public SchedulePeriod getPeriod() {
        return this.period;
    }

    public void setPeriod(SchedulePeriod period) {
        this.period = period;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public ScheduleStatus getStatus() {
        return this.status;
    }

    public void setStatus(ScheduleStatus status) {
        this.status = status;
    }

    public TransferSchedule getTransfer() {
        return this.transfer;
    }

    public void setTransfer(TransferSchedule transfer) {
        this.transfer = transfer;
    }

    public static class DeleteRequestBuilder extends RequestBuilder<Schedule> {
        private String scheduleId;
        public DeleteRequestBuilder(String scheduleId) {
            this.scheduleId = scheduleId;
        }

        @Override
        protected String method() {
            return DELETE;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "schedules", scheduleId);
        }

        @Override
        protected ResponseType<Schedule> type() {
            return new ResponseType<>(Schedule.class);
        }
    }

    public static class GetRequestBuilder extends RequestBuilder<Schedule> {
        private String scheduleId;
        public GetRequestBuilder(String scheduleId) {
            this.scheduleId = scheduleId;
        }

        @Override
        protected String method() {
            return GET;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "schedules", scheduleId);
        }

        @Override
        protected ResponseType<Schedule> type() {
            return new ResponseType<>(Schedule.class);
        }
    }

    public static class ListRequestBuilder extends RequestBuilder<ScopedList<Schedule>> {
        private ScopedList.Options options;

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
                  .segments()
                  .params(options)
                  .build();
        }

        @Override
        protected ResponseType<ScopedList<Schedule>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Schedule>>() {});
        }

        public ListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }

    public static class CreateRequestBuilder extends RequestBuilder<Schedule> {

        @JsonProperty
        private ChargeSchedule.Params charge;
        @JsonProperty("end_date")
        private LocalDate endDate;
        @JsonProperty
        private long every;
        @JsonProperty
        private ScheduleOn.Params on;
        @JsonProperty
        private SchedulePeriod period;
        @JsonProperty("start_date")
        private LocalDate startDate;
        @JsonProperty
        private TransferSchedule.Params transfer;

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "schedules");
        }

        @Override
        protected ResponseType<Schedule> type() {
            return new ResponseType<>(Schedule.class);
        }

        public CreateRequestBuilder charge(ChargeSchedule.Params charge) {
            this.charge = charge;
            return this;
        }

        public CreateRequestBuilder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public CreateRequestBuilder every(long every) {
            this.every = every;
            return this;
        }

        public CreateRequestBuilder on(ScheduleOn.Params on) {
            this.on = on;
            return this;
        }

        public CreateRequestBuilder period(SchedulePeriod period) {
            this.period = period;
            return this;
        }

        public CreateRequestBuilder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public CreateRequestBuilder transfer(TransferSchedule.Params transfer) {
            this.transfer = transfer;
            return this;
        }

        @Override
        protected RequestBody payload() throws IOException  {
            return serialize();
        }
    }

    public static class ListOccurrencesRequestBuilder extends RequestBuilder<ScopedList<Occurrence>> {
        private String scheduleId;
        private ScopedList.Options options;
        public ListOccurrencesRequestBuilder(String scheduleId) {
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

        public ListOccurrencesRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }

    public static class CustomerScheduleListRequestBuilder extends RequestBuilder<ScopedList<Schedule>> {
        private String customerId;
        private ScopedList.Options options;
        public CustomerScheduleListRequestBuilder(String customerId) {
            this.customerId = customerId;
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
            return new HttpUrlBuilder(Endpoint.API, "customers", serializer())
                  .segments(customerId, "schedules")
                  .params(options)
                  .build();
        }

        @Override
        protected ResponseType<ScopedList<Schedule>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Schedule>>() {});
        }

        public CustomerScheduleListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }

    public static class RecipientScheduleListRequestBuilder extends RequestBuilder<ScopedList<Schedule>> {
        private String recipientId;
        private ScopedList.Options options;
        public RecipientScheduleListRequestBuilder(String recipientId) {
            this.recipientId = recipientId;
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
            return new HttpUrlBuilder(Endpoint.API, "recipients", serializer())
                  .segments(recipientId, "schedules")
                  .params(options)
                  .build();
        }

        @Override
        protected ResponseType<ScopedList<Schedule>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Schedule>>() {});
        }

        public RecipientScheduleListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }

    public static class TransferScheduleListRequestBuilder extends RequestBuilder<ScopedList<Schedule>> {
        private ScopedList.Options options;

        @Override
        protected String method() {
            return GET;
        }

        @Override
        protected HttpUrl path() {
            if (options == null) {
                options = new ScopedList.Options();
            }
            return new HttpUrlBuilder(Endpoint.API, "transfers", serializer())
                  .segments("schedules")
                  .params(options)
                  .build();
        }

        @Override
        protected ResponseType<ScopedList<Schedule>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Schedule>>() {});
        }

        public TransferScheduleListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }
}