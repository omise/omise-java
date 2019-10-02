package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

import java.io.IOException;

public class Balance extends Model {
    private String currency;
    private String location;
    private long reserve;
    private long total;
    private long transferable;

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getReserve() {
        return this.reserve;
    }

    public void setReserve(long reserve) {
        this.reserve = reserve;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTransferable() {
        return this.transferable;
    }

    public void setTransferable(long transferable) {
        this.transferable = transferable;
    }

    public static class GetRequestBuilder extends RequestBuilder<Balance> {

        @Override
        protected String method() {
            return GET;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "balance");
        }

        @Override
        protected ResponseType<Balance> type() {
            return new ResponseType<>(Balance.class);
        }
    }
}