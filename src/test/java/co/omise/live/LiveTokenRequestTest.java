package co.omise.live;

import co.omise.Client;
import co.omise.models.Card;
import co.omise.models.Token;
import co.omise.requests.Request;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LiveTokenRequestTest extends BaseLiveTest {

    @Test
    @Ignore("only hit when test on live.")
    public void getToken_success() throws Exception {
        Client client = getLiveClient();

        Request<Token> tokenRequest = new Token.CreateRequestBuilder()
                .card(new Card.Create()
                        .name("testGetToken")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2020))
                .build();

        Token createdToken = client.sendRequest(tokenRequest);

        System.out.println("Token created: " + createdToken.getId());

        Request<Token> request = new Token.GetRequestBuilder(createdToken.getId()).build();
        Token retrievedToken = client.sendRequest(request);

        System.out.println("Token retrieved: " + retrievedToken.getId());

        assertEquals(createdToken.getId(), retrievedToken.getId());
    }

    @Test
    @Ignore("only hit when test on live.")
    public void createTokenSuccess() throws Exception {
        Client client = getLiveClient();

        Request<Token> request = new Token.CreateRequestBuilder()
                .card(new Card.Create()
                        .name("testCreateToken")
                        .number("4242424242424242")
                        .securityCode("123")
                        .expiration(10, 2020))
                .build();

        Token token = client.sendRequest(request);

        System.out.println("Token created: " + token.getId());

        assertEquals("Visa", token.getCard().getBrand());
        assertEquals("testCreateToken", token.getCard().getName());
        assertEquals("4242", token.getCard().getLastDigits());
        assertEquals(10, token.getCard().getExpirationMonth());
        assertEquals(2020, token.getCard().getExpirationYear());
    }
}