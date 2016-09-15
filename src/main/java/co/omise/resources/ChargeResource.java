package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.Charge;
import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class ChargeResource extends Resource {
    public ChargeResource(OkHttpClient httpClient) {
        super(httpClient);
    }

    public ScopedList<Charge> list() throws IOException, OmiseException {
        return list(new ScopedList.Options());
    }

    public ScopedList<Charge> list(ScopedList.Options options) throws IOException, OmiseException {
        return httpGet(urlFor("")).params(options).returns(new TypeReference<ScopedList<Charge>>() {
        });
    }

    public Charge get(String chargeId) throws IOException, OmiseException {
        return httpGet(urlFor(chargeId)).returns(Charge.class);
    }

    public Charge create(Charge.Create params) throws IOException, OmiseException {
        return httpPost(urlFor("")).params(params).returns(Charge.class);
    }

    public Charge update(String chargeId, Charge.Update params) throws IOException, OmiseException {
        return httpPatch(urlFor(chargeId)).params(params).returns(Charge.class);
    }

    public Charge capture(String chargeId) throws IOException, OmiseException {
        return httpPost(urlFor(chargeId, "capture")).returns(Charge.class);
    }

    public Charge reverse(String chargeId) throws IOException, OmiseException {
        return httpPost(urlFor(chargeId, "reverse")).returns(Charge.class);
    }

    private HttpUrl urlFor(String chargeId) {
        return buildUrl(Endpoint.API, "charges", chargeId);
    }

    private HttpUrl urlFor(String chargeId, String resource) {
        return buildUrl(Endpoint.API, "charges", chargeId, resource);
    }
}
