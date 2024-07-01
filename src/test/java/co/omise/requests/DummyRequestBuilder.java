package co.omise.requests;

import co.omise.Endpoint;
import co.omise.models.Charge;
import co.omise.models.ScopedList;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;

public class DummyRequestBuilder extends RequestBuilder<ScopedList<Charge>> {
    private ScopedList.Options options;

    @Override
    protected HttpUrl path() {
        if (options == null) {
            options = new ScopedList.Options();
        }

        return buildUrl(Endpoint.API, "charges", options);
    }

    @Override
    protected ResponseType<ScopedList<Charge>> type() {
        return new ResponseType<>(new TypeReference<ScopedList<Charge>>() {});
    }

    public DummyRequestBuilder options(ScopedList.Options options) {
        this.options = options;
        return this;
    }
}
