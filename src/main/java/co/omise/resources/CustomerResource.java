package co.omise.resources;

import co.omise.models.Customer;
import co.omise.models.ScopedList;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class CustomerResource extends Resource {
    public CustomerResource(OkHttpClient httpClient) {
        super(httpClient);
    }

    public CustomerSpecificResource withId(String customerId) {
        return new CustomerSpecificResource(httpClient(), customerId);
    }

    public ScopedList<Customer> list() throws IOException {
        return list(new ScopedList.Options());
    }

    public ScopedList<Customer> list(ScopedList.Options options) throws IOException {
        return httpGet(urlFor("")).params(options).returns(new TypeReference<ScopedList<Customer>>() {
        });
    }

    public Customer get(String customerId) throws IOException {
        return httpGet(urlFor(customerId)).returns(Customer.class);
    }

    public Customer create(Customer.Create params) throws IOException {
        return httpPost(urlFor("")).params(params).returns(Customer.class);
    }

    public Customer update(String customerId, Customer.Update params) throws IOException {
        return httpPost(urlFor(customerId)).params(params).returns(Customer.class);
    }

    public Customer destroy(String customerId) throws IOException {
        return httpDelete(urlFor(customerId)).returns(Customer.class);
    }

    private HttpUrl urlFor(String customerId) {
        return apiUrl("/customers")
                .addPathSegment(customerId)
                .build();
    }
}
