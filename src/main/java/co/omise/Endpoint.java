package co.omise;

import okhttp3.HttpUrl;

public class Endpoint {
    public static final Endpoint VAULT = new Endpoint("vault.omise.co");
    public static final Endpoint API = new Endpoint("api.omise.co");

    private final String host;

    protected Endpoint(String host) {
        this.host = host;
    }

    public String scheme() {
        return "https";
    }

    public String host() {
        return host;
    }

    public HttpUrl.Builder buildUrl() {
        return new HttpUrl.Builder()
                .scheme(scheme())
                .host(host());
    }
}

