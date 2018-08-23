package co.omise.requests;

import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import co.omise.models.schedules.*;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ScheduleRequestTest extends RequestTest {

    private final String SCHEDULE_ID = "schd_test_57s33hm9fg1pzcqihxs";
    
    private Requester requester;

    @Before
    public void setUp() {
        requester = getTestRequester();
    }

    @Test
    public void testGet() throws IOException, OmiseException {
        Request<Schedule> request = new Schedule.GetRequestBuilder(SCHEDULE_ID).build();

        Schedule schedule = requester.sendRequest(request);

        assertRequested("GET", "/schedules/" + SCHEDULE_ID, 200);

        assertEquals(SCHEDULE_ID, schedule.getId());
        assertEquals(ScheduleStatus.Active, schedule.getStatus());
        assertEquals(1, schedule.getEvery());
        assertEquals(SchedulePeriod.month, schedule.getPeriod());
        assertEquals(11, schedule.getNextOccurrenceDates().size());
    }

    @Test
    public void testList() throws IOException, OmiseException {
        Request<ScopedList<Schedule>> request = new Schedule.ListRequestBuilder().build();

        ScopedList<Schedule> scheduleList = requester.sendRequest(request);

        assertRequested("GET", "/schedules", 200);
        assertEquals(11, scheduleList.getTotal());
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

        Schedule schedule = requester.sendRequest(request);

        assertRequested("POST", "/schedules", 200);

        assertEquals(SCHEDULE_ID, schedule.getId());
        assertEquals(1, schedule.getEvery());
        assertEquals(SchedulePeriod.month, schedule.getPeriod());
        assertEquals(11, schedule.getNextOccurrenceDates().size());
    }

    @Test
    public void testDelete() throws IOException, OmiseException {
        Request<Schedule> request = new Schedule.DeleteRequestBuilder(SCHEDULE_ID).build();

        Schedule schedule = requester.sendRequest(request);

        assertRequested("DELETE", "/schedules/" + SCHEDULE_ID, 200);

        assertEquals(SCHEDULE_ID, schedule.getId());
        assertEquals(ScheduleStatus.Deleted, schedule.getStatus());
    }

    @Test
    public void testChargeScheduleList() throws IOException, OmiseException {
        Request<ScopedList<Schedule>> request = new Schedule.ChargeScheduleListRequestBuilder().build();

        ScopedList<Schedule> schedules = requester.sendRequest(request);

        assertRequested("GET", "/charges/schedules", 200);
        assertEquals(5, schedules.getTotal());
    }

    @Test
    public void testCustomerScheduleList() throws IOException, OmiseException {
        String customerId = "cust_test_4yq6txdpfadhbaqnwp3";
        Request<ScopedList<Schedule>> request = new Schedule.CustomerScheduleListRequestBuilder(customerId).build();

        ScopedList<Schedule> schedules = requester.sendRequest(request);

        assertRequested("GET", "/customers/" + customerId + "/schedules", 200);
        assertEquals(2, schedules.getTotal());
    }
}
