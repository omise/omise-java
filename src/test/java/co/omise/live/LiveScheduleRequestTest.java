package co.omise.live;

import co.omise.Client;
import co.omise.models.*;
import co.omise.models.schedules.*;
import co.omise.requests.Request;
import org.joda.time.LocalDate;
import org.joda.time.DurationFieldType;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

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
        Schedule schedule = scheduleList.getData().get(0);
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
                .endDate(LocalDate.now().withFieldAdded(DurationFieldType.years(), 99))
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
        assertEquals(ScheduleStatus.Running, schedule.getStatus());
        assertTrue(schedule.isActive());
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
                .endDate(LocalDate.now().withFieldAdded(DurationFieldType.years(), 99))
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
                .endDate(LocalDate.now().withFieldAdded(DurationFieldType.years(), 99))
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

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeScheduleListGet() throws IOException, OmiseException {
        Request<ScopedList<Schedule>> request = new Schedule.ChargeScheduleListRequestBuilder().build();

        ScopedList<Schedule> scheduleList = client.sendRequest(request);

        System.out.println("get charge schedule list: " + scheduleList.getTotal());

        assertNotNull(scheduleList);
        Schedule schedule = scheduleList.getData().get(0);
        assertNotNull(schedule);
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveListChargeScheduleWithOption() throws IOException, OmiseException {
        ScopedList.Options options = new ScopedList.Options()
                .limit(3)
                .order(Ordering.Chronological);
        Request<ScopedList<Schedule>> request =
                new Schedule.ChargeScheduleListRequestBuilder()
                        .options(options)
                        .build();
        ScopedList<Schedule> transactions = client.sendRequest(request);

        assertEquals(3, transactions.getLimit());
        assertEquals(Ordering.Chronological, transactions.getOrder());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveCustomerScheduleListGet() throws IOException, OmiseException {
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
                .email("johndoe@omise.co")
                .build();

        Customer customer = client.sendRequest(customerRequest);

        Request<Schedule> scheduleRequest = new Schedule.CreateRequestBuilder()
                .every(1)
                .period(SchedulePeriod.week)
                .on(new ScheduleOn.Params().weekdays(Weekdays.Friday))
                .endDate(LocalDate.now().withFieldAdded(DurationFieldType.years(), 1))
                .charge(new ChargeScheduling.Params()
                        .customer(customer.getId())
                        .amount(2000)
                        .currency("THB")
                        .description("Monthly membership fee"))
                .build();

        Schedule createdSchedule = client.sendRequest(scheduleRequest);

        Request<ScopedList<Schedule>> request = new Schedule.CustomerScheduleListRequestBuilder(customer.getId()).build();

        ScopedList<Schedule> scheduleList = client.sendRequest(request);

        System.out.println("get customer schedule list: " + scheduleList.getTotal());

        assertNotNull(scheduleList);
        Schedule actualSchedule = scheduleList.getData().get(0);
        assertEquals(createdSchedule.getId(), actualSchedule.getId());
        assertEquals(customer.getId(), actualSchedule.getCharge().getCustomer());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveCustomerScheduleListWithOption() throws IOException, OmiseException {
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
                .email("johndoe@omise.co")
                .build();

        Customer customer = client.sendRequest(customerRequest);

        Request<Schedule> scheduleRequest = new Schedule.CreateRequestBuilder()
                .every(1)
                .period(SchedulePeriod.week)
                .on(new ScheduleOn.Params().weekdays(Weekdays.Friday))
                .endDate(LocalDate.now().withFieldAdded(DurationFieldType.years(), 1))
                .charge(new ChargeScheduling.Params()
                        .customer(customer.getId())
                        .amount(2000)
                        .currency("THB")
                        .description("Monthly membership fee"))
                .build();

        Schedule createdSchedule = client.sendRequest(scheduleRequest);

        ScopedList.Options options = new ScopedList.Options()
                .limit(10)
                .order(Ordering.Chronological);
        Request<ScopedList<Schedule>> request = new Schedule.CustomerScheduleListRequestBuilder(customer.getId())
                .options(options)
                .build();

        ScopedList<Schedule> scheduleList = client.sendRequest(request);

        System.out.println("get customer schedule list: " + scheduleList.getTotal());

        assertNotNull(scheduleList);
        assertEquals(10, scheduleList.getLimit());
        assertEquals(Ordering.Chronological, scheduleList.getOrder());
        Schedule actualSchedule = scheduleList.getData().get(0);
        assertEquals(createdSchedule.getId(), actualSchedule.getId());
        assertEquals(customer.getId(), actualSchedule.getCharge().getCustomer());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveTransferScheduleList() throws IOException, OmiseException {
        Request<Recipient> recipientRequest = new Recipient.CreateRequestBuilder()
                .name("John Doe")
                .email("john.doe@example.com")
                .description("Default recipient")
                .type(RecipientType.Individual)
                .bankAccount(new BankAccount.Params()
                        .brand("kbank")
                        .number("1234567890")
                        .name("SOMCHAI PRASERT"))
                .build();
        Recipient recipient = client.sendRequest(recipientRequest);

        Request<Schedule> scheduleRequest = new Schedule.CreateRequestBuilder()
                .every(1)
                .period(SchedulePeriod.week)
                .on(new ScheduleOn.Params().weekdays(Weekdays.Friday))
                .endDate(LocalDate.now().withFieldAdded(DurationFieldType.years(), 1))
                .transfer(new TransferScheduling.Params()
                        .amount(2000L)
                        .recipient(recipient.getId())
                )
                .build();
        client.sendRequest(scheduleRequest);

        Request<ScopedList<Schedule>> transferScheduleListRequest = new Schedule.TransferScheduleListRequestBuilder().build();
        ScopedList<Schedule> transferScheduleList = client.sendRequest(transferScheduleListRequest);

        System.out.println("get transfer schedule list: " + transferScheduleList.getTotal());

        assertNotNull(transferScheduleList);
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveTransferScheduleListWithOption() throws IOException, OmiseException {
        Request<Recipient> recipientRequest = new Recipient.CreateRequestBuilder()
                .name("John Doe")
                .email("john.doe@example.com")
                .description("Default recipient")
                .type(RecipientType.Individual)
                .bankAccount(new BankAccount.Params()
                        .brand("kbank")
                        .number("1234567890")
                        .name("SOMCHAI PRASERT"))
                .build();
        Recipient recipient = client.sendRequest(recipientRequest);

        Request<Schedule> scheduleRequest = new Schedule.CreateRequestBuilder()
                .every(1)
                .period(SchedulePeriod.week)
                .on(new ScheduleOn.Params().weekdays(Weekdays.Friday))
                .endDate(LocalDate.now().withFieldAdded(DurationFieldType.years(), 1))
                .transfer(new TransferScheduling.Params()
                        .amount(2000L)
                        .recipient(recipient.getId())
                )
                .build();
        client.sendRequest(scheduleRequest);

        ScopedList.Options options = new ScopedList.Options()
                .limit(10)
                .order(Ordering.Chronological);
        Request<ScopedList<Schedule>> transferScheduleListRequest = new Schedule.TransferScheduleListRequestBuilder()
                .options(options)
                .build();
        ScopedList<Schedule> transferScheduleList = client.sendRequest(transferScheduleListRequest);


        System.out.println("get transfer schedule list: " + transferScheduleList.getTotal());

        assertNotNull(transferScheduleList);
        assertEquals(10, transferScheduleList.getLimit());
        assertEquals(Ordering.Chronological, transferScheduleList.getOrder());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveRecipientScheduleList() throws IOException, OmiseException {
        Request<Recipient> recipientRequest = new Recipient.CreateRequestBuilder()
                .name("John Doe")
                .email("john.doe@example.com")
                .description("Default recipient")
                .type(RecipientType.Individual)
                .bankAccount(new BankAccount.Params()
                        .brand("kbank")
                        .number("1234567890")
                        .name("SOMCHAI PRASERT"))
                .build();
        Recipient recipient = client.sendRequest(recipientRequest);

        Request<Schedule> scheduleRequest = new Schedule.CreateRequestBuilder()
                .every(1)
                .period(SchedulePeriod.week)
                .on(new ScheduleOn.Params().weekdays(Weekdays.Friday))
                .endDate(LocalDate.now().withFieldAdded(DurationFieldType.years(), 1))
                .transfer(new TransferScheduling.Params()
                        .amount(2000L)
                        .recipient(recipient.getId())
                )
                .build();
        client.sendRequest(scheduleRequest);

        Request<ScopedList<Schedule>> recipientScheduleListRequest = new Schedule.RecipientScheduleListRequestBuilder(recipient.getId())
                .build();
        ScopedList<Schedule> recipientScheduleList = client.sendRequest(recipientScheduleListRequest);


        System.out.println("get recipient schedule list: " + recipientScheduleList.getTotal());

        assertNotNull(recipientScheduleList);
        Schedule recipientSchedule = recipientScheduleList.getData().get(0);
        assertEquals(recipient.getId(), recipientSchedule.getTransfer().getRecipient());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveRecipientScheduleListWithOption() throws IOException, OmiseException {
        Request<Recipient> recipientRequest = new Recipient.CreateRequestBuilder()
                .name("John Doe")
                .email("john.doe@example.com")
                .description("Default recipient")
                .type(RecipientType.Individual)
                .bankAccount(new BankAccount.Params()
                        .brand("kbank")
                        .number("1234567890")
                        .name("SOMCHAI PRASERT"))
                .build();
        Recipient recipient = client.sendRequest(recipientRequest);

        Request<Schedule> scheduleRequest = new Schedule.CreateRequestBuilder()
                .every(1)
                .period(SchedulePeriod.week)
                .on(new ScheduleOn.Params().weekdays(Weekdays.Friday))
                .endDate(LocalDate.now().withFieldAdded(DurationFieldType.years(), 1))
                .transfer(new TransferScheduling.Params()
                        .amount(2000L)
                        .recipient(recipient.getId())
                )
                .build();
        client.sendRequest(scheduleRequest);

        ScopedList.Options options = new ScopedList.Options()
                .limit(10)
                .order(Ordering.Chronological);
        Request<ScopedList<Schedule>> recipientScheduleListRequest = new Schedule.RecipientScheduleListRequestBuilder(recipient.getId())
                .options(options)
                .build();
        ScopedList<Schedule> recipientScheduleList = client.sendRequest(recipientScheduleListRequest);


        System.out.println("get recipient schedule list: " + recipientScheduleList.getTotal());

        assertNotNull(recipientScheduleList);
        assertEquals(10, recipientScheduleList.getLimit());
        assertEquals(Ordering.Chronological, recipientScheduleList.getOrder());
    }
}
