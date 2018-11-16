package co.omise;

import co.omise.models.Account;
import co.omise.models.Card;
import co.omise.models.OmiseException;
import co.omise.models.Token;
import co.omise.requests.Request;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class ClientTest extends OmiseTest {
    private static final String LIVETEST_PKEY = "pkey_test_replaceme";
    private static final String LIVETEST_SKEY = "skey_test_replaceme";

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSuccess() throws ClientException, IOException {
        try {
            Request<Account> getAccountRequest = new Account.GetRequestBuilder().build();
            Client client = new Client.Builder()
                    .publicKey(LIVETEST_PKEY)
                    .secretKey(LIVETEST_SKEY)
                    .build();

            Account account = client.sendRequest(getAccountRequest);

            assertNotNull(account);
        } catch (OmiseException e) {
            fail();
        }
    }

    @Test
    public void testBuildClientWithoutAnyKeyError() {
        try {
            Client client = new Client.Builder().build();
        } catch (ClientException e) {
            assertEquals("Client initialization failure.", e.getMessage());
            assertEquals("The key must have at least one key.", e.getCause().getMessage());
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

            Client client = new Client.Builder()
                    .publicKey("pkey_test_123")
                    .secretKey("skey_test_123")
                    .build();

            client.sendRequest(request);
        } catch (OmiseException e) {
            assertEquals("authentication_failure", e.getCode());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveError() throws ClientException, IOException {
        try {
            Request<Account> getAccountRequest = new Account.GetRequestBuilder().build();
            Client client = new Client.Builder()
                    .secretKey("skey_test_123")
                    .build();
            client.sendRequest(getAccountRequest);
        } catch (OmiseException e) {
            assertEquals("authentication_failure", e.getCode());
        }
    }
}