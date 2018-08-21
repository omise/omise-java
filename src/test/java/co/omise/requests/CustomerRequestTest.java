package co.omise.requests;

import co.omise.models.Customer;
import co.omise.models.OmiseException;
import org.junit.Test;

import java.io.IOException;

public class CustomerRequestTest extends RequestTest {
    private static final String CUSTOMER_ID = "cust_test_4yq6txdpfadhbaqnwp3";

    @Test
    public void testCreate() throws IOException, OmiseException {
        Request<Customer> request = new Customer.CreateRequestBuilder()
                .email("john.doe@example.com")
                .description("John Doe (id: 30)")
                .build();

        Customer customer = getTestRequester().sendRequest(request);

        assertRequested("POST", "/customers", 200);

        assertEquals("customer", customer.getObject());
        assertEquals(CUSTOMER_ID, customer.getId());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("John Doe (id: 30)", customer.getDescription());
    }

    @Test
    public void testGet() throws IOException, OmiseException {
        Request<Customer> request = new Customer.GetRequestBuilder(CUSTOMER_ID).build();
        Customer customer = getTestRequester().sendRequest(request);

        assertRequested("GET", "/customers/" + CUSTOMER_ID, 200);

        assertEquals("customer", customer.getObject());
        assertEquals(CUSTOMER_ID, customer.getId());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("John Doe (id: 30)", customer.getDescription());
    }

}
