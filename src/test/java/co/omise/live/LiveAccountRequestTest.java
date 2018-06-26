package co.omise.live;

import co.omise.Client;
import co.omise.models.Account;
import co.omise.requests.Request;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class LiveAccountRequestTest extends BaseLiveTest{

    @Test
    @Ignore("only hit when test on live.")
    public void getAccount_success() throws Exception {
        // Give
        Client client = new Client(getPublicKey(), getSecretKey());

        // When
        Request<Account> getAccountRequest = new Account.GetRequestBuilder().build();
        Account actualAccount = client.sendRequest(getAccountRequest, Account.class);

        System.out.println("Account retrieved: " + actualAccount.getEmail());

        // Then
        Assert.assertEquals(getUserEmail(), actualAccount.getEmail());
    }
}
