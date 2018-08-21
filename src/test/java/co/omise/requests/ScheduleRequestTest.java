package co.omise.requests;

import co.omise.models.OmiseException;
import co.omise.models.schedules.ChargeScheduling;
import co.omise.models.schedules.Schedule;
import co.omise.models.schedules.ScheduleOn;
import co.omise.models.schedules.SchedulePeriod;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.IOException;

public class ScheduleRequestTest extends RequestTest {

    @Test
    public void testGet() {

    }

    @Test
    public void testList() {

    }

    @Test
    public void testCreate() throws IOException, OmiseException {
        Request<Schedule> request = new Schedule.CreateRequestBuilder()
                .every(1)
                .period(SchedulePeriod.month)
                .on(new ScheduleOn.Params().daysOfMonth(2))
                .startDate(DateTime.parse("2017-04-27"))
                .endDate(DateTime.parse("2018-04-27"))
                .charge(new ChargeScheduling.Params()
                        .customer("cust_test_55bb3hkywglfyyachha")
                        .amount(88800)
                        .description("Monthly membership fee"))
                .build();

        Schedule schedule = getTestRequester().sendRequest(request);

        assertRequested("POST", "/schedules", 200);

        assertEquals("schd_test_57s33hm9fg1pzcqihxs", schedule.getId());
        assertEquals(1, schedule.getEvery());
        assertEquals(SchedulePeriod.month, schedule.getPeriod());
        assertEquals(11, schedule.getNextOccurrenceDates().size());
    }

    @Test
    public void testDelete() {

    }
}
