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

        Token createdToken = client.sendRequest(tokenRequest, Token.class);

        System.out.println("Token created: " + createdToken.getId());

        Request<Token> request = new Token.GetRequestBuilder(createdToken.getId()).build();
        Token retrievedToken = client.sendRequest(request, Token.class);

        System.out.println("Token retrieved: " + retrievedToken.getId());

        assertEquals(createdToken.getId(), retrievedToken.getId());
    }
}