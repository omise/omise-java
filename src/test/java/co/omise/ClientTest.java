package co.omise;

import co.omise.models.*;
import co.omise.models.schedules.*;
import org.joda.time.DateTime;
import org.joda.time.DurationFieldType;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class ClientTest extends OmiseTest {
    private static final String LIVETEST_PKEY = "pkey_test_replaceme";
    private static final String LIVETEST_SKEY = "skey_test_replaceme";

    @Test
    public void testCtor() throws ClientException {
        try {
            new Client(null);
            fail("exception expected");
        } catch (NullPointerException e) {
        }
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveErrorVault() throws ClientException, IOException {
        try {
            Token.Create creation = new Token.Create().card(new Card.Create()
                    .name("Omise Co., Ltd.")
                    .number("4242424242424242")
                    .expiration(10, 2020)
                    .securityCode("123")
            );

            new Client("pkey_test_123", "skey_test_123").tokens().create(creation);
        } catch (OmiseException e) {
            assertEquals("authentication_failure", e.getCode());
        }
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveTransfer() throws ClientException, IOException, OmiseException {
        Client client = new Client("pkey_test_55m9sc46dt7wequrp3j", "skey_test_55m9sazu79b5ir95ced");
        Recipient recipient = client.recipients().create(new Recipient.Create()
                .name("Omise-Java Recipient")
                .email("support@omise.co")
                .type(RecipientType.Individual)
                .bankAccount(new BankAccount.Params()
                        .name("Omise-Java Bank")
                        .number("7772-727-272")
                        .brand("kbank")));
        System.out.println("created recipient: " + recipient.getId());

        Transfer transfer = client.transfers().create(new Transfer.Create()
                .recipient(recipient.getId())
                .amount(10000));
        System.out.println("created transfer: " + transfer.getId());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveError() throws ClientException, IOException {
        try {
            new Client("skey_test_123").account().get();
        } catch (OmiseException e) {
            assertEquals("authentication_failure", e.getCode());
        }
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveCard() throws ClientException, IOException, OmiseException {
        ScopedList<Card> list = liveTestClient()
                .customer("cust_test_5665swqhhb3mioax1y7")
                .cards()
                .list();

        for (Card card : list.getData()) {
            System.out.println(card.getId() + " : " + card.getLastDigits());
        }
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveCharge() throws ClientException, IOException, OmiseException {
        Client client = liveTestClient();
        Token token = client.tokens().create(new Token.Create()
                .card(new Card.Create()
                        .name("Omise Co., Ltd. - testLiveCharge")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2020)));

        Charge charge = client.charges().create(new Charge.Create()
                .amount(2000) // $20
                .currency("usd")
                .description("omise-java test")
                .card(token.getId()));

        charge = client.charges().update(charge.getId(), new Charge.Update()
                .description("omise-java test charge")
                .metadata("test-date", DateTime.now().toString())
                .metadata("library", "omise-java")
        );

        System.out.println("created charge: " + charge.getId());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSchedule() throws ClientException, IOException, OmiseException {
        Client client = liveTestClient();
        Token token = client.tokens().create(new Token.Create()
                .card(new Card.Create()
                        .name("testLiveSchedule")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2020)));

        Customer customer = client.customers().create(new Customer.Create()
                .card(token.getId())
                .description("Test customer for scheduling")
                .email("chakrit@omise.co"));

        Schedule schedule = client.schedules().create(new Schedule.Create()
                .every(1)
                .period(SchedulePeriod.week)
                .endDate(DateTime.now().withFieldAdded(DurationFieldType.years(), 99))
                .on(new ScheduleOn.Params()
                        .weekdays(Weekdays.Friday))
                .charge(new ChargeScheduling.Params()
                        .amount(2000)
                        .currency("THB")
                        .customer(customer.getId())));

        System.out.println("created schedule: " + schedule.getId());

        ScopedList<Occurrence> list = client.schedule(schedule.getId()).occurrences().list();

        System.out.println("occurrences:");
        for (Occurrence occurrence : list.getData()) {
            System.out.println("- " + occurrence.getId());
        }
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveFetch() throws ClientException, IOException, OmiseException {
        Balance balance = liveTestClient().balance().get();
        assertTrue(balance.getTotal() > 100000);

        System.out.println("current balance: " + Long.toString(balance.getTotal()));
    }

    private Client liveTestClient() throws ClientException {
        return new Client(LIVETEST_PKEY, LIVETEST_SKEY);
    }
}
