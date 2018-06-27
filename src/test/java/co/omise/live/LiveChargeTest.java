package co.omise.live;

import co.omise.Client;
import co.omise.ClientException;
import co.omise.models.Card;
import co.omise.models.Charge;
import co.omise.models.OmiseException;
import co.omise.models.Token;
import co.omise.requests.Request;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class LiveChargeTest extends BaseLiveTest {

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveCharge() throws ClientException, IOException, OmiseException {
        Client client = new Client(getPublicKey(), getSecretKey());
        //TODO Change this to new format when Token creation flow is changed to new flow
        Token token = client.tokens().create(new Token.Create()
                .card(new Card.Create()
                        .name("Omise Co., Ltd. - testLiveCharge")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2020)));

        //TODO Change this to new format when Charge creation flow is changed to new flow
        Charge createdCharge = client.charges().create(new Charge.Create()
                .amount(2000) // $20
                .currency("usd")
                .description("omise-java test")
                .card(token.getId()));

        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder(createdCharge.getId()).build();
        Charge actualCharge = client.sendRequest(getChargeRequest, Charge.class);


        System.out.println("Retrieved charge: " + actualCharge.getId());

        assertEquals(createdCharge.getId(), actualCharge.getId());
    }
}
