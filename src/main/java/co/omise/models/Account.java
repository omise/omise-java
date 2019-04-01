package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import okhttp3.HttpUrl;
import org.joda.time.DateTime;

/**
 * Represents Omise Account object and contains its {@link RequestBuilder}.
 *
 * @see <a href="https://www.omise.co/account-api">Account API</a>
 */
public class Account extends Model {
    private String email;
    private String currency;

    public Account(String object, String location, String id, boolean liveMode, DateTime created, boolean deleted, String email, String currency) {
        super(object, location, id, liveMode, created, deleted);
        this.email = email;
        this.currency = currency;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * The {@link RequestBuilder} class for getting the user's Account. This class only overrides the path() method from its parent.
     */
    public static class GetRequestBuilder extends RequestBuilder<Account> {

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "account");
        }

        @Override
        protected ResponseType<Account> type() {
            return new ResponseType<>(Account.class);
        }
    }
}
