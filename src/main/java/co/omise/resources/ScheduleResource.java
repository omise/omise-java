package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.OmiseException;
import co.omise.models.schedules.Schedule;
import co.omise.models.ScopedList;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class ScheduleResource extends Resource {
    public ScheduleResource(OkHttpClient httpClient) {
        super(httpClient);
    }

    public ScopedList<Schedule> list() throws IOException, OmiseException {
        return list(new ScopedList.Options());
    }

    public ScopedList<Schedule> list(ScopedList.Options options) throws IOException, OmiseException {
        return httpGet(urlFor("")).params(options).returns(new TypeReference<ScopedList<Schedule>>() {
        });
    }

    public Schedule get(String scheduleId) throws IOException, OmiseException {
        return httpGet(urlFor(scheduleId)).returns(Schedule.class);
    }

    public Schedule create(Schedule.Create params) throws IOException, OmiseException {
        return httpPost(urlFor("")).params(params).returns(Schedule.class);
    }

    public Schedule destroy(String scheduleId) throws IOException, OmiseException {
        return httpDelete(urlFor(scheduleId)).returns(Schedule.class);
    }

    private HttpUrl urlFor(String scheduleId) {
        return buildUrl(Endpoint.API, "schedules", scheduleId);
    }

    private HttpUrl urlFor(String scheduleId, String resource) {
        return buildUrl(Endpoint.API, "schedules", scheduleId, resource);
    }
}
