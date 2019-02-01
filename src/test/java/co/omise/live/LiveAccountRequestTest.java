package co.omise.live;

import co.omise.Client;
import co.omise.models.Account;
import co.omise.requests.Request;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LiveAccountRequestTest extends BaseLiveTest {

    @Test
    @Ignore("only hit when test on live.")
    public void getAccount_EmailSuccess() throws Exception {
        Client client = getLiveClient();

        Request<Account> getAccountRequest = new Account.GetRequestBuilder().build();
        Account actualAccount = client.sendRequest(getAccountRequest);

        System.out.println("Account retrieved: " + actualAccount.getEmail());

        assertEquals(getUserEmail(), actualAccount.getEmail());
    }

    @Test
    @Ignore("only hit when test on live.")
    public void getAccount_CurrencySuccess() throws Exception {
        Client client = getLiveClient();

        Request<Account> request = new Account.GetRequestBuilder().build();
        Account account = client.sendRequest(request);

        System.out.println("Account retrieved: " + account.getEmail());

        assertEquals("thb", account.getCurrency());
    }
}
