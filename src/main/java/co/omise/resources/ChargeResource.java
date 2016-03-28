package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.Charge;
import co.omise.models.ScopedList;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class ChargeResource extends Resource {
    public ChargeResource(OkHttpClient httpClient) {
        super(httpClient);
    }

    public ScopedList<Charge> list() throws IOException {
        return list(new ScopedList.Options());
    }

    public ScopedList<Charge> list(ScopedList.Options options) throws IOException {
        return httpGet(urlFor("")).params(options).returns(new TypeReference<ScopedList<Charge>>() {
        });
    }

    public Charge get(String chargeId) throws IOException {
        return httpGet(urlFor(chargeId)).returns(Charge.class);
    }

    public Charge create(Charge.Create params) throws IOException {
        return httpPost(urlFor("")).params(params).returns(Charge.class);
    }

    public Charge update(String chargeId, Charge.Update params) throws IOException {
        return httpPatch(urlFor(chargeId)).params(params).returns(Charge.class);
    }

    private HttpUrl urlFor(String chargeId) {
        return buildUrl(Endpoint.API, "charges", chargeId);
    }
}
