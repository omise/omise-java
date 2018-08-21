package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.Customer;
import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class CustomerResource extends Resource {
    public CustomerResource(OkHttpClient httpClient) {
        super(httpClient);
    }

    public ScopedList<Customer> list() throws IOException, OmiseException {
        return list(new ScopedList.Options());
    }

    public ScopedList<Customer> list(ScopedList.Options options) throws IOException, OmiseException {
        return httpGet(urlFor("")).params(options).returns(new TypeReference<ScopedList<Customer>>() {
        });
    }

    private HttpUrl urlFor(String customerId) {
        return buildUrl(Endpoint.API, "customers", customerId);
    }
}