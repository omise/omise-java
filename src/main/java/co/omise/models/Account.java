package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import okhttp3.HttpUrl;

/**
 * Represents Omise Account object and contains its {@link RequestBuilder}.
 *
 * @see <a href="https://www.omise.co/account-api">Account API</a>
 */
public class Account extends Model {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * The {@link RequestBuilder} class for getting the user's Account. This class only overrides the path() method from its parent.
     */
    public static class GetRequestBuilder extends RequestBuilder<Account> {

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "account");
        }
    }
}
