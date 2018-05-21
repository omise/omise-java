package co.omise.staging;

import co.omise.Client;
import co.omise.models.Account;
import co.omise.requests.Request;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class StagingAccountTest {

    private final String STAGING_PKEY = "[YOUR_PKEY]";
    private final String STAGING_SKEY = "[YOUR_SKEY]";

    @Test
    @Ignore("only hit when test on staging.")
    public void getAccount_success() throws Exception {
        // Give
        Client client = new Client(STAGING_PKEY, STAGING_SKEY);

        // When
        Request<Account> getAccountRequest =  Account.buildGetRequest().build();
        Account actualAccount = client.sendRequest(getAccountRequest, Account.class);

        // Then
        Assert.assertEquals("natthawut@omise.co", actualAccount.getEmail());
    }
}
