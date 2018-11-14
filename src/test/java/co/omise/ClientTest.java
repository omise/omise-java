package co.omise;

import co.omise.models.Account;
import co.omise.models.Card;
import co.omise.models.OmiseException;
import co.omise.models.Token;
import co.omise.requests.Request;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class ClientTest extends OmiseTest {
    private static final String LIVETEST_PKEY = "pkey_test_replaceme";
    private static final String LIVETEST_SKEY = "skey_test_replaceme";

    @Test
    public void testCreateClient() {
        try {
            Client client = new Client.Builder()
                    .publicKey(LIVETEST_PKEY)
                    .secretKey(LIVETEST_SKEY)
                    .build();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

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

            new Client("pkey_test_123", "skey_test_123").sendRequest(request);
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
            new Client("skey_test_123").sendRequest(getAccountRequest);
        } catch (OmiseException e) {
            assertEquals("authentication_failure", e.getCode());
        }
    }

    private Client liveTestClient() throws ClientException {
        return new Client(LIVETEST_PKEY, LIVETEST_SKEY);
    }
}