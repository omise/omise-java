package co.omise.requests;

import co.omise.models.Account;
import co.omise.models.OmiseException;
import org.junit.Test;

import java.io.IOException;

public class AccountRequestTest extends RequestTest {

    @Test
    public void testGet() throws IOException, OmiseException {
        Requester requester = getTestRequester();
        Request<Account> getAccountRequest = new Account.GetRequestBuilder().build();
        Account account = requester.sendRequest(getAccountRequest);

        assertRequested("GET", "/account", 200);

        assertEquals("account", account.getObject());
        assertEquals("/account", account.getLocation());
    }

    @Test
    public void testGetEmail() throws IOException, OmiseException {
        Requester requester = getTestRequester();
        Request<Account> getAccountRequest = new Account.GetRequestBuilder().build();
        Account account = requester.sendRequest(getAccountRequest);

        assertRequested("GET", "/account", 200);

        assertEquals("chakrit@omise.co", account.getEmail());
    }

    @Test
    public void testGetUserId() throws IOException, OmiseException {
        Requester requester = getTestRequester();
        Request<Account> getAccountRequest = new Account.GetRequestBuilder().build();
        Account account = requester.sendRequest(getAccountRequest);

        assertRequested("GET", "/account", 200);
        assertEquals("acct_4yq6tcsyoged5c0ocxd", account.getId());
    }

    @Test
    public void testGetCurrency() throws IOException, OmiseException {
        Requester requester = getTestRequester();
        Request<Account> request = new Account.GetRequestBuilder().build();
        Account account = requester.sendRequest(request);

        assertRequested("GET", "/account", 200);

        assertEquals("thb", account.getCurrency());
    }
}