package co.omise.models.schedules;

import co.omise.models.Model;
import co.omise.models.Params;
import co.omise.models.ScopedList;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Represents Omise Schedule object.
 *
 * @see <a href="https://www.omise.co/schedules-api">Schedule API</a>
 */
public class Schedule extends Model {
    private ScheduleStatus status;
    private int every;
    private SchedulePeriod period;
    private ScheduleOn on;
    @JsonProperty("in_words")
    private String inWords;
    @JsonProperty("start_date")
    private DateTime startDate;
    @JsonProperty("end_date")
    private DateTime endDate;

    private ChargeScheduling charge;
    private TransferScheduling transfer;

    private ScopedList<Occurrence> occurrences;
    @JsonProperty("next_occurrences")
    private ScopedList<Occurrence> nextOccurrences;

    @JsonProperty("next_occurrence_dates")
    private List<LocalDate> nextOccurrenceDates;

    public ScheduleStatus getStatus() {
        return status;
    }

    public void setStatus(ScheduleStatus status) {
        this.status = status;
    }

    public int getEvery() {
        return every;
    }

    public void setEvery(int every) {
        this.every = every;
    }

    public SchedulePeriod getPeriod() {
        return period;
    }

    public void setPeriod(SchedulePeriod period) {
        this.period = period;
    }

    public ScheduleOn getOn() {
        return on;
    }

    public void setOn(ScheduleOn on) {
        this.on = on;
    }

    public String getInWords() {
        return inWords;
    }

    public void setInWords(String inWords) {
        this.inWords = inWords;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public ChargeScheduling getCharge() {
        return charge;
    }

    public void setCharge(ChargeScheduling charge) {
        this.charge = charge;
    }

    public TransferScheduling getTransfer() {
        return transfer;
    }

    public void setTransfer(TransferScheduling transfer) {
        this.transfer = transfer;
    }

    public ScopedList<Occurrence> getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(ScopedList<Occurrence> occurrences) {
        this.occurrences = occurrences;
    }

    public ScopedList<Occurrence> getNextOccurrences() {
        return nextOccurrences;
    }

    public void setNextOccurrences(ScopedList<Occurrence> nextOccurrences) {
        this.nextOccurrences = nextOccurrences;
    }

    public List<LocalDate> getNextOccurrenceDates() { return this.nextOccurrenceDates; }

    public void setNextOccurrenceDates(List<LocalDate> nextOccurrenceDates) { this.nextOccurrenceDates = nextOccurrenceDates; }

    public static class Create extends Params {
        @JsonProperty
        private int every;
        @JsonProperty
        private SchedulePeriod period;
        @JsonProperty
        private ScheduleOn.Params on;
        @JsonProperty("start_date")
        private DateTime startDate;
        @JsonProperty("end_date")
        private DateTime endDate;
        @JsonProperty
        private ChargeScheduling.Params charge;
        @JsonProperty
        private TransferScheduling.Params transfer;

        public Create every(int units) {
            this.every = units;
            return this;
        }

        public Create period(SchedulePeriod period) {
            this.period = period;
            return this;
        }

        public Create startDate(DateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Create endDate(DateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Create on(ScheduleOn.Params on) {
            this.on = on;
            return this;
        }

        public Create charge(ChargeScheduling.Params charge) {
            this.charge = charge;
            return this;
        }
    }
}
