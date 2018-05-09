package co.omise;

import co.omise.models.*;
import co.omise.models.schedules.*;
import org.joda.time.DateTime;
import org.joda.time.DurationFieldType;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ClientTest extends OmiseTest {
    private static final String LIVETEST_PKEY = "pkey_test_replaceme";
    private static final String LIVETEST_SKEY = "skey_test_replaceme";
    private static final String LIVETEST_CUST = "cust_test_replaceme";

    @Test(expected = NullPointerException.class)
    public void testCreator() throws ClientException {
        new Client(null);
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
        Client client = new Client(LIVETEST_PKEY, LIVETEST_SKEY);
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("description", "DESCRIPTION");
        metadata.put("invoice_id", "inv_N1ayTWJ2FV");
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
                .amount(10000)
                .metadata(metadata));
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
                .customer(LIVETEST_CUST)
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
    public void testLiveSourceVirtualAccount() throws ClientException, IOException, OmiseException {
        Source source = liveTestClient().sources().create(new Source.Create()
                .type(SourceType.VirtualAccountSinarmas)
                .amount(1100000)
                .currency("idr"));

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("virtual_account_sinarmas", source.getType().toString());
        assertEquals("offline", source.getFlow().toString());
        assertEquals(1100000L, source.getAmount());
        assertEquals("idr", source.getCurrency());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeWithInternetBanking() throws ClientException, IOException, OmiseException {
        Source source = liveTestClient().sources().create(new Source.Create()
                .type(SourceType.InternetBankingBay)
                .amount(10000)
                .currency("thb"));

        Charge charge = liveTestClient().charges().create(new Charge.Create()
                .source(source.getId())
                .amount(10000)
                .currency("thb")
                .returnUri("http://example.com/orders/345678/complete"));

        System.out.println("created charge: " + charge.getId());

        assertNotNull(charge.getId());
        assertEquals(SourceType.InternetBankingBay, charge.getSource().getType());
        assertEquals(FlowType.Redirect, charge.getSource().getFlow());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeWithBillPaymentTescoLotus() throws ClientException, IOException, OmiseException {
        Source source = liveTestClient().sources().create(new Source.Create()
                .type(SourceType.BillPaymentTescoLotus)
                .amount(10000)
                .currency("thb"));

        Charge charge = liveTestClient().charges().create(new Charge.Create()
                .source(source.getId())
                .amount(10000)
                .currency("thb")
                .returnUri("http://example.com/orders/345678/complete"));

        System.out.println("created charge: " + charge.getId());

        assertNotNull(charge.getId());
        assertEquals(SourceType.BillPaymentTescoLotus, charge.getSource().getType());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeWithAlipay() throws ClientException, IOException, OmiseException {
        Source source = liveTestClient().sources().create(new Source.Create()
                .type(SourceType.Alipay)
                .amount(10000)
                .currency("thb"));

        Charge charge = liveTestClient().charges().create(new Charge.Create()
                .source(source.getId())
                .amount(10000)
                .currency("thb")
                .returnUri("http://example.com/orders/345678/complete"));

        System.out.println("created charge: " + charge.getId());

        assertNotNull(charge.getId());
        assertEquals(SourceType.Alipay, charge.getSource().getType());
        assertEquals(FlowType.Redirect, charge.getSource().getFlow());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceVirtualAccountCharge() throws ClientException, IOException, OmiseException {
        Source source = liveTestClient().sources().create(new Source.Create()
                .type(SourceType.VirtualAccountSinarmas)
                .amount(1100000)
                .currency("idr"));

        Charge charge = liveTestClient().charges().create(new Charge.Create()
                .source(source.getId())
                .amount(1100000)
                .currency("idr"));

        System.out.println("created charge: " + charge.getId());

        assertNotNull(charge.getId());
        assertNotNull(charge.getSource());
        assertNotNull(charge.getSource().getReferences());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceWalletAlipay() throws ClientException, IOException, OmiseException {
        Source source = liveTestClient().sources().create(new Source.Create()
                .type(SourceType.WalletAlipay)
                .amount(200000)
                .currency("thb")
                .description("wallet alipay charge")
                .barcode("1234567890")
                .storeId("store_1")
                .storeName("store 1")
                .terminalId("POS-01"));

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("wallet_alipay", source.getType().toString());
        assertEquals("offline", source.getFlow().toString());
        assertEquals(200000, source.getAmount());
        assertEquals("thb", source.getCurrency());
        assertEquals("1234567890", source.getBarcode());
        assertEquals("store_1", source.getStoreId());
        assertEquals("store 1", source.getStoreId());
        assertEquals("POS-01", source.getTerminalId());
    }

    private Client liveTestClient() throws ClientException {
        return new Client(LIVETEST_PKEY, LIVETEST_SKEY);
    }
}
