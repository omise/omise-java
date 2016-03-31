package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.Account;
import co.omise.models.ScopedList;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class DummyResource extends Resource {
    protected DummyResource(OkHttpClient httpClient) {
        super(httpClient);
    }

    public Account list(ScopedList.Options options) throws IOException {
        return httpGet(buildUrl(Endpoint.API, "/account")).params(options).returns(Account.class);
    }
}
