package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import okhttp3.HttpUrl;

public class Forex extends Model {
    private double rate;
    private String from;
    private String to;

    public Forex() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
