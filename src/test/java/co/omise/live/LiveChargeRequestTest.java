package co.omise.live;

import co.omise.Client;
import co.omise.models.*;
import co.omise.requests.Request;
import java.time.ZonedDateTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertFalse;

public class LiveChargeRequestTest extends BaseLiveTest {
    private Client client;

    @Before
    public void setup() throws Exception {
        client = getLiveClient();
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveGetCharge() throws IOException, OmiseException {
        Request<Token> tokenRequest = new Token.CreateRequestBuilder()
                .card(new Card.Create()
                        .name("Omise Co., Ltd. - testLiveCharge")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2020))
                .build();

        Token token = client.sendRequest(tokenRequest);

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(2000) // $20
                        .currency("usd")
                        .description("omise-java test")
                        .card(token.getId())
                        .build();
        Charge createdCharge = client.sendRequest(createChargeRequest);

        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder(createdCharge.getId()).build();
        Charge actualCharge = client.sendRequest(getChargeRequest);


        System.out.println("Retrieved charge: " + actualCharge.getId());

        assertEquals(createdCharge.getId(), actualCharge.getId());
    }


    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveCreateCharge() throws IOException, OmiseException {
        Request<Token> tokenRequest = new Token.CreateRequestBuilder()
                .card(new Card.Create()
                        .name("Omise Co., Ltd. - testLiveCharge")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2020))
                .build();

        Token token = client.sendRequest(tokenRequest);

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(2000) // $20
                        .currency("usd")
                        .description("omise-java test")
                        .card(token.getId())
                        .build();

        Charge charge = client.sendRequest(createChargeRequest);

        System.out.println("created charge: " + charge.getId());

        assertNotNull(charge.getId());
        assertEquals(2000, charge.getAmount());
        assertEquals("USD", charge.getCurrency());
        assertEquals(token.getCard().getLastDigits(), charge.getCard().getLastDigits());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeWithInternetBanking() throws IOException, OmiseException {
        Request<Source> sourceRequest = new Source.CreateRequestBuilder()
                .type(SourceType.InternetBankingBay)
                .amount(10000)
                .currency("thb")
                .build();

        Source source = client.sendRequest(sourceRequest);

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .source(source.getId())
                        .amount(10000)
                        .currency("thb")
                        .returnUri("http://example.com/orders/345678/complete")
                        .build();

        Charge charge = client.sendRequest(createChargeRequest);

        System.out.println("created charge: " + charge.getId());

        assertNotNull(charge.getId());
        assertEquals(10000, charge.getAmount());
        assertEquals("THB", charge.getCurrency());
        assertEquals(SourceType.InternetBankingBay, charge.getSource().getType());
        assertEquals(FlowType.Redirect, charge.getSource().getFlow());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeWithBillPaymentTescoLotus() throws IOException, OmiseException {
        Request<Source> sourceRequest = new Source.CreateRequestBuilder()
                .type(SourceType.BillPaymentTescoLotus)
                .amount(10000)
                .currency("thb")
                .build();

        Source source = client.sendRequest(sourceRequest);

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .source(source.getId())
                        .amount(10000)
                        .currency("thb")
                        .returnUri("http://example.com/orders/345678/complete")
                        .build();

        Charge charge = client.sendRequest(createChargeRequest);

        System.out.println("created charge: " + charge.getId());

        assertNotNull(charge.getId());
        assertEquals(10000, charge.getAmount());
        assertEquals("THB", charge.getCurrency());
        assertEquals(SourceType.BillPaymentTescoLotus, charge.getSource().getType());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeWithAlipay() throws IOException, OmiseException {
        Request<Source> sourceRequest = new Source.CreateRequestBuilder()
                .type(SourceType.Alipay)
                .amount(10000)
                .currency("thb")
                .build();

        Source source = client.sendRequest(sourceRequest);

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .source(source.getId())
                        .amount(10000)
                        .currency("thb")
                        .returnUri("http://example.com/orders/345678/complete")
                        .build();

        Charge charge = client.sendRequest(createChargeRequest);

        System.out.println("Created charge: " + charge.getId());

        assertNotNull(charge.getId());
        assertEquals(SourceType.Alipay, charge.getSource().getType());
        assertEquals(FlowType.Redirect, charge.getSource().getFlow());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeWithZeroInterestsInstallments() throws IOException, OmiseException {
        Request<Source> sourceRequest = new Source.CreateRequestBuilder()
                .type(SourceType.InstallmentKbank)
                .zeroInterestInstallments(true)
                .installmentTerm(4)
                .amount(300000)
                .currency("thb")
                .build();

        Source source = client.sendRequest(sourceRequest);

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .source(source.getId())
                        .amount(300000)
                        .currency("thb")
                        .zeroInterestInstallments(true)
                        .returnUri("http://example.com/orders/345678/complete")
                        .build();

        Charge charge = client.sendRequest(createChargeRequest);

        System.out.println("Created charge: " + charge.getId());

        assertNotNull(charge.getId());
        assertEquals(SourceType.InstallmentKbank, charge.getSource().getType());
        assertTrue(charge.isZeroInterestInstallments());
        assertEquals(FlowType.Redirect, charge.getSource().getFlow());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeWithPromptpay() throws IOException, OmiseException {
        Request<Source> sourceRequest = new Source.CreateRequestBuilder()
                .type(SourceType.PromptPay)
                .amount(10000)
                .currency("thb")
                .build();

        Source source = client.sendRequest(sourceRequest);

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .source(source.getId())
                        .amount(10000)
                        .currency("thb")
                        .returnUri("http://example.com/orders/345678/complete")
                        .build();

        Charge charge = client.sendRequest(createChargeRequest);

        System.out.println("created charge: " + charge.getId());

        assertNotNull(charge.getId());
        assertEquals(10000, charge.getAmount());
        assertEquals("THB", charge.getCurrency());
        assertEquals(SourceType.PromptPay, charge.getSource().getType());
        assertEquals(Barcode.class, charge.getSource().getScannableCode().getClass());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeUpdate() throws IOException, OmiseException {
        Request<Token> tokenRequest = new Token.CreateRequestBuilder()
                .card(new Card.Create()
                        .name("Omise Co., Ltd. - testLiveCharge")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2020))
                .build();

        Token token = client.sendRequest(tokenRequest);

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(2000) // $20
                        .currency("usd")
                        .description("omise-java test")
                        .card(token.getId())
                        .build();

        Charge createdCharge = client.sendRequest(createChargeRequest);

        System.out.println("Created charge: " + createdCharge.getId());

        Request<Charge> updateChargeRequest =
                new Charge.UpdateRequestBuilder(createdCharge.getId())
                        .description("omise-java test charge")
                        .metadata("test-date", String.valueOf(ZonedDateTime.now().getDayOfMonth()))
                        .metadata("library", "omise-java")
                        .build();

        Charge updatedCharge = client.sendRequest(updateChargeRequest);

        System.out.println("Updated charge: " + updatedCharge.getId());


        assertNotNull(updatedCharge.getId());
        assertEquals(createdCharge.getId(), updatedCharge.getId());
        assertEquals("omise-java test charge", updatedCharge.getDescription());
        assertEquals(updatedCharge.getMetadata().get("library"), "omise-java");
        assertEquals(updatedCharge.getMetadata().get("test-date"), String.valueOf(ZonedDateTime.now().getDayOfMonth()));
        assertEquals(2000, updatedCharge.getAmount());
        assertEquals("USD", updatedCharge.getCurrency());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeCapture() throws IOException, OmiseException {
        Request<Token> tokenRequest = new Token.CreateRequestBuilder()
                .card(new Card.Create()
                        .name("Omise Co., Ltd. - testLiveCharge")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2030))
                .build();

        Token token = client.sendRequest(tokenRequest);

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(2000) // $20
                        .currency("usd")
                        .description("omise-java test")
                        .card(token.getId())
                        .capture(false)
                        .build();
        Charge unCapturedCharge = client.sendRequest(createChargeRequest);

        System.out.println("created charge: " + unCapturedCharge.getId());

        assertNotNull(unCapturedCharge.getId());
        assertFalse(unCapturedCharge.isCapture());

        Request<Charge> captureChargeRequest =
                new Charge.CaptureRequestBuilder(unCapturedCharge.getId()).build();

        Charge capturedCharge = client.sendRequest(captureChargeRequest);

        assertNotNull(capturedCharge);
        assertEquals(unCapturedCharge.getId(), capturedCharge.getId());
        assertTrue(capturedCharge.isPaid());
    }


    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeSinglePartialCapture() throws IOException, OmiseException {
        Request<Token> tokenRequest = new Token.CreateRequestBuilder()
                .card(new Card.Create()
                        .name("Omise Co., Ltd. - testLiveCharge")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2030))
                .build();

        Token token = client.sendRequest(tokenRequest);

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(100000)
                        .currency("thb")
                        .description("omise-java test")
                        .card(token.getId())
                        .capture(false)
                        .authorizationType(AuthorizationType.PreAuth)
                        .build();
        Charge unCapturedCharge = client.sendRequest(createChargeRequest);

        System.out.println("created charge: " + unCapturedCharge.getId());

        assertNotNull(unCapturedCharge.getId());
        assertFalse(unCapturedCharge.isCapture());
        assertEquals(AuthorizationType.PreAuth, unCapturedCharge.getAuthorizationType());

        Request<Charge> captureChargeRequest =
                new Charge.CaptureRequestBuilder(unCapturedCharge.getId()).captureAmount(50000).build();

        Charge capturedCharge = client.sendRequest(captureChargeRequest);

        assertNotNull(capturedCharge);
        assertEquals(unCapturedCharge.getId(), capturedCharge.getId());
        assertTrue(capturedCharge.isPaid());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeReverse() throws IOException, OmiseException {
        Request<Token> tokenRequest = new Token.CreateRequestBuilder()
                .card(new Card.Create()
                        .name("Omise Co., Ltd. - testLiveCharge")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2020))
                .build();

        Token token = client.sendRequest(tokenRequest);

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(2000) // $20
                        .currency("usd")
                        .description("omise-java test")
                        .card(token.getId())
                        .capture(false)
                        .build();
        Charge initialCharge = client.sendRequest(createChargeRequest);

        System.out.println("created charge: " + initialCharge.getId());

        assertNotNull(initialCharge.getId());
        assertFalse(initialCharge.isReversed());

        Request<Charge> reverseChargeRequest =
                new Charge.ReverseRequestBuilder(initialCharge.getId())
                        .build();
        Charge reversedCharge = client.sendRequest(reverseChargeRequest);

        assertNotNull(reversedCharge.getId());
        assertEquals(initialCharge.getId(), reversedCharge.getId());
        assertTrue(reversedCharge.isReversed());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeListGet() throws IOException, OmiseException {
        Request<ScopedList<Charge>> getChargeListRequest =
                new Charge.ListRequestBuilder()
                        .build();


        ScopedList<Charge> charges = client.sendRequest(getChargeListRequest);

        assertEquals(20, charges.getLimit());
        assertTrue(charges.getTotal() > 0);

        Charge charge = charges.getData().get(0);
        assertNotNull(charge);
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeListGet_withOptions() throws IOException, OmiseException {
        Request<ScopedList<Charge>> getChargeListRequest =
                new Charge.ListRequestBuilder()
                        .options(new ScopedList.Options()
                                .limit(3)
                                .order(Ordering.Chronological))
                        .build();


        ScopedList<Charge> charges = client.sendRequest(getChargeListRequest);

        assertEquals(3, charges.getLimit());
        assertEquals(3, charges.getData().size());
        assertEquals(Ordering.Chronological, charges.getOrder());

        Charge charge = charges.getData().get(0);
        assertNotNull(charge);
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeDisputableFlag() throws IOException, OmiseException {
        Request<Token> tokenRequest = new Token.CreateRequestBuilder()
                .card(new Card.Create()
                        .name("Omise Co., Ltd. - testLiveCharge")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2020))
                .build();

        Token token = client.sendRequest(tokenRequest);

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(2000) // $20
                        .currency("usd")
                        .description("omise-java test")
                        .card(token.getId())
                        .build();
        Charge createdCharge = client.sendRequest(createChargeRequest);

        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder(createdCharge.getId()).build();
        Charge actualCharge = client.sendRequest(getChargeRequest);


        System.out.println("Retrieved charge: " + actualCharge.getId());

        assertEquals(createdCharge.isDisputable(), actualCharge.isDisputable());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeCapturableFlag() throws IOException, OmiseException {
        Request<Token> tokenRequest = new Token.CreateRequestBuilder()
                .card(new Card.Create()
                        .name("Omise Co., Ltd. - testLiveCharge")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2020))
                .build();

        Token token = client.sendRequest(tokenRequest);

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(2000) // $20
                        .currency("usd")
                        .description("omise-java test")
                        .card(token.getId())
                        .build();
        Charge createdCharge = client.sendRequest(createChargeRequest);

        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder(createdCharge.getId()).build();
        Charge actualCharge = client.sendRequest(getChargeRequest);


        System.out.println("Retrieved charge: " + actualCharge.getId());

        assertEquals(createdCharge.isCapturable(), actualCharge.isCapturable());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeReversibleFlag() throws IOException, OmiseException {
        Request<Token> tokenRequest = new Token.CreateRequestBuilder()
                .card(new Card.Create()
                        .name("Omise Co., Ltd. - testLiveCharge")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2020))
                .build();

        Token token = client.sendRequest(tokenRequest);

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(2000) // $20
                        .currency("usd")
                        .description("omise-java test")
                        .card(token.getId())
                        .build();
        Charge createdCharge = client.sendRequest(createChargeRequest);

        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder(createdCharge.getId()).build();
        Charge actualCharge = client.sendRequest(getChargeRequest);


        System.out.println("Retrieved charge: " + actualCharge.getId());

        assertEquals(createdCharge.isReversible(), actualCharge.isReversible());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeRefundableFlag() throws IOException, OmiseException {
        Request<Token> tokenRequest = new Token.CreateRequestBuilder()
                .card(new Card.Create()
                        .name("Omise Co., Ltd. - testLiveCharge")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2020))
                .build();

        Token token = client.sendRequest(tokenRequest);

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(2000) // $20
                        .currency("usd")
                        .description("omise-java test")
                        .card(token.getId())
                        .build();
        Charge createdCharge = client.sendRequest(createChargeRequest);

        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder(createdCharge.getId()).build();
        Charge actualCharge = client.sendRequest(getChargeRequest);


        System.out.println("Retrieved charge: " + actualCharge.getId());

        assertEquals(createdCharge.isRefundable(), actualCharge.isRefundable());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeWithAtome() throws IOException, OmiseException {

        Shipping shipping = new Shipping();
        shipping.country = "TH";
        shipping.postalCode = "000000";
        shipping.street1 = "Street";

        Item item = new Item();
        item.quantity = 1;
        item.sku = "SKU";
        item.name = "name";
        item.amount = 15000;

        Request<Source> sourceRequest = new Source.CreateRequestBuilder()
                .type(SourceType.Atome)
                .amount(15000) // 150 THB
                .currency("thb")
                .terminalId("test_term_id")
                .storeId("test_store_id")
                .storeName("Omise Shop")
                .shipping(shipping)
                .addItem(item)
                .phoneNumber("0000000000")
                .build();

        Source source = client.sendRequest(sourceRequest);

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .source(source.getId())
                        .amount(15000)
                        .currency("thb")
                        .returnUri("http://example.com/")
                        .build();

        Charge charge = client.sendRequest(createChargeRequest);

        System.out.println("created charge: " + charge.getId());

        assertNotNull(charge.getId());
        assertEquals(15000, charge.getAmount());
        assertEquals("THB", charge.getCurrency());
        assertEquals(SourceType.Atome, charge.getSource().getType());
        assertEquals(FlowType.Redirect, charge.getSource().getFlow());
    }
    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeWithDuitNowOBW() throws IOException, OmiseException {

        Request<Source> sourceRequest = new Source.CreateRequestBuilder()
                .type(SourceType.DuitNowOBW)
                .amount(15000) // 150 MYR
                .currency("MYR")
                .bank("affin")
                .build();

        Source source = client.sendRequest(sourceRequest);

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .source(source.getId())
                        .amount(15000)
                        .currency("MYR")
                        .returnUri("http://example.com/")
                        .build();

        Charge charge = client.sendRequest(createChargeRequest);

        System.out.println("created charge: " + charge.getId());

        assertNotNull(charge.getId());
        assertEquals(15000, charge.getAmount());
        assertEquals("MYR", charge.getCurrency());
        assertEquals(SourceType.DuitNowOBW, charge.getSource().getType());
        assertEquals("affin", charge.getSource().getBank());
        assertEquals(FlowType.Redirect, charge.getSource().getFlow());
    }
    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeWithWechatPay() throws IOException, OmiseException {

        Request<Source> sourceRequest = new Source.CreateRequestBuilder()
                .type(SourceType.WeChatPay)
                .amount(15000)
                .currency("THB")
                .ip("127.0.0.1")
                .build();

        Source source = client.sendRequest(sourceRequest);

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .source(source.getId())
                        .amount(15000)
                        .currency("THB")
                        .returnUri("http://example.com/")
                        .build();

        Charge charge = client.sendRequest(createChargeRequest);

        System.out.println("created charge: " + charge.getId());

        assertNotNull(charge.getId());
        assertEquals(15000, charge.getAmount());
        assertEquals("THB", charge.getCurrency());
        assertEquals("127.0.0.1", charge.getSource().getIp());
        assertEquals(SourceType.WeChatPay, charge.getSource().getType());
        assertEquals(FlowType.AppRedirect, charge.getSource().getFlow());
    }
}
