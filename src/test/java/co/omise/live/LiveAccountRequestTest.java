package co.omise.live;

import co.omise.Client;
import co.omise.models.Account;
import co.omise.requests.Request;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class LiveAccountRequestTest extends BaseLiveTest {

    @Test
    @Ignore("only hit when test on live.")
    public void getAccount_success() throws Exception {
        Client client = getLiveClient();

        Request<Account> getAccountRequest = new Account.GetRequestBuilder().build();
        Account actualAccount = client.sendRequest(getAccountRequest);

        System.out.println("Account retrieved: " + actualAccount.getEmail());

        Assert.assertEquals(getUserEmail(), actualAccount.getEmail());
    }
}
