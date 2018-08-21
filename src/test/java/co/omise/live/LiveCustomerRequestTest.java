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
import static org.junit.Assert.assertNotEquals;

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

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveUpdateCustomer() throws IOException, OmiseException {
        Request<Customer> createRequest = new Customer.CreateRequestBuilder()
                .email("john.doe@example.com")
                .description("John Doe (id: 30)")
                .build();

        Customer createdCustomer = client.sendRequest(createRequest);

        System.out.println("Created customer: " + createdCustomer.getId());

        Request<Customer> updateRequest = new Customer.UpdateRequestBuilder(createdCustomer.getId())
                .email("john.doe.not.so.doe@example.com")
                .metadata("first_thing", "first")
                .metadata("second_thing", "second")
                .build();

        Customer updatedCustomer = client.sendRequest(updateRequest);

        System.out.println("Updated customer: " + updatedCustomer.getId());

        assertEquals(createdCustomer.getId(), updatedCustomer.getId());
        assertNotEquals(createdCustomer.getEmail(), updatedCustomer.getEmail());
        assertEquals("john.doe.not.so.doe@example.com", updatedCustomer.getEmail());
        assertEquals(updatedCustomer.getMetadata().get("first_thing"), "first");
        assertEquals(updatedCustomer.getMetadata().get("second_thing"), "second");
    }
}
