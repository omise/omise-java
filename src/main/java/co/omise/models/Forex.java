package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import okhttp3.HttpUrl;

public class Forex extends Model {
    private double rate;
    private String base;
    private String quote;

    public Forex() {
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * The {@link RequestBuilder} class for getting the exchange rate of a particular currency.
     */
    public static class GetRequestBuilder extends RequestBuilder<Forex> {
        private String currency;

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
