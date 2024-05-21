package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import okhttp3.HttpUrl;

/**
 * Forex object
 *
 * @see <a href="https://www.omise.co/forex-api">Forex API</a>
 */
public class Forex extends Model {
    private String base;
    private String location;
    private String quote;
    private Double rate;

    public String getBase() {
        return this.base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getQuote() {
        return this.quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public Double getRate() {
        return this.rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public static class GetRequestBuilder extends RequestBuilder<Forex> {
        private final String currency;
        public GetRequestBuilder(String currency) {
            this.currency = currency;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "forex", currency);
        }

        @Override
        protected ResponseType<Forex> type() {
            return new ResponseType<>(Forex.class);
        }
    }
}