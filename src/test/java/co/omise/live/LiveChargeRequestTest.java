package co.omise.live;

import co.omise.Client;
import co.omise.models.*;
import co.omise.requests.Request;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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
        assertEquals("usd", charge.getCurrency());
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
        assertEquals("thb", charge.getCurrency());
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
        assertEquals("thb", charge.getCurrency());
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
                        .metadata("test-date", String.valueOf(ZonedDateTime.now(ZoneId.of("Z")).getDayOfMonth()))
                        .metadata("library", "omise-java")
                        .build();

        Charge updatedCharge = client.sendRequest(updateChargeRequest);

        System.out.println("Updated charge: " + updatedCharge.getId());


        assertNotNull(updatedCharge.getId());
        assertEquals(createdCharge.getId(), updatedCharge.getId());
        assertEquals("omise-java test charge", updatedCharge.getDescription());
        assertEquals(updatedCharge.getMetadata().get("library"), "omise-java");
        assertEquals(updatedCharge.getMetadata().get("test-date"), String.valueOf(ZonedDateTime.now(ZoneId.of("Z")).getDayOfMonth()));
        assertEquals(2000, updatedCharge.getAmount());
        assertEquals("usd", updatedCharge.getCurrency());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeCapture() throws IOException, OmiseException {
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

}
