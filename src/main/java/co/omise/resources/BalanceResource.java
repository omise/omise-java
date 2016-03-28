package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.Balance;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class BalanceResource extends Resource {
    public BalanceResource(OkHttpClient httpClient) {
        super(httpClient);
    }

    public Balance get() throws IOException {
        return httpGet(buildUrl(Endpoint.API, "balance")).returns(Balance.class);
    }
}
