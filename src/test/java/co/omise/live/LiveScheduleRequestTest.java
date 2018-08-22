package co.omise.live;

import co.omise.Client;
import co.omise.models.*;
import co.omise.models.schedules.*;
import co.omise.requests.Request;
import org.joda.time.DateTime;
import org.joda.time.DurationFieldType;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LiveScheduleRequestTest extends BaseLiveTest {

    private Client client;

    @Before
    public void setup() throws Exception {
        client = getLiveClient();
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveScheduleListGet() throws IOException, OmiseException {
        Request<ScopedList<Schedule>> request =
                new Schedule.ListRequestBuilder()
                        .build();

        ScopedList<Schedule> scheduleList = client.sendRequest(request);

        System.out.println("get schedule list: " + scheduleList.getTotal());

        assertNotNull(scheduleList);
        Schedule schedule= scheduleList.getData().get(0);
        assertNotNull(schedule);
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveListScheduleWithOption() throws IOException, OmiseException {
        ScopedList.Options options = new ScopedList.Options()
                .limit(3)
                .order(Ordering.Chronological);
        Request<ScopedList<Schedule>> request =
                new Schedule.ListRequestBuilder()
                        .options(options)
                        .build();
        ScopedList<Schedule> transactions = client.sendRequest(request);

        assertEquals(3, transactions.getLimit());
        assertEquals(Ordering.Chronological, transactions.getOrder());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveScheduleGet() throws IOException, OmiseException {
        Request<Token> tokenRequest = new Token.CreateRequestBuilder()
                .card(new Card.Create()
                        .name("testLiveSchedule")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2020))
                .build();

        Token token = client.sendRequest(tokenRequest);

        Request<Customer> customerRequest = new Customer.CreateRequestBuilder()
                .card(token.getId())
                .description("Test customer for scheduling")
                .email("chakrit@omise.co")
                .build();

        Customer customer = client.sendRequest(customerRequest);

        Request<Schedule> scheduleRequest = new Schedule.CreateRequestBuilder()
                .every(1)
                .period(SchedulePeriod.week)
                .on(new ScheduleOn.Params().weekdays(Weekdays.Friday))
                .endDate(DateTime.now().withFieldAdded(DurationFieldType.years(), 99))
                .charge(new ChargeScheduling.Params()
                        .customer(customer.getId())
                        .amount(2000)
                        .currency("THB")
                        .description("Monthly membership fee"))
                .build();

        Schedule createdSchedule = client.sendRequest(scheduleRequest);

        System.out.println("created schedule: " + createdSchedule.getId());

        Request<Schedule> getScheduleRequest = new Schedule.GetRequestBuilder(createdSchedule.getId()).build();

        Schedule schedule = client.sendRequest(getScheduleRequest);

        System.out.println("get schedule: " + schedule.getId());

        assertNotNull(schedule);
        assertEquals(ScheduleStatus.Active, schedule.getStatus());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveScheduleCreate() throws IOException, OmiseException {
        Request<Token> tokenRequest = new Token.CreateRequestBuilder()
                .card(new Card.Create()
                        .name("testLiveSchedule")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2020))
                .build();

        Token token = client.sendRequest(tokenRequest);

        Request<Customer> customerRequest = new Customer.CreateRequestBuilder()
                .card(token.getId())
                .description("Test customer for scheduling")
                .email("chakrit@omise.co")
                .build();

        Customer customer = client.sendRequest(customerRequest);

        Request<Schedule> scheduleRequest = new Schedule.CreateRequestBuilder()
                .every(1)
                .period(SchedulePeriod.week)
                .on(new ScheduleOn.Params().weekdays(Weekdays.Friday))
                .endDate(DateTime.now().withFieldAdded(DurationFieldType.years(), 99))
                .charge(new ChargeScheduling.Params()
                        .customer(customer.getId())
                        .amount(2000)
                        .currency("THB")
                        .description("Monthly membership fee"))
                .build();

        Schedule schedule = client.sendRequest(scheduleRequest);

        System.out.println("created schedule: " + schedule.getId());

        assertNotNull(schedule);
        assertEquals(1, schedule.getEvery());
        assertEquals(SchedulePeriod.week, schedule.getPeriod());
        assertEquals(customer.getId(), schedule.getCharge().getCustomer());

        ScopedList<Occurrence> list = client.schedule(schedule.getId()).occurrences().list();

        System.out.println("occurrences:");
        for (Occurrence occurrence : list.getData()) {
            System.out.println("- " + occurrence.getId());
        }
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveScheduleDelete() throws IOException, OmiseException {
        Request<Token> tokenRequest = new Token.CreateRequestBuilder()
                .card(new Card.Create()
                        .name("testLiveSchedule")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2020))
                .build();

        Token token = client.sendRequest(tokenRequest);

        Request<Customer> customerRequest = new Customer.CreateRequestBuilder()
                .card(token.getId())
                .description("Test customer for scheduling")
                .email("chakrit@omise.co")
                .build();

        Customer customer = client.sendRequest(customerRequest);

        Request<Schedule> scheduleRequest = new Schedule.CreateRequestBuilder()
                .every(1)
                .period(SchedulePeriod.week)
                .on(new ScheduleOn.Params().weekdays(Weekdays.Friday))
                .endDate(DateTime.now().withFieldAdded(DurationFieldType.years(), 99))
                .charge(new ChargeScheduling.Params()
                        .customer(customer.getId())
                        .amount(2000)
                        .currency("THB")
                        .description("Monthly membership fee"))
                .build();

        Schedule schedule = client.sendRequest(scheduleRequest);

        System.out.println("created schedule: " + schedule.getId());

        Request<Schedule> deleteScheduleRequest = new Schedule.DeleteRequestBuilder(schedule.getId()).build();

        Schedule deletedSchedule = client.sendRequest(deleteScheduleRequest);

        System.out.println("deleted schedule: " + schedule.getId());

        assertNotNull(deletedSchedule);
        assertEquals(ScheduleStatus.Deleted, deletedSchedule.getStatus());
    }
}
