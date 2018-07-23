package co.omise;

import co.omise.models.*;
import co.omise.models.schedules.*;
import co.omise.requests.Request;
import org.joda.time.DateTime;
import org.joda.time.DurationFieldType;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class ClientTest extends OmiseTest {
    private static final String LIVETEST_PKEY = "pkey_test_replaceme";
    private static final String LIVETEST_SKEY = "skey_test_replaceme";
    private static final String LIVETEST_CUST = "cust_test_replaceme";
    private static final String LIVETEST_DIPUTE = "dspt_test_replaceme";

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveErrorVault() throws ClientException {
        try {
            Request<Token> request = new Token.CreateRequestBuilder()
                    .card(new Card.Create()
                            .name("Omise Co., Ltd.")
                            .number("4242424242424242")
                            .expiration(10, 2020)
                            .securityCode("123"))
                    .build();

            new Client("pkey_test_123", "skey_test_123").sendRequest(request, Token.class);
        } catch (OmiseException e) {
            assertEquals("authentication_failure", e.getCode());
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveError() throws ClientException, IOException {
        try {
            Request<Account> getAccountRequest = new Account.GetRequestBuilder().build();
            new Client("skey_test_123").sendRequest(getAccountRequest, Account.class);
        } catch (OmiseException e) {
            assertEquals("authentication_failure", e.getCode());
        }
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSchedule() throws ClientException, IOException, OmiseException {
        Client client = liveTestClient();

        Request<Token> request = new Token.CreateRequestBuilder()
                .card(new Card.Create()
                        .name("testLiveSchedule")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2020))
                .build();

        Token token = client.sendRequest(request, Token.class);

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
    public void testLiveSourceInternetBankingBay() throws ClientException, IOException, OmiseException {
        Source source = liveTestClient().sources().create(new Source.Create()
                .type(SourceType.InternetBankingBay)
                .amount(10000)
                .currency("thb"));

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("internet_banking_bay", source.getType().toString());
        assertEquals("redirect", source.getFlow().toString());
        assertEquals(10000L, source.getAmount());
        assertEquals("thb", source.getCurrency());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceInternetBankingKtb() throws ClientException, IOException, OmiseException {
        Source source = liveTestClient().sources().create(new Source.Create()
                .type(SourceType.InternetBankingKtb)
                .amount(10000)
                .currency("thb"));

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("internet_banking_ktb", source.getType().toString());
        assertEquals("redirect", source.getFlow().toString());
        assertEquals(10000L, source.getAmount());
        assertEquals("thb", source.getCurrency());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceInternetBankingScb() throws ClientException, IOException, OmiseException {
        Source source = liveTestClient().sources().create(new Source.Create()
                .type(SourceType.InternetBankingScb)
                .amount(10000)
                .currency("thb"));

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("internet_banking_scb", source.getType().toString());
        assertEquals("redirect", source.getFlow().toString());
        assertEquals(10000L, source.getAmount());
        assertEquals("thb", source.getCurrency());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceInternetBankingBbl() throws ClientException, IOException, OmiseException {
        Source source = liveTestClient().sources().create(new Source.Create()
                .type(SourceType.InternetBankingBbl)
                .amount(10000)
                .currency("thb"));

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("internet_banking_bbl", source.getType().toString());
        assertEquals("redirect", source.getFlow().toString());
        assertEquals(10000L, source.getAmount());
        assertEquals("thb", source.getCurrency());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceBillPayment() throws ClientException, IOException, OmiseException {
        Source source = liveTestClient().sources().create(new Source.Create()
                .type(SourceType.BillPaymentTescoLotus)
                .amount(10000)
                .currency("thb"));

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("bill_payment_tesco_lotus", source.getType().toString());
        assertEquals("offline", source.getFlow().toString());
        assertNotNull(source.getType());
        assertEquals(10000L, source.getAmount());
        assertEquals("thb", source.getCurrency());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceAlipay() throws ClientException, IOException, OmiseException {
        Source source = liveTestClient().sources().create(new Source.Create()
                .type(SourceType.Alipay)
                .amount(10000)
                .currency("thb"));

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("alipay", source.getType().toString());
        assertEquals("redirect", source.getFlow().toString());
        assertEquals(10000L, source.getAmount());
        assertEquals("thb", source.getCurrency());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceBarcodeAlipay() throws ClientException, IOException, OmiseException {
        Source source = liveTestClient().sources().create(new Source.Create()
                .type(SourceType.BarcodeAlipay)
                .amount(200000)
                .currency("thb")
                .description("barcode alipay charge")
                .barcode("1234567890")
                .storeId("store_1")
                .storeName("store 1")
                .terminalId("POS-01"));

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("barcode_alipay", source.getType().toString());
        assertEquals("offline", source.getFlow().toString());
        assertEquals(200000, source.getAmount());
        assertEquals("thb", source.getCurrency());
        assertEquals("1234567890", source.getBarcode());
        assertEquals("store_1", source.getStoreId());
        assertEquals("store 1", source.getStoreName());
        assertEquals("POS-01", source.getTerminalId());
    }

    private Client liveTestClient() throws ClientException {
        return new Client(LIVETEST_PKEY, LIVETEST_SKEY);
    }
}
