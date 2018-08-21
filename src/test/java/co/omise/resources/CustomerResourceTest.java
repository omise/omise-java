package co.omise.resources;

import co.omise.models.Customer;
import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import org.junit.Test;

import java.io.IOException;

public class CustomerResourceTest extends ResourceTest {
    private static final String CUSTOMER_ID = "cust_test_4yq6txdpfadhbaqnwp3";

    @Test
    public void testGetList() throws IOException, OmiseException {
        ScopedList<Customer> list = resource().list();
        assertRequested("GET", "/customers", 200);

        assertEquals(1, list.getTotal());
        assertEquals(20, list.getLimit());

        Customer customer = list.getData().get(0);
        assertEquals("customer", customer.getObject());
        assertEquals(CUSTOMER_ID, customer.getId());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("John Doe (id: 30)", customer.getDescription());
    }

    private CustomerResource resource() {
        return new CustomerResource(testClient());
    }
}