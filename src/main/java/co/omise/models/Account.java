package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import okhttp3.HttpUrl;

/**
 * Represents Omise Account object and contains its {@link RequestBuilder<Account>}.
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
     * public accessor method for the a {@link RequestBuilder<Account>} for getting the user's Account.
     *
     * @return the get request builder
     */
    public static GetRequestBuilder buildGetRequest() {
        return new GetRequestBuilder();
    }

    /**
     * The {@link RequestBuilder<Account>} class for for getting the user's Account.
     */
    public static class GetRequestBuilder extends RequestBuilder<Account> {

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "account");
        }
    }
}
