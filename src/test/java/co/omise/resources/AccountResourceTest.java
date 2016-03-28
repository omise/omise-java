package co.omise.resources;

import co.omise.models.Account;
import org.junit.Test;

import java.io.IOException;

public class AccountResourceTest extends ResourceTest {
    @Test
    public void testGet() throws IOException {
        Account account = resource().get();
        assertRequested("GET", "/account", 200);

        assertEquals("chakrit@omise.co", account.getEmail());
        assertEquals("acct_4yq6tcsyoged5c0ocxd", account.getId());
        assertEquals("account", account.getObject());
        assertEquals("/account", account.getLocation());
    }

    private AccountResource resource() {
        return new AccountResource(testClient());
    }
}