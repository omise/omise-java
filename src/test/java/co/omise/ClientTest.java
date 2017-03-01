package co.omise;

import co.omise.models.*;
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
    @Ignore("only hit the network when we need to.")
    public void testLiveCard() throws ClientException, IOException {
        try {
            ScopedList<Card> list = new Client("skey_test_55m9sazu79b5ir95ced")
                    .customer("cust_test_558xjomi2zgaquajwx5")
                    .cards()
                    .list();
            for (Card card : list.getData()) {
                System.out.println(card.getId() + " : " + card.getLastDigits());
            }
        } catch (OmiseException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveFetch() throws ClientException, IOException, OmiseException {
        Client client = new Client("skey_test_123");
        Balance balance = client.balance().get();
        assertEquals(16741576, balance.getTotal());
    }
}
