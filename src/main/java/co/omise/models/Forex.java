package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import okhttp3.HttpUrl;
import org.joda.time.DateTime;

public class Forex extends Model {
    private double rate;
    private String from;
    private String to;

    public Forex(String object, String location, String id, boolean liveMode, DateTime created, boolean deleted, double rate, String from, String to) {
        super(object, location, id, liveMode, created, deleted);
        this.rate = rate;
        this.from = from;
        this.to = to;
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
