package co.omise.live;

import co.omise.Client;
import co.omise.ClientException;
import co.omise.models.*;
import co.omise.requests.Request;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class LiveChargeTest extends BaseLiveTest {
    private Client client;

    @Before
    public void setup() throws ClientException {
        client = new Client(getPublicKey(), getSecretKey());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveGetCharge() throws IOException, OmiseException {
        //TODO Change this to new format when Token creation flow is changed to new flow
        Token token = client.tokens().create(new Token.Create()
                .card(new Card.Create()
                        .name("Omise Co., Ltd. - testLiveCharge")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2020)));

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(2000) // $20
                        .currency("usd")
                        .description("omise-java test")
                        .card(token.getId())
                        .build();
        Charge createdCharge = client.sendRequest(createChargeRequest, Charge.class);

        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder(createdCharge.getId()).build();
        Charge actualCharge = client.sendRequest(getChargeRequest, Charge.class);


        System.out.println("Retrieved charge: " + actualCharge.getId());

        assertEquals(createdCharge.getId(), actualCharge.getId());
    }


    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveCreateCharge() throws IOException, OmiseException {
        //TODO Change this to new format when Token creation flow is changed to new flow
        Token token = client.tokens().create(new Token.Create()
                .card(new Card.Create()
                        .name("Omise Co., Ltd. - testLiveCharge")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2020)));

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(2000) // $20
                        .currency("usd")
                        .description("omise-java test")
                        .card(token.getId())
                        .build();

        Charge charge = client.sendRequest(createChargeRequest, Charge.class);

        System.out.println("created charge: " + charge.getId());

        assertNotNull(charge);
        assertNotNull(charge.getId());
        assertEquals(2000, charge.getAmount());
        assertEquals("usd", charge.getCurrency());
        assertEquals(token.getCard().getLastDigits(), charge.getCard().getLastDigits());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeWithInternetBanking() throws IOException, OmiseException {
        //TODO Change this to new format when Source creation flow is changed to new flow
        Source source = client.sources().create(new Source.Create()
                .type(SourceType.InternetBankingBay)
                .amount(10000)
                .currency("thb"));

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .source(source.getId())
                        .amount(10000)
                        .currency("thb")
                        .returnUri("http://example.com/orders/345678/complete")
                        .build();

        Charge charge = client.sendRequest(createChargeRequest, Charge.class);

        System.out.println("created charge: " + charge.getId());

        assertNotNull(charge);
        assertNotNull(charge.getId());
        assertEquals(10000, charge.getAmount());
        assertEquals("thb", charge.getCurrency());
        assertEquals(SourceType.InternetBankingBay, charge.getSource().getType());
        assertEquals(FlowType.Redirect, charge.getSource().getFlow());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeWithBillPaymentTescoLotus() throws IOException, OmiseException {
        //TODO Change this to new format when Source creation flow is changed to new flow
        Source source = client.sources().create(new Source.Create()
                .type(SourceType.BillPaymentTescoLotus)
                .amount(10000)
                .currency("thb"));

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .source(source.getId())
                        .amount(10000)
                        .currency("thb")
                        .returnUri("http://example.com/orders/345678/complete")
                        .build();

        Charge charge = client.sendRequest(createChargeRequest, Charge.class);

        System.out.println("created charge: " + charge.getId());

        assertNotNull(charge);
        assertNotNull(charge.getId());
        assertEquals(10000, charge.getAmount());
        assertEquals("thb", charge.getCurrency());
        assertEquals(SourceType.BillPaymentTescoLotus, charge.getSource().getType());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveChargeWithAlipay() throws IOException, OmiseException {
        //TODO Change this to new format when Source creation flow is changed to new flow
        Source source = client.sources().create(new Source.Create()
                .type(SourceType.Alipay)
                .amount(10000)
                .currency("thb"));

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .source(source.getId())
                        .amount(10000)
                        .currency("thb")
                        .returnUri("http://example.com/orders/345678/complete")
                        .build();

        Charge charge = client.sendRequest(createChargeRequest, Charge.class);

        System.out.println("created charge: " + charge.getId());

        assertNotNull(charge.getId());
        assertEquals(SourceType.Alipay, charge.getSource().getType());
        assertEquals(FlowType.Redirect, charge.getSource().getFlow());
    }

    //TODO add this for an update test
//    charge = client.charges().update(charge.getId(), new Charge.Update()
//            .description("omise-java test charge")
//                .metadata("test-date", DateTime.now().toString())
//            .metadata("library", "omise-java")
}
