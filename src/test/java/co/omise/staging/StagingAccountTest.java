package co.omise.staging;

import co.omise.Client;
import co.omise.models.Account;
import org.junit.Assert;
import org.junit.Test;

public class StagingAccountTest {

    private final String STAGING_PKEY = "[YOUR_PKEY]";
    private final String STAGING_SKEY = "[YOUR_SKEY]";

    @Test
    public void getAccount_success() throws Exception {
        // Give
        Client client = new Client(STAGING_PKEY, STAGING_SKEY);

        // When
        Account actualAccount = client.account().get();

        // Then
        Assert.assertEquals("natthawut@omise.co", actualAccount.getEmail());
    }
}
