package co.omise.testutils;

import co.omise.Endpoint;
import co.omise.models.Source;
import okhttp3.HttpUrl;

public class TestSourceRequestBuilder extends Source.CreateRequestBuilder {
    @Override
    protected HttpUrl path() {
        return buildUrl(Endpoint.API, "sources", "installments");
    }
}
