package co.omise.requests;

import co.omise.models.Customer;
import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
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

    @Test
    public void testUpdate() throws IOException, OmiseException {
        Request<Customer> request = new Customer.UpdateRequestBuilder(CUSTOMER_ID)
                .email("john.doe.the.second@example.com")
                .build();
        Customer customer = getTestRequester().sendRequest(request);

        assertRequested("PATCH", "/customers/" + CUSTOMER_ID, 200);

        assertEquals("cust_test_4yq6txdpfadhbaqnwp3", customer.getId());
        assertEquals("john.doe.the.second@example.com", customer.getEmail());
    }

    @Test
    public void testDestroy() throws IOException, OmiseException {
        Request<Customer> request = new Customer.DeleteRequestBuilder(CUSTOMER_ID).build();
        Customer customer = getTestRequester().sendRequest(request);

        assertRequested("DELETE", "/customers/" + CUSTOMER_ID, 200);

        assertEquals("cust_test_4yq6txdpfadhbaqnwp3", customer.getId());
        assertTrue(customer.isDeleted());
    }

    @Test
    public void testGetList() throws IOException, OmiseException {
        Request<ScopedList<Customer>> request = new Customer.ListRequestBuilder().build();
        ScopedList<Customer> list = getTestRequester().sendRequest(request);

        assertRequested("GET", "/customers", 200);

        assertEquals(1, list.getTotal());
        assertEquals(20, list.getLimit());

        Customer customer = list.getData().get(0);
        assertEquals("customer", customer.getObject());
        assertEquals(CUSTOMER_ID, customer.getId());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("John Doe (id: 30)", customer.getDescription());
    }
}