package co.omise.live;

import co.omise.Client;
import co.omise.models.Customer;
import co.omise.models.OmiseException;
import co.omise.requests.Request;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LiveCustomerRequestTest extends BaseLiveTest {
    private Client client;

    @Before
    public void setup() throws Exception {
        client = getLiveClient();
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveCreateCustomer() throws IOException, OmiseException {
        Request<Customer> request = new Customer.CreateRequestBuilder()
                .email("john.doe@example.com")
                .description("John Doe (id: 30)")
                .build();

        Customer customer = client.sendRequest(request);

        System.out.println("Created customer: " + customer.getId());

        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("John Doe (id: 30)", customer.getDescription());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveGetCustomer() throws IOException, OmiseException {
        Request<Customer> createRequest = new Customer.CreateRequestBuilder()
                .email("john.doe@example.com")
                .description("John Doe (id: 30)")
                .build();

        Customer createdCustomer = client.sendRequest(createRequest);

        System.out.println("Created customer: " + createdCustomer.getId());

        Request<Customer> retrieveRequest = new Customer.GetRequestBuilder(createdCustomer.getId()).build();

        Customer retrievedCustomer = client.sendRequest(retrieveRequest);

        System.out.println("Retrieved customer: " + retrievedCustomer.getId());

        assertEquals(createdCustomer.getId(), retrievedCustomer.getId());
        assertEquals(createdCustomer.getEmail(), retrievedCustomer.getEmail());
    }
}
