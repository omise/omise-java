package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import co.omise.models.schedules.Occurrence;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class ScheduleOccurrenceResource extends Resource {
    private final String scheduleId;

    protected ScheduleOccurrenceResource(OkHttpClient httpClient, String scheduleId) {
        super(httpClient);
        this.scheduleId = scheduleId;
    }

    public String scheduleId() {
        return scheduleId;
    }

    public ScopedList<Occurrence> list() throws IOException, OmiseException {
        return list(new ScopedList.Options());
    }

    public ScopedList<Occurrence> list(ScopedList.Options options) throws IOException, OmiseException {
        return httpGet(collectionUrl()).params(options).returns(new TypeReference<ScopedList<Occurrence>>() {
        });
    }

    private HttpUrl collectionUrl() {
        return buildUrl(Endpoint.API, "schedules", scheduleId, "occurrences");
    }
}
