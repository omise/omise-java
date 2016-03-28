package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.Refund;
import co.omise.models.ScopedList;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class RefundResource extends Resource {
    private final String chargeId;

    protected RefundResource(OkHttpClient httpClient, String chargeId) {
        super(httpClient);
        this.chargeId = chargeId;
    }

    public ScopedList<Refund> list() throws IOException {
        return list(new ScopedList.Options());
    }

    public ScopedList<Refund> list(ScopedList.Options options) throws IOException {
        return httpGet(urlFor("")).params(options).returns(new TypeReference<ScopedList<Refund>>() {
        });
    }

    public Refund get(String refundId) throws IOException {
        return httpGet(urlFor(refundId)).returns(Refund.class);
    }

    public Refund create(Refund.Create params) throws IOException {
        return httpPost(urlFor("")).params(params).returns(Refund.class);
    }

    private HttpUrl urlFor(String refundId) {
        return buildUrl(Endpoint.API, "charges", chargeId, "refunds", refundId);
    }
}
