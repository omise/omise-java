package co.omise;

import co.omise.models.Balance;
import co.omise.models.OmiseException;
import co.omise.models.Token;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class ClientTest extends OmiseTest {
    @Test
    public void testCtor() throws ClientException {
        try {
            new Client(null);
            fail("exception expected");
        } catch (NullPointerException e) {
        }
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveErrorVault() throws ClientException, IOException {
        try {
            new Client("pkey_test_123", "skey_test_123").tokens().create(new Token.Create()
                    .name("Omise Co., Ltd.")
                    .number("4242424242424242")
                    .expiration(10, 2020)
                    .securityCode("123"));
        } catch (OmiseException e) {
            assertEquals("authentication_failure", e.getCode());
        }
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
    @Ignore("adds your skey here to do a live test.")
    public void testLiveFetch() throws ClientException, IOException, OmiseException {
        Client client = new Client("skey_test_123");
        Balance balance = client.balance().get();
        assertEquals(16741576, balance.getTotal());
    }
}
