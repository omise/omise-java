package co.omise.resources;

import okhttp3.OkHttpClient;

public class ScheduleSpecificResource extends Resource {
    private final String scheduleId;

    public ScheduleSpecificResource(OkHttpClient httpClient, String scheduleId) {
        super(httpClient);
        this.scheduleId = scheduleId;
    }

    public String scheduleId() {
        return scheduleId;
    }

    public ScheduleOccurrenceResource occurrences() {
        return new ScheduleOccurrenceResource(httpClient(), scheduleId);
    }
}
